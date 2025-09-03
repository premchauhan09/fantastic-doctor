package pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class operationTheatre_Pages extends StartupPage {

    // ---------- Locators ----------
    // TC-1
    By usernameTextbox = By.id("username_id");
    By passwordTextbox = By.id("password");
    By signInButton = By.id("login");
    By registeredPatientTextElement = By.xpath("//p[contains(text(), 'Registered Patient')]");

    // TC-2
    By operationTheatreModule = By.xpath("//span[.='OperationTheatre']");
    By hospitalNumberByElement = By.xpath("//span[contains(text(), 'Hospital Number')]");

    // TC-3
    By newOtButtonByElement = By.xpath("//button[contains(text(), 'New OT Booking')]");
    By bookingOTScheduleFormsTitleNameByElement = By.xpath("//span[contains(text(), 'Booking OT Schedule  | New Patient')]");

    // TC-4
    By selectpatientTextboxElement = By.id("srch_PatientList");
    By diagnosisTextboxElement = By.id("Diagnosis");
    By surgeryTypeTextboxElement = By.id("surgeryType");

    // TC-5
    By remarksTextAreaByElement = By.id("remarks");

    // TC-6
    By remarkTextboxElement = By.xpath("//textarea[@placeholder='Remarks']");
    By surgenNameTextFieldElement = By.id("id_input_currentRequestedByDoctor_selectReferral");
    By surgenNameFirstButtonElement = By.xpath("//ul[@class='select-list']//li[1]");

    // TC-7
    By externalCheckBoxElement = By.xpath("//label[@class='is-ref mt-checkbox mt-checkbox-outline']//span");

    // TC-8
    By plusIconButtonElement = By.xpath("//a[@class='btn blue btn-xs btn-ext']");
    By addButtonOfAddExternalReferralByElement = By.xpath("//button[contains(text(), 'Add')]");
    By errorMessageOfAddExternalReferralFormsByElement = By.xpath("//span[contains(text(), 'Referrer Name is required')]");

    // TC-9
    By referrerNameTextFieldElement = By.id("referrerName");
    By extRefferAddressTextFieldElement = By.id("ExtRefferaddress");
    By contactNumberTextFieldElement = By.id("contactNum");
    By emailTextFieldElement = By.id("email");
    By isIncentiveApplicableCheckboxElement = By.xpath("(//div[@class='icheck-inline']//span)[2]");
    By addButtonElement = By.id("add");
    By successNotificationPopupMessageElement = By.cssSelector("div.msg-text.padding-8-all");
    By successNotificationPopupMessageOneElement = By.cssSelector("p.msg-status");
    By successNotificationPopupMessageTwoElement = By.cssSelector("p.main-message");
    By titleOfaddExternalReferralFormsByElement = By.xpath("//span[contains(text(), 'Add External Referral')]");

    public operationTheatre_Pages(WebDriver driver) {
        super(driver);
    }

    // ---------- TC 1.1 ----------
    public boolean loginToHealthAppByGivenValidCredetial(Map<String, String> expectedData) {
        driver.findElement(usernameTextbox).sendKeys(expectedData.get("username"));
        driver.findElement(passwordTextbox).sendKeys(expectedData.get("password"));
        driver.findElement(signInButton).click();
        return driver.findElement(registeredPatientTextElement).isDisplayed();
    }

    // ---------- TC 1.2 ----------
    public String verifyTitleOfThePage() {
        return driver.getTitle();
    }

    // ---------- TC 1.3 ----------
    public String verifyURLOfThePage() {
        return driver.getCurrentUrl();
    }

    // ---------- TC 2 ----------
    public String goToOperationTheatreModuleVerifyTableHeaderName() throws InterruptedException {
        driver.findElement(operationTheatreModule).click();
        Thread.sleep(3000);
        return driver.findElement(hospitalNumberByElement).getText();
    }

    // ---------- TC 3 ----------
    public String verifyBookingOTScheduleFormByClickingOnNewOTBookingButton() throws InterruptedException {
        driver.findElement(newOtButtonByElement).click();
        Thread.sleep(2000);
        return driver.findElement(bookingOTScheduleFormsTitleNameByElement).getText();
    }

    // ---------- TC 4 ----------
    public String verifyEnteredDataIsPresentInSelectPatientTextbox(Map<String, String> expectedData) {
        driver.findElement(selectpatientTextboxElement).sendKeys(expectedData.get("patientName"));
        driver.findElement(diagnosisTextboxElement).sendKeys(expectedData.get("diagnosisValue"));
        driver.findElement(surgeryTypeTextboxElement).sendKeys(expectedData.get("surgeryTypeValue"));
        return driver.findElement(selectpatientTextboxElement).getAttribute("value");
    }

    // ---------- TC 5 ----------
    public String verifyPlaceholderNameOfTexboxIfTextboxIsEnabled() throws InterruptedException {
        WebElement remarks = driver.findElement(remarksTextAreaByElement);
        if (remarks.isDisplayed() && remarks.isEnabled()) {
            remarks.click();
            Thread.sleep(2000);
            return remarks.getAttribute("placeholder");
        }
        return "";
    }

    // ---------- TC 6 ----------
    public String verifyButtonIsPresentAfterSendValueToSurgenNameTextfield(Map<String, String> expectedData) throws InterruptedException {
        driver.findElement(surgenNameTextFieldElement).sendKeys(expectedData.get("surgenNameValue1"), Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(remarkTextboxElement).click();
        Thread.sleep(2000);
        return driver.findElement(surgenNameFirstButtonElement).getText();
    }

    // ---------- TC 7 ----------
    public boolean verifyPlusButtonIsPresentAfterClickOnExternalCheckbox() throws InterruptedException {
        driver.findElement(externalCheckBoxElement).click();
        Thread.sleep(3000);
        return driver.findElement(plusIconButtonElement).isDisplayed();
    }

    // ---------- TC 8 ----------
    public String verifyErrorMessageInAddExternalReferralForms() throws InterruptedException {
        driver.findElement(plusIconButtonElement).click();
        Thread.sleep(2000);
        driver.findElement(addButtonOfAddExternalReferralByElement).click();
        Thread.sleep(2000);
        return driver.findElement(errorMessageOfAddExternalReferralFormsByElement).getText();
    }

    // ---------- TC 9 ----------
    public String verifySuccessNotificationPopupMessage(Map<String, String> expectedData) throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(referrerNameTextFieldElement).sendKeys(expectedData.get("referrerName"));
        driver.findElement(extRefferAddressTextFieldElement).sendKeys(expectedData.get("ExtRefferaddress"));
        driver.findElement(contactNumberTextFieldElement).sendKeys(expectedData.get("contactNum"));
        driver.findElement(emailTextFieldElement).sendKeys(expectedData.get("email"));
        driver.findElement(isIncentiveApplicableCheckboxElement).click();
        driver.findElement(addButtonElement).click();

        String msg1 = driver.findElement(successNotificationPopupMessageOneElement).getText();
        String msg2 = driver.findElement(successNotificationPopupMessageTwoElement).getText();
        return msg1 + " " + msg2;
    }
}