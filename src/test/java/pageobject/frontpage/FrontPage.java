package pageobject.frontpage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.PageObject;
import pageobject.timeanddate.TimeAndDatePage;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class FrontPage extends PageObject {

    @FindBy(id = "time_from")
    private WebElement dateFrom;

    @FindBy(id = "hour_from")
    private WebElement hourFrom;

    @FindBy(id = "time_to")
    private WebElement dateTo;

    @FindBy(id = "hour_to")
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

    public void selectDatePickerFrom() {
        dateFrom.click();
    }

    public void selectTimePickerFrom() {
        hourFrom.click();
    }

    public void selectDatePickerTo() {
        dateTo.click();
    }

    public void selectTimePickerTo() {
        hourTo.click();
    }

    public TimeAndDatePage clickButtonOrder() {
        buttonOrder.click();
        return new TimeAndDatePage(driver);
    }

    public void selectDay(int daysFromToday) {
        for (WebElement day : availableDays) {
            if (day.getText().equals(getFutureDay(daysFromToday))) {
                day.click();
                break;
            }
        }
    }

    public void selectTime(String time) {
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
            int timeInt = Integer.parseInt(time.substring(0, 2));

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

    private String getFutureDay(int daysFromToday) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int dayInt = calendar.get(Calendar.DAY_OF_MONTH) + daysFromToday;
        return Integer.toString(dayInt);
    }

    public boolean verifyThatActualDayIsDisabled() {
        boolean todayIsDisabled = false;
        for (WebElement day : unavailableDays) {
            if (day.getText().equals(getFutureDay(0))) {
                todayIsDisabled = true;
            }
        }
        return todayIsDisabled;
    }
}