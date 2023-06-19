package test;

import data.DataHelper;
import data.SqlHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SqlHelper.cleanDatabase;


public class SQLTest {
    @AfterAll
    static void clean() {
        cleanDatabase();
    }

    @Test
    void happyPath() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoTest();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.visiblityPage();
        var verificationCode = SqlHelper.getVerificationCode();
        var dashboardPage = verificationPage.validV(verificationCode.getCode());
        dashboardPage.headingVisible();
    }

    @Test
    void invalidCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoTest();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.visiblityPage();
        var notVerificationCode = DataHelper.genRandomVerCode();
        verificationPage.verify(notVerificationCode.getCode());
        verificationPage.verifyError();
    }

    @Test
    void invalidPass() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoTest();
        var loginPage1 = loginPage.notValidPass(authInfo);
        loginPage1.notValidPass(authInfo);
        loginPage1.notValidPass(authInfo);
        loginPage1.notValidPass(authInfo);
        loginPage1.notValidPass(authInfo);
        loginPage1.verifyError();
    }
}