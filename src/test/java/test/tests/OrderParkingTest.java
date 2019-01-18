package test.tests;

import helper.enums.AdditionalServiceType;
import helper.enums.Airport;
import helper.enums.ServiceAction;
import org.junit.Test;
import pageobject.frontpage.FrontPage;
import pageobject.orderpage.OrderPage;
import pageobject.timeanddate.TimeAndDatePage;
import test.TestBase;

public class OrderParkingTest extends TestBase {

    private FrontPage front = new FrontPage(driver);

    @Test
    public void checkThatRigaHasASingleParkingZone() {
        TimeAndDatePage time = front.fillInTheReservationInfo(1, "15:00", 2, "15:00");
        time.selectAirport(Airport.RIGA_AIRPORT.getNumeration());
        time.checkParkingZoneSize(1);
    }

    @Test
    public void orderCheapestParking() {
        TimeAndDatePage time = front.fillInTheReservationInfo(1, "15:00", 2, "15:00");
        time.selectAirport(Airport.VILNIUS_AIRPORT.getNumeration());
        time.enterCarNumber("JCA666");
        OrderPage order = time.selectCheapestParkingZone();
        order.waitUntilPageIsDisplayed();
        order.serviceSelector(AdditionalServiceType.FAST_CHECK_IN.getNumeration(), ServiceAction.ADD.getAction(), 2);
        order.serviceSelector(AdditionalServiceType.LUGGAGE_PACKING.getNumeration(), ServiceAction.ADD.getAction(), 2);
        order.fillInPersonalData("Jonas", "Jonka", "+37066666666", "jon.jonk@jonkatesting.org");
        order.checkAllCheckboxes();
        order.fillInOrganisationData("Jono nakvynės namai", "Saltoniškių g. 15-11", "121212121212", "12121212121214");
    }
}