package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.CardsPage;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {

    @BeforeEach
    void setUp(){
//        Configuration.browserSize = "800x750";
//        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");

    }

    @Test
    void shouldTransferMoneyfromFirstToSecond() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var cardsPage = new CardsPage();
        var cardInfoFirst = DataHelper.getFirstCard();
        var initialBalanceFirst = new DashboardPage().getCardBalance(cardInfoFirst);
        var cardInfoSecond = DataHelper.getSecondCard();
        var initialBalanceSecond = new DashboardPage().getCardBalance(cardInfoSecond);
        int  randomSum = DataHelper.getSumForTopUpSecondCard();
        cardsPage.topUpSecondCard();

        var transferPage =new TransferPage();
        transferPage.topUp(randomSum);

        var balanceFirst = new DashboardPage().getCardBalance(cardInfoFirst);
        var balanceSecond = new DashboardPage().getCardBalance(cardInfoSecond);
        int expectedFirst = initialBalanceFirst - randomSum;
        int expectedSecond= initialBalanceSecond + randomSum;
        assertEquals(expectedFirst, balanceFirst);
        assertEquals(expectedSecond, balanceSecond);

        //проверка что баланс востановился
        cardsPage.topUpFirstCard();
        transferPage.topFinish(randomSum);
        var expectedBalans = new DashboardPage().getCardBalance(cardInfoFirst);
        var actualBalans = new DashboardPage().getCardBalance(cardInfoSecond);
        assertEquals(expectedBalans, actualBalans);
    }

    @Test
    void shouldNotTransferIfBalanseNotEnough() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);

        var cardsPage = new CardsPage();
        var cardInfoFirst = DataHelper.getFirstCard();
        var initialBalanceFirst = new DashboardPage().getCardBalance(cardInfoFirst);
        var cardInfoSecond = DataHelper.getSecondCard();
        var initialBalanceSecond = new DashboardPage().getCardBalance(cardInfoSecond);
        int  randomSum = DataHelper.getInvalidSumForTopUpSecondCard();
        cardsPage.topUpSecondCard();

        var transferPage =new TransferPage();
        transferPage.topUp(randomSum);

        var balanceSecond = new DashboardPage().getCardBalance(cardInfoSecond);
        int expectedFirst = initialBalanceFirst - randomSum;
        int expectedSecond= initialBalanceSecond + randomSum;
        Assertions.assertFalse(expectedFirst < 0);
        assertEquals(expectedSecond, balanceSecond);

        //проверка что баланс востановился
        cardsPage.topUpFirstCard();
        transferPage.topFinish(randomSum);
        var expectedBalans = new DashboardPage().getCardBalance(cardInfoFirst);
        var actualBalans = new DashboardPage().getCardBalance(cardInfoSecond);
        assertEquals(expectedBalans, actualBalans);

    }

}
