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

public class ReturnItemValidation extends BaseTests{
	@Test(dataProvider = "schedule_Return_Not_Eligible_Data", 
            groups = {TestGroup.QA_Environment, "ReturnItemTests"}
            , description = "Verify return item option is present for eligible orders", enabled = true)
    public void MSP_Verify_ReturnItem_Option_Visible(String orderId, String sku) {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
        addCloneIDHostname(data);
        
        LogFormatterAction.beginSetup();
        User user = new User();
        user.userName=UserPool.getUser();
        user.password = "TestPassword";
         
        As.guestUser.goToHomePage()
        		.addlogType(TestStepType.WHEN)
                	.login(user)
                	.addlogType(TestStepType.THEN)
                	.verifyonlineagent()
                	.addlogType(TestStepType.WHEN)
                	.searchByOrderId(orderId)
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
                	.verifyOrderDetailsPageDisplayed()
                	.addlogType(TestStepType.GIVEN)
                	.clickOnSkuNumberUnderLineItemTab(sku)
                ._LineItemDetailsAction()
                .addlogType(TestStepType.THEN)
                	.verifyOptionVisible("Return Item")
                	.addlogType(TestStepType.THEN)
                	.fillReturnItemPopUp(1)
        ;
    }
	
	
	@Test(dataProvider = "Return_Not_Eligible_Data",
            groups = {TestGroup.QA_Environment, "ReturnItemTests"}
            , description = "Verify return option is not displayed for orders that are not eligible", enabled = true)
    public void MSP_Verify_ReturnItem_Option_Not_Visible(String orderId) {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
        addCloneIDHostname(data);
        
        LogFormatterAction.beginSetup();
        User user = new User();
        user.userName=UserPool.getUser();
        user.password = "TestPassword";
         
        As.guestUser.goToHomePage()
        .addlogType(TestStepType.WHEN)
                	.login(user)
                	.addlogType(TestStepType.THEN)
                	.verifyonlineagent()
                	.addlogType(TestStepType.WHEN)
                	.searchByOrderId(orderId)
                ._OrderDetailsAction()
                .addlogType(TestStepType.THEN)
                	.verifyOrderDetailsPageDisplayed()
                	.addlogType(TestStepType.GIVEN)
                	.clickOnSkuNumberUnderLineItemTab(1)
                ._LineItemDetailsAction()
                .addlogType(TestStepType.THEN)
                	.verifyOptionNotVisible("Return Item")
        ;
    }
	@Test(dataProvider = "schedule_Return_Not_Eligible_Data",
			  groups = {TestGroup.MSPReturnItem, "ReturnItemTests"}, 
           description = "Verify return tracking information is displayed", enabled = true)
  public void MSP_Verify_ReturnTrackingInformation_Option_Visible(String orderId, String sku) {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
      LogFormatterAction.beginSetup();
      User user = new User();
      user.userName=UserPool.getUser();
      user.password = "TestPassword";
       
      As.guestUser.goToHomePage()
      			.addlogType(TestStepType.WHEN)
              	.login(user)
              	.addlogType(TestStepType.THEN)
              	.verifyonlineagent()
              	.addlogType(TestStepType.WHEN)
              	.searchByOrderId(orderId)
              ._OrderDetailsAction()
              .addlogType(TestStepType.THEN)
              	.verifyOrderDetailsPageDisplayed()
              	.addlogType(TestStepType.GIVEN)
              	.clickOnSkuNumberUnderLineItemTab(sku)
              ._LineItemDetailsAction()
              .addlogType(TestStepType.THEN)
              	.verifyOptionVisible("Return Tracking Information")
              	.addlogType(TestStepType.THEN)
              	.verifyReturnTrackingInformationPopUp()
      ;
  }
	
	
	@Test(dataProvider = "ReturnTrackingNotEligible", 
          groups = {TestGroup.MSPReturnItem, "ReturnItemTests"}, 
           description = "Verify return tracking information is not visible for ineligible orders", enabled = true)
  public void MSP_Verify_ReturnTrackingInformation_Option_Not_Visible(String orderId, String sku) {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
      addCloneIDHostname(data);
      
      LogFormatterAction.beginSetup();
      User user = new User(); user.userName=UserPool.getUser();
       
      As.guestUser.goToHomePage()
      			.addlogType(TestStepType.WHEN)
              	.login(user)
              	.addlogType(TestStepType.THEN)
              	.verifyonlineagent()
              	.addlogType(TestStepType.WHEN)
              	.searchByOrderId(orderId)
              ._OrderDetailsAction()
              .addlogType(TestStepType.THEN)
              	.verifyOrderDetailsPageDisplayed()
              	.addlogType(TestStepType.GIVEN)
              	.clickOnSkuNumberUnderLineItemTab(sku)
              ._LineItemDetailsAction()
              .addlogType(TestStepType.THEN)
              	.verifyOptionNotVisible("Return Tracking Information")
      ;
  }
	@Test(dataProvider = "schedule_Return_Data",
			  groups = {TestGroup.MSPReturnItem, "ReturnItemTests"}, 
           description = "Verify schedule return option is visible", enabled = true)
  public void MSP_Verify_ScheduleReturn_Option_Visible(String orderId, String sku) {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
      LogFormatterAction.beginSetup();
      User user = new User();
      user.userName=UserPool.getUser();
      user.password = "TestPassword";
       
      As.guestUser.goToHomePage()
      .addlogType(TestStepType.WHEN)
              	.login(user)
              	.addlogType(TestStepType.THEN)
              	.verifyonlineagent()
              	.addlogType(TestStepType.WHEN)
              	.searchByOrderId(orderId)
              ._OrderDetailsAction()
              .addlogType(TestStepType.THEN)
              	.verifyOrderDetailsPageDisplayed()
              	.addlogType(TestStepType.GIVEN)
              	.clickOnSkuNumberUnderLineItemTab(sku)
              ._LineItemDetailsAction()
              .addlogType(TestStepType.THEN)
              	.verifyOptionVisible("Schedule Return")
      ;
  }
	
	
	@Test(dataProvider = "schedule_Return_Not_Eligible_Data",
          groups = {TestGroup.MSPReturnItem, "ReturnItemTests"}, 
           description = "Verify schedule return option is not visible for ineligible orders", enabled = true)
  public void MSP_Verify_ScheduleReturn_Option_Not_Visible(String orderId, String sku) {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
      addCloneIDHostname(data);
      
      LogFormatterAction.beginSetup();
      User user = new User();
      user.userName=UserPool.getUser();
      user.password = "TestPassword";
       
      As.guestUser.goToHomePage()
      			.addlogType(TestStepType.WHEN)
              	.login(user)
              	.addlogType(TestStepType.THEN)
              	.verifyonlineagent()
              	.addlogType(TestStepType.WHEN)
              	.searchByOrderId(orderId)
              ._OrderDetailsAction()
              .addlogType(TestStepType.THEN)
              	.verifyOrderDetailsPageDisplayed()
              	.addlogType(TestStepType.GIVEN)
              	.clickOnSkuNumberUnderLineItemTab(sku)
              ._LineItemDetailsAction()
              	.addlogType(TestStepType.THEN)
              	.verifyOptionNotVisible("Schedule Return")
      ;
  }
	
	 @DataProvider (name="ReturnTrackingEligible")
	    public Object[][] DP_Return_Tracking_Eligible_orderID() throws Exception{
		 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().return_Tracking_Information_Data();
		 String OrderID[]={Retrieval_Test_Data_By_Query.lineitem_Return_Tracking_Information_Eligible_orderID};
		 String sku[]={Retrieval_Test_Data_By_Query.lineitem_Return_Tracking_Information_Eligible_SKU};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= sku[0];
	         return (testData);
	 		}
	 
	 @DataProvider (name="ReturnTrackingNotEligible")
	    public Object[][] DP_Return_Tracking_NotEligible_orderID() throws Exception{
		 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().return_Tracking_Information_Data();
		 String OrderID[]={Retrieval_Test_Data_By_Query.lineitem_Return_Tracking_Information_StoreExpt_orderID};
		 String sku[]={Retrieval_Test_Data_By_Query.lineitem_Return_Tracking_Information_StoreExpt_SKU};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= sku[0];
	         return (testData);
	 		}
	 @DataProvider (name="schedule_Return_Data")
	    public Object[][] DP_schedule_Return_Data() throws Exception{
		 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().schedule_Return_Data();
		 String OrderID[]={Retrieval_Test_Data_By_Query.lineitem_schedule_Return_Eligible_orderID};
		 String sku[]={Retrieval_Test_Data_By_Query.lineitem_schedule_Return_Eligible_SKU};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= sku[0];
	         return (testData);
	 		}
	 
	 @DataProvider (name="schedule_Return_Not_Eligible_Data")
	    public Object[][] DP_schedule_Return_NotEligible_Data() throws Exception{
		 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().schedule_Return_Data();
		 String OrderID[]={Retrieval_Test_Data_By_Query.lineitem_schedule_Return_FFMinExpt_orderID};
		 String sku[]={Retrieval_Test_Data_By_Query.lineitem_schedule_Return_FFMinExpt_SKU};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= sku[0];
	         return (testData);
	 		}

	 @DataProvider (name="Return_Not_Eligible_Data")
	    public Object[][] DP_Return_NotEligible_Data() throws Exception{
		 Retrieval_Test_Data_By_Query.release_Order_Data();
		 String OrderID[]={Retrieval_Test_Data_By_Query.release_order_eligible_orderID};
		 Object[][] testData = new Object[1][1];
		 testData[0][0]= OrderID[0];
	         return (testData);
	 		}
	 
	 
}
