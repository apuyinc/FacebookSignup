package puck.tests.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(id="reg_error_inner")
	private WebElement errorMessage;
	
    //@FindBy(id="u_0_n")
	@FindBy(name="firstname")
    private WebElement firstName;
    
	@FindBy(name="lastname")
    private WebElement lastName;
    
    @FindBy(name="reg_email__")
    private WebElement email;
    
    @FindBy(name="reg_email_confirmation__")
    private WebElement confirmEmail;
    
    @FindBy(name="reg_passwd__")
    private WebElement password;
    
    @FindBy(id="day")
    private WebElement day;
    
    @FindBy(id="month")
    private WebElement month;
    
    @FindBy(id="year")
    private WebElement year;
    
    /*@FindBy(name="sex")
    private List<WebElement> gender;*/
    
    @FindBy(css="[type='radio'][name='sex'][value='1']")
    private WebElement genderFemale;
    
    @FindBy(css="[type='radio'][name='sex'][value='2']")
    private WebElement genderMale;
    
    @FindBy(id="u_0_15")
    private WebElement createAccountButton;
    
    public String getFirstName() {
    	return this.firstName.getAttribute("value");
    }
    
    public void clearFirstName() {
    	this.firstName.clear();
    }
    
    public void typeFirstName(String name) {
    	this.firstName.sendKeys(name);
    }
    
    public String getLastName() {
    	return this.lastName.getAttribute("value");
    }
    
    public void typeLastName(String name) {
    	this.lastName.sendKeys(name);
    }
    
    public String getEmailOrPhoneNumber() {
    	return this.email.getAttribute("value");
    }
    
    public void typeEmailOrPhoneNumber(String emailOrPhoneNumber) {
    	this.email.sendKeys(emailOrPhoneNumber);
    }
    
    public String getConfirmationEmail() {
    	return this.confirmEmail.getAttribute("value");
    }
    
    public void typeConfirmationEmail(String email) {
    	this.confirmEmail.sendKeys(email);
    }
    
    public String getPassword( ) {
    	return this.password.getAttribute("value");
    }
    
    public void typePassword(String password) {
    	this.password.sendKeys(password);
    }
    
    public void selectDay(String day) {
    	this.day.sendKeys(day);
    }
    
    public void selectMonth(String month) {
    	this.month.sendKeys(month);
    }
    
    public void selectYear(String year) {
    	this.year.sendKeys(year);
    }
    
    public void selectMale() {
    	this.genderMale.click();
    }
    
    public void selectFemale() {
    	this.genderFemale.click();
    }
    
    public void clickCreateAccount( ) {
    	this.createAccountButton.click();
    }
    
    public boolean isTextInScreen(String text) {
    	String bodyText = driver.findElement(By.tagName("body")).getText();
    	return bodyText.contains(text);
    }
}
