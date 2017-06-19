package com.shc.msp.ft.tests;

import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.Constant;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class Login_Logout extends BaseTests {
	
	@Test(groups = {TestGroup.MSPP1OnlineTests, "loginLogout_OnlineAgent"}
            , description = "Verify login and logout is working for online agent")
    public void loginLogout_OnlineAgent() {

        LogFormatterAction.beginSetup();
        User user = new User(); user.userName=UserPool.getUser();
        user.userName = UserPool.getUser();
        user.password = Constant.OnlinePassword;
        
        As.guestUser.goToHomePage()
                ._NavigationAction()
                 .addlogType(TestStepType.WHEN)
                .login(user)
                 .addlogType(TestStepType.THEN)
                .logout();
    }
	
	
	@Test(groups = {TestGroup.MSPP1OnlineTests, "loginLogout_OfflineAgent"}
            , description = "Verify login and logout is working for offline agent")
    public void loginLogout_OfflineAgent() {

        LogFormatterAction.beginSetup();
        User user = new User(); user.userName=UserPool.getUser();
        user.userName = Constant.OfflineUserName;
        user.password = Constant.OfflinePassword;
        
        As.guestUser.goToHomePage()
                ._NavigationAction()
                .addlogType(TestStepType.WHEN)
                .login(user)
                .addlogType(TestStepType.THEN)
                .logout()
         ;
    }
	
	
	@Test(groups = {TestGroup.MSPP1OnlineTests, "loginLogout_Delivery_Agent"}
            , description = "Verify login and logout is working for delivery agent")
    public void loginLogout_Delivery_Agent() {

        LogFormatterAction.beginSetup();
        User user = new User(); user.userName=UserPool.getUser();
        user.userName = Constant.DeliveryUserName;
        user.password = Constant.DeliveryPassword;
        
        As.guestUser.goToHomePage()
                ._NavigationAction()
                .addlogType(TestStepType.WHEN)
                .login(user)
                .addlogType(TestStepType.THEN)
                .logout()
         ;
    }
}
