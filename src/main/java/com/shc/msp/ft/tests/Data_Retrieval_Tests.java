package com.shc.msp.ft.tests;

import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;

public class Data_Retrieval_Tests extends BaseTests {
	
	//Order Level Testing Data
	@Test( description = "Receival Sales Tax Adjustment data", enabled = true)
       	public void retrieval_Sales_Tax_Adjustments_Data() throws Exception{
		    	
           LogFormatterAction.beginSetup();
	           As.guestUser.goToHomePage()
	           ._OrderDetailsAction()
               	.sales_Tax_Adjustment_Data();				
   	}	   	
	
	
	
	@Test( description = "Receival Shipping Adjustments ddata", enabled = true)
   	public void retrieval_Shipping_Adjustment_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._OrderDetailsAction()
           	.shipping_Adjustment_Data();				
	}
	
	
	@Test( description = "Receival Release_Order_Data", enabled = true)
   	public void retrieval_Release_Order_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._OrderDetailsAction()
             .release_Order_Data();				
	}	
	
	
	@Test( description = "Receival Contact Customer Data", enabled = true)
   	public void retrieval_ContactCustomer_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._OrderDetailsAction()
             .contactCustomer_Data();				
	}	
	
	
	@Test( description = "Receival Cancellation Data", enabled = true)
   	public void retrieval_Cancellation_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._OrderDetailsAction()
             .cancellation_Data();				
	}	

	
	@Test( description = "Receival Resend Order Confirmation Data", enabled = true)
   	public void retrieval_Resend_Order_Confirmation_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._OrderDetailsAction()
             .resend_Order_Confirmation_Data();				
	}	
	
	//Sales Check Level Testing Data
	
	@Test( description = "Release Sales Check Data", enabled = true)
   	public void retrieval_Release_Sales_Check_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._SalesCheckDetailsAction()
             .release_Sales_Check_Data();				
	}	
	
	@Test( description = "Update Sales Check Data", enabled = true)
   	public void update_Sales_Check_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._SalesCheckDetailsAction()
             .update_Sales_Check_Data();				
	}	
	
	@Test( description = "Contact Customer on Sales Check Level Data", enabled = true)
   	public void contact_Customer_Sales_Check_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._SalesCheckDetailsAction()
             .contact_Customer_Sales_Check_Data();				
	}	

	@Test( description = "Cancellation on Sales Check Level Data", enabled = true)
   	public void cancellation_Sales_Check_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._SalesCheckDetailsAction()
             .cancle_Sales_Check_Data();				
	}	
	
	@Test( description = "Ready for Pickup Email Data", enabled = true)
   	public void ready_for_Pickup_Email_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._SalesCheckDetailsAction()
             .ready_for_Pickup_Email_Data();				
	}	
	
	//Line Item Level Testing Data
	
	@Test( description = "sale adjustment Data", enabled = true)
   	public void line_Item_Sales_Adjustment_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._LineItemDetailsAction()
             .sale_Adjustment_Line_Item_Data();				
	}	
	
	@Test( description = "contact_Marketplace_Seller Data", enabled = true)
   	public void contact_Marketplace_Seller_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._LineItemDetailsAction()
             .contact_Marketplace_Seller_Data();				
	}	
	
	@Test( description = "contact_Vendor Data", enabled = true)
   	public void contact_Vendor_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._LineItemDetailsAction()
             .contact_Vendor_Data();				
	}	
	
	@Test( description = "update_Expected_Ship_Arrival_Date Data", enabled = true)
   	public void update_Expected_Ship_Arrival_Date_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._LineItemDetailsAction()
           .update_Expected_Ship_Arrival_Date_Data();				
	}	
	
	@Test( description = "return_Tracking_Information Data", enabled = true)
   	public void return_Tracking_Information_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._LineItemDetailsAction()
           .return_Tracking_Information_Data();				
	}	
	
	@Test( description = "contact_Customer Data", enabled = true)
   	public void contact_Customer_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._LineItemDetailsAction()
           .contact_Customer_Data();				
	}	
	
	@Test( description = "Cancellation Data", enabled = true)
   	public void Line_Item_Cancellation_Data()throws Exception{
    	
        LogFormatterAction.beginSetup();
            As.guestUser.goToHomePage()
            ._LineItemDetailsAction()
            .Line_Item_Cancellation_Data();				
 	}	
	
	@Test( description = "reschedule Delivery Data", enabled = true)
   	public void reschedule_Delivery_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._LineItemDetailsAction()
           .reschedule_Delivery_Data();				
	}	
	
	@Test( description = "reschedule Delivery Data", enabled = true)
   	public void schedule_Return_Data() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._LineItemDetailsAction()
           .schedule_Return_Data();				
	}
	
	@Test( description = "Receival Return Item Data", enabled = true)
   	public void Line_Item_Return_Item_Data() throws Exception{
    	
	       LogFormatterAction.beginSetup();
	           As.guestUser.goToHomePage()
	           ._LineItemDetailsAction()
	           .Line_Item_Return_Item_Data();				
		}	
	
	@Test( description = "Start Automated Return Item Data", enabled = true)
   	public void Line_Item_Start_Automated_Return_Data() throws Exception{
    	
	       LogFormatterAction.beginSetup();
	           As.guestUser.goToHomePage()
	           ._LineItemDetailsAction()
	           .Line_Item_Start_Automated_Return_Data();				
		}	
	
	@Test( description = "Receival Cancellation Data", enabled = true)
   	public void Adjustment_OrderID() throws Exception{
	    	
       LogFormatterAction.beginSetup();
           As.guestUser.goToHomePage()
           ._OrderDetailsAction()
             .Adjustment_OrderID();				
	}
	
	
}
