package edu.training.news_portal.util;

import java.util.regex.Pattern;

import edu.training.news_portal.beans.RegistrationInfo;

public class RegistrationValidator {
	
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[А-ЯA-Z][а-яa-zёЁ-]{1,30}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+=-]{6,}$");
    
    private RegistrationValidator() {}
    
    public static boolean isValid(RegistrationInfo info) {
        if (info == null) return false;

        return isValidEmail(info.getEmail()) &&
               isValidPassword(info.getPassword()) &&
               isValidName(info.getName()) &&
               isValidSurname(info.getSurname());
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name).matches();
    }

    public static boolean isValidSurname(String surname) {
        return surname != null && NAME_PATTERN.matcher(surname).matches();
    }
}


