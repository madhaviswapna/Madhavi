package com.shc.msp.ft.pages;

import java.util.Random;

import com.shc.automation.AjaxCondition;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.PageAssert;
import com.shc.automation.SoftAssert;
import com.shc.automation.utils.TestUtils.CheckLocatorFor;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.util.Utility;


public class VendorDetailsPage extends Page {
	
	public VendorDetailsPage(SiteFactory factory) {
        super(factory);
    }

    protected String getPageName() {
        return "Vendor Details Page";
    }

    protected String pageName() {
        return "Vendor Details Page";
    }

    Utility util = new Utility();
    public static final Locator VENDOR_DETAILS_PAGE= new Locator("VENDOR_DETAILS_PAGE", "//h4[contains(text(),'Vendor Details')]/ancestor::div[@class='modal-content']", "Vendor Details Page");
    public static final Locator MANAGE_QUEUES= new Locator("Manage queues", "//span[contains(text(),'Manage Queues')]", "Manage queues");
    public static final Locator MANAGE_ROLES= new Locator("MANAGE_ROLES", "//span[contains(text(),'Manage Roles')]", "MANAGE_ROLES");
    public static final Locator MANAGE_ROLES_PAGE= new Locator("MANAGE_ROLES_PAGE", "//h3[contains(text(),'Manage Roles')]", "MANAGE_ROLES_PAGE");
    public static final Locator ROLE= new Locator("ROLE", "//a[contains(text(),'{0}')]", "ROLE");
    //public static final Locator ADMIN_ROLE_PRIVILEGE= new Locator("ADMIN_ROLE_PRIVILAGE", "(//div[contains(@ng-repeat,'role in roles ')])[1]//li[contains(text(),'{0}')]", "ADMIN_ROLE_PRIVILAGE");
    public static final Locator ASSIGNED_ROLE_PRIVILEGE= new Locator("ASSIGNED_ROLE_PRIVILEGE","//legend[contains(.,'{0}')]/following-sibling::div//li[contains(text(),'{1}')]","ASSIGNED_ROLE_PRIVILEGE");
    
    public static final Locator CHECKED_ROLE= new Locator("CHECKED_ROLE", "//span[@ng-if='privilege.checked']/input", "CHECKED_ROLE");
    public static final Locator UNCHECKED_ROLE= new Locator("UNCHECKED_ROLE", "//span[@ng-if='!privilege.checked']/input", "UNCHECKED_ROLE");
    
    public static final Locator UNCHECKED_PRIVILEGE= new Locator("UNCHECKED_PRIVILEGE", "(//span[@ng-if='!privilege.checked']/input){0}", "UNCHECKED_PRIVILEGE");
    public static final Locator ASSIGNED_PRIVILEGE= new Locator("ASSIGNED_PRIVILEGE","//input[@value='{0}']","ASSIGNED_PRIVILEGE");
    
    public static final Locator QUEUE_NAME= new Locator("queue name", "//input[@id='queueName']", "queue name");
    public static final Locator SEARCH_BUTTON= new Locator("searchbutton", "   //button[contains(@class,'search')]", "searchbutton");
    public static final Locator SUBMIT_BUTTON= new Locator("SUBMIT_BUTTON", "//button[contains(text(),'Submit')]", "SUBMIT_BUTTON");
    public static final Locator EDIT_BUTTON= new Locator("edit button", " (//a[contains(text(),'Edit')])[1]", "edit button");
    public static final Locator QUEUE_PRIORITY_DROPDOWN= new Locator("Priority dropdown", "//select[contains(@ng-options,'Selectedpriority ')]{0}", "Priority dropdown");
    public static final Locator QUEUE_DAY_DROPDOWN= new Locator("Day dropdown", "//select[contains(@ng-options,'Selectedday ')]{0}", "Day dropdown");
    public static final Locator QUEUE_HOUR_DROPDOWN= new Locator("Hour dropdown", "//select[contains(@ng-options,'Selectedtime')]{0}", "Hour dropdown");
    public static final Locator QUEUE_MINUTE_DROPDOWN= new Locator("Minute dropdown", "//select[contains(@ng-options,'Selectedminute')]{0}", "Minute dropdown");
    public static final Locator SAVE_BUTTON= new Locator("save button", "//a[contains(text(),'Save')]", "save button");
    public static final Locator SUCCESS= new Locator("Success", "//h4[contains(text(),' Success')]", "Success message");
    public static final Locator OK_BUTTON= new Locator("ok button", "//button[contains(text(),'OK')]", "ok button");
    public static final Locator SEARCH_LAYAWAY= new Locator("SEARCH_LAYAWAY", "//a[@ui-sref='search-layaway' ]", "Search Layaway");
    public static final Locator MANAGE_VENDOR= new Locator("Manage Vendor", "//span[contains(text(),'Manage Vendor')]", "Manage Vendor");
    public static final Locator VENDOR_SEARCH_BUTTON= new Locator("Vendor search button", "//button[@class='btn btn-sm btn-primary']", "Vendor search button");
    public static final Locator RESULT_VENDOR_ID= new Locator("Result VendorID", "//tr[@class='ng-scope']/td", "Result VendorID");
    public static final Locator VENDOR_ID= new Locator("VendorID", "  //input[@id='vendorId']", "VendorID");
    public final Locator LAYAWAY_SEARCH_BUTTON = new Locator("search button", "  //button[@class='btn btn-sm btn-primary']","search Button");
    public static final Locator CONTRACT_ID= new Locator("CONTRACT_ID", " //input[@class='form-control input-sm ng-pristine ng-valid' and @id = 'contractId']", "Contract ID");
    public static final Locator LAYAWAY_CONTRACT_INFORMATION= new Locator("layaway contract information tab", "//legend[text()='Layaway Contract Information']", "layaway contract information tab");
    public final Locator MENU_BUTTON = new Locator("", "//div[@class='fa fa-bars header-icon']","Menu Button");
    public final Locator VENDOR_DETAILS_PAGE_LEGENDS = new Locator("VENDOR_DETAILS_PAGE_LEGENDS", "//legend[contains(text(),'{0}')]","Menu Button");
    public final Locator VENDOR_DETAILS_PAGE_CONTENTS = new Locator("VENDOR_DETAILS_PAGE_CONTENTS", "//Label[contains(text(),'{0}')]/parent::*/div/p","VENDOR_DETAILS_PAGE_CONTENTS");
    public final Locator VENDOR_FORM = new Locator("VENDOR_FORM", "//*[@class='form-horizontal ng-pristine ng-valid']","VENDOR_FORM");
  
    
    public VendorDetailsPage verifyVendorDetailsPageDisplayed() {
    	Logger.log("Verify if vendor details page is displayed", TestStepType.VERIFICATION_STEP);
    	AjaxCondition.forElementVisible(VENDOR_DETAILS_PAGE).waitForResponse();             
    	return this;
    }
    public VendorDetailsPage verifyVendorDetailsPageContents() {
    	Logger.log("verify in VendorDetails popup main headings are displayed like Vendor Details,Return Address,Primary Contact,Alt Vendor Contact 1 etc", TestStepType.VERIFICATION_STEP);
    	AjaxCondition.forElementVisible(VENDOR_DETAILS_PAGE_LEGENDS.format("Vendor Details")).waitForResponse();
    	AjaxCondition.forElementVisible(VENDOR_DETAILS_PAGE_LEGENDS.format("Return Address")).waitForResponse();
    	AjaxCondition.forElementVisible(VENDOR_DETAILS_PAGE_LEGENDS.format("Primary Contact")).waitForResponse();
    	AjaxCondition.forElementVisible(VENDOR_DETAILS_PAGE_LEGENDS.format("Alt Vendor Contact 1")).waitForResponse();
    	AjaxCondition.forElementVisible(VENDOR_DETAILS_PAGE_LEGENDS.format("Order Related Inquiry")).waitForResponse();
    	AjaxCondition.forElementVisible(VENDOR_DETAILS_PAGE_LEGENDS.format("Alt Vendor Contact 2")).waitForResponse();
    	
    	getAction().click(VENDOR_FORM);
    	//verify vendor id is displayed
    	System.out.println("-----------------------------------------VENDOR_DETAILS_PAGE_CONTENTS:"+VENDOR_DETAILS_PAGE_CONTENTS.format("Vendor ID").getValue());
    	AjaxCondition.forElementVisible(VENDOR_DETAILS_PAGE_CONTENTS.format("Vendor ID")).waitForResponse();
    	String vendorID=getAction().getText(VENDOR_DETAILS_PAGE_CONTENTS.format("Vendor ID"));
    	
    	//String regex = "[0-9]";
    	System.out.println("--------------------------------------------------------------"+vendorID);
    	System.out.println("--------------------------------------------------------------"+vendorID.length());
    	SoftAssert.checkConditionAndContinueOnFailure("Verify vendor id is displayed",vendorID.length()>0);
    	
    	//verify vendor name is displayed
    	AjaxCondition.forElementVisible(VENDOR_DETAILS_PAGE_CONTENTS.format("Vendor Name")).waitForResponse();
    	String vendorName=getAction().getText(VENDOR_DETAILS_PAGE_CONTENTS.format("Vendor Name"));
    	//regex = "[a-zA-Z]";
    	System.out.println("--------------------------------------------------------------"+vendorName);
    	System.out.println("--------------------------------------------------------------"+vendorName.length());
    	SoftAssert.checkConditionAndContinueOnFailure("Verify vendor name is displayed",vendorName.length()>0);
    	
    	return this;
    }
    
    
    public VendorDetailsPage verifyManageQueues(String queueName) {
    	Logger.log("Verify if queue priority changing", TestStepType.VERIFICATION_STEP);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(MENU_BUTTON).waitForResponse();  
    	getAction().click(MENU_BUTTON);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(MANAGE_QUEUES).waitForResponse();  
    	getAction().click(MANAGE_QUEUES);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(QUEUE_NAME).waitForResponse();  
    	getAction().click(QUEUE_NAME);
    	getAction().type(QUEUE_NAME,queueName);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse();  
    	getAction().click(SEARCH_BUTTON);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(EDIT_BUTTON).waitForResponse();  
    	getAction().click(EDIT_BUTTON);
    	getAction().waitFor(2000);
    	
    	
    	AjaxCondition.forElementVisible(QUEUE_PRIORITY_DROPDOWN.format("")).waitWithoutException(3000);  
    	int rndPriorityOption = util.randomGenerator(getAction().getElementCount(QUEUE_PRIORITY_DROPDOWN.format("/option")));  	
    	setData("Priority", getAction().getSelectedLabel(QUEUE_PRIORITY_DROPDOWN.format("")));
    	Logger.log("Select option # "+rndPriorityOption+" in the Priority dropdown", TestStepType.STEP);
    	getAction().selectUsingIndex(QUEUE_PRIORITY_DROPDOWN.format(""), rndPriorityOption);
    	
    	AjaxCondition.forElementVisible(QUEUE_DAY_DROPDOWN.format("")).waitWithoutException(3000);  
    	int rndDayOption = util.randomGenerator(getAction().getElementCount(QUEUE_DAY_DROPDOWN.format("/option")));  	
    	setData("Day", getAction().getSelectedLabel(QUEUE_DAY_DROPDOWN.format("")));
    	Logger.log("Select option # "+rndDayOption+" in the Day dropdown", TestStepType.STEP);
    	getAction().selectUsingIndex(QUEUE_DAY_DROPDOWN.format(""), rndDayOption);
    	
    	AjaxCondition.forElementVisible(QUEUE_HOUR_DROPDOWN.format("")).waitWithoutException(3000);  
    	int rndHourOption = util.randomGenerator(getAction().getElementCount(QUEUE_HOUR_DROPDOWN.format("/option")));  
    	setData("Hour", getAction().getSelectedLabel(QUEUE_HOUR_DROPDOWN.format("")));
    	Logger.log("Select option # "+rndHourOption+" in the Hour dropdown", TestStepType.STEP);
    	getAction().selectUsingIndex(QUEUE_HOUR_DROPDOWN.format(""), rndHourOption);
    	
    	AjaxCondition.forElementVisible(QUEUE_MINUTE_DROPDOWN.format("")).waitWithoutException(3000);  
    	int rndMinuteOption= util.randomGenerator(getAction().getElementCount(QUEUE_MINUTE_DROPDOWN.format("/option")));  
    	setData("Minute", getAction().getSelectedLabel(QUEUE_MINUTE_DROPDOWN.format("")));
    	Logger.log("Select option # "+rndMinuteOption+" in the Minute dropdown", TestStepType.STEP);
    	getAction().selectUsingIndex(QUEUE_MINUTE_DROPDOWN.format(""), rndMinuteOption);

    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse();  
    	getAction().click(SAVE_BUTTON);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(SUCCESS).waitForResponse();
    	AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse();  
    	getAction().click(OK_BUTTON);
    	return this;
    }
    
    public VendorDetailsPage resetQueueValues(){
    	getAction().waitFor(2000);
    	String oldPriority = (String) getData("Priority");
    	String oldDay = getDataString("Day");
    	String oldHour = getDataString("Hour");
    	String oldMinute =getDataString("Minute");
    	Logger.log("Click on the Edit button", TestStepType.STEP);
    	AjaxCondition.forElementVisible(EDIT_BUTTON).waitForResponse();  
    	getAction().click(EDIT_BUTTON);
    	
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(QUEUE_PRIORITY_DROPDOWN.format("")).waitForResponse();  
    	getAction().click(QUEUE_PRIORITY_DROPDOWN.format("/option[@value='"+oldPriority+"']"));
    	
    /*	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(QUEUE_DAY_DROPDOWN.format("")).waitForResponse();  
    	getAction().click(QUEUE_DAY_DROPDOWN.format("/option[@value='"+oldDay+"']"));
    	
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(QUEUE_HOUR_DROPDOWN.format("")).waitForResponse();  
    	System.out.println(QUEUE_HOUR_DROPDOWN.format("/option[@value='"+oldHour+"']").getValue());
    	getAction().click(QUEUE_HOUR_DROPDOWN.format("/option[@value='"+oldHour+"']"));
    	
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(QUEUE_MINUTE_DROPDOWN.format("")).waitForResponse();  
    	getAction().click(QUEUE_MINUTE_DROPDOWN.format("/option[@value='"+oldMinute+"']"));*/
    	
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse();  
    	getAction().click(SAVE_BUTTON);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(SUCCESS).waitForResponse();
    	AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse();  
    	getAction().click(OK_BUTTON);
    	
    	return this;
    }
    
    public VendorDetailsPage verifyPrivelegeAssignmentToRole(String role) {
    	Logger.log("Verify Privilege can be assigned to the Agent Role:- "+role, TestStepType.STEP);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(MENU_BUTTON).waitForResponse();  
    	getAction().click(MENU_BUTTON);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(MANAGE_ROLES).waitForResponse();  
    	getAction().click(MANAGE_ROLES);
    	Logger.log("Click on the Menu followed by Manage Roles", TestStepType.STEP);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(MANAGE_ROLES_PAGE).waitForResponse(); 
    	AjaxCondition.forElementVisible(ROLE.format(role)).waitForResponse();
    	getAction().scrollTo(ROLE.format(role));
    	Logger.log("Click on Role:- "+role, TestStepType.STEP);
    	getAction().click(ROLE.format(role));
    	
    	int privlegeCount = getAction().getElementCount(UNCHECKED_PRIVILEGE.format(""));
    	Random rand = new Random();
    	Integer index = rand.nextInt(privlegeCount);
    	String randomUncheckedPrivlege = "["+index.toString()+"]";
    	String newPrivilege=getAction().getAttribute(UNCHECKED_PRIVILEGE.format(randomUncheckedPrivlege),"value");
    	setData("newPrivilege", newPrivilege);
    	Logger.log("Click on Checkbox:- "+newPrivilege, TestStepType.STEP);
    	getAction().click(UNCHECKED_PRIVILEGE.format(randomUncheckedPrivlege));
    	Logger.log("Click on Submit Button", TestStepType.STEP); 	
    	AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();  
    	getAction().click(SUBMIT_BUTTON);
    	getAction().waitFor(2000);
    	SoftAssert.checkElementAndContinueOnFailure(ASSIGNED_ROLE_PRIVILEGE.format(role,newPrivilege), "Verify the privlege "+newPrivilege+"is added to the role "+role, CheckLocatorFor.isVisible);
    	
    	return this;
    }
    
    public VendorDetailsPage resetPrivelegeAssignmentToRole(String role){
    	Logger.log("Reset privilege assigned to the Agent Role:- "+role, TestStepType.STEP);
    	getAction().waitFor(2000);
    	getAction().scrollTo(ROLE.format(role));
    	Logger.log("Click on Role:- "+role, TestStepType.STEP);
    	getAction().click(ROLE.format(role));
    	String newPrivilege= (String)getData("newPrivilege");
    	getAction().waitFor(2000);
    	Logger.log("Uncheck the checkbox:- "+newPrivilege, TestStepType.STEP);
    	getAction().scrollTo(ASSIGNED_PRIVILEGE.format(newPrivilege));
    	getAction().click(ASSIGNED_PRIVILEGE.format(newPrivilege));
    	Logger.log("Click on Submit Button", TestStepType.STEP); 	
    	AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();  
    	getAction().click(SUBMIT_BUTTON);
    	getAction().waitFor(2000);
    	SoftAssert.checkElementAndContinueOnFailure(ASSIGNED_ROLE_PRIVILEGE.format(role,newPrivilege), "Verify the privlege "+newPrivilege+"is added to the role "+role, CheckLocatorFor.isNotVisible);
    	return this;
    }

    public VendorDetailsPage VerifyLayawayContractDetails(String contractID){
       	Logger.log("Verify if Layaway Contract Details is displayed", TestStepType.VERIFICATION_STEP);
       	getAction().waitFor(4000);
       	AjaxCondition.forElementVisible(MENU_BUTTON).waitForResponse(); 
       	getAction().click(MENU_BUTTON);
       	getAction().waitFor(4000);
       	AjaxCondition.forElementVisible(SEARCH_LAYAWAY).waitForResponse();
       	getAction().click(SEARCH_LAYAWAY);
       	getAction().waitFor(3000);
       	AjaxCondition.forElementVisible(CONTRACT_ID).waitForResponse();
       	getAction().type(CONTRACT_ID, contractID);
       	getAction().waitFor(4000);
       	AjaxCondition.forElementVisible(LAYAWAY_SEARCH_BUTTON).waitForResponse();
       	getAction().click(LAYAWAY_SEARCH_BUTTON);
       	getAction().waitFor(4000);
       	AjaxCondition.forElementVisible(LAYAWAY_CONTRACT_INFORMATION).waitForResponse();
       	
       	
       	return this;
       }

    public VendorDetailsPage VerifyVendorDetails(String VendorID){
     	Logger.log("Verify if Vendor details are displayed", TestStepType.VERIFICATION_STEP);
    	getAction().waitFor(2000);
       	AjaxCondition.forElementVisible(MENU_BUTTON).waitForResponse(); 
       	getAction().click(MENU_BUTTON);
       	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(MANAGE_VENDOR).waitForResponse(); 
       	getAction().click(MANAGE_VENDOR);
       	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(VENDOR_ID).waitForResponse();
       	getAction().waitFor(2000);
    	getAction().type(VENDOR_ID, VendorID);
      	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(VENDOR_SEARCH_BUTTON).waitForResponse(); 
       	getAction().click(VENDOR_SEARCH_BUTTON);
      	getAction().waitFor(2000);
      	PageAssert.textPresentIn(RESULT_VENDOR_ID, VendorID);
      	
      	
    	return this;
    }

}