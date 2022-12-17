package methods;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Objects.ProjectConfigObj;
import net.bytebuddy.build.Plugin.Engine.Source.Empty;

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

	public void quit() {
		driver.quit();
	}

	public void close() {
		driver.close();
	}
	public void clear(String locator) {
		driver.findElement(By.xpath(locator)).clear();
	}

	public void input(String kind, String locator, String data) {
		if (kind.equalsIgnoreCase("cssselector")) {
			driver.findElement(By.cssSelector(locator)).sendKeys(data);
		} else if (kind.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator)).sendKeys(data);
		} else if (kind.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator)).sendKeys(data);
		} else if (kind.equalsIgnoreCase("name")) {
			driver.findElement(By.name(locator)).sendKeys(data);
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

	public void submit(String kind, String locator) {
		if (kind.equalsIgnoreCase("cssselector")) {
			driver.findElement(By.cssSelector(locator)).submit();
		} else if (kind.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(locator)).submit();
		} else if (kind.equalsIgnoreCase("id")) {
			driver.findElement(By.id(locator)).submit();
		} else if (kind.equalsIgnoreCase("linkText")) {
			driver.findElement(By.linkText(locator)).submit();
		} else if (kind.equalsIgnoreCase("classname"))
			driver.findElement(By.className(locator)).submit();

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

	public void validationVisible(String kind, String locator) {
		boolean present;
		if (kind.equalsIgnoreCase("xpath")) {
			try {
				driver.findElement(By.xpath(locator)).isDisplayed();
				present = true;
				System.out.println("Element is visible");
			}

			catch (NoSuchElementException e) {
				present = false;
				System.out.println("Element not visible");
			}

			if (kind.equalsIgnoreCase("id")) {
				try {
					driver.findElement(By.xpath(locator)).isDisplayed();
					present = true;
					System.out.println("Element is visible");
				}

				catch (NoSuchElementException e) {
					present = false;
					System.out.println("Element not visible");
				}
			}
		}
	}
	public void validationIsEmpty(String kind, String locator) {
		if (kind.equalsIgnoreCase("xpath")) {
			try {
				 boolean Empty = driver.findElement(By.xpath(locator)).getText().isEmpty();
		
				System.out.println("Element is empty");
			}
			
			catch (NoSuchElementException e) {
				System.out.println("Element not empty");
			}
			
			if (kind.equalsIgnoreCase("id")) {
				try {
					boolean Empty2 = driver.findElement(By.xpath(locator)).getText().isEmpty();
					System.out.println("Element is empty");
				}
				
				catch (NoSuchElementException e) {
					System.out.println("Element not empty");
				}
			}
		}
	}
	public void validationTextMessage(String kind, String locator, String data) {
		if (kind.equalsIgnoreCase("xpath")) {
			String NameVal = driver.findElement(By.xpath(locator)).getText();
			System.out.println(NameVal);
			Boolean msj = NameVal.contains(data);

			if (msj) {
				System.out.println("Element is located");

			} else {
				System.out.println("Element isn´t located");
			}
		} else if (kind.equalsIgnoreCase("id")) {
			String NameVal = driver.findElement(By.id(locator)).getText();
			System.out.println(NameVal);
			Boolean msj = NameVal.contains(data);

			if (msj) {
				System.out.println("Element is located");

			} else {
				System.out.println("Element isn´t located");
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

	public void initializeReport(String nameTest, String description, String docSave) {
		htmlReporter = new ExtentHtmlReporter("./reports/" + docSave + ".html");
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
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String base64Info = takeScreenShotBase64();
		test.log(Status.FAIL, passMessage, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Info).build());
		extent.flush();
	}

	// Takes screenshot and attach to the report
	public void takeScreenShotAndAddToTheReport(String passMessage, Boolean stat) throws IOException {
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if (stat) {
			String base64Info = takeScreenShotBase64();
			test.log(Status.FAIL, passMessage,
					MediaEntityBuilder.createScreenCaptureFromBase64String(base64Info).build());
			extent.flush();

		} else {
			String base64Info = takeScreenShotBase64();
			test.log(Status.PASS, passMessage,
					MediaEntityBuilder.createScreenCaptureFromBase64String(base64Info).build());
			extent.flush();

		}
	}

	public void takeScreenShotVersionCorta(String passMessage, Boolean stat) throws IOException {
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if (stat) {
			String base64Info = takeScreenShotBase64();
			test.log(Status.FAIL, passMessage,
					MediaEntityBuilder.createScreenCaptureFromBase64String(base64Info).build());
			extent.flush();

		} else {
			String base64Info = takeScreenShotBase64();
			test.log(Status.PASS, passMessage,
					MediaEntityBuilder.createScreenCaptureFromBase64String(base64Info).build());
			extent.flush();
			// boolean popo= TestNG.getDefault().hasFailure();
			// boolean popis= test.getStatus().equals(stat);

		}
	}

	public void listCreate(String kind, String locator) {
		if (kind.equalsIgnoreCase("cssselector")) {
			List<WebElement> allsites = driver.findElements(By.cssSelector(locator));
			System.out.println("The size of the page is = " + allsites.size());
			for (WebElement item : allsites) {
				System.out.println("Name of the site is : " + item.getText());
				System.out.println("Title of the Next Page is : " + driver.getTitle());
			}
		} else if (kind.equalsIgnoreCase("xpath")) {
			List<WebElement> allsites = driver.findElements(By.xpath(locator));
			System.out.println("The size of the page is = " + allsites.size());
			for (WebElement item : allsites) {
				System.out.println("Name of the site is : " + item.getText());
				System.out.println("Title of the Next Page is : " + driver.getTitle());
			}
			allsites.get(0).click();
		} else if (kind.equalsIgnoreCase("id")) {
			List<WebElement> allsites = driver.findElements(By.id(locator));
			System.out.println("The size of the page is = " + allsites.size());
			for (WebElement item : allsites) {
				System.out.println("Name of the site is : " + item.getText());
				System.out.println("Title of the Next Page is : " + driver.getTitle());
			}
		} else if (kind.equalsIgnoreCase("linkText")) {
			List<WebElement> allsites = driver.findElements(By.linkText(locator));
			System.out.println("The size of the page is = " + allsites.size());
			for (WebElement item : allsites) {
				System.out.println("Name of the site is : " + item.getText());
				System.out.println("Title of the Next Page is : " + driver.getTitle());
			}
		}
	}
	
	public void printElement (String locator) {
		String NameLocate = driver.findElement(By.xpath(locator)).getText();
		System.out.println(NameLocate);
		
	}

	@SuppressWarnings("deprecation")
	public void explicitWait( String kind,String locator) {
		if (kind.equalsIgnoreCase("xpath")) {
			WebDriverWait wait = new WebDriverWait(driver, ProjectConfigObj.WAITING_TIME);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		} else if (kind.equalsIgnoreCase("id")) {
			WebDriverWait wait = new WebDriverWait(driver, ProjectConfigObj.WAITING_TIME);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
		}
	}
	
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void backPage() {
		driver.navigate().back();

	}

	// Switches to Popup Window, you need to pass (button that open the popup window
	// / and the name of the popup window)
	public void switchWindow(String button, String windowName) throws InterruptedException {
		driver.findElement(By.xpath(button)).click();
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.name(windowName)));
		Thread.sleep(2000);
	}

}
