package testCase;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import methods.Methods;
import pageObjects.HomePageObject;
import pageObjects.ProjectConfigObject;

public class TC01_Login_Orange {
	Methods m = new Methods();
	static Boolean statusTest ;
	
	@BeforeTest
	private void BeforeTest() {
		System.out.println("Inicio de ejecucion para Login");
		m.initializeReport("Test OrangeHRM", "Test Case 01 Login","OrangeLogin");
		m.launchBrowser(ProjectConfigObject.BROWSER_CHROME, ProjectConfigObject.URL, ProjectConfigObject.RUN_HEADLES);

	}

	@Test(priority = 0)
	public void Login() throws IOException {
		
		m.input("id", HomePageObject.USER_TXBOX, ProjectConfigObject.USERNAME);
		m.input("id", HomePageObject.PASSWORD_TXBOX, ProjectConfigObject.PASSWORD);
		m.click("id", HomePageObject.LOGIN_BUTTON);
	    m.takeScreenShotAndAddToTheReport("User Logged in successfully",statusTest);
		m.close();

	}

}
