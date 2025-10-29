package edu.training.news_portal.dao;

import java.util.Optional;

import edu.training.news_portal.beans.RegistrationInfo;
import edu.training.news_portal.beans.User;

public interface UserDao {
	
	Optional<User> checkControl(String email, String password) throws DaoException;
	
	boolean registration(RegistrationInfo regInfo) throws DaoException;

	Optional<User> findByEmail(String email) throws DaoException;
	
		
	}


