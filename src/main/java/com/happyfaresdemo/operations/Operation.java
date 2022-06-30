package com.happyfaresdemo.operations;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IAlterTestName;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.happyfaresdemo.config.Reader;
import com.happyfaresdemo.config.Webdriverconfig;
import com.happyfaresdemo.entity.Testentity;
import com.happyfaresdemo.utility.Filefilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Operation {

	public RemoteWebDriver driver;
	WebElement wm;
	Duration timeout = Duration.ofSeconds(40);
	int count = 0;

	@Test(invocationCount = 1)
	public void perform() throws IOException, NumberFormatException, InterruptedException {
		
		Logger logger = LoggerFactory.getLogger("Happy Fares");
		File[] files = Filefilter.getfiles();
		Reader rs = new Reader();
		List<Testentity> l = rs.testlist(files[count].getName());
		logger.info("Executing "+files[count].getName()+" .........................................");
		Reporter.log("Executing "+files[count].getName()+" .........................................");
		for (Testentity tn : l) {
			switch (tn.getKeyword()) {
			case "CLICK":
				logger.info(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				if(tn.getLocatortype().equalsIgnoreCase("xpath")) {
					wm = driver.findElement(By.xpath(tn.getLocator()));
				}else if(tn.getLocatortype().equalsIgnoreCase("linkText")) {
					wm = driver.findElement(By.linkText(tn.getLocator()));
				}else if(tn.getLocatortype().equalsIgnoreCase("id")) {
					wm = driver.findElement(By.id(tn.getLocator()));
				}
				wm.click();
				Reporter.log(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				break;
			case "SETTEXT":
				logger.info(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				if(tn.getLocatortype().equalsIgnoreCase("xpath")) {
					wm = driver.findElement(By.xpath(tn.getLocator()));
				}else if(tn.getLocatortype().equalsIgnoreCase("linkText")) {
					wm = driver.findElement(By.linkText(tn.getLocator()));
				}else if(tn.getLocatortype().equalsIgnoreCase("id")) {
					wm = driver.findElement(By.linkText(tn.getLocator()));
				}
				wm.clear();
				wm.sendKeys(tn.getTestdata());
				Reporter.log(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				break;
			case "JS":
				logger.info(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				js.executeScript(tn.getTestdata());
				Reporter.log(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				break;
			case "VALIDATION-VERIFY":
				logger.info(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				if(tn.getLocatortype().equalsIgnoreCase("xpath")) {
					Assert.assertEquals(driver.findElement(By.xpath(tn.getLocator())).getAttribute("validationMessage"), tn.getTestdata());
				}else if(tn.getLocatortype().equalsIgnoreCase("linkText")) {
					Assert.assertEquals(driver.findElement(By.linkText(tn.getLocator())).getAttribute("validationMessage"), tn.getTestdata());
				}else if(tn.getLocatortype().equalsIgnoreCase("id")) {
					Assert.assertEquals(driver.findElement(By.id(tn.getLocator())).getAttribute("validationMessage"), tn.getTestdata());
					System.out.println(driver.findElement(By.id(tn.getLocator())).getAttribute("validationMessage"));
				}
				Reporter.log(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				break;
			case "VERIFY":
				logger.info(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				if(tn.getLocatortype().equalsIgnoreCase("xpath")) {
					Assert.assertEquals(driver.findElement(By.xpath(tn.getLocator())).getText(), tn.getTestdata());
				}else if(tn.getLocatortype().equalsIgnoreCase("linkText")) {
					Assert.assertEquals(driver.findElement(By.linkText(tn.getLocator())).getText(), tn.getTestdata());
				}else if(tn.getLocatortype().equalsIgnoreCase("id")) {
					Assert.assertEquals(driver.findElement(By.id(tn.getLocator())).getText(), tn.getTestdata());
				}
				Reporter.log(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				break;
			case "SELECT":
				logger.info(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				if(tn.getLocatortype().equalsIgnoreCase("xpath")) {
					new Select(driver.findElement(By.xpath(tn.getLocator()))).selectByVisibleText(tn.getTestdata());
				}else if(tn.getLocatortype().equalsIgnoreCase("linkText")) {
					new Select(driver.findElement(By.linkText(tn.getLocator()))).selectByVisibleText(tn.getTestdata());
				}else if(tn.getLocatortype().equalsIgnoreCase("id")) {
					new Select(driver.findElement(By.id(tn.getLocator()))).selectByVisibleText(tn.getTestdata());
				}
				Reporter.log(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				break;
			case "ACTION":
				logger.info(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				wm = driver.findElement(By.xpath(tn.getLocator()));
				Actions builder = new Actions(driver);
				builder.moveToElement(wm).click().perform();
				wm.sendKeys(Keys.DOWN);
				wm.sendKeys(Keys.DOWN);
				wm.sendKeys(Keys.ENTER);
				Reporter.log(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				break;
			case "SOFTVERIFY":
				logger.info(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				SoftAssert sf = new SoftAssert();
				if(tn.getLocatortype().equalsIgnoreCase("xpath")) {
					sf.assertEquals(driver.findElement(By.xpath(tn.getLocator())).getText(), tn.getTestdata());
				}else if(tn.getLocatortype().equalsIgnoreCase("linkText")) {
					sf.assertEquals(driver.findElement(By.linkText(tn.getLocator())).getText(), tn.getTestdata());
				}else if(tn.getLocatortype().equalsIgnoreCase("id")) {
					sf.assertEquals(driver.findElement(By.id(tn.getLocator())).getText(), tn.getTestdata());
				}
				Reporter.log(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				break;
			case "WAIT":
				logger.info(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				if(tn.getLocatortype().equalsIgnoreCase("xpath")) {
					wm = driver.findElement(By.xpath(tn.getLocator()));
				}else if(tn.getLocatortype().equalsIgnoreCase("linkText")) {
					wm = driver.findElement(By.linkText(tn.getLocator()));
				}else if(tn.getLocatortype().equalsIgnoreCase("id")) {
					wm = driver.findElement(By.id(tn.getLocator()));
				}				
				WebDriverWait wait = new WebDriverWait(driver, timeout);
				wait.until(ExpectedConditions.visibilityOf(wm));
				Reporter.log(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				break;
			case "OPEN":
				logger.info(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				Webdriverconfig wdfg = new Webdriverconfig();
				if (tn.getPlatform().equalsIgnoreCase("chrome")) {
					driver = wdfg.Chromeconfig(tn.getTestdata());
				} else {
					System.out.println(tn.getPlatform() + " not available");
				}
				Reporter.log(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				break;
			case "FIXWAIT":
				logger.info(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				Thread.sleep(Integer.parseInt(tn.getTestdata()));
				Reporter.log(tn.getCaseId()+"-->"+tn.getScenario()+"--"+tn.getDescription());
				break;
			}
		}
	}

	@AfterMethod
	public void endTest(ITestResult result) throws IOException {
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				count++;
				Reporter.setCurrentTestResult(result);
				Reporter.log("Testcase Passed");
				driver.quit();
			} else if (result.getStatus() == ITestResult.FAILURE) {
				count++;
				Reporter.setCurrentTestResult(result);
				Reporter.log("Testcase Failed");
				driver.quit();
			} else if (result.getStatus() == ITestResult.SKIP) {
				count++;
				Reporter.setCurrentTestResult(result);
				Reporter.log("Testcase Skipped");
				driver.quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
