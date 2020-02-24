package mobiquity.qa.engineer;

public class Constants
{
	public static String WebURL = "http://cafetownsend-angular-rails.herokuapp.com/";
	public static int DefaultTimeoutSeconds = 5;
	public static String LoginTestDataPath = "C:\\Users\\verge\\Desktop\\Projects\\Eclipse\\mobiquity.qa.engineer\\src\\test\\resources\\testdata\\login.csv";
	public static String CreateTestDataPath = "C:\\Users\\verge\\Desktop\\Projects\\Eclipse\\mobiquity.qa.engineer\\src\\test\\resources\\testdata\\create.csv";
	public static String UpdateTestDataPath = "C:\\Users\\verge\\Desktop\\Projects\\Eclipse\\mobiquity.qa.engineer\\src\\test\\resources\\testdata\\update.csv";
	public static String DeleteTestDataPath = "C:\\Users\\verge\\Desktop\\Projects\\Eclipse\\mobiquity.qa.engineer\\src\\test\\resources\\testdata\\delete.csv";
	
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