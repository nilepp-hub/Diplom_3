import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.config.PageMap;
import ru.yandex.praktikum.page.MainPage;

public class ConstructorTests {
    private MainPage mainPage = new MainPage();
    private PageMap pageMap = new PageMap();

    @Before
    public void start() {
        pageMap.openMainPage();
    }

    @Test
    @DisplayName("Переход к булкам")
    public void transitionBunTest() {
        mainPage.getSauce().click();
        mainPage.getBun().click();
        mainPage.getActive().shouldHave(Condition.text("Булки"));
    }

    @Test
    @DisplayName("Переход к соусам")
    public void transitionSauceTest() {
        mainPage.getSauce().click();
        mainPage.getActive().shouldHave(Condition.text("Соусы"));
    }

    @Test
    @DisplayName("Переход к начинкам")
    public void transitionFillingTest() {
        mainPage.getFilling().click();
        mainPage.getActive().shouldHave(Condition.text("Начинки"));
    }

    @After
    public void finish() {
        Selenide.closeWebDriver();
    }
}
