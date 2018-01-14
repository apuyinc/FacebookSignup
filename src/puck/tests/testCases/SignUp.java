package puck.tests.testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import puck.tests.pageObject.ConfirmationPage;
import puck.tests.pageObject.HomePage;
import puck.tests.utils.PuckTestWatcher;

public class SignUp extends PuckTestWatcher {

	WebDriver driver;
	Random generator = new Random();
	Properties prop = new Properties();
	InputStream input = null;
	
	@Before
	public void setUp() {
	    try {
			input = new FileInputStream("src/resources/conf.properties");
		} catch (FileNotFoundException e) {
			System.out.println("error loading the properties file");
			e.printStackTrace();
		}
		try {
			prop.load(input);
		} catch (IOException e) {
			System.out.println("error loading the properties from the file");
			e.printStackTrace();
		}

		String browser = prop.getProperty("Browser");
		switch (browser) {
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverLocator"));
			driver = new ChromeDriver();
			break;
		default:
			System.setProperty("webdriver.chrome.driver", prop.getProperty("ChromeDriverLocator"));
			driver = new ChromeDriver();
			//here the rest of the browsers
		}
		
		driver.get(prop.getProperty("SignUpURL"));
		driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("ImplicitWait")), TimeUnit.SECONDS);
	}

	@Test
	public void RegisterOk() {

		HomePage hp = PageFactory.initElements(driver, HomePage.class);
		ConfirmationPage cp = PageFactory.initElements(driver, ConfirmationPage.class);
		fillGenericFields(hp);
		String email = generateEmail();
		hp.typeEmailOrPhoneNumber(email);
		hp.typeConfirmationEmail(email);
		hp.clickCreateAccount();
	
		Assert.assertTrue("user not redirected to confirmation page after sign up", cp.requestConfirmationCodeIsPresent());
		
	}
	
	@Test
	public void RegisterUTF8Names() {
		HomePage hp = PageFactory.initElements(driver, HomePage.class);
		ConfirmationPage cp = PageFactory.initElements(driver, ConfirmationPage.class);
		
		String email = generateEmail();
		hp.typeEmailOrPhoneNumber(email);
		hp.typeConfirmationEmail(email);
		hp.typeFirstName(prop.getProperty("FirstNameUTF8"));
		fillGenericFields(hp);
		hp.clickCreateAccount();	
		
		Assert.assertTrue("user not redirected to confirmation page after sign up", cp.requestConfirmationCodeIsPresent());
	}
	
	@Test
	public void RegisterUsingMobile() {
		HomePage hp = PageFactory.initElements(driver, HomePage.class);
		ConfirmationPage cp = PageFactory.initElements(driver, ConfirmationPage.class);
		
		hp.typeEmailOrPhoneNumber(prop.getProperty("PhoneNumber"));
		fillGenericFields(hp);
		hp.clickCreateAccount();
		
		Assert.assertTrue("user not redirected to confirmation page after sign up", cp.requestConfirmationCodeIsPresent());
	}
	
	@Test
	public void MissingFirstName() {
		HomePage hp = PageFactory.initElements(driver, HomePage.class);
		
		hp.typeEmailOrPhoneNumber(prop.getProperty("PhoneNumber"));
		fillGenericFields(hp);
		hp.clearFirstName();
		hp.clickCreateAccount();
		
		Assert.assertEquals("user does not remain in sign up page", driver.getCurrentUrl(), prop.getProperty("SignUpURL"));
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	private void fillGenericFields(HomePage hp) {
		if (hp.getFirstName().length() == 0) hp.typeFirstName(prop.getProperty("FirstName"));
		if (hp.getLastName().length() == 0) hp.typeLastName(prop.getProperty("LastName"));
		hp.selectDay(prop.getProperty("Day1"));
		hp.selectMonth(prop.getProperty("Month"));
		hp.selectYear(prop.getProperty("Year"));
		if (hp.getPassword().length() == 0) hp.typePassword(prop.getProperty("Password"));
		hp.selectFemale();
	}
	
	private String generateEmail() {
		int random = generator.nextInt(1000) + 1;
		return prop.getProperty("Email")+random+prop.getProperty("EmailDomain");
	}
	
}
