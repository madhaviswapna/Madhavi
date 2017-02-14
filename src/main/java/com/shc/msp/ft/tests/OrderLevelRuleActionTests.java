package com.shc.msp.ft.tests;



import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.Retrieval_Test_Data_By_Query;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class OrderLevelRuleActionTests extends BaseTests{



	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPOrderLevelRuleAction, "MSPOrderLevelRuleActionTests","order_Level_Sales_Tax_Adjustment_Eligible"}
	, description = "Verify sale adjustment at order level", enabled = true, priority=34)	
	public void order_Level_Sales_Tax_Adjustment_Eligible(TestData data) {
		//String OrderID = getProductToTest("SaleAdjustmentOrder");
		String OrderID = "840027769";
		
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOptionVisible("Sales Tax Adjustment")
		.taxadjustment("Sales Tax Adjustment",0.1,OrderID)
		.verifyTrialBalance();
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPOrderLevelRuleAction, "MSPOrderLevelRuleActionTests"}
	, description = "Verify sale tax adjustment is not available at order level for ineligible orders", enabled = true, priority=35)
	public void order_Level_Sales_Tax_Adjustment_NonEligible(TestData data) {
		String OrderID = getProductToTest("IneligibleSaleAdjustmentOrder");
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOptionIsNotVisible("Sales Tax Adjustment");
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.MSPOrderLevelRuleAction, "MSPOrderLevelRuleActionTests"}
	, description = "Verify shipping adjustment is available for eligible orders", enabled = true, priority=35)
	public void order_Level_Shipping_Adjustment_Eligible(TestData data) {
		//String OrderID = getProductToTest("ShippingAdjustmentEligibleOrder");
		String OrderID = "840027700";
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.verifyOptionVisible("Shipping Adjustment")
		.taxadjustment("Shipping Adjustment",0.1,OrderID)
		;
	}


	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.MSPOrderLevelRuleAction, "MSPOrderLevelRuleActionTests"}
	, description = "Verify shipping adjustment is not available for orders that are not eligible", enabled = true, priority=37)
	public void order_Level_Shipping_Adjustment_NonEligible(TestData data) {
		String OrderID = getProductToTest("ShippingAdjustmentIneligibleOrder");
		
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();	
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOptionIsNotVisible("Shipping Ajustment");

	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, 
			groups = {TestGroup.MSPOrderLevelRuleAction, "MSPOrderLevelRuleActionTests"}
	, description = "Verify held orders can be released", enabled = true, priority=38)
	public void order_Level_Release_Order_Eligible(TestData data) {
		String OrderID = getProductToTest("MSP_OL_OrderEligibleForRelease");
		

		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOptionVisible("Release Order")
		.releaseOrder();
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,	groups = {TestGroup.MSPOrderLevelRuleAction, "MSPOrderLevelRuleActionTests"}
	, description = "Verify release option is not shown for orders that are not eligible", enabled = true, priority=39)
	public void order_Level_Release_Order_NonEligible(TestData data) {
		String OrderID = getProductToTest("MSP_OL_OrderNotEligibleForRelease");
		

		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOptionIsNotVisible("Release Order");

	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,	groups = {TestGroup.MSPOrderLevelRuleAction, "MSPOrderLevelRuleActionTests"}
	, description = "Verify customer contact option is displayed for eligible orders", enabled = true, priority=40)

	public void order_Level_Contact_Customer_Eligible(TestData data) {
		String OrderID = getProductToTest("MSP_OL_OrderEligibleForContactCustomer");
		

		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()
		.addlogType(TestStepType.THEN)
		.verifyOptionVisible("Contact Customer")
		.addlogType(TestStepType.THEN)
		.verifyEmailTemplatePopUp(); 

	}


	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPOrderLevelRuleAction, "MSPOrderLevelRuleActionTests"}
	, description = "Verify order level cancellation", enabled = true, priority=41)
	public void  order_Level_Cancellation_Eligible(TestData data) {
		String OrderID = getProductToTest("MSP_OL_OrderEligibleForCancellation");
		

		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOptionVisible("Cancellation - Order")
		.cancelOrder(OrderID);

	}


	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPOrderLevelRuleAction, "MSPOrderLevelRuleActionTests"}
	, description = "Verify cancellation is not shown for orders that are not eligible", enabled = true, priority=42)
	public void order_Level_Cancellation_NonEligible(TestData data) {
		String OrderID = getProductToTest("MSP_OL_OrderInEligibleForCancellation");
		
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOptionIsNotVisible("Cancellation - Order");

	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPP0Tests, "MSPOrderLevelRuleActionTests"}
	, description = "MSPReSendOrderConfirmation", enabled = true, priority=43)
	public void order_Level_ReSendOrderConfirmation_Eligible(TestData data) {
		String OrderID = getProductToTest("MSP_OL_OrderEligibleForResendOrderConfirmation");
		
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOptionVisible("Re-Send Order Confirmation")
		.addlogType(TestStepType.THEN)
		.reSendOrderConfirmation();

	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPP0Tests, "MSPOrderLevelRuleActionTests"}
	, description = "Verify that resend order confirmation option is not shown for ineligible orders", enabled = true, priority=44)
	public void order_Level_ReSendOrderConfirmation_NonEligible(TestData data) {
		String OrderID = getProductToTest("MSP_OL_OrderInEligibleForResendOrderConfirmation");
		
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();	           
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()    		       
		.addlogType(TestStepType.THEN)
		.verifyOptionIsNotVisible("Re-Send Order Confirmation");

	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests, "MSPActionTests","MSPOrderLevelRuleActionTests"}
	, description = "Verify Email to customer is captured in notes", enabled = true, priority=40)
	public void order_Level_EmailToCustomer_CapturedInNotes(TestData data) {
		String OrderId = getProductToTest("MSP_OL_OrderEligibleForContactCustomer");
		

		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.deleteCasesforOrderfromDB(OrderId)
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderId)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()
		.addlogType(TestStepType.THEN)
		.verifyOptionVisible("Contact Customer")
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyEmailCapturedInInteraction()
		.addlogType(TestStepType.THEN)
		.verifyOrderWrapUp()
		.addlogType(TestStepType.THEN)
		.fillRFCForm()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderId)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyEmailCapturedInNotes()
		;




	}


	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests, "MSPOrderLevelRuleActionTests",TestGroup.MSPOrderLevelRuleAction, "Order_Level_Sales_Tax_Adjustment_CapturedNotesInteraction"}
	, description = "Verify sale adjustment at order level", enabled = true, priority=34)	
	public void Order_Level_Sales_Tax_Adjustment_CapturedNotesInteraction(TestData data) {
		String OrderID = getProductToTest("SaleAdjustmentOrder");
		
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.deleteCasesforOrderfromDB(OrderID)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOptionVisible("Sales Tax Adjustment")
		.taxadjustment("Sales Tax Adjustment",0.1,OrderID)
		.verifyTrialBalance()
		.verifyAdjustmentCapturedInInteraction("Sales Tax Adjustment")
		.verifyOrderWrapUp()
		.addlogType(TestStepType.THEN)
		.fillRFCForm()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.verifyAdjustmentCapturedInNotes("Sales Tax Adjustment")
		;
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests, "MSPOrderLevelRuleActionTests",TestGroup.MSPOrderLevelRuleAction, "Order_Level_Shipping_Adjustment_CapturedNotesInteraction"}
	, description = "Verify sale adjustment at order level", enabled = true, priority=34)	
	public void Order_Level_Shipping_Adjustment_CapturedNotesInteraction(TestData data) {
		String OrderID = getProductToTest("ShippingAdjustmentOrder");
		
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.deleteCasesforOrderfromDB(OrderID)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOptionVisible("Shipping Adjustment")
		.taxadjustment("Shipping Adjustment",0.1,OrderID)
		.verifyTrialBalance()
		.verifyAdjustmentCapturedInInteraction("Shipping Adjustment")
		.verifyOrderWrapUp()
		.addlogType(TestStepType.THEN)
		.fillRFCForm()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.verifyAdjustmentCapturedInNotes("Shipping Adjustment")
		;
		
	}

	/***********
	 * Data Provider
	 ************/



	@DataProvider (name="DP_ST_Adjustment_Eligible" ,parallel=true)
	public Object[][] DP_ST_Adjustment_Eligible_OrderID() throws Exception{
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().sales_Tax_Adjustment_Data();
		String OrderID[]={Retrieval_Test_Data_By_Query.adj_eligible_orderID};
		Object testData[][] = {OrderID};
		return (testData);

	}


	@DataProvider (name="DP_ST_Adjustment_NonEligible",parallel=true)
	public Object[][] DP_ST_Adjustment_NonEligible_OrderID() throws Exception{
		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().sales_Tax_Adjustment_Data();
		String OrderID[]={Retrieval_Test_Data_By_Query.adj_store_exception};
		Object testData[][] = {OrderID};
		return (testData);		
	}

	@DataProvider (name="DP_ShipAdjustment_Eligible")
	public Object[][] DP_ShipAdjustment_Eligible_OrderID() throws Exception{
		Retrieval_Test_Data_By_Query.shipping_Adjustment_Data();
		String OrderID[]={Retrieval_Test_Data_By_Query.ship_adj_eligible_orderID};
		Object testData[][] = {OrderID};
		return (testData);
	}

	@DataProvider (name="DP_ShipAdjustment_NonEligible",parallel=true)
	public Object[][] DP_ShipAdjustment_NonEligible_OrderID() throws Exception{
		Retrieval_Test_Data_By_Query.shipping_Adjustment_Data();
		String OrderID[]={Retrieval_Test_Data_By_Query.ship_adj_store_exception};
		Object testData[][] = {OrderID};
		return (testData);

	}

	@DataProvider (name="DP_ReleaseOrder_Eligible")
	public Object[][] DP_ReleaseOrder_Eligible() throws Exception{
		Retrieval_Test_Data_By_Query.release_Order_Data();
		String OrderID[]={Retrieval_Test_Data_By_Query.release_order_eligible_orderID};
		Object testData[][] = {OrderID};
		return (testData);

	}

	@DataProvider (name="DP_ReleaseOrder_NonEligible",parallel=true)
	public Object[][] DP_ReleaseOrder_NonEligibl() throws Exception{
		Retrieval_Test_Data_By_Query.release_Order_Data();
		// String OrderID[]={Retrieval_Test_Data_By_Query.release_order_store_exception};
		String OrderIDstatus[]={Retrieval_Test_Data_By_Query.release_order_status_exception};
		Object testData[][] = {OrderIDstatus};
		return (testData);
	}

	@DataProvider (name="DP_ContactCustomer_Eligible")
	public Object[][] DP_ST_Contact_Customer_Eligible_OrderID() throws Exception{
		Retrieval_Test_Data_By_Query.contactCustomer_Data();
		String OrderID[]={Retrieval_Test_Data_By_Query.cc_eligible_orderID};
		Object testData[][] = {OrderID};
		return (testData); 
	}

	@DataProvider (name="DP_CancelOrder_Eligible")
	public Object[][] DP_Cancellation_Eligible_OrderID() throws Exception{
		Retrieval_Test_Data_By_Query.cancellation_Data();
		String OrderID[]={Retrieval_Test_Data_By_Query.cancellation_orderID};
		Object testData[][] = {OrderID};
		return (testData);
	}

	@DataProvider (name="DP_CancelOrder_NonEligible")
	public Object[][] DP_Cancellation_NonEligible_OrderID() throws Exception{
		Retrieval_Test_Data_By_Query.cancellation_Data();
		String OrderIDffm[]={Retrieval_Test_Data_By_Query.cancellation_FFM_exception};
		Object testData[][] = {OrderIDffm};
		return (testData);
	}


	@DataProvider (name="DP_Resend_Order_Confirm_Eligible")
	public Object[][] DP_Resend_Order_Confirm_Confirm_Eligible() throws Exception{
		Retrieval_Test_Data_By_Query.resend_Order_Confirmation_Data();
		String OrderID[]={Retrieval_Test_Data_By_Query.resend_order_confirm_eligible_orderID};
		Object testData[][] = {OrderID};
		return (testData);
	}

	@DataProvider (name="DP_Resend_Order_Confirm_NonEligible",parallel=true)
	public Object[][] DP_Resend_Order_Confirm_NonEligibl() throws Exception{
		Retrieval_Test_Data_By_Query.resend_Order_Confirmation_Data();
		String OrderID[]={Retrieval_Test_Data_By_Query.resend_order_confirm_store_exception};
		//String OrderIDstatus[]={Retrieval_Test_Data_By_Query.resend_order_confirm_status_exception};
		Object testData[][] = {OrderID};
		return (testData);

	}
}
