package edu.training.news_portal.web.concrete.impl;

import java.io.IOException;

import edu.training.news_portal.web.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageAddNews implements Command {

	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("defaultStatus", 2); // 2 = черновик
		request.setAttribute("currentDate", java.time.LocalDate.now().toString());

		
		request.getRequestDispatcher("WEB-INF/jsp/addNews.jsp").forward(request, response);
	}
}
