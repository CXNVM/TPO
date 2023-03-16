package firstTask;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static Boolean checkLogin(String login) {
        if (login == null)
            return false;
        return login.length() <= 35 && login.length() >= 4;
    }

    public static Boolean checkPassword(String password) {
        if (password == null)
            return false;
        return password.length() <= 35 && password.length() >= 4;
    }
}