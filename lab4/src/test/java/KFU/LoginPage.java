package KFU;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

//главная страница
public class LoginPage {

    // полные пути до ВебЭлементов
    //лк на главной
    public SelenideElement lkButton = $(byXpath("/html/body/header/div[1]/div[1]/div[2]/a"));
    // элементы диалогового окна
    private SelenideElement loginField = $(byXpath("/html/body/div[14]/div/div[2]/form/input[1]"));
    private SelenideElement passwordField = $(byXpath("/html/body/div[14]/div/div[2]/form/input[2]"));
    private SelenideElement enterButton = $(byXpath("/html/body/div[14]/div/div[2]/form/input[3]"));

    // should - проверка условий, проверка на существование элементов с заданным xpath
    // should - неявное ожидание, ожидание 4 секунды на соответствие условию
    public void clickLkButton() {
        lkButton.should(Condition.exist).click();
    }
    public void enterLogin(String login) {
        loginField.should(Condition.exist).setValue(login);
    }
    public void enterPassword(String password) {
        passwordField.should(Condition.exist).setValue(password);
    }

    public void enterButtonClick() {
        enterButton.should(Condition.exist).click();
    }
}
