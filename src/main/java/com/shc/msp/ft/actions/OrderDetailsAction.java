package com.shc.msp.ft.actions;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.bouncycastle.asn1.x509.ReasonFlags;
import org.json.JSONException;
import org.xml.sax.SAXException;

import com.shc.automation.Logger;
import com.shc.automation.dao.ProductData;
import com.shc.automation.utils.TestUtils;
import com.shc.automation.utils.TestUtils.Feature;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.modules.ActionDropdown.SelectPage;
import com.shc.msp.ft.util.Retrieval_Test_Data_By_Query;


public class OrderDetailsAction extends BaseAction {

	//  @Override
	protected Feature feature() {
		return Feature.BROWSE;
	}

	public OrderDetailsAction(SiteFactory factory) {
		super(factory);
	}


	@Override
	public OrderDetailsAction addlogType(TestUtils.TestStepType testStepType)
	{
		super.addlogType(testStepType);
		return this;
	}

	public OrderDetailsAction verifyCustomerInfoVerify(String OrderID) throws SQLException{
		Logger.log("Customer information should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().customerInfoVerify(OrderID);
		return this;
	}
	public OrderDetailsAction  clickSYWMaxTabandVerify(String usertype) {
		Logger.log("Go to SYW Max tab and verify member is "+usertype,TestStepType.THEN);
		this.factory.orderdetailspage().clickSYWMaxTabandVerify(usertype);
		return this;
	}
	public OrderDetailsAction  verifySYWMaxTabSavingsAmount(String amount) {
		Logger.log("Verify the SYW Max savings amount is "+amount,TestStepType.THEN);
		this.factory.orderdetailspage().verifySYWMaxSavingsAmount(amount);
		return this;
	}
	

	public OrderDetailsAction verifyOrderSummary(String OrderID) throws ParseException, SQLException{
		Logger.log("Order summary should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().orderSummaryVerify(OrderID);
		return this;
	}

	public OrderDetailsAction verifyOrderCharges(String OrderID) throws SQLException, ParseException {
		Logger.log("Order charges section should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().verifyOrderCharges(OrderID);
		return this;
	}

	public OrderDetailsAction verifyDiscounts(String OrderID) throws SQLException{
		Logger.log("Discount section should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().verifyDiscounts(OrderID);
		return this;
	}

	public OrderDetailsAction updateAndVerifyNameEmailNumber() {
		Logger.log("Updating name,Email and home phone number",TestStepType.THEN);
		this.factory.orderdetailspage().updateAndVerifyNameEmailNumber();
		return this;
	}
	public OrderDetailsAction verifyAdjustments(String OrderID) throws ParseException, SQLException{
		Logger.log("Adjustments for the order should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().verifyAdjustments(OrderID);
		return this;
	}
	
	public OrderDetailsAction verifyAdjustmentCapturedInOrderSummary(String adjust, String amount){
		Logger.log("Verify "+adjust+" in Order Summary",TestStepType.THEN);
		this.factory.orderdetailspage().verifyAdjustmentCapturedInOrderSummary(adjust, amount);
		return this;
	}

	public OrderDetailsAction verifyPayments(String OrderID,String storeId) throws SQLException{
		Logger.log("Payment summary should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().verifyPayments(OrderID,storeId);
		return this;
	}

	public OrderDetailsAction verifyActionsPresent(String level) throws SQLException{
		Logger.log("verify that the actions are displayed correctly at "+level+" level",TestStepType.THEN);
		this.factory.orderdetailspage().verifyActionsPresent(level);
		return this;
	}

	public OrderDetailsAction verifyCurrentInteraction(String level) throws SQLException{
		Logger.log("verify that the actions are displayed correctly in current interaction",TestStepType.THEN);
		this.factory.orderdetailspage().verifyCurrentInteraction(level);
		return this;
	}



	public OrderDetailsAction verifyOrderDetails(String OrderID, String storeId) throws SQLException, JSONException{
		Logger.log("Order details should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().verifyOrderDetails(OrderID, storeId);
		return this;
	}

	public OrderDetailsAction cancelOrder(String orderId) {
		Logger.log("Agent should be able to cancel an order",TestStepType.THEN);
		this.factory.orderdetailspage().CancelOrder(orderId);
		return this;
	}
	
	public OrderDetailsAction verifyEmpowermentGuidelinePopUp(){
		Logger.log("Verify Empowerment Guideline pop up",TestStepType.THEN);
		this.factory.orderdetailspage().verifyEmpowermentGuidelinePopUp();
		return this;
	}
	
	public OrderDetailsAction verifyMarketplaceItemPopUp(){
		Logger.log("Verify Marketplace Item pop up",TestStepType.THEN);
		this.factory.orderdetailspage().verifyMarketplaceItemPopUp();
		return this;
	}
	
	public OrderDetailsAction verifyEmpowermentGuidelineStatusColor(String Enabled){
		Logger.log("Verify Empowerment Guideline Status Color",TestStepType.THEN);
		this.factory.orderdetailspage().verifyEmpowermentGuidelineStatusColor(Enabled);
		return this;
	}
	public OrderDetailsAction verifyOrderDetailsPageDisplayed() {
		Logger.log("Order details page should be displayed", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().verifyOrderDetailsPageDisplayed();
		return this;
	}
	public OrderDetailsAction verifyOrderDetailsDescription(String itemcondition,String rowNumber) {
		Logger.log("Order details page should be displayed", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().verifyOrderDetailsDescription(itemcondition,rowNumber);
		return this;
	}


	//Real time MSAT - Online Agent wrapping up order and submitting RFC
	public OrderDetailsAction msatOnlineAgentOrderWrapupRFC() {
		Logger.log("Order details page should be displayed", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().msatOnlineAgentOrderWrapupRFC();
		return this;
	}

	//Real time MSAT - Online Agent submitting RFC from home page - General Inquiry
	public OrderDetailsAction msatOnlineAgentGeneralInquiryWrapupRFC() {
		Logger.log("Order details page should be displayed", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().msatOnlineAgentGeneralInquiryWrapupRFC();
		return this;
	}


	public OrderDetailsAction verifyLayawayDetailsPageDisplayed(){
		Logger.log("layaway details page should be displayed", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().verifyLayawayDetailsPageDisplayed();
		return this;
	}


	//For Delivery Flow - Order Details page
	public OrderDetailsAction verifySearchedDOSOrderIsDisplayed(String searchVal, String searchField) {
		Logger.log("Verification for the searched order withing the order details page", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().verifySearchedDOSOrderIsDisplayed(searchVal, searchField);
		return this;
	}


	//For Delivery Flow - Order Details page
	public OrderDetailsAction verifyUpdateOptionForPendedOrder(String dosorderid, String dosunitid) {
		Logger.log("Verification for the searched order withing the order details page", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().verifyUpdateOptionForPendedOrder(dosorderid, dosunitid);
		return this;
	}

	//For Delivery Flow - Suggested Address Validation
	public OrderDetailsAction verifySuggestedAddressForAddressUpdates(String dosorderid, String dosunitid) {
		Logger.log("Verification for the searched order withing the order details page", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().verifySuggestedAddressForAddressUpdates(dosorderid, dosunitid);
		return this;
	}

	// TODO Split the verification into different step
	public OrderDetailsAction taxadjustment(String adjusttaxOption,Double amount,String orderID) {
		Logger.log("Agent should be able to process "+adjusttaxOption+" ",TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().taxaddjustment(adjusttaxOption,amount,orderID);
		return this;
	}


	public OrderDetailsAction saleAdjustmentLineitem(String adjusttaxOption,Double amount) {
		this.factory.orderdetailspage().saleAdjustmentLineitem(adjusttaxOption,amount);
		return this;
	}

	public OrderDetailsAction clickOnSkuNumberUnderLineItemTab(int skuNumber) {
		Logger.log("Agent clicks on the first SKU in line item tab", TestUtils.TestStepType.WHEN);
		Logger.log("Agent is on line item details tab and clicks on the SKU in line "+skuNumber, TestUtils.TestStepType.GIVEN);
		this.factory.orderdetailspage().clickOnSkuNumberUnderLineItemTab(skuNumber);
		return this;
	}

	public OrderDetailsAction clickOnSkuNumberUnderLineItemTab(String sku) {
		Logger.log("Agent clicks on a SKU in line item tab", TestUtils.TestStepType.WHEN);
		Logger.log("Agent is on line item details tab and clicks on SKU", TestUtils.TestStepType.GIVEN);
		this.factory.orderdetailspage().clickOnSkuNumberUnderLineItemTab(sku);
		return this;
	}
	public OrderDetailsAction verifylineitemdetails()   {
		Logger.log("Line item detail and summary table should be displayed ", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().verifylineitemdetails();
		return this;
	}
	public OrderDetailsAction verifysalescheckdetails()   {
		Logger.log("Sales check detail and shipping information should be displayed ", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().verifysalescheckdetails();
		return this;
	}


	public OrderDetailsAction clickOnSalesCheckNumberUnderSalesCheckTab(int salesCheckNumber) {
		Logger.log("Agent selects LineItem tab and clicks on salescheck number ", TestUtils.TestStepType.WHEN);
		this.factory.orderdetailspage().clickOnSalesCheckNumberUnderSalesCheckTab(salesCheckNumber);
		return this;
	}

	public OrderDetailsAction verifyOptionNotVisible(String optionName) {
		Logger.log(optionName+"  should not be available", TestUtils.TestStepType.THEN);
		this.factory.actionDropdown().verifyOptionNotVisible(SelectPage.SUMMARY_PAGE, optionName);
		return this;
	}

	public OrderDetailsAction verifyOptionVisible(String optionName) {
		Logger.log(optionName+"  should be available", TestUtils.TestStepType.THEN);
		this.factory.actionDropdown().verifyOptionVisible(SelectPage.SUMMARY_PAGE, optionName);
		return this;
	}

	public OrderDetailsAction verifyTrialBalance() {
		Logger.log("Trial balance option should be available", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().verifyTrialBalance();
		return this;	
	}
	
	public OrderDetailsAction verifyTrialBalanceIfPresent() {
		
		try {
			this.factory.orderdetailspage().verifyTrialBalance();
			Logger.log("Verify Trial balance if available", TestUtils.TestStepType.THEN);
		} catch (Exception e) {
			Logger.log("Trial balance Not Present, proceeding further", TestUtils.TestStepType.THEN);
			e.printStackTrace();
		}
		return this;	
	}
	public OrderDetailsAction selectAction(String actionName) {
		Logger.log("Agent selects "+actionName+" - action in summary page",TestStepType.WHEN);
		this.factory.actionDropdown().selectAction(SelectPage.SUMMARY_PAGE, actionName);
		return this;
	}
	public OrderDetailsAction verifyEmailTemplatePopUp() {
		Logger.log("Email template should be displayed to the agent",TestStepType.THEN);
		this.factory.emailTemplatePopUp().verifyEmailTemplatePopUp();
		return this;	
	}
	public OrderDetailsAction contactMarketPlaceSeller() {
		Logger.log("Verify Email template and send Email to marketplace seller.",TestStepType.THEN);
		this.factory.emailTemplatePopUp().contactMarketPlaceSeller();
		return this;	
	}
	public OrderDetailsAction releaseOrder() {
		Logger.log("Agent should be able to release order",TestStepType.THEN);
		this.factory.orderdetailspage().verifyReleaseOrder();
		return this;
	}

	public OrderDetailsAction reSendOrderConfirmation() {
		Logger.log("Resend order confirmation popup should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().re_Send_Order_Confirmation();
		return this;
	}

	public OrderDetailsAction closeMarketplaceWarningPopUp() {
		this.factory.orderdetailspage().closeMarketplaceWarningPopUp();
		return this;
	}

	public OrderDetailsAction verifysoapdetails(String orderID, String storeId) {
		this.factory.orderdetailspage().verifysoapdetails(orderID, storeId);
		return this;
	}

	public OrderDetailsAction verifyagentNotesPresent() {
		Logger.log("Agent notes field should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().agentNotesVerifyPresent();
		return this;
	}

	public OrderDetailsAction sales_Tax_Adjustment_Data() throws Exception {

		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().sales_Tax_Adjustment_Data();
		return this;

	}

	public OrderDetailsAction shipping_Adjustment_Data() throws Exception {
		Retrieval_Test_Data_By_Query.shipping_Adjustment_Data();
		return this;
	}

	public OrderDetailsAction  release_Order_Data() throws Exception {

		Retrieval_Test_Data_By_Query.release_Order_Data() ;
		return this;
	}

	public OrderDetailsAction contactCustomer_Data() throws Exception {
		Retrieval_Test_Data_By_Query.contactCustomer_Data();
		return this;
	}

	public OrderDetailsAction cancellation_Data() throws Exception {
		Retrieval_Test_Data_By_Query.cancellation_Data();
		return this;
	}

	public OrderDetailsAction resend_Order_Confirmation_Data() throws Exception {
		Retrieval_Test_Data_By_Query.resend_Order_Confirmation_Data();
		return this;
	}

	public OrderDetailsAction orderSummaryPageVerify(String orderId, String storeId) throws SQLException, ParseException {
		Logger.log("Order summary page should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().orderSummaryPageVerify(orderId, storeId);
		return this;
	}

	public OrderDetailsAction verifyOptionIsVisible(String optionName) {
		Logger.log(optionName+" should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().verifyOptionIsVisible(optionName);
		return this;
	}

	public OrderDetailsAction verifyOptionIsNotVisible(String optionName) {
		Logger.log(optionName+" should not be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().verifyOptionIsNotVisible(optionName);
		return this;
	}
	public OrderDetailsAction verifyDDCfulfillment () {
		Logger.log("DDC fulfillment should be displayed",TestStepType.THEN);
		this.factory.orderdetailspage().verifyDDCfulfillment();
		return this;
	}

	public OrderDetailsAction  clickTabs() {
		this.factory.orderdetailspage().clickTabs();
		return this;
	}

	public OrderDetailsAction  Adjustment_OrderID() throws Exception {
		Retrieval_Test_Data_By_Query.Adjustment_OrderID();
		return this;
	}

	public OrderDetailsAction clickOnSalesCheckNumberUnderSalesCheckTab(String scNO) {
		Logger.log("Agent clicks on a salescheck number in sales check tab",TestStepType.WHEN);
		this.factory.orderdetailspage().clickOnSalesCheckNumberUnderSalesCheckTab(scNO);
		return this;
	}

	public OrderDetailsAction captureInteractionCaseId() {
		Logger.log("Capture Case id for Interaction",TestStepType.THEN);
		this.factory.orderdetailspage().captureInteractionCaseId();
		return this;
	}
	public OrderDetailsAction captureOrderIdFromODPage() {
		Logger.log("Capture order Id",TestStepType.THEN);
		this.factory.orderdetailspage().captureOrderIdFromODPage();
		return this;
	}

	public OrderDetailsAction verifyOrderWrapUp() {
		Logger.log("Verify Order Wrap Up",TestStepType.THEN);
		this.factory.orderdetailspage().verifyOrderWrapUp();
		return this;
	}
	public OrderDetailsAction verifyCreateCaseByRouting() {
		Logger.log("Verify Create case by Routing to Queue",TestStepType.THEN);
		this.factory.orderdetailspage().verifyCreateCaseByRouting();
		return this;
	}
	public OrderDetailsAction verifyAllCategoriesReasonCodes(int categoryIndex) {
		Logger.log("Verify Queues selected for All Categories and Reason Codes",TestStepType.THEN);
		this.factory.orderdetailspage().verifyAllCategoriesReasonCodes(categoryIndex);
		return this;
	}
	public OrderDetailsAction fillRFCForm() {
		Logger.log("Fill RFC Form",TestStepType.THEN);
		this.factory.orderdetailspage().fillRFCForm();
		return this;
	}
	public OrderDetailsAction verifyEmailCapturedInNotes() {
		Logger.log("Verify Email sent to customer is captured in notes",TestStepType.THEN);
		this.factory.orderdetailspage().verifyEmailCapturedInNotes();
		return this;
	}
	public OrderDetailsAction verifyEmailCapturedInInteraction() {
		Logger.log("Verify Email sent to customer is captured in interaction",TestStepType.THEN);
		this.factory.orderdetailspage().verifyEmailCapturedInInteraction();
		return this;
	}
	public OrderDetailsAction verifyAdjustmentCapturedInInteraction(String adjust) {
		Logger.log("Verify "+adjust+" is captured in interaction",TestStepType.THEN);
		this.factory.orderdetailspage().verifyAdjustmentCapturedInInteraction(adjust);
		return this;
	}
	public OrderDetailsAction verifyAdjustmentCapturedInInteraction(List<String> adjust) {
		
		for(String s:adjust){
			Logger.log("Verify "+s+" is captured in interaction",TestStepType.THEN);
			this.factory.orderdetailspage().verifyAdjustmentCapturedInInteraction(s);
		}
		
		return this;
	}
	public OrderDetailsAction verifyAdjustmentCapturedInNotes(String adjust) {
		Logger.log("Verify "+adjust+" is captured in interaction",TestStepType.THEN);
		this.factory.orderdetailspage().verifyAdjustmentCapturedInNotes(adjust);
		return this;
	}
	public OrderDetailsAction verifyActionCapturedInNotes(String action) {
		Logger.log("Verify  Action "+action+"is captured in notes",TestStepType.THEN);
		this.factory.orderdetailspage().verifyActionCapturedInNotes(action);
		return this;
	}
	public OrderDetailsAction verifyRoutingForOfflineAgent() {
		Logger.log("Verify Case Routing for Offline Agent",TestStepType.THEN);
		this.factory.orderdetailspage().verifyRoutingForOfflineAgent();
		return this;
	}
	public OrderDetailsAction verifyCloseCaseByWrapupOfflineAgent() {
		Logger.log("Verify that offline agent can close case by wrapup",TestStepType.THEN);
		this.factory.orderdetailspage().verifyCloseCaseByWrapupOfflineAgent();
		return this;
	}

	public OrderDetailsAction verifyRereservebuttonPresent() {
		Logger.log("Verify Rereserve button present in action center",TestStepType.THEN);
		this.factory.orderdetailspage().verifyRereservebuttonPresent();
		return this;
	}
	public OrderDetailsAction rereserveItem(String orderType,String order) {
		Logger.log("Verify Rereserve action for "+orderType,TestStepType.THEN);
		this.factory.orderdetailspage().rereserveItem(orderType,order);
		return this;
	}

	public OrderDetailsAction goToActionCenter() {
		Logger.log("Navigating to action center",TestStepType.WHEN);
		this.factory.orderdetailspage().goToActionCenter();
		return this;
	}
	
	public OrderDetailsAction goToAuditTrail(){
		Logger.log("Navigating to Audit Trail",TestStepType.WHEN);
		this.factory.orderdetailspage().goToAuditTrail();
		return this;
	}
	public OrderDetailsAction verifyActionCapturedInAuditTrail(String action){
		Logger.log("Verify Action is Performed in Audit Trail",TestStepType.THEN);
		this.factory.orderdetailspage().verifyActionCapturedInAuditTrail(action);
		return this;
	}
	public OrderDetailsAction pickupOrder() {
		Logger.log("Verify agent able to do pickup action",TestStepType.THEN);
		this.factory.orderdetailspage().pickupOrder();
		return this;
	}

	public OrderDetailsAction pickupEntireOrder() {
		Logger.log("Verify agent able to do pickup action on entire order",TestStepType.THEN);
		this.factory.orderdetailspage().pickupEntireOrder();
		return this;
	}

	public OrderDetailsAction verifyEvenExchangeEligibility(String orderType){
		Logger.log("Verify, if Even Exchange button is present",TestStepType.THEN);
		this.factory.orderdetailspage().verifyEvenExchangeEligibility(orderType);
		return this;
	}
	public OrderDetailsAction verifyEvenExchange(){
		Logger.log("Verify, if Even Exchange",TestStepType.THEN);
		this.factory.orderdetailspage().verifyEvenExchange();
		return this;
	}
	public OrderDetailsAction verifyEvenExchangeEntireOrder(String orderStatus){
		Logger.log("Verify, if Even Exchange for entire order",TestStepType.THEN);
		this.factory.orderdetailspage().verifyEvenExchangeEntireOrder(orderStatus);
		return this;
	}
	
	public OrderDetailsAction verifyEvenExchangeNotAllowed(){
		Logger.log("Verify, no items are allowed for Even Exchange ",TestStepType.THEN);
		this.factory.orderdetailspage().verifyEvenExchangeNotAllowed();
		return this;
	}

	public OrderDetailsAction verifyPickupbuttonPresent() {
		Logger.log("Verify Pickup button present in action center",TestStepType.THEN);
		this.factory.orderdetailspage().verifyPickupbuttonPresent();
		return this;
	}
	public OrderDetailsAction cancelOrderDelivery(String type, String orderStatus,String agent) {
		Logger.log("Cancel order",TestStepType.THEN);
		this.factory.orderdetailspage().cancelOrderDelivery(type,orderStatus, agent);
		return this;
	}
	public OrderDetailsAction verifyAction(String type) {
		Logger.log("Verify actions:"+type,TestStepType.THEN);
		this.factory.orderdetailspage().verifyAction(type);
		return this;
	}
	public OrderDetailsAction rescheduleDeliveryOrder(String type) throws ParseException {
		Logger.log("Reschedule delivery",TestStepType.THEN);
		this.factory.orderdetailspage().rescheduleDeliveryOrder(type);
		return this;
	}
	public OrderDetailsAction rescheduleDeliveryOrder(String type,String ordType) throws ParseException {
		Logger.log("Reschedule delivery",TestStepType.THEN);
		this.factory.orderdetailspage().rescheduleDeliveryOrder(type,ordType);
		return this;
	}

public OrderDetailsAction scheduleFollowUp() throws ParseException {
		Logger.log("Schedule for follow up",TestStepType.THEN);
		this.factory.orderdetailspage().scheduleFollowUp();
		return this;
	}

	public OrderDetailsAction queueForFollowUp(String reasonName) throws ParseException {
		Logger.log("Add to queue for follow up",TestStepType.THEN);
		this.factory.orderdetailspage().queueForFollowUp(reasonName);
		return this;
	}

	public OrderDetailsAction goToDeliveryNotes() {
		Logger.log("Go to delivery notes tab",TestStepType.THEN);
		this.factory.orderdetailspage().goToDeliveryNotes();
		return this;
	}
	public OrderDetailsAction verifyTabsDelivery(String ordType) {
		Logger.log("Verify data in all the tabs for delivery order",TestStepType.THEN);
		this.factory.orderdetailspage().verifyTabsDelivery(ordType);
		return this;
	}

	public OrderDetailsAction verifyDataInDeliveryNotes(String data){
		Logger.log("Verify data in delivery notes tab",TestStepType.THEN);
		this.factory.orderdetailspage().verifyDataInDeliveryNotes(data);
		return this;
	}


	public OrderDetailsAction verifyOrderPhoneNumber(String phoneNumber) {
		Logger.log("Verify Order Phone Number match",TestStepType.THEN);
		this.factory.orderdetailspage().verifyOrderPhoneNumber(phoneNumber);
		return this;
	}

	public OrderDetailsAction clickSearchAnotherOrder(){
		Logger.log("Click on Search for Another Order", TestStepType.WHEN);
		this.factory.orderdetailspage().clickSearchAnotherOrder();
		return this;
	}
	public OrderDetailsAction verifySywLinkDetailsPageDisplayed() {
		Logger.log("verifySywLinkDetailsPageDisplayed", TestUtils.TestStepType.WHEN);
		this.factory.orderdetailspage().verifySywLinkDetailsPageDisplayed();
		return this;

	}
	/*public OrderDetailsAction verifyReasonCodePresence(String reasonName, boolean presence) {
		Logger.log("verify reason code presence", TestUtils.TestStepType.THEN);
    	this.factory.orderdetailspage().verifyReasonCodePresence(reasonName,presence);
    	return this;
	}*/
	public OrderDetailsAction verifyReasonCodes(String reasonName, boolean statusPresence) {
		Logger.log("verify reason code   "+reasonName, TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().verifyReasonCodes(reasonName,statusPresence);
		return this;
	}
	public OrderDetailsAction clickOnReasonDropdown() {
		Logger.log("Navigate to queue for follow up action ", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().clickOnReasonDropdown();
		return this;
	}
	public void verifyAllReasonCodePresence(ArrayList<String> keyword) {
		clickOnReasonDropdown();
		for(int i=0;i<keyword.size();i++){
			verifyReasonCodes(keyword.get(i).toString(),true);
		}
	}
	public void verifyAllReasonCodePresence(List <Object> keyword, String agent) {
		if(agent.equalsIgnoreCase("delivery"))
		clickOnReasonDropdown();
		Iterator<Object> itr= keyword.iterator();
		while (itr.hasNext()) {
			ProductData incoming =   (ProductData) itr.next();
			String [] split = incoming.getPartNumber().toString().split(":");
			System.out.println("test array action level      "+split[0]+"           "+Boolean.parseBoolean(split[1]));
			verifyReasonCodes(split[0],Boolean.parseBoolean(split[1]));
		}
	}
	public OrderDetailsAction wrapUpOrderWithoutContactDelivery(){
		Logger.log("Wrapup Order and Contact", TestUtils.TestStepType.WHEN);
		this.factory.orderdetailspage().wrapUpOrderWithoutContactDelivery();
		return this;
	}
	

	public OrderDetailsAction verifyCapturedInInteractionsforUpdateContact() {
		Logger.log("Verify adjustment is updated in the current interaction for updated contact",TestStepType.THEN);
		this.factory.orderdetailspage().verifyCapturedInInteractionsforUpdateContact();
		return this;
	}
	public OrderDetailsAction verifyActionCapturedInNotesForUpdateContact() {
		Logger.log("Verify adjustment is updated in the current interaction",TestStepType.THEN);
		this.factory.orderdetailspage().verifyActionCapturedInNotesForUpdateContact();
		return this;
	}
	public OrderDetailsAction verifyLineItemDetail(String orderType) {
		Logger.log("Verify delivery line item detail",TestStepType.THEN);
		this.factory.orderdetailspage().verifyLineItemDetail(orderType);
		return this;
	}
	public OrderDetailsAction verifyAdjustmentCapturedInInteractionsForCancelOrder(String adjust) {
		Logger.log("Verify adjustment is updated in the current interaction for the cancel order",TestStepType.THEN);
		String note="Following Item(s) were cancelled : [";
		this.factory.orderdetailspage().verifyAdjustmentCapturedInInteractions(adjust,note);
		return this;
	}
	public OrderDetailsAction verifyPickupbuttonnotPresent() {
		Logger.log("Verify Pickup button not present in action center",TestStepType.THEN);
		this.factory.orderdetailspage().verifyPickupbuttonnotPresent();
		return this;
	}
	public OrderDetailsAction verifyActionCapturedHistoryNotes() {
		Logger.log("Verify contact hisory is updated",TestStepType.THEN);
		this.factory.orderdetailspage().verifyActionCapturedHistoryNotes();
		return this;
	}
	public OrderDetailsAction verifyDeliveryOSHNotes(List<String> list){
		System.out.println("-----------------------------------------inside verifyDeliveryOSHNotes");
		Iterator<String> itr =list.iterator();
		while(itr.hasNext()){
			String verifyData=itr.next();
			Logger.log("Verify delivery notes:-" + verifyData,TestStepType.THEN);
			
			System.out.println("------------------------------------------------------------"+verifyData);
			verifyDataInDeliveryNotes(verifyData);
		}
		return this;
	}
	public OrderDetailsAction clickSubmitButton() {
		Logger.log("Trial balance option should be available", TestUtils.TestStepType.THEN);
		this.factory.orderdetailspage().clickSubmitButton();
		return this;	
	}
	public OrderDetailsAction verifyupdateScimCode(String orderType ) {
		Logger.log("verify update scim code",TestStepType.THEN);
		this.factory.orderdetailspage().verifyupdateScimCode(orderType);
		return this;

	}

	public OrderDetailsAction verifyAdjustmentCapturedInInteractionsForScimCode(String adjust) {
		Logger.log("Verify adjustment is updated in the current interaction for the scim code updation",TestStepType.THEN);
		String note= "Scim Code for the following Item(s) were Updated, DcNo: [";
		this.factory.orderdetailspage().verifyAdjustmentCapturedInInteractions(adjust,note);
		return this;
	}

	public OrderDetailsAction recoveryServiceWindowVerification() {
		Logger.log("verify recovery window service",TestStepType.THEN);
		this.factory.orderdetailspage().recoveryServiceWindowVerification();
		return this;

	}
	public OrderDetailsAction verifyReasoncodeAndWrapup(){
		Logger.log("Wrapup Order and Contact", TestUtils.TestStepType.WHEN);
		this.factory.orderdetailspage().verifyReasoncodeAndWrapup();
		return this;
	}
	public OrderDetailsAction rescheduleServiceWindowOrder(String type, String ordType,String windowType,String agent) throws ParseException {
		Logger.log("Reschdeule the delivery order",TestStepType.THEN);
		this.factory.orderdetailspage().rescheduleServiceWindowOrder(type,ordType,windowType,agent);
		return this;
	}
	public OrderDetailsAction verifyCancelbuttonnotPresent() {
		Logger.log("Verify cancel button not present in action center",TestStepType.THEN);
		this.factory.orderdetailspage().verifyCancelbuttonnotPresent();
		return this;
	}
	public OrderDetailsAction captureSalescheckNumber() {
		Logger.log("Capture the salescheck number to verify the same salescheck number is ther for the newly created order",TestStepType.THEN);
		this.factory.orderdetailspage().captureSalescheckNumber();
		return this;
	}
	public OrderDetailsAction verifyNewOrderhassameSalescheckNumber() {
		Logger.log("Capture the salescheck number to verify the same salescheck number is ther for the newly created order",TestStepType.THEN);
		this.factory.orderdetailspage().verifyNewOrderhassameSalescheckNumber();
		return this;
	}
	
	public OrderDetailsAction verifyDateandUserInDeliveryOSHNote(String str1, String str2){
		Logger.log("Verify data in delivery notes tab",TestStepType.THEN);
		this.factory.orderdetailspage().verifyDateandUserInDeliveryOSHNote(str1,str2);
		return this;
	}
		
}