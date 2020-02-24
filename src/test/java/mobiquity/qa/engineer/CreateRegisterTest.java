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
public class CreateRegisterTest
{
	//Web Driver For Browser Management
	private WebDriver driver;
	
	//Parameters For Test Execution
	private boolean CloseBrowser;
	
	@Parameters
	public static Collection<Object[]> data()
	{
		//Distincts Scenaries To Test The Login Function
		ArrayList<Object[]> scenaries = new ArrayList<Object[]>();
		
		//Read Escenaries From a CSV File
		for(String[] data : Utilities.getTestDataFromCSV(Constants.getCreateTestDataPath(), ","))
		{
			scenaries.add(new Object[] {data[0], data[1], data[2].equalsIgnoreCase("true"), data[3].equalsIgnoreCase("true"), data[4].equalsIgnoreCase("true")});
		}
		
		return scenaries;
	}
	
	/*
	 * @Param UserName = User Name For Login
	 * @Param UserPass = User Password For Login
	 * @Param ExpectedResult = Expected Result For Login (Login Successful = True)
	 * @Param LogOut = Perform Log Out After Login
	 * */
	public CreateRegisterTest(String UserName, String UserPass, boolean ExpectedResult, boolean LogOut, boolean CloseBrowser)
	{
		super();
		this.CloseBrowser = CloseBrowser;
	}
	
	@Before
	public void setUp()
	{
		//Gets a Mozilla Firefox Browser to Test Passed URL
		driver = WebDriverFactory.getSingleFirefoxDriver(Constants.getWebURL());
	}
	
	@Test
	public void testGoogleSearch()
	{
		//Call Login Test To Perform Log-In. If Test Fails, Finish This Test As Failed
		assertTrue("Login Should Be Successful to Perform This Test", Utilities.getTestResult(LoginTest.class));
		
		//Gets Web Elements From Main Window
		WebElement createButton = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.CreateButton.getXpath());
		assertTrue("Create Button Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(createButton));
		
		WebElement editButton = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EditButton.getXpath());
		assertTrue("Edit Button Should be Visible and Disabled", Utilities.isWebElementDisplayedAndEnabled(editButton));
		
		WebElement deleteButton = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.DeleteButton.getXpath());
		assertTrue("Delete Button Should be Visible and Diabled", Utilities.isWebElementDisplayedAndEnabled(deleteButton));
		
		//Defines Data To Insert Into List
		String firstName = "Juan";
		String lastName = "Berges";
		String startDate = "2020-02-23";
		String email = "myemail@email.com";
		
		//Verifies if Data Exsits Before Insert
		WebElement employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
		assertTrue("Employee List Should be Visible and Diabled", Utilities.isWebElementDisplayedAndEnabled(employeeList));
		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
		{
			assertTrue("Register Already Exsits In List", !we.getText().equalsIgnoreCase(firstName + " " + lastName));
		}
		
		Utilities.clickOnElement(createButton);
		
		WebElement firstNameInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.FirstNameInput.getXpath());
		assertTrue("First Name Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(firstNameInput));
		
		WebElement lastNameInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.LastNameInput.getXpath());
		assertTrue("Last Name Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(lastNameInput));
		
		WebElement startDateInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.StartDateInput.getXpath());
		assertTrue("Start Date Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(startDateInput));
		
		WebElement emailInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.EmailInput.getXpath());
		assertTrue("Email Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(emailInput));
		
		WebElement cancelButton = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.CancelButton.getXpath());
		assertTrue("Cancel Button Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(cancelButton));
		
		WebElement addButton = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.AddButton.getXpath());
		assertTrue("Add Button Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(addButton));
		
		//Test The Cancel Button from Create Form
		Utilities.clickOnElement(cancelButton);
		
		//Gets Back to Create Form
		createButton = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.CreateButton.getXpath());
		Utilities.clickOnElement(createButton);
		
		firstNameInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.FirstNameInput.getXpath());
		Utilities.setTextOnElement(firstNameInput, firstName);
		
		lastNameInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.LastNameInput.getXpath());
		Utilities.setTextOnElement(lastNameInput, lastName);
		
		startDateInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.StartDateInput.getXpath());
		Utilities.setTextOnElement(startDateInput, startDate);
		
		emailInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.EmailInput.getXpath());
		Utilities.setTextOnElement(emailInput, email);
		
		//Test The Cancel Button from Create Form
		addButton = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.AddButton.getXpath());
		Utilities.clickOnElement(addButton);
		
		//Verifies if Data Exsits After Insert
		employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
		WebElement insertedRegister = null;
		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
		{
			if(we.getText().equalsIgnoreCase(firstName + " " + lastName))
			{
				System.out.println("Inserted Register Found!");
				insertedRegister = we;
				break;
			}
		}
		
		Utilities.clickOnElement(insertedRegister);
		
		//Edit
		firstName = "Pedro";
		lastName = "Marte";
		startDate = "2020-02-24";
		email = "fulano@gmail.com";
		
		editButton = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EditButton.getXpath());
		Utilities.clickOnElement(editButton);
		
		firstNameInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.FirstNameInput.getXpath());
		Utilities.setTextOnElement(firstNameInput, firstName);
		
		lastNameInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.LastNameInput.getXpath());
		Utilities.setTextOnElement(lastNameInput, lastName);
		
		startDateInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.StartDateInput.getXpath());
		Utilities.setTextOnElement(startDateInput, startDate);
		
		emailInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.EmailInput.getXpath());
		Utilities.setTextOnElement(emailInput, email);
		
		//Test The Cancel Button from Create Form
		WebElement updateButton = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.UpdateButton.getXpath());
		Utilities.clickOnElement(updateButton);
		
		//Verifies if Data Exsits After Update
		employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
		WebElement updatedRegister = null;
		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
		{
			if(we.getText().equalsIgnoreCase(firstName + " " + lastName))
			{
				System.out.println("Updated Register Found!");
				updatedRegister = we;
				break;
			}
		}
		
		assertTrue("Updated Element Not Found", updatedRegister != null);
		
		Utilities.clickOnElement(updatedRegister);
		
		deleteButton = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.DeleteButton.getXpath());
		Utilities.clickOnElement(deleteButton);
		
        Alert alert = driver.switchTo().alert();
        alert.accept();
        
        Utilities.waitForSeconds(10);
        
        //Verifies if Data Exsits After Delete
  		employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
  		WebElement deletedRegister = null;
  		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
  		{
  			if(we.getText().equalsIgnoreCase(firstName + " " + lastName))
  			{
  				deletedRegister = we;
  				break;
  			}
  		}
  		
  		assertTrue("Deleted Element Was Found", deletedRegister == null);
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