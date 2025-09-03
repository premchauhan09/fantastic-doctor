package pages;

import org.openqa.selenium.WebDriver;



public class StartupPage {
	
	public WebDriver driver;
//	public UserActions userActions;
	
	public StartupPage(WebDriver driver) {
		this.driver = driver;
	}
	
//	public LoginPage navigateToLoginPage() {
//		return new LoginPage(driver);
//	}
	
	public void navigateToUrl(String url) throws Exception {
		
		driver.get(url);		 
	}

}
