package KFU;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import javax.sql.CommonDataSource;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class StatementsPage {
    //кнопку 'документы' на странице заявлений
    private SelenideElement docButton = $(byXpath("/html/body/div[2]/div[4]/div/a[8]"));
    //Документация по работе в КС КФУ
    private SelenideElement typeDocButton = $(byXpath("/html/body/div[2]/content/div/div/div[2]/div/div[1]/ul/li[4]/a"));
    // иконка загрузки pdf инструкции по восстановлению пароля
    public SelenideElement downloadButton = $(byXpath("/html/body/div[2]/content/div/div/div[3]/div/div[1]/div[5]/p[3]/a"));
    // кнопка  выхода выход
    public SelenideElement exitButton = $(byXpath("/html/body/div[2]/header/a[1]"));

    // should - проверка условий, проверка на существование элементов с заданным xpath
    // should - неявное ожидание, ожидание 4 секунды на соответствие условию

    public void docButtonClick() {
        docButton.should(Condition.exist).click();
    }
    public void typeDocButtonClick() {
        typeDocButton.should(Condition.exist).click();
    }
    public void exitButtonClick() {
        //exitButton.should(Condition.exist).click();
        exitButton.shouldBe(Condition.visible).click();
    }


}
