package com.shc.msp.ft.tests;

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

public class Search_Summary extends BaseTests {
	
	
	@Test(dataProvider = "DP_SearchByOrderID",groups = {TestGroup.MSPOrderSearchAndSummary, "MSPOrderSearchAndSummaryTests"}
    , description = "Verify search by order id")//, enabled = true,dependsOnMethods = { "MSP_Search_Tests" }

	public void Search_Summary_Test(String orderId, String storeId) throws Exception {
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
	        	.addlogType(TestStepType.THEN)
	        	.orderSummaryPageVerify(orderId, storeId)
	        	.addlogType(TestStepType.WHEN)
	        	.clickOnSalesCheckNumberUnderSalesCheckTab(1)
	        	._SalesCheckDetailsAction()
	        	.addlogType(TestStepType.THEN)
	        	.salesCheckSummaryPageVerify(orderId, storeId)
	        	._OrderDetailsAction()
	        	.addlogType(TestStepType.WHEN)
	        	.clickOnSkuNumberUnderLineItemTab(1)
	        	._LineItemDetailsAction()
	        	.addlogType(TestStepType.THEN)
	        	.lineItemSummaryPageVerify(1,orderId,storeId);
	}

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPP0Tests}
    , description = "Verify if the case ID associated to the activity is captured as interaction")
	public void caseInteractionCapture(TestData data) throws Exception {
	String orderId=Retrieval_Test_Data_By_Query.getOrder();
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
	        	.addlogType(TestStepType.WHEN)
	        	.closeWarningPopupWindow()
	        	._OrderDetailsAction()
	        	.addlogType(TestStepType.THEN)
	        	.verifyOrderDetailsPageDisplayed()
	        	.addlogType(TestStepType.THEN)
	        	.captureInteractionCaseId()
	        	._NavigationAction()
	        	.addlogType(TestStepType.THEN)
	        	.refreshPage()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCaseDetails("VIEW_ONLY","Recent Activity")
	        	.addlogType(TestStepType.WHEN)
	        	.searchByCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCaseDetails("VIEW_ONLY","Case Search Results")
	        	;
	        	
	}


	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPP0Tests}
    , description = "Verify Order Wrap Up with RFC")//, enabled = true,dependsOnMethods = { "MSP_Search_Tests" }

	public void orderWrapWithRFC(TestData data) throws Exception {
		String orderId=Retrieval_Test_Data_By_Query.getOrder();
		
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
	        	.addlogType(TestStepType.WHEN)
	        	.closeWarningPopupWindow()
	        	._OrderDetailsAction()
	        	.addlogType(TestStepType.THEN)
	        	.verifyOrderDetailsPageDisplayed()
	        	.addlogType(TestStepType.THEN)
	        	.captureInteractionCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyOrderWrapUp()
	        	.addlogType(TestStepType.THEN)
	        	.fillRFCForm()
	        	._NavigationAction()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCaseDetails("INTERACTION_CLOSED","Recent Activity")
	        	.addlogType(TestStepType.WHEN)
	        	.searchByCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCaseDetails("INTERACTION_CLOSED","Case Search Results")
	        	;
	        	
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPP0Tests}
    , description = "Verify if a case can be created by assigning it to a queue.")//, enabled = true,dependsOnMethods = { "MSP_Search_Tests" }

	public void orderWrapUpCreateCaseByRoutingToQueue(TestData data) throws Exception {
		String orderId=Retrieval_Test_Data_By_Query.getOrder();
		
	addCloneIDHostname(data);
	LogFormatterAction.beginSetup();
	//orderId="941243744";
	User user = new User(); user.userName=UserPool.getUser();
	As.guestUser.goToHomePage()
				.addlogType(TestStepType.WHEN)
	        	.login(user)
	        	.addlogType(TestStepType.THEN)
	        	.verifyonlineagent()
	        	.addlogType(TestStepType.WHEN)
	        	.deleteCasesforOrderfromDB(orderId)
	        	.addlogType(TestStepType.WHEN)
	        	.searchByOrderId(orderId)
	        	.addlogType(TestStepType.WHEN)
	        	.closeWarningPopupWindow()
	        	._OrderDetailsAction()
	        	.addlogType(TestStepType.THEN)
	        	.verifyOrderDetailsPageDisplayed()
	        	.addlogType(TestStepType.THEN)
	        	.captureInteractionCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCreateCaseByRouting()
	        	.addlogType(TestStepType.THEN)
	        	.fillRFCForm()
	        	._NavigationAction()
	        	.addlogType(TestStepType.WHEN)
	        	.searchByCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCaseDetails("CASE_UNASSIGNED","Case Search Results")
	        	.addlogType(TestStepType.THEN)
	        	.verifyCaseAssignedToQueue("USPS");
	        	;
	        	
	}

	@DataProvider(name = "getCategoryIndex", parallel = true)
	public Object[][] getCategoryIndex() throws Exception {
		int noOfCategoriesToTest = 38;
		Integer[][] dataArray = new Integer[noOfCategoriesToTest][1];;
		for (int i=0,j=0;i<noOfCategoriesToTest+1;i++) {
			if(i==30){
				continue;
			}
			dataArray[j++][0]=i+2;
			
		}
		return dataArray;
	}


	
	@Test(dataProvider = "getCategoryIndex",groups = {TestGroup.MSP_P2_ONLINE_VERIFY_ALL_CATEGORIES}
    , description = "Verify Wrap up with All Category & Reason Code combinations")//, enabled = true,dependsOnMethods = { "MSP_Search_Tests" }
	public void orderWrapUpVerifyAllCategoriesReasonCodes(Integer categoryIndex) throws Exception {
		String orderId=Retrieval_Test_Data_By_Query.getOrder();
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
	        	.deleteCasesforOrderfromDB(orderId)
	        	.addlogType(TestStepType.WHEN)
	        	.searchByOrderId(orderId)
	        	.addlogType(TestStepType.WHEN)
	        	.closeWarningPopupWindow()
	        	._OrderDetailsAction()
	        	.addlogType(TestStepType.THEN)
	        	.verifyOrderDetailsPageDisplayed()
	        	.addlogType(TestStepType.THEN)
	        	.captureInteractionCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyAllCategoriesReasonCodes(categoryIndex)
	        	.addlogType(TestStepType.THEN)
	        	.fillRFCForm()
	        	._NavigationAction()
	        	.addlogType(TestStepType.WHEN)
	        	.searchByCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCaseDetails("","Case Search Results")
	        	//.addlogType(TestStepType.THEN)
	        	//.verifyCaseAssignedToQueue("USPS");
	        	;
	        	
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPP0Tests}
    , description = "Verify if a case can be searched as offline agent.")

	public void searchAndOpenCase(TestData data) throws Exception {
		String orderId=Retrieval_Test_Data_By_Query.getOrder();
		
	addCloneIDHostname(data);
	LogFormatterAction.beginSetup();
	User user = User.find("Onlineuser1");
	As.guestUser.goToHomePage()
				.addlogType(TestStepType.WHEN)
	        	.login(user)
	        	.addlogType(TestStepType.THEN)
	        	.verifyonlineagent()
	        	.addlogType(TestStepType.WHEN)
	        	.deleteCasesforOrderfromDB(orderId)
	        	.addlogType(TestStepType.WHEN)
	        	.searchByOrderId(orderId)
	        	.addlogType(TestStepType.WHEN)
	        	.closeWarningPopupWindow()
	        	._OrderDetailsAction()
	        	.addlogType(TestStepType.THEN)
	        	.verifyOrderDetailsPageDisplayed()
	        	.addlogType(TestStepType.THEN)
	        	.captureInteractionCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCreateCaseByRouting()
	        	.addlogType(TestStepType.THEN)
	        	.fillRFCForm()
	        	._NavigationAction()
	        	.addlogType(TestStepType.THEN)
	        	.verifyofflineagent()
	        	.addlogType(TestStepType.WHEN)
	        	.searchByCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCaseDetails("CASE_UNASSIGNED","Case Search Results");
	        	
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPP0Tests}
	, description = "Verify if a case can be selected from assigned queue by an offline agent and wrapped up.")

	public void Offline_Agent_Assign_From_Queue(TestData data) throws Exception {
		String orderId=Retrieval_Test_Data_By_Query.getOrder();
		
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		User user = User.find("Onlineuser1");
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.deleteCasesforOrderfromDB(orderId)
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(orderId)
		.addlogType(TestStepType.WHEN)
		.closeWarningPopupWindow()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()
		.addlogType(TestStepType.THEN)
		.captureInteractionCaseId()
		.addlogType(TestStepType.THEN)
		.verifyCreateCaseByRouting()
		.addlogType(TestStepType.THEN)
		.fillRFCForm()
		._NavigationAction()
		.addlogType(TestStepType.THEN)
		.verifyofflineagent()
		.addlogType(TestStepType.WHEN)
		.selectCaseFromAssignedQueue()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()
		.captureInteractionCaseId()
		.captureOrderIdFromODPage()
		._NavigationAction()
		.navigateToHomePageFromMenu()
		.searchByCaseId()
		.addlogType(TestStepType.THEN)
		.verifyCaseDetails("CASE_WORKING","Case Search Results")
		.openCaseByOrderID()
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()
		.verifyCloseCaseByWrapupOfflineAgent()
		._NavigationAction()
		.navigateToHomePageFromMenu()
		.searchByCaseId()
		.addlogType(TestStepType.THEN)
		.verifyCaseDetails("CASE_CLOSED","Case Search Results")
		;


	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPP0Tests}
    , description = "Verify if a case can be searched and opened as offline agent.")

	public void caseValidation(TestData data) throws Exception {
		String orderId=Retrieval_Test_Data_By_Query.getOrder();
		
	addCloneIDHostname(data);
	LogFormatterAction.beginSetup();
	User user = User.find("Onlineuser1");
	As.guestUser.goToHomePage()
				.addlogType(TestStepType.WHEN)
	        	.login(user)
	        	.addlogType(TestStepType.THEN)
	        	.verifyonlineagent()
	        	.addlogType(TestStepType.WHEN)
	        	.deleteCasesforOrderfromDB(orderId)
	        	.addlogType(TestStepType.WHEN)
	        	.searchByOrderId(orderId)
	        	.addlogType(TestStepType.WHEN)
	        	.closeWarningPopupWindow()
	        	._OrderDetailsAction()
	        	.addlogType(TestStepType.THEN)
	        	.verifyOrderDetailsPageDisplayed()
	        	.addlogType(TestStepType.THEN)
	        	.captureInteractionCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCreateCaseByRouting()
	        	.addlogType(TestStepType.THEN)
	        	.fillRFCForm()
	        	._NavigationAction()
	        	.addlogType(TestStepType.THEN)
	        	.verifyofflineagent()
	        	.addlogType(TestStepType.WHEN)
	        	.searchByCaseId()
	        	.addlogType(TestStepType.THEN)
	        	.verifyCaseDetails("CASE_UNASSIGNED","Case Search Results")
	        	.addlogType(TestStepType.THEN)
	        	.openCaseByOrderID()
	        	._OrderDetailsAction()
	        	.addlogType(TestStepType.THEN)
	        	.verifyOrderDetailsPageDisplayed();
	}
	@DataProvider(name = "DP_SearchByOrderID", parallel = true)
	public Object[][] DP_SearchByOrderID() throws Exception {
		ArrayList<String> storeID = ExcelUtil.getExcelUtil().searchKeyWord("OrderSummary2", Constant.OrderSearch, Constant.StartColumn_StoreID, Constant.TotalCols_SearchByOrderID);
		ArrayList<String> orderID = ExcelUtil.getExcelUtil().searchKeyWord("OrderSummary2", Constant.OrderSearch, Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
		Object testData[][] = ExcelUtil.getExcelUtil().getStoreid_Orderid_DataArray(orderID, storeID, Constant.OrderSearch);

		return (testData);

	}

	}

