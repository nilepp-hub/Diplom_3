package ru.yandex.praktikum.config;

import static com.codeborne.selenide.Selenide.open;

public class PageMap {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private static final String REG_URL = "https://stellarburgers.nomoreparties.site/register";
    private static final String FORGOT_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    public void openMainPage() {
        open(BASE_URL);
    }

    public void openRegPage() {
        open(REG_URL);
    }

    public void openForgotPasswordPage() {
        open(FORGOT_URL);
    }
}
