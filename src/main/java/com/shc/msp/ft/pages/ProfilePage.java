package com.shc.msp.ft.pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.shc.automation.AjaxCondition;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.PageAssert;
import com.shc.automation.SoftAssert;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.util.StringUtils;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.automation.utils.TestUtils.CheckLocatorFor;

public class ProfilePage extends Page {

	
	
	public Locator YTD_SPENDING_GRAPHIC = new Locator("","//*[name()='svg']","YTD Spending graphics");
	public Locator PERSONAL_INFO_COLUMN = new Locator("","(//div[@id='workspace']//div[@class='column-parent'])[1]","Personal information");
	public Locator CONTACT_INFO_COLUMN = new Locator("","(//div[@id='workspace']//div[@class='column-parent'])[2]","Contact information");
	public Locator UPDATE_PROFILE_LINK = new Locator("","//div[@id='workspace']//div[@class='column-parent'][2]//a[text()='Update Profile']","Contact information");
	public Locator RECENT_ORDERS_SECTION = new Locator("","//div[@ng-controller='RecentOrdersCntrl']","Recent orders section");
	public Locator INTERACTION_HISTORY= new Locator("","//div[contains(@ng-controller,'DemoCtrl')]//strong[text()='Interaction History']","Interaction history section");
	public Locator PURCHASE_HISTORY = new Locator("","//div[contains(@ng-controller,'DemoCtrl')]//strong[text()='Purchase History']","Purchase History section");
	public Locator VIEW_ITEMS_BUTTON = new Locator("","(//div[@ng-click='viewOrderDetails(order);']//button)[1]","View Items button");
	public Locator ORDER_ITEM_DETAILS_TABLE = new Locator("","//table[@ng-table='orderItemsTableParams']","Order item details table");
	public Locator FILTER_PURCHASE_HISTORY = new Locator("","//div[@ng-controller='DemoCtrl1']//input[@placeholder='Filter']","Purchase history filter");
	public Locator ORDERS_IN_PURCHASE_HISTORY = new Locator("","//div[@ng-controller='DemoCtrl1']//td[@data-title-text='Order']","Order number in purchase history");
	public final Locator CUSTOMER_PHONE_NUMBER = new Locator("CUSTOMER PHONE NUMBER", "//div[@ng-repeat='phone in memberProfile.phoneNumbers']//parent::div[@class='column-parent']","Customer Phone Number");
	public Locator REASON_FOR_CONTACT_SECTION = new Locator("REASON_FOR_CONTACT_SECTION", "//span[@info='reason_for_contact']", "Reason For Contact");
	public Locator CHANGE_CUSTOMER = new Locator("CHANGE_CUSTOMER", "(//a[contains(text(),'Change Customer')])", "Change Customer");
	public Locator LOGOUT_OF_MEMBER = new Locator("LOGOUT_OF_MEMBER", "//a[contains(text(),'Log out of Member')]","Log Out Of Member");
	public Locator MEMBER_INFORMATION_TEXTBOX = new Locator("MEMBER_INFORMATION_TEXTBOX", "//input[@id='memberInfo']", "Member Info Text Box");
	public Locator CHANGE_CUSTOMER_SEARCH_BUTTON = new Locator("CHANGE_CUSTOMER_SEARCH_BUTTON", "//button[contains(text(),'Search')]", "Change Customer Search Button");
	public Locator CUSTOMER_IN_CHANGE_CUSTOMER_LIST = new Locator("CUSTOMER_IN_CHANGE_CUSTOMER_LIST", "(//div[@ng-repeat='member in model.searchResults'])[{0}]", "Customer in Change Customer List");
	
	public ProfilePage(SiteFactory factory) {
		super(factory);
	}

	@Override
	protected String pageName() {
		return "Member Profile page";
	}

	/**
	 * Verify the basic elements in Member profile page
	 * @return
	 */
	public ProfilePage verifyProfilePage(){
		Logger.log("Verify all details are displayed in member profile page",TestStepType.VERIFICATION_STEP);
		SoftAssert.checkElementAndContinueOnFailure(PERSONAL_INFO_COLUMN, "Personal information is displayed",CheckLocatorFor.isVisible);
		SoftAssert.checkConditionAndContinueOnFailure("Personal information data is present", getAction().getText(PERSONAL_INFO_COLUMN).length()>1);
		SoftAssert.checkElementAndContinueOnFailure(CONTACT_INFO_COLUMN, "Contact information is displayed",CheckLocatorFor.isVisible);
		SoftAssert.checkConditionAndContinueOnFailure("Contact information data is present", getAction().getText(CONTACT_INFO_COLUMN).length()>1);
		SoftAssert.checkElementAndContinueOnFailure(UPDATE_PROFILE_LINK, "Update profile link is displayed",CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(YTD_SPENDING_GRAPHIC, "YTD spending graphic is displayed",CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(RECENT_ORDERS_SECTION, "Recent orders section is displayed",CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(INTERACTION_HISTORY, "Interaction history section is displayed",CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(PURCHASE_HISTORY, "Purchase history section is displayed",CheckLocatorFor.isVisible);
		return this;
	}
	
	/**
	 * Click on the first view item button
	 * @return
	 */
	public ProfilePage clickOnViewItemsButtonForFirstOrder(){
		factory.homePage().closeWarningPopupWindow();
		Logger.log("Click on View Items button for the first order in recent order section",TestStepType.STEP);
		AjaxCondition.forElementVisible(VIEW_ITEMS_BUTTON).waitForResponse();
		getAction().click(VIEW_ITEMS_BUTTON);
		return this;
	}
	
	/**
	 * Verify item details are displayed for the order
	 * @return
	 */
	public ProfilePage verifyItemsAreDisplayed(){
		Logger.log("Verify item details are displayed for the order",TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(ORDER_ITEM_DETAILS_TABLE).waitForResponse();
		return this;
	}
	
	/**
	 * Verify that filter is working for purchase history. This method will fetch the first order number in the purchase history
	 * and enter it in filter. Filter functionality is validated by ensuring there is only result after filtering
	 * @return
	 */
	public ProfilePage verifyFilterForPurchaseHistory(){
		Logger.log("Verify filter option in purchase history",TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(FILTER_PURCHASE_HISTORY).waitForResponse();
		Logger.log("Filter by 'Order number'",TestStepType.VERIFICATION_SUBSTEP);
		Logger.log("Fetch the first order number in purchase history section",TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(ORDERS_IN_PURCHASE_HISTORY).waitForResponse();
		List<WebElement> orders = getAction().driver.findElements(getAction().getWebElement(ORDERS_IN_PURCHASE_HISTORY));
		String firstOrderNumber=orders.get(0).getText();
		Logger.log("First order number in purchase history - "+firstOrderNumber,TestStepType.DATA_CAPTURE);
		Logger.log("Enter order number "+firstOrderNumber+" in filter",TestStepType.STEP);
		getAction().type(FILTER_PURCHASE_HISTORY, firstOrderNumber);
		Logger.log("Verify only one order is displayed in purchase history section",TestStepType.VERIFICATION_STEP);
//		orders=getAction().driver.findElements(getAction().getWebElement(ORDERS_IN_PURCHASE_HISTORY));
		PageAssert.verifyElementCount(ORDERS_IN_PURCHASE_HISTORY, 1);
		
		return this;
		
	}

	public void verifyPhoneOnProfilePage(String phoneNumber) {
		Logger.log("Verify Call phone number matches with Profile phone number on Profile details page "+phoneNumber ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(CUSTOMER_PHONE_NUMBER).waitForResponse();
		PageAssert.verifyTrue(StringUtils.extractNumber(getAction().getText(CUSTOMER_PHONE_NUMBER)).contains(phoneNumber), "Verify Customer phone number matcher with Profile - "+phoneNumber);
		
	}
	
	public void clickChangeCustomer(){
		Logger.log("Click on reason For Contact",TestStepType.STEP);
		AjaxCondition.forElementVisible(REASON_FOR_CONTACT_SECTION).waitWithoutException(10);
		getAction().click(REASON_FOR_CONTACT_SECTION);
		Logger.log("Click on Change Customer",TestStepType.STEP);
		AjaxCondition.forElementVisible(CHANGE_CUSTOMER).waitWithoutException(10);
		getAction().click(CHANGE_CUSTOMER);
	}
	
	public void clickLogoutOfMember(){
		Logger.log("Click on reason For Contact",TestStepType.STEP);
		AjaxCondition.forElementVisible(LOGOUT_OF_MEMBER).waitWithoutException(10);
		getAction().click(LOGOUT_OF_MEMBER);
	}
	
	public void verifyPrePopulatedPhoneNumberChangeCustomer(String phoneNumber){
		Logger.log("Verify Change Customer popup is Pre-Populated with Phone Number -"+phoneNumber,TestStepType.STEP);
		AjaxCondition.forElementVisible(MEMBER_INFORMATION_TEXTBOX).waitWithoutException(10);
		PageAssert.verifyEqual(getAction().getValue(MEMBER_INFORMATION_TEXTBOX), phoneNumber);
	}
	
	public void  searchAnotherCustomerFromChangeCustomer(String phoneNumber){
		Logger.log("Search another customer"+phoneNumber,TestStepType.STEP);
		AjaxCondition.forElementVisible(MEMBER_INFORMATION_TEXTBOX).waitWithoutException(10);
		getAction().type(MEMBER_INFORMATION_TEXTBOX, phoneNumber);
		getAction().click(CHANGE_CUSTOMER_SEARCH_BUTTON);
	}
	
	public void selectCustomerFromChangeCustomerList(int index){
		Logger.log("Click on index "+index+"from Customer list",TestStepType.STEP);
		AjaxCondition.forElementVisible(CUSTOMER_IN_CHANGE_CUSTOMER_LIST.format(index)).waitWithoutException(10);
		getAction().click(CUSTOMER_IN_CHANGE_CUSTOMER_LIST.format(index));
	}
}
