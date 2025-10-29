package edu.training.news_portal.web.filter;

import java.io.IOException;

import edu.training.news_portal.beans.User;
import edu.training.news_portal.web.filter.security.SecurityFilterConfig;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*") 
public class SecurityFilter implements Filter { 
	
	public  SecurityFilter() {
		super();
	}

	public void destroy() {
	}


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String command = httpRequest.getParameter("command");
        HttpSession session = httpRequest.getSession(false);
        
        User user = (session != null) ? (User) session.getAttribute("auth") : null;
        
        boolean isPublic = (command == null) || SecurityFilterConfig.PUBLIC_COMMANDS.contains(command.toUpperCase());

        if (!isPublic && user == null) {
        	
            httpResponse.sendRedirect("NewsPortalController?command=page_auth&message=need_auth");
            return;
        }
        
        System.out.println("[SecurityFilter] Passing to next filter/controller");	
        chain.doFilter(request, response);
        System.out.println("[SecurityFilter] Returned from next filter/controller");
		
		
		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {

	}
	
	

}
