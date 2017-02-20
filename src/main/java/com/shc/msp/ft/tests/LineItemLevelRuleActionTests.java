package com.shc.msp.ft.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.ExcelUtil;
import com.shc.msp.ft.util.Retrieval_Test_Data_By_Query;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class LineItemLevelRuleActionTests extends BaseTests {
	TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
	
	//Sale Adjustment
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
            groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests","line_Item_Level_Verify_Sales_Adjustment_Eligible"}
            , description = "Verify sales adjustment can be done at line item level", enabled = true)
    public void  line_Item_Level_Verify_Sales_Adjustment_Eligible(TestData data) {
		//String[] test_data = getProductToTest("MSP_OL_ItemLevel_SaleAdjustmentData").split("\\|");
		String[] test_data ="940155199|02208196000".split("\\|");
		String orderId=test_data[0];
		String sku = test_data[1];
        addCloneIDHostname(data);
        LogFormatterAction.beginSetup();
        User user = new User(); user.userName=UserPool.getUser();
        As.guestUser.goToHomePage()
        	._NavigationAction()
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
        	.addlogType(TestStepType.GIVEN)
        	.clickOnSkuNumberUnderLineItemTab(sku)
        	._NavigationAction()
        	.closeWarningPopupWindow()
        	._OrderDetailsAction()
        	.addlogType(TestStepType.THEN)
        	.verifylineitemdetails()
        	.verifyOptionVisible("Sale Adjustment")
        	.addlogType(TestStepType.GIVEN)
        	//TODO logger needs to be corrected
            .taxadjustment("Sale Adjustment",0.1,orderId)
            .verifyTrialBalance();
    }	   
	
	
    @Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
            groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests","line_Item_Level_Verify_Sales_Adjustment_NonEligible"}
    , description = "Verify Sale adjustment is not shown for ineligible items", enabled = true)
    public void  line_Item_Level_Verify_Sales_Adjustment_NonEligible(TestData data) {
		String[] test_data = getProductToTest("MSP_OL_ItemLevel_SaleAdjustmentInEligibleData").split("\\|");
		String orderId=test_data[0];
		String sku = test_data[1];
        addCloneIDHostname(data);
        
        LogFormatterAction.beginSetup();
        User user = new User(); user.userName=UserPool.getUser();
         
        As.guestUser.goToHomePage()
        	._NavigationAction()
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
		    .addlogType(TestStepType.GIVEN)
		    .clickOnSkuNumberUnderLineItemTab(sku)
		    ._LineItemDetailsAction()
		    .addlogType(TestStepType.THEN)
        	.verifyOptionIsNotVisible("Sale Adjustment");
        }
  
    //Cancellation - Line Item 
    @Test
	 (dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
	          groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests","line_Item_Level_Verify_Cancellation_Eligible"}
	            , description = "Verify cancellation at line item level for eligible orders", enabled = true)
	    public void line_Item_Level_Verify_Cancellation_Eligible(TestData data) {
    		String[] test_data = getProductToTest("ItemLevelCancellationEligible").split("\\|");
    		String OrderID=test_data[0];
    		String sku = test_data[1];
        
	       addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
                ._NavigationAction()
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
                .addlogType(TestStepType.GIVEN)
                .clickOnSkuNumberUnderLineItemTab(sku)
                ._LineItemDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionVisible("Cancellation - Line Item")
                .cancelLineItem();
	 }
	 
	 
	 @Test
	 (dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests"},
	 description = "Verify ineligible items cannot be cancelled", enabled = true)
	    public void  line_Item_Level_Verify_Cancellation_NonEligible(TestData data) {
		 
	  		String[] test_data = getProductToTest("ItemLevelCancellationInEligible").split("\\|");
    		String OrderID=test_data[0];
    		String sku = test_data[1];
	        addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
                ._NavigationAction()
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
                .addlogType(TestStepType.GIVEN)
                .clickOnSkuNumberUnderLineItemTab(sku)
                ._LineItemDetailsAction()
                .addlogType(TestStepType.THEN)
                .verifyOptionNotVisible("Cancellation - Line Item");
	 
	 }
	 
	 //Return Item
	 @Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
	           groups = {TestGroup.MSPP0Tests,"MSPLineItemLevelRuleActionTests"}
	            , description = "Verify return functionality at line item level for eligible items", enabled = true)
	    public void line_Item_Level_Verify_Return_Item_Eligible(TestData data) {
		 //	String[] test_data = getProductToTest("MSP_OL_ItemLevelReturnEligible").split("\\|");
		 String[] test_data ="940220064|02252449000".split("\\|");
		 	
 			String orderId=test_data[0];
 			String sku = test_data[1];
	        addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
            	._NavigationAction()
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
            	.addlogType(TestStepType.GIVEN)
            	.clickOnSkuNumberUnderLineItemTab(sku)
            	._LineItemDetailsAction()
            	.addlogType(TestStepType.THEN)
            	.verifyOptionVisible("Return Item")
            	.addlogType(TestStepType.THEN)
            	.fillReturnItemPopUp(1);
	    }
	 
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
	            groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests"}
	            , description = "Verify return option is not shown for orders which are not eligible", enabled = true)
	    public void line_Item_Level_Verify_Return_Item_NonEligible(TestData data) {
			String[] test_data = getProductToTest("MSP_OL_ItemLevelReturnInEligible").split("\\|");
 			String orderId=test_data[0];
 			String sku = test_data[1];
	        addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
            	._NavigationAction()
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
            	.addlogType(TestStepType.GIVEN)
            	.clickOnSkuNumberUnderLineItemTab(sku)
            	._LineItemDetailsAction()
            	.addlogType(TestStepType.THEN)
            	.verifyOptionNotVisible("Return Item")
	        ;
	    }
			
		//Return Tracking Information 
	
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
		groups = {TestGroup.MSPLineItemLevelRuleActionTest,"MSPLineItemLevelRuleActionTests"}, 
        description = "Verify return tracking information is displayed for eligible items", enabled = true)		
		  public void line_Item_Level_Verify_ReturnTrackingInformation_Eligible(TestData data) {
			String[] test_data = getProductToTest("MSP_OL_ItemLevelReturnTrackingInfoEligible").split("\\|");
 			String orderId=test_data[0];
 			String sku = test_data[1];
			addCloneIDHostname(data);
	      LogFormatterAction.beginSetup();
	      User user = new User(); user.userName=UserPool.getUser();
	       
	      As.guestUser.goToHomePage()
          	._NavigationAction()
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
          	.addlogType(TestStepType.GIVEN)
          	.clickOnSkuNumberUnderLineItemTab(sku)
          	._LineItemDetailsAction()
          	.addlogType(TestStepType.THEN)
          	.verifyOptionVisible("Return Tracking Information")
          	.verifyReturnTrackingInformationPopUp()
	      ;
	  }
		
		
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPLineItemLevelRuleActionTest, "MSPLineItemLevelRuleActionTests"},
	           description = "Verify return tracking information should not be shown for ineligible orders", enabled = true)
	  public void line_Item_Level_Verify_ReturnTrackingInformation_NonEligible(TestData data) {
			String[] test_data = getProductToTest("MSP_OL_ItemLevelReturnTrackingInfoInEligible").split("\\|");
 			String orderId=test_data[0];
 			String sku = test_data[1];
	      addCloneIDHostname(data);
	      
	      LogFormatterAction.beginSetup();
	      User user = new User(); user.userName=UserPool.getUser();
	       
	      As.guestUser.goToHomePage()
          	._NavigationAction()
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
          	.addlogType(TestStepType.GIVEN)
          	.clickOnSkuNumberUnderLineItemTab(sku)
          	._LineItemDetailsAction()
          	.addlogType(TestStepType.THEN)
          	.verifyOptionIsNotVisible("Return Tracking Information")
	      ;
	  }
		
	   	
		
	//Update Expected Ship/Arrival Date 
    	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, //dataProviderClass = TestDataProvider.class,
                groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests"},
            description = "Verify online agent can update expected ship arrival date for eligible orders", enabled = true)
       public void  line_Item_Level_Verify_UpdateExpectedShipArrivalDate_Eligible(TestData data) {
    		String[] test_data = getProductToTest("MSP_OL_ItemLevelUpdateETAEligible").split("\\|");
 			String orderId=test_data[0];
 			String sku = test_data[1];
   		addCloneIDHostname(data);
           LogFormatterAction.beginSetup();
           User user = new User(); user.userName=UserPool.getUser();
            
           As.guestUser.goToHomePage()
                   	._NavigationAction()
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
                   	.addlogType(TestStepType.GIVEN)
                   	.clickOnSkuNumberUnderLineItemTab(sku)
                   ._LineItemDetailsAction()
                   .addlogType(TestStepType.THEN)
                   	.verifyOptionVisible("Update Expected Ship/Arrival Date")
                   	.verifyUpdateExpectedShipArrivalDatePopUp();
       }
   	
   	
  	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, //dataProviderClass = TestDataProvider.class,
            groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests"},
        description = "Verify that ineligible orders should not have update ship arrival date option", enabled = true)
   	public void  line_Item_Level_Verify_UpdateExpectedShipArrivalDate_NonEligible(TestData data) {//TestData data
  		
  		String[] test_data = getProductToTest("MSP_OL_ItemLevelUpdateETAInEligible").split("\\|");
			String orderId=test_data[0];
			String sku = test_data[1];
           addCloneIDHostname(data);
           
           LogFormatterAction.beginSetup();
           User user = new User(); user.userName=UserPool.getUser();
           As.guestUser.goToHomePage()
	           	._NavigationAction()
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
	           	.addlogType(TestStepType.GIVEN)
	           	.clickOnSkuNumberUnderLineItemTab(sku)
	           ._LineItemDetailsAction()
	           .addlogType(TestStepType.THEN)
	           .verifyOptionIsNotVisible("Update Expected Ship/Arrival Date");
       }
   	
   	
   	//Contact Marketplace Seller 
   	/*
	 * Verify if email can be sent to Marketplace Seller on the Line Item Level
	 * */	
   	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
            groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests"}
            , description = "Verify if email can be send to the Marketplace Seller from the Line Item Detail Page", enabled = true)
    public void line_Item_Level_Verify_Contact_MarketplaceSeller_Eligible(TestData data) {
   		String[] test_data = getProductToTest("MSP_OL_ItemLevelContactMPSellerEligible").split("\\|");
		String OrderID=test_data[0];
		String sku = test_data[1];
       addCloneIDHostname(data);
        LogFormatterAction.beginSetup();
        User user = new User(); user.userName=UserPool.getUser();
         
        As.guestUser.goToHomePage()
        	._NavigationAction()
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
        	.addlogType(TestStepType.GIVEN)
        	.clickOnSkuNumberUnderLineItemTab(sku)
        	.addlogType(TestStepType.THEN)
        	.verifyOptionVisible("Contact Marketplace Seller")
        	.addlogType(TestStepType.THEN)
        	.verifyEmailTemplatePopUp();    	
 }
	
	
	
	//Contact Vendor
	/*
	 * Verify if email can be sent to Vendor on the Line Item Level
	 * */
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, //dataProviderClass = TestDataProvider.class,
	            groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests"}
	            , description = "Verify if Email can be sent to the Vendor from the Line Item Detail Page", enabled = true)
	    public void  line_Item_Level_Verify_Contact_Vendor_Eligible(TestData data) {
			
			String[] test_data = getProductToTest("MSP_OL_ItemLevelContactVendorEligible").split("\\|");
			String orderId=test_data[0];
			String sku = test_data[1];
			        addCloneIDHostname(data);

	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	         
	        As.guestUser.goToHomePage()
            	._NavigationAction()
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
            	.addlogType(TestStepType.GIVEN)
            	.clickOnSkuNumberUnderLineItemTab(sku)
            	._LineItemDetailsAction()
            	.addlogType(TestStepType.THEN)
            	.verifyOptionVisible("Contact Vendor")
            	.verifyEmailTemplatePopUp() ;
	    }
		
		
		/*
		 * Verify if fulfilled orders have "Contact Vendor" option on the Line Item Level
		 * */
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
	            groups = {TestGroup.MSPLineItemLevelRuleActionTest, "MSPLineItemLevelRuleActionTests"}
	            , description = " Verify if Vendor Fulfilled Orders have Contact Vendor option in Line Item Detail Page", enabled = true)
	    public void  line_Item_Level_Verify_VendorFulfilledOrders_Have_ContactVendor_Option(TestData data) {
			String[] test_data = getProductToTest("MSP_OL_ItemLevelContactVendorEligible").split("\\|");
			String orderId=test_data[0];
			String sku = test_data[1];
			addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	         
	        As.guestUser.goToHomePage()
            	._NavigationAction()
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
            	.clickOnSkuNumberUnderLineItemTab(sku)
            	._LineItemDetailsAction()
            	.addlogType(TestStepType.GIVEN)
            	.clickOnFulfillByUnderLineItemSummary()
            	.addlogType(TestStepType.THEN)
            	.verifyVendorDetailsPageDisplayed();
	    }
		
		//Contact Customer
		/*
		 * Verify if email can be sent to customer on the Line Item Level
		 * */	
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
	            groups = {TestGroup.MSPLineItemLevelRuleActionTest, "MSPLineItemLevelRuleActionTests"}
	            , description = "Verify if Email can be send to the Customer from the Line Item Detail Page", enabled = true)
	    public void line_Item_Level_Verify_Contact_Customer_Eligible(TestData data) {
			String[] test_data = getProductToTest("MSP_OL_ItemLevelContactCustomerEligible").split("\\|");
			String OrderID=test_data[0];
			String sku = test_data[1];
			addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();

	        User user = new User(); user.userName=UserPool.getUser();
	       As.guestUser.goToHomePage()
            	._NavigationAction()
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
            	.addlogType(TestStepType.GIVEN)
            	.clickOnSkuNumberUnderLineItemTab(sku)
            	._LineItemDetailsAction()
            	.addlogType(TestStepType.THEN)
            	.verifyOptionVisible("Contact Customer")
            	.addlogType(TestStepType.THEN)
            	.verifyEmailTemplatePopUp();
				
	    }
		
		//Schedule Return
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,//dataProviderClass = TestDataProvider.class,
				  groups = {TestGroup.MSPLineItemLevelRuleActionTest, "MSPLineItemLevelRuleActionTests"}, 
	           description = "Verify if 'Schedule Return' option is Visible", enabled = true)
	  public void line_Item_Level_Verify_ScheduleReturn_Eligible(TestData data) {

			String[] test_data = getProductToTest("MSP_OL_ItemLevelScheduleReturnEligible").split("\\|");
			String orderId=test_data[0];
			String sku = test_data[1];
			addCloneIDHostname(data);
	      LogFormatterAction.beginSetup();
	      User user = new User(); user.userName=UserPool.getUser();
	      As.guestUser.goToHomePage()
          	._NavigationAction()
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
          	.addlogType(TestStepType.GIVEN)
          	.clickOnSkuNumberUnderLineItemTab(sku)
          	._LineItemDetailsAction()
          	.addlogType(TestStepType.THEN)
          	.verifyOptionVisible("Schedule Return")
          	.verifyScheduleReturnPopUp();
	  }
		
		
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, //dataProviderClass = TestDataProvider.class,
	         groups = {TestGroup.MSPLineItemLevelRuleActionTest, "MSPLineItemLevelRuleActionTests"}, 
	           description = " Verify that 'Schedule Return' option is not displayed for orders not eligible for return", enabled = true)
	  public void line_Item_Level_Verify_ScheduleReturn_NonEligible(TestData data) {
			
			String[] test_data = getProductToTest("MSP_OL_ItemLevelScheduleReturnInEligible").split("\\|");
			String orderId=test_data[0];
			String sku = test_data[1];
			
	      addCloneIDHostname(data);
	      
	      LogFormatterAction.beginSetup();
	      User user = new User(); user.userName=UserPool.getUser();
	       
	      As.guestUser.goToHomePage()
              	._NavigationAction()
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
              	.addlogType(TestStepType.GIVEN)
              	.clickOnSkuNumberUnderLineItemTab(sku)
              	._LineItemDetailsAction()
              	.addlogType(TestStepType.THEN)
              	.verifyOptionNotVisible("Schedule Return");
	  }
		
	//	Reschedule Delivery	
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,//dataProviderClass = TestDataProvider.class,
				groups = {TestGroup.MSPLineItemLevelRuleActionTest, "MSPLineItemLevelRuleActionTests"}
	    , description = "Verify reschedule delivery option is shown for eligible orders", enabled = true)
		public void line_Item_Level_Verify_Eligible_Reschedule_delivery_Eligible(TestData data) throws Exception{
			
			String[] test_data = getProductToTest("MSP_OL_ItemLevelRescheduleDeliveryEligible").split("\\|");
			String OrderID=test_data[0];
			String sku = test_data[1];
			
				addCloneIDHostname(data);
		        LogFormatterAction.beginSetup();

		        User user = new User(); user.userName=UserPool.getUser();
		         
		        As.guestUser.goToHomePage()
		                ._NavigationAction()
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
		                .addlogType(TestStepType.GIVEN)
		                .clickOnSkuNumberUnderLineItemTab(sku)
		                ._LineItemDetailsAction()
		                .addlogType(TestStepType.THEN)
		                .verifyOptionVisible("Reschedule Delivery")
		                .addlogType(TestStepType.THEN)
		        		.verify_Reschedule_Delivery();

		}
		
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,//dataProviderClass = TestDataProvider.class,
				groups = {TestGroup.MSPLineItemLevelRuleActionTest, "MSPLineItemLevelRuleActionTests"}
	    , description = "Verify reschedule delivery option is not shown for ineligible orders", enabled = true)
		public void line_Item_Level_Verify_Eligible_Reschedule_delivery_NonEligible(TestData data) throws Exception{
			
			String[] test_data = getProductToTest("MSP_OL_ItemLevelRescheduleDeliveryInEligible").split("\\|");
			String OrderID=test_data[0];
			String sku = test_data[1];
				addCloneIDHostname(data);
		    	
		        LogFormatterAction.beginSetup();

		        User user = new User(); user.userName=UserPool.getUser();
		         
		        As.guestUser.goToHomePage()
		                ._NavigationAction()
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
		                .addlogType(TestStepType.GIVEN)
		                .clickOnSkuNumberUnderLineItemTab(sku)
		                ._LineItemDetailsAction()
		                .addlogType(TestStepType.THEN)
		                .verifyOptionIsNotVisible("Reschedule Delivery");

		}
		
		 //Start Automated Return Item
		@Test(dataProvider = "DP_Start_Automated_Return_Eligible_orderID", 
		           groups = {TestGroup.MSPP0Tests,"MSPLineItemLevelRuleActionTests"}
		            , description = "Verify Start Automated Return functionality at line item level for eligible items", enabled = true)
		    public void line_Item_Level_Verify_Start_Automated_Return_Item_Eligible(String orderId,String sku) {
			 	orderId="940155199";sku="02208196000";
				
		        addCloneIDHostname(data);
		        LogFormatterAction.beginSetup();
		        User user = new User(); user.userName=UserPool.getUser();
		        As.guestUser.goToHomePage()
	            	._NavigationAction()
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
	            	.addlogType(TestStepType.GIVEN)
	            	.clickOnSkuNumberUnderLineItemTab(sku)
	            	._LineItemDetailsAction()
	            	.addlogType(TestStepType.THEN)
	            	.verifyOptionVisible("Start Automated Return")
	            	.addlogType(TestStepType.THEN)
	            	.verifyStartAutomatedReturnEligible();
		 }

		@Test(dataProvider = "DP_Start_Automated_Return_NonEligible_orderID", 
		           groups = {TestGroup.MSPP0Tests,"MSPLineItemLevelRuleActionTests"}
		            , description = "Verify Start Automated Return functionality at line item level for eligible items", enabled = true)
		    public void line_Item_Level_Verify_Start_Automated_Return_Item_NonEligible(String orderId,String sku) {
			 	orderId="840018754";sku="02213003000";
		        addCloneIDHostname(data);
		        LogFormatterAction.beginSetup();
		        User user = new User(); user.userName=UserPool.getUser();
		        As.guestUser.goToHomePage()
	            	._NavigationAction()
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
	            	.addlogType(TestStepType.GIVEN)
	            	.clickOnSkuNumberUnderLineItemTab(sku)
	            	._LineItemDetailsAction()
	            	.addlogType(TestStepType.THEN)
	            	.verifyOptionVisible("Start Automated Return")
	            	.addlogType(TestStepType.THEN)
	            	.verifyStartAutomatedReturnNonEligible();
		 }
		

			
			//Sale Adjustment
			@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
		            groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests","line_Item_Level_Verify_Sales_Adjustment_CapturedNotesInteraction"}
		            , description = "Verify sales adjustment can be done at line item level", enabled = true)
		    public void  line_Item_Level_Verify_Sales_Adjustment_CapturedNotesInteraction(TestData data) {
				String[] test_data = getProductToTest("MSP_OL_ItemLevel_SaleAdjustmentData").split("\\|");
				//String[] test_data ="940155199|02208196000".split("\\|");
				String orderId=test_data[0];
				String sku = test_data[1];
		        addCloneIDHostname(data);
		        LogFormatterAction.beginSetup();
		        User user = new User(); user.userName=UserPool.getUser();
		        As.guestUser.goToHomePage()
		        	._NavigationAction()
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
		        	.addlogType(TestStepType.GIVEN)
		        	.clickOnSkuNumberUnderLineItemTab(sku)
		        	._NavigationAction()
		        	.closeWarningPopupWindow()
		        	._OrderDetailsAction()
		        	.addlogType(TestStepType.THEN)
		        	.verifylineitemdetails()
		        	.verifyOptionVisible("Sale Adjustment")
		        	.addlogType(TestStepType.GIVEN)
		            .taxadjustment("Sale Adjustment",0.1,orderId)
		            .verifyTrialBalance()
		            .verifyAdjustmentCapturedInInteraction("Sale Adjustment")
		    		.verifyOrderWrapUp()
		    		.addlogType(TestStepType.THEN)
		    		.fillRFCForm()
		    		._NavigationAction()
		    		.addlogType(TestStepType.WHEN)
		    		.searchByOrderId(orderId)
		    		.closeWarningPopupWindow()
		    		._OrderDetailsAction()
		    		.verifyAdjustmentCapturedInNotes("Sale Adjustment")
		    		;
		    }
		
			@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
		            groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests","line_Item_Level_Contact_Customer_CapturedNotesInteraction"}
		            , description = "line_Item_Level_Contact_Customer_CapturedNotesInteraction", enabled = true)
		    public void  line_Item_Level_Contact_Customer_CapturedNotesInteraction(TestData data) {
				String[] test_data = getProductToTest("MSP_OL_ItemLevelContactCustomerEligible").split("\\|");
				//String[] test_data ="940155199|02208196000".split("\\|");
				String orderId=test_data[0];
				String sku = test_data[1];
		        addCloneIDHostname(data);
		        LogFormatterAction.beginSetup();
		        User user = new User(); user.userName=UserPool.getUser();
		        As.guestUser.goToHomePage()
		        	._NavigationAction()
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
		        	.addlogType(TestStepType.GIVEN)
		        	.clickOnSkuNumberUnderLineItemTab(sku)
		        	._NavigationAction()
		        	.closeWarningPopupWindow()
		        	._OrderDetailsAction()
		        	.addlogType(TestStepType.THEN)
		        	.verifylineitemdetails()
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
	        		._OrderDetailsAction()
	        		.addlogType(TestStepType.THEN)
	        		.verifyEmailCapturedInNotes();
			}
			
			  /*
			 * Test case Name :line_Item_Level_Verify_NoCancellation_FBM
			 * Input parameters : order id from OHM
			 * Description:Verify that we don't show cancel option for market place item, 
			 				* this test case is specifically checking cancel line item option is not shown.
			 				* the line item status which is being tested here is CNF status
			 * Date Modified:
			 * Author:Sarika Patil
			 * comments:
			 */
			 

		    @Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
		  	          groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests","line_Item_Level_Verify_Cancellation_Eligible"}
		  	            , description = "Verify cancellation at line item level for eligible orders", enabled = true)
		  	    public void line_Item_Level_Verify_NoCancellation_FBM(TestData data) {
		      		String[] test_data = getProductToTest("ItemLevelFBMCancellation").split(",");
		      		String OrderID=test_data[0];
		      		String sku = test_data[1];
		          
		  	       addCloneIDHostname(data);
		  	        LogFormatterAction.beginSetup();
		  	        User user = new User(); user.userName=UserPool.getUser();
		  	        As.guestUser.goToHomePage()
		                  ._NavigationAction()
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
		                  .addlogType(TestStepType.GIVEN)
		                  .clickOnSkuNumberUnderLineItemTab(sku)
		                  ._LineItemDetailsAction()
		                  .addlogType(TestStepType.THEN)
		                  .verifyOptionNotVisible("Cancellation - Line Item");
		  	 }
		    /*
		   	 * Test case Name :line_Item_Level_Verify_NoCancellation_FBM
		   	 * Input parameters : order id from OHM 
		   	 * Description:Verify that we don't show cancel option for market place item(Kmart), 
		   	 				* this test case is specifically checking cancel line item option is not shown.
		   	 				* the line item status which is being tested here is VDC status
		   	 * Date Modified:
		   	 * Author:Sarika Patil
		   	 * comments:
		   	 */
		   	 

		       @Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
		     	          groups = {TestGroup.MSPP0Tests, "MSPLineItemLevelRuleActionTests","line_Item_Level_Verify_Cancellation_Eligible"}
		     	            , description = "Verify cancellation at line item level for eligible orders", enabled = true)
		     	    public void line_Item_Level_Verify_NoCancellation_FBMKmart(TestData data) {
		         		String[] test_data = getProductToTest("ItemLevelFBMCancellationKmart").split(",");
		         		String OrderID=test_data[0];
		         		String sku = test_data[1];
		             
		     	       addCloneIDHostname(data);
		     	        LogFormatterAction.beginSetup();
		     	        User user = new User(); user.userName=UserPool.getUser();
		     	        As.guestUser.goToHomePage()
		                     ._NavigationAction()
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
		                     .addlogType(TestStepType.GIVEN)
		                     .clickOnSkuNumberUnderLineItemTab(sku)
		                     ._LineItemDetailsAction()
		                     .addlogType(TestStepType.THEN)
		                     .verifyOptionNotVisible("Cancellation - Line Item");
		     	 }
			 

		@DataProvider (name="DP_CancelOrder_Eligible")
		    public Object[][] DP_Cancellation_Eligible_OrderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().Line_Item_Cancellation_Data();
		  String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Cancellation_Eligible_orderID};
		  String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Cancellation_Eligible_SKU};
		  Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		}

		 @DataProvider (name="DP_CancelOrder_NonEligible")
		    public Object[][] DP_Cancellation_NonEligible_OrderID() throws Exception{
			Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().Line_Item_Cancellation_Data();
			String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Cancellation_StatusExpt_orderID};
			String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Cancellation_StatusExpt_SKU};
			Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
			return (testData);		
		 }
		
		@DataProvider (name="DP_SaleAdjustment_Eligible", parallel=true)
	    public Object[][] DP_SaleAdjustment_Eligible_OrderID() throws Exception{
			Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().sale_Adjustment_Line_Item_Data();
			 String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_sale_adjustment_eligible_orderID};
			 String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_sale_adjustment_eligible_SKU};
			 Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return(testData);
	         
	}
		
		@DataProvider (name="DP_SaleAdjustment_NonEligible",parallel=true)
		public Object[][] DP_SaleAdjustment_NonEligible_OrderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().sale_Adjustment_Line_Item_Data();
	         String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_sale_adjustment_status_Expt_orderID};
	         String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_sale_adjustment_status_Expt_SKU};
	         Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
					return (testData);
			}
		 @DataProvider (name="DP_Marketplace_Eligible")
		    public Object[][] DP_Marketplace_Eligible_OrderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().contact_Marketplace_Seller_Data();
		  String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_market_place_seller_eligible_orderID};
		  String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().market_place_seller_eligible_sku};
			 Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return(testData);
		 	}
		 
		 @DataProvider (name="DP_Marketplace_NonEligible")
		    public Object[][] DP_Marketplace_NonEligible_OrderID() throws Exception{
			Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().contact_Marketplace_Seller_Data();
			//String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_market_place_seller_Vendor_id_sc_Expt1_orderID};
			String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_market_place_seller_store_Expt_orderID};
			String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_market_place_seller_store_Expt_SKU};
			/*int sizeOfTestData=OrderID.length+OrderIDstatus.length;
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
			 testData[0][1]= sku[0];
			return testData;
		 }
		 @DataProvider (name="DP_contact_Vendor_Data_Eligible")
		    public Object[][] DP_contact_Vendor_Data_Eligible_OrderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().contact_Vendor_Data();
			 String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Vendor_eligible_orderID};
			 String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Vendor_eligible_SKU};
			 Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		}
		 @DataProvider (name="DP_contact_Vendor_Data_NonEligible")
		    public Object[][] DP_contact_Vendor_Data_NonEligible_OrderID() throws Exception{
			Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().contact_Vendor_Data();
			String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Vendor_store_Expt_orderID};
			String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Vendor_store_Expt_SKU};
			/*String OrderIDstatus[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Vendor_Vendor_id_sc_Expt2_orderID};
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
			 testData[0][1]= sku[0];
			return testData;
		 } 
		 @DataProvider (name="DP_update_Expected_Ship_Arrival_Eligible_OrderID")
		    public Object[][] DP_update_Expected_Ship_Arrival_Eligible_OrderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().update_Expected_Ship_Arrival_Date_Data();
		  String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_update_Expected_Ship_Arrival_D_Eligible_orderID};
		  String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_update_Expected_Ship_Arrival_D_Eligible_SKU};
		  Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		}
		 
		 
		 @DataProvider (name="DP_update_Expected_Ship_Arrival_NonEligible_OrderID")
		    public Object[][] DP_update_Expected_Ship_Arrival_NonEligible_OrderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().update_Expected_Ship_Arrival_Date_Data();
		  String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_update_Expected_Ship_Arrival_D_StoreExpt_orderID};
		  String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_update_Expected_Ship_Arrival_D_StoreExpt_SKU};
		  Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		} 
		 
		 
		 @DataProvider (name="DP_return_Tracking_Information_Eligible_orderID")
		    public Object[][] DP_return_Tracking_Information_Eligible_orderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().return_Tracking_Information_Data();
			  String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Return_Tracking_Information_Eligible_orderID};
			  String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Return_Tracking_Information_Eligible_SKU};
			  Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		}
		 @DataProvider (name="DP_DP_return_Tracking_Information_NonEligible_OrderID")
		    public Object[][] DP_return_Tracking_Information_NonEligible_OrderID() throws Exception{
			Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().return_Tracking_Information_Data();
			String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Return_Tracking_Information_StoreExpt_orderID};
			String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Return_Tracking_Information_StoreExpt_SKU};
			/* Assigning the same data once we get the valid one we can change 
			
			String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Return_Tracking_Information_StoreExpt_orderID};
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
			 testData[0][1]= sku[0];
	         return (testData);
		 } 
		 @DataProvider (name="DP_contact_Customer_Data_Eligible_orderID")
		    public Object[][] DP_contact_Customer_Data_Eligible_orderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().contact_Customer_LineItem_Data();
			 String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Contact_Customer_Eligible_orderID};
			 String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Contact_Customer_Eligible_SKU};
			   Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		}
	 	 
		 @DataProvider (name="DP_reschedule_Delivery_Eligible_orderID")
		    public Object[][] DP_reschedule_Delivery_Eligible_orderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().reschedule_Delivery_Data();
			  String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_reschedule_Delivery_Eligible_orderID};
			  String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_reschedule_Delivery_Eligible_SKU};
			  Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		}
		 @DataProvider (name="DP_reschedule_Delivery_Data_NonEligible_OrderID")
		    public Object[][] DP_reschedule_Delivery_NonEligible_OrderID() throws Exception{
			Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().reschedule_Delivery_Data();
			String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_reschedule_Delivery_FFMinExpt_orderID};
			String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_reschedule_Delivery_FFMinExpt_SKU};
			Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
	         return (testData);
		 } 	 
		 @DataProvider (name="DP_schedule_Eligible_orderID")
		    public Object[][] DP_schedule_Eligible_orderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().schedule_Return_Data();
		  String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_schedule_Return_Eligible_orderID};
		  String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_schedule_Return_Eligible_SKU};
		  Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		}
		 @DataProvider (name="DP_schedule_NonEligible_orderID")
		    public Object[][] DP_schedule_NonEligible_orderID() throws Exception{
			Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().schedule_Return_Data();
			String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_schedule_Return_FFMinExpt_orderID};
			String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_schedule_Return_FFMinExpt_SKU};
			/*String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_schedule_Return_StoreExpt_orderID};
			
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
			 testData[0][1]= sku[0];
	         return (testData);
		 } 
		 
		 @DataProvider (name="DP_Return_Item_Eligible_orderID")
		    public Object[][] DP_Return_Item_Eligible_orderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().Line_Item_Return_Item_Data();
		  String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Return_Item_Eligible_orderID };
		  String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Return_Item_Eligible_SKU };
		  Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		}
		 
		 @DataProvider (name="DP_Return_Item_NonEligible_orderID")
		    public Object[][] DP_Return_Item_NonEligible_orderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().Line_Item_Return_Item_Data();
		  String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Return_Item_StatusExpt_orderID };
		  String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Return_Item_StatusExpt_SKU };
		  Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		}
		 
		 @DataProvider (name="DP_Start_Automated_Return_Eligible_orderID")
		    public Object[][] DP_Start_Automated_Return_Eligible_orderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().Line_Item_Start_Automated_Return_Data();
		  String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Start_Automated_Return_Eligible_orderID };
		  String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Start_Automated_Return_Eligible_SKU };
		  Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		}
		 
		 @DataProvider (name="DP_Start_Automated_Return_NonEligible_orderID")
		    public Object[][] DP_Start_Automated_Return_NonEligible_orderID() throws Exception{
			 Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().Line_Item_Start_Automated_Return_Data();
		  String OrderID[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Start_Automated_Return_StatusExpt_orderID};
		  String sku[]={Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().lineitem_Start_Automated_Return_StatusExpt_SKU};
		  Object[][] testData = new Object[1][2];
			 testData[0][0]= OrderID[0];
			 testData[0][1]= sku[0];
		         return (testData);
		} 
		 
		 @AfterMethod
		 public void tearDown(){
			 Retrieval_Test_Data_By_Query.thread.remove();
			 ExcelUtil.thread.remove();
		 }
}
	 


