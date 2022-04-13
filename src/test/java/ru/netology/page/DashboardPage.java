package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");
    private SelenideElement update = $("[data-test-id=\"action-reload\"] .button__text");
    private SelenideElement sum = $("[data-test-id=\"amount\"] .input__control");
    private SelenideElement from = $("[data-test-id=from] .input__control");
    private SelenideElement to = $("[data-test-id=to] .input__control");
    private ElementsCollection topUpButtons = $$("[data-test-id=action-deposit]");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelTransfer = $("[data-test-id=\"action-cancel\"]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

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

    public void transfer(int index, int amount, String numberCard) {
        topUpButtons.get(index).click();
        sum.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        sum.setValue(String.valueOf(amount));
        from.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        from.setValue(numberCard);
        transferButton.click();
    }
}
