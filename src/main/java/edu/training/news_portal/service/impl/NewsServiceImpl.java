package edu.training.news_portal.service.impl;

import java.util.List;
import java.util.Optional;

import edu.training.news_portal.beans.News;
import edu.training.news_portal.beans.Page;
import edu.training.news_portal.dao.DaoException;
import edu.training.news_portal.dao.DaoProvider;
import edu.training.news_portal.dao.NewsDao;
import edu.training.news_portal.service.NewsService;
import edu.training.news_portal.service.ServiceException;
import edu.training.news_portal.util.NewsValidator;

public class NewsServiceImpl implements NewsService {
	
	private final NewsDao newsDao = DaoProvider.getInstance().getNewsDao();
	private final NewsValidator validator;
	
	private final int DEFAULT_SIZE = 9;
    private final int MAX_SIZE = 50;
    private final int MAX_TOP_NEWS = 10;
    
    public NewsServiceImpl(NewsValidator validator) {
        this.validator = validator;       
    }

	@Override
	public List<News> takeTopNews(int count) throws ServiceException {
		
		 if (count <= 0 || count > MAX_TOP_NEWS) {
	            throw new ServiceException("Invalid number of top news items: " + count);
	     }
		
		try {
			return newsDao.takeTopNews(count);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> findAllNews() throws ServiceException {
		try {
            return newsDao.findAllNews();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
	}

	@Override
	public Optional<News> findById(int id) throws ServiceException {
		if (id <= 0) {
            throw new ServiceException("News ID must be positive.");
        }
		   try {
        	Optional<News> newsOpt = newsDao.findById(id); 
                   
            return newsOpt;
            
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
	}

	@Override
	public void addNews(News news) throws ServiceException {
		if (!validator.isValid(news)) {
            throw new ServiceException("Invalid news data.");
        }
        try {
            newsDao.addNews(news);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
		
	}

	@Override
	public void updateNews(News news) throws ServiceException {
		if (news.getId() <= 0) {
            throw new ServiceException("News ID must be set for update.");
        }
		if (!validator.isValid(news)) {
            throw new ServiceException("Invalid news data.");
        }
        try {
            newsDao.updateNews(news);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
		
	}

	@Override
	public void deleteNews(int id) throws ServiceException {
		 if (id <= 0) {
	            throw new ServiceException("News ID must be positive.");
	        }
	        try {
	            newsDao.deleteNews(id);
	        } catch (DaoException e) {
	            throw new ServiceException(e);
	        }
		
	}

	@Override
	public Page<News> findNewsPage(int page, int size) throws ServiceException {
		int safeSize = (size <= 0) ? DEFAULT_SIZE : Math.min(size, MAX_SIZE);
        int safePage = Math.max(1, page);

        try {
            long totalItems = newsDao.countAllNews();
            int totalPages = (int) Math.max(1, (totalItems + safeSize - 1) / safeSize);

            
            if (safePage > totalPages) { // Если запросили страницу больше, чем есть — возвращаем последнюю
                safePage = totalPages;
            }

            int offset = (safePage - 1) * safeSize;
            List<News> content = newsDao.findPage(offset, safeSize);

            return new Page<>(content, safePage, safeSize, totalItems);

        } catch (DaoException e) {
            throw new ServiceException("Error while retrieving paginated news", e);
        }
    }

	
	}
	
	 


