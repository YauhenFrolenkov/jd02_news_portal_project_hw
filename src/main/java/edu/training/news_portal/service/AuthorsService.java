package edu.training.news_portal.service;

import java.util.Optional;

public interface AuthorsService {
	
	void addAuthorToNews(int userId, int newsId) throws ServiceException;
    void deleteAuthorFromNews(int newsId) throws ServiceException;   
    Optional<Integer> findUserIdByNewsId(int newsId) throws ServiceException; 

}
