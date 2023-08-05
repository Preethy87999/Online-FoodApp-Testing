package com.simplilearn.OnlineFoodService;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinitions {
	ChromeDriver driver;
	@Before
	public  void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		 driver.manage().deleteAllCookies();
	     driver.get("https://www.pizzahut.co.in/");
	     driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		System.out.println("Lanuch of Page");
	}


	@Given("user land on home page")
	public void landPage() {
		
		driver.get("https://www.pizzahut.co.in/");
	}

	@When("user search the locations for deliver")
	public void searchLocations() throws InterruptedException {
		driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Vellore Institute of Technology "
				+ "- VIT Chennai, Kelambakkam - "
				+ "Vandalur Road, Rajan Nagar, Chennai, Tamil Nadu, India");
	     Thread.sleep(2000);
	     driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[1]/div[2]")).click();
	     Thread.sleep(2000);
	     driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[4]/div/div/div/div[2]/form/div/div[2]/div/button")).click();
	    Thread.sleep(3000);
	     driver.findElement(By.cssSelector(".button.button--primary")).click();
	     Thread.sleep(2000);
	     
	     String expectedUrl = "https://www.pizzahut.co.in/order/deals/";
	     String actualUrl = driver.getCurrentUrl();
	     Assert.assertEquals(actualUrl,expectedUrl);
	}
	
	@Then("I Discount add")
	public void addDiscount() {
		 driver.findElement(By.xpath("(//div[contains(@class,'list-item__image')])[2]")).click();
	     try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	     driver.findElement(By.cssSelector(".button.button--green.mb-10")).click();
	}

	@Then("I search for Products and add in cart")
	public void searchProduct() {
		 List<WebElement> listitem = driver.findElements(By.cssSelector(".typography-4.list-item__name.flex-1.px-10.pt-10"));

	     for(int i=0;i<listitem.size();i++)
	     {
	     String product = listitem.get(i).getText();
	     if(product.contains("Awesome American Cheesy"))
         {
	     driver.findElements(By.cssSelector(".button.button--md.button--green.flex-1.font-semibold")).get(i).click();
	     break;
	     }
	     } 
	     driver.findElement(By.linkText("Sides")).click();
	     String productName1 = "Spicy Baked Chicken Wings";
	     List<WebElement> listitem1 = driver.findElements(By.cssSelector(".typography-4.list-item__name.flex-1.px-10.pt-10"));

	     for(int i=0;i<listitem1.size();i++)
	     {
	     String product = listitem1.get(i).getText();
	     if(product.contains("Spicy Baked Chicken Wings"))
	     {
	     driver.findElements(By.cssSelector(".button.button--md.button--green.flex-1.font-semibold")).get(i).click();
	     break;
	     }
	     } 

	     driver.findElement(By.linkText("Drinks")).click();
	     
	     String productName3 = "Mirinda";
	     List<WebElement> listitem3 = driver.findElements(By.cssSelector(".typography-4.list-item__name.flex-1.px-10.pt-10"));

	     for(int i=0;i<listitem3.size();i++)
	     {
	     String product = listitem3.get(i).getText();
	     if(product.contains("Mirinda"))
	     {
	     driver.findElements(By.cssSelector(".button.button--md.button--green.flex-1.font-semibold")).get(i).click();
	     break;
	     }
	     } 
	     
	}

	@Then("user move to cart Page for further details")
	public void cartPage() {
		 driver.findElement(By.className("basket-checkout")).click();
	     try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	     String expectedUrl2 = "https://www.pizzahut.co.in/order/checkout/";
	     String actualUrl2 = driver.getCurrentUrl();
	     Assert.assertEquals(actualUrl2,expectedUrl2);
	    
	    driver.findElement(By.id("checkout__name")).sendKeys("Priya");
	    driver.findElement(By.id("checkout__phone")).sendKeys("9888458864");
	    driver.findElement(By.id("checkout__email")).sendKeys("priya@gmail.com");
	    driver.findElement(By.cssSelector(".sc-fzqMdD.eohbfP")).click();
	    driver.findElement(By.xpath("//button[normalize-space()='Cancel']")).click();
	}

	@Then("verify the given resources")
	public void verifyCartPage() {
		String expectedUrl2 = "https://www.pizzahut.co.in/order/deals?showGiftCardModalV2=true";
	     String actualUrl2 = driver.getCurrentUrl();
	     Assert.assertEquals(actualUrl2,expectedUrl2);
	}
	

@After
public void closeBrowser() {
	driver.close();
}
}
