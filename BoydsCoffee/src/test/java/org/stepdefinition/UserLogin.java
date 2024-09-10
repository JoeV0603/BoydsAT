package org.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class UserLogin {

	WebDriver driver = new ChromeDriver();
	
	@Given("User navigates to the login page")
    public void navigateToLoginPage() throws InterruptedException {
        
		driver.get("https://boyds.wp.shottqsr.com/");

		driver.manage().window().maximize();

		Thread.sleep(1000);
		
		driver.findElement(By.xpath("(//a[@class=\'main-login'])")).click();
			
		Thread.sleep(1000);
		
    }

    @When("User enters valid credentials")
    public void enterValidCredentials() throws InterruptedException {
        
        Thread.sleep(1000);
		
		driver.findElement(By.name("username")).sendKeys("vishal@hurekatek.com");
		
		driver.findElement(By.name("password")).sendKeys("Joevishal@6394");
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[@class='woocommerce-button button woocommerce-form-login__submit']")).click();

		Thread.sleep(1000);
    }

    @Then("User should be logged in successfully")
    public void verifyLoginSuccess() throws InterruptedException {
        
    	WebElement successMessage = driver.findElement(By.xpath("//div[@class='woocommerce-MyAccount-content']"));

    	assertTrue(successMessage.isDisplayed());

    	String actualText = successMessage.getText();

    	String expectedSubstring = "Your Custom User ID:";

    	assertTrue("The success message does not contain the expected text.", actualText.contains(expectedSubstring));
    	Thread.sleep(2000);
    	
        driver.quit();
    	
    }
    
    @When("User enters invalid credentials")
    public void enterInvalidCredentials() throws InterruptedException {
        
        Thread.sleep(1000);
		
		driver.findElement(By.name("username")).sendKeys("vishal+onee@hurekatek.com");
		
		driver.findElement(By.name("password")).sendKeys("Joevishal@6394");
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[@class='woocommerce-button button woocommerce-form-login__submit']")).click();

		Thread.sleep(1000);
    	
    }

    @Then("User should see an error message")
    public void verifyErrorMessage() throws InterruptedException {
        
    	Thread.sleep(2000);
  	  
        WebElement invaliderror = driver.findElement(By.xpath("(//ul[@role='alert'])"));
        
        assertTrue(invaliderror.isDisplayed());
        
        assertEquals("Unknown email address. Check again or try your username.", invaliderror.getText());
    
        Thread.sleep(2000);
        
      driver.quit();
    	
    }
    
    @When("User enters incorrect password")
    public void enterIncorrectPassword() throws InterruptedException {
        
        Thread.sleep(1000);
		
		driver.findElement(By.name("username")).sendKeys("vishal@hurekatek.com");
		
		driver.findElement(By.name("password")).sendKeys("Joevishal@0603");
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[@class='woocommerce-button button woocommerce-form-login__submit']")).click();

		Thread.sleep(1000);
		
    }

    @Then("User should see an error message1")
    public void verifyErrorMessage1() throws InterruptedException {
        
    	Thread.sleep(2000);
    	  
        WebElement incorrectPass = driver.findElement(By.xpath("(//ul[@role='alert'])"));
        
        assertTrue(incorrectPass.isDisplayed());
        
        assertEquals("Error: The password you entered for the email address vishal@hurekatek.com is incorrect. Lost your password?", incorrectPass.getText());
    
        Thread.sleep(2000);
        
      driver.quit();
    	
    }
    
    @When("User enters empty username")
    public void enterEmptyUsername() throws InterruptedException {
       
        Thread.sleep(1000);
		
		driver.findElement(By.name("password")).sendKeys("Joevishal@6394");
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[@class='woocommerce-button button woocommerce-form-login__submit']")).click();

		Thread.sleep(1000);
    	
    }

    @Then("User should see a validation message")
    public void verifyValidationMessage() throws InterruptedException {
        
    	Thread.sleep(2000);
    	  
        WebElement usernameerror = driver.findElement(By.xpath("(//ul[@role='alert'])"));
        
        assertTrue(usernameerror.isDisplayed());
        
        assertEquals("Error: Username is required.", usernameerror.getText());
    
        Thread.sleep(2000);
        
        driver.quit();
    	
    }
    
    @When("User enters empty password")
    public void enterEmptyPassword() throws InterruptedException {
        
        Thread.sleep(1000);
		
		driver.findElement(By.name("username")).sendKeys("vishal@hurekatek.com");
		
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//button[@class='woocommerce-button button woocommerce-form-login__submit']")).click();

		Thread.sleep(1000);
    	
    }

    @Then("User should see a validation message1")
    public void verifyValidationMessage1() throws InterruptedException {
        
    	Thread.sleep(2000);
  	  
        WebElement passerror = driver.findElement(By.xpath("(//ul[@role='alert'])"));
        
        assertTrue(passerror.isDisplayed());
        
        assertEquals("Error: The password field is empty.", passerror.getText());
    
        Thread.sleep(2000);
        
      driver.quit();
    	
    }
    
    @When("User clicks on Forgot Password link")
    public void clickForgotPasswordLink() throws InterruptedException {
       
    	Thread.sleep(2000);
    	
    	driver.findElement(By.xpath("//a[@href='https://boyds.wp.shottqsr.com/my-account/lost-password/']")).click();
    	
    	Thread.sleep(2000);
    
    }
    
    @Then("User should be redirected to the Forgot Password page")
    public void verifyForgotPasswordPage() throws InterruptedException {
        
    	Thread.sleep(3000);
    	  
         WebElement forgotpass = driver.findElement(By.xpath("(//label[@for='user_login'])"));
        
        assertTrue(forgotpass.isDisplayed());
        
        assertEquals("Username or email *", forgotpass.getText());
    
        Thread.sleep(2000);
        
        driver.quit();
      
	}
    
}
