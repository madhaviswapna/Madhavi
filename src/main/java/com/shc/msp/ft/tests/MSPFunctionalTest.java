package com.shc.msp.ft.tests;

import org.testng.annotations.Test;
import com.shc.automation.BaseTests;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.TestGroup;
//import com.shc.msp.ft.util.RetrieveDataInMySQLDB;


public class MSPFunctionalTest extends BaseTests {
                
	
	    	
	    	
	    	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
	                groups = {TestGroup.QA_Environment,"MSP_Verify_EasyOptions_Option_SalesCheckDetailPage_Not_Visible"}
	                , description = "MSP_Verify_EasyOptions_Option_SalesCheckDetailPage_Not_Visible", enabled = true)
	        public void MSP_Verify_EasyOptions_Option_SalesCheckDetailPage_Not_Visible(TestData data) {
	            addCloneIDHostname(data);
	            
	            LogFormatterAction.beginSetup();
	            String orderId = "224002348";
	            User user = new User();
	            user.userName = "TestOnline0001";
		        user.password = "TestPassword";
	             
	            As.guestUser.goToHomePage()
	                    	.demarcate(LogFormatterAction.LogType.BEGINTESTFLOW, "Search for Order Id : "+orderId+ " and Verify if 'Easy Options' option in Sales Check Detail Page is not visible")
	                    ._NavigationAction()
	                    	.login(user)
	                    	.verifyonlineagent()
	                    	.searchByOrderId(orderId)
	                    ._OrderDetailsAction()
	                    	.verifyOrderDetailsPageDisplayed()
	                    	.clickOnSalesCheckNumberUnderSalesCheckTab(1)	 
	                    	//.verifyOptionNotVisible(SelectPage.SALES_CHECK_DETAILS_PAGE, "Easy Options")
	                    ._SalesCheckDetailsAction()
	                    	.verifyOptionNotVisible("Easy Options")
	            ;
	        }
	    
	    	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
	                groups = {TestGroup.QA_Environment,"MSP_Verify_EasyOptions_Option_SalesCheckDetailPage_Visible"}
	                , description = "MSP_Verify_EasyOptions_Option_SalesCheckDetailPage_Visible", enabled = true)
	        public void MSP_Verify_EasyOptions_Option_SalesCheckDetailPage_Visible(TestData data) {
	            addCloneIDHostname(data);
	            
	            LogFormatterAction.beginSetup();
	            String orderId = "6308519";
	            User user = new User();
	            user.userName = "TestOnline0001";
		        user.password = "TestPassword";
	             
	            As.guestUser.goToHomePage()
	                    	.demarcate(LogFormatterAction.LogType.BEGINTESTFLOW, "Search for Order Id : "+orderId+ " and Verify if 'Easy Options' option in Sales Check Detail Page is visible")
	                    ._NavigationAction()
	                    	.login(user)
	                    	.verifyonlineagent()
	                    	.searchByOrderId(orderId)
	                    ._OrderDetailsAction()
	                    	.verifyOrderDetailsPageDisplayed()
	                    	.clickOnSalesCheckNumberUnderSalesCheckTab(1)
	                    ._SalesCheckDetailsAction()
	                    	.verifyStatus()
	                    	.verifyOptionVisible("Easy Options")
	            ;
	        }
	    		    	
	   	
	   	/*
	   	 * Verify if Marketplace Warning PopUp is present
	   	 * */	
	   	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
	               groups = {TestGroup.MSPP1OnlineTests, "MSP_Verify_Marketplace_Warning_PopUp"}
	               , description = "MSP_Verify_Marketplace_Warning_PopUp", enabled = true)
	       public void MSP_Verify_Marketplace_Warning_PopUp(TestData data) {
	           addCloneIDHostname(data);
	           
	           LogFormatterAction.beginSetup();
	           String OrderID = "6444510";
	           User user = new User();
	           user.userName = "TestOnline0001";
	           user.password = "TestPassword";
	            
	           As.guestUser.goToHomePage()
	                   	.demarcate(LogFormatterAction.LogType.BEGINTESTFLOW, "Search for Order Id : "+OrderID+ " and Verify if Marketplace Warning PopUp is present")
	                   ._NavigationAction()
	                   	.login(user)
	                   	.verifyonlineagent()
	                   	.searchByOrderId(OrderID)
	                   ._OrderDetailsAction()
	                   	.closeMarketplaceWarningPopUp()
	           ; 
	           		}
	   	
	   	
	   	
	}