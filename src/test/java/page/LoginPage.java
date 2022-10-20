package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement textError = $("body");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public LoginPage invalidLogin(DataHelper.AuthInfo info) {
        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        loginField.setValue(info.getLogin());
        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new LoginPage();
    }

    public SelenideElement error() {
        return textError.shouldHave(text("Пользователь заблокирован"));
    }
}