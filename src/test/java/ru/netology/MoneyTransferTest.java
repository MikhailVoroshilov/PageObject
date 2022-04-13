package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {

    @Test
    void shouldValidLoginUpdateCardFirst() {
        LoginPage login = new LoginPage();
        login.validLoginPage();
        VerificationPage verificationPage = new VerificationPage();
        verificationPage.validCode(DataHelper.getVerificationCodeFor());
        var card1 = DataHelper.getCardNumber1().getNumber();
        var card2 = DataHelper.getCardNumber2().getNumber();

        DashboardPage dashboardPage = new DashboardPage();
        int balanceCard0001 = dashboardPage.getCardBalance(0);
        int balanceCard0002 = dashboardPage.getCardBalance(1);
        int randomSum = DataHelper.getRandomSum(balanceCard0001);
        dashboardPage.transfer(1, randomSum, card1);
        assertEquals(balanceCard0001 - randomSum, dashboardPage.getCardBalance(0));
        assertEquals(balanceCard0002 + randomSum, dashboardPage.getCardBalance(1));

        dashboardPage.transfer(0, randomSum, card2);
        assertEquals(balanceCard0001, dashboardPage.getCardBalance(0));
        assertEquals(balanceCard0002, dashboardPage.getCardBalance(1));
        System.out.println();
    }

    @Test
    void shouldValidLoginUpdateCardSecond() {
        LoginPage login = new LoginPage();
        login.validLoginPage();
        VerificationPage verificationPage = new VerificationPage();
        verificationPage.validCode(DataHelper.getVerificationCodeFor());
        var card1 = DataHelper.getCardNumber1().getNumber();
        var card2 = DataHelper.getCardNumber2().getNumber();

        DashboardPage dashboardPage = new DashboardPage();
        int balanceCard0001 = dashboardPage.getCardBalance(0);
        int balanceCard0002 = dashboardPage.getCardBalance(1);
        int randomSum = DataHelper.getRandomSum(balanceCard0001);
        dashboardPage.transfer(0, randomSum, card2);
        assertEquals(balanceCard0001 + randomSum, dashboardPage.getCardBalance(0));
        assertEquals(balanceCard0002 - randomSum, dashboardPage.getCardBalance(1));

        dashboardPage.transfer(1, randomSum, card1);
        assertEquals(balanceCard0001, dashboardPage.getCardBalance(0));
        assertEquals(balanceCard0002, dashboardPage.getCardBalance(1));
        System.out.println();

    }
}