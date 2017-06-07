package com.shc.msp.ft.tests.Delivery;

import org.testng.annotations.Test;

import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.tests.BaseTestsEx;
import com.shc.msp.ft.util.DcNumber;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class EvenExchange extends BaseTestsEx{

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Test_Even_Exchange_Partially_Shipped_Order"}
	, description = "Even Exchange for Partially Shipped Order", enabled = true)
	public void Test_Even_Exchange_Partially_Shipped_Order(TestData data) {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		String orderType = "Partially Shipped";

		String[] values= getProductToTest("Pickup_Eligible_Partially_shipped_Order",true).split(",");
		String orderId=values[0];
		String dc_no=values[1];
		System.out.println("orderId:"+orderId+" "+dc_no);

		//String orderId= getProductToTest("Even_Exchange_Partially_Shipped");

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
		.chooseHDOrders(orderType)

		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()

		.addlogType(TestStepType.THEN)
		.verifyEvenExchangeEligibility(orderType)

		.addlogType(TestStepType.THEN)
		.verifyEvenExchange();
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Test_Even_Exchange_Open_Order"}
	, description = "Even Exchange for Open Order", enabled = true)
	public void Test_Even_Exchange_Open_Order(TestData data) {
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		String orderType = "Open";
		//ProductData orderDetails= getProductDataToTest("Even_Exchange_Open_HD_Order");

		String orderId= getProductToTest("Even_Exchange_Open_HD_Order",true);

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
		.chooseHDOrders(orderType)

		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()

		.addlogType(TestStepType.THEN)
		.verifyEvenExchangeEligibility(orderType);
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Test_Even_Exchange_Shipped_Order"}
	, description = "Even Exchange for Shipped Order", enabled = true)
	public void Test_Even_Exchange_Whole_Order(TestData data) {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		String dosorderID= getProductToTest("Pickup_Eligible_Shipped_Line_Item",true);

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
		.captureSalescheckNumber()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyEvenExchangeEntireOrder("shipped")
		.verifyNewOrderhassameSalescheckNumber()
		;
	}



	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP2DeliveryTests,"Test_Even_Exchange_Div605_Shipped_Item"}
	, description = "Test_Even_Exchange_Div605_Shipped_Item", enabled = true)
	public void Test_Even_Exchange_Div605_Shipped_Item(TestData data) {
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		String orderType = "Shipped";
		String orderIdDosNo = getProductToTest("MSPEvenExchangeDiv605ShippedOrder");

		String orderId=orderIdDosNo.split(",")[0];
		String dosNo=orderIdDosNo.split(",")[1];
		System.out.println(orderId+dosNo+"    5555555555555555555555555555");
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId, dosNo)

		.addlogType(TestStepType.WHEN)
		.chooseHDOrders(orderType)

		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()

		.addlogType(TestStepType.THEN)
		.verifyEvenExchangeNotAllowed("Rereserve")
		;
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Test_Even_Exchange_Released_Order"}
	, description = "Even Exchange for released Order", enabled = true)
	public void Test_Even_Exchange_Released_Order(TestData data) throws Exception {
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		String orderType = "Released";

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

		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()

		.addlogType(TestStepType.THEN)
		.verifyEvenExchangeEligibility(orderType)
		.verifyEvenExchangeEntireOrder("Released")
		.goToDeliveryNotes()
		.verifyDataInDeliveryNotes("OSH/MSO-WEB: PICK UP/EXCHANGE APP")
		.verifyDataInDeliveryNotes("MSP NEW EXCHANGE ORDER");
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP1DeliveryTests,"Test_Even_Exchange_Released_DOD_Order_Driver_Agent"}
	, description = "Even Exchange for released DOD Order", enabled = true)
	public void Test_Even_Exchange_Released_DOD_Order_Driver_Agent(TestData data) throws Exception {
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		String orderType = "Released";
		/*	List<String> list= new ArrayList<String>();
					list.add("MSP USER"+": "+user.userName);
					list.add("OSH/MSO-WEB: CANCEL ORDER");
					list.add("MSP USER"+": "+user.userName);*/

		String orderId= getProductToTest("Released_DOD_Order",true);	

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

		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.verifyLineItemDetail("Released")
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()

		.addlogType(TestStepType.THEN)
		.verifyEvenExchangeEligibility(orderType)
		.addlogType(TestStepType.THEN)
		.verifyEvenExchangeEntireOrder("Release DOD");
		/* .goToDeliveryNotes()
			        .addlogType(TestStepType.THEN)
					.verifyDeliveryOSHNotes(list)
					.addlogType(TestStepType.THEN)
					.verifyAdjustmentCapturedInInteractionsForCancelOrder("Cancel Delivery Order")
					.addlogType(TestStepType.WHEN)
					.goToActionCenter()
					.wrapUpOrderWithoutContactDelivery()
					._NavigationAction()
					.addlogType(TestStepType.WHEN)
					.searchByDeliveryOrderId(orderId,DcNumber.DC_NO)
					.addlogType(TestStepType.WHEN)
					.selectOrderInMyRecentDeliveryInteractions(1)
					.closeWarningPopupWindow()
					._OrderDetailsAction()
					.verifyActionCapturedHistoryNotes();*/
	}

}