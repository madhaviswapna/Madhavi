package com.shc.msp.ft.actions;

import com.shc.automation.Logger;
import com.shc.automation.utils.TestUtils;
import com.shc.automation.utils.TestUtils.Feature;
import com.shc.msp.ft.factory.SiteFactory;

public class AdminAction extends BaseAction {

	public AdminAction(SiteFactory factory) {
        super(factory);
    }

    @Override
	public AdminAction addlogType(TestUtils.TestStepType testStepType)
	{
		super.addlogType(testStepType);
		return this;
	}
    
	@Override
	protected Feature feature() {
		// TODO Auto-generated method stub
		return Feature.BROWSE;
	}
	
	public AdminAction searchUserAndClickOnUserID(String user){
		Logger.log("Search for Agent and click on the User ID"+ user, TestUtils.TestStepType.GIVEN);
		 this.factory.adminPage().searchUserAndClickOnUserID(user);
		 return this;
	}
	
	public AdminAction turnVacationModeOff(){
		Logger.log("Turn Vacation Mode OFF", TestUtils.TestStepType.WHEN);
		 this.factory.adminPage().turnVacationModeOff();
		 return this;
	}
	
	public AdminAction turnVacationModeOn(){
		Logger.log("Turn Vacation Mode ON", TestUtils.TestStepType.WHEN);
		 this.factory.adminPage().turnVacationModeOn();
		 return this;
	}
	
	public AdminAction changeAgentQueue(String queue){
		Logger.log("Change Agent Queue", TestUtils.TestStepType.THEN);
		this.factory.adminPage().changeAgentQueue(queue);
		return this;
	}
	
	public AdminAction clickUpdateButton(){
		Logger.log("Click Update Button", TestUtils.TestStepType.THEN);
		this.factory.adminPage().clickUpdateButton();
		return this;
		
	}
	public AdminAction resetQueueFromAgentProfile(String queue){
		Logger.log("Reset Agent Queue", TestUtils.TestStepType.THEN);
		this.factory.adminPage().resetQueueFromAgentProfile(queue);
		return this;
	}
	public AdminAction deactivateUser(){
		Logger.log("Deactivate User", TestUtils.TestStepType.THEN);
		this.factory.adminPage().deactivateUser();
		return this;
	}
	public AdminAction activateUser(){
		Logger.log("Activate User", TestUtils.TestStepType.THEN);
		this.factory.adminPage().activateUser();
		return this;
	}
}
