package com.shc.msp.ft.actions;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.shc.automation.Logger;
import com.shc.automation.utils.TestUtils;
import com.shc.automation.utils.TestUtils.Feature;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.modules.ActionDropdown.SelectPage;
import com.shc.msp.ft.util.Retrieval_Test_Data_By_Query;

public class SalesCheckDetailsAction extends BaseAction {
	
	@Override
	protected Feature feature() {
        return Feature.BROWSE;
    }

    public SalesCheckDetailsAction(SiteFactory factory) {
        super(factory);
    }

    @Override
	public SalesCheckDetailsAction addlogType(TestUtils.TestStepType testStepType)
	{
		super.addlogType(testStepType);
		return this;
	}
    
    public SalesCheckDetailsAction verifyOptionNotVisible(String optionName) {
    	Logger.log(optionName+" should not be visible.",TestStepType.THEN);
        this.factory.actionDropdown().verifyOptionNotVisible(SelectPage.SALES_CHECK_DETAILS_PAGE, optionName);
        return this;
    }
	
	public SalesCheckDetailsAction verifyOptionVisible(String optionName) {
		Logger.log(optionName+" should be visible.",TestStepType.THEN);
        this.factory.actionDropdown().verifyOptionVisible(SelectPage.SALES_CHECK_DETAILS_PAGE, optionName);
        return this;
    }
	
	public SalesCheckDetailsAction cancelSalesCheck() {
		Logger.log("Agent should be able to cancel a salescheck",TestStepType.THEN);
        this.factory.orderdetailspage().cancelSalesCheck();
        return this;
    }
	
    public SalesCheckDetailsAction verifyStatus() {
        this.factory.salesCheckDetailsPage().verifyStatus();
        return this;
    }
       
    public SalesCheckDetailsAction selectAction(String actionName) {
    	Logger.log("Agent selects "+actionName,TestStepType.WHEN);
        this.factory.actionDropdown().selectAction(SelectPage.SALES_CHECK_DETAILS_PAGE, actionName);
        return this;
	}
    
    
    public SalesCheckDetailsAction verifyEmailTemplatePopUp() {
    	Logger.log("Email template should be displayed",TestStepType.THEN);
        this.factory.emailTemplatePopUp().verifyEmailTemplatePopUp();
        return this;	
	}
    
    public SalesCheckDetailsAction verifyUpdateSaleCheck() {
    	Logger.log("Agent should be able to update the salescheck",TestStepType.THEN);
        this.factory.salesCheckDetailsPage().verifyUpdateSaleCheck();
        return this;	
	}
    
    public SalesCheckDetailsAction verifyReleaseSaleCheck(){
    	Logger.log("Agent should be able to release the salescheck",TestStepType.THEN);
        this.factory.salesCheckDetailsPage().verifyReleaseSaleCheck();
        return this;       
   }
    
    public SalesCheckDetailsAction verifyTrialBalance() {
        this.factory.orderdetailspage().verifyTrialBalance();
        return this;	
	}

	public SalesCheckDetailsAction verifySalesCheckSummary(String saleschecknumber) {
		Logger.log("Sales check summary section should be shown with relevant details",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().salesCheckSummaryVerify(saleschecknumber);
        return this;	
	}

	public SalesCheckDetailsAction salesCheckDetailVerify(String orderID) {
		Logger.log("Sales check details should be shown with relevant data",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().salesCheckDetailVerify(orderID);
		return this;
	}

	public SalesCheckDetailsAction storePOStoWEBVerify() {
		Logger.log("Store POS to Web details should be displayed",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().storePOStoWEBVerify();
		return this;
	}

	public SalesCheckDetailsAction salesCheckShippingAddressVerify(String orderID) {
		Logger.log("Shipping address for sales check should be displayed",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().salesCheckShippingAddressVerify(orderID);
		return this;
	}

	public SalesCheckDetailsAction  salesCheckContactInfoVerify(String OrderID) {
		Logger.log("Contact information details should be displayed",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().salesCheckContactInfoVerify(OrderID);
		return this;
	}
	
	public SalesCheckDetailsAction  verifyReturnGiftCardInformation(String OrderID) {
		Logger.log("Return gift card information should be displayed",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().verifyReturnGiftCardInformation(OrderID);
		return this;
	}

	public SalesCheckDetailsAction verifyDiscounts() {
		Logger.log("Discount details should be displayed",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().verifyDiscounts();
		return this;
	}
	
	public SalesCheckDetailsAction verifyDeliveryDetails(String OrderID) {
		Logger.log("Delivery details should be displayed",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().verifyDeliveryDetails(OrderID);
		return this;
	}
	
	public SalesCheckDetailsAction verifyPayments(String OrderID, String storeId) throws SAXException, IOException, ParserConfigurationException {
		Logger.log("Payment details should be displayed",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().verifyPayments(OrderID,storeId);
		return this;
	}
	
	public SalesCheckDetailsAction verifySoapDetailsSalesCheck(String OrderID, String storeId){
		Logger.log("SOAP response should be displayed",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().verifySoapDetailsSalesCheck(OrderID, storeId);
		return this;
	}

	public SalesCheckDetailsAction release_Sales_Check_Data() throws Exception {
		Retrieval_Test_Data_By_Query.release_Sales_Check_Data();
		return this;
	}

	public SalesCheckDetailsAction update_Sales_Check_Data() throws Exception {
		Retrieval_Test_Data_By_Query.update_Sales_Check_Data();
		return this;
	}

	public SalesCheckDetailsAction contact_Customer_Sales_Check_Data() throws Exception {
		Retrieval_Test_Data_By_Query.contact_Customer_Sales_Check_Data();
		return this;
	}

	public SalesCheckDetailsAction cancle_Sales_Check_Data() throws Exception {
		Retrieval_Test_Data_By_Query.cancle_Sales_Check_Data();
		return this;
	}

	public SalesCheckDetailsAction  ready_for_Pickup_Email_Data() throws Exception {
		Retrieval_Test_Data_By_Query.ready_for_Pickup_Email_Data();
		return this;
	}

	public SalesCheckDetailsAction salesCheckSummaryPageVerify(String orderId, String storeId) {
		Logger.log("Sales check summary should be displayed",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().salesCheckSummaryPageVerify(orderId, storeId);
		return this;
	}

	public SalesCheckDetailsAction verifyOptionIsVisible(String optionName) {
		Logger.log(optionName+" should be visible",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().verifyOptionVisible(optionName);
		return this;
	}

	public SalesCheckDetailsAction verifyOptionIsNotVisible(String optionName) {
		Logger.log(optionName+" should not be visible",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().verifyOptionNotVisible(optionName);
		return this;
	}

	public SalesCheckDetailsAction verifyReadyPickupEmail() {
		Logger.log("Ready for pickup mail popup should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().verifyReadyPickupEmail();
		return this;
	}

	public SalesCheckDetailsAction saraLinkVerify() {
		Logger.log("SARA link should be displayed sales check page",TestStepType.THEN);
		this.factory.salesCheckDetailsPage().saraLinkVerify();
		return this;
	}
	
	
    
}