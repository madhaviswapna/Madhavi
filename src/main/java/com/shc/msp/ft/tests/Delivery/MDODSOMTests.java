package com.shc.msp.ft.tests.Delivery;

import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class MDODSOMTests extends BaseTests {

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {"MSP_MDODSOM_Verify_MDO_Queue_After_FollowUP"}
	, description = "Verify if case can be routed to MDO Queue and verify", enabled = true)
	public void MSP_MDODSOM_Verify_MDO_Queue_After_FollowUP(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		
		String salescheck=  getProductToTest("Rereserve_Eligible_Open_Order");
		
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
		.addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.captureInteractionCaseId()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.queueForFollowUp("Gas Leak - Member requests urgent assistance with gas leak")
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
	     .searchByCaseId()	
		.addlogType(TestStepType.THEN)
		.verifyQueueName();
		
	} 	
	
	
	
	
}
