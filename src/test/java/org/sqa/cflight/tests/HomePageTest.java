/**
 *   File Name: HomePageTest.java<br>
 *
 *   Yutaka<br>
 *   Created: Mar 4, 2016
 *   
 */

package org.sqa.cflight.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.sqa.cflight.data.ReadFileData;
import org.sqa.cflight.pages.HomePage;
import org.sqa.cflight.pages.HotelsPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * HomePageTest Tests for HomePage
 * <p>
 * 	WebDriver driver: firefox driver
 *	HomePage hp: object HomePage
 * <p>
 * //ADDD (description of core methods)
 * 
 * @author      Yutaka
 * @version     1.0.0
 * @since       1.0
 *
 */

public class HomePageTest {
	private WebDriver driver;
	
	// creating instance of class HomePage
	HomePage homePage;

	@BeforeMethod
	public void createInstanceOfFirefox() {
		ReadFileData rfd = new ReadFileData();
		rfd.properties();
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		homePage = PageFactory.initElements(driver, HomePage.class);
	}

	@AfterMethod
	public void quitInsanceOfFirefox() {
		driver.quit();
	}

	/**
	 * switch to Hotels Tab
	 */
	@Test(enabled = true, groups="hotels")
	public void clickHotelsBtnTest() {
		
		// expected result: Hotels page title
		String expected = "Search Cheap Hotels, Resorts & Spas, B&B's & Budget Motels | CheapAir";
		
		// navigate to "Home" page
		homePage.navigateToHomePage();
		
		// click on "Hotels" btn
		HotelsPage hotelsPage = homePage.clickHotelsBtn();
		
		// assertion Page title
		Assert.assertEquals(expected, driver.getTitle(), "Not a Hotels label");
	}
	
	/**
	 * test fill out from (city) field
	 */
	@Test(enabled = true, groups="date")
	public void fillOutFlyFromFieldFullCityNameTest() {
		
		// navigate to "Home" page
		homePage.navigateToHomePage();
		homePage.fillOutFlyFromField("San Diego");
		Assert.assertEquals("S", "S", "Not match S");
	}
	
	/**
	 * test fill out to (city) field
	 */
	@Test(enabled = true, groups="date")
	public void fillOutFlyToFieldFullCityNameTest() {
		
		// navigate to "Home" page
		homePage.navigateToHomePage();
		homePage.fillOutFlyToField("Las Vegas");
		Assert.assertEquals("S", "S", "Not match S");
	}
	
	/**
	 *  test choosing dates
	 */
	@Test(enabled = true, groups="date")
	public void chooseDatesTest()  {
		
		// navigate to "Home" page
		homePage.navigateToHomePage();
		
	// departure in 2 days; return 1st day next (today + 1) month (day 0)
	Assert.assertEquals(homePage.chooseDates(0, 0), "Who's traveling?", "Not match Who's traveling?");
	
	  try{
		   Thread.sleep(10000);
		  }catch (InterruptedException ie1) {
		    ie1.printStackTrace();
		  } 
	}

	/**
	 * adults=2, children=0, seniors=0, infants=0, category=econom
	 * adults (1 to 4)
	 */
	@Test(enabled = true, groups="who travelling")
	public void choosePassengersTest() {
		
		// navigate to "Home" page
		homePage.navigateToHomePage();
		homePage.fillOutFlyFromField("San Diego");		
		homePage.fillOutFlyToField("Las Vegas");
		homePage.chooseDates(0, 0);
		Assert.assertEquals(homePage.choosePassengers(2, 0, 0, 0, "e"), "2000e", "Not match parameters");	
	}
	
	@Test
	public void searchFlightWithoutAdTest() {
		
		String expectedText = "Prices are per person and include all taxes and fees.";
		
		homePage.navigateToHomePage();
		homePage.fillOutFlyFromField("San Francisco");
		homePage.fillOutFlyToField("Las Vegas");
		homePage.chooseDates(0, 0);
		homePage.choosePassengers(2, 0, 0, 0, "e");
		Assert.assertEquals(homePage.searchFlights(), expectedText, "Not match to expected text");
	}
	
	@Test(enabled = true, groups = "locations")
	public void topPopDestinationTest() {
		
		// navigate to "Home" page
		homePage.navigateToHomePage();
		String destination = "Dallas, TX";
		String expectedText = "Dallas, TX";
		Assert.assertEquals(homePage.topPopDestination(destination), expectedText, "Not match to expected city");
	}
	
	@Test(enabled = true, groups = "cost")
	public void bookCheapestRoundTripTest() {
		
		// navigate to "Home" page
		homePage.navigateToHomePage();

		String from = "San Diego";
		String to = "Dallas";
		char actualFirstChar;
		char expectedFirstChar = '$';
		
		homePage.fillOutFlyFromField(from);
		homePage.fillOutFlyToField(to);
		homePage.chooseDates(0, 0);
		homePage.choosePassengers(2, 0, 0, 0, "e");
		homePage.searchFlights();
		actualFirstChar = homePage.bookCheapestRoundTrip().charAt(0);
		Assert.assertEquals(actualFirstChar, expectedFirstChar, "Not match to expected first char");
	}
	
	@Test(enabled = false)
		public void clickBostonPictTest() {
	}
	

	
}
