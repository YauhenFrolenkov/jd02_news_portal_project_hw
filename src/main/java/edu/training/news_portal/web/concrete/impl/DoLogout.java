package edu.training.news_portal.web.concrete.impl;

import java.io.IOException;

import edu.training.news_portal.web.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoLogout implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate(); // Закрываем сессию
		}

		Cookie cookie = new Cookie("remember-me", ""); // Удаляем cookie "remember-me"
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		System.out.println("[RememberMeFilter] remember-me cookie removed.");

		response.sendRedirect("NewsPortalController?command=page_main");

	}

}
