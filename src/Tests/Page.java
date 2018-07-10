package com.shc.msp.ft.pages;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.automation.Action;
import com.shc.automation.FrameworkProperties;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.PageAssert;
import com.shc.automation.utils.TestContext;
import com.shc.msp.ft.factory.SiteFactory;


public abstract class Page {

    public SiteFactory factory;

    public Page(SiteFactory factory) {
        String pageName = pageName();
        getContext().put("PageName", pageName);
        this.factory = factory;
    }

    protected abstract String pageName();

    public TestContext getContext() {
        return TestContext.get();
    }

    public Action getAction() {
        return getContext().getAction();
    }

    public By getWebElement(Locator locator) {
		By typeBy = null;
		switch (locator.getLocatorType()) {
		case 1:
			typeBy = By.id(locator.getValue());
			break;
		case 2:
			typeBy = By.name(locator.getValue());
			break;
		case 3:
			typeBy = By.linkText(locator.getValue());
			break;
		case 4:
			typeBy = By.className(locator.getValue());
			break;
		case 5:
			typeBy = By.cssSelector(locator.getValue());
			break;
		case 6:
			typeBy = By.partialLinkText(locator.getValue());
			break;
		default:
			typeBy = By.xpath(locator.getValue());
			break;
		}
		return typeBy;
	}

	public Action isElementPresent(Locator loc){
		getAction().isElementPresent(loc);
		highlight(loc);
		return getContext().getAction();
	}

	public void highlight(Locator loc) {
		By by=getWebElement(loc);
		WebElement element= getAction().driver.findElement(by);
		highlight(element);
	}

	public void highlight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) getAction().driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "border: 3px solid red;");
	}
	
	public void StoringCurrentWindowHandle(){
			try {
			getContext().put("WinhandleBefore", getAction().driver.getWindowHandle());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void SwitchBack() {
		try {
		getAction().waitFor(1000);
		getAction().driver.switchTo().window((String)getContext().get("WinhandleBefore"));
	} catch (Exception e) {
		e.getMessage();}
	
}
	public void SwitchToNewlyOpenedWindow() {
		try {
		getAction().waitFor(1000);
		
		Set<String> windowHandles = getAction().driver.getWindowHandles();
		for(String handle:windowHandles){
			getAction().driver.switchTo().window(handle);
			getAction().waitFor(1000);
		}
		getAction().driver.manage().window().maximize();
		getAction().waitFor(1000);
	} catch (Exception e) {
		e.printStackTrace();}
	
}
	/**
	 * Verifies Current Page Title matches with expectedTitle and Logs Verification
	 * result to report if fail
	 * @return 
	 */
	public boolean verifyTitle(String expectedTitle,int timeout) {
		String actualTitle = "";
		for (int seconds = 0;; seconds++) {
			actualTitle = getAction().getTitle().trim();
			if (seconds >= timeout) {
				Assert.fail("Expected Title: " + expectedTitle + " NOT present." + "Actual Title : " + actualTitle);
				return false;
			}
			if (actualTitle.contains(expectedTitle.trim())) {
				Logger.log("Verify Title: " + expectedTitle, TestStepType.VERIFICATION_STEP);
				return true;
			}
			getAction().waitFor(1000);
		}
		
	}
	
	public boolean verifyTitle(String expectedTitle) {
		return verifyTitle(expectedTitle,10);
	}
	/**
	 * Returns true if expectedTextPattern matches actualText, else return false
	 * 
	 * @param expectedTextPattern
	 * @param actualText
	 * @return
	 */
	public static boolean isTextMatching(String expectedTextPattern, String actualText) {
		Pattern p = Pattern.compile(expectedTextPattern);

		Matcher m;
		m = p.matcher(actualText);
		if (m.find()) {
			return true;
		}
		return false;
	}
	/**
	 * Returns text matching with group in expectedTextPattern
	 * @param expectedTextPattern
	 * @param actualText
	 * @return
	 */
	public static String getMatchingTextGroup(String expectedTextPattern, String actualText) {
		Pattern p = Pattern.compile(expectedTextPattern);

		Matcher m;
		m = p.matcher(actualText);
		if (m.find()) {
			String text;
			try {
				text = m.group(1);
			} catch (Exception e) {
				return "";
			}
			return text;
		}
		return "";
	}
	public String getDataString(String key) {
		Object value = getData(key);
		if(value!=null){
			return value.toString();
		}else{
			return "";
		}
	}

	public Object getData(String key) {
		return getContext().get(key);
	}

	public void setData(String key,Object value) {
		getContext().put(key,value);
	}
	
	public void check(Locator locator) {
		if(!getAction().isChecked(locator)){
			getAction().click(locator);
			getAction().waitFor(1000);
		}
	}
	public void scrollDown() {
		try {
			long windowHeight = (Long) getAction().executeScriptAndReturnValue("return window.screen.availHeight");
			getAction().executeScript("window.scrollBy(0," + windowHeight + ");");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void clickJIE11(Locator locator) {
		if(FrameworkProperties.isIE()){
			getAction().executeScript("arguments[0].click();",locator);
		}else{
			getAction().click(locator);
		}		
	}

	public void clickJ(Locator locator) {
		getAction().executeScript("arguments[0].click();", locator);
	}

}