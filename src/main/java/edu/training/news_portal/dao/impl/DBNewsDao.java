package edu.training.news_portal.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import edu.training.news_portal.beans.News;
import edu.training.news_portal.dao.DaoException;
import edu.training.news_portal.dao.DaoRuntimeException;
import edu.training.news_portal.dao.NewsDao;
import edu.training.news_portal.dao.pool.ConnectionPool;

public class DBNewsDao implements NewsDao {

	private final ConnectionPool pool = ConnectionPool.getInstance();

	private static final String SELECT_TOP_NEWS_SQL = "SELECT * FROM news ORDER BY publish_date DESC LIMIT ?";

	@Override
	public List<News> takeTopNews(int count) throws DaoException {
		List<News> newsList = new ArrayList<>();
		try (Connection con = pool.takeConnection(); PreparedStatement ps = con.prepareStatement(SELECT_TOP_NEWS_SQL)) {

			ps.setInt(1, count);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					newsList.add(mapRowToNews(rs));
				}
			}

		} catch (SQLException e) {
			throw new DaoException("Error while retrieving top news", e);
		}
		return newsList;

	}

	private static final String SELECT_ALL_NEWS_SQL = "SELECT * FROM news ORDER BY publish_date DESC";

	@Override
	public List<News> findAllNews() throws DaoException {
		List<News> newsList = new ArrayList<>();
		try (Connection con = pool.takeConnection(); PreparedStatement ps = con.prepareStatement(SELECT_ALL_NEWS_SQL);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				newsList.add(mapRowToNews(rs));
			}

		} catch (SQLException e) {
			throw new DaoException("Error while retrieving all news", e);
		}
		return newsList;
	}

	private static final String SELECT_BY_ID_SQL = "SELECT * FROM news WHERE idnews=?";

	@Override
	public Optional<News> findById(int id) throws DaoException {
		try (Connection con = pool.takeConnection(); PreparedStatement ps = con.prepareStatement(SELECT_BY_ID_SQL)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					News news = mapRowToNews(rs);

					String contentPath = news.getContentPath();
					Optional<String> fileContent = readContentFromFile(contentPath);

					fileContent.ifPresent(news::setContent);

					return Optional.of(news);
				} else {
					return Optional.empty();
				}
			}

		} catch (SQLException e) {
			throw new DaoException("Error while retrieving news by ID", e);
		}
	}

	private static final String INSERT_NEWS_SQL = "INSERT INTO news(title, brief, cont_path, publish_date, news_status_id) VALUES (?, ?, ?, ?, ?)";

	@Override
	public void addNews(News news) throws DaoException {

		Connection con = null;
		try {
			con = pool.takeConnection();
			con.setAutoCommit(false);
			try (PreparedStatement ps = con.prepareStatement(INSERT_NEWS_SQL, Statement.RETURN_GENERATED_KEYS)) {

				ps.setString(1, news.getTitle());
				ps.setString(2, news.getBrief());
				ps.setString(3, news.getContentPath());
				ps.setString(4, news.getPublishDate());
				ps.setInt(5, news.getStatusId());

				int affectedRows = ps.executeUpdate();
				if (affectedRows == 0) {
					throw new DaoException("Failed to add news — no rows affected");
				}

				try (ResultSet rs = ps.getGeneratedKeys()) {
					if (rs.next()) {
						news.setId(rs.getInt(1));
					}
				}
			}

			con.commit();

		} catch (SQLException e) {
			rollbackQuietly(con);
			throw new DaoException("Error while adding news.", e);
		} finally {
			if (con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			}
		}

	}

	private static final String UPDATE_NEWS_SQL = "UPDATE news SET title=?, brief=?, cont_path=?, publish_date=?, news_status_id=? WHERE idnews=?";

	@Override
	public void updateNews(News news) throws DaoException {

		Connection con = null;
		try {
			con = pool.takeConnection();
			con.setAutoCommit(false);

			try (PreparedStatement ps = con.prepareStatement(UPDATE_NEWS_SQL)) {

				ps.setString(1, news.getTitle());
				ps.setString(2, news.getBrief());
				ps.setString(3, news.getContentPath());
				ps.setString(4, news.getPublishDate());
				ps.setInt(5, news.getStatusId());
				ps.setInt(6, news.getId());

				int affectedRows = ps.executeUpdate();
				if (affectedRows == 0) {
					throw new DaoException("Failed to update news — no rows affected");
				}
			}
			con.commit();

		} catch (SQLException e) {
			rollbackQuietly(con);
			throw new DaoException("Error while updating news.", e);
		} finally {
			if (con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			}
		}

	}
	
	private static final String DELETE_NEWS_SQL = "DELETE FROM news WHERE idnews=?";

	@Override
	public void deleteNews(int id) throws DaoException {

		Connection con = null;
		try {
			con = pool.takeConnection();
			con.setAutoCommit(false);

			try (PreparedStatement ps = con.prepareStatement(DELETE_NEWS_SQL)) {

				ps.setInt(1, id);

				int affectedRows = ps.executeUpdate();
				if (affectedRows == 0) {
					throw new DaoException("Failed to update news — no rows affected.");
				}
			}

		} catch (SQLException e) {
			rollbackQuietly(con);
			throw new DaoException("Error while deleting news", e);
		} finally {
			if (con != null) {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException e) {
					throw new DaoException(e);
				}
			}
		}

	}
	
	private static final String COUNT_ALL_SQL = "SELECT COUNT(*) FROM news";
   	
	@Override
	public long countAllNews() throws DaoException {
		try (Connection con = pool.takeConnection(); PreparedStatement ps = con.prepareStatement(COUNT_ALL_SQL);
	             
				ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {
	                return rs.getLong(1);
	            } else {
	                return 0;
	            }

	        } catch (SQLException e) {
	            throw new DaoException("Error while counting news", e);
	        }
	}
	
	 private static final String FIND_PAGE_SQL = "SELECT * FROM news ORDER BY publish_date DESC LIMIT ? OFFSET ?";

	@Override
	public List<News> findPage(int offset, int limit) throws DaoException {
		List<News> newsList = new ArrayList<>();
        try (Connection con = pool.takeConnection(); PreparedStatement ps = con.prepareStatement(FIND_PAGE_SQL)) {

            ps.setInt(1, limit);
            ps.setInt(2, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    newsList.add(mapRowToNews(rs));
                }
            }

        } catch (SQLException e) {
            throw new DaoException("Error while retrieving news page", e);
        }
        return newsList;
	}

	private News mapRowToNews(ResultSet rs) throws SQLException {
		News news = new News();
		news.setId(rs.getInt("idnews"));
		news.setTitle(rs.getString("title"));
		news.setBrief(rs.getString("brief"));
		news.setContentPath(rs.getString("cont_path"));
		news.setPublishDate(rs.getString("publish_date"));
		news.setStatusId(rs.getInt("news_status_id"));
		return news;
	}

	private Optional<String> readContentFromFile(String filePath) {
	    try (InputStream is = getClass().getClassLoader().getResourceAsStream("news_files/" + filePath)) {
	        if (is == null) {
	            throw new DaoRuntimeException("File not found: " + filePath);
	        }
	        String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
	        return Optional.of(content);
	    } catch (IOException e) {
	        throw new DaoRuntimeException("Error reading file: " + filePath, e);
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
	
}
