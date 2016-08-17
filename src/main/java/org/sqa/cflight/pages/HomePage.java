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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sqa.cflight.data.ReadFileData;

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
public class HomePage {
	
	// Main Page methods using Page factory
	
	@FindBy(css = "#idx-0-0-Price")
	//@CacheLookup
	private WebElement foptPriceDeparture;
	
	@FindBy(id = "idx-1-0-Price")
	private WebElement foptPriceReturn;
	
	@FindBy(css = "a[href='/hotels/'")
	private WebElement hotelsBtn;
	
	@FindBy(id = "to1")
	private	WebElement toField;
	
	@FindBy(css = ".depart-date>.placeholder")
	private WebElement departField;
	
	@FindBy(css = ".tile.BOS")
	private WebElement bostonPict;
	
	@FindBy(id = "from1")
	private WebElement fromField;	
	
	@FindBy(css = ".ui-datepicker-group.ui-datepicker-group-last [data-handler='selectDay']>.ui-state-default")
	private List <WebElement> returnDates;
	
	@FindBy(css = ".ui-datepicker-group.ui-datepicker-group-first [data-handler='selectDay']>.ui-state-default")
	private List <WebElement> departDates;
	
	@FindBy(id = "wizardInstructionsContainer")
	private WebElement whoIsTravelingTabLabel;
	
	@FindBy(css = "[data-model='travelers.adults'][data-action='increase']")
	private WebElement adultPlusBtn;
	
	@FindBy(css = ".trav-select.first [value='2']")
	private WebElement qtyAdults;
	
	@FindBy(css = "[data-model='travelers.adults'][data-action='increase']")
	private WebElement adultPlusBtnMore;
	
	@FindBy(css = ".trav-select.first [value='3']")
	private WebElement qtyAdults3;
	
	@FindBy(css = "[data-model='travelers.adults'][data-action='increase']")
	private WebElement adultPlusBtnMoreMore;
	
	@FindBy(css = ".trav-select.first [value='4']")
	private WebElement qtyAdults4;
	
	@FindBy(css = ".btn.large.block")
	private WebElement searchFlightsBtn;
	
	@FindBy(id = "adCheckbox")
	private WebElement adCheckbox;
	
	@FindBy(css = ".icn.icn-delete")
	private WebElement closeActMdlBtn;
	
	@FindBy(className = "flt-header-legalese-includes")
	private WebElement fltHeaderLegaleseIncludes;
	
	@FindBy(css = ".tile.DFW")
	private WebElement dallasPict;
	
	@FindBy(id = "to1")
	private WebElement labelTo1;
	
	@FindBy(id = "priceSumm")
	private WebElement priceSummBeforeClickOnBookBtnLabel;
	
	@FindBy(id = "bookBtn")
	private WebElement bookBtn;
	
	@FindBy(css = ".tbl-lite-ftr.last")
	private WebElement totalPrice;
	
			
	private WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePage navigateToHomePage(){
		driver.get(ReadFileData.HOME_PAGE_URL);	
		return this;
	}
	
	/**
	 * click on "Hotels" btn
	 * 
	 * @return HotelsPage page object
	 */
	public HotelsPage clickHotelsBtn() {
		hotelsBtn.click();
		return PageFactory.initElements(driver, HotelsPage.class);
	}
	
	/**
	 * @param driver
	 * @param url
	 */
	public void clickBostonPict(String url) {

		WebDriverWait wait = new WebDriverWait(driver, 8);	
		wait.until(ExpectedConditions.visibilityOf(bostonPict));
		bostonPict.click();
	}
	
	/**
	 * @param cityFrom
	 */
		public void fillOutFlyFromField(String cityFrom) {

		WebDriverWait wait = new WebDriverWait(driver, 8);	
		wait.until(ExpectedConditions.visibilityOf(fromField));
		fromField.click();
		fromField.clear();
		fromField.sendKeys(cityFrom);
	}
	
	/**
	 * @param cityTo
	 */
	public void fillOutFlyToField(String cityTo) {
		
		WebDriverWait wait = new WebDriverWait(driver, 8);			
		wait.until(ExpectedConditions.visibilityOf(toField));
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
	 * @param departDate : today + departDate + 1
	 * @param returnDate : returnDate + 1
	 * @return
	 */
	public String chooseDates(int departDate, int returnDate) {
		
		WebDriverWait wait = new WebDriverWait(driver, 8);	

		wait.until(ExpectedConditions.visibilityOf(departField));
		departField.click();
		wait = new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.visibilityOfAllElements(departDates));
		departDates.get(departDate).click();

		wait.until(ExpectedConditions.visibilityOfAllElements(returnDates));
		returnDates.get(returnDate).click();
		
		String whoIsTravelingTab = "_";
		wait.until(ExpectedConditions.visibilityOf(whoIsTravelingTabLabel));
		whoIsTravelingTabLabel.getText();
		return whoIsTravelingTabLabel.getText();
	}
	
	public String choosePassengers(int adult, int children, int seniors, int infants, String category) {
		String qty = "";
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		if (adult == 2) {
			wait.until(ExpectedConditions.visibilityOf(adultPlusBtn));
			adultPlusBtn.click();
			wait.until(ExpectedConditions.visibilityOf(qtyAdults));
			String qtyAdultsString = qtyAdults.toString();
			qty = (qtyAdultsString.substring(116, qtyAdultsString.length() - 3));
		}
		
		if (adult == 3) {
			wait.until(ExpectedConditions.visibilityOf(adultPlusBtn));
			adultPlusBtn.click();
			
			try {
				   Thread.sleep(1000);
				} catch (InterruptedException e) {
				 e.printStackTrace();
				}
			wait.until(ExpectedConditions.visibilityOf(adultPlusBtnMore));		
			adultPlusBtnMore.click();	
			wait.until(ExpectedConditions.visibilityOf(qtyAdults3));
			String qtyAdultsString = qtyAdults3.toString();
			qty = (qtyAdultsString.substring(116, qtyAdultsString.length() - 3));
		}
		
		if (adult == 4) {
			wait.until(ExpectedConditions.visibilityOf(adultPlusBtn));
			adultPlusBtn.click();
			
			try {
				   Thread.sleep(1000);
				} catch (InterruptedException e) {
				 e.printStackTrace();
				}
			
			wait.until(ExpectedConditions.visibilityOf(adultPlusBtnMore));		
			adultPlusBtnMore.click();	
			
			try {
				   Thread.sleep(1000);
				} catch (InterruptedException e) {
				 e.printStackTrace();
				}
			
			wait.until(ExpectedConditions.visibilityOf(adultPlusBtnMoreMore));		
			adultPlusBtnMoreMore.click();		
			
			wait.until(ExpectedConditions.visibilityOf(qtyAdults4));
			String qtyAdultsString = qtyAdults4.toString();
			qty = (qtyAdultsString.substring(116, qtyAdultsString.length() - 3));
		}

		qty += "000e"; // adults=?, children=0, seniors=0, infants=0
//		System.out.println("qty = " + qty);
		
		wait.until(ExpectedConditions.visibilityOf(searchFlightsBtn));
		searchFlightsBtn.click();
		
		try {
			   Thread.sleep(3000);
			} catch (InterruptedException e) {
			 e.printStackTrace();
			}
		
		return qty;
	}
	
	public String searchFlights() {
		String searchResults = "";
		
	//	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		wait.until(ExpectedConditions.visibilityOf(adCheckbox));
		adCheckbox.click();
		
		wait.until(ExpectedConditions.visibilityOf(closeActMdlBtn));
		closeActMdlBtn.click();

		wait.until(ExpectedConditions.visibilityOf(fltHeaderLegaleseIncludes));
		searchResults = fltHeaderLegaleseIncludes.getText().toString();
		System.out.println("searchResults: " + searchResults);
		return searchResults;
	}
	
	/**
	 * @param city Dallas only
	 * @return
	 */
	public String topPopDestination(String city) {

	//	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 6);
		
		wait.until(ExpectedConditions.visibilityOf(dallasPict));
		dallasPict.click();
		
		wait.until(ExpectedConditions.visibilityOf(labelTo1));
		return labelTo1.getAttribute("value");
	}
	
	
	public String bookCheapestRoundTrip() {

		String priceDetails = "";
		
	//	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 8);
		
		wait.until(ExpectedConditions.visibilityOf(foptPriceDeparture));	
		foptPriceDeparture.click();
		
		wait.until(ExpectedConditions.visibilityOf(foptPriceReturn));
		foptPriceReturn.click();

		wait.until(ExpectedConditions.visibilityOf(priceSummBeforeClickOnBookBtnLabel));
		priceSummBeforeClickOnBookBtnLabel.getText();
		
		wait.until(ExpectedConditions.visibilityOf(bookBtn));
		bookBtn.click();
		
		wait.until(ExpectedConditions.visibilityOf(totalPrice));
		priceDetails = totalPrice.getText();
		return priceDetails;
	}
	
	
}