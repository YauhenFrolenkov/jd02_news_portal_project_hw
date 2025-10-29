package edu.training.news_portal.web.concrete.impl;

import java.io.IOException;
import java.util.List;

import edu.training.news_portal.beans.News;
import edu.training.news_portal.beans.Page;
import edu.training.news_portal.service.NewsService;
import edu.training.news_portal.service.ServiceException;
import edu.training.news_portal.service.ServiceProvider;
import edu.training.news_portal.web.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageNewsList implements Command {

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String pageParam = request.getParameter("page");
			 String sizeParam = request.getParameter("pageSize");
			 
			int pageNumber = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
			int pageSize = (sizeParam != null) ? Integer.parseInt(sizeParam) : 3;

			Page<News> page = newsService.findNewsPage(pageNumber, pageSize);

			request.setAttribute("page", page);
			request.setAttribute("newsList", page.getContent());
			request.getRequestDispatcher("WEB-INF/jsp/newsList.jsp").forward(request, response);

		} catch (ServiceException e) {
			response.sendRedirect("NewsPortalController?command=page_main&error=true");
		}

	}

}
