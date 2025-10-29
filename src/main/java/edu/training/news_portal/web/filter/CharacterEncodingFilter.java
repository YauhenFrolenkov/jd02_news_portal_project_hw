package edu.training.news_portal.web.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

@WebFilter("/NewsPortalController") 
public class CharacterEncodingFilter extends HttpFilter implements Filter {
	
	public CharacterEncodingFilter() {
        super();        
    }

	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("CharacterEncodingFilter before");		
		chain.doFilter(request, response);
		System.out.println("CharacterEncodingFilter after");
		
	}


	public void init(FilterConfig fConfig) {		 
	}

}
