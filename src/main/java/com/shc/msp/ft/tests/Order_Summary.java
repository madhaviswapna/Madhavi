package com.shc.msp.ft.tests;

import java.awt.Color;
import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.Constant;
import com.shc.msp.ft.util.ExcelUtil;
import com.shc.msp.ft.util.Retrieval_Test_Data_By_Query;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class Order_Summary extends BaseTests {
	
	@Test(dataProvider = "DP_SearchByOrderID1", groups = {TestGroup.MSPP0Tests, "MSPCustomerInfoTests"}
    , description = "Verify customer information for an order", enabled = true, priority=6)
	public void orderSummaryCustomerInfoVerify(String OrderID) throws Exception{
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
            .searchByOrderId(OrderID)
            .closeWarningPopupWindow()
            ._OrderDetailsAction()
            .addlogType(TestStepType.THEN)
            .verifyOrderDetailsPageDisplayed()
            .addlogType(TestStepType.THEN)
    		.verifyCustomerInfoVerify(OrderID);
	}

	@Test(dataProvider = "DP_SearchByOrderID2",groups = {TestGroup.MSPP0Tests, "orderSummaryVerify"}
    , description = "Verify order summary after searching for an order", enabled = true, priority=7)	
	public void orderSummaryVerify(String OrderID) throws Exception{
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
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
            .addlogType(TestStepType.THEN)
    		.verifyOrderSummary(OrderID);
	}
	
	@Test(dataProvider = "DP_SearchByOrderID3",groups = {TestGroup.MSPP0Tests, "orderSummaryOrderChargesVerify"}, 
			description = "Verify order charges section", enabled = true, priority=8)
	public void orderSummaryOrderChargesVerify(String OrderID) throws Exception{
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
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
            .addlogType(TestStepType.THEN)
    		.verifyOrderCharges(OrderID);
	}
	
	@Test(dataProvider = "DP_Discount_Eligible_orderID",groups = {TestGroup.MSPP0Tests, "orderSummaryDiscountsVerify"}
    , description = "Verify discount section for an order", enabled = true, priority=9)
	public void orderSummaryDiscountsVerify(String OrderID) throws Exception{
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
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
            .addlogType(TestStepType.THEN)
    		.verifyDiscounts(OrderID);
	}

	@Test(dataProvider = "DP_Adjustment", groups = {TestGroup.MSPP1OnlineTests, "orderSummaryAdjustmentsVerify"}
    , description = "Verify adjustments section for an order", enabled = true, priority=10)
	public void orderSummaryAdjustmentsVerify(String OrderID) throws Exception{
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
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
            .addlogType(TestStepType.THEN)
    		.verifyAdjustments(OrderID);
	}
	
	@Test(dataProvider = "DP_Payment", groups = {TestGroup.MSPP0Tests, "orderSummarypaymentVerify_OnlineAgent"}
    , description = "Verify payment section in order summary for online agent", enabled = true)	
	public void orderSummarypaymentVerify_OnlineAgent(String OrderID, String storeID) throws Exception{
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
        User user = new User(); user.userName=UserPool.getUser();
        getContext().put("UserType", "OnlineAgent");
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
            .addlogType(TestStepType.THEN)
    		.verifyPayments(OrderID,storeID);

	}
	
	@Test(dataProvider = "DP_Payment", groups = {TestGroup.MSPP1OnlineTests, "orderSummarypaymentVerify_OfflineAgent"}
    , description = "Verify payment section in order summary for offline agent", enabled = true)	
	public void orderSummarypaymentVerify_OfflineAgent(String OrderID, String storeID) throws Exception{
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		 User user = new User();
	        user.userName = Constant.OfflineUserName;
	        user.password = Constant.OfflinePassword;
	        getContext().put("UserType", "OfflineAgent");
        As.guestUser.goToHomePage()
        	.addlogType(TestStepType.WHEN)
            .login(user)
            .addlogType(TestStepType.WHEN)
            .searchByOrderId(OrderID)
            .closeWarningPopupWindow()
            ._OrderDetailsAction()
            .addlogType(TestStepType.THEN)
            .verifyOrderDetailsPageDisplayed()
            .addlogType(TestStepType.THEN)
    		.verifyPayments(OrderID,storeID);

	}
	
	@Test(dataProvider = "DP_OrderDetails",groups = {TestGroup.MSPP0Tests, "orderSummaryOrderDetailsVerify"}
    , description = "Verify details in order details page", enabled = true, priority=12)
	public void orderSummaryOrderDetailsVerify(String OrderID, String storeId) throws Exception{
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
            .searchByOrderId(OrderID)
            .closeWarningPopupWindow()
            ._OrderDetailsAction()
            .addlogType(TestStepType.THEN)
            .verifyOrderDetailsPageDisplayed()
            .addlogType(TestStepType.THEN)
    		.verifyOrderDetails(OrderID, storeId);

	}

	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups={TestGroup.MSPP1OnlineTests,"MemberPage360DegreeValidation"},description = "Search for member and verify profile details")
	public void MemberPage360DegreeValidation(TestData data){
		addCloneIDHostname(data);
		User user = new User(); user.userName=UserPool.getUser();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.WHEN)
		.searchByCustomer("Namith Newton")
		.addlogType(TestStepType.THEN)
		.verify360DegreePage()
		.addlogType(TestStepType.THEN)
		.logout();
	}
	
	
	
	@Test(dataProvider = "DP_SearchByOrderID2",groups = {TestGroup.MSPP1OnlineTests, "agentNotesVerifyPresent"}
    , description = "Verify agent notes field is present in order details page", enabled = true, priority=14)

	public void agentNotesVerifyPresent(String OrderID) throws Exception{
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
            .searchByOrderId(OrderID)
            .closeWarningPopupWindow()
            ._OrderDetailsAction()
            .addlogType(TestStepType.THEN)
            .verifyOrderDetailsPageDisplayed()
            .addlogType(TestStepType.THEN)
    		.verifyagentNotesPresent();

	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP1OnlineTests, "verifyEmpowermentGuidelineNotificationEnabled"}
    , description = "verifyEmpowermentGuidelineNotificationEnabled", enabled = true, priority=14)
	public void verifyEmpowermentGuidelineNotificationEnabled(TestData data) throws Exception{
		addCloneIDHostname(data);
		//String OrderID = "840044331";
		String OrderID = getProductToTest("MSP_Online_LTV_Enabled_Order");
        LogFormatterAction.beginSetup();
        User user = new User(); user.userName=UserPool.getUser();
        As.guestUser.goToHomePage()
        .addlogType(TestStepType.WHEN)
            .login(user)
            .addlogType(TestStepType.THEN)
            .verifyonlineagent()
            .addlogType(TestStepType.WHEN)
            .searchByOrderId(OrderID)
            ._OrderDetailsAction()
            .addlogType(TestStepType.THEN)
            .verifyEmpowermentGuidelinePopUp()
            .addlogType(TestStepType.THEN)
            .verifyEmpowermentGuidelineStatusColor("enabled");
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP1OnlineTests, "verifyEmpowermentGuidelineNotificationEnabledMarketPlaceItem"}
    , description = "verifyEmpowermentGuidelineNotificationEnabledMarketPlaceItem", enabled = true, priority=14)
	public void verifyEmpowermentGuidelineNotificationEnabledMarketPlaceItem(TestData data) throws Exception{
		addCloneIDHostname(data);
		
		String OrderID = getProductToTest("MSP_Online_LTV_Enabled_Marketplace_Order");
        LogFormatterAction.beginSetup();
        User user = new User(); user.userName=UserPool.getUser();
        As.guestUser.goToHomePage()
        .addlogType(TestStepType.WHEN)
            .login(user)
            .addlogType(TestStepType.THEN)
            .verifyonlineagent()
            .addlogType(TestStepType.WHEN)
            .searchByOrderId(OrderID)
            ._OrderDetailsAction()
            .addlogType(TestStepType.THEN)
            .verifyEmpowermentGuidelinePopUp()
            .addlogType(TestStepType.THEN)
            .verifyMarketplaceItemPopUp()
            .addlogType(TestStepType.THEN)
            .verifyEmpowermentGuidelineStatusColor("enabled");
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP1OnlineTests, "verifyEmpowermentGuidelineNotificationDisabledMarketPlaceItem"}
    , description = "verifyEmpowermentGuidelineNotificationEnabledMarketPlaceItem", enabled = true, priority=14)
	public void verifyEmpowermentGuidelineNotificationDisabledMarketPlaceItem(TestData data) throws Exception{
		addCloneIDHostname(data);
		
		String OrderID = getProductToTest("MSP_Online_LTV_Disabled_Marketplace_Order");
		
        LogFormatterAction.beginSetup();
        User user = new User(); user.userName=UserPool.getUser();
        As.guestUser.goToHomePage()
        .addlogType(TestStepType.WHEN)
            .login(user)
            .addlogType(TestStepType.THEN)
            .verifyonlineagent()
            .addlogType(TestStepType.WHEN)
            .searchByOrderId(OrderID)
            ._OrderDetailsAction()
            .addlogType(TestStepType.THEN)
            .verifyMarketplaceItemPopUp()
            .addlogType(TestStepType.THEN)
            .verifyEmpowermentGuidelineStatusColor("disabled");
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP1OnlineTests, "verifyEmpowermentGuidelineNotificationDisabledKmart"}
    , description = "verifyEmpowermentGuidelineNotificationDisabledKmart", enabled = true, priority=14)
	public void verifyEmpowermentGuidelineNotificationDisabledKmart(TestData data) throws Exception{
		addCloneIDHostname(data);
		//String OrderID = "840048306";//840044803";
		String OrderID = getProductToTest("MSP_Online_LTV_Disabled_Order_Kmart"); 
				
        LogFormatterAction.beginSetup();
        User user = new User(); user.userName=UserPool.getUser();
        As.guestUser.goToHomePage()
        .addlogType(TestStepType.WHEN)
            .login(user)
            .addlogType(TestStepType.THEN)
            .verifyonlineagent()
            .addlogType(TestStepType.WHEN)
            .searchByOrderId(OrderID)
            ._OrderDetailsAction()
            .addlogType(TestStepType.THEN)
            .verifyEmpowermentGuidelineStatusColor("disabled");
	}
	
	 @DataProvider (name="DP_SearchByOrderID1",parallel=true)
	 public Object[][] DP_SearchByOrderID1() throws Exception{		
	 ArrayList<String> searchrows = ExcelUtil.getExcelUtil().searchKeyWord("OrderSummary1", Constant.OrderSearch, 
				Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
	 Object[][] testData = ExcelUtil.getExcelUtil().getFilterDataArray(searchrows, Constant.TotalCols_SearchByOrderID);
    return (testData);
	 }
	 
	 @DataProvider (name="DP_SearchByOrderID2",parallel=true)
	 public Object[][] DP_SearchByOrderID2() throws Exception{		
	 ArrayList<String> searchrows = ExcelUtil.getExcelUtil().searchKeyWord("OrderSummary2", Constant.OrderSearch, 
				Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
	 Object[][] testData = ExcelUtil.getExcelUtil().getFilterDataArray(searchrows, Constant.TotalCols_SearchByOrderID);
    return (testData);
	 }
	 
	 @DataProvider (name="DP_SearchByOrderID3",parallel=true)
	 public Object[][] DP_SearchByOrderID3() throws Exception{		
	 ArrayList<String> searchrows = ExcelUtil.getExcelUtil().searchKeyWord("OrderSummary3", Constant.OrderSearch, 
				Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
	 Object[][] testData = ExcelUtil.getExcelUtil().getFilterDataArray(searchrows, Constant.TotalCols_SearchByOrderID);
    return (testData);
	 }
	 
	 @DataProvider (name="DP_Adjustment",parallel=true)
	    public Object[][] DP_Adjustment() throws Exception{
		 Retrieval_Test_Data_By_Query.Adjustment_OrderID();
		 String OrderID[]={Retrieval_Test_Data_By_Query.hasAdjustment_OrderID};
		 
		 Object testData[][] = {OrderID};
		 return (testData);
			}
	 
	 @DataProvider (name="DP_Payment",parallel=true)
	 public Object[][] DP_Payment() throws Exception{
	 ArrayList<String> storeID = ExcelUtil.getExcelUtil().searchKeyWord("OrderSummary4", Constant.OrderSearch, 
				Constant.StartColumn_StoreID, Constant.TotalCols_SearchByOrderID);
	 ArrayList<String> orderID = ExcelUtil.getExcelUtil().searchKeyWord("OrderSummary4", Constant.OrderSearch, 
				Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
	 Object testData[][] = ExcelUtil.getExcelUtil().getStoreid_Orderid_DataArray(orderID, storeID, Constant.OrderSearch);
     return (testData);
	 }
	 
	 @DataProvider (name="DP_OrderDetails",parallel=true)
	 public Object[][] DP_OrderDetails() throws Exception{
	 ArrayList<String> storeID = ExcelUtil.getExcelUtil().searchKeyWord("OrderSummary5", Constant.OrderSearch, 
				Constant.StartColumn_StoreID, Constant.TotalCols_SearchByOrderID);
	 ArrayList<String> orderID = ExcelUtil.getExcelUtil().searchKeyWord("OrderSummary5", Constant.OrderSearch, 
				Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
	 Object testData[][] = ExcelUtil.getExcelUtil().getStoreid_Orderid_DataArray(orderID, storeID, Constant.OrderSearch);
     return (testData);
	 }
	 
	 @DataProvider (name="DP_Discount_Eligible_orderID")
	 public Object[][] DP_Discount_Eligible_orderID() throws Exception{
     Retrieval_Test_Data_By_Query.Discount_OrderID();
	 String OrderID[]={Retrieval_Test_Data_By_Query.hasDiscount_OrderID};
	 Object testData[][] = {OrderID};
	 return (testData);
	} 
}
