package com.shc.msp.ft.tests;
import org.testng.annotations.Test;
import com.shc.automation.BaseTests;
import com.shc.automation.FrameworkProperties;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.Constant;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class MemberSearchTests extends BaseTests{
	String url=FrameworkProperties.SELENIUM_BASE_URL;
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests}
	, description = "Verify Member Search using Email")
	public void MSP_Online_Member_Search_By_Email(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();
		String searchEmail = getSearchTermToTest("MSP_MemberSearch_Email");		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.WHEN)
        .searchByCustomer(searchEmail)
        .addlogType(TestStepType.THEN)
        .verify360DegreePage();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests,TestGroup.MSPP0SanityProdTests,"MSP_Online_Member_Search_By_SYW_No"}
	, description = "Verify Member Search using SYW Number")
	public void MSP_Online_Member_Search_By_SYW_No(TestData data) throws Exception {
		User user = new User();		
		String SYW_No = getSearchTermToTest("MSP_MemberSearch_By_SYWNumber");
		
		if(url.contains("msp.prod.global")){
			user.userName = "testonline0509";
			user.password = Constant.OnlinePasswordProd;
			//SYW_No = "7081127519381493";
	    }else{
	    	user.userName = UserPool.getUser();
	    	//SYW_No = "7081047668031139";
	    }
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.WHEN)
        .searchByCustomer(SYW_No)
        .addlogType(TestStepType.THEN)
        .verify360DegreePage();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests,TestGroup.MSPP0SanityProdTests,"MSP_Online_Member_Search_By_Phone_Number"}
	, description = "Verify Member Search using Phone Number")
	public void MSP_Online_Member_Search_By_Phone_Number(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();
		String phoneNumber = getSearchTermToTest("MSP_MemberSearch_By_PhoneNumber");
		
		if(url.contains("msp.prod.global")){
			user.userName = "testonline0507";
			user.password = Constant.OnlinePasswordProd;
	    }else{
	    	user.userName = UserPool.getUser();
	    }
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.WHEN)
        .searchByCustomer(phoneNumber)
        .addlogType(TestStepType.THEN);
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests,TestGroup.MSPP0SanityProdTests,"MSP_Online_Member_Search_By_First_and_Last_Name"}
	, description = "Verify Member Search by first name and last name")
	public void MSP_Online_Member_Search_By_First_and_Last_Name(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();
		String name = getSearchTermToTest("MSP_MemberSearch_By_FirstAndLastName");
		String zipCode = "00000";
		
		if(url.contains("msp.prod.global")){
			user.userName = "testonline0507";
	    }else{
	    	user.userName = UserPool.getUser();
	    }
		
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.WHEN)
        .searchForMemberWithName(name, zipCode)
        .addlogType(TestStepType.THEN)
        .verify360DegreePage();
        
	}
}
