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

public class SalesCheckLevelRuleActionTests extends BaseTests{
	TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,  
			groups = {TestGroup.MSPSalesCheckLevelRuleAction,TestGroup.MSPP1OnlineTests, "sales_Check_Level_Release_Sales_Check_Captured_Notes_Interaction"}
            , description = "Verify release at sales check level", enabled = true, priority=45)
    public void sales_Check_Level_Release_Sales_Check_Captured_Notes_Interaction(TestData data) throws Exception {
		
		Retrieval_Test_Data_By_Query.salesCheckRelease_Data();
		String orderId= Retrieval_Test_Data_By_Query.SalesCheckReleaseEligible_OrderId;
        String scNo = Retrieval_Test_Data_By_Query.SalesCheckReleaseEligible_SalesCheckNumber;
        
		addCloneIDHostname(data);
        
        User user = new User(); user.userName=UserPool.getUser();       
        As.guestUser.goToHomePage()
        		.addlogType(TestStepType.WHEN)
                .login(user)
                .addlogType(TestStepType.THEN)
                .verifyonlineagent()
                .addlogType(TestStepType.WHEN)
                .searchByOrderId(orderId)
                .closeWarningPopupWindow()
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOrderDetailsPageDisplayed()
                .addlogType(TestStepType.WHEN)
                .clickOnSalesCheckNumberUnderSalesCheckTab(scNo)
                ._SalesCheckDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionVisible("Release Sales Check")
                .addlogType(TestStepType.THEN)
                .verifyReleaseSaleCheck()
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
		        .verifyAdjustmentCapturedInInteraction("Release Sales Check")
		        .addlogType(TestStepType.WHEN)
		    	.verifyOrderWrapUp()
				.addlogType(TestStepType.THEN)
				.fillRFCForm()
				._NavigationAction()
				.addlogType(TestStepType.WHEN)
				.searchByOrderId(orderId)
				.closeWarningPopupWindow()
				.addlogType(TestStepType.THEN)
				._OrderDetailsAction()
				.verifyActionCapturedInNotes("Release Sales Check")
		    	;
    }
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.MSPSalesCheckLevelRuleAction, "MSPSalesCheckLevelRuleActionTests"}
            , description = "Verify release sales check is not shown for ineligible orders", enabled = true, priority=46)
    public void sales_Check_Level_Release_Sales_Check_NonEligible(TestData data) {
		String orderId=getProductToTest("MSP_OL_OrderInEligibleForSalesCheckLevelRelease");
        addCloneIDHostname(data);
        
        User user = new User(); user.userName=UserPool.getUser();
         
        As.guestUser.goToHomePage()
        	.addlogType(TestStepType.WHEN)
                .login(user)
                .addlogType(TestStepType.THEN)
                .verifyonlineagent()
                .addlogType(TestStepType.WHEN)
                .searchByOrderId(orderId)
                .closeWarningPopupWindow()
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOrderDetailsPageDisplayed()
                .addlogType(TestStepType.WHEN)
                .clickOnSalesCheckNumberUnderSalesCheckTab(1)
               ._SalesCheckDetailsAction()
               .addlogType(TestStepType.THEN)
                .verifyOptionIsNotVisible("Release Sales Check");
    }
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.MSPP1OnlineTests, "sales_Check_Level_Update_Sales_Check_Captured_Notes_Interaction"}
            , description = "sales_Check_Level_Update_Sales_Check_Captured_Notes_Interaction", enabled = true, priority=47)
    public void sales_Check_Level_Update_Sales_Check_Captured_Notes_Interaction(TestData data) {
		String orderId=getProductToTest("MSP_OL_OrderEligibleForUpdateSalesCheck");
	    addCloneIDHostname(data);    	        
        User user = new User(); user.userName=UserPool.getUser();
        
        As.guestUser.goToHomePage()
        		.addlogType(TestStepType.WHEN)
                .login(user)
                .addlogType(TestStepType.THEN)
                .verifyonlineagent()
                .addlogType(TestStepType.WHEN)
                .searchByOrderId(orderId)
                .closeWarningPopupWindow()
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOrderDetailsPageDisplayed()
                .addlogType(TestStepType.WHEN)
                .clickOnSalesCheckNumberUnderSalesCheckTab(1)
                ._SalesCheckDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionIsVisible("Update Sales Check")
                .addlogType(TestStepType.WHEN)
                .selectAction("Update Sales Check")
                .addlogType(TestStepType.THEN)
                .verifyUpdateSaleCheck()
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyAdjustmentCapturedInInteraction("Update Sales Check")
             	.verifyOrderWrapUp()
         		.addlogType(TestStepType.THEN)
         		.fillRFCForm()
         		._NavigationAction()
         		.addlogType(TestStepType.WHEN)
         		.searchByOrderId(orderId)
         		.closeWarningPopupWindow()
         		._OrderDetailsAction()
         		.verifyAdjustmentCapturedInNotes("Update Sales Check");
    }
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.MSPSalesCheckLevelRuleAction, "MSPSalesCheckLevelRuleActionTests"}
            , description = "Verify Update Sales Check option is not shown for ineligible orders", enabled = true, priority=48)
    public void sales_Check_Level_Update_Sales_Check_NonEligible(TestData data) {
		
		//String orderId=getProductToTest("MSP_OL_OrderInEligibleForUpdateSalesCheck");
		String orderId="941374199";
	    addCloneIDHostname(data);    	        
        User user = new User(); user.userName=UserPool.getUser();
         
        As.guestUser.goToHomePage()
        		.addlogType(TestStepType.WHEN)
                .login(user)
                .addlogType(TestStepType.THEN)
                .verifyonlineagent()
                .addlogType(TestStepType.WHEN)
                .searchByOrderId(orderId)
                .closeWarningPopupWindow()
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOrderDetailsPageDisplayed()
                .addlogType(TestStepType.WHEN)
                .clickOnSalesCheckNumberUnderSalesCheckTab(1)
                ._SalesCheckDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionIsNotVisible("Update Sales Check");
    }
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, 
			groups = {TestGroup.MSPSalesCheckLevelRuleAction, "MSPSalesCheckLevelRuleActionTests"}
            , description = "Verify agent is able to contact customer at salescheck level", enabled = true, priority=49)
    public void sales_Check_Level_Contact_Customer_Eligible(TestData data) {
        
		String OrderID=getProductToTest("MSP_OL_OrderEligibleForContactCustomerAtSalescheck");
		
		addCloneIDHostname(data);
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
                .addlogType(TestStepType.WHEN)
                .clickOnSalesCheckNumberUnderSalesCheckTab(1)
                ._SalesCheckDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionIsVisible("Contact Customer")
                .addlogType(TestStepType.WHEN)
                .selectAction("Contact Customer")
                .addlogType(TestStepType.THEN)
                .verifyEmailTemplatePopUp();  
       
	}
	
	@Test(dataProvider = "DP_Cancel_Sales_Check_Eligible",groups = {TestGroup.MSPP0Tests, "MSPSalesCheckLevelRuleActionTests","sales_Check_Level_Cancellation_Captured_AuditTrail_Notes_Interaction"}
            , description = "Verify cancellation at salescheck level", enabled = true, priority=51)
    public void sales_Check_Level_Cancellation_Captured_AuditTrail_Notes_Interaction(String OrderID,String SalesCheck) {		
		//
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
                .clickOnSalesCheckNumberUnderSalesCheckTab(SalesCheck)
                ._SalesCheckDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionIsVisible("Cancellation - Sales Check")
                .addlogType(TestStepType.THEN)
                .cancelSalesCheck()
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
		        .verifyAdjustmentCapturedInInteraction("Sales Check Cancellation")
		        .addlogType(TestStepType.WHEN)
		        .goToAuditTrail()
		        .addlogType(TestStepType.THEN)
		        .verifyActionCapturedInAuditTrail("KNCNCancel Request")
		    	.verifyOrderWrapUp()
				.addlogType(TestStepType.THEN)
				.fillRFCForm()
				._NavigationAction()
				.addlogType(TestStepType.WHEN)
				.searchByOrderId(OrderID)
				.closeWarningPopupWindow()
				.addlogType(TestStepType.THEN)
				._OrderDetailsAction()
				.verifyActionCapturedInNotes("Sales Check Cancellation")
		    	; 
       	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.MSPP0Tests, "MSPSalesCheckLevelRuleActionTests"}
            , description = "Verify ineligible orders does not have cancel options", enabled = true, priority=52)
    public void sales_Check_Level_Cancellation_NonEligible(TestData data) {
		
		String OrderID=getProductToTest("MSP_OL_OrderInEligibleForCancellationAtSalescheck");
        addCloneIDHostname(data);
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
                .clickOnSalesCheckNumberUnderSalesCheckTab(1)
                ._SalesCheckDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionIsNotVisible("Cancellation - Sales Check");
 
	}

	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.MSPP0Tests, "MSPSalesCheckLevelRuleActionTests","sales_Check_Level_ReadyForPickupEmail_Option_Eligible"}
            , description = "Verify ready for pickup mails", enabled = true, priority=53)
    public void sales_Check_Level_ReadyForPickupEmail_Option_Eligible(TestData data) {
		
		String[] arr=getProductToTest("MSP_OL_OrderEligibleForReadyForPickup").split("\\|");
		String orderId=arr[0];
		String salecheck=arr[1];
		System.out.println("---------------------------------------------"+orderId);
		System.out.println("---------------------------------------------"+salecheck);
		
		//String orderId="840027665";
		addCloneIDHostname(data);
        
      
        User user = new User(); user.userName=UserPool.getUser();
       As.guestUser.goToHomePage()
       			.addlogType(TestStepType.WHEN)
                .login(user)
                .addlogType(TestStepType.THEN)
                .verifyonlineagent()
                .addlogType(TestStepType.WHEN)
                .searchByOrderId(orderId)
                .closeWarningPopupWindow()
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOrderDetailsPageDisplayed()
                .addlogType(TestStepType.WHEN)
                .clickOnSalesCheckNumberUnderSalesCheckTab(salecheck)
                ._SalesCheckDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionIsVisible("Ready for Pickup Email")
                .addlogType(TestStepType.THEN)
                .verifyReadyPickupEmail()
                
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyAdjustmentCapturedInInteraction("Sales Check Ready for Pickup Email")
             	.verifyOrderWrapUp()
         		.addlogType(TestStepType.THEN)
         		.fillRFCForm()
  
         		._NavigationAction()
         		.addlogType(TestStepType.WHEN)
         		.searchByOrderId(orderId)
         		.closeWarningPopupWindow()
         		._OrderDetailsAction()
         		.verifyAdjustmentCapturedInNotes("Sales Check Ready for Pickup Email");
    }
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.MSPP0Tests, "MSPSalesCheckLevelRuleActionTests"}
            , description = "Verify ready for pickup email is not shown for ineligible orders", enabled = true, priority=54)
    public void sales_Check_Level_ReadyForPickupEmail_Option_NonEligible(TestData data) {
		
		String orderId=getProductToTest("MSP_OL_OrderInEligibleForReadyForPickup");
        addCloneIDHostname(data);
        
        User user = new User(); user.userName=UserPool.getUser();
       As.guestUser.goToHomePage()
       			.addlogType(TestStepType.WHEN)
                .login(user)
                .addlogType(TestStepType.THEN)
                .verifyonlineagent()
                .addlogType(TestStepType.WHEN)
                .searchByOrderId(orderId)
                .closeWarningPopupWindow()
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOrderDetailsPageDisplayed()
                .addlogType(TestStepType.WHEN)
                .clickOnSalesCheckNumberUnderSalesCheckTab(1)	 
                ._SalesCheckDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionIsNotVisible("Ready for Pickup Email");
    }
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.MSPP0Tests, "MSPSalesCheckLevelRuleActionTests","sales_Check_Level_EmailToCustomer_CapturedInNotes"}
            , description = "sales_Check_Level_EmailToCustomer_CapturedInNotes", enabled = true, priority=54)
    public void sales_Check_Level_EmailToCustomer_CapturedInNotes(TestData data) {
		
		String orderId=getProductToTest("MSP_OL_OrderEligibleForContactCustomerAtSalescheck");
        addCloneIDHostname(data);
        
        User user = new User(); user.userName=UserPool.getUser();
       As.guestUser.goToHomePage()
       			.addlogType(TestStepType.WHEN)
                .login(user)
                .addlogType(TestStepType.THEN)
                .verifyonlineagent()
                .addlogType(TestStepType.WHEN)
                .searchByOrderId(orderId)
                .closeWarningPopupWindow()
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOrderDetailsPageDisplayed()
                .addlogType(TestStepType.WHEN)
                .clickOnSalesCheckNumberUnderSalesCheckTab(1)
                .addlogType(TestStepType.THEN)
                .verifyOptionVisible("Contact Customer")
                .addlogType(TestStepType.THEN)
       			.verifyEmailCapturedInInteraction()
        		.addlogType(TestStepType.THEN)
        		.verifyOrderWrapUp()
        		.addlogType(TestStepType.THEN)
        		.fillRFCForm()
        		._NavigationAction()
        		.addlogType(TestStepType.WHEN)
        		.searchByOrderId(orderId)
        		.closeWarningPopupWindow()
        		._OrderDetailsAction()
        		.addlogType(TestStepType.THEN)
        		.verifyEmailCapturedInNotes()
        		;
	}
	/*
	 * Test case Name :sales_Check_Level_Verify_NoCancellation_FBM
	 * Input parameters : order id from OHM
	 * Description:Verify that we don't show cancel option for market place item, 
	 				* this test case is specifically checking cancel sales check option is not shown.
	 				* the salescheck which is being tested is held CSI status.
	 * Date Modified:
	 * Author:Sarika Patil
	 * comments:
	 */
	
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.MSPP0Tests, "sales_Check_Level_Verify_NoCancellation_FBM"}
            , description = "Verify ineligible orders does not have cancel options", enabled = true, priority=52)
    public void sales_Check_Level_Verify_NoCancellation_FBM(TestData data) {
		String[] test_data = getProductToTest("SalesCheckLevelFBMCancellation").split(",");
  		String OrderID=test_data[0];
  		String salescheckNumber = test_data[1];
  		
        addCloneIDHostname(data);
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
                .clickOnSalesCheckNumberUnderSalesCheckTab(salescheckNumber)
                ._SalesCheckDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionIsNotVisible("Cancellation - Sales Check");
 
	}
	
	/* * Test case Name :sales_Check_Level_Verify_NoCancellation_FBM
	 * Input parameters : order id from OHM
	 * Description:Verify that we don't show cancel option for market place item(Kmart), 
	 				* this test case is specifically checking cancel sales check option is not shown.
	 				* the salescheck which is being tested is held PAC status.
	 * Date Modified:
	 * Author:Sarika Patil
	 * comments:
	 */
	
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.MSPP0Tests, "sales_Check_Level_Verify_NoCancellation_FBMKmart"}
            , description = "Verify ineligible orders does not have cancel options", enabled = true, priority=52)
    public void sales_Check_Level_Verify_NoCancellation_FBMKmart(TestData data) {
		String[] test_data = getProductToTest("SalesCheckLevelFBMCancellationKmart").split(",");
  		String OrderID=test_data[0];
  		String salescheckNumber = test_data[1];
  		
        addCloneIDHostname(data);
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
                .clickOnSalesCheckNumberUnderSalesCheckTab(salescheckNumber)
                ._SalesCheckDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionIsNotVisible("Cancellation - Sales Check");
 
	}
		
	@DataProvider (name="DP_Release_Sales_Check_Eligible")
    public Object[][] DP_Release_SalesCheck_Eligible_OrderID() throws Exception{
		 Retrieval_Test_Data_By_Query.release_Sales_Check_Data();
         String OrderID[]={Retrieval_Test_Data_By_Query.release_sales_check_eligible_orderID};
         String scNO[]={Retrieval_Test_Data_By_Query.release_sales_check_eligible_SCNO};
         Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= scNO[0];
	         return (testData);
			}
	 @DataProvider (name="DP_Release_Sales_Check_NonEligible",parallel=true)
    public Object[][] DP_Release_SalesCheck_NonEligible_OrderID() throws Exception{
		 Retrieval_Test_Data_By_Query.release_Sales_Check_Data();
         String OrderID[]={Retrieval_Test_Data_By_Query.release_sales_check_status_exception_ID};
         String scNO[]={Retrieval_Test_Data_By_Query.release_sales_check_status_exception_SCNO};
         Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= scNO[0];
				return (testData);
	 		}
	@DataProvider (name="DP_Update_Sales_Check_Eligible")
    public Object[][] DP_Update_Sales_Check_Eligible_OrderID() throws Exception{
		 Retrieval_Test_Data_By_Query.update_Sales_Check_Data();
         String OrderID[]={Retrieval_Test_Data_By_Query.update_sales_check_eligible_orderID};
         String scNO[]={Retrieval_Test_Data_By_Query.update_sales_check_eligible_SCNO};
         Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= scNO[0];
	         return (testData);
			}
	 @DataProvider (name="DP_Update_Sales_Check_NonEligible",parallel=true)
	 public Object[][] DP_Update_Sales_Check_NonEligible_OrderID() throws Exception{
		 Retrieval_Test_Data_By_Query.update_Sales_Check_Data();
         String OrderID[]={Retrieval_Test_Data_By_Query.update_sales_check_store_exception_ID};
         String scNO[]={Retrieval_Test_Data_By_Query.update_sales_check_store_exception_SCNO};
         /*String OrderIDstatus[]={Retrieval_Test_Data_By_Query.update_sales_check_status_exception_ID};
         int sizeOfTestData=OrderID.length+OrderIDstatus.length;
		 Object[][] testData = new Object[sizeOfTestData][1];
		 int i=0;
		for(;i<OrderID.length;i++){		
					testData[i][0]= OrderID[i];
		}
		for(;i<sizeOfTestData;i++){
			for(int j=0; j<OrderIDstatus.length;j++)
			{
				testData[i][0]=OrderIDstatus[j];
			}

		}*/
         Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= scNO[0];
				return (testData);
	 	}
	@DataProvider (name="DP_Contact_Customer_Sales_Check_Eligible")
    public Object[][] DP_Contact_Customer_Eligible_OrderID() throws Exception{
		 Retrieval_Test_Data_By_Query.contact_Customer_Sales_Check_Data();
         String OrderID[]={Retrieval_Test_Data_By_Query.cc_sales_check_eligible_orderID};
         String scNO[]={Retrieval_Test_Data_By_Query.cc_sales_check_eligible_SCNO};
         Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= scNO[0];
	         return (testData);
			}
	@DataProvider (name="DP_Contact_Customer_Sales_Check_NonEligible")
    public Object[][] DP_Contact_Customer_Sales_Check_NonEligible_OrderID() throws Exception{
		 Retrieval_Test_Data_By_Query.contact_Customer_Sales_Check_Data();
         String OrderID[]={Retrieval_Test_Data_By_Query.cc_sales_check_status_exception_ID};
         String scNO[]={Retrieval_Test_Data_By_Query.cc_sales_check_status_exception_SCNO};
         Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= scNO[0];
	         return (testData);
			}
	@DataProvider (name="DP_Cancel_Sales_Check_Eligible")
    public Object[][] DP_Cancellation_Eligible_OrderID() throws Exception{

		 Retrieval_Test_Data_By_Query.cancle_Sales_Check_Data();
         String OrderID[]={Retrieval_Test_Data_By_Query.cancel_sc_eligible_orderID};
         String scNO[]={Retrieval_Test_Data_By_Query.cancel_sc_eligible_SCNO};
         Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= scNO[0];
	         return (testData);
			}
	@DataProvider (name="DP_Cancellation_NonEligible",parallel=true)
	public Object[][] DP_Cancellation_NonEligible_OrderID() throws Exception{
		 Retrieval_Test_Data_By_Query.cancle_Sales_Check_Data();
         String OrderID[]={Retrieval_Test_Data_By_Query.cancel_sc_store_exception_ID};
         String scNO[]={Retrieval_Test_Data_By_Query.cancel_sc_store_exception_SCNO};
          Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= scNO[0];
				return (testData);
			}
 	@DataProvider (name="DP_ ReadyForPickupEmail_Eligible")
	public Object[][] DP_ReadyForPickupEmail_Eligible_OrderID() throws Exception{
		 Retrieval_Test_Data_By_Query.ready_for_Pickup_Email_Data();
         String OrderID[]={Retrieval_Test_Data_By_Query.ready_for_Pickup_Email_eligible_orderID};
         String scNO[]={Retrieval_Test_Data_By_Query.ready_for_Pickup_Email_eligible_SCNO};
         Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= scNO[0];
	         return (testData);
			}
	@DataProvider (name="DP_ ReadyForPickupEmail_NonEligible",parallel=true)
	public Object[][] DP_ReadyForPickupEmail_NonEligible_OrderID() throws Exception{
			 Retrieval_Test_Data_By_Query.ready_for_Pickup_Email_Data();
	         String OrderID[]={Retrieval_Test_Data_By_Query.ready_for_Pickup_Email_store_exception_ID};
	         String scNO[]={Retrieval_Test_Data_By_Query.ready_for_Pickup_Email_store_exception_SCNO};
	         Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= scNO[0];
					return (testData);
		}
}