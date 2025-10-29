package edu.training.news_portal.dao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.training.news_portal.dao.DaoRuntimeException;
import edu.training.news_portal.dao.pool.ConnectionPool;

public class UserReferenceData {

	public static final int ROLE_USER_ID;
	public static final int ROLE_ADMIN_ID;
	public static final int USER_STATUS_ACTIVE_ID;
	public static final int USER_STATUS_BLOCKED_ID;

	private UserReferenceData() {

	}

	static {
		try (Connection connection = ConnectionPool.getInstance().takeConnection()) {

			ROLE_USER_ID = getId(connection, "SELECT id FROM roles WHERE name = 'user'");
			ROLE_ADMIN_ID = getId(connection, "SELECT id FROM roles WHERE name = 'admin'");
			USER_STATUS_ACTIVE_ID = getId(connection, "SELECT id FROM user_status WHERE user_status = 'active'");
			USER_STATUS_BLOCKED_ID = getId(connection, "SELECT id FROM user_status WHERE user_status = 'blocked'");

			
		} catch (SQLException e) {
			throw new DaoRuntimeException("Ошибка при инициализации", e);
		}
	}

	private static int getId(Connection connection, String sql) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new SQLException("Не найдено значение по запросу: " + sql);
			}
		}
	}

}
