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

    private int tomorrow = 1;
    private int dayAfterTomorrow = 2;
    private String reservationTimeFrom = "15:00";
    private String reservationTimeTo = "15:00";
    private String name = "Jonas";
    private String lastName = "Jonka";
    private String phoneNumber = "+37066666666";
    private String email = "jon.jonk@jonjonka.lt";
    private String organisationName = "Jonkos nakvynės namai";
    private String address = "Saltoniškių g. 15-11";
    private String organisationNumber = "121212121212";
    private String vatNumber = "12121212121214";

    private FrontPage front = new FrontPage(driver);

    @Test
    public void checkThatRigaHasASingleParkingZone() {
        TimeAndDatePage time = front.fillInTheReservationInfo(tomorrow, reservationTimeFrom, dayAfterTomorrow, reservationTimeTo);
        time.selectAirport(Airport.RIGA_AIRPORT.getNumeration());
        time.checkParkingZoneSize(1);
    }

    @Test
    public void orderCheapestParking() {
        TimeAndDatePage time = front.fillInTheReservationInfo(tomorrow, reservationTimeFrom, dayAfterTomorrow, reservationTimeTo);
        time.selectAirport(Airport.VILNIUS_AIRPORT.getNumeration());
        time.enterCarNumber("JCA666");
        OrderPage order = time.selectCheapestParkingZone();
        order.waitUntilPageIsDisplayed();
        order.serviceSelector(AdditionalServiceType.FAST_CHECK_IN.getNumeration(), ServiceAction.ADD.getAction(), 2);
        order.serviceSelector(AdditionalServiceType.LUGGAGE_PACKING.getNumeration(), ServiceAction.ADD.getAction(), 2);
        order.fillInPersonalData(name, lastName, phoneNumber, email);
        order.checkAllCheckboxes();
        order.fillInOrganisationData(organisationName, address, organisationNumber, vatNumber);
    }
}