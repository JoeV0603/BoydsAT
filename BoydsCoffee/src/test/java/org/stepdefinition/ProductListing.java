package org.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductListing {

	WebDriver driver = new ChromeDriver();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	 @Given("I navigate to the product listing page")
	    public void iNavigateToTheProductListingPage() throws InterruptedException {
	        
		    driver.get("https://boyds.wp.shottqsr.com/");
		    
		    Thread.sleep(1000);
			
			driver.manage().window().maximize();

			Thread.sleep(1000);
			
			driver.findElement(By.xpath("(//a[@href='https://boyds.wp.shottqsr.com/shop/'])")).click();
			
			Thread.sleep(1000);
	        
	    }

	    @Then("the product listing page should load successfully")
	    public void theProductListingPageShouldLoadSuccessfully() throws InterruptedException {
	       
	    	WebElement productList = driver.findElement(By.xpath("(//div[@class='woo-container'])"));

	        assertTrue(productList.isDisplayed());

	        Thread.sleep(1000);
	        
	        List<WebElement> products = productList.findElements(By.xpath(".//div[@class='product-detailss']"));

	        assertFalse(products.isEmpty());

	        Thread.sleep(1000);
	        
	        for (WebElement product : products) {
	            System.out.println(product.getText());
	        }
	    	
	        WebElement categories = driver.findElement(By.xpath("//div[@class='filters_sec']"));
	        
	        assertTrue(categories.isDisplayed());
	        
	        String expectedText = "Categories";
	        String actualText = categories.getText();
	        assertTrue("Expected text not found in the empty cart message", actualText.contains(expectedText));
	    
	        Thread.sleep(1000);
	        
            WebElement certifications = driver.findElement(By.xpath("//div[@class='filters_sec']"));
	        
	        assertTrue(certifications.isDisplayed());
	        
	        String expectedText1 = "Certifications";
	        String actualText1 = certifications.getText();
	        assertTrue("Expected text not found in the empty cart message", actualText1.contains(expectedText1));
	    
	        Thread.sleep(1000);
	        
            WebElement roastLevels = driver.findElement(By.xpath("//div[@class='filters_sec']"));
	        
	        assertTrue(roastLevels.isDisplayed());
	        
	        String expectedText2 = "Roast Levels";
	        String actualText2 = roastLevels.getText();
	        assertTrue("Expected text not found in the empty cart message", actualText2.contains(expectedText2));
	    
	        Thread.sleep(1000);
	        
	        WebElement sort = driver.findElement(By.xpath("//span[@class='sort-by-tx']"));
	        
	    	assertTrue(sort.isDisplayed());
	        
	    	assertEquals("Sort by", sort.getText());

	    	Thread.sleep(2000);
	        
	        driver.quit();
	        
	    }
	
	    @When("I view the product details")
	    public void iViewTheProductDetails() throws InterruptedException {
	        
	    	driver.findElement(By.xpath("//a[@href='https://boyds.wp.shottqsr.com/product/boyds-breaktime-12-oz-ground-coffee/']")).click();
	        
	        Thread.sleep(1000);
	    	
	    }

	    @Then("the product details should be displayed correctly")
	    public void theProductDetailsShouldBeDisplayedCorrectly() throws InterruptedException {
	        
	    	WebElement productDetails = driver.findElement(By.xpath("//div[@id='product-2755']"));

	        String actualText = productDetails.getText();
	        System.out.println("Actual Product Details: " + actualText);

	        String expectedText = "Breaktime- 12oz. Ground Coffee";
	        assertTrue("Product details do not contain the expected text", 
	            actualText.contains(expectedText));
	        
	    	Thread.sleep(1000);
	        
	    	try {

	            WebElement productImage = driver.findElement(By.xpath("//img[@src='https://farmerbrother-wp-bucket.s3.amazonaws.com/wp-content/uploads/sites/2/2024/07/17131829/BoydsCoffee_Breaktime-front-scaled.jpg']"));
	            assert (productImage.isDisplayed()) : "Product image is not displayed";
	        } catch (NoSuchElementException e) {
	            System.out.println("Element not found: " + e.getMessage());
	            assert false : "Product details verification failed";
	        } finally {
	        
	        driver.quit();
	        
	        }
	    }
	    
	    @When("I navigate to the next page")
	    public void iNavigateToTheNextPage() {
	        
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	      
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    @Then("the next set of products should be displayed")
	    public void theNextSetOfProductsShouldBeDisplayed() throws InterruptedException {
	        
	    	WebElement productList = driver.findElement(By.xpath("(//div[@class='woo-container'])"));
	    	
	    	List<WebElement> moreproducts = productList.findElements(By.xpath(".//div[@class='product-detailss']"));

		    assertFalse(moreproducts.isEmpty());

		    Thread.sleep(1000);
		        
		    for (WebElement product : moreproducts) {
		       System.out.println(product.getText());
		        }
	    	
	    	driver.quit();
	    	
	    }
   
	    @Then("each product should have an image and a link to the product details")
	    public void eachProductShouldHaveAnImageAndALinkToTheProductDetails() throws InterruptedException {
	        
	    	List<WebElement> products = driver.findElements(By.xpath("//ul[@class='products columns-3']"));

	        for (WebElement product : products) {
	            
	            WebElement productImage = product.findElement(By.xpath(".//img"));
	            assert (productImage.isDisplayed()) : "Product image is not displayed for one of the products";

	            Thread.sleep(1000);
	            
	            WebElement productLink = product.findElement(By.xpath(".//a"));
	            assert (productLink.isDisplayed()) : "Product link is not displayed for one of the products";
	            
	            Thread.sleep(1000);
	            
	            WebElement productDetails = product.findElement(By.xpath(".//div[@class='product-detailss']"));
	            assert (productDetails.isDisplayed()) : "Product Details is not displayed for one of the products";
	            
	        }

	        Thread.sleep(1000);
	        
	        driver.quit();
	    }

}
