package com.shc.msp.ft.tests.Delivery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.FrameworkProperties;
import com.shc.automation.Logger;
import com.shc.automation.dao.ProductData;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.tests.BaseTestsEx;
import com.shc.msp.ft.util.Constant;
import com.shc.msp.ft.util.DcNumber;
import com.shc.msp.ft.util.ExcelUtil;
import com.shc.msp.ft.util.MongoDB;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class DeliveryOrderSearch extends BaseTestsEx{
	String url=FrameworkProperties.SELENIUM_BASE_URL;

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Deliver_Test_search_phonenumber"}
	, description = "Search by phone number and verify results", enabled = true)
	public void MSP_Delivery_Test_Search_Phonenumber(TestData data) {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		//user.userName = "testdelivery0001";
		//user.password = "TestPassword";
		String phoneNumber = getProductToTest("MSP_Delivery_Phone");
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByPhoneForDeliveryUser(phoneNumber)

		;
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Deliver_Test_search_phonenumber"}
	, description = "Search by phone number and verify results", enabled = true)
	public void MSP_Delivery_Test_Search_ALTPhonenumber(TestData data) {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		//user.userName = "testdelivery0001";
		//user.password = "TestPassword";
		//String phoneNumber = "3145550792";
		String phoneNumber = getProductToTest("MSP_Delivery_Alternate_Phone");
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByALTPhoneForDeliveryUser(phoneNumber)

		;
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Deliver_Test_search_phonenumber"}
	, description = "Search by phone number and verify results", enabled = true)
	public void MSP_Delivery_Test_Search_SOLDTOPhonenumber(TestData data) {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		//user.userName = "testdelivery0001";
		//user.password = "TestPassword";
		//String phoneNumber ="3145554037";
		String phoneNumber = getProductToTest("MSP_Delivery_Sold_To_Phone");
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchBySOLDTOPhoneForDeliveryUser(phoneNumber)

		;
	}
	
	
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Deliver_Test_search_phonenumber"}
	, description = "Search by phone number and verify results", enabled = true)
	public void MSP_Delivery_Test_Search_CELLPhonenumber(TestData data) {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		//user.userName = "testdelivery0001";
		//user.password = "TestPassword";
		//String phoneNumber ="5735551621";
		String phoneNumber = getProductToTest("MSP_Delivery_Cell_Phone");
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByCELLPhoneForDeliveryUser(phoneNumber)

		;
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Sequence"}
	, description = "Verify the sequence in which the orders appear on order search", enabled = true)
	public void MSP_Delivery_Test_Sequence(TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//MongoDB.createUser(user.userName);
		//user.userName = "testdelivery0001";
		//user.password = "TestPassword";
		//String phoneNumber ="6164503584";
		String phoneNumber = getProductToTest("MSP_Delivery_Sequence_Phone");
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByPhoneForDeliveryUser(phoneNumber)
		.verifySequenceOfDisplay()

		;
	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Update_Name_Email_Number"}
	, description = "Update the phone number, address line, name and email and verify whether updated", enabled = true)
	public void MSP_Delivery_Update_Name_Email_Number(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//user.userName = "testdelivery0001";
		//user.password = "TestPassword";
		String orderId= getProductToTest("Even_Exchange_Open_HD_Order",true);	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId,  DcNumber.DC_NO)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.updateAndVerifyNameEmailNumber()

		;
	}  
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"MSP_Delivery_Update_Address_Captured_Notes_Interaction_Open_Order"}
	, description = "MSP_Delivery_Update_Address_Captured_Notes_Interaction_Open_Order", enabled = true)
	public void MSP_Delivery_Update_Address_Captured_Notes_Interaction_Open_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String orderId= getProductToTest("Even_Exchange_Open_HD_Order");	
		
		String [] interaction = {"Update Delivery Address","Update Delivery Contact Details"};
		String [] dosNotes = {"UPDATED CONTACT INFO","UPDATED DELIVERY ADDRESS"};
		
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId,  DcNumber.DC_NO)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.updateAndVerifyNameEmailNumber()
		.addlogType(TestStepType.THEN)
		.verifyAdjustmentCapturedInInteraction(Arrays.asList(interaction))
		.addlogType(TestStepType.THEN)
		.goToDeliveryNotes()
		.verifyDeliveryOSHNotes(Arrays.asList(dosNotes))
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.WHEN)
		.wrapUpOrderWithoutContactDelivery()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyActionCapturedInNotesForUpdateContact()
		;
	} 
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Update_Name_Email_address_partiallyshippedOrder"}
	, description = "Update the phone number, address line, name and email and verify whether updated", enabled = true)
	public void MSP_Delivery_Update_Name_Email_address_partiallyshippedOrder(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//user.userName = "testdelivery0001";
		//user.password = "TestPassword";
		//String[] orderId= getProductToTest("Partially_Shipped_HD_Line_Item",true).split(",");	
		//String dosorderId= getProductToTest("Partially_Shipped_Order");
		
		
		String[] values= getProductToTest("Scheduleforfollowup_Eligible_Partially_Shipped_Order").split(",");
		String orderId=values[0];
		String dc_no=values[1];
		System.out.println("orderId:"+orderId+" "+dc_no);
		
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, dc_no)
		.choosePartiallyshippedHDOrders()
		._OrderDetailsAction()
		.updateAndVerifyNameEmailNumber()

		;
	}  
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"MSP_Delivery_Update_Delivery_Info_Partially_Shipped_Order"}
	, description = "MSP_Delivery_Update_Delivery_Info_Partially_Shipped_Order", enabled = true)
	public void MSP_Delivery_Update_Delivery_Info_Partially_Shipped_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String[] values= getProductToTest("Scheduleforfollowup_Eligible_Partially_Shipped_Order").split(",");
		String orderId=values[0];
		String dc_no=values[1];
		System.out.println("orderId:"+orderId+" "+dc_no);
		String [] interaction = {"Update Delivery Address","Update Delivery Contact Details"};
		String [] dosNotes = {"UPDATED CONTACT INFO","UPDATED DELIVERY ADDRESS"};
		
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, dc_no)
		.choosePartiallyshippedHDOrders()
		._OrderDetailsAction()
		.updateAndVerifyNameEmailNumber()
		.addlogType(TestStepType.THEN)
		.verifyAdjustmentCapturedInInteraction(Arrays.asList(interaction))
		.addlogType(TestStepType.THEN)
		.goToDeliveryNotes()
		.verifyDeliveryOSHNotes(Arrays.asList(dosNotes))
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.WHEN)
		.wrapUpOrderWithoutContactDelivery()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, dc_no)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyActionCapturedInNotesForUpdateContact()
		;
	}
	//Melvin - Updated code using product id
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSPP0DeliveryTests, "MSP_Delivery_Agent_Search_By_DOS_Order_And_DOS_Unit"}

	, description = "Verify search by order id", enabled = true, priority=1)
	public void MSP_Delivery_Agent_Search_By_DOS_Order_And_DOS_Unit(TestData data) throws Exception {
		addCloneIDHostname(data);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName=UserPool.getDeliveryUser();
		ProductData order = getProductDataToTest("Reschedule_Open_HD_Order");
		String dosorderID=order.getPartNumber().toString();
		String dosunitID=DcNumber.DC_NO;//order.getUnitNumber().toString();
		String dosOrderInfo = dosorderID + "-" + dosunitID;

		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.searchByDeliveryOrderId(dosorderID,dosunitID)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifySearchedDOSOrderIsDisplayed(dosOrderInfo, "Order");
	}


	//Melvin - Updated code using product id
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSPP0DeliveryTests, "MSP_Delivery_Agent_Search_By_PhoneNumber"}
	, description = "Verify search by phone number", enabled = true, priority=1)
	public void MSP_Delivery_Agent_Search_By_PhoneNumber(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName = UserPool.getDeliveryUser();

		ProductData order = getProductDataToTest("MSP_Delivery_Phone");
		String phoneNumber=order.getPartNumber().toString();

		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.searchByDOSPhone(phoneNumber)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifySearchedDOSOrderIsDisplayed(phoneNumber, "Phone");
	}


	//Melvin - Updated code using product id
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSPP0DeliveryTests, "MSP_Delivery_Agent_Search_By_SalesCheck"}
	, description = "Verify search by sales check", enabled = true, priority=1)
	public void MSP_Delivery_Agent_Search_By_SalesCheck(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName = UserPool.getDeliveryUser();

		ProductData order = getProductDataToTest("MSP_Delivery_Salescheck");
		String salescheckNumber=order.getPartNumber().toString();

		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.searchByDOSSalesCheck(salescheckNumber)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifySearchedDOSOrderIsDisplayed(salescheckNumber, "Salescheck");
	}


	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSPP0DeliveryTests, "MSP_Delivery_Agent_Search_By_Date"}
	, description = "Verify search by delivery date", enabled = true, priority=1)
	public void MSP_Delivery_Agent_Search_By_Date(TestData data) throws Exception {

		addCloneIDHostname(data);

		LogFormatterAction.beginSetup();


		User user = new User();
		user.userName = UserPool.getDeliveryUser();

		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.searchDOSOrdersByDate()
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifySearchedDOSOrderIsDisplayed("", "Date");
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSPP0DeliveryTests, "Delivery_Search_By_DOS_Order_Number"}

	, description = "Verify search by order id", enabled = true, priority=1)
	public void Delivery_Search_By_DOS_Order_Number(TestData data) throws Exception {
		addCloneIDHostname(data);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName=UserPool.getDeliveryUser();
		String dosorderId= getProductToTest("Reschedule_Open_HD_Order");


		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.searchByDeliveryOrderId(dosorderId, "")
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifySearchedDOSOrderIsDisplayed(dosorderId, "dosorderId");
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSPP0DeliveryTests, "Delivery_Search_By_DOS_Order_And_DOS_Unit_For_Release_Order"}

	, description = "Verify search by order id", enabled = true, priority=1)
	public void Delivery_Search_By_DOS_Order_And_DOS_Unit_For_Release_Order(TestData data) throws Exception {
		addCloneIDHostname(data);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Rereserve_Eligible_Released_Order");	
		String dosOrderInfo = orderId + "-" + DcNumber.DC_NO;
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.searchByDeliveryOrderId(orderId,DcNumber.DC_NO)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifySearchedDOSOrderIsDisplayed(dosOrderInfo, "Order");
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSP_DOD_DeliveryTests, "Delivery_Search_By_DOS_Order_And_DOS_Unit_For_Partially_Shipped_Order"}

	, description = "Verify search by order id", enabled = true, priority=1)
	public void Delivery_Search_By_DOS_Order_And_DOS_Unit_For_Partially_Shipped_Order(TestData data) throws Exception {
		addCloneIDHostname(data);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName=UserPool.getDeliveryUser();
		String orderId= getProductToTest("Partially_Shipped_DOD_Order");	
		String dosOrderInfo = orderId + "-" + DcNumber.DC_NO;
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.searchByDeliveryOrderId(orderId,DcNumber.DC_NO)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifySearchedDOSOrderIsDisplayed(dosOrderInfo, "Order");
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSPP0DeliveryTests, "Delivery_Search_By_DOS_Order_And_DOS_Unit_For_Shipped_Order"}

	, description = "Verify search by order id", enabled = true, priority=1)
	public void Delivery_Search_By_DOS_Order_And_DOS_Unit_For_Shipped_Order(TestData data) throws Exception {
		addCloneIDHostname(data);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName=UserPool.getDeliveryUser();
		String orderId= getProductToTest("Delivery_Shipped_Order");	
		String dosOrderInfo = orderId + "-" + DcNumber.DC_NO;
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.searchByDeliveryOrderId(orderId,DcNumber.DC_NO)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifySearchedDOSOrderIsDisplayed(dosOrderInfo, "Order");
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Verify_Performance_Support_Message_PhoneNumber"}
	, description = "Verify search by order id", enabled = true, priority=1)
	public void Verify_Performance_Support_Message_PhoneNumber(TestData data) throws Exception {
		addCloneIDHostname(data);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName=UserPool.getDeliveryUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.VerifyDeliveryAgent()
		.ClickOnOrderTab()
		.ClickOnPerformanceSupport("Phone Number")
		.VerifyPerformanceSupportMessage("Delivery Search - Simple - Phone Number ")
		.VerifyPerformanceSupportMessage("Search by Sold To, Deliver To or Alternate Phone #")
		.ClickOnPerformanceSupport("DOS Order Number")
		.VerifyPerformanceSupportMessage("Delivery Search – Advanced – Order Number")
		.VerifyPerformanceSupportMessage("DOS order number required")
		.ClickOnPerformanceSupport("Online Order Number")
		.VerifyPerformanceSupportMessage("Delivery Search – Advanced – Online Order Number")
		.VerifyPerformanceSupportMessage("Use the order number member received after they placed an order online.");
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Verify_Performance_Support_Message_OrderSearchResults"}
	, description = "Verify search by order id", enabled = true, priority=1)
	public void Verify_Performance_Support_Message_OrderSearchResults_RouteStatus(TestData data) throws Exception {
		addCloneIDHostname(data);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName=UserPool.getDeliveryUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.VerifyDeliveryAgent()
		.ClickOnOrderTab()
		.searchByDeliveryOrderId("100","")
		.ClickOnPerformanceSupportOnOrderSearchResults("Route Status:")
		.VerifyPerformanceSupportMessage("Delivery Search Results – Route Status")	
		.VerifyPerformanceSupportMessage("On-Time: ETA is within 2 hour window.")
		.VerifyPerformanceSupportMessage("Warning: Risk of missing 2 hour window.")
		.VerifyPerformanceSupportMessage("Late: Will arrive outside of 2 hour window.")
		.VerifyPerformanceSupportMessage("Updated: The Delivery team has arrived at the member’s home.");
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.QA_Environment,TestGroup.MSPP2DeliveryTests,"Verify_Performance_Support_Message_OrderSearchResults_OrderStatus"}
	, description = "Verify performance support message of order status", enabled = true, priority=1)
	public void Verify_Performance_Support_Message_OrderSearchResults_OrderStatus(TestData data) throws Exception {
		addCloneIDHostname(data);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName=UserPool.getDeliveryUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.VerifyDeliveryAgent()
		.ClickOnOrderTab()
		.searchByDeliveryOrderId("100","")
		.ClickOnPerformanceSupportOnOrderSearchResults("Order Status")
		.VerifyPerformanceSupportMessage("Released – Routed for delivery")	
		.VerifyPerformanceSupportMessage("Open – Not yet routed")
		.VerifyPerformanceSupportMessage("Partial – Some items delivered, other to be delivered")
		.VerifyPerformanceSupportMessage("Cancelled – Order cancelled")
		.VerifyPerformanceSupportMessage("Shipped – Order delivered");
	}
	
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"MSP_Delivery_UpdateAddress"}
	, description = "Update the address and verify the suggesseed address are displayed", enabled = true)
	public void MSP_Delivery_UpdateAddress(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("546410","8730")
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.updateAddress();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPP2DeliveryTests,"Verify_Performance_Support_Message_OrderSearchResults_ActionCenterTAB"}
	, description = "Verify performance support message in action center tab", enabled = true, priority=1)
	public void Verify_Performance_Support_Message_OrderSearchResults_ActionCenterTAB(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		String orderId= getProductToTest("Even_Exchange_Open_HD_Order");

		User user = new User();
		user.userName=UserPool.getDeliveryUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.VerifyDeliveryAgent()
		.ClickOnOrderTab()
		.searchByDeliveryOrderId(orderId,DcNumber.DC_NO)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		._NavigationAction()
		.ClickOnPerformanceSupportOnActionTab("Cancel Order")
		.VerifyPerformanceSupportMessage("Delivery needs to be cancelled.")	
		.VerifyPerformanceSupportMessage("Commercial orders")
		.VerifyPerformanceSupportMessage(" cannot be cancelled in MSP. Transfer caller to Commercial Customer Care using proper hold and transfer process.")
		.ClickOnPerformanceSupportOnActionTab("Reschedule Delivery")
		.VerifyPerformanceSupportMessage("Delivery needs to be rescheduled to a")	
		.VerifyPerformanceSupportMessage("future date")
		.VerifyPerformanceSupportMessage("If member requests an earlier delivery date then what is available and has been prevoiusly inconvenienced, see the EDD P&P on")
		.ClickOnPerformanceSupportOnActionTab("Hold for Future Delivery")
		.VerifyPerformanceSupportMessage("Member requests the delivery be postponed to an undetermined future date.")	
		.VerifyPerformanceSupportMessage("Schedule a date to follow up with the user regarding a new delivery date. Advise member to call back once they determine a convenient delivery date.")
		.VerifyPerformanceSupportMessage("If member requests an earlier delivery date then what is available and has been prevoiusly inconvenienced, see the EDD P&P on")
		.ClickOnPerformanceSupportOnActionTab("Queue for Follow Up")
		.VerifyPerformanceSupportMessage("Additional offline action is required to address the member’s delivery concern.")	
		.VerifyPerformanceSupportMessage("Delivery – Member History – Queue for follow-up");
		
	}
}


