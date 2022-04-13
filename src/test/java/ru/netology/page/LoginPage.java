package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private SelenideElement login = $x("//*[@data-test-id=\"login\"]//input");
    private SelenideElement password = $x("//*[@data-test-id=\"password\"]//input");
    private SelenideElement button = $x("//*[@data-test-id=\"action-login\"]//*[@class=\"button__text\"]");
    private SelenideElement verifacation = $x("//*[@data-test-id=\"code\"]//*[@class=\"input__top\"]");

    public LoginPage() {
        open("http://localhost:9999");
    }

    public VerificationPage validLoginPage() {
        login.sendKeys(DataHelper.getAuthInfo().getLogin());
        password.sendKeys(DataHelper.getAuthInfo().getPassword());
        button.click();
        return new VerificationPage();
    }
}
