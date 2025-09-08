package tests;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import pages.contactPageForm;
import utils.configReader;
import driverSetup.basesetup;

public class testContactPage extends basesetup {
	
	private static final SoftAssertions SOFT = new SoftAssertions();
	private contactPageForm page;
	
	@BeforeTest
	public void driverSetUp() {
		setup();
		page = new contactPageForm(driver, SOFT);
	}
	
	@Test
	@Description("Verify Login form")
	@Severity(SeverityLevel.CRITICAL)
	@Story("Loign story")
	public void contactPage() {
		page.enterUserName(configReader.getProperty("user"));
		page.enterContactNumber(configReader.getProperty("contact"));
		page.enterEmail(configReader.getProperty("email"));
		page.selectCountry(configReader.getProperty("country"));
		page.selectEmployeeCount(configReader.getProperty("numberofemployee"));
		page.enterJobTitle(configReader.getProperty("jobtitle"));
		page.enterComment(configReader.getProperty("message"));
//		page.clickCaptcha();
		page.clickContactSales_btn();
		page.afterLoginVerification();
	}
	
	@AfterTest
	public void closeBrowser() {
		teardown();
	}
}
