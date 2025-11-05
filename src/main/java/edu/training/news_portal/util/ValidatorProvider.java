package edu.training.news_portal.util;

public class ValidatorProvider {
	
	private static final ValidatorProvider instance = new ValidatorProvider();
	
	private final RegistrationValidator regValidator = RegistrationValidator.getInstance();
	private final NewsValidator newsValidator = NewsValidator.getInstance();

	private ValidatorProvider() {
		
	}

	public NewsValidator getNewsValidator() {
        return newsValidator;
    }

    public RegistrationValidator getRegistrationValidator() {
        return regValidator;
    }

	public static ValidatorProvider getInstance() {
		return instance;
	}

	
	

}
