package edu.training.news_portal.service.impl;

import java.util.Optional;

import edu.training.news_portal.dao.AuthorsDao;
import edu.training.news_portal.dao.DaoException;
import edu.training.news_portal.dao.DaoProvider;
import edu.training.news_portal.service.AuthorsService;
import edu.training.news_portal.service.ServiceException;

public class AuthorsServiceImpl implements AuthorsService {

	private final AuthorsDao authorsDao = DaoProvider.getInstance().getAuthorsDao();

	@Override
	public void addAuthorToNews(int userId, int newsId) throws ServiceException {
		try {
			authorsDao.addAuthorToNews(userId, newsId);
		} catch (DaoException e) {
			throw new ServiceException("Failed to add author to news", e);
		}

	}

	@Override
	public void deleteAuthorFromNews(int newsId) throws ServiceException {
		try {
			authorsDao.deleteAuthorFromNews(newsId);
		} catch (DaoException e) {
			throw new ServiceException("Failed to delete author from news", e);
		}
	}

	@Override
	public Optional<Integer> findUserIdByNewsId(int newsId) throws ServiceException {
		try {
			return authorsDao.findUserIdByNewsId(newsId);
		} catch (DaoException e) {
			throw new ServiceException("Error finding for userId by newsId", e);
		}
	}

}
