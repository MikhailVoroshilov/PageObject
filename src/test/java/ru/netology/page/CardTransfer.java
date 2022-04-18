package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardTransfer {
    private SelenideElement sum = $("[data-test-id=\"amount\"] .input__control");
    private SelenideElement verification = $("[class=\"heading heading_size_xl heading_theme_alfa-on-white\"]");
    private SelenideElement from = $("[data-test-id=from] .input__control");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement cancelTransfer = $("[data-test-id=\"action-cancel\"]");
    private SelenideElement to = $("[data-test-id=to] .input__control");

    public CardTransfer(){
        verification.should(visible).should(text("Пополнение карты"));
    }

    public DashboardPage transfer(int amount, String numberCard) {
        sum.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        sum.setValue(String.valueOf(amount));
        from.sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        from.setValue(numberCard);
        transferButton.click();
        return new DashboardPage();
    }
}
