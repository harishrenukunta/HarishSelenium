package HarishMaven.SeleniumMavenTestNG;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.knockOutPages.BetterList;

public class BasicAnnotationsTest {
	
	String strUrl = "http://knockoutjs.com/examples/betterList.html";
	WebDriver driver = null;
	
  @BeforeTest(groups={"Functional", "Regression"})
  public void beforeMethod()
  {
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
  }
  
  @Test
  public void verifyUIObjects() {
	  
	  try
	  {  
		  
		  driver.navigate().to("http://www.google.com");
		  driver.get(strUrl);
		  
		  WebElement txtItem = driver.findElement(By.cssSelector("input[data-bind*='value:itemToAdd']"));
		  WebElement btnAdd = driver.findElement(By.xpath("//button[text()='Add']"));
		  WebElement btnRemove = driver.findElement(By.xpath("//button[text()='Remove']"));
		  WebElement btnSort = driver.findElement(By.xpath("//button[text()='Sort']"));
		  WebElement lstItems = driver.findElement(By.cssSelector("select[multiple='multiple'][data-bind*='selectedOptions:selectedItems']"));
		  
		  List<String> mismatches = new ArrayList<String>();
		  if(!txtItem.isDisplayed())
		  {
			  mismatches.add("Item textbox not displayed");
			  
		  }
		  
		  if(!btnAdd.isDisplayed())
		  {
			  mismatches.add("Add button not displayed");
			  
		  }
		  
		  if(!btnRemove.isDisplayed())
		  {
			  mismatches.add("Remove button not displayed");
			  
		  }
		  
		  if(!btnSort.isDisplayed())
		  {
			  mismatches.add("Sort button not displayed");
			  
		  }
		  
		  if(!lstItems.isDisplayed())
		  {
			  mismatches.add("Items listbox not displayed");
			  
		  }
		  
		  assertTrue(mismatches.isEmpty(), "Objects not displayed:" + mismatches);

	  }
	  catch(Exception ex)
	  {
		  System.out.println("Exception: " + ex.getMessage());
		  fail("Exception : " + ex.getMessage());
	  }
	  
  }
    
  @Test(enabled=false)
  public void verifyAddItem()
  {
	  try
	  {
		  driver.get("http://www.google.com");
		  driver.get(strUrl);
		  
		  WebElement txtItem = driver.findElement(By.cssSelector("input[data-bind*='value:itemToAdd']"));
		  WebElement btnAdd = driver.findElement(By.xpath("//button[text()='Add']"));
		  WebElement btnRemove = driver.findElement(By.xpath("//button[text()='Remove']"));
		  WebElement btnSort = driver.findElement(By.xpath("//button[text()='Sort']"));
		  WebElement lstItems = driver.findElement(By.cssSelector("select[multiple='multiple'][data-bind*='selectedOptions:selectedItems']"));
		  
		  String strItemToAdd = "Apple";
		  String strItem = "";
		  boolean blnItemFound = false;
		  
		  //Enter item name into text box
		  txtItem.sendKeys(strItemToAdd);
		  
		  //Click on Add button
		  btnAdd.click();
		  
		  //Verify if item got added to list box
		  Select selectAllItems = new Select(lstItems);
		  
		  for(WebElement option : selectAllItems.getOptions())
		  {
			  strItem = option.getText();
			  if(strItem.equalsIgnoreCase(strItemToAdd))
			  {
				  blnItemFound = true;
			  }
		  }
		  
		  assertTrue(blnItemFound, strItemToAdd + " item not found in list box");
		  
		  
	  }
	  catch(Exception ex)
	  {
		  System.out.println("Exception ex:" + ex.getMessage());
		  fail("Exception :" + ex.getMessage());
	  }
	  
	  
  }
  
  
  @Test(groups={"Regression", "Functional"})
  public void verifyAddItemPageObject()
  {
	  BetterList objBL = null;
	  
	  try
	  {
		  driver.get(strUrl);
		  
		  objBL = PageFactory.initElements(driver, BetterList.class);
		  
		  String strItemToAdd = "Sugar Doughnut";
		  
		  objBL.addItem(strItemToAdd);
		  
		  assertTrue(objBL.findItem(strItemToAdd), strItemToAdd + " item not found");
		  
		  
	  }
	  catch(Exception ex)
	  {
		  System.out.println("Exception :" + ex.getMessage());
		  fail("Exception :" + ex.getMessage());
	  }
  }
  
  
  @Test(groups={"Functional"})
  public void verifyRemoveItemPageObject()
  {
	  BetterList objBL = null;
	  
	  try
	  {
		  driver.get(strUrl);
		  
		  objBL = PageFactory.initElements(driver, BetterList.class);
		  
		  String strItemToAdd = "Item to be Removed";
		  
		  objBL.addItem(strItemToAdd);
		  
		  objBL.removeItem(strItemToAdd);
		  
		  assertFalse(objBL.findItem(strItemToAdd), strItemToAdd + " item found");
	  }
	  catch(Exception ex)
	  {
		  System.out.println("Exception :" + ex.getMessage());
		  fail("Exception :" + ex.getMessage());
	  }
  }
  
  @Test(enabled=false)
  public void verifyRemoveItemPageObjectNegative()
  {
	  BetterList objBL = null;
	  
	  try
	  {
		  driver.get(strUrl);
		  
		  objBL = PageFactory.initElements(driver, BetterList.class);
		  
		  String strItemToAdd = "Item to be Removed";
		  
		  objBL.addItem(strItemToAdd);
		  
		  objBL.removeItem("Item1");
		  
		  assertFalse(objBL.findItem(strItemToAdd), strItemToAdd + " item found");
		  
	  }
	  catch(Exception ex)
	  {
		  System.out.println("Exception :" + ex.getMessage());
		  fail("Exception :" + ex.getMessage());
	  }
  }
  @Parameters({"ItemToAdd"})
  @Test(enabled=false)
  public void verifyAddItemPageObjectParameterised(String strItemToAdd)
  {
	  BetterList objBL = null;
	  
	  try
	  {
		  driver.get(strUrl);
		  
		  objBL = PageFactory.initElements(driver, BetterList.class);
		  
		  //String strItemToAdd = "Sugar Doughnut";
		  
		  objBL.addItem(strItemToAdd);
		  
		  assertTrue(objBL.findItem(strItemToAdd), strItemToAdd + " item not found");
		  
		  
	  }
	  catch(Exception ex)
	  {
		  System.out.println("Exception :" + ex.getMessage());
		  fail("Exception :" + ex.getMessage());
	  }
  }
  @AfterTest(groups={"Functional", "Regression"})
  public void afterMethod()
  {
	  driver.quit();
	  driver = null;
  }


}
