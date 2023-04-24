import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.config.PageMap;
import ru.yandex.praktikum.config.UserStatesAPI;
import ru.yandex.praktikum.page.RegPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;
import static org.apache.hc.core5.http.HttpStatus.SC_ACCEPTED;
import static ru.yandex.praktikum.config.UserTestData.*;

public class RegTests {
    private String email;
    private String pass;
    private String name;
    private String passInvalid;
    private String accessToken;
    private PageMap pageMap = new PageMap();
    private RegPage regPage = new RegPage();
    private UserStatesAPI userStatesAPI = new UserStatesAPI();

    @Before
    public void start() {
        pageMap.openRegPage();
        name = getRndStringRu(5);
        email = getRndStringEng(5) + "@" + getRndStringEng(5) + ".ru";
        pass = getRndStringEng(6);
        passInvalid = getRndStringEng(5);
        regPage.getH2Registration().shouldBe(visible);
    }

    @Test
    @DisplayName("Reg success")
    public void regSuccessTest() {
        regPage.regUser(name, email, pass);
        regPage.clickRegButton();
        step("Проверка Открытия формы входа", () -> regPage.getH2Entry().shouldHave(text("Вход")));
        accessToken = new UserStatesAPI().login(email, pass);
        step("Авторизация пользователя", () -> {
            Selenide.executeJavaScript("localStorage.setItem('accessToken', '" + accessToken + "')");
        });
    }

    @Test
    @DisplayName("Error Reg invalid pass")
    public void regInvalidPassTest() {
        regPage.enterName(name);
        regPage.enterEmail(email);
        regPage.setPassInvalid(passInvalid);
        regPage.clickRegButton();
        step("Проверка ошибки", () -> regPage.getError().shouldBe(visible));
        if (!regPage.getError().isDisplayed()) {
            step("Авторизация пользователя", () -> {
                accessToken = new UserStatesAPI().login(email, pass);
                Selenide.executeJavaScript("localStorage.setItem('accessToken', '" + accessToken + "')");
            });
        }
    }

    @After
    public void clean() {
        Selenide.closeWebDriver();
        if (accessToken != null) {
            userStatesAPI.delete(accessToken)
                   .statusCode(SC_ACCEPTED)
                   .extract().path("success");
            userStatesAPI.delete("accessToken");
        }
    }
}

