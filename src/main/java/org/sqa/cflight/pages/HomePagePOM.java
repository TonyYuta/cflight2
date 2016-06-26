/**
 *   File Name: HomePage.java<br>
 *
 *   Yutaka<br>
 *   Created: Mar 4, 2016
 *   
 */

package org.sqa.cflight.pages;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * HomePage //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 * 
 * @author      Yutaka
 * @version     1.0.0
 * @since       1.0
 *
 */
public class HomePagePOM {
	
	// Main Page methods
	
	/*@FindBy(id = "idx-0-0-Price")
	//@CacheLookup
	private WebElement foptPriceDeparture;
	*/
	
	@FindBy(css = "#idx-0-0-Price")
	//@CacheLookup
	private WebElement foptPriceDeparture;
	
	@FindBy(id = "idx-1-0-Price")
	private WebElement foptPriceReturn;
	
	
	public HomePagePOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * @param driver
	 * @param url
	 * @return
	 */
	public String clickHotelsBtn(WebDriver driver, String url) {
		// Set implicit wait to 3 seconds
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(url);
		WebElement hotelsBtn = driver.findElement(By.cssSelector("a[href='/hotels/']"));
		hotelsBtn.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement hotelsLabel = driver.findElement(By.className("main-content-hdr"));
		String label = "l";
		label = hotelsLabel.getText();
		return label;
	}
	
	/**
	 * @param driver
	 * @param url
	 */
	public void clickBostonPict(WebDriver driver, String url) {
		// Set implicit wait to 3 seconds
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	//	driver.get(url);
		WebElement bostonPict = driver.findElement(By.cssSelector(".tile.BOS"));
		bostonPict.click();
	}
	
	/**
	 * @param driver
	 * @param url
	 * @param cityFrom
	 */
	public void fillOutFlyFromField(WebDriver driver, String cityFrom) {
		// Set implicit wait to 3 seconds
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement fromField = driver.findElement(By.id("from1"));
		fromField.click();
		fromField.clear();
		fromField.sendKeys(cityFrom);
	}
	
	/**
	 * @param driver
	 * @param url
	 * @param cityTo
	 */
	public void fillOutFlyToField(WebDriver driver, String cityTo) {
		// Set implicit wait to 3 seconds
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement toField = driver.findElement(By.id("to1"));
		toField.click();
		toField.clear();
		toField.sendKeys(cityTo);
	}

	/**
	 * @return dayOfMonthNumber : today day in current month
	 */
	public int todayDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		int currentDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		return currentDayOfMonth;
	}
	
	/**
	 * @return quantity days in current month
	 */
	public int qtyDaysInCurrentMonth() {	
	Calendar cal = Calendar.getInstance();
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @param driver
	 * @param departDate : today + departDate + 1
	 * @param returnDate : returnDate + 1
	 * @return
	 */
	public String chooseDates(WebDriver driver, int departDate, int returnDate) {
		// Set implicit wait to 3 seconds
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebElement departField = driver.findElement(By.cssSelector(".depart-date>.placeholder"));
		departField.click();
		// Set implicit wait to 1 seconds
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);	
		WebDriverWait wait = new WebDriverWait(driver, 6);
		List <WebElement> departDates = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ui-datepicker-group.ui-datepicker-group-first [data-handler='selectDay']>.ui-state-default")));
//		for (int i = 0; i < departDates.size(); i++) {
//			System.out.println(i+1 + ": " + departDates.get(i).getText());
//		}
		departDates.get(departDate).click();
		//System.out.println("departDates.get(departDateFromEndOfMonth).click() = " + departDates.get(departDate));
		
		List <WebElement> returnDates = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ui-datepicker-group.ui-datepicker-group-last [data-handler='selectDay']>.ui-state-default")));
		returnDates.get(returnDate).click();
		
		String whoIsTravelingTab = "_";
		WebElement whoIsTravelingTabLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wizardInstructionsContainer")));
		whoIsTravelingTabLabel.getText();
		return whoIsTravelingTabLabel.getText();
	}
	
	public String choosePassengers(WebDriver driver, int adult, int children, int seniors, int infants, String category) {
		String qty = "";
		// Set implicit wait to 3 seconds
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 6);
		
		if (adult == 2) {
			WebElement adultPlusBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-model='travelers.adults'][data-action='increase']")));
			adultPlusBtn.click();
			WebElement qtyAdults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".trav-select.first [value='2']")));
			String qtyAdultsString = qtyAdults.toString();
			qty = (qtyAdultsString.substring(116, qtyAdultsString.length() - 3));
		}
		
		if (adult == 3) {
			WebElement adultPlusBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-model='travelers.adults'][data-action='increase']")));
			adultPlusBtn.click();
			
			try {
				   Thread.sleep(1000);
				} catch (InterruptedException e) {
				 e.printStackTrace();
				}
			WebElement adultPlusBtnMore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-model='travelers.adults'][data-action='increase']")));		
			adultPlusBtnMore.click();	
			WebElement qtyAdults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".trav-select.first [value='3']")));
			String qtyAdultsString = qtyAdults.toString();
			qty = (qtyAdultsString.substring(116, qtyAdultsString.length() - 3));
		}
		
		if (adult == 4) {
			WebElement adultPlusBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-model='travelers.adults'][data-action='increase']")));
			adultPlusBtn.click();
			
			try {
				   Thread.sleep(1000);
				} catch (InterruptedException e) {
				 e.printStackTrace();
				}
			
			WebElement adultPlusBtnMore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-model='travelers.adults'][data-action='increase']")));		
			adultPlusBtnMore.click();	
			
			try {
				   Thread.sleep(1000);
				} catch (InterruptedException e) {
				 e.printStackTrace();
				}
			
			WebElement adultPlusBtnMoreMore = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-model='travelers.adults'][data-action='increase']")));		
			adultPlusBtnMoreMore.click();		
			
			WebElement qtyAdults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".trav-select.first [value='4']")));
			String qtyAdultsString = qtyAdults.toString();
			qty = (qtyAdultsString.substring(116, qtyAdultsString.length() - 3));
		}

		qty += "000e"; // adults=?, children=0, seniors=0, infants=0
		System.out.println("---------------------------------------------------------------");
		System.out.println("qty = " + qty);
		System.out.println("---------------------------------------------------------------");
		
		
		WebElement searchFlightsBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.large.block")));
		searchFlightsBtn.click();
		
		try {
			   Thread.sleep(3000);
			} catch (InterruptedException e) {
			 e.printStackTrace();
			}
		
		return qty;
	}
	
	public String searchFlights(WebDriver driver) {
		String searchResults = "";
		
		// Set implicit wait to 3 seconds
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 6);
		
		WebElement adCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("adCheckbox")));
		adCheckbox.click();
		
		WebElement closeActMdlBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".icn.icn-delete")));
		closeActMdlBtn.click();

		WebElement fltHeaderLegaleseIncludes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("flt-header-legalese-includes")));
		searchResults = fltHeaderLegaleseIncludes.getText().toString();
		System.out.println("searchResults: " + searchResults);
		return searchResults;
	}
	
	/**
	 * @param driver
	 * @param city Dallas only
	 * @return
	 */
	public String topPopDestination(WebDriver driver, String city) {

		// Set implicit wait to 3 seconds
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 6);
		
		WebElement dallasPict  = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tile.DFW")));
		dallasPict.click();
		
		WebElement labelTo1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("to1")));
		return labelTo1.getAttribute("value");
	}
	
	//public String bookCheapestRoundTrip(WebDriver driver) {
	public String bookCheapestRoundTrip(WebDriver driver) {
		

		String priceDetails = "";
		
		// Set implicit wait to 3 seconds
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 8);
		
		
//		WebElement foptPriceDeparture = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idx-0-0-Price")));
//		foptPriceDeparture.click();

		wait.until(ExpectedConditions.visibilityOf(foptPriceDeparture));	
		foptPriceDeparture.click();
		
		//WebElement foptPriceReturn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idx-1-0-Price")));
		wait.until(ExpectedConditions.visibilityOf(foptPriceReturn));
		foptPriceReturn.click();

		WebElement priceSummBeforeClickOnBookBtnLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("priceSumm")));
		priceSummBeforeClickOnBookBtnLabel.getText();
		
		WebElement bookBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bookBtn")));
		bookBtn.click();
		
		WebElement totalPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tbl-lite-ftr.last")));
		priceDetails = totalPrice.getText();
		return priceDetails;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//HomePage hp = new HomePage();
		//hp.clickHotelsBtn();
	}

}
