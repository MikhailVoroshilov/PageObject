package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private SelenideElement update = $("[data-test-id=\"action-reload\"] .button__text");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private ElementsCollection topUpButtons = $$("[data-test-id=action-deposit]");

    public DashboardPage() {
        heading.shouldBe(visible).should(text("Личный кабинет"));
    }

    public int getCardBalance(int id) {
        var text = cards.get(id).text();
        return extractBalance(text);
    }

    public int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public CardTransfer transferPage(int index){
        topUpButtons.get(index).click();
        return new CardTransfer();
    }
}
