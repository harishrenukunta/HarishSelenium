package HarishMaven.SeleniumMavenTestNG;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DPInAnotherClass {
	
  @DataProvider(name="GetItemsToBuy")
  public static Object[][] getItemsToBuy() throws Exception {
	  
	  InputStream inp = null;
	  List<Map<String, String>> shoppingCartDPData = new ArrayList<Map<String, String>>();
	  HSSFWorkbook wb = null;
	  HSSFSheet sh = null;
	  
	  try
	  {
		  List<String> header = new ArrayList<String>();
		  inp = DPInAnotherClass.class.getClassLoader().getResourceAsStream("ShoppingCartList.xls");//new FileInputStream("D:\\Documents and Settings\\harish\\git\\SeleniumNikhil\\NikhilSeleniumMavenTestNG\\src\\test\\resources\\ShoppingCartList.xls");
		  
		  //Need to find out how to load .xls file at run time when test pack executed using maven
		  //inp = DPInAnotherClass.class.getClassLoader().getResourceAsStream("/ShoppingCartList.xls");
		  
		  wb = new HSSFWorkbook(inp);
		  sh = wb.getSheetAt(0);
		  
		  HSSFRow row = sh.getRow(0);
		  
		  int intCounter = 0;
		  
		  while(row.getCell(intCounter) != null)
		  {
			  header.add(row.getCell(intCounter).getStringCellValue());
			  intCounter++;
		  }
		  
		  String[] shoppingCartDataItems = null;
		  shoppingCartDataItems = header.toArray(new String[0]);
		  
		  System.out.println("Shopping cart data items:" + shoppingCartDataItems);
		  
		 int totalRows = sh.getPhysicalNumberOfRows();
		 for(int rowNo=1;rowNo < totalRows; rowNo++)
		 {
			 Map<String, String> shoppingCartRow = new HashMap<String, String>();
			 
			 row = sh.getRow(rowNo);
			 
			 intCounter = 1;
			 
			 while(row.getCell(intCounter) != null)
			 {
				 String strCellValue = "";
				 strCellValue = row.getCell(intCounter).getStringCellValue();
				 shoppingCartRow.put(shoppingCartDataItems[intCounter], strCellValue );
				 intCounter++;
			 }
			 
			 shoppingCartDPData.add(shoppingCartRow);
		 }
		 
		 Object[][] ObjectsToReturn = new Object[(shoppingCartDPData.toArray()).length][1];
		 intCounter = 0;
		 for(Map<String, String>rowData : shoppingCartDPData)
		 {
			 ObjectsToReturn[intCounter][0] = rowData;
			 intCounter++;
			 
			 
		 }
		 
		  return ObjectsToReturn;
	  }
	  catch(Exception ex)
	  {
		  throw new Error(ex.getMessage());
	  }
	  finally
	  {
		  inp.close();
	  }
  }
}
