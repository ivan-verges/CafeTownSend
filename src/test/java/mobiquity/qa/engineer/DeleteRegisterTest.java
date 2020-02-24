package mobiquity.qa.engineer;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import mobiquity.qa.engineer.utilities.Utilities;
import mobiquity.qa.engineer.utilities.WebDriverFactory;
import mobiquity.qa.engineer.utilities.Constants;

@RunWith(Parameterized.class)
public class DeleteRegisterTest
{
	//Web Driver For Browser Management
	private WebDriver driver;
	
	//Parameters For Test Execution
	private String FirstName;
	private String LastName;
	private boolean CloseBrowser;
	
	@Parameters
	public static Collection<Object[]> data()
	{
		//Distincts Scenaries To Test The Login Function
		ArrayList<Object[]> scenaries = new ArrayList<Object[]>();
		
		//Read Escenaries From a CSV File
		for(String[] data : Utilities.getTestDataFromCSV(Constants.getUpdateTestDataPath(), ","))
		{
			scenaries.add(new Object[] {data[0], data[1], data[6].equalsIgnoreCase("true")});
		}
		
		return scenaries;
	}
	
	/*
	 * @Param FirstName = First Name to Update Record
	 * @Param LastName = Last Name to Update Record
	 * @Param CloseBrowser = Close Browser After Register
	 * */
	public DeleteRegisterTest(String FirstName, String LastName, boolean CloseBrowser)
	{
		super();
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.CloseBrowser = CloseBrowser;
	}
	
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
		
		//Verifies if Data Exsits Before Insert. If Found
		WebElement employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
		assertTrue("Employee List Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(employeeList));
		WebElement toDelete = null;
		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
		{
			if(we.getText().equalsIgnoreCase(FirstName + " " + LastName))
			{
				System.out.println("There Is A Register Found That Match With Supplied First and Last Name");
				toDelete = we;
				break;
			}
		}
		
		assertTrue("Element To Delete Not Found", toDelete != null);
		
		//Clicks on the Create Button to Delete
		Utilities.clickOnElement(driver, toDelete);
		
		//Clicks on the Create Button to Delete The Register
		Utilities.clickOnElement(driver, deleteButton);
		
		Alert alert = driver.switchTo().alert();
        alert.accept();
        
        Utilities.waitForSeconds(10);
        
        //Verifies if Data Exsits After Delete
  		employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
  		toDelete = null;
  		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
  		{
  			if(we.getText().equalsIgnoreCase(FirstName + " " + LastName))
  			{
  				toDelete = we;
  				break;
  			}
  		}
  		
  		assertTrue("Deleted Element Was Found", toDelete == null);
	}
	
	@After
	public void tearDown()
	{
		if(CloseBrowser)
		{
			driver.close();
		}
	}
}