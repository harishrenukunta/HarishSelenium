package HarishMaven.SeleniumMavenTestNG;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import com.knockOutPages.ShoppingCart;

public class DataProviderTest {
	
  String strUrl = "http://knockoutjs.com/examples/cartEditor.html";
  WebDriver driver = null;
  ShoppingCart sc = null;
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(20,  TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  driver.get(strUrl);
	  
	  sc = PageFactory.initElements(driver, ShoppingCart.class);
  }
  
  @DataProvider(name="cartDataProvider")
  public Object[][]createCartData()
  {
	  return new Object[][]{
			  {"Motorcycles","1974 Ducati 350 Mk3 Desmo" },
			  {"Classic Cars", "1961 Chevrolet Impala"}
			  //{"Classic Cars","1968 Ford Mustang"}
	  };
  }
  
  @Test(dataProvider="cartDataProvider")
  public void verifyAddProduct(String Category, String Product)
  {
	  try
	  {
		  //sc.addProduct("Motorcycles","1974 Ducati 350 Mk3 Desmo");
		  sc.addProduct(Category, Product);
		  
		  Thread.sleep(4000);
		  
		  assertTrue(true);
	  }
	  catch(Exception ex)
	  {
		  System.out.println("Exception:" + ex.getMessage());
		  fail("Row failed withe exception:" + ex.getMessage());
	  }
	  
	  
  }
  
  @Test(dataProvider="GetItemsToBuy", dataProviderClass=DPInAnotherClass.class)
  public void verifyAddProductDPInAnotherClass(Map<String, String>shoppingCartRow)
  {
	  try
	  {
		  //sc.addProduct("Motorcycles","1974 Ducati 350 Mk3 Desmo");
		  sc.addProduct(shoppingCartRow.get("Category"),shoppingCartRow.get("Product"));
		  
		  Thread.sleep(4000);
		  
		  assertTrue(true);
	  }
	  catch(Exception ex)
	  {
		  System.out.println("Exception:" + ex.getMessage());
		  fail("Row failed withe exception:" + ex.getMessage());
	  }
	  
	
  }
  
  
  
  @AfterTest
  public void afterTest()
  {
	  driver.quit();
	  driver = null;
  }
}
