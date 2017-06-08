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
import com.shc.msp.ft.util.MongoDB;
import com.shc.msp.ft.util.Retrieval_Test_Data_By_Query;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class AdminTests extends BaseTests {

	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP1OnlineTests,"SuperAdmin_Manage_Queue"}, description = "SuperAdmin_Manage_Queue", enabled = true)
	public void SuperAdmin_Manage_Queue(TestData data) throws Exception{

		addCloneIDHostname(data);
		User user = User.find("Onlineuser1");
		String queueName = MongoDB.fetchRandomQueueFrom_cssQueue();
		
		LogFormatterAction.beginSetup();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifySuperAdmin()
		.addlogType(TestStepType.THEN)
		._NavigationAction()
		.verifyManageQueues(queueName)
		.addlogType(TestStepType.THEN)
		.resetQueueValues();
	}	
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP1OnlineTests,"Admin_Manage_Queue"}, description = "Admin_Manage_Queue", enabled = true)
	public void Admin_Manage_Queue(TestData data) throws Exception{

		addCloneIDHostname(data);
		User user = new User(); user.userName=UserPool.getUser();
		String queueName = MongoDB.fetchRandomQueueFrom_cssQueue();
		
		LogFormatterAction.beginSetup();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyAdmin()
		.addlogType(TestStepType.THEN)
		._NavigationAction()
		.verifyManageQueues(queueName)
		.addlogType(TestStepType.THEN)
		.resetQueueValues();
	}

	@Test(dataProvider = "DP_Layaway_Contract_Details",groups = {TestGroup.MSPP0Tests,TestGroup.MSPSuperAdminTest,"Search_Layaway_SuperAdmin"}, description = "Admin search layaway contract by contact id")
	public void Search_Layaway_SuperAdmin(String contractID)throws Exception {

		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		LogFormatterAction.beginSetup();
		User user = User.find("Onlineuser1");
		addCloneIDHostname(data);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifySuperAdmin()
		.addlogType(TestStepType.THEN)
		._NavigationAction()
		.VerifyLayawayContractDetails(contractID)
		.addlogType(TestStepType.THEN)
		.logout();
	}

	@Test(dataProvider = "DP_Vendor_Details",groups = {TestGroup.MSPP0Tests,TestGroup.MSPSuperAdminTest,"Search_vendor_SuperAdmin"}, description = "Admin searches vendor details by vendorid")
	public void Search_Vendor_SuperAdmin(String VendorID)throws Exception {
		VendorID=getProductToTest("MSP_Vendor_ID");
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		LogFormatterAction.beginSetup();
		User user = User.find("Onlineuser1");
		addCloneIDHostname(data);
		As.guestUser.goToHomePage()
		._NavigationAction()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifySuperAdmin()
		.addlogType(TestStepType.THEN)
		._NavigationAction()	
		.VerifyVendorDetails(VendorID)
		.addlogType(TestStepType.THEN)
		.logout();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP1OnlineTests,"verifyRolePrivelegeAssignmentSuperAdmin"}, description = "verifyRolePrivelegeAssignmentSuperAdmin", enabled = true)
	public void verifyRolePrivelegeAssignmentSuperAdmin(TestData data) throws Exception{

		addCloneIDHostname(data);
		User user = User.find("Onlineuser1");
		String role = MongoDB.fetchRandomRoleFrom_cssRole();
		LogFormatterAction.beginSetup();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifySuperAdmin()
		.addlogType(TestStepType.THEN)
		._NavigationAction()
		.verifyPrivelegeAssignmentToRole(role)
		.resetPrivelegeAssignmentToRole(role);
	}
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPSuperAdminTest,TestGroup.MSPP1OnlineTests,"manage_Roles_SuperAdmin"}, description = "superAdmin_manage_roles", enabled = true)
	public void View_Queue_Volume_Report_Admin(TestData data) throws Exception{

		addCloneIDHostname(data);
		User user = User.find("Onlineuser1");

		LogFormatterAction.beginSetup();
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyAdmin()
		.addlogType(TestStepType.THEN)
		._NavigationAction()
		.queueVolumeReport();
	}	

	@DataProvider (name="DP_Layaway_Contract_Details",parallel=true)
	public Object[][] DP_Return_Tracking_Information() throws Exception{
		Retrieval_Test_Data_By_Query.layaway_contract_details_fetch();
		String contractID[] = {Retrieval_Test_Data_By_Query.layaway_contract_details_fetch};
		Object[][] testData = {contractID};
		return (testData);
	}

	@DataProvider (name="DP_Vendor_Details",parallel=true)
	public Object[][] DP_Vendor_Details() throws Exception{
		Retrieval_Test_Data_By_Query.vendor_details_fetch();
		String VendorID[] = {Retrieval_Test_Data_By_Query.Vendor_details_fetch};
		Object[][] testData = {VendorID};
		return (testData);
	}
}
