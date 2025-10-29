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
			throw new DaoException("Ошибка при проверке пользователя", e);
		}
	}

	private boolean checkPassword(ResultSet rs, String password) {
		try {
			String hashedPassword = rs.getString("password");
			return BCrypt.checkpw(password, hashedPassword);
		} catch (SQLException e) {
			throw new DaoRuntimeException("Ошибка при получении пароля из ResultSet", e);
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

	private static final String INSERT_USER_SQL = "INSERT INTO users (email, password, roles_id, user_status_id, date_registration) VALUES (?, ?, ?, ?, ?)";
	private static final String INSERT_USER_DETAILES_SQL = "INSERT INTO user_details (users_id, name, surname) VALUES (?, ?, ?)";

	@Override
	public boolean registration(RegistrationInfo regInfo) throws DaoException {

		Connection connection = null;

		try {
			connection = pool.takeConnection();
			connection.setAutoCommit(false);
			System.out.println("Соединение получено, начинаем регистрацию...");

			try (PreparedStatement psUser = connection.prepareStatement(INSERT_USER_SQL,
					PreparedStatement.RETURN_GENERATED_KEYS);
					PreparedStatement psDetails = connection.prepareStatement(INSERT_USER_DETAILES_SQL)) {

				String hashedPassword = BCrypt.hashpw(regInfo.getPassword(), BCrypt.gensalt(12));
				System.out.println("Пароль захэширован");

				psUser.setString(1, regInfo.getEmail());
				psUser.setString(2, hashedPassword);
				psUser.setInt(3, UserReferenceData.ROLE_USER_ID);
				psUser.setInt(4, UserReferenceData.USER_STATUS_ACTIVE_ID);
				psUser.setDate(5, new java.sql.Date(System.currentTimeMillis()));

				int affectedRows = psUser.executeUpdate();
				if (affectedRows == 0) {
					throw new DaoException("Не удалось вставить пользователя, нет затронутых строк.");
				}
				 System.out.println("Пользователь вставлен, строк: " + affectedRows);

				int userId;
				try (ResultSet rs = psUser.getGeneratedKeys()) {
					if (!rs.next()) {
						throw new DaoException("Не удалось получить ID пользователя.");
					}
					userId = rs.getInt(1);
					System.out.println("Получен userId: " + userId);
				}

				psDetails.setInt(1, userId);
				psDetails.setString(2, regInfo.getName());
				psDetails.setString(3, regInfo.getSurname());
				psDetails.executeUpdate();
				System.out.println("user_details вставлен");

				connection.commit();
				 System.out.println("Транзакция зафиксирована");
				return true;

			} catch (SQLException e) {
				if (connection != null) {
					try {
						connection.rollback();
						 System.out.println("Откат транзакции");
					} catch (SQLException ex) {
						throw new DaoException("Ошибка при откате транзакции", ex);
					}
				}
				throw new DaoException("Ошибка при регистрации пользователя", e);

			}
		} catch (SQLException e) {
			throw new DaoException(e);

		} finally {
			if (connection != null) {
				try {
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
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
