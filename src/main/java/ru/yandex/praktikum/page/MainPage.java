package ru.yandex.praktikum.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private  final SelenideElement
            personalAccount = $("a[href='/account']"),
            buttonEnterAccount = $$("button").findBy(text("Войти в аккаунт")),
            constructor = $$("a[href='/']").findBy(text("Конструктор")),
            logo = $("a[href='/']"),
            bun = $$("div span").findBy(text("Булки")),
            sauce = $$("div span").findBy(text("Соусы")),
            h1TextCreateBurger = $$("h1").findBy(text("Соберите бургер")),
            filling = $$("div span").findBy(text("Начинки")),
            active = $x("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span[@class='text text_type_main-default']");

    public void clickButtonEntryToAccount() {
        buttonEnterAccount.click();
    }

    public void clickPersonalAccount() {
        personalAccount.click();
    }

    public void clickButtonConstructor() {
        constructor.click();
    }

    public SelenideElement getH1TextCreateBurger() {
        return h1TextCreateBurger;
    }

    public void clickLogo() {
        logo.click();
    }

    public SelenideElement getBun() {
        return (bun);
    }

    public SelenideElement getSauce() {
        return (sauce);
    }

    public SelenideElement getFilling() {
        return (filling);
    }

    public SelenideElement getActive() {
        return (active);
    }
}
