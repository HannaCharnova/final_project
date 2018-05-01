package epam.chernova.finalproject.util;

import epam.chernova.finalproject.exception.ValidatorException;
import javafx.animation.PathTransition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String REGEX_FOR_LOGIN = "^[a-zA-Z](.[a-zA-Z0-9_-]*)$";
    private static final String REGEX_FOR_PASSWORD = "\\w{6,}";

    public final static void isNull(Object... objects) throws ValidatorException {
        for (Object ob : objects) {
            if (ob == null) {
                throw new ValidatorException("Input error (string is null)");
            }
        }
    }


    public final static void isEmptyString(String... strings) throws ValidatorException {
        for (String s : strings) {
            if (s.isEmpty()) {
                throw new ValidatorException("Input error (string is empty)");
            }
        }
    }

    public final static void matchLogin(String... strings) throws ValidatorException {
        Pattern pattern = Pattern.compile(REGEX_FOR_LOGIN);
        Matcher matcher;
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                throw new ValidatorException("Login format error");
            }
        }
    }


    public final static void matchPassword(String... strings) throws ValidatorException {
        Pattern pattern = Pattern.compile(REGEX_FOR_PASSWORD);
        Matcher matcher;
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                throw new ValidatorException("Password format error");
            }
        }
    }


}
