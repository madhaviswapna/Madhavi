package com.shc.msp.ft.tests.Delivery;

import java.text.ParseException;

import org.testng.Assert;
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
		.rereserveItem("line item")
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
		.rereserveItem("whole order")
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
		.verifyRereservebuttonPresent();

	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Rereserve_Eligible_Partially_Shipped_Orders"}
	, description = "Verify whether rereserve button is present for partially HD shipped orders", enabled = true)
	public void MSP_Delivery_Test_Rereserve_Eligible_Partially_Shipped_Orders(TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		
		//String salescheck= getProductToTest("Rereserve_Eligible_Partially_Shipped_Order");

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
		.addlogType(TestStepType.GIVEN)
		.choosePartiallyshippedHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyRereservebuttonPresent();

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
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//String salescheck= getProductToTest("Pickup_Eligible_Partially_shipped_Order");

		String[] values= getProductToTest("Pickup_Eligible_Partially_shipped_Order",true).split(",");
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
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.cancelOrderDelivery("Whole order");

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
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.cancelOrderDelivery("Line item");

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
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.cancelOrderDelivery("Line item");
		
	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Open_HD_Order"}
	, description = "Verify if an order for open HD order can be rescheduled", enabled = true)
	public void MSP_Delivery_Test_Reschedule_Open_HD_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		
		String orderId= getProductToTest("Reschedule_Open_HD_Order",true);	
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
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Open_HD_Line_Item"}
	, description = "Verify if a line item for open HD order can be rescheduled", enabled = true)
	public void MSP_Delivery_Test_Reschedule_Open_HD_Line_Item(TestData data) throws Exception {
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
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleDeliveryOrder("OPEN","Line Item")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: PARTIAL RELEASE");
		
	} 
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Open_Dock_Pickup_Order"}
	, description = "Verify if an order for open dock pickup order can be rescheduled", enabled = true)
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
		.rescheduleDeliveryOrder("OPEN","ORDER")
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
		.rescheduleDeliveryOrder("RELEASED","ORDER")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("RESCHEDULED");
		
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Reschedule_Released_HD_Line_Item"}
	, description = "Verify if a line item for released order can be rescheduled", enabled = true)
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
	, description = "Verify if an order for partially shipped order can be rescheduled", enabled = true)
	public void MSP_Delivery_Test_Reschedule_Partially_Shipped_HD_Order(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		
		String salescheck=  getProductToTest("Reschedule_Partially_Shipped_HD_Order");	
		
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
		.rescheduleDeliveryOrder("PARTIALLY SHIPPED","ORDER")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("RESCHEDULED");
		
	}  
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
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
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.rescheduleDeliveryOrder("PARTIALLY SHIPPED","Line Item")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: PARTIAL RELEASE");
		
	}  
	
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
		.selectOrderInMyRecentDeliveryInteractions(1)
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.scheduleFollowUp();
		
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
		.verifyDeliveryOfflineagent();
		
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
	    
	    ProductData order = getProductDataToTest("MSP_Delivery_Pended_Order");
	    String dosorderID=order.getPartNumber().toString();
		String dosunitID=order.getUnitNumber().toString();
		
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
}
	
	
