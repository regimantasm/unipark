package test.frontpagetest;

import org.junit.Assert;
import org.junit.Test;
import pageobject.frontpage.FrontPage;
import pageobject.orderpage.OrderPage;
import pageobject.timeanddate.TimeAndDatePage;
import test.TestBase;

public class FrontPageTest extends TestBase {

    private FrontPage front = new FrontPage(driver);

    @Test
    public void checkThatRigaHasASingleParkingZone() {
        TimeAndDatePage time = front.fillInTheReservationInfo(1, "15:00", 2, "15:00");
        time.selectAirport(3);
        time.checkParkingZoneSize(1);
    }

    @Test
    public void selectTheCheapestZoneInVilnius() throws InterruptedException {
        TimeAndDatePage time = front.fillInTheReservationInfo(1, "15:00", 2, "15:00");
        time.selectAirport(1);
        time.enterCarNumber("JCA666");
        OrderPage order = time.selectCheapestParkingZone();
        order.checkPageDisplay();
        order.serviceSelector(1, "up", 2);
        order.serviceSelector(2, "up", 2);
        order.fillInPersonalData("Jonas", "Jonka", "+37066666666", "jon.jonk@jonkatesting.org");
        order.checkAllCheckboxes();
        Thread.sleep(5000);
    }
}