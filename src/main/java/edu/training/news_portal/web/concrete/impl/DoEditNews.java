package edu.training.news_portal.web.concrete.impl;

import java.io.IOException;
import java.time.LocalDate;

import edu.training.news_portal.beans.News;
import edu.training.news_portal.beans.NewsBuilder;
import edu.training.news_portal.service.NewsService;
import edu.training.news_portal.service.ServiceException;
import edu.training.news_portal.service.ServiceProvider;
import edu.training.news_portal.util.NewsValidator;
import edu.training.news_portal.web.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoEditNews implements Command{
	
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

        News news = new NewsBuilder()
                .setTitle(request.getParameter("title"))
                .setBrief(request.getParameter("brief"))
                .setContentPath(request.getParameter("contentPath"))
                .setPublishDate(LocalDate.now().toString())
                .setStatusId("publish".equalsIgnoreCase(request.getParameter("action")) ? 1 : 2)
                .build();

        news.setId(newsId);

        try {
        	if (!NewsValidator.getInstance().isValid(news)) {
                response.sendRedirect("NewsPortalController?command=page_edit_news&id=" + newsId + "&error=true");
                return;
            }

            newsService.updateNews(news);
            response.sendRedirect("NewsPortalController?command=page_news_list&updated=true");

        } catch (ServiceException e) {
            response.sendRedirect("NewsPortalController?command=page_edit_news&id=" + newsId + "&error=true");
        }
    }
}