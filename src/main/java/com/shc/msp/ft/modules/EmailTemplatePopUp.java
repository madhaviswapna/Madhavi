package com.shc.msp.ft.modules;

import com.shc.automation.AjaxCondition;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.PageAssert;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.util.Utility;

public class EmailTemplatePopUp extends Module{

	public EmailTemplatePopUp(SiteFactory factory) {
		super(factory);
	}
	
	protected String getPageName() {
        return "Email Template PopUp Module";
    }

    protected String pageName() {
        return "Email Template PopUp Module";
    }

    public static final Locator EMAIL_TEMPLATE_POPUP= new Locator("EMAIL_TEMPLATE_POPUP", "//div[@class='modal-content']", "Email Template PopUp");
    public static final Locator STORE_DROPDOWN= new Locator("STORE_DROPDOWN", "(//select[@ng-model='emailService.selected.store'])[1]", "Store Dropdown");
    public static final Locator STORE_DROPDOWN_OPTION_COUNT= new Locator("STORE_DROPDOWN_OPTION_COUNT", "//select[@ng-model='emailService.selected.store']/option", "Store Dropdown Option Count");
    public static final Locator STORE_DROPDOWN_OPTION= new Locator("STORE_DROPDOWN_OPTION", "//select[@ng-model='emailService.selected.store']/option[@value='{0}']", "Store Dropdown Option");
    public static final Locator EMAIL_TEMPLATE_DROPDOWN= new Locator("EMAIL_TEMPLATE_DROPDOWN", "//select[@ng-model='emailService.selected.typeByStore']", "Email Template Dropdown");
    public static final Locator EMAIL_TEMPLATE_DROPDOWN_OPTION_COUNT= new Locator("EMAIL_TEMPLATE_DROPDOWN_OPTION_COUNT", "//select[@ng-model='emailService.selected.typeByStore']/option", "Email Template Dropdown Option Count");
    public static final Locator EMAIL_TEMPLATE_DROPDOWN_OPTION= new Locator("EMAIL_TEMPLATE_DROPDOWN_OPTION","//select[@ng-model='emailService.selected.typeByStore']/option[@value='{0}']", "Email Template Dropdown Option");
    public static final Locator TO_FIELD= new Locator("TO_FIELD", "//input[@ng-model='emailService.toEmailAddress']", "To Field");
    public static final Locator SUBJECT_FIELD= new Locator("SUBJECT_FIELD", "//textarea[@ng-model='emailService.subject']", "Subject Field");
    public static final Locator EMAIL_TEMPLATE_TEXTAREA= new Locator("EMAIL_TEMPLATE_TEXTAREA", "//div[@ng-model='emailService.emailTemplateHtml']", "Email Template Text Area");
    public static final Locator CANCEL_BUTTON= new Locator("CANCEL_BUTTON", "//div[@class='modal-footer ng-scope']/button[@ng-click='cancel()']", "Cancel Button");
    public static final Locator SUBMIT_BUTTON= new Locator("SUBMIT_BUTTON", "//button[@ng-click='ok()' and @validatetext='emailService.emailTemplateHtml']", "Submit Button");
    public final Locator LINEITEMDETAILS_ACTION_SELECT_BOX = new Locator("", "//div[@ng-if='actions']//select", "Select action drop down");
	public final Locator LINEITEDETAILS_ACTION_GO_BUTTON = new Locator("", "//div[@ng-if='actions']//button[contains(.,'Go')]", "Select action Go button");
	
    Utility util = new Utility();
    
    
    public EmailTemplatePopUp verifyEmailTemplatePopUp(){
    	Logger.log("Verify if \"Email Template\" popup is visible",TestStepType.VERIFICATION_STEP);
    	AjaxCondition.forElementVisible(EMAIL_TEMPLATE_POPUP).waitForResponse();
    	getAction().focus(EMAIL_TEMPLATE_POPUP);
    	Logger.log("Verify if \"Store\" dropdown is visible",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(STORE_DROPDOWN).waitForResponse(); 
    	getAction().focus(STORE_DROPDOWN);
    	getAction().waitFor(1000);
    	getAction().click(STORE_DROPDOWN);
		int storeCount =getAction().getElementCount(STORE_DROPDOWN_OPTION_COUNT);
			if(storeCount==0)
			{
				PageAssert.fail("Store dropdown has no data to select");
			}
			else
			{
			getAction().waitFor(3000);
			getAction().click(STORE_DROPDOWN);
			String storeName=getAction().getText(STORE_DROPDOWN_OPTION.format(0));
			Logger.log("Select "+storeName+" store ",TestStepType.STEP);
			AjaxCondition.forElementVisible(STORE_DROPDOWN_OPTION.format(0)).waitForResponse();
			getAction().click(STORE_DROPDOWN_OPTION.format(0));
			}
    	
    	Logger.log("Verify if \"Email Template\" dropdown is visible",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(EMAIL_TEMPLATE_DROPDOWN).waitForResponse();
    	getAction().click(EMAIL_TEMPLATE_DROPDOWN);
		int emailTemplateCount =getAction().getElementCount(EMAIL_TEMPLATE_DROPDOWN_OPTION_COUNT);
			if(emailTemplateCount==0)
			{
				PageAssert.fail("Email Template dropdown has no data to select");
			}
			else
			{
			getAction().waitFor(2000);	
			int emailTemplateRandom = util.randomGenerator(emailTemplateCount);
			getAction().focus(EMAIL_TEMPLATE_DROPDOWN);
			getAction().click(EMAIL_TEMPLATE_DROPDOWN);
			getAction().waitFor(10000);			
			String emailTemplateName=getAction().getText(EMAIL_TEMPLATE_DROPDOWN_OPTION.format(emailTemplateRandom));
			Logger.log("Select "+emailTemplateName+" Email Template at Index No. "+emailTemplateRandom,TestStepType.STEP);
			AjaxCondition.forElementVisible(EMAIL_TEMPLATE_DROPDOWN_OPTION.format(emailTemplateRandom)).waitForResponse();
			getContext().put("adjustmentOption", emailTemplateName);
			getAction().click(EMAIL_TEMPLATE_DROPDOWN_OPTION.format(emailTemplateRandom));
			}
			    	
    	Logger.log("Verify if \"To\" Field is Visible",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(TO_FIELD).waitForResponse();
    	
    	Logger.log("Verify if \"Subject\" Field is Visible",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(SUBJECT_FIELD).waitForResponse();
    	
    	Logger.log("Verify if \"Email Template\" Text Area is Visible",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(EMAIL_TEMPLATE_TEXTAREA).waitForResponse();
    	
    	Logger.log("Verify if \"Cancel\" Button is Visible",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(CANCEL_BUTTON).waitForResponse();
    	
    	Logger.log("Verify if \"Submit\" Button is Visible",TestStepType.VERIFICATION_SUBSTEP);
    	AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
   
		return this;
    }
    
    public EmailTemplatePopUp contactMarketPlaceSeller(){
    	verifyEmailTemplatePopUp();
    	Logger.log("Click \"Submit\" Button",TestStepType.VERIFICATION_SUBSTEP);
    	getAction().scrollTo(SUBMIT_BUTTON);
    	getAction().click(SUBMIT_BUTTON);
		return this;
	}
    
    public EmailTemplatePopUp contactVendor(){
    	verifyEmailTemplatePopUp();
    	Logger.log("Click \"Submit\" Button",TestStepType.VERIFICATION_SUBSTEP);
    	getAction().scrollTo(SUBMIT_BUTTON);
    	getAction().click(SUBMIT_BUTTON);
		return this;
	}
}