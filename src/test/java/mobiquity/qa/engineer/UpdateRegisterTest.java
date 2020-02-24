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
public class UpdateRegisterTest
{
	//Web Driver For Browser Management
	private WebDriver driver;
	
	//Parameters For Test Execution
	private String OldFirstName;
	private String OldLastName;
	private String NewFirstName;
	private String NewLastName;
	private String StartDate;
	private String Email;
	private boolean CloseBrowser;
	
	@Parameters
	public static Collection<Object[]> data()
	{
		//Distincts Scenaries To Test The Login Function
		ArrayList<Object[]> scenaries = new ArrayList<Object[]>();
		
		//Read Escenaries From a CSV File
		for(String[] data : Utilities.getTestDataFromCSV(Constants.getUpdateTestDataPath(), ","))
		{
			scenaries.add(new Object[] {data[0], data[1], data[2], data[3], data[4], data[5], data[6].equalsIgnoreCase("true")});
		}
		
		return scenaries;
	}
	
	/*
	 * @Param OldFirstName = First Name to Find Record To Update
	 * @Param OldLastName = Last Name to Find Record To Update
	 * @Param NewFirstName = First Name to Update Record
	 * @Param NewLastName = Last Name to Update Record
	 * @Param StartDate = Start Date to Update Record
	 * @Param Email = Email to Update Record
	 * @Param CloseBrowser = Close Browser After Register
	 * */
	public UpdateRegisterTest(String OldFirstName, String OldLastName, String NewFirstName, String NewLastName, String StartDate, String Email, boolean CloseBrowser)
	{
		super();
		this.OldFirstName = OldFirstName;
		this.OldLastName = OldLastName;
		this.NewFirstName = NewFirstName;
		this.NewLastName = NewLastName;
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
		WebElement editButton = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EditButton.getXpath());
		assertTrue("Edit Button Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(editButton));
		
		//Verifies if Data Exsits Before Insert. If Found
		WebElement employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
		assertTrue("Employee List Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(employeeList));
		WebElement toUpdate = null;
		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
		{
			if(we.getText().equalsIgnoreCase(OldFirstName + " " + OldLastName))
			{
				System.out.println("There Is A Register Found That Match With Supplied First and Last Name");
				toUpdate = we;
				break;
			}
		}
		
		assertTrue("Element To Update Not Found", toUpdate != null);
		
		//Clicks on the Create Button to Start Creating New Register
		Utilities.clickOnElement(driver, toUpdate);
		
		//Clicks on the Create Button to Start Creating New Register
		Utilities.clickOnElement(driver, editButton);
		
		//Test The Back Button from Create Form
		WebElement backButton = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.BackButton.getXpath());
		assertTrue("Cancel Button Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(backButton));
		Utilities.clickOnElement(driver, backButton);
		
		//Verifies if Data Exsits Before Insert. If Found
		employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
		{
			if(we.getText().equalsIgnoreCase(OldFirstName + " " + OldLastName))
			{
				toUpdate = we;
				break;
			}
		}
		
		assertTrue("Element To Update Not Found", toUpdate != null);
		
		//Clicks on the Create Button to Start Creating New Register
		Utilities.clickOnElement(driver, toUpdate);
		
		//Gets Back to Create Form
		editButton = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EditButton.getXpath());
		Utilities.clickOnElement(driver, editButton);
		
		WebElement firstNameInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.FirstNameInput.getXpath());
		assertTrue("First Name Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(firstNameInput));
		
		WebElement lastNameInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.LastNameInput.getXpath());
		assertTrue("Last Name Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(lastNameInput));
		
		WebElement startDateInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.StartDateInput.getXpath());
		assertTrue("Start Date Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(startDateInput));
		
		WebElement emailInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.EmailInput.getXpath());
		assertTrue("Email Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(emailInput));
		
		WebElement updateButton = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.UpdateButton.getXpath());
		assertTrue("Update Button Input Should be Visible and Enabled", Utilities.isWebElementDisplayedAndEnabled(updateButton));
		
		firstNameInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.FirstNameInput.getXpath());
		Utilities.setTextOnElement(firstNameInput, NewFirstName);
		
		lastNameInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.LastNameInput.getXpath());
		Utilities.setTextOnElement(lastNameInput, NewLastName);
		
		startDateInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.StartDateInput.getXpath());
		Utilities.setTextOnElement(startDateInput, StartDate);
		
		emailInput = Utilities.getWebElementFromXpath(driver, Constants.EditFormWebElementsPaths.EmailInput.getXpath());
		Utilities.setTextOnElement(emailInput, Email);
		
		//Test The Cancel Button from Create Form
		Utilities.clickOnElement(driver, updateButton);
		
		//Verifies if Data Exsits After Insert
		employeeList = Utilities.getWebElementFromXpath(driver, Constants.MainWebElementsPaths.EmployeesList.getXpath());
		WebElement insertedRegister = null;
		for(WebElement we : Utilities.getWebElementsFromWebElementByTagName(employeeList, "li"))
		{
			if(we.getText().equalsIgnoreCase(NewFirstName + " " + NewLastName))
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