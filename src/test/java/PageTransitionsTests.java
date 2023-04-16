import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.config.PageMap;
import ru.yandex.praktikum.config.UserStatesAPI;
import ru.yandex.praktikum.config.UserTestData;
import ru.yandex.praktikum.page.LoginPage;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.ProfilePage;

import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

public class PageTransitionsTests {
    String name;
    String email;
    String pass;
    String accessToken;
    PageMap pageMap = new PageMap();
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    ProfilePage profilePage = new ProfilePage();
    UserStatesAPI userStatesAPI = new UserStatesAPI();

    @Before
    public void start() {
        name = UserTestData.getRndStringEng(5);
        email = UserTestData.getRndStringEng(5) + "@" + UserTestData.getRndStringEng(5) + ".ru";
        pass = UserTestData.getRndStringEng(6);
        ValidatableResponse response = userStatesAPI.create(email, pass, name);
        accessToken = response.extract().path("accessToken");
        pageMap.openMainPage();
        mainPage.clickButtonEntryToAccount();
        loginPage.clickButtonEnter();
        loginPage.authorization(email, pass);
    }

    @Test
    @DisplayName("click-through to the \"Personal Account\"")
    public void clickThroughPersonalAccountTest() {
        pageMap.openMainPage();
        mainPage.clickPersonalAccount();
        step("Проверка что мы в личном кабинете", () ->
                profilePage.getTextPersonalAccount().shouldBe(visible));
    }

    @Test
    @DisplayName("click-through to the \"Constructor\"")
    public void clickThroughConstructorTest() {
        pageMap.openMainPage();
        mainPage.clickPersonalAccount();
        mainPage.clickButtonConstructor();
        step("Проверка что перешли и кнопка оформить заказ(авторизованы)", () -> {
            mainPage.getH1TextCreateBurger().shouldBe(visible);
            loginPage.checkSuccessEntry();
        });
    }

    @Test
    @DisplayName("click-through to the \"Logotype\"")
    public void clickThroughLogotypeTest() {
        pageMap.openMainPage();
        mainPage.clickPersonalAccount();
        mainPage.clickLogo();
        step("Проверка что перешли и кнопка оформить заказ(авторизованы)", () -> {
            mainPage.getH1TextCreateBurger().shouldBe(visible);
            loginPage.checkSuccessEntry();
        });
    }

    @Test
    @DisplayName("logout")
    public void logoutTest() {
        pageMap.openMainPage();
        mainPage.clickPersonalAccount();
        profilePage.clickExit();
        step("Проверка отбражения страницы авторизации", () -> {
            loginPage.checkVisibleButtonEntry();
            loginPage.checkVisibleH2Entry();
        });
    }
    @After
    public void clean() {
        UserStatesAPI userStatesAPI = new UserStatesAPI();
        userStatesAPI.delete(accessToken);
        Selenide.closeWebDriver();
    }
}
