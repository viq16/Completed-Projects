package tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import pageObject.MainObjects;
import pageObject.TablePaginationObjects;

public class TablePaginationTest {

	private RemoteWebDriver wb;
	private TablePaginationObjects rbp;
	private MainObjects mo;
	
	@Before
	public void setUp() throws Exception{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Erazer\\Desktop\\Selenium\\chromedriver.exe");
		wb = new ChromeDriver();
        wb.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mo = new MainObjects(wb);
        Thread.sleep(1000);
	}
	@Test
	public void downloadingTest() throws Exception{
		rbp = new TablePaginationObjects(wb);
		mo.openViaUrl(rbp.url);
		rbp.initElements(wb);//1
		Assert.assertEquals("active", rbp.getFirstButtonValidation().getAttribute("class"));
		mo.buttonClick(rbp.nextButtonClick()); //2
		Assert.assertEquals("active", rbp.getSecondButtonValidation().getAttribute("class"));
		Thread.sleep(1000);
		mo.buttonClick(rbp.nextButtonClick()); //3
		Assert.assertEquals("active", rbp.getThirdButtonValidation().getAttribute("class"));
		Thread.sleep(1000);
		mo.buttonClick(rbp.previousButtonClick());//2
		Assert.assertEquals("active", rbp.getSecondButtonValidation().getAttribute("class"));
		Thread.sleep(1000);
		mo.buttonClick(rbp.previousButtonClick());//1
		Assert.assertEquals("active", rbp.getFirstButtonValidation().getAttribute("class"));
		Thread.sleep(1000);
		mo.buttonClick(rbp.thirdButtonClick());//3
		Assert.assertEquals("active", rbp.getThirdButtonValidation().getAttribute("class"));
		Thread.sleep(1000);
		mo.buttonClick(rbp.secondButtonClick());//2
		Assert.assertEquals("active", rbp.getSecondButtonValidation().getAttribute("class"));
		Thread.sleep(1000);
		mo.buttonClick(rbp.firstButtonClick());//1
		Assert.assertEquals("active", rbp.getFirstButtonValidation().getAttribute("class"));
		
	}
	@After
    public void tearDown() throws Exception {
    	Thread.sleep(1000);
        wb.close();
        wb.quit();
    }
}
