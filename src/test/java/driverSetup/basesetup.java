package driverSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.configReader;

public class basesetup {
	
	protected WebDriver driver;
	
	public void setup() {
		String browser = configReader.getProperty("browser");		
		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new  ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else {
			throw new RuntimeException("browser not found" + browser);
		}
		driver.get(configReader.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	public void teardown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
