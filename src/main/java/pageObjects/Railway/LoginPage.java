package pageObjects.Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import common.Constant;

public class LoginPage extends GeneralPage {
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By forgotPasswordLink = By.xpath("//a[contains(@href, '/Account/ForgotPassword.cshtml')]");

    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }

    public WebElement getBtnLogin() {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }

    public WebElement getLblLoginErrorMsg() {
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }

    public HomePage login(String username, String password) {
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();

        return new HomePage();
    }


    public ForgotPasswordPage gotoForgotPasswordPage() {
        Constant.WEBDRIVER.findElement(forgotPasswordLink).click();
        return new ForgotPasswordPage(Constant.WEBDRIVER);
    }
}

