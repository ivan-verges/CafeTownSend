package mobiquity.qa.engineer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory
{
	
	private static WebDriver chromeDriver;
	private static WebDriver firefoxDriver;
	
	/*
	 * Return a String With The File Name Based On The Operating System To Select The Right Web Driver
	 * @Return String
	*/
	private static String getWebDriverFileName()
	{
		String fileName = "";
		String OS = System.getProperty("os.name").toLowerCase();
		
		if(OS.indexOf("win") >= 0)
		{
			fileName = "windows.exe";
		}
		else if(OS.indexOf("mac") >= 0)
		{
			fileName = "mace";
		}
		else if(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") >= 0)
		{
			fileName = "linux";
		}
		
		return fileName;
	}
	
	/*
	 * Returns a Singleton Google Chrome WebDriver Object Instance
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getSingleChromeDriver()
	{
		if(chromeDriver == null)
		{
			chromeDriver = getNewChromeDriver();
		}
		
		return chromeDriver;
	}
	
	/*
	 * Returns a Singleton Google Chrome WebDriver Object Instance
	 * @Param url = URL Path to Navigate
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getSingleChromeDriver(String url)
	{
		if(chromeDriver == null)
		{
			chromeDriver = getNewChromeDriver(url);
		}
		
		return chromeDriver;
	}
	
	/*
	 * Returns a Singleton Google Chrome WebDriver Object Instance
	 * @Param timeoutSeconds = Seconds to Wait for Elements Before a Timeout
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getSingleChromeDriver(int timeoutSeconds)
	{
		if(chromeDriver == null)
		{
			chromeDriver = getNewChromeDriver(timeoutSeconds);
		}
		
		return chromeDriver;
	}
	
	/*
	 * Returns a Singleton Google Chrome WebDriver Object Instance
	 * @Param url = URL Path to Navigate
	 * @Param timeoutSeconds = Seconds to Wait for Elements Before a Timeout
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getSingleChromeDriver(String url, int timeoutSeconds)
	{
		if(chromeDriver == null)
		{
			chromeDriver = getNewChromeDriver(url, timeoutSeconds);
		}
		
		return chromeDriver;
	}
	
	/*
	 * Returns a Singleton Mozilla Firefox WebDriver Object Instance
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getSingleFirefoxDriver()
	{
		if(firefoxDriver == null)
		{
			firefoxDriver = getNewFirefoxDriver();
		}
		
		return firefoxDriver;
	}
	
	/*
	 * Returns a Singleton Mozilla Firefox WebDriver Object Instance
	 * @Param url = URL Path to Navigate
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getSingleFirefoxDriver(String url)
	{
		if(firefoxDriver == null)
		{
			firefoxDriver = getNewFirefoxDriver(url);
		}
		
		return firefoxDriver;
	}
	
	/*
	 * Returns a Singleton Mozilla Firefox WebDriver Object Instance
	 * @Param timeoutSeconds = Seconds to Wait for Elements Before a Timeout
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getSingleFirefoxDriver(int timeoutSeconds)
	{
		if(firefoxDriver == null)
		{
			firefoxDriver = getNewFirefoxDriver(timeoutSeconds);
		}
		
		return firefoxDriver;
	}
	
	/*
	 * Returns a Singleton Mozilla Firefox WebDriver Object Instance
	 * @Param url = URL Path to Navigate
	 * @Param timeoutSeconds = Seconds to Wait for Elements Before a Timeout
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getSingleFirefoxDriver(String url, int timeoutSeconds)
	{
		if(firefoxDriver == null)
		{
			firefoxDriver = getNewFirefoxDriver(url, timeoutSeconds);
		}
		
		return firefoxDriver;
	}
	
	/*
	 * Returns a New Google Chrome WebDriver Object Instance
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getNewChromeDriver()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/webdriver/chrome/" + getWebDriverFileName());
		
		WebDriver cd = new ChromeDriver();
		
		//Set Default Timeout in Seconds
		cd.manage().timeouts().implicitlyWait(Constants.DefaultTimeoutSeconds, TimeUnit.SECONDS);
		
		//Maximize Browser Window
		cd.manage().window().maximize();
		
		return cd;
	}
	
	/*
	 * Returns a New Google Chrome WebDriver Object Instance
	 * @Param url = URL Path to Navigate
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getNewChromeDriver(String url)
	{
		WebDriver cd = getNewChromeDriver();
		
		//Open Defined URL
		cd.get(url);
		
		return cd;
	}
	
	/*
	 * Returns a New Google Chrome WebDriver Object Instance
	 * @Param timeoutSeconds = Seconds to Wait for Elements Before a Timeout
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getNewChromeDriver(int timeoutSeconds)
	{
		WebDriver cd = getNewChromeDriver();
		
		//Set Default Timeout in Seconds
		cd.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);
		
		return cd;
	}
	
	/*
	 * Returns a New Google Chrome WebDriver Object Instance
	 * @Param url = URL Path to Navigate
	 * @Param timeoutSeconds = Seconds to Wait for Elements Before a Timeout
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getNewChromeDriver(String url, int timeoutSeconds)
	{
		WebDriver cd = getNewChromeDriver(url);
		
		//Set Default Timeout in Seconds
		cd.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);
		
		return cd;
	}
	
	/*
	 * Returns a New Mozilla Firefox WebDriver Object Instance
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getNewFirefoxDriver()
	{
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/webdriver/firefox/" + getWebDriverFileName());
		
		WebDriver fd = new FirefoxDriver();
		
		//Set Default Timeout in Seconds
		fd.manage().timeouts().implicitlyWait(Constants.DefaultTimeoutSeconds, TimeUnit.SECONDS);
		
		//Maximize Browser Window
		fd.manage().window().maximize();
		
		return fd;
	}
	
	/*
	 * Returns a New Mozilla Firefox WebDriver Object Instance
	 * @Param url = URL Path to Navigate
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getNewFirefoxDriver(String url)
	{
		WebDriver fd = getNewFirefoxDriver();
		
		//Open Defined URL
		fd.get(url);
		
		return fd;
	}
	
	/*
	 * Returns a New Mozilla Firefox WebDriver Object Instance
	 * @Param timeoutSeconds = Seconds to Wait for Elements Before a Timeout
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getNewFirefoxDriver(int timeoutSeconds)
	{
		WebDriver fd = getNewFirefoxDriver();
		
		//Set Default Timeout in Seconds
		fd.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);
		
		return fd;
	}
	
	/*
	 * Returns a New Mozilla Firefox WebDriver Object Instance
	 * @Param url = URL Path to Navigate
	 * @Param timeoutSeconds = Seconds to Wait for Elements Before a Timeout
	 * @Return WebDriver to Interact With Browser
	*/
	public static WebDriver getNewFirefoxDriver(String url, int timeoutSeconds)
	{
		WebDriver fd = getNewFirefoxDriver(url);
		
		//Set Default Timeout in Seconds
		fd.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);
		
		return fd;
	}
}