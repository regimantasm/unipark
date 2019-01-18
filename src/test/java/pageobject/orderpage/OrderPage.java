package pageobject.orderpage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.PageObject;

import java.util.List;

public class OrderPage extends PageObject {

    private WebDriverWait wait = new WebDriverWait(driver, 5);

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

    public void checkPageDisplay() {
        wait.until(ExpectedConditions.visibilityOf(inputFirstName));
    }

    public void serviceSelector(int service, String arrow, int quantity) {
        for (int i = 0; i < quantity; i++) {
            additionalServices.get(service - 1).findElement(By.className(arrow)).click();
        }
    }

    public void checkAllCheckboxes() {
        checkRulesAndConditions();
        checkNewsLetter();
        checkReceiveInvoice();
    }

    public void fillInPersonalData(String firstName, String lastName, String phoneNumber, String email) {
        inputFirstName.sendKeys(firstName);
        inputLasttName.sendKeys(lastName);
        inputPhoneNumber.sendKeys(phoneNumber);
        inputEmail.sendKeys(email);
    }

    public void fillInCompanyData(String companyName, String address, String organisationNumber, String vatNumber) {
        inputOrganisationName.sendKeys(companyName);
        inputAddress.sendKeys(address);
        inputOrganisationNumber.sendKeys(organisationNumber);
        inputVatNumber.sendKeys(vatNumber);
    }

    private void checkRulesAndConditions() {
        scrollToElement(checkboxes.get(0));
        checkboxes.get(0).click();
        scrollToElement(buttonAccept);
        buttonAgreeCookie.click();
        wait.until(ExpectedConditions.visibilityOf(buttonAccept));
        buttonAccept.click();
    }

    private void checkNewsLetter() {
        checkboxes.get(2).click();
    }

    private void checkReceiveInvoice() {
        checkboxes.get(3).click();

    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}