package edu.training.news_portal.web.concrete.impl;

import java.io.IOException;
import java.util.List;

import edu.training.news_portal.beans.News;
import edu.training.news_portal.service.NewsService;
import edu.training.news_portal.service.ServiceException;
import edu.training.news_portal.service.ServiceProvider;
import edu.training.news_portal.web.concrete.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageUserHome implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<News> news;
		try {
			news = newsService.takeTopNews(3);
			request.setAttribute("topNews", news);

			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userHome.jsp");
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect("NewsPortalController?command=no_command");
		}
	}

}
