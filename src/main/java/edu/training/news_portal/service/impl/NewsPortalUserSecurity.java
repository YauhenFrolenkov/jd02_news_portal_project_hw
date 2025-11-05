package edu.training.news_portal.service.impl;

import java.util.Optional;

import edu.training.news_portal.beans.RegistrationInfo;
import edu.training.news_portal.beans.User;
import edu.training.news_portal.dao.DaoException;
import edu.training.news_portal.dao.DaoProvider;
import edu.training.news_portal.dao.UserDao;
import edu.training.news_portal.dao.util.UserReferenceData;
import edu.training.news_portal.service.ServiceException;
import edu.training.news_portal.service.UserSecurity;
import edu.training.news_portal.util.RegistrationValidator;
import edu.training.news_portal.util.ValidatorProvider;

public class NewsPortalUserSecurity implements UserSecurity {

	private final UserDao userDao = DaoProvider.getInstance().getUserDao();
	private final RegistrationValidator validator = ValidatorProvider.getInstance().getRegistrationValidator();
	

	@Override
	public Optional<User> signIn(String email, String password) throws ServiceException {

		if (email == null || password == null || email.isBlank() || password.isBlank()) {
			throw new ServiceException("Email and password cannot be empty");
		}

		try {
			Optional<User> maybeUser = userDao.checkControl(email, password);

			User user = maybeUser.orElseThrow(() -> new ServiceException("User not found"));

			
			if (user.getStatusId() == UserReferenceData.USER_STATUS_BLOCKED_ID) {
				throw new ServiceException("User is blocked");
			}

			return Optional.of(user);

		} catch (DaoException e) {
			throw new ServiceException("User login error", e);
		}
	}

	@Override
	public boolean signUp(RegistrationInfo regInfo) throws ServiceException {

		try {

			if (!validator.isValid(regInfo)) {
				throw new ServiceException("Invalid registration data");
			}

			return userDao.registration(regInfo);

		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Optional<User> findByEmail(String email) throws ServiceException {
	    if (email == null || email.isBlank()) {
	        return Optional.empty();
	    }
	    try {
	        return userDao.findByEmail(email); 
	    } catch (DaoException e) {
	        throw new ServiceException("Error searching for user by email", e);
	    }
	}

}
