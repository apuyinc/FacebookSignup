package puck.tests.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage {
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(id="code_in_cliff")
	private WebElement confirmationCode;
	
	public boolean requestConfirmationCodeIsPresent() {
		return driver.findElement(By.id("code_in_cliff")) != null;
		//String bodyText = driver.findElement(By.tagName("body")).getText();
	}
	
}
