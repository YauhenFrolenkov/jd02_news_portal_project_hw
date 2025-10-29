package edu.training.news_portal.dao;

public class DaoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public DaoException() {
		super();
	}
	
	public DaoException(Exception ex) {
		super(ex);
	}
	
	public DaoException(String e) {
		super(e);
	}
	
	public DaoException(String e, Exception ex) {
		super(e, ex);
	}
	

}
