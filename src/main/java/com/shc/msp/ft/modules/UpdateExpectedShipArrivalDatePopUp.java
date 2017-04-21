package com.shc.msp.ft.modules;

import java.util.Calendar;



import com.shc.automation.AjaxCondition;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.util.DateUtils;

public class UpdateExpectedShipArrivalDatePopUp extends Module{
	
	public UpdateExpectedShipArrivalDatePopUp(SiteFactory factory) {
		super(factory);
	}
	
	protected String getPageName() {
        return "Update Expected Ship/Arrival Date PopUp Module";
    }

    protected String pageName() {
        return "Update Expected Ship/Arrival Date PopUp Module";
    }
    
    
    public static final Locator UPDATE_EXPECTED_SHIP_ARRIVAL_DATE_POPUP= new Locator("UPDATE_EXPECTED_SHIP_ARRIVAL_DATE_POPUP", "//h4[contains(text(),'Update Expected Ship/Arrival Dates')]/ancestor::div[@class='modal-content']", "Update Expected Ship/Arrival Date PopUp");
    public static final Locator EXPECTED_SHIP_DATE_PICKER= new Locator("EXPECTED_SHIP_DATE_INPUT", "//input[@ng-model='selected.expectedShipDate']", "Expected Ship Date Picker");
    public static final Locator EXPECTED_SHIP_DATE_CALENDER_DATE = new Locator("EXPECTED_SHIP_DATE_CALENDER_DATE", "(//input[@ng-model='selected.expectedShipDate']/following-sibling::ul//span[contains(text(),'{0}')])[{1}]", "Expected Ship Date in calender popup");
    public static final Locator EXPECTED_ARRIVAL_DATE_PICKER= new Locator("EXPECTED_ARRIVAL_DATE_INPUT", "//input[@ng-model='selected.expectedArrivalDate']", "Expected Arrival Date Picker");
    public static final Locator EXPECTED_ARRIVAL_DATE_CALENDER_DATE = new Locator("EXPECTED_Arrival_DATE_CALENDER_DATE", "(//input[@ng-model='selected.expectedArrivalDate']/following-sibling::ul//span[contains(text(),'{0}')])[{1}]", "Expected Arrival Date in calender popup");
    public static final Locator SUBMIT_BUTTON= new Locator("SUBMIT_BUTTON", "//button[@ng-click='ok()']", "Submit Button");
    public static final Locator NOTES= new Locator("NOTES", "//form[@name='updateExpectedShipDateForm']//textarea", "NOTES");
    
    public UpdateExpectedShipArrivalDatePopUp verifyUpdateExpectedShipArrivalDatePopUp(){
    	
    	Logger.log("Verify if \"Update Expected Ship/Arrival Date\" PopUp is Visible",TestStepType.VERIFICATION_STEP);
    	AjaxCondition.forElementVisible(UPDATE_EXPECTED_SHIP_ARRIVAL_DATE_POPUP).waitForResponse();
    	
    	Logger.log("Verify if \"Expected Ship Date\" Picker is Visible in Update Expected Ship/Arrival Date PopUp",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(EXPECTED_SHIP_DATE_PICKER).waitForResponse();
    	
    	Logger.log("Verify if \"Expected Arrival Date\" Picker is Visible in Update Expected Ship/Arrival Date PopUp",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(EXPECTED_ARRIVAL_DATE_PICKER).waitForResponse();
    	
    	Logger.log("Verify if \"Submit\" Button is Visible in Update Expected Ship/Arrival Date PopUp",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
    	
    	return this;
    }
    
   
    
    public UpdateExpectedShipArrivalDatePopUp UpdateExpectedShipArrivalDate(){
    	verifyUpdateExpectedShipArrivalDatePopUp();
    	Integer dayOfTheMonth =DateUtils.fetchCurrentDayOfMonth();
    	String dateIndex = dayOfTheMonth>15 ? "last()" : "1"; 
    
    	Logger.log("Select the Expected Ship date as "+ dayOfTheMonth,TestStepType.STEP);
    	getAction().click(EXPECTED_SHIP_DATE_PICKER);
    	AjaxCondition.forElementVisible(EXPECTED_SHIP_DATE_CALENDER_DATE.format(dayOfTheMonth.toString(),dateIndex)).waitForResponse();
      	getAction().click(EXPECTED_SHIP_DATE_CALENDER_DATE.format(dayOfTheMonth.toString(),dateIndex));
    	
      	Logger.log("Select the Expected Arrival date as "+ dayOfTheMonth,TestStepType.STEP);
    	getAction().click(EXPECTED_ARRIVAL_DATE_PICKER);
    	AjaxCondition.forElementVisible(EXPECTED_ARRIVAL_DATE_CALENDER_DATE.format(dayOfTheMonth,dateIndex)).waitForResponse();
    	getAction().click(EXPECTED_ARRIVAL_DATE_CALENDER_DATE.format(dayOfTheMonth,dateIndex));
    	getAction().type(NOTES, "Ship and Arrival dates are changed as a part of automation testing.");
    	getContext().put("adjustmentOption","Ship and Arrival dates are changed as a part of automation testing.");
    	
    	Logger.log("Click on Submit ",TestStepType.STEP);
    	getAction().click(SUBMIT_BUTTON);
    	
    	return this;
    }

}