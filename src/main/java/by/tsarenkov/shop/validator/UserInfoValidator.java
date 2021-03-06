package by.tsarenkov.shop.validator;

import by.tsarenkov.shop.bean.UserRegistrationInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfoValidator {

    private UserRegistrationInfo user;

    private static final String EMAIL_PATTERN = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String SURNAME_PATTERN = "^[a-zA-ZА-Яа-я]{2,}$";;
    private static final String PHONE_NUMBER_PATTERN = "^(\\+375)((29)|(33)|(44))\\d{7}$";

    private static final Pattern validEmailRegex;
    private static final Pattern validPassword;
    private static final Pattern validSurname;
    private static final Pattern validPhoneNumber;
    private Map<String, String> resultValidation;

    static {
        validEmailRegex = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
        validPassword = Pattern.compile(PASSWORD_PATTERN, Pattern.CASE_INSENSITIVE);
        validSurname = Pattern.compile(SURNAME_PATTERN);
        validPhoneNumber = Pattern.compile(PHONE_NUMBER_PATTERN);
    }

    public UserInfoValidator() {
    }

    public UserInfoValidator(UserRegistrationInfo user) {
        this.user = user;
    }

    public Map<String, String> validate() {
        resultValidation = new HashMap<>();
        isEmailValid(user.getEmail());
        isPasswordValid(user.getPassword(), user.getRepeatedPassword());
        isSurnameValid(user.getSurname());
        isPhoneNumberValid(user.getPhoneNumber());
        if (!isSurnameValid(user.getName())) {
           resultValidation.put("name", "error.validation.email");
        }
        return resultValidation;
    }


    private boolean isEmailValid(String email) {
        boolean result = false;
        Matcher emailMatcher = validEmailRegex.matcher(email);
        if (emailMatcher.matches()) {
            result = true;
        } else {
            resultValidation.put("email", "error.validation.email");
        }
        return result;
    }

    private boolean isPasswordValid(String password, String repeatedPassword) {
        boolean result = false;
        Matcher passwordMatcher = validPassword.matcher(password);
        if (passwordMatcher.matches()) {
            result = true;
        } else {
            resultValidation.put("password", "error.validation.password");
        }
        if (!password.equals(repeatedPassword)) {
            resultValidation.put("repeated", "error.validation.repeated");
        }
        return result;
    }

    private boolean isSurnameValid(String surname) {
        boolean result = false;
        Matcher surnameMatcher = validSurname.matcher(surname);
        if (surnameMatcher.matches()) {
            result = true;
        } else {
            resultValidation.put("surname", "error.validation.surname");
        }
        return result;
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        boolean result = false;
        Matcher phoneNumberMatcher = validPhoneNumber.matcher(phoneNumber);
        if (phoneNumberMatcher.matches()) {
            result = true;
        } else {
            resultValidation.put("phoneNumber", "error.validation.phoneNumber");
        }
        return result;
    }

}
