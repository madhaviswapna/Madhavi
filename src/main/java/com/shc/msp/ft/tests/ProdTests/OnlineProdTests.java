package com.shc.msp.ft.tests.ProdTests;

import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.Constant;
import com.shc.msp.ft.util.DcNumber;
import com.shc.msp.ft.util.ExcelUtil;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class OnlineProdTests extends BaseTests{


	@Test(groups = {TestGroup.MSPP0SanityProdTests} , description = "Verify search by order id", enabled = true)
	public void MSP_Online_Agent_Search_By_OrderNumber() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName = "testonline0501";
		user.password = Constant.OnlinePasswordProd;
		String orderId="941235758";
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(orderId)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed();

	}

	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify search by name", enabled = true)

	public void MSP_Online_Agent_Search_By_Name() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);

		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);

		LogFormatterAction.beginSetup();
		User user = new User();
		user.userName = "testonline0505";
		user.password = Constant.OnlinePasswordProd;
		String firstName="ARANYA"; 
		String lastName="ARJUN";

		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByName(firstName, lastName)
		.selectOrderInMyRecentInteractions(1)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed();

	}



	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify search by salescheck", enabled = true)

	public void MSP_Online_Agent_Search_SalesCheck() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);

		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName = "testonline0504";
		user.password = Constant.OnlinePasswordProd;
		String salesCheck="093002447496";
		String store="10153";

		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchBySalesCheck(salesCheck,store)
		.selectOrderInMyRecentInteractions_SearchByEmail(1)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()

		;

	}

	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify agent search by phone number", enabled = true)

	public void MSP_Online_Agent_Search_Phone() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);

		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName = "testonline0503";
		user.password = Constant.OnlinePasswordProd;
		String phoneNumber="9999999999";

		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByPhone(phoneNumber)
		.selectOrderInMyRecentInteractions(1)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed() ;

	}


	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify order search by email", enabled = true)

	public void MSP_Online_Agent_Search_Email() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);

		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName = "testonline0502";
		user.password = Constant.OnlinePasswordProd;
		String email="searsarjun@gmail.com";


		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByEmail(email)
		.selectOrderInMyRecentInteractions_SearchByEmail(1)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed() ;

	}

	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify customer search by email", enabled = true)

	public void MSP_Online_Agent_Search_Customer() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);

		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName = "testonline0506";
		user.password = Constant.OnlinePasswordProd;
		String email="aranyaaarjun@gmail.com";


		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.navigateToTab("Customer")
		.searchandVerifyCustomer(email);

	}
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify product search navigates to shop", enabled = true)

	public void MSP_Online_Agent_Search_Product() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);

		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName = "testonline0507";
		user.password = Constant.OnlinePasswordProd;
		String product="blender";


		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.navigateToTab("Product")
		.searchandVerifyProductInShop(product);

	}
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify agent can search a case", enabled = true)

	public void MSP_Online_Agent_Search_Case() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);

		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName = "testonline0508";
		user.password = Constant.OnlinePasswordProd;
		String caseId="25534149";


		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.navigateToTab("Case")
		.searchByCaseId(caseId);

	}
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify agent can check SYW status", enabled = true)

	public void MSP_Online_Agent_Search_SYWLink() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);

		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);

		LogFormatterAction.beginSetup();

		User user = new User();
		user.userName = "testonline0509";
		user.password = Constant.OnlinePasswordProd;
		String member="aranyaaarjun@gmail.com";


		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.navigateToTab("SYW link")
		.searchSYWLinkStatus(member);

	}

	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify line item summary for an order", enabled = true, priority =25)
	public void lineItemSummaryVerify() throws Exception{

		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		String OrderID= "979576579";

		User user = new User(); 
		user.userName = "testonline0510";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		.closeWarningPopupWindow()
		._OrderDetailsAction().addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()
		.addlogType(TestStepType.GIVEN)
		.clickOnSkuNumberUnderLineItemTab(1)
		._LineItemDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifylineItemSummary(1);
	}

	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify sales check summary section", enabled = true, priority =15)
	public void salesCheckSummaryVerify() throws Exception {

		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		String OrderID= "979576579";

		User user = new User(); 
		user.userName = "testonline0511";
		user.password = Constant.OnlinePasswordProd;        
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()
		.addlogType(TestStepType.WHEN)
		.clickOnSalesCheckNumberUnderSalesCheckTab(1)
		._SalesCheckDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifySalesCheckSummary(OrderID);
	}

	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify details in order details page", enabled = true, priority=12)
	public void orderSummaryOrderDetailsVerify() throws Exception{
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		String OrderID= "979576579";
		String storeId="10153";
		User user = new User(); 
		user.userName = "testonline0512";
		user.password = Constant.OnlinePasswordProd;      
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

	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify payment section in order summary for online agent", enabled = true)	
	public void orderSummarypaymentVerify_OnlineAgent() throws Exception{
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		String OrderID= "979576579";
		String storeId="10153";
		User user = new User(); 
		user.userName = "testonline0513";
		user.password = Constant.OnlinePasswordProd;      

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
		.verifyPayments(OrderID,storeId);

	}

	@Test(groups = {TestGroup.MSPP0SanityProdTests}, 
			description = "Verify order charges section", enabled = true, priority=8)
	public void orderSummaryOrderChargesVerify() throws Exception{
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		String OrderID= "979576579";

		User user = new User(); 
		user.userName = "testonline0514";
		user.password = Constant.OnlinePasswordProd; 

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

	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify order summary after searching for an order", enabled = true, priority=7)	
	public void orderSummaryVerify() throws Exception{
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		String OrderID= "979576579";

		User user = new User(); 
		user.userName = "testonline0515";
		user.password = Constant.OnlinePasswordProd; 

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
	
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify actions at salescheck level", enabled = true, priority =15)
	public void Verify_Action_Salescheck_Level() throws Exception {

		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		String OrderID= "979576579";

		User user = new User(); 
		user.userName = "testonline0516";
		user.password = Constant.OnlinePasswordProd;        
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()
		.addlogType(TestStepType.WHEN)
		.clickOnSalesCheckNumberUnderSalesCheckTab(1)
		._SalesCheckDetailsAction()
		.addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.verifyActionsPresent("salescheck");

	}
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify actions at order level", enabled = true, priority =15)
	public void Verify_Action_Order_Level() throws Exception {

		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		String OrderID= "979576579";

		User user = new User(); 
		user.userName = "testonline0517";
		user.password = Constant.OnlinePasswordProd;        
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()
		.addlogType(TestStepType.WHEN)
		.clickOnSalesCheckNumberUnderSalesCheckTab(1)
		._SalesCheckDetailsAction()
		.addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.verifyActionsPresent("order");

	}
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify actions at line item level", enabled = true, priority =15)
	public void Verify_Action_line_Level() throws Exception {

		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		String OrderID= "979576579";

		User user = new User(); 
		user.userName = "testonline0518";
		user.password = Constant.OnlinePasswordProd;        
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()		
		.addlogType(TestStepType.THEN)
		.clickOnSkuNumberUnderLineItemTab(1)
		._OrderDetailsAction()
		.verifyActionsPresent("line item");

	}

	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify sales check summary section", enabled = true, priority =15)
	public void Verify_CurrentInteraction() throws Exception {

		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		LogFormatterAction.beginSetup();
		String OrderID= "979576579";

		User user = new User(); 
		user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;        
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.addlogType(TestStepType.WHEN)
		.searchByOrderId(OrderID)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyOrderDetailsPageDisplayed()		
		.addlogType(TestStepType.THEN)
		._OrderDetailsAction()
		.verifyCurrentInteraction("View Order");

	}
	
	@Test(groups = {TestGroup.MSPP0SanityProdTests}, description = "Admin changing roles")
	public void Verify_Role_Changes_Admin()throws Exception {

		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		 user.userName = "testonline0520";
		user.password = Constant.OnlinePasswordProd; 
		

		addCloneIDHostname(data);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyAdmin()
		.addlogType(TestStepType.THEN)
		._NavigationAction()
		.verifyRoleChange(user.userName, "ONLINEAGENT RFC EXEMPT")
		.addlogType(TestStepType.THEN)
		.logout()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyDisplayAdmin("ONLINE AGENT RFC EXEMPT")
		;
	}
	
	@Test(groups = {TestGroup.MSPP0SanityProdTests}, description = "Admin verifying hamburger menu")
	public void Verify_Breadcrumb_Admin()throws Exception {

		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		LogFormatterAction.beginSetup();
		User user = new User(); 
		 user.userName = "testonline0520";
		user.password = Constant.OnlinePasswordProd; 

		addCloneIDHostname(data);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyAdmin()
		.addlogType(TestStepType.THEN)
		._NavigationAction()
		.verifyAdminBreadCrumb()
		.addlogType(TestStepType.THEN)
		.logout()
		
		;
	}
	
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Action_Cancel() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("200", "")
		.chooseOpenHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAction("cancel");

	}  
	
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Action_Hold_For_Future() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("200", "")
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.addlogType(TestStepType.WHEN)
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAction("hold for future");

	}  
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Action_Queue_For_FollowUp() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("200", "")
		.chooseOpenHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAction("queue for follow up");

	}  
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Action_Rereserve() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("200", "")
		.chooseOpenHDOrders()
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAction("re-reserve");

	}  
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Action_Update_SCIM() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("200", "")
		.chooseOpenHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAction("update scim");

	} 
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Action_Wrapup() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("200", "")
		.chooseOpenHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()	
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAction("wrapup");

	}  
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Action_Reschedule() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("200", "")
		.chooseOpenHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAction("reschedule");

	}  
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Action_Pickup() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("100", "")
		.chooseReleasedHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAction("pickup");

	}  
	
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Action_Even_Exchange() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("100", "")
		.chooseReleasedHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.goToActionCenter()
		.addlogType(TestStepType.THEN)
		.verifyAction("even exchange");

	} 
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Tabs_Open_Order() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("200", "")
		.chooseOpenHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyTabsDelivery("open");
		

	}  
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Tabs_Released_Order() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("100", "")
		.chooseReleasedHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyTabsDelivery("released");
		

	}  
	@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Tabs_Shipped_Order() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("200", "")
		.chooseShippedHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyTabsDelivery("shipped");
		

	} 
	/*@Test(groups = {TestGroup.MSPP0SanityProdTests}
	, description = "Verify if cancel button is present", enabled = true)
	public void Verify_Tabs_PartiallyShipped_Order() throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
		User user = new User(); 
		 user.userName = "testonline0519";
		user.password = Constant.OnlinePasswordProd;
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.VerifyDeliveryAgent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.searchByDeliveryOrderId("200", "")
		.chooseShippedHDOrders()
		.addlogType(TestStepType.WHEN)
		._OrderDetailsAction()
		.addlogType(TestStepType.THEN)
		.verifyTabsDelivery("partially-shipped");
		

	}*/
	
	
}
