package ru.yandex.praktikum.config;

import java.util.Random;

public class UserTestData {
    public static String getRndStringEng(int length) {
        String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            result.append(SALTCHARS.charAt(index));
        }

        return result.toString();
    }

    public static String getRndStringRu(int length) {
        String SALTCHARS = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            result.append(SALTCHARS.charAt(index));
        }

        return result.toString();
    }
}
