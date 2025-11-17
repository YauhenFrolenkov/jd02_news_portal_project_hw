package edu.training.news_portal.web.concrete.impl;

import java.io.IOException;
import java.util.Optional;

import edu.training.news_portal.beans.News;
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

public class PageViewNews implements Command {

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
			
			Optional<News> newsOpt = newsService.findById(newsId);

			if (newsOpt.isEmpty()) {
				response.sendRedirect("NewsPortalController?command=page_news_list&error=true");
				return;
			}
			
			Optional<Integer> userIdOpt = authorsService.findUserIdByNewsId(newsId);  // Для добавления автора в новость
			if (userIdOpt.isPresent()) {
	            Optional<User> authorOpt = userService.findById(userIdOpt.get());
	            authorOpt.ifPresent(author -> request.setAttribute("author", author));
	        }

			request.setAttribute("news", newsOpt.get());
			request.getRequestDispatcher("WEB-INF/jsp/viewNews.jsp").forward(request, response);

		} catch (NumberFormatException | ServiceException e) {
			response.sendRedirect("NewsPortalController?command=page_news_list&error=true");
		}

	}

}
