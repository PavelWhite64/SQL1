package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.page;

public class VerificationPage {
    @FindBy(css = "[data-test-id=code] input")
    private SelenideElement verifyCode;
    @FindBy(css = "[data-test-id=action-verify]")
    private SelenideElement actionVerButton;
    @FindBy(css = "[data-test-id=error-notification]")
    private SelenideElement errorVf;

    public void visiblityPage() {
        verifyCode.shouldBe(Condition.visible);
    }

    public void verifyError() {
        errorVf.shouldBe(Condition.visible);
    }

    public void verify(String verificationCode) {
        verifyCode.setValue(verificationCode);
        actionVerButton.click();
    }

    public DashboardPage validV(String verificationCode) {
        verify(verificationCode);
        return page(DashboardPage.class);
    }
}
