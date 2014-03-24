package com.knockOutPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BetterList {

	WebDriver driver = null;
	
	@FindBy(css="input[data-bind*='value:itemToAdd']")
	WebElement txtItem ;
	
	@FindBy(xpath="//button[text()='Add']")
	WebElement btnAdd;
	
	@FindBy(xpath="//button[text()='Remove']")
	WebElement btnRemove;
	
	@FindBy(xpath="//button[text()='Sort']")
	WebElement btnSort;
	
	@FindBy(css="select[multiple='multiple'][data-bind*='selectedOptions:selectedItems']")
	WebElement lstItems;
	
	Select selectAllItems = null;
	
	
	public BetterList(WebDriver dr)
	{
		driver = dr;
	}
	
	public void addItem(String strItemToAdd)
	{
		txtItem.sendKeys(strItemToAdd);
		btnAdd.click();
	}
	
	public boolean findItem(String strItemToFind)
	{
		boolean blnItemFound = false;
		String strItem;
		
		if(selectAllItems == null)
		{
			selectAllItems = new Select(lstItems);
		}
		  
		for(WebElement option : selectAllItems.getOptions())
		{
			  strItem = option.getText();
			  if(strItem.equalsIgnoreCase(strItemToFind))
			  {
				  blnItemFound = true;
			  }
		}
		
		return blnItemFound;
	}
	
	public void removeItem(String strItemToBeRemoved)
	{
		if(selectAllItems == null)
		{
			selectAllItems = new Select(lstItems);
		}
		
		if(findItem(strItemToBeRemoved))
		{
			selectAllItems.selectByVisibleText(strItemToBeRemoved);
			btnRemove.click();
		}
		else
		{
			throw new Error("Item " + strItemToBeRemoved + " doesn't exist and so cannot remove");
		}
	}
}
