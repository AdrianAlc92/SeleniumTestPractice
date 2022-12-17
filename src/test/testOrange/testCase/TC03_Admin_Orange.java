package testCase;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import methods.Methods;
import pageObjects.AdminPageObject;
import pageObjects.HomePageObject;
import pageObjects.ProjectConfigObject;

public class TC03_Admin_Orange {
	
	
	Methods m = new Methods();
	static Boolean statusPage;
	static Boolean a ;
	
	@BeforeTest
	private void BeforeTest() {
		System.out.println("Inicio de ejecucion para modificacion Admin");
		m.initializeReport("Test OrangeHRM", "Test Case 03 Admin Orange","OrangeAdmin");
		m.launchBrowser(ProjectConfigObject.BROWSER_CHROME, ProjectConfigObject.URL, ProjectConfigObject.RUN_HEADLES);

	}

	@Test(priority = 0)
	public void Login() throws Exception {

		m.input("id", HomePageObject.USER_TXBOX, ProjectConfigObject.USERNAME);
		m.input("id", HomePageObject.PASSWORD_TXBOX, ProjectConfigObject.PASSWORD);
		m.click("id", HomePageObject.LOGIN_BUTTON);
		statusPage = false;

	}
	
	@SuppressWarnings("unused")
	@Test(priority = 1 )
	public void Admin() throws IOException {
		m.takeScreenShotAndAddToTheReport("User login successfully", statusPage);
		statusPage = true;
		m.click("id", AdminPageObject.Admin_BTN);
		m.input("id",AdminPageObject.Admin_UserName, AdminPageObject.Admin_Name);
		m.selectOptDropdown("id", AdminPageObject.Admin_UseRole, 2);
		m.input("id", AdminPageObject.Admin_EmpName, AdminPageObject.Admin_NAME_F_EMPNAME);
		if(AdminPageObject.Admin_Status == "Enabled"){
			a = false;
		}else {
			a = true;
		}
		m.click("id", AdminPageObject.Admin_SerchBTN);
		statusPage = false;
	}
	
	@Test(priority = 2)
	public void UpdateFiona() throws IOException, InterruptedException {
		
		m.takeScreenShotAndAddToTheReport("User Admin", statusPage);
		statusPage = true;
		m.click("xpath", AdminPageObject.Admin_SELECT_FIONA);
		m.click("id", AdminPageObject.Admin_UPDATE_FIONA_BTN);
		if (a == false) {
			m.selectOptDropdown("id", AdminPageObject.Admin_EDITH_STATUS, 0);//Enable		
		}else {
			m.selectOptDropdown("id", AdminPageObject.Admin_EDITH_STATUS, 1);//Disable	
		}
		m.click("id", AdminPageObject.Admin_SAVE_STATUS);
		Thread.sleep(2000);
		m.input("id",AdminPageObject.Admin_UserName, AdminPageObject.Admin_Name);
		m.selectOptDropdown("id", AdminPageObject.Admin_UseRole, 2);
		m.input("id", AdminPageObject.Admin_EmpName, AdminPageObject.Admin_NAME_F_EMPNAME);
		statusPage = false;
	}
	
	
	@AfterTest
	
	public void close() throws IOException {
		m.takeScreenShotAndAddToTheReport("User updatefiona", statusPage);
		statusPage = true;
		
	m.close();
		
	}
	
}
