package mobiquity.qa.engineer;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	
	@Parameters
	public static Collection<Object[]> data()
	{
		//Distincts Scenaries To Test The Login Function
		Object[][] scenaries = {
				//Credentials = Right, Login Expected Result = Login OK, Expected Test Result = Pass
				//{Constants.Credentials.RightUser.getValue(), Constants.Credentials.RightPass.getValue(), true, true},
				
				//Credentials = Wrong, Login Expected Result = Login Fail, Expected Test Result = Pass
				//{Constants.Credentials.WrongUser.getValue(), Constants.Credentials.WrongPass.getValue(), false, false},
				
				//Credentials = Right, Login Expected Result = Login OK, Expected Test Result = Fail
				//{Constants.Credentials.RightUser.getValue(), Constants.Credentials.RightPass.getValue(), false, true},
				
				//Credentials = Wrong, Login Expected Result = Login OK, Expected Test Result = Fail
				//{Constants.Credentials.WrongUser.getValue(), Constants.Credentials.WrongPass.getValue(), true, false},
				
				//Login With Right Credentials and Leave Session Open for Other Tests
				{Constants.Credentials.RightUser.getValue(), Constants.Credentials.RightPass.getValue(), true, false}
		};
		
		return Arrays.asList(scenaries);
	}
	
	/*
	 * @Param UserName = User Name For Login
	 * @Param UserPass = User Password For Login
	 * @Param ExpectedResult = Expected Result For Login (Login Successful = True)
	 * @Param LogOut = Perform Log Out After Login
	 * */
	public LoginTest(String UserName, String UserPass, boolean ExpectedResult, boolean LogOut)
	{
		super();
		this.UserName = UserName;
		this.UserPass = UserPass;
		this.ExpectedResult = ExpectedResult;
		this.LogOut = LogOut;
	}
	
	@Before
	public void setUp()
	{
		//Gets a Mozilla Firefox Browser to Test Passed URL
		driver = WebDriverFactory.getSingleFirefoxDriver(Constants.WebURL);
	}
	
	@Test
	public void testGoogleSearch()
	{
		//Flag For Login Result
		boolean loginResult = false;
		
		//Finds Input User Name Element And Writes User Name For Login
		WebElement inputUserName = Utilities.getWebElementFromXpath(driver, Constants.LoginWebElementsPaths.UserNameInput.getXpath());
		assertTrue("Input Text For User Name Not Found", inputUserName != null);
		if(inputUserName != null)
		{
			Utilities.setTextOnElement(inputUserName, UserName);
		}
		
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
		///driver.close();
	}
}