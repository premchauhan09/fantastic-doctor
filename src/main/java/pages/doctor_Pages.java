package pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class doctor_Pages extends StartupPage {

    WebDriver driver;
    WebDriverWait wait;

    // Locators for TC-1
    By usernameTextbox = By.xpath("//input[@id='username_id']");
    By passwordTextbox = By.xpath("//input[@id='password']");
    By signInButton = By.xpath("//button[@id='login']");
    By doctorTab = By.xpath("//span[.='Doctor']");

    // Locators for TC-2
    By doctorToggle = By.xpath("//span[@data-target='#Doctor']");
    By outPatientSubModule = By.xpath("//span[.='Out Patient']");
    By inPatientDepartmentSubModule = By.xpath("//span[.='In Patient Department']");
    By patientRecordSubModule = By.xpath("//span[.='Patient Record']");

    // Locators for TC-3
    By showDoctorWisePatientListCheckBox = By.id("showDoctorWisePatients");

    // Locators for TC-4
    By departmentFilterDropdown = By.id("departmentlist");

    // Locators for TC-5
    By myFavoritesButton = By.xpath("//a[.=' My Favorites']");
    By pendingListButton = By.xpath("//a[.=' Pending List']");
    By showDetailsButton = By.xpath("(//a[contains(text(),'Show Details')])[1]");
    By freeTextTemplatePageTitle = By.xpath("//div[.=' Progress Note ']");

    // Locators for TC-7
    By XbuttonInFreeTextTemplate = By.xpath("//i[.='X']");
    By doctorNameWhereHospitalNumberIs2312000010 = By.xpath("//div[.='Dr. Amit Shah']");

    // Locators for TC-8
    By previewIcon = By.xpath("//a[@title='Preview']");
    By problemsModule = By.xpath("//a[.='Problems']");
    By surgicalHistoryTab = By.xpath("//a[.='Surgical History']");
    By addNewButton = By.xpath("//a[.=' Add New ']");
    By addButton = By.xpath("//input[@name='name']");
    By searchProblemFieldErrorMessage = By.xpath("//span[.=' Select ICD-11 Code ']");

    // Locators for TC-9
    By dischargeSummaryModule = By.xpath("//a[.='Discharge Summary']");
    By updateButton = By.xpath("(//input[@value='Update'])[2]");

    // ✅ Constructor
    public doctor_Pages(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ✅ Safe click utility
    private void safeClick(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    // ✅ TC-1: Login
    public boolean loginToHealthAppByGivenValidCredetial(Map<String, String> expectedData) throws Exception {
        driver.findElement(usernameTextbox).sendKeys(expectedData.get("username"));
        driver.findElement(passwordTextbox).sendKeys(expectedData.get("password"));
        driver.findElement(signInButton).click();

        return wait.until(ExpectedConditions.visibilityOfElementLocated(doctorTab)).isDisplayed();
    }

    public String verifyTitleOfThePage() {
        return driver.getTitle();
    }

    public String verifyURLOfThePage() {
        return driver.getCurrentUrl();
    }

    // ✅ TC-2: Verify Submodules
    public Boolean verifyAllSubModulesArePresentAndClickOnDispensary() {
        safeClick(doctorToggle);

        boolean opd = wait.until(ExpectedConditions.visibilityOfElementLocated(outPatientSubModule)).isDisplayed();
        boolean ipd = wait.until(ExpectedConditions.visibilityOfElementLocated(inPatientDepartmentSubModule)).isDisplayed();
        boolean record = wait.until(ExpectedConditions.visibilityOfElementLocated(patientRecordSubModule)).isDisplayed();

        return opd && ipd && record;
    }

    // ✅ TC-3: Checkbox
    public Boolean tickOnCheckBoxValidateTheCheckBoxThenUntick() {
        WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(showDoctorWisePatientListCheckBox));
        safeClick(showDoctorWisePatientListCheckBox);
        boolean selected = checkbox.isSelected();
        safeClick(showDoctorWisePatientListCheckBox); // untick
        return selected;
    }

    // ✅ TC-4: Dropdown
    public String selectNEUROSURGERYFromDepartmentDropdownAndVerifySelection(Map<String, String> expectedData) {
        safeClick(inPatientDepartmentSubModule);

        Select select = new Select(wait.until(ExpectedConditions.elementToBeClickable(departmentFilterDropdown)));
        select.selectByVisibleText(expectedData.get("departmentName"));
        return select.getFirstSelectedOption().getText();
    }

    // ✅ TC-5: Favorites + Pending list
    public Boolean verifyMyFavoritesAndPendingListButtonsArePresent() {
        boolean fav = wait.until(ExpectedConditions.visibilityOfElementLocated(myFavoritesButton)).isDisplayed();
        boolean pend = wait.until(ExpectedConditions.visibilityOfElementLocated(pendingListButton)).isDisplayed();
        return fav && pend;
    }

    public String validateTheTitleNameOfTheFreeTextTemplateForm() throws InterruptedException {
        safeClick(pendingListButton);
        safeClick(showDetailsButton);
        Thread.sleep(2000); // small pause
        return wait.until(ExpectedConditions.visibilityOfElementLocated(freeTextTemplatePageTitle)).getText();
    }

    // ✅ TC-7: Validate doctor name
    public String validateTheDoctorName() throws InterruptedException {
        safeClick(XbuttonInFreeTextTemplate);
        safeClick(pendingListButton);
        Thread.sleep(2000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(doctorNameWhereHospitalNumberIs2312000010)).getText();
    }

    // ✅ TC-8: Error message in problems
    public String verifyTheErrorMessageInSearchProblemField() throws InterruptedException {
        safeClick(previewIcon);
        safeClick(problemsModule);
        safeClick(surgicalHistoryTab);
        safeClick(addNewButton);
        safeClick(addButton);
        Thread.sleep(1000);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchProblemFieldErrorMessage)).getText();
    }

    // ✅ TC-9: Scroll + verify button
    public Boolean performScrollingOpertaionAndVerifyThePresenceOfButton() throws InterruptedException {
        WebElement dischargeSummary = wait.until(ExpectedConditions.visibilityOfElementLocated(dischargeSummaryModule));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dischargeSummary);
        dischargeSummary.click();

        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(updateButton)).isDisplayed();
    }

}
