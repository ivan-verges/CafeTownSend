package mobiquity.qa.engineer;

import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Utilities
{
	//Receives a Test Class, Executes the Tests and Returns It's Result
	public static <T> boolean getTestResult(Class<T> className)
	{
		Result result = JUnitCore.runClasses(className);
	    for (Failure failure : result.getFailures())
	    {
	    	System.out.println(failure.toString());
	    }
	    
	    return result.wasSuccessful();
	}
	
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
	
	public static List<WebElement> getWebElementsFromWebElementByTagName(WebElement we, String tagName)
	{
		List<WebElement> result = null;
		if(we != null)
		{
			result = we.findElements(By.tagName(tagName));
		}
		
		return result;
	}
	
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
	
	public static boolean clickOnElement(WebElement we)
	{
		boolean result = false;
		if(we != null)
		{
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