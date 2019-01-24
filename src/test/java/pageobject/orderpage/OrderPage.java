package pageobject.orderpage;

import helper.Scroll;
import helper.Wait;
import helper.enums.CheckboxType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobject.PageObject;

import java.util.List;

public class OrderPage extends PageObject {

    @FindBy(css = "table[class=service-item]:not([style*='none'])")
    private List<WebElement> additionalServices;

    @FindBy(css = "#firstname1")
    private WebElement inputFirstName;

    @FindBy(css = "#lastname1")
    private WebElement inputLasttName;

    @FindBy(css = "#vehicle_number1")
    private WebElement inputCarNumber;

    @FindBy(css = "#phone_number1")
    private WebElement inputPhoneNumber;

    @FindBy(css = "#email1")
    private WebElement inputEmail;

    @FindBy(css = "#invoice01")
    private WebElement inputOrganisationName;

    @FindBy(css = "#invoice02")
    private WebElement inputAddress;

    @FindBy(css = "#invoice03")
    private WebElement inputOrganisationNumber;

    @FindBy(css = "#invoice04")
    private WebElement inputVatNumber;

    @FindBy(css = "#accept-button")
    private WebElement buttonAccept;

    @FindBy(css = ".checkDivBox .accept-text")
    private List<WebElement> checkboxes;

    @FindBy(css = ".cookieConsentOK")
    private WebElement buttonAgreeCookie;

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilPageIsDisplayed() {
        Wait.waitUntilTheElementIsVisible(driver, inputFirstName);
    }

    public void serviceSelector(int service, String action, int quantity) {
        for (int i = 0; i < quantity; i++) {
            additionalServices.get(service).findElement(By.className(action)).click();
        }
    }

    public void fillInPersonalData(String firstName, String lastName, String phoneNumber, String email) {
        inputFirstName.sendKeys(firstName);
        inputLasttName.sendKeys(lastName);
        inputPhoneNumber.sendKeys(phoneNumber);
        inputEmail.sendKeys(email);
    }

    public void fillInOrganisationData(String organisationName, String address, String organisationNumber, String vatNumber) {
        inputOrganisationName.sendKeys(organisationName);
        inputAddress.sendKeys(address);
        inputOrganisationNumber.sendKeys(organisationNumber);
        inputVatNumber.sendKeys(vatNumber);
    }

    public void checkAllCheckboxes() {
        checkRulesAndConditions();
        checkNewsLetter();
        checkReceiveInvoice();
    }

    private void checkRulesAndConditions() {
        Scroll.scrollToElement(driver, checkboxes.get(CheckboxType.TERMS_AND_CONDITIONS.getNumeration()));
        checkboxes.get(CheckboxType.TERMS_AND_CONDITIONS.getNumeration()).click();
        Scroll.scrollToElement(driver, buttonAccept);
        buttonAgreeCookie.click();
        Wait.waitUntilTheElementIsVisible(driver, buttonAccept);
        buttonAccept.click();
    }

    private void checkNewsLetter() {
        checkboxes.get(CheckboxType.NEWS_LETTER.getNumeration()).click();
    }

    private void checkReceiveInvoice() {
        checkboxes.get(CheckboxType.GET_INVOICE.getNumeration()).click();
    }
}