package edu.training.news_portal.web.filter.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SecurityFilterConfig {
	
	public static final Set<String> PUBLIC_COMMANDS;
	
	private SecurityFilterConfig() {
		
    }
	
	static {
        Set<String> cmds = new HashSet<>(Arrays.asList(
                "PAGE_AUTH",
                "PAGE_REGISTRATION",
                "PAGE_MAIN",
                "DO_AUTH",
                "DO_REGISTRATION",
                "PAGE_NEWS_LIST",
                "PAGE_VIEW_NEWS" 
        ));
        
        PUBLIC_COMMANDS = Collections.unmodifiableSet(cmds);
    }

    
}


