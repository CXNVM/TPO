package firstTask;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidationTest {

    // валидация логина позитивный тест
    @Test
    public void loginValidate1() {
        String login = "correctLogin";
        Assert.assertTrue(Validation.checkLogin(login));
    }

    // валидация логина негативный тест
    @Test
    public void loginValidate2() {
        String login = "asd";
        Assert.assertTrue(Validation.checkLogin(login));
    }

    // валидация пароля негативный тест
    @Test
    public void passwordValidate1() {
        String password = "correctPassword";
        Assert.assertTrue(Validation.checkPassword(password));
    }

    // валидация пароля негативный тест
    @Test
    public void passwordValidate2() {
        String password = "asd";
        Assert.assertTrue(Validation.checkPassword(password));
    }
}