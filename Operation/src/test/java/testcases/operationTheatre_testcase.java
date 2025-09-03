package testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import coreUtilities.testutils.ApiHelper;
import coreUtilities.utils.FileOperations;
import pages.StartupPage;
import pages.operationTheatre_Pages;
import testBase.AppTestBase;
import testdata.LocatorsFactory;

public class operationTheatre_testcase extends AppTestBase
{
	Map<String, String> configData;
	Map<String, String> loginCredentials;
	String expectedDataFilePath = testDataFilePath + "expected_data.xlsx";
	String loginFilePath = loginDataFilePath + "Login.xlsx";
	StartupPage startupPage;
	operationTheatre_Pages operationTheatre_PagesInstance;
	LocatorsFactory locatorsFactoryInstance;


	@Parameters({"browser", "environment"})
	@BeforeClass(alwaysRun = true)
	public void initBrowser(String browser, String environment) throws Exception {
		configData = new FileOperations().readExcelPOI(config_filePath, environment);
		configData.put("url", configData.get("url").replaceAll("[\\\\]", ""));
		configData.put("browser", browser);

		boolean isValidUrl = new ApiHelper().isValidUrl(configData.get("url"));
		Assert.assertTrue(isValidUrl, configData.get("url")+" might be Server down at this moment. Please try after sometime.");
		initialize(configData);
		startupPage = new StartupPage(driver);
	}

	@Test(priority = 1, groups = {"sanity"}, description="Verify the title and url of  the current page.")
	public void verifyTitleAndURLOfTheHomePage() throws Exception {

		operationTheatre_PagesInstance = new operationTheatre_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);

		Map<String, String> loginData = new FileOperations().readExcelPOI(loginFilePath, "credentials");
		Assert.assertTrue(operationTheatre_PagesInstance.loginToHealthAppByGivenValidCredetial(loginData),"Login failed, Invalid credentials ! Please check manually");

		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "healthApp");
		Assert.assertEquals(operationTheatre_PagesInstance.verifyTitleOfThePage(),expectedData.get("dasboardTitle")) ;
		Assert.assertEquals(operationTheatre_PagesInstance.verifyURLOfThePage(),expectedData.get("pageUrl")) ;
		Assert.assertTrue(locatorsFactoryInstance.totalDoctorTextIsPresent(driver).isDisplayed(), "total doctors text is not present in the current page, Please check manually");
	}

	@Test(priority = 2, groups = {"sanity"}, description="Verify that OperationTheatre module is present or not ?\r\n"
			+ "If OperationTheatre Module is present, \r\n"
			+ "then go to OperationTheatre Page and \r\n"
			+ "check whether the \"Hospital Number\" \r\n"
			+ "header text is present or not in the given table")
	public void goToOperationTheatreModuleVerifyTableHeaderName() throws Exception {
		operationTheatre_PagesInstance = new operationTheatre_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "operationTheatreModule");

		Assert.assertEquals(operationTheatre_PagesInstance.goToOperationTheatreModuleVerifyTableHeaderName(),expectedData.get("hospitalNumber"),  "OperationTheatre Module is not present, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.verifyNewOTBookingButtonIsPresent(driver).isDisplayed(), "New OT Button is not present in the current page, Please check manually");
	}

	@Test(priority = 3, groups = {"sanity"}, description="• On the operation theatre modules, verify that\r\n"
			+ "New OT Booking button is present?\r\n"
			+ "• If New OT Booking button is presenrt then\r\n"
			+ "clicking it popups \"Booking OT Schedule | New Patient\" form.")
	public void verifyBookingOTScheduleFormByClickingOnNewOTBookingButton() throws Exception {

		operationTheatre_PagesInstance = new operationTheatre_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "operationTheatreModuleStringVal");

		Assert.assertEquals(operationTheatre_PagesInstance.verifyBookingOTScheduleFormByClickingOnNewOTBookingButton(),expectedData.get("bookingOTSchedulePageName")) ;
		Assert.assertTrue(locatorsFactoryInstance.verifyAddNewOTButtonIsPresent(driver).isDisplayed(), "Add New OT" + "Button is not present in the current page, Please check manually");
	}

	@Test(priority = 4, groups = {"sanity"}, description="On the \"Booking OT Schedule | New Patient\" form's\r\n"
			+ "validate that\r\n"
			+ "Select Patient textbox\r\n"
			+ "Diagnosis textbox &  \r\n"
			+ "Type of Surgery Textbox are present or not ?\r\n"
			+ "If present then fill the below textboxes :\r\n"
			+ "Select Patient textbox\r\n"
			+ "Diagnosis textbox\r\n"
			+ "Type of Surgery textbox\r\n"
			+ "Check the data which we are entered are present\r\n"
			+ "in Select Patient textbox or not ?")
	public void verifyEnteredDataIsPresentInSelectPatientTextbox() throws Exception {

		operationTheatre_PagesInstance = new operationTheatre_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "operationTheatreModuleStringVal");

		Assert.assertEquals(operationTheatre_PagesInstance.verifyEnteredDataIsPresentInSelectPatientTextbox(expectedData),expectedData.get("patientName")) ;
		Assert.assertEquals(locatorsFactoryInstance.verifyValueIsPresentInTypeOfSurgeryTextbox(),expectedData.get("surgeryTypeValue")) ;
	}

	@Test(priority = 5, groups = {"sanity"}, description="On the \"Booking OT Schedule | New Patient\" form's\r\n"
			+ "validate that Remarks field is present or not ?\r\n"
			+ "Verify \"Remarks\" Text area is enable and\r\n"
			+ "verify the placeholder name  of \"Remarks\" text area.")
	public void verifyPlaceholderNameOfTexboxIfTextboxIsEnabled() throws Exception {

		operationTheatre_PagesInstance = new operationTheatre_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "operationTheatreModuleStringVal");

		Assert.assertEquals(operationTheatre_PagesInstance.verifyPlaceholderNameOfTexboxIfTextboxIsEnabled(),expectedData.get("remarksPlaceHolderName")) ;
		Assert.assertTrue(locatorsFactoryInstance.verifyAddNewOTButtonIsPresent(driver).isDisplayed(), "Add New OT" + "Button is not present in the current page, Please check manually");
	}

	@Test(priority = 6, groups = {"sanity"}, description="On  the \"Booking OT Schedule | New Patient\" form's,\r\n"
			+ "type \"Dr. Pooja Mishra \" in surgen Name textfield and  verify the Dr. pooja mishra button  is present or not ?")
	public void verifyButtonIsPresentAfterSendValueToSurgenNameTextfield() throws Exception {

		operationTheatre_PagesInstance = new operationTheatre_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "operationTheatreModuleStringVal");

		Assert.assertEquals(operationTheatre_PagesInstance.verifyButtonIsPresentAfterSendValueToSurgenNameTextfield(expectedData),expectedData.get("surgenNameValue2")) ;
		Assert.assertTrue(locatorsFactoryInstance.verifySurgenNameFirstButtonElementIsPresent(driver).isDisplayed(), "Surgen Name First Button is not present in the current page, Please check manually");
	}

	@Test(priority = 7, groups = {"sanity"}, description="On  the \"Booking OT Schedule | New Patient\" form's,\r\n"
			+ "click on the \"External?\" checkbox and \r\n"
			+ "verify the check box is selected or not ?")
	public void verifyPlusButtonIsPresentAfterClickOnExternalCheckbox() throws Exception {
		operationTheatre_PagesInstance = new operationTheatre_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Assert.assertTrue(operationTheatre_PagesInstance.verifyPlusButtonIsPresentAfterClickOnExternalCheckbox(), "check box didn't click and Plus icon is not showing, please check manually");
		Assert.assertTrue(locatorsFactoryInstance.verifyPlusIconElementIsPresent(driver).isDisplayed(), "plus icon is not present in the current page, Please check manually");
	}

	@Test(priority = 8, groups = {"sanity"}, description="On the \"Add External Refferal\" form's, \r\n"
			+ "when we click on add button without filling any information, \r\n"
			+ "Verify the error message (Referrer Name is required)")
	public void verifyErrorMessageInAddExternalReferralForms() throws Exception {

		operationTheatre_PagesInstance = new operationTheatre_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "operationTheatreModule");

		Assert.assertEquals(operationTheatre_PagesInstance.verifyErrorMessageInAddExternalReferralForms(),expectedData.get("errorMessageOfAddExternalReferralForms")) ;
		Assert.assertTrue(locatorsFactoryInstance.verifyErrorMessageOfAddExternalReferralForms(driver).isDisplayed(), "Error Message Of AddExternal Referral Forms is not present in the current page, Please check manually");
	}
	
	@Test(priority = 9, groups = {"sanity"}, description="On  the \"Booking OT Schedule | New Patient\" form's, \r\n"
			+ "External? Checkbox must be selected.\r\n"
			+ "Click on \"+\" icon to popup the Add External Referral form\r\n"
			+ "then fill all the details (get the data from excel),\r\n"
			+ "click on all checkbox and then click on \"Add\" button\r\n"
			+ "then verify the success notifications message.")
	public void verifySuccessNotificationPopupMessage() throws Exception {

		operationTheatre_PagesInstance = new operationTheatre_Pages(driver);
		locatorsFactoryInstance = new LocatorsFactory(driver);
		Map<String, String> expectedData = new FileOperations().readExcelPOI(expectedDataFilePath, "addExternalReferralPageInfo");

		Assert.assertEquals(operationTheatre_PagesInstance.verifySuccessNotificationPopupMessage(expectedData),expectedData.get("successNotificationPopupMessage")) ;
		Assert.assertEquals(locatorsFactoryInstance.verifySuccessNotificationPopupMessageIsPresent(),expectedData.get("successNotificationPopupMessage")) ;
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		System.out.println("before closing the browser");
		browserTearDown();
	}

	@AfterMethod
	public void retryIfTestFails() throws Exception {
		startupPage.navigateToUrl(configData.get("url"));
	}
}
