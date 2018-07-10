package com.shc.msp.ft.tests;

import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.Constant;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class Product_Search_Tests extends BaseTests{
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests,"MSP_Online_Product_Search_Sears"}
	, description = "MSP_Online_Product_Search_Sears")
	public void MSP_Online_Product_Search_Sears(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();
		String product = getSearchTermToTest("MSP_ProductSearchTerm");
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.navigateToTab("Product")
		.addlogType(TestStepType.THEN)
		.searchAndVerifyProduct("Sears", product);
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests,"MSP_Online_Product_Search_Kmart"}
	, description = "MSP_Online_Product_Search_Kmart")
	public void MSP_Online_Product_Search_Kmart(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();
		String product = getSearchTermToTest("MSP_ProductSearchTerm");
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
		.login(user)
		.addlogType(TestStepType.THEN)
		.verifyonlineagent()
		.closeWarningPopupWindow()
		.addlogType(TestStepType.WHEN)
		.navigateToTab("Product")
		.addlogType(TestStepType.THEN)
		.searchAndVerifyProduct("Kmart", product);
	}
}
