package com.shc.msp.ft.tests.Delivery;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import com.shc.automation.BaseTests;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.Constant;
import com.shc.msp.ft.util.ExcelUtil;
import com.shc.msp.ft.util.Retrieval_Test_Data_By_Query;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class Sample extends BaseTests{
	
	//TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,
            groups = {TestGroup.QA_Environment}
            , description = "MSPDELIVERY", enabled = true)
     	        public void MSP_Delivery_Test_Sample(TestData data) {
		addCloneIDHostname(data);
	            LogFormatterAction.beginSetup();
		           User user = new User(); user.userName=UserPool.getUser();
	           user.userName = "testdelivery0001";
	           user.password = "TestPassword";
	           String phoneNumber ="708 889 9150";
	       	As.guestUser.goToHomePage()
                   .demarcate(LogFormatterAction.LogType.BEGINTESTFLOW, "Search for order" + phoneNumber + "and check sales tax adjustment")
                   ._NavigationAction()
                   .login(user)
                  // .verifyonlineagent()
                   .searchByPhoneForDeliveryUser(phoneNumber)
                   ._OrderDetailsAction()
 	       		    		;
	}  
}
	
	
