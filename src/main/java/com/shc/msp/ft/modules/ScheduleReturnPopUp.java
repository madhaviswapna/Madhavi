package com.shc.msp.ft.modules;

import com.shc.automation.AjaxCondition;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;


public class ScheduleReturnPopUp extends Module{
	
	public ScheduleReturnPopUp(SiteFactory factory) {
		super(factory);
	}
	
	protected String getPageName() {
        return "Schedule Return PopUp Module";
    }

    protected String pageName() {
        return "Schedule Return PopUp Module";
    }
    
    
    public static final Locator SCHEDULE_RETURN_POPUP= new Locator("SCHEDULE_RETURN_POPUP", "//h4[contains(text(),'Schedule Return')]/ancestor::div[@class='modal-content']", "Schedule Return PopUp");
    public static final Locator START_DATE_PICKER= new Locator("START_DATE_PICKER", "//input[@ng-model='model.startDate']", "Start Date Picker");
    public static final Locator NEW_SELECTED_DATE = new Locator("NEW SELECTED DATE", "//button[contains(@class,'dt-selected')]", "New Selected Date Button");
    public static final Locator GET_AVAILABLE_DATES_BUTTON= new Locator("GET_AVAILABLE_DATES_BUTTON", "//button[@ng-click='getAvailableDates()']", "Get Available Dates Button");
    public static final Locator SCHEDULE_RETURN_ERROR_POPUP= new Locator("SCHEDULE_RETURN_error mssage Window","//div[@class='modal-header dialog-header-error ng-scope ng-isolate-scope']","Schedule Return Error Mssage Window");
    public static final Locator SCHEDULE_RETURN_ERROR_POPUP_CLOSE= new Locator("SCHEDULE_RETURN_error mssage close ", "//button[@id='modalclose']", "SCHEDULE_RETURN_ERROR_POPUP_CLOSE");
    public static final Locator RETURN_QUANTITY = new Locator("RETURN QUANTITY","//input[@ng-model='model.returnQuantity']","Returen Quantity");
    public static final Locator RETURN_ALL_KMART_DELIVERY_BUTTON = new Locator("RETURN_ALL_KMART_DELIVERY_BUTTON","(//button[text()='Yes'])[1]","Return all Kmart Delivery Button");
    public static final Locator SELECT_RESON_CODE = new Locator("Select reson code ", "(//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required'])", " SELECT RESON CODE");
	public static final Locator SELECTED_RESON_CODE = new Locator("Selected reson code ", "(//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required'])", " SELECTED RESON CODE");
	public static final Locator CONTACT_CUSTOMER_BUTTON = new Locator("CONTACT_CUSTOMER_BUTTON","(//button[text()='Yes'])[2]","Contact Customer Button");
    public static final Locator NOTES = new Locator("Adjustment notes", "(//textarea[@class='form-control input-sm ng-pristine ng-valid'])", "notes");
	public static final Locator SUBMIT_BUTTON= new Locator("SUBMIT_BUTTON", "//button[@ng-click='ok()']", "Submit Button");
    

    public ScheduleReturnPopUp verifyScheduleReturnPopUp(){
    	
    	Logger.log("Verify if \"Schedule Return\" PopUp is Visible",TestStepType.VERIFICATION_STEP);
    	AjaxCondition.forElementVisible(SCHEDULE_RETURN_POPUP).waitForResponse();
    	
    	Logger.log("Verify if \"Start Date\" Picker is Visible in Schedule Return PopUp",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(START_DATE_PICKER).waitForResponse();
    	getAction().click(START_DATE_PICKER);
    	getAction().waitFor(3000);
    	AjaxCondition.forElementVisible(NEW_SELECTED_DATE).waitForResponse(10);
    	getAction().click(NEW_SELECTED_DATE);
    	getAction().waitFor(3000);
    	Logger.log("Verify if \"Get Available Dates\" Button is Visible in Schedule Return PopUp",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(GET_AVAILABLE_DATES_BUTTON).waitForResponse();
    	getAction().click(GET_AVAILABLE_DATES_BUTTON);
    	getAction().waitFor(1000);
    	if(getAction().isVisible(SCHEDULE_RETURN_POPUP)){
    		getAction().focus(SCHEDULE_RETURN_POPUP);
    		AjaxCondition.forElementVisible(RETURN_QUANTITY).waitForResponse(5);
    		getAction().type(RETURN_QUANTITY, "1");
    		AjaxCondition.forElementVisible(RETURN_ALL_KMART_DELIVERY_BUTTON).waitForResponse(5);
    		getAction().click(RETURN_ALL_KMART_DELIVERY_BUTTON);
    		AjaxCondition.forElementVisible(RETURN_ALL_KMART_DELIVERY_BUTTON).waitForResponse(5);
    		getAction().waitFor(10000);
    		AjaxCondition.forElementVisible(SELECT_RESON_CODE).waitForResponse(5);
			getAction().click(SELECT_RESON_CODE);
			getAction().selectByVisibleText(SELECTED_RESON_CODE, "Product - Quality");
			AjaxCondition.forElementVisible(CONTACT_CUSTOMER_BUTTON).waitForResponse(5);
    		getAction().click(CONTACT_CUSTOMER_BUTTON);
			AjaxCondition.forElementVisible(NOTES).waitForResponse();
			getAction().click(NOTES);
			getAction().type(NOTES,"Customer Initiated Rescheduling");
			AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
			Logger.log("Verify if \"Submit\" Button is Visible in Schedule Return PopUp",TestStepType.VERIFICATION_SUBSTEP);
	    	AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
		    }else if(getAction().isVisible(SCHEDULE_RETURN_ERROR_POPUP)){
		    	AjaxCondition.forElementVisible(SCHEDULE_RETURN_ERROR_POPUP_CLOSE).waitForResponse(10);
				getAction().focus(SCHEDULE_RETURN_ERROR_POPUP_CLOSE);
				getAction().waitFor(1000);
				getAction().click(SCHEDULE_RETURN_ERROR_POPUP_CLOSE);
				getAction().waitFor(3000);
				AjaxCondition.forElementVisible(START_DATE_PICKER).waitForResponse();
		    	getAction().click(START_DATE_PICKER);
				AjaxCondition.forElementVisible(NEW_SELECTED_DATE).waitForResponse(10);
				getAction().click(NEW_SELECTED_DATE);
				AjaxCondition.forElementVisible(GET_AVAILABLE_DATES_BUTTON).waitForResponse();
		    	getAction().click(GET_AVAILABLE_DATES_BUTTON);
		    	
		    }
    	return this;
    	}
}