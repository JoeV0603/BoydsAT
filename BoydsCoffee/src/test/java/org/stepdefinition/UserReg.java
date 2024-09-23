package org.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserReg {

	WebDriver driver = new ChromeDriver();
	
	@Given("User is on registration page")
    public void user_is_on_registration_page() throws InterruptedException {
		
		driver.get("https://boyds.wp.shottqsr.com/");
        
		driver.manage().window().maximize();

		Thread.sleep(2000);
		
		driver.findElement(By.xpath("(//a[@class=\'main-login'])")).click();
			
		Thread.sleep(3000);		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='button reg_btn']")));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		
	}

	@When("User enters valid registration details")
    public void user_enters_valid_registration_details() throws InterruptedException {
        
		Thread.sleep(5000);
		
		driver.findElement(By.id("reg_username")).sendKeys("Jotestv");
		
		driver.findElement(By.id("reg_email")).sendKeys("vishal+test73511@hurekatek.com");
		
		driver.findElement(By.id("reg_password")).sendKeys("Joevishal@0603");
		
		driver.findElement(By.xpath("(//button[@class='woocommerce-Button woocommerce-button button woocommerce-form-register__submit'])")).click();
		
		Thread.sleep(3000);
    
	 }

      @Then("User registration should be successful")
      public void user_registration_should_be_successful() throws InterruptedException {
    	
    	WebElement successMessage = driver.findElement(By.xpath("(//div[@class='title'])"));
        
    	assertTrue(successMessage.isDisplayed());
        
    	assertEquals("My Account", successMessage.getText());

    	Thread.sleep(2000);
    	
        driver.quit();
   
	}

    @When("User enters invalid email")
    public void user_enters_invalid_email() throws InterruptedException {

    	Thread.sleep(5000);
    	
    	driver.findElement(By.id("reg_username")).sendKeys("Jotestvt");
    	
    	driver.findElement(By.id("reg_email")).sendKeys("vishal+joo.hurekatek.com");
		
		driver.findElement(By.id("reg_password")).sendKeys("Joevishal@0603");
		
		driver.findElement(By.xpath("(//button[@class='woocommerce-Button woocommerce-button button woocommerce-form-register__submit'])")).click();
		
    }

    @Then("User should see an email validation error")
    public void user_should_see_an_email_validation_error() throws InterruptedException {
    	
    	Thread.sleep(3000);
    	
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        String actualErrorMessage = (String) js.executeScript(
        "return document.getElementById('reg_email').validationMessage;");

        System.out.println("Actual error message: " + actualErrorMessage);

        assertTrue(actualErrorMessage.contains("include an '@' in the email address"));
        assertTrue(actualErrorMessage.contains("missing an '@'"));
    	
    	 driver.quit();
    
    }

      @When("User enters a short password")
      public void user_enters_a_short_password() throws InterruptedException {

    	  Thread.sleep(5000);
    	  
    	  driver.findElement(By.id("reg_username")).sendKeys("Jotestvt");
    	  
    	  driver.findElement(By.id("reg_email")).sendKeys("vishal+joev.hurekatek.com");
    	  
    	  driver.findElement(By.id("reg_password")).sendKeys("Joe@0603");
    	  
      }

      @Then("User should see a password validation error")
      public void user_should_see_a_password_validation_error() throws InterruptedException {

    	  Thread.sleep(3000);
    	  
    	  WebElement errorMessage = driver.findElement(By.xpath("(//small[@class='woocommerce-password-hint'])"));
          
    	  assertTrue(errorMessage.isDisplayed());
    	        
    	  assertEquals("Hint: The password should be at least twelve characters long. To make it stronger, use upper and lower case letters, numbers, and symbols like ! \" ? $ % ^ & ).", errorMessage.getText());

   	      Thread.sleep(2000);
    	    	
          driver.quit();
    	    
      }

      @When("User enters a strong password")
      public void user_enters_a_strong_password() throws InterruptedException {
    	  
          Thread.sleep(5000);
    	  
          driver.findElement(By.id("reg_username")).sendKeys("Jotestvt");
          
    	  driver.findElement(By.id("reg_email")).sendKeys("vishal+testing.hurekatek.com");
    	  
    	  driver.findElement(By.id("reg_password")).sendKeys("LCx47WGRQKhkGmr");
      
      }
      
      @Then("User should see a password validation text")
      public void user_should_see_a_password_validation_text() throws InterruptedException {

    	  Thread.sleep(3000);
    	  
    	  WebElement strongMessage = driver.findElement(By.xpath("(//div[@class='woocommerce-password-strength strong'])"));
          
    	  assertTrue(strongMessage.isDisplayed());
    	        
    	  assertEquals("Strong", strongMessage.getText());

   	      Thread.sleep(2000);
    	    	
          driver.quit();
          
      }
      
      @When("User enters an email that is already registered")
      public void user_enters_an_email_that_is_already_registered() throws InterruptedException {
       
    	  Thread.sleep(5000);
    	  
    	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	  
    	  WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("reg_username")));
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", usernameField);
          usernameField.sendKeys("Joetestingv");
          
          WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("reg_email")));
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", emailField);
          emailField.sendKeys("vishal@hurekatek.com");

          WebElement passField = wait.until(ExpectedConditions.elementToBeClickable(By.id("reg_password")));
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", passField);
          passField.sendKeys("Joevishal@0603");
    	  
          Thread.sleep(2000);
          
          WebElement registerButton = driver.findElement(By.xpath("//button[@class='woocommerce-Button woocommerce-button button woocommerce-form-register__submit']"));
          JavascriptExecutor js = (JavascriptExecutor) driver;
          js.executeScript("arguments[0].click();", registerButton);

        	  
      }

      @Then("User should see an email already registered error")
      public void user_should_see_an_email_already_registered_error() throws InterruptedException {
          
    	  Thread.sleep(3000);
    	  
          WebElement errorMessage = driver.findElement(By.xpath("(//ul[@role='alert'])"));
          
          assertTrue(errorMessage.isDisplayed());
          
          assertEquals("Error: An account is already registered with your email address. Please log in.", errorMessage.getText());
      
          Thread.sleep(2000);
          
        driver.quit();
    	  
     }
}
