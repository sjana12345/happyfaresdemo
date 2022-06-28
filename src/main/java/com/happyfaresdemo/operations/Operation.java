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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.happyfaresdemo.config.Reader;
import com.happyfaresdemo.config.Webdriverconfig;
import com.happyfaresdemo.entity.Testentity;
import com.happyfaresdemo.utility.Filefilter;

public class Operation {

	public RemoteWebDriver driver;
	WebElement wm;
	Duration timeout = Duration.ofSeconds(40);
	int count = 0;

	@Test(invocationCount = 1)
	public void perform() throws IOException, NumberFormatException, InterruptedException {
		File[] files = Filefilter.getfiles();
		Reader rs = new Reader();
		List<Testentity> l = rs.testlist(files[count].getName());

		for (Testentity tn : l) {
			switch (tn.getKeyword()) {
			case "CLICK":
				wm = driver.findElement(By.xpath(tn.getLocator()));
				wm.click();
				break;
			case "SETTEXT":
				wm = driver.findElement(By.xpath(tn.getLocator()));
				wm.sendKeys(tn.getTestdata());
				break;
			case "JS":
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				js.executeScript(tn.getTestdata());
				break;
			case "VERIFY":
				Assert.assertEquals(driver.findElement(By.xpath(tn.getLocator())).getText(), tn.getTestdata());
				break;
			case "ACTION":
				wm = driver.findElement(By.xpath(tn.getLocator()));
				Actions builder = new Actions(driver);
				builder.moveToElement(wm).click().perform();
				wm.sendKeys(Keys.DOWN);
				wm.sendKeys(Keys.DOWN);
				wm.sendKeys(Keys.ENTER);
				break;
			case "SOFTVERIFY":
				SoftAssert sf = new SoftAssert();
				sf.assertEquals(driver.findElement(By.xpath(tn.getLocator())).getText(), tn.getTestdata());
				break;
			case "WAIT":
				wm = driver.findElement(By.xpath(tn.getLocator()));
				WebDriverWait wait = new WebDriverWait(driver, timeout);
				wait.until(ExpectedConditions.visibilityOf(wm));
				break;
			case "OPEN":
				Webdriverconfig wdfg = new Webdriverconfig();
				if (tn.getPlatform().equalsIgnoreCase("chrome")) {
					driver = wdfg.Chromeconfig(tn.getTestdata());
				} else {
					System.out.println(tn.getPlatform() + " not available");
				}
				break;
			case "FIXWAIT":
				Thread.sleep(Integer.parseInt(tn.getTestdata()));
				break;
			}
		}
	}

	@AfterMethod
	public void endTest(ITestResult result) throws IOException {
		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				count++;
				driver.quit();
			} else if (result.getStatus() == ITestResult.FAILURE) {
				count++;
				driver.quit();
			} else if (result.getStatus() == ITestResult.SKIP) {
				count++;
				driver.quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
