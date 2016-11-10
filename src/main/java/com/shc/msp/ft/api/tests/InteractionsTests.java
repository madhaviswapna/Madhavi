package com.shc.msp.ft.api.tests;

import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.api.test.framework.APITestContext;
import com.shc.automation.api.test.framework.APITestManager;
import com.shc.automation.api.test.framework.entities.APITestResponse;
import com.shc.automation.api.test.framework.exception.APITestException;
import com.shc.automation.utils.TestHarnessContext;

public class InteractionsTests extends BaseTests {

	@Test(groups = { "InteractionsTests" }, description = "Get Orders by email")
	public void GetOrdersByEmail() throws APITestException {
		APITestContext.get().getTestConfig().setPrintJsonResponseInReport(true);
		
		APITestResponse response = new APITestManager().runTest(TestHarnessContext.get().getMethodName());
		APITestContext.get().setTestResponse(response);
		
	}

}
