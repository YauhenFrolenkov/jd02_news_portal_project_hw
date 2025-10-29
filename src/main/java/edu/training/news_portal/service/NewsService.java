package edu.training.news_portal.service;

import java.util.List;
import java.util.Optional;
import edu.training.news_portal.beans.News;
import edu.training.news_portal.beans.Page;

public interface NewsService {
	
	List<News> takeTopNews(int count) throws ServiceException;
    List<News> findAllNews() throws ServiceException;         
    Optional<News> findById(int id) throws ServiceException;       
    void addNews(News news) throws ServiceException;         
    void updateNews(News news) throws ServiceException;     
    void deleteNews(int id) throws ServiceException;
    Page<News> findNewsPage(int page, int size) throws ServiceException;

}
