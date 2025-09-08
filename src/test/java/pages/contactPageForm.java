package pages;

import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import utils.common;

public class contactPageForm extends common {
	private final WebDriver driver;
	private final SoftAssertions SOFT;
	
	public contactPageForm(WebDriver driver, SoftAssertions SOFT) {
		this.driver = driver;
		this.SOFT = SOFT;
	}
	
	By usernameBy = By.id("Form_getForm_FullName");
	By usernamelabelBy = By.xpath("//*[@id = 'Form_getForm_FullName_Holder']//label");
	By contactBy = By.id("Form_getForm_Contact");
	By emailBy = By.id("Form_getForm_Email");
	By countryNameBy = By.id("Form_getForm_Country");
	By employeeNumberBy = By.id("Form_getForm_NoOfEmployees");
	By jobTitleBy = By.id("Form_getForm_JobTitle");
	By messageBy = By.id("Form_getForm_Comment");
	By captchabtnBy = By.xpath("//span[@id='recaptcha-anchor']");
	By contactbtnBy = By.id("Form_getForm_action_submitForm");
	By headingtextBy = By.xpath("//*[@class = 'thank-you-box']/h1");
	
	public void enterUserName(String username) {
		standby(driver).until(ExpectedConditions.visibilityOfElementLocated(usernameBy));
		
		SOFT.assertThat(driver.findElement(usernamelabelBy).getText())
			.withFailMessage("Label value is changed" + driver.findElement(usernamelabelBy).getText())
			.isEqualTo("Full Name");

		SOFT.assertThat(driver.findElement(usernameBy).getAttribute("placeholder"))
		.withFailMessage("Placeholder value is not Matching" + driver.findElement(usernameBy).getAttribute("placeholder"))
		.isEqualTo("Your Full Name*");
		
		driver.findElement(usernameBy).sendKeys(username);
	}
	
	public void enterContactNumber(String number) {
		standby(driver).until(ExpectedConditions.visibilityOfElementLocated(contactBy));
		driver.findElement(contactBy).sendKeys(number);
	}
	
	public void enterEmail(String email) {
		standby(driver).until(ExpectedConditions.visibilityOfElementLocated(emailBy));
		driver.findElement(emailBy).sendKeys(email);
	}
	
	public void selectCountry(String country_name) {
		standby(driver).until(ExpectedConditions.visibilityOfElementLocated(countryNameBy));
		
		Select select = new Select(driver.findElement(countryNameBy));
		List<WebElement> elementCount = select.getOptions();
		
		for(WebElement value : elementCount) {
			String country = value.getText().trim();
			if(country.equalsIgnoreCase(country_name)) {
				value.click();
				break;
			}
		}
	}
	
	public void selectEmployeeCount(String employeeNumber) {
		standby(driver).until(ExpectedConditions.visibilityOfElementLocated(employeeNumberBy));
		Select select = new Select(driver.findElement(employeeNumberBy));
		
		List<WebElement> employee_count = select.getOptions();
		
		for(WebElement value : employee_count) {
			String employee = value.getText().trim();
			if(employee.equalsIgnoreCase(employeeNumber)) {
				value.click();
				break;
			}
		}	
		SOFT.assertThat(driver.findElement(employeeNumberBy).getText())
		.withFailMessage("Employee Number is not selected" + driver.findElement(employeeNumberBy).getText())
		.contains("200 - 1,000");
	}
	
	public void enterJobTitle(String jobtitle) {
		standby(driver).until(ExpectedConditions.visibilityOfElementLocated(jobTitleBy));
		driver.findElement(jobTitleBy).sendKeys(jobtitle);;
	}
		
	public void enterComment(String message) {
		standby(driver).until(ExpectedConditions.visibilityOfElementLocated(messageBy));
		driver.findElement(messageBy).sendKeys(message);;
	}
	
	public void clickCaptcha() {
		standby(driver).until(ExpectedConditions.visibilityOfElementLocated(captchabtnBy));
		standby(driver).until(ExpectedConditions.elementToBeClickable(captchabtnBy));
		try {
			driver.findElement(captchabtnBy).click();
		} catch (Exception e) {
			System.out.println("Retrying click");
			param(driver).executeScript("arguments[0].click()", captchabtnBy);
		}
	}
	
	public void clickContactSales_btn() {
		standby(driver).until(ExpectedConditions.visibilityOfElementLocated(contactbtnBy));
		standby(driver).until(ExpectedConditions.elementToBeClickable(contactbtnBy));
		try {
			driver.findElement(contactbtnBy).click();
		} catch (Exception e) {
			System.out.println("Retrying click");
			param(driver).executeScript("arguments[0].click()", driver.findElement(contactbtnBy));
		}
	}
	
	public void afterLoginVerification() {
		standby(driver).until(ExpectedConditions.visibilityOfElementLocated(headingtextBy));
		SOFT.assertThat(driver.findElement(headingtextBy).getText())
			.withFailMessage("Heading name is not matching" + driver.findElement(headingtextBy).getText())
			.contains("Thank You for Reaching Out to ");
	}
}
