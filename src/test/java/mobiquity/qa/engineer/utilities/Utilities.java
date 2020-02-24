package mobiquity.qa.engineer.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utilities
{
	/*
	 * Receives a Test Class, Executes the Tests and Returns It's Result
	 * @Return Boolean
	*/
	public static <T> boolean getTestResult(Class<T> className)
	{
		Result result = JUnitCore.runClasses(className);
	    for (Failure failure : result.getFailures())
	    {
	    	System.out.println(failure.toString());
	    }
	    
	    return result.wasSuccessful();
	}
	
	/*
	 * Return a String With The Resource Path
	 * @Return String
	*/
	public static String getResourcePath(String resourceName)
	{
		String result = null;
		
		if(resourceName != null && !resourceName.isEmpty())
		{
			result = Constants.class.getClassLoader().getResource(resourceName).getPath();
		}
		
		return result;
	}
	
	/*
	 * Return a String With The File Name Based On The Operating System To Select The Right Web Driver
	 * @Return String
	*/
	public static String getWebDriverFileName()
	{
		String fileName = "";
		String OS = System.getProperty("os.name").toLowerCase();
		
		if(OS.indexOf("win") >= 0)
		{
			fileName = "windows.exe";
		}
		else if(OS.indexOf("mac") >= 0)
		{
			fileName = "mac";
		}
		else if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") >= 0)
		{
			fileName = "linux";
		}
		
		return fileName;
	}
	
	/*
	 * Return a String[] List All Registers Loaded From The CSV File
	 * @Return ArrayList<String[]>
	*/
	public static ArrayList<String[]> getTestDataFromCSV(String csvPath, String separator)
	{
		ArrayList<String[]> result = new ArrayList<String[]>();
		try
		{
			BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
			String line;
			int i = 0;
			while((line = csvReader.readLine()) != null)
			{
				if(i == 0)
				{
					i++;
					continue;
				}
				
			    result.add(line.split(separator));
			}
			csvReader.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error Reading Data From CSV File: " + csvPath);
		}
		
		return result;
	}
	
	/*
	 * Return A Web Element From The XPath
	 * @Return WebElement*/
	public static WebElement getWebElementFromXpath(WebDriver driver, String xpath)
	{
		WebElement result = null;
		
		if(driver != null && xpath != null && !xpath.isEmpty())
		{
			try
			{
				result = driver.findElement(By.xpath(xpath));
			}
			catch(Exception ex)
			{
				result = null;
			}
		}
		
		return result;
	}
	
	/*
	 * Return A WebElement From The ID
	 * @Return WebElement*/
	public static WebElement getWebElementFromId(WebDriver driver, String elementId)
	{
		WebElement result = null;
		
		if(driver != null && elementId != null && !elementId.isEmpty())
		{
			try
			{
				result = driver.findElement(By.id(elementId));
			}
			catch(Exception ex)
			{
				result = null;
			}
		}
		
		return result;
	}
	
	/*
	 * Returns A List<WebElement> From A Tag Name
	 * @Return List<WebElement>*/
	public static List<WebElement> getWebElementsFromWebElementByTagName(WebElement we, String tagName)
	{
		List<WebElement> result = null;
		if(we != null)
		{
			result = we.findElements(By.tagName(tagName));
		}
		
		return result;
	}
	
	/*
	 * Waits For The Supplied Seconds*/
	public static void waitForSeconds(int seconds)
	{
		try
		{
			Thread.sleep(seconds * 1000);
		}
		catch(Exception ex)
		{}
	}
	
	public static boolean isWebElementEnabled(WebElement we)
	{
		boolean result = false;
		if(we != null)
		{
			result = we.isEnabled();
		}
		
		return result;
	}
	
	public static boolean isWebElementDisplayed(WebElement we)
	{
		boolean result = false;
		if(we != null)
		{
			result = we.isDisplayed();
		}
		
		return result;
	}
	
	public static boolean isWebElementSelected(WebElement we)
	{
		boolean result = false;
		if(we != null)
		{
			result = we.isSelected();
		}

		return result;
	}
	
	public static boolean isWebElementDisplayedAndEnabled(WebElement we)
	{
		if(we != null)
		{
			return isWebElementDisplayed(we) && isWebElementEnabled(we);
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isWebElementEnabled(WebDriver driver, String xpath)
	{
		WebElement we = getWebElementFromXpath(driver, xpath);
		
		return isWebElementEnabled(we);
	}
	
	public static boolean isWebElementDisplayed(WebDriver driver, String xpath)
	{
		WebElement we = getWebElementFromXpath(driver, xpath);
		
		return isWebElementDisplayed(we);
	}
	
	public static boolean isWebElementSelected(WebDriver driver, String xpath)
	{
		WebElement we = getWebElementFromXpath(driver, xpath);
		
		return isWebElementSelected(we);
	}
	
	public static void highLightElement(WebDriver driver, WebElement we)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", we);
	}
	 
	public static boolean clickOnElement(WebDriver driver, WebElement we)
	{
		boolean result = false;
		if(we != null)
		{
			highLightElement(driver, we);
			waitForSeconds(1);
			we.click();
			waitForSeconds(2);
			result = true;
		}
		
		return result;
	}
	
	public static boolean setTextOnElement(WebElement we, String text)
	{
		boolean result = false;
		if(we != null)
		{
			we.clear();
			we.sendKeys(text);
			result = true;
		}
		
		return result;
	}
	
	public static String getTextFromElement(WebElement we)
	{
		String result = null;
		if(we != null)
		{
			result = we.getText();
		}
		
		return result;
	}
}