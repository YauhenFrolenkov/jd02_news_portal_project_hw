package edu.training.news_portal.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import edu.training.news_portal.web.concrete.CommandProvider;
import edu.training.news_portal.web.concrete.RequestPath;

@WebServlet("/NewsPortalController")
public class NewsPortalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommandProvider provider = new CommandProvider();

	public NewsPortalController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		process(request, response);

	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(request.getParameter("command"));

		String commandParam = request.getParameter("command");

		if (commandParam == null) {
			commandParam = RequestPath.NO_COMMAND.name();
		}
		provider.take(commandParam).execute(request, response);

	}

}
