package edu.training.news_portal.web.concrete.impl;

import java.io.IOException;

import edu.training.news_portal.service.NewsService;
import edu.training.news_portal.service.ServiceException;
import edu.training.news_portal.service.ServiceProvider;
import edu.training.news_portal.web.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoDeleteNews implements Command{
	
	 private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            response.sendRedirect("NewsPortalController?command=page_news_list&error=true");
            return;
        }

        int newsId;
        try {
            newsId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("NewsPortalController?command=page_news_list&error=true");
            return;
        }

        try {
            newsService.deleteNews(newsId);
            response.sendRedirect("NewsPortalController?command=page_news_list&deleted=true");
        } catch (ServiceException e) {
            response.sendRedirect("NewsPortalController?command=page_news_list&error=true");
        }
    }
}
