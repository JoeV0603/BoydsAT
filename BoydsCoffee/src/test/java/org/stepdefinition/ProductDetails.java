package org.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ProductDetails {

	WebDriver driver = new ChromeDriver();
	
	@Given("the user is on the product details page")
    public void the_user_is_on_the_product_details_page() throws InterruptedException {

		driver.get("https://boyds.wp.shottqsr.com/");
		
		driver.manage().window().maximize();

        driver.findElement(By.xpath("(//a[@href='https://boyds.wp.shottqsr.com/shop/']) [1]")).click();
		
		Thread.sleep(1000);
		
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://boyds.wp.shottqsr.com/product/boyds-breaktime-12-oz-ground-coffee/']")));
		element.click();

		Thread.sleep(2000);
			
    }


    @Then("the product description should be accurate")
    public void the_product_description_should_be_accurate() throws InterruptedException {

    	Thread.sleep(1000);

        WebElement prodDescription = driver.findElement(By.xpath("//div[@class='woocommerce-product-details__full-description']"));

        String actualText = prodDescription.getText();
        System.out.println("Actual Product Description: " + actualText);

        String expectedText = "Boyd's Breaktime Coffee - Strong and Full-Bodied";
        assertTrue("Product description do not contain the expected text", 
            actualText.contains(expectedText));
        
    	Thread.sleep(1000);
    	
    	driver.quit();
    	
    }

    @Then("the product availability status should be displayed")
    public void the_product_availability_status_should_be_displayed() throws InterruptedException {
        
        Thread.sleep(1000);
    	
        WebElement availStatus = driver.findElement(By.xpath("//button[@class='single_add_to_cart_button button alt']"));
        
    	assertTrue(availStatus.isDisplayed());
        
    	assertEquals("Add To Cart", availStatus.getText());

    	Thread.sleep(1000);
    	
    	driver.quit();
    	
    }

    @Then("the related products should be displayed")
    public void the_related_products_should_be_displayed() throws InterruptedException {
        
    	Thread.sleep(1000);

        WebElement relatedProds = driver.findElement(By.xpath("//section[@class='related products']"));

        String actualText = relatedProds.getText();
        System.out.println("Actual Related Products: " + actualText);

        String expectedText1 = "Related Products";
        assertTrue("This section do not contain the expected result",
            actualText.contains(expectedText1));
        
    	Thread.sleep(1000);
    	
    	WebElement productList = driver.findElement(By.xpath("//ul[@class='products columns-4']"));

        assertTrue(productList.isDisplayed());

        Thread.sleep(1000);
        
        List<WebElement> products = productList.findElements(By.xpath(".//li"));

        assertFalse(products.isEmpty());

        Thread.sleep(1000);
        
        for (WebElement product : products) {
            System.out.println(product.getText());
        }
    	
    	driver.quit();
    	
    }

    @Then("the product price should be displayed")
    public void the_product_price_should_be_displayed() throws InterruptedException {
        
    	WebElement price = driver.findElement(By.xpath("//p[@class='price']"));

        String actualText = price.getText();
        System.out.println("Actual Price: " + actualText);

        String expectedText = "$10.99";
        assertTrue("Product does not contain the expected price", 
            actualText.contains(expectedText));
        
    	Thread.sleep(1000);
    	
    	driver.quit();
    	
    }

    @Then("multiple product images should be displayed")
    public void multiple_product_images_should_be_displayed() throws InterruptedException {
        
    	WebElement prodImages = driver.findElement(By.xpath("//div[@class='woocommerce-product-gallery woocommerce-product-gallery--with-images woocommerce-product-gallery--columns-4 images']"));

        assertTrue(prodImages.isDisplayed());

        Thread.sleep(1000);
        
        List<WebElement> images = prodImages.findElements(By.xpath(".//img"));

        assertFalse(images.isEmpty());

        Thread.sleep(1000);
        
        for (WebElement product : images) {
            System.out.println(product.getText());
        }
    	
    	driver.quit();
    	
    }

    @Then("the product SKU should be displayed")
    public void the_product_SKU_should_be_displayed() throws InterruptedException {
        
    	WebElement prodSKU = driver.findElement(By.xpath("//p[@class='product-sku']"));

        String actualText = prodSKU.getText();
        System.out.println("Actual Product SKU: " + actualText);

        String expectedText = "SKU:";
        assertTrue("Product details do not contain the expected SKU", 
            actualText.contains(expectedText));
        
    	Thread.sleep(1000);
        
    	driver.quit();
    	
    }

}
