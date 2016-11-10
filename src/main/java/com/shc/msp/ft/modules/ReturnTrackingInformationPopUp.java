package com.shc.msp.ft.modules;

import com.shc.automation.AjaxCondition;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;


public class ReturnTrackingInformationPopUp extends Module{
	
	public ReturnTrackingInformationPopUp(SiteFactory factory) {
		super(factory);
	}
	
	protected String getPageName() {
        return "Return Tracking Information PopUp Module";
    }

    protected String pageName() {
        return "Return Tracking Information PopUp Module";
    }
    
    
    public static final Locator RETURN_TRACKING_INFORMATION_POPUP= new Locator("RETURN_TRACKING_INFORMATION_POPUP", "//h4[contains(text(),'Return Tracking Information')]/ancestor::div[@class='modal-content']", "Return Tracking Information PopUp");
    public static final Locator SELECTED_CARRIER_TYPE= new Locator("SELECTED_CARRIER_TYPE", "//select[@ng-model='row.selected.carrierType']", "Selected Carrier Type");
    public static final Locator SELECTED_RETURN_TRACKING_NUMBER= new Locator("SELECTED_RETURN_TRACKING_NUMBER", "//input[@ng-model='row.selected.returnTrackingNumber']", "Selected Return Tracking Number");
    public static final Locator SELECTED_QUANTITY= new Locator("SELECTED_QUANTITY", "//input[@ng-model='row.selected.quantity']", "Selected Quantity");
    public static final Locator DELETE_ROW= new Locator("DELETE_ROW", "//button[@ng-click='deleteRow(row)']", "Delete Row");
    public static final Locator SUBMIT_BUTTON= new Locator("SUBMIT_BUTTON", "//button[@ng-click='ok()']", "Submit Button");
    public static final Locator NOTE_TEXT_AREA = new Locator("NOTE_TEXT_AREA","(//textarea[@ng-model='selected.note'])","Note for Retrun tracking");

    public ReturnTrackingInformationPopUp verifyReturnTrackingInformationPopUp(){
    	
    	String trackingNo = "01010100000012345";
    	Logger.log("Verify if \"Return Tracking Information\" PopUp is Visible",TestStepType.VERIFICATION_STEP);
    	AjaxCondition.forElementVisible(RETURN_TRACKING_INFORMATION_POPUP).waitForResponse();
    	Logger.log("Verify if \"Selected Carrier Type\" is Visible in Return Tracking Information PopUp",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(SELECTED_CARRIER_TYPE).waitForResponse();
    	getAction().selectByVisibleText(SELECTED_CARRIER_TYPE, "FedEx");
    	Logger.log("Verify if \"Selected Return Tracking Number\" is Visible in Return Tracking Information PopUp",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(SELECTED_RETURN_TRACKING_NUMBER).waitForResponse();
    	getAction().type(SELECTED_RETURN_TRACKING_NUMBER, trackingNo);
    	Logger.log("Verify if \"Selected Quantity\" is Visible in Return Tracking Information PopUp",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(SELECTED_QUANTITY).waitForResponse();
    	getAction().type(SELECTED_QUANTITY, "1");
    	AjaxCondition.forElementVisible(NOTE_TEXT_AREA).waitForResponse();
    	getAction().type(NOTE_TEXT_AREA, "test one return tracking information");
    	Logger.log("Verify if \"Delete Row\" Button is Visible in Return Tracking Information PopUp",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(DELETE_ROW).waitForResponse();
    	
    	Logger.log("Verify if \"Submit\" Button is Visible in Return Tracking Information PopUp",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
    	getAction().click(DELETE_ROW);
    	return this;
    }
    
}