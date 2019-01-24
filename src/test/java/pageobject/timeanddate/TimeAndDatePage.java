package pageobject.timeanddate;

import helper.Scroll;
import helper.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.PageObject;
import pageobject.orderpage.OrderPage;

import java.util.*;

import static org.junit.Assert.*;

public class TimeAndDatePage extends PageObject {

    @FindBy(css = ".tabs a")
    private List<WebElement> airPorts;

    @FindBy(id = "nr")
    private WebElement inputCarNumber;

    @FindBy(id = "place_0")
    private WebElement zones;

    @FindBy(css = "div[id*='place'][style*='block'] tr[id*=zone]")
    private List<WebElement> parkingZones;

    public TimeAndDatePage(WebDriver driver) {
        super(driver);
    }

    public void selectAirport(int number) {
        airPorts.get(number).click();
        airPorts.get(number).click();
    }

    public void checkParkingZoneSize(int size) {
        assertEquals("The size should be equal to " + size, parkingZones.size(), size);
    }

    public void enterCarNumber(String carNumber) {
        inputCarNumber.sendKeys(carNumber);
    }

    public OrderPage selectCheapestParkingZone() {
        Scroll.scrollToElement(driver, parkingZones.get(0));
        Wait.waitUntilTheElementIsVisible(driver, parkingZones.get(0));
        for (WebElement zone : parkingZones) {
            String priceFormatted = zone.findElement(By.className("coll-4")).getText().replace(",", ".");
            if (priceFormatted.contains(findCheapestParkingZone().toString())) {
                zone.findElement(By.className("choose-btn")).click();
                break;
            }
        }
        return new OrderPage(driver);
    }

    private Double findCheapestParkingZone() {
        List<String> prices = new ArrayList<String>();
        for (WebElement zone : parkingZones) {
            String price = zone.findElement(By.className("coll-4")).getText().replace(",", ".");
            prices.add(price);
        }
        Iterator<String> i = prices.iterator();
        while (i.hasNext()) {
            String s = i.next();
            if (s.equals("Užsakyk")) {
                i.remove();
            }
        }
        List<Double> pricesd = new ArrayList<Double>();
        for (String price : prices) {
            double p = Double.parseDouble(price);
            pricesd.add(p);
        }
        return Collections.min(pricesd);
    }
}