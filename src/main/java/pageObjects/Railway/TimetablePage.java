package pageObjects.Railway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;


public class TimetablePage extends GeneralPage {
    private final WebDriver driver;


    private final By departFromDropdown = By.xpath("//table[contains(@class, 'MyTable')]//th[contains(text(), 'Depart Station')]");
    private final By arriveStationDropdown = By.xpath("//table[contains(@class, 'MyTable')]//th[contains(text(), 'Arrive Station')]");
    private final By bookTicketButton = By.xpath("//*[@id=\"content\"]/div/div/table/tbody/tr[17]/td[7]/a");

    public TimetablePage(WebDriver driver) {

        this.driver = driver;
    }

    public WebElement getBookTicketButton() {
        return driver.findElement(bookTicketButton);
    }

    public void clickBookTicketButton() {
        WebElement bookTicketButton = driver.findElement(By.xpath("//*[@id='content']/div/div/table/tbody/tr[17]/td[7]/a"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bookTicketButton);
        bookTicketButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']/div/div/table/tbody/tr[17]/td[7]/a")));
        element.click();
        getBookTicketButton().click();
    }

    public WebElement getDepartFromDropdown() {
        return driver.findElement(departFromDropdown);
    }

    public WebElement getArriveStationDropdown() {
        return driver.findElement(arriveStationDropdown);
    }

    public void selectDepartureAndArrival(String departFrom, String arriveStation) {
        WebElement departDropdown = getDepartFromDropdown();
        WebElement arriveDropdown = getArriveStationDropdown();

        List<WebElement> departOptions = departDropdown.findElements(By.xpath(".//following-sibling::td//select/option"));
        for (WebElement option : departOptions) {
            if (option.getText().equals(departFrom)) {
                option.click();
                break;
            }
        }

        List<WebElement> arriveOptions = arriveDropdown.findElements(By.xpath(".//following-sibling::td//select/option"));
        for (WebElement option : arriveOptions) {
            if (option.getText().equals(arriveStation)) {
                option.click();
                break;
            }
        }
    }

    public String[] getSelectedStations() {
        WebElement departDropdown = getDepartFromDropdown();
        WebElement arriveDropdown = getArriveStationDropdown();

        String departFrom = getSelectedOption(departDropdown);
        String arriveStation = getSelectedOption(arriveDropdown);

        return new String[] { departFrom, arriveStation };
    }

    private String getSelectedOption(WebElement dropdown) {
        List<WebElement> options = dropdown.findElements(By.xpath(".//following-sibling::td//select/option"));
        for (WebElement option : options) {
            if (option.isSelected()) {
                return option.getText();
            }
        }
        return null;
    }
}
