package edu.training.news_portal.web.concrete.impl;

import java.io.IOException;

import edu.training.news_portal.beans.RegistrationInfo;
import edu.training.news_portal.service.ServiceException;
import edu.training.news_portal.service.ServiceProvider;
import edu.training.news_portal.service.UserSecurity;
import edu.training.news_portal.web.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {
	
	private final UserSecurity userSecurity = ServiceProvider.getInstance().getUserSecurity();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		RegistrationInfo.RegBuider builder = new RegistrationInfo.RegBuider();
		
		builder.setEmail(request.getParameter("email")).setPassword(request.getParameter("password")).setName(request.getParameter("name")).setSurname(request.getParameter("surname"));
		
		RegistrationInfo regInfo = builder.build();
		
		try {
			
			userSecurity.signUp(regInfo);
			response.sendRedirect("NewsPortalController?command=page_auth&after_reg=true");
			
		} catch(ServiceException e) {
			response.sendRedirect("NewsPortalController?command=page_registration&registerError=true");
		}
		
	}

}
