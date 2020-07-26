package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @Test
    void shouldTransferMoneyFromCard2ToCard1() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV2();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val cardPage = new CardPage();
        int balanceBegin = cardPage.getCard1Balance();
        cardPage.card1Replenish();

        val cardPageReplenish = new CardPageReplenish();
        val infoFrom1To2 = DataHelper.getCardInfoFromCard2();
        cardPageReplenish.replenishCard1ToCard2(infoFrom1To2);
        val cardPageAfter = new CardPage();
        assertEquals(balanceBegin + 2000, cardPageAfter.getCard1Balance());
    }

    @Test
    void shouldTransferMoneyFromCard1ToCard2() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV2();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        val cardPage = new CardPage();
        int balanceBegin = cardPage.getCard2Balance();
        cardPage.card2Replenish();

        val cardPageReplenish = new CardPageReplenish();
        val infoFrom2To1 = DataHelper.getCardInfoFromCard1();
        cardPageReplenish.replenishCard2ToCard1(infoFrom2To1);
        val cardPageAfter = new CardPage();
        assertEquals(balanceBegin + 1000, cardPageAfter.getCard2Balance());
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        open("http://localhost:9999");
        val loginPage = new LoginPageV1();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV3() {
        val loginPage = open("http://localhost:9999", LoginPageV3.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }
}
