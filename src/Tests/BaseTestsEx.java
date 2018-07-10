package com.shc.msp.ft.tests;

import org.testng.Assert;

import com.shc.automation.BaseTests;
import com.shc.msp.ft.util.OHM;

public class BaseTestsEx extends BaseTests {
	public String getProductToTest(String keyword, boolean deactivateProduct) {
		String value = getProductToTest(keyword);
		if (value == null) {
			Assert.fail("Test data not found for keyword - " + keyword);
		}
		if (value != null && value.length() > 1 && deactivateProduct) {
			OHM.deactivateProduct(keyword, value);
		}
		return value;
	}

}
