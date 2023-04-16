package ru.yandex.praktikum.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {
    private final SelenideElement
            exit = $$("button").findBy(text("Выход")),
            textPersonalAccount = $$("p")
            .findBy(text("В этом разделе вы можете изменить свои персональные данные"));

    public void clickExit() {
        exit.click();
    }

    public SelenideElement getTextPersonalAccount() {
        return textPersonalAccount;
    }
}
