package test.frontpagetest;

import org.junit.Assert;
import org.junit.Test;
import pageobject.frontpage.FrontPage;
import pageobject.orderpage.OrderPage;
import pageobject.timeanddate.TimeAndDatePage;
import test.TestBase;

public class FrontPageTest extends TestBase {

    private String preferredFromTime = "15:00";
    private FrontPage front = new FrontPage(driver);

    @Test
    public void checkThatActualDayIsDisabled() {
        front.selectDatePickerFrom();
        front.selectDay(1);
        front.selectTimePickerFrom();
        front.selectTime(preferredFromTime);
        front.selectDatePickerTo();
        Assert.assertTrue(front.verifyThatActualDayIsDisabled());
    }

    @Test
    public void checkThatRigaHasASingleParkingZone() {
        TimeAndDatePage time = fillInTheReservationInfo();
        time.selectAirport(3);
        time.checkParkingZoneSize(1);
    }

    @Test
    public void selectTheCheapestZoneInVilnius() throws InterruptedException {
        String carNumber = "JDC777";
        TimeAndDatePage time = fillInTheReservationInfo();
        time.selectAirport(1);
        time.enterCarNumber(carNumber);
        OrderPage order = time.selectCheapestParkingZone();
        order.checkPageDisplay();
        order.serviceSelector(1, "up", 2);
        order.serviceSelector(2, "up", 2);
        order.fillInPersonalData("Jonas", "Jonka", "+37066666666", "jon.jonk@jonkatesting.org");
        order.checkAllCheckboxes();
        Thread.sleep(5000);
    }

    private TimeAndDatePage fillInTheReservationInfo() {
        front.selectDatePickerFrom();
        front.selectDay(1);
        front.selectTimePickerFrom();
        front.selectTime(preferredFromTime);
        front.selectDatePickerTo();
        front.selectDay(2);
        front.selectTimePickerTo();
        String preferredToTime = "15:00";
        front.selectTime(preferredToTime);
        return front.clickButtonOrder();
    }
}