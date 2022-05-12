package methods;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Methods {

	WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest test;
	public ExtentReports extent;
	public String caseID;

	public WebDriver launchBrowser(String browser, String url, boolean mode) {
		if (browser.equalsIgnoreCase("ch")) {
			String exePath = "./driver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", exePath);
			if (mode == true) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver(options);
			} else {
				driver = new ChromeDriver();
			}
		} else if (browser.equalsIgnoreCase("ff")) {
			String exePath = "./driver/geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", exePath);
			driver = new FirefoxDriver();

		}
		driver.manage().window().maximize();
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return driver;
	}

	public void close() {
		driver.quit();
	}

	public void input(String kind, String locator, String data) {
		if (kind.equalsIgnoreCase("cssselector")) {
			driver.findElement(By.cssSelector(locator)).sendKeys(data);
		} else if (kind.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator)).sendKeys(data);
		} else if (kind.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator)).sendKeys(data);
		} else if (kind.equalsIgnoreCase("name")) {
			driver.findElement(By.id(locator)).sendKeys(data);
		}

	}

	public void click(String kind, String locator) {
		if (kind.equalsIgnoreCase("cssselector")) {
			driver.findElement(By.cssSelector(locator)).click();
		} else if (kind.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator)).click();
		} else if (kind.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator)).click();
		} else if (kind.equalsIgnoreCase("linkText"))
			driver.findElement(By.xpath(locator)).click();
	}

	public void selectOptDropdown(String kind, String locator, int data) {
		if (kind.equalsIgnoreCase("xpath")) {
			Select objectSelect = new Select(driver.findElement(By.xpath(locator)));
			objectSelect.selectByIndex(data);
		} else if (kind.equalsIgnoreCase("id")) {
			Select objectSelect = new Select(driver.findElement(By.id(locator)));
			objectSelect.selectByIndex(data);
		}

	}

	public void validation(String kind, String locator, String data) {
		if (kind.equalsIgnoreCase("xpath")) {
			String NameVal = driver.findElement(By.xpath(locator)).getText();
			System.out.println(NameVal);
			Boolean msj = NameVal.contains(data);

			if (msj) {
				System.out.println("El nombre del representante de ventas es Nathan Elliot");

			} else {
				System.out.println("Error: El nombre es diferente, nombre actual es " + NameVal);
			}
		} else if (kind.equalsIgnoreCase("id")) {
			String NameVal = driver.findElement(By.id(locator)).getText();
			System.out.println(NameVal);
			Boolean msj = NameVal.contains(data);

			if (msj) {
				System.out.println("El nombre del representante de ventas es Nathan Elliot");

			} else {
				System.out.println("Error: El nombre es diferente, nombre actual es " + NameVal);
			}

		} else {

			System.out.println("Utiliza un xpath o id para localizar el elemento de validacion");

		}

	}

	public static void takeScreenshot(WebDriver webdriver, String testCaseName) {
		// toma el screenshot de la pagina sin formato
		File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		// Establecer ruta donde se guarda el screenshot
		String screenshotPath = "./test-output/ExecutionResults";

		try {
			FileHandler.createDir(new File(screenshotPath));
			FileHandler.copy(scrFile, new File(screenshotPath + File.separator + testCaseName + ".png"));

		} catch (IOException e) {
			System.out.println("El folder de screenshot no pudo ser creado");
			e.printStackTrace();

		}
	}

	public void initializeReport(String nameTest, String description,String docSave) {
		htmlReporter = new ExtentHtmlReporter("./reports/"+docSave+".html");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Created by Adrian and Angel");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		test = extent.createTest(nameTest, description);
	}

	// Takes Screenshot
	public String takeScreenShotBase64() {
		String file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return file;
	}

	// Returns screenshot with the failed step
	public void failedTest(String passMessage) throws IOException {
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String base64Info = takeScreenShotBase64();
		test.log(Status.FAIL, passMessage, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Info).build());
		extent.flush();
	}

	// Takes screenshot and attach to the report
	public void takeScreenShotAndAddToTheReport(String passMessage, Boolean stat) throws IOException {
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if (stat) {
			String base64Info = takeScreenShotBase64();
			test.log(Status.FAIL, passMessage, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Info).build());
			extent.flush();
			
		} else {
			String base64Info = takeScreenShotBase64();
			test.log(Status.PASS, passMessage, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Info).build());
			extent.flush();
			
			

		}
	}
		public void takeScreenShotVersionCorta(String passMessage, Boolean stat) throws IOException {
			//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			if (stat) {
				String base64Info = takeScreenShotBase64();
				test.log(Status.FAIL, passMessage, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Info).build());
				extent.flush();
				
			} else {
				String base64Info = takeScreenShotBase64();
				test.log(Status.PASS, passMessage, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Info).build());
				extent.flush();
			//	boolean popo= TestNG.getDefault().hasFailure();
			//	boolean popis= test.getStatus().equals(stat);
				

			}
	}

}
