package com.knockOutPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCart {
	
	WebDriver driver = null;
	
	@FindBy(css="button[data-bind='click: addLine']")
	WebElement addProduct;
	
	@FindBy(css="button[data-bind='click: save']")
	WebElement submitCart;
	
	@FindBy(css="div.liveExample>table")
	WebElement cartTable;
	
	public ShoppingCart(WebDriver dr)
	{
		driver = dr;
	}
	
	public void addProduct(String strCategory, String strProduct)
	{
		List<WebElement> cartTableRows = cartTable.findElements(By.cssSelector("tbody tr"));
		
		int intTotalRows = cartTableRows.size();
		
		if(!(intTotalRows == 1 && getCategoryFromShoppingCart(1).toLowerCase().contains("select")))
		{
			addProduct.click();
		}
		selectCategory(strCategory);
		selectProduct(strProduct);
		
		System.out.println("Total rows : " + cartTableRows.size());
		
		
		
	}
	
	public String getCategoryFromShoppingCart(int rowNo)
	{
		WebElement drpCategory = cartTable.findElement(By.cssSelector("tbody tr:nth-child(" + rowNo + ") td:first-child select") );
		
		Select selectCategory = new Select(drpCategory);
		
		return selectCategory.getFirstSelectedOption().getText();
	}
	public void selectCategory(String strCategory)
	{
		WebElement drpCategory = cartTable.findElement(By.cssSelector("tbody tr:last-child td:first-child select"));
		
		Select selectCategory = new Select(drpCategory);
		
		selectCategory.selectByVisibleText(strCategory);
		
	}
	
	public void selectProduct(String strProduct)
	{
		WebElement drpProduct = cartTable.findElement(By.cssSelector("tbody tr:last-child td:nth-child(2) select"));
		
		Select selectProduct = new Select(drpProduct);
		
		selectProduct.selectByVisibleText(strProduct);
		
	}

}
