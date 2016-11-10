package com.shc.msp.ft.pages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.shc.automation.AjaxCondition;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.PageAssert;
import com.shc.automation.SoftAssert;
import com.shc.automation.utils.TestUtils.CheckLocatorFor;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.util.MysqlDBConnection;
import com.shc.msp.ft.util.SOAPRequest;
import com.shc.msp.ft.util.Utility;


public class LineItemDetailsPage extends Page {
	
	public static final Locator ITEM_NUMBER = new Locator ("ITEM_NUMBER","//legend[contains(text(),'Item # ')]","Item #");
	public static final Locator ORDERDETAILS_ACTION_SELECT_BOX = new Locator("", "//div[@ng-if='actions']//select", "Select action drop down");
	public static final Locator SCHEDULERETURN_POP_UP = new Locator("", "//div[@class='modal-dialog']//div[@class='modal-content']//div//h4[text()='Schedule Return']","Retrun Schedule Pop Up");
	public static final Locator SCHEDULERETURN_ACTION_TEXT = new Locator("", "//p[text()='Schedule Return']","Action: Schedule Return");
	//LINE ITEM SUMMARY :
	public static final Locator LINE_ITEM_SUMMARY_TITLE = new Locator("LINE_ITEM_SUMMARY_TITLE", "//legend[text()='Line Item Summary']", "Line Item Summary Title");
	public static final Locator LINE_ITEM_SUMMARY_TABLE = new Locator("LINE_ITEM_SUMMARY_TABLE", "//legend[text()='Line Item Summary']/following-sibling::form", "Line Item Summary Table");
	public static final Locator LINE_ITEM_EXPECTED_SHIP_DATE_TEXT = new Locator("LINE_ITEM_SUMMARY_EXPECTED_SHIP_DATE_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Expected Ship Date']", "Line Item Summary Expected Ship Date Text");
	public static final Locator LINE_ITEM_EXPECTED_ARRIVAL_DATE_TEXT = new Locator("LINE_ITEM_SUMMARY_EXPECTED_ARRIVAL_DATE_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Expected Arrival Date']", "Line Item Summary Expected Arrival Date Text");
	public static final Locator LINE_ITEM_ITEM_STATUS_TEXT = new Locator("LINE_ITEM_SUMMARY_ITEM_STATUS_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Item Status']", "Line Item Summary Item Status Text");
	public static final Locator LINE_ITEM_SALES_CHECK_NUMBER_TEXT = new Locator("LINE_ITEM_SUMMARY_SALES_CHECK_NUMBER_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Sales Check #']", "Line Item Summary Sales Check # Text");
	public static final Locator LINE_ITEM_FULFILL_BY_TEXT = new Locator("LINE_ITEM_SUMMARY_FULFILL_BY_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Fulfill By']", "Line Item Summary Fulfill By Text");
	public static final Locator LINE_ITEM_ZERO_PERCENT_TEXT = new Locator("LINE_ITEM_SUMMARY_ZERO_PERCENT_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Zero Percent']", "Line Item Summary Zero Percent Text");
	public static final Locator LINE_ITEM_SKU_TEXT = new Locator("LINE_ITEM_SUMMARY_SKU_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='SKU']", "Line Item Summary SKU Text");
	public static final Locator LINE_ITEM_DESCRIPTION_TEXT = new Locator("LINE_ITEM_SUMMARY_DESCRIPTION_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Description']", "Line Item Summary Description Text");
	public static final Locator LINE_ITEM_QUANTITY_TEXT = new Locator("LINE_ITEM_SUMMARY_QUANTITY_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Quantity']", "Line Item Summary Quantity Text");
	public static final Locator LINE_ITEM_MFR_MODEL_NUMBER_TEXT = new Locator("LINE_ITEM_SUMMARY_MFR_MODEL_NUMBER_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Mfr Model #']", "Line Item Summary Mfr Model # Text");
	public static final Locator LINE_ITEM_STORE_TEXT = new Locator("LINE_ITEM_SUMMARY_STORE_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Store']", "Line Item Summary Store Text");
	public static final Locator LINE_ITEM_WEIGHT_TEXT = new Locator("LINE_ITEM_SUMMARY_WEIGHT_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Weight (in lbs)']", "Line Item Summary Weight (in lbs) Text");
	public static final Locator LINE_ITEM_PRICE_TEXT = new Locator("LINE_ITEM_SUMMARY_PRICE_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Price']", "Line Item Summary Price Text");
	public static final Locator LINE_ITEM_TAX_TEXT = new Locator("LINE_ITEM_SUMMARY_TAX_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Tax']", "Line Item Summary Tax Text");
	public static final Locator LINE_ITEM_TAX_PERCENT_TEXT = new Locator("LINE_ITEM_SUMMARY_TAX_PERCENT_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Tax Percent']", "Line Item Summary Tax Percent Text");
	public static final Locator LINE_ITEM_SURCHARGE_AMT_TEXT = new Locator("LINE_ITEM_SUMMARY_SURCHARGE_AMT_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Surcharge Amt']", "Line Item Summary Surcharge Amt Text");
	public static final Locator LINE_ITEM_ENVIRONMENTAL_FEE_TEXT = new Locator("LINE_ITEM_SUMMARY_ENVIRONMENTAL_FEE_TEXT", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Environmental Fee']", "Line Item Summary Environmental Fee Text");
	
	public static final Locator LINE_ITEM_EXPECTED_SHIP_DATE_DETAIL = new Locator("LINE_ITEM_EXPECTED_SHIP_DATE_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Expected Ship Date']/following-sibling::div/p", "Line Item Summary Expected Ship Date Detail");
	public static final Locator LINE_ITEM_EXPECTED_ARRIVAL_DATE_DETAIL = new Locator("LINE_ITEM_EXPECTED_ARRIVAL_DATE_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Expected Arrival Date']/following-sibling::div/p", "Line Item Summary Expected Arrival Date Detail");
	public static final Locator LINE_ITEM_ITEM_STATUS_DETAIL = new Locator("LINE_ITEM_ITEM_STATUS_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Item Status']/following-sibling::div/p", "Line Item Summary Item Status Detail");
	public static final Locator LINE_ITEM_SALES_CHECK_NUMBER_DETAIL = new Locator("LINE_ITEM_SALES_CHECK_NUMBER_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Sales Check #']/following-sibling::div/p", "Line Item Summary Sales Check # By Detail");
	public static final Locator LINE_ITEM_FULFILL_BY_DETAIL = new Locator("LINE_ITEM_FULFILL_BY_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Fulfill By']/following-sibling::div/p", "Line Item Summary Fulfill By Detail");
	public static final Locator LINE_ITEM_ZERO_PERCENT_DETAIL = new Locator("LINE_ITEM_ZERO_PERCENT_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Zero Percent']/following-sibling::div/p", "Line Item Summary Zero Percent  Detail");
	public static final Locator LINE_ITEM_SKU_DETAIL = new Locator("LINE_ITEM_SKU_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='SKU']/following-sibling::div/p", "Line Item Summary SKU Detail");
	public static final Locator LINE_ITEM_DESCRIPTION_DETAIL = new Locator("LINE_ITEM_DESCRIPTION_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Description']/following-sibling::div/p", "Line Item Summary Description Detail");
	public static final Locator LINE_ITEM_QUANTITY_DETAIL = new Locator("LINE_ITEM_QUANTITY_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Quantity']/following-sibling::div/p", "Line Item Summary Quantity Detail");
	public static final Locator LINE_ITEM_MFR_MODEL_NUMBER_DETAIL = new Locator("LINE_ITEM_MFR_MODEL_NUMBER_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Mfr Model #']/following-sibling::div/p", "Line Item Summary Mfr Model # Detail");
	public static final Locator LINE_ITEM_STORE_DETAIL = new Locator("LINE_ITEM_STORE_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Store']/following-sibling::div/p", "Line Item Summary Store Detail");
	public static final Locator LINE_ITEM_WEIGHT_DETAIL = new Locator("LINE_ITEM_WEIGHT_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Weight (in lbs)']/following-sibling::div/p", "Line Item Summary Weight (in lbs) Detail");
	public static final Locator LINE_ITEM_PRICE_DETAIL = new Locator("LINE_ITEM_PRICE_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Price']/following-sibling::div/p", "Line Item Summary Price Detail");
	public static final Locator LINE_ITEM_TAX_DETAIL = new Locator("LINE_ITEM_TAX_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Tax']/following-sibling::div/p", "Line Item Summary Tax Detail");
	public static final Locator LINE_ITEM_TAX_PERCENT_DETAIL = new Locator("LINE_ITEM_TAX_PERCENT_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Tax Percent']/following-sibling::div/p", "Line Item Summary Tax Percent Detail");
	public static final Locator LINE_ITEM_SURCHARGE_AMT_DETAIL = new Locator("LINE_ITEM_SURCHARGE_AMT_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Surcharge Amt']/following-sibling::div/p", "Line Item Summary Surcharge Amt Detail");
	public static final Locator LINE_ITEM_ENVIRONMENTAL_FEE_DETAIL = new Locator("LINE_ITEM_ENVIRONMENTAL_FEE_DETAIL", "//legend[text()='Line Item Summary']/following-sibling::form//label[text()='Environmental Fee']/following-sibling::div/p", "Line Item Summary Environmental Fee Detail");
	
	//SHIPPING INFORMATION :
	public static final Locator SHIPPING_INFORMATION_TITLE = new Locator("SHIPPING_INFORMATION_TITLE", "//legend[text()='Shipping Information']", "Shipping Information Title");
	public static final Locator SHIPPING_INFORMATION_TABLE = new Locator("SHIPPING_INFORMATION_TABLE", "//legend[text()='Shipping Information']/following-sibling::form", "Shipping Information Table");
	public static final Locator SHIPPING_INFO_SHIPPING_METHOD_TEXT = new Locator("SHIPPING_INFO_SHIPPING_METHOD_TEXT", "//legend[text()='Shipping Information']/following-sibling::form//label[text()='Shipping Method']", "Shipping Information Shipping Method Text");
	public static final Locator SHIPPING_INFO_SHIPPING_CHARGE_TEXT = new Locator("SHIPPING_INFO_SHIPPING_CHARGE_TEXT", "//legend[text()='Shipping Information']/following-sibling::form//label[text()='Shipping Charge']", "Shipping Information Shipping Charge Text");
	public static final Locator SHIPPING_INFO_SHIPPED_QUANTITY_TEXT = new Locator("SHIPPING_INFO_SHIPPED_QUANTITY_TEXT", "//legend[text()='Shipping Information']/following-sibling::form//label[text()='Shipped Quantity']", "Shipping Information Shipped Quantity Text");
	public static final Locator SHIPPING_INFO_SHIPPING_METHOD_DETAIL = new Locator("SHIPPING_INFO_SHIPPING_METHOD_DETAIL", "//legend[text()='Shipping Information']/following-sibling::form//label[text()='Shipping Method']/following-sibling::div/p", "Shipping Information Shipping Method Detail");
	public static final Locator SHIPPING_INFO_SHIPPING_CHARGE_DETAIL = new Locator("SHIPPING_INFO_SHIPPING_CHARGE_DETAIL", "//legend[text()='Shipping Information']/following-sibling::form//label[text()='Shipping Charge']/following-sibling::div/p", "Shipping Information Shipping Charge Detail");
	public static final Locator SHIPPING_INFO_SHIPPED_QUANTITY_DETAIL = new Locator("SHIPPING_INFO_SHIPPED_QUANTITY_DETAIL", "//legend[text()='Shipping Information']/following-sibling::form//label[text()='Shipped Quantity']/following-sibling::div/p", "Shipping Information Shipped Quantity Detail");

	//Tracking Detail :
	public static final Locator TRACKING_DETAIL_TITLE = new Locator("TRACKING_DETAIL_TITLE", "//legend[text()='Tracking Details']", "Tracking Details Title");
	public static final Locator TRACKING_DETAIL_TABLE = new Locator("TRACKING_DETAIL_TABLE", "//legend[text()='Tracking Details']/following-sibling::div//table", "Tracking Details Table");
	public static final Locator TRACKING_CARRIER_TEXT = new Locator("TRACKING CARRIER TEXT","((//legend[text()='Tracking Details']/following-sibling::div//table//tr)[1]//th)[1]","Tracking Carrier Text");
	public static final Locator TRACKING_NUMBER_TEXT = new Locator("TRACKING NUMBER TEXT","((//legend[text()='Tracking Details']/following-sibling::div//table//tr)[1]//th)[2]","Tracking Carrier Text");
	public static final Locator TRACKING_CARRIER = new Locator("TRACKING CARRIER","((//legend[text()='Tracking Details']/following-sibling::div//table//tr)[2]//td)[1]","Tracking Carrier Text");
	public static final Locator TRACKING_NUMBER = new Locator("TRACKING NUMBER","((//legend[text()='Tracking Details']/following-sibling::div//table//tr)[2]//td)[2]","Tracking Carrier Text");
	
	public static final Locator TRACKING_DETAIL_NO_RESPONSE = new Locator("TRACKING_DETAIL_NO_RESPONSE", "//span[text() = 'No tracking records found']","Tracking Deatil No Response");
	
	//Return Information
	public static final Locator RETURN_INFORMATION_TITLE = new Locator("RETURN_INFORMATION_TITLE", "//legend[text()='Return Information']", "Return Information Title");
	public static final Locator PREVIOUSE_RETURN_QTY_TEXT = new Locator("PREVIOUSE_RETURN_QTY_TEXT", "(//div[@ng-if='selectedLineItem.returnInfo'])[1]", "Previous Return Qty");
	public static final Locator RETURN_INFORMATION_TABLE = new Locator("RETURN_INFORMATION_TABLE", "(//div[@ng-if='selectedLineItem.returnInfo'])[2]//table","Return Informtion Table");
	public static final Locator RETURN_INFORMATION_SC_TEXT = new Locator("RETURN_INFORMATION_SC_TEXT", "(//div[@ng-if='selectedLineItem.returnInfo'])[2]//table/thead/tr/th[1]","Return Information Sales Check Number");
	public static final Locator RETURN_INFORMATION_AMT_TEXT = new Locator("RETURN_INFORMATION_AMT_TEXT","(//div[@ng-if='selectedLineItem.returnInfo'])[2]//table/thead/tr/th[2]","Return Information Amount");
	public static final Locator RETURN_INFORMATION_RGI_NO_TEXT = new Locator("RETURN_INFORMATION_RGI_NO_TEXT","(//div[@ng-if='selectedLineItem.returnInfo'])[2]//table/thead/tr/th[3]","Return Information RGI #");
	public static final Locator RETURN_INFORMATION_RMA_NO_TEXT = new Locator("RETURN_INFORMATION_RMA_NO_TEXT","(//div[@ng-if='selectedLineItem.returnInfo'])[2]//table/thead/tr/th[4]","Return Information RMA #");
	public static final Locator RETURN_INFORMATION_REASON_CODE_TEXT = new Locator("RETURN_INFORMATION_REASON_CODE_TEXT","(//div[@ng-if='selectedLineItem.returnInfo'])[2]//table/thead/tr/th[5]","Return Information Reason Code");
	public static final Locator RETURN_INFORMATION_RETURN_DATE_TEXT = new Locator("RETURN_INFORMATION_RETURN_DATE_TEXT","(//div[@ng-if='selectedLineItem.returnInfo'])[2]//table/thead/tr/th[6]","Return Information Return Date");
	public static final Locator RETURN_INFORMATION_TABLE_CONTENT = new Locator("RETURN_INFORMATION_TABLE_CONTENT", "(//div[@ng-if='selectedLineItem.returnInfo'])[2]//table//tbody","Return Informtion Table Content");
	public static final Locator RETURN_INFORMATION_NO_RESPONSE = new Locator("RETURN_INFORMATION_NO_RESPONSE","//span[text()='No return records found']|//label[text()='Previous Return Qty: ']","Return Information No Response");
	//public static final Locator RETURN_INFORMATION_RETURN_QTY = new Locator("RETURN_INFORMATION_RESPONSE","//label[text()='Previous Return Qty: ']","Return Information  Response");
	
	
	//Return Tracking Information :
	public static final Locator RETURN_TRACKING_INFORMATION_TITLE = new Locator("RETURN_TRACKING_INFORMATION_TITLE", "//legend[text()='Return Tracking Information']", "Return Tracking Information Title");
	public static final Locator RETURN_TRACKING_INFORMATION_TABLE = new Locator("RETURN_TRACKING_INFORMATION_TABLE", "//legend[text()='Return Tracking Information']/following-sibling::div//table", "Return Tracking Information Table");
	public static final Locator RETURN_TRACKING_DATE_TEXT = new Locator("RETURN_TRACKING_DATE_TEXT", "((//legend[text()='Return Tracking Information']/following-sibling::div//table//tr)[1]//th)[1]", "Return Tracking Date Text");
	public static final Locator RETURN_TRACKING_CARRIER_TEXT = new Locator("RETURN_TRACKING_CARRIER_TEXT", "((//legend[text()='Return Tracking Information']/following-sibling::div//table//tr)[1]//th)[2]", "Return Tracking Carrier Text");
	public static final Locator RETURN_TRACKING_NUMBER_TEXT = new Locator("RETURN_TRACKING_NUMBER_TEXT", "((//legend[text()='Return Tracking Information']/following-sibling::div//table//tr)[1]//th)[3]", "Return Tracking Number Text");
	public static final Locator RETURN_TRACKING_QTY_TEXT = new Locator("RETURN_TRACKING_QTY_TEXT", "((//legend[text()='Return Tracking Information']/following-sibling::div//table//tr)[1]//th)[4]", "Return Tracking Quantity Text");
	public static final Locator RETURN_TRACKING_INFORMATION_TABLE_CONTENT = new Locator("RETURN_TRACKING_INFORMATION_TABLE_CONTENT", "//legend[text()='Return Tracking Information']/following-sibling::div//table//tbody", "Return Tracking Information Table Content");
	public static final Locator RETURN_TRACKING_INFORMATION_NO_RESPONSE = new Locator("RETURN_TRACKING_INFORMATION_NO_RESPONSE", "//span[text() = 'No return tracking records found']","Return Tracking Information No Response");
	
	//Installation Information:
	public static final Locator INSTALLATION_INFO_TITLE = new Locator("INSTALLATION_INFO_TITLE", "//legend[text()='Installation Information']", "Installation Information Title");
	public static final Locator INSTALLATION_INFO_TABLE = new Locator("INSTALLATION_INFO_TABLE", "//legend[text()='Installation Information']/following-sibling::div//form", "Installation Information Table");
	public static final Locator INSTALLATION_INFO_BASE_INSTAL_COST_TEXT = new Locator("INSTALLATION_INFO_BASE_INSTAL_COST_TEXT", "//label[text()='Base Installation Cost']", "Installation Information Base Installation Cost Text");
	public static final Locator INSTALLATION_INFO_DISTANCE_CHARGE_TEXT = new Locator("INSTALLATION_INFO_DISTANCE_CHARGE_TEXT", "//label[text()='Distance Charge']", "Installation Information Distance Charge Text");
	public static final Locator INSTALLATION_INFO_INSTALL_DATE_TEXT = new Locator("INSTALLATION_INFO_INSTAL_DATE_TEXT", "//label[text()='Installation Date']", "Installation Information Installation Date Text");
	public static final Locator INSTALLATION_INFO_INCLUSION_TEXT_TEXT = new Locator("INSTALLATION_INFO_INCLUSION_TEXT_TEXT", "//label[text()='Inclusion Text']", "Installation Information Inclusion Text Text");
	public static final Locator INSTALLATION_INFO_PROD_DETAILS_TEXT = new Locator("INSTALLATION_INFO_PROD_DETAILS_TEXT", "//label[text()='Product Details']", "Installation Information Product Details Text");
	public static final Locator INSTALLATION_INFO_STATEMENTS_TEXT = new Locator("INSTALLATION_INFO_STATEMENTS_TEXT", "//label[text()='Statements']", "Installation Information Statements Text");
	public static final Locator INSTALLATION_INFO_STORE_RINGING_INSTR_TEXT = new Locator("INSTALLATION_INFO_STORE_RINGING_INSTR_TEXT", "//label[text()='Store Ringing Instr']", "Installation Information Store Ringing Instr Text");
	public static final Locator INSTALLATION_INFO_STORE_NUMBER_TEXT = new Locator("INSTALLATION_INFO_STORE_NUMBER_TEXT", "//label[text()='Store Number']", "Installation Information Store Number Text");
	public static final Locator INSTALLATION_INFO_INSTALL_ADDRESS_TEXT = new Locator("INSTALLATION_INFO_INSTALL_ADDRESS_TEXT", "//label[text()='Installation Address']", "Installation Information Installation Address Text");
	public static final Locator INSTALLATION_INFO_BASE_INSTAL_COST = new Locator("INSTALLATION_INFO_BASE_INSTAL_COST", "//label[text()='Base Installation Cost']/following-sibling::div//p", "Installation Information Base Installation Cost");
	public static final Locator INSTALLATION_INFO_DISTANCE_CHARGE = new Locator("INSTALLATION_INFO_DISTANCE_CHARGE", "//label[text()='Distance Charge']/following-sibling::div//p", "Installation Information Distance Charge");
	public static final Locator INSTALLATION_INFO_INSTALL_DATE = new Locator("INSTALLATION_INFO_INSTAL_DATE", "//label[text()='Installation Date']/following-sibling::div//p", "Installation Information Installation Date");
	public static final Locator INSTALLATION_INFO_INCLUSION_TEXT = new Locator("INSTALLATION_INFO_INCLUSION_TEXT", "//label[text()='Inclusion Text']/following-sibling::div//p", "Installation Information Inclusion Text");
	public static final Locator INSTALLATION_INFO_PROD_DETAILS = new Locator("INSTALLATION_INFO_PROD_DETAILS", "//label[text()='Product Details']/following-sibling::div//p", "Installation Information Product Details");
	public static final Locator INSTALLATION_INFO_STATEMENTS = new Locator("INSTALLATION_INFO_STATEMENTS", "//div[@ng-if='selectedLineItem.installationInfo']//div[@class='row'][4]//p", "Installation Information Statements");
	public static final Locator INSTALLATION_INFO_STORE_RINGING_INSTR = new Locator("INSTALLATION_INFO_STORE_RINGING_INSTR", "//label[text()='Store Ringing Instr']/following-sibling::div//p", "Installation Information Store Ringing Instr");
	public static final Locator INSTALLATION_INFO_STORE_NUMBER = new Locator("INSTALLATION_INFO_STORE_NUMBER", "//label[text()='Store Number']/following-sibling::div//p", "Installation Information Store Number");
	public static final Locator INSTALLATION_INFO_INSTALL_ADDRESS1 = new Locator("INSTALLATION_INFO_INSTALL_ADDRESS1", "(//label[text()='Installation Address']/following-sibling::div//p)[1]", "Installation Information Installation Address1");
	public static final Locator INSTALLATION_INFO_INSTALL_ADDRESS2 = new Locator("INSTALLATION_INFO_INSTALL_ADDRESS2", "(//label[text()='Installation Address']/following-sibling::div//p)[2]", "Installation Information Installation Address2");
	public static final Locator INSTALLATION_INFO_INSTALL_CITY_STATE = new Locator("INSTALLATION_INFO_INSTALL_CITY_STATE", "(//label[text()='Installation Address']/following-sibling::div//p)[3]", "Installation Information City and State");
	public static final Locator IINSTALLATION_INFO_NO_RESPONSE = new Locator("IINSTALLATION_INFO_NO_RESPONSE", "//span[text() = 'No installation records found']","Installation Information No Response");

	//Gift Card Information:
	public static final Locator GIFT_CARD_INFO_TITLE = new Locator("GIFT_CARD_INFO_TITLE", "//legend[text()='Gift Card Information']", "Gift Card Information Title");
	public static final Locator GIFT_CARD_INFO_TABLE = new Locator("GIFT_CARD_INFO_TABLE", "//legend[text()='Gift Card Information']/following-sibling::div//table", "Gift Card Information Table");
	public static final Locator GIFT_CARD_INFO_SERIAL_NUMBER_TEXT = new Locator("GIFT_CARD_INFO_SERIAL_NUMBER_TEXT", "((//legend[text()='Gift Card Information']/following-sibling::div//table//tr)[1]//th)[1]", "Gift Card Information Serial Number Text");
	public static final Locator GIFT_CARD_INFO_CARD_NUMBER_TEXT = new Locator("GIFT_CARD_INFO_CARD_NUMBER_TEXT", "((//legend[text()='Gift Card Information']/following-sibling::div//table//tr)[1]//th)[2]", "Gift Card Information Card Number Text");
	public static final Locator GIFT_CARD_INFO_FROM_TEXT = new Locator("GIFT_CARD_INFO_FROM_TEXT", "((//legend[text()='Gift Card Information']/following-sibling::div//table//tr)[1]//th)[3]", "Gift Card Information From Text");
	public static final Locator GIFT_CARD_INFO_TO_TEXT = new Locator("GIFT_CARD_INFO_TO_TEXT", "((//legend[text()='Gift Card Information']/following-sibling::div//table//tr)[1]//th)[4]", "Gift Card Information To Text");
	public static final Locator GIFT_CARD_INFO_EMAIL_TEXT = new Locator("GIFT_CARD_INFO_EMAIL_TEXT", "((//legend[text()='Gift Card Information']/following-sibling::div//table//tr)[1]//th)[5]", "Gift Card Information Email Text");
	public static final Locator GIFT_CARD_INFO_SENDER_ADDRESS_TEXT = new Locator("GIFT_CARD_INFO_SENDER_ADDRESS_TEXT", "((//legend[text()='Gift Card Information']/following-sibling::div//table//tr)[1]//th)[6]", "Gift Card Information Sender Address Text");
	public static final Locator GIFT_CARD_INFO_RECEIVER_ADDRESS_TEXT = new Locator("GIFT_CARD_INFO_RECEIVER_ADDRESS_TEXT", "((//legend[text()='Gift Card Information']/following-sibling::div//table//tr)[1]//th)[7]", "Gift Card Information Receiver Address Text");
	public static final Locator GIFT_CARD_INFO_AMOUNT_TEXT = new Locator("GIFT_CARD_INFO_AMOUNT_TEXT", "((//legend[text()='Gift Card Information']/following-sibling::div//table//tr)[1]//th)[8]", "Gift Card Information Amount Text");
	public static final Locator GIFT_CARD_INFO_MESSAGE_TEXT = new Locator("GIFT_CARD_INFO_MESSAGE_TEXT", "((//legend[text()='Gift Card Information']/following-sibling::div//table//tr)[1]//th)[9]", "Gift Card Information Message Text");
	public static final Locator GIFT_CARD_INFO_TABLE_CONTENT = new Locator("GIFT_CARD_INFO_TABLE_CONTENT", "//legend[text()='Gift Card Information']/following-sibling::div//table//tbody", "Gift Card Information Table Content");
	public static final Locator GIFT_CARD_INFO_NO_RESPONSE = new Locator("GIFT_CARD_INFO_NO_RESPONSE", "//span[text() = 'No records found']","Gift Card Information No Response");
	
	//Discounts:
	public static final Locator DISCOUNTS_TITLE = new Locator("DISCOUNTS_TITLE", "//legend[text()='Discounts']", "Discounts Title");
	public static final Locator DISCOUNTS_TABLE = new Locator("DISCOUNTS_TABLE", "//legend[text()='Discounts']/following-sibling::div[@ng-if='selectedLineItem.discounts']", "Discounts Table");
	public static final Locator DESCRIPTION_TEXT = new Locator("DESCRIPTION_TEXT","//legend[text()='Discounts']/following-sibling::div[@ng-if='selectedLineItem.discounts']//thead/tr/th[1]","Description Text");
	public static final Locator AMOUNT_TEXT = new Locator("AMOUNT_TEXT","//legend[text()='Discounts']/following-sibling::div[@ng-if='selectedLineItem.discounts']//thead/tr/th[2]","Amount Text");
	public static final Locator DISCOUNTS_TABLE_CONTENT = new Locator("DISCOUNTS_TABLE_CONTENT", "//legend[text()='Discounts']/following-sibling::div[@ng-if='selectedLineItem.discounts']//tbody", "Discounts Table Content");
	public static final Locator DISCOUNTS_NO_RESPONSE = new Locator("DISCOUNTS_NO_RESPONSE", "//span[text() = 'No discount records found']","Discounts No Response");
	
	//Adjustments:
	public static final Locator ADJUSTMENTS_TITLE = new Locator("ADJUSTMENTS_TITLE", "//legend[text()='Adjustments']", "Adjustments Title");
	public static final Locator ADJUSTMENTS_TABLE = new Locator("ADJUSTMENTS_TABLE", "//legend[text()='Adjustments']/following-sibling::div[@ng-if='selectedLineItem.adjustments']", "Adjustments Table");
	public static final Locator ADJUSTMENTS_TYPE_COL_NAME = new Locator("ADJUSTMENTS_TYPE_COL_NAME", "//legend[text()='Adjustments']/following-sibling::div[@ng-if='selectedLineItem.adjustments']//thead/tr/th[1]", "Adjustments Type");
	public static final Locator ADJUSTMENTS_AMOUNT_COL_NAME = new Locator("ADJUSTMENTS_AMOUNT_COL_NAME", "//legend[text()='Adjustments']/following-sibling::div[@ng-if='selectedLineItem.adjustments']//thead/tr/th[2]", "Adjustments Amount");
	public static final Locator ADJUSTMENTS_DATE_COL_NAME = new Locator("ADJUSTMENTS_DATE_COL_NAME", "//legend[text()='Adjustments']/following-sibling::div[@ng-if='selectedLineItem.adjustments']//thead/tr/th[3]", "Adjustments Date");
	public static final Locator ADJUSTMENTS_SALECHECK_NO_COL_NAME = new Locator("ADJUSTMENTS_SALECHECK_NO_COL_NAME", "//legend[text()='Adjustments']/following-sibling::div[@ng-if='selectedLineItem.adjustments']//thead/tr/th[4]", "Adjustments Sales Check Number");
	public static final Locator ADJUSTMENTS_TABLE_CONTENT = new Locator("ADJUSTMENTS_TABLE", "//legend[text()='Adjustments']/following-sibling::div[@ng-if='selectedLineItem.adjustments']//tbody", "Adjustments Table Content");
	public static final Locator ADJUSTMENTS_NO_RESPONSE = new Locator("ADJUSTMENTS_NO_RESPONSE", "//span[text() = 'No adjustment records found']","Adjustments No Response");
	
	public static final Locator SALES_CHECK_NUMBER= new Locator("SALES_CHECK_NUMBER", "(//a[@class='spacetwo ng-binding'])[{0}]", "Sales Check Number");
	public static final Locator FULFILL_BY= new Locator("FULFILL_BY", "//div[@ng-if='vendor.hasVendorInfo()']//a", "Fulfill By");
    
	//Reschedule Delivery
	
	public static final Locator RESCHEDULE_DELIVERY_TEXT= new Locator("Reschedule Delivery pop up", "//h4[contains(text(),'Reschedule Delivery')]", "Reschedule Delivery");	
	public static final Locator RESCHEDULE_DELIVERY_FORM= new Locator("Reschedule Delivery Form", "//form[@name='rescheduleDeliveryForm']", "Reschedule Delivery Form");
	public static final Locator RESCHEDULE_DELIVERY_SELECT_CALENDER= new Locator("Reschedule Delivery Form", "//input[@class='form-control date ng-isolate-scope ng-pristine ng-valid ng-valid-date']", "Reschedule Delivery Form");
	public static final Locator RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES= new Locator("RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES", "//button[contains(text(),'Get Available Dates')]", "RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES");	
	public static final Locator RESCHEDULE_DELIVERY_SELECT_DATES= new Locator("RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES", "(//button[@ng-disabled='dt.disabled'])[{0}]", "RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES");
	public static final Locator RESCHEDULE_DELIVERY_ERROR_POPUP= new Locator("RESCHEDULE_DELIVERY_error mssage ", "//div[contains(text(),'Problems occurred while processing: Get Reschedule Delivery Dates')]", "RESCHEDULE_DELIVERY_ERROR_POPUP");
	public static final Locator RESCHEDULE_DELIVERY_ERROR_POPUP_CLOSE= new Locator("RESCHEDULE_DELIVERY_error mssage close ", "//button[@id='modalclose']", "RESCHEDULE_DELIVERY_ERROR_POPUP_CLOSE");
	public static final Locator NEW_SELECTED_DATE = new Locator("NEW SELECTED DATE", "(((//table)[5]//tbody//tr)[6]//td)[8]", "New Selected Date Button");
	public final Locator SELECT_RESON_CODE = new Locator("Select reson code ", "(//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required'])", " SELECT RESON CODE");
	public final Locator SELECTED_RESON_CODE = new Locator("Selected reson code ", "(//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required'])", " SELECTED RESON CODE");
	public static final Locator NOTES = new Locator("Adjustment notes", "(//textarea[@class='form-control input-sm ng-pristine ng-valid'])", "notes");
	public static final Locator SUBMIT_BUTTON = new Locator("SUBMIT_BUTTON", "(//button[contains(text(),'Submit')])[1]", "Submit Button");
	//public final Locator RETURN_ITEM_RESON_CODE_COUNT= new Locator("Reson code count", "(//option[@ng-repeat='reasonCode in reasonCodes'])", "Reson code count");
	public static final Locator RETURN_ITEM_RESON_DROPDOWN= new Locator("Reson code","//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required']", "Reson code");
	public static final Locator RESON_CODE= new Locator("Reson code", "(//option[text()='{0}'])", "Reson code");

	Utility util = new Utility();
	Connection conn1 = null;
	PreparedStatement st1 = null;
	ResultSet rs1 = null;
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
	DecimalFormat formatter = new DecimalFormat("#,##0.00");
	DecimalFormat df = new DecimalFormat("0.00");
	DecimalFormat df2 = new DecimalFormat("0");
	public LineItemDetailsPage(SiteFactory factory) {
        super(factory);
    }

    protected String getPageName() {
        return "Line Item Details Page";
    }

    protected String pageName() {
        return "Line Item Details Page";
    }
    
    public LineItemDetailsPage clickOnFulfillByUnderLineItemSummary(){    	
    	Logger.log("Click on Fulfill By under Line Item Summary",TestStepType.STEP);
    	AjaxCondition.forElementVisible(FULFILL_BY).waitForResponse();
    	getAction().click(FULFILL_BY);
		return this;
    }
    
    
    
    private String getStoreName(String storeID){
    	HashMap<String,String> storeIDNameMap = new HashMap<String,String>();
    	storeIDNameMap.put("10153","Sears");
	    storeIDNameMap.put("10151","Kmart");
	           storeIDNameMap.put("30151","Kmart");
		       storeIDNameMap.put("30165","Kmart (PR)");
		       storeIDNameMap.put("10171","Outlet");
		       storeIDNameMap.put("10175","My Gofer 3");
		       storeIDNameMap.put("10199","B2BGC");
		       storeIDNameMap.put("10165","Sears PR");
		       storeIDNameMap.put("10191","Sears SCO");
		       storeIDNameMap.put("9305","ShopYourWay Mall");
		       storeIDNameMap.put("10195","Affinity");
		       storeIDNameMap.put("10156","TGI");
		       storeIDNameMap.put("10181","Delver");
		       storeIDNameMap.put("10197","Skymall");
		       storeIDNameMap.put("10182","Delver Mkt Place");
		       storeIDNameMap.put("10188","Case Management Git Card");
		       storeIDNameMap.put("10196","Full Line Commercial");
		       storeIDNameMap.put("20101","Multi Channel Fulfill by Sears");
		       storeIDNameMap.put("20195","Affinity Kmart");
		       storeIDNameMap.put("30153","Sears (ShopSears)");
		       storeIDNameMap.put("40153", "Sears.com");
		       storeIDNameMap.put("40154","Sears (MarketPlace");
		       storeIDNameMap.put("50153","Sears Mobile");
		       storeIDNameMap.put("50151","KMart Mobile");
		       storeIDNameMap.put("10152","KCON");
		       storeIDNameMap.put("10161","My Gofer 2");
		       storeIDNameMap.put("30154","Shopsears market place items");
		      return storeIDNameMap.get(storeID);
    }
    public LineItemDetailsPage lineItemSummaryVerify(int saleschecknumber) throws ParseException{
        String expectedShipDate = null;
        String expectedArrivalDate = null;
        String itemStatuscd= null;
        String itemStatus = null;
        String salesCheckNo = null;
        String fulfillBy = null;
        String zeroPercent = null;
        String SKU = null;
        String description = null;
        String quantity= null;
        String mfrModelNo = null;
        String storecd = null;
        String store =null;
        Double weight = null;
        Double price = null;
        Double tax = null;
        Double taxPercent= null;
        Double surchargeAmt = null;
        Double environmentalFee = null;
        Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
        getAction().waitFor(5000);
        String itemNumberContent = getAction().getText(ITEM_NUMBER);
    	String itemNumber = itemNumberContent.split(" ")[itemNumberContent.split(" ").length-1];
        String salesCheckNumber = getAction().getText(SALES_CHECK_NUMBER.format(saleschecknumber));
        Logger.log("Verify Item # is displayed",TestStepType.VERIFICATION_STEP);
        AjaxCondition.forElementVisible(ITEM_NUMBER).waitForResponse();
        PageAssert.verifyPartiallyEqual(getAction().getText(ITEM_NUMBER), "Item # ");
        String sql ="select oi.REQUESTED_SHIP_DATE, oi.PROMISED_AVAILABLE_TIME, oi.ORDER_ITEM_STS_CD, sc.SALES_CHECK_NUMBER, "
        		+ "fm.ffm_class_id, oi.FFM_METHOD_ID, oi.ITEM_ID, oi.ITEM_NM, oi.QUANTITY, oi.MANUFACTURER_MODEL_NUMBER, oi.SITE_ID, "
        		+ "pi.WEIGHT, oi.LIST_PRICE, oi.TAX_AMT, oi.TAX_RATE, oi.SURCHARGE_AMOUNT, oi.env_fee from ord o, ord_item oi, "
        		+ "sales_check sc, ffm_method fm, product_dimension_order_item pi where o.order_id = oi.order_id and o.order_id = sc.order_id and "
        		+ "oi.ffm_method_id = fm.ffm_method_id and oi.ORDER_ITEM_ID=pi.ORDER_ITEM_ID and oi.ORDER_ITEM_ID = ? and sc.SALES_CHECK_NUMBER =? ";
        try {
			    st = conn.prepareStatement(sql);
	            st.setString (1, itemNumber);
	            st.setString (2, salesCheckNumber);
	            st.execute();
	            rs = st.getResultSet();
	            while(rs.next()){
	            expectedShipDate = rs.getString("REQUESTED_SHIP_DATE") ;
	            expectedArrivalDate = rs.getString("PROMISED_AVAILABLE_TIME") ;
	            itemStatuscd = rs.getString("ORDER_ITEM_STS_CD") ;
	            salesCheckNo=rs.getString("SALES_CHECK_NUMBER") ;
	            fulfillBy=rs.getString("ffm_class_id") ;
	            //zeroPercent =rs.getString("") ;
	            SKU = rs.getString("ITEM_ID") ;
	            description = rs.getString("ITEM_NM").replaceAll("2&trade;", "").trim().replaceAll(" +", " ") ;
	            quantity= rs.getString("QUANTITY") ;
	            mfrModelNo = rs.getString("MANUFACTURER_MODEL_NUMBER") ;
	            storecd = rs.getString("SITE_ID") ;
	            weight = rs.getDouble("WEIGHT") ;
	            price = rs.getDouble("LIST_PRICE") ;
	            tax = rs.getDouble("TAX_AMT") ;
	            taxPercent=rs.getDouble("TAX_RATE") ;
	            surchargeAmt = rs.getDouble("SURCHARGE_AMOUNT") ;
	            environmentalFee = rs.getDouble("env_fee") ;}
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
        		Logger.log("Verify Line Item Summary title text is present", TestStepType.VERIFICATION_STEP);
			    PageAssert.textPresent(LINE_ITEM_SUMMARY_TITLE, "Line Item Summary");
			    
			    if(SKU!=null)
			    {
				    if(getAction().isVisible(LINE_ITEM_SUMMARY_TABLE)) 
				    {
				    	Logger.log("Verify line item summary section", TestStepType.VERIFICATION_STEP);
				    	/****
			             * Verify Text Present
			             ****/
			            SoftAssert.checkConditionAndContinueOnFailure("Line Item Expected Ship Date text is present",
			            		getAction().getText(LINE_ITEM_EXPECTED_SHIP_DATE_TEXT).equalsIgnoreCase("Expected Ship Date"));
			            
			            SoftAssert.checkConditionAndContinueOnFailure("Line Item Expected Arrival Date text is present",
			            		getAction().getText(LINE_ITEM_EXPECTED_ARRIVAL_DATE_TEXT).equalsIgnoreCase("Expected Arrival Date"));
			        
			            
			            SoftAssert.checkConditionAndContinueOnFailure("Item Status heading is Present",
			            		getAction().getText(LINE_ITEM_ITEM_STATUS_TEXT).equalsIgnoreCase("Item Status"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure("Sales Check # Text is Present",
			            		getAction().getText(LINE_ITEM_SALES_CHECK_NUMBER_TEXT).equalsIgnoreCase("Sales Check #"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure("Fulfill By Text is Present",
			            		getAction().getText(LINE_ITEM_FULFILL_BY_TEXT).equalsIgnoreCase("Fulfill By"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure(" Zero Percent Text is Present",
			            		getAction().getText(LINE_ITEM_ZERO_PERCENT_TEXT).equalsIgnoreCase("Zero Percent"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure(" SKU Text is Present",
			            		getAction().getText(LINE_ITEM_SKU_TEXT).equalsIgnoreCase("SKU"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure(" Description Text is Present",
			            		getAction().getText(LINE_ITEM_DESCRIPTION_TEXT).equalsIgnoreCase("Description"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure(" Quantity Text is Present",
			            		getAction().getText(LINE_ITEM_QUANTITY_TEXT).equalsIgnoreCase("Quantity"));
			            
			            Logger.log("Verify Mfr Model # Text is Present", TestStepType.VERIFICATION_SUBSTEP);
			            PageAssert.textPresent(LINE_ITEM_MFR_MODEL_NUMBER_TEXT, "Mfr Model #");
			            
			            SoftAssert.checkConditionAndContinueOnFailure(" Mfr Model # Text is Present",
			            		getAction().getText(LINE_ITEM_MFR_MODEL_NUMBER_TEXT).equalsIgnoreCase("Mfr Model #"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure("Store Text is Present",
			            		getAction().getText(LINE_ITEM_STORE_TEXT).equalsIgnoreCase("Store"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure("Weight Text is Present",
			            		getAction().getText(LINE_ITEM_WEIGHT_TEXT).equalsIgnoreCase("Weight (in lbs)"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure("Price Text is Present",
			            		getAction().getText(LINE_ITEM_PRICE_TEXT).equalsIgnoreCase("Price"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure("Tax Text is Present",
			            		getAction().getText(LINE_ITEM_TAX_TEXT).equalsIgnoreCase("Tax"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure("Tax percent text is Present",
			            		getAction().getText(LINE_ITEM_TAX_PERCENT_TEXT).equalsIgnoreCase("Tax Percent"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure("Surcharge Amt text is Present",
			            		getAction().getText(LINE_ITEM_SURCHARGE_AMT_TEXT).equalsIgnoreCase("Surcharge Amt"));
			            
			            
			            SoftAssert.checkConditionAndContinueOnFailure("Environmental Fee text is Present",
			            		getAction().getText(LINE_ITEM_ENVIRONMENTAL_FEE_TEXT).equalsIgnoreCase("Environmental Fee"));
			            
				    	if(expectedShipDate == null){
				    		expectedShipDate = "";
				    		Logger.log("Verify Expected Ship Date is "+ expectedShipDate+" in Database and "+getAction().getText(LINE_ITEM_EXPECTED_SHIP_DATE_DETAIL)+" is Displayed in Line Item Summary", TestStepType.STEP);
				    		PageAssert.verifyEqual(expectedShipDate, getAction().getText(LINE_ITEM_EXPECTED_SHIP_DATE_DETAIL));
				    		
					    	}else{
					    		Logger.log("Verify Expected Ship Date is "+ expectedShipDate+" in Database and "+sdf2.format(sdf1.parse(expectedShipDate.split(" ")[0]))+" is Displayed in Line Item Summary", TestStepType.STEP);
					    		PageAssert.verifyEqual(sdf2.format(sdf1.parse(expectedShipDate.split(" ")[0])), getAction().getText(LINE_ITEM_EXPECTED_SHIP_DATE_DETAIL));
					    		
					    		}
				    	if(expectedArrivalDate == null)  {
				    		 expectedArrivalDate = "";
				    		 Logger.log("Verify Expected Arrival Date is "+ expectedArrivalDate+" in Database and is Displayed in Line Item Summary", TestStepType.STEP);
				    		 PageAssert.verifyEqual(expectedArrivalDate, getAction().getText(LINE_ITEM_EXPECTED_ARRIVAL_DATE_DETAIL));
				    	
					    	 }else{
					    		 Logger.log("Verify Expected Arrival Date is "+ expectedArrivalDate+" in Database and "+sdf2.format(sdf1.parse(expectedArrivalDate.split(" ")[0]))+" is Displayed in Line Item Summary", TestStepType.STEP);
					    		 PageAssert.verifyEqual(sdf2.format(sdf1.parse(expectedArrivalDate.split(" ")[0])), getAction().getText(LINE_ITEM_EXPECTED_ARRIVAL_DATE_DETAIL));
					    		    
					    	 		} 
					    itemStatus=Utility.getLineItemStatusDescription(itemStatuscd);
					
				    	 SoftAssert.checkConditionAndContinueOnFailure("Line item status in database should match with application", 
					    			getAction().getText(LINE_ITEM_ITEM_STATUS_DETAIL).equalsIgnoreCase(itemStatus));
			    		
				    	
				    	 SoftAssert.checkConditionAndContinueOnFailure("Sales Check # in database should match with application", 
					    			getAction().getText(LINE_ITEM_SALES_CHECK_NUMBER_DETAIL).equalsIgnoreCase(salesCheckNo));
				    	 
				    	   //need edit
				    	Logger.log("FulfillBy values in db is "+fulfillBy + " fulfillBy in site is "+getAction().getText(LINE_ITEM_FULFILL_BY_DETAIL));
				    	String pageFulfill = getAction().getText(LINE_ITEM_FULFILL_BY_DETAIL);
				    	pageFulfill=pageFulfill.replace("/", "");
				    	 SoftAssert.checkConditionAndContinueOnFailure("Fulfill By in database should match with application", 
				    			 pageFulfill.equalsIgnoreCase(fulfillBy));
				    	
				    	 SoftAssert.checkConditionAndContinueOnFailure("SKU in database should match with application", 
					    			getAction().getText(LINE_ITEM_SKU_DETAIL).replaceAll("[^\\d.]","").equalsIgnoreCase(SKU));
				    	 
				    	 SoftAssert.checkConditionAndContinueOnFailure("Description in database should match with application", 
					    			getAction().getText(LINE_ITEM_DESCRIPTION_DETAIL).replaceAll("2™", "").equalsIgnoreCase(description));
					    	   
				   	   SoftAssert.checkConditionAndContinueOnFailure("Quantity in database should match with application", 
				    			getAction().getText(LINE_ITEM_QUANTITY_DETAIL).equalsIgnoreCase(quantity));
				    	   
				    	 String mfrModelNumber = null;
				    	 if(mfrModelNo == null){
				    		mfrModelNumber = ""; 
				    	 	}else{
				    		mfrModelNumber = mfrModelNo;
				    	 	}
				  	   SoftAssert.checkConditionAndContinueOnFailure("Mfr Model #  in database should match with application", 
				    			getAction().getText(LINE_ITEM_MFR_MODEL_NUMBER_DETAIL).equalsIgnoreCase(mfrModelNumber));
				  	   
				    	store=getStoreName(storecd);
			    	   SoftAssert.checkConditionAndContinueOnFailure("Store in database should match with application", 
					    			getAction().getText(LINE_ITEM_STORE_DETAIL).equalsIgnoreCase(store+" ("+storecd+")"));
			    	  
					    if(weight==null){
					    	String weight_w ="";
					 	   SoftAssert.checkConditionAndContinueOnFailure("Weight (in lbs) in database should match with application", 
					    			getAction().getText(LINE_ITEM_WEIGHT_DETAIL).equalsIgnoreCase(weight_w));
					    
					    }else{	   
			    	 	   SoftAssert.checkConditionAndContinueOnFailure("Weight (in lbs) in database should match with application", 
					    			getAction().getText(LINE_ITEM_WEIGHT_DETAIL).equalsIgnoreCase(df2.format(weight)));
					    }

			    	   SoftAssert.checkConditionAndContinueOnFailure("Price in database should match with application", 
				    			getAction().getText(LINE_ITEM_PRICE_DETAIL).equalsIgnoreCase("$"+formatter.format(price)));
			    	   
			    	   
			    	   SoftAssert.checkConditionAndContinueOnFailure("Tax in database should match with application", 
				    			getAction().getText(LINE_ITEM_TAX_DETAIL).equalsIgnoreCase("$"+df.format(tax)));
	
			    	   if(taxPercent==0){
				    	SoftAssert.checkConditionAndContinueOnFailure("Tax percent in database should match with application", 
				    			getAction().getText(LINE_ITEM_TAX_PERCENT_DETAIL).equalsIgnoreCase("$"+df2.format(taxPercent)+" %"));
				    	   }else{
				    		   Logger.log("Verify Tax Percent is "+ taxPercent+" in Database and Displayed "+df2.format(taxPercent)+" % "+" in Line Item Summary", TestStepType.STEP);
				    		   String existingTax=getAction().getText(LINE_ITEM_TAX_PERCENT_DETAIL);
				    		   existingTax= existingTax.replace(" ", "");
				    		   PageAssert.verifyEqual(df.format(taxPercent)+"%", existingTax);
				    		   
						    	SoftAssert.checkConditionAndContinueOnFailure("Tax percent in database should match with application", 
						    			existingTax.equalsIgnoreCase(df.format(taxPercent)+"%"));
					    	   
				    	   }

				    	SoftAssert.checkConditionAndContinueOnFailure("Surcharge amount in database should match with application", 
				    			getAction().getText(LINE_ITEM_SURCHARGE_AMT_DETAIL).equalsIgnoreCase("$"+df.format(surchargeAmt)));
				    	
				    	SoftAssert.checkConditionAndContinueOnFailure("Environmental fee in database should match with application", 
				    			getAction().getText(LINE_ITEM_ENVIRONMENTAL_FEE_DETAIL).equalsIgnoreCase("$"+df.format(environmentalFee)));
				    	   
				    	   Reporter.log("Verified Sales Check Summary Table Successfully");
				    }else{
				    	Reporter.log("Failed Retrieval Data for Line Item Summary");
				    }
			    }
	    	else{
			    	
			    	 Reporter.log("This is no Data in Database for Line Item Summary Table");
			    }
			    try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
				return this;
    	}
 	
  	public LineItemDetailsPage lineItemShippingInfoVerify(int saleschecknumber) {
  		
        String shippingmethod = null;
        String shippingcharge = null;
        String shippingquantity = null;
        Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
        Logger.log("Fetch item number from line item tab",TestStepType.STEP);
        AjaxCondition.forElementVisible(ITEM_NUMBER).waitForResponse();
        String itemNumberContent = getAction().getText(ITEM_NUMBER);
    	String itemNumber = itemNumberContent.split(" ")[itemNumberContent.split(" ").length-1];
        String sql ="select sm.DESCRIPTION, oi.SHIPPING_AMT, si.QUANTITY from ord_item oi left join shipping_mode sm "
        		+ "on sm.shipping_mode_id = oi.SHIPPING_MODE_ID left join shipped_item si on si.order_item_id = oi.order_item_id "
        		+ "where oi.order_item_id = ?";
        try {
			    st = conn.prepareStatement(sql);
	            st.setString (1, itemNumber);
	            st.execute();
	            rs = st.getResultSet();
	            while(rs.next()){
	            shippingmethod = rs.getString("DESCRIPTION") ;
	            shippingcharge = rs.getString("SHIPPING_AMT") ;
	            shippingquantity = rs.getString("QUANTITY") ;
	            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        SoftAssert.checkConditionAndContinueOnFailure(" Shipping Information column is Present",
        		getAction().getText(SHIPPING_INFORMATION_TITLE).equalsIgnoreCase("Shipping Information"));
        
        if(shippingmethod!=null || shippingcharge!=null ||shippingquantity!=null){
        /****
         * Verify Text Present
         ****/
        
        SoftAssert.checkConditionAndContinueOnFailure(" Shipping Method column is Present",
        		getAction().getText(SHIPPING_INFO_SHIPPING_METHOD_TEXT).equalsIgnoreCase("Shipping Method"));
        
        SoftAssert.checkConditionAndContinueOnFailure("Shipping Charge column is Present",
        		getAction().getText(SHIPPING_INFO_SHIPPING_CHARGE_TEXT).equalsIgnoreCase("Shipping Charge"));
        
        SoftAssert.checkConditionAndContinueOnFailure("Shipped Quantity column is Present",
        		getAction().getText(SHIPPING_INFO_SHIPPED_QUANTITY_TEXT).equalsIgnoreCase("Shipped Quantity"));
        
        if(getAction().isVisible(SHIPPING_INFORMATION_TABLE)) {
        	if(shippingmethod==null){
        		shippingmethod = "";
        	}
            SoftAssert.checkConditionAndContinueOnFailure("Shipping method is updated",
            		getAction().getText(SHIPPING_INFO_SHIPPING_METHOD_DETAIL).equalsIgnoreCase(shippingmethod));
            
	        SoftAssert.checkConditionAndContinueOnFailure("Shipping Charge matches with that in DB",
            		getAction().getText(SHIPPING_INFO_SHIPPING_CHARGE_DETAIL).equalsIgnoreCase("$"+df.format(Double.parseDouble(shippingcharge))));
	    	   
		    	if(shippingquantity==null) {
		    		shippingquantity = "0";
		    	}
	    	 SoftAssert.checkConditionAndContinueOnFailure("Shipped Quantity matches with that in DB",
	            		getAction().getText(SHIPPING_INFO_SHIPPED_QUANTITY_DETAIL).equalsIgnoreCase(shippingquantity));
		    }
        else{
		    	Reporter.log("Failed Retrieval Data for Shipping Information Table");
		    }
	    }else{
	    	Reporter.log("This is no Data in Database for Shipping Information Table");
	    }
        try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
    	return this;
  	}

  	public LineItemDetailsPage verifyTrackingDetails() {
  		String shippingCarrier = null;
        String trackingNumber = null;
        Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
        String itemNumberContent = getAction().getText(ITEM_NUMBER);
    	String itemNumber = itemNumberContent.split(" ")[itemNumberContent.split(" ").length-1];
        String sql ="select CARRIER, TRACKING_NUMBER from ship_unit where SHIP_UNIT_ID in (select SHIP_UNIT_ID from ship_invoice_item "
        		+ "where order_item_id =?)";
        try {
			    st = conn.prepareStatement(sql);
	            st.setString (1, itemNumber);
	            st.execute();
	            rs = st.getResultSet();
	            while(rs.next()){
	            shippingCarrier = rs.getString("CARRIER") ;
	            trackingNumber = rs.getString("TRACKING_NUMBER") ;
	            }  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SoftAssert.checkConditionAndContinueOnFailure("Tracking Details section in present",
        		getAction().getText(TRACKING_DETAIL_TITLE).equalsIgnoreCase("Tracking Details"));
  		if(shippingCarrier!=null ||trackingNumber!=null){
  			if(getAction().isVisible(TRACKING_DETAIL_TABLE)){
		        /****
		         * Verify Text Present
		         ****/
		  		
		  		 SoftAssert.checkConditionAndContinueOnFailure("Shipping Carrier column in present",
		            		getAction().getText(TRACKING_CARRIER_TEXT).equalsIgnoreCase("Shipping Carrier"));
		  		 
		        SoftAssert.checkConditionAndContinueOnFailure("Tracking # column in present",
	            		getAction().getText(TRACKING_NUMBER_TEXT).equalsIgnoreCase("Tracking #"));
		        
		        /****
		         * Verify Data
		         ****/
		        	if(shippingCarrier==null){
		        		shippingCarrier = "";
		        	}
		        	
			    	 SoftAssert.checkConditionAndContinueOnFailure("Shipping Carrier matches with that in DB",
			            		getAction().getText(TRACKING_CARRIER).equalsIgnoreCase(shippingCarrier));
		 	    	
		 	    	if(trackingNumber==null){
		 	    		trackingNumber = "";
		        	}
		 	    	
			    	 SoftAssert.checkConditionAndContinueOnFailure("Tracking Number matches with that in DB",
			            		getAction().getText(TRACKING_NUMBER).equalsIgnoreCase(trackingNumber));
	  			}else{
	  				Reporter.log("Failed Retrieval Data for Tracking Details Table~");
	  			}
  			}else{
  				AjaxCondition.forElementVisible(TRACKING_DETAIL_NO_RESPONSE).waitForResponse(10);
  				Reporter.log("No Tracking Details found in Database");
      		   }
  		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
  		return this;
      	}

	public LineItemDetailsPage verifyReturnInformation(int saleschecknumber) throws ParseException {
	  	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
	  	ArrayList<String> return_info_sc  =  new ArrayList<String>();
	  	ArrayList<Double> return_info_amt  =  new ArrayList<Double>();
	  	ArrayList<String> return_info_rmano  =  new ArrayList<String>();
	  	ArrayList<String> return_info_reason_code  =  new ArrayList<String>();
	  	ArrayList<String> return_info_return_date  =  new ArrayList<String>();
	  	ArrayList<String> return_info_quantity  =  new ArrayList<String>();
	  	
        String itemNumberContent = getAction().getText(ITEM_NUMBER);
    	String itemNumber = itemNumberContent.split(" ")[itemNumberContent.split(" ").length-1];
        String salesCheckNumber = getAction().getText(SALES_CHECK_NUMBER.format(saleschecknumber));
        PageAssert.verifyPartiallyEqual(getAction().getText(ITEM_NUMBER), "Item # ");
        
        String sql ="select r.return_sales_check_number,r.total_credit_amt,r.RMA_ID,rmi.RETURN_REASON_CD,oir.TRACKINGDATE,rmi.QUANTITY "
        		+ "from rma r,rma_item rmi,sales_check sc, ord_item oi, order_item_return oir where oi.order_id = sc.order_id "
        		+ "and oi.order_item_id = oir.order_item_id and sc.sales_check_id=r.sales_check_id and rmi.RMA_ID=r.RMA_ID "
        		+ "and oi.ORDER_ITEM_ID= ? and sc.sales_check_number=? ;";
	        try {
				    st = conn.prepareStatement(sql);
		            st.setString (1, itemNumber);
		            st.setString (2, salesCheckNumber);
		            st.execute();
		            rs = st.getResultSet();
		            while(rs.next()){
		            	return_info_sc.add(rs.getString("return_sales_check_number"));
			            return_info_amt.add(rs.getDouble("total_credit_amt"));
			            return_info_rmano.add(rs.getString("RMA_ID"));
			            return_info_reason_code.add(rs.getString("RETURN_REASON_CD"));
			            return_info_return_date.add(rs.getString("TRACKINGDATE"));
			            return_info_quantity.add(rs.getString("QUANTITY"));
		            }  
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	  		PageAssert.textPresent(RETURN_INFORMATION_TITLE, "Return Information");
	  		
	  		Logger.log("Verify Return Information Title is Present", TestStepType.STEP);
	  		
	  		if(return_info_quantity.size()!=0){
		  		if(getAction().isVisible(RETURN_INFORMATION_TABLE)){
			  		Logger.log("Verify Return Information Table is Visible", TestStepType.STEP);

			  		SoftAssert.checkConditionAndContinueOnFailure("Previous Return Qty column is present",
			            		getAction().getText(PREVIOUSE_RETURN_QTY_TEXT).equalsIgnoreCase("Previous Return Qty:"));
			  		
			  		SoftAssert.checkConditionAndContinueOnFailure("Sales Check# column is present",
		            		getAction().getText(RETURN_INFORMATION_SC_TEXT).equalsIgnoreCase("Sales Check#"));
			  		
			  		
			  		SoftAssert.checkConditionAndContinueOnFailure("Return Information Amount column is present",
		            		getAction().getText(RETURN_INFORMATION_AMT_TEXT).equalsIgnoreCase("Amount"));
			  		
			  		SoftAssert.checkConditionAndContinueOnFailure("Return Information RGI #  column is present",
		            		getAction().getText(RETURN_INFORMATION_RGI_NO_TEXT).equalsIgnoreCase("RGI #"));
			  		
			  		SoftAssert.checkConditionAndContinueOnFailure("Return Information RMA # column is present",
		            		getAction().getText(RETURN_INFORMATION_RMA_NO_TEXT).equalsIgnoreCase("RMA #"));
			  		
			  		SoftAssert.checkConditionAndContinueOnFailure("Return Information Reason Code column is present",
		            		getAction().getText(RETURN_INFORMATION_REASON_CODE_TEXT).equalsIgnoreCase("Reason Code"));
			  		
			  		SoftAssert.checkConditionAndContinueOnFailure("Return Information Return Date column is present",
		            		getAction().getText(RETURN_INFORMATION_RETURN_DATE_TEXT).equalsIgnoreCase("Return Date"));
			  		
			  		WebElement returninfotable = getAction().findElement(RETURN_INFORMATION_TABLE_CONTENT );
			  		List<WebElement> returninfo_table_rows = returninfotable.findElements(By.tagName("tr"));
			  		int returninfo_table_rows_count = returninfo_table_rows.size();
			  		for (int i=0; i<returninfo_table_rows_count; i++){
						 List<WebElement> returninfo_table_columns = returninfo_table_rows.get(i).findElements(By.tagName("td"));
						 Logger.log("Verify Return Information Sales Check Number in Return Information is "+ return_info_sc.get(0) +" in Database and "+returninfo_table_columns.get(0).getText()+" is displayed", TestStepType.STEP);
						 PageAssert.verifyEqual(return_info_sc.get(i), returninfo_table_columns.get(0).getText());
						 Logger.log("Verify Return Information Amount in Return Information is "+ return_info_amt.get(i) +" in Database and "+returninfo_table_columns.get(1).getText()+" is displayed", TestStepType.STEP);
				    	 PageAssert.verifyEqual("$"+df.format(return_info_amt.get(i)), returninfo_table_columns.get(1).getText());
				    	 
				    	 /*PageAssert.verifyEqual(return_info_rgino.get(i), returninfo_table_columns.get(2).getText());
				    	 Logger.log("Verify Return Information RGI # in Return Information is "+ return_info_rgino.get(i) +" in Database and "+returninfo_table_columns.get(2).getText()+" is displayed", TestStepType.STEP);*/
				    	 SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information RMA # in Return Information is "+ return_info_rmano.get(i),return_info_rmano.get(i).equals(returninfo_table_columns.get(3).getText()));
				    	 Logger.log("Verify Return Information Reason Code in Return Information is "+ return_info_reason_code.get(i) +" in Database and "+returninfo_table_columns.get(4).getText()+" is displayed", TestStepType.STEP);
				    	 PageAssert.verifyEqual(return_info_reason_code.get(i), returninfo_table_columns.get(4).getText());
				    	 
				    	 SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information Return Date in Return Information is "+ return_info_return_date.get(i),sdf2.format(sdf1.parse(return_info_return_date.get(i).split(" ")[0])).equals(returninfo_table_columns.get(5).getText())); 
				    	}	
			  				Reporter.log("Verified Line Item Return Informaiton Table Successfully");
		  			}else{
		  				Reporter.log("Failed Retrival Data for Return Informaiton Table~");
		  			}
	  		}else{
	  				AjaxCondition.forElementVisible(RETURN_INFORMATION_NO_RESPONSE).waitForResponse(10);
	  				Reporter.log("No Data in Database for Return Information Table");			
	  				
	  		}
	  		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	  		return this;
	  	}

  	public LineItemDetailsPage verifyReturnTrackingInfo(int saleschecknumber) throws ParseException {
  		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
	  	ArrayList<String> tracking_date  =  new ArrayList<String>();
	  	ArrayList<String> carrier_type  =  new ArrayList<String>();
	  	ArrayList<String> return_tracking_number  =  new ArrayList<String>();
	  	ArrayList<String> quantity  =  new ArrayList<String>();
        String itemNumberContent = getAction().getText(ITEM_NUMBER);
    	String itemNumber = itemNumberContent.split(" ")[itemNumberContent.split(" ").length-1];
        String salesCheckNumber = getAction().getText(SALES_CHECK_NUMBER.format(saleschecknumber));
        PageAssert.verifyPartiallyEqual(getAction().getText(ITEM_NUMBER), "Item # ");
    	Logger.log("item number is displayed");
        String sql ="select rt.created_ts, rt.CARRIER, rt.TRACKING_NUMBER,rt.QUANTITY from return_tracking rt,ord_item oi,ord o, sales_check sc "
        		+ "where oi.ORDER_ITEM_ID=rt.ORDER_ITEM_ID and o.ORDER_ID=oi.ORDER_ID and oi.order_id = sc.order_id and oi.ORDER_ITEM_ID= ? "
        		+ "and sc.sales_check_number=?";  
	        try {
				    st = conn.prepareStatement(sql);
		            st.setString (1, itemNumber);
		            st.setString (2, salesCheckNumber);
		            st.execute();
		            rs = st.getResultSet();
		            while(rs.next()){
		            	tracking_date.add(rs.getString("created_ts"));
		            	carrier_type.add(rs.getString("CARRIER"));
		            	return_tracking_number.add(rs.getString("TRACKING_NUMBER"));
		            	quantity.add(rs.getString("QUANTITY"));
		            }  
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
      	getAction().waitFor(1000);
  		PageAssert.textPresent(RETURN_TRACKING_INFORMATION_TITLE, "Return Tracking Information");
  		Logger.log("Verify Return Tracking Information Title is Present", TestStepType.STEP);
  		if(quantity.size()!=0){
  		if(getAction().isVisible(RETURN_TRACKING_INFORMATION_TABLE)){
  		Logger.log("Verify Return Tracking Information Table is Visible", TestStepType.STEP);
  		
  		SoftAssert.checkConditionAndContinueOnFailure("Tracking Date column is present",
        		getAction().getText(RETURN_TRACKING_DATE_TEXT).equalsIgnoreCase("Tracking Date"));
  		
  		SoftAssert.checkConditionAndContinueOnFailure("Return Tracking Carrier Type column is present",
        		getAction().getText(RETURN_TRACKING_CARRIER_TEXT).equalsIgnoreCase("Carrier Type"));
  		
  		SoftAssert.checkConditionAndContinueOnFailure("Return Tracking Number column is present",
        		getAction().getText(RETURN_TRACKING_NUMBER_TEXT).equalsIgnoreCase("Return Tracking Number"));
  		
  		SoftAssert.checkConditionAndContinueOnFailure("Return Tracking Quantity column is present",
        		getAction().getText(RETURN_TRACKING_QTY_TEXT).equalsIgnoreCase("Quantity"));
  		
  		WebElement returntrackinginfotable = getAction().findElement(RETURN_TRACKING_INFORMATION_TABLE_CONTENT );
  		List<WebElement> returntrackinginfo_table_rows = returntrackinginfotable.findElements(By.tagName("tr"));
  		int returntrackinginfo_table_rows_count = returntrackinginfo_table_rows.size();
  		for (int i=0; i<returntrackinginfo_table_rows_count; i++){
			 List<WebElement> returntrackinginfo_table_columns = returntrackinginfo_table_rows.get(i).findElements(By.tagName("td"));
			 
			 Logger.log("Verify Return Tracking Information 'Retrun Date' is "+ tracking_date.get(i) +" in Database and "+returntrackinginfo_table_columns.get(0).getText()+" is displayed", TestStepType.STEP);
			 PageAssert.verifyEqual(sdf2.format(sdf1.parse(tracking_date.get(i).split(" ")[0])), returntrackinginfo_table_columns.get(0).getText());
			 
			 Logger.log("Verify Return Tracking Information Carrier is "+ carrier_type.get(i) +" in Database and "+returntrackinginfo_table_columns.get(1).getText()+" is displayed", TestStepType.STEP);
	    	 PageAssert.verifyEqual(carrier_type.get(i), returntrackinginfo_table_columns.get(1).getText());
	    	 
	    	 Logger.log("Verify Return Tracking Information Tracking Number is "+ return_tracking_number.get(i) +" in Database and "+returntrackinginfo_table_columns.get(2).getText()+" is displayed", TestStepType.STEP);
	    	 PageAssert.verifyEqual(return_tracking_number.get(i), returntrackinginfo_table_columns.get(2).getText());
	    	 
	    	 Logger.log("Verify Return Tracking Information Quantiy is "+ quantity.get(i) +" in Database and "+returntrackinginfo_table_columns.get(3).getText()+" is displayed", TestStepType.STEP);
	    	 PageAssert.verifyEqual(quantity.get(i), returntrackinginfo_table_columns.get(3).getText());
	    	}	
  				Reporter.log("Verified Line Item Return Tracking Informaiton Table Successfully");
  		
  		}else{
  			Reporter.log("Failed Retrieval Data for Return Tracking Information Table ");
  		}
  			}else{
  				AjaxCondition.forElementVisible(RETURN_TRACKING_INFORMATION_NO_RESPONSE).waitForResponse(10);
  				Reporter.log("No Data in Database for Return Tracking Information Table ");
      		   }
  		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
  		return this;
      	}

  	//need add Installation Information table content
  	public LineItemDetailsPage verifyInstallationInformation(String orderId, int saleschecknumber) throws ParseException {
  		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
  		String city = null;
        String state = null;
        Double base_installation_cost  =  null;
        Double distance_charge  =  null;
        String installation_date  =  null;
        String inclusion_text  =  null;
        String product_details  =  null;
	  	String statements  =  null;
	  	String store_ringing_instr =  null;
	  	String store_number =  null;
	  	String installation_address1 = null;
	  	String installation_address2 = null;
	  	String ffm_type = null;
	  	
	  	AjaxCondition.forElementPresent(ITEM_NUMBER).waitForResponse();
        String itemNumberContent = getAction().getText(ITEM_NUMBER);
    	String itemNumber = itemNumberContent.split(" ")[itemNumberContent.split(" ").length-1];
        PageAssert.verifyPartiallyEqual(getAction().getText(ITEM_NUMBER), "Item # ");
    	String salesCheckNumber = getAction().getText(SALES_CHECK_NUMBER.format(saleschecknumber));
        String sql_general ="select dioi.INSTALLATION_DATE,oi.ITEM_NM,fm.ffm_class_id,o.site_id "
        		+ "from ord o, ord_item oi, delivery_installation_order_item dioi, ffm_method fm "
        		+ "where o.order_id = oi.order_id and dioi.order_item_id = oi.order_item_id "
        		+ "and oi.ffm_method_id = fm.ffm_method_id and oi.order_item_id=?";  
        String customer_sql =" select cci.FIRST_NM, cci.LAST_NM, cci.ADDR_LINE_1, cci.ADDR_LINE_2, cci.CITY, "
        		+ "cci.STATE_CD, cci.ZIP_CD, cci. COUNTRY_CD "
        		+ "from ord o, customer_contact_info cci "
        		+ "where o. BILLING_ADDRESS_ID = cci.ADDRESS_ID and o.site_gen_ord_id =  ?";
        
        String store_sql ="select name, phone, ADDR_LINE_1, ADDR_LINE_2, city, state, zip_cd "
        		+ "from unit u, ffm_method ffm, sales_check sc, ord_item oi, ord o "
        		+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = ffm.ffm_method_id "
        		+ "and u.unit_number = ffm.RETAIL_UNIT_NO and oi.SALES_CHECK_ID = sc.SALES_CHECK_ID and sc.sales_check_number =  ?";
          try {
				    st = conn.prepareStatement(sql_general);
		            st.setString (1, itemNumber);
		            st.execute();
		            rs = st.getResultSet();
		            while(rs.next()){
		            	installation_date = rs.getString("INSTALLATION_DATE");
		            	statements = rs.getString("ITEM_NM");
		            	store_number = rs.getString("site_id");
		            	ffm_type = rs.getString("ffm_class_id");
		            }  
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
          getAction().waitFor(1000);
          if(installation_date!=null ||statements!=null ||installation_address1!=null){
          if(ffm_type.equals("SPU")){
          	try {
  			    st = conn.prepareStatement(store_sql);
  	   
  	            st.setString (1, salesCheckNumber);
  	            st.execute();
  	            rs = st.getResultSet();
  	            getAction().waitFor(1000);
  	            while(rs.next()){
  	            installation_address1 = rs.getString("ADDR_LINE_1");
  	            installation_address2 = rs.getString("ADDR_LINE_2");
  	            city = rs.getString("city");
  	            state = rs.getString("state");
  	            }
  	            } catch (SQLException e) {
  	                e.printStackTrace();
  	            }
          }else{
          try {
  			    st = conn.prepareStatement(customer_sql);
  	   
  	            st.setString (1, orderId);
  	            st.execute();
  	            rs = st.getResultSet();
  	            getAction().waitFor(1000);
  	            while(rs.next()){
  	            installation_address1 = rs.getString("ADDR_LINE_1");
  	            installation_address2 = rs.getString("ADDR_LINE_2");
  	            city = rs.getString("CITY");
  	            state = rs.getString("STATE_CD");
  	            }
  	            } catch (SQLException e) {
  	                e.printStackTrace();
  	            }}
  		getAction().waitFor(1000);
  		
  		SoftAssert.checkConditionAndContinueOnFailure("Installation Information column is present",
        		getAction().getText(INSTALLATION_INFO_TITLE).equalsIgnoreCase("Installation Information"));
  		
	  		if(getAction().isVisible(INSTALLATION_INFO_TABLE)){
	  		Logger.log("Verify Installation Information Table is Visible", TestStepType.STEP);
	  		
	  		SoftAssert.checkConditionAndContinueOnFailure("Base Installation Cost column is present",
	        		getAction().getText(INSTALLATION_INFO_BASE_INSTAL_COST_TEXT).equalsIgnoreCase("Base Installation Cost"));
	  		
	  		SoftAssert.checkConditionAndContinueOnFailure("Distance Charge column is present",
	        		getAction().getText(INSTALLATION_INFO_DISTANCE_CHARGE_TEXT).equalsIgnoreCase("Distance Charge"));
	  		
	  		SoftAssert.checkConditionAndContinueOnFailure("Installation Date column is present",
	        		getAction().getText(INSTALLATION_INFO_INSTALL_DATE_TEXT).equalsIgnoreCase("Installation Date"));
	  		
	  		SoftAssert.checkConditionAndContinueOnFailure("Inclusion Text column is present",
	        		getAction().getText(INSTALLATION_INFO_INCLUSION_TEXT_TEXT).equalsIgnoreCase("Inclusion Text"));
	  		
	  		SoftAssert.checkConditionAndContinueOnFailure("Product Details column is present",
	        		getAction().getText(INSTALLATION_INFO_PROD_DETAILS_TEXT).equalsIgnoreCase("Product Details"));
	  		
	  		SoftAssert.checkConditionAndContinueOnFailure("Statements column is present",
	        		getAction().getText(INSTALLATION_INFO_STATEMENTS_TEXT).equalsIgnoreCase("Statements"));
	  		
	  		SoftAssert.checkConditionAndContinueOnFailure("Store Ringing Instr column is present",
	        		getAction().getText(INSTALLATION_INFO_STORE_RINGING_INSTR_TEXT).equalsIgnoreCase("Store Ringing Instr"));
	  		
	  		SoftAssert.checkConditionAndContinueOnFailure("Store Number column is present",
	        		getAction().getText(INSTALLATION_INFO_STORE_NUMBER_TEXT).equalsIgnoreCase("Store Number"));
	  		
	  		SoftAssert.checkConditionAndContinueOnFailure("Installation Address column is present",
	        		getAction().getText(INSTALLATION_INFO_INSTALL_ADDRESS_TEXT).equalsIgnoreCase("Installation Address"));
	  		
	  		Logger.log("Verify Installation Date' is "+ sdf2.format(sdf1.parse(installation_date))+" in Installation Information Table", TestStepType.STEP);
	  		PageAssert.verifyEqual(sdf2.format(sdf1.parse(installation_date)), getAction().getText(INSTALLATION_INFO_INSTALL_DATE));
	  		
	    	/*PageAssert.verifyEqual(inclusion_text, getAction().getText(INSTALLATION_INFO_INCLUSION_TEXT));
	    	Logger.log("Verify Inclusion Text is "+ inclusion_text+" in Installation Information Table", TestStepType.STEP);
	    	PageAssert.verifyEqual(product_details, getAction().getText(INSTALLATION_INFO_PROD_DETAILS));
	    	Logger.log("Verify Product Details is "+ product_details+" in Installation Information Table", TestStepType.STEP);
	    	*/
	  		Logger.log("Verify statements is "+ statements+" in Installation Information Table", TestStepType.STEP);
	    	PageAssert.elementPresent(INSTALLATION_INFO_STATEMENTS);
	    	
	    	//PageAssert.verifyEqual(store_ringing_instr, getAction().getText(INSTALLATION_INFO_STORE_RINGING_INSTR));
	    	//Logger.log("Verify Store Ringing Instr is "+ store_ringing_instr+" in Installation Information Table", TestStepType.STEP);
	    	if(ffm_type.equals("SPU")  ){
	    		Logger.log("Verify Store Number is "+ store_number+" in Installation Information Table", TestStepType.STEP);
	    	PageAssert.verifyEqual(store_number, getAction().getText(INSTALLATION_INFO_STORE_NUMBER));
	    	
	    	}else{
	    		store_number = "";
	    		Logger.log("Verify Store Number is "+ store_number+" in Installation Information Table", TestStepType.STEP);
	    		PageAssert.verifyEqual(store_number, getAction().getText(INSTALLATION_INFO_STORE_NUMBER));
		    	
	    	}
	    	Logger.log("Verify Address 1 is "+ installation_address1+" in Installation Information Table", TestStepType.STEP);
	    	PageAssert.verifyEqual(installation_address1, getAction().getText(INSTALLATION_INFO_INSTALL_ADDRESS1));
	    	

	    	if(installation_address2!=null){
	    		Logger.log("Verify Address 2 is "+ installation_address2+" in Installation Information Table", TestStepType.STEP);
	            PageAssert.verifyEqual(installation_address2, getAction().getText(INSTALLATION_INFO_INSTALL_ADDRESS2));
	            
	          
	            }else{
	            	installation_address2="";
	            	Logger.log("Verify Address 2 is Empty in Installation Information Table", TestStepType.STEP);
	            	PageAssert.verifyEqual(installation_address2, getAction().getText(INSTALLATION_INFO_INSTALL_ADDRESS2));
	   	            
	   	        
		            }
	    	
	    	Logger.log("Verify Installation City and State is "+ city+", "+state+" in Installation Information Table", TestStepType.STEP);
	    	PageAssert.textPresentIn(INSTALLATION_INFO_INSTALL_CITY_STATE,city);
	    	
	    	Reporter.log("Verify Information for Installation Information Table Successfully !");
		  		}else{
		  			Reporter.log("Failed Retrieval Data for Installation Information Table ");
		  		}
  			}else{
  				AjaxCondition.forElementVisible(IINSTALLATION_INFO_NO_RESPONSE).waitForResponse(10);
  				Reporter.log("No Data in Database for Installation Information Table ");
      		   }
          try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
      	return this;
      	}    
 
	public LineItemDetailsPage verifyGiftCardInformation() {
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		ArrayList<String> serialnumber = new ArrayList<String>();
		ArrayList<String> cardNO = new ArrayList<String>();
		ArrayList<String> from = new ArrayList<String>();
		ArrayList<String> to = new ArrayList<String>();
		ArrayList<String> email = new ArrayList<String>();
		ArrayList<String> senderAddress = new ArrayList<String>();
		ArrayList<String> receiverAddress  =  new ArrayList<String>();
		ArrayList<String> price  =  new ArrayList<String>();
		ArrayList<String> message  =  new ArrayList<String>();
        String serialNumber = null;
        String giftCardNumber = null;
        String fromWhere =null;
        String toWhere = null;
        String emailAddress = null;
        String senderaddress = null;
        String receiveraddress = null;
        String priceAmount =null;
        String messages = null;
        String itemNumberContent = getAction().getText(ITEM_NUMBER);
    	String itemNumber = itemNumberContent.split(" ")[itemNumberContent.split(" ").length-1];
    	Logger.log("Verify whether item number is displayed");
        PageAssert.verifyPartiallyEqual(getAction().getText(ITEM_NUMBER), "Item # ");
    	
    	String sql ="select gcc.GIFT_CARD_SERIAL_NUMBER,goi.GIFT_CARD_NUMBER,goi.FROM_NAME,goi.TO_NAME,goi.COMMENTS, goi.EMAIL,goi.AMOUNT "
    			+ "from ord o, ord_item oi, sales_check sc, gift_card_control gcc, giftcard_order_item goi where o.order_id = oi.order_id "
    			+ "and oi.order_item_id = goi.order_item_id and oi.order_id = sc.order_id and oi.order_item_id=? group by goi.email;";

    	/*"select gcc.GIFT_CARD_SERIAL_NUMBER,goi.GIFT_CARD_NUMBER,goi.FROM_NAME,goi.TO_NAME,goi.EMAIL,goi.AMOUNT,cci.ADDR_LINE_1, "
    	+ "cci.ADDR_LINE_2, cci.CITY, cci.STATE_CD, cci.ZIP_CD from ord o, ord_item oi, sales_check sc, gift_card_control gcc, giftcard_order_item goi,"
    	+ "customer_contact_info cci,payment_instruction pi where o.order_id = oi.order_id and oi.order_item_id = goi.order_item_id and "
    	+ "oi.order_id = sc.order_id and o.BILLING_ADDRESS_ID = cci.ADDRESS_ID and pi.ADDRESS_ID = cci.ADDRESS_ID and oi.order_item_id='1712008' group by goi.email";*/
    	 try {
			    st = conn.prepareStatement(sql);
	            st.setString (1, itemNumber);
	            st.execute();
	            rs = st.getResultSet();
	            while(rs.next()){
	            	serialnumber.add(rs.getString("GIFT_CARD_SERIAL_NUMBER"));
	            	cardNO.add(rs.getString("GIFT_CARD_NUMBER"));
	            	from.add(rs.getString("FROM_NAME"));
	            	to.add(rs.getString("TO_NAME"));
	            	email.add(rs.getString("EMAIL"));
	            	price.add(rs.getString("AMOUNT"));
	            	message.add(rs.getString("COMMENTS"));
	            }  
		     } catch (SQLException e) {
		         e.printStackTrace();
		     }
    		getAction().waitFor(2000);
    		PageAssert.textPresent(GIFT_CARD_INFO_TITLE, "Gift Card Information");
    		Logger.log("Verify Gift Card Information Title is Present", TestStepType.STEP);
    		if(serialnumber.size()!=0||cardNO.size()!=0||from.size()!=0||to.size()!=0||email.size()!=0||price.size()!=0){
    			
	    		if(getAction().isVisible(GIFT_CARD_INFO_TABLE)){
	    		Logger.log("Verify Gift Card Information Table is Visible", TestStepType.STEP);
	    		
		  		SoftAssert.checkConditionAndContinueOnFailure("Gift Card Information Serial Number column is present",
		        		getAction().getText(GIFT_CARD_INFO_SERIAL_NUMBER_TEXT).equalsIgnoreCase("SerialNumber"));
		  		
		  		SoftAssert.checkConditionAndContinueOnFailure("Gift Card Information Card Number column is present",
		        		getAction().getText(GIFT_CARD_INFO_CARD_NUMBER_TEXT).equalsIgnoreCase("Card #"));
		  		
		  		SoftAssert.checkConditionAndContinueOnFailure("Gift Card Information From column is present",
		        		getAction().getText(GIFT_CARD_INFO_FROM_TEXT).equalsIgnoreCase("From"));
		  		
		  		SoftAssert.checkConditionAndContinueOnFailure("Gift Card Information To column is present",
		        		getAction().getText(GIFT_CARD_INFO_TO_TEXT).equalsIgnoreCase("To"));
		  		
		  		SoftAssert.checkConditionAndContinueOnFailure("Gift Card Information Email column is present",
		        		getAction().getText(GIFT_CARD_INFO_EMAIL_TEXT).equalsIgnoreCase("Email #"));
		  		
		  		SoftAssert.checkConditionAndContinueOnFailure("Gift Card Sender Address column is present",
		        		getAction().getText(GIFT_CARD_INFO_SENDER_ADDRESS_TEXT).equalsIgnoreCase("Sender Address"));
		  		
		  		SoftAssert.checkConditionAndContinueOnFailure("Receiver Address column is present",
		        		getAction().getText(GIFT_CARD_INFO_RECEIVER_ADDRESS_TEXT).equalsIgnoreCase("ReceiverAddress"));
		  		
		  		SoftAssert.checkConditionAndContinueOnFailure("Price column is present",
		        		getAction().getText(GIFT_CARD_INFO_AMOUNT_TEXT).equalsIgnoreCase("Price"));
		  		
		  		SoftAssert.checkConditionAndContinueOnFailure("Message column is present",
		        		getAction().getText(GIFT_CARD_INFO_MESSAGE_TEXT).equalsIgnoreCase("Message"));

		  		WebElement giftcardinfotable = getAction().findElement(GIFT_CARD_INFO_TABLE_CONTENT);
		  		List<WebElement> giftcardinfo_table_rows = giftcardinfotable.findElements(By.tagName("tr"));
		  		int giftcardinfo_table_rows_count = giftcardinfo_table_rows.size();
		  		for (int i=0; i<giftcardinfo_table_rows_count; i++){
					 List<WebElement> giftcardinfo_table_columns = giftcardinfo_table_rows.get(i).findElements(By.tagName("td"));
					 if(serialnumber.get(i)==null){
						 serialNumber = "";
					 }else{
						 serialNumber = serialnumber.get(i);
					 }
					 if(cardNO.get(i)==null){
						 giftCardNumber = "";
					 }else{
						 giftCardNumber = cardNO.get(i);
					 }
					 if(from.get(i)==null){
						 fromWhere = "";
					 }else{
						 fromWhere = from.get(i);
					 }
					 if(to.get(i)==null){
						 toWhere = "";
					 }else{
						 toWhere = to.get(i);
					 }
					 if(email.get(i)==null){
						 emailAddress = "";
					 }else{
						 emailAddress = email.get(i);
					 }
					 
					 if(message.get(i)==null){
						 messages = "";
					 }else{
						 messages = message.get(i);
					 }
					 
			    	 Logger.log("Verify Gift Card Information Serial Number is "+ serialNumber +" in Database and "+giftcardinfo_table_columns.get(0).getText()+" is displayed", TestStepType.STEP);
			    	 PageAssert.verifyEqual(serialNumber, giftcardinfo_table_columns.get(0).getText());
			    	 
			    	 Logger.log("Verify Gift Card Information Card Number is "+ giftCardNumber +" in Database and "+giftcardinfo_table_columns.get(1).getText()+" is displayed", TestStepType.STEP);
			    	 PageAssert.verifyEqual(giftCardNumber, giftcardinfo_table_columns.get(1).getText());
			    	 
			    	 Logger.log("Verify Gift Card Information From is "+ fromWhere +" in Database and "+giftcardinfo_table_columns.get(2).getText()+" is displayed", TestStepType.STEP);
			    	 PageAssert.verifyEqual(fromWhere, giftcardinfo_table_columns.get(2).getText());
			    	 
			    	 Logger.log("Verify Gift Card Information To is "+ toWhere +" in Database and "+giftcardinfo_table_columns.get(3).getText()+" is displayed", TestStepType.STEP);
			    	 PageAssert.verifyEqual(toWhere, giftcardinfo_table_columns.get(3).getText());
			    	 
			    	 Logger.log("Verify Gift Card Information Email is "+ emailAddress +" in Database and "+giftcardinfo_table_columns.get(4).getText()+" is displayed", TestStepType.STEP);
			    	 PageAssert.verifyEqual(emailAddress, giftcardinfo_table_columns.get(4).getText());
			    	 
			    	 if(price.get(i)==null){
						 priceAmount = "";
				    	 Logger.log("Verify Gift Card Information Price is "+ priceAmount +" in Database and "+giftcardinfo_table_columns.get(7).getText()+" is displayed", TestStepType.STEP);
				    	 PageAssert.verifyEqual(priceAmount, giftcardinfo_table_columns.get(7).getText());
				    	 
					 }else{
						 priceAmount = price.get(i);
				    	 Logger.log("Verify Gift Card Information Price is "+ "$"+df.format(Double.parseDouble(priceAmount)) +" in Database and "+giftcardinfo_table_columns.get(7).getText()+" is displayed", TestStepType.STEP);
				    	 PageAssert.verifyEqual("$"+df.format(Double.parseDouble(priceAmount)), giftcardinfo_table_columns.get(7).getText());
				    	 }
			    	 Logger.log("Verify Gift Card Information Message is "+ messages +" in Database and "+giftcardinfo_table_columns.get(4).getText()+" is displayed", TestStepType.STEP);
			    	 PageAssert.verifyEqual(messages, giftcardinfo_table_columns.get(8).getText());
			    	 }	
		  				Reporter.log("Verified Gift Card Informaiton Table Successfully");
		  		
		    		}else{
		    			Reporter.log("Failed Retrieval Data for Gift Card Information Table ");
		    		}
    			}else{
    				AjaxCondition.forElementVisible(GIFT_CARD_INFO_NO_RESPONSE).waitForResponse(10);
    				Reporter.log("No Data in Database for Gift Card Information Table ");
    		   }
    		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
    		return this;
    	}
    
    public LineItemDetailsPage verifyDiscountsOnLineItem(String OrderID) {
    	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
    	ArrayList<String> description  =  new ArrayList<String>();
    	ArrayList<Double> amount  =  new ArrayList<Double>();
    	getAction().waitFor(5000);
    	String itemNumberContent = getAction().getText(ITEM_NUMBER);
    	String itemNumber = itemNumberContent.split(" ")[itemNumberContent.split(" ").length-1];
    		
    	String sql ="select PROMOTION_NAME, AMOUNT "
				+ "from ord_item_discount where order_item_id in (select order_item_id "
				+ "from ord_item where order_id in (select order_id from ord where site_gen_ord_id = ?) and order_item_id = ? ) ";

    	try {
    		st = conn.prepareStatement(sql);
    		st.setString (1, OrderID);
    		st.setString (2, itemNumber);
    		st.execute();
    		rs = st.getResultSet();
    		
    		while(rs.next()){
 
			description.add(rs.getString("PROMOTION_NAME"));
			amount.add(rs.getDouble("AMOUNT"));
			
    		}
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	getAction().waitFor(1000);

		if(description.size()!=0 || amount.size()!=0){
    		Logger.log("Starting Verify Discounts Table Content for Item No: "+itemNumber, TestStepType.STEP);
    		PageAssert.textPresent(DISCOUNTS_TITLE, "Discounts");
    		Logger.log("Verify Discounts Text is Present In Line Item Summary", TestStepType.STEP);
    		//highlight(DISCOUNTS_TITLE);
    		
    		if(getAction().isVisible(DISCOUNTS_TABLE)){
    		Logger.log("Verify Discount Table is Visible In Line Item Summary", TestStepType.STEP);
    		
	  		SoftAssert.checkConditionAndContinueOnFailure("Discount Description column is present",
	        		getAction().getText(DESCRIPTION_TEXT).equalsIgnoreCase("Discount Description"));
    		
	  		SoftAssert.checkConditionAndContinueOnFailure("Amount column is present",
	        		getAction().getText(AMOUNT_TEXT).equalsIgnoreCase("Amount"));
    		
    		WebElement discountsstable = getAction().findElement(DISCOUNTS_TABLE_CONTENT );
    		List<WebElement> disct_table_rows = discountsstable.findElements(By.tagName("tr"));
    		int disct_table_rows_count = disct_table_rows.size();
    		
    		for (int i=0; i<disct_table_rows_count; i++){

			   List<WebElement> disct_table_columns = disct_table_rows.get(i).findElements(By.tagName("td"));
			   
			   Logger.log("Verify Description in Discount is "+ description.get(i) +" in Database and "+disct_table_columns.get(0).getText()+" is displayed In Line Item Summary", TestStepType.STEP);
			   PageAssert.verifyEqual(description.get(i), disct_table_columns.get(0).getText());
	    	   
			   Logger.log("Verify Amount in Discount is "+"$"+String.format("%.2f", Math.abs(amount.get(i)))+" in Database and "+disct_table_columns.get(1).getText()+" is displayed In Line Item Summary", TestStepType.STEP);
	    	   PageAssert.verifyEqual("($"+df.format(Math.abs(amount.get(i)))+")", disct_table_columns.get(1).getText());
	    	   
    		}	
    		}else{
    			PageAssert.fail("Failed Retrieval Data for Line Item Discounts");
    		}
    			}else{
    				AjaxCondition.forElementVisible(DISCOUNTS_NO_RESPONSE).waitForResponse(10);
    				Logger.log("No Line ItemDiscounts information found in Database", TestStepType.STEP);
    			
    		   }
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;
    	}   
    
    public LineItemDetailsPage verifyAdjustmentsOnLineItem(String OrderID) throws ParseException {
    	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
    	ArrayList<String> type = new ArrayList<String>();
		ArrayList<Double> amount = new ArrayList<Double>();
		ArrayList<String> date = new ArrayList<String>();
		ArrayList<String> sc_No = new ArrayList<String>();
		
		getAction().waitFor(3000);
		String itemNumberContent = getAction().getText(ITEM_NUMBER);
    	String itemNumber = itemNumberContent.split(" ")[itemNumberContent.split(" ").length-1];
    	
		String sql ="select r.rma_type, r.TOTAL_CREDIT_AMT, sctf.eod_dt, r.RETURN_SALES_CHECK_NUMBER "
				+ "from ord o, ord_item oi, rma r, rma_item ri, sales_check_tran_file sctf "
				+ "where oi.order_id = o.order_id and oi.order_item_id =ri.order_item_id and r.rma_id = ri.rma_id  "
				+ "and r.tran_file_id = sctf.tran_file_id and site_gen_ord_id = ? and oi.ORDER_ITEM_ID =?";
		/***
		 * data from mysql database
		 ***/
    	try {
    		st = conn.prepareStatement(sql);
    		st.setString (1, OrderID);
    		st.setString (2, itemNumber);
    		st.execute();
    		rs = st.getResultSet();
    		getAction().waitFor(1000);
    		while(rs.next()){

			type.add(rs.getString("RMA_TYPE"));
			amount.add(rs.getDouble("TOTAL_CREDIT_AMT"));
			date.add(rs.getString("sctf.eod_dt"));
			sc_No.add(rs.getString("RETURN_SALES_CHECK_NUMBER"));
			}
    		
    	} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	if(type.size()!=0 || amount.size()!=0 || date.size()!=0 ||sc_No.size()!=0){
    		
    		
		/***
    	 * verify text present
    	 ***/
    		getAction().waitFor(1000);
    		
    		PageAssert.textPresent(ADJUSTMENTS_TITLE, "Adjustments");
    		
    		Logger.log("Verify Adjustment Text is Present In Line Item Summary", TestStepType.STEP);
    		if( getAction().isVisible(ADJUSTMENTS_TABLE)){
    		Logger.log("Verify Adjustment Table is Visible In Line Item Summary", TestStepType.STEP);
	  		SoftAssert.checkConditionAndContinueOnFailure("Adjustment Type column is present",
	        		getAction().getText(ADJUSTMENTS_TYPE_COL_NAME).equalsIgnoreCase("Type"));
	  		
    		SoftAssert.checkConditionAndContinueOnFailure("Adjustment Amount column is present",
	        		getAction().getText(ADJUSTMENTS_AMOUNT_COL_NAME).equalsIgnoreCase("Amount"));
    		
    		SoftAssert.checkConditionAndContinueOnFailure("Adjustment Date column is present",
	        		getAction().getText(ADJUSTMENTS_DATE_COL_NAME).equalsIgnoreCase("Date"));
    		
    		SoftAssert.checkConditionAndContinueOnFailure("Adjustment Date column is present",
	        		getAction().getText(ADJUSTMENTS_SALECHECK_NO_COL_NAME).equalsIgnoreCase("Sales Check #"));
    		
		/***
    	 * verify data
    	 ***/
    		getAction().waitFor(1000);

    		WebElement adjustmentstable = getAction().findElement(ADJUSTMENTS_TABLE_CONTENT );
    		List<WebElement> adj_table_rows = adjustmentstable.findElements(By.tagName("tr"));
    		int adj_table_rows_count = adj_table_rows.size();
    		String type_des = null;	
    		System.out.println("table rows"+adj_table_rows_count);
    		for (int i=0; i<adj_table_rows_count; i++){
			   List<WebElement> adj_table_columns = adj_table_rows.get(i).findElements(By.tagName("td"));
			   if (type.get(i).equals("TADJ")){
				   type_des = "SALESTAX";
			   }else if (type.get(i).equals("SLADJ")){
				   type_des = "SALE";
			   }else if (type.get(i).equals("SHADJ")){
				   type_des = "Shipping Charge";
			   }else if (type.get(i).equals("REFWORT")){
				   type_des = "REFUNDWITHOUTRETURN";
			   }
			   
	    	   Logger.log("Verify Type of Adjustment is "+ type_des+" in Database", TestStepType.STEP);
	    	   PageAssert.verifyEqual(type_des, adj_table_columns.get(0).getText());
	    	   

	    	   Logger.log("Verify Amount in Adjustment is "+"($"+df.format(amount.get(i))+")"+" in DataBase", TestStepType.STEP);
	    	   PageAssert.verifyEqual("($"+formatter.format(amount.get(i))+")", adj_table_columns.get(1).getText());

	    	   Logger.log("Verify Date in Adjustment is "+date.get(i)+" in Database", TestStepType.STEP);
			   PageAssert.verifyEqual(sdf2.format(sdf1.parse(date.get(i))), adj_table_columns.get(2).getText());

			   Logger.log("Verify Sales Check # in  Adjustment is "+ sc_No.get(i)+" in Database", TestStepType.STEP);
			   PageAssert.verifyEqual(sc_No.get(i), adj_table_columns.get(3).getText());

    			}	
   		 	}else{
   		 	Logger.log("Failed retrieval data for Adjustments ", TestStepType.STEP);
				PageAssert.fail("Retrieval Data from Database failed");
			}
		}else{
			AjaxCondition.forElementVisible(ADJUSTMENTS_NO_RESPONSE).waitForResponse(10);
			Logger.log("There is no Adjustments Information in Database", TestStepType.STEP);
		}

    	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
    	return this;
    }
    

public LineItemDetailsPage lineItemSummaryPageVerify(int saleschecknumber,String OrderID, String storeId) throws ParseException {
    	
	 String expectedShipDate = null;
     String expectedArrivalDate = null;
     String itemStatuscd= null;
     String itemStatus = null;
     String salesCheckNo = null;
     String fulfillBy = null;
     String zeroPercent = null;
     String SKU = null;
     String description = null;
     String quantity= null;
     String mfrModelNo = null;
     String storecd = null;
     String store =null;
     Double weight = null;
     Double price = null;
     Double tax = null;
     Double taxPercent= null;
     Double surchargeAmt = null;
     Double environmentalFee = null;
     Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
     AjaxCondition.forElementVisible(ITEM_NUMBER).waitForResponse();
     String itemNumberContent = getAction().getText(ITEM_NUMBER);
 	String itemNumber = itemNumberContent.split(" ")[itemNumberContent.split(" ").length-1];
     String salesCheckNumber = getAction().getText(SALES_CHECK_NUMBER.format(saleschecknumber));
     PageAssert.verifyPartiallyEqual(getAction().getText(ITEM_NUMBER), "Item # ");
 	Logger.log("item number is displayed");
     String sql ="select oi.REQUESTED_SHIP_DATE, oi.PROMISED_AVAILABLE_TIME, oi.ORDER_ITEM_STS_CD, sc.SALES_CHECK_NUMBER, "
     		+ "fm.ffm_class_id, oi.FFM_METHOD_ID, oi.ITEM_ID, oi.ITEM_NM, oi.QUANTITY, oi.MANUFACTURER_MODEL_NUMBER, oi.SITE_ID, "
     		+ "pi.WEIGHT, oi.LIST_PRICE, oi.TAX_AMT, oi.TAX_RATE, oi.SURCHARGE_AMOUNT, oi.env_fee from ord o, ord_item oi, "
     		+ "sales_check sc, ffm_method fm, product_dimension_order_item pi where o.order_id = oi.order_id and o.order_id = sc.order_id and "
     		+ "oi.ffm_method_id = fm.ffm_method_id and oi.ORDER_ITEM_ID=pi.ORDER_ITEM_ID and oi.ORDER_ITEM_ID = ? and sc.SALES_CHECK_NUMBER =? ";
     try {
			    st = conn.prepareStatement(sql);
	            st.setString (1, itemNumber);
	            st.setString (2, salesCheckNumber);
	            st.execute();
	            rs = st.getResultSet();
	            while(rs.next()){
	            expectedShipDate = rs.getString("REQUESTED_SHIP_DATE") ;
	            expectedArrivalDate = rs.getString("PROMISED_AVAILABLE_TIME") ;
	            itemStatuscd = rs.getString("ORDER_ITEM_STS_CD") ;
	            salesCheckNo=rs.getString("SALES_CHECK_NUMBER") ;
	            fulfillBy=rs.getString("ffm_class_id") ;
	            //zeroPercent =rs.getString("") ;
	            SKU = rs.getString("ITEM_ID") ;
	            description = rs.getString("ITEM_NM").replaceAll("2&trade;", "").trim().replaceAll(" +", " ") ;
	            quantity= rs.getString("QUANTITY") ;
	            mfrModelNo = rs.getString("MANUFACTURER_MODEL_NUMBER") ;
	            storecd = rs.getString("SITE_ID") ;
	            weight = rs.getDouble("WEIGHT") ;
	            price = rs.getDouble("LIST_PRICE") ;
	            tax = rs.getDouble("TAX_AMT") ;
	            taxPercent=rs.getDouble("TAX_RATE") ;
	            surchargeAmt = rs.getDouble("SURCHARGE_AMOUNT") ;
	            environmentalFee = rs.getDouble("env_fee") ;}
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
     			Logger.log("Verify Line Item Summary Title Text is Present", TestStepType.STEP);
			    PageAssert.textPresent(LINE_ITEM_SUMMARY_TITLE, "Line Item Summary");
			    
	            
			    if(SKU!=null){
			    if(getAction().isVisible(LINE_ITEM_SUMMARY_TABLE)) {
			    	Logger.log("Line Item Summary Table is Visiable", TestStepType.STEP);
	            /****
	             * Verify Text Present
	             ****/
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Line Item Summary Title Text is Present",getAction().getText(LINE_ITEM_SUMMARY_TITLE).equals("Line Item Summary"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Line Item Expected Ship Date Text is Present",getAction().getText(LINE_ITEM_EXPECTED_SHIP_DATE_TEXT).equals("Expected Ship Date"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Item Status Text is Present",getAction().getText(LINE_ITEM_ITEM_STATUS_TEXT).equals("Item Status"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Sales Check # Text is Present",getAction().getText(LINE_ITEM_SALES_CHECK_NUMBER_TEXT).equals("Sales Check #"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Fulfill By Text is Present",getAction().getText(LINE_ITEM_FULFILL_BY_TEXT).equals("Fulfill By"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Zero Percent Text is Present",getAction().getText(LINE_ITEM_ZERO_PERCENT_TEXT).equals("Zero Percent"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify SKU Text is Present",getAction().getText(LINE_ITEM_SKU_TEXT).equals("SKU"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Description Text is Present",getAction().getText(LINE_ITEM_DESCRIPTION_TEXT).equals("Description"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Quantity Text is Present",getAction().getText(LINE_ITEM_QUANTITY_TEXT).equals("Quantity"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Mfr Model # Text is Present",getAction().getText(LINE_ITEM_MFR_MODEL_NUMBER_TEXT).equals("Mfr Model #"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Store Text is Present",getAction().getText(LINE_ITEM_STORE_TEXT).equals("Store"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Weight (in lbs) Text is Present",getAction().isVisible(LINE_ITEM_WEIGHT_TEXT));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Price Text is Present",getAction().getText(LINE_ITEM_PRICE_TEXT).equals("Price"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Tax Text is Present",getAction().getText(LINE_ITEM_TAX_TEXT).equals("Tax"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Tax Percent Text is Present",getAction().getText(LINE_ITEM_TAX_PERCENT_TEXT).equals("Tax Percent"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Surcharge Amt Text is Present",getAction().getText(LINE_ITEM_SURCHARGE_AMT_TEXT).equals("Surcharge Amt"));
        		SoftAssert.checkConditionAndContinueOnFailure("Verify Environmental Fee Text is Present",getAction().getText(LINE_ITEM_ENVIRONMENTAL_FEE_TEXT).equals("Environmental Fee"));
                
			    if(getAction().isVisible(LINE_ITEM_SUMMARY_TABLE)) {
			    	Logger.log("Line Item Summary Table is Visiable", TestStepType.STEP);
			    	if(expectedShipDate == null){
			    		expectedShipDate = "";
			    		SoftAssert.checkConditionAndContinueOnFailure("Verify Expected Ship Date",expectedShipDate.equals(getAction().getText(LINE_ITEM_EXPECTED_SHIP_DATE_DETAIL)));
		            }else{
		            	SoftAssert.checkConditionAndContinueOnFailure("Verify Expected Ship Date",getAction().getText(LINE_ITEM_EXPECTED_SHIP_DATE_DETAIL).equals(sdf2.format(sdf1.parse(expectedShipDate.split(" ")[0]))));
		            	}
			    	if(expectedArrivalDate == null)  {
			    		 expectedArrivalDate = "";	 
			    		 SoftAssert.checkConditionAndContinueOnFailure("Verify Expected Arrival Date",expectedArrivalDate.equals(getAction().getText(LINE_ITEM_EXPECTED_ARRIVAL_DATE_DETAIL)));
				     }else{
				    	 SoftAssert.checkConditionAndContinueOnFailure("Verify Expected Arrival Date",getAction().getText(LINE_ITEM_EXPECTED_ARRIVAL_DATE_DETAIL).equals(sdf2.format(sdf1.parse(expectedArrivalDate.split(" ")[0]))));   	
		    		} 
			    	
			    	itemStatus=Utility.getLineItemStatusDescription(itemStatuscd);
			    	SoftAssert.checkConditionAndContinueOnFailure("Verify Item Status",itemStatus.equals(getAction().getText(LINE_ITEM_ITEM_STATUS_DETAIL)));
			    	SoftAssert.checkConditionAndContinueOnFailure("Verify Sales Check #",salesCheckNo.equals(getAction().getText(LINE_ITEM_SALES_CHECK_NUMBER_DETAIL)));
			    	SoftAssert.checkConditionAndContinueOnFailure("Verify Fulfill By",getAction().getText(LINE_ITEM_FULFILL_BY_DETAIL).contains(fulfillBy));
				  
			    	SoftAssert.checkConditionAndContinueOnFailure("Verify SKU",SKU.equals(getAction().getText(LINE_ITEM_SKU_DETAIL)));
					
			    	SoftAssert.checkConditionAndContinueOnFailure("Verify Quantity",quantity.equals(getAction().getText(LINE_ITEM_QUANTITY_DETAIL)));
			    	SoftAssert.checkConditionAndContinueOnFailure("Verify Mfr Model #",mfrModelNo.equals(getAction().getText(LINE_ITEM_MFR_MODEL_NUMBER_DETAIL)));
					
			    	store=getStoreName(storecd);

			    SoftAssert.checkConditionAndContinueOnFailure("Verify Store",getAction().getText(LINE_ITEM_STORE_DETAIL).equals(store+" ("+storecd+")"));
//	    	   SoftAssert.checkConditionAndContinueOnFailure("Verify Weight (in lbs)",getAction().getText(LINE_ITEM_WEIGHT_DETAIL).equals(weight)); TODO
	    	   SoftAssert.checkConditionAndContinueOnFailure("Verify Price",getAction().getText(LINE_ITEM_PRICE_DETAIL).equals("$"+df.format(price)));
//	    	   SoftAssert.checkConditionAndContinueOnFailure("Verify Tax",getAction().getText(LINE_ITEM_TAX_DETAIL).equals("$"+df.format(tax)+"%")); TODO
			    
	    	   SoftAssert.checkConditionAndContinueOnFailure("Verify Tax Percent",getAction().getText(LINE_ITEM_TAX_PERCENT_DETAIL).equals(df.format(taxPercent)+" %"));
	    	   SoftAssert.checkConditionAndContinueOnFailure("Verify Surcharge Amt",getAction().getText(LINE_ITEM_SURCHARGE_AMT_DETAIL).equals("$"+df.format(surchargeAmt)));
	    	   SoftAssert.checkConditionAndContinueOnFailure("Verify Environmental Fee",getAction().getText(LINE_ITEM_ENVIRONMENTAL_FEE_DETAIL).equals("$"+df.format(environmentalFee)));
			
		    }
		    
		    Reporter.log("Verified Sales Check Summary Table Successfully");
		    
		    String shippingmethod = null;
	        String shippingcharge = null;
	        String shippingquantity = null;
	       
	        String sql_ship ="select sm.service_level_code, oi.SHIPPING_AMT, si.QUANTITY from ord_item oi left join shipping_mode sm "
	        		+ "on sm.shipping_mode_id = oi.SHIPPING_MODE_ID left join shipped_item si on si.order_item_id = oi.order_item_id "
	        		+ "where oi.order_item_id = ?";

	        try {
				    st = conn.prepareStatement(sql_ship);
		            st.setString (1, itemNumber);
		            st.execute();
		            rs = st.getResultSet();
		            while(rs.next()){
		           
		            shippingmethod = rs.getString("service_level_code") ;
		            shippingcharge = rs.getString("SHIPPING_AMT") ;
		            shippingquantity = rs.getString("QUANTITY") ;
		            }
		            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        /****
	         * Verify Text Present
	         ****/
	        SoftAssert.checkConditionAndContinueOnFailure("Verify Line Item Shipping Information Title Text is Present",getAction().getText(SHIPPING_INFORMATION_TITLE).equals("Shipping Information"));
	        SoftAssert.checkConditionAndContinueOnFailure("Verify Line Item Shipping Method Text is Present",getAction().getText(SHIPPING_INFO_SHIPPING_METHOD_TEXT).equals("Shipping Method"));
	        SoftAssert.checkConditionAndContinueOnFailure("Verify Line Item Shipping Charge Text is Present",getAction().getText(SHIPPING_INFO_SHIPPING_CHARGE_TEXT).equals("Shipping Charge"));
	        SoftAssert.checkConditionAndContinueOnFailure("Verify Item Shipped Quantity Text is Present",getAction().getText(SHIPPING_INFO_SHIPPED_QUANTITY_TEXT).equals("Shipped Quantity"));
	        if(getAction().isVisible(SHIPPING_INFORMATION_TABLE)) {
	        	if(shippingmethod==null){
	        		shippingmethod = "Store";
	        		SoftAssert.checkConditionAndContinueOnFailure("VerifyShipping Method ",shippingmethod.equals(getAction().getText(SHIPPING_INFO_SHIPPING_METHOD_DETAIL).trim()));	
	        	}else{
	        	SoftAssert.checkConditionAndContinueOnFailure("VerifyShipping Method ",shippingmethod.equals(getAction().getText(SHIPPING_INFO_SHIPPING_METHOD_DETAIL).trim()));
	        	}
	        if(shippingcharge==null){
	        	shippingcharge="$0.00";
	        	SoftAssert.checkConditionAndContinueOnFailure("Verify Shipping Charge ",getAction().getText(SHIPPING_INFO_SHIPPING_CHARGE_DETAIL).equals(shippingcharge));
	        }else{
	        	SoftAssert.checkConditionAndContinueOnFailure("Verify Shipping Charge ",getAction().getText(SHIPPING_INFO_SHIPPING_CHARGE_DETAIL).equals("$"+df.format(Double.parseDouble(shippingcharge))));
	        }
	        if(shippingquantity==null) {
		    		shippingquantity = "0";
		    		SoftAssert.checkConditionAndContinueOnFailure("Verify Shipped Quantity ",shippingquantity.equals(getAction().getText(SHIPPING_INFO_SHIPPED_QUANTITY_DETAIL)));
		    	}else{  
		    		SoftAssert.checkConditionAndContinueOnFailure("Verify Shipped Quantity ",shippingquantity.equals(getAction().getText(SHIPPING_INFO_SHIPPED_QUANTITY_DETAIL)));
			    	
		    	}
		    }
		    
		    Reporter.log("Verified Line Item Shipping Information Table Successfully");
		    
		   
	  		//getAction().waitFor(2000);
      		String shippingCarrier = null;
            String trackingNumber = null;
            //getAction().waitFor(2000);
            String sql_tracking_details ="select CARRIER, TRACKING_NUMBER from ship_unit where SHIP_UNIT_ID in (select SHIP_UNIT_ID from ship_invoice_item "
            		+ "where order_item_id =?)";
            try {
    			    st = conn.prepareStatement(sql_tracking_details);
    	            st.setString (1, itemNumber);
    	            st.execute();
    	            rs = st.getResultSet();
    	            while(rs.next()){
    	            shippingCarrier = rs.getString("CARRIER") ;
    	            trackingNumber = rs.getString("TRACKING_NUMBER") ;
    	            System.out.println(shippingCarrier);
    	            System.out.println(trackingNumber);
    	            }  
            } catch (SQLException e) {
                e.printStackTrace();
            }
	  		SoftAssert.checkConditionAndContinueOnFailure("Verify Tracking Details Title is Present",getAction().getText(TRACKING_DETAIL_TITLE).equals("Tracking Details"));
	  		if(shippingCarrier!=null ||trackingNumber!=null){
	      		Logger.log("Verify Tracking Details Table is Visible", TestStepType.STEP);
	  		if(getAction().isVisible(TRACKING_DETAIL_TABLE)){
	  		Logger.log("Verify Tracking Details Table is Visible", TestStepType.STEP);
		  		 /****
		         * Verify Text Present
		         ****/
		  		SoftAssert.checkConditionAndContinueOnFailure("Verify Line Item Shipping Carrier Text is Present",getAction().getText(TRACKING_CARRIER_TEXT).equals("Shipping Carrier"));
		  		SoftAssert.checkConditionAndContinueOnFailure("Verify Line Item Tracking # Text is Present",getAction().getText(TRACKING_NUMBER_TEXT).equals("Tracking #"));
		        /****
		         * Verify Data
		         ****/
	        	if(shippingCarrier==null){
	        		shippingCarrier = "";
	        	}
	        	SoftAssert.checkConditionAndContinueOnFailure("Verify Shipping Carrier is "+ shippingCarrier+" in Database and Displayed in Line Item Summary",getAction().getText(TRACKING_CARRIER).equals(shippingCarrier));
		        
	 	    	if(trackingNumber==null){
	 	    		trackingNumber = "";
	        	}
	 	    	SoftAssert.checkConditionAndContinueOnFailure("Verify Tracking Number is "+ trackingNumber+" in Database and Displayed in Line Item Summary",getAction().getText(TRACKING_NUMBER).equals(trackingNumber));
		        
		    	Reporter.log("Verified Line Item Tracking Details Table Successfully");
		  		}else{
	  				Reporter.log("Failed Retrieval Data for Tracking Details Table~");
	  			}
	  		}else{
	  			SoftAssert.checkElementAndContinueOnFailure(TRACKING_DETAIL_NO_RESPONSE, "No Tracking Details found in Tracking Detail Table", CheckLocatorFor.isPresent);
	  		}
	  		
	  		ArrayList<String> previouse_return_qty  =  new ArrayList<String>();
    	  	ArrayList<String> return_info_sc  =  new ArrayList<String>();
    	  	ArrayList<Double> return_info_amt  =  new ArrayList<Double>();
    	  	ArrayList<String> return_info_rgino  =  new ArrayList<String>();
    	  	ArrayList<String> return_info_rmano  =  new ArrayList<String>();
    	  	ArrayList<String> return_info_reason_code  =  new ArrayList<String>();
    	  	ArrayList<String> return_info_return_date  =  new ArrayList<String>();
    	  	ArrayList<String> return_info_quantity  =  new ArrayList<String>();
    	  	
            String sql_return_info ="select r.return_sales_check_number,r.total_credit_amt,r.RMA_ID,rmi.RETURN_REASON_CD,oir.TRACKINGDATE,rmi.QUANTITY "
            		+ "from rma r,rma_item rmi,sales_check sc, ord_item oi, order_item_return oir where oi.order_id = sc.order_id "
            		+ "and oi.order_item_id = oir.order_item_id and sc.sales_check_id=r.sales_check_id and rmi.RMA_ID=r.RMA_ID "
            		+ "and oi.ORDER_ITEM_ID= ? and sc.sales_check_number=? ;";
    	        try {
    				    st = conn.prepareStatement(sql_return_info);
    		            st.setString (1, itemNumber);
    		            st.setString (2, salesCheckNumber);
    		            st.execute();
    		            rs = st.getResultSet();
    		            while(rs.next()){
    		            	return_info_sc.add(rs.getString("return_sales_check_number"));
    			            return_info_amt.add(rs.getDouble("total_credit_amt"));
    			            return_info_rmano.add(rs.getString("RMA_ID"));
    			            return_info_reason_code.add(rs.getString("RETURN_REASON_CD"));
    			            return_info_return_date.add(rs.getString("TRACKINGDATE"));
    			            return_info_quantity.add(rs.getString("QUANTITY"));
    		            }  
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
		  	SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information Title is Present",getAction().getText(RETURN_INFORMATION_TITLE).equals("Return Information"));
		  	if(return_info_quantity.size()!=0){
		  		if(getAction().isVisible(RETURN_INFORMATION_TABLE)){
		  		Logger.log("Verify Return Information Table is Visible", TestStepType.STEP);
		  		SoftAssert.checkConditionAndContinueOnFailure("Verify Previous Return Qty Column Name is Present",getAction().getText(PREVIOUSE_RETURN_QTY_TEXT).equals("Previous Return Qty:"));
		  		SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information Sales Check Number Column Name is Present",getAction().getText(RETURN_INFORMATION_SC_TEXT).equals("Sales Check#"));
		  		SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information Amount Column Name is Present",getAction().getText(RETURN_INFORMATION_AMT_TEXT).equals("Amount"));
		  		SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information RGI # Column Name is Present",getAction().getText(RETURN_INFORMATION_RGI_NO_TEXT).equals("RGI #"));
		  		SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information RMA # Column Name is Present",getAction().getText(RETURN_INFORMATION_RMA_NO_TEXT).equals("RMA #"));
		  		SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information Reason Code Column Name is Present",getAction().getText(RETURN_INFORMATION_REASON_CODE_TEXT).equals("Reason Code"));
		  		SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information Return Date Column Name is Present",getAction().getText(RETURN_INFORMATION_RETURN_DATE_TEXT).equals("Return Date"));

		  		WebElement returninfotable = getAction().findElement(RETURN_INFORMATION_TABLE_CONTENT );
		  		List<WebElement> returninfo_table_rows = returninfotable.findElements(By.tagName("tr"));
		  		int returninfo_table_rows_count = returninfo_table_rows.size();
		  		
		  		for (int i=0; i<returninfo_table_rows_count; i++){
					 List<WebElement> returninfo_table_columns = returninfo_table_rows.get(i).findElements(By.tagName("td"));
					 //SoftAssert.checkConditionAndContinueOnFailure("Verify Previous Return Qty in Return Information",previouse_return_qty.get(i).equals(returninfo_table_columns.get(0).getText()));
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information Sales Check Number in Return Information",return_info_sc.get(i).equals(returninfo_table_columns.get(0).getText()));
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information Amount in Return Information ",return_info_amt.get(i).equals(returninfo_table_columns.get(1).getText()));
					// SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information RGI # in Return Information",return_info_rgino.get(i).equals(returninfo_table_columns.get(2).getText()));
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information RMA # in Return Information",return_info_rmano.get(i).equals(returninfo_table_columns.get(3).getText()));
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information Reason Code in Return Information",return_info_reason_code.get(i).equals(returninfo_table_columns.get(4).getText()));
					// SoftAssert.checkConditionAndContinueOnFailure("Verify Return Information Return Date in Return Information",return_info_return_date.get(i).equals(returninfo_table_columns.get(5).getText()));
			  				}Reporter.log("Verified Line Item Return Informaiton Table Successfully");
			  			}else{
			  				Reporter.log("Failed Retrival Data for Return Informaiton Table~");
			  			}
		  			}else{
		  				SoftAssert.checkElementAndContinueOnFailure(RETURN_INFORMATION_NO_RESPONSE, "No Return Information found ", CheckLocatorFor.isPresent);

		  		   }
		  		
		  		//getAction().waitFor(1000);
		  		ArrayList<String> tracking_date  =  new ArrayList<String>();
	    	  	ArrayList<String> carrier_type  =  new ArrayList<String>();
	    	  	ArrayList<String> return_tracking_number  =  new ArrayList<String>();
	    	  	ArrayList<String> quantity_rt  =  new ArrayList<String>();
	            String sql_return_tracking ="select rt.created_ts, rt.CARRIER, rt.TRACKING_NUMBER,rt.QUANTITY from return_tracking rt,ord_item oi,ord o, sales_check sc "
	            		+ "where oi.ORDER_ITEM_ID=rt.ORDER_ITEM_ID and o.ORDER_ID=oi.ORDER_ID and oi.order_id = sc.order_id and oi.ORDER_ITEM_ID= ? "
	            		+ "and sc.sales_check_number=?";  
	    	        try {
	    				    st = conn.prepareStatement(sql_return_tracking);
	    		            st.setString (1, itemNumber);
	    		            st.setString (2, salesCheckNumber);
	    		            st.execute();
	    		            rs = st.getResultSet();
	    		            while(rs.next()){
	    		            	tracking_date.add(rs.getString("created_ts"));
	    		            	carrier_type.add(rs.getString("CARRIER"));
	    		            	return_tracking_number.add(rs.getString("TRACKING_NUMBER"));
	    		            	quantity_rt.add(rs.getString("QUANTITY"));
	    		            }  
	    	        } catch (SQLException e) {
	    	            e.printStackTrace();
	    	        }
	          	//getAction().waitFor(1000);
		  		SoftAssert.checkConditionAndContinueOnFailure("Verify Return Tracking Information Title is Present",getAction().getText(RETURN_TRACKING_INFORMATION_TITLE).equals("Return Tracking Information"));
		  		if(quantity_rt.size()!=0){
		  		if(getAction().isVisible(RETURN_TRACKING_INFORMATION_TABLE)){
		  		Logger.log("Verify Return Tracking Information Table is Visible", TestStepType.STEP);
			  		SoftAssert.checkConditionAndContinueOnFailure("Verify Tracking Date is Present",getAction().getText(RETURN_TRACKING_DATE_TEXT).equals("Tracking Date"));
			  		SoftAssert.checkConditionAndContinueOnFailure("Verify Return Tracking Carrier Type is Present",getAction().getText(RETURN_TRACKING_CARRIER_TEXT).equals("Carrier Type"));
			  		SoftAssert.checkConditionAndContinueOnFailure("Verify Return Tracking Number is Present",getAction().getText(RETURN_TRACKING_NUMBER_TEXT).equals("Return Tracking Number"));
			  		SoftAssert.checkConditionAndContinueOnFailure("Verify Return Tracking Quantity is Present",getAction().getText(RETURN_TRACKING_QTY_TEXT).equals("Quantity"));
			  		
		      		WebElement returntrackinginfotable = getAction().findElement(RETURN_TRACKING_INFORMATION_TABLE_CONTENT );
		      		List<WebElement> returntrackinginfo_table_rows = returntrackinginfotable.findElements(By.tagName("tr"));
		      		int returntrackinginfo_table_rows_count = returntrackinginfo_table_rows.size();
		      		for (int i=0; i<returntrackinginfo_table_rows_count; i++){
		    			 List<WebElement> returntrackinginfo_table_columns = returntrackinginfo_table_rows.get(i).findElements(By.tagName("td"));
		    			 SoftAssert.checkConditionAndContinueOnFailure("Verify Return Tracking Information 'Retrun Date'",sdf2.format(sdf1.parse(tracking_date.get(i).split(" ")[0])).equals(returntrackinginfo_table_columns.get(0).getText()));
		    			 SoftAssert.checkConditionAndContinueOnFailure("Verify Return Tracking Information Carrier",carrier_type.get(i).equals(returntrackinginfo_table_columns.get(1).getText()));
		    			 SoftAssert.checkConditionAndContinueOnFailure("Verify Return Tracking Information Tracking Number",return_tracking_number.get(i).equals(returntrackinginfo_table_columns.get(2).getText()));
		    			 SoftAssert.checkConditionAndContinueOnFailure("Verify Return Tracking Information Quantiy",quantity_rt.get(i).equals(returntrackinginfo_table_columns.get(3).getText()));
						 	}Reporter.log("Verified Line Item Return Tracking Informaiton Table Successfully");
		      		}else{
		      			Reporter.log("Failed Retrieval Data for Return Tracking Information Table ");
		      		}
	  			}else{
	  				SoftAssert.checkElementAndContinueOnFailure(RETURN_TRACKING_INFORMATION_NO_RESPONSE, "No Return Tracking Information found", CheckLocatorFor.isPresent);
	  			}
	  		
		  		//getAction().waitFor(1000);
		  		String city = null;
	            String state = null;
	            Double base_installation_cost  =  null;
	            Double distance_charge  =  null;
	            String installation_date  =  null;
	            String inclusion_text  =  null;
	            String product_details  =  null;
	    	  	String statements  =  null;
	    	  	String store_ringing_instr =  null;
	    	  	String store_number =  null;
	    	  	String installation_address1 = null;
	    	  	String installation_address2 = null;
	    	  	String ffm_type = null;
	            String sql_general_if ="select dioi.INSTALLATION_DATE,oi.ITEM_NM,fm.ffm_class_id,o.site_id "
	            		+ "from ord o, ord_item oi, delivery_installation_order_item dioi, ffm_method fm "
	            		+ "where o.order_id = oi.order_id and dioi.order_item_id = oi.order_item_id "
	            		+ "and oi.ffm_method_id = fm.ffm_method_id and oi.order_item_id=?";  
	            String customer_sql =" select cci.FIRST_NM, cci.LAST_NM, cci.ADDR_LINE_1, cci.ADDR_LINE_2, cci.CITY, "
	            		+ "cci.STATE_CD, cci.ZIP_CD, cci. COUNTRY_CD "
	            		+ "from ord o, customer_contact_info cci "
	            		+ "where o. BILLING_ADDRESS_ID = cci.ADDRESS_ID and o.site_gen_ord_id =  ?";
	            
	            String store_sql ="select name, phone, ADDR_LINE_1, ADDR_LINE_2, city, state, zip_cd "
	            		+ "from unit u, ffm_method ffm, sales_check sc, ord_item oi, ord o "
	            		+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = ffm.ffm_method_id "
	            		+ "and u.unit_number = ffm.RETAIL_UNIT_NO and oi.SALES_CHECK_ID = sc.SALES_CHECK_ID and sc.sales_check_number =  ?";
	              try {
	    				    st = conn.prepareStatement(sql_general_if);
	    		            st.setString (1, itemNumber);
	    		            st.execute();
	    		            rs = st.getResultSet();
	    		            while(rs.next()){
	    		            	installation_date = rs.getString("INSTALLATION_DATE");
	    		            	statements = rs.getString("ITEM_NM");
	    		            	store_number = rs.getString("site_id");
	    		            	ffm_type = rs.getString("ffm_class_id");
	    		            	System.out.println("ffm type: "+ffm_type);
	    		            }  
	    	        } catch (SQLException e) {
	    	            e.printStackTrace();
	    	        }
	              //getAction().waitFor(2000);
	              if(installation_date!=null ||statements!=null ||installation_address1!=null){
	              if(ffm_type.equals("SPU")){
	              	try {
	      			    st = conn.prepareStatement(store_sql);
	      	   
	      	            st.setString (1, salesCheckNumber);
	      	            st.execute();
	      	            rs = st.getResultSet();
	      	            getAction().waitFor(1000);
	      	            while(rs.next()){
	      	            installation_address1 = rs.getString("ADDR_LINE_1");
	      	            installation_address2 = rs.getString("ADDR_LINE_2");
	      	            city = rs.getString("city");
	      	            state = rs.getString("state");
	      	            }
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
	      	            installation_address1 = rs.getString("ADDR_LINE_1");
	      	            installation_address2 = rs.getString("ADDR_LINE_2");
	      	            city = rs.getString("CITY");
	      	            state = rs.getString("STATE_CD");
	      	            }
	      	            } catch (SQLException e) {
	      	                e.printStackTrace();
	      	            }}
	            
	      		getAction().waitFor(1000);
	      		SoftAssert.checkConditionAndContinueOnFailure("Verify Installation Information Title is Present",getAction().getText(INSTALLATION_INFO_TITLE).equals("Installation Information"));
		  		
		  		
		  		if(getAction().isVisible(INSTALLATION_INFO_TABLE)){
		  			Logger.log("Verify Installation Information Table is Visible", TestStepType.STEP);
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Base Installation Cost is Present",getAction().getText(INSTALLATION_INFO_BASE_INSTAL_COST_TEXT).equals("Base Installation Cost"));
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Distance Charge is Present",getAction().getText(INSTALLATION_INFO_DISTANCE_CHARGE_TEXT).equals("Distance Charge"));
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Installation Date is Present",getAction().getText(INSTALLATION_INFO_INSTALL_DATE_TEXT).equals("Installation Date"));
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Inclusion Text is Present",getAction().getText(INSTALLATION_INFO_INCLUSION_TEXT_TEXT).equals("Inclusion Text"));
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Product Details is Present",getAction().getText(INSTALLATION_INFO_PROD_DETAILS_TEXT).equals("Product Details"));
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Statements is Present",getAction().getText(INSTALLATION_INFO_STATEMENTS_TEXT).equals("Statements"));
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Store Ringing Instr is Present",getAction().getText(INSTALLATION_INFO_STORE_RINGING_INSTR_TEXT).equals("Store Ringing Instr"));
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Store Number is Present",getAction().getText(INSTALLATION_INFO_STORE_NUMBER_TEXT).equals("Store Number"));
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Installation Address is Present",getAction().getText(INSTALLATION_INFO_INSTALL_ADDRESS_TEXT).equals("Installation Address"));
					
		  			//SoftAssert.checkConditionAndContinueOnFailure("Verify Base Installation Cost",getAction().getText(INSTALLATION_INFO_BASE_INSTAL_COST).equals("$"+df.format(base_installation_cost)));
		  			//SoftAssert.checkConditionAndContinueOnFailure("Verify Distance Charge",getAction().getText(INSTALLATION_INFO_DISTANCE_CHARGE).equals("$"+df.format(distance_charge)));
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Installation Date",getAction().getText(INSTALLATION_INFO_INSTALL_DATE).equals(sdf2.format(sdf1.parse(installation_date))));
		  			//SoftAssert.checkConditionAndContinueOnFailure("Verify Inclusion Text",getAction().getText(INSTALLATION_INFO_INCLUSION_TEXT).equals(inclusion_text));
		  			//SoftAssert.checkConditionAndContinueOnFailure("Verify Product Details",getAction().getText(INSTALLATION_INFO_PROD_DETAILS).equals(product_details));
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Statements",getAction().getText(INSTALLATION_INFO_STATEMENTS).equals(statements));
		  			//SoftAssert.checkConditionAndContinueOnFailure("Verify Store Ringing Instr",getAction().getText(INSTALLATION_INFO_STORE_RINGING_INSTR).equals(store_ringing_instr));
					if(ffm_type.equals("SPU")  ){
						SoftAssert.checkConditionAndContinueOnFailure("Verify Store Number",getAction().getText(INSTALLATION_INFO_STORE_NUMBER).equals(store_number));
			  			}else{
	    	    		store_number = "";
	    	    		SoftAssert.checkConditionAndContinueOnFailure("Verify Store Number",getAction().getText(INSTALLATION_INFO_STORE_NUMBER).equals(store_number));
			  			}
					SoftAssert.checkConditionAndContinueOnFailure("Verify Address 1",getAction().getText(INSTALLATION_INFO_INSTALL_ADDRESS1).equals(installation_address1));
		  			if(installation_address2!=null){
		  				SoftAssert.checkConditionAndContinueOnFailure("Verify Address 2",getAction().getText(INSTALLATION_INFO_INSTALL_ADDRESS2).equals(installation_address2));
			  			}else{
    	            	installation_address2="";
    	            	SoftAssert.checkConditionAndContinueOnFailure("Verify Address 2",getAction().getText(INSTALLATION_INFO_INSTALL_ADDRESS2).equals(installation_address2));
			  			}
		  			SoftAssert.checkConditionAndContinueOnFailure("Verify Installation City and State ",getAction().getText(INSTALLATION_INFO_INSTALL_CITY_STATE).equals(city+", "+state));
		  			Reporter.log("Verify Information for Installation Information Table Successfully !");
			  		}else{
			  			Reporter.log("Failed Retrieval Data for Installation Information Table ");
			  		}
		  		}else{
		  			SoftAssert.checkElementAndContinueOnFailure(IINSTALLATION_INFO_NO_RESPONSE, "No Installation Information found", CheckLocatorFor.isPresent);
		  
		      		   }	
		  		//getAction().waitFor(2000);
		  		ArrayList<String> serialnumber = new ArrayList<String>();
	    		ArrayList<String> cardNO = new ArrayList<String>();
	    		ArrayList<String> from = new ArrayList<String>();
	    		ArrayList<String> to = new ArrayList<String>();
	    		ArrayList<String> email = new ArrayList<String>();
	    		ArrayList<String> senderAddress = new ArrayList<String>();
	    		ArrayList<String> receiverAddress  =  new ArrayList<String>();
	    		ArrayList<String> price_gf  =  new ArrayList<String>();
	    		ArrayList<String> message  =  new ArrayList<String>();
	            String serialNumber = null;
	            String giftCardNumber = null;
	            String fromWhere =null;
	            String toWhere = null;
	            String emailAddress = null;
	            String senderaddress = null;
	            String receiveraddress = null;
	            String priceAmount =null;
	            String messages = null;
	        	String sql_GF_info ="select gcc.GIFT_CARD_SERIAL_NUMBER,goi.GIFT_CARD_NUMBER,goi.FROM_NAME,goi.TO_NAME,goi.COMMENTS, goi.EMAIL,goi.AMOUNT "
	        			+ "from ord o, ord_item oi, sales_check sc, gift_card_control gcc, giftcard_order_item goi where o.order_id = oi.order_id "
	        			+ "and oi.order_item_id = goi.order_item_id and oi.order_id = sc.order_id and oi.order_item_id=? group by goi.email;";
	        	try {
	    			    st = conn.prepareStatement(sql_GF_info);
	    	            st.setString (1, itemNumber);
	    	            st.execute();
	    	            rs = st.getResultSet();
	    	            while(rs.next()){
	    	            	serialnumber.add(rs.getString("GIFT_CARD_SERIAL_NUMBER"));
	    	            	cardNO.add(rs.getString("GIFT_CARD_NUMBER"));
	    	            	from.add(rs.getString("FROM_NAME"));
	    	            	to.add(rs.getString("TO_NAME"));
	    	            	email.add(rs.getString("EMAIL"));
	    	            	price_gf.add(rs.getString("AMOUNT"));
	    	            	message.add(rs.getString("COMMENTS"));
	    	            }  
	    		     } catch (SQLException e) {
	    		         e.printStackTrace();
	    		     }
	        		//getAction().waitFor(2000);
	        		SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Title is Present",getAction().getText(GIFT_CARD_INFO_TITLE).equals("Gift Card Information"));
			  		
	        		if(serialnumber.size()!=0||cardNO.size()!=0||from.size()!=0||to.size()!=0||email.size()!=0||price_gf.size()!=0){
	    		if(getAction().isVisible(GIFT_CARD_INFO_TABLE)){
	    		Logger.log("Verify Gift Card Information Table is Visible", TestStepType.STEP);
	    		SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Serial Number Text is Present",getAction().getText(GIFT_CARD_INFO_SERIAL_NUMBER_TEXT).equals("SerialNumber"));
	    		SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Card Number Text is Present",getAction().getText(GIFT_CARD_INFO_CARD_NUMBER_TEXT).equals("Card #"));
	    		SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information From Text is Present",getAction().getText(GIFT_CARD_INFO_FROM_TEXT).equals("From"));
	    		SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information To Text is Present",getAction().getText(GIFT_CARD_INFO_TO_TEXT).equals("To"));
	    		SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Email Text is Present",getAction().getText(GIFT_CARD_INFO_EMAIL_TEXT).equals("Email #"));
	    		SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Sender Address Text is Present",getAction().getText(GIFT_CARD_INFO_SENDER_ADDRESS_TEXT).equals("Sender Address"));
	    		SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Receiver Address Text is Present",getAction().getText(GIFT_CARD_INFO_RECEIVER_ADDRESS_TEXT).equals("ReceiverAddress"));
	    		SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Price Text is Present",getAction().getText(GIFT_CARD_INFO_AMOUNT_TEXT).equals("Price"));
	    		SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Message Text is Present",getAction().getText(GIFT_CARD_INFO_MESSAGE_TEXT).equals("Message"));
				
		  		WebElement giftcardinfotable = getAction().findElement(GIFT_CARD_INFO_TABLE_CONTENT);
		  		List<WebElement> giftcardinfo_table_rows = giftcardinfotable.findElements(By.tagName("tr"));
		  		int giftcardinfo_table_rows_count = giftcardinfo_table_rows.size();
		  		for (int i=0; i<giftcardinfo_table_rows_count; i++){
					 List<WebElement> giftcardinfo_table_columns = giftcardinfo_table_rows.get(i).findElements(By.tagName("td"));
					 if(serialnumber.get(i)==null){
						 serialNumber = "";
					 	}else{
						 serialNumber = serialnumber.get(i);}
					 if(cardNO.get(i)==null){
						 giftCardNumber = "";
					 		}else{
						 giftCardNumber = cardNO.get(i);}
					 if(from.get(i)==null){
						 fromWhere = "";
					 	}else{
						 fromWhere = from.get(i);}
					 if(to.get(i)==null){
						 toWhere = "";
					 	}else{
						 toWhere = to.get(i); }
					 if(email.get(i)==null){
						 emailAddress = "";
					 	}else{
						 emailAddress = email.get(i);}
					 if(message.get(i)==null){
						 messages = "";
					 	}else{
						 messages = message.get(i);}
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Serial Number",serialNumber.equals(giftcardinfo_table_columns.get(0).getText()));
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Card Number",giftCardNumber.equals(giftcardinfo_table_columns.get(1).getText()));
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information From",fromWhere.equals(giftcardinfo_table_columns.get(2).getText()));
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information To",toWhere.equals(giftcardinfo_table_columns.get(3).getText()));
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Email",emailAddress.equals(giftcardinfo_table_columns.get(4).getText()));
					 if(price_gf.get(i)==null){
						 priceAmount = "";
						 SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Price",priceAmount.equals(giftcardinfo_table_columns.get(7).getText()));
					 }else{
						 priceAmount = price_gf.get(i);
						 SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Price",priceAmount.equals(giftcardinfo_table_columns.get(7).getText()));
						 }
					 SoftAssert.checkConditionAndContinueOnFailure("Verify Gift Card Information Message",messages.equals(giftcardinfo_table_columns.get(8).getText()));
					 }	
		  				Reporter.log("Verified Gift Card Informaiton Table Successfully");
		  		
		    		}else{
		    			Reporter.log("Failed Retrieval Data for Gift Card Information Table ");}
	    		}else{
    				SoftAssert.checkElementAndContinueOnFailure(GIFT_CARD_INFO_NO_RESPONSE, "No gift card information found", CheckLocatorFor.isPresent);
 
    		   }
	        		
	    		ArrayList<String> description_page  =  new ArrayList<String>();
	        	ArrayList<Double> amount_page  =  new ArrayList<Double>();
	        	//getAction().waitFor(1000);
	        		
	        	String sql_discount ="select PROMOTION_NAME, AMOUNT "
	    				+ "from ord_item_discount where order_item_id in (select order_item_id "
	    				+ "from ord_item where order_id in (select order_id from ord where site_gen_ord_id = ?) ) ";

	        	try {
	        		st = conn.prepareStatement(sql_discount);
	        		st.setString (1, OrderID);
	        		st.execute();
	        		rs = st.getResultSet();
	        		
	        		while(rs.next()){
	     
	    			description_page.add(rs.getString("PROMOTION_NAME"));
	    			amount_page.add(rs.getDouble("AMOUNT"));
	    			
	        		}
	        	} catch (SQLException e) {
	    			e.printStackTrace();
	    		}
	        	//getAction().waitFor(1000);
	        	
	        		Logger.log("Starting Verify Discounts Table Content for Item No: "+itemNumber, TestStepType.STEP);
	        		SoftAssert.checkConditionAndContinueOnFailure("Verify Discounts Text is Present In Line Item Summary",getAction().getText(DISCOUNTS_TITLE).equals("Discounts"));
	        		if(getAction().isVisible(DISCOUNTS_TABLE)){
	        		Logger.log("Verify Discount Table is Visible In Line Item Summary", TestStepType.STEP);
	        		SoftAssert.checkConditionAndContinueOnFailure("Verify Discount Description Column Name is Present In Line Item Summary",getAction().getText(DESCRIPTION_TEXT).equals("Discount Description"));
	        		SoftAssert.checkConditionAndContinueOnFailure("Verify Discount Amount Column Name is Present In Line Item Summary",getAction().getText(AMOUNT_TEXT).equals("Amount"));
	        		WebElement discountsstable = getAction().findElement(DISCOUNTS_TABLE_CONTENT );
	        		List<WebElement> disct_table_rows = discountsstable.findElements(By.tagName("tr"));
	        		int disct_table_rows_count = disct_table_rows.size();
	        		
	        		for (int i=0; i<disct_table_rows_count; i++){

	    			   List<WebElement> disct_table_columns = disct_table_rows.get(i).findElements(By.tagName("td"));
	    			   
	    			   SoftAssert.checkConditionAndContinueOnFailure("Verify Description in Discount",description_page.get(i).equals(disct_table_columns.get(0).getText()));
	    			   SoftAssert.checkConditionAndContinueOnFailure("Verify Amount in Discount",disct_table_columns.get(1).getText().equals("($"+df.format(Math.abs(amount_page.get(i)))+")"));
	    			  }	
	        			}else{
	        				SoftAssert.checkElementAndContinueOnFailure(DISCOUNTS_NO_RESPONSE, "No Discounts Found", CheckLocatorFor.isPresent);
	        			}	
	        		ArrayList<String> type_adj = new ArrayList<String>();
	        		ArrayList<Double> amount_adj = new ArrayList<Double>();
	        		ArrayList<String> date_adj = new ArrayList<String>();
	        		ArrayList<String> sc_No_adj = new ArrayList<String>();
	        		JSONArray adjustmentArray=null;
	            	String type_adj_ws=null;
	        		
	        		//need edit query
	        		String sql_adj ="select r.rma_type, r.TOTAL_CREDIT_AMT, sctf.eod_dt, r.RETURN_SALES_CHECK_NUMBER "
				+ "from ord o, ord_item oi, rma r, rma_item ri, sales_check_tran_file sctf "
				+ "where oi.order_id = o.order_id and oi.order_item_id =ri.order_item_id and r.rma_id = ri.rma_id  "
				+ "and r.tran_file_id = sctf.tran_file_id and site_gen_ord_id = ? and oi.ORDER_ITEM_ID =?";
	        		/***
	        		 * data from mysql database
	        		 ***/
	        		try {
	            		st = conn.prepareStatement(sql);
	            		st.setString (1, OrderID);
	            		st.setString (2, itemNumber);
	            		st.execute();
	            		rs = st.getResultSet();
	            		//getAction().waitFor(1000);
	            		while(rs.next()){

	        			type_adj.add(rs.getString("RMA_TYPE"));
	        			amount_adj.add(rs.getDouble("TOTAL_CREDIT_AMT"));
	        			date_adj.add(rs.getString("sctf.eod_dt"));
	        			sc_No_adj.add(rs.getString("RETURN_SALES_CHECK_NUMBER"));
	        			}
	            	} catch (SQLException e) {
	        			e.printStackTrace();
	        		}
	        		if(type_adj.size()!=0 || amount_adj.size()!=0 || date_adj.size()!=0 ||sc_No_adj.size()!=0){
	            	

	                    SoftAssert.checkConditionAndContinueOnFailure("Verify Adjustment Text is Present In Line Item Summary",getAction().getText(ADJUSTMENTS_TITLE).equals("Adjustments"));
		        		
	            		if( getAction().isVisible(ADJUSTMENTS_TABLE)){
	            		Logger.log("Verify Adjustment Table is Visible In Line Item Summary", TestStepType.STEP);
	            		SoftAssert.checkConditionAndContinueOnFailure("Verify Adjustment Type Column Name is Present In Line Item Summary",getAction().getText(ADJUSTMENTS_TYPE_COL_NAME).equals("Type"));
	            		SoftAssert.checkConditionAndContinueOnFailure("Verify Adjustment Amount Column Name is Present In Line Item Summary",getAction().getText(ADJUSTMENTS_AMOUNT_COL_NAME).equals("Amount"));
	            		SoftAssert.checkConditionAndContinueOnFailure("Verify Adjustment Date Column Name is Present In Line Item Summary",getAction().getText(ADJUSTMENTS_DATE_COL_NAME).equals("Date"));
	            		SoftAssert.checkConditionAndContinueOnFailure("Verify Adjustment Sales Check # Column Name is Present In Line Item Summary",getAction().getText(ADJUSTMENTS_SALECHECK_NO_COL_NAME).equals("Sales Check #"));
			        
	        			/***
	        	    	 * verify data
	        	    	 ***/
	                    getAction().waitFor(3000);

	            		WebElement adjustmentstable = getAction().findElement(ADJUSTMENTS_TABLE_CONTENT );
	            		
				        
	            		List<WebElement> adj_table_rows = adjustmentstable.findElements(By.tagName("tr"));
	            		int adj_table_rows_count = adj_table_rows.size();
	            		String type_des = null;	
	            		System.out.println("table rows"+adj_table_rows_count);
	            		for (int i=0; i<adj_table_rows_count; i++){
	        			   List<WebElement> adj_table_columns = adj_table_rows.get(i).findElements(By.tagName("td"));
	        			   if (type_adj.get(i).equals("TADJ")){
	        				   type_des = "SALESTAX";
	        			   }else if (type_adj.get(i).equals("SLADJ")){
	        				   type_des = "SALE";
	        			   }else if (type_adj.get(i).equals("SHADJ")){
	        				   type_des = "Shipping Charge";
	        			   }else if (type_adj.get(i).equals("REFWORT")){
	        				   type_des = "REFUNDWITHOUTRETURN";
	        			   }
	        			   SoftAssert.checkConditionAndContinueOnFailure("Verify Type of Adjustment",type_des.equals(adj_table_columns.get(0).getText()));
	        			   SoftAssert.checkConditionAndContinueOnFailure("Verify Amount of Adjustment",adj_table_columns.get(1).getText().equals("($"+df.format(amount_adj.get(i))+")"));
	        			   SoftAssert.checkConditionAndContinueOnFailure("Verify Date in Adjustment",sdf2.format(sdf1.parse(date_adj.get(i))).equals(adj_table_columns.get(2).getText()));
	        			   SoftAssert.checkConditionAndContinueOnFailure("Verify Sales Check # in  Adjustment",sc_No_adj.get(i).equals(adj_table_columns.get(3).getText()));
			            }	
	            			}  else{
	            				Logger.log("Failed retrieval data for Adjustments ", TestStepType.STEP);
	            				PageAssert.fail("Retrieval Data from Database failed");
	                    		

	            			}
	            		}else{

	            			SoftAssert.checkElementAndContinueOnFailure(ADJUSTMENTS_NO_RESPONSE, "There is no Adjustments Information in Database", CheckLocatorFor.isPresent);

	            		}
	            	//getAction().waitFor(1000);}}
			    }}
			    try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
				return this;
    	}

public JSONObject verifysoapdetails(String OrderID, String storeId)  {
	String url = "http://oms.qa.ch3.s.com/KanaRS/services/Sears-Services";

	String pagerequest="sch:RetrieveOrderSummaryRequest", schemaURL="http://www.shc.com/schema", transactionid="39ee24db-cd9e-47fe-a687-a0d56f3d57ce", username="XXXX",password="XXXXX",  orderid = OrderID, stoteid = storeId, source="msp";
	
	JSONObject orderSummary=null;
	String result=null;
	
	try {
		result=SOAPRequest.getSOAPRespose(url,SOAPRequest.createSOAPRequest( pagerequest, schemaURL, transactionid, username, password, orderid, stoteid, source));
		JSONObject xmlJSONObj = XML.toJSONObject(result);

		System.out.println("result: "+result);

		//System.out.println("RESPONCE COVERTED TO JSON:"+jsonResp);
		JSONObject envelop=(JSONObject)xmlJSONObj.get("soapenv:Envelope");
		JSONObject body=(JSONObject)envelop.get("soapenv:Body");
		JSONObject retrieveOrderSummaryResponse=(JSONObject)body.get("RetrieveOrderSummaryResponse");
		JSONObject dataArea=(JSONObject)retrieveOrderSummaryResponse.get("DATAAREA");
		orderSummary=(JSONObject)dataArea.get("OrderSummary");
	
        } catch (Exception e) {
		e.printStackTrace();
	}
	
	return orderSummary;		 

}

public LineItemDetailsPage verify_Reschedule_Delivery(){
	
	Logger.log("Verify Reschedule delivery popup text ", TestStepType.STEP);
	AjaxCondition.forElementVisible(RESCHEDULE_DELIVERY_TEXT).waitForResponse(10);
	Logger.log("RESCHEDULE DELIVERY TEXT is present ", TestStepType.STEP);
	Logger.log("Verify Reschedule Form ", TestStepType.STEP);
	AjaxCondition.forElementVisible(RESCHEDULE_DELIVERY_FORM).waitForResponse(10);
	Logger.log("RESCHEDULE DELIVERY Form is present ", TestStepType.STEP);
	AjaxCondition.forElementVisible(RESCHEDULE_DELIVERY_SELECT_CALENDER).waitForResponse(10);
	getAction().click(RESCHEDULE_DELIVERY_SELECT_CALENDER);
	getAction().waitFor(3000);
	AjaxCondition.forElementVisible(NEW_SELECTED_DATE).waitForResponse(10);
	getAction().click(NEW_SELECTED_DATE);
	getAction().waitFor(3000);
	AjaxCondition.forElementVisible(RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES).waitForResponse(10);
	getAction().click(RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES);
	getAction().waitFor(2000);
	if(getAction().isVisible(RESCHEDULE_DELIVERY_ERROR_POPUP))
	{
		getAction().waitFor(3000);
	
		AjaxCondition.forElementVisible(RESCHEDULE_DELIVERY_ERROR_POPUP_CLOSE).waitForResponse(10);
		getAction().click(RESCHEDULE_DELIVERY_ERROR_POPUP_CLOSE);
		AjaxCondition.forElementVisible(NEW_SELECTED_DATE).waitForResponse(10);
		getAction().click(NEW_SELECTED_DATE);
		AjaxCondition.forElementVisible(RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES).waitForResponse(10);
		getAction().click(RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES);

	}
		else
		{
			getAction().waitFor(10000);
			getAction().click(SELECTED_RESON_CODE);
	
					getAction().selectByVisibleText(SELECTED_RESON_CODE, "Customer Initiated Rescheduling");
					AjaxCondition.forElementVisible(NOTES).waitForResponse();
					getAction().click(NOTES);
					getAction().type(NOTES,"Customer Initiated Rescheduling");
					AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
					//getAction().click(SUBMIT_BUTTON);
		
}
		
	return this;
}

public LineItemDetailsPage verifyOptionIsNotVisible(String optionName){
	getAction().waitFor(3000);
	if(!getAction().isVisible(ORDERDETAILS_ACTION_SELECT_BOX)){
		Logger.log("Dropdown Box is not Visible", TestStepType.VERIFICATION_SUBSTEP);
	
	}else{
		PageAssert.textNotPresentIn(ORDERDETAILS_ACTION_SELECT_BOX, optionName);
		Logger.log("Verify "+optionName+" is Not Visible in DropDownList", TestStepType.VERIFICATION_SUBSTEP);
	}
	return this;
	
}


public LineItemDetailsPage verifyScheduleReturnPopUp(){
	
	Logger.log("Verify if \"Schedule Return\" PopUp is Visible",TestStepType.VERIFICATION_STEP);
	AjaxCondition.forElementVisible(SCHEDULERETURN_POP_UP).waitForResponse();
	
	Logger.log("Verify if \"Schedule Return Text\" is Visible in PopUp",TestStepType.VERIFICATION_SUBSTEP);
	AjaxCondition.forElementVisible(SCHEDULERETURN_ACTION_TEXT).waitForResponse();
	
	AjaxCondition.forElementVisible(RESCHEDULE_DELIVERY_SELECT_CALENDER).waitForResponse(10);
	getAction().click(RESCHEDULE_DELIVERY_SELECT_CALENDER);
	getAction().waitFor(3000);
	AjaxCondition.forElementVisible(NEW_SELECTED_DATE).waitForResponse(10);
	getAction().click(NEW_SELECTED_DATE);
	getAction().waitFor(3000);
	AjaxCondition.forElementVisible(RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES).waitForResponse(10);
	getAction().click(RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES);
	getAction().waitFor(2000);
	if(getAction().isVisible(RESCHEDULE_DELIVERY_ERROR_POPUP))
	{
		getAction().waitFor(3000);
	
		AjaxCondition.forElementVisible(RESCHEDULE_DELIVERY_ERROR_POPUP_CLOSE).waitForResponse(10);
		getAction().click(RESCHEDULE_DELIVERY_ERROR_POPUP_CLOSE);
		AjaxCondition.forElementVisible(NEW_SELECTED_DATE).waitForResponse(10);
		getAction().click(NEW_SELECTED_DATE);
		AjaxCondition.forElementVisible(RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES).waitForResponse(10);
		getAction().click(RESCHEDULE_DELIVERY_GET_AVAILABLE_DATES);

	}
		else
		{
			getAction().waitFor(10000);
			getAction().click(SELECTED_RESON_CODE);
	
					getAction().selectByVisibleText(SELECTED_RESON_CODE, "Customer Initiated Rescheduling");
					AjaxCondition.forElementVisible(NOTES).waitForResponse();
					getAction().click(NOTES);
					getAction().type(NOTES,"Customer Initiated Rescheduling");
					AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
					//getAction().click(SUBMIT_BUTTON);
		
}
		
	Logger.log("Verify if \"Submit\" Button is Visible in PopUp",TestStepType.VERIFICATION_SUBSTEP);
	AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
	
	return this;
}

}
