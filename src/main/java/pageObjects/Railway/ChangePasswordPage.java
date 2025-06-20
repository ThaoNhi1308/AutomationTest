package pageObjects.Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage {
    WebDriver driver;


    public ChangePasswordPage(WebDriver driver) {
        this.driver = driver;
    }


    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
        WebElement currentPassField = driver.findElement(By.id("currentPassword"));
        WebElement newPassField = driver.findElement(By.id("newPassword"));
        WebElement confirmPassField = driver.findElement(By.id("confirmPassword"));

        currentPassField.sendKeys(currentPassword);
        newPassField.sendKeys(newPassword);
        confirmPassField.sendKeys(confirmPassword);

        WebElement submitButton = driver.findElement(By.xpath("//input[@value='Change Password']"));
        submitButton.click();
    }

    public String successMessage() {
        WebElement successMessageElement = driver.findElement(By.xpath("//p[@class='message success']"));
        return successMessageElement.getText();
    }

}
