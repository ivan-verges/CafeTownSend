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
public class LoginTest
{
	//Web Driver For Browser Management
	private WebDriver driver;
	
	//Parameters For Test Execution
	private String UserName;
	private String UserPass;
	private boolean ExpectedResult;
	private boolean LogOut;
	private boolean CloseBrowser;
	
	@Parameters
	public static Collection<Object[]> data()
	{
		//Distincts Scenaries To Test The Login Function
		ArrayList<Object[]> scenaries = new ArrayList<Object[]>();
		
		//Read Escenaries From a CSV File
		for(String[] data : Utilities.getTestDataFromCSV(Constants.getLoginTestDataPath(), ","))
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
	 * @Param CloseBrowser = Closes Browser After Running Test
	 * */
	public LoginTest(String UserName, String UserPass, boolean ExpectedResult, boolean LogOut, boolean CloseBrowser)
	{
		super();
		this.UserName = UserName;
		this.UserPass = UserPass;
		this.ExpectedResult = ExpectedResult;
		this.LogOut = LogOut;
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
		//Flag For Login Result
		boolean loginResult = false;
		
		//Finds Label User Name Element And Writes User Name For Login
		WebElement labelUserName = Utilities.getWebElementFromXpath(driver, Constants.LoginWebElementsPaths.UserNameLabel.getXpath());
		assertTrue("Label Text For User Name Not Found", labelUserName != null);
		
		//Finds Input User Name Element And Writes User Name For Login
		WebElement inputUserName = Utilities.getWebElementFromXpath(driver, Constants.LoginWebElementsPaths.UserNameInput.getXpath());
		assertTrue("Input Text For User Name Not Found", inputUserName != null);
		if(inputUserName != null)
		{
			Utilities.setTextOnElement(inputUserName, UserName);
		}
		
		//Finds Label User Password Element And Writes User Password For Login
		WebElement labelUserPass = Utilities.getWebElementFromXpath(driver, Constants.LoginWebElementsPaths.UserPassLabel.getXpath());
		assertTrue("Input Text For User Password Not Found", labelUserPass != null);
		
		//Finds Input User Password Element And Writes User Password For Login
		WebElement inputUserPass = Utilities.getWebElementFromXpath(driver, Constants.LoginWebElementsPaths.UserPassInput.getXpath());
		assertTrue("Input Text For User Password Not Found", inputUserPass != null);
		if(inputUserPass != null)
		{
			Utilities.setTextOnElement(inputUserPass, UserPass);
		}
		
		//Finds Log-In Button Element And Clicks On It To Perform Log-In
		WebElement buttonLogIn = Utilities.getWebElementFromXpath(driver, Constants.LoginWebElementsPaths.LoginButton.getXpath());
		assertTrue("Log-In Button Not Found", buttonLogIn != null);
		if(buttonLogIn != null)
		{
			Utilities.clickOnElement(buttonLogIn);
		}
		
		//Finds Invalid Credentials Text Element And Verify If Login Was Successful
		WebElement textInvalidCredentials = Utilities.getWebElementFromXpath(driver, Constants.LoginWebElementsPaths.InvalidCredentialsLabel.getXpath());
		loginResult = textInvalidCredentials == null;
		if(ExpectedResult)
		{
			assertTrue("Invalid User Name/User Password Text Is Found And Shouldn't Be", loginResult);
		}
		else
		{
			assertTrue("Invalid User Name/User Password Text Not Found And Should Be", !loginResult);
		}
		
		//If Login Was Successful
		if(loginResult)
		{
			//Finds Greeting User Text Element And Verify If Login Was Successful
			WebElement textGreeting = Utilities.getWebElementFromXpath(driver, Constants.LoginWebElementsPaths.GreetingLabel.getXpath());
			assertTrue("Greeting Text Not Found", textGreeting != null);
			
			//Finds Log-Out Button Element And Clicks On It To Perform Log-Out
			WebElement buttonLogOut = Utilities.getWebElementFromXpath(driver, Constants.LoginWebElementsPaths.LogoutButton.getXpath());
			assertTrue("Log-Out Button Not Found", buttonLogOut != null);
			if(buttonLogOut != null)
			{
				//Validates If Login Result Was Successful (Greeting Text And Log-Out Button Shows, Invalid Credentials Text Doesn't Show)
				loginResult = (textGreeting != null && buttonLogOut != null && textInvalidCredentials == null);
				
				//If Log-Out Flag It's True, Perform Log-Out
				if(LogOut)
				{
					Utilities.clickOnElement(buttonLogOut);
				}
			}
		}
		
		//Verify Final Test Result
		assertTrue("Login Test Result It's Not As Expected", loginResult == ExpectedResult);
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