/**
 *   File Name: HotelsPage.java<br>
 *
 *   Yutaka<br>
 *   Created: Apr 18, 2016
 *   
 */

package org.sqa.cflight.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sqa.cflight.data.ReadFileData;


/**
 * HotelsPage //ADDD (description of class)
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
public class HotelsPage {
	
	// Hotels Page methods using Page factory
	
	@FindBy(className = "main-content-hdr")
	WebElement hotelsLabel;
	
	//@FindBy(id = "destination")
	//WebElement destinationTextField;
	
	@FindBy(id = "destination")
	WebElement cityAddressOrHotelField;
	
	@FindBy(id = "wizardInstructionsContainer")
	WebElement whichDatesLabel;
	
	@FindBy(className = ".depart-date>.placeholder")
	WebElement placeholderCheckIn;
	
	@FindBy(className = ".return-date>.placeholder")
	WebElement placeholderCheckOut;
	
	@FindBy(css = ".ui-datepicker-group.ui-datepicker-group-first [data-handler='selectDay']>.ui-state-default")
	private List <WebElement> checkInDate;
	
	@FindBy(css = ".ui-datepicker-group.ui-datepicker-group-last [data-handler='selectDay']>.ui-state-default")
	private List <WebElement> checkOutDate;
	
	@FindBy(css = ".form-group.col.rooms.col-last .form-control.readonly.prominent")
	WebElement roomsAndResidents;
	
	@FindBy(id = "wizardInstructionsContainer")
	WebElement howManyRoomsAndWhoIsStayingLabel;
	
	@FindBy(id = "room1adults")
	WebElement room1adultsLabel;
	
	@FindBy(id = "room1children")
	WebElement room1childrenLabel;
	
	@FindBy(xpath = ".//*[@id='roomsContainer']/div/div[1]/div[1]/button[1]")
	WebElement adultsMinusBtn;
	
	@FindBy(xpath = ".//*[@id='roomsContainer']/div/div[1]/div[1]/button[2]")
	WebElement adultsPlusBtn;
	
	@FindBy(xpath = ".//*[@id='roomsContainer']/div/div[1]/div[2]/button[1]")
	WebElement childrenMinusBtn;
	
	@FindBy(xpath = ".//*[@id='roomsContainer']/div/div[1]/div[2]/button[2]")
	WebElement childrenPlusBtn;
	
	@FindBy(css = ".modifier.icn.icn-plus")
	WebElement addAnotherRoomBtn;
	
	@FindBy(css = ".btn.large.block")
	WebElement searchHotelsBtn;
	
	@FindBy(css = ".pg-hdr.location")
	WebElement locationLabel;
	
	
	private WebDriver driver;

	public HotelsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public HotelsPage navigateToHotelsPage(){
		//ReadFileData rfd = new ReadFileData();
		//rfd.properties();
		driver.get(ReadFileData.HOTELS_PAGE_URL);	
		return this;
	}
	
	public String fillOutcityAddressOrHotelField(String destinamion) {
		WebDriverWait wait = new WebDriverWait(driver, 8);
		wait.until(ExpectedConditions.visibilityOf(cityAddressOrHotelField));
		cityAddressOrHotelField.click();
		cityAddressOrHotelField.clear();
		cityAddressOrHotelField.sendKeys(destinamion);
		cityAddressOrHotelField.sendKeys(Keys.RETURN);

		wait.until(ExpectedConditions.visibilityOf(whichDatesLabel));
		return whichDatesLabel.getText();
	}
	
	/**
	 * @param check in : today + departDate + 1
	 * @param check out : returnDate + 1
	 */
	public void chooseDates(int checkIn, int checkOut) {
		
		WebDriverWait wait = new WebDriverWait(driver, 8);	

		//wait.until(ExpectedConditions.visibilityOf(placeholderCheckIn));
		//placeholderCheckIn.click();
		//wait = new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.visibilityOfAllElements(checkInDate));
		checkInDate.get(checkIn).click();

		wait.until(ExpectedConditions.visibilityOfAllElements(checkOutDate));
		checkOutDate.get(checkOut).click();
	}
	
	public String  howManyRoomsAndWhoIsStayingTab() {
	  
	  WebDriverWait wait = new WebDriverWait(driver, 5);
	  wait.until(ExpectedConditions.visibilityOf(howManyRoomsAndWhoIsStayingLabel));
	  return howManyRoomsAndWhoIsStayingLabel.getText();
	}
	
	/**
	 * 3 adults, 3 children, additional room
	 * @throws InterruptedException 
	 */
	public void searchHotel() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		wait.until(ExpectedConditions.visibilityOf(adultsPlusBtn));
		adultsPlusBtn.click();
		
		wait.until(ExpectedConditions.visibilityOf(childrenPlusBtn));
		childrenPlusBtn.click();
		
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.visibilityOf(childrenPlusBtn));
		childrenPlusBtn.click();
		
		wait.until(ExpectedConditions.visibilityOf(addAnotherRoomBtn));
		addAnotherRoomBtn.click();
		
		wait.until(ExpectedConditions.visibilityOf(searchHotelsBtn));
		searchHotelsBtn.click();
	}
	
	public String locationLabel() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(locationLabel));
		return locationLabel.getText();
	}

}
