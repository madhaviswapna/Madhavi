package com.shc.msp.ft.modules;

import com.shc.automation.AjaxCondition;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;


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
    public static final Locator EXPECTED_ARRIVAL_DATE_PICKER= new Locator("EXPECTED_ARRIVAL_DATE_INPUT", "//input[@ng-model='selected.expectedArrivalDate']", "Expected Arrival Date Picker");
    public static final Locator SUBMIT_BUTTON= new Locator("SUBMIT_BUTTON", "//button[@ng-click='ok()']", "Submit Button");
    
    
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

}