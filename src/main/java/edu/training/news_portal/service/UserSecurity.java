package edu.training.news_portal.service;

import java.util.Optional;

import edu.training.news_portal.beans.RegistrationInfo;
import edu.training.news_portal.beans.User;

public interface UserSecurity {
	
	abstract Optional<User> signIn(String login, String password) throws ServiceException;
	
	boolean signUp(RegistrationInfo regInfo) throws ServiceException;
	
	Optional<User> findByEmail(String email) throws ServiceException;

}
