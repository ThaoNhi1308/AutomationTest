package pageObjects.Railway;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import common.Constant;

public class MyTicketPage {
    private WebDriver driver;

    public MyTicketPage(WebDriver driver) {
        this.driver = driver;
    }
    private int departStation;
    private String arriveStation;
    private String seatType;
    private String departDate;
    private int amount;

    public MyTicketPage() {

    }

    public MyTicketPage(int departStation, String arriveStation, String seatType, String departDate, int amount) {
        this.departStation = departStation;
        this.arriveStation = arriveStation;
        this.seatType = seatType;
        this.departDate = departDate;
        this.amount = amount;
    }

    private final By myTicketMenu = By.xpath("//*[@id='menu']/ul/li[7]/a");

    public void goToMyTicketPage() {
        Constant.WEBDRIVER.findElement(myTicketMenu).click();
    }

    public boolean isSomeElementDisplayed() {
        return Constant.WEBDRIVER.findElement(myTicketMenu).isDisplayed();
    }



    public void cancelTicket(String ticketId) {
        try {
            WebElement ticketRow = driver.findElement(By.xpath("//tr[td[contains(text(),'" + ticketId + "')]]"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", ticketRow);

            WebElement cancelBtn = ticketRow.findElement(By.xpath(".//input[@value='Cancel']"));
            cancelBtn.click();

            driver.switchTo().alert().accept();
            System.out.println("Ticket with ID: " + ticketId + " has been successfully cancelled.");

        } catch (NoSuchElementException e) {
            System.out.println("Ticket with ID: " + ticketId + " not found for cancellation.");
        } catch (Exception e) {
            System.out.println("An error occurred while canceling the ticket with ID: " + ticketId);
            e.printStackTrace();
        }
    }

    public boolean isTicketCancelled(String ticketId) {
        try {
            Constant.WEBDRIVER.findElement(By.xpath("//tr[td[contains(text(),'" + ticketId + "')]]"));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public int getDepartStation() {
        return departStation;
    }

    public String getArriveStation() {
        return arriveStation;
    }

    public String getSeatType() {
        return seatType;
    }

    public String getDepartDate() {
        return departDate;
    }

    public int getAmount() {
        return amount;
    }
}
