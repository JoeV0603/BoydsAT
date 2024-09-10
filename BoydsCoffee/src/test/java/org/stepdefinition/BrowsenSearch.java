package org.stepdefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BrowsenSearch {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait;
	
	@Given("I am on the home page")
    public void i_am_on_the_home_page() throws InterruptedException {
       
		driver.get("https://boyds.wp.shottqsr.com/");

		Thread.sleep(1000);
		
		driver.manage().window().maximize();

		Thread.sleep(1000);
		
    }

    @When("I navigate to the product browsing section and should see a list of products")
    public void i_navigate_to_the_product_browsing_section_and_should_see_a_list_of_products() throws InterruptedException {
        
        Thread.sleep(1000);
		
		driver.findElement(By.xpath("(//a[@href='https://boyds.wp.shottqsr.com/shop/']) [1]")).click();
		
		Thread.sleep(1000);
		
		WebElement productList = driver.findElement(By.xpath("(//div[@class='woo-container'])"));

        assertTrue(productList.isDisplayed());

        Thread.sleep(1000);
        
        List<WebElement> products = productList.findElements(By.xpath(".//div[@class='product-detailss']"));

        assertFalse(products.isEmpty());

        Thread.sleep(1000);
        
        for (WebElement product : products) {
            System.out.println(product.getText());
        }
    	
    }

    @Then("I should be able to click a product and see the product details page")
    public void i_should_be_able_to_click_a_product_and_see_the_product_details_page() throws InterruptedException {
       
    	Thread.sleep(1000);
    	
    	driver.findElement(By.xpath("(//a[@href='https://boyds.wp.shottqsr.com/product/boyds-breaktime-12-oz-ground-coffee/'])")).click();
        
        Thread.sleep(1000);

        WebElement productDetails = driver.findElement(By.xpath("(//div[@id='product-2755'])"));

        String actualText = productDetails.getText();
        System.out.println("Actual Product Details: " + actualText);

        String expectedText = "Breaktime- 12oz. Ground Coffee";
        assertTrue("Product details do not contain the expected text", 
            actualText.contains(expectedText));
        
    	Thread.sleep(1000);
        
        driver.quit();
    
    }
    
    @When("I navigate to the product browsing section and search for Ground Coffee")
    public void I_navigate_to_the_product_browsing_section_and_search_for_Ground_Coffee() throws InterruptedException {
       
        Thread.sleep(1000);
		
		driver.findElement(By.xpath("(//a[@href='https://boyds.wp.shottqsr.com/shop/']) [1]")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("product-search")).sendKeys("Ground Coffee");
    	
		Thread.sleep(5000);
		
    }

    @Then("I should see products related to Ground Coffee")
    public void i_should_see_products_related_to_Ground_Coffee() throws InterruptedException {
     
    	Thread.sleep(2000);
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	
	    WebElement searchResults = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='products columns-3']")));
        assertTrue("Search results are not displayed", searchResults.isDisplayed());

        List<WebElement> products = searchResults.findElements(By.xpath(".//div[@class='product-detailss']"));

        boolean found = false;
    
        for (WebElement product : products) {
            
        	String productName = product.findElement(By.xpath(".//h2")).getText();
            
        if (productName.toLowerCase().contains("ground coffee"))
           {
            found = true;
            break;
           }
        }
    
        assertTrue("No product related to Ground Coffee found", found);

        Thread.sleep(1000);
        
    	driver.quit();
    	
   }

    @When("I navigate to the product browsing section and search for Juice")
    public void I_navigate_to_the_product_browsing_section_and_search_for_Juice() throws InterruptedException {
        
        Thread.sleep(1000);
		
		driver.findElement(By.xpath("(//a[@href='https://boyds.wp.shottqsr.com/shop/']) [1]")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("product-search")).sendKeys("Juice");
    	
		Thread.sleep(5000);
    	
    }
    
    @Then("I should see a message indicating no results found")
    public void i_should_see_a_message_indicating_no_results_found() throws InterruptedException {
        
    	Thread.sleep(2000);
    	
        WebElement noResults = driver.findElement(By.xpath("//ul[@class='products columns-3']"));
        
    	assertTrue(noResults.isDisplayed());
        
    	assertEquals("No products found", noResults.getText());

    	Thread.sleep(2000);
    	
    	driver.quit();
    	
    }

    @Given("I am on the product browsing page")
    public void i_am_on_the_product_browsing_page() throws InterruptedException {
        
    	driver.get("https://boyds.wp.shottqsr.com/");

		driver.manage().window().maximize();

		Thread.sleep(1000);
		
        driver.findElement(By.xpath("(//a[@href='https://boyds.wp.shottqsr.com/shop/']) [1]")).click();
		
		Thread.sleep(1000);
    }

    @When("I sort products by price")
    public void i_sort_products_by_price() throws InterruptedException {
        
    	Thread.sleep(1000);
    	
    	driver.findElement(By.xpath("//select[@class='order_by']")).click();
    	
    	Thread.sleep(1000);
    	
    	driver.findElement(By.xpath("//option[@value='price']")).click();
    	
    	Thread.sleep(5000);
    	
    }

    @Then("the products should be sorted by price in ascending order")
    public void the_products_should_be_sorted_by_price_in_ascending_order() throws InterruptedException {

        List<WebElement> priceElements = driver.findElements(By.xpath("//span[@class='woocommerce-Price-amount amount']"));
        
        List<Double> prices = new ArrayList<Double>();
        
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "").trim(); 
        	double price = Double.parseDouble(priceText);
        	prices.add(price);
        
        }
        
        boolean sorted = isSortedAscending(prices);
        assertTrue("Products are not sorted by price in ascending order", sorted);
       
        Thread.sleep(3000);
        
        driver.quit();
        
        }

        private boolean isSortedAscending(List<Double> list) {
        	if (list.isEmpty()) {
        	return true;
        }
        
        for (int i = 0; i < list.size() - 1; i++) {
        	if (list.get(i) > list.get(i + 1)) {
            return false;
            }
        }
        return true;
    	
        }
            
    @When("I apply a filter for roast level")
    public void i_apply_a_filter_for_roast_level() throws InterruptedException {
        
    	Thread.sleep(3000);
    	
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	
        WebElement filterElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='light']")));
    	
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", filterElement);
    	    
        filterElement.click();
  
    	Thread.sleep(5000);
    	
    }

    @Then("I should see only products from the selected roast level")
    public void i_should_see_only_products_from_the_selected_brand_roast_level() throws InterruptedException {
        
    	 Thread.sleep(1000);
    	
    	 List<WebElement> productList = driver.findElements(By.cssSelector("li.product"));

         for (WebElement product : productList) {
             WebElement productTitleElement = product.findElement(By.cssSelector(".woocommerce-loop-product__title"));
             String productTitle = productTitleElement.getText();

             WebElement priceElement = product.findElement(By.cssSelector(".price .woocommerce-Price-amount"));
             String price = priceElement.getText();

             WebElement productDetailsElement = product.findElement(By.cssSelector(".product-detailss"));
             String productDetails = productDetailsElement.getText();

             if (productDetails.toLowerCase().contains("light roast")) {
                 System.out.println("Found product with Light Roast: " + productTitle + ", Price: " + price);
             
             }
             
         }
    	
         Thread.sleep(1000);
    	 
         driver.quit();
    	
    }
		
}
