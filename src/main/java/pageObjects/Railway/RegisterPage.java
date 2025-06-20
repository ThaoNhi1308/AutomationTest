package pageObjects.Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import common.Constant;

public class RegisterPage {

    private final By txtUsername = By.id("email");
    private final By txtPassword = By.id("password");
    private final By txtConfirmPassword = By.id("confirmPassword");
    private final By txtpidPassport = By.id("pid");
    private final By btnRegister = By.xpath("//input[@value='Register']");
    private final By lblRegisterSuccessMessage = By.xpath("//*[@id=\"content\"]/p");
    private final By lblRegisterErrorMessage = By.xpath("//p[@class='message error']");

    // Elements
    public WebElement getTxtUsername() {
        return Constant.WEBDRIVER.findElement(txtUsername);
    }

    public WebElement getTxtPassword() {
        return Constant.WEBDRIVER.findElement(txtPassword);
    }

    public WebElement getTxtConfirmPassword() {
        return Constant.WEBDRIVER.findElement(txtConfirmPassword);
    }

    public WebElement getTxtpidPassport() {
        return Constant.WEBDRIVER.findElement(txtpidPassport);
    }

    public WebElement getBtnRegister() {
        return Constant.WEBDRIVER.findElement(btnRegister);
    }

    public WebElement getLblRegisterSuccessMessage() {
        return Constant.WEBDRIVER.findElement(lblRegisterSuccessMessage);
    }

    public WebElement getLblRegisterErrorMessage() {
        return Constant.WEBDRIVER.findElement(lblRegisterErrorMessage);
    }

    // Method to register a new account
    public RegisterPage register(String email, String password, String confirmPassword, String pidPassport) {
        this.getTxtUsername().sendKeys(email);
        this.getTxtPassword().sendKeys(password);
        this.getTxtConfirmPassword().sendKeys(confirmPassword);
        this.getTxtpidPassport().sendKeys(pidPassport); // Sửa lại để điền vào trường PID
        this.getBtnRegister().click();
        return this;
    }


    public String getRegisterSuccessMessage() {
        return this.getLblRegisterSuccessMessage().getText();
    }


    public String getRegisterErrorMessage() {
        return this.getLblRegisterErrorMessage().getText();
    }
}
