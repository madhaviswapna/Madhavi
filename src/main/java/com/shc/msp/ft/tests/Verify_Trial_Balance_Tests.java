package com.shc.msp.ft.tests;
/// No Longer A Feature
import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.TestGroup;

public class Verify_Trial_Balance_Tests extends BaseTests {
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
            groups = {TestGroup.QA_Environment, "MSPTrial_BalanceTests"}
            , description = "MSPTrial_Balance_Refund_Without_Return", enabled = true)
    public void MSP_Verify_Trial_Balance_Refund_Without_Return(TestData data) {
        addCloneIDHostname(data);
        
        LogFormatterAction.beginSetup();
        String orderId = "1504499505";
        User user = new User();
        user.userName = "TestOnline0001";
        user.password = "TestPassword";
         
        As.guestUser.goToHomePage()
        .login(user)
    	.verifyonlineagent()
    	.searchByOrderId(orderId)
    ._OrderDetailsAction()
    	.verifyOrderDetailsPageDisplayed()
    	.clickOnSkuNumberUnderLineItemTab(1)
    ._LineItemDetailsAction()
    	.verifyOptionVisible("Refund Without Return")	                    	
        //.startAutomatedReturn(0.1)
        .verifyTrialBalance()
        ;
    }
	
	
    @Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
            groups = {TestGroup.QA_Environment, "MSPTrial_BalanceTests"}
    , description = "MSPTrial_Balance_Sales_Tax_Adjustment", enabled = true)
     	        public void MSP_Verify_Trial_Balance_Sales_Tax_Adjustment(TestData data) {
            addCloneIDHostname(data);
            LogFormatterAction.beginSetup();
            String orderId = "1503100103152157";//
           User user = new User();
           user.userName = "TestOnline0001";
           user.password = "TestPassword";
            As.guestUser.goToHomePage()
                    .demarcate(LogFormatterAction.LogType.BEGINTESTFLOW, "Search for order" + orderId + "and cancel the order")
                   ._NavigationAction()
                    .login(user)
                    .verifyonlineagent()
                    .searchByOrderId(orderId)
                    ._OrderDetailsAction()
                    .taxadjustment("Sales Tax Adjustment",0.1,orderId)
                    .verifyTrialBalance()
                    ;
}
    
    
    // Below test case is failing due to test data 
    @Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
            groups = {TestGroup.QA_Environment, "MSPTrial_BalanceTests"}
    , description = "MSPTrial_Balance_Shipping_Adjustment", enabled = true)
 	        public void MSP_Verify_Trial_Balance_Shipping_Adjustment(TestData data) {
        addCloneIDHostname(data);
        LogFormatterAction.beginSetup();
        String orderId = "555407539150";//
       User user = new User();

       user.userName = "TestOnline0001";
       user.password = "TestPassword";
        As.guestUser.goToHomePage()
                .demarcate(LogFormatterAction.LogType.BEGINTESTFLOW, "Search for order" + orderId + "and cancel the order")
               ._NavigationAction()
                .login(user)
                .verifyonlineagent()
                .searchByOrderId(orderId)
                ._OrderDetailsAction()
                .taxadjustment("Shipping Adjustment",1.0,orderId)
                .verifyTrialBalance()
                ;
}
    //Failing due to test data
    @Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
            groups = {TestGroup.QA_Environment, "MSPTrial_BalanceTests"}
    , description = "MSPTrial_Balance_Sales_Adjustment_LineItem", enabled = true)
    public void  MSP_Verify_Trial_Balance_Sales_Adjustment_LineItem(TestData data)   {
        addCloneIDHostname(data);
        
        LogFormatterAction.beginSetup();
        String orderId = "224002348";
        User user = new User();
        user.userName = "TestOnline0001";
        user.password = "TestPassword";
         
        As.guestUser.goToHomePage()
                	.demarcate(LogFormatterAction.LogType.BEGINTESTFLOW, "Search for Order Id : "+orderId+ " and Verify if 'Return Item' option is not visible")
                	._NavigationAction()
                	.login(user)
                	.verifyonlineagent()
                	.searchByOrderId(orderId)
                	._OrderDetailsAction()
                	.verifyOrderDetailsPageDisplayed()
                	.clickOnSkuNumberUnderLineItemTab(1)
                	.verifylineitemdetails()
                    .taxadjustment("Sale Adjustment",0.1,orderId)
                    .verifyTrialBalance()
                	;
    }	        
    
    @Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
            groups = {TestGroup.QA_Environment, "MSPTrial_BalanceTests"}
    , description = "MSPTrial_Balance_Salescheck_cancelOrder", enabled = true)
    public void MSP_Verify_Trial_Balance_Salescheck_cancelOrder(TestData data) {
        addCloneIDHostname(data);
        LogFormatterAction.beginSetup();
        String orderId = "940039022";//
       User user = new User();

       user.userName = "TestOnline0001";
       user.password = "TestPassword";
        As.guestUser.goToHomePage()
                .demarcate(LogFormatterAction.LogType.BEGINTESTFLOW, "Search for order" + orderId + "and cancel the order")
               ._NavigationAction()
                .login(user)
                .verifyonlineagent()
                .searchByOrderId(orderId)
                ._OrderDetailsAction()
                .clickOnSalesCheckNumberUnderSalesCheckTab(1)
                .verifysalescheckdetails()
                ._SalesCheckDetailsAction()
                .cancelSalesCheck()
                .verifyTrialBalance()

        ;
    }
    //Failing due to test data
    @Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
            groups = {TestGroup.QA_Environment, "MSPTrial_BalanceTests"}
    , description = "MSPTrial_Balance_Sales_Adjustment", enabled = true)
    public void  MSP_Verify_Trial_Balance_Sales_Adjustment(TestData data) {
        addCloneIDHostname(data);
        
        LogFormatterAction.beginSetup();
        String orderId = "1503100103152157";
        User user = new User();
        user.userName = "TestOnline0001";
        user.password = "TestPassword";
         
        As.guestUser.goToHomePage()
                .demarcate(LogFormatterAction.LogType.BEGINTESTFLOW, "Search for Order Id : "+orderId+ " and Verify if 'Return Item' option is not visible")
                ._NavigationAction()
                .login(user)
                .verifyonlineagent()
                .searchByOrderId(orderId)
                ._OrderDetailsAction()
                .verifyOrderDetailsPageDisplayed()
                .saleAdjustmentLineitem("Sale Adjustment",0.1)
                .verifyTrialBalance()

                	;
    }	        
    @Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
            groups =  {TestGroup.QA_Environment, "MSPTrial_BalanceTests"}
    , description = "MSPTrial_Balance_lineItem_cancelOrder", enabled = true)
    public void MSP_Verify_Trial_Balance_lineItem_cancelOrder(TestData data) {
        addCloneIDHostname(data);
        LogFormatterAction.beginSetup();
        String orderId = "4948006";//
       User user = new User();

       user.userName = "TestOnline0001";
       user.password = "TestPassword";
        As.guestUser.goToHomePage()
               .demarcate(LogFormatterAction.LogType.BEGINTESTFLOW, "Search for order" + orderId + "and cancel the order")
               ._NavigationAction()
               .login(user)
               .verifyonlineagent()
               .searchByOrderId(orderId)
               ._OrderDetailsAction()
               .clickOnSkuNumberUnderLineItemTab(1)
               ._LineItemDetailsAction()
               .cancelLineItem()
               .verifyTrialBalance() ;
    }
    
    
}
