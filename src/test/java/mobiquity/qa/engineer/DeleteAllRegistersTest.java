package mobiquity.qa.engineer;

import static org.junit.Assert.assertTrue;

import org.junit.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mobiquity.qa.engineer.utilities.Utilities;
import mobiquity.qa.engineer.utilities.WebDriverFactory;
import mobiquity.qa.engineer.utilities.Constants;

public class DeleteAllRegistersTest
{
	//Web Driver For Browser Management
	private WebDriver driver;
	
	@Before
	public void setUp()
	{
		//Gets a Mozilla Firefox Browser to Test Passed URL
		driver = WebDriverFactory.getSingleFirefoxDriver(Constants.getWebURL());
	}
	
	@Test
	public void testExecution()
	{
		//Gets Web Elements From Main Window
		WebElement deleteButton = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.DeleteButton.getXpath());
		assertTrue("Delete Button Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(deleteButton));
		
		//Get All Elements And Deletes Them
		WebElement employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
		assertTrue("Employee List Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(employeeList));
		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
		{
			//Clicks on the Create Button to Delete
			Utilities.clickOnElement(driver, we);
			
			//Clicks on the Create Button to Delete The Register
			Utilities.clickOnElement(driver, deleteButton);
			
			Alert alert = driver.switchTo().alert();
	        alert.accept();
	        
	        Utilities.waitForSeconds(3);
		}
		
        //Verifies if Data Exsits After Delete
  		employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
  		WebElement toDelete = null;
  		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
  		{
			toDelete = we;
  		}
  		
  		assertTrue("Deleted Element Was Found", toDelete == null);
	}
	
	@After
	public void tearDown()
	{
		//driver.close();
	}
}