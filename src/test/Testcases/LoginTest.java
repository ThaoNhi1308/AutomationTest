package pageObjects.Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import common.Constant;

public class LoginPage extends GeneralPage {
    // Locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By _loginFormTitle = By.xpath("//h1[text()='Login Page']"); // Xác định locator của tiêu đề trang đăng nhập

    // Elements
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

    // Methods
    public HomePage login(String username, String password) {
        // Submit login credentials
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();

        // Land on Homepage
        return new HomePage();
    }

    public String getLoginFormTitle() {
        // Lấy văn bản của tiêu đề trang đăng nhập
        return Constant.WEBDRIVER.findElement(_loginFormTitle).getText();
    }
}
