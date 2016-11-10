package com.shc.msp.ft.modules;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.shc.automation.AjaxCondition;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.PageAssert;
import com.shc.automation.SoftAssert;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;


public class ActionDropdown extends Module {
	
	public ActionDropdown(SiteFactory factory) {
		super(factory);
	}
	
	protected String getPageName() {
        return "Action Dropdown";
    }

    protected String pageName() {
        return "Action Dropdown";
    }

    
    public static final Locator ACTION_DROPDOWN= new Locator("ACTION_DROPDOWN", "(//select[@class='input-sm ng-pristine ng-valid'])[1]", "Action Dropdown");
    public static final Locator GO_BUTTON= new Locator("GO_BUTTON", "(//button[contains(text(),'Go')])[2]", "Go Button");
    public static final Locator WARNING_MSG= new Locator("WARNING_MSG", "//div[@ng-bind-html='msg']", "Waring msg");
    public static final Locator WARNING_MSG_OK= new Locator("WARNING_MSG_OK", "//button[contains(.,'OK')]", "Waring msg ok");
	public final Locator ORDERDETAILS_ACTION_GO_BUTTON = new Locator("", "//div[@ng-if='actions']//button[contains(.,'Go')]", "Select action Go button");
	public static final Locator SALES_CHECK_NUMBER= new Locator("SALES_CHECK_NUMBER", "//a[@class='spacetwo ng-binding']", "Sales Check Number");
	public static final Locator SALES_CHECK_DETAIL_PAGE= new Locator("SALES_CHECK_DETAIL_PAGE", "//div[@id='salesCheckDiv']", "Sales Check Detail Page");
	public static final Locator SKU_NUMBER= new Locator("SKU_NUMBER", "(//a[@ng-click='viewLineItem(data.summary.orderID, item.itemId, item.storeId)'])[{0}]", "SKU Number");
	public static final Locator LINE_ITEM_DETAIL_PAGE= new Locator("LINE_ITEM_DETAIL_PAGE", "//div[@id='lineItemSalesCheckSection']", "Line Item Detail Page");
	
    
    
    public enum SelectPage {
		SUMMARY_PAGE("summaryAction"),
		LINE_ITEM_DETAILS_PAGE("lineItemAction"),
		SALES_CHECK_DETAILS_PAGE("salesCheckAction");
		private String value;

		private SelectPage(String value) {
		this.value = value;
		}

		public String getValue() {
		return this.value;
		}
	}
    
    
    public ActionDropdown verifyOptionNotVisible(SelectPage pageName, String optionName){
		   getAction().waitFor(1000);
		   Logger.log("Verify if "+optionName+" option is present in action dropdown",TestStepType.VERIFICATION_STEP);
		   if(AjaxCondition.forElementVisible(ACTION_DROPDOWN).waitWithoutException(10)){
			   AjaxCondition.forElementVisible(ACTION_DROPDOWN.format(pageName.getValue())).waitForResponse();
			   PageAssert.textNotPresentIn(ACTION_DROPDOWN.format(pageName.getValue()),optionName);
		   }
		   else{
			   Logger.log("Action dropdown is not present in the page",TestStepType.VERIFICATION_STEP);
		   }
		return this;
	   }
    public ActionDropdown verifyOptionVisible(SelectPage pageName, String optionName){
    	getAction().waitFor(3000);
		   Logger.log("Verify if "+optionName+" Option is Present in Action Dropdown",TestStepType.VERIFICATION_STEP);
		   AjaxCondition.forElementVisible(ACTION_DROPDOWN.format(pageName.getValue())).waitForResponse();
		  
		   PageAssert.textPresentIn(ACTION_DROPDOWN.format(pageName.getValue()), optionName);  
		   selectAction(pageName, optionName);
		     
		   return this;
	   }
   
    public ActionDropdown selectAction(SelectPage pageName, String actionName){
		   
		   Logger.log("Click on "+actionName+" Option in Action Dropdown",TestStepType.STEP);
		   getAction().scrollTo(ACTION_DROPDOWN.format(pageName.getValue()));
		   AjaxCondition.forElementVisible(ACTION_DROPDOWN.format(pageName.getValue())).waitForResponse();
		   PageAssert.textPresentIn(ACTION_DROPDOWN.format(pageName.getValue()), actionName);
		   getAction().selectValueInDropDown(ACTION_DROPDOWN.format(pageName.getValue()), actionName);
				   
		   Logger.log("Click on 'Go' Button",TestStepType.STEP);
		   getAction().waitFor(3000);
System.out.println("pageName="+pageName.getValue());
			//if(AjaxCondition.forElementVisible(GO_BUTTON.format(pageName.getValue())).waitForResponse())
 if(AjaxCondition.forElementVisible(GO_BUTTON).waitWithoutException(2))
				getAction().click(GO_BUTTON);   
			else 
			{
				getAction().waitFor(3000);
				if(AjaxCondition.forElementVisible(ORDERDETAILS_ACTION_GO_BUTTON).waitWithoutException(1))
					getAction().click(ORDERDETAILS_ACTION_GO_BUTTON);
			
			}
			  
		   return this;		  		  
	   }

}