package edu.training.news_portal.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import edu.training.news_portal.dao.AuthorsDao;
import edu.training.news_portal.dao.DaoException;
import edu.training.news_portal.dao.pool.ConnectionPool;

public class DBAuthorsDao implements AuthorsDao {

	private final ConnectionPool pool = ConnectionPool.getInstance();

	private static final String INSERT_AUTHORS_SQL = "INSERT INTO authors (users_id, news_idnews) VALUES (?, ?)";

	@Override
	public void addAuthorToNews(int userId, int newsId) throws DaoException {
		try (Connection con = pool.takeConnection(); PreparedStatement ps = con.prepareStatement(INSERT_AUTHORS_SQL)) {

			ps.setInt(1, userId);
			ps.setInt(2, newsId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("Error while adding author to news.", e);
		}

	}

	private static final String DELETE_AUTHORS_SQL = "DELETE FROM authors WHERE news_idnews = ?";

	@Override
	public void deleteAuthorFromNews(int newsId) throws DaoException {
		try (Connection con = pool.takeConnection(); PreparedStatement ps = con.prepareStatement(DELETE_AUTHORS_SQL)) {

			ps.setInt(1, newsId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("Error while deleting author from news.", e);
		}

	}
	
	private static final String FIND_USER_BY_NEWS_ID = "SELECT users_id FROM authors WHERE news_idnews = ?";

	@Override
	public Optional<Integer> findUserIdByNewsId(int newsId) throws DaoException {
		try (Connection con = pool.takeConnection(); PreparedStatement ps = con.prepareStatement(FIND_USER_BY_NEWS_ID)) {
		        ps.setInt(1, newsId);
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		            return Optional.of(rs.getInt("users_id"));
		        } else {
		            return Optional.empty();
		        }
		    } catch (SQLException e) {
		        throw new DaoException(e);
		    }
	}

}
