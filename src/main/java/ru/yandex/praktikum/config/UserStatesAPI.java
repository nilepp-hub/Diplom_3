package ru.yandex.praktikum.config;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class UserStatesAPI {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site/api";
    public static final String USER_REG = "/auth/register";
    public static final String USER_DEL = "/auth/user";
    public static final String USER_LOGIN = "/auth/login";

    public static RequestSpecification spec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URI)
                .build();
    }

    @Step("Reg user")
    public ValidatableResponse create(String email, String password, String name) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("email", email);
        userMap.put("password", password);
        userMap.put("name", name);
        return given()
                .spec(spec())
                .body(userMap)
                .when()
                .post(USER_REG)
                .then();
    }

    @Step("Del user")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(spec())
                .header("authorization", accessToken)
                .when()
                .delete(USER_DEL)
                .then();
    }

    @Step("Login user")
    public String login(String email, String password) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("email", email);
        userMap.put("password", password);
        ValidatableResponse response = given()
                .spec(spec())
                .body(userMap)
                .when()
                .post(USER_LOGIN)
                .then();
        response.statusCode(SC_OK);
        return response
                .extract()
                .path("accessToken");
    }
}
