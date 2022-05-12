package testCase;

import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import methods.Methods;
import pageObjects.DirectoryPageObject;
import pageObjects.HomePageObject;
import pageObjects.PimPageObject;
import pageObjects.ProjectConfigObject;

public class TC04_PIM_Orange {
	Methods m = new Methods();
	static Boolean statusPage;
	static Boolean validSubUnit;

	@BeforeTest
	private void BeforeTest() {
		System.out.println("Inicio de ejecucion para la secion de PIM");
		m.initializeReport("Test OrangeHRM", "Test Case 04 PIM", "OrangePIM");
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
	@Test(priority = 1)
	public void PimFilterPage() throws IOException {
		m.takeScreenShotAndAddToTheReport("User login", statusPage);
		statusPage = true;
		m.click("id", PimPageObject.PIM_TAB);
		m.input("xpath", PimPageObject.EMPLOYEE_NAME_TXBOX, PimPageObject.OBIS_NAME);
		m.input("id", PimPageObject.ID_TXBOX, PimPageObject.OBIS_ID);
		m.selectOptDropdown("id", PimPageObject.JOB_TITLE_DRDWN, 9);
		m.click("id", PimPageObject.SEARCH_BUTTON);
		if (PimPageObject.VALID_ADMIN_SUB_UNIT_TXT== "Administration") {
			validSubUnit=true;
			System.out.println("El usuario Odis esta en la unidad de Administracion " );
		}else {
			validSubUnit=false;
			System.out.println("El usuario Odis esta en la unidad de Administracion "+ PimPageObject.VALID_ADMIN_SUB_UNIT_TXT);
		}
		m.validation("xpath", PimPageObject.VALID_ADMIN_SUB_UNIT_TXT, "Administration");
		m.click("id", PimPageObject.NAME_SELECTION_LIST);
		statusPage = false;
	}

	@Test(priority = 2)
	public void EditEmployEdit() throws Exception {
		m.takeScreenShotAndAddToTheReport("PimFilter page", statusPage);
		statusPage = true;
		m.click("xpath", PimPageObject.JOB_LAT_MENU);
		m.click("xpath", PimPageObject.EDIT_SAVE_BUTTON);
		if (validSubUnit) {
			m.selectOptDropdown("id", PimPageObject.CHANGE_EMPLOY_STATUS_DRDWN, 1);
			m.selectOptDropdown("id", PimPageObject.CHANGE_SUB_UNIT_DRDWN, 4);
		}else {
			m.selectOptDropdown("id", PimPageObject.CHANGE_EMPLOY_STATUS_DRDWN, 3);
			m.selectOptDropdown("id", PimPageObject.CHANGE_SUB_UNIT_DRDWN, 1);
		}
		m.click("xpath", PimPageObject.EDIT_SAVE_BUTTON);
		statusPage = false;
	}
	
	@Test(priority = 3)

	@AfterClass
	public void close() throws Exception {
		m.click("xpath", PimPageObject.EMPLOY_LIST_SUBTAB);
		m.takeScreenShotAndAddToTheReport("Final modification ", statusPage);
		m.close();
	}

}
