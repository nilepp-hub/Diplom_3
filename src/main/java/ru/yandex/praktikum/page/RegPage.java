package ru.yandex.praktikum.page;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegPage {
    private final SelenideElement
            h2Registration = $("h2"),
            emailInput = $x("//div[label[text() = 'Email']]/input"),
            nameInput = $x("//div[label[text() = 'Имя']]/input"),
            passwordInput = $x("//div[label[text() = 'Пароль']]/input"),
            buttonRegistration = $$("button").findBy(text("Зарегистрироваться")),
            h2Entry = $("h2"),
            error = $$("p").findBy(text("Некорректный пароль")),
            hyperTextEntry = $("a[href='/login']");

    @Step("Reg user")
    public void regUser(String name, String email, String Password) {
        enterName(name);
        enterEmail(email);
        setPassValid(Password);
    }

    public void enterName(String name) {
        step("Ввод в поле имя", () -> nameInput.val(name));
    }

    public void enterEmail(String email) {
        step("Ввод в поле email", () -> emailInput.val(email));
    }

    public void setPassValid(String password) {
        step("Ввод в поле пароль", () -> passwordInput.val(password));
    }

    public void setPassInvalid(String passwordInvalid) {
        step("Ввод в поле пароль", () -> passwordInput.val(passwordInvalid));
    }

    public void clickRegButton() {
        step("Клик по кнопке зарегистрироваться", () -> buttonRegistration.click());
    }

    public void clickLinkEnter() {
        step("Клик по link Войти", () -> hyperTextEntry.click());
    }

    public SelenideElement getError() {
        return error;
    }

    public SelenideElement getH2Entry() {
        return h2Entry;
    }

    public SelenideElement getH2Registration() {
        return h2Registration;
    }
    @Step("Enter data reg user")
    public void enterDataCreatedUser(String email, String password) {
        enterEmail(email);
        setPassValid(password);
    }
}
