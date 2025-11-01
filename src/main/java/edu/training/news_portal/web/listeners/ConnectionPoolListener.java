package edu.training.news_portal.web.listeners;

import java.sql.SQLException;

import edu.training.news_portal.dao.DaoRuntimeException;
import edu.training.news_portal.dao.pool.ConnectionPool;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ConnectionPoolListener implements ServletContextListener {

	public ConnectionPoolListener() {

	}

	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		try {

			ConnectionPool.getFirstInstance("jdbc:mysql://localhost:3306/nova_news_6?useSSL=false", "root", "1234", 5);

		} catch (SQLException | ClassNotFoundException e) {
			String errorMessage = "Connection pool initialization error. " + e;
			context.setAttribute("initError", errorMessage);
			throw new DaoRuntimeException(errorMessage, e);

		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		try {
			ConnectionPool.getInstance().close();
		} catch (SQLException e) {
			throw new DaoRuntimeException("Error while closing the connection pool.", e);

		}
	}

}
