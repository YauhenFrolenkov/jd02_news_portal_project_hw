package edu.training.news_portal.dao;

import java.util.List;
import java.util.Optional;

import edu.training.news_portal.beans.News;

public interface NewsDao {
	
	List<News> takeTopNews(int count) throws DaoException; 
    List<News> findAllNews() throws DaoException;        
    Optional<News> findById(int id) throws DaoException;       
    void addNews(News news) throws DaoException;         
    void updateNews(News news) throws DaoException;      
    void deleteNews(int id) throws DaoException;
    long countAllNews() throws DaoException;
    List<News> findPage(int offset, int limit) throws DaoException;

}
