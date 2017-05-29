package com.shc.msp.ft.tests.Delivery;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.dao.ProductData;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.tests.BaseTestsEx;
import com.shc.msp.ft.util.DcNumber;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.OHM;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class DeliveryActionCenter extends BaseTestsEx{




	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Rereserve_Eligible"}
	, description = "Verify whether rereserve button is present for open orders", enabled = true)
	public void MSP_Delivery_Test_Rereserve_Eligible_Open_Orders(TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//String phoneNumber ="6164503584";
		//String salescheck= getProductToTest("Rereserve_Eligible_Open_Order");	

		String orderId= getProductToTest("Rereserve_Eligible_Open_Order",true);	
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rereserveItem("open","line item")
		;

	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Rereserve_Eligible"}
	, description = "Verify whether rereserve button is present for open orders", enabled = true)
	public void MSP_Delivery_Test_Rereserve_Whole_Order(TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//String phoneNumber ="6164503584";
		//String salescheck= getProductToTest("Rereserve_Eligible_Open_Order");	

		String orderId= getProductToTest("Reschedule_Open_HD_Line_Item",true);	
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rereserveItem("open","whole order")
		;

	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Rereserve_Eligible_Released_Orders"}
	, description = "Verify whether rereserve button is present for released orders", enabled = true)
	public void MSP_Delivery_Test_Rereserve_Eligible_Released_Orders(TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//String salescheck= getProductToTest("Rereserve_Eligible_Released_Order");	

		String orderId= getProductToTest("Rereserve_Eligible_Released_Order",true);	
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseReleasedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyRereservebuttonPresent()
		.rereserveItem("released","whole order")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: RERESERVATION");

	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Rereserve_Eligible_Partially_Shipped_Orders"}
	, description = "Verify whether rereserve button is present for partially HD shipped orders", enabled = true)
	public void MSP_Delivery_Test_Rereserve_Eligible_Partially_Shipped_Orders(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		//String salescheck= getProductToTest("Rereserve_Eligible_Partially_Shipped_Order");

	//	String orderId= getProductToTest("Rereserve_Eligible_Partially_Shipped_Order",true);	
		//String orderId= getProductToTest("Rereserve_Partially_Shipped_Order");
		
		
		String[] values= getProductToTest("Rereserve_Eligible_Partially_shipped_Order",true).split(",");
		String orderId=values[0];
		String dc_no=values[1];
		System.out.println("orderId:"+orderId+" "+dc_no);
		
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, dc_no)
		.addlogType(TestStepType.GIVEN)
		.choosePartiallyshippedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rereserveItem("Partially Shipped","Line Item")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: RERESERVATION")
		._OrderDetailsAction()
		.goToActionCenter()
		.wrapUpOrderWithoutContactDelivery()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, dc_no)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.verifyActionCapturedInNotes("Re Reserve Delivery")
		;

	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Pickup_Eligible_Shipped_Order"}
	, description = "Verify if pickup button is available for shipped HD order", enabled = true)
	public void MSP_Delivery_Test_Pickup_Eligible_Shipped_HD_Order(TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();		

		String orderId= getProductToTest("Pickup_Eligible_Shipped_Order",true);	
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseShippedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyPickupbuttonPresent()
		.addlogType(TestStepType.THEN)
		.pickupOrder();



	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Pickup_Eligible_Partially_shipped_Order"}
	, description = "Verify if pickup button is available for partially shipped order", enabled = true)
	public void MSP_Delivery_Test_Pickup_Eligible_Partially_shipped_HD_Order (TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName="testdelivery0121";
		String[] values= getProductToTest("Pickup_Eligible_Partially_shipped_Order",true).split(",");
		String orderId=values[0];
		String dc_no=values[1];
		System.out.println("orderId:"+orderId+" "+dc_no);
		
		//String dosorderID= getProductToTest("Pickup_Partially_Shipped");
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, dc_no)
		.addlogType(TestStepType.GIVEN)
		.choosePartiallyshippedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyPickupbuttonPresent()
		.addlogType(TestStepType.THEN)
		.pickupOrder();



	} 
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Pickup_Order"}
	, description = "Verify order is created after pickup action", enabled = true)
	public void MSP_Delivery_Test_Pickup_Entire_Order (TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String dosorderID= getProductToTest("Pickup_Eligible_Shipped_Line_Item");

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseShippedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyPickupbuttonPresent()
		.addlogType(TestStepType.THEN)
		.pickupEntireOrder()
		;

	}  
	/*	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Pickup_Order"}
	, description = "Verify order is created after pickup action", enabled = true)
	public void MSP_Delivery_Test_Pickup_Order_Eligible_Shipped_Dockpickup_Order(TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Pickup_Eligible_Shipped_Dockpickup_Order",true);	
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseShippedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyPickupbuttonPresent()
		.addlogType(TestStepType.THEN)
		.pickupOrder();

	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Pickup_Eligible_Partially_shipped_Order"}
	, description = "Verify if pickup button is available for partially shipped order", enabled = true)
	public void MSP_Delivery_Test_Pickup_Eligible_Partially_shipped_Dockpickup_Order (TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String salescheck= getProductToTest("Pickup_Eligible_Partially_shipped_Dockpickup_Order");

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDOSSalesCheck(salescheck)
		.addlogType(TestStepType.GIVEN)
		.choosePartiallyshippedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyPickupbuttonPresent()
		.pickupOrder();


	}  */

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Cancel_Open_Whole_Order"}
	, description = "Verify if whole order can be cancelled", enabled = true)
	public void MSP_Delivery_Test_Cancel_Open_Whole_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String orderId= getProductToTest("Cancel_Eligible_Order",true);	
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifySearchedDOSOrderIsDisplayed("dosorderId", orderId)
		.addlogType(TestStepType.THEN)
		.cancelOrderDelivery("Whole order","Open","");


	}  


	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Cancel_Open_Line_Item"}
	, description = "Verify if a line item for open order can be cancelled", enabled = true)
	public void MSP_Delivery_Test_Cancel_Open_Line_Item(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String orderId= getProductToTest("Cancel_Eligible_Line_Item",true);	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.chooseOpenHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.cancelOrderDelivery("Line item","Open","");
	}  

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Cancel_Partially_Shipped_Line_Item"}
	, description = "Verify if a line item for Partially Shipped order can be cancelled", enabled = true)
	public void MSP_Delivery_Test_Cancel_Partially_Shipped_Line_Item(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//String orderId= getProductToTest("Partially_Shipped_HD_Line_Item",true);	

		String[] values= getProductToTest("Partially_Shipped_HD_Line_Item",true).split(",");
		String orderId=values[0];
		String dc_no=values[1];
		System.out.println("orderId:"+orderId+" "+dc_no);
		
		//String orderId= getProductToTest("Cancel_Partially_Shipped",true);
		
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId,dc_no)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.cancelOrderDelivery("Line item","Partially Shipped","");
	}  

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Cancel_Released_Line_Item"}
	, description = "Verify if a line item for released order can be cancelled", enabled = true)
	public void MSP_Delivery_Test_Cancel_Released_Line_Item(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String orderId= getProductToTest("Cancel_Eligible_Released_Line_Item",true);	
		System.out.println("salescheck:"+orderId);

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.chooseReleasedHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.cancelOrderDelivery("Line item","Open","");


	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Open_HD_Order"}
	, description = "Verify if an order for open HD order can be rescheduled", enabled = true)
	public void MSP_Delivery_Test_Reschedule_Open_HD_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Reschedule_Open_HD_Order",true);	
		//System.out.println("salescheck:"+orderId);

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.chooseOpenHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleServiceWindowOrder("OPEN","ENTIRE ORDER","Preferred Time Windows","")
		.addlogType(TestStepType.THEN)
		.verifyAdjustmentCapturedInInteraction("Reschedule Delivery Date")
		.addlogType(TestStepType.THEN)
		.goToDeliveryNotes()
		.addlogType(TestStepType.THEN)
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: RESCHED ORD")
		._OrderDetailsAction()
		.goToActionCenter()
		.wrapUpOrderWithoutContactDelivery();
		/* ._NavigationAction()
		 .addlogType(TestStepType.WHEN)
		 .searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		 .addlogType(TestStepType.WHEN)
		 .selectOrderInMyRecentDeliveryInteractions(1)
		 .closeWarningPopupWindow()
		 ._OrderDetailsAction()
		 .verifyActionCapturedInNotes("Reschedule Delivery Date")
		 ;*/

	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Open_HD_Line_Item"}
	, description = "Verify if a line item for open HD order can be rescheduled", enabled = true)
	public void MSP_Delivery_Test_Reschedule_Open_HD_Line_Item(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Reschedule_Open_HD_Line_Item",true);	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.chooseOpenHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleServiceWindowOrder("Open", "Line Item","Service Recovery Windows","");
		/*.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: PARTIAL RELEASE")*/;

	} 


	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Open_Dock_Pickup_Order"}
	, description = "Verify if an order for open dock pickup order can be rescheduled", enabled = false)
	public void MSP_Delivery_Test_Reschedule_Open_Dock_Pickup_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String salescheck= getProductToTest("Reschedule_Open_Dock_Up_Order");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDOSSalesCheck(salescheck)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleServiceWindowOrder("OPEN","ENTIRE ORDER","Preferred Time Windows","")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("RESCHEDULED");

	}  
	/*@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Open_Dock_Pickup_Line_Item"}
	, description = "Verify if a line item for open dock pickup order can be rescheduled", enabled = true)
	public void MSP_Delivery_Test_Reschedule_Open_Dock_Pickup_Line_Item(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String salescheck= getProductToTest("Reschedule_Open_Dock_Up_Line_Item");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDOSSalesCheck(salescheck)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleDeliveryOrder("OPEN","Line Item")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("RESCHEDULED");

	}  */

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Released_HD_Order"}
	, description = "Verify if an order for released order can be rescheduled", enabled = true)
	public void MSP_Delivery_Test_Reschedule_Released_HD_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Rereserve_Eligible_Released_Order",true);	
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.chooseReleasedHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleServiceWindowOrder("Released", "ENTIRE ORDER","Service Recovery Windows","");

		/*.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: RESCHED ORD")
		.verifyAdjustmentCapturedInInteraction("Reschedule Delivery Date")
		._OrderDetailsAction()
		 .goToActionCenter()
		 .wrapUpOrderWithoutContactDelivery()
		 ._NavigationAction()
		 .addlogType(TestStepType.WHEN)
		 .searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		 .addlogType(TestStepType.WHEN)
		 .selectOrderInMyRecentDeliveryInteractions(1)
		 .closeWarningPopupWindow()
		 ._OrderDetailsAction()
		 .verifyActionCapturedInNotes("Reschedule Delivery Date")*/
		;
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Released_HD_Line_Item"}
	, description = "Verify if a line item for released order can be rescheduled", enabled = false)
	public void MSP_Delivery_Test_Reschedule_Released_HD_Line_Item(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		//String salescheck= getProductToTest("Released_HD_Line_Item");	
		String orderId= getProductToTest("Released_HD_Line_Item",true);	
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleDeliveryOrder("RELEASED","Line item")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("RESCHEDULED");

	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Partially_Shipped_HD_Order"}
	, description = "Verify if an order for partially shipped order can be rescheduled", enabled = true, priority=1)
	public void MSP_Delivery_Test_Reschedule_Partially_Shipped_HD_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		//String dosorderId= getProductToTest("Partially_Shipped_Order");	
		
		String[] values= getProductToTest("Reschedule_Eligible_Partially_Shipped_Order",true).split(",");
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
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleServiceWindowOrder("Open", "ENTIRE ORDER","Unrestricted Time Window","")
		.verifyAdjustmentCapturedInInteraction("Reschedule Delivery Date")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: RESCHED ORD")
		._OrderDetailsAction()
		.goToActionCenter()
		.wrapUpOrderWithoutContactDelivery()
		/*._NavigationAction()
		 .addlogType(TestStepType.WHEN)
		 .searchByDOSSalesCheck(salescheck)
		 .addlogType(TestStepType.WHEN)
		 .selectOrderInMyRecentDeliveryInteractions(1)
		 .closeWarningPopupWindow()
		 ._OrderDetailsAction()
		 .verifyActionCapturedInNotes("Reschedule Delivery Date")*/
		;

	}  

	/*@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Partially_Shipped_HD_Line_Item"}
	, description = "Verify if a line item for partially shipped order can be cancelled", enabled = true)
	public void MSP_Delivery_Test_Reschedule_Partially_Shipped_HD_Line_Item(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		//String salescheck=  getProductToTest("Partially_Shipped_HD_Line_Item");	

		String[] values= getProductToTest("Partially_Shipped_HD_Line_Item",true).split(",");
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
		.addlogType(TestStepType.WHEN)
		.choosePartiallyshippedHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleServiceWindowOrder("Open", "LINE ITEM","Unrestricted Time Window","")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: PARTIAL RELEASE");

	}  
*/
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Schedule_Follow_Up_Open_HD_Order"}
	, description = "Verify if an open HD order can be scheduled for follow up", enabled = true)
	public void MSP_Delivery_Test_Schedule_Follow_Up_Open_HD_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Reschedule_Open_HD_Order",true);	
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.chooseOpenHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.scheduleFollowUp()
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: UPDATE PEND CODE");
	}  

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"MSP_Delivery_Test_Schedule_Follow_Up_Open_HD_Order_Captured_Notes_Interactions"}
	, description = "MSP_Delivery_Test_Schedule_Follow_Up_Open_HD_Order_Captured_Notes_Interactions", enabled = true)
	public void MSP_Delivery_Test_Schedule_Follow_Up_Open_HD_Order_Captured_Notes_Interactions(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Reschedule_Open_HD_Order",true);	
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.scheduleFollowUp()
		.verifyAdjustmentCapturedInInteraction("Hold for Future Date")
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.wrapUpOrderWithoutContactDelivery()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.verifyActionCapturedInNotes("Hold for Future Date")
		;


	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Schedule_Follow_Up_Released_HD_Order"}
	, description = "Verify if an open HD order can be scheduled for follow up", enabled = true)
	public void MSP_Delivery_Test_Schedule_Follow_Up_Released_HD_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Reschedule_Released_HD_Order",true);	
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.scheduleFollowUp()
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: UPDATE PEND CODE");


	} 

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"MSP_Delivery_Test_Schedule_Follow_Up_Released_HD_Order_Captured_Notes_Interactions"}
	, description = "MSP_Delivery_Test_Schedule_Follow_Up_Released_HD_Order_Captured_Notes_Interactions", enabled = true)
	public void MSP_Delivery_Test_Schedule_Follow_Up_Released_HD_Order_Captured_Notes_Interactions(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Reschedule_Released_HD_Order",true);	
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.scheduleFollowUp()
		.verifyAdjustmentCapturedInInteraction("Hold for Future Date")
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.wrapUpOrderWithoutContactDelivery()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.verifyActionCapturedInNotes("Hold for Future Date")
		;

	} 




	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_verify_ReasonCode_HD_Account_Validation_Queue"}
	, description = "Verify if the reaons codes whle trying to wrap up an order in HD -Account validation queue", enabled = true)
	public void MSP_Delivery_Test_verify_ReasonCode_HD_Account_Validation_Queue(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName="testdelivery0122";
		user.password="TestPassword";

		String orderId= getProductToTest("Reschedule_Open_HD_Order",true);

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.deleteCasesforOrderfromDB("queue.queueDescreption", "HD - Account Validation")
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Member receiving notifications for delivery not purchased")
		.addlogType(TestStepType.WHEN)
		._NavigationAction()
		.verifyDeliveryOfflineagent()
		.selectCaseFromAssignedQueue()
		._OrderDetailsAction()
		.goToActionCenter()
		.verifyReasoncodeAndWrapup();
	} 


	/**
	 * Verification to assert that pended orders don't have the 'UPDATE' button
	 */
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests, "MSP_Delivery_Test_UpdateButton_For_PendedOrders"}
	, description = "Verify update option for pended orders", enabled = true, priority=1)
	public void MSP_Delivery_Agent_Update_Button_When_Pended(TestData data) throws Exception {
		addCloneIDHostname(data);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName=UserPool.getDeliveryUser();
		
		String[] arr= getProductToTest("Pended_Open_Order",true).split(",");
		//ProductData order = getProductDataToTest("Pended_Open_Order");
		String dosorderID=arr[0];
		String dosunitID=arr[1];

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
		.verifyUpdateOptionForPendedOrder(dosorderID,dosunitID);
	}
	/*@Test(dataProvider = "DP_reasonName", groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Verify_RPA_Concession_Request_Reason_Code_Presence"}
	, description = "Verify RPA concession reason Code presence", enabled = true)
	public void Verify_RPA_Concession_Request_Reason_Code_Presence(String reasonName, boolean presence) throws ParseException {

		TestData<String, String, Integer> data = new TestData<String, String, Integer>(reasonName, String.valueOf(presence), 1);
		addCloneIDHostname(data);

		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//String dosorderID= getProductToTest("Pickup_Eligible_Shipped_Line_Item");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("5000", "8720")
		.addlogType(TestStepType.GIVEN)
		.chooseShippedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyReasonCodePresence(reasonName, presence);
	}

	@DataProvider (name="DP_reasonName",parallel=true)
	public Object[][] DP_queueName() throws Exception{
		Object[][] testData = null;
		//List<String> list= new ArrayList<String>();
		String[] reason_presence=null;
		String reasonCodes=null;
		int i = 0;
		List<Object> keywords= getAllProductToTest("reasonNameShippedOrder");
		Iterator<Object> itr= keywords.iterator();
		testData=new Object[keywords.size()][2];
		while (itr.hasNext()) {

			ProductData part_num = (ProductData) itr.next();
			reasonCodes=part_num.getPartNumber().toString();
			reason_presence = reasonCodes.split(",");
			testData[i][0]=reason_presence[0];
			testData[i][1]=Boolean.parseBoolean(reason_presence[1]);
			System.out.println("testing data "+(i+1)+": "+testData[i][0]+","+testData[i][1]);
			//list.add(reasonCodes);
			i++;
		}


		testData=new Object[list.size()][2];
		for(int i=0;i<list.size();i++){
			reason_presence=list.get(i).split(",");			
			testData[i][0]=reason_presence[j];
			testData[i][1]=Boolean.parseBoolean(reason_presence[j+1]);

			System.out.println("testing data "+(i+1)+": "+testData[i][0]+","+testData[i][1]);
		return testData;
	}*/

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Verify_RPA_Concession_Request_Reason_Code_Presence"}
	, description = "Verify RPA concession reason code is not present", enabled = true)
	public void Verify_RPA_Concession_Request_Reason_Code_Presence(TestData data) throws ParseException {

		addCloneIDHostname(data);
		List<Object> keywords= getAllProductToTest("reasonNameShippedOrder");
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String dosorderID= getProductToTest("Delivery_Shipped_Order");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseShippedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAllReasonCodePresence(keywords,"delivery");
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,"Verify_Changed_Reason_Code_Descriptions_For_Shipped_Order"}
	, description = "Verify changed reason code appears correctly for shipped order", enabled = false)
	public void Verify_Changed_Reason_Code_Descriptions_For_Shipped_Order(TestData data) throws ParseException {

		addCloneIDHostname(data);
		List<Object> keywords= getAllProductToTest("reasonNameShippedOrder");
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String dosorderID= getProductToTest("Delivery_Shipped_Order");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseShippedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAllReasonCodePresence(keywords,"delivery");
	}


	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,"Verify_Case_Exist_Popup_for_Account_Validation_Queue"}
	, description = "Verify case exists pop up comes in account validation queue for duplicate case creation", enabled = true)
	public void Verify_Case_Exist_Popup_for_Account_Validation_Queue(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String dosorderID= getProductToTest("Rereserve_Eligible_Partially_Shipped_Order");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.deleteCasesforOrderfromDB("queue.queueDescreption","HD - Account Validation")
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Account Validation - Member receiving notifications for delivery not purchased")
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.logout()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Account Validation - Member receiving notifications for delivery not purchased");


	} 

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,"Verify_Case_Exist_Popup_for_Mattress_Exchange_Queue"}
	, description = "Verify case exists pop up comes mattress exchange queue for duplicate case creation", enabled = true)
	public void Verify_Case_Exist_Popup_for_Mattress_Exchange_Queue(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String dosorderID= getProductToTest("Rereserve_Eligible_Partially_Shipped_Order");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.deleteCasesforOrderfromDB("queue.queueDescreption", "HD - Mattress Exchange Request")
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Mattress Exchange - Member requests matress exchange")
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.logout()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Mattress Exchange - Member requests matress exchange");


	} 

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,"Verify_Case_Exist_Popup_for_Earlier_Delivery_Date_Queue"}
	, description = "Verify case exists pop up comes in earlier delivery date queue for duplicate case creation", enabled = true)
	public void Verify_Case_Exist_Popup_for_Earlier_Delivery_Date_Queue(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String dosorderID= getProductToTest("Rereserve_Eligible_Partially_Shipped_Order");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.deleteCasesforOrderfromDB("queue.queueDescreption", "HD - Earlier Delivery Date")
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Earlier Delivery Date Request")
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.logout()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Earlier Delivery Date Request");


	} 

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,"Verify_Case_Exist_Popup_for_Manual_Work_Order_Request_Queue"}
	, description = "Verify case exists pop up comes in  Manual Work Order Request queue for duplicate case creation", enabled = true)
	public void Verify_Case_Exist_Popup_for_Manual_Work_Order_Request_Queue(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String dosorderID= getProductToTest("Rereserve_Eligible_Partially_Shipped_Order");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.deleteCasesforOrderfromDB("queue.queueDescreption", "HD - Manual Work Order Request")
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Manual Work Order Request- delivery team to return to complete delivery service")
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.logout()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Manual Work Order Request- delivery team to return to complete delivery service");


	} 

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,"Verify_Case_Exist_Popup_for_Part_Request_Queue"}
	, description = "Verify case exists pop up comes in Part_Request queue for duplicate case creation", enabled = true)
	public void Verify_Case_Exist_Popup_for_Part_Request_Queue(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String dosorderID= getProductToTest("Rereserve_Eligible_Partially_Shipped_Order");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.deleteCasesforOrderfromDB("queue.queueDescreption", "HD - Part Request")
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Part Request - Member requests replacement of missing, broken, or non-functional part on a recently delivered item")
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.logout()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Part Request - Member requests replacement of missing, broken, or non-functional part on a recently delivered item");

	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,"Verify_Case_Exist_Popup_for_Uneven_Exchange_com_Queue"}
	, description = "Verify case exists pop up comes in Uneven Exchange.com  queue for duplicate case creation", enabled = true)
	public void Verify_Case_Exist_Popup_for_Uneven_Exchange_com_Queue(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String dosorderID= getProductToTest("Delivery_Shipped_Order");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.deleteCasesforOrderfromDB("queue.queueDescreption", "HD - Uneven Exchange .com")
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Uneven Exchange .com - Exchange for different item than the one previously delivered")
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.logout()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Uneven Exchange .com - Exchange for different item than the one previously delivered");

	}

	//verify all the reason codes present in the queue for followup in delivery open order with pend code as TBC
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.QA_Environment,"Verify_QueueForFollowup_ReasonCode_PENDCODE_TBC_OpenOrder"}
	, description = "verify all the queues for delivery open order with ped code TBC", enabled = true)
	public void Verify_QueueForFollowup_ReasonCode_PENDCODE_TBC_OpenOrder(TestData data) throws ParseException {

		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String dosorderID= getProductToTest("MSP_DeliveryOpenOrder_PendCode_TBC");	

		ArrayList<String> reasonCode=new ArrayList<String>();
		reasonCode.add("Select");
		reasonCode.add("Account Validation - Member receiving notifications for delivery not purchased");
		reasonCode.add("Carrier Settlement Dispute - Member is unhappy with settlement offer");
		reasonCode.add("Certificate of Insurance Request");
		reasonCode.add("DDL Pend - Delivery Date Late");
		reasonCode.add("DDS Pend - Source Ship Problem");
		reasonCode.add("Earlier Delivery Date Request");
		reasonCode.add("Gas Leak - Member requests urgent assistance with gas leak");
		reasonCode.add("Go Back Request - Member threatening to cancel");
		reasonCode.add("Hybrid Ship Confirmed in Error - Member has not received product");
		reasonCode.add("Installation Request - Member requesting Installation");
		reasonCode.add("MDO Ship Confirmed in Error - Member has not received product");
		reasonCode.add("Open Damage Claim >72 Hrs - Member has not been contacted");
		reasonCode.add("Recovery- Member has experienced a failed delivery,reselection needed");
		reasonCode.add("Research Check - Member requesting status of concession");
		reasonCode.add("Research Credit - Member requesting status of concession");
		reasonCode.add("Research Gift Card - Member requesting status of concession");
		reasonCode.add("Research Parts - Member requesting status of concession");
		reasonCode.add("Research RPA - Member requesting status of concession");
		reasonCode.add("Ship Confirm Requested - Member has received merchandise, order status needs to be updated");
		reasonCode.add("Shuttle/Outlet Product Inquiry - Member requests info on Shuttle / Store Stock");
		reasonCode.add("Special Handling - Special Handling required on scheduled delivery");
		reasonCode.add("T Pend Past Due - TB Pend Code to be worked");
		reasonCode.add("Turnaround Request - Member Stating they will Cancel");
		reasonCode.add("Water Leak - Member requests urgent assistance with water leak");

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAllReasonCodePresence(reasonCode);
	}

	//verify all the reason codes present in the queue for followup in delivery open order with pend code as TBC
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.QA_Environment,"Verify_QueueForFollowup_ReasonCode_PENDCODE_TBH_ReleasedOrder"}
	, description = "verify all the queues for delivery released order with ped code TBH", enabled = true)
	public void Verify_QueueForFollowup_ReasonCode_PENDCODE_TBH_ReleasedOrder(TestData data) throws ParseException {
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String dosorderID= getProductToTest("MSP_DeliveryReleasedOrder_PendCode_TBH");	

		ArrayList<String> reasonCode=new ArrayList<String>();
		reasonCode.add("Select");
		reasonCode.add("Account Validation - Member receiving notifications for delivery not purchased");
		reasonCode.add("Carrier Settlement Dispute - Member is unhappy with settlement offer");
		reasonCode.add("Certificate of Insurance Request");
		reasonCode.add("DDL Pend - Delivery Date Late");
		reasonCode.add("DDS Pend - Source Ship Problem");
		reasonCode.add("Gas Leak - Member requests urgent assistance with gas leak");
		reasonCode.add("Go Back Request - Member threatening to cancel");
		reasonCode.add("Hybrid Ship Confirmed in Error - Member has not received product");
		reasonCode.add("Installation Request - Member requesting Installation");
		reasonCode.add("Manual Work Order Request- delivery team to return to complete delivery service");
		reasonCode.add("Mattress Exchange - Member requests matress exchange");
		reasonCode.add("MDO Ship Confirmed in Error - Member has not received product");
		reasonCode.add("Open Damage Claim >72 Hrs - Member has not been contacted");
		reasonCode.add("Part Request - Member requests replacement of missing, broken, or non-functional part on a recently delivered item");
		reasonCode.add("Recovery- Member has experienced a failed delivery,reselection needed");
		reasonCode.add("Research Check - Member requesting status of concession");
		reasonCode.add("Research Credit - Member requesting status of concession");
		reasonCode.add("Research Gift Card - Member requesting status of concession");
		reasonCode.add("Research Parts - Member requesting status of concession");
		reasonCode.add("Research RPA - Member requesting status of concession");
		reasonCode.add("Ship Confirm Requested - Member has received merchandise, order status needs to be updated");
		reasonCode.add("Shuttle/Outlet Product Inquiry - Member requests info on Shuttle / Store Stock");
		reasonCode.add("Special Handling - Special Handling required on scheduled delivery");
		reasonCode.add("T Pend Past Due - TB Pend Code to be worked");
		reasonCode.add("Turnaround Request - Member Stating they will Cancel");
		reasonCode.add("Uneven Exchange - Exchange for a different item than is currently in the home.");
		reasonCode.add("Water Leak - Member requests urgent assistance with water leak");

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseReleasedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAllReasonCodePresence(reasonCode);
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,"Rereserve_Not_Eligible_For_Shipped_Orders"}
	, description = "Verify whether rereserve button is not present for shipped orders", enabled = true)
	public void Rereserve_Not_Eligible_For_Shipped_Orders(TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//String phoneNumber ="6164503584";
		//String salescheck= getProductToTest("Rereserve_Eligible_Open_Order");	

		String dosorderID= getProductToTest("Delivery_Shipped_Order");	

		System.out.println("OrderId:"+dosorderID);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(dosorderID, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseShippedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rereserveItem("shipped","")
		;
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP2DeliveryTests,"MSP_Delivery_Test_Reschedule_Open_Installation_HD_Order"}
	, description = "MSP_Delivery_Test_Reschedule_Open_Installation_HD_Order", enabled = true)
	public void MSP_Delivery_Test_Reschedule_Open_Installation_HD_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		getContext().put("orderWith", "InstallationItem");
		String orderId= getProductToTest("Reschedule_Open_Installation_HD_Order");
		//String orderId= "209610";
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleDeliveryOrder("OPEN","ORDER")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: RESCHED ORD");
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"MSP_Delivery_Test_Reschedule_Open_Order_Having_KitHeader"}
	, description = "Verify if an order having kit header can be re scheduled", enabled = true)
	public void MSP_Delivery_Test_Reschedule_Open_Order_Having_KitHeader(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Reschedule_Open_KitHeader_Order");	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleDeliveryOrder("OPEN","ORDER")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: RESCHED ORD");
	} 

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"MSP_Delivery_Update_Contact_Delivery"}
	, description = "Update the contact details and verify notes and interaction are captured", enabled = true)
	public void MSP_Delivery_Test_Pickup_not_Eligible_Cancelled_HD_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();		

		String orderId= getProductToTest("Cancelled_order");
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyPickupbuttonnotPresent();

	} 


	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Delivery_Cancel_Order_Captured_Notes_Verification"}
	, description = "Cancel the whole order and verify notes and interaction are captured", enabled = true)
	public void Delivery_Cancel_Order_Captured_Notes_Verification(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Cancel_Eligible_Order",true);	
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyLineItemDetail("Open")
		.cancelOrderDelivery("Whole order","Open","")
		.verifyAdjustmentCapturedInInteractionsForCancelOrder("Cancel Delivery Order")
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.wrapUpOrderWithoutContactDelivery()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.verifyActionCapturedHistoryNotes();
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Delivery_Rereserve_Order_Captured_Notes_Verification"}
	, description = "Re-reserve whole order and verify Delivery notes", enabled = true)
	public void Delivery_Rereserve_Order_Captured_Notes_Verification(TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		List<String> list= new ArrayList<String>();
		list.add("MSP  ENTIRE ORDER HAS BEEN CANCELLED");
		list.add("MSP USER"+": "+user.userName);
		list.add("OSH/MSO-WEB: RERESERVATION");
		list.add("MSP USER"+": "+user.userName);

		String orderId= getProductToTest("Reschedule_Open_HD_Line_Item",true);	
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rereserveItem("open","whole order")
		._NavigationAction()
		.logout()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToDeliveryNotes()
		.verifyDeliveryOSHNotes(list)
		;
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Delivery_SCIM_Update_Open_Order"}
	, description = "Verify if Scim code can be updated and verify interaction and contact history notes ", enabled = true)
	public void Delivery_SCIM_Update_Open_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		String orderId= getProductToTest("Reschedule_Open_HD_Line_Item",true);
		System.out.println("OrderId:"+orderId);

		As.guestUser.goToHomePage()

		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)

		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()

		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()

		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)

		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)

		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyupdateScimCode("Open")

		.addlogType(TestStepType.THEN)
		.verifyLineItemDetail("Open")

		.addlogType(TestStepType.THEN)
		.verifyAdjustmentCapturedInInteractionsForScimCode("Update Scim Code")

		/*		.addlogType(TestStepType.WHEN)
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
		.verifyActionCapturedHistoryNotes()*/;

	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Recovery_Service_Windows_Available_Open_Order_Verification"}
	, description = "Verify all three recovery time window are available for open order", enabled = true)
	public void Recovery_Service_Windows_Available_Open_Order_Verification(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Reschedule_Open_HD_Order",true);	
		System.out.println("OrderId:"+orderId);

		As.guestUser.goToHomePage()

		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)

		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()

		.closeWarningPopupWindow()

		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)

		.addlogType(TestStepType.WHEN)
		.chooseOpenHDOrders()

		._OrderDetailsAction()

		.addlogType(TestStepType.WHEN)
		.goToActionCenter()

		.addlogType(TestStepType.THEN)
		.recoveryServiceWindowVerification();

	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Delivery_SCIM_Update_Captured_Notes_Verification_ReleasedOrder"}
	, description = "Verify if Scim code can be updated and verify interaction and contact history notes ", enabled = true)
	public void Delivery_SCIM_Update_Captured_Notes_Verification_ReleasedOrder(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		//String orderId= getProductToTest("Rereserve_Eligible_Partially_Shipped_Order",true);
		String orderId= getProductToTest("Rereserve_Eligible_Released_Order",true);	
		System.out.println("--------------------------------------------------------------------OrderId:"+orderId);

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyupdateScimCode("Released")		
		.addlogType(TestStepType.THEN)
		.verifyLineItemDetail("Released")		
		.addlogType(TestStepType.THEN)
		.verifyAdjustmentCapturedInInteractionsForScimCode("Update Scim Code")		
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()		
		.addlogType(TestStepType.WHEN)
		.wrapUpOrderWithoutContactDelivery();
		/*._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyActionCapturedHistoryNotes();*/
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Delivery_SCIM_Update_Captured_Notes_Verification"}
	, description = "Verify if Scim code can be updated and verify interaction and contact history notes ", enabled = true)
	public void Delivery_SCIM_Update_Captured_Notes_Verification_PartiallyShippedOrder(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		String orderId= getProductToTest("Reschedule_Released_HD_Order",true);
		System.out.println("--------------------------------------------------------------------OrderId:"+orderId);

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyupdateScimCode("Released")		
		.addlogType(TestStepType.THEN)
		.verifyLineItemDetail("Released")		
		.addlogType(TestStepType.THEN)
		.verifyAdjustmentCapturedInInteractionsForScimCode("Update Scim Code")		
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
		.verifyActionCapturedHistoryNotes();
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Reschedule_Preferred_Service_Window_Verification_Open_Order"}
	, description = "Verify a time window in order detail page from preferred time window", enabled = true)
	public void Reschedule_Preferred_Service_Window_Verification_Open_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Reschedule_Open_HD_Order",true);	
		System.out.println("orderId:"+orderId);

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleServiceWindowOrder("OPEN","ORDER","Preferred Time Windows","");
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Recovery_Service_Windows_Available_Partially_Shipped_Order_Verification"}
	, description = "Verify all three recovery time window are available for partially Shipped order", enabled = true)
	public void Recovery_Service_Windows_Available_Partially_Shipped_Order_Verification(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Rereserve_Eligible_Partially_Shipped_Order");	
		System.out.println("OrderId:"+orderId);


		As.guestUser.goToHomePage()

		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)

		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()

		.closeWarningPopupWindow()

		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)

		.addlogType(TestStepType.WHEN)
		.choosePartiallyshippedHDOrders()

		._OrderDetailsAction()

		.addlogType(TestStepType.WHEN)
		.goToActionCenter()

		.addlogType(TestStepType.THEN)
		.recoveryServiceWindowVerification();

	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Schedule_Follow_Up_PartiallyShipped_Order"}
	, description = "Verify if an open HD order can be scheduled for follow up", enabled = true)
	public void MSP_Delivery_Test_Schedule_Follow_Up_PartiallyShipped_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//String orderId= getProductToTest("Schedule_Follow_Up_Partially_Shipped");
		
		String[] values= getProductToTest("Scheduleforfollowup_Eligible_Partially_Shipped_Order",true).split(",");
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
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.scheduleFollowUp()
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: UPDATE PEND CODE")
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN);

	}



	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Delivery_Cancel_Order_Captured_Notes_Verification_Released_Order"}
	, description = "Cancel the whole order and verify notes and interaction are captured for Released order", enabled = true)
	public void Delivery_Cancel_Order_Captured_Notes_Verification_Released_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Reschedule_Released_HD_Order",true);	
		//System.out.println("orderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.GIVEN)
		.chooseReleasedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyLineItemDetail("Released")
		.addlogType(TestStepType.THEN)
		.cancelOrderDelivery("Whole order","Released","Delivery Driver")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: CANCEL ORDER")
		.verifyAdjustmentCapturedInInteractionsForCancelOrder("Cancel Delivery Order")
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.wrapUpOrderWithoutContactDelivery()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.verifyActionCapturedHistoryNotes();
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Released_HD_Order_Unrestricted_time_window"}
	, description = "Verify if an order for released order can be rescheduled", enabled = true)
	public void MSP_Delivery_Test_Reschedule_Released_HD_Order_Unrestricted_time_window(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();

		String orderId= getProductToTest("Rereserve_Eligible_Released_Order",true);	
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleServiceWindowOrder("Released", "ENTIRE ORDER","Unrestricted Time Window","Delivery Driver");
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"MSP_Delivery_Test_Cancel_Open_Whole_Order_CaptureNotesVerification"}
	, description = "Verify if whole order can be cancelled", enabled = true)
	public void MSP_Delivery_Test_Cancel_Open_Whole_Order_CaptureNotesVerification(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String orderId= getProductToTest("Cancel_Eligible_Order",true);	
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifySearchedDOSOrderIsDisplayed("dosorderId", orderId)
		.addlogType(TestStepType.THEN)
		.cancelOrderDelivery("Whole order","Open","")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: CANCEL ORDER")
		._OrderDetailsAction()
		.goToActionCenter()
		.wrapUpOrderWithoutContactDelivery()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.verifyActionCapturedInNotes("Cancel Delivery Order");


	}  
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"MSP_Delivery_Test_Cancel_Open_Line_Item"}
	, description = "Verify if a line item for open order can be cancelled", enabled = true)
	public void MSP_Delivery_Test_Cancel_Open_Line_Item_VerifyContactHistory(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String orderId= getProductToTest("Cancel_Eligible_Line_Item",true);	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.chooseOpenHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.cancelOrderDelivery("Line item","Open","")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: ITEM CANCEL")
		._OrderDetailsAction()
		.goToActionCenter()
		.wrapUpOrderWithoutContactDelivery()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.verifyActionCapturedInNotes("Cancel Delivery Item");
	}  
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Delivery_SCIM_Update_Captured_Notes_Verification_Open_Order"}
	, description = "Verify if Scim code can be updated and verify interaction and contact history notes ", enabled = true)
	public void Delivery_SCIM_Update_Captured_Notes_Verification_Open_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		System.out.println();
		List<String> list= new ArrayList<String>();
		list.add("SCIM HANDLE CODE CHANGED");
		list.add("MSP USER"+": "+user.userName);
		list.add("OSH/MSO-WEB: SCIM HANDLE CODE UPD");

		String orderId= getProductToTest("Reschedule_Open_HD_Line_Item",true);
		System.out.println("OrderId:"+orderId);

		As.guestUser.goToHomePage()

		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)

		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()

		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()

		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, DcNumber.DC_NO)

		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)

		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyupdateScimCode("Open")

		.addlogType(TestStepType.THEN)
		.verifyLineItemDetail("Open")

		.addlogType(TestStepType.THEN)
		.verifyAdjustmentCapturedInInteractionsForScimCode("Update Scim Code")

		.addlogType(TestStepType.WHEN)
		.goToDeliveryNotes()

		.addlogType(TestStepType.THEN)
		.verifyDeliveryOSHNotes(list)

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
		.verifyActionCapturedHistoryNotes();

	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"MSP_Delivery_Test_Cancel_not_Eligible_Released_TBC_Order"}
	, description = "verify cancel nutton is not present for released TBC order", enabled = true)
	public void MSP_Delivery_Test_Cancel_not_Eligible_Released_TBC_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();		

		String[] arr= getProductToTest("Released_TBC_Order").split(",");
		String orderId=arr[0];
		String dc_number=arr[1];
		
		System.out.println("OrderId:"+orderId);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, dc_number)
		.addlogType(TestStepType.GIVEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyCancelbuttonnotPresent();
	} 
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"MSP_Delivery_Test_verify_ReasonCode_HD_Uneven_Exchange_Queue"}
	, description = "Verify if the reaons codes whle trying to wrap up an order in HD_Uneven_Exchange queue", enabled = true)
	public void MSP_Delivery_Test_verify_ReasonCode_HD_Uneven_Exchange_Queue(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName="testdelivery0122";
		user.password="TestPassword";
		List<Object> keywords= getAllProductToTest("unevenExchangeReasonCodes");

		//String orderId= getProductToTest("Pickup_Eligible_Shipped_Order");

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.deleteCasesforOrderfromDB("queue.queueDescreption", "HD - Uneven Exchange")
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("384500", DcNumber.DC_NO)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Uneven Exchange - Exchange for a different item than is currently in the home.")
		.addlogType(TestStepType.WHEN)
		._NavigationAction()
		.verifyDeliveryOfflineagent()
		.selectCaseFromAssignedQueue()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.WHEN)
		.verifyReasoncodeAndWrapup()
		.addlogType(TestStepType.THEN)
		.verifyAllReasonCodePresence(keywords,"deliveryOffline");
	
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Rereserve_Eligible"}
	, description = "Verify whether rereserve button is present for open orders", enabled = true)
	public void MSP_Delivery_Test_Rereserve_Whole_MultiQuantity_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		
		Calendar currentdate = Calendar.getInstance();
        String strdate = null;
        DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        strdate = formatter.format(currentdate.getTime());
        TimeZone obj = TimeZone.getTimeZone("CST");
        formatter.setTimeZone(obj);
        strdate = formatter.format(currentdate.getTime());

        User user = new User(); user.userName=UserPool.getDeliveryUser();
		List<String> list= new ArrayList<String>();
		list.add("RERES DLVR DT SUPPORTED");
		list.add("MSP USER"+": "+user.userName);
		list.add("OSH/MSO-WEB: RERESERVATION");
		list.add("MSP USER"+": "+user.userName);
		
		
		String[] arr= getProductToTest("Open_MultiQuantity_Order").split(",");
		String orderId=arr[0];
		String dc_number=arr[1];
		
		String ss="MSP USER"+": "+user.userName;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)	
		.searchByDeliveryOrderId(orderId,dc_number)
		.addlogType(TestStepType.GIVEN)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.captureSalescheckNumber()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rereserveItem("open","whole order")
		.verifyNewOrderhassameSalescheckNumber()
		.goToDeliveryNotes()
		.verifyDeliveryOSHNotes(list)
		.verifyDateandUserInDeliveryOSHNote(strdate,ss);
	}	
}