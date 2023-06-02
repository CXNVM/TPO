package KFU;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.IOException;


import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Selenide.open;

//использование данной аннотации позволяет использовать аннотацию @order для тестовых методов (т.е. с @Test)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// добавление расширения тестовому классу
// делает скриншоты любых ошибок (не только selenide) в тестах
@ExtendWith({ScreenShooterExtension.class})
//@ExtendWith({TextReportExtension.class})
public class KFUTest {

    private static LoginPage loginPage;
    private static ProfilePage profilePage;
    private static StatementsPage statementsPage;


    //Configuration - настройки для selenide browser
    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));
        loginPage = new LoginPage();
        profilePage = new ProfilePage();
        statementsPage = new StatementsPage();
        // значение по умолчанию 4 секунды на выполнение условий теста
        // задаем свое значение
        Configuration.timeout = 1200;
        // точка начала теста, открытие окна по ссылке
        open("https://kpfu.ru/");
        // путь хранящий скриншоты
        //Configuration.reportsFolder = "build/test-results/reports";
    }

    // аннотация @order задает порядок выполнения элементов относительно элементов той же категории
    // проверка авторизации
    @Test
    @Order(1)
    public void loginTest() {
        loginPage.clickLkButton();
        loginPage.enterLogin("hani");
        loginPage.enterPassword("e7");
        loginPage.enterButtonClick();
        //отображается ли кнопка выхода на странице
        Assertions.assertFalse(statementsPage.exitButton.isDisplayed());
    }

    // проверка на нахождение на странице загрузки документа
    @Test
    @Order(2)
    public void downloadPageTest() {
        profilePage.statementButtonClick();
        statementsPage.docButtonClick();
        statementsPage.typeDocButtonClick();
        // проверка на отображение кнопки загрузки
        Assertions.assertTrue(statementsPage.downloadButton.isDisplayed());
    }

    //тест загрузки файла
    @Test
    @Order(3)
    public void pdfCheckTest() throws IOException {
        // загрузка файла в build/downloads
        File file = statementsPage.downloadButton.download();
        //проверка на существование файла, может быть как файлом так и директорией
        /*assertThat(file).exists();*/
        //проверка на существование файла и его расширение
        assertThat(file).isFile().hasExtension("pdf");
        //создаем экземпляр PDF класса, конструктор считывает путь файла
        PDF pdf = new PDF(file);
        assertThat(pdf).isNotNull().containsText("kpfu.ru");
    }

    @Test
    @Order(4)
    public void logoutTest() {
       statementsPage.exitButtonClick();
       Assertions.assertTrue(loginPage.lkButton.isDisplayed());
    }

}
