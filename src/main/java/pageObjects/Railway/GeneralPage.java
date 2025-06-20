package pageObjects.Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import common.Constant;
public class GeneralPage {
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");

    protected WebElement getTabLogin(){
        return Constant.WEBDRIVER.findElement(tabLogin);
    }


    protected WebElement getLblWelcomeMessage(){
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }

    public String getWelcomeMessage()
    {
        return this.getLblWelcomeMessage().getText();
    }

    public LoginPage gotoLoginPage()
    {
        this.getTabLogin().click();
        return  new LoginPage();
    }
}
