package pageObjects.Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import common.Constant;

public class HomePage extends GeneralPage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://railwayb1.somee.com");
    }

    // Locators for various tabs
    private final By bookTicketTab = By.xpath("//a[@href='/Page/BookTicketPage.cshtml']");
    private final By changePasswordTab = By.xpath("//a[@href='/Account/ChangePassword.cshtml']");
    private final By myTicketTab = By.xpath("//a[@href='/Page/ManageTicket.cshtml']");
    private final By timetableTab = By.xpath("//a[span[text()='Timetable']]");

    // Default constructor using Constant.WEBDRIVER
    public HomePage() {
        this.driver = Constant.WEBDRIVER;
    }

    public BookTicketPage gotoBookTicketPage() {
        driver.findElement(bookTicketTab).click();

        return new BookTicketPage();
    }

    public void clickBookTicket() {
        Constant.WEBDRIVER.findElement(bookTicketTab).click();
    }

    public MyTicketPage gotoMyTicketPage() {
        driver.findElement(myTicketTab).click();
        return new MyTicketPage();
    }

    public RegisterPage gotoRegisterPage() {
        Constant.WEBDRIVER.findElement(By.linkText("Register")).click();
        return new RegisterPage();
    }

    public String getErrorMessage() {
        return "Invalid username or password. Please try again.";
    }

    public ChangePasswordPage gotoChangePasswordPage() {
        driver.findElement(changePasswordTab).click();
        return new ChangePasswordPage(driver);
    }

    public TimetablePage gotoTimetablePage() {
        WebElement timetableTabElement = driver.findElement(timetableTab);
        timetableTabElement.click();
        return new TimetablePage(driver);
    }
}
