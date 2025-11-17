package edu.training.news_portal.dao;

import java.util.Optional;

public interface AuthorsDao {
	
	void addAuthorToNews(int userId, int newsId) throws DaoException;
	void deleteAuthorFromNews(int newsId) throws DaoException;
	Optional<Integer> findUserIdByNewsId(int newsId) throws DaoException;

}
