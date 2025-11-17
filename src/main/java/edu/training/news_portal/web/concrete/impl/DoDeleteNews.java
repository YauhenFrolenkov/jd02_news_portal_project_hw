package edu.training.news_portal.web.concrete.impl;

import java.io.IOException;

import edu.training.news_portal.beans.User;
import edu.training.news_portal.service.AuthorsService;
import edu.training.news_portal.service.NewsService;
import edu.training.news_portal.service.ServiceException;
import edu.training.news_portal.service.ServiceProvider;
import edu.training.news_portal.service.UserSecurity;
import edu.training.news_portal.web.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoDeleteNews implements Command{
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final AuthorsService authorsService = ServiceProvider.getInstance().getAuthorsService();
	private final UserSecurity userService = ServiceProvider.getInstance().getUserSecurity();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            response.sendRedirect("NewsPortalController?command=page_news_list&error=true");
            return;
        }

        try {
        	int newsId = Integer.parseInt(idParam);
        	User user = (User) request.getSession(false).getAttribute("auth");
            int userId = user.getId();
            
            if (!userService.isRoleAdmin(userId) && !userService.isRoleReporter(userId, newsId)) { // Проверка прав
                response.sendRedirect("NewsPortalController?command=page_news_list&error=unauthorized");
                return;
            }
            
        	authorsService.deleteAuthorFromNews(newsId);
            newsService.deleteNews(newsId);            
            response.sendRedirect("NewsPortalController?command=page_news_list&deleted=true");
        } catch (NumberFormatException | ServiceException e) {
            response.sendRedirect("NewsPortalController?command=page_news_list&error=true");
        }
    }
}
