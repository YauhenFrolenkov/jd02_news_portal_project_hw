package edu.training.news_portal.web.concrete.impl;

import java.io.IOException;
import java.time.LocalDate;

import edu.training.news_portal.beans.News;
import edu.training.news_portal.beans.NewsBuilder;
import edu.training.news_portal.beans.User;
import edu.training.news_portal.service.AuthorsService;
import edu.training.news_portal.service.NewsService;
import edu.training.news_portal.service.ServiceException;
import edu.training.news_portal.service.ServiceProvider;
import edu.training.news_portal.service.UserSecurity;
import edu.training.news_portal.util.NewsValidator;
import edu.training.news_portal.web.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoAddNews implements Command {
	
	 private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	 private final UserSecurity userService = ServiceProvider.getInstance().getUserSecurity();
	 private final AuthorsService authorsService = ServiceProvider.getInstance().getAuthorsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("auth");
        int userId = user.getId();
				
		News news = new NewsBuilder()
                .setTitle(request.getParameter("title"))
                .setBrief(request.getParameter("brief"))
                .setContentPath(request.getParameter("contentPath"))
                .setPublishDate(LocalDate.now().toString())
                .setStatusId("publish".equalsIgnoreCase(request.getParameter("action")) ? 1 : 2)
                .build();

        try {
        	if (!NewsValidator.getInstance().isValid(news)) {
                response.sendRedirect("NewsPortalController?command=page_add_news&error=true");
                return;
            }
            
        	newsService.addNews(news);
        	userService.promoteFromUserToReporter(userId);
            authorsService.addAuthorToNews(userId, news.getId());
            response.sendRedirect("NewsPortalController?command=page_news_list&added=true");

        } catch (ServiceException e) {
            response.sendRedirect("NewsPortalController?command=page_add_news&error=true");
        }
    }
	
}


