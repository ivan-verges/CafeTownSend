package mobiquity.qa.engineer;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
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
	private String FirstName;
	private String LastName;
	private String StartDate;
	private String Email;
	private boolean CloseBrowser;
	
	@Parameters
	public static Collection<Object[]> data()
	{
		//Distincts Scenaries To Test The Login Function
		ArrayList<Object[]> scenaries = new ArrayList<Object[]>();
		
		//Read Escenaries From a CSV File
		for(String[] data : Utilities.getTestDataFromCSV(Constants.getCreateTestDataPath(), ","))
		{
			scenaries.add(new Object[] {data[0], data[1], data[2], data[3], data[4].equalsIgnoreCase("true")});
		}
		
		return scenaries;
	}
	
	/*
	 * @Param FirstName = First Name to Register
	 * @Param LastName = Last Name to Register
	 * @Param StartDate = Start Date to Register
	 * @Param Email = Email to Register
	 * @Param CloseBrowser = Close Browser After Register
	 * */
	public CreateRegisterTest(String FirstName, String LastName, String StartDate, String Email, boolean CloseBrowser)
	{
		super();
		this.FirstName = FirstName;
		this.LastName = LastName;
		this.StartDate = StartDate;
		this.Email = Email;
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
		WebElement createButton = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.CreateButton.getXpath());
		assertTrue("Create Button Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(createButton));
		
		//Verifies if Data Exsits Before Insert. If Found
		WebElement employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
		assertTrue("Employee List Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(employeeList));
		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
		{
			if(we.getText().equalsIgnoreCase(FirstName + " " + LastName))
			{
				System.out.println("There Is A Register Found That Match With Supplied First and Last Name");
			}
		}
		
		//Clicks on the Create Button to Start Creating New Register
		Utilities.clickOnElement(driver, createButton);
		
		//Test The Cancel Button from Create Form
		WebElement cancelButton = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.CancelButton.getXpath());
		assertTrue("Cancel Button Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(cancelButton));
		Utilities.clickOnElement(driver, cancelButton);
		
		
		//Gets Back to Create Form
		createButton = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.CreateButton.getXpath());
		Utilities.clickOnElement(driver, createButton);
		
		WebElement firstNameInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.FirstNameInput.getXpath());
		assertTrue("First Name Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(firstNameInput));
		
		WebElement lastNameInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.LastNameInput.getXpath());
		assertTrue("Last Name Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(lastNameInput));
		
		WebElement startDateInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.StartDateInput.getXpath());
		assertTrue("Start Date Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(startDateInput));
		
		WebElement emailInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.EmailInput.getXpath());
		assertTrue("Email Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(emailInput));
		
		WebElement addButton = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.AddButton.getXpath());
		assertTrue("Add Button Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(addButton));
		
		firstNameInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.FirstNameInput.getXpath());
		Utilities.setTextOnElement(firstNameInput, FirstName);
		
		lastNameInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.LastNameInput.getXpath());
		Utilities.setTextOnElement(lastNameInput, LastName);
		
		startDateInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.StartDateInput.getXpath());
		Utilities.setTextOnElement(startDateInput, StartDate);
		
		emailInput = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.EmailInput.getXpath());
		Utilities.setTextOnElement(emailInput, Email);
		
		//Test The Cancel Button from Create Form
		addButton = Utilities.getWebElementFromXpath(driver, Constants.CreateFormWebElementsPaths.AddButton.getXpath());
		Utilities.clickOnElement(driver, addButton);
		
		//Verifies if Data Exsits After Insert
		employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
		WebElement insertedRegister = null;
		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
		{
			if(we.getText().equalsIgnoreCase(FirstName + " " + LastName))
			{
				System.out.println("Inserted Register Found!");
				insertedRegister = we;
				break;
			}
		}
		
		Utilities.clickOnElement(driver, insertedRegister);
		
		assertTrue("Register Was Not Added", insertedRegister != null);
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