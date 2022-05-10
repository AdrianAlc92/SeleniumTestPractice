package pageObjects;

public class DirectoryPageObject {
	public static final String DIRECTORY_TAB = "menu_directory_viewDirectory";
	public static final String NAME_TXBOX = "searchDirectory_emp_name_empName";
	public static final String JOB_DRDWN = "searchDirectory_job_title";
	public static final String LOCATION_DRDWN = "//option[contains(text(),'United States')]";
	public static final String LOCATION_DRDWN2 = "//select[@id='searchDirectory_location']";
	public static final String SEARCH_BUTTON = "searchBtn";
	public static final String NAME_RESULT = "//*[@id='resultTable']//b[contains(text(),'Nathan Elliot')]";
	public static final String NAME_RESULT2 = "//*[@id='resultTable']//b[contains(text(),'Luke Wright')]";
	
	
	public static final String NAME_SEARCH = "Nathan";
	public static final String NATHAN_NAME_VALID = "Nathan Elliot";

}
