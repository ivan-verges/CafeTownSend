package mobiquity.qa.engineer;

import mobiquity.qa.engineer.utilities.Utilities;

public class Main
{
	public static void main(String[] args)
	{
		//System.out.println("User Login Test Result: " + (Utilities.getTestResult(LoginTest.class)? "Passed" : "Failed"));
		System.out.println("Create Register Test Result: " + (Utilities.getTestResult(CreateRegisterTest.class)? "Passed" : "Failed"));
	}
}