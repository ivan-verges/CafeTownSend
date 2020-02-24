package mobiquity.qa.engineer.utilities;

public class Constants
{
	private static String WebURL = "http://cafetownsend-angular-rails.herokuapp.com/";
	private static int DefaultTimeoutSeconds = 5;
	private static String LoginTestDataPath = "testdata/login.csv";
	private static String CreateTestDataPath = "testdata/create.csv";
	private static String UpdateTestDataPath = "testdata/update.csv";
	private static String DeleteTestDataPath = "testdata/delete.csv";
	private static String ChromeDriverBasePath = "webdriver/chrome/";
	private static String FirefoxDriverBasePath = "webdriver/firefox/";
	
	public static String getWebURL()
	{
		return WebURL;
	}

	public static int getDefaultTimeoutSeconds()
	{
		return DefaultTimeoutSeconds;
	}
	
	public static String getLoginTestDataPath()
	{
		return Utilities.getResourcePath(LoginTestDataPath);
	}

	public static String getCreateTestDataPath()
	{
		return Utilities.getResourcePath(CreateTestDataPath);
	}

	public static String getUpdateTestDataPath()
	{
		return Utilities.getResourcePath(UpdateTestDataPath);
	}

	public static String getDeleteTestDataPath()
	{
		return Utilities.getResourcePath(DeleteTestDataPath);
	}
	
	public static String getChromeDriverBasePath()
	{
		return Utilities.getResourcePath(ChromeDriverBasePath);
	}
	
	public static String getFirefoxDriverBasePath()
	{
		return Utilities.getResourcePath(FirefoxDriverBasePath);
	}

	public static enum LoginWebElementsPaths
	{
		LoginButton("/html/body/div/div/div/form/fieldset/button"),
	    LogoutButton("/html/body/div/header/div/p[1]"),
	    UserNameLabel("/html/body/div/div/div/form/fieldset/label[1]/span[1]"),
		UserNameInput("/html/body/div/div/div/form/fieldset/label[1]/input"),
	    UserPassLabel("/html/body/div/div/div/form/fieldset/label[2]/span"),
		UserPassInput("/html/body/div/div/div/form/fieldset/label[2]/input"),    
	    GreetingLabel("/html/body/div/header/div/p[2]"),
	    InvalidCredentialsLabel("/html/body/div/div/div/form/fieldset/p[1]");
	 
	    private String xPath;
	 
	    LoginWebElementsPaths(String xPath)
	    {
	        this.xPath = xPath;
	    }
	 
	    public String getXpath()
	    {
	        return xPath;
	    }
	}
	
	public static enum MainWebElementsPaths
	{
		CreateButton("/html/body/div/div/div/ul/li[1]/a"),
		EditButton("/html/body/div/div/div/ul/li[2]/a"),
		DeleteButton("/html/body/div/div/div/ul/li[3]/a"),
		EmployeesList("/html/body/div/div/div/div/ul"),
		EmployeeListElement("/html/body/div/div/div/div/ul/li[INDEX]");
		
		private String xPath;
		 
		MainWebElementsPaths(String xPath)
	    {
	        this.xPath = xPath;
	    }
	 
	    public String getXpath()
	    {
	        return xPath;
	    }
	}
	
	public static enum CreateFormWebElementsPaths
	{
		FirstNameLabel("/html/body/div/div/div/form/fieldset/label[1]/span"),
		FirstNameInput("/html/body/div/div/div/form/fieldset/label[1]/input"),
		LastNameLabel("/html/body/div/div/div/form/fieldset/label[2]/span"),
		LastNameInput("/html/body/div/div/div/form/fieldset/label[2]/input"),
		StartDateLabel("/html/body/div/div/div/form/fieldset/label[3]/span"),
		StartDateInput("/html/body/div/div/div/form/fieldset/label[3]/input"),
		EmailLabel("/html/body/div/div/div/form/fieldset/label[4]/span"),
		EmailInput("/html/body/div/div/div/form/fieldset/label[4]/input"),
		AddButton("/html/body/div/div/div/form/fieldset/div/button[2]"),
		CancelButton("/html/body/div/div/div/ul[2]/li/a");
		
		private String xPath;
		 
		CreateFormWebElementsPaths(String xPath)
	    {
	        this.xPath = xPath;
	    }
	 
	    public String getXpath()
	    {
	        return xPath;
	    }
	}
	
	public static enum EditFormWebElementsPaths
	{
		FirstNameLabel("/html/body/div/div/div/form/fieldset/label[1]/span"),
		FirstNameInput("/html/body/div/div/div/form/fieldset/label[1]/input"),
		LastNameLabel("/html/body/div/div/div/form/fieldset/label[2]/span"),
		LastNameInput("/html/body/div/div/div/form/fieldset/label[2]/input"),
		StartDateLabel("/html/body/div/div/div/form/fieldset/label[3]/span"),
		StartDateInput("/html/body/div/div/div/form/fieldset/label[3]/input"),
		EmailLabel("/html/body/div/div/div/form/fieldset/label[4]/span"),
		EmailInput("/html/body/div/div/div/form/fieldset/label[4]/input"),
		UpdateButton("/html/body/div/div/div/form/fieldset/div/button[1]"),
		BackButton("/html/body/div/div/div/ul[1]/li/a"),
		DeleteButton("/html/body/div/div/div/form/fieldset/div/p");
		
		private String xPath;
		 
		EditFormWebElementsPaths(String xPath)
	    {
	        this.xPath = xPath;
	    }
	 
	    public String getXpath()
	    {
	        return xPath;
	    }
	}
	
	public static enum Credentials
	{
		RightUser("Luke"),
	    RightPass("Skywalker"),
		WrongUser("WrongUser"),
	    WrongPass("WrongPass");
	 
	    private String value;
	 
	    Credentials(String value)
	    {
	        this.value = value;
	    }
	 
	    public String getValue()
	    {
	        return value;
	    }
	}
}