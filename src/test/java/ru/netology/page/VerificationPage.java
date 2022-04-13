package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class VerificationPage {
    private SelenideElement code = $x("//*[@data-test-id=\"code\"]//input");
    private SelenideElement button = $x("//*[@data-test-id=\"action-verify\"]//*[@class=\"button__text\"]");
    private SelenideElement dashBord = $x("//*[@data-test-id=\"dashboard\"]//span");

    public VerificationPage() {
        code.should(visible);
    }

    public DashboardPage validCode(DataHelper.VerificationCode verificationCode) {
        code.setValue(verificationCode.getCode());
        button.click();
        return new DashboardPage();
    }
}
