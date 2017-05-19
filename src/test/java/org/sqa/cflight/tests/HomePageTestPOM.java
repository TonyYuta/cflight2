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
import org.sqa.cflight.pages.HomePagePOM;
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

public class HomePageTestPOM {
	
	WebDriver driver ;
	
	//= new FirefoxDriver();
	HomePagePOM hp;
	String homePageUrl;
	
	/**
	 * before method methods
	 */
	@BeforeMethod
	public void startSeleniumWithWebDriver() {
		driver = new FirefoxDriver();
//		driver = new ChromeDriver();
		//driver = new PhantomJSDriver();
		driver.manage().window().maximize();
		hp = new HomePagePOM(driver);
		homePageUrl = "https://www.cheapair.com";
		driver.get(homePageUrl);
	}
	
	/**
	 * after method methods
	 */
	@AfterMethod
	public void stopSeleniumWithWebDriver() {
		driver.close();
	}

	/**
	 * switch to Hotels Tab
	 */
	@Test(enabled = false, groups="hotels")
	public void clickHotelssBtnTest() {
		Assert.assertEquals("Hotels", hp.clickHotelsBtn(driver, homePageUrl), "Not a Hotels label");
	}
	
	/**
	 * test fill out from (city) field
	 */
	@Test(enabled = true, groups="date")
	public void fillOutFlyFromFieldFullCityNameTest() {
		hp.fillOutFlyFromField(driver, "San Diego");
		Assert.assertEquals("S", "S", "Not match S");
	}
	
	/**
	 * test fill out to (city) field
	 */
	@Test(enabled = true, groups="date")
	public void fillOutFlyToFieldFullCityNameTest() {
		hp.fillOutFlyToField(driver, "Las Vegas");
		Assert.assertEquals("S", "S", "Not match S");
	}
	
	/**
	 *  test choosing dates
	 */
	@Test(enabled = true, groups="date")
	public void chooseDatesTest()  {
		
	// departure in 2 days; return 1st day next (today + 1) month (day 0)
	Assert.assertEquals(hp.chooseDates(driver, 1, 0), "Who's traveling?", "Not match Who's traveling?");
	
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
		hp.fillOutFlyFromField(driver, "San Diego");
		hp.fillOutFlyToField(driver, "Las Vegas");
		hp.chooseDates(driver, 1, 0);
		Assert.assertEquals(hp.choosePassengers(driver, 2, 0, 0, 0, "e"), "2000e", "Not match parameters");	
	}
	
	@Test
	public void searchFlightWithoutAdTest() {
		String expectedText = "Prices are per person and include all taxes and fees.";
		hp.fillOutFlyFromField(driver, "San Francisco");
		hp.fillOutFlyToField(driver, "Las Vegas");
		hp.chooseDates(driver, 1, 0);
		hp.choosePassengers(driver, 2, 0, 0, 0, "e");
		Assert.assertEquals(hp.searchFlights(driver), expectedText, "Not match to expected text");
	}
	
	@Test(enabled = true, groups = "locations")
	public void topPopDestinationTest() {
		String destination = "Dallas, TX";
		String expectedText = "Dallas, TX";
		Assert.assertEquals(hp.topPopDestination(driver, destination), expectedText, "Not match to expected city");
	}
	
	@Test(enabled = true, groups = "cost")
	public void bookCheapestRoundTripTest() {
		String from = "San Diego";
		String to = "Dallas";
		char actualFirstChar;
		char expectedFirstChar = '$';
		
		hp.fillOutFlyFromField(driver, from);
		hp.fillOutFlyToField(driver, to);
		hp.chooseDates(driver, 1, 0);
		hp.choosePassengers(driver, 2, 0, 0, 0, "e");
		hp.searchFlights(driver);
		actualFirstChar = hp.bookCheapestRoundTrip(driver).charAt(0);
		Assert.assertEquals(actualFirstChar, expectedFirstChar, "Not match to expected first char");
	}
	
	@Test(enabled = false)
	public void clickBostonPictTest() {
		
		//String label =	hp.clickBostonPict();
        //System.out.println(label);
       // Assert.assertEquals("https://www.cheapair.com/hotels/", driver.getTitle(), "Hotels");
	}
	
}
