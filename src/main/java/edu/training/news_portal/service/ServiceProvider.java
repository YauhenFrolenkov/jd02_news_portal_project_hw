package edu.training.news_portal.service;

import edu.training.news_portal.service.impl.NewsPortalUserSecurity;
import edu.training.news_portal.service.impl.NewsServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();
	
	private final UserSecurity security = new NewsPortalUserSecurity();
	
	private final NewsService newsService = new NewsServiceImpl();
	
	private ServiceProvider() {}
	
	public UserSecurity getUserSecurity() {
		return security;
	}
	
	public NewsService getNewsService() {
		return newsService;
	}
	
	public static ServiceProvider getInstance() {
		return instance;
	}
	
	

}
