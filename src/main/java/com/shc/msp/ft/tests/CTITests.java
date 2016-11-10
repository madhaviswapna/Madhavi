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

public class CTITests extends BaseTests{
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify Manual Search is disabled on Connecting to CTI as Online Agent")
	public void CTI_Manual_Search_Disabled_On_Connected(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();	    
	    String phoneId = "3333333";
		addCloneIDHostname(data);
	
		 
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.WHEN)
	            .enterPhoneId(phoneId)
	            .addlogType(TestStepType.THEN)
	            .verifyManualSearchDisabled()
	            
	            ;
		
	}	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify Phone Id text displyed next to Connected Text")
	public void CTI_PhoneIdText_Displayed_On_Connected(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();	    
	    String phoneId = "3333333";
		addCloneIDHostname(data);
	
		 
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.WHEN)
	            .enterPhoneId(phoneId)
	            .addlogType(TestStepType.THEN)
	            .verifyPhoneIdText(phoneId)
	            
	            ;
		
	}	
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify Error messages for Invalid Phone Id - Online Agent")
	public void CTI_Verify_Invalid_PhoneId_Errors(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();	    
	    addCloneIDHostname(data);
	
		 
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.THEN)
	            .verifyPhoneIdDialog()
	            .addlogType(TestStepType.THEN)
	            .verifyInvalidPhoneId()
	            .addlogType(TestStepType.THEN)
	            .verifyManualSearchDisabled()
	            
	            ;
		
	}	

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify Phone Id Cancel button and Reconnect")
	public void CTI_Verify_PhoneId_Cancel_And_Reconnect(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();	    
	    addCloneIDHostname(data);
	
		 
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.THEN)
	            .verifyPhoneIdDialog()
	            .addlogType(TestStepType.THEN)
	            .verifyPhoneIdCancel()
	            .addlogType(TestStepType.THEN)
	            .verifyManualSearchEnabled()
	            .addlogType(TestStepType.THEN)
	            .verifyPhoneIdReconnect()
	            .addlogType(TestStepType.THEN)
	            .verifyManualSearchDisabled()
	            
	            ;
		
	}	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify PhoneId Dialog displayed for CTI Role - Online Agent")
	public void CTI_Verify_PhoneId_Dialog_Online_Agent(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();	    
	    addCloneIDHostname(data);
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.THEN)
	            .verifyPhoneIdDialog()
	            ;
		
	}	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify PhoneId Dialog displayed for CTI Role - Delivery Agent")
	public void CTI_Verify_PhoneId_Dialog_Delivery_Agent(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getDeliveryUser();	    
	    addCloneIDHostname(data);
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyAgentRole("DELIVERY AGENT")
	            .addlogType(TestStepType.THEN)
	            .verifyPhoneIdDialog()
	            ;
		
	}	
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify PhoneId Dialog displayed for CTI Role - Senior Associate")
	public void CTI_Verify_PhoneId_Dialog_Senior_Asociate(TestData data) throws Exception {
		User user = new User();
		user.userName = "testseniorassociate001";
		MongoDB.createUser(user.userName);
	    String role = "SENIOR ASSOCIATE";
		addCloneIDHostname(data);
	
		 
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyAgentRole(role)
	            .addlogType(TestStepType.THEN)
	            .verifyPhoneIdDialog()
	            
	            ;
		
	}	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify PhoneId Dialog NOT displayed for Non CTI Roles - Offline Agent")
	public void CTI_Verify_PhoneId_Dialog_Offline_Agent(TestData data) throws Exception {
		User user = new User();
		user.userName = "testofflineagent0001";
		String role = "OFFLINE AGENT";
		addCloneIDHostname(data);
	
		MongoDB.createUser(user.userName);
	     
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyAgentRole(role)
	            .addlogType(TestStepType.THEN)
	            .verifyNoPhoneIdDialog()
	            
	            ;
		
	}	
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify Search Phone Pre-Populated and Manual Search is Enabled on call NOT matching any order and profile.")
	public void CTI_Online_NoResult(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();	    
	    //String phoneNumber = "6306356310";
	    String phoneNumber = getProductToTest("MSP_CTI_No_Result");
	    addCloneIDHostname(data);
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.WHEN)
	            .enterPhoneId("333333")
	            .addlogType(TestStepType.WHEN)
	            .callAPIForScreenPop(phoneNumber,"DC_MMH_VOICEREF_SALE",user.userName)
	            .addlogType(TestStepType.THEN)
	            .verifyOrderSearchPhonePrePopulated(phoneNumber)
	            .addlogType(TestStepType.WHEN)
	            .searchByOrderId(Retrieval_Test_Data_By_Query.getOrder())
	            .addlogType(TestStepType.WHEN)
	        	.closeWarningPopupWindow()
	        	._OrderDetailsAction()
	            .addlogType(TestStepType.THEN)
	            .verifyOrderDetailsPageDisplayed()
	            ;
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests}, description = "Verify Order details page displayed on call with single matching order without profile. Wrap the RFC session & verify")
	public void CTI_Online_SingleOrder_WithoutProfile_OrderWrap(TestData data) throws Exception {
		
		User user = new User();
		user.userName = UserPool.getUser();	    
	    //String phoneNumber = "7655130495";
	    String phoneNumber = getProductToTest("MSP_CTI_OnlineSingleOrderNoProfile");
	    

	    addCloneIDHostname(data);
	
		 
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.WHEN)
	            .enterPhoneId("333333")
	            .addlogType(TestStepType.WHEN)
	            .callAPIForScreenPop(phoneNumber,"DC_MMH_VOICEREF_SALE",user.userName)
	            ._OrderDetailsAction()
	            .addlogType(TestStepType.THEN)
	            .verifyOrderDetailsPageDisplayed()
	            .addlogType(TestStepType.THEN)
	            .verifyOrderPhoneNumber(phoneNumber)
	            .addlogType(TestStepType.THEN)
	        	.captureInteractionCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyOrderWrapUp()
	        	.addlogType(TestStepType.THEN)
	        	.fillRFCForm()
	        	._NavigationAction()
	        	.addlogType(TestStepType.WHEN)
	        	.closeWarningPopupWindow()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCaseDetails("INTERACTION_CLOSED","Recent Activity")
	        	.addlogType(TestStepType.THEN)
	            .verifyManualSearchDisabled()
	            .addlogType(TestStepType.THEN)
	            .refreshPage()
	            .addlogType(TestStepType.WHEN)
	        	.closeWarningPopupWindow()
	        	.addlogType(TestStepType.THEN)
	            .verifyPhoneIdCancel()
	            .addlogType(TestStepType.THEN)
	            .verifyManualSearchEnabled()
	            .addlogType(TestStepType.WHEN)
	        	.searchByCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCaseDetails("INTERACTION_CLOSED","Case Search Results")
	            ;
		
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify Customer profile page displayed on call with single matching order with single matching profile")
	public void CTI_Online_SingleOrder_WithProfile(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();	    
	    //String phoneNumber = "7888798789";
	    String phoneNumber = getProductToTest("MSP_CTI_OnlineSingleOrderSingleProfile");
	    addCloneIDHostname(data);
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.WHEN)
	            .enterPhoneId("333333")
	            .addlogType(TestStepType.WHEN)
	            .callAPIForScreenPop(phoneNumber,"DC_MMH_VOICEREF_SALE",user.userName)
	            .addlogType(TestStepType.THEN)
	            .verifyProfilePage()
	            
	            ;
		
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify Customer Search done by default for PV8 NOT existing in Lookup")
	public void CTI_Online_Automated_Default_Customer_Search(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();	    
	    String phoneNumber = getProductToTest("MSP_CTI_WrongCustomerSingleProfile");
	    addCloneIDHostname(data);
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.WHEN)
	            .enterPhoneId("333333")
	            .addlogType(TestStepType.WHEN)
	            .callAPIForScreenPop(phoneNumber,"DC_MMH_VOICEREF_SALEXYZ",user.userName)
	            .addlogType(TestStepType.THEN)
	            .verifyProfilePage()
	            
	            ;
		
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify Customer Search done by default for PV8 Not existing in Lookup")
	public void CTI_Delivery_Automated_Default_Customer_Search(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getDeliveryUser();	    
	    String phoneNumber = getProductToTest("MSP_CTI_WrongCustomerSingleProfile");
	    addCloneIDHostname(data);
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .VerifyDeliveryAgent()
	            .addlogType(TestStepType.WHEN)
	            .enterPhoneId("333333")
	            .addlogType(TestStepType.WHEN)
	            .callAPIForScreenPop(phoneNumber,"DC_MMH_VOICEREF_SALEXYZ",user.userName)
	            .addlogType(TestStepType.THEN)
	            .verifyProfilePage()
	            
	            ;
		
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests}, description = "Verify Matching Orders list displayed on call with multiple matching orders without profile")
	public void CTI_Online_MultipleOrder_Without_Profile(TestData data) throws Exception {
		
		User user = new User();
		user.userName = UserPool.getUser();	    
		//String phoneNumber = "2012250127";
		String phoneNumber = getProductToTest("MSP_CTI_OnlineMultipleOrderNoProfile");
		addCloneIDHostname(data);
	
	     
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.WHEN)
	            .enterPhoneId("333333")
	            .addlogType(TestStepType.WHEN)
	            .callAPIForScreenPop(phoneNumber,"DC_MMH_VOICEREF_SALE",user.userName)
	            .addlogType(TestStepType.THEN)
	            .verifyOrderPhoneNumberInOrderSearchResults(phoneNumber)
	            .selectOrderInMyRecentInteractions(1)
	            ._OrderDetailsAction()
	            .addlogType(TestStepType.THEN)
	            .verifyOrderDetailsPageDisplayed()
	            .addlogType(TestStepType.THEN)
	            .verifyOrderPhoneNumber(phoneNumber)
	            ;
		
	}
	
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify Customer profile page displayed on call with multiple matching orders with single matching profile")
	public void CTI_Online_MultipleOrder_With_Profile(TestData data) throws Exception {

		User user = new User();
		user.userName = UserPool.getUser();	    
	    //String phoneNumber = "7888798789";
	    String phoneNumber = getProductToTest("MSP_CTI_OnlineMultipleOrderSingleProfile");
	    
	    addCloneIDHostname(data);
	
		 
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.WHEN)
	            .enterPhoneId("333333")
	            .addlogType(TestStepType.WHEN)
	            .callAPIForScreenPop(phoneNumber,"DC_MMH_VOICEREF_SALE",user.userName)
	            .addlogType(TestStepType.THEN)
	            .verifyProfilePage()
	            .addlogType(TestStepType.THEN)
	            .verifyPhoneOnProfilePage(phoneNumber)
	            
	            ;
		
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests}, description = "Verify Customer profile search results displayed on call with multiple matching orders with multiple matching profiles")
	public void CTI_Online_MultipleOrder_With_Multi_Profile(TestData data) throws Exception {

		User user = new User();
		user.userName = UserPool.getUser();	    
	    //String phoneNumber = "2233445566";
	    String phoneNumber = getProductToTest("MSP_CTI_OnlineMultipleOrderMultipleProfile");
		
	    addCloneIDHostname(data);
	
		 
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.WHEN)
	            .enterPhoneId("333333")
	            .addlogType(TestStepType.WHEN)
	            .callAPIForScreenPop(phoneNumber,"DC_MMH_VOICEREF_SALE",user.userName)
	            .addlogType(TestStepType.THEN)
	            .verifyPhoneInProfileSearchResults(phoneNumber)
	            .addlogType(TestStepType.WHEN)
	            .selectProfileFromSearchResults("last()")
	            .addlogType(TestStepType.THEN)
	            .verifyProfilePage()
	            .addlogType(TestStepType.THEN)
	            .verifyPhoneOnProfilePage(phoneNumber)
	            
	            ;
		
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests}, description = "Verify Order details page is displayed on API call with Single Deliery order, No Profile")
	public void CTI_Delivery_SingleOrder_NoProfile(TestData data) throws Exception {
		addCloneIDHostname(data);
		
		User user = new User();
		user.userName = UserPool.getDeliveryUser();
		
		String phoneNumber = getProductToTest("MSP_CTI_DeliverySingleOrderNoProfile");
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .VerifyDeliveryAgent()
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.WHEN)
        .callAPIForScreenPop(phoneNumber,"DL_3P_CC",user.userName)
        ._OrderDetailsAction()
        .addlogType(TestStepType.THEN)
        .verifyOrderDetailsPageDisplayed()
        .addlogType(TestStepType.THEN)
        .verifyOrderPhoneNumber(phoneNumber);  
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests}, description = "Verify multiple matching orders are displayed on API call with multiple Deliery orders No Profile")
	public void CTI_Delivery_MultipleOrders_NoProfile(TestData data) throws Exception {
		addCloneIDHostname(data);
		
		User user = new User();
		user.userName = UserPool.getDeliveryUser();
				
		//String phoneNumber = "2233445566";// Multiple Order No profile
		String phoneNumber = getProductToTest("MSP_CTI_DeliveryMultipleOrderNoProfile");
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .VerifyDeliveryAgent()
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.WHEN)
        .callAPIForScreenPop(phoneNumber,"DL_3P_CC",user.userName)
        .addlogType(TestStepType.WHEN)
        .chooseOpenHDOrders()
        ._OrderDetailsAction()
        .addlogType(TestStepType.THEN)
        .verifyOrderDetailsPageDisplayed();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify multiple matching orders are displayed on API call with multiple Deliery orders Single Profile")
	public void CTI_Delivery_MultipleOrders_SingleProfile(TestData data) throws Exception {
		addCloneIDHostname(data);
		
		User user = new User();
		user.userName = UserPool.getDeliveryUser();
				
		String phoneNumber = getProductToTest("MSP_CTI_DeliveryMultipleOrderSingleProfile");
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .VerifyDeliveryAgent()
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.WHEN)
        .callAPIForScreenPop(phoneNumber,"DL_3P_CC",user.userName)
        .addlogType(TestStepType.WHEN)
        .chooseOpenHDOrders()
        ._OrderDetailsAction()
        .addlogType(TestStepType.THEN)
        .verifyOrderDetailsPageDisplayed();
	}
	

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "Verify No Results Found on call")
	public void CTI_Delivery_No_Search_Results(TestData data) throws Exception {
		addCloneIDHostname(data);
		
		User user = new User();
		user.userName = UserPool.getDeliveryUser();
		//String phoneNumber = "7655130495";
		String phoneNumber = getProductToTest("MSP_CTI_No_Result");
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .VerifyDeliveryAgent()
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.WHEN)
        .callAPIForScreenPop(phoneNumber,"DL_3P_CC",user.userName)
        .addlogType(TestStepType.THEN)
        .verifyEmptyDeliverySearchResult()
        .addlogType(TestStepType.THEN)
        .verifyDeliveryOrderSearchPhonePrePopulated(phoneNumber)
        .addlogType(TestStepType.THEN)
        .verifyManualSearchEnabledDelivery();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests}, description = "Verify Worng Order for Online")
	public void CTI_Online_Wrong_Order_CTI_Connected (TestData data) throws Exception {
		addCloneIDHostname(data);
		User user = new User();
		user.userName = UserPool.getUser();
		
		//String phoneNumber = "7655130495";
		String phoneNumber = getProductToTest("MSP_CTI_OnlineSingleOrderNoProfile");
		String phoneNumberAnotherOrder = getProductToTest("MSP_CTI_OnlineMultipleOrderNoProfile");//"2012250127";
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.WHEN)
        .callAPIForScreenPop(phoneNumber,"DC_MMH_VOICEREF_SALE",user.userName)
        .addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.verifyOrderDetailsPageDisplayed()
		.addlogType(TestStepType.WHEN)
		.clickSearchAnotherOrder()
		.addlogType(TestStepType.THEN)
		._NavigationAction()
		.verifyOrderSearchPhonePrePopulated(phoneNumber)
		.addlogType(TestStepType.THEN)
		.verifySearchButtonEnabled()
		.addlogType(TestStepType.WHEN)
		.searchByPhone(phoneNumberAnotherOrder)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentInteractions(1)
		.addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.verifyOrderDetailsPageDisplayed();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests}, description = "Verify Worng Order for Online")
	public void CTI_Online_Wrong_Order_CTI_Not_Connected (TestData data) throws Exception {
		addCloneIDHostname(data);
		User user = new User();
		user.userName = UserPool.getUser();
						
		String phoneNumber = getProductToTest("MSP_CTI_OnlineSingleOrderNoProfile");
		String phoneNumberAnotherOrder = getProductToTest("MSP_CTI_OnlineMultipleOrderNoProfile");
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.WHEN)
        .verifyPhoneIdCancel()
        .addlogType(TestStepType.WHEN)
        .searchByPhone(phoneNumber)
        .addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.verifyOrderDetailsPageDisplayed()
		.addlogType(TestStepType.WHEN)
		.clickSearchAnotherOrder()
		.addlogType(TestStepType.THEN)
		._NavigationAction()
		.verifyOrderSearchPhonePrePopulated(phoneNumber)
		.addlogType(TestStepType.THEN)
		.verifySearchButtonEnabled()
		.addlogType(TestStepType.WHEN)
		.searchByPhone(phoneNumberAnotherOrder)
		.addlogType(TestStepType.WHEN)
		.selectOrderInMyRecentInteractions(1)
		.addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.verifyOrderDetailsPageDisplayed();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests}, description = "Verify Worng Order for Delivery")
	public void CTI_Delivery_Wrong_Order_CTI_Connected (TestData data) throws Exception {
		addCloneIDHostname(data);
		
		User user = new User();
		user.userName = UserPool.getDeliveryUser();
		//String phoneNumber = "6302154373";
		//String phoneNumberAnotherOrder = "2233445566";
		String phoneNumber = getProductToTest("MSP_CTI_DeliverySingleOrderNoProfile");
		String phoneNumberAnotherOrder =  getProductToTest("MSP_CTI_DeliveryMultipleOrderSingleProfile");
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .VerifyDeliveryAgent()
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.WHEN)
        .callAPIForScreenPop(phoneNumber,"DL_3P_CC",user.userName)
        .addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.verifyOrderDetailsPageDisplayed()
		.addlogType(TestStepType.WHEN)
		.clickSearchAnotherOrder()
		.addlogType(TestStepType.THEN)
		._NavigationAction()
		.verifyDeliveryOrderSearchPhonePrePopulated(phoneNumber)
		.addlogType(TestStepType.THEN)
		.verifySearchButtonEnabledDelivery()
		.addlogType(TestStepType.WHEN)
		.searchByPhoneForDeliveryUser(phoneNumberAnotherOrder)
		.addlogType(TestStepType.WHEN)
		.chooseOpenHDOrders()
		.addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.verifyOrderDetailsPageDisplayed();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests}, description = "Verify Worng Order for Delivery")
	public void CTI_Delivery_Wrong_Order_CTI_Not_Connected (TestData data) throws Exception {
		addCloneIDHostname(data);
		
		User user = new User();
		user.userName = UserPool.getDeliveryUser();
		/*String phoneNumber = "6302154373";
		String phoneNumberAnotherOrder = "2233445566";*/
		String phoneNumber = getProductToTest("MSP_CTI_DeliverySingleOrderNoProfile");
		String phoneNumberAnotherOrder =  getProductToTest("MSP_CTI_DeliveryMultipleOrderSingleProfile");
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .VerifyDeliveryAgent()
        .addlogType(TestStepType.WHEN)
        .verifyPhoneIdCancel()
        .addlogType(TestStepType.WHEN)
        .searchByPhoneForDeliveryUser(phoneNumber)
        .addlogType(TestStepType.WHEN)
        .selectOrderInMyRecentDeliveryInteractions(1)
        .addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.verifyOrderDetailsPageDisplayed()
		.addlogType(TestStepType.WHEN)
		.clickSearchAnotherOrder()
		.addlogType(TestStepType.THEN)
		._NavigationAction()
		.verifyDeliveryOrderSearchPhonePrePopulated(phoneNumber)
		.addlogType(TestStepType.THEN)
		.verifySearchButtonEnabledDelivery()
		.addlogType(TestStepType.WHEN)
		.searchByPhoneForDeliveryUser(phoneNumberAnotherOrder)
		.addlogType(TestStepType.WHEN)
		.chooseOpenHDOrders()
		.addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.verifyOrderDetailsPageDisplayed();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "CTI_Wrong_Customer_Logout_Of_Member")
	public void CTI_Wrong_Customer_Logout_Of_Member (TestData data) throws Exception {
		addCloneIDHostname(data);
		User user = new User();
		user.userName = UserPool.getUser();
		String phoneNumber = getProductToTest("MSP_CTI_WrongCustomerSingleProfile");//"7888798789";
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.WHEN)
        .callAPIForScreenPop(phoneNumber,"DC_MMH_VOICEREF_SALE",user.userName)
        .addlogType(TestStepType.THEN)
        .verifyProfilePage()
        .addlogType(TestStepType.WHEN)
        .clickChangeCustomer()
        .addlogType(TestStepType.WHEN)
        .clickLogoutOfMember()
        .addlogType(TestStepType.THEN)
        .verifyOrderSearchPhonePrePopulated(phoneNumber);
	}	
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "CTI_Wrong_Customer_Search_Another_Customer")
	public void CTI_Wrong_Customer_Search_Customer (TestData data) throws Exception {
		addCloneIDHostname(data);
		User user = new User();
		user.userName = UserPool.getUser();
		String phoneNumber = getProductToTest("MSP_CTI_WrongCustomerSingleProfile");   //"7888798789";
		String changeCustomerPhone = getProductToTest("MSP_CTI_WrongCustomerMultipleProfile");  //"2233445566";
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.WHEN)
        .callAPIForScreenPop(phoneNumber,"DC_MMH_VOICEREF_SALE",user.userName)
        .addlogType(TestStepType.THEN)
        .verifyProfilePage()
        .addlogType(TestStepType.WHEN)
        .clickChangeCustomer()
        .addlogType(TestStepType.THEN)
        .verifyPrePopulatedPhoneNumberChangeCustomer(phoneNumber)
        .addlogType(TestStepType.WHEN)
        .searchAnotherCustomerFromChangeCustomer(changeCustomerPhone)
        .addlogType(TestStepType.WHEN)
        .selectCustomerFromChangeCustomerList(1)
        .addlogType(TestStepType.THEN)
        .verifyProfilePage();
	}	
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "CTI_Logout_Not_Connected_User")
	public void CTI_Logout_Not_Connected_User(TestData data) throws Exception {
		addCloneIDHostname(data);
		User user = new User();
		user.userName = UserPool.getUser();
	
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.THEN)
        .verifyPhoneIdCancel()
        .logout();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "CTI_Logout_Connected_User")
	public void CTI_Logout_Connected_User(TestData data) throws Exception {
		addCloneIDHostname(data);
		User user = new User();
		user.userName = UserPool.getUser();
	
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.THEN)
        .logout()
        .addlogType(TestStepType.THEN)
        .verifyCTILogout("No")
        .addlogType(TestStepType.THEN)
        .logout()
        .addlogType(TestStepType.THEN)
        .verifyCTILogout("Yes")
        ;
	}
	

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "CTI_Change_Agent_Roles_CTI_To_Non_CTI_Not_Connected")
		public void CTI_Change_Agent_Roles_CTI_To_Non_CTI_Not_Connected(TestData data) throws Exception {
			addCloneIDHostname(data);
			//Not Connected
			//ONLINE AGENT --> OFFLINE AGENT --> ONLINE AGENT
			User user = new User();
			user.userName = UserPool.getUser();
			As.guestUser.goToHomePage()
			.addlogType(TestStepType.WHEN)
	        .login(user)
	        .addlogType(TestStepType.THEN)
	        .verifyPhoneIdCancel()
	        .addlogType(TestStepType.THEN)
	        .validateAgentRole("ONLINE AGENT")
	        .addlogType(TestStepType.WHEN)
	        .changeAgentRole("OFFLINE AGENT", false)
			.addlogType(TestStepType.THEN)
			.validateAgentRole("OFFLINE AGENT")
			.addlogType(TestStepType.WHEN)
	        .changeAgentRole("ONLINE AGENT", false)
	        .addlogType(TestStepType.THEN)
	        .validateAgentRole("ONLINE AGENT")
	        .addlogType(TestStepType.THEN)
	        .verifyPhoneIdCancel();
		}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "CTI_Change_Agent_Roles_CTI_To_CTI_Phone_Not_Connected")
		public void CTI_Change_Agent_Roles_CTI_To_CTI_Not_Connected(TestData data) throws Exception {
			addCloneIDHostname(data);
			//Not Connected
			//DELIVERY AGENT --> ONLINE AGENT --> SENIOR ASSOCIATE --> DELIVERY AGENT 
			User user = new User();
			user.userName =UserPool.getDeliveryUser();			
			As.guestUser.goToHomePage()
			.addlogType(TestStepType.WHEN)
	        .login(user)
	        .addlogType(TestStepType.THEN)
	        .verifyPhoneIdCancel()
	        .addlogType(TestStepType.THEN)
	        .validateAgentRole("DELIVERY AGENT")
	        .addlogType(TestStepType.WHEN)
	        .changeAgentRole("ONLINE AGENT", false)
	        .addlogType(TestStepType.THEN)
	        .validateAgentRole("ONLINE AGENT")
	        .addlogType(TestStepType.WHEN)
	        .changeAgentRole("SENIOR ASSOCIATE", false)
	        .addlogType(TestStepType.THEN)
	        .validateAgentRole("SENIOR ASSOCIATE")
	        .addlogType(TestStepType.WHEN)
	        .changeAgentRole("DELIVERY AGENT", false)
	        .addlogType(TestStepType.THEN)
	        .validateAgentRole("DELIVERY AGENT");
		}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "CTI_Change_Agent_Roles_CTI_To_CTI_Connected")
	public void CTI_Change_Agent_Roles_CTI_To_CTI_Connected(TestData data) throws Exception {
		addCloneIDHostname(data);
		//CTI Connected
		//DELIVERY AGENT --> SENIOR ASSOCIATE --> ONLINE AGENT --> DELIVERY AGENT
		User user = new User();
		user.userName =UserPool.getDeliveryUser();
			
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
	    .login(user)
	    .addlogType(TestStepType.WHEN)
	    .enterPhoneId("333333")
	    .addlogType(TestStepType.THEN)
        .validateAgentRole("DELIVERY AGENT")
        .addlogType(TestStepType.WHEN)
        .changeAgentRole("SENIOR ASSOCIATE", false)
        .addlogType(TestStepType.THEN)
        .validateAgentRole("SENIOR ASSOCIATE")
        .addlogType(TestStepType.WHEN)
        .changeAgentRole("ONLINE AGENT", false)
        .addlogType(TestStepType.THEN)
        .validateAgentRole("ONLINE AGENT")
        .addlogType(TestStepType.WHEN)
        .changeAgentRole("DELIVERY AGENT", false)
        .addlogType(TestStepType.THEN)
        .validateAgentRole("DELIVERY AGENT");
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "CTI_Change_Agent_Roles_CTI_To_CTI_Phone_Not_Connected")
	public void CTI_Change_Agent_Roles_CTI_To_Non_CTI_Connected(TestData data) throws Exception {
		addCloneIDHostname(data);
		
		//Connected to Not Connected
		//DELIVERY AGENT --> OFFLINE AGENT -->DELIVERY AGENT
		
		User user = new User();
		user.userName =UserPool.getDeliveryUser();
			
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
	    .login(user)
	    .addlogType(TestStepType.WHEN)
	    .enterPhoneId("333333")
	    .addlogType(TestStepType.THEN)
        .validateAgentRole("DELIVERY AGENT")
        .addlogType(TestStepType.WHEN)
        .changeAgentRole("OFFLINE AGENT", true)
        .addlogType(TestStepType.THEN)
        .verifyPhoneIdNotConnected()
        .addlogType(TestStepType.THEN)
		.validateAgentRole("OFFLINE AGENT")
		.addlogType(TestStepType.WHEN)
        .changeAgentRole("DELIVERY AGENT", false)
        .addlogType(TestStepType.THEN)
        .validateAgentRole("DELIVERY AGENT")
        .enterPhoneId("333333");
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "CTI_Change_Agent_Roles_CTI_To_CTI_Phone_Not_Connected")
	public void CTI_Delivery_Change_Agent_Channel_CTI_Connected(TestData data) throws Exception {
		addCloneIDHostname(data);

		User user = new User();
		user.userName =UserPool.getDeliveryUser();
			
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
	    .login(user)
	    .addlogType(TestStepType.WHEN)
	    .enterPhoneId("333333")
	    .addlogType(TestStepType.THEN)
        .validateAgentRole("DELIVERY AGENT")
        .addlogType(TestStepType.WHEN)
        .changeAgentChannel("CHAT", true)
        .addlogType(TestStepType.THEN)
        .validateAgentChannel("CHAT")
        .addlogType(TestStepType.WHEN)
        .changeAgentChannel("PHONE", false)
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.THEN)
        .validateAgentChannel("PHONE");        
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests,TestGroup.CTI_Tests_Prod}, description = "CTI_Change_Agent_Roles_CTI_To_CTI_Phone_Not_Connected")
	public void CTI_Online_Change_Agent_Channel_CTI_Connected(TestData data) throws Exception {
		addCloneIDHostname(data);

		User user = new User();
		user.userName =UserPool.getUser();
			
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
	    .login(user)
	    .addlogType(TestStepType.WHEN)
	    .enterPhoneId("333333")
	    .addlogType(TestStepType.THEN)
        .validateAgentRole("ONLINE AGENT")
        .addlogType(TestStepType.WHEN)
        .changeAgentChannel("CHAT", true)
        .addlogType(TestStepType.THEN)
        .validateAgentChannel("CHAT")
        .addlogType(TestStepType.WHEN)
        .changeAgentChannel("PHONE", false)
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.THEN)
        .validateAgentChannel("PHONE");        
	}
	

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests}, description = "CTI_Change_Agent_Roles_CTI_To_CTI_Phone_Not_Connected")
	public void CTI_Online_Automated_Search_PV7_NULL(TestData data) throws Exception {
		addCloneIDHostname(data);

		User user = new User();
		user.userName =UserPool.getUser();
		//String phoneNumber = "7655130495";
		String phoneNumber = getProductToTest("MSP_CTI_OnlineSingleOrderNoProfile");
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.WHEN)
        .callAPIForScreenPopPV7Null(phoneNumber,"DC_MMH_VOICEREF_SALE",user.userName)
        ._OrderDetailsAction()
        .addlogType(TestStepType.THEN)
        .verifyOrderDetailsPageDisplayed();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.CTI_Tests}, description = "CTI_Change_Agent_Roles_CTI_To_CTI_Phone_Not_Connected")
	public void CTI_Delivery_Automated_Search_PV7_NULL(TestData data) throws Exception {
		addCloneIDHostname(data);

		User user = new User();
		user.userName =UserPool.getDeliveryUser();
		//String phoneNumber = "6302154373";
		String phoneNumber = getProductToTest("MSP_CTI_DeliverySingleOrderNoProfile");
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .VerifyDeliveryAgent()
        .addlogType(TestStepType.WHEN)
        .enterPhoneId("333333")
        .addlogType(TestStepType.WHEN)
        .callAPIForScreenPopPV7Null(phoneNumber,"DL_3P_CC",user.userName)
        ._OrderDetailsAction()
        .addlogType(TestStepType.THEN)
        .verifyOrderDetailsPageDisplayed();
	}
}
