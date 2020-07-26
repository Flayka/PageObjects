package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardPage {
    private SelenideElement cardPage = $("[data-test-id='dashboard']");

    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceEnd = " р.";

    private SelenideElement replenishCard1 = cards.first().$("[data-test-id=action-deposit]");
    private SelenideElement replenishCard2 = cards.last().$("[data-test-id=action-deposit]");
    private SelenideElement reload = $("[data-test-id='action-reload']");


    public CardPage() {
        cardPage.shouldBe(visible);
    }

    public CardPageReplenish card1Replenish() {
        replenishCard1.click();
        return new CardPageReplenish();
    }

    public CardPageReplenish card2Replenish() {
        replenishCard2.click();
        return new CardPageReplenish();
    }

    public int getCard1Balance() {
        val text = cards.first().text();
        return extractBalanceCard1(text);
    }

    private int extractBalanceCard1(String text) {
        val start = text.indexOf(balanceStart);
        val end = text.indexOf(balanceEnd);
        val value = text.substring(start + balanceStart.length(), end);
        return Integer.parseInt(value);
    }

    public int getCard2Balance() {
        val text = cards.last().text();
        return extractBalanceCard2(text);
    }

    private int extractBalanceCard2(String text) {
        val start = text.indexOf(balanceStart);
        val end = text.indexOf(balanceEnd);
        val value = text.substring(start + balanceStart.length(), end);
        return Integer.parseInt(value);
    }
}
