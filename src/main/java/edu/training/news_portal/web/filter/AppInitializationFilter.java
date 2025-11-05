package edu.training.news_portal.web.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class AppInitializationFilter implements Filter {

	public AppInitializationFilter() {
		super();
	}

	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		ServletContext context = httpRequest.getServletContext();

		String initializationError = (String) context.getAttribute("initializationError"); // Проверяем, была ли ошибка при инициализации приложения в ConnectionPoolListener

		if (initializationError != null) {
			System.out.println("Фильтр перехватил ошибку инициализации: " + initializationError);
			request.setAttribute("errorMessage", initializationError);  // Если была ошибка при старте приложения, показываем страницу ошибок
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
			return; 
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
