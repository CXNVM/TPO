import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class YandexAuthTest {
    private static WebDriver driver;
    private static YandexAuthPage yandexAuthPage;
    private static WebDriverWait wait;
    private static YandexIdPage yandexIdPage;

    @BeforeAll
    public static void setUp() {
        //обозначаем расположение webdriver'a
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\cxmv\\IdeaProjects\\TPO_lab3\\src\\test\\resources\\chromedriver\\chromedriver.exe");
        //создаем новый chromedriver
        driver = new ChromeDriver(/*options*/);
        //мы передаем driver в экземпляры классов, чтобы пользоваться Pagefactory
        yandexAuthPage = new YandexAuthPage(driver);
        yandexIdPage = new YandexIdPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // загрузка вебстаницы
        driver.get("https://passport.yandex.ru/");
    }

    @AfterAll
    public static void tearDown() {
        // выход из драйвера, закрытие всех связанных с ним окон
        driver.close();
    }

    @Test
    public void authPositive() {
        //ожидания, пока условие не станет истинным или не будет равно null.
        wait.until(ExpectedConditions.visibilityOf(yandexAuthPage.getLoginField()));
        //отображается ли на странице данный элемент
        Assertions.assertTrue(yandexAuthPage.getLoginField().isDisplayed());
        wait.until(ExpectedConditions.visibilityOf(yandexAuthPage.getMailButton()));
        yandexAuthPage.mailButtonClick();
        wait.until(ExpectedConditions.visibilityOf(yandexAuthPage.getLoginField()));
        yandexAuthPage.enterLogin("cxnvmcursed");
        yandexAuthPage.clickSignInButton();
        wait.until(ExpectedConditions.visibilityOf(yandexAuthPage.getPasswordField()));
        try{
            Thread.sleep(10000);
        }
        catch(InterruptedException ie){
        }
        yandexAuthPage.enterPassword("cemka20");
        yandexAuthPage.clickSignInButton();
        wait.until(ExpectedConditions.visibilityOf(yandexIdPage.getNavbar()));
        Assertions.assertTrue(yandexIdPage.getNavbar().isDisplayed());
        /*wait.until(ExpectedConditions.visibilityOf(yandexIdPage.getSearchField()));
        Assertions.assertTrue(yandexIdPage.getSearchField().isDisplayed());*/
    }

    @Test
    public void logoutPositive() {
        //достаточно секунды
        /*try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }*/
        yandexIdPage.navbarClick();
        //wait.until(ExpectedConditions.visibilityOf(yandexIdPage.logoutButton));
        yandexIdPage.logoutButtonClick();
        wait.until(ExpectedConditions.visibilityOf(yandexAuthPage.getPasswordField()));
        Assertions.assertTrue(yandexAuthPage.getPasswordField().isDisplayed());
    }
}
