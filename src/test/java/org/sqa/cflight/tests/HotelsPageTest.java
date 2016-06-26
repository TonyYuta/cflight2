package org.sqa.cflight.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.sqa.cflight.data.ReadFileData;
import org.sqa.cflight.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HotelsPageTest {
	private WebDriver driver;
	// creating instance of class HomePage
	HotelsPage hotelsPage;

	@BeforeMethod
	public void createInstanceOfFirefox() {
		ReadFileData rfd = new ReadFileData();
		rfd.properties();
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		hotelsPage = PageFactory.initElements(driver, HotelsPage.class);
	}

	@AfterMethod
	public void quitInsanceOfFirefox() {
		driver.quit();
	}

	@Test(enabled = true, groups = "hotels")
	public void fillOutcityAddressOrHotelFieldTest() {
		// navigate to "Hotels" page
		hotelsPage.navigateToHotelsPage();
		hotelsPage.fillOutcityAddressOrHotelField("San Diego");
		Assert.assertEquals(hotelsPage.fillOutcityAddressOrHotelField("San Diego"), "Which dates?", "Not match S");	
	}

	/**
	 * choose dates check in & check out
	 */
	@Test(enabled = true, groups = "date")
	public void chooseDatesTest() {
		
		String expected = "How many rooms and who's staying?";
	  
		// navigate to "Hotels" page
		hotelsPage.navigateToHotelsPage();
		hotelsPage.fillOutcityAddressOrHotelField("San Diego");

		// departure in 2 days; return 1st day next (today + 1) month (day 0)
		hotelsPage.chooseDates(0, 0);
		
		Assert.assertEquals(hotelsPage.howManyRoomsAndWhoIsStayingTab(), expected, "Not match to a label: How many rooms and who's staying?"); 
  }

	@Test(enabled = true, groups= "hotels")
	public void searchHotelTest() throws InterruptedException {
		String locationInput = "San Diego";
		String locationLabelExpected = "San Diego, CA";
		
		hotelsPage.navigateToHotelsPage();
		hotelsPage.fillOutcityAddressOrHotelField(locationInput);
		// departure in 2 days; return 1st day next (today + 1) month (day 0)
		
		hotelsPage.chooseDates(0, 0);
		hotelsPage.searchHotel();
		Assert.assertEquals(hotelsPage.locationLabel(), locationLabelExpected, "Not match to a label: " + hotelsPage.locationLabel());	
	}
}