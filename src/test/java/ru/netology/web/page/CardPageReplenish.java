package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CardPageReplenish {
    private SelenideElement sumField = $("[data-test-id='amount'] .input__control");
    private SelenideElement cardFrom = $("[data-test-id='from'] .input__control");
    private SelenideElement replenish = $("[data-test-id='action-transfer']");
    private SelenideElement cardPageReplenish = $("[data-test-id='dashboard']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");

    public CardPageReplenish() {
        cardPageReplenish.shouldBe(visible);
    }

    public void replenishCardToCard(DataHelper.CardInfo cardInfo, String sum) {
        sumField.setValue(sum);
        cardFrom.setValue(cardInfo.getCardFrom());
        replenish.click();
    }

    public String errorNotificationAppear() {
        errorNotification.shouldBe(visible);
        String text = errorNotification.getText();
        return text;
    }
}
