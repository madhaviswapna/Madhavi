package com.shc.msp.ft.pages;

import com.shc.automation.AjaxCondition;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;

public class AdminPage extends Page{

	public AdminPage(SiteFactory factory) {
		super(factory);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String pageName() {
		return "Admin Page";
	}
	
	public final Locator USER_SEARCH = new Locator("", "//h3[contains(text(),'User Search')]","User search");
	public final Locator USER_ID = new Locator("", "//input[@id='userId']","User id");
	public final Locator USER_ID_LINK = new Locator("", "//td[contains(@data-title-text,'User Id')]//a","User ID LINK");
	public final Locator AGENT_CHECKBOX = new Locator("", "//input[@id='{0}']","AGENT_CHECKBOX");
	public final Locator UPDATE_BUTTON = new Locator("", "//button[contains(text(),'Update')]","UPDATE_BUTTON");
	public final Locator UPDATE_MESSAGE = new Locator("", "//div[contains(text(),'Update user Action has been successfully processed')]","UPDATE_MESSAGE");
	public final Locator UPDATE_MESSAGE_OK = new Locator("", "//button[contains(text(),'OK')]","UPDATE_MESSAGE_OK");
	public final Locator SEARCH = new Locator("SEARCH", "//button[contains(text(),'Search')]", "SEARCH");
	
	
	public AdminPage roleAssignmentUser(String user,String role){
		Logger.log("Search for user "+user+"and click on Search" , TestStepType.STEP);
		getAction().waitFor(4000);
		AjaxCondition.forElementVisible(USER_SEARCH).waitForResponse();
		
		AjaxCondition.forElementVisible(USER_ID).waitForResponse();
		getAction().type(USER_ID, user);
		AjaxCondition.forElementVisible(SEARCH).waitForResponse();
		getAction().click(SEARCH);
		
		AjaxCondition.forElementVisible(USER_ID_LINK).waitForResponse();
		getAction().click(USER_ID_LINK);
		getAction().scrollTo(AGENT_CHECKBOX.format(role));
		getAction().waitFor(3000);
		System.out.println("*************************  "+!getAction().isChecked(AGENT_CHECKBOX.format(role)));
		String action = !getAction().isChecked(AGENT_CHECKBOX.format(role))?"Assign":"Remove";
		System.out.println(action+" "+role+" in the agent profile");
		Logger.log(action+" "+role+" in the agent profile" , TestStepType.STEP);
		AjaxCondition.forElementVisible(AGENT_CHECKBOX.format(role)).waitForResponse();
		getAction().click(AGENT_CHECKBOX.format(role));
		AjaxCondition.forElementVisible(UPDATE_BUTTON).waitForResponse();
		getAction().scrollTo(UPDATE_BUTTON);
		getAction().click(UPDATE_BUTTON);
		getContext().put("Check", false);
		AjaxCondition.forElementVisible(UPDATE_MESSAGE).waitForResponse();
		AjaxCondition.forElementVisible(UPDATE_MESSAGE_OK).waitForResponse();
		getAction().click(UPDATE_MESSAGE_OK);
		getAction().waitFor(2000);
		return this;
	}
	
	public AdminPage roleRemovalUser(String user,String role){
		roleAssignmentUser(user,role);
		return this;
	}
}
