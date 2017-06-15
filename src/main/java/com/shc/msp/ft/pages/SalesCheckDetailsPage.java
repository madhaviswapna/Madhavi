package com.shc.msp.ft.pages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.shc.automation.AjaxCondition;
import com.shc.automation.FrameworkProperties;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.PageAssert;
import com.shc.automation.SoftAssert;
import com.shc.automation.utils.TestUtils.CheckLocatorFor;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.util.MysqlDBConnection;
import com.shc.msp.ft.util.SOAPRequest;


public class SalesCheckDetailsPage extends Page {

	public SalesCheckDetailsPage(SiteFactory factory) {
		super(factory);
	}

	protected String getPageName() {
		return "Sales Check Details Page";
	}

	protected String pageName() {
		return "Sales Check Details Page";
	}

	public static final Locator STATUS= new Locator("STATUS", "//tr[@ng-repeat='salesCheckLineItem in selectedSalesCheck.lineItem']/td[1]", "Status");
	public static final Locator UPDATE_SALES_CHECK_POPUP= new Locator("Update sales check popup", "//h4[contains(text(),'Update Sale Check')]", "Update sales check popup");
	public static final Locator RINGING_Id_TEXTBOX= new Locator("Ringing id text box", "//input[@ng-model='selectedSalesCheck.ringId']", "Ringing id text box");
	public static final Locator RC_CODE_TEXTBOX= new Locator("RC_CODE_TEXTBOX", "//input[@ng-model='selectedSalesCheck.refundValidationNumber']", "rc code text box");
	public static final Locator SALES_CHECK_Id_TEXTBOX= new Locator("Ringing id text box", "//input[@ng-model='selectedSalesCheck.salesCheckNumber']", "Sales check id text box");
	public static final Locator RELEASE_SALES_CHECK_POPUP= new Locator("Release sales check popup", "//h4[contains(text(),'Release Sales Check')]", "Release sales check popup");
	public static final Locator OK_POPUp= new Locator("sucess popup ok button", "//button[contains(text(),'OK')]", "sucess popup ok button");
	public static final Locator HEADER_SALES_CHECK_SUMMARY = new Locator("HEADER_SALES_CHECK_SUMMARY","//div[@id='lineItemSalesCheckSection']//div[@id='salesCheckDiv']//h5","Header Sales Check Summary");
	public static final Locator ACTION_SELECT_BOX = new Locator("", "//div[@ng-if='actions']//select", "Select action drop down");

	//SALES CHECK SUMMARY :

	public static final Locator SALES_CHECK_SUMMARY_TITLE = new Locator("SALES_CHECK_SUMMARY_TITLE", "//legend[text()='Sales Check Summary']", "Sales Check Summary Title");
	public static final Locator SALES_CHECK_SUMMARY_TABLE = new Locator("SALES_CHECK_SUMMARY_TABLE", "//legend[text()='Sales Check Summary']/following-sibling::form", "Sales Check Summary Table");
	public static final Locator SUBTOTAL_TEXT =new Locator("SALES_CHECK_SUMMARY_TITLE","//label[text()='Subtotal']","Subtotal Text");   
	public static final Locator TOTAL_SHIPPING_TEXT =new Locator("TOTAL_SHIPPING_TEXT","//label[text()='Total Shipping']","Total Shipping Text");
	public static final Locator SURCHARGE_TEXT =new Locator("SURCHARGE_TEXT","//label[text()='Surcharge']","Surcharge Text");
	public static final Locator TAX_PERCENT_TEXT =new Locator("TAX_PERCENT_TEXT","//label[text()='Tax Percent']","Tax Percent Text");
	public static final Locator TAX_TEXT =new Locator("TAX_TEXT","//label[text()='Tax']","Tax Text");
	public static final Locator TOTAL_TEXT =new Locator("TOTAL_TEXT","//label[text()='Total']","Total Text");
	public static final Locator SUBTOTAL_CONTENT =new Locator("SUBTOTAL_CONTENT","//label[text()='Subtotal']/following-sibling::div/p","Subtotal Detail");
	public static final Locator TOTAL_SHIPPING_CONTENT =new Locator("TOTAL_SHIPPING_CONTENT","//label[text()='Total Shipping']/following-sibling::div/p","Total Shipping Detail");
	public static final Locator SURCHARGE_CONTENT =new Locator("SURCHARGE_CONTENT","//label[text()='Surcharge']/following-sibling::div/p","Surcharge Detail");
	public static final Locator TAX_PERCENT_CONTENT =new Locator("TAX_PERCENT_CONTENT","//label[text()='Tax Percent']/following-sibling::div/p","Tax Percent Detail");
	public static final Locator TAX_CONTENT =new Locator("TAX_CONTENT","//label[text()='Tax']/following-sibling::div/p","Tax Detail");
	public static final Locator TOTAL_CONTENT =new Locator("TOTAL_CONTENT","//label[text()='Total']/following-sibling::div/p","Total Detail");

	//SALES CHECK DETAILS
	public static final Locator SALES_CHECK_DETAILS_TITLE = new Locator("SALES_CHECK_DETAILS_TITLE", "//legend[text()='Sales Check Details']", "Sales Check Details Title");
	public static final Locator SALES_CHECK_DETAILS_TABLE = new Locator("SALES_CHECK_DETAILS_TABLE", "//legend[text()='Sales Check Details']/following-sibling::form", "Sales Check Details Table");
	public static final Locator SALES_CHECK_DETAILS_RINGING_ID_TEXT = new Locator("SALES_CHECK_DETAILS_RINGING_ID_TEXT", "//legend[text()='Sales Check Details']/following-sibling::form//label[text()='Ringing Id']", "Sales Check Details Ringing Id Detail Text");
	public static final Locator SALES_CHECK_DETAILS_SC_NO_TEXT = new Locator("SALES_CHECK_DETAILS_SC_NO_TEXT", "//legend[text()='Sales Check Details']/following-sibling::form//label[text()='Sales Check #']", "Sales Check Details Sales Check # Detail Text");
	public static final Locator SALES_CHECK_DETAILS_RC_CODE_TEXT = new Locator("SALES_CHECK_DETAILS_RC_CODE", "//legend[text()='Sales Check Details']/following-sibling::form//label[text()='RC Code']", "Sales Check Details RC Code Detail Text");
	public static final Locator SALES_CHECK_DETAILS_RINGING_ID = new Locator("SALES_CHECK_DETAILS_RINGING_ID", "//legend[text()='Sales Check Details']/following-sibling::form//label[text()='Ringing Id']/following-sibling::div/p", "Sales Check Details Ringing Id Detail");
	public static final Locator SALES_CHECK_DETAILS_SC_NO = new Locator("SALES_CHECK_DETAILS_SC_NO", "//legend[text()='Sales Check Details']/following-sibling::form//label[text()='Sales Check #']/following-sibling::div/p", "Sales Check Details Sales Check # Detail");
	public static final Locator SALES_CHECK_DETAILS_RC_CODE = new Locator("SALES_CHECK_DETAILS_RC_CODE", "//legend[text()='Sales Check Details']/following-sibling::form//label[text()='RC Code']/following-sibling::div/p", "Sales Check Details RC Code Detail");

	// STORE POS TO WEB 
	public static final Locator STORE_POS_TO_WEB_TITLE = new Locator("STORE_POS_TO_WEB_TITLE", "//legend[text()='Store POS to Web']", "Store POS to Web Title");
	public static final Locator STORE_POS_TO_WEB_TABLE = new Locator("STORE_POS_TO_WEB_TABLE", "//legend[text()='Store POS to Web']/following-sibling::form", "Store POS to Web Table");
	public static final Locator STORE_POS_TO_WEB_DETAIL_TEXT = new Locator("STORE_POS_TO_WEB_DETAIL_TEXT", "//legend[text()='Store POS to Web']/following-sibling::form//label[text()='Payment Sales Check:']", "Store POS to Web Detail Text");
	public static final Locator STORE_POS_TO_WEB_DETAIL = new Locator("STORE_POS_TO_WEB_DETAIL", "//legend[text()='Store POS to Web']/following-sibling::form//label[text()='Payment Sales Check:']/following-sibling::div/p", "Store POS to Web Detail");

	//SHIPPING ADDRESS 
	public static final Locator SHIPPING_ADDRESS_TITLE = new Locator("SHIPPING_ADDRESS_TITLE", "//legend[text()='Shipping Address']", "Shipping Address Title");
	public static final Locator SHIPPING_ADDRESS_TABLE = new Locator("SHIPPING_ADDRESS_TABLE", "//legend[text()='Shipping Address']/following-sibling::form", "Shipping Address Table");
	public static final Locator SHIPPING_NICKNAME_TEXT = new Locator("SHIPPING_NICKNAME_TEXT", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Nickname']", "Nickname Text");
	public static final Locator SHIPPING_NAME_TEXT = new Locator("SHIPPING_NAME_TEXT ", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Name']", "Name Text");
	public static final Locator SHIPPING_ADDRESS1_TEXT = new Locator("SHIPPING_ADDRESS1_TEXT", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Address 1']", "Address 1 Text");
	public static final Locator SHIPPING_ADDRESS2_TEXT = new Locator("SHIPPING_ADDRESS2_TEXT ", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Address 2']", "Address 2 Text");
	public static final Locator SHIPPING_CITY_TEXT = new Locator("SHIPPING_CITY_TEXT ", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='City']", "City Text");
	public static final Locator SHIPPING_STATE_TEXT = new Locator("SHIPPING_STATE_TEXT", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='State']", "State Text");
	public static final Locator SHIPPING_ZIP_TEXT = new Locator("SHIPPING_ZIP_TEXT", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Zip']", "Zip Text");
	public static final Locator SHIPPING_COUNTRY_TEXT = new Locator("SHIPPING_COUNTRY_TEXT", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Country']", "Country Text");
	public static final Locator SHIPPING_STORE_PHONE_NO_TEXT = new Locator("SHIPPING_STORE_PHONE_NO_TEXT", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Store Phone #']", "Store Phone # Text");
	public static final Locator SHIPPING_NICKNAME_DETAIL = new Locator("SHIPPING_NICKNAME_DETAIL", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Nickname']/following-sibling::div/p", "Nickname Detail");
	public static final Locator SHIPPING_NAME_DETAIL = new Locator("SHIPPING_NAME_DETAIL", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Name']/following-sibling::div/p", "Name Detail");
	public static final Locator SHIPPING_ADDRESS1_DETAIL = new Locator("SHIPPING_ADDRESS1_DETAIL", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Address 1']/following-sibling::div/p", "Address 1 Detail");
	public static final Locator SHIPPING_ADDRESS2_DETAIL = new Locator("SHIPPING_ADDRESS2_DETAIL", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Address 2']/following-sibling::div/p", "Address 2 Detail");
	public static final Locator SHIPPING_CITY_DETAIL = new Locator("SHIPPING_CITY_DETAIL", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='City']/following-sibling::div/p", "City Detail");
	public static final Locator SHIPPING_STATE_DETAIL = new Locator("SHIPPING_STATE_DETAIL", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='State']/following-sibling::div/p", "State Detail");
	public static final Locator SHIPPING_ZIP_DETAIL = new Locator("SHIPPING_ZIP_DETAIL", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Zip']/following-sibling::div/p", "Zip Detail");
	public static final Locator SHIPPING_COUNTRY_DETAIL = new Locator("SHIPPING_COUNTRY_DETAIL", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Country']/following-sibling::div/p", "Country Detail");
	public static final Locator SHIPPING_STORE_PHONE_NO_DETAIL = new Locator("SHIPPING_STORE_PHONE_NO_DETAIL", "//legend[text()='Shipping Address']/following-sibling::form//label[text()='Store Phone #']/following-sibling::div/p", "Store Phone # Detail");

	//CONTACT INFORMATION 
	public static final Locator CONTACT_INFORMATION_TITLE = new Locator("CONTACT_INFORMATION_TITLE", "//legend[text()='Contact Information']", "Contact Information Title");
	public static final Locator CONTACT_INFORMATION_TABLE = new Locator("CONTACT_INFORMATION_TABLE", "//legend[text()='Contact Information']/following-sibling::form", "Contact Information Table");
	public static final Locator CONTACT_INFORMATION_EMAIL_TEXT = new Locator("CONTACT_INFORMATION_EMAIL_TEXT", "//legend[text()='Contact Information']/following-sibling::form//label[text()='Email']", "Contact Information Email Text");
	public static final Locator CONTACT_INFORMATION_DPHONE_TEXT = new Locator("CONTACT_INFORMATION_DPHONE_TEXT", "//legend[text()='Contact Information']/following-sibling::form//label[text()='Daytime Phone']", "Contact Information Daytime Phone Text");
	public static final Locator CONTACT_INFORMATION_EPHONE_TEXT = new Locator("CCONTACT_INFORMATION_EPHONE_TEXT", "//legend[text()='Contact Information']/following-sibling::form//label[text()='Evening Phone']", "Contact Information Evening Phone Text");
	public static final Locator CONTACT_INFORMATION_EMAIL_DETAIL = new Locator("CONTACT_INFORMATION_EMAIL_DETAIL", "//legend[text()='Contact Information']/following-sibling::form//label[text()='Email']/following-sibling::div/p", "Contact Information 'Email Detail");
	public static final Locator CONTACT_INFORMATION_DPHONE_DETAIL = new Locator("CONTACT_INFORMATION_DPHONE_DETAIL", "//legend[text()='Contact Information']/following-sibling::form//label[text()='Daytime Phone']/following-sibling::div/p", "Contact Information Daytime Phone Detail");
	public static final Locator CONTACT_INFORMATION_EPHONE_DETAIL = new Locator("CONTACT_INFORMATION_EPHONE_DETAIL", "//legend[text()='Contact Information']/following-sibling::form//label[text()='Evening Phone']/following-sibling::div/p", "Contact Information Evening Phone Detail");

	//Return Gift Card Information
	public static final Locator RETURN_GIFT_CARD_INFO_TITLE = new Locator("RETURN_GIFT_CARD_INFO_TITLE", "//legend[text()='Return Gift Card Information']", "Return Gift Card Information Title");
	public static final Locator RETURN_GIFT_CARD_INFO_TABLE = new Locator("RETURN_GIFT_CARD_INFO_TABLE", "//legend[text()='Return Gift Card Information']/following-sibling::div[@ng-if='selectedSalesCheck.gcinfo']//table", "Return Gift Card Information Table");
	public static final Locator RETURN_GIFT_CARD_INFO_SERIAL_NO_TEXT = new Locator("RETURN_GIFT_CARD_INFO_SERIAL_NO_TEXT", "((//legend[text()='Return Gift Card Information']/following-sibling::div[@ng-if='selectedSalesCheck.gcinfo']//table//tr)[1]//th)[1]", "Return Gift Card Information Serial # Text");
	public static final Locator RETURN_GIFT_CARD_INFO_CARD_NUMBER_TEXT = new Locator("RETURN_GIFT_CARD_INFO_CARD_NUMBER_TEXT", "((//legend[text()='Return Gift Card Information']/following-sibling::div[@ng-if='selectedSalesCheck.gcinfo']//table//tr)[1]//th)[2]", "Return Gift Card Information Card Number Text");
	public static final Locator RETURN_GIFT_CARD_INFO_AMOUNT_TEXT = new Locator("RETURN_GIFT_CARD_INFO_AMOUNT_TEXT", "((//legend[text()='Return Gift Card Information']/following-sibling::div[@ng-if='selectedSalesCheck.gcinfo']//table//tr)[1]//th)[3]", "Return Gift Card Information Amount Text");
	public static final Locator RETURN_GIFT_CARD_INFO_TABLE_CONTENT = new Locator("RETURN_GIFT_CARD_INFO_TABLE_CONTENT", "//legend[text()='Return Gift Card Information']/following-sibling::div[@ng-if='selectedSalesCheck.gcinfo']//table//tbody", "Return Gift Card Information Table Content");
	public static final Locator RETURN_GIFT_CARD_INFO_NO_RESPONSE = new Locator("RETURN_GIFT_CARD_INFO_NO_RESPONSE", "//span[text()='No return gift card information found']","");

	//Discounts
	public static final Locator DISCOUNTS_TITLE = new Locator("DISCOUNTS_TITLE", "//legend[text()='Discounts']", "Discounts Title");
	public static final Locator DISCOUNTS_TABLE = new Locator("DISCOUNTS_TABLE", "//legend[text()='Discounts']/following-sibling::div[@ng-if='selectedSalesCheck.discounts']", "Discounts Table");
	public static final Locator DISCOUNTS_TABLE_CONTENT = new Locator("DISCOUNTS_TABLE_CONTENT", "//legend[text()='Discounts']/following-sibling::div[@ng-if='selectedSalesCheck.discounts']//tbody","Discounts Table Content");
	public static final Locator DESCRIPTION_TEXT =new Locator("DESCRIPTION_TEXT","//legend[text()='Discounts']/following-sibling::div[@ng-if='selectedSalesCheck.discounts']//thead/tr/th[1]","Discount Description Text");
	public static final Locator AMOUNT_TEXT =new Locator("AMOUNT_TEXT","//legend[text()='Discounts']/following-sibling::div[@ng-if='selectedSalesCheck.discounts']//thead/tr/th[2]","Amount Text");
	//public static final Locator DESCRIPTION_DETAIL =new Locator("DESCRIPTION_DETAIL","//legend[text()='Discounts']/following-sibling::div[@ng-if='selectedSalesCheck.discounts']//tbody/tr/td[1]","Discount Description Detail");
	//public static final Locator AMOUNT_DETAIL =new Locator("AMOUNT_DETAIL","//legend[text()='Discounts']/following-sibling::div[@ng-if='selectedSalesCheck.discounts']//tbody/tr/td[2]","Amount Detail");
	public static final Locator DISCOUNTS_NO_RESPONSE = new Locator("DISCOUNTS_NO_RESPONSE","//span[text()='No discount records found']","No discount records found");
	//Delivery Details
	public static final Locator DELIVERY_DETAIL_TITLE = new Locator("DELIVERY_DETAIL_TITLE", "//legend[text()='Delivery Details']", "Delivery Details Title");

	//Payments
	public static final Locator PAYMENTS_TITLE = new Locator("PAYMENTS_TITLE", "//legend[text()='Payments']", "Payments Title");
	public static final Locator PAYMENTS_TABLE = new Locator("PAYMENTS_TABLE", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']/table", "Payments Table");
	public static final Locator PAYMENTS_TABLE_CONTENT = new Locator("PAYMENTS_TABLE_CONTENT", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']//table//tbody", "Payments Table Content");
	public static final Locator PAYMENTS_TYPE_TEXT = new Locator("PAYMENTS_TYPE_TEXT", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']/table/thead/tr/th[1]", "Payments Type Text");
	public static final Locator PAYMENTS_CARD_NUMBER_TEXT = new Locator("PAYMENTS_CARD_NUMBER_TEXT", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']/table/thead/tr/th[2]", "Payments Card Number Text");
	public static final Locator PAYMENTS_AMOUNT_TEXT = new Locator("PAYMENTS_AMOUNT_TEXT", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']/table/thead/tr/th[3]", "Payments Amount Text");
	public static final Locator PAYMENTS_PO_NUMBER_TEXT = new Locator("PAYMENTS_PO_NUMBER_TEXT", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']/table/thead/tr/th[4]", "Payments PO Number Text");
	public static final Locator PAYMENTS_EXPIRATION_DATE_TEXT = new Locator("PAYMENTS_EXPIRATION_DATE_TEXT", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']/table/thead/tr/th[5]", "Payments Expiration Date Text");

	public static final Locator PAYMENTS_TYPE_DETAIL = new Locator("PAYMENTS_TYPE_DETAIL", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']/table/tbody/tr/td[1]", "Payments Type Details");
	public static final Locator PAYMENTS_CARD_NUMBER_DETAIL = new Locator("PAYMENTS_CARD_NUMBER_DETAIL", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']/table/tbody/tr/td[2]", "Payments Card Number Details");
	public static final Locator PAYMENTS_AMOUNT_DETAIL = new Locator("PAYMENTS_AMOUNT_DETAIL", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']/table/tbody/tr/td[3]", "Payments Amount Details");
	public static final Locator PAYMENTS_PO_NUMBER_DETAIL = new Locator("PAYMENTS_PO_NUMBER_DETAIL", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']/table/tbody/tr/td[4]", "Payments PO Number Details");
	public static final Locator PAYMENTS_EXPIRATION_DATE_DETAIL = new Locator("PAYMENTS_EXPIRATION_DATE_DETAIL", "//legend[text()='Payments']/following-sibling::div[@ng-if='selectedSalesCheck.payment']/table/tbody/tr/td[5]", "Payments Expiration Date Details");
	public static final Locator SALES_CHECK_NUMBER_N= new Locator("SALES_CHECK_NUMBER", "//a[@class='spacetwo ng-binding']", "Sales Check Number");
	public static final Locator SALES_CHECK_DETAIL_PAGE= new Locator("SALES_CHECK_DETAIL_PAGE", "//div[@id='salesCheckDiv']", "Sales Check Detail Page");
	//DDC fulfillment 
	public final Locator DELIVERYDETAILS_TEXT = new Locator("DELIVERYDETAILS TEXT","//legend[text()='Delivery Details']","DELIVERYDETAILS TEXT");
	public final Locator DELIVERYDETAILS = new Locator("DELIVERYDETAILS","//div[@ng-if='verifyHomeDelivery()']","DELIVERYDETAILS");
	public final Locator DELIVERYDETAILS_TABS = new Locator("DELIVERYDETAILS TABS","//a[text()='{0}']","DELIVERYDETAILS TABS");
	public final Locator DELIVERYDETAILS_DOS_NUMBER = new Locator("DELIVERYDETAILS DOSNUMBER","//a[@ng-click='switchOrderView($index,order);']","DELIVERYDETAILS DOS NUMBER");
	public final Locator DELIVERYDETAILS_UPDATE_BUTTON = new Locator("DELIVERYDETAILS UPDATE BUTTON","//button[text()='Update']","Update Button");
	public final Locator DELIVERYDETAILS_SPECIFIC_INSTRUCTION_EDIT_BUTTON = new Locator("DELIVERYDETAILS SPECIFIC INSTRUCTION EDIT BUTTON","(//button[text()='Edit'])[1]","Specific Instruction Edit Button");
	public final Locator DELIVERYDETAILS_DELIVERY_INSTRUCTION_EDIT_BUTTON = new Locator("DELIVERYDETAILS DELIVERY INSTRUCTION_EDIT BUTTON","(//button[text()='Edit'])[2]","Delivery Instruction Edit Button");
	Connection conn1 = null;
	PreparedStatement st1 = null;
	ResultSet rs1 = null;
	PreparedStatement st21 = null;
	ResultSet rs21 = null;
	PreparedStatement st31 = null;
	ResultSet rs31= null;
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
	DecimalFormat df = new DecimalFormat("0.00");
	DecimalFormat df1 = new DecimalFormat("0.0");
	DecimalFormat df2 = new DecimalFormat("0");
	DecimalFormat formatter = new DecimalFormat("#,###.00");


	public SalesCheckDetailsPage salesCheckSummaryVerify(String OrderID) {
		getAction().waitFor(2000);
		String saleschecknumberText = getAction().getText(HEADER_SALES_CHECK_SUMMARY);
		String saleschecknumber = saleschecknumberText.split(" ")[saleschecknumberText.split(" ").length-1];
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		ArrayList<String> subtotal = new ArrayList<String>();
		ArrayList<String>  total_shipping = new ArrayList<String>();
		ArrayList<String> surcharge = new ArrayList<String>();
		ArrayList<String>  tax_percent = new ArrayList<String>();
		ArrayList<String>   tax = new ArrayList<String>();
		Double total = null;
		Double surCharge = null;
		String sql_sc_summary ="select SUBTOTAL_AMOUNT,  SHIPPING_AMT, SURCHARGE_AMOUNT, tax_rate,  TAX_AMT from ord_item "
				+ "where sales_check_id in (select sales_check_id from sales_check where SALES_CHECK_NUMBER = ? and order_id =? )";

		try {

			st = conn.prepareStatement(sql_sc_summary);
			st.setString (1, saleschecknumber);
			st.setString(2, OrderID);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){

				subtotal.add(rs.getString("SUBTOTAL_AMOUNT") );
				total_shipping.add(rs.getString("SHIPPING_AMT")) ;
				surcharge.add(rs.getString("SURCHARGE_AMOUNT") );
				tax_percent.add(rs.getString("tax_rate"));
				tax.add(rs.getString("TAX_AMT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Logger.log("Verify Sales Check Summary column is present", TestStepType.STEP);
		PageAssert.textPresent(SALES_CHECK_SUMMARY_TITLE, "Sales Check Summary");
		
		String url=FrameworkProperties.SELENIUM_BASE_URL;
		if(!url.contains("msp.prod.global")){
		if(subtotal.size()!=0 || total_shipping.size()!=0 || surcharge.size()!=0 || tax_percent.size()!=0 ||  tax.size()!=0 
				||  total!=null){
			if(getAction().isVisible(SALES_CHECK_SUMMARY_TABLE)) { 
				/****
				 * Verify Text Present
				 ****/
				
			    SoftAssert.checkConditionAndContinueOnFailure("Subtotal column is Present",
	            		getAction().getText(SUBTOTAL_TEXT).equalsIgnoreCase("Subtotal"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Total Shipping column is Present",
	            		getAction().getText(TOTAL_SHIPPING_TEXT).equalsIgnoreCase("Total Shipping"));
			    
			    
			    SoftAssert.checkConditionAndContinueOnFailure("Surcharge column is Present",
	            		getAction().getText(SURCHARGE_TEXT).equalsIgnoreCase("Surcharge"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Tax Percent column is Present",
	            		getAction().getText(TAX_PERCENT_TEXT).equalsIgnoreCase("Tax Percent"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Tax column is Present",
	            		getAction().getText(TAX_TEXT).equalsIgnoreCase("Tax"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Total column is Present",
	            		getAction().getText(TOTAL_TEXT).equalsIgnoreCase("Total"));

				Double sum_subtotal=0.0  ;
				Double sum_totalshipping=0.0;
				Double sum_tax = 0.0;
				Double sum_surcharge = 0.0;
				for(int i=0; i<subtotal.size();i++){
					sum_subtotal += Double.parseDouble(subtotal.get(i));
					sum_totalshipping +=Double.parseDouble(total_shipping.get(i));
					if(surcharge.get(i) == null){
						surCharge = 0.00;
					}else{
						surCharge = Double.parseDouble(surcharge.get(i));
					}
					sum_surcharge +=surCharge;
					sum_tax += Double.parseDouble(tax.get(i));
					total = sum_subtotal + sum_totalshipping +sum_surcharge + sum_tax;
				}
				
		    	 SoftAssert.checkConditionAndContinueOnFailure("Subtotal amount in database should match with application", 
			    			getAction().getText(SUBTOTAL_CONTENT).equalsIgnoreCase("$"+formatter.format(sum_subtotal)));

		    	 SoftAssert.checkConditionAndContinueOnFailure("Total Shipping in database should match with application", 
			    			getAction().getText(TOTAL_SHIPPING_CONTENT).equalsIgnoreCase("$"+df.format(sum_totalshipping)));
		    	 
			   	 SoftAssert.checkConditionAndContinueOnFailure("Surcharge in database should match with application", 
			    			getAction().getText(SURCHARGE_CONTENT).equalsIgnoreCase("$"+df.format(sum_surcharge)));
				
			   	 SoftAssert.checkConditionAndContinueOnFailure("Tax Percent in database should match with application", 
			    			getAction().getText(TAX_PERCENT_CONTENT).equalsIgnoreCase(df1.format((Double.parseDouble(tax_percent.get(0))*100))+"%"));
				
			   	 SoftAssert.checkConditionAndContinueOnFailure("Tax amount in database should match with application", 
			    			getAction().getText(TAX_CONTENT).equalsIgnoreCase("$"+df.format(sum_tax)));
				
				//Logger.log("Verify Tax calculated ", TestStepType.STEP); // It should calculate on basis of taxable subtotal.
				//PageAssert.verifyEqual(df.format(sum_tax), df.format((sum_subtotal + sum_totalshipping +sum_surcharge)*Double.parseDouble(tax_percent.get(0))));  
			   	 
				Logger.log("Verify Total amount - "+ "$"+formatter.format(total)+" in database matches with application", TestStepType.STEP);
				PageAssert.verifyEqual("$"+formatter.format(total), getAction().getText(TOTAL_CONTENT));
			}
				else{
				PageAssert.fail("Failed to retrieve data for Sales Check Summary");
			}
		}else{
			Logger.log(" There are no Sales Check Summary information in Database", TestStepType.STEP);
		}
		}
		else{
			
			
		    SoftAssert.checkConditionAndContinueOnFailure("Subtotal column is Present",
            		getAction().getText(SUBTOTAL_TEXT).equalsIgnoreCase("Subtotal"));
			
		    SoftAssert.checkConditionAndContinueOnFailure("Total Shipping column is Present",
            		getAction().getText(TOTAL_SHIPPING_TEXT).equalsIgnoreCase("Total Shipping"));
		    
		    
		    SoftAssert.checkConditionAndContinueOnFailure("Surcharge column is Present",
            		getAction().getText(SURCHARGE_TEXT).equalsIgnoreCase("Surcharge"));
			
		    SoftAssert.checkConditionAndContinueOnFailure("Tax Percent column is Present",
            		getAction().getText(TAX_PERCENT_TEXT).equalsIgnoreCase("Tax Percent"));
			
		    SoftAssert.checkConditionAndContinueOnFailure("Tax column is Present",
            		getAction().getText(TAX_TEXT).equalsIgnoreCase("Tax"));
			
		    SoftAssert.checkConditionAndContinueOnFailure("Total column is Present",
            		getAction().getText(TOTAL_TEXT).equalsIgnoreCase("Total"));
		    
		    SoftAssert.checkConditionAndContinueOnFailure("Subtotal amount in database should not be empty", 
	    			!getAction().getText(SUBTOTAL_CONTENT).isEmpty());

    	 SoftAssert.checkConditionAndContinueOnFailure("Total Shipping in database should not be empty", 
	    			!getAction().getText(TOTAL_SHIPPING_CONTENT).isEmpty());
    	 
	   	 SoftAssert.checkConditionAndContinueOnFailure("Surcharge in database should not be empty", 
	    			!getAction().getText(SURCHARGE_CONTENT).isEmpty());
		
	   	 SoftAssert.checkConditionAndContinueOnFailure("Tax Percent in database should not be empty", 
	    			!getAction().getText(TAX_PERCENT_CONTENT).isEmpty());
		
	   	 SoftAssert.checkConditionAndContinueOnFailure("Tax amount in database should not be empty", 
	    			!getAction().getText(TAX_CONTENT).isEmpty());
		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;
	}

	public SalesCheckDetailsPage salesCheckDetailVerify(String orderID) {
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		String ringing_id = null;
		String sc_number = null;
		String rc_code= null;
		getAction().waitFor(2000);
		String saleschecknumberText = getAction().getText(HEADER_SALES_CHECK_SUMMARY);
		String saleschecknumber = saleschecknumberText.split(" ")[saleschecknumberText.split(" ").length-1];
		String sql ="select RINGING_CSR_ID, SALES_CHECK_NUMBER, REFUND_VALIDATION_NUMBER from sales_check "
				+ "where SALES_CHECK_NUMBER = ? and order_id = ? ";
		try {
			st = conn.prepareStatement(sql);
			st.setString (1, saleschecknumber);
			st.setString (2, orderID);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){

				ringing_id = rs.getString("RINGING_CSR_ID") ;
				sc_number = rs.getString("SALES_CHECK_NUMBER") ;
				rc_code = rs.getString("REFUND_VALIDATION_NUMBER") ;
				if(ringing_id==null){
					ringing_id = "";
				}
				if(rc_code==null){
					rc_code = "0";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Logger.log("Verify Sales Check Details column is present", TestStepType.STEP);
		PageAssert.textPresent(SALES_CHECK_DETAILS_TITLE, "Sales Check Details");
		

		if( ringing_id!=null || sc_number!=null || rc_code!=null){

		    SoftAssert.checkConditionAndContinueOnFailure("Ringing Id column is Present",
            		getAction().getText(SALES_CHECK_DETAILS_RINGING_ID_TEXT).equalsIgnoreCase("Ringing Id"));
			
		    SoftAssert.checkConditionAndContinueOnFailure("Sales Check # column is Present",
            		getAction().getText(SALES_CHECK_DETAILS_SC_NO_TEXT).equalsIgnoreCase("Sales Check #"));
			
		    SoftAssert.checkConditionAndContinueOnFailure("RC Code # column is Present",
            		getAction().getText(SALES_CHECK_DETAILS_RC_CODE_TEXT).equalsIgnoreCase("RC Code"));

			if(getAction().isVisible(SALES_CHECK_DETAILS_TABLE)) {
				
			    SoftAssert.checkConditionAndContinueOnFailure("Ringing Id in database matches with application",
	            		getAction().getText(SALES_CHECK_DETAILS_RINGING_ID).equalsIgnoreCase(ringing_id));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Sales Check # in database matches with application",
	            		getAction().getText(SALES_CHECK_DETAILS_SC_NO).equalsIgnoreCase(sc_number));
				
			    SoftAssert.checkConditionAndContinueOnFailure("RC Code in database matches with application",
	            		getAction().getText(SALES_CHECK_DETAILS_RC_CODE).equalsIgnoreCase(rc_code));
				
			}else{
				PageAssert.fail("Failed to retrieve data for Sales Check Detail");
			}
		}else{
			Logger.log(" There is no Sales Check Detail information in Database", TestStepType.STEP);
		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;
	}

	public SalesCheckDetailsPage saraLinkVerify(){
		String url=FrameworkProperties.SELENIUM_BASE_URL;
		String partialsaraLinkPopUpWinUrl = null;
		Logger.log("Sales Check Number is Present in Sales Check Details");
		AjaxCondition.forElementPresent(SALES_CHECK_DETAILS_SC_NO).waitForResponse();
		
		if(url.contains("mspapp301p.dev.ch3.s.com:9680")){
			partialsaraLinkPopUpWinUrl = FrameworkProperties.SELENIUM_BASE_URL+"/saraCheck/getReceiptBySalesCheck?"
					+ "salesCheckId="+getAction().getText(SALES_CHECK_DETAILS_SC_NO);//+"&ordDate="+purchaseDate+"&storeId="+storeId
		}else{
			partialsaraLinkPopUpWinUrl = FrameworkProperties.SELENIUM_BASE_URL+"/saraCheck/getReceiptBySalesCheck?"
					+ "salesCheckId="+getAction().getText(SALES_CHECK_DETAILS_SC_NO);//+"&ordDate="+purchaseDate+"&storeId="+storeId
		}
		if(getAction().findElement(SALES_CHECK_DETAILS_SC_NO).isEnabled()){
			getAction().waitFor(1000);
			Logger.log("Click Sales Check Sara Link", TestStepType.STEP);
			getAction().click(SALES_CHECK_DETAILS_SC_NO);
			
			Logger.log("Switch to Sara Window", TestStepType.STEP);
			Set<String> windowId = getAction().driver.getWindowHandles(); 
			Iterator<String> itererator = windowId.iterator();   
			String mainWinID = itererator.next();
			String  newSaradwinID = itererator.next();
			getAction().driver.switchTo().window(newSaradwinID);

			getAction().waitFor(2000);
			
			Logger.log("Verify Sara link Pop up Window URL", TestStepType.STEP);
			PageAssert.verifyPartiallyEqual(getAction().driver.getCurrentUrl(), partialsaraLinkPopUpWinUrl);
			
			Logger.log("Close Sara Link Window", TestStepType.STEP);
			getAction().driver.close();
			
			Logger.log("Switch to Main Window", TestStepType.STEP);
			getAction().driver.switchTo().window(mainWinID);
		}else{
			Logger.log("Sales Check Number Sara Link is not Clickable", TestStepType.STEP);
		}

		return this;
	}

	public SalesCheckDetailsPage storePOStoWEBVerify() {
		getAction().waitFor(2000);
		String saleschecknumberText = getAction().getText(HEADER_SALES_CHECK_SUMMARY);
		String saleschecknumber = saleschecknumberText.split(" ")[saleschecknumberText.split(" ").length-1];
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		String payment_sc_check = null;

		String sql ="select psc.PAYMENT_SALES_CHECK_NUMBER from sales_check sc,payment_sales_check psc "
				+ "where sc.order_id = psc.order_id and sc.sales_check_number = ?;";
		try {
			st = conn.prepareStatement(sql);
			st.setString (1, saleschecknumber);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				payment_sc_check = rs.getString("PAYMENT_SALES_CHECK_NUMBER") ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(payment_sc_check!=null){
		    SoftAssert.checkConditionAndContinueOnFailure("Store POS to Web column is Present",
            		getAction().getText(STORE_POS_TO_WEB_TITLE).equalsIgnoreCase("Store POS to Web"));

			if(getAction().isVisible(STORE_POS_TO_WEB_TABLE)) {
				
			    SoftAssert.checkConditionAndContinueOnFailure("Payment Sales Check column is Present",
	            		getAction().getText(STORE_POS_TO_WEB_DETAIL_TEXT).equalsIgnoreCase("Payment Sales Check:"));
				
				Logger.log("Verify Payment Sales Check is "+ payment_sc_check+" in database matches with application", TestStepType.STEP);
				PageAssert.verifyEqual(payment_sc_check, getAction().getText(STORE_POS_TO_WEB_DETAIL));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Payment Sales Check in DB matches with application",
	            		getAction().getText(STORE_POS_TO_WEB_DETAIL).equalsIgnoreCase(payment_sc_check));
			    
			}else{
				Reporter.log("Failed Retrieval Data for Store POS to Web Table~");
			}
		}else{
			Logger.log(" There is no Sales Check Store POS to WEB informtioan in Database", TestStepType.STEP);
		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;

	}

	public SalesCheckDetailsPage salesCheckShippingAddressVerify(String OrderID) {
		getAction().waitFor(2000);
		String saleschecknumberText = getAction().getText(HEADER_SALES_CHECK_SUMMARY);
		String saleschecknumber = saleschecknumberText.split(" ")[saleschecknumberText.split(" ").length-1];
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		String nickname = null;
		String name = null;
		String address1 = null;
		String address2 = null;
		String city = null;
		String state = null;
		String zipcode = null;
		String country = null ;
		String ffm = null;
		// String store_phone_no = null;???????????

		String ffm_sql = "select fm.ffm_class_id from ord o, ord_item oi,  sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id "
				+ "and sc.SALES_CHECK_NUMBER = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String customer_sql =" select cci.FIRST_NM, cci.LAST_NM, cci.ADDR_LINE_1, cci.ADDR_LINE_2, cci.CITY, "
				+ "cci.STATE_CD, cci.ZIP_CD, cci. COUNTRY_CD "
				+ "from ord o, customer_contact_info cci "
				+ "where o. BILLING_ADDRESS_ID = cci.ADDRESS_ID and o.site_gen_ord_id =  ?";

		String store_sql ="select name, phone, ADDR_LINE_1, ADDR_LINE_2, city, state, zip_cd"
				+ "from unit u, ffm_method ffm, sales_check sc, ord_item oi, ord o "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = ffm.ffm_method_id "
				+ "and u.unit_number = ffm.RETAIL_UNIT_NO and oi.SALES_CHECK_ID = sc.SALES_CHECK_ID and sc.sales_check_number =  ?";
		try{
			st = conn.prepareStatement(ffm_sql);
			st.setString (1, saleschecknumber);
			st.execute();
			rs = st.getResultSet();
			getAction().waitFor(1000);
			while(rs.next()){
				ffm = rs.getString("ffm_class_id");
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		getAction().waitFor(1000);
		if(ffm.equals("SPU")||ffm.equals("XVRES")||ffm.equals("SRES")  ){
			try {
				st = conn.prepareStatement(store_sql);

				st.setString (1, saleschecknumber);
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					// nickname = rs.getString("");
					name = rs.getString("name") ;
					address1 = rs.getString("ADDR_LINE_1");
					address2 = rs.getString("ADDR_LINE_2");
					city = rs.getString("city");
					state = rs.getString("state");
					zipcode = rs.getString("zip_cd");
					// store_phone_no = rs.getString(" ");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if (ffm.equals("VGC")){
			nickname = "";
			name = "";
			address1 = "";
			address2 = "";
			city = "";
			state = "";
			zipcode = "";
			try {
				st = conn.prepareStatement(customer_sql);

				st.setString (1, OrderID);
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					country = rs.getString("COUNTRY_CD");}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				st = conn.prepareStatement(customer_sql);

				st.setString (1, OrderID);
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					//nickname = rs.getString(" ");
					name = rs.getString("FIRST_NM") +","+ rs.getString("LAST_NM");
					address1 = rs.getString("ADDR_LINE_1");
					address2 = rs.getString("ADDR_LINE_2");
					city = rs.getString("CITY");
					state = rs.getString("STATE_CD");
					zipcode = rs.getString("ZIP_CD");
					country = rs.getString("COUNTRY_CD");
					// store_phone_no = rs.getString(" ");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		Logger.log("Verify Shipping Address column is present", TestStepType.STEP);
		PageAssert.textPresent(SHIPPING_ADDRESS_TITLE, "Shipping Address");


		if(name!=null || address1!=null || address2!=null || city!=null || state!=null || zipcode!=null 
				|| country!=null ){    
			if(getAction().isVisible(SHIPPING_ADDRESS_TABLE)){
				AjaxCondition.forElementVisible(SHIPPING_NICKNAME_TEXT).waitForResponse();
			    SoftAssert.checkConditionAndContinueOnFailure("Nickname column is present",
	            		getAction().getText(SHIPPING_NICKNAME_TEXT).equalsIgnoreCase("Nickname"));
			    
			    SoftAssert.checkConditionAndContinueOnFailure("Name column is present",
	            		getAction().getText(SHIPPING_NAME_TEXT).equalsIgnoreCase("Name"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Address 1 column is present",
	            		getAction().getText(SHIPPING_ADDRESS1_TEXT).equalsIgnoreCase("Address 1"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Address 2 column is present",
	            		getAction().getText(SHIPPING_ADDRESS2_TEXT).equalsIgnoreCase("Address 2"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("City column is present",
	            		getAction().getText(SHIPPING_CITY_TEXT).equalsIgnoreCase("City"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("State column is present",
	            		getAction().getText(SHIPPING_STATE_TEXT).equalsIgnoreCase("State"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Zip column is present",
	            		getAction().getText(SHIPPING_ZIP_TEXT).equalsIgnoreCase("Zip"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Country column is present",
	            		getAction().getText(SHIPPING_COUNTRY_TEXT).equalsIgnoreCase("Country"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Store Phone # column is present",
	            		getAction().getText(SHIPPING_STORE_PHONE_NO_TEXT).equalsIgnoreCase("Store Phone #"));
				
				if(ffm.equals("SYWM")){
					Reporter.log("Verify Data for Shipping Address Successful");
				} else{
					if(ffm.equals("SPU")||ffm.equals("XVRES")||ffm.equals("SRES") ){
						
						 SoftAssert.checkConditionAndContinueOnFailure("Verify name",
								 getAction().getText(SHIPPING_NAME_DETAIL).equalsIgnoreCase(name+","));

					}else{
						
						 SoftAssert.checkConditionAndContinueOnFailure("Verify name",
								 getAction().getText(SHIPPING_NAME_DETAIL).equalsIgnoreCase(name));
						
					}

					 SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Address1",
							 getAction().getText(SHIPPING_ADDRESS1_DETAIL).toLowerCase().replace("\n", "").replace("\r", "").equalsIgnoreCase(address1.toLowerCase()));
					

					if(address2!=null){
						
						 SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Address2",
								 getAction().getText(SHIPPING_ADDRESS2_DETAIL).toLowerCase().replace("\n", "").replace("\r", "").equalsIgnoreCase(address2.toLowerCase()));

					}else{
						address2="";
						 SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Address2",
								 getAction().getText(SHIPPING_ADDRESS2_DETAIL).toLowerCase().replace("\n", "").replace("\r", "").equalsIgnoreCase(address2.toLowerCase()));
						

					}
					
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Customer City",
							 getAction().getText(SHIPPING_CITY_DETAIL).toLowerCase().replace("\n", "").replace("\r", "").equalsIgnoreCase(city.toLowerCase()));
					
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Customer State",
							 getAction().getText(SHIPPING_STATE_DETAIL).toLowerCase().replace("\n", "").replace("\r", "").equalsIgnoreCase(state.toLowerCase()));

					 SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Zipcode",
							 getAction().getText(SHIPPING_ZIP_DETAIL).toLowerCase().replace("\n", "").replace("\r", "").equalsIgnoreCase(zipcode));
					
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Country",
							 getAction().getText(SHIPPING_COUNTRY_DETAIL).toLowerCase().replace("\n", "").replace("\r", "").equalsIgnoreCase(country));

				}
			}else{

				PageAssert.fail("Failed Retrieval Data for Shipping Address");
			}

		}else{

			Logger.log(" There is no Sales Check Shipping Address informtioan in Database", TestStepType.STEP);

		}   

		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;
	}


	public SalesCheckDetailsPage salesCheckContactInfoVerify(String OrderID) {
		getAction().waitFor(2000);
		String saleschecknumberText = getAction().getText(HEADER_SALES_CHECK_SUMMARY);
		String saleschecknumber = saleschecknumberText.split(" ")[saleschecknumberText.split(" ").length-1];
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		String email = null;
		String dayphone = null;
		String nightphone = null;

		String sql ="  select cci.EMAIL_ADDR_1, cci.PHONE_1, cci.PHONE_2 from ord o, customer_contact_info cci "
				+ "where o. BILLING_ADDRESS_ID = cci.ADDRESS_ID and o.site_gen_ord_id =  ?";
		getAction().waitFor(1000);
		try {
			st = conn.prepareStatement(sql);

			st.setString (1, OrderID);
			st.execute();
			rs = st.getResultSet();
			getAction().waitFor(1000);
			while(rs.next()){
				email = rs.getString("EMAIL_ADDR_1");
				dayphone = rs.getString("PHONE_1") ;
				nightphone = rs.getString("PHONE_2");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getAction().scrollTo(CONTACT_INFORMATION_TITLE);
		
	    SoftAssert.checkConditionAndContinueOnFailure("Contact Information column is present",
        		getAction().getText(CONTACT_INFORMATION_TITLE).equalsIgnoreCase("Contact Information"));

		if(email!=null || dayphone!=null || nightphone!=null){
			if(getAction().isVisible(CONTACT_INFORMATION_TABLE)){
				
			    SoftAssert.checkConditionAndContinueOnFailure("Email column is present",
		        		getAction().getText(CONTACT_INFORMATION_EMAIL_TEXT).equalsIgnoreCase("Email"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Daytime Phone column is present",
		        		getAction().getText(CONTACT_INFORMATION_DPHONE_TEXT).equalsIgnoreCase("Daytime Phone"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Evening Phone column is present",
		        		getAction().getText(CONTACT_INFORMATION_EPHONE_TEXT).equalsIgnoreCase("Evening Phone"));
				
				Logger.log("Verify Customer Email for Sales Check No: " + saleschecknumber+" is " + email, TestStepType.STEP);
				PageAssert.verifyEqual(email, getAction().getText(CONTACT_INFORMATION_EMAIL_DETAIL));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Email in DB should match with application",
		        		getAction().getText(CONTACT_INFORMATION_EMAIL_DETAIL).equalsIgnoreCase(email));
				
				SoftAssert.checkConditionAndContinueOnFailure("Email in DB should match with application",
		        		getAction().getText(CONTACT_INFORMATION_DPHONE_DETAIL).replaceAll("[\\D]", "").equalsIgnoreCase(dayphone));
				
				if(nightphone == null || StringUtils.isEmpty(nightphone) || StringUtils.isBlank(nightphone) ){
					//TODO invert the condition
				}else{
					SoftAssert.checkConditionAndContinueOnFailure("Eveningtime Phone Number in DB should match with application",
			        		getAction().getText(CONTACT_INFORMATION_EPHONE_DETAIL).replaceAll("[\\D]", "").equalsIgnoreCase(nightphone));
				}
				
			}else{
				PageAssert.fail("Failed Retrieval Data for Sales Check Detail");
			}
		} else{
			Logger.log(" There is no Sales Check Contact informtioan in Database", TestStepType.STEP);
		}

		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;
	}

	public SalesCheckDetailsPage verifyReturnGiftCardInformation(String OrderID) {
		getAction().waitFor(2000);
		String saleschecknumberText = getAction().getText(HEADER_SALES_CHECK_SUMMARY);
		String saleschecknumber = saleschecknumberText.split(" ")[saleschecknumberText.split(" ").length-1];
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		ArrayList<String> serialNumber  =  new ArrayList<String>();
		ArrayList<String> giftcardNumber  =  new ArrayList<String>();
		ArrayList<Double> amount  =  new ArrayList<Double>();
		String sql ="select gcc.GIFT_CARD_SERIAL_NUMBER,goi.GIFT_CARD_NUMBER,goi.AMOUNT "
				+ "from ord o, ord_item oi, sales_check sc, gift_card_control gcc, giftcard_order_item goi "
				+ "where o.order_id = oi.order_id and oi.order_item_id = goi.order_item_id and oi.order_id = sc.order_id "
				+ "and sc.sales_check_number=? group by goi.email";

		try {
			st = conn.prepareStatement(sql);
			st.setString (1, saleschecknumber);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				giftcardNumber.add(rs.getString("GIFT_CARD_NUMBER"));
				amount.add(rs.getDouble("AMOUNT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getAction().scrollTo(RETURN_GIFT_CARD_INFO_TITLE);

		Logger.log("Verify Return Gift Card Information Title is Present", TestStepType.STEP);
		PageAssert.textPresent(RETURN_GIFT_CARD_INFO_TITLE, "Return Gift Card Information");

		if(giftcardNumber.size()!=0 || amount.size()!=0){
			if(getAction().isVisible(RETURN_GIFT_CARD_INFO_TABLE)){
				Logger.log("Verify Discount Table is Visible", TestStepType.STEP);
				
				
			    SoftAssert.checkConditionAndContinueOnFailure("Serial # column is present",
		        		getAction().getText(RETURN_GIFT_CARD_INFO_SERIAL_NO_TEXT).equalsIgnoreCase("Serial #"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Card # column is present",
		        		getAction().getText(RETURN_GIFT_CARD_INFO_CARD_NUMBER_TEXT).equalsIgnoreCase("Card #"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Date issued column is present",
		        		getAction().getText(RETURN_GIFT_CARD_INFO_AMOUNT_TEXT).equalsIgnoreCase("Date issued"));
				
				WebElement returngfcardtable = getAction().findElement(RETURN_GIFT_CARD_INFO_TABLE_CONTENT);
				List<WebElement> returngfcard_table_rows = returngfcardtable.findElements(By.tagName("tr"));
				int returngfcard_table_rows_count = returngfcard_table_rows.size();

				for (int i=0; i<returngfcard_table_rows_count && i<giftcardNumber.size(); i++){

					List<WebElement> returngfcard_table_columns = returngfcard_table_rows.get(i).findElements(By.tagName("td"));

					/*PageAssert.verifyEqual(serialNumber.get(i), returngfcard_table_columns.get(0).getText());
	    	   Logger.log("Verify Description in Return Gift Card is "+ serialNumber.get(i) +" in Database and "+returngfcard_table_columns.get(0).getText()+" is displayed", TestStepType.STEP);
					 */
					
					Logger.log("Verify Gift Card Number in Return Gift Card is "+giftcardNumber.get(i)+" in Database and "+returngfcard_table_columns.get(1).getText()+" is displayed", TestStepType.STEP);
					PageAssert.verifyEqual(giftcardNumber.get(i), returngfcard_table_columns.get(1).getText());
					
					String amt = returngfcard_table_columns.get(2).getText();
					if(amt.equalsIgnoreCase(""));
						amt="$0.00";
					Logger.log("Verify Amount in Return Gift Card is "+"$"+df.format(amount.get(i))+" in Database and "+amt+" is displayed", TestStepType.STEP);
					
					PageAssert.verifyEqual("$"+df.format(amount.get(i)), amt);

				}
			}else{
				PageAssert.fail("Failed Retrieval Data for Sales Check Return Gift Card");
			}
		}else{
			Logger.log("No return gift card information found in Database", TestStepType.STEP);
			AjaxCondition.forElementVisible(RETURN_GIFT_CARD_INFO_NO_RESPONSE).waitForResponse(10);
		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;
	}



	public SalesCheckDetailsPage verifyDiscounts() {
		getAction().waitFor(2000);
		String saleschecknumberText = getAction().getText(HEADER_SALES_CHECK_SUMMARY);
		String saleschecknumber = saleschecknumberText.split(" ")[saleschecknumberText.split(" ").length-1];
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		ArrayList<String> description  =  new ArrayList<String>();
		ArrayList<Double> amount  =  new ArrayList<Double>();
		String sql ="select oid.PROMOTION_NAME, oid.AMOUNT from ord_item_discount oid, ord_item oi, sales_check sc "
				+ "where oid.ORDER_ITEM_ID = oi.ORDER_ITEM_ID and oi.SALES_CHECK_ID = sc.SALES_CHECK_ID and sc.SALES_CHECK_NUMBER = ?";

		try {
			st = conn.prepareStatement(sql);
			st.setString (1, saleschecknumber);
			st.execute();
			rs = st.getResultSet();

			while(rs.next()){

				description.add(rs.getString("PROMOTION_NAME"));
				amount.add(rs.getDouble("AMOUNT"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		getAction().scrollTo(DISCOUNTS_TITLE);
		Logger.log("Starting Verify Discounts Table Content for Sales Check No: "+saleschecknumber, TestStepType.STEP);
		
	    SoftAssert.checkConditionAndContinueOnFailure("Discounts column is present",
        		getAction().getText(DISCOUNTS_TITLE).equalsIgnoreCase("Discounts"));

		if(description.size()!=0 || amount.size()!=0){
			if(getAction().isVisible(DISCOUNTS_TABLE)){
				Logger.log("Verify Discount Table is Visible", TestStepType.STEP);

			    SoftAssert.checkConditionAndContinueOnFailure("Discount Description column is present",
		        		getAction().getText(DESCRIPTION_TEXT).equalsIgnoreCase("Discount Description"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Amount column is present",
		        		getAction().getText(AMOUNT_TEXT).equalsIgnoreCase("Amount"));

				WebElement discountsstable = getAction().findElement(DISCOUNTS_TABLE_CONTENT );
				List<WebElement> disct_table_rows = discountsstable.findElements(By.tagName("tr"));
				int disct_table_rows_count = disct_table_rows.size();

				for (int i=0; i<disct_table_rows_count; i++){

					List<WebElement> disct_table_columns = disct_table_rows.get(i).findElements(By.tagName("td"));

					Logger.log("Verify Description in Discount is "+ description.get(i) +" in Database and "+disct_table_columns.get(0).getText()+" is displayed", TestStepType.STEP);
					PageAssert.verifyEqual(description.get(i), disct_table_columns.get(0).getText());

					Logger.log("Verify Amount in Discount is "+"$"+String.format("%.2f", Math.abs(amount.get(i)))+" in Database and "+disct_table_columns.get(1).getText()+" is displayed", TestStepType.STEP);
					PageAssert.verifyEqual("($"+df.format(Math.abs(amount.get(i)))+")", disct_table_columns.get(1).getText());
					
				}	
			}else{
				PageAssert.fail("Failed Retrieval Data for Sales Check Discounts");
			}
		}else{
			
			Logger.log("No Sales Check Discounts information found in Database", TestStepType.STEP);
			AjaxCondition.forElementVisible(DISCOUNTS_NO_RESPONSE).waitForResponse(10);
			

		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;
	}

	public SalesCheckDetailsPage verifyDeliveryDetails(String OrderID){

		AjaxCondition.forElementVisible(DELIVERY_DETAIL_TITLE).waitForResponse(5);
	    SoftAssert.checkConditionAndContinueOnFailure("Delivery Details column is present",
        		getAction().getText(DELIVERY_DETAIL_TITLE).equalsIgnoreCase("Delivery Details"));
	    
		getAction().scrollTo(DELIVERY_DETAIL_TITLE);
		if(getAction().isVisible(DELIVERYDETAILS)){
			Logger.log("Verify Delivery Table is Visible", TestStepType.STEP);

			Logger.log("Delivery Details DOS  is present", TestStepType.VERIFICATION_STEP);
			AjaxCondition.forElementVisible(DELIVERYDETAILS_DOS_NUMBER).waitForResponse(5);
			
			Logger.log("Delivery Update Button is present", TestStepType.VERIFICATION_STEP);
			AjaxCondition.forElementVisible(DELIVERYDETAILS_UPDATE_BUTTON).waitForResponse(5);
			
			SoftAssert.checkConditionAndContinueOnFailure("Verify Update Button is Disabled",!getAction().findElement(DELIVERYDETAILS_UPDATE_BUTTON).isEnabled());
			
			Logger.log("Delivery Specific Instruction Edit Button is present", TestStepType.VERIFICATION_STEP);
			AjaxCondition.forElementVisible(DELIVERYDETAILS_SPECIFIC_INSTRUCTION_EDIT_BUTTON).waitForResponse(5);
			
			SoftAssert.checkConditionAndContinueOnFailure("Verify Specific Instruction Edit Button is Disabled",!getAction().findElement(DELIVERYDETAILS_SPECIFIC_INSTRUCTION_EDIT_BUTTON).isEnabled());
			
			Logger.log("Delivery Instruction Edit Button is present", TestStepType.VERIFICATION_STEP);
			AjaxCondition.forElementVisible(DELIVERYDETAILS_DELIVERY_INSTRUCTION_EDIT_BUTTON).waitForResponse(5);
			
			SoftAssert.checkConditionAndContinueOnFailure("Verify Delivery Instruction Edit Button is Disabled",!getAction().findElement(DELIVERYDETAILS_DELIVERY_INSTRUCTION_EDIT_BUTTON).isEnabled());

			AjaxCondition.forElementVisible(DELIVERYDETAILS_TABS.format("Order Detail")).waitForResponse(5);
			getAction().waitFor(2000);
			Logger.log("Order Details  is present", TestStepType.VERIFICATION_STEP);
			getAction().focus(DELIVERYDETAILS_TABS.format("Order Detail"));
			getAction().click(DELIVERYDETAILS_TABS.format("Order Detail"));
			
			Logger.log("Delivery Details  is present", TestStepType.VERIFICATION_STEP);
			AjaxCondition.forElementVisible(DELIVERYDETAILS_TABS.format("Delivery Detail")).waitForResponse(5);
			getAction().waitFor(2000);
			getAction().click(DELIVERYDETAILS_TABS.format("Delivery Detail"));
			
			Logger.log("Miscellaneous  is present", TestStepType.VERIFICATION_STEP);
			AjaxCondition.forElementVisible(DELIVERYDETAILS_TABS.format("Miscellaneous")).waitForResponse(5);
			getAction().waitFor(2000);
			getAction().click(DELIVERYDETAILS_TABS.format("Miscellaneous"));
			
			
			Logger.log("Delivery Notes is present", TestStepType.VERIFICATION_STEP);
			AjaxCondition.forElementVisible(DELIVERYDETAILS_TABS.format("Delivery Notes")).waitForResponse(5);
			getAction().waitFor(2000);
			getAction().click(DELIVERYDETAILS_TABS.format("Delivery Notes"));
			

			Logger.log("Route Tracking is present", TestStepType.VERIFICATION_STEP);
			AjaxCondition.forElementVisible(DELIVERYDETAILS_TABS.format("Route Tracking")).waitForResponse(5);
			getAction().waitFor(2000);
			getAction().click(DELIVERYDETAILS_TABS.format("Route Tracking"));
			
			Logger.log("Action Center is present", TestStepType.VERIFICATION_STEP); 	
			AjaxCondition.forElementVisible(DELIVERYDETAILS_TABS.format("Action Center")).waitForResponse(5);
			getAction().waitFor(2000);
			getAction().click(DELIVERYDETAILS_TABS.format("Action Center"));
			

		}else{

			Logger.log("Delivery Table is not visible", TestStepType.STEP);

		}
		return this;
	}


	public SalesCheckDetailsPage verifyPayments( String OrderID, String storeId) {
		getAction().waitFor(2000);
		String saleschecknumberText = getAction().getText(HEADER_SALES_CHECK_SUMMARY);
		String saleschecknumber = saleschecknumberText.split(" ")[saleschecknumberText.split(" ").length-1];
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		String Sql ="select pm.PAYMENT_METHOD_DS, pi.TOTAL_AMT from pmt_method pm, payment_instruction pi, ord o,sales_check sc "
				+ "where o.order_id = pi.order_id and o.order_id = sc.order_id and pi.payment_method_id = pm.payment_method_id "
				+ "and sc.sales_check_number = ? and o.site_gen_ord_id = ?";
		ArrayList<String> payment_type  =  new ArrayList<String>();
		ArrayList<Double> payment_amount =  new ArrayList<Double>();
		String card_number_ws = null;
		String expire_month_ws = null;
		String expire_year_ws = null;
		/****
		 * data from mysql db
		 ****/
		try {

			st = conn.prepareStatement(Sql);
			st.setString (1, saleschecknumber);
			st.setString (2, OrderID);
			st.execute();
			rs = st.getResultSet();
			getAction().waitFor(1000);
			while(rs.next()){
				payment_type.add(rs.getString("PAYMENT_METHOD_DS"));
				payment_amount.add(rs.getDouble("TOTAL_AMT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		/***
		 * data from web services
		 ****/
		JSONObject orderSummary = verifySoapDetailsSalesCheck(OrderID, storeId);
		/***
		 * verify text present
		 ***/
		getAction().scrollTo(PAYMENTS_TITLE);
		PageAssert.textPresent(PAYMENTS_TITLE, "Payments");
		Logger.log("Verify Payments Title column is present", TestStepType.STEP);
		if(payment_type.size()!=0 || payment_amount.size()!=0 || card_number_ws!=null || expire_month_ws!=null || expire_year_ws!=null){

			if( getAction().isVisible(PAYMENTS_TABLE)){
				Logger.log("Verify Payments Table is Visible", TestStepType.STEP);
				
			    SoftAssert.checkConditionAndContinueOnFailure("Payment Type column is present",
		        		getAction().getText(PAYMENTS_TYPE_TEXT).equalsIgnoreCase("Payment Type"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Payments Card Number column is present",
		        		getAction().getText(PAYMENTS_CARD_NUMBER_TEXT).equalsIgnoreCase("Card Number"));
			    
			    SoftAssert.checkConditionAndContinueOnFailure("Amount column is present",
		        		getAction().getText(PAYMENTS_AMOUNT_TEXT).equalsIgnoreCase("Amount"));
				
			    SoftAssert.checkConditionAndContinueOnFailure("Expiration Date is present",
		        		getAction().getText(PAYMENTS_EXPIRATION_DATE_TEXT).equalsIgnoreCase("Expiration Date"));
				
				/***
				 * verify value
				 ***/	

				WebElement paymentstable = getAction().findElement(PAYMENTS_TABLE_CONTENT);
				List<WebElement> payment_table_rows = paymentstable.findElements(By.tagName("tr"));
				int payment_table_rows_count = payment_table_rows.size();
				try
				{
					if (orderSummary.get("PaymentDetails") instanceof JSONObject){
						JSONObject paymentDetailsObj = orderSummary.getJSONObject("PaymentDetails");
						if(String.valueOf(paymentDetailsObj.getLong("CardNumber"))!=null){
							card_number_ws = maskCreditCardNumber(String.valueOf(paymentDetailsObj.getLong("CardNumber")));

						}else if(paymentDetailsObj.getString("MaskedCardNumber")!=null){
							card_number_ws = String.valueOf(paymentDetailsObj.getString("MaskedCardNumber"));
						}

						List<WebElement> payment_table_columns = payment_table_rows.get(0).findElements(By.tagName("td"));
						
						Logger.log("Verify Payments Type in Payments is "+ payment_type+" in DataBase and "+payment_table_columns.get(0).getText()+" is displayed", TestStepType.STEP);
						PageAssert.verifyEqual(payment_type.get(0), payment_table_columns.get(0).getText());
						
						Logger.log("Verify Card Number in Payments is "+ card_number_ws+" in Web Services and "+payment_table_columns.get(2).getText()+" is displayed", TestStepType.STEP);
						PageAssert.verifyEqual(card_number_ws, payment_table_columns.get(2).getText());
						
						if(payment_type.get(0).equalsIgnoreCase("CreditCard")){
							expire_month_ws = String.valueOf(paymentDetailsObj.getInt("ExpiryMonth"));
							expire_year_ws = String.valueOf(paymentDetailsObj.getInt("ExpiryYear"));

							Logger.log("Verify Expiration Date in Payments is "+expire_month_ws+"/"+expire_year_ws+" in Web Services and " +payment_table_columns.get(6).getText()+" is displayed", TestStepType.STEP);
							PageAssert.verifyEqual(expire_month_ws.replaceAll("\\d+.*", "")+"/"+expire_year_ws.replaceAll("\\d+.*", ""), payment_table_columns.get(6).getText());
						
						}
						
						Logger.log("Verify Payment Amount in Payments is "+"$"+String.format("%.2f", payment_amount.get(0))+" in DataBase and "+payment_table_columns.get(3).getText()+" is displayed", TestStepType.STEP);
						PageAssert.verifyEqual("$"+String.format("%.2f", payment_amount.get(0)), payment_table_columns.get(3).getText());
					}   
					else if (orderSummary.get("PaymentDetails") instanceof JSONArray){
						JSONArray paymentDetailsArray = orderSummary.getJSONArray("PaymentDetails");

						Logger.log("Verify number of payment options is same in MSP and service",TestStepType.STEP);
						PageAssert.verifyEqual(payment_table_rows_count, paymentDetailsArray.length());
						for (int i=0; i<payment_table_rows_count; i++){
							JSONObject tempObject = (JSONObject)paymentDetailsArray.getJSONObject(i);
							card_number_ws = maskCreditCardNumber(String.valueOf(tempObject.getLong("CardNumber")));
							List<WebElement> payment_table_columns = payment_table_rows.get(0).findElements(By.tagName("td"));
							
							Logger.log("Verify Payments Type in Payments is "+ payment_type.get(i)+" in DataBase and "+payment_table_columns.get(0).getText()+" is displayed", TestStepType.STEP);
							PageAssert.verifyEqual(payment_type.get(i), payment_table_columns.get(0).getText());
							
							Logger.log("Verify Card Number in Payments is "+ card_number_ws+" in Web Services and "+payment_table_columns.get(2).getText()+" is displayed", TestStepType.STEP);
							PageAssert.verifyEqual(card_number_ws, payment_table_columns.get(2).getText());
							
							if(payment_type.get(i).equalsIgnoreCase("CreditCard")){    	        			
								expire_month_ws = String.valueOf(tempObject.getInt("ExpiryMonth"));
								expire_year_ws = String.valueOf(tempObject.getInt("ExpiryYear"));
								
								Logger.log("Verify Expiration Date in Payments is "+expire_month_ws+"/"+expire_year_ws+" in Web Services and " +payment_table_columns.get(6).getText()+" is displayed", TestStepType.STEP);
								PageAssert.verifyEqual(expire_month_ws.replaceAll("\\d+.*", "")+"/"+expire_year_ws.replaceAll("\\d+.*", ""), payment_table_columns.get(6).getText());
								
							}
							
							Logger.log("Verify Payment Amount in Payments is "+"$"+String.format("%.2f", payment_amount.get(i))+" in DataBase and "+payment_table_columns.get(3).getText()+" is displayed", TestStepType.STEP);
							PageAssert.verifyEqual("$"+String.format("%.2f", payment_amount.get(i)), payment_table_columns.get(3).getText());
						}
					}

					else{
						Logger.log("Payments Table is not visible", TestStepType.STEP);
					}}
				catch (Exception e) {				
					e.printStackTrace();
				}  
			}else{
				PageAssert.fail("Failed Retrieval Data for Payments");
			}
		}else{
			Logger.log(" There is no Payment informtioan in Database", TestStepType.STEP);
		}	
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this; 
	}

	public JSONObject verifySoapDetailsSalesCheck(String OrderID, String storeId)  {
		String url = "http://oms.qa.ch3.s.com/KanaRS/services/Sears-Services";
		String pagerequest="sch:RetrieveOrderSummaryRequest", schemaURL="http://www.shc.com/schema", transactionid="39ee24db-cd9e-47fe-a687-a0d56f3d57ce", username="XXXX",password="XXXXX",  orderid = OrderID, stoteid = storeId, source="msp";
		JSONObject orderSummary=null;
		String result=null;
		try {
			result=SOAPRequest.getSOAPRespose(url,SOAPRequest.createSOAPRequest( pagerequest, schemaURL, transactionid, username, password, orderid, stoteid, source));
			JSONObject xmlJSONObj = XML.toJSONObject(result);
			JSONObject envelop=(JSONObject)xmlJSONObj.get("soapenv:Envelope");
			JSONObject body=(JSONObject)envelop.get("soapenv:Body");
			JSONObject retrieveOrderSummaryResponse=(JSONObject)body.get("ns1:RetrieveOrderSummaryResponse");
			JSONObject dataArea=(JSONObject)retrieveOrderSummaryResponse.get("DATAAREA");
			orderSummary=(JSONObject)dataArea.get("OrderSummary");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderSummary;
	}

	public static String maskCreditCardNumber(String creditCardNumber) {
		Pattern pattern = Pattern.compile("[0-9]+");

		Matcher matcher = pattern.matcher(creditCardNumber);
		String maskingChar = "*";
		StringBuilder finalMask = new StringBuilder(maskingChar);
		while (matcher.find()) {
			String group = matcher.group();
			int groupLen = group.length();
			if(groupLen>4){
				for(int i=1; i<group.length()-4; i++){
					finalMask.append(maskingChar);
				}
				finalMask.append(group.substring(groupLen-4));
			}
			creditCardNumber = creditCardNumber.replace(group, finalMask);
		}
		return creditCardNumber;
	}

	public SalesCheckDetailsPage verifyStatus(){    	
		Logger.log("Verify if Status is 'R' or 'RCAN'",TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(STATUS).waitForResponse();
		String status = getAction().getText(STATUS);
		if(!(status.equalsIgnoreCase("R") || status.equalsIgnoreCase("RCAN")))
		{
			PageAssert.fail("Status doesnt match: "+status);
		}
		else
		{
			Logger.log("Status : "+status, TestStepType.VERIFICATION_PASSED);
		}
		return this;
	}

	public SalesCheckDetailsPage verifyUpdateSaleCheck() {
		getAction().waitFor(5000);
		
		Logger.log("Verify Update sales check Popup" , TestStepType.STEP);
		AjaxCondition.forElementVisible(UPDATE_SALES_CHECK_POPUP).waitForResponse();
		
		Logger.log("Verify Ringin ID textbox", TestStepType.STEP);
		AjaxCondition.forElementVisible(RINGING_Id_TEXTBOX).waitForResponse();
		Logger.log("Verify Rc code", TestStepType.STEP);
		AjaxCondition.forElementVisible(RC_CODE_TEXTBOX).waitForResponse();
		AjaxCondition.forElementVisible(OrderDetailsPage.ADJUSTMENT_NOTES).waitForResponse();
		Logger.log("Enter the Notes", TestStepType.STEP);
		getAction().type(OrderDetailsPage.ADJUSTMENT_NOTES,"MSP automation update sales check");
		getContext().put("adjustmentOption", "MSP automation update sales check");
		Logger.log("Submit Botton is Visible", TestStepType.STEP);
		AjaxCondition.forElementVisible(OrderDetailsPage.SUBMIT_BUTTON).waitForResponse();
		
		getAction().click(OrderDetailsPage.SUBMIT_BUTTON);
		return this;
	}

	public SalesCheckDetailsPage verifyReleaseSaleCheck() {
		getAction().waitFor(10000);
		Logger.log("Vefify Release sales check Popup" , TestStepType.STEP);
		AjaxCondition.forElementVisible(RELEASE_SALES_CHECK_POPUP).waitForResponse();
		Logger.log("Verify adjustment notes is visible",TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(OrderDetailsPage.ADJUSTMENT_NOTES).waitForResponse();
		getAction().waitFor(1000); 
		Logger.log("Enter details on adjustment notes",TestStepType.STEP);
		getAction().click(OrderDetailsPage.ADJUSTMENT_NOTES);
		getAction().type(OrderDetailsPage.ADJUSTMENT_NOTES,"MSP automation release sales check");
		getContext().put("adjustmentOption", "MSP automation release sales check");
		getAction().waitFor(1000);
		Logger.log("Verify that Submit button is visible",TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(OrderDetailsPage.SUBMIT_BUTTON).waitForResponse();
		Logger.log("Click on Submit Button" , TestStepType.STEP);
		getAction().click(OrderDetailsPage.SUBMIT_BUTTON);
		
		/* getAction().click(OrderDetailsPage.SUBMIT_BUTTON);
	       getAction().waitFor(3000);
	       AjaxCondition.forElementVisible(OK_POPUp).waitForResponse();
	       getAction().click(OK_POPUp);*/

		return this;
	}

	public static boolean validatePhoneNumber(String phoneNo) {
		if (phoneNo.matches("D: "+"\\d{10}")) return true;
		else if(phoneNo.matches("D: "+"\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
		else if(phoneNo.matches("D: "+"\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
		else if(phoneNo.matches("D: "+"\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
		else return false;

	}

	public SalesCheckDetailsPage salesCheckSummaryPageVerify(String OrderID, String storeId) {
		AjaxCondition.forElementVisible(HEADER_SALES_CHECK_SUMMARY).waitForResponse();
		String saleschecknumberText = getAction().getText(HEADER_SALES_CHECK_SUMMARY);
		String saleschecknumber = saleschecknumberText.split(" ")[saleschecknumberText.split(" ").length-1];
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		ArrayList<String> subtotal = new ArrayList<String>();
		ArrayList<String>  total_shipping = new ArrayList<String>();
		ArrayList<String> surcharge = new ArrayList<String>();
		ArrayList<String>  tax_percent = new ArrayList<String>();
		ArrayList<String>   tax = new ArrayList<String>();
		Double total = null;
		Double surCharge = null;
		String sql_sc_summary ="select SUBTOTAL_AMOUNT,  SHIPPING_AMT, SURCHARGE_AMOUNT, tax_rate,  TAX_AMT from ord_item "
				+ "where sales_check_id in (select sales_check_id from sales_check where SALES_CHECK_NUMBER = ? and order_id =? )";

		try {

			st = conn.prepareStatement(sql_sc_summary);
			st.setString (1, saleschecknumber);
			st.setString(2, OrderID);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){

				subtotal.add(rs.getString("SUBTOTAL_AMOUNT") );
				total_shipping.add(rs.getString("SHIPPING_AMT")) ;
				surcharge.add(rs.getString("SURCHARGE_AMOUNT") );
				tax_percent.add(rs.getString("tax_rate"));
				tax.add(rs.getString("TAX_AMT"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		SoftAssert.checkConditionAndContinueOnFailure("Verify Sales Check Summary column is present",getAction().getText(SALES_CHECK_SUMMARY_TITLE).equals("Sales Check Summary"));
		if(subtotal.size()!=0 || total_shipping.size()!=0 || surcharge!=null || tax_percent.size()!=0 ||  tax.size()!=0 
				||  total!=null){
			if(getAction().isVisible(SALES_CHECK_SUMMARY_TABLE)) { 
				/****
				 * Verify Text Present
				 ****/
				SoftAssert.checkConditionAndContinueOnFailure("Verify Subtotal column is present",getAction().getText(SUBTOTAL_TEXT).equals("Subtotal"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Total Shipping column is present",getAction().getText(TOTAL_SHIPPING_TEXT).equals("Total Shipping"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Surcharge column is present",getAction().getText(SURCHARGE_TEXT).equals("Surcharge"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Tax Percent column is present",getAction().getText(TAX_PERCENT_TEXT).equals("Tax Percent"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Tax column is present",getAction().getText(TAX_TEXT).equals("Tax"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Total column is present",getAction().getText(TOTAL_TEXT).equals("Total"));

				Double sum_subtotal=0.0  ;
				Double sum_totalshipping=0.0;
				Double sum_tax = 0.0;
				Double sum_surcharge = 0.0;
				for(int i=0; i<subtotal.size();i++){
					sum_subtotal += Double.parseDouble(subtotal.get(i));
					sum_totalshipping +=Double.parseDouble(total_shipping.get(i));
					if(surcharge.get(i) == null){
						surCharge = 0.00;
					}else{
						surCharge = Double.parseDouble(surcharge.get(i));
					}
					sum_surcharge +=surCharge;
					sum_tax += Double.parseDouble(tax.get(i));
					total = sum_subtotal + sum_totalshipping +sum_surcharge + sum_tax;
					}
				SoftAssert.checkConditionAndContinueOnFailure("Verify Subtotal",getAction().getText(SUBTOTAL_CONTENT).equals("$"+formatter.format(sum_subtotal)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Total Shipping",getAction().getText(TOTAL_SHIPPING_CONTENT).equals("$"+df.format(sum_totalshipping)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Surcharge",getAction().getText(SURCHARGE_CONTENT).equals("$"+df.format(sum_surcharge)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Tax Percent",getAction().getText(TAX_PERCENT_CONTENT).replaceAll("//s","").equals(df.format(Double.parseDouble(tax_percent.get(0))*100)+"%"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Tax with Front End",getAction().getText(TAX_CONTENT).equals("$"+df.format(sum_tax)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Tax with Calculation",df.format(sum_tax).equals(df.format((sum_subtotal + sum_totalshipping +sum_surcharge)*Double.parseDouble(tax_percent.get(0)))));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Total",getAction().getText(TOTAL_CONTENT).equals("$"+formatter.format(total)));

				Reporter.log("Verified Sales Check Summary Table Successfully");   
			}else{

				PageAssert.fail("Failed Retrieval Data for Sales Check Summary");
			}

		}else{

			Logger.log(" There is no Sales Check Summary informtioan in Database", TestStepType.STEP);

		}


		String ringing_id = null;
		String sc_number = null;
		String rc_code= null;

		String sql_Detail ="select RINGING_CSR_ID, SALES_CHECK_NUMBER, REFUND_VALIDATION_NUMBER "
				+ "from sales_check where SALES_CHECK_NUMBER = ? ";

		try {
			st = conn.prepareStatement(sql_Detail);
			st.setString (1, saleschecknumber);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){

				ringing_id = rs.getString("RINGING_CSR_ID") ;
				sc_number = rs.getString("SALES_CHECK_NUMBER") ;
				rc_code = rs.getString("REFUND_VALIDATION_NUMBER") ;

				if(ringing_id==null){
					ringing_id = "";
				}

				if(rc_code==null){
					rc_code = "0";
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SoftAssert.checkConditionAndContinueOnFailure("Verify Sales Check Details column is present",getAction().getText(SALES_CHECK_DETAILS_TITLE).equals("Sales Check Details"));

		if( ringing_id!=null || sc_number!=null || rc_code!=null){
			SoftAssert.checkConditionAndContinueOnFailure("Verify Ringing Id column is present",getAction().getText(SALES_CHECK_DETAILS_RINGING_ID_TEXT).equals("Ringing Id"));
			SoftAssert.checkConditionAndContinueOnFailure("Verify Total Sales Check # column is present",getAction().getText(SALES_CHECK_DETAILS_SC_NO_TEXT).equals("Sales Check #"));
			SoftAssert.checkConditionAndContinueOnFailure("Verify RC Code column is present",getAction().getText(SALES_CHECK_DETAILS_RC_CODE_TEXT).equals("RC Code"));

			if(getAction().isVisible(SALES_CHECK_DETAILS_TABLE)) {
				SoftAssert.checkConditionAndContinueOnFailure("Verify Ringing Id",ringing_id.equals(getAction().getText(SALES_CHECK_DETAILS_RINGING_ID)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Sales Check # ",sc_number.equals(getAction().getText(SALES_CHECK_DETAILS_SC_NO)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify RC Code",rc_code.equals(getAction().getText(SALES_CHECK_DETAILS_RC_CODE)));
				Reporter.log("Verified Sales Check Details Table Successfully");

			}else{

				PageAssert.fail("Failed Retrieval Data for Sales Check Detail");
			}

		}else{

			Logger.log(" There is no Sales Check Detail informtioan in Database", TestStepType.STEP);

		}


		String payment_sc_check = null;

		String sql ="select psc.PAYMENT_SALES_CHECK_NUMBER from sales_check sc,payment_sales_check psc "
				+ "where sc.order_id = psc.order_id and sc.sales_check_number = ?;";
		try {
			st = conn.prepareStatement(sql);
			st.setString (1, saleschecknumber);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				payment_sc_check = rs.getString("PAYMENT_SALES_CHECK_NUMBER") ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(payment_sc_check!=null){
			SoftAssert.checkConditionAndContinueOnFailure("Verify Store POS to Web column is present",getAction().getText(STORE_POS_TO_WEB_TITLE).equals("Store POS to Web"));
			if(getAction().isVisible(STORE_POS_TO_WEB_TABLE)) {
				SoftAssert.checkConditionAndContinueOnFailure("Verify Payment Sales Check column is present",getAction().getText(STORE_POS_TO_WEB_DETAIL_TEXT).equals("Payment Sales Check:"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Payment Sales Check Number",getAction().getText(STORE_POS_TO_WEB_DETAIL).equals(payment_sc_check));

				Reporter.log("Verified Store POS to Web Table Successfully!");

			}else{
				Reporter.log("Failed Retrieval Data for Store POS to Web Table~");
			}
		}else{
			Logger.log(" There is no Sales Check Store POS to WEB informtioan in Database", TestStepType.STEP);
		}


		String nickname = null;
		String name = null;
		String address1 = null;
		String address2 = null;
		String city = null;
		String state = null;
		String zipcode = null;
		String country = null ;
		String ffm = null;
		// String store_phone_no = null;???????????
		String ffm_sql = "select fm.ffm_class_id from ord o, ord_item oi,  sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id "
				+ "and sc.SALES_CHECK_NUMBER = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String customer_sql ="select cci.FIRST_NM, cci.LAST_NM, cci.ADDR_LINE_1, cci.ADDR_LINE_2, cci.CITY, "+
				"cci.STATE_CD, cci.ZIP_CD, cci. COUNTRY_CD "+
				"from ord_item o, customer_contact_info cci "+
				"where o.SHIPTO_ADDRESS_ID = cci.ADDRESS_ID and o.order_id =?";

		String store_sql ="select name, phone, ADDR_LINE_1, ADDR_LINE_2, city, state, zip_cd "
				+ "from unit u, ffm_method ffm, sales_check sc, ord_item oi, ord o "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = ffm.ffm_method_id "
				+ "and u.unit_number = ffm.RETAIL_UNIT_NO and oi.SALES_CHECK_ID = sc.SALES_CHECK_ID and sc.sales_check_number =  ?";
		try{
			st = conn.prepareStatement(ffm_sql);
			st.setString (1, saleschecknumber);
			st.execute();
			rs = st.getResultSet();
			//getAction().waitFor(1000);
			while(rs.next()){
				ffm = rs.getString("ffm_class_id");
				System.out.println("ffm: "+ffm);
			}
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		//getAction().waitFor(1000);
		if(ffm.equals("SPU")  ){
			try {
				st = conn.prepareStatement(store_sql);

				st.setString (1, saleschecknumber);
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					nickname = rs.getString("name");
					name = rs.getString("") ;
					address1 = rs.getString("ADDR_LINE_1");
					address2 = rs.getString("ADDR_LINE_2");
					city = rs.getString("city");
					state = rs.getString("state");
					zipcode = rs.getString("zip_cd");

					// store_phone_no = rs.getString(" ");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if (ffm.equals("VGC")){
			nickname = "";
			name = "";
			address1 = "";
			address2 = "";
			city = "";
			state = "";
			zipcode = "";
			try {
				st = conn.prepareStatement(customer_sql);

				st.setString (1, OrderID);
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					country = rs.getString("COUNTRY_CD");}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				st = conn.prepareStatement(customer_sql);

				st.setString (1, OrderID);
				st.execute();
				rs = st.getResultSet();
				//getAction().waitFor(1000);
				while(rs.next()){
					//nickname = rs.getString(" ");
					name = rs.getString("FIRST_NM") +","+ rs.getString("LAST_NM");
					address1 = rs.getString("ADDR_LINE_1");
					address2 = rs.getString("ADDR_LINE_2");
					city = rs.getString("CITY");
					state = rs.getString("STATE_CD");
					zipcode = rs.getString("ZIP_CD");
					country = rs.getString("COUNTRY_CD");
					// store_phone_no = rs.getString(" ");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}}
		SoftAssert.checkConditionAndContinueOnFailure("Verify Shipping Address column is present",getAction().getText(SHIPPING_ADDRESS_TITLE).equals("Shipping Address"));

		if(name!=null || address1!=null || address2!=null || city!=null || state!=null || zipcode!=null 
				|| country!=null ){    
			if(getAction().isVisible(SHIPPING_ADDRESS_TABLE)){
				SoftAssert.checkConditionAndContinueOnFailure("Verify Nickname column is present",getAction().getText(SHIPPING_NICKNAME_TEXT).equals("Nickname"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Name column is present",getAction().getText(SHIPPING_NAME_TEXT).equals("Name"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Address 1 column is present",getAction().getText(SHIPPING_ADDRESS1_TEXT).equals("Address 1"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Address 2 column is present",getAction().getText(SHIPPING_ADDRESS2_TEXT).equals("Address 2"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify City column is present",getAction().getText(SHIPPING_CITY_TEXT).equals( "City"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify State column is present",getAction().getText(SHIPPING_STATE_TEXT).equals( "State"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Zipy column is present",getAction().getText(SHIPPING_ZIP_TEXT).equals( "Zip"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Country column is present",getAction().getText(SHIPPING_COUNTRY_TEXT).equals( "Country"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Store Phone # column is present",getAction().getText(SHIPPING_STORE_PHONE_NO_TEXT).equals( "Store Phone #"));

				/*PageAssert.verifyEqual(nickname.toLowerCase(), getAction().getText(SHIPPING_NICKNAME_DETAIL).toLowerCase());
      	            Logger.log("Verify Nickname for Sales Check No: " + saleschecknumber+" is " +nickname, TestStepType.STEP);
				 */
				if(ffm.equals("SYWM")){
					Reporter.log("Verify Data for Shipping Address Successful");
				} else{
					if(ffm.equals("SPU")){

						SoftAssert.checkConditionAndContinueOnFailure("Verify Store Name for Sales",getAction().getText(SHIPPING_NAME_DETAIL).equals(name+","));
					}else{
						SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Name ",name.contains( getAction().getText(SHIPPING_NAME_DETAIL).trim().replace(",", ""))&&getAction().getText(SHIPPING_NAME_DETAIL).trim().length()>1);
					}

					SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Address1 ",address1.replace("  ", " ").contains(getAction().getText(SHIPPING_ADDRESS1_DETAIL).replace("\n", "").replace("\r", "").trim()));

					if(address2!=null){
						SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Address2 ",address2.equals(getAction().getText(SHIPPING_ADDRESS2_DETAIL).replace("\n", "").replace("\r", "").trim()));
					}else{
						address2="";
						SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Address2 ",address2.equals(getAction().getText(SHIPPING_ADDRESS2_DETAIL).replace("\n", "").replace("\r", "").trim()));

					}
					SoftAssert.checkConditionAndContinueOnFailure("Verify Customer City",city.equalsIgnoreCase(getAction().getText(SHIPPING_CITY_DETAIL).replace("\n", "").replace("\r", "").trim()));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Customer State",state.equals( getAction().getText(SHIPPING_STATE_DETAIL).replace("\n", "").replace("\r", "").trim()));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Zipcode",zipcode.equals(getAction().getText(SHIPPING_ZIP_DETAIL).replace("\n", "").replace("\r", "").trim()));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Country",country.equals(getAction().getText(SHIPPING_COUNTRY_DETAIL).replace("\n", "").replace("\r", "").trim()));

					//PageAssert.verifyEqual(store_phone_no, getAction().getText(SHIPPING_STORE_PHONE_NO_DETAIL).replaceAll("[\\D]", ""));
					//validatePhoneNumber(getAction().getText(SHIPPING_STORE_PHONE_NO_DETAIL));
					//xxxxxxxxxx ,(xxx) xxx-xxxx, (xxx)xxx-xxxx , xxx-xxx-xxxx , xxx xxx xxxx
					// Logger.log("Verify Store Phone Number for Sales Check No: " + saleschecknumber+" is " +store_phone_no, TestStepType.STEP);
				}
				Reporter.log("Verified Shipping Address Table Successfully!");
			}else{

				PageAssert.fail("Failed Retrieval Data for Shipping Address");
			}

		}else{

			Logger.log(" There is no Sales Check Shipping Address informtioan in Database", TestStepType.STEP);

		}   


		String email = null;
		String dayphone = null;
		String nightphone = null;

		String sql_contact_info ="  select cci.EMAIL_ADDR_1, cci.PHONE_1, cci.PHONE_2 from ord o, customer_contact_info cci "
				+ "where o. BILLING_ADDRESS_ID = cci.ADDRESS_ID and o.site_gen_ord_id =?";
		//getAction().waitFor(1000);
		try {
			st = conn.prepareStatement(sql_contact_info);

			st.setString (1, OrderID);
			st.execute();
			rs = st.getResultSet();
			//getAction().waitFor(1000);
			while(rs.next()){
				email = rs.getString("EMAIL_ADDR_1");
				dayphone = rs.getString("PHONE_1") ;
				nightphone = rs.getString("PHONE_2");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Logger.log("Starting Verify Contact Information for Sales Check No: "+saleschecknumber, TestStepType.STEP);
		SoftAssert.checkConditionAndContinueOnFailure("Verify Contact Information column is present",getAction().getText(CONTACT_INFORMATION_TITLE).equals("Contact Information"));


		if(email!=null || dayphone!=null || nightphone!=null){
			if(getAction().isVisible(CONTACT_INFORMATION_TABLE)){
				SoftAssert.checkConditionAndContinueOnFailure("Verify Nickname column is present",getAction().getText(CONTACT_INFORMATION_EMAIL_TEXT).equals("Email"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Daytime Phone column is present",getAction().getText(CONTACT_INFORMATION_DPHONE_TEXT).equals("Daytime Phone"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Evening Phone column is present",getAction().getText(CONTACT_INFORMATION_EPHONE_TEXT).equals("Evening Phone"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Email ",email.equals(getAction().getText(CONTACT_INFORMATION_EMAIL_DETAIL)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Daytime Phone Number",dayphone.equals(getAction().getText(CONTACT_INFORMATION_DPHONE_DETAIL).replaceAll("[\\D]", "")));
				//SoftAssert.checkConditionAndContinueOnFailure("Validte Customer Daytime Phone Number Format",validatePhoneNumber(getAction().getText(CONTACT_INFORMATION_DPHONE_DETAIL)));

				if(nightphone == null || StringUtils.isEmpty(nightphone) || StringUtils.isBlank(nightphone) ){
					nightphone = "";
					SoftAssert.checkConditionAndContinueOnFailure("Validte Customer Nighttime Phone Number ",nightphone.equals(getAction().getText(CONTACT_INFORMATION_EPHONE_DETAIL).replaceAll("[\\D]", "")));
					//SoftAssert.checkConditionAndContinueOnFailure("Validte Customer Nighttime Phone Number Format",validatePhoneNumber(getAction().getText(CONTACT_INFORMATION_EPHONE_DETAIL)));

				}else{
					SoftAssert.checkConditionAndContinueOnFailure("Validte Customer Nighttime Phone Number ",nightphone.equals(getAction().getText(CONTACT_INFORMATION_EPHONE_DETAIL).replaceAll("[\\D]", "")));
					//SoftAssert.checkConditionAndContinueOnFailure("Validte Customer Nighttime Phone Number Format",validatePhoneNumber(getAction().getText(CONTACT_INFORMATION_EPHONE_DETAIL)));

				}

			}else{

				PageAssert.fail("Failed Retrieval Data for Sales Check Detail");
			}
		} else{

			Logger.log(" There is no Sales Check Contact informtioan in Database", TestStepType.STEP);

		}

		ArrayList<String> serialNumber  =  new ArrayList<String>();
		ArrayList<String> giftcardNumber  =  new ArrayList<String>();
		ArrayList<Double> amount  =  new ArrayList<Double>();
		String sql_return_gf ="select gcc.GIFT_CARD_SERIAL_NUMBER,goi.GIFT_CARD_NUMBER,goi.AMOUNT "
				+ "from ord o, ord_item oi, sales_check sc, gift_card_control gcc, giftcard_order_item goi "
				+ "where o.order_id = oi.order_id and oi.order_item_id = goi.order_item_id and oi.order_id = sc.order_id "
				+ "and sc.sales_check_number=? group by goi.email";

		try {
			st = conn.prepareStatement(sql_return_gf);
			st.setString (1, saleschecknumber);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				giftcardNumber.add(rs.getString("GIFT_CARD_NUMBER"));
				amount.add(rs.getDouble("AMOUNT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		SoftAssert.checkConditionAndContinueOnFailure("Verify Return Gift Card Information Title is Present",getAction().getText(RETURN_GIFT_CARD_INFO_TITLE).equals("Return Gift Card Information"));
		if(giftcardNumber.size()!=0 || amount.size()!=0){
			if(getAction().isVisible(RETURN_GIFT_CARD_INFO_TABLE)){
				Logger.log("Verify Discount Table is Visible", TestStepType.STEP);
				SoftAssert.checkConditionAndContinueOnFailure("Verify Serial # column is present",getAction().getText(RETURN_GIFT_CARD_INFO_SERIAL_NO_TEXT).equals("Serial #"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Card Number column is present",getAction().getText(RETURN_GIFT_CARD_INFO_CARD_NUMBER_TEXT).equals("Card #"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Price column is present",getAction().getText(RETURN_GIFT_CARD_INFO_AMOUNT_TEXT).equals("Price"));

				WebElement returngfcardtable = getAction().findElement(RETURN_GIFT_CARD_INFO_TABLE_CONTENT);
				List<WebElement> returngfcard_table_rows = returngfcardtable.findElements(By.tagName("tr"));
				int returngfcard_table_rows_count = returngfcard_table_rows.size();

				for (int i=0; i<returngfcard_table_rows_count; i++){
					List<WebElement> returngfcard_table_columns = returngfcard_table_rows.get(i).findElements(By.tagName("td"));
					//SoftAssert.checkConditionAndContinueOnFailure("Verify Serial #  in Return Gift Card ",serialNumber.get(i).equals(returngfcard_table_columns.get(0).getText()));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Number in Return Gift Card",giftcardNumber.get(i).equals(returngfcard_table_columns.get(1).getText()));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Amount in Return Gift Card",returngfcard_table_columns.get(2).getText().equals("$"+df.format(amount.get(i))));
					Logger.log("Verified return gift card information Successfully!", TestStepType.STEP);
				}
			}else{
				PageAssert.fail("Failed Retrieval Data for Sales Check Return Gift Card");

			}
		}else{
			Logger.log("No return gift card information found in Database", TestStepType.STEP);
			AjaxCondition.forElementVisible(RETURN_GIFT_CARD_INFO_NO_RESPONSE).waitForResponse(10);
		}

		ArrayList<String> description_page  =  new ArrayList<String>();
		ArrayList<Double> amount_page  =  new ArrayList<Double>();
		String sql_discount ="select oid.PROMOTION_NAME, oid.AMOUNT from ord_item_discount oid, ord_item oi, sales_check sc "
				+ "where oid.ORDER_ITEM_ID = oi.ORDER_ITEM_ID and oi.SALES_CHECK_ID = sc.SALES_CHECK_ID and sc.SALES_CHECK_NUMBER = ?";

		try {
			st = conn.prepareStatement(sql_discount);
			st.setString (1, saleschecknumber);
			st.execute();
			rs = st.getResultSet();

			while(rs.next()){

				description_page.add(rs.getString("PROMOTION_NAME"));
				amount_page.add(rs.getDouble("AMOUNT"));

				for(String des:description_page){
					for(int i=0; i<description_page.size();i++){
						System.out.print(des);}}

				for(Double amt:amount_page){
					for(int i=0; i<amount_page.size();i++){
						System.out.print(amt);}}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		SoftAssert.checkConditionAndContinueOnFailure("Verify Discount Title is Present",getAction().getText(DISCOUNTS_TITLE).equals("Discounts"));

		if(description_page.size()!=0 || amount_page.size()!=0){

			if(getAction().isVisible(DISCOUNTS_TABLE)){
				Logger.log("Verify Discount Table is Visible", TestStepType.STEP);

				SoftAssert.checkConditionAndContinueOnFailure("Verify Discount Description Column Name is Present",getAction().getText(DESCRIPTION_TEXT).equals("Discount Description"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Discount Amount Column Name is Present",getAction().getText(AMOUNT_TEXT).equals("Amount"));

				WebElement discountsstable = getAction().findElement(DISCOUNTS_TABLE_CONTENT );
				List<WebElement> disct_table_rows = discountsstable.findElements(By.tagName("tr"));
				int disct_table_rows_count = disct_table_rows.size();

				for (int i=0; i<disct_table_rows_count; i++){

					List<WebElement> disct_table_columns = disct_table_rows.get(i).findElements(By.tagName("td"));

					SoftAssert.checkConditionAndContinueOnFailure("Verify Description in Discount",description_page.get(i).equals(disct_table_columns.get(0).getText()));

					SoftAssert.checkConditionAndContinueOnFailure("Verify Amount in Discount",disct_table_columns.get(1).getText().equals("($"+df.format(Math.abs(amount_page.get(i)))+")"));
				}	
			}else{

				Logger.log("Failed retrieval data for Discounts ", TestStepType.STEP);
				PageAssert.fail("Retrieval Data from Database failed");
				
			}
		}else{
			SoftAssert.checkElementAndContinueOnFailure(DISCOUNTS_NO_RESPONSE, "There is no Discounts Information in Database", CheckLocatorFor.isPresent);

		}
		//getAction().waitFor(1000);


		SoftAssert.checkConditionAndContinueOnFailure("Verify Delivery Details Title column is present",getAction().getText(DELIVERY_DETAIL_TITLE).equals("Delivery Details"));

		if(getAction().isVisible(DELIVERYDETAILS)){
			Logger.log("Verify Delivery Table is Visible", TestStepType.STEP);
			SoftAssert.checkElementAndContinueOnFailure(DELIVERY_DETAIL_TITLE, "Delivery Title  is present", CheckLocatorFor.isPresent);
			SoftAssert.checkConditionAndContinueOnFailure("Verify Delivery Details Title column is present",getAction().getText(DELIVERY_DETAIL_TITLE).equals("Delivery Details"));
			SoftAssert.checkElementAndContinueOnFailure(DELIVERYDETAILS_DOS_NUMBER, "Delivery Details DOS  is present", CheckLocatorFor.isPresent);
			SoftAssert.checkElementAndContinueOnFailure(DELIVERYDETAILS_UPDATE_BUTTON, "Delivery Update Button is present", CheckLocatorFor.isPresent);
			SoftAssert.checkConditionAndContinueOnFailure("Verify Update Button is Disabled",!getAction().findElement(DELIVERYDETAILS_UPDATE_BUTTON).isEnabled());
			SoftAssert.checkElementAndContinueOnFailure(DELIVERYDETAILS_SPECIFIC_INSTRUCTION_EDIT_BUTTON, "Delivery Specific Instruction Edit Button is present", CheckLocatorFor.isPresent);
			SoftAssert.checkConditionAndContinueOnFailure("Verify Specific Instruction Edit Button is Disabled",!getAction().findElement(DELIVERYDETAILS_SPECIFIC_INSTRUCTION_EDIT_BUTTON).isEnabled());
			SoftAssert.checkElementAndContinueOnFailure(DELIVERYDETAILS_DELIVERY_INSTRUCTION_EDIT_BUTTON, "Delivery Instruction Edit Button is present", CheckLocatorFor.isPresent);
			SoftAssert.checkConditionAndContinueOnFailure("Verify Delivery Instruction Edit Button is Disabled",!getAction().findElement(DELIVERYDETAILS_DELIVERY_INSTRUCTION_EDIT_BUTTON).isEnabled());

			SoftAssert.checkElementAndContinueOnFailure(DELIVERYDETAILS_TABS.format("Order Detail"), "Order Detail Tab is present", CheckLocatorFor.isPresent);
			getAction().waitFor(2000);
			
			Logger.log("Order Details  is present", TestStepType.VERIFICATION_PASSED);
			getAction().focus(DELIVERYDETAILS_TABS.format("Order Detail"));
			getAction().click(DELIVERYDETAILS_TABS.format("Order Detail"));
			
			Logger.log("Delivery Details  is present", TestStepType.VERIFICATION_PASSED);
			SoftAssert.checkElementAndContinueOnFailure(DELIVERYDETAILS_TABS.format("Delivery Detail"), "Delivery Detail Tab is present", CheckLocatorFor.isPresent);
			getAction().click(DELIVERYDETAILS_TABS.format("Delivery Detail"));
			
			Logger.log("Miscellaneous  is present", TestStepType.VERIFICATION_PASSED);
			SoftAssert.checkElementAndContinueOnFailure(DELIVERYDETAILS_TABS.format("Miscellaneous"), "Miscellaneous Tab is present", CheckLocatorFor.isPresent);
			getAction().waitFor(2000);
			getAction().click(DELIVERYDETAILS_TABS.format("Miscellaneous"));
			
			Logger.log("Delivery Notes is present", TestStepType.VERIFICATION_PASSED);
			SoftAssert.checkElementAndContinueOnFailure(DELIVERYDETAILS_TABS.format("Delivery Notes"), "Delivery Notes Tab is present ", CheckLocatorFor.isPresent);
			getAction().waitFor(2000);
			getAction().click(DELIVERYDETAILS_TABS.format("Delivery Notes"));
			
			Logger.log("Route Tracking is present", TestStepType.VERIFICATION_PASSED);
			SoftAssert.checkElementAndContinueOnFailure(DELIVERYDETAILS_TABS.format("Route Tracking"), "Route Tracking Tab is present ", CheckLocatorFor.isPresent);
			getAction().waitFor(2000);
			getAction().click(DELIVERYDETAILS_TABS.format("Route Tracking"));
			
			Logger.log("Action Center is present", TestStepType.VERIFICATION_PASSED);
			SoftAssert.checkElementAndContinueOnFailure(DELIVERYDETAILS_TABS.format("Action Center"), "Action Center Tab is present ", CheckLocatorFor.isPresent);
			getAction().waitFor(2000);
			getAction().click(DELIVERYDETAILS_TABS.format("Action Center"));
			 	

		}else{

			Logger.log("Delivery Table is not visible", TestStepType.STEP);

		}


		String Sql_payment ="select pm.PAYMENT_METHOD_DS, pi.TOTAL_AMT "
				+ "from pmt_method pm, payment_instruction pi, ord o "
				+ "where o.order_id = pi.order_id and pi.payment_method_id = pm.payment_method_id "
				+ "and site_gen_ord_id = ?";
		ArrayList<String> payment_type  =  new ArrayList<String>();
		ArrayList<Double> payment_amount =  new ArrayList<Double>();
		String card_number_ws = null;
		String expire_month_ws = null;
		String expire_year_ws = null;


		/****
		 * data from mysql db
		 ****/
		try {

			st = conn.prepareStatement(Sql_payment);

			st.setString (1, OrderID);
			st.execute();
			rs = st.getResultSet();
			//getAction().waitFor(1000);
			while(rs.next()){

				payment_type.add(rs.getString("PAYMENT_METHOD_DS"));
				payment_amount.add(rs.getDouble("TOTAL_AMT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/***
		 * data from web services
		 ****/
		JSONObject orderSummary = verifySoapDetailsSalesCheck(OrderID, storeId);


		/***
		 * verify text present
		 ***/

		SoftAssert.checkConditionAndContinueOnFailure("Verify Payments Title column is present",getAction().getText(PAYMENTS_TITLE).equals("Payments"));

		if(payment_type!=null || payment_amount!=null || card_number_ws!=null || expire_month_ws!=null || expire_year_ws!=null){

			if( getAction().isVisible(PAYMENTS_TABLE)){
				Logger.log("Verify Payments Table is Visible", TestStepType.STEP);
				SoftAssert.checkConditionAndContinueOnFailure("Verify Payments Type Column Name is Present",getAction().getText(PAYMENTS_TYPE_TEXT).equals("Payment Type"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Payments Card Number Column Name is Present",getAction().getText(PAYMENTS_CARD_NUMBER_TEXT).equals("Card Number"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Payment Amount Column Name is Present",getAction().getText(PAYMENTS_AMOUNT_TEXT).equals("Amount"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Expiration Date Column Name is Present",getAction().getText(PAYMENTS_EXPIRATION_DATE_TEXT).equals("Expiration Date"));

				/***
				 * verify value
				 ***/	

				WebElement paymentstable = getAction().findElement(PAYMENTS_TABLE_CONTENT);
				List<WebElement> payment_table_rows = paymentstable.findElements(By.tagName("tr"));
				int payment_table_rows_count = payment_table_rows.size();

				try
				{
					if (orderSummary.get("PaymentDetails") instanceof JSONObject)
					{
						JSONObject paymentDetailsObj = orderSummary.getJSONObject("PaymentDetails");
						card_number_ws = (String.valueOf(paymentDetailsObj.get("MaskedCardNumber")));

						List<WebElement> payment_table_columns = payment_table_rows.get(0).findElements(By.tagName("td"));
						SoftAssert.checkConditionAndContinueOnFailure("Verify Payments Type in Payments",payment_type.get(0).equals(payment_table_columns.get(0).getText()));
						SoftAssert.checkConditionAndContinueOnFailure("Verify Card Number in Payments",card_number_ws.equals(payment_table_columns.get(2).getText()));

						if(payment_type.get(0).equalsIgnoreCase("CreditCard"))
						{
							expire_month_ws = String.valueOf(paymentDetailsObj.getInt("ExpiryMonth"));
							expire_year_ws = String.valueOf(paymentDetailsObj.getInt("ExpiryYear"));
							SoftAssert.checkConditionAndContinueOnFailure("Verify Expiration Date in Payments",payment_table_columns.get(5).getText().equals("** / ****"));

						}
						SoftAssert.checkConditionAndContinueOnFailure("Verify Payment Amount in Payments",payment_table_columns.get(3).getText().equals("$"+String.format("%.2f", payment_amount.get(0))));

					}    		

					else if (orderSummary.get("PaymentDetails") instanceof JSONArray)
					{
						JSONArray paymentDetailsArray = orderSummary.getJSONArray("PaymentDetails");

						Logger.log("Verify number of payment options is same in site and service response",TestStepType.VERIFICATION_STEP);
						PageAssert.verifyEqual(payment_table_rows_count, paymentDetailsArray.length());

						for (int i=0; i<payment_table_rows_count; i++){
							JSONObject tempObject = (JSONObject)paymentDetailsArray.getJSONObject(i);

							card_number_ws = (String.valueOf(tempObject.get("CardNumber")));

							List<WebElement> payment_table_columns = payment_table_rows.get(0).findElements(By.tagName("td"));
							SoftAssert.checkConditionAndContinueOnFailure("Verify Payments Type in Payments",payment_type.get(i).equals(payment_table_columns.get(2).getText()));
							SoftAssert.checkConditionAndContinueOnFailure("Verify Card Number in Payments",card_number_ws.equals(payment_table_columns.get(0).getText()));

							if(payment_type.get(i).equalsIgnoreCase("CreditCard"))
							{    	        			
								expire_month_ws = String.valueOf(tempObject.getInt("ExpiryMonth"));
								expire_year_ws = String.valueOf(tempObject.getInt("ExpiryYear"));
								SoftAssert.checkConditionAndContinueOnFailure("Verify Expiration Date in Payments",payment_table_columns.get(6).getText().equals(expire_month_ws.replaceAll("\\d+.*", "")+"/"+expire_year_ws.replaceAll("\\d+.*", "")));
							}
							SoftAssert.checkConditionAndContinueOnFailure("Verify Payment Amount in Payments",payment_table_columns.get(3).getText().equals("$"+String.format("%.2f", payment_amount.get(i))));

						}
					}

					else{
						Logger.log("Payments Table is not visible", TestStepType.STEP);
					}}

				catch (Exception e) {				
					e.printStackTrace();
				}  
			}else{

				PageAssert.fail("Failed Retrieval Data for Payments");
			}
		}else{

			Logger.log(" There is no Payment informtioan in Database", TestStepType.STEP);}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;
	}	

	public SalesCheckDetailsPage verifyOptionVisible(String optionName){

		Logger.log("Verify "+optionName+" is Visible in DropDownList", TestStepType.VERIFICATION_SUBSTEP);
		AjaxCondition.forElementVisible(ACTION_SELECT_BOX).waitForResponse(5);
		PageAssert.textPresentIn(ACTION_SELECT_BOX, optionName);
		
		return this;

	}

	public SalesCheckDetailsPage verifyOptionNotVisible(String optionName){

		Logger.log("Verify "+optionName+" is Not Visible in DropDownList", TestStepType.VERIFICATION_SUBSTEP);
		AjaxCondition.forElementVisible(ACTION_SELECT_BOX).waitForResponse(5);
		PageAssert.textNotPresentIn(ACTION_SELECT_BOX, optionName);
		
		return this;

	}



}