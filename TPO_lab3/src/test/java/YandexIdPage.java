import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexIdPage {
    private WebDriver driver;
    @FindBy(xpath = "//div[@class='UserID-Badge ']")
    private WebElement navbar;

    @FindBy(xpath = "//div[contains(@class, 'Logout')]")
    public WebElement logoutButton;
    @FindBy(xpath = "//iframe[@class='UserWidget-Iframe']")
    public WebElement iframeElement;

    @FindBy(xpath = "//*[@id=\"__next\"]/div/header/div[3]/div[1]/div")
    private WebElement searchField;

    // устанавливаем PageFactory для данного класса
    // для взаимодействия с элементами типа WebElement
    public YandexIdPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public WebElement getSearchField() {
        return searchField;
    }
    public WebElement getNavbar() {
        return navbar;
    }

    // имитация нажатия на кнопку
    public void navbarClick() {
        navbar.click();
    }

    //все последующие команды будут перенаправлены .switchTo() к фрейму .frame(iframeElement)
    public void logoutButtonClick() {
        driver.switchTo().frame(iframeElement);
        try{
            Thread.sleep(2000);
        }
        catch(InterruptedException ie){
        }
        logoutButton.click();

        //возвращение к содержимому по умолчанию
        driver.switchTo().defaultContent();
    }
}
