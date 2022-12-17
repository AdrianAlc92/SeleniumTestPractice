package caseTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Objects.HomepageX;
import Objects.ListGamePage;
import Objects.ProjectConfigObj;
import methods.Methods;


public class Xbox {
	
	Methods m = new Methods();
	static Boolean statusPage;
	

	@BeforeTest
	private void BeforeTest() {
		System.out.println("Inicio de ejecucion para XBOX");
		m.initializeReport("XBOX", "Test Case 01","XboxShopping");
		m.launchBrowser(ProjectConfigObj.BROWSER_CHROME, ProjectConfigObj.URL, ProjectConfigObj.RUN_HEADLES);

	}

	@Test(priority = 0)
	public void SearchBarXbox() throws Exception {
		m.click("id", HomepageX.SEARCH_BOX_BUTTON);
		m.input("id", HomepageX.SEARCH_BOX, "xbox");
		m.submit("id", HomepageX.SEARCH_BOX_BUTTON);
		statusPage = false;

	}
	@Test(priority = 1)
	public void ListResultCheck() throws Exception {
		
		m.explicitWait("xpath",HomepageX.LINK_LIST);
		m.listCreate("xpath", HomepageX.LINK_LIST);
		m.explicitWait("xpath",HomepageX.POPUP_CLOSE2);
		m.click("xpath", HomepageX.POPUP_CLOSE2);
		m.click("xpath", HomepageX.JUEGOS_DIGITALES);
		m.click("xpath", HomepageX.JUEGOS_DIGITALES);
		
		
		
		statusPage = false;
		
	}
	
	@Test(priority = 2)
	public void SelectItemstoBuyForza() throws Exception {
		
		m.explicitWait("xpath",ListGamePage.FORZA_HORIZ_SELECT);
		m.click("xpath", ListGamePage.FORZA_HORIZ_SELECT);
		m.explicitWait("xpath",ListGamePage.ADD_CAR_BTTN);
		m.click("xpath", ListGamePage.ADD_CAR_BTTN);
		m.explicitWait("xpath",ListGamePage.CAR_WITH_1ART);
		m.backPage();
		
		statusPage = false;
	}
	@Test(priority = 3)
	public void SelectItemstoBuyHalo() throws Exception {
		
		m.explicitWait("xpath",ListGamePage.HALO_INFINITE_SELECT);
		m.click("xpath", ListGamePage.HALO_INFINITE_SELECT);
		m.explicitWait("xpath",ListGamePage.ADD_CAR_BTTN);
		m.click("xpath", ListGamePage.ADD_CAR_BTTN);
		m.explicitWait("xpath",ListGamePage.CAR_WITH_2ART);
		m.backPage();
		System.out.println(ListGamePage.CAR_WITH_2ART);
		
		statusPage = false;
	}
	
	
}
