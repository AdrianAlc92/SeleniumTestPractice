package caseTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ObjectTravelPage.ProjectConfigObjTravel;
import ObjectTravelPage.TravelPage;
import methods.Methods;

public class BlackPearlTravel {

	Methods m = new Methods();
	static Boolean statusPage;

	@BeforeTest
	private void BeforeTest() {
		System.out.println("Inicio de ejecucion para Viajes del perla negra");
		m.initializeReport("Perla Negra", "Test Case 01", "Viajes");
		m.launchBrowser(ProjectConfigObjTravel.BROWSER_CHROME, ProjectConfigObjTravel.URL, ProjectConfigObjTravel.RUN_HEADLES);

	}

	@Test(priority = 0)
	public void SearchBarXbox() throws Exception {
		m.validationVisible("xpath", TravelPage.SEARCH_TXTBOX);
		m.validationVisible("xpath", TravelPage.SEARCH_BTN);

	}

	@Test(priority = 1)
	public void SearchTextMessageEmpty() throws Exception {
		m.validationIsEmpty("xpath", TravelPage.SEARCH_TXTBOX);
		m.click("xpath", TravelPage.SEARCH_BTN);
		m.validationTextMessage("id", TravelPage.MSN_TEXT_RESULT_EMPTY, "Provide some query");

	}

	@Test(priority = 2)
	public void SearchIslandResults() throws Exception {
		m.input("xpath", TravelPage.SEARCH_TXTBOX, TravelPage.ISLA_WRT);
		m.click("xpath", TravelPage.SEARCH_BTN);
		m.listCreate("id", TravelPage.RESULT_LIST); // if page change to element of each result
		m.printElement(TravelPage.RESULT_LIST);

	}

	@Test(priority = 3)
	public void SearchCastlesResult() throws Exception {
		m.clear(TravelPage.SEARCH_TXTBOX);
		m.click("xpath", TravelPage.SEARCH_BTN);
		m.input("xpath", TravelPage.SEARCH_TXTBOX, TravelPage.CASTILLOS_WRT);
		m.click("xpath", TravelPage.SEARCH_BTN);
		m.validationTextMessage("id", TravelPage.MSN_TEXT_RESULT_NORESULT, "No results");

	} 

	@Test(priority = 4)
	public void SearchPortRoyalResult() throws Exception {
		m.clear(TravelPage.SEARCH_TXTBOX);
		m.click("xpath", TravelPage.SEARCH_BTN);
		m.input("xpath", TravelPage.SEARCH_TXTBOX, TravelPage.PORT_ROYAL_WRT);
		m.click("xpath", TravelPage.SEARCH_BTN);
		m.listCreate("id", TravelPage.RESULT_LIST); // if page change to element of each result
		m.printElement(TravelPage.RESULT_LIST);

	}

}
