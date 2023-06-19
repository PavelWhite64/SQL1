package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    @FindBy(css = "[data-test-id=login] input")
    private SelenideElement loginField;
    @FindBy(css = "[data-test-id=password] input")
    private SelenideElement passwordField;
    @FindBy(css = "[data-test-id=action-login]")
    private SelenideElement actionButton;
    @FindBy(css = "[data-test-id=error-notification]")
    private SelenideElement error;

    public void verifyError() {
        error.shouldBe(Condition.visible);
    }

    public void loginVisible() {
        loginField.shouldBe(Condition.visible);
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        actionButton.click();
        return page(VerificationPage.class);
    }

    public LoginPage notValidPass(DataHelper.AuthInfo info) {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        loginField.setValue(info.getLogin());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        passwordField.setValue(DataHelper.genRandomUser().getPassword());
        actionButton.click();
        return this;
    }
}