package test;

import data.Datahelper;
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
        var authInfo = Datahelper.getAuthInfoTest();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.visiblityPage();
        var verificationCode = SqlHelper.getVerificationCode();
        verificationPage.validV(verificationCode.getCode());
    }

    @Test
    void invalidCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = Datahelper.getAuthInfoTest();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.visiblityPage();
        var notVerificationCode = Datahelper.genRandomVerCode();
        verificationPage.verify(notVerificationCode.getCode());
        verificationPage.verifyError();

    }

    @Test
    void invalidPass() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = Datahelper.getAuthInfoTest();
        var loginPage1 = loginPage.notValidPass(authInfo);
        loginPage1.verifyError();

    }
}