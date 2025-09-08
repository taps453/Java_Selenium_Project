package utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class common {
	
	public static WebDriverWait standby(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(180));
	}
	
	public static JavascriptExecutor param(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js;
	}
}
