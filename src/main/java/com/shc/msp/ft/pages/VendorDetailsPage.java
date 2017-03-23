package com.shc.msp.ft.pages;

import com.shc.automation.AjaxCondition;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.PageAssert;
import com.shc.automation.SoftAssert;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;


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

    
    public static final Locator VENDOR_DETAILS_PAGE= new Locator("VENDOR_DETAILS_PAGE", "//h4[contains(text(),'Vendor Details')]/ancestor::div[@class='modal-content']", "Vendor Details Page");
    public static final Locator MANAGE_QUEUES= new Locator("Manage queues", "//span[contains(text(),'Manage Queues')]", "Manage queues");
    public static final Locator MANAGE_ROLES= new Locator("MANAGE_ROLES", "//span[contains(text(),'Manage Roles')]", "MANAGE_ROLES");
    public static final Locator MANAGE_ROLES_PAGE= new Locator("MANAGE_ROLES_PAGE", "//h3[contains(text(),'Manage Roles')]", "MANAGE_ROLES_PAGE");
    public static final Locator ADMIN_ROLE= new Locator("ADMIN_ROLE", "//a[contains(text(),'ADMIN')]", "ADMIN_ROLE");
    public static final Locator ADMIN_ROLE_PRIVILAGE= new Locator("ADMIN_ROLE_PRIVILAGE", "(//div[contains(@ng-repeat,'role in roles ')])[1]//li[contains(text(),'{0}')]", "ADMIN_ROLE_PRIVILAGE");
    public static final Locator CHECKED_ROLE= new Locator("CHECKED_ROLE", "//span[@ng-if='privilege.checked']/input", "CHECKED_ROLE");
    public static final Locator UNCHECKED_ROLE= new Locator("UNCHECKED_ROLE", "//span[@ng-if='!privilege.checked']/input", "UNCHECKED_ROLE");
    public static final Locator QUEUE_NAME= new Locator("queue name", "//input[@id='queueName']", "queue name");
    public static final Locator SEARCH_BUTTON= new Locator("searchbutton", "   //button[contains(@class,'search')]", "searchbutton");
    public static final Locator SUBMIT_BUTTON= new Locator("SUBMIT_BUTTON", "//button[contains(text(),'Submit')]", "SUBMIT_BUTTON");
    public static final Locator EDIT_BUTTON= new Locator("edit button", " (//a[contains(text(),'Edit')])[1]", "edit button");
    public static final Locator PRIORITY_DROPDOWN= new Locator("Prority dropdown", "//select[contains(@ng-options,'Selectedpriority ')]", "Prority dropdown");
    public static final Locator PRIORITY_OPTION= new Locator("Priority option", " //select[contains(@ng-options,'Selectedpriority ')]/option[@value='7']", "priority option");
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
    
    
    public VendorDetailsPage ManageQueues() {
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
    	getAction().type(QUEUE_NAME, "demar");
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse();  
    	getAction().click(SEARCH_BUTTON);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(EDIT_BUTTON).waitForResponse();  
    	getAction().click(EDIT_BUTTON);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(PRIORITY_DROPDOWN).waitForResponse();  
    	getAction().click(PRIORITY_DROPDOWN);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(PRIORITY_OPTION).waitForResponse(); 
    	getAction().click(PRIORITY_OPTION);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse();  
    	getAction().click(SAVE_BUTTON);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(SUCCESS).waitForResponse();
    	AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse();  
    	getAction().click(OK_BUTTON);
    	return this;
    }
    public VendorDetailsPage manageRoles() {
    	Logger.log("Verify if role management can be done", TestStepType.VERIFICATION_STEP);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(MENU_BUTTON).waitForResponse();  
    	getAction().click(MENU_BUTTON);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(MANAGE_ROLES).waitForResponse();  
    	getAction().click(MANAGE_ROLES);
    	getAction().waitFor(2000);
    	AjaxCondition.forElementVisible(MANAGE_ROLES_PAGE).waitForResponse(); 
    	AjaxCondition.forElementVisible(ADMIN_ROLE).waitForResponse(); 
    	getAction().click(ADMIN_ROLE);
    	
    	AjaxCondition.forElementVisible(CHECKED_ROLE).waitForResponse();  
    	Logger.log("Unchecking the privilage: "+getAction().getText(CHECKED_ROLE)+"for admin", TestStepType.VERIFICATION_STEP);
    	getAction().click(CHECKED_ROLE);
    	AjaxCondition.forElementVisible(UNCHECKED_ROLE).waitForResponse();
    	String role=getAction().getText(UNCHECKED_ROLE);
    	Logger.log("selecting the privilage: "+role+"for admin", TestStepType.STEP);
    	getAction().click(UNCHECKED_ROLE);

    	AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();  
    	getAction().click(SUBMIT_BUTTON);
    	getAction().waitFor(2000);
    	
    	AjaxCondition.forElementVisible(ADMIN_ROLE_PRIVILAGE.format(role)).waitForResponse();
    	Logger.log("Verified that the added privilage is present in the privilage list", TestStepType.VERIFICATION_STEP);
    	
    	
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