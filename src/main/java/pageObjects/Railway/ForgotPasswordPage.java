package pageObjects.Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailAddressField = By.id("email");
    private By sendInstructionsButton = By.xpath("//input[@value='Send Instructions']");

    public void enterEmailAddress(String email) {
        WebElement emailField = driver.findElement(emailAddressField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void clickSendInstructionsButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(sendInstructionsButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

        button.click();
    }
}
