package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String cardFrom;
    }

    public static CardInfo getCardInfoFromCard1() {
        return new CardInfo("5559_0000_0000_0001");
    }

    public static CardInfo getCardInfoFromCard2() {
        return new CardInfo("5559_0000_0000_0002");
    }

    public static CardInfo getCardInfoFromCard3() {
        return new CardInfo("5559_0000_0000_0003");
    }
}
