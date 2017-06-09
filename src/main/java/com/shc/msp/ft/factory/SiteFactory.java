package com.shc.msp.ft.factory;

import com.shc.msp.ft.actions.LineItemDetailsAction;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.actions.NavigationAction;
import com.shc.msp.ft.actions.OrderDetailsAction;
import com.shc.msp.ft.actions.SalesCheckDetailsAction;
import com.shc.msp.ft.modules.ActionDropdown;
import com.shc.msp.ft.modules.EmailTemplatePopUp;
import com.shc.msp.ft.modules.ReturnTrackingInformationPopUp;
import com.shc.msp.ft.modules.ScheduleReturnPopUp;
import com.shc.msp.ft.modules.UpdateExpectedShipArrivalDatePopUp;
import com.shc.msp.ft.pages.AdminPage;
import com.shc.msp.ft.pages.HomePage;
import com.shc.msp.ft.pages.LineItemDetailsPage;
import com.shc.msp.ft.pages.OrderDetailsPage;
import com.shc.msp.ft.pages.ProfilePage;
import com.shc.msp.ft.pages.SalesCheckDetailsPage;
import com.shc.msp.ft.pages.VendorDetailsPage;


public class SiteFactory {

	
    public HomePage homePage() {
        return new HomePage(this);
    }

    public OrderDetailsPage orderdetailspage() {
        return new OrderDetailsPage(this);
    }
    
    public LineItemDetailsPage lineItemDetailsPage() {
        return new LineItemDetailsPage(this);
    }
    
    public SalesCheckDetailsPage salesCheckDetailsPage() {
        return new SalesCheckDetailsPage(this);
    }
    
    public VendorDetailsPage vendorDetailsPage() {
        return new VendorDetailsPage(this);
    }
    
    public EmailTemplatePopUp emailTemplatePopUp() {
        return new EmailTemplatePopUp(this);
    }
    
    public UpdateExpectedShipArrivalDatePopUp updateExpectedShipArrivalDatePopUp() {
        return new UpdateExpectedShipArrivalDatePopUp(this);
    }
    
    public ReturnTrackingInformationPopUp returnTrackingInformationPopUp() {
        return new ReturnTrackingInformationPopUp(this);
    }
    
    public ScheduleReturnPopUp scheduleReturnPopUp() {
        return new ScheduleReturnPopUp(this);
    }
    
    public ActionDropdown actionDropdown() {
        return new ActionDropdown(this);
    }

    public NavigationAction navigationAction() {
        return new NavigationAction(this);
    }

    public OrderDetailsAction orderDetailsAction() {
        return new OrderDetailsAction(this);
    }
    
    public LineItemDetailsAction lineItemDetailsAction() {
        return new LineItemDetailsAction(this);
    }
    
    public SalesCheckDetailsAction salesCheckDetailsAction() {
        return new SalesCheckDetailsAction(this);
    }

    public LogFormatterAction logFormatterAction() {
        return new LogFormatterAction(this);
    }
    
    public ProfilePage profilePage(){
    	return new ProfilePage(this);
    }
    
    public AdminPage adminPage(){
    	return new AdminPage(this);
    }
}