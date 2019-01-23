package pageobject.frontpage;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.PageObject;
import pageobject.timeanddate.TimeAndDatePage;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class FrontPage extends PageObject {

    public WebDriverWait wait = new WebDriverWait(driver, 5);

    @FindBy(css = "#time_from")
    private WebElement dateFrom;

    @FindBy(id = "hour_from")
    private WebElement hourFrom;

    @FindBy(id = "time_to")
    private WebElement dateTo;

    @FindBy(css = "#hour_to")
    private WebElement hourTo;

    @FindBy(css = ".order-button")
    private WebElement buttonOrder;

    @FindBy(xpath = "//*[contains(@data-handler, 'selectDay')]")
    private List<WebElement> availableDays;

    @FindBy(xpath = "//*[contains(@class, 'ui-state-disabled')]")
    private List<WebElement> unavailableDays;

    @FindBy(css = ".ui-timepicker-list li")
    private List<WebElement> availableHours;

    @FindBy(css = "td[class*='ui-datepicker-today']")
    public WebElement today;

    public FrontPage(WebDriver driver) {
        super(driver);
    }

    private void clickDateFrom() {
        dateFrom.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("ui-datepicker-div"))));
    }

    private TimeAndDatePage clickButtonOrder() {
        buttonOrder.click();
        return new TimeAndDatePage(driver);
    }

    private void selectDay(int daysFromToday) {
        for (WebElement day : availableDays) {
            if (day.getText().equals(getFutureDay(daysFromToday))) {
                day.click();
                break;
            }
        }
    }

    private void selectTime(String time) {
        boolean hourFromFound = false;
        for (WebElement hour : availableHours) {
            if (hour.getText().equals(time)) {
                hour.click();
                hourFromFound = true;
                break;
            }
        }
        if (!hourFromFound) {
            boolean foundClosest = false;
            int timeInt = Integer.parseInt(time.substring(0, 2)) - 1;

            while (!foundClosest) {
                for (WebElement hour : availableHours) {
                    int hourInt = Integer.parseInt(hour.getText().substring(0, 2));
                    if (timeInt == hourInt) {
                        hour.click();
                        foundClosest = true;
                        break;
                    }
                }
                if (!foundClosest) {
                    timeInt -= 1;
                }
            }
        }
    }

    public TimeAndDatePage fillInTheReservationInfo(int daysFromToday, String timeFrom, int daysFromToday2, String timeTo) {
        clickDateFrom();
        selectDay(daysFromToday);
        hourFrom.click();
        selectTime(timeFrom);
        dateTo.click();
        verifyThatActualDayIsDisabled();
        selectDay(daysFromToday2);
        hourTo.click();
        selectTime(timeTo);
        return clickButtonOrder();
    }

    private void verifyThatActualDayIsDisabled() {
        Assert.assertTrue("Actual day must be disabled!", today.getAttribute("class").contains("ui-state-disabled"));
    }

    private String getFutureDay(int daysFromToday) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int dayInt = calendar.get(Calendar.DAY_OF_MONTH) + daysFromToday;
        return Integer.toString(dayInt);
    }
}