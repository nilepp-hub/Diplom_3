import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.config.PageMap;
import ru.yandex.praktikum.config.UserStatesAPI;
import ru.yandex.praktikum.config.UserTestData;
import ru.yandex.praktikum.page.ForgotPasswordPage;
import ru.yandex.praktikum.page.LoginPage;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.RegPage;

public class AuthTests {
    String name;
    String email;
    String pass;
    String accessToken;
    PageMap pageMap = new PageMap();
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    RegPage regPage = new RegPage();
    ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();

    public void startYandex() {
        System.setProperty("webdriver.chrome.driver", "d:\\Webdriver\\bin\\yandexdriver.exe");
        System.setProperty("selenide.browser", "Chrome");
    }

    UserStatesAPI userStatesAPI = new UserStatesAPI();

    @Before
    public void start() {
        name = UserTestData.getRndStringEng(5);
        email = UserTestData.getRndStringEng(5) + "@" + UserTestData.getRndStringRu(5) + ".ru";
        pass = UserTestData.getRndStringEng(6);
        ValidatableResponse response = userStatesAPI.create(email, pass, name);
        accessToken = response.extract().path("accessToken");
    }

    @Test
    @DisplayName("Successfully authorization through button Enter  in main page")
    public void checkButtonEnterAccountMainPageTest() {
        pageMap.openMainPage();
        mainPage.clickButtonEntryToAccount();
        loginPage.authorization(email, pass);
    }

    @Test
    @DisplayName("Successfully authorization through button Personal_Account in main page")
    public void enterButtonPersonalAccountTest() {
        pageMap.openMainPage();
        mainPage.clickPersonalAccount();
        loginPage.authorization(email, pass);
    }

    @Test
    @DisplayName("Successfully authorization through Link in form Registrations page")
    public void checkLinkEnterRegistrationsPageTest() {
        pageMap.openRegPage();
        regPage.clickLinkEnter();
        loginPage.authorization(email, pass);
    }

    @Test
    @DisplayName("Successfully authorization through Link in form Forgot_Password Page")
    public void checkLinkEnterForgotPasswordPageTest() {
        pageMap.openForgotPasswordPage();
        forgotPasswordPage.clickButtonHyperTextEntryToForgotPassword();
        loginPage.authorization(email, pass);
    }

    @After
    public void clean() {
        userStatesAPI.delete(accessToken);
        Selenide.closeWebDriver();
    }
}
