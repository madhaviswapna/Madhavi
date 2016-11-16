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
		
		String searchEmail = "mswapna@searshc.com";
				
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
		String SYW_No = null;
		
		if(url.contains("msp.prod.global")){
			user.userName = "testonline0509";
			user.password = Constant.OnlinePasswordProd;
			SYW_No = "7081127519381493";
	    }else{
	    	user.userName = UserPool.getUser();
	    	SYW_No = "7081490000143156";
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
		String phoneNumber = null;
		
		if(url.contains("msp.prod.global")){
			user.userName = "testonline0507";
			user.password = Constant.OnlinePasswordProd;
			phoneNumber = "7276438621";
	    }else{
	    	user.userName = UserPool.getUser();
	    	phoneNumber = "7888798789";
	    }
		
		As.guestUser.goToHomePage()
		.addlogType(TestStepType.WHEN)
        .login(user)
        .addlogType(TestStepType.THEN)
        .verifyonlineagent()
        .addlogType(TestStepType.WHEN)
        .searchByCustomer(phoneNumber)
        .addlogType(TestStepType.THEN)
        .verify360DegreePage();
	}
	
	@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests,TestGroup.MSPP0SanityProdTests,"MSP_Online_Member_Search_By_First_and_Last_Name"}
	, description = "Verify Member Search using Phone Number")
	public void MSP_Online_Member_Search_By_First_and_Last_Name(TestData data) throws Exception {
		User user = new User();
		user.userName = UserPool.getUser();
		String name = null;
		String zipCode = null;
		
		if(url.contains("msp.prod.global")){
			user.userName = "testonline0507";
			user.password = Constant.OnlinePasswordProd;
			name = "JOHN NIGRATO";
			zipCode = "34614";
	    }else{
	    	user.userName = UserPool.getUser();
	    	name = "Aranyaambi Arjun";
	    	zipCode = "77002";
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
