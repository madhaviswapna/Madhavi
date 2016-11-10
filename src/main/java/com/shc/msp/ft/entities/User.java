package com.shc.msp.ft.entities;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Reporter;

import com.shc.automation.FrameworkProperties;
import com.shc.automation.Logger;
import com.shc.automation.utils.DataNotFoundException;
import com.shc.automation.utils.DataReader;
import com.shc.automation.utils.TestContext;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.NavigationAction;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.util.UserPool;


public class User {

	SiteFactory factory = new SiteFactory();
    public String userName="testonline0001";
    public String password="TestPassword";

    public NavigationAction goToHomePage() {
        this.factory.homePage().maximizeWindow();
        openBaseUrl();
        return this.factory.navigationAction();
    }

    public void openBaseUrl(){
    	Logger.log("Opening baseurl <a href=\""+FrameworkProperties.SELENIUM_BASE_URL+"\" target=_blank>"+FrameworkProperties.SELENIUM_BASE_URL+"</a>", TestStepType.DATA_CAPTURE);
    	TestContext.get().getWebDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        try {
			TestContext.get().getWebDriver().get(FrameworkProperties.SELENIUM_BASE_URL);
		} catch (Exception e) {
			Logger.log("Opening baseurl <a href=\""+FrameworkProperties.SELENIUM_BASE_URL+"\" target=_blank>"+FrameworkProperties.SELENIUM_BASE_URL+"</a>", TestStepType.DATA_CAPTURE);
	    	TestContext.get().getWebDriver().get(FrameworkProperties.SELENIUM_BASE_URL);
		}
        
    }
    /**
     * Method to find user from user yaml file for given  key
     * @param <U>
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
       public static <U> U find(String key) {
        Map<String, User> userMap = new DataReader<User>().readMap("userDetails");
        U user = (U) userMap.get(key);
        if (user == null) {
            throw new DataNotFoundException("User: " + key + " not Found!");
        }
    
        return user;
    }
}