package testCase;

import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import methods.Methods;
import pageObjects.DirectoryPageObject;
import pageObjects.HomePageObject;
import pageObjects.ProjectConfigObject;

public class TC02_Directory_Orange {
	Methods m = new Methods();
	static Boolean statusPage;

	@BeforeTest
	private void BeforeTest() {
		System.out.println("Inicio de ejecucion para la secion de directiorios");
		m.initializeReport("Test OrangeHRM", "Test Case 02 Directory","OrangeDirectory");
		m.launchBrowser(ProjectConfigObject.BROWSER_CHROME, ProjectConfigObject.URL, ProjectConfigObject.RUN_HEADLES);

	}

	@Test(priority = 0)
	public void Login() throws Exception {

		m.input("name", HomePageObject.USER_TXBOX, ProjectConfigObject.USERNAME);
		m.input("name", HomePageObject.PASSWORD_TXBOX, ProjectConfigObject.PASSWORD);
		m.click("xpath", HomePageObject.LOGIN_BUTTON);
		statusPage = false;

	}

	@Test(priority = 1)
	public void DirctoryPage() throws IOException {

		m.takeScreenShotAndAddToTheReport("User login successfully", statusPage);
		statusPage = true;
		m.click("id", DirectoryPageObject.DIRECTORY_TAB);
		m.input("id", DirectoryPageObject.NAME_TXBOX, DirectoryPageObject.NAME_SEARCH);
		m.selectOptDropdown("id", DirectoryPageObject.JOB_DRDWN, 21);
		// m.click("xpath", DirectoryPageObject.LOCATION_DRDWN); // intercambiar con la
		// linea 34
		m.selectOptDropdown("xpath", DirectoryPageObject.LOCATION_DRDWN2, 3);
		m.click("id", DirectoryPageObject.SEARCH_BUTTON);
		statusPage = false;
	}

	@Test(priority = 2)
	public void Validate() throws Exception {

		m.takeScreenShotAndAddToTheReport("User search directory successfully", statusPage);
		statusPage = true;
		m.validation("xpath", DirectoryPageObject.NAME_RESULT, DirectoryPageObject.NATHAN_NAME_VALID);

		statusPage = false;

	}

	@AfterClass
	public void close() throws Exception {

		m.takeScreenShotAndAddToTheReport("User validation directory successfull", statusPage);
		m.close();
	}

}
