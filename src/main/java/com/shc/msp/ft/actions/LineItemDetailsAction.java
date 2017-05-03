package com.shc.msp.ft.actions;

import java.text.ParseException;

import com.shc.automation.Logger;
import com.shc.automation.utils.TestUtils;
import com.shc.automation.utils.TestUtils.Feature;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.modules.ActionDropdown.SelectPage;
import com.shc.msp.ft.util.Retrieval_Test_Data_By_Query;

public class LineItemDetailsAction extends BaseAction {

	@Override
	protected Feature feature() {
        return Feature.BROWSE;
    }

    public LineItemDetailsAction(SiteFactory factory) {
        super(factory);
    }
    
    
    @Override
	public LineItemDetailsAction addlogType(TestUtils.TestStepType testStepType)
	{
		super.addlogType(testStepType);
		return this;
	}
    
    public LineItemDetailsAction verifyOptionNotVisible(String optionName) {
    	Logger.log(optionName+" - option should not be visible in action dropdown",TestStepType.THEN);
        this.factory.actionDropdown().verifyOptionNotVisible(SelectPage.LINE_ITEM_DETAILS_PAGE, optionName);
        return this;
    }
	
	public LineItemDetailsAction verifyOptionVisible(String optionName) {
		Logger.log(optionName+" - option should be visible in action dropdown",TestStepType.THEN);
        this.factory.actionDropdown().verifyOptionVisible(SelectPage.LINE_ITEM_DETAILS_PAGE, optionName);
        return this;
    }
	
    public LineItemDetailsAction clickOnFulfillByUnderLineItemSummary() {
    	Logger.log("Agent is in lineitem summary and clicks on fulfull by link",TestStepType.GIVEN);
        this.factory.lineItemDetailsPage().clickOnFulfillByUnderLineItemSummary();
        return this;
    }

    public LineItemDetailsAction verifyVendorDetailsPageDisplayed() {
    	Logger.log("Vendor details should be displayed",TestStepType.THEN);
        this.factory.vendorDetailsPage().verifyVendorDetailsPageDisplayed();
        return this;
    }
    public LineItemDetailsAction verifyVendorDetailsPageContents() {
    	Logger.log("Vendor details poup mail labels are displayed",TestStepType.THEN);
        this.factory.vendorDetailsPage().verifyVendorDetailsPageContents();
        return this;
    }
    
 
    public LineItemDetailsAction cancelLineItem() {
    	Logger.log("Agent should be able to cancel the order",TestStepType.THEN);
        this.factory.orderdetailspage().cancelLineItem();
        return this;
    }
    
    public LineItemDetailsAction selectAction(String actionName) {
        this.factory.actionDropdown().selectAction(SelectPage.LINE_ITEM_DETAILS_PAGE, actionName);
        return this;
	}
    
    public LineItemDetailsAction verifyEmailTemplatePopUp() {
    	Logger.log("Email template should be displayed to the agent",TestStepType.THEN);
        this.factory.emailTemplatePopUp().verifyEmailTemplatePopUp();
        return this;	
	}
    public LineItemDetailsAction contactVendor() {
    	Logger.log("Verify Email template and send Email to Vendor",TestStepType.THEN);
        this.factory.emailTemplatePopUp().contactVendor();
        return this;	
	}
    public LineItemDetailsAction verifyStartAutomatedReturnEligible() {
    	Logger.log("Agent should be able to process Start Automated return action",TestStepType.THEN);
    	this.factory.orderdetailspage().verifyStartAutomatedReturnEligible();
        return this;
    }
    public LineItemDetailsAction verifyStartAutomatedReturnNonEligible() {
    	Logger.log("Agent should NOT be able to process Start Automated return action",TestStepType.THEN);
    	this.factory.orderdetailspage().verifyStartAutomatedReturnNonEligible();
        return this;
    }
    
    public LineItemDetailsAction fillReturnItemPopUp(int quantity) {
    	Logger.log("Agent should be able to enter reason and proceed with return action",TestStepType.THEN);
        this.factory.orderdetailspage().fillReturnItemPopUp(quantity);
        return this;
	}
    
    public LineItemDetailsAction verifyTrialBalance() {
        this.factory.orderdetailspage().verifyTrialBalance();
        return this;	
	}
    
    public LineItemDetailsAction saleAdjustmentLineitem(String adjusttaxOption,Double amount) {
		this.factory.orderdetailspage().saleAdjustmentLineitem(adjusttaxOption,amount);
		return this;
	}
    
    public LineItemDetailsAction verifyUpdateExpectedShipArrivalDatePopUp() {
    	Logger.log("Agent should be able to update expected ship arrival date", TestUtils.TestStepType.THEN);
        this.factory.updateExpectedShipArrivalDatePopUp().verifyUpdateExpectedShipArrivalDatePopUp();
        return this;	
	}
    
    public LineItemDetailsAction UpdateExpectedShipArrivalDate() {
    	Logger.log("Update expected ship arrival date", TestUtils.TestStepType.THEN);
        this.factory.updateExpectedShipArrivalDatePopUp().UpdateExpectedShipArrivalDate();
        return this;	
	}
    
    public LineItemDetailsAction verifyReturnTrackingInformationPopUp() {
    	Logger.log("Return tracking information popup should be displayed", TestUtils.TestStepType.THEN);
        this.factory.returnTrackingInformationPopUp().verifyReturnTrackingInformationPopUp();
        return this;	
	}
    
    public LineItemDetailsAction verifyScheduleReturnPopUp() {
    	Logger.log("Schedule return popup should be displayed", TestUtils.TestStepType.THEN);
        this.factory.scheduleReturnPopUp().verifyScheduleReturnPopUp();
        return this;	
	}

	public LineItemDetailsAction verifylineItemSummary(int saleschecknumber) throws ParseException {
		Logger.log("Summary of the line item should be displayed", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().lineItemSummaryVerify(saleschecknumber);
        return this;
	}
	
	public LineItemDetailsAction veryItemConditionInlineItemSummary(int saleschecknumber,String itemCondition) throws ParseException {
		Logger.log("Summary of the line item should be displayed", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().veryItemConditionInlineItemSummary(saleschecknumber,itemCondition);
        return this;
	}
	
	

	/*public LineItemDetailsAction lineItemNumberVerify() {
		this.factory.lineItemDetailsPage().lineItemNumberVerify();
        return this;
	}*/

	public LineItemDetailsAction verifyShippingInfo(int saleschecknumber) {
		Logger.log("Shipping information should be displayed", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().lineItemShippingInfoVerify(saleschecknumber);
        return this;
	}

	public LineItemDetailsAction verifyTrackingDetails() {
		Logger.log("Tracking details should be displayed", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().verifyTrackingDetails();
        return this;
	}

	public LineItemDetailsAction verifyReturnInformation(int saleschecknumber) throws ParseException {
		Logger.log("Return information should be displayed", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().verifyReturnInformation(saleschecknumber);
        return this;
	}

	public LineItemDetailsAction verifyReturnTrackingInfo(int saleschecknumber) throws ParseException {
		Logger.log("Tracking information should be displayed", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().verifyReturnTrackingInfo(saleschecknumber);
		return this;
	}

	public LineItemDetailsAction  verifyInstallationInformation(String orderId, int saleschecknumber) throws ParseException {
		Logger.log("Installation details should be displayed", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().verifyInstallationInformation(orderId,saleschecknumber);
		return this;
		
	}

	public LineItemDetailsAction verifyGiftCardInformation() {
		Logger.log("Gift card details should be displayed", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().verifyGiftCardInformation();
		return this;
	}

	public LineItemDetailsAction verifyDiscountsOnLineItem(String orderID) {
		Logger.log("Discount(s) at line item level should be displayed", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().verifyDiscountsOnLineItem(orderID);
		return this;
	}

	public LineItemDetailsAction  verifyAdjustmentsOnLineItem(String OrderID) throws ParseException {
		Logger.log("Adjustments for order should be displayed", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().verifyAdjustmentsOnLineItem(OrderID);
		return this;
	}
	public LineItemDetailsAction  verify_Reschedule_Delivery()  {
		Logger.log("Agent should be able to reschedule delivery", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().verify_Reschedule_Delivery();
		return this;
	}
	
	public LineItemDetailsAction  lineItemSummaryPageVerify(int i, String orderId,String storeId) throws ParseException {
		Logger.log("Line item level details including shipping, price, environmental fee etc should be displayed", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().lineItemSummaryPageVerify(i, orderId, storeId);
		return this;
	}

	public LineItemDetailsAction  contact_Marketplace_Seller_Data() throws Exception {
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().contact_Marketplace_Seller_Data();
		return this;
	}

	public LineItemDetailsAction contact_Vendor_Data() throws Exception {
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().contact_Vendor_Data();
		return this;
	}

	public LineItemDetailsAction  update_Expected_Ship_Arrival_Date_Data() throws Exception {
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().update_Expected_Ship_Arrival_Date_Data() ;
		return this;
	}

	public LineItemDetailsAction return_Tracking_Information_Data() throws Exception {
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().return_Tracking_Information_Data() ;
		return this;
	}

	public  LineItemDetailsAction contact_Customer_Data() throws Exception {
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().contact_Customer_LineItem_Data();
		return this;
	}

	public LineItemDetailsAction reschedule_Delivery_Data() throws Exception {
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().reschedule_Delivery_Data();
		return this;
	}

	public LineItemDetailsAction schedule_Return_Data() throws Exception {
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().schedule_Return_Data();
		return this;
		
	}

	public LineItemDetailsAction  verifyOptionIsNotVisible(String optionName) {
		Logger.log(optionName+" should not be visible", TestUtils.TestStepType.THEN);
		this.factory.lineItemDetailsPage().verifyOptionIsNotVisible(optionName);
		return this;
	}

	public LineItemDetailsAction sale_Adjustment_Line_Item_Data() throws Exception {
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().sale_Adjustment_Line_Item_Data();
		return this;
		
	}

	public LineItemDetailsAction Line_Item_Cancellation_Data() throws Exception {
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().Line_Item_Cancellation_Data();
		return this;
	}

	public LineItemDetailsAction Line_Item_Return_Item_Data() throws Exception {
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().Line_Item_Return_Item_Data();
		return this;
	}

	public LineItemDetailsAction Line_Item_Start_Automated_Return_Data() throws Exception {
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().Line_Item_Start_Automated_Return_Data();
		return this;
	}
	public LineItemDetailsAction verifyReturnSuccess(String sku) {
    	Logger.log("status check when return happens", TestUtils.TestStepType.THEN);
    	  this.factory.orderdetailspage().verifyReturnSuccess(sku);
        return this;
	}
	
	
	
}