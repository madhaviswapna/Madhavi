package com.shc.msp.ft.tests;

import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.MongoDB;
import com.shc.msp.ft.util.Retrieval_Test_Data_By_Query;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class RealTimeMSAT extends BaseTests{
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSAT_Tests}, description = "Verify Manual Search is disabled on Connecting to CTI as Online Agent")
	public void MSAT_OnlineAgentOrderWrapupRFC(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();
		
	    String orderId = "941298302";
		addCloneIDHostname(data);
	
		 
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.WHEN)
	            .searchByOrderId(orderId)
	            ._OrderDetailsAction().addlogType(TestStepType.THEN)
	            .msatOnlineAgentOrderWrapupRFC()
	            ;
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSAT_Tests}, description = "Verify Manual Search is disabled on Connecting to CTI as Online Agent")
	public void MSAT_OnlineAgentGeneralInquiryRFC(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();
		
	    String orderId = "941298302";
		addCloneIDHostname(data);
	
		 
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            ._OrderDetailsAction()
	            .msatOnlineAgentGeneralInquiryWrapupRFC()
	            ;
	}	
}
