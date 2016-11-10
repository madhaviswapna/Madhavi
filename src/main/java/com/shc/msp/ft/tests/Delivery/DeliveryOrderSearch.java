package com.shc.msp.ft.tests.Delivery;

import java.text.ParseException;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.FrameworkProperties;
import com.shc.automation.Logger;
import com.shc.automation.dao.ProductData;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.tests.BaseTestsEx;
import com.shc.msp.ft.util.Constant;
import com.shc.msp.ft.util.DcNumber;
import com.shc.msp.ft.util.ExcelUtil;
import com.shc.msp.ft.util.MongoDB;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class DeliveryOrderSearch extends BaseTestsEx{
	String url=FrameworkProperties.SELENIUM_BASE_URL;

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"Deliver_Test_search_phonenumber"}
	, description = "Search by phone number and verify results", enabled = true)
	public void MSP_Delivery_Test_Search_Phonenumber(TestData data) {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		user.userName=UserPool.getDeliveryUser();
		//user.userName = "testdelivery0001";
		//user.password = "TestPassword";
		String phoneNumber ="6164503584";
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByPhoneForDeliveryUser(phoneNumber)

		;
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Test_Sequence"}
	, description = "Verify the sequence in which the orders appear on order search", enabled = true)
	public void MSP_Delivery_Test_Sequence(TestData data) throws ParseException {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//MongoDB.createUser(user.userName);
		//user.userName = "testdelivery0001";
		//user.password = "TestPassword";
		String phoneNumber ="6164503584";
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByPhoneForDeliveryUser(phoneNumber)
		.verifySequenceOfDisplay()

		;
	}  
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
			groups = {TestGroup.QA_Environment,TestGroup.MSPP0DeliveryTests,"MSP_Delivery_Update_Name_Email_Number"}
	, description = "Update the phone number, address line, name and email and verify whether updated", enabled = true)
	public void MSP_Delivery_Update_Name_Email_Number(TestData data) throws Exception {
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = new User(); user.userName=UserPool.getDeliveryUser();
		//user.userName = "testdelivery0001";
		//user.password = "TestPassword";
		String orderId= getProductToTest("Rereserve_Eligible_Open_Order",true);	

		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId(orderId,  DcNumber.DC_NO)
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.updateAndVerifyNameEmailNumber()

		;
	}  
	
        //Melvin - Updated code using product id
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSPP0DeliveryTests, "MSP_Delivery_Agent_Search_By_OrderNumber"}

        , description = "Verify search by order id", enabled = true, priority=1)
		public void MSP_Delivery_Agent_Search_By_OrderNumber(TestData data) throws Exception {
			addCloneIDHostname(data);
		
		    LogFormatterAction.beginSetup();
		
		    User user = new User();
		    user.userName=UserPool.getDeliveryUser();
		    ProductData order = getProductDataToTest("MSP_Delivery_Order");
		    String dosorderID=order.getPartNumber().toString();
			String dosunitID=order.getUnitNumber().toString();
			String dosOrderInfo = dosorderID + "-" + dosunitID;

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
		            .verifySearchedDOSOrderIsDisplayed(dosOrderInfo, "Order");
		}
		

		//Melvin - Updated code using product id
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSPP0DeliveryTests, "MSP_Delivery_Agent_Search_By_PhoneNumber"}
        , description = "Verify search by phone number", enabled = true, priority=1)
		public void MSP_Delivery_Agent_Search_By_PhoneNumber(TestData data) throws Exception {
			addCloneIDHostname(data);
		    LogFormatterAction.beginSetup();
		
		    User user = new User();
		    user.userName = UserPool.getDeliveryUser();

		    ProductData order = getProductDataToTest("MSP_Delivery_Phone");
		    String phoneNumber=order.getPartNumber().toString();
		    
		    As.guestUser.goToHomePage()
		    		.addlogType(TestStepType.WHEN)
		            .login(user)
		            .addlogType(TestStepType.WHEN)
		            .closeWarningPopupWindow()
		            .searchByDOSPhone(phoneNumber)
		            .selectOrderInMyRecentDeliveryInteractions(1)
		            .closeWarningPopupWindow()
		            ._OrderDetailsAction()
		            .addlogType(TestStepType.THEN)
		            .verifySearchedDOSOrderIsDisplayed(phoneNumber, "Phone");
		}

		
		//Melvin - Updated code using product id
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSPP0DeliveryTests, "MSP_Delivery_Agent_Search_By_SalesCheck"}
        , description = "Verify search by sales check", enabled = true, priority=1)
		public void MSP_Delivery_Agent_Search_By_SalesCheck(TestData data) throws Exception {
			addCloneIDHostname(data);
		    LogFormatterAction.beginSetup();
		
		    User user = new User();
		    user.userName = UserPool.getDeliveryUser();
		    
		    ProductData order = getProductDataToTest("MSP_Delivery_Salescheck");
		    String salescheckNumber=order.getPartNumber().toString();
		    
		    As.guestUser.goToHomePage()
		    		.addlogType(TestStepType.WHEN)
		            .login(user)
		            .addlogType(TestStepType.WHEN)
		            .closeWarningPopupWindow()
		            .searchByDOSSalesCheck(salescheckNumber)
		            .selectOrderInMyRecentDeliveryInteractions(1)
		            .closeWarningPopupWindow()
		            ._OrderDetailsAction()
		            .addlogType(TestStepType.THEN)
		            .verifySearchedDOSOrderIsDisplayed(salescheckNumber, "Salescheck");
		}
		

		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPSearch,TestGroup.MSPP0DeliveryTests, "MSP_Delivery_Agent_Search_By_Date"}
        , description = "Verify search by delivery date", enabled = true, priority=1)
		public void MSP_Delivery_Agent_Search_By_Date(TestData data) throws Exception {
			
			addCloneIDHostname(data);
			
		    LogFormatterAction.beginSetup();
		
		     
		    User user = new User();
		    user.userName = UserPool.getDeliveryUser();
		    
		    As.guestUser.goToHomePage()
		    		.addlogType(TestStepType.WHEN)
		            .login(user)
		            .addlogType(TestStepType.WHEN)
		            .closeWarningPopupWindow()
		            .searchDOSOrdersByDate()
            		.selectOrderInMyRecentDeliveryInteractions(1)
            		.closeWarningPopupWindow()
            		._OrderDetailsAction()
		            .addlogType(TestStepType.THEN)
		            .verifySearchedDOSOrderIsDisplayed("", "Date");
		}
	 	
}


