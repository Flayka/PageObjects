package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.CardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @Test
    void shouldTransferMoneyFromCard2ToCard1() {
        String sum = "5000";
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val cardPage = verificationPage.validVerify(verificationCode);
        int balanceBeginCard1 = cardPage.getCard1Balance();
        int balanceBeginCard2 = cardPage.getCard2Balance();
        val cardPageReplenish = cardPage.card1Replenish();
        val infoFrom1To2 = DataHelper.getCardInfoFromCard2();
        cardPageReplenish.replenishCardToCard(infoFrom1To2, sum);
        assertEquals(balanceBeginCard1 + Integer.parseInt(sum), cardPage.getCard1Balance());
        assertEquals(balanceBeginCard2 - Integer.parseInt(sum), cardPage.getCard2Balance());
    }

    @Test
    void shouldTransferMoneyFromCard1ToCard2() {
        String sum = "2000";
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val cardPage = verificationPage.validVerify(verificationCode);
        int balanceBeginCard1 = cardPage.getCard1Balance();
        int balanceBeginCard2 = cardPage.getCard2Balance();
        val cardPageReplenish = cardPage.card2Replenish();
        val infoFrom2To1 = DataHelper.getCardInfoFromCard1();
        cardPageReplenish.replenishCardToCard(infoFrom2To1, sum);
        val cardPageAfter = new CardPage();
        assertEquals(balanceBeginCard2 + Integer.parseInt(sum), cardPageAfter.getCard2Balance());
        assertEquals(balanceBeginCard1 - Integer.parseInt(sum), cardPage.getCard1Balance());
    }

    @Test
    void shouldErrorMessageAppearIfBalanceBelowZero() {
        String sum = "21000";
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val cardPage = verificationPage.validVerify(verificationCode);
        val cardPageReplenish = cardPage.card1Replenish();
        val infoFrom1To2 = DataHelper.getCardInfoFromCard2();
        cardPageReplenish.replenishCardToCard(infoFrom1To2, sum);
        assertEquals("Недостаточно средств на карте.", cardPageReplenish.errorNotificationAppear());
    }

    @Test
    void shouldErrorMessageAppearIfUseWrongCard() {
        String sum = "2000";
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val cardPage = verificationPage.validVerify(verificationCode);
        val cardPageReplenish = cardPage.card2Replenish();
        val infoFrom3To1 = DataHelper.getCardInfoFromCard3();
        cardPageReplenish.replenishCardToCard(infoFrom3To1, sum);
        assertEquals("Проверьте правильность ввода номера карты.", cardPageReplenish.errorNotificationAppear());
    }
}
