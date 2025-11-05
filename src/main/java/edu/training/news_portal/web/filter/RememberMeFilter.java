package edu.training.news_portal.web.filter;

import java.io.IOException;
import java.util.Optional;

import edu.training.news_portal.beans.User;
import edu.training.news_portal.service.ServiceException;
import edu.training.news_portal.service.ServiceProvider;
import edu.training.news_portal.service.UserSecurity;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/NewsPortalController")
public class RememberMeFilter extends HttpFilter implements Filter {

	private final UserSecurity security = ServiceProvider.getInstance().getUserSecurity();

	public RememberMeFilter() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest)request).getSession(false);
	    	    
	    if (session != null && session.getAttribute("auth") != null) { // Если пользователь уже авторизован — пропускаем 																	// дальше
			chain.doFilter(request, response);
			return;
		}
		
		Cookie[] cookies = ((HttpServletRequest)request).getCookies(); // Проверяем наличие cookies
		if (cookies == null) {
			chain.doFilter(request, response);
			return;
		}

		Cookie rememberMeCookie = findRememberMe(cookies); // Ищем cookie "remember-me"
		if (rememberMeCookie == null || rememberMeCookie.getValue().isBlank()) {
			chain.doFilter(request, response);
			return;
		}
		
		try { 
			restoreUser(((HttpServletRequest)request), rememberMeCookie.getValue()); // Восстанавливаем пользователя по cookie
		} catch (ServiceException e) {
			throw new ServletException("Error in RememberMeFilter while searching for user by email: ", e);
		}
		
		System.out.println("[RememberMeFilter] Passing to next filter/controller");
		chain.doFilter(request, response);
		System.out.println("[RememberMeFilter] Returned from next filter/controller");

	}
	
	
    private Cookie findRememberMe(Cookie[] cookies) { // Находим cookie "remember-me"
        for (Cookie c : cookies) {
            if ("remember-me".equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

 
    private void restoreUser(HttpServletRequest request, String email) throws ServiceException { // Восстановление пользователя и установка в сессию
        Optional<User> maybeUser = security.findByEmail(email);
        if (maybeUser.isPresent()) {
            HttpSession session = request.getSession(true);
            session.setAttribute("auth", maybeUser.get());
        }
    }
    
 
    public void init(FilterConfig fConfig) throws ServletException {

	}

}
