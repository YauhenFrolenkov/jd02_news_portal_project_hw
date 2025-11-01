package edu.training.news_portal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import edu.training.news_portal.beans.RegistrationInfo;
import edu.training.news_portal.beans.User;
import edu.training.news_portal.dao.DaoException;
import edu.training.news_portal.dao.DaoRuntimeException;
import edu.training.news_portal.dao.UserDao;
import edu.training.news_portal.dao.pool.ConnectionPool;
import edu.training.news_portal.dao.util.UserReferenceData;

public class DBUserDao implements UserDao {

	private final ConnectionPool pool = ConnectionPool.getInstance();

	private static final String CHECK_USER_SQL = "SELECT u.id, u.email, u.password, u.roles_id, u.user_status_id, d.name, d.surname FROM users u JOIN user_details d ON u.id = d.users_id WHERE u.email = ?";

	@Override
	public Optional<User> checkControl(String email, String password) throws DaoException {

		try (Connection connection = pool.takeConnection();
				PreparedStatement ps = connection.prepareStatement(CHECK_USER_SQL)) {

			ps.setString(1, email);

			try (ResultSet rs = ps.executeQuery()) {
				if (!rs.next()) {
					return Optional.empty(); // нет пользователя
				}

				if (!checkPassword(rs, password)) {
					return Optional.empty(); // пароль не совпадает
				}

				return mapRowToUser(rs);
			}
		} catch (SQLException e) {
			throw new DaoException("Error while verifying user", e);
		}
	}

	private boolean checkPassword(ResultSet rs, String password) {
		try {
			String hashedPassword = rs.getString("password");
			return BCrypt.checkpw(password, hashedPassword);
		} catch (SQLException e) {
			throw new DaoRuntimeException("Error while retrieving password from ResultSet", e);
		}
	}

	private static final String FIND_BY_EMAIL_SQL = "SELECT u.id, u.email, u.roles_id, u.user_status_id, d.name, d.surname FROM users u JOIN user_details d ON u.id = d.users_id WHERE u.email = ?";

	@Override
	public Optional<User> findByEmail(String email) throws DaoException {
		try (Connection connection = pool.takeConnection();
				PreparedStatement ps = connection.prepareStatement(FIND_BY_EMAIL_SQL)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					mapRowToUser(rs);
				}
				return Optional.empty();
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public boolean registration(RegistrationInfo regInfo) throws DaoException {
	    Connection connection = null;

	    try {
	        connection = pool.takeConnection();
	        connection.setAutoCommit(false);
	       
	        String hashedPassword = hashPassword(regInfo.getPassword());  // 1. Хэшируем пароль

	        int userId = insertUser(connection, regInfo, hashedPassword); // 2. Вставляем пользователя и получаем ID

	        insertUserDetails(connection, userId, regInfo); // 3. Вставляем детали пользователя

	        connection.commit();  // 4. Фиксируем транзакцию
	       
	        return true;

	    } catch (SQLException e) {
	        rollbackQuietly(connection);
	         throw new DaoException("Error during user registration", e);

	    } finally {
	        if (connection != null) {
	            try {
	                connection.setAutoCommit(true);
	                connection.close(); // Соединение закрыто и autoCommit восстановлен	                
	            } catch (SQLException e) {
	                throw new DaoException("Error while closing the connection", e);
	            }
	        }
	    }
	}

	private String hashPassword(String password) {
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
		return hashedPassword;
	}

	private static final String INSERT_USER_SQL = "INSERT INTO users (email, password, roles_id, user_status_id, date_registration) VALUES (?, ?, ?, ?, ?)";

	private int insertUser(Connection connection, RegistrationInfo regInfo, String hashedPassword) throws SQLException, DaoException {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, regInfo.getEmail());
			ps.setString(2, hashedPassword);
			ps.setInt(3, UserReferenceData.ROLE_USER_ID);
			ps.setInt(4, UserReferenceData.USER_STATUS_ACTIVE_ID);
			ps.setDate(5, new java.sql.Date(System.currentTimeMillis()));

			int affectedRows = ps.executeUpdate();
			if (affectedRows == 0) {
				throw new DaoException("Failed to insert user, no rows affected");
			}

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (!rs.next()) {
					throw new DaoException("Failed to retrieve user ID");
				}
				int userId = rs.getInt(1);
				return userId;
			}
		}
	}

	private static final String INSERT_USER_DETAILES_SQL = "INSERT INTO user_details (users_id, name, surname) VALUES (?, ?, ?)";

	private void insertUserDetails(Connection connection, int userId, RegistrationInfo regInfo) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_USER_DETAILES_SQL)) {
			ps.setInt(1, userId);
			ps.setString(2, regInfo.getName());
			ps.setString(3, regInfo.getSurname());
			ps.executeUpdate();
		}
	}
	
	private void rollbackQuietly(Connection connection) throws DaoException {
	    if (connection != null) {
	        try {
	            connection.rollback();	            
	        } catch (SQLException ex) {
	        	throw new DaoException("Transaction rollback error", ex);
	        }
	    }
	}

	private Optional<User> mapRowToUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setEmail(rs.getString("email"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		user.setRoleId(rs.getInt("roles_id"));
		user.setStatusId(rs.getInt("user_status_id"));
		return Optional.of(user);
	}

}
