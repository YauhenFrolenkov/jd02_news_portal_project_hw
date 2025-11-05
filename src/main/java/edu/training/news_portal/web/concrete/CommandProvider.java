package edu.training.news_portal.web.concrete;

import java.util.HashMap;
import java.util.Map;

import edu.training.news_portal.web.concrete.impl.*;

public class CommandProvider {

	private final Map<RequestPath, Command> commands = new HashMap<>();

	public CommandProvider() {
		commands.put(RequestPath.PAGE_MAIN, new PageMain());
		commands.put(RequestPath.PAGE_AUTH, new PageAuth());
		commands.put(RequestPath.PAGE_REGISTRATION, new PageRegistration());
		commands.put(RequestPath.PAGE_USER_HOME, new PageUserHome());
		commands.put(RequestPath.DO_AUTH, new DoAuth());
		commands.put(RequestPath.DO_REGISTRATION, new DoRegistration());
		commands.put(RequestPath.NO_COMMAND, new NoCommand());
		commands.put(RequestPath.PAGE_NEWS_LIST, new PageNewsList());
		commands.put(RequestPath.PAGE_ADD_NEWS, new PageAddNews());
		commands.put(RequestPath.PAGE_EDIT_NEWS, new PageEditNews());
		commands.put(RequestPath.PAGE_VIEW_NEWS, new PageViewNews());
		commands.put(RequestPath.DO_ADD_NEWS, new DoAddNews());
		commands.put(RequestPath.DO_EDIT_NEWS, new DoEditNews());
		commands.put(RequestPath.DO_DELETE_NEWS, new DoDeleteNews());
		commands.put(RequestPath.DO_LOGOUT, new DoLogout());
	}
	
	public Command take(String path) {

		try {
			RequestPath pathName = RequestPath.valueOf(path.toUpperCase());
			
			Command command = commands.get(pathName);
			
			return (command != null) ? command : new NoCommand();
		} catch (IllegalArgumentException | NullPointerException e) {
			
			return new NoCommand();
		}
	}

}
