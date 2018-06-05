package epam.chernova.finalproject.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String REGEX_FOR_LOGIN = "^[a-zA-Z](.[a-zA-Z0-9_-]*)$";
    private static final String REGEX_FOR_PASSWORD = "\\w{6,}";
    private static final String REGEX_FOR_ACCOUNT_NUMBER = "^[0-9]{7}$";
    private static final String REGEX_FOR_QUANTITY = "^[0-9]+$";
    private static final String REGEX_FOR_WEIGHT_COST= "^(([0-9]+)(\\.){0,1}([0-9]+))$";
    private static final String REGEX_FOR_NAME = "([A-Z]{1}[a-z]+)|([А-Я]{1}[а-я]+)";
    private static final String REGEX_FOR_EMAIL = "[0-9a-z\\_\\-\\.]+@[0-9a-z_-]+\\.[a-z]{2,5}";
    private static final String REGEX_FOR_PRODUCT_NAME = "([A-Z][a-z\\s\\-\\`A-Z]+)|([А-Я][а-я\\s\\-\\`А-Я]+)";


    public final static boolean isNull(Object... objects) {
        for (Object ob : objects) {
            if (ob == null) {
                return false;
            }
        }
        return true;
    }


    public final static boolean isEmptyString(String... strings) {
        for (String s : strings) {
            if (s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public final static boolean matchLogin(String... strings) {
        Pattern pattern = Pattern.compile(REGEX_FOR_LOGIN);
        Matcher matcher;
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    public final static boolean matchProductQuantity(Integer... integers) {
        Pattern pattern = Pattern.compile(REGEX_FOR_QUANTITY);
        Matcher matcher;
        for (Integer s : integers) {

            matcher = pattern.matcher(String.valueOf(s));
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    public final static boolean matchProductWeightCost(Double... doubles) {
        Pattern pattern = Pattern.compile(REGEX_FOR_WEIGHT_COST);
        Matcher matcher;
        for (Double s : doubles) {
            matcher = pattern.matcher(String.valueOf(s));
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    public final static boolean matchPassword(String... strings) {
        Pattern pattern = Pattern.compile(REGEX_FOR_PASSWORD);
        Matcher matcher;
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    public final static boolean matchProperName(String... strings) {
        Pattern pattern = Pattern.compile(REGEX_FOR_NAME);
        Matcher matcher;
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }


    public final static boolean matchAccountNumber(String... strings) {
        Pattern pattern = Pattern.compile(REGEX_FOR_ACCOUNT_NUMBER);
        Matcher matcher;
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }


    public final static boolean matchEmail(String... strings) {
        Pattern pattern = Pattern.compile(REGEX_FOR_EMAIL);
        Matcher matcher;
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    public final static boolean matchProductName(String... strings) {
        Pattern pattern = Pattern.compile(REGEX_FOR_PRODUCT_NAME);
        Matcher matcher;
        for (String s : strings) {
            matcher = pattern.matcher(s);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }


}
