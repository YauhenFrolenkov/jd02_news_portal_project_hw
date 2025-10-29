package edu.training.news_portal.dao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.training.news_portal.dao.DaoRuntimeException;
import edu.training.news_portal.dao.pool.ConnectionPool;

public class NewsReferenceData {
	public static final int STATUS_DRAFT_ID;
    public static final int STATUS_PUBLISHED_ID;    

    private NewsReferenceData() { 
    	
    }

    static {
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            STATUS_DRAFT_ID = getId(connection, "SELECT id FROM news_status WHERE title = 'draft'");
            STATUS_PUBLISHED_ID = getId(connection, "SELECT id FROM news_status WHERE title = 'published'");           
        } catch (SQLException e) {
            throw new DaoRuntimeException("Ошибка при инициализации NewsReferenceData", e);
        }
    }

    private static int getId(Connection connection, String sql) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Не найдено значение по запросу: " + sql);
            }
        }
    }
}


