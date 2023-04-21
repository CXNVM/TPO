package KFU;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

// страница профиля
// Selenide element - оболочка для webelement, предоставляющая дополнительные методы по типу should()
public class ProfilePage {
    private SelenideElement statementsButton = $(byXpath("/html/body/div[2]/content/div/div[1]/div[1]/div[8]/a"));
    public void statementButtonClick() {
        statementsButton.should(Condition.exist).click();
    }
}
