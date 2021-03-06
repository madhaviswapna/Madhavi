package com.shc.msp.ft.pages;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.mysql.fabric.xmlrpc.base.Array;
import com.shc.automation.AjaxCondition;
import com.shc.automation.FrameworkProperties;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.PageAssert;
import com.shc.automation.SoftAssert;
import com.shc.automation.utils.TagAttributes;
import com.shc.automation.utils.TestUtils.CheckLocatorFor;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.entities.CancelReasonCode;
import com.shc.msp.ft.entities.OrderCharge;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.pages.OrderDetailsPage.OrderTab;
import com.shc.msp.ft.util.FileUtil;
import com.shc.msp.ft.util.MongoDB;
import com.shc.msp.ft.util.MysqlDBConnection;
import com.shc.msp.ft.util.SOAPRequest;
import com.shc.msp.ft.util.StringUtils;
import com.shc.msp.ft.util.Utility;

public class OrderDetailsPage extends Page {

	public OrderDetailsPage(SiteFactory factory) {
		super(factory);
	}

	protected String getPageName() {
		return "OrderDetails Page";
	}

	protected String pageName() {
		return "OrderDetails Page";
	}

	public final Locator ORDERDETAILS_TAB = new Locator("", "//li[@ng-if='selectedOrderInfo']//tab-heading[contains(.,'{0}')]", "Order details tab");
	public static final Locator ORDERDETAILS_ACTION_SELECT_BOX = new Locator("", "//div[@ng-if='actions']//select", "Select action drop down");
	public static final Locator ACTION_DROPDOWN_OPTIONS = new Locator("", "//div[@ng-if='actions']//select/option[contains(@ng-repeat,'actionType in actions')]", "Select action drop down options");
	public static final Locator ACTION_DROPDOWN_OPTIONS_COUNT = new Locator("", "(//div[@ng-if='actions']//select/option[contains(@ng-repeat,'actionType in actions')])[{0}]", "Select action drop down options");
	public static final Locator ORDERDETAILS_ACTION_GO_BUTTON = new Locator("", "//div[@ng-if='actions']//button[contains(.,'Go')]", "Select action Go button");
	public final Locator SALECANCEL_ACTION_GO_BUTTON = new Locator("", "//button[@class='btn btn-sm btn-primary']", "Select action Go button");
	public final Locator ERROR_POP_WINDOW = new Locator("ERROR POP UP WINDOW","//div[@class='modal-content']","Error Pop-up Window");
	public final Locator CLOSE_ERROR_POP_WINDOW = new Locator("CLOSE ERROR POP UP WINDOW","//button[@id='modalclose']","Close Error Pop-up Window Button");
	public final Locator CANCELLATION_POP_UP = new Locator("", "//div[@class='modal-dialog']//div[@class='modal-content']//div//h4[contains(.,'{0}')]", "Cancel order pop up");
	public final Locator CANCEL_ORDER_POP_UP_REASON_SELECT_BOX = new Locator("", "//div[@class='modal-dialog']//div[@class='modal-content']//form//label[contains(.,'Reason Code')]//following-sibling::div/select", "Cancel reason dropdown in cancel popup");
	public final Locator CANCEL_ORDER_POP_UP_SEND_EMAIL_BUTTON = new Locator("", "//div[@class='modal-dialog']//div[@class='modal-content']//form//label[contains(.,'Send Email')]//following-sibling::div//button[contains(.,'{0}')]", "Send email(y/n) button in cancel popup");
	public final Locator CANCEL_ORDER_POP_UP_NOTES_TEXT_FIELD = new Locator("", "//div[@class='modal-dialog']//div[@class='modal-content']//form//label[contains(.,'Notes')]//following-sibling::div//textarea", "Send email(y/n) button in cancel order popup");
	public final Locator CANCEL_ORDER_POP_UP_SELECTBOX = new Locator("", "//div[@class='modal-dialog']//div[@class='modal-content']//form//input[@type='checkbox']", "Check box in cancel order popup");
	public final Locator CANCEL_ORDER_POP_UP_SUBMIT_BUTTON = new Locator("", "//div[@class='modal-dialog']//div[@class='modal-content']//div[contains(@class,'footer')]//button[contains(.,'Submit')]", "Submit button in cancel order popup");
	public final Locator CANCEL_ORDER_POP_UP_CANCEL_BUTTON = new Locator("", "//div[@class='modal-dialog']//div[@class='modal-content']//div[contains(@class,'footer')]//button[contains(.,'Cancel')]", "Cancel button in cancel order popup");
	public final Locator READY_FOR_PICKUP_EMAIL_POP_UP = new Locator("", "//div[@class='modal-header ng-scope ng-isolate-scope']//h4", "Ready Pickup Email pop up");
	public final Locator READY_FOR_PICKUP_EMAIL = new Locator("","//input[@type='email']","Ready for Pickup Email");
	public final Locator READY_FOR_PICKUP_EMAIL_NOTES = new Locator("","//form[@name='sendReadyForPickupEmailForm']//textarea[@ng-model='selected.note']","Ready for Pickup Email Notes");
	public final Locator READY_FOR_PICKUP_EMAIL_SUBMIT = new Locator("","//button[contains(@ng-click,'ok()')]","Ready for Pickup Email Submit");

	public static final Locator ORDER_TAB_IN_ODP= new Locator("ORDER_TAB_IN_ODP", "//li[@title='{0}']/a", "Order Tab in ODP");

	public final Locator MEMBERDETAILS_IN_ODP = new Locator("MEMBERDETAILS_IN_ODP", "//div[@class='workspace col-md-10']/div", "Member Details in ODP");
	public final Locator MEMBERHISTORY_IN_ODP = new Locator("MEMBERHISTORY_IN_ODP", "//div[@id='memberHistory']|//div[@id='tabcontent']", "Member History in ODP");
	public final Locator AGENT_NOTES_IN_ODP = new Locator("AGENT_NOTES_IN_ODP", "//div[@class='notes col-md-2 flex-container-column']/div", "Agent Notes in ODP");
	public final Locator LAYAWAY_CONTRACT_INFORMATION = new Locator("LAYAWAY_CONTRACT_INFOMRMATION", "//legend[contains(text(),'Layaway Contract Information')]/ancestor::fieldset", "Layaway Contract Information");
	public final Locator LAYAWAY_INSTALLMENT_INFORMATION = new Locator("LAYAWAY_INSTALLMENT_INFOMRMATION", "//legend[contains(text(),'Paid Installment Information')]/ancestor::fieldset", "Layaway Installment Information");

	public final Locator CUSTOMER_NAME = new Locator("CUSTOMER NAME", "//div[@id='demoGraphicPanel']//div[@class='col-md-2']//span[contains(@class,'customer-name')]","Customer Name");
	public final Locator CUSTOMER_ADDRESS =	new Locator("CUSTOMER ADDRESS", "//div[@id='demoGraphicPanel']//div[@class='col-md-2']//span[@ng-if='customerAddress']","Customer Address");
	public final Locator CUSTOMER_PHONE_TITLE = new Locator("CUSTOMER PHONE TITLE", "//div[@id='demoGraphicPanel']//div[@class='col-md-2'][2]//strong","Customer Phone Title");
	public final Locator CUSTOMER_PHONE_NUMBER = new Locator("CUSTOMER PHONE NUMBER", "//strong[contains(text(),'Phone:')]/ancestor::span/following-sibling::div/span | //p[@ng-if='customer.daytimePhone']","Customer Phone Number");
	public final Locator CUSTOMER_EMAIL_TITLE = new Locator("CUSTOMER EMAIL TITLE", "//div[@id='demoGraphicPanel']//div[@class='col-md-2'][3]//strong","Customer E-mail Title");
	public final Locator CUSTOMER_EMAIL_ADDRESS = new Locator("CUSTOMER EMAIL ADDRESS", "//div[@id='demoGraphicPanel']//div[@class='col-md-2'][3]//p","Customer E-mail Address");
	public final Locator CUSTOMER_DEMOGRAPHIC = new Locator ("CUSTOMER DEMOGRAPHIC", "//div[@class='panel-body customer-demographics-panel']","Customer Demographic");
	public final Locator SHOP_YOUR_WAY_IMG = new Locator("SHOP YOUR WAY IMG", "//div[@id='SYW']//div[contains(@class,'syw-controller')]//img","Shop Your Way Image");
	public final Locator SHOP_YOUR_WAY_MENU_DOWN = new Locator("SHOP YOUR WAY MENU DOWN", "//div[@id='SYW']//div[contains(@class,'syw-controller')]//span[contains(@class,'glyphicon-menu-down')]","Shop Your Way Menu Down");
	public final Locator SHOP_YOUR_WAY_MENU_UP = new Locator("SHOP YOUR WAY MENU UP", "(//div[@id='SYW']//div[contains(@class,'syw-controller')])//span[contains(@class,'glyphicon-menu-up')]","Shop Your Way Menu Up");
	public final Locator SHOP_YOUR_WAY_AMOUNT_IN_POINTS = new Locator("SHOP YOUR WAY AMOUNT IN POINTS", "//div[@id='SYW']//div[contains(@class,'syw-controller')]/span[1]","Shop Your Way TotalAmount In Points");
	public final Locator SHOP_YOUR_WAY_TOTAL_POINTS = new Locator("SHOP YOUR WAY TOTAL POINTS", "//div[@id='SYW']//div[contains(@class,'syw-controller')]/span[2]","Shop Your Way Total Points");
	public final Locator SHOP_YOUR_WAY_BONUS_POINTS_TEXT = new Locator("SHOP YOUR WAY BONUS POINTS TEXT", "//div[@id='SYW']//div[contains(@class,'syw-info')]/span[1]","Shop Your Way Bonus Points Text");
	public final Locator SHOP_YOUR_WAY_BAG_IMG = new Locator("SHOP YOUR WAY BAG IMG", "//div[@id='SYW']//div[contains(@class,'syw-info')]//img","Shop Your Way Bag Image");
	public final Locator SHOP_YOUR_WAY_CUSTOMER_NAME = new Locator("SHOP YOUR WAY CUSTOMER NAME", "(//div[@id='SYW']//div[contains(@class,'syw-info')]//div[@class='col-md-12'])[3]//span","Shop Your Way Customer Name");
	public final Locator SHOP_YOUR_WAY_ACCOUNT_NO_TEXT = new Locator("SHOP YOUR WAY ACCOUNT NO TEXT ", "//div[@id='SYW']//div[contains(@class,'syw-info')]//span[text()='account#:']","Shop Your Way Account# Text");
	public final Locator SHOP_YOUR_WAY_ACCOUNT_NO = new Locator("SHOP YOUR WAY ACCOUNT NO", "(//div[@id='SYW']//div[contains(@class,'syw-info')]//div[@class='col-md-12'])[4]//span[2]","Shop Your Way Account Number");
	public final Locator SHOP_YOUR_WAY_SURPRISE_NO_TEXT = new Locator("SHOP YOUR WAY SURPRISE NO TEXT", "//div[@id='SYW']//div[contains(@class,'syw-info')]//span[text()='Surprise Points :']","Shop Your Way Surprise Points Text");
	public final Locator SHOP_YOUR_WAY_SURPRISE_NO = new Locator("SHOP YOUR WAY SURPRISE NO", "//div[@id='SYW']//div[contains(@class,'syw-info')]//span[text()='']","Shop Your Way Surprise Points");
	public final Locator SHOP_YOUR_WAY_MEMBER_LEVEL_TEXT = new Locator("SHOP YOUR WAY MEMBER LEVEL TEXT", "(//div[@id='SYW']//div[contains(@class,'syw-info')]//p//text())[1]","Shop Your Way Member Level Text");
	public final Locator SHOP_YOUR_WAY_MEMBER_LEVEL_TEXT_CONTENT = new Locator("SHOP YOUR WAY MEMBER LEVEL CONTENT", "(//div[@id='SYW']//div[contains(@class,'syw-info')]//p//text())[2]","Shop Your Way Member Level Content");

	public final Locator AGENT_NOTES_TEXT = new Locator("AGENT NOTES TEXT","//div[contains(@class,'flex-container')]//h3","Agent Notes Text");
	public final Locator AGENT_NOTES_DROPDOWN_BUTTON = new Locator("AGENT NOTES DROPDOWN BUTTON","//button[@class='notes-down-arrow ng-scope']","Agent Notes Drop Dwon Button");
	public final Locator AGENT_NOTES_TEXT_AREA = new Locator("AGENT NOTES TEXT AREA","//form[@name='noteSaveForm']//div[@class='well']/textarea","Agent Notes Text Area");
	public final Locator AGENT_NOTES_REQUIREDFIELD_SIGN = new Locator("AGENT NOTES REQUIREDFIELD SIGN","//span[text()='*']","Agent Notes Required Field Sign");
	public final Locator AGENT_NOTES_SAVE_BUTTON = new Locator("AGENT NOTES SAVE BUTTON","//button[@ng-click='addNote()']","Agent Notes Save Button");
	public final Locator AGENT_NOTES_DELETE = new Locator("AGENT NOTES DELETE","//form[@name='noteSaveForm']//div[@class='well']/img","Agent Notes Delete");
	public final Locator AGENT_NOTES_ADD_NEW_BUTTON = new Locator("AGENT NOTES ADD NEW BUTTON","//button[text()='Add New']","Agent Notes Add New Button");
	public final Locator AGENT_NOTES_EXISTING_NOTE_BUTTON = new Locator("AGENT NOTES EXISTING NOTE BUTTON","(//button[@ng-click='showNote(note);'])[1]","Agent Notes Existing Note Button");
	public final Locator AGENT_NOTES_UPDATE_BUTTON = new Locator("AGENT NOTES UPDATE BUTTON","//button[text()='Update']","Agent Notes Update Button");

	public final Locator MEMBER_HISTORY_TEXT = new Locator("MEMBER HISTORY TEXT","//div[@id='memberHistory']//h3","Member History Text");
	public final Locator PROFILE_TAB = new Locator("PROFILE TAB","//tab-heading[contains(@ng-click,'customer.profile')]","Profile Tab");
	public final Locator RECENT_ORDERS_TAB = new Locator("RECENT ORDERS TAB","//tab-heading[contains(@ng-click,'customer.orders')]","Recent Orders Tab");
	public final Locator ORDER_DETAIL_TAB = new Locator("ORDER DETAIL TAB","//tab-heading[contains(@ng-click,'selectedOrderInfo')]","Order Detail Tab");

	public final Locator CATEGORY_TEXT = new Locator("CATEGORY EXT","//label[text()='Category']","Category Text");
	public final Locator CATEGORY_DROPDOWN = new Locator("CATEGORY DROPDOWN","//select[@id='category']","Caregory Dropdown");
	public final Locator CATEGORY_OPTION = new Locator("","(//select[@id='category']//option)[{0}]","Caregory Option");
	public final Locator REASON_CODE_TEXT = new Locator("REASON CODE TEXT","//label[text()='Reason Code']","Reason Code Text");
	public final Locator REASON_CODE_DROPDOWN = new Locator("REASON CODE DROPDOWN","//select[@id='reasonCode']","Reason Code Dropdown");
	public final Locator CASE_CREATED = new Locator("Case already created message","//div[contains(text(),'Order has open case in this queue already, please add notes and close interaction.')]","Case already created message");
	public final Locator RETURN_DAMAGED_ITEM_WARNING_MSG = new Locator("Return policy for damaged item","//div[contains(text(),'The return policy for Damaged items is 3 days from the date of delivery.  This item is no longer eligible for return, as today is')]","Return policy for damaged item");

	public final Locator REASON_CODE_OPTION = new Locator("","(//select[@id='reasonCode']//option)[{0}]","Reason Code Option");
	public final Locator GUIDED_EXPERIENCE_TEXT = new Locator("GUIDED EXPERIENCE TEXT","//label[text()='Guided Experience']","Guided Experience Text");
	public final Locator GUIDED_EXPERIENCE_DROPDOWN = new Locator("GUIDED EXPERIENCE DROPDOWN","//select[@id='guidedExperience']","Guided Experience Dropdown");
	public final Locator SELECT_QUEUE_TO_ROUTE = new Locator("SELECT QUEUE TO ROUTE","//label[@for='selectQueue']","Select Queue to Route Text");
	public final Locator SELECT_QUEUE_ONE_RESPONSE = new Locator("SELECT QUEUE ONE RESPONSE","//p[@ng-if='metadata.queues.length == 1']","Select Queue One Response");
	public final Locator SELECT_QUEUE = new Locator("","//label[@for='selectQueue']//following-sibling::p","Select Queue");
	public final Locator SELECT_QUEUE_MULTI_RESPONSE = new Locator("SELECT QUEUE MULTI RESPONSE","//p[@ng-if='metadata.queues.length > 1']","Select Queue Multi Response");
	public final Locator SELECT_QUEUE_NO_RESPONSE = new Locator("SELECT QUEUE NO RESPONSE","//p[@ng-hide='metadata.queues.length > 0']","Select Queue No Response");
	public final Locator ORDER_NOTES_TEXT_AREA = new Locator("ORDER NOTES TEXT AREA","//textarea[@id='selectedNote']","Order Notes Text Area");
	public final Locator ORDER_NOTES_REQUIREDFIELD_SIGN = new Locator("ORDER NOTES REQUIREDFIELD SIGN","(//span[text()='*'])[1]","Order Notes Required Field Sign");
	public final Locator SHOW_ORDER = new Locator ("SHOW ORDER", "//div[@ng-if='showActions']","Show Order");
	public final Locator CLOSE_BUTTON = new Locator ("CLOSE BUTTON","//button[contains(@ng-click,'closeCase')]","Close Button");
	public final Locator SUCCESS_PAGE = new Locator ("SUCCESS PAGE","//h4[@class='modal-title text-info ng-binding']","Success Page");
	public final Locator OK_BUTTON = new Locator ("OK_BUTTON","//button[@id='modalclose']","Ok Button");
	public final Locator ROUTE_BUTTON = new Locator ("ROUTE_BUTTON","//button[contains(text(),'Route')]","ROUTE Button");
	public final Locator USER_ICON = new Locator ("USER_ICON","//div[@id='guestDisplay']/div[1]","User Icon");
	public final Locator USER_ICON_RFC = new Locator ("USER_ICON_RFC","//*[@id='placeHolder']/div/a[3]","User Icon RFC");

	public final Locator UPDATE_BUTTON = new Locator ("UPDATE_BUTTON","//button[contains(text(),'Update')]","UPDATE BUTTON");
	public final Locator SAVE_BUTTON = new Locator ("SAVE_BUTTON","//button[@ng-click='saveModifiedAddress()']","SAVE BUTTON");
	public final Locator SUGGESTED_ADDRESS = new Locator ("SUGGESTED_ADDRESS","(//div[@value='address'])[1]","SUGGESTED ADDRESS");
	public final Locator SUBMIT_BUTTON_ADDRESS = new Locator ("SUBMIT_BUTTON_ADDRESS","//button[contains(text(),'Submit')]","SUBMIT BUTTON in suggested address popup");
	public final Locator SUGGESTED_ADDRESS_TITLE = new Locator ("SUGGESTED_ADDRESS_TITLE","//button[@ng-click='saveModifiedAddress()']","SUGGESTED ADDRESS TITLE in popup");
	public final Locator UPDATE_NAME = new Locator ("UPDATE_NAME","//input[@ng-model='model.addrUpdate.name']","UPDATE NAME textbox");
	public final Locator UPDATE_NAME_VALUE = new Locator ("UPDATE_NAME_VALUE","//input[@ng-model='model.addrUpdate.name']//ancestor::p[@class='form-control-static']/div[@ng-show='!model.updatingAddress']","UPDATE NAME value");
	public final Locator UPDATE_EMAIL = new Locator ("UPDATE_EMAIL","//input[@ng-model='model.addrUpdate.email']","UPDATE EMAIL textbox");
	public final Locator UPDATE_EMAIL_VALUE = new Locator ("UPDATE_EMAIL_VALUE","//input[@ng-model='model.addrUpdate.email']//ancestor::p[@class='form-control-static']/div","UPDATE EMAIL value");
	public final Locator UPDATE_HOME_PHONE = new Locator ("UPDATE_HOME_PHONE","//input[@ng-model='model.addrUpdate.homePhone']","UPDATE_HOME_PHONE");
	public final Locator UPDATE_WORK_PHONE = new Locator ("UPDATE_WORK_PHONE","//input[@ng-model='model.addrUpdate.workPhone']","UPDATE_WORK_PHONE");
	public final Locator UPDATE_PHONE_VALUE = new Locator ("UPDATE_PHONE_VALUE","//input[@ng-model='model.addrUpdate.homePhone']//ancestor::p[@class='form-control-static']/div","UPDATE PHONE value");
	public final Locator UPDATE_ADDRESS = new Locator ("UPDATE_ADDRESS","//input[@ng-model='model.addrUpdate.address1']","UPDATE ADDRESS textbox");
	public final Locator UPDATE_ADDRESS_VALUE = new Locator ("UPDATE_ADDRESS_VALUE","//input[@ng-model='model.addrUpdate.address1']//ancestor::p[@class='form-control-static']/div","UPDATE ADDRESS value");
	public final Locator NOTATION_PAD = new Locator ("NOTATION_PAD","//span[contains(text(),'Delivery information has been updated')]","NOTATION PAD");

	//For Delivery Flow
	public final Locator DOS_MEMBERDETAILS_IN_ODP = new Locator("DOS_MEMBERDETAILS_IN_ODP", "//div[@class='col-md-12']/div", "DOS_MEMBERDETAILS_IN_ODP");
	public final Locator DOS_ORDER_NO_LONGER_EXIST = new Locator("DOS_ORDER_NO_LONGER_EXIST", "//div[@ng-show='showNoOrderExistDiv']/div", "DOS_ORDER_NO_LONGER_EXIST");
	public final Locator DOS_ORDER_SUMMARY_ORDER_NUMBER = new Locator("DOS_ORDER SUMMARY ORDER NUMBER","//*[contains(text(),'DOS Order Number')]//ancestor::div[contains(@class,'form-group')]//div[@class='col-md-6']//*[not(*)]","DOS Order Summary Order Number");
	public final Locator DOS_ORDER_SUMMARY_UNIT_NUMBER = new Locator("DOS_ORDER SUMMARY UNIT NUMBER","//*[contains(text(),'DOS Unit')]//ancestor::div[contains(@class,'form-group')]//div[@class='col-md-6']//*[not(*)]","DOS Order Summary Unit Number");
	public final Locator DOS_ORDER_SUMMARY_SALESCHECK_NUMBER = new Locator("DOS_ORDER SUMMARY SALESCHECK NUMBER","//*[contains(text(),'Salescheck')]//ancestor::div[contains(@class,'form-group')]//div[@class='col-md-6']//*[not(*)]","DOS Order Summary SalesCheck Number");
	public final Locator DOS_ORDER_SUMMARY_HOME_PHONE = new Locator("DOS_ORDER SUMMARY HOME PHONE","//*[contains(text(),'Home Phone')]//ancestor::div[contains(@class,'form-group')]//div[@class='col-md-6']//*[not(*)]","DOS Order Summary Home Phone");
	public final Locator DOS_ORDER_SUMMARY_WORK_PHONE = new Locator("DOS_ORDER SUMMARY WORK PHONE","//*[contains(text(),'Work or Alt Phone / Extn')]//ancestor::div[contains(@class,'form-group')]//div[@class='col-md-6']//*[not(*)]","DOS Order Summary Work Phone");
	public final Locator DOS_ORDER_SUMMARY_CELL_PHONE = new Locator("DOS_ORDER SUMMARY CELL PHONE","//*[contains(text(),'Cell Phone')]//ancestor::div[contains(@class,'form-group')]//div[@class='col-md-6']//*[not(*)]","DOS Order Summary Cell Phone");
	public final Locator DOS_ORDER_SUMMARY_PEND_CODE = new Locator("DOS_ORDER SUMMARY PEND CODE","//*[contains(text(),'Pend Code')]//ancestor::div[contains(@class,'form-group')]//div[@class='col-md-6']//*[not(*)]","DOS Order Summary Pend Code");
	public final Locator DOS_ORDER_SUMMARY_ADDRESS1 = new Locator("DOS_ORDER SUMMARY ADDRESS1","//*[contains(text(),'Address Line 1')]//ancestor::div[contains(@class,'form-group')]//div[@class='col-md-6']//*[not(*)]","DOS Order Summary Address1");

	//adjustment locaters 
	public final Locator ADJUSTMENT_AMOUNT = new Locator("Addjustment amount ", "//input[@name='saletaxajustamt']", "Addjustment amount text box");
	public final Locator ADJUSTMENT_AMOUNT_SHIP = new Locator("Addjustment amount ", "//input[@class='form-control ng-pristine ng-invalid ng-invalid-required']", "Addjustment amount text box");

	public final Locator ADJUSTMENT_AMOUNT_PERCENTAGE = new Locator("Addjustment amount percentage", "//input[@name='saletaxajust']", "Addjustment amount percentage text box");
	public final Locator AMOUNT = new Locator("Amount", "(//label[@class='ng-binding'])[3]", " Amount");
	public final Locator SELECT_RESON_CODE = new Locator("Select reson code ", "(//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required'])", " SELECT RESON CODE");
	public final Locator SELECTED_REASON_CODE = new Locator("Selected reson code ", "(//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required'])", " SELECTED RESON CODE");
	public static final Locator ADJUSTMENT_NOTES = new Locator("Adjustment notes", "(//textarea[@ng-model='selected.note'])", "Adjustment notes");
	public static final Locator SUBMIT_BUTTON = new Locator("SUBMIT_BUTTON", "(//button[contains(text(),'Submit')])[1]", "Submit Button");
	public final Locator SALESTAXADJUSTMENT_CONFIRM_TABLE_PAYMENT_TYPE = new Locator("Adjustment confirm table payment type", "//div[@class='modal fade modal-bottom in']//thead/tr/th[1]","Adjustment confirm table Payment Type");
	public final Locator SALESTAXADJUSTMENT_CONFIRM_TABLE_CREDIT = new Locator("Adjustment confirm table Credit", "//div[@class='modal fade modal-bottom in']//thead/tr/th[2]","Adjustment confirm table Credit");
	public final Locator SALESTAXADJUSTMENT_CONFIRM_TABLE_CREDIT_POINT = new Locator("Adjustment confirm table Credit Point", "//div[@class='modal fade modal-bottom in']//thead/tr/th[3]","Adjustment confirm table Credit Point");
	public final Locator SALESTAXADJUSTMENT_CONFIRM_TABLE_CREDIT_VALUE = new Locator("Adjustment confirm table Credit value", "//div[@class='modal fade modal-bottom in']//tbody/tr/td[2]","Adjustment confirm table Credit value");

	public final Locator SALESTAXADJUSTMENT_CONFIRM_TABLE_SUBMIT = new Locator("","//div[@class='modal fade modal-bottom in']//button[contains(.,'Submit')]","Adjustment confirm table Submit Button");
	public final Locator SELECT_ACTION_DROPDOWN_LINEITEM= new Locator("Select drop down line item", "//select[@class='input-sm ng-pristine ng-valid']", "Select drop down line item");

	public final Locator SELECT_ACTION_DROPDOWN= new Locator("Select drop down", "//select[@ng-model='summaryAction']", "Select drop down");
	public final Locator SELECT_DROPDOWN_VALUE= new Locator("Select drop down value", "//option[@value='{0}']", "Select drop down value");
	public final Locator ITEM_NOT_CHARGED= new Locator("ITEM_NOT_CHARGED", "//div[contains(text(),'ITEM IS NOT CHARGED')]", "alert message Item not charged");
	public final Locator ITEM_SELECT_CHARGE= new Locator("ITEM_SELECT_CHARGE", "(//input[@name='shipping'])[{0}]", "Select the charge");
	public final Locator RETURN_ITEM_RESON_CODE= new Locator("Reson code", "//option[@ng-repeat='reasonCode in reasonCodes'][{0}]", "Reson code");
	public final Locator RETURN_ITEM_RESON_CODE_COUNT= new Locator("Reson code count", "(//option[@ng-repeat='reasonCode in reasonCodes'])", "Reson code count");
	public final Locator RETURN_ITEM_RESON_DROPDOWN= new Locator("Reson code","//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required']", "Reson code");
	public final Locator ADJUSTMENT_ALERT_MESSAGE= new Locator("Alert message ","//div[@class='alert alert-danger ng-scope ng-binding']", "adjustment alert message");

	public static final Locator SKU_NUMBER_POSITION= new Locator("SKU_NUMBER", "(//td[@data-title-text='SKU']/a)[{0}]", "SKU Number");
	public static final Locator SKU_NUMBER= new Locator("SKU_NUMBER", "//td[@data-title-text='SKU']//a[contains(text(),'{0}')]", "SKU Number");
	public static final Locator SKU_WARRANTY= new Locator("SKU_WARRANTY", "//td[contains(.,'WARRANTY')]//ancestor::tr//td[@data-title-text='SKU']/a", "SKU for Warranty Item");
	public static final Locator SALES_CHECK_NUMBER_COUNT= new Locator("SALES_CHECK_NUMBER", "(//a[@class='spacetwo ng-binding'])", "Sales Check Number");
	public static final Locator SALES_CHECK_NUMBER= new Locator("SALES_CHECK_NUMBER", "(//a[@class='spacetwo ng-binding'])[{0}]", "Sales Check Number");
	public static final Locator LINE_ITEM_DETAIL_PAGE= new Locator("LINE_ITEM_DETAIL_PAGE", "//div[@id='lineItemSalesCheckSection']", "Line Item Detail Page");
	public static final Locator SALES_CHECK_DETAIL_PAGE= new Locator("SALES_CHECK_DETAIL_PAGE", "//div[@id='salesCheckDiv']", "Sales Check Detail Page");
	public static final Locator RETURN_ITEM_POPUP= new Locator("RETURN_ITEM_POPUP", "//h4[contains(text(),'Return Item')]", "Return Item PopUp");
	public static final Locator RETURN_QUANTITY= new Locator("RETURN QUANTITY", "//input[@name='returnQuantity']", "Return quantity");
	//trial balance 
	public static final Locator TRIAL_BALANCE_POPUP= new Locator("Trail balance popup", "(//div[@class='modal-content'])[2]", "Trail balance popup");
	public static final Locator TRIAL_BALANCE_POPUP_PAYMENTTYPE= new Locator("Trail balance popup payment", "//td[contains(text(),'Cash') or contains(text(),'Credit Card') or contains(text(),'CreditCard') or contains(text(),'PayPalUC') or contains(text(),'PinDebit')]", "Trail balance popup payment");
	public static final Locator TRIAL_BALANCE_POPUP_CANCEL_BUTTON= new Locator("Trail balance popup cancel button",  "(//button[contains(text(),'Cancel')])[2]" , "Trail balance popup cancel button");
	public static final Locator TRIAL_BALANCE_POPUP_SUBMIT_BUTTON= new Locator("Trail balance popup submit button",  "(//button[contains(text(),'Submit')])[2]" , "Trail balance popup submit button");
	public static final Locator TRIAL_BALANCE_POPUP_MESSAGE= new Locator("Trail balance popup message",  "//font[contains(text(),'Credit Amount(s) include')]"  , "Trail balance message");
	public static final Locator TRIAL_BALANCE_OK= new Locator("Trail balance ok button", "//button[@id='modalclose']", "Trail balance ok button");
	public static final Locator SALE_ADJUSTMENT_RESON_CODE= new Locator("Sale Adjustment reson code",  "(//option[@ng-repeat='reasonCode in reasonCodes'])[{0}]"  , "Sale Adjustment reson code");

	public static final Locator RELEASE_ORDER_POPUP= new Locator("Release Order popup", "//h4[contains(text(),'Release Order')]", "Release order popup");
	public static final Locator RELEASE_ORDER_POPUP_NOTES_TEXT_FIELD = new Locator("", "//div[@class='modal-dialog']//div[@class='modal-content']//form//label[contains(.,'Notes')]//following-sibling::div//textarea", "Release Order popup notes textarea");

	public static final Locator RESEND_ORDER_CONFIRMATION_POPUP = new Locator("Resend Order Confirmation popup", "//h4[contains(text(),'Re-Send Order Confirmation')]", "Resend Order Confirmation popup");
	public static final Locator RESEND_ORDER_CONFIRMATION_POPUP_EMAIL_TEXTFIELD = new Locator("Resend Order Confirmation popup notes textarea", "//div[@class='modal-dialog']//div[@class='modal-content']//form//label[contains(.,'Email')]//following-sibling::div//input", "Resend Order Confirmation popup E-mail textarea");
	public static final Locator RESEND_ORDER_CONFIRMATION_POPUP_NOTES_TEXTFIELD = new Locator("Resend Order Confirmation popup notes textarea", "//div[@class='modal-dialog']//div[@class='modal-content']//form//label[contains(.,'Notes')]//following-sibling::div//textarea", "Resend Order Confirmation popup notes textarea");
	public static final Locator REFUND_WITHOUT_RETURN_POP_UP = new Locator("Refund without return popup", "//h4[contains(text(),'Refund without return popup'", "Refund without return popup");
	public static final Locator REFUND_WITHOUT_RETURN_POPUP_RUFUND_QUANTITY_TEXTFIELD = new Locator("Refund without return popup Refund Quantity textarea", "//input[@class='form-control ng-pristine ng-valid']", "Refund without return popup Refund Quantity textarea");
	public static final Locator REFUND_WITHOUT_RETURN_POP_UP_SELECTED_RESONCODE = new Locator("Selected reson code ", "(//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required'])", " SELECTED RESON CODE");
	public static final Locator REFUND_WITHOUT_RETURN_POPUP_NOTES_TEXTFIELD = new Locator("", "//div[@class='modal-dialog']//div[@class='modal-content']//form//label[contains(.,'Note')]//following-sibling::div//textarea", "Refund Without Return popup notes textarea");
	public static final Locator DROPDOWN_SELECT_BOX = new Locator("", "//div[@id='lineItemSalesCheckSection']//div[@ng-if='actions']//select", "Select action drop down");

	public static final Locator MARKETPLACE_WARNING_POPUP= new Locator("MARKETPLACE_WARNING_POPUP", "//div[@class='modal-content']", "Marketplace Warning PopUp");
	public static final Locator MARKETPLACE_WARNING_POPUP_CONTENT = new Locator("MARKETPLACE_WARNING_POPUP_CONTENT", "//div[contains(text(),'MARKETPLACE (FBM) item')]", "Marketplace Warning PopUp Content");
	public static final Locator MARKETPLACE_WARNING_POPUP_CLOSE_BUTTON = new Locator("MARKETPLACE_WARNING_POPUP_CLOSE_BUTTON", "//button[@id='modalclose']", "Marketplace Warning PopUp Close Button");

	//LINEITEMDETAILS

	public static final Locator LINEITEMDETAILSTABLE = new Locator("LINEITEMDETAILSTABLE", "//table[@ng-table='lineItemTableParams']", "LINEITEMDETAILSTABLE");
	public static final Locator LINEITEMDETAILSUMMARY = new Locator("LINEITEMDETAILSUMMARY","(//div[@class='col-md-6'])[2]", "LINEITEMDETAILSUMMARY");
	public static final Locator LINEITEMDETAILINFORMATION = new Locator("LINEITEMDETAILINFORMATION","//div[@id='lineItemDiv']", "LINEITEMDETAILINFORMATION");

	//salescheck details page   
	public static final Locator SALESCHECKSUMMARY = new Locator("SALESCHECKSHIPPINGADDRESS","(//div[@class='col-md-3'])[5]", " Shipping Address");
	public static final Locator SALESCHECKDETAILS = new Locator("SALESCHECKSUMMARY","(//div[@class='col-md-4'])[1]", "Sales check summary");
	public static final Locator SALESCHECKSHIPPINGADDRESS = new Locator("SALESCHECKDETAILS","(//div[@class='col-md-5'])[1]", "Sales Check details");
	private static final Locator SEARCH_FOR_ANOTHER_ORDER_LINK = new Locator("SEARCH_FOR_ANOTHER_ORDER_LINK","//span[@info='search_order_link']", "Search for another order link");

	//Audit trail page
	public final Locator SORT_BY_US_CENTRAL_TIME = new Locator("sortByUSCentralTime", "//div[contains(text(),'US Central Time')]", "sortByUSCentralTime");
	public final Locator AUDIT_TRAIL_ENTRY = new Locator("AUDIT_TRAIL_ENTRY","//tr[contains(.,'{0}')]", "AUDIT_TRAIL_ENTRY");

	//tabs
	public final Locator ORDER_SUMMARY_TAB = new Locator("ORDER SUMMARY TAB","//a[text()='Order Summary']","Order Summary Tab");
	public final Locator ORDER_SUMMARY_WARNING_POPUP = new Locator("ORDER SUMMARY POP ","//div[contains(@class,'modal-content')]//h4[contains(@class,'modal-title ')]","Order Summary Tab popup");
	public final Locator ORDER_SUMMARY_WARNING_POPUP_CLOSE = new Locator("ORDER SUMMARY POP CLOSE ","//button[@id='modalclose']","Order Summary Tab popup close");
	public final Locator LINE_ITEM_SALES_CHECKS_TAB = new Locator("LINE ITEM SALES CHECKS TAB","//a[contains(text(),'Sales Checks')]","Line Item/Sales Checks Tab");
	public final Locator AUDIT_TRAIL_TAB = new Locator("AUDIT TRAIL TAB","//a[text()='Audit Trail']","Audit Trail Tab");
	public final Locator SYW_MAX_TAB = new Locator("SYW MAX TAB","//a[text()='SYW Max']","SYW Max Tab");

	//Order Summary 
	public final Locator ORDER_SUMMARY_TITLE_TEXT = new Locator("ORDER SUMMARY TITLE TEXT","//legend[text()='Order Summary']","Order Summary Title Text");
	public final Locator ORDER_SUMMARY_TABLE = new Locator("ORDER SUMMARY TABLE","//form[@role='form']","Order Summary Table");
	public final Locator ORDER_SUMMARY_ORDER_NUMBER_TEXT = new Locator("ORDER SUMMARY ORDER NUMBER TEXT","//strong[text()='Order Number']","Order Summary Order Number Text");
	public final Locator ORDER_SUMMARY_ORDER_NUMBER = new Locator("ORDER SUMMARY ORDER NUMBER","(//form[@role='form']//div)[2]//p","Order Summary Order Number");
	public final Locator ORDER_SUMMARY_STORE_TEXT = new Locator("ORDER SUMMARY STORE TEXT","//strong[text()='Store']","Order Summary Store Text");
	public final Locator ORDER_SUMMARY_STORE_NAME = new Locator("ORDER SUMMARY STORE NAME","(//form[@role='form']//div)[4]//p","Order Summary Store Name");
	public final Locator ORDER_SUMMARY_PURCHASE_DATE_TEXT = new Locator("ORDER SUMMARY PURCHASE DATE TEXT","//strong[text()='Purchase Date']","Order Summary Purchase Date Text");
	public final Locator ORDER_SUMMARY_PURCHASE_DATE = new Locator("ORDER SUMMARY PURCHASE DATE","(//form[@role='form']//div)[6]//p","Order Summary Purchase Date");
	public final Locator ORDER_SUMMARY_ORDER_STATUS_TEXT = new Locator("ORDER SUMMARY ORDER STATUS TEXT","//strong[text()='Order Status']","Order Summary Order Status Text");
	public final Locator ORDER_SUMMARY_ORDER_STATUS = new Locator("ORDER SUMMARY ORDER STATUS","(//form[@role='form']//div)[8]//p","Order Summary Order Status");
	public final Locator ORDER_SUMMARY_PLACED_BY_TEXT = new Locator("ORDER SUMMARY PLACED BY TEXT","//strong[text()='Order Placed By']","Order Summary Order Placed By Text");
	public final Locator ORDER_SUMMARY_PLACED_BY = new Locator("ORDER SUMMARY PLACED BY","(//form[@role='form']//div)[10]//p","Order Summary Order Placed By");
	public final Locator LAYAWAY_CONTRACT_NO_TEXT = new Locator("ORDER SUMMARY LOYALTY CONTRACT NO TEXT","//strong[text()='Layaway Contract #']","Layaway Contract # Text");
	public final Locator LAYAWAY_CONTRACT_NO = new Locator("ORDER SUMMARY LOYALTY CONTRACT NO","(//form[@role='form']//div)[12]//p","Layaway Contract # ");
	public final Locator LAYAWAY_BALANCE_TEXT = new Locator("ORDER SUMMARY LOYALTY BALANCE NO TEXT","//strong[text()='Layaway Balance']","Layaway Balance Text");
	public final Locator LAYAWAY_BALANCE = new Locator("ORDER SUMMARY LOYALTY BALANCE NO","(//form[@role='form']//div)[14]//p","Layaway Balance ");
	public final Locator ORDER_SUMMARY_LOYALTY_NO_TEXT = new Locator("ORDER SUMMARY LOYALTY NO TEXT","//strong[text()='Loyalty #']","Order Summary Loyalty # Text");
	public final Locator ORDER_SUMMARY_LOYALTY_NO = new Locator("ORDER SUMMARY LOYALTY NO","//strong[text()='Loyalty #']//parent::span//following-sibling::div//p","Order Summary Loyalty #");
	public final Locator ORDER_SUMMARY_EMPOWERMENT_GUIDELINE_POPUP = new Locator("", "(//div[@class='modal-content'])[last()]", "Empower Guideline popup");
	public final Locator ORDER_SUMMARY_MARKETPLACE_ITEM_POPUP = new Locator("", "(//div[@class='modal-content'])[last()]", "Marketplace item Guideline popup");
	public final Locator WARNING_POPUP_OK_BUTTON = new Locator("", "(//button[@id='modalclose'])[last()]", "Warning popup Ok button");
	public final Locator EMPOWERMENT_GUIDELINE_STATUS_COLOR = new Locator("", "//strong[contains(text(),'Empowerment Guideline')]/parent::span/following-sibling::span", "Empower Guideline popup status color");

	//Order Summary Order Charge
	public final Locator ORDER_CHARGES_TITLE_TEXT = new Locator("ORDER CHARGES TITLE TEXT","//legend[text()='Order Charges']","Order Charges Title Text");
	public final Locator ORDER_CHARGES_TABLE = new Locator("ORDER CHARGES TABLE","//legend[text()='Order Charges']//following-sibling::div//table","Order Charges Table");
	public final Locator ORDER_CHARGES_DESCRIPTION_COL_NAME = new Locator("ORDER CHARGES DESCRIPTION COL NAME","//legend[text()='Order Charges']//following-sibling::div//table//thead//tr//th[1]","Order Charges Description Column Name");
	public final Locator ORDER_CHARGES_ORIGINAL_COL_NAME = new Locator("ORDER CHARGES ORIGINAL COL NAME","//legend[text()='Order Charges']//following-sibling::div//table//thead//tr//th[2]","Order Charges Original Column Name");
	public final Locator ORDER_CHARGES_CURRENT_COL_NAME = new Locator("ORDER CHARGES CURRENT COL NAME","//legend[text()='Order Charges']//following-sibling::div//table//thead//tr//th[3]","Order Charges Current Column Name");
	public final Locator MERCHANDISE_TOTAL_ROW_NAME = new Locator("MERCHANDISE TOTAL ROW NAME","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[1]//td[1]","Merchandise Total Row Name");
	public final Locator MERCHANDISE_TOTAL_ORIGINAL_PRICE = new Locator("MERCHANDISE TOTAL ORIGINAL PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[1]//td[2]","Merchandise Total Original Price");
	public final Locator MERCHANDISE_TOTAL_CURRENT_PRICE = new Locator("MERCHANDISE TOTAL CURRENT PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[1]//td[3]","Merchandise Total Current Price");
	public final Locator SHIPPING_CHARGE_ROW_NAME = new Locator("SHIPPING CHARGE ROW NAME","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[2]//td[1]","Shipping Charge Row Name");
	public final Locator SHIPPING_CHARGE_ORIGINAL_PRICE = new Locator("SHIPPING CHARGE ORIGINAL PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[2]//td[2]","Shipping Charge Original Price");
	public final Locator SHIPPING_CHARGE_CURRENT_PRICE = new Locator("SHIPPING CHARGE CURRENT PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[2]//td[3]","Shipping Charge Current Price");
	public final Locator DELIVERY_CHARGE_ROW_NAME = new Locator("DELIVERY CHARGES ROW NAME","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[3]//td[1]","Delivery Charge Row Name");
	public final Locator DELIVERY_CHARGE_ORIGINAL_PRICE = new Locator("DELIVERY CHARGES ORIGINAL PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[3]//td[2]","Delivery Charge Original Price");
	public final Locator DELIVERY_CHARGE_CURRENT_PRICE = new Locator("DELIVERY CHARGES CURRENT PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[3]//td[3]","Delivery Charge Current Price");
	public final Locator INSTALLATION_CHARGE_ROW_NAME = new Locator("INSTALLATION CHARGE ROW NAME","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[4]//td[1]","Installation Charge Row Name");
	public final Locator INSTALLATION_CHARGE_ORIGINAL_PRICE = new Locator("INSTALLATION CHARGE ORIGINAL_PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[4]//td[2]","Installation Charge Original Price");
	public final Locator INSTALLATION_CHARGE_CURRENT_PRICE = new Locator("INSTALLATION CHARGE CURRENT PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[4]//td[3]","Installation Charge Current Price");
	public final Locator TAX_ROW_NAME = new Locator("TAX ROW NAME","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[5]//td[1]","Tax Row Name");
	public final Locator TAX_ORIGINAL_PRICE = new Locator("TAX ORIGINAL PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[5]//td[2]","Tax Original Price");
	public final Locator TAX_CURRENT_PRICE = new Locator("TAX CURRENT PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[5]//td[3]","Tax Current Price");
	public final Locator ENVIRONMENTAL_FEE_ROW_NAME = new Locator("ENVIRONMENTAL FEE ROW NAME","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[6]//td[1]","Environmental Fee Row Name");
	public final Locator ENVIRONMENTAL_FEE_ORIGINAL_PRICE = new Locator("ENVIRONMENTAL FEE ORIGINAL PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[6]//td[2]","Environmental Fee Original Price");
	public final Locator ENVIRONMENTAL_FEE_CURRENT_PRICE = new Locator("ENVIRONMENTAL FEE CURRENT PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//tr[6]//td[3]","Environmental Fee Current Price");
	public final Locator TOTAL_ROW_NAME = new Locator("TOTAL ROW NAME","//legend[text()='Order Charges']//following-sibling::div//table//tbody//td[text()='Total']","Total Row Name");
	public final Locator TOTAL_ORIGINAL_PRICE = new Locator("TOTAL ORIGINAL PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//td[text()='Total']//following-sibling::td[1]","Total Original Price");
	public final Locator TOTAL_CURRENT_PRICE = new Locator("TOTAL CURRENT PRICE","//legend[text()='Order Charges']//following-sibling::div//table//tbody//td[text()='Total']//following-sibling::td[2]","Total Current Price");   
	public final Locator TOTAL_ADJUSTMENT_DISCOUNT = new Locator("TOTAL ADJUSTMENT DISCOUNT","//span[@ng-if='data.summary.orderSummaryCalculated.totalAdjustmentsApplied > 0']","Total Adjustment Discount");
	//Order Summary Discounts
	public final Locator DISCOUNTS_TITLE_TEXT = new Locator("DISCOUNTS TITLE TEXT","//legend[text()='Discounts']","Discounts Title Text");
	public final Locator NO_DISCOUNTS_RESPONSE = new Locator("NO DISCOUNTS RESPONSE","//div[@ng-if='data.summary.discounts.length==0']/span","NO Discounts Response");
	public final Locator DISCOUNTS_TABLE_O = new Locator("DISCOUNTS TABLE","//legend[text()='Discounts']//following-sibling::div//table","Discounts Table");
	public final Locator DISCOUNTS_TABLE_CONTENT = new Locator("DISCOUNTS TABLE CONTENT","//legend[text()='Discounts']//following-sibling::div//table//tbody","Discounts Table Content");
	public final Locator DISCOUNTS_DESCRIPTION_COL_NAME = new Locator("DISCOUNTS DESCRIPTION COL NAME","//legend[text()='Discounts']//following-sibling::div//table//thead//tr//th[1]","Discounts Description Column Name");
	public final Locator DISCOUNTS_AMOUNT_COL_NAME = new Locator("DISCOUNTS AMOUNT COL NAME","//legend[text()='Discounts']//following-sibling::div//table//thead//tr//th[2]","Discounts Amount Column Name");

	//Order Summary Adjustment
	public final Locator ADJUSTMENTS_TITLE_TEXT = new Locator("ADJUSTMENTS TITLE TEXT","//legend[text()='Adjustments']","Adjustments Title Text");
	public final Locator NO_ADJUSTMENTS_RESPONSE = new Locator("NO ADJUSTMENTS RESPONSE","//div[@ng-if='data.summary.adjustments.length==0']/span","No Adjustments Response");
	public final Locator ADJUSTMENTS_TABLE = new Locator("ADJUSTMENTS TABLE","//legend[text()='Adjustments']//following-sibling::div//table","Adjustments Table");
	public final Locator ADJUSTMENTS_TABLE_CONTENT = new Locator("ADJUSTMENTS TABLE CONTENT","//legend[text()='Adjustments']//following-sibling::div//table//tbody","Adjustments Table Content");
	public final Locator ADJUSTMENTS_TYPE_COL_NAME = new Locator("ADJUSTMENTS TYPE DESCRIPTION COL NAME","//legend[text()='Adjustments']//following-sibling::div//table//thead//tr//th[1]","Adjustments Type Column Name");
	public final Locator ADJUSTMENTS_AMOUNT_COL_NAME = new Locator("ADJUSTMENTS AMOUNT COL NAME","//legend[text()='Adjustments']//following-sibling::div//table//thead//tr//th[2]","Adjustments Amount Column Name");
	public final Locator ADJUSTMENTS_DATE_COL_NAME = new Locator("ADJUSTMENTS DATE COL NAME","//legend[text()='Adjustments']//following-sibling::div//table//thead//tr//th[3]","Adjustments Dates Column Name");
	public final Locator ADJUSTMENTS_SALECHECK_NO_COL_NAME = new Locator("ADJUSTMENTS SALECHECK# COL NAME","//legend[text()='Adjustments']//following-sibling::div//table//thead//tr//th[4]","Adjustments Sales Check# Column Name");
	public final Locator LATEST_ADJUSTMENT_IN_ORDER_SUMMARY = new Locator("LATEST_ADJUSTMENT_IN_ORDER_SUMMARY", "(//legend[text()='Adjustments']//following-sibling::div//tr)[last()]/td[{0}]", "LATEST_ADJUSTMENT_IN_ORDER_SUMMARY");

	//Order Summary Payment
	public final Locator PAYMENTS_TITLE_TEXT = new Locator("PAYMENTS TITLE TEXT","//legend[text()='Payments']","Payments Title Text");
	public final Locator PAYMENTS_TABLE_O = new Locator("ORDER PAYMENTS TABLE","//legend[text()='Payments']//following-sibling::div//table","Payments Table");
	public final Locator PAYMENTS_TABLE_CONTENT = new Locator("PAYMENTS TABLE CONTENT","//legend[text()='Payments']//following-sibling::div//table//tbody","Payments Table Content");
	public final Locator PAYMENT_TYPE_COL_NAME = new Locator("PAYMENT TYPE COL NAME","//legend[text()='Payments']//following-sibling::div//table//tr//th[1]","Payment Type Column Name");
	public final Locator CARD_NUMBER_COL_NAME = new Locator("CARD NUMBER COL NAME","//legend[text()='Payments']//following-sibling::div//table//tr//th[2]","Card Number Column Name");
	public final Locator CARD_TYPE_COL_NAME = new Locator("CARD TYPE COL NAME","//legend[text()='Payments']//following-sibling::div//table//tr//th[3]","Card Type Column Name");
	public final Locator NAME_ON_CARD_COL_NAME = new Locator("NAME ON CARD COL NAME","//legend[text()='Payments']//following-sibling::div//table//tr//th[4]","Name on Card Column Name");
	public final Locator EXPIRATION_DATE_COL_NAME = new Locator("EXPIRATION DATE COL NAME","//legend[text()='Payments']//following-sibling::div//table//tr//th[5]","Expiration Date Column Name");
	public final Locator PAYMENT_AMOUNT_COL_NAME = new Locator("PAYMENT AMOUNT COL NAME","//legend[text()='Payments']//following-sibling::div//table//tr//th[6]","Payment Amount Column Name");
	public final Locator CREDIT_POINTS_COL_NAME = new Locator("CREDIT POINTS COL NAME","//legend[text()='Payments']//following-sibling::div//table//tr//th[7]","Adj/Credit pts Column Name");
	public final Locator CREDIT_COL_NAME = new Locator("CREDIT COL NAME","//legend[text()='Payments']//following-sibling::div//table//tr//th[8]","Adj/Credit Column Name");

	//Order Detail
	public final Locator ORDER_DETAILS_TITLE_TEXT = new Locator("ORDER DETAILS TITLE TEXT","//legend[text()='Order Details']","Order Details Title Text");
	public final Locator ORDER_DETAILS_TABLE = new Locator("ORDER DETAILS TABLE","//table[@ng-table='lineItemTableParams']","Order Details Table");
	public final Locator ORDER_DETAILS_TABLE_CONTENT = new Locator("ORDER DETAILS TABLE CONTENT","//table[@ng-table='lineItemTableParams']//tbody","Order Details Table Content");
	public final Locator STATUS_COL_NAME = new Locator("STATUS COL NAME","(//table[@ng-table='lineItemTableParams']//thead//tr//th)[1]","Order Details Status Column Name");
	public final Locator DESCRIPTION_COL_NAME = new Locator("DESCRIPTION COL NAME","(//table[@ng-table='lineItemTableParams']//thead//tr//th)[2]","Order Details Description Column Name");

	public final Locator DESCRIPTION_COL_NAME_TEXT = new Locator("DESCRIPTION_COL_NAME_TEXT","(//table[@ng-table='lineItemTableParams']/tbody//td[@data-title-text='Description']/div)[{0}]","Order Details Description Column text");

	public final Locator SKU_COL_NAME = new Locator("SKU COL NAME","(//table[@ng-table='lineItemTableParams']//thead//tr//th)[3]","Order Details SKU Column Name");
	public final Locator QTY_COL_NAME = new Locator("QTY COL NAME","(//table[@ng-table='lineItemTableParams']//thead//tr//th)[4]","Order Details Quantity Column Name");
	public final Locator PRICE_COL_NAME = new Locator("PRICE COL NAME","(//table[@ng-table='lineItemTableParams']//thead//tr//th)[5]","Order Details Price Column Name");
	public final Locator FULFILL_BY_COL_NAME = new Locator("FULFILL_BY COL NAME","(//table[@ng-table='lineItemTableParams']//thead//tr//th)[6]","Order Details Fulfill By Column Name");
	public final Locator SHIPPING_CHARGE_COL_NAME = new Locator("SHIPPING_CHARGE COL NAME","(//table[@ng-table='lineItemTableParams']//thead//tr//th)[7]","Order Details Shipping Charge Column Name");
	public final Locator TRACKING_COL_NAME = new Locator("TRACKING COL NAME","(//table[@ng-table='lineItemTableParams']//thead//tr//th)[8]","Order Details Tracking Column Name");
	public final Locator SHIPPING_METHOD_COL_NAME = new Locator("SHIPPING_METHOD","(//table[@ng-table='lineItemTableParams']//thead//tr//th)[9]","Order Details Shipping Method Column Name");
	public final Locator SHIPPING_LAST_NAME_COL_NAME = new Locator("SHIPPING_LAST_NAME","(//table[@ng-table='lineItemTableParams']//thead//tr//th)[10]","Order Details Shipping Last Name Column Name");

	public final Locator ORDER_DETAILS_HIDDEN_COL = new Locator("ORDER DETAILS HIDDEN COL","//span[contains(@class,'glyphicon-chevron-down')]","Order Details Hidden Column ");
	public final Locator ORDER_DETAILS_NONE_TEXT = new Locator("ORDER DETAILS NON TEXT","//a[contains(@ng-click,'!group.$hideRows')]//following-sibling::strong[@class='ng-binding']","Order Details None Text");
	public final Locator ORDER_DETAILS_RELEASE_NUMBER = new Locator("ORDER DETAILS RELEASE NUMBER","//span[@ng-show='group.data[0].releaseNumber != null']","Order Details Release Number");

	//Footer//need edit
	public final Locator CURRENT_INTERACTION = new Locator("CURRENT INTERACTION","//h4//span[contains(text(),'Current Interaction')]","Current Interaction");
	public final Locator CONTACT_HISTORY = new Locator("CONTACT HISTORY","//div[@id='memberHistory']//h4//span[contains(text()='Contact History')]","Contact History");
	public final Locator EXPAND_CONTACT_HISTORY = new Locator( "EXPAND_CONTACT_HISTORY", "//div[@id='memberHistory']//h4//span[contains(text(),'Current Interaction')]//following-sibling::i","Expand Contact History");
	//Current Interaction Table //need edit
	public final Locator CURRENT_INTERACTION_TABLE = new Locator("CURRENT INTERACTION TABLE","(//table[contains(@class,'table-condensed')])[2]","Current Interaction Table");
	public final Locator EVENT_DATE_COL_NAME = new Locator("EVENT DATE COL NAME","(//table[contains(@class,'table-condensed')])[2]//thead//tr//th[1]","Event Date Column Name");
	public final Locator OWNNER_COL_NAME = new Locator("OWNNER COL NAME","(//table[contains(@class,'table-condensed')])[2]//thead//tr//th[2]","Ownner Column Name");
	public final Locator EVENT_TYPE_COL_NAME = new Locator("EVENT TYPE COL NAME","(//table[contains(@class,'table-condensed')])[2]//thead//tr//th[3]","Event Type Column Name");
	public final Locator QUEUE_COL_NAME = new Locator("QUEUE COL NAME","(//table[contains(@class,'table-condensed')])[2]//thead//tr//th[4]","Queue Column Name");
	public final Locator NOTE_COL_NAME = new Locator("NOTE COL NAME","(//table[contains(@class,'table-condensed')])[2]//thead//tr//th[5]","Note Column Name");
	public final Locator EVENT_DATE = new Locator("EVENT DATE","(//table[contains(@class,'table-condensed')])[2]//tbody//tr//td[1]","Event Date ");
	public final Locator OWNNER = new Locator("OWNNER ","(//table[contains(@class,'table-condensed')])[2]//tbody//tr//td[2]","Ownner ");
	public final Locator EVENT_TYPE = new Locator("EVENT TYPE","(//table[contains(@class,'table-condensed')])[2]//tbody//tr//td[3]","Event Type ");
	public final Locator QUEUE = new Locator("QUEUE ","(//table[contains(@class,'table-condensed')])[2]//tbody//tr//td[4]","Queue");
	public final Locator NOTE = new Locator("NOTE ","(//table[contains(@class,'table-condensed')])[2]//tbody//tr//td[5]","Note ");

	//Contact Hisoty //need edit
	public final Locator FILTER_BY_ORDERID_TEXT = new Locator("FILTER BY ORDER NUMBER TEXT","//span[text()='Filtered by Order#: ']","Filter By Order Number Text");
	public final Locator FILTER_BY_ORDERID = new Locator("FILTER BY ORDER NUMBER","//span[text()='Filtered by Order#: ']//b","Filter By Order Number");
	public final Locator FILTER_SHOW_TEXT = new Locator("FILTER SHOW TEXT","//span[text()='Show : ']","Filter Show Text");
	public final Locator FILTER_DROPDOWN = new Locator("FILTER DROPDOWN","//span[text()='Show : ']//select","Filter Dropdown");
	public final Locator SEE_NOTES_BUTTON = new Locator("SEE NOTES BUTTON","//button[text()='See Notes']","See Notes Button");
	public final Locator QA_FORM_BUTTON = new Locator("QA_FORM_BUTTON","//button[text()='QA Form']","QA Form Button");
	public final Locator CONTACT_HISTORY_TABLE = new Locator("CONTACT HISTORY TABLE","//table[@ng-table='tableParams']","Contact History Table");
	public final Locator CASE_NUMBER_COL_NAME = new Locator("CASE# COL NAME","//table[@ng-table='tableParams']//thead//tr//th[1]//div","Case # Column Name");
	public final Locator CREATE_ON_COL_NAME = new Locator("CREATE ON COL NAME","//table[@ng-table='tableParams']//thead//tr//th[2]//div","Create On Column Name");
	public final Locator ORDER_NUMBER_COL_NAME = new Locator("ORDER# COL NAME","//table[@ng-table='tableParams']//thead//tr//th[3]//div","Order # Column Name");
	public final Locator STATUS_NUMBER_COL_NAME = new Locator("STATUS COL NAME","//table[@ng-table='tableParams']//thead//tr//th[4]//div","Status Column Name");
	public final Locator CONTACT_HISTORY_TABLE_CONTENT = new Locator("CONTACT HISTORY TABLE CONTENT","//table[@ng-table='tableParams']//tbody//tr[i]//td[j]","Contact History Table Content");
	public final Locator PRE_PAGE_BUTTON = new Locator("PRE PAGE BUTTON","(//div[contains(@class,'ng-table-pager')])[2]//a[@ng-switch-when='prev']","Previous Page Button");
	public final Locator NEXT_PAGE_BUTTON = new Locator("NEXT PAGE BUTTON","(//div[contains(@class,'ng-table-pager')])[2]//a[@ng-switch-when='next']","Next Page Button");
	public final Locator FIRST_PAGE_BUTTON = new Locator("FIRST PAGE BUTTON","(//div[contains(@class,'ng-table-pager')])[2]//a[@ng-switch-when='first']","First Page Button");
	public final Locator LAST_PAGE_BUTTON = new Locator("LAST PAGE BUTTON","(//div[contains(@class,'ng-table-pager')])[2]//a[@ng-switch-when='last']","Last Page Button");
	public final Locator MIDDLE_PAGE_BUTTON = new Locator("MIDDLE PAGE BUTTON","((//div[contains(@class,'ng-table-pager')])[2]//a[@ng-switch-when='page'])[2]","Middle Page Button");
	public final Locator NO_RESULTS_FOUND = new Locator("No results found ","//h4[contains(.,'Total Found: 0')]","No results found");
	public final Locator NO_RESULTS_FOUND_LAYAWAY = new Locator("No results found ","//h4[contains(.,'Total Found: 0')])[2]","No results found in Layaway");
	public final Locator GET_TOKEN_BUTTON = new Locator("Get Token button","//a[text()='Get Token']","Get Token button");
	public final Locator TOKEN_POPUP = new Locator("","//div[@class='modal-dialog']//h5/b[contains(text(),'The Token ID is : ')]","Token popup");
	public final Locator CLOSE_TOKEN = new Locator("","//button[@class='close']","Close token popup");
	public final Locator ORDER_ID = new Locator("","//h3[contains(text(),'Order# ')]","order ID in order details page");
	public final Locator CONTACT_HISTORY_MENU_DOWN = new Locator("", "//i[contains(@class,'glyphicon-chevron-down')]", "Contact History Menu down");
	//DDC fulfillment 

	public final Locator DELIVERYDETAILS_TEXT = new Locator("DELIVERYDETAILS TEXT","//legend[text()='Delivery Details']","DELIVERYDETAILS TEXT");
	public final Locator DELIVERYDETAILS = new Locator("DELIVERYDETAILS","//div[@ng-if='verifyHomeDelivery()']","DELIVERYDETAILS");
	public final Locator DELIVERYDETAILS_TABS = new Locator("DELIVERYDETAILS TABS","//a[text()='{0}']","DELIVERYDETAILS TABS");
	public final Locator DELIVERYDETAILS_DOS_NUMBER = new Locator("DELIVERYDETAILS DOSNUMBER","//a[@ng-click='switchOrderView($index,order);']","DELIVERYDETAILS DOS NUMBER");


	public final Locator WRAP_UP_TAB = new Locator("","//a[text()='Wrap Up']","Wrap up tab");
	public final Locator ORDER_NUMBER_INPUT = new Locator("","//input[@id='orderNumber']","Order Number input on Order Lookup Page");
	public final Locator RFC_OPTION = new Locator("","(//div[contains(@class,'modal-body')]//select)[{0}]","RFC Option");
	public final Locator RFC_FIRST_OPTION_VALUE = new Locator("","(//div[contains(@class,'modal-body')]//select//option[@value='0'])[{0}]","RFC First Option Value");
	public final Locator RFC_OPTION_LABEL = new Locator("","(//div[contains(@class,'modal-body')]//select//ancestor::div[contains(@class,'rfc-row')]//label)[{0}]","RFC Option Label");
	public final Locator RFC_WHO_CONTACTED = new Locator("","//select[contains(@ng-change,'selectContactType')]","RFC Who Contacted");
	public final Locator RFC_HOW_CONTACTED = new Locator("","//select[contains(@ng-model,'selected.channel')]","RFC How Contacted");
	public final Locator RFC_WHATISSUETYPE = new Locator("","//select[contains(@ng-options,'CARE') and contains(@ng-model,'selected.category')]","RFC What Type Of Issue");
	public final Locator RFC_WHY_CONTACTED = new Locator("","//select[contains(@ng-model,'selected.rfcReason')]","RFC Why Contacted");
	public final Locator RFC_REASON1 = new Locator("","//select[contains(@ng-model,'selected.rfcWrapup')]","RFC Reason 1");
	public final Locator RFC_BUTTON_WRAPUP = new Locator("","//button[text()='Wrap Up']","RFC Wrap Up button");
	public final Locator RFC_BUTTON_CANCEL = new Locator("","//button[contains(text(),'Cancel')]","RFC Cancel button");
	public final Locator RFC_TELL_US_MORE = new Locator("","//select[contains(@ng-model,'selected.rfcReason[1]')]","RFC Tell us more");
	public final Locator RFC_EDITED_EMAIL_ADDRESS = new Locator("","//input[contains(@ng-model,'selected.editedEmailAddress')]","RFC Edited Email Address");
	public final Locator RFC_GUEST_ICON = new Locator("","//div[contains(@on-show,'showGuestActions')]","RFC Guest Icon");
	public final Locator RFC_RADIO_EDITED_EMAIL = new Locator("","//input[contains(@ng-change,'populateEmailDeclinedReasons(true)')]","RFC Radio Edited Email Address");
	public final Locator SELECT_ITEM_FOR_WRAPUP = new Locator("","//input[contains(@ng-model,'item.Selected')]","Select Item For Wrap Up");
	public final Locator BUTTON_WRAP_ORDER_CONTACT = new Locator("","//button[contains(text(),'WRAP UP ORDER & CONTACT')]","WRAP UP ORDER & CONTACT button");
	public final Locator BUTTON_WRAP_ORDER = new Locator("","//button[contains(text(),'WRAP UP & CONTINUE')]","WRAP UP & CONTINUE W/ CONTACT button");
	public final Locator EMAIL_SUBMIT_BUTTON = new Locator("","//button[text()='Submit']","Email submit button");
	public final Locator SUCCESS_OK_BUTTON = new Locator("","//button[text()='OK']","Sucess OK button");
	public final Locator ORDER_CONTACT_HISTORY= new Locator("","//span[contains(text(),'Contact History')]","Order Contact history");
	public final Locator ORDER_CONTACT_HISTORY_INTERACTION= new Locator("","(//tbody[@ng-repeat='caseEvent in caseModal.events']//tr//td[3])[1]","Order Contact history interaction");
	public final Locator ORDER_CONTACT_HISTORY_ADJUSTMENT= new Locator("","//td[contains(text(),'{0}')]//parent::tr//td/div[contains(.,'{1}')]","Order Contact history");
	public final Locator ORDER_CONTACT_HISTORY_NOTES= new Locator("","//td[contains(text(),'{0}')]//ancestor::table[@class='table table-condensed']//tr[4]/td/div","Order Contact history NOTES");
	public final Locator ORDER_CONTACT_HISTORY_NOTES_DELIVERY= new Locator("","//td[contains(text(),'{0}')]","Order Contact history NOTES");

	//public final Locator EMAIL_TEMPLATE_NOTES= new Locator("","//div[@class='ng-isolate-scope']//a","Email Template in interaction");
	public final Locator EMAIL_TEMPLATE_NOTES= new Locator("","//td[contains(text(),'{0}')]//parent::tr//a[contains(text(),'{1}')]","Email Template in interaction");
	public final Locator ACTION_DROPDOWN= new Locator("","//select[@id='summaryAction']","Action  dropdown");
	public final Locator ACTION_DROPDOWN_OPTION= new Locator("","//select[@id='summaryAction']/option[@value='Contact Customer']","Action dropdown option");
	public final Locator GO_BUTTON= new Locator("","//button[text()='Go']","Go button");
	public final Locator STORE_DROPDOWN= new Locator("","//select[@name='store']","store dropdown");
	public final Locator STORE_DROPDOWN_OPTION= new Locator("","//select[@name='store']/option[@value='{0}']","store dropdown option");
	public final Locator EMAILTEMPLATE_DROPDOWN= new Locator("","//select[@name='typeByStore']","EmailTemplate dropdown");
	public final Locator EMAILTEMPLATE_DROPDOWN_OPTION= new Locator("","//select[@name='typeByStore']/option[@value='{0}']","EmailTemplate dropdown option");
	public final Locator CONTACT_HISTORY_EMAILTEMPLATE= new Locator("","//div[@class='ng-scope']//a[text()='{0}']","Email Template in notes");
	//delivery locators
	public final Locator PICKUP_BUTTON= new Locator("","//button[contains(text(),'Pickup')]","Pickup button");
	public final Locator WRAPUP_BUTTON= new Locator("","//button[contains(text(),'Wrap Up')]","wrap up button");
	public final Locator REASON_FOR_PICKUP_DROPDOWN= new Locator("","//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required']","Reason for pickup dropdown");
	public final Locator REASON_FOR_PICKUP_DROPDOWN_OPTION= new Locator("","//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required']/option[contains(text(),'Other')]","Reason for pickup dropdown option");
	public final Locator REASON_FOR_PICKUP_DROPDOWN_OPTIONS= new Locator("","//select[@class='input-sm ng-pristine ng-invalid ng-invalid-required']/option[contains(text(),'{0}')]","Reason for pickup dropdown option");
	
	
	public final Locator SELECT_ITEM= new Locator("","(//table/tbody[@class='ng-scope ng-isolate-scope mspselector selectedItemsForPickUp noselection'])[{0}]","Select item");
	public final Locator SELECT_ITEM_NUMBER= new Locator("","//table/tbody[@class='ng-scope ng-isolate-scope mspselector selectedItemsForPickUp noselection']","Select item");
	public final Locator PICKUP_QUANTITY= new Locator("","//input[@ng-model='items.selectedQuantity']","Pickup quantity");
	public final Locator CONTINUE_BUTTON= new Locator("","//button[contains(text(),'Continue')]","continue button");
	public final Locator RESCHEDULE_LINE_ITEM_VERIFY= new Locator("","//div[contains(text(),'New order has been created. Close this notification to proceed to new order and reschedule selected items.')]","RESCHEDULE_LINE_ITEM_VERIFY");
	public final Locator CREATE_NEW_ORDER= new Locator("CREATE_NEW_ORDER","//button[contains(text(),'Create New Order to Rescehdule Items')]","CREATE_NEW_ORDER");
	public final Locator RERESERVE_BUTTON= new Locator("","	//button[contains(text(),'Re Reserve Order')]","Rereserve button");
	public final Locator RERESERVE_ITEM_COUNT= new Locator("","//tr[contains(@msp-selector,'model.selectedItemsForReReserve')]","Rereserve button");
	public final Locator RERESERVE_ITEM= new Locator("","(//tr[contains(@msp-selector,'model.selectedItemsForReReserve')])[{0}]","Rereserve button");
	public final Locator ACTION_CENTER= new Locator("","//a[contains(text(),'Action Center')]","Action center");
	public final Locator CONCESSION_YES= new Locator("","//div[@answer='model.concessionOffered']//button[@class='btn btn-primary radio ng-pristine ng-valid' and text()='Yes']","Concession Yes button");
	public final Locator CONCESSIONACCEPTED_NO= new Locator("","//div[@answer='model.concessionAccepted']//button[@class='btn btn-primary radio ng-pristine ng-valid' and text()='No']","Concession Accepted No button");
	public final Locator CATEGORY_CODE_DROPDOWN= new Locator("","(//select[@ng-model='item.confirmationCategoryCode'])[{0}]","Category drop down");
	public final Locator CATEGORY_CODE_DROPDOWN_RERESERVE= new Locator("","(//select[@ng-model='item.reasonCode'])[{0}]","Category drop down");
	public final Locator CATEGORY_CODE_DROPDOWN_OPTION= new Locator("","(//select[@ng-model='item.confirmationCategoryCode']/option[contains(text(),'Product')])[{0}]","Category drop down option");
	public final Locator PICKUP_REASON_CODE_DROPDOWN= new Locator("","(//select[@ng-model='item.confirmationReasonCode'])[{0}]","Reasoncode drop down");
	public final Locator REASON_CODE_DROPDOWN_OPTION= new Locator("","(//select[@ng-model='item.confirmationReasonCode']/option[contains(text(),'NOT Match Order') and @value=0])[{0}]","Reasoncode drop down option");
	public final Locator REASON_CODE_DROPDOWN_OPTION_RERESERVE= new Locator("","(//select[@ng-model='item.reasonCode']/option[contains(text(),'Damage by Delivery Team')])[{0}]","Reasoncode drop down option");
	public final Locator CREATE_ORDER_BUTTON= new Locator("","//button[contains(text(),'Create Order')]","create order button");
	public final Locator ORDERCREATED_OK_BUTTON= new Locator("","//button[text()='OK']","ORDERCREATED_OK_BUTTON");
	public final Locator NEW_ORDER_CREATED_MSG= new Locator("","//div[@class='modal-body text-info ng-scope ng-binding']","new order created msg");
	public final Locator DOS_ORDER_STATUS= new Locator("","//strong[text()='DOS Order Status:']//ancestor::div[@label='DOS Order Status']/div/p/span","DOS order status");
	public final Locator DOS_ORDER_NUMBER= new Locator("DOS_ORDER_NUMBER","//strong[text()='DOS Order Number:']//ancestor::div[@label='DOS Order Number']/div/p/span","DOS_ORDER_NUMBER");
	public final Locator DOS_UNIT_NUMBER= new Locator("DOS_UNIT_NUMBER","//strong[text()='DOS Unit:']//ancestor::div[@label='DOS Unit']/div/p/span","DOS_UNIT_NUMBER");
	public final Locator SALESCHECK_LINK= new Locator("SALESCHECK_LINK","//strong[text()='Salescheck:']//ancestor::div[@label='Salescheck']/div/p/a","SALESCHECK_LINK");
	public final Locator SALE_DATE= new Locator("SALE_DATE","//strong[text()='Sale Date:']//ancestor::div[@label='Sale Date']/div/p/span","SALE_DATE");
	public final Locator DELIVERY_DATE_ORDER= new Locator("DELIVERY_DATE_ORDER","//strong[text()='Delivery Date:']//ancestor::div[@label='Delivery Date']/div/p/span","DELIVERY_DATE_ORDER");
	public final Locator SHIP_METHOD= new Locator("SHIP_METHOD","//strong[text()='Ship Method:']//ancestor::div[@label='Ship Method']/div/p/span","SHIP_METHOD");
	public final Locator TIME_WINDOW= new Locator("TIME_WINDOW","(//strong[text()='Time Window:']//ancestor::div[@label='Time Window']/div/p/span)[{0}]","TIME_WINDOW");
	public final Locator ORIGINAL_DELIVERY_DATE= new Locator("ORIGINAL_DELIVERY_DATE","(//strong[text()='Original Delivery Date:']//ancestor::div[@label='Original Delivery Date']/div/p/span)[{0}]","ORIGINAL_DELIVERY_DATE");
	public final Locator ORDER_TABLE= new Locator("ORDER_TABLE","//tr[@ng-repeat='item in $data']","ORDER_TABLE");
	public final Locator DELIVERY_NOTE= new Locator("DELIVERY_NOTE","//textarea[@placeholder=' Enter notes']","DELIVERY_NOTE");
	public final Locator ADD_NOTE= new Locator("ADD_NOTE","//button[contains(text(),'Add Note')]","ADD_NOTE");
	public final Locator ROUTE_STATUS_OPEN= new Locator("ROUTE_STATUS_OPEN","//div[contains(@ng-hide,'routeDetails.status.')]/span","ROUTE_STATUS_OPEN");
	public final Locator ROUTE_STATUS= new Locator("ROUTE_STATUS","//tr[@ng-repeat='stop in routeDetails.routeInfo.stopsInfo']","ROUTE_STATUS");



	public final Locator CANCEL_BUTTON= new Locator("CANCEL_BUTTON","//button[contains(text(),'Cancel Order')]","CANCEL BUTTON");
	public final Locator TAB_NAME= new Locator("TAB_NAME","//a[contains(text(),'{0}')]","TAB_NAME");
	public final Locator CANCEL_BUTTON_POPUP= new Locator("CANCEL_BUTTON_POPUP","//button[@class='btn btn-default btn-sm' and contains(text(),'Cancel')]","CANCEL_BUTTON_POPUP");
	public final Locator COMPLETE_CANCEL_BUTTON= new Locator("COMPLETE_CANCEL_BUTTON","//button[contains(text(),'Complete Cancellation')]","COMPLTE CANCEL BUTTON");
	public final Locator COMPLETE_CANCEL_VERIFY= new Locator("COMPLETE_CANCEL_VERIFY","//span[contains(text(),'Selected Order has been Cancelled')]","COMPLETE CANCEL VERIFIED TEXT");
	public final Locator COMPLETE_CANCEL_VERIFY_FOR_LINE_ITEM= new Locator("COMPLETE_CANCEL_VERIFY_FOR_LINE_ITEM","//span[contains(text(),'Selected Items(s) has been Cancelled')]","COMPLETE CANCEL VERIFIED TEXT FOR LINE ITEM");
	public final Locator ORDER_STATUS= new Locator("ORDER_STATUS","//div[@label='DOS Order Status']//div/p/span","ORDER STATUS");
	public final Locator ORDER_STATUS_CANCELLED= new Locator("ORDER_STATUS_CANCELLED","//div[@label='DOS Order Status']//div/p/span[contains(text(),'Cancelled')]","ORDER STATUS Cancelled");
	public final Locator ORDER_STATUS_OPEN= new Locator("","//div[@label='DOS Order Status']//div/p/span[contains(text(),'Open')]","ORDER STATUS Open");
	public final Locator ENTIRE_ORDER= new Locator("CANCEL_ENTIRE_ORDER","//button[contains(text(),'Entire Order')]","CANCEL ENTIRE ORDER BUTTON");
	public final Locator SELECT_ANOTHER_ACTION= new Locator("SELECT_ANOTHER_ACTION","//a[contains(text(),' < Select another action ')]","SELECT_ANOTHER_ACTION");
	public final Locator LINE_ITEM= new Locator("CANCEL_LINE_ITEM","//button[contains(text(),'Select Items Only')]","CANCEL LINE ITEM BUTTON");
	public final Locator REASON_DROPDOWN_CANCEL= new Locator("REASON_DROPDOWN_CANCEL","//select[@ng-model='selected.reasonCode']","REASON DROPDOWN FOR CANCEL ");
	public final Locator BACK_TO_ORDER= new Locator("BACK_TO_ORDER","//button[contains(text(),'Back to Order ')]","BACK_TO_ORDER ");
	public final Locator REASON_DROPDOWN_CANCEL_OPTIONS_COUNT= new Locator("REASON_DROPDOWN_CANCEL_OPTIONS","//select[@ng-model='selected.reasonCode']/option","OPTION IN REASON DROPDOWN FOR CANCEL");
	public final Locator REASON_DROPDOWN_CANCEL_OPTIONS= new Locator("REASON_DROPDOWN_CANCEL_OPTIONS","//option[@ng-repeat='reasonCode in reasonCodes'][{0}]","OPTION IN REASON DROPDOWN FOR CANCEL");
	public final Locator LINE_ITEM_COUNT= new Locator("","//td[contains(@data-title,'Dos Item Status')]","LINE ITEM COUNT");
	public final Locator LINE_ITEM_TEXT= new Locator("","(//td[contains(@data-title,'Dos Item Status')])[{0}]","LINE_ITEM_TEXT");
	public final Locator LINE_ITEM_NAME_STATUS= new Locator("","//td[contains(@data-title,'Description') and contains(text(),'{0}')]//ancestor::tr//td[contains(@data-title,'Dos Item Status') and contains(text(),'{1}')]","Line Item Name Status");
	//public final Locator LINE_ITEM_NAME_NOT_CANCELLED= new Locator("","//td[contains(@data-title,'Dos Item Status') and not(contains(text(),'Cancelled'))]//ancestor::tr//td[contains(@data-title,'Description')]","Line Item Name");
	public final Locator LINE_ITEM_NAME_NOT_CANCELLED= new Locator("","(//tr[@value='item'])[1]/td[2]","Line Item Name");
	public final Locator LINE_ITEM_NAME_CANCELLED= new Locator("","//td[contains(@data-title,'Dos Item Status') and contains(text(),'Cancelled')]//ancestor::tr//td[contains(@data-title,'Description')]","Line Item Name");
	public final Locator LINE_ITEM_ROW= new Locator("LINE_ITEM_ROW","(//tr[@value='item'])[{0}]","LINE_ITEM_ROW");
	public final Locator LINE_ITEM_ROW_QUANTITY_AVAILABLE= new Locator("","//th[contains(text(),'Available')]/ancestor::table/tbody[{0}]//td[6]","LINE ITEM ROW Quantity Available");
	public final Locator LINE_ITEM_ROW_QUANTITY_ORDERED= new Locator("","//th[contains(text(),'Available')]/ancestor::table/tbody[{0}]//td[10]","LINE ITEM ROW Quantity Available");
	public final Locator LINE_ITEM_ROW_QUANTITY_AVAILABLE_EVEN_EXCHANGE= new Locator("","//input[@name='selectedQuantity']//ancestor::tr//td[8]","LINE ITEM ROW Quantity Available Even Exchange");
	public final Locator LINE_ITEM_ROW_QUANTITY= new Locator("LINE_ITEM_ROW_QUANTITY","(//td[contains(text(),'{0}')]/parent::tr//input[@name='selectedQuantity'])['{1}']","LINE ITEM ROW Quantity");
	public final Locator LINE_ITEM_ROW_QUANTITY_COUNT= new Locator("LINE_ITEM_ROW_QUANTITY_COUNT","(//input[@name='selectedQuantity'])[{0}]","LINE ITEM ROW Quantity");
	public final Locator LINE_ITEM_CANCEL= new Locator("LINE_ITEM_CANCEL","//button[contains(text(),'Cancel Items')]","LINE ITEM CANCEL");
	public final Locator EVEN_EXCHANGE_BUTTON = new Locator("Even Exchange Button", "//button[contains(text(),'Even Exchange')]", "Even Exchange Button");
	public final Locator EVEN_EXCHANGE_ITEM = new Locator("EVEN_EXCHANGE_ITEM", "//table[@class='table']/tbody[{0}]/tr", "Even Exchange Item");
	public final Locator EVEN_EXCHANGE_ITEM_TABLE = new Locator("EVEN_EXCHANGE_ITEM", "//table[@class='table']/tbody/tr", "Even Exchange Item");
	public final Locator EVEN_EXCHANGE_ITEM_DROPDOWN = new Locator("EVEN_EXCHANGE_LIST_ROW_DROPDOWN", "//table[@class='table']/tbody[{0}]/tr//select", "Even Exchange List Item Dropdown");
	public final Locator ACTION_CETNER_CONTINUE_BUTTON = new Locator("Action Center Continue Button", "//button[contains(text(),'Continue')]", "Action Center Continue Button");
	public final Locator EVEN_EXCHANGE_RETURN_CATEGORY_CODE_DROPDOWN = new Locator("RETURN_CATEGORY_CODE_DROPDOWN", "(//select[starts-with(@ng-options,'selectedCategory')])[{0}]", "Return Category Code Dropdown");
	public final Locator EVEN_EXCHANGE_RETURN_REASON_CODE_DROPDOWN = new Locator("RETURN_REASON_CODE_DROPDOWN", "(//select[starts-with(@ng-options,'selectedReasonCode')])[{0}]", "Return Reason Code Dropdown");
	public final Locator EVEN_EXCHANGE_CREATE_ORDER_BUTTON =new Locator("EVEN_EXCHANGE_CREATE_ORDER_BUTTON", "//button[contains(text(),'Create Order')]", "Even Exchange-Create Order Button");
	public final Locator RERESERVE_CREATE_ORDER_BUTTON =new Locator("EVEN_EXCHANGE_CREATE_ORDER_BUTTON", "//button[contains(text(),'Create New Order to Re-reserve Items')]", "Even Exchange-Create Order Button");
	public final Locator OFFER_CONSESSION_NO_BUTTON = new Locator("OFFER_CONSESSION_NO_BUTTON", "//div[starts-with(@question,'Was the')]//button[contains(text(),'No')]", "Offer Consession No Button");
	public final Locator RESCHEDULE_BUTTON= new Locator("RESCHEDULE_BUTTON","//button[contains(text(),'Reschedule Delivery')]","RESCHEDULE BUTTON");
	public final Locator EVEN_EXCHANGE_SUCCESS_MESSAGE = new Locator("EVEN_EXCHANGE_SUCCESS_MESSAGE", "//div[contains(text(),'New order has been created')]", "New Order Created dialog");
	public final Locator EVEN_EXCHANGE_SUCCESS_DIALOG_OK_BUTTON = new Locator("EVEN_EXCHANGE_SUCCESS_DIALOG_OK_BUTTON", "//button[contains(text(),'OK')]", "Ok Button on New Order Created dialog");
	public final Locator EVEN_EXCHANGE_NO_ITEMS_ALLOWED = new Locator("EVEN_EXCHANGE_NO_ITEMS_ALLOWED","//span[contains(text(),'No items are eligible for Pickup/Exchange')]","");


	public final Locator HOLD_FOR_DELIVERY= new Locator("HOLD_FOR_DELIVERY","//button[contains(text(),'Hold for Future Delivery')]","HOLD_FOR_DELIVERY");
	public final Locator QUEUE_FOR_FOLLOW_UP= new Locator("QUEUE_FOR_FOLLOW_UP","//button[contains(text(),'Queue for Follow Up')]","QUEUE_FOR_FOLLOW_UP");
	public final Locator UPDATE_SCIM_CODE= new Locator("UPDATE_SCIM_CODE","//button[contains(text(),'Update SCIM Code')]","UPDATE_SCIM_CODE");
	public final Locator UPDATE_SCIM_CODES= new Locator("UPDATE_SCIM_CODE","//button[contains(text(),'Update SCIM Codes')]","UPDATE_SCIM_CODE");
	public final Locator QUEUE_FOR_FOLLOW_UP_VERIFY= new Locator("QUEUE_FOR_FOLLOW_UP_VERIFY","//div[contains(text(),'Get  Queue route Action has been successfully processed')]","QUEUE_FOR_FOLLOW_UP_VERIFY");
	public final Locator QUEUE_FOR_FOLLOW_UP_CHECKBOX= new Locator("QUEUE_FOR_FOLLOW_UP_CHECKBOX","//input[@ng-model='item.Selected']","QUEUE_FOR_FOLLOW_UP_CHECKBOX");
	public final Locator SELECTED_OPTION_QUEUE= new Locator("SELECTED_OPTION_QUEUE","//select[@ng-model='selected.reasonCode']/option[contains(text(),'{0}')]","SELECTED_OPTION_QUEUE");
	public final Locator QUEUE_NAME= new Locator("QUEUE_NAME","//strong[contains(text(),'Queue:')]//parent::h6","QUEUE_NAME");
	public final Locator SCHEDULE_FOLLOW_UP= new Locator("SCHEDULE_FOLLOW_UP","//button[contains(text(),'Schedule Follow-Up Date')]","SCHEDULE_FOLLOW_UP");
	public final Locator SCHEDULE_FOLLOW_UP_VERIFY= new Locator("SCHEDULE_FOLLOW_UP_VERIFY","//div[contains(text(),'This order has been held to delay delivery to an undetermined date.')]","SCHEDULE_FOLLOW_UP_VERIFY");
	public final Locator AVAILABLE_DATE_IN_CALANDER= new Locator("AVAILABLE_DATE_IN_CALANDER","//button[@class='btn btn-default btn-sm dt-available dt-verified']","AVAILABLE DATE IN CALANDER");
	public final Locator AVAILABLE_DATE_IN_CALANDER_TEXT= new Locator("AVAILABLE_DATE_IN_CALANDER_TEXT","//button[@class='btn btn-default btn-sm dt-available dt-verified']/span","AVAILABLE_DATE_IN_CALANDER_TEXT");
	public final Locator AVAILABLE_TIME= new Locator("AVAILABLE_DATE_IN_CALANDER","//button[@ng-if='!dayOfDelivery']","AVAILABLE DATE IN CALANDER");
	public final Locator RESCHEDULE_VERIFY= new Locator("RESCHEDULE_VERIFY","//span[contains(text(),'Delivery order has been rescheduled.')]","RESCHEDULE VERIFY TEXT");
	public final Locator RESCHEDULE_SUBMIT= new Locator("RESCHEDULE_SUBMIT","//button[contains(text(),'Submit')]","RESCHEDULE SUBMIT TEXT");
	public final Locator DELIVERY_DATE= new Locator("DELIVERY_DATE","//strong[text()='Delivery Date:']//ancestor::div[@label='Delivery Date']/div/p/span[string-length(text()) > 7]","DELIVERY DATE");
	public final Locator DELIVERY_DATE_TEXT= new Locator("DELIVERY_DATE","//strong[text()='Delivery Date:']//ancestor::div[@label='Delivery Date']/div/p/span[contains(text(),'{0}')]","DELIVERY DATE TEXT");
	public final Locator PEND_CODE= new Locator("PEND_CODE","//strong[text()='Pend Code:']//ancestor::div[@label='Pend Code']/div/p/span","PEND CODE TEXT");

	public final Locator DELIVERY_NOTES= new Locator("DELIVERY_NOTES","//a[contains(text(),'Delivery Notes')]","DELIVERY NOTES");
	public final Locator ORDER_DETAIL= new Locator("ORDER_DETAIL","//a[contains(text(),'Order Detail')]","ORDER DETAIL");
	public final Locator DELIVERY_NOTES_DATA= new Locator("DELIVERY_NOTES_DATA","//td[contains(text(),'{0}')]","DELIVERY NOTES DATA");

	public final Locator SYW_LINK_LINKED_INFORMATION = new Locator("SYW_LINK_LINKED_INFORMATION", "//*[contains(@ng-repeat,'member in memberData.member')]//div[contains(text(),'Linked')]", "SYW_LINK_LINKED_INFORMATION");
	public final Locator SYW_LINK_ADDRESS_INFORMATION = new Locator("SYW_LINK_ADDRESS_INFORMATION", "//*[contains(@ng-repeat,'member in memberData.member')]//div[contains(text(),'Address')]", "SYW_LINK_ADDRESS_INFORMATION");
	public final Locator SYW_LINK_EMAIL_INFORMATION = new Locator("SYW_LINK_EMAIL_INFORMATION", "//*[contains(@ng-repeat,'member in memberData.member')]//div[contains(text(),'Email')]", "SYW_LINK_EMAIL_INFORMATION");
	public final Locator SYW_LINK_CITY_INFORMATION = new Locator("SYW_LINK_CITY_INFORMATION", "//*[contains(@ng-repeat,'member in memberData.member')]//div[contains(text(),'City')]", "SYW_LINK_CITY_INFORMATION");
	public final Locator SYW_NO_SEARCH_RESULTS_LABEL = new Locator("", "//span[contains(text(),'No records found for this search criteria')]", "SYW_NO_SEARCH_RESULTS_LABEL");

	public final Locator AUDIT_TRAIL_DETAIL_PAGE = new Locator("AUDIT_TRAIL_Detail","//div[@ng-controller='auditTrailCtrl']","AUDIT TRAIL Detail");
	public final Locator SYW_MAX_DETAIL_PAGE = new Locator("SYW_MAX_Detail","//div[@ng-controller='sywMaxCtrl']//div/p","SYW MAX Detail");
	public final Locator NOT_A_SYW_MAX_MEMBER = new Locator("NOT_A_SYW_MAX_MEMBER","//div[@ng-controller='sywMaxCtrl']","NOT_A_SYW_MAX_MEMBER");
	public final Locator SYW_MAX_SAVINGS = new Locator("SYW_MAX_SAVINGS", "//label[contains(text(),'Max Savings')]/following-sibling::div/p", "SYW_MAX_SAVINGS");
	public final Locator REASON_DROPDOWN= new Locator("REASON_DROPDOWN","//select[@ng-model='selected.reasonCode']","REASON DROPDOWN");

	public final Locator CATEGORY_DROPDOWN_CODE_COUNT= new Locator("CATEGORY_DROPDOWN_CODE","//strong[contains(text(),'Category')]/ancestor::div[1]/div//select/option","CATEGORY_DROPDOWN_CODE");
	public final Locator CATEGORY_DROPDOWN_CODE= new Locator("CATEGORY_DROPDOWN_CODE_COUNT","//strong[contains(text(),'Category')]/ancestor::div[1]/div//select","CATEGORY_DROPDOWN_CODE_COUNT");
	public final Locator DELIVERY_REASON_DROPDOWN_COUNT= new Locator("DELIVERY_REASON_DROPDOWN","//select[@name='reasonCode']/option","DELIVERY_REASON_DROPDOWN");
	public final Locator DELIVERY_REASON_DROPDOWN= new Locator("DELIVERY_REASON_DROPDOWN_COUNT","//select[@name='reasonCode']","DELIVERY_REASON_DROPDOWN_COUNT");
	public final Locator WRAP_UP_NOTES= new Locator("WRAP_UP_NOTES","//strong[contains(text(),'Notes')]/parent::span/following-sibling::div//textarea","WRAP_UP_NOTES");
	public final Locator SELECT_ITEMS_FOR_CONTACT= new Locator("SELECT_ITEMS_FOR_CONTACT","//th[contains(text(),'Description')]/parent::tr//input","SELECT_ITEMS_FOR_CONTACT");
	public final Locator WRAP_UP_WITHOUT_CONTACT= new Locator("WRAP_UP_WITHOUT_CONTACT","//button[contains(text(),'WRAP UP & CONTINUE W/ CONTACT')]","WRAP_UP_WITHOUT_CONTACT");
	public final Locator WRAP_UP_ORDER_CONTACT= new Locator("WRAP UP ORDER & CONTACT","//button[contains(text(),'WRAP UP ORDER & CONTACT')]","WRAP UP ORDER & CONTACT");

	public final Locator CASE_EXISTS= new Locator("CASE_EXISTS"," //div[contains(text(),'Open Case')]","CASE_EXISTS");
	public final Locator OK_BUTTON_ON_POPUP= new Locator("OK_BUTTON_ON_POPUP","//button[@id='modalclose']","OK_BUTTON_ON_POPUP");
	public final Locator ACTION_CENTER_INSTALLATION_NOTES = new Locator("", "//div[contains(text(),'Note') and contains(text(),'installation')]", "Installation Service Notification");

	// Commmercial Order Popup
	public final Locator COMMERCIAL_SALES_ORDER_POPUP_TEXT = new Locator("", "//div[contains(text(),'commercial sales')]", "COMMERCIAL_SALES_ORDER_POPUP_TEXT");
	public final Locator COMMERCIAL_SALES_ORDER_POPUP_CONTINUE_BUTTON = new Locator("", "//button[contains(text(),'CONTINUE')]", "COMMERCIAL_SALES_ORDER_POPUP_CONTINUE_BUTTON");


	//update contact 
	public final Locator SHOW_NAME_UPDATE= new Locator("SHOW_NAME_UPDATE","//td[contains(text(),'Update Delivery Contact Details')]/a[contains(text(),'Show Updates')]","SHOW_NAME_UPDATE");
	public final Locator UPDATE_DELIVERY_ADDRESS= new Locator("UPDATE_DELIVERY_ADDRESS","//td[contains(text(),'Update Delivery Address')]/a[contains(text(),'Show Updates')]","UPDATE_DELIVERY_ADDRESS");

	public final Locator CUSTOMER_NAME_FIELD= new Locator("CUSTOMER_NAME_FIELD","//td[contains(text(),'Order Ship To Cutomer Name')]","CUSTOMER_NAME_FIELD");
	public final Locator ORIGINAL_NAME= new Locator("ORIGINAL_NAME","//td[contains(text(),'Order Ship To Cutomer Name')]/parent::tr/td[2]","ORIGINAL_NAME");
	public final Locator UPDATED_NAME= new Locator("UPDATED_NAME","//td[contains(text(),'Order Ship To Cutomer Name')]/parent::tr/td[3]","UPDATED_NAME");
	public final Locator VALUE= new Locator("VALUE","//tr[td[contains(text(),'Order Ship To Cutomer Name')]]/td[contains(text(),'{0}')]","VALUE");
	public final Locator ORDER_SHIP_TO_CUSTOMER_NAME= new Locator("ORDER_SHIP_TO_CUSTOMER_NAME","//td[contains(text(),'{0}')] and //td[contains(text(),'{0}')]","VALUE");
	public final Locator UPDATE_CONTACT_NOTES= new Locator("UPDATE_CONTACT_NOTES","//tbody[tr[td[contains(text(),'{0}')]/following-sibling::td[contains(text(),'{1}')]//following-sibling::td[contains(text(),'{2}')]]]","UPDATE_CONTACT_NOTES");
	public final Locator DOS_ITEM_STATUS_COUNT= new Locator("DOS_ITEM_STATUS_COUNT","//td[contains(text(),'{0}') and @data-title-text='Dos Item Status']","DOS_ITEM_STATUS_COUNT");		
	public final Locator ITEM= new Locator("ITEM","//tr[td[contains(text(),'{0}')]][{1}]/td[contains(@data-title,'Item')]","ITEM");
	public final Locator DIVISION= new Locator("DIVISION","//tr[td[contains(text(),'{0}')]][{1}]/td[contains(@data-title,'Division')]","DIVISION");

	public final Locator ORDER_CONTACT_HISTORY_ADJUSTMENT_UPDATE= new Locator("","//td[contains(text(),'{0}')]","Order Contact history");
	public final Locator Cancelled_HD_ORDERS = new Locator("", "//span[contains(text(),'Cancelled')]/parent::div/parent::div/parent::div/following-sibling::div[@class='row']//span[contains(text(),'Home Delivery')]", "Cancelled HD orders ");
	public final Locator CONTACT_HISTORY_NOTES = new Locator("CONTACT_HISTORY_NOTES", "//div[contains(text(),'{0}')]", "CONTACT_HISTORY_NOTES");


	public final Locator newxpath = new Locator("","//td[contains(text(),'Contact Customer')]/..//a[contains(text(),'Bad Order')]/parent::div/following-sibling::div","");

	//Error PopUp window
	public final Locator ERROR_POPUP = new Locator("Error popup","//div[@class='modal-header dialog-header-error ng-scope ng-isolate-scope']","Error popup");
	public final Locator ERROR_POPUP_CLOSE = new Locator("Error popup close","(//button[@id='modalclose'])","Error popup close");   
	public final Locator WARNING_POPUP = new Locator("Warning popup","//h4[@class='modal-title text-info ng-binding']","Warning popup");
	public final Locator WARNING_POPUP_CLOSE = new Locator("WARNING_POPUP close","//button[@id='modalclose']","WARNING_POPUP close");

	//scim code
	//public final Locator UPDATE_SCIM_CODE= new Locator("UPDATE_SCIM_CODE","//button[contains(text(),'Update SCIM Code')]","UPDATE_SCIM_CODE");
	//public final Locator UPDATE_SCIM_CODES= new Locator("UPDATE_SCIM_CODE","//button[contains(text(),'Update SCIM Codes')]","UPDATE_SCIM_CODE");
	//public final Locator DOS_ITEM_STATUS_COUNT= new Locator("DOS_ITEM_STATUS_COUNT","//td[contains(text(),'Open')]","DOS_ITEM_STATUS_COUNT");
	//public final Locator ITEM_DESCRIPTIONs= new Locator("ITEM_DESCRIPTIONs","//td[contains(text(),'Open')]/parent::tr/td[contains(@data-title,'Description')]","ITEM_DESCRIPTION");
	//public final Locator SCIM_CODEs= new Locator("SCIM_CODEs","//td[contains(text(),'Open')]/parent::tr/td[contains(@data-title,'Deluxe (SCIM)')]","SCIM_CODE");
	public final Locator ITEM_DESCRIPTION= new Locator("ITEM_DESCRIPTION","//tr[td[contains(text(),'{0}')]][{1}]/td[contains(@data-title,'Description')]","ITEM_DESCRIPTION");
	public final Locator SCIM_CODE= new Locator("SCIM_CODE","//tr[td[contains(text(),'{0}')]][{1}]/td[contains(@data-title,'Deluxe (SCIM)')]","SCIM_CODE");
	public final Locator CLICK_TO_UPDATE= new Locator("CLICK_TO_UPDATE","//th[contains(text(),'SCIM Code')]/ancestor::table/tbody[{0}]//td[9]","CLICK_TO_UPDATE");
	public final Locator SCIM_CODE_COUNT= new Locator("SCIM_CODE_COUNT","//th[contains(text(),'SCIM Code')]/ancestor::table/tbody[1]//td[9]/select/option","SCIM_CODE_COUNT");
	public final Locator SCIM_CODE_SELECTION= new Locator("SCIM_CODE_SELECTION","//th[contains(text(),'SCIM Code')]/ancestor::table/tbody[{0}]//td[9]/select/option[{1}]","SCIM_CODE_SELECTION");
	public final Locator EARLIEST_AVAILABLE_DATE= new Locator("EARLIEST_AVAILABLE_DATE","//div[contains(text(),'earliest available date')]","EARLIEST_AVAILABLE_DATE");

	//Recovery Service Windows
	public final Locator UNRESTRICTED_TIME_WINDOWS= new Locator("UNRESTRICTED_TIME_WINDOWS","//strong[contains(text(),'Unrestricted Time Window')]/parent::div/following-sibling::div[contains(@ng-repeat,'availableTimingForDate')][{0}]","UNRESTRICTED_TIME_WINDOWS");
	public final Locator UNRESTRICTED_TIME_WINDOWS_COUNT= new Locator("UNRESTRICTED_TIME_WINDOWS_COUNT","//div[contains(@ng-repeat,'availableTimingForDate')]//button","UNRESTRICTED_TIME_WINDOWS_COUNT");

	public final Locator PREFERRED_TIME_WINDOWS= new Locator("PREFERRED_TIME_WINDOWS","//strong[contains(text(),'Preferred Time Windows (must qualify)')]/parent::div/following-sibling::div[contains(@ng-repeat,'preferredWindowList')][{0}]","PREFERRED_TIME_WINDOWS");
	public final Locator PREFERRED_TIME_WINDOWS_COUNT= new Locator("PREFERRED_TIME_WINDOWS_COUNT","//div[contains(@ng-repeat,'preferredWindowList')]//button","PREFERRED_TIME_WINDOWS_COUNT");

	public final Locator PREFERRED_TIME_WINDOWS_NOT_AVAILABLE= new Locator("PREFERRED_TIME_WINDOWS_NOT_AVAILABLE","//div[contains(text(),'no preferred time windows available')]","PREFERRED_TIME_WINDOWS_NOT_AVAILABLE");

	public final Locator SERVICE_RECOVERY_WINDOW= new Locator("SERVICE_RECOVERY_WINDOW","//strong[contains(text(),'Service Recovery Windows')]/parent::div/following-sibling::div[contains(@ng-repeat,'recoveryWindowList')][{0}]//button","SERVICE_RECOVERY_WINDOW");
	public final Locator SERVICE_RECOVERY_WINDOW_COUNT= new Locator("SERVICE_RECOVERY_WINDOW_COUNT","//div[contains(@ng-repeat,'recoveryWindowList')]//button","SERVICE_RECOVERY_WINDOW_COUNT");

	public final Locator ORIGINAL_TIME_WINDOW= new Locator("ORIGINAL_TIME_WINDOW","//strong[@info='time_window']//ancestor::div[@label='Time Window']/div/p/span","ORIGINAL_TIME_WINDOW");
	public final Locator TIME_WINDOW_TYPE= new Locator("TIME_WINDOW_TYPE","//strong[text()='Time Window Type:']//ancestor::div[@label='Time Window Type']/div/p/span","TIME_WINDOW_TYPE");
	public final Locator CURRENT_TIME_WINDOW= new Locator("CURRENT_TIME_WINDOW","//strong[contains(text(),'Current Time Window')]/parent::div[contains(text(),'{0}')]","CURRENT_TIME_WINDOW");
	public final Locator PREFERRED_CONFIRMATION_TIME_WINDOW_POP_UP= new Locator("PREFERRED_CONFIRMATION_TIME_WINDOW_POP_UP","//div[contains(text(),'selected Preferred time window')]","PREFERRED_CONFIRMATION_TIME_WINDOW_POP_UP");
	public final Locator UNRESTRICTED_CONFIRMATION_TIME_WINDOW_POP_UP= new Locator("UNRESTRICTED_CONFIRMATION_TIME_WINDOW_POP_UP","//div[contains(text(),'Confirmation required')]","UNRESTRICTED_CONFIRMATION_TIME_WINDOW_POP_UP");
	public final Locator RECOVERY_CONFIRMATION_TIME_WINDOW_POP_UP= new Locator("RECOVERY_CONFIRMATION_TIME_WINDOW_POP_UP","//div[contains(text(),'selected Recovery time window')]","RECOVERY_CONFIRMATION_TIME_WINDOW_POP_UP");
	public final Locator RECOVERY_TIME_WINDOW_EXIST= new Locator("RECOVERY_TIME_WINDOW_EXIST","//div[contains(text(),'recovery time window exist')]","RECOVERY_TIME_WINDOW_EXIST");



	//public final Locator CONFIRMATION_TIME_WINDOW_POP_UP= new Locator("CONFIRMATION_TIME_WINDOW_POP_UP","//div[contains(text(),'selected Preferred time window')]","CONFIRMATION_TIME_WINDOW_POP_UP");

	public final Locator MONTH_AND_YEAR_PICKER= new Locator("MONTH_AND_YEAR_PICKER","//button[contains(@id,'datepicker')]","MONTH_AND_YEAR_PICKER");
	public final Locator SELECTED_DATE= new Locator("SELECTED_DATE","//strong[contains(text(),'Reschedule')]/ancestor::div[contains(@ng-if,'Reschedule Delivery')]/form/div[3]//div//div[3]/table/tbody/tr/td/button[contains(@class,'dt-selected')]","SELECTED_DATE");
	public final Locator BLUE= new Locator("BLUE","//span[contains(text(),'Selected')]/preceding-sibling::button","BLUE");
	public final Locator DELIVERY_REASON_DROPDOWN_OPTIONS= new Locator("DELIVERY_REASON_DROPDOWN_OPTIONS","//select[@name='reasonCode']/option[contain(text(),'{0}')]","DELIVERY_REASON_DROPDOWN_OPTIONS");
	public final Locator WRAP_UP= new Locator("WRAP UP ORDER & CONTACT","//button[text()='WRAP UP']","WRAP UP ORDER & CONTACT");

	public final Locator DAY_OF_DELIVERY_AGENT= new Locator("DAY_OF_DELIVERY_AGENT","//button[contains(text(),'{0}')]","DAY_OF_DELIVERY_AGENT");
	public final Locator DAY_OF_DELIVERY_RETURN_CODES_COUNT= new Locator("DAY_OF_DELIVERY_RETURN_CODES_COUNT","select[name='returnCodeSelect']>option","DAY_OF_DELIVERY_RETURN_CODES_COUNT",TagAttributes.CSSSELECTOR);
	public final Locator DAY_OF_DELIVERY_RETURN_CODES= new Locator("DAY_OF_DELIVERY_RETURN_CODES_COUNT","select[name='returnCodeSelect']>option[value='{0}']","DAY_OF_DELIVERY_RETURN_CODES",TagAttributes.CSSSELECTOR);
	public final Locator ARRIVAL_TIME= new Locator("ARRIVAL_TIME","//label[contains(text(),'Arrival Time')]/following-sibling::table//tr[3]/td[3]/a","ARRIVAL_TIME");
	public final Locator DEPARTURE_TIME= new Locator("DEPARTURE_TIME","//label[contains(text(),'Departure Time')]/following-sibling::table//tr[1]/td[3]/a","DEPARTURE_TIME");
	public final Locator STATUS_UPDATE= new Locator("STATUS_UPDATE","//button[contains(text(),'Complete Status Update')]","STATUS_UPDATE");
	public final Locator ORDER_ROUTE_STATUS= new Locator("ORDER_ROUTE_STATUS","//strong[contains(text(),'Route Status')]/parent::span/following-sibling::div/p","ORDER_ROUTE_STATUS");
	public final Locator CONTINUE_TO_RESCHDEULE= new Locator("CONTINUE_TO_RESCHDEULE","//button[contains(text(),'Continue to Reschedule')]","CONTINUE_TO_RESCHDEULE");

	//RFP
	public final Locator SUCCESS_MESSAGE_RFP = new Locator("","//div[contains(text(),'Ready for Pickup Email')]","Ready for Pickup Email Success Message");

	//layaway
	public final Locator LAYAWAY_CONTRACT_INFO=new Locator("","//legend[contains(text(),'Layaway Contract Information')]","LAYAWAY_CONTRACT_INFO");
	public final Locator CONTRACT_ID = new Locator("","//legend[contains(text(),'Layaway Contract Information')]/following-sibling::form/div[1]","CONTRACT_ID");
	public final Locator CONTRACT_STATUS = new Locator("","//legend[contains(text(),'Layaway Contract Information')]/following-sibling::form/div[2]","CONTRACT_STATUS");
	public final Locator ORDER_NUMBER = new Locator("","//legend[contains(text(),'Layaway Contract Information')]/following-sibling::form/div[3]","ORDER_NUMBER");
	public final Locator ORDER_TOTAL = new Locator("","//legend[contains(text(),'Layaway Contract Information')]/following-sibling::form/div[4]","ORDER_TOTAL");
	public final Locator FULLFIMENT_TYPE = new Locator("","//legend[contains(text(),'Layaway Contract Information')]/following-sibling::form/div[5]","FULLFIMENT_TYPE");
	public final Locator PERCENTAGE_COMPLETED = new Locator("","//legend[contains(text(),'Layaway Contract Information')]/following-sibling::form/div[6]","PERCENTAGE_COMPLETED");
	public final Locator PAYMENT_INFO = new Locator("","//legend[contains(text(),'Payment Information')]","PAYMENT_INFO");
	public final Locator MIN_PAYMENT_AMT = new Locator("","//legend[contains(text(),'Payment Information')]/following-sibling::div[1]","MIN_PAYMENT_AMT");
	public final Locator NEXT_PAYMENT_DATE = new Locator("","//legend[contains(text(),'Payment Information')]/following-sibling::div[2]","NEXT_PAYMENT_DATE");
	public final Locator UNPAID_AMT = new Locator("","//legend[contains(text(),'Payment Information')]/following-sibling::div[3]","UNPAID_AMT");
	public final Locator CONTRACT_TERMS_AND_CONDITION = new Locator("","//legend[contains(text(),'Contract Terms & Conditions')]","CONTRACT_TERMS_AND_CONDITION");
	public final Locator CONTRACT_START_DATE = new Locator("","//legend[contains(text(),'Contract Terms & Conditions')]/following-sibling::div[1]","CONTRACT_START_DATE");
	public final Locator END_DATE = new Locator("","//legend[contains(text(),'Contract Terms & Conditions')]/following-sibling::div[2]","END_DATE");
	public final Locator WEEKS_OF_DURATION = new Locator("","//legend[contains(text(),'Contract Terms & Conditions')]/following-sibling::div[3]","WEEKS_OF_DURATION");
	public final Locator LAST_PAYMENT_DATE = new Locator("","//legend[contains(text(),'Contract Terms & Conditions')]/following-sibling::div[4]","LAST_PAYMENT_DATE");
	public final Locator FUTURE = new Locator("","//legend[contains(text(),'Contract Terms & Conditions')]/following-sibling::div[5]","FUTURE");
	public final Locator DELIVERY_INFO = new Locator("","//legend[contains(text(),'Delivery Information')]/parent::fieldset","DELIVERY_INFO");
	
	public final Locator STORE_INFORMATION = new Locator("","//legend[contains(text(),'Store Information')]","STORE_INFORMATION");
	public final Locator ORIGIN_STORE = new Locator("","//strong[contains(text(),'Origin Store #:')]","ORIGIN_STORE");
	public final Locator ORIGIN_STORE_TYPE = new Locator("","//strong[contains(text(),'Origin Store Type:')]","ORIGIN_STORE_TYPE");
	public final Locator PICK_UP_LOC_STORE  = new Locator("","//strong[contains(text(),'Pick-Up Loc Store #:')]","PICK_UP_LOC_STORE");
	public final Locator ADDRESS = new Locator("","//strong[contains(text(),'Address:')]","ADDRESS");
	public final Locator CITY = new Locator("","//fieldset[legend[contains(text(),'Store Info')]]//strong[contains(text(),'City')]","CITY");
	public final Locator STATE = new Locator("","//fieldset[legend[contains(text(),'Store Info')]]//strong[contains(text(),'State:')]","STATE");
	public final Locator PHONE = new Locator("","//strong[contains(text(),'Phone:')]","PHONE");

	//return
	public final Locator RETURNED_STATUS = new Locator("","//td[@data-title-text='SKU']//a[contains(text(),'{0}')]/ancestor::tr/td[contains(text(),'Returned')]","RETURNED_STATUS");
	public final Locator RETURN_SUCCESS_MESSAGE = new Locator("","//div[contains(text(),'Return Item Action')]","RETURN_SUCCESS_MESSAGE");
	public final Locator SHIPPED_STATUS = new Locator("","//td[@data-title-text='SKU']//a[contains(text(),'{0}')]/ancestor::tr/td[contains(text(),'Shipped')]","SHIPPED_STATUS");
	public final Locator QUANTITY = new Locator("","//div[contains(text(),'Qty')]/ancestor::thead/following-sibling::tbody/tr[2]/td[@data-title-text='Qty']","QUANTITY");

	//DOD return
	public final Locator CONTINUE_TO_CANCEL_ORDER= new Locator("CONTINUE_TO_CANCEL_ORDER","//button[contains(text(),'Continue to Cancel Order')]","CONTINUE_TO_CANCEL_ORDER");

	//pick up mattress and exception
	public final Locator MATTRESS_TEXT= new Locator("MATTRESS_TEXT","//div[contains(text(),'bed frame, mattress, mattress foundation')]","MATTRESS_TEXT");
	public final Locator MATTRESS_DROPDOWN= new Locator("MATTRESS_DROPDOWN","//div[contains(text(),'bed frame, mattress, mattress foundation')]/following-sibling::select","MATTRESS_DROPDOWN");
	public final Locator MATRESS_DROPDOWN_TEXT= new Locator("MATRESS_DROPDOWN_TEXT","//div[contains(text(),'bed frame, mattress, mattress foundation')]/following-sibling::select/option[contains(text(),'Adjustable Bed Frame')]","MATRESS_DROPDOWN_TEXT");
	public final Locator RETURN_POLICY= new Locator("RETURN_POLICY","//div[contains(text(),'authorization from an Approver')]","RETURN_POLICY");
	public final Locator CLAIM_NUMBER_ENTRY= new Locator("CLAIM_NUMBER_ENTRY","//h3[contains(text(),'Claim Number Entry')]","CLAIM_NUMBER_ENTRY");
	public final Locator CLAIM_NUMBER= new Locator("CLAIM_NUMBER","//label[contains(text(),'Claim Number')]/following-sibling::input","CLAIM_NUMBER");
	public final Locator SUBMIT= new Locator("SUBMIT","//button[contains(text(),'Submit')]","SUBMIT");
	public final Locator PICK_UP_EXCEPTION= new Locator("PICK_UP_EXCEPTION","//h3[contains(text(),'Pick-Up Exception')]","PICK_UP_EXCEPTION");
	public final Locator EXCEPTION_USERNAME= new Locator("EXCEPTION_USERNAME","//label[contains(text(),'Username')]/following-sibling::input","EXCEPTION_USERNAME");
	public final Locator EXCEPTION_PASSWORD= new Locator("EXCEPTION_PASSWORD","//label[contains(text(),'Password')]/following-sibling::input","EXCEPTION_PASSWORD");
	public final Locator REASON_EXCEPTION= new Locator("REASON_EXCEPTION","//label[contains(text(),'Reason for exception')]/following-sibling::textarea","REASON_EXCEPTION");
	//public final Locator CONTINUE_TO_CANCEL_ORDER= new Locator("CONTINUE_TO_CANCEL_ORDER","//label[contains(text(),'Password')]/following-sibling::input","CONTINUE_TO_CANCEL_ORDER");

	public final Locator DELIVERY_NOTES_DATA1= new Locator("DELIVERY_NOTES_DATA","//td[contains(text(),'{0}') and contains(text(),'{1}')]","DELIVERY NOTES DATA");

	public final Locator PICK_UP_ITEMS_COUNT= new Locator("PICK_UP_ITEMS_COUNT","//td[contains(text(),'PICK-UP')]","PICK_UP_ITEMS_COUNT");
	public final Locator ITEM_DESCRIPTION_COUNT= new Locator("ITEM_DESCRIPTION_COUNT","//tr[td[contains(text(),'{0}')]]/td[contains(@data-title,'Division')]","ITEM_DESCRIPTION_COUNT");

	public final Locator DOD_RETURN_CODES_LINE_ITEM_COUNT= new Locator("DOD_RETURN_CODES_LINE_ITEM","(//select[@name='returnCodeSelect'])[{0}]/option","DOD_RETURN_CODES_LINE_ITEM");
	public final Locator DOD_RETURN_CODES_LINE_ITEM= new Locator("DOD_RETURN_CODES_LINE_ITEM","(//select[@name='returnCodeSelect'])[{0}]/option[{1}]","DOD_RETURN_CODES_LINE_ITEM");
	public final Locator UPDATE_DELIVERY_STATUS= new Locator("UPDATE_DELIVERY_STATUS","//button[contains(@ng-click,'submit') and contains(text(),'Update Delivery Status')]","UPDATE_DELIVERY_STATUS");
	public final Locator CONTINUE_TO_RETURN_EXCHANGE_ITEM= new Locator("CONTINUE_TO_RETURN_EXCHANGE_ITEM","//button[contains(text(),'Continue to Return/Exchange Item')]","CONTINUE_TO_RETURN_EXCHANGE_ITEM");

	public final Locator PICK_UP_ITEMS= new Locator("PICK_UP_ITEMS"," (//td[contains(text(),'PICK-UP')])[{0}]","PICK_UP_ITEMS");
	public final Locator ITEM_DESCRIPTION_WITHOUT_PICKUP= new Locator("ITEM_DESCRIPTION_WITHOUT_PICKUP","(//td[contains(@data-title,'Description') and not(contains(text(),'PICK-UP'))])[{0}]","ITEM_DESCRIPTION_WITHOUT_PICKUP");
	public final Locator ITEM_DESCRIPTION_WITHOUT_PICKUP_COUNT= new Locator("ITEM_DESCRIPTION_WITHOUT_PICKUP_COUNT","//td[contains(@data-title,'Description') and not(contains(text(),'PICK-UP'))]","ITEM_DESCRIPTION_WITHOUT_PICKUP_COUNT");

	public final Locator CONTACT_HISTORY_NOTES_DELIVERY= new Locator("CONTACT_HISTORY_NOTES_DELIVERY","//div[contains(text(),'{0}')]","CONTACT_HISTORY_NOTES_DELIVERY");
	public final Locator OFFER_CONSESSION_YES_BUTTON = new Locator("OFFER_CONSESSION_YES_BUTTON", "//div[starts-with(@question,'Was the')]//button[contains(text(),'Yes')]", "Offer Consession Yes Button");
	public final Locator MEMBER_CONSESSION_YES_BUTTON = new Locator("MEMBER_CONSESSION_YES_BUTTON", "//div[starts-with(@question,'Did the')]//button[contains(text(),'Yes')]", "Member Consession Yes Button");
	public final Locator MEMBER_CONSESSION_NO_BUTTON = new Locator("MEMBER_CONSESSION_NO_BUTTON", "//div[starts-with(@question,'Did the')]//button[contains(text(),'No')]", "Member Consession No Button");
	public final Locator SELECT_CONSESSION_TYPE = new Locator("SELECT_CONSESSION_TYPE", "(//*[contains(@ng-model,'selectedConcession')])[{0}]", "SELECT_CONSESSION_TYPE");

	public final Locator CONSESSION_AMOUNT = new Locator("CONSESSION_AMOUNT","(//input[@name='quantity'])[{0}]", "CONSESSION_AMOUNT");
	public final Locator CONSESSION_DELETE = new Locator("CONSESSION_DELETE", "(//button[contains(@ng-click,'removeConcession')])[{0}]", "CONSESSION_DELETE");
	public final Locator FINISH_BUTTON = new Locator("FINISH_BUTTON", "//button[contains(text(),'Finish')]", "FINISH_BUTTON");
	public final Locator ADD_CONCESSION = new Locator("ADD_CONCESSION", "(//*[contains(@ng-click,'addAdditionalConcession')])[{0}]", "ADD_CONCESSION");
	public final Locator DELETE_CONCESSION = new Locator("ADD_CONCESSION", "(//*[contains(@ng-click,'removeConcession')])[{0}]", "ADD_CONCESSION");
	public final Locator CONTACT_HISTORY_NOTESS = new Locator("CONTACT_HISTORY_NOTES", "//*[contains(text(),'{0}') and contains(text(),'{1}') and contains(text(),'{2}')]", "CONTACT_HISTORY_NOTES");
	public final Locator SCIM_CODE_LINE_ITEM = new Locator("SCIM_CODE_LINE_ITEM", "(//select[contains(@ng-model,'item.confirmationScimCode')])[{0}]", "SCIM_CODE_LINE_ITEM");
	public final Locator SCIM_CODE_REASON_CODE_COUNT = new Locator("SCIM_CODE_REASON_CODE_COUNT", "(//select[contains(@ng-model,'item.confirmationScimCode')])[{0}]/option", "SCIM_CODE_REASON_CODE_COUNT");
	public final Locator SCIM_CODE_REASON_CODE_SELECTION = new Locator("SCIM_CODE_REASON_CODE_SELECTION", "(//select[contains(@ng-model,'item.confirmationScimCode')])[{0}]/option[{1}]", "SCIM_CODE_REASON_CODE_SELECTION");
	public final Locator ITEM_DESCRIPTION_ACTION_CENTER = new Locator("ITEM_DESCRIPTION_ACTION_CENTER", "(//th[contains(text(),'Selected Item:')]/ancestor::table/tbody//td[1])[{0}]", "ITEM_DESCRIPTION_ACTION_CENTER");
	public final Locator ORDER_DETAIL_SCIM_CODE = new Locator("ORDER_DETAIL_SCIM_CODE", "//td[contains(text(),'{0}') and not(contains(text(),'PICK-UP'))]/parent::tr/td[contains(@data-title,'Deluxe (SCIM) Code') and contains(text(),'{1}')]", "ORDER_DETAIL_SCIM_CODE");

	public final Locator RERESERVE_NOT_ALLOWED = new Locator("RERESERVE_NOT_ALLOWED", "//div[contains(text(),'No item(s) eligible for Re-reserve action')]", "RERESERVE_NOT_ALLOWED");
	public final Locator AVAILABLE_QTY = new Locator("AVAILABLE_QTY", "(//input[@name='selectedQuantity']//ancestor::tr//td[8])", "AVAILABLE_QTY");
	public final Locator INVALID_QTY_ERR = new Locator("INVALID_QTY_ERR", "//span[contains(text(),'Invalid Quantity')]", "INVALID_QTY_ERR");
	public final Locator ORDER_STATUS_RELEASED = new Locator("ORDER_STATUS_RELEASED", "//div[@label='DOS Order Status']//div/p/span[contains(text(),'Released')]", "ORDER_STATUS_RELEASED");
	public final Locator ORDER_STATUS_SHIPPED = new Locator("ORDER_STATUS_SHIPPED", "//div[@label='DOS Order Status']//div/p/span[contains(text(),'Shipped')]", "ORDER_STATUS_SHIPPED");
	
	
	public final Locator CANCEL_ORDER_SUCCESS_MESSAGE = new Locator("CANCEL_ORDER_SUCCESS_MESSAGE", "//div[contains(text(),'Cancel Order Action has been successfully processed')]", "CANCEL_ORDER_SUCCESS_MESSAGE");
	public final Locator CANCEL_ORDER__SUCCESS_DIALOG_OK_BUTTON = new Locator("CANCEL_ORDER__SUCCESS_DIALOG_OK_BUTTON", "//button[contains(text(),'OK')]", "CANCEL_ORDER__SUCCESS_DIALOG_OK_BUTTON");
	public final Locator RELEASE_ORDER_SUCCESS_MESSAGE = new Locator("CANCEL_ORDER_SUCCESS_MESSAGE", "//div[contains(text(),'Release Order Action has been successfully processed')]", "CANCEL_ORDER_SUCCESS_MESSAGE");
	
	public final Locator SUGGESTED_ADDRESS_MSG = new Locator ("SUGGESTED_ADDRESS_MSG","(//div[contains(text(),'The address you entered could not be recognized.  You can either select one of the suggested address or select the address you entered.')])","SUGGESTED ADDRESS");
	public final Locator VERIFY_SUGGESTED_ADDRESS_DISPLAYED = new Locator ("VERIFY_SUGGESTED_ADDRESS_DISPLAYED","(//div/b[contains(text(),'{0}')])","VERIFY_SUGGESTED_ADDRESS_DISPLAYED");
	public final Locator FIRST_SUGGESTED_ADDRESS = new Locator ("FIRST_SUGGESTED_ADDRESS","(//div[@value='address']//b)[1]","FIRST_SUGGESTED_ADDRESS");
	
	public final Locator ITEM_DETAIL = new Locator("ITEM_DETAIL", "//td[contains(text(),'{0}')]/following-sibling::td[contains(text(),'{1}')]/following-sibling::td[contains(text(),'{2}')]", "ITEM_DETAIL");
	public final Locator PEND_CODE_ALERT = new Locator("PEND_CODE_ALERT", "//div[contains(text(),'{0}')]", "PEND_CODE_ALERT");
	public final Locator PEND_CODE_ALERT_NAME = new Locator("PEND_CODE_ALERT_NAME", "//h4[contains(text(),'Pended Order')]", "PEND_CODE_ALERT_NAME");

	
	
	Map<String, List<String>> map =new LinkedHashMap<>();



	DecimalFormat formatter = new DecimalFormat("#,##0.00");
	DecimalFormat df = new DecimalFormat("0.00");
	Connection conn1 = null;
	PreparedStatement st1 = null;
	ResultSet rs1 = null;
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");

	public enum OrderTab {
		ORDER_SUMMARY("Order Summary"),
		LINE_ITEM_SALES_CHECKS("Line Item/Sales Checks"),
		AUDIT_TRAIL("Audit Trail"),
		SYW_MAX("SYW Max");
		private String value;
		private OrderTab(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	Utility util = new Utility();


	public OrderDetailsPage CancelOrder(String OrderId) {
		CancelReasonCode crc = new CancelReasonCode();
		crc.CancelReasoncode = "Out of Stock";
		//		Logger.log("Verify Order details page of" + OrderId, TestStepType.STEP);
		//		AjaxCondition.forElementVisible(ORDERDETAILS_TAB.format(OrderId)).waitForResponse();
		//		Logger.log("Cancelling order" + OrderId, TestStepType.STEP);
		//		getAction().waitFor(2000);
		//		if(AjaxCondition.forElementVisible(ORDERDETAILS_ACTION_SELECT_BOX).waitWithoutException(1)) {
		//			Logger.log("Action :" + "Cancellation - Order", TestStepType.DATA_CAPTURE);
		//			getAction().selectByVisibleText(ORDERDETAILS_ACTION_SELECT_BOX, "Cancellation - Order");
		//			getAction().click(ORDERDETAILS_ACTION_GO_BUTTON);
		//		}
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(CANCELLATION_POP_UP.format("Cancel Order")).waitForResponse();
		Logger.log("Enter cancel details in cancel pop-up:", TestStepType.STEP);

		Logger.log("cancel reason code :" + crc.CancelReasoncode, TestStepType.DATA_CAPTURE);
		getAction().selectByVisibleText(CANCEL_ORDER_POP_UP_REASON_SELECT_BOX, crc.CancelReasoncode);
		getAction().waitFor(2000);

		Logger.log("send email :" + "yes", TestStepType.DATA_CAPTURE);
		getAction().selectByVisibleText(CANCEL_ORDER_POP_UP_REASON_SELECT_BOX.format("No"), "Out of Stock");
		getAction().waitFor(2000);

		Logger.log("select checkbox :" + "selected", TestStepType.DATA_CAPTURE);
		getAction().selectByVisibleText(CANCEL_ORDER_POP_UP_REASON_SELECT_BOX.format("No"), "Out of Stock");
		getAction().waitFor(2000);

		Logger.log("notes :" + "my note", TestStepType.DATA_CAPTURE);
		getAction().type(CANCEL_ORDER_POP_UP_NOTES_TEXT_FIELD, "my note");
		getAction().waitFor(2000);

		//AjaxCondition.forElementVisible(CANCEL_ORDER_POP_UP_SUBMIT_BUTTON).waitForResponse(10);	
		
		//getAction().waitFor(1000);
		Logger.log("Click Submit Button" , TestStepType.STEP);
		getAction().click(SUBMIT_BUTTON);
		getAction().waitFor(2000);
		
		AjaxCondition.forElementVisible(CANCEL_ORDER_SUCCESS_MESSAGE).waitForResponse(10);
		PageAssert.elementVisible(CANCEL_ORDER_SUCCESS_MESSAGE);
		Logger.log("Click 'OK' on pop up",TestStepType.STEP);
		getAction().click(CANCEL_ORDER__SUCCESS_DIALOG_OK_BUTTON);
		getAction().waitFor(5000);
		
		return this;
	}


	public OrderDetailsPage clickOnOrderTabInODP(OrderTab tabTitle){

		Logger.log("Click on "+tabTitle.getValue()+" Order Tab in Order Details Page",TestStepType.STEP);
		AjaxCondition.forElementVisible(ORDER_TAB_IN_ODP.format(tabTitle.getValue())).waitForResponse();
		getAction().click(ORDER_TAB_IN_ODP.format(tabTitle.getValue()));

		return this;
	}

	public OrderDetailsPage clickSYWMaxTabandVerify(String usertype){

		if(getAction().isVisible(SYW_MAX_TAB)){
			clickOnOrderTabInODP(OrderTab.SYW_MAX);
		}
		//get the text from the syw max tab
		getAction().waitFor(2000);
		Logger.log("Verify if the member is "+usertype);
		if(usertype.equalsIgnoreCase("active")){
			String text=getAction().getText(SYW_MAX_DETAIL_PAGE);

			PageAssert.textPresentIn(SYW_MAX_DETAIL_PAGE, "ACTIVE");
		}
		else if(usertype.equalsIgnoreCase("inactive")){
			String text=getAction().getText(NOT_A_SYW_MAX_MEMBER);
			PageAssert.textPresentIn(NOT_A_SYW_MAX_MEMBER, "Not a ShopYourWay Max member.");
		}
		return this;
	}

	public OrderDetailsPage verifySYWMaxSavingsAmount(String amount){
		getAction().waitFor(1000);
		String actualSavings ="";
		actualSavings = getAction().getText(SYW_MAX_SAVINGS);
		//double actAmount = Double.parseDouble(savings.replace('$','0'));
		System.out.println("amount"+amount+"           "+actualSavings);
		PageAssert.verifyTrue(actualSavings.contains(amount), "Verify the Max Savings are "+amount);
		return this;
	}

	public OrderDetailsPage verifyOrderDetailsPageDisplayed() {
		Logger.log("Verify if Order Details Page is displayed", TestStepType.VERIFICATION_STEP);
		getAction().waitFor(2000);
		if(AjaxCondition.forElementVisible(NO_RESULTS_FOUND).waitWithoutException(1)) 	
			Logger.log("Test Data is not valid");
		else
		{
			SoftAssert.checkElementAndContinueOnFailure(MEMBERDETAILS_IN_ODP, "Verify Member Details section is displayed", CheckLocatorFor.isVisible);
			SoftAssert.checkElementAndContinueOnFailure(MEMBERHISTORY_IN_ODP, "Verify if Member History is displayed", CheckLocatorFor.isVisible);
			SoftAssert.checkElementAndContinueOnFailure(AGENT_NOTES_IN_ODP, "Verify if Agent Notes is displayed", CheckLocatorFor.isVisible);
			//SoftAssert.checkElementAndContinueOnFailure(SEARCH_FOR_ANOTHER_ORDER_LINK, "Verify if Agent Notes is displayed", CheckLocatorFor.isVisible);

		}	
		return this;
	}

	public OrderDetailsPage verifyEmpowermentGuidelinePopUp(){
		Logger.log("Verify the Empower Guideline message is present in the Popup",TestStepType.STEP);
		AjaxCondition.forElementVisible(ORDER_SUMMARY_EMPOWERMENT_GUIDELINE_POPUP).waitWithoutException(3);
		String empowermentNotificationMessage = getAction().getAttribute(ORDER_SUMMARY_EMPOWERMENT_GUIDELINE_POPUP, "innerText");
		PageAssert.verifyTrue(empowermentNotificationMessage.contains("This order originates from one of our best members and therefore we want to do more for them. Select the Tool Tip next to the Empowerment Guideline to see what more can be done for them depending upon their reason for contact today. Ensure you thank them for being one of our best members."), "Improper Text present in Empowerment guideline popup"+empowermentNotificationMessage);
		Logger.log("Close Empower Guideline Popup",TestStepType.STEP);
		getAction().click(WARNING_POPUP_OK_BUTTON);
		getAction().waitFor(1000);
		return this;
	}

	public OrderDetailsPage verifyMarketplaceItemPopUp(){
		Logger.log("Verify the marketplace item message is present in the Popup",TestStepType.STEP);
		AjaxCondition.forElementVisible(ORDER_SUMMARY_MARKETPLACE_ITEM_POPUP).waitWithoutException(3);
		String marketPlaceItemNotificationMessage = getAction().getAttribute(ORDER_SUMMARY_MARKETPLACE_ITEM_POPUP, "innerText");
		System.out.println("Marketplace Notification Message-----------------------    "+marketPlaceItemNotificationMessage+"         --------------------------");
		PageAssert.verifyTrue(marketPlaceItemNotificationMessage.contains("ATTENTION - This order contains a MARKETPLACE (FBM) item!"), "Improper Text present in Marketplace Item popup"+marketPlaceItemNotificationMessage);
		Logger.log("Close marketplace item Popup",TestStepType.STEP);
		getAction().click(WARNING_POPUP_OK_BUTTON);
		getAction().waitFor(1000);
		return this;
	}

	public OrderDetailsPage verifyEmpowermentGuidelineStatusColor(String enabled){
		Logger.log("Verify the Empower Guideline status color is shown",TestStepType.STEP);
		AjaxCondition.forElementVisible(EMPOWERMENT_GUIDELINE_STATUS_COLOR).waitWithoutException(3);
		//String empowermentNotificationStatusColor = getAction().getAttribute(EMPOWERMENT_GUIDELINE_STATUS_COLOR,"style");
		String empowermentNotificationStatusColor =getAction().driver.findElement(getAction().getWebElement(EMPOWERMENT_GUIDELINE_STATUS_COLOR)).getCssValue("color");
		System.out.println("color retrieved------------------------------  "+ empowermentNotificationStatusColor);
		if (enabled.equalsIgnoreCase("enabled")){
			SoftAssert.checkConditionAndContinueOnFailure("Verify empowerment guideline status color is Green", empowermentNotificationStatusColor.equalsIgnoreCase("rgba(46, 204, 113, 1)"));
			//System.out.println("Result  ----          color ------------"+empowermentNotificationStatusColor.equalsIgnoreCase("rgba(46, 204, 113, 1)"));
		}
		else{
			SoftAssert.checkConditionAndContinueOnFailure("Verify empowerment guideline status color is Grey", empowermentNotificationStatusColor.equalsIgnoreCase("rgba(189, 195, 199, 1)"));
			//System.out.println("Result  ----          color ------------"+empowermentNotificationStatusColor.equalsIgnoreCase("rgba(189, 195, 199, 1)"));
		}
		return this;

	}

	public OrderDetailsPage verifyOrderDetailsDescription(String itemcondition,String rowNumber) {
		Logger.log("Verify if Order Details Page is displayed", TestStepType.VERIFICATION_STEP);
		getAction().waitFor(2000);
		if(AjaxCondition.forElementVisible(NO_RESULTS_FOUND).waitWithoutException(1)) 	
			Logger.log("Test Data is not valid");
		else
		{
			SoftAssert.checkElementAndContinueOnFailure(MEMBERDETAILS_IN_ODP, "Verify Member Details section is displayed", CheckLocatorFor.isVisible);

			String actualItemCondition=getAction().getText(DESCRIPTION_COL_NAME_TEXT.format(rowNumber));
			if(actualItemCondition.contains(itemcondition)){
				System.out.println("----------------------------------order level verification of item condition pass");
			}
			else
				System.out.println("-----------------------------------order level verification of item condition fail");
		}	
		return this;
	}

	public OrderDetailsPage verifyLayawayDetailsPageDisplayed() {
		Logger.log("Verify if Layaway Details Page is displayed", TestStepType.VERIFICATION_STEP);
		getAction().waitFor(2000);
		if(AjaxCondition.forElementVisible(NO_RESULTS_FOUND_LAYAWAY).waitWithoutException(1)) 	
			Logger.log("Test Data is not valid");
		else
		{
			AjaxCondition.forElementPresent(MEMBERDETAILS_IN_ODP).waitForResponse(3);
			SoftAssert.checkElementAndContinueOnFailure(MEMBERDETAILS_IN_ODP, "Verify Member Details section is displayed", CheckLocatorFor.isVisible);

			//sample test
			
			Locator[] loc={LAYAWAY_CONTRACT_INFO,PAYMENT_INFO,CONTRACT_TERMS_AND_CONDITION,LAYAWAY_INSTALLMENT_INFORMATION};
			Locator[] loc1={CONTRACT_ID,CONTRACT_STATUS,ORDER_NUMBER,ORDER_TOTAL,FULLFIMENT_TYPE,PERCENTAGE_COMPLETED};
			Locator[] loc2={MIN_PAYMENT_AMT,NEXT_PAYMENT_DATE,UNPAID_AMT};
			Locator[] loc3={CONTRACT_START_DATE, END_DATE, WEEKS_OF_DURATION, LAST_PAYMENT_DATE, FUTURE};
			//Locator[] loc4={ORIGIN_STORE, ORIGIN_STORE_TYPE, PICK_UP_LOC_STORE, ADDRESS, CITY,STATE,PHONE};
			
		
			
			
			
			for (int i = 0; i < loc.length; i++) {
				
				
				
				Logger.log( getAction().getText(loc[i]), TestStepType.STEP);
				SoftAssert.checkElementAndContinueOnFailure(loc[i], "Verify "+ getAction().getText(loc[i]) +" text is displayed", CheckLocatorFor.isVisible);
			
				switch (i) {
				case 0:
					for (int j = 0; j < loc1.length; j++) 
					SoftAssert.checkElementAndContinueOnFailure(loc1[j], "Verify "+ getAction().getText(loc1[j]) +" text is displayed", CheckLocatorFor.isVisible);
					break;
					
				case 1:
					for (int j = 0; j < loc2.length; j++) 
					SoftAssert.checkElementAndContinueOnFailure(loc2[j], "Verify "+ getAction().getText(loc2[j]) +" text is displayed", CheckLocatorFor.isVisible);
					break;
	
				case 2:
					for (int j = 0; j < loc3.length; j++) 
					SoftAssert.checkElementAndContinueOnFailure(loc3[j], "Verify "+ getAction().getText(loc3[j]) +" text is displayed", CheckLocatorFor.isVisible);
					break;
					
				/*case 3:
					for (int j = 0; j < loc4.length; j++) 
					SoftAssert.checkElementAndContinueOnFailure(loc4[j], "Verify "+ getAction().getText(loc4[j]) +" text is displayed", CheckLocatorFor.isVisible);
					break;
						*/
				default:
					break;
				}
				
				
			}
			
		}
		return this;
	}


	//For Delivery Flow - Verification for the DOS order details page
	public OrderDetailsPage verifySearchedDOSOrderIsDisplayed(String searchVal, String searchField) {
		
		if(getAction().isVisible(PEND_CODE_ALERT_NAME))
			verifyPendCodeAlertMsg();	
			
		Logger.log("Verify if Order Details Page is displayed", TestStepType.VERIFICATION_STEP);
		getAction().waitFor(2000);
		if(AjaxCondition.forElementVisible(NO_RESULTS_FOUND).waitWithoutException(1)) 	
			Logger.log("Test Data is not valid");
		else if (AjaxCondition.forElementVisible(DOS_ORDER_NO_LONGER_EXIST).waitWithoutException(5)) {
			Logger.log("Order no longer exist in DOS system.");
			System.out.println("------------------------------------ Order no longer exist in DOS system. ------------------------------------");
		}
		else
		{
			SoftAssert.checkElementAndContinueOnFailure(DOS_MEMBERDETAILS_IN_ODP, "Verify Member Details section is displayed", CheckLocatorFor.isVisible);
			SoftAssert.checkElementAndContinueOnFailure(DOS_ORDER_NO_LONGER_EXIST, "Verify Order Exists", CheckLocatorFor.isNotVisible);

			if(searchField=="dosorderId")
				PageAssert.verifyEqual(searchVal, getAction().getText(DOS_ORDER_SUMMARY_ORDER_NUMBER));

			if (searchField=="Order") {
				String[] str_array = searchVal.split("-");
				String dosorderId = str_array[0]; 
				String dosunitId = str_array[1];
				PageAssert.verifyEqual(dosorderId, getAction().getText(DOS_ORDER_SUMMARY_ORDER_NUMBER));
				if (!dosunitId.isEmpty()) {
					PageAssert.verifyEqual(dosunitId, getAction().getText(DOS_ORDER_SUMMARY_UNIT_NUMBER));
				}
			}

			if (searchField=="Salescheck") {
				PageAssert.verifyEqual(searchVal, getAction().getText(DOS_ORDER_SUMMARY_SALESCHECK_NUMBER));
			}

			if (searchField=="Date") {
				Logger.log("Search by date was successful", TestStepType.VERIFICATION_PASSED);
			}

			if (searchField=="Phone") {
				//strip off the hyphen and parenthesis from the phone number to get the numerical value
				String homephone = getAction().getText(DOS_ORDER_SUMMARY_HOME_PHONE).replaceAll("[()\\-\\s]", "");
				String workphone = getAction().getText(DOS_ORDER_SUMMARY_WORK_PHONE).replaceAll("[()\\-\\s]", "");
				String cellphone = getAction().getText(DOS_ORDER_SUMMARY_CELL_PHONE).replaceAll("[()\\-\\s]", "");
				//Check if any of the phone numbers matched with the searched phone
				if (searchVal.equals(homephone)){
					PageAssert.verifyEqual(searchVal, homephone);
				}
				else if (searchVal.equals(workphone)) {
					PageAssert.verifyEqual(searchVal, workphone);
				}
				else {
					PageAssert.verifyEqual(searchVal, cellphone);
				}
			}
			String orderRouteStatus=getAction().getText(ORDER_ROUTE_STATUS);
			getContext().put("orderRouteStatus", orderRouteStatus);
			System.out.println("orderRouteStatus----"+orderRouteStatus+"****");
		}	
		return this;
	}



	/**
	 * Delivery Flow - Verification to assert that pended orders don't have the 'UPDATE' button
	 */
	public OrderDetailsPage verifyUpdateOptionForPendedOrder(String searchVal, String searchField) {
		Logger.log("Verify if Order Details Page is displayed", TestStepType.VERIFICATION_STEP);
		getAction().waitFor(2000);
		if(AjaxCondition.forElementVisible(NO_RESULTS_FOUND).waitWithoutException(1)) 	
			Logger.log("Test Data is not valid");
		else
		{
			SoftAssert.checkElementAndContinueOnFailure(DOS_ORDER_NO_LONGER_EXIST, "Verify Order Exists", CheckLocatorFor.isNotVisible);

			//Check if the order is pended
			if (getAction().getText(DOS_ORDER_SUMMARY_PEND_CODE).isEmpty()) {
				Logger.log("Order is not pended - Invalid Order", TestStepType.VERIFICATION_FAILED);
			}
			else {
				//Check if the Update button is enabled
				AjaxCondition.forElementVisible(UPDATE_BUTTON).waitForResponse();
				if (getAction().findElement(UPDATE_BUTTON).isEnabled()) {
					Logger.log("Update button is enabled for pended orders", TestStepType.VERIFICATION_FAILED);
				}
				else {
					Logger.log("Update button is disabled for pended orders", TestStepType.VERIFICATION_PASSED);
				}	
			}
		}	
		return this;
	}


	public OrderDetailsPage verifyOptionIsVisible(String optionName){
		Logger.log("Verify "+optionName+" is Visible in DropDownList", TestStepType.VERIFICATION_SUBSTEP);
		AjaxCondition.forElementVisible(ORDERDETAILS_ACTION_SELECT_BOX).waitForResponse(5);
		PageAssert.textPresentIn(ORDERDETAILS_ACTION_SELECT_BOX, optionName);
		return this;

	}

	public OrderDetailsPage verifyOptionIsNotVisible(String optionName){
		Logger.log("Verify "+optionName+" is not visible in dropdown", TestStepType.VERIFICATION_SUBSTEP);
		AjaxCondition.forElementVisible(ORDERDETAILS_ACTION_SELECT_BOX).waitForResponse(5);
		PageAssert.textNotPresentIn(ORDERDETAILS_ACTION_SELECT_BOX, optionName);

		return this;

	}


	public OrderDetailsPage updateAndVerifyNameEmailNumber(){
		Logger.log("Updating name, email and home phone number ",TestStepType.STEP);

		Locator [] loc={UPDATE_NAME,UPDATE_ADDRESS,UPDATE_EMAIL,UPDATE_HOME_PHONE,UPDATE_WORK_PHONE};

		int i;
		String name="SEARS USER";
		String email="SEARS@AUTOMATION.COM";
		String homePhone="8087210247";
		String address="SEARS TOWER";
		String workPhone="4048210247";
		String name2="KMART USER";
		String email2="KMART@AUTOMATION.COM";
		String homephone2="8087210456";
		String workPhone2="4049210247";
		String address2="KMART T0WER";
		String data="";

		AjaxCondition.forElementVisible(UPDATE_BUTTON).waitForResponse();
		getAction().click(UPDATE_BUTTON);
		AjaxCondition.forElementVisible(UPDATE_NAME).waitForResponse();
		getAction().scrollTo(UPDATE_NAME);

		for( i=0;i<loc.length;i++){
			AjaxCondition.forElementVisible(loc[i]).waitForResponse();

			switch(i){
			case 0:
				getContext().put("originalName",getAction().getValue(loc[i]));
				data= getContext().get("originalName").toString().equalsIgnoreCase(name)?name2:name;	
				System.out.println("----------"+data);
				getContext().put("updatedName", data);
				break;
			case 1:
				getContext().put("orginalAddress",getAction().getValue(loc[i]));
				data=getContext().get("orginalAddress").toString().equalsIgnoreCase(address)?address2:address;
				getContext().put("updatedAddress", data);
				break;
			case 2:
				getContext().put("originalEmail",getAction().getValue(loc[i]));
				data=getContext().get("originalEmail").toString().equalsIgnoreCase(email)?email2:email;
				getContext().put("updatedEmail", data);
				break;
			case 3:
				getContext().put("originalHomePhone",getAction().getValue(loc[i]));
				data=getContext().get("originalHomePhone").toString().equalsIgnoreCase(homePhone)?homephone2:homePhone;	
				getContext().put("updatedHomePhone", data);
				break;
			case 4:
				getContext().put("originalWorkPhone",getAction().getValue(loc[i]));
				System.out.println(getContext().get("originalWorkPhone"));
				try{
					data=getContext().get("originaWorkPhone").toString().equalsIgnoreCase(workPhone)?workPhone2:workPhone;	
				}catch (NullPointerException e) {
					data=workPhone;
				}
				getContext().put("updatedWorkPhone", data);
				break;	
			}
			getAction().type(loc[i], data);
			getAction().waitFor(2000);
		}

		String updatedName= (String) getContext().get("updatedName");
		String updatedHomePhone= (String) getContext().get("updatedHomePhone");
		String updatedEmail= (String) getContext().get("updatedEmail");
		String updatedAddress= (String) getContext().get("updatedAddress");
		String updatedWorkPhone= (String) getContext().get("updatedWorkPhone");

		AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse();
		getAction().click(SAVE_BUTTON);
		if(AjaxCondition.forElementVisible(SUGGESTED_ADDRESS).waitWithoutException(5000)){
			AjaxCondition.forElementVisible(SUGGESTED_ADDRESS).waitForResponse();
			getAction().click(SUGGESTED_ADDRESS);
			AjaxCondition.forElementVisible(SUBMIT_BUTTON_ADDRESS).waitForResponse();
			getAction().click(SUBMIT_BUTTON_ADDRESS);
			AjaxCondition.forElementVisible(NOTATION_PAD).waitForResponse(3000);
			SoftAssert.checkConditionAndContinueOnFailure("updatedName: "+updatedName, getAction().getValue(UPDATE_NAME).equalsIgnoreCase(updatedName));
			SoftAssert.checkConditionAndContinueOnFailure("updatedAddress: "+updatedAddress, getAction().getValue(UPDATE_ADDRESS).equalsIgnoreCase(updatedAddress));
			SoftAssert.checkConditionAndContinueOnFailure("updatedEmail: "+updatedEmail, getAction().getValue(UPDATE_EMAIL).equalsIgnoreCase(updatedEmail));
			SoftAssert.checkConditionAndContinueOnFailure("updatedHomePhone: "+updatedHomePhone, getAction().getValue(UPDATE_HOME_PHONE).equalsIgnoreCase(updatedHomePhone));
			SoftAssert.checkConditionAndContinueOnFailure("updatedWorkPhone: "+updatedWorkPhone, getAction().getValue(UPDATE_WORK_PHONE).equalsIgnoreCase(updatedWorkPhone));

		}
		getContext().put("adjustmentOption", "");

		return this;
	}

	public OrderDetailsPage cancelSalesCheck() {
		CancelReasonCode crc = new CancelReasonCode();
		crc.CancelReasoncode = "Out of Stock";
		getAction().waitFor(2000);
		if (AjaxCondition.forElementVisible(ORDERDETAILS_ACTION_SELECT_BOX).waitWithoutException(1)) {
			Logger.log("Select cancellation - sales check option and click on go button", TestStepType.SUBSTEP);
			getAction().selectByVisibleText(ORDERDETAILS_ACTION_SELECT_BOX, "Cancellation - Sales Check");
			AjaxCondition.forElementVisible(SALECANCEL_ACTION_GO_BUTTON).waitForResponse();
			getAction().click(SALECANCEL_ACTION_GO_BUTTON);

		}
		Logger.log("Enter cancel details in cancel pop-up:", TestStepType.STEP);
		AjaxCondition.forElementVisible(CANCELLATION_POP_UP.format("Cancellation - Sales Check")).waitForResponse();


		Logger.log("Enter reason code :" + crc.CancelReasoncode, TestStepType.SUBSTEP);
		getAction().selectByVisibleText(CANCEL_ORDER_POP_UP_REASON_SELECT_BOX, crc.CancelReasoncode);
		getAction().waitFor(2000);


		Logger.log("send email :" + "yes", TestStepType.SUBSTEP);
		getAction().selectByVisibleText(CANCEL_ORDER_POP_UP_REASON_SELECT_BOX.format("No"), "Out of Stock");
		getAction().waitFor(2000);


		Logger.log("Enter notes :" + "my note", TestStepType.SUBSTEP);
		getAction().type(CANCEL_ORDER_POP_UP_NOTES_TEXT_FIELD, "This cancellation is performed by an automated test.");
		getAction().waitFor(2000);
		getContext().put("adjustmentOption", "This cancellation is performed by an automated test.");
		Logger.log("Verify the Submit button is present", TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(CANCEL_ORDER_POP_UP_SUBMIT_BUTTON).waitForResponse(10);

		try{
			Logger.log("Clicking the Submit Button",TestStepType.STEP);
			verifyTrialBalance();
		}catch(Exception e){
			e.printStackTrace();
			Logger.log("No trial Balance popup present, Proceeding further.",TestStepType.STEP);
			System.out.println("--------- No trial Balance popup present -------------");
		}		
		return this;

	}
	public OrderDetailsPage cancelLineItem() {
		CancelReasonCode crc = new CancelReasonCode();
		crc.CancelReasoncode = "Out of Stock";
		Logger.log("Cancel Line Item", TestStepType.STEP);
		AjaxCondition.forElementVisible(CANCELLATION_POP_UP.format("Cancel Line Item")).waitForResponse();
		Logger.log("Enter cancel details in cancel pop-up:", TestStepType.STEP);

		Logger.log("cancel reason code :" + crc.CancelReasoncode, TestStepType.DATA_CAPTURE);
		getAction().selectByVisibleText(CANCEL_ORDER_POP_UP_REASON_SELECT_BOX, crc.CancelReasoncode);
		getAction().waitFor(2000);


		Logger.log("send email :" + "yes", TestStepType.DATA_CAPTURE);
		getAction().selectByVisibleText(CANCEL_ORDER_POP_UP_REASON_SELECT_BOX.format("No"), "Out of Stock");
		getAction().waitFor(2000);

		Logger.log("select checkbox :" + "is selected", TestStepType.DATA_CAPTURE);
		getAction().click(CANCEL_ORDER_POP_UP_SELECTBOX);


		Logger.log("notes :" + "my note", TestStepType.DATA_CAPTURE);
		getAction().type(CANCEL_ORDER_POP_UP_NOTES_TEXT_FIELD, "This order is cancelled as a part of automated test.");
		getAction().waitFor(2000);
		getContext().put("adjustmentOption", "This order is cancelled as a part of automated test.");
		AjaxCondition.forElementVisible(CANCEL_ORDER_POP_UP_SUBMIT_BUTTON).waitForResponse(10);
		getAction().click(CANCEL_ORDER_POP_UP_SUBMIT_BUTTON);
		return this;

	}

	public OrderDetailsPage verifyActionsPresent(String level) {
		Logger.log("verify actions available in : "+level+" level",TestStepType.STEP);
		String[] actionType={};
		if(level.equalsIgnoreCase("order")){
			String[] orderactionTypes={"Contact Customer","Re-Send Order Confirmation","Sales Tax Adjustment","Shipping Adjustment"};
			actionType=orderactionTypes;
		}
		else if(level.equalsIgnoreCase("line item")){
			String[] lineactionTypes={"Sale Adjustment","Update Expected Ship/Arrival Date","Return Tracking Information","Contact Customer","Start Automated Return"};	
			actionType=lineactionTypes;
		}
		else{
			String[] salesactionTypes={"Contact Customer","Update Sales Check"};
			actionType=salesactionTypes;
		}

		AjaxCondition.forElementVisible(ACTION_DROPDOWN_OPTIONS).waitForResponse();
		int count=getAction().getVisibleElementCount(ACTION_DROPDOWN_OPTIONS);
		for (int i = 1; i < count; i++) {
			AjaxCondition.forElementVisible(ACTION_DROPDOWN_OPTIONS_COUNT.format(i)).waitForResponse();
			if(getAction().getText(ACTION_DROPDOWN_OPTIONS_COUNT.format(i)).equalsIgnoreCase(actionType[i-1])){
				continue;
			}
			else{
				PageAssert.fail(actionType[i]+" not available in actions");
			}

		}

		return this;

	}

	public OrderDetailsPage  verifyCurrentInteraction(String action) {

		Logger.log("verify interaction:"+action+" captured in current interaction",TestStepType.STEP);
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY_INTERACTION).waitForResponse();
		PageAssert.textPresentIn(ORDER_CONTACT_HISTORY_INTERACTION, action);


		return this;
	}


	public OrderDetailsPage  taxaddjustment(String adjusttaxOption, Double amount, String orderID) {
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Logger.log("Adjust sales tax",TestStepType.VERIFICATION_STEP);
		if (adjusttaxOption.equalsIgnoreCase("Sales Tax Adjustment")) {			
			AjaxCondition.forElementVisible(ADJUSTMENT_AMOUNT).waitForResponse();
			getAction().click(ADJUSTMENT_AMOUNT);
			getAction().type(ADJUSTMENT_AMOUNT, String.valueOf(amount));
			getAction().click(SELECTED_REASON_CODE);
			getAction().selectByVisibleText(SELECTED_REASON_CODE, "Wrong Tax Rate");
			getContext().put("adjustmentOption", "Wrong Tax Rate");
			AjaxCondition.forElementVisible(ADJUSTMENT_NOTES).waitForResponse();
			getAction().click(ADJUSTMENT_NOTES);
			getAction().type(ADJUSTMENT_NOTES,"Wrong Tax Rate");
			AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
			getAction().findElement(SUBMIT_BUTTON).isEnabled();		 
			Logger.log("Submit Adjust sales tax buttton is present and clickable",TestStepType.VERIFICATION_STEP);

		}

		else if (adjusttaxOption.equalsIgnoreCase("Shipping Adjustment")) {
			ArrayList<Double> shippingcharge = new ArrayList<Double>();
			ArrayList<Double> deliverycharge = new ArrayList<Double>();
			ArrayList<Double> installationcharge = new ArrayList<Double>();
			String general_charge_sql = "select o.SHIPPING_AMT from ord o where o.order_id  = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";
			String delivery_charge_sql = "select oi.LIST_PRICE from ord o, ord_item oi where o.order_id = oi.order_id and "
					+ "oi.ITEM_NM like 'HOME DELIVERY CHARGE' and LIST_PRICE > 0 and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

			String installation_sql = "select oi.ORDER_ITEM_TYP, oi.LIST_PRICE from ord o, ord_item oi where o.order_id = oi.order_id and oi.ITEM_NM like '%installation%' "
					+ "and oi.LIST_PRICE > 0 and o.order_id  = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$';";
			try {
				if(conn!=null){
					st = conn.prepareStatement(general_charge_sql);
					orderID = orderID.replaceAll("[^\\d.]", "");
					st.setString (1, orderID.trim());
					st.execute();
					rs = st.getResultSet();
					getAction().waitFor(1000);
					while(rs.next()){
						shippingcharge.add(rs.getDouble("SHIPPING_AMT"));
						System.out.println("Shipping Charge: "+ shippingcharge.size());}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if(conn!=null){
					st = conn.prepareStatement(delivery_charge_sql);
					orderID = orderID.replaceAll("[^\\d.]", "");
					st.setString (1, orderID.trim());
					st.execute();
					rs = st.getResultSet();
					getAction().waitFor(1000);
					while(rs.next()){
						deliverycharge.add(rs.getDouble("LIST_PRICE"));
						System.out.println("Delivery Charge: "+deliverycharge.size());}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {

				if(conn!=null){
					st = conn.prepareStatement(installation_sql);
					orderID = orderID.replaceAll("[^\\d.]", "");
					st.setString (1, orderID.trim());
					st.execute();
					rs = st.getResultSet();
					getAction().waitFor(1000);
					while(rs.next()){
						installationcharge.add(rs.getDouble("LIST_PRICE"));
						System.out.println("Installation Charge: "+installationcharge.size());}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(shippingcharge.size()!=0 || deliverycharge.size()!=0 || installationcharge.size()!=0){
				if(getAction().findElement(ITEM_SELECT_CHARGE.format(1)).isEnabled()){
					AjaxCondition.forElementVisible(ITEM_SELECT_CHARGE.format(1)).waitForResponse();
					getAction().click(ITEM_SELECT_CHARGE.format(1));
				}else if(getAction().findElement(ITEM_SELECT_CHARGE.format(2)).isEnabled()){
					AjaxCondition.forElementVisible(ITEM_SELECT_CHARGE.format(2)).waitForResponse();
					getAction().click(ITEM_SELECT_CHARGE.format(2));	
				}else if(getAction().findElement(ITEM_SELECT_CHARGE.format(3)).isEnabled()){
					AjaxCondition.forElementVisible(ITEM_SELECT_CHARGE.format(3)).waitForResponse();
					getAction().click(ITEM_SELECT_CHARGE.format(3));	
				}else{
					Logger.log("The Select Charge is not Clickable", TestStepType.STEP);
				}
				AjaxCondition.forElementVisible(ADJUSTMENT_AMOUNT_SHIP).waitForResponse();
				getAction().click(ADJUSTMENT_AMOUNT_SHIP);
				getAction().type(ADJUSTMENT_AMOUNT_SHIP, String.valueOf(amount));
				getAction().click(SELECTED_REASON_CODE);
				getAction().selectByVisibleText(SELECTED_REASON_CODE, "Customer Satisfaction");
				AjaxCondition.forElementVisible(ADJUSTMENT_NOTES).waitForResponse();
				getAction().click(ADJUSTMENT_NOTES);
				getAction().type(ADJUSTMENT_NOTES,"Customer Satisfaction");
				getContext().put("adjustmentOption", "Customer Satisfaction");
				AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
				getAction().findElement(SUBMIT_BUTTON).isEnabled();		 
				Logger.log("Submit Shipping Adjustment buttton is present and clickable",TestStepType.VERIFICATION_STEP);
				//Trial balance is no longer available
				//				verifyTrialBalance() ;
			}else{
				Logger.log("Shipping Adjustment is not available for this order",TestStepType.VERIFICATION_STEP);
			}
			try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		}

		else if (adjusttaxOption.equalsIgnoreCase("Sale Adjustment")) {

			Logger.log("Addjust sales tax",TestStepType.VERIFICATION_STEP);
			AjaxCondition.forElementVisible(ADJUSTMENT_AMOUNT).waitForResponse();
			getAction().click(ADJUSTMENT_AMOUNT);
			getAction().type(ADJUSTMENT_AMOUNT, String.valueOf(amount));
			getAction().click(SELECTED_REASON_CODE);
			{

				int resonecodeCount =getAction().getElementCount(RETURN_ITEM_RESON_CODE_COUNT);
				if(resonecodeCount==0)
					PageAssert.fail("Reason code drop down has no data to select");
				else
				{
					int ran = util.randomGenerator(resonecodeCount);
					if(ran==0)
						ran++;
					String resonName=getAction().getText(RETURN_ITEM_RESON_CODE.format(ran));
					Logger.log("Select Reson code "+resonName+" at index "+ran,TestStepType.STEP);
					AjaxCondition.forElementVisible(RETURN_ITEM_RESON_CODE.format(ran)).waitForResponse();
					getAction().click(SELECTED_REASON_CODE);
					getAction().waitFor(2000);
					getAction().click(RETURN_ITEM_RESON_CODE.format(ran));
					//	getAction().selectByVisibleText(RETURN_ITEM_RESON_CODE.format(ran), resonName);
					AjaxCondition.forElementVisible(ADJUSTMENT_NOTES).waitForResponse();
					getAction().click(ADJUSTMENT_NOTES);
					getAction().type(ADJUSTMENT_NOTES,resonName);
					getContext().put("adjustmentOption", resonName);
					AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
					getAction().findElement(SUBMIT_BUTTON).isEnabled();		 
					Logger.log("Submit Sales Adjustment buttton is present and clickable",TestStepType.VERIFICATION_STEP);
					//getAction().click(SUBMIT_BUTTON);
				}
			}

		}

		else 
		{
			if (AjaxCondition.forElementVisible(ITEM_NOT_CHARGED).waitForResponse());  
			PageAssert.fail("Tax adjustment is not applied");

		}			


		return this;
	}

	public OrderDetailsPage fillReturnItemPopUp(int quantity) {

		Logger.log("Verify if Return Item PopUp is Visible",TestStepType.VERIFICATION_STEP);
		//AjaxCondition.forElementVisible(RETURN_ITEM_POPUP).waitForResponse();

		Logger.log("Type Quantity in Return Quantity Field : "+quantity,TestStepType.STEP);
		AjaxCondition.forElementVisible(RETURN_QUANTITY).waitForResponse();
		getAction().type(RETURN_QUANTITY,String.valueOf(quantity));

		Logger.log("Select Reason Code",TestStepType.STEP);

		getAction().waitFor(3000);
		{

			int resonecodeCount =getAction().getElementCount(RETURN_ITEM_RESON_CODE_COUNT);
			if(resonecodeCount==0)
				PageAssert.fail("Reson code drop down has no data to select");
			else
			{
				int ran = util.randomGenerator(resonecodeCount);
				if(ran==0)
					ran++;
				String resonName=getAction().getText(RETURN_ITEM_RESON_CODE.format(ran));
				Logger.log("Select Reason code "+resonName+" at index "+ran,TestStepType.STEP);
				AjaxCondition.forElementVisible(RETURN_ITEM_RESON_CODE.format(ran)).waitForResponse();

				Logger.log("Reason is present in drop down",TestStepType.VERIFICATION_PASSED);
				getAction().click(RETURN_ITEM_RESON_CODE.format(ran));
				getAction().waitFor(3000);
				AjaxCondition.forElementVisible(ADJUSTMENT_NOTES).waitForResponse();
				getAction().type(ADJUSTMENT_NOTES,resonName);
				getContext().put("adjustmentOption", resonName);
			}

			Logger.log("Click on Submit Button",TestStepType.STEP);
			AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
		}
		return this;



	}

	public OrderDetailsPage clickOnSkuNumberUnderLineItemTab(int skuNumber){
		//getAction().waitFor(2000);

		clickOnOrderTabInODP(OrderTab.LINE_ITEM_SALES_CHECKS);
		Logger.log("Click on SKU in row "+skuNumber,TestStepType.STEP);
		getAction().waitFor(1000);

		AjaxCondition.forElementVisible(SKU_NUMBER_POSITION.format(skuNumber)).waitForResponse();
		getAction().click(SKU_NUMBER_POSITION.format(skuNumber));

		Logger.log("Verify if Line Item Detail Page is displayed",TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(LINE_ITEM_DETAIL_PAGE).waitForResponse();

		return this;
	}

	public OrderDetailsPage clickOnSkuNumberUnderLineItemTab(String sku){
		//getAction().waitFor(2000);
		AjaxCondition.forElementPresent(LINE_ITEM_SALES_CHECKS_TAB).waitForResponse(3000);
		getAction().click(LINE_ITEM_SALES_CHECKS_TAB);

		Logger.log("Click on SKU Number "+sku+" under the Line Item Tab",TestStepType.STEP);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(SKU_NUMBER.format(sku)).waitForResponse();
		getAction().waitFor(2000);
		getAction().focus(SKU_NUMBER.format(sku));
		getAction().click(SKU_NUMBER.format(sku));
		getAction().waitFor(1000);
		Logger.log("Verify if Line Item Detail Page is displayed",TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(LINE_ITEM_DETAIL_PAGE).waitForResponse();

		return this;
	}

	public OrderDetailsPage clickOnSalesCheckNumberUnderSalesCheckTab(int salesCheckNumber){		   
		getAction().waitFor(2000);
		Logger.log("Click on line item/sales check tab",TestStepType.STEP);
		AjaxCondition.forElementVisible(LINE_ITEM_SALES_CHECKS_TAB).waitForResponse();
		getAction().click(LINE_ITEM_SALES_CHECKS_TAB);
		Logger.log("Click on Sales Check in row "+salesCheckNumber,TestStepType.STEP);
		getAction().waitFor(1000);
		AjaxCondition.forElementVisible(SALES_CHECK_NUMBER.format(salesCheckNumber)).waitForResponse();	    	
		getAction().scrollTo(SALES_CHECK_NUMBER.format(salesCheckNumber));
		getAction().click(SALES_CHECK_NUMBER.format(salesCheckNumber));	    	
		Logger.log("Verify if Sales Check Detail Page is displayed",TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(SALES_CHECK_DETAIL_PAGE).waitForResponse();	    	
		return this;
	}

	public OrderDetailsPage clickOnSalesCheckNumberUnderSalesCheckTab(String scNO){
		getAction().waitFor(2000);
		if(AjaxCondition.forElementVisible(ORDER_SUMMARY_WARNING_POPUP).waitWithoutException(5)){
			Logger.log("Closing warning pop up",TestStepType.SUBSTEP);
			getAction().click(ORDER_SUMMARY_WARNING_POPUP_CLOSE);
		}
		Logger.log("Click on line item/sales check tab",TestStepType.STEP);
		AjaxCondition.forElementVisible(LINE_ITEM_SALES_CHECKS_TAB).waitForResponse();
		getAction().executeScript("arguments[0].click()", LINE_ITEM_SALES_CHECKS_TAB);
		getAction().click(LINE_ITEM_SALES_CHECKS_TAB);

		Logger.log("Click on Sales Check Number "+scNO+" under the Sales Check Tab",TestStepType.STEP);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(SALES_CHECK_NUMBER_COUNT).waitForResponse();
		getAction().scrollTo(SALES_CHECK_NUMBER_COUNT);
		int count=getAction().getElementCount(SALES_CHECK_NUMBER_COUNT);
		for(int i=0;i<count;i++){
			if(getAction().getText(SALES_CHECK_NUMBER.format(i+1)).contains(scNO)){
				getAction().executeScript("arguments[0].click()", SALES_CHECK_NUMBER.format(i+1));
				System.out.println("Click the "+(i+1)+" Sales Check Number");
			}
		}
		Logger.log("Verify if sales check details section is displayed",TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(LINE_ITEM_DETAIL_PAGE).waitForResponse();

		return this;
	}
	public OrderDetailsPage verifyEvenExchange(){

		String dosOrderNumber = getAction().getText(DELIVERYDETAILS_DOS_NUMBER);
		User user = User.find("Onlineuser1");

		Logger.log("Click on Even Exchange Button",TestStepType.STEP);
		try{
			getAction().scrollTo(EVEN_EXCHANGE_BUTTON);
			getAction().click(EVEN_EXCHANGE_BUTTON);
		}catch(Exception e){
			clickJ(EVEN_EXCHANGE_BUTTON);
		}

		Logger.log("Click on Line Item",TestStepType.STEP);
		AjaxCondition.forElementVisible(EVEN_EXCHANGE_ITEM.format("1")).waitForResponse(5);
		try {
			getAction().scrollTo(EVEN_EXCHANGE_ITEM.format("1"));
			getAction().click(EVEN_EXCHANGE_ITEM.format("1"));
		} catch (Exception e) {
			clickJ(EVEN_EXCHANGE_ITEM.format("1"));
		}
		Logger.log("Select the Reason for Pickup",TestStepType.STEP);
		AjaxCondition.forElementVisible(EVEN_EXCHANGE_ITEM_DROPDOWN.format("1")).waitForResponse(5);
		getAction().selectUsingIndex(EVEN_EXCHANGE_ITEM_DROPDOWN.format("1"), 3);
		Logger.log("Click on Continue",TestStepType.STEP);
		AjaxCondition.forElementVisible(LINE_ITEM_ROW_QUANTITY.format(1,1)).waitForResponse();
		getAction().type(LINE_ITEM_ROW_QUANTITY.format(1,1), getAction().getText(LINE_ITEM_ROW_QUANTITY_AVAILABLE_EVEN_EXCHANGE));
		getAction().waitFor(1000);


		if(getAction().isVisible(MATTRESS_TEXT)){
			getAction().click(MATTRESS_DROPDOWN);
			AjaxCondition.forElementPresent(MATRESS_DROPDOWN_TEXT).waitForResponse(2000);
			getAction().click(MATRESS_DROPDOWN_TEXT);
			getAction().click(CONTINUE_BUTTON);
		}
		getAction().waitFor(2000);
		if(getAction().isVisible(RETURN_POLICY)){
			Logger.log(getAction().getText(RETURN_POLICY),TestStepType.STEP);
			getAction().click(CONTINUE_BUTTON);
			AjaxCondition.forElementVisible(PICK_UP_EXCEPTION).waitForResponse(2000);
			getAction().type(EXCEPTION_USERNAME, user.userName);
			getAction().type(EXCEPTION_PASSWORD,user.password);
			getAction().type(REASON_EXCEPTION, "this is an automated test");
			getAction().waitFor(2000);
			getAction().click(SUBMIT);
		}

		getAction().waitFor(3000);


		AjaxCondition.forElementVisible(ACTION_CETNER_CONTINUE_BUTTON).waitForResponse(5);
		getAction().click(ACTION_CETNER_CONTINUE_BUTTON);
		Logger.log("Click 'No' on the Consession confirmation dialog",TestStepType.STEP);
		AjaxCondition.forElementVisible(OFFER_CONSESSION_NO_BUTTON).waitForResponse(5);
		getAction().click(OFFER_CONSESSION_NO_BUTTON);
		Logger.log("Select the Category Code",TestStepType.STEP);
		AjaxCondition.forElementVisible(EVEN_EXCHANGE_RETURN_CATEGORY_CODE_DROPDOWN.format("1")).waitForResponse(5);
		getAction().selectUsingIndex(EVEN_EXCHANGE_RETURN_CATEGORY_CODE_DROPDOWN.format("1"), 1);
		Logger.log("Select the Reason Code",TestStepType.STEP);
		AjaxCondition.forElementVisible(EVEN_EXCHANGE_RETURN_REASON_CODE_DROPDOWN.format("1")).waitForResponse(5);
		getAction().selectUsingIndex(EVEN_EXCHANGE_RETURN_REASON_CODE_DROPDOWN.format("1"),1);
		Logger.log("Click Create Order button",TestStepType.STEP);
		AjaxCondition.forElementVisible(EVEN_EXCHANGE_CREATE_ORDER_BUTTON).waitForResponse(5);
		getAction().click(EVEN_EXCHANGE_CREATE_ORDER_BUTTON);
		Logger.log("Verify the Success pop up is displayed",TestStepType.STEP);
		AjaxCondition.forElementVisible(EVEN_EXCHANGE_SUCCESS_MESSAGE).waitForResponse(10);
		PageAssert.elementVisible(EVEN_EXCHANGE_SUCCESS_MESSAGE);
		Logger.log("Click 'OK' on pop up",TestStepType.STEP);
		getAction().click(EVEN_EXCHANGE_SUCCESS_DIALOG_OK_BUTTON);
		getAction().waitFor(5000);
		String newDosOrderNumber = getAction().getText(DELIVERYDETAILS_DOS_NUMBER);
		System.out.println("New order created    "+newDosOrderNumber+" Old order "+dosOrderNumber);
		SoftAssert.checkTrue(!(dosOrderNumber.equals(newDosOrderNumber)), "New order is created for even exchange:-"+newDosOrderNumber);
		Logger.log("Verified that New Order status is Open", TestStepType.VERIFICATION_PASSED);

		AjaxCondition.forElementVisible(ORDER_STATUS_OPEN).waitForResponse();
		return this;
	}
	public OrderDetailsPage verifyEvenExchangeEntireOrder(String orderStatus){
		List<String> list= new ArrayList<String>();
		String orderRouteStatus= (String) getContext().get("orderRouteStatus");
		String dosOrderNumber = getAction().getText(DELIVERYDETAILS_DOS_NUMBER);  
		Logger.log("Click on Even Exchange Button",TestStepType.STEP);
		try{
			getAction().scrollTo(EVEN_EXCHANGE_BUTTON);
			getAction().click(EVEN_EXCHANGE_BUTTON);
		}catch(Exception e){
			clickJ(EVEN_EXCHANGE_BUTTON);
		}
		getAction().waitFor(3000);
		Logger.log("Click on Line Item",TestStepType.STEP);
		int num=getAction().getVisibleElementCount(EVEN_EXCHANGE_ITEM_TABLE);
		getContext().put("num", num);
		for(int i=1;i<=num;i++){
			AjaxCondition.forElementVisible(EVEN_EXCHANGE_ITEM.format(i)).waitForResponse(5);
			try {
				getAction().scrollTo(EVEN_EXCHANGE_ITEM.format(i));
				getAction().click(EVEN_EXCHANGE_ITEM.format(i));
			} catch (Exception e) {
				clickJ(EVEN_EXCHANGE_ITEM.format(i));
			}

			Logger.log("Select the Reason for Pickup",TestStepType.STEP);
			AjaxCondition.forElementVisible(EVEN_EXCHANGE_ITEM_DROPDOWN.format(i)).waitForResponse(5);
			getAction().selectUsingIndex(EVEN_EXCHANGE_ITEM_DROPDOWN.format(i), 3);

			Logger.log("Click on Continue",TestStepType.STEP);
			AjaxCondition.forElementVisible(LINE_ITEM_ROW_QUANTITY_COUNT.format(i)).waitForResponse();
			getAction().type(LINE_ITEM_ROW_QUANTITY_COUNT.format(i), getAction().getText(LINE_ITEM_ROW_QUANTITY_AVAILABLE_EVEN_EXCHANGE));
			getAction().waitFor(1000);
		}
		AjaxCondition.forElementVisible(ACTION_CETNER_CONTINUE_BUTTON).waitForResponse(5);
		getAction().click(ACTION_CETNER_CONTINUE_BUTTON);

		try {
			if(orderRouteStatus.equalsIgnoreCase("ON TIME")){
				getAction().waitFor(3000);
				verifyDayOfDelivery("Delivery Driver","Select Items");
				if("DELIVERY DRIVER".equalsIgnoreCase("Delivery Driver")){
					AjaxCondition.forElementVisible(CONTINUE_TO_RETURN_EXCHANGE_ITEM).waitForResponse();
					getAction().click(CONTINUE_TO_RETURN_EXCHANGE_ITEM);}}
		} catch (NullPointerException e ) {
			orderRouteStatus="";
		}

		if(orderStatus.equalsIgnoreCase("Shipped")||orderStatus.equalsIgnoreCase("Partially Shipped")||orderStatus.equalsIgnoreCase("Released")){
			Logger.log("Click 'No' on the Consession confirmation dialog",TestStepType.STEP);
			AjaxCondition.forElementVisible(OFFER_CONSESSION_NO_BUTTON).waitForResponse(5);
			getAction().click(OFFER_CONSESSION_NO_BUTTON);}

		Logger.log("Select the Category Code",TestStepType.STEP);
		for(int i=1;i<=num;i++){
			AjaxCondition.forElementVisible(CATEGORY_CODE_DROPDOWN.format(i)).waitForResponse();
			getAction().click(CATEGORY_CODE_DROPDOWN.format(i));
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(CATEGORY_CODE_DROPDOWN_OPTION.format(i)).waitForResponse();
			getAction().click(CATEGORY_CODE_DROPDOWN_OPTION.format(i));
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(PICKUP_REASON_CODE_DROPDOWN.format(i)).waitForResponse();
			getAction().click(PICKUP_REASON_CODE_DROPDOWN.format(i));
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(REASON_CODE_DROPDOWN_OPTION.format(i)).waitForResponse();
			getAction().click(REASON_CODE_DROPDOWN_OPTION.format(i));

			//trial code for the scim code
			AjaxCondition.forElementPresent(SCIM_CODE_LINE_ITEM.format(i)).waitForResponse();
			int rndCodeCategory = generateRandomNumberSelect(SCIM_CODE_REASON_CODE_COUNT.format(i));
			//AjaxCondition.forElementPresent(SCIM_CODE_SELECTION.format(i,rndCodeCategory)).waitForResponse();
			System.out.println(getAction().getText(SCIM_CODE_REASON_CODE_SELECTION.format(i,rndCodeCategory)));
			System.out.println("item:scim code--"+getAction().getText(ITEM_DESCRIPTION_ACTION_CENTER.format(i))+":"+getAction().getText(SCIM_CODE_REASON_CODE_SELECTION.format(i,rndCodeCategory)));
			list.add(getAction().getText(ITEM_DESCRIPTION_ACTION_CENTER.format(i))+":"+getAction().getText(SCIM_CODE_REASON_CODE_SELECTION.format(i,rndCodeCategory)));
			getContext().put("list", list);
			getAction().click(SCIM_CODE_REASON_CODE_SELECTION.format(i,rndCodeCategory));
			getAction().waitFor(1000);
		}
		Logger.log("Click Create Order button",TestStepType.STEP);
		AjaxCondition.forElementVisible(EVEN_EXCHANGE_CREATE_ORDER_BUTTON).waitForResponse(5);
		getAction().click(EVEN_EXCHANGE_CREATE_ORDER_BUTTON);
		Logger.log("Verify the Success pop up is displayed",TestStepType.STEP);
		AjaxCondition.forElementVisible(EVEN_EXCHANGE_SUCCESS_MESSAGE).waitForResponse(10);
		PageAssert.elementVisible(EVEN_EXCHANGE_SUCCESS_MESSAGE);
		Logger.log("Click 'OK' on pop up",TestStepType.STEP);
		getAction().click(EVEN_EXCHANGE_SUCCESS_DIALOG_OK_BUTTON);
		getAction().waitFor(5000);
		String newDosOrderNumber = getAction().getText(DELIVERYDETAILS_DOS_NUMBER);
		System.out.println("New order created    "+newDosOrderNumber+" Old order "+dosOrderNumber);
		SoftAssert.checkTrue(!(dosOrderNumber.equals(newDosOrderNumber)), "New order is created for even exchange:-"+newDosOrderNumber);
		Logger.log("Verified that New Order status is Open", TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(ORDER_STATUS_OPEN).waitForResponse();
		verifyPickUpItemCreated();
		verifylineItemScimCode();

		return this;
	}
	public OrderDetailsPage verifyEvenExchangeNotAllowed(String action){
		String dosOrderNumber = getAction().getText(DELIVERYDETAILS_DOS_NUMBER);  
		Logger.log("Click on "+action+ " Button",TestStepType.STEP);
		switch (action) {
		case "Even Exchange":
			try{
				getAction().scrollTo(EVEN_EXCHANGE_BUTTON);
				getAction().click(EVEN_EXCHANGE_BUTTON);
			}catch(Exception e){
				clickJ(EVEN_EXCHANGE_BUTTON);
			}
			getAction().waitFor(3000);
			Logger.log("Verify None of the items are eligible for Even Exchange", TestStepType.STEP);
			PageAssert.elementVisible(EVEN_EXCHANGE_NO_ITEMS_ALLOWED);
			break;

		case "Rereserve":
			Logger.log("Click on Re-reserve Button",TestStepType.STEP);
			try{
				getAction().scrollTo(RERESERVE_BUTTON);
				getAction().click(RERESERVE_BUTTON);
			}catch(Exception e){
				clickJ(RERESERVE_BUTTON);
			}
			getAction().waitFor(3000);

			Logger.log("No item(s) eligible for Re-reserve action", TestStepType.STEP);
			PageAssert.elementVisible(RERESERVE_NOT_ALLOWED);
			break;
		default:
			break;
		}

		return this;
	}

	public OrderDetailsPage verifyEvenExchangeEligibility(String orderType){
		if(orderType.equalsIgnoreCase("Open")){
			Logger.log("Verify Even Exchange Button is not avalable in Action Center" , TestStepType.STEP);
			PageAssert.elementNotVisible(EVEN_EXCHANGE_BUTTON);
		}else{
			Logger.log("Verify Even Exchange Button is Present in Action Center" , TestStepType.STEP);
			PageAssert.elementVisible(EVEN_EXCHANGE_BUTTON);
		}
		return this;
	}
	public OrderDetailsPage verifyReleaseOrder() {

		getAction().waitFor(2000);
		Logger.log("Verify Release Order Popup" , TestStepType.STEP);
		AjaxCondition.forElementVisible(RELEASE_ORDER_POPUP).waitForResponse();
		Logger.log("Enter text in notes field" , TestStepType.SUBSTEP);;
		AjaxCondition.forElementVisible(RELEASE_ORDER_POPUP_NOTES_TEXT_FIELD).waitForResponse();
		getAction().type(RELEASE_ORDER_POPUP_NOTES_TEXT_FIELD,"MSP automation release order");
		Logger.log("Verify Release Order 'Submit' Button is visible" , TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
		
		Logger.log("Click Submit Button" , TestStepType.STEP);
		getAction().click(SUBMIT_BUTTON);
		getAction().waitFor(2000);
		
		AjaxCondition.forElementVisible(RELEASE_ORDER_SUCCESS_MESSAGE).waitForResponse(10);
		PageAssert.elementVisible(RELEASE_ORDER_SUCCESS_MESSAGE);
		Logger.log("Click 'OK' on pop up",TestStepType.STEP);
		getAction().click(CANCEL_ORDER__SUCCESS_DIALOG_OK_BUTTON);
		getAction().waitFor(5000);
		return this;
	}   

	public OrderDetailsPage re_Send_Order_Confirmation() {
		getAction().waitFor(1000);
		Logger.log("Verify resend order confirmation popup is displayed",TestStepType.STEP);
		AjaxCondition.forElementVisible(RESEND_ORDER_CONFIRMATION_POPUP).waitForResponse();
		Logger.log("Enter text in notes field" , TestStepType.SUBSTEP);  
		AjaxCondition.forElementVisible(RESEND_ORDER_CONFIRMATION_POPUP_NOTES_TEXTFIELD ).waitForResponse();
		getAction().type(RESEND_ORDER_CONFIRMATION_POPUP_NOTES_TEXTFIELD ,"MSP automation re-send order confirmation notes");
		getContext().put("adjustmentOption", "MSP automation re-send order confirmation notes");
		Logger.log("Click Re-Send Order Confirmation 'Submit' button" , TestStepType.STEP);;
		AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
		getAction().click(SUBMIT_BUTTON);
		return this;
	}   

	public OrderDetailsPage verifyTrialBalance() {
		getAction().waitFor(1000);
		Logger.log("Click Submit Button" , TestStepType.STEP);
		getAction().click(SUBMIT_BUTTON);
		getAction().waitFor(2000);
		Logger.log("Verify Trail Balance Popup" , TestStepType.STEP);
		AjaxCondition.forElementVisible(TRIAL_BALANCE_POPUP).waitForResponse(5);
		Logger.log("Verify trail balance payment type", TestStepType.STEP);
		AjaxCondition.forElementVisible(TRIAL_BALANCE_POPUP_PAYMENTTYPE).waitForResponse();
		Logger.log("Verify trail balance popup message", TestStepType.STEP);
		AjaxCondition.forElementVisible(TRIAL_BALANCE_POPUP_MESSAGE).waitForResponse();
		Logger.log("Verify trail balance cancel button", TestStepType.STEP);
		AjaxCondition.forElementVisible(TRIAL_BALANCE_POPUP_CANCEL_BUTTON).waitForResponse();
		Logger.log("Verify trail balance submit button", TestStepType.STEP);
		AjaxCondition.forElementVisible(TRIAL_BALANCE_POPUP_SUBMIT_BUTTON).waitForResponse();
		getAction().click(TRIAL_BALANCE_POPUP_SUBMIT_BUTTON);
		getAction().waitFor(3000);
		if(getAction().isVisible(TRIAL_BALANCE_POPUP_SUBMIT_BUTTON)){
			Logger.log("Clicking on the Submit button again");
			getAction().click(TRIAL_BALANCE_POPUP_SUBMIT_BUTTON);
		}
		AjaxCondition.forElementVisible(TRIAL_BALANCE_OK).waitForResponse(3000);
		getAction().click(TRIAL_BALANCE_OK);
		return this;
	}   


	public OrderDetailsPage   saleAdjustmentLineitem(String adjusttaxOption, Double amount){
		Logger.log("Verify Sale adjustment for a line item",TestStepType.VERIFICATION_STEP);
		getAction().waitFor(2000);
		Logger.log("Enter adjustment amount -"+amount,TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(ADJUSTMENT_AMOUNT).waitForResponse();
		getAction().click(ADJUSTMENT_AMOUNT);
		getAction().type(ADJUSTMENT_AMOUNT, String.valueOf(amount));

		Logger.log("Click on reason code dropdown"+amount,TestStepType.SUBSTEP);
		getAction().click(SELECTED_REASON_CODE);
		int reasonCodeCount =getAction().getElementCount(RETURN_ITEM_RESON_CODE_COUNT);
		if(reasonCodeCount==0)
			PageAssert.fail("Reason code drop down has no data to select");
		else
		{
			int ran = util.randomGenerator(reasonCodeCount);
			if(ran==0)
				ran++;
			Logger.log("Select one reason code from dropdown in random ",TestStepType.STEP);
			String reasonName=getAction().getText(RETURN_ITEM_RESON_CODE.format(ran));
			Logger.log("Select reason code "+reasonName,TestStepType.STEP);
			AjaxCondition.forElementVisible(RETURN_ITEM_RESON_CODE.format(ran)).waitForResponse();
			getAction().click(SELECTED_REASON_CODE);
			getAction().waitFor(1000);
			Logger.log("Select return reason code ",TestStepType.STEP);
			getAction().click(RETURN_ITEM_RESON_CODE.format(ran));
			//	getAction().selectByVisibleText(RETURN_ITEM_RESON_CODE.format(ran), resonName);
			Logger.log("Enter data in notes field ",TestStepType.STEP);
			AjaxCondition.forElementVisible(ADJUSTMENT_NOTES).waitForResponse();
			getAction().click(ADJUSTMENT_NOTES);
			getAction().type(ADJUSTMENT_NOTES,reasonName);
			Logger.log("Click on submit button",TestStepType.STEP);
			AjaxCondition.forElementVisible(SUBMIT_BUTTON).waitForResponse();
			getAction().click(SUBMIT_BUTTON);

		}
		return this;
	}

	public OrderDetailsPage verifyStartAutomatedReturnEligible() {

		Logger.log("Verify Start Automated return Eligible", TestStepType.STEP);
		getAction().waitFor(2000);
		StoringCurrentWindowHandle();
		SwitchToNewlyOpenedWindow();
		Logger.log("Verify Order Lookup Page displayed", TestStepType.VERIFICATION_RESULT);
		//AjaxCondition.forElementVisible(ORDER_NUMBER_INPUT).waitForResponse();
		//verifyTitle("Check Order Status");

		getAction().driver.close();
		SwitchBack();
		/*Logger.log("Click on Child SKU Number under the Line Item Tab",TestStepType.STEP);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(SKU_WARRANTY).waitForResponse();
		getAction().waitFor(2000);
		getAction().focus(SKU_WARRANTY);
		getAction().click(SKU_WARRANTY);
		getAction().waitFor(1000);
		Logger.log("Verify if Line Item Detail Page is displayed",TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(LINE_ITEM_DETAIL_PAGE).waitForResponse();

		this.factory.lineItemDetailsAction().verifyOptionVisible("Start Automated Return");

		verifyStartAutomatedReturnNonEligible();*/
		return this;
	}
	public OrderDetailsPage verifyStartAutomatedReturnNonEligible() {

		Logger.log("Verify Start Automated return Non Eligible", TestStepType.STEP);
		getAction().waitFor(2000);
		SwitchToNewlyOpenedWindow();
		Logger.log("Verify Return Policy Page displayed", TestStepType.VERIFICATION_RESULT);
		AjaxCondition.forElementNotVisible(ORDER_NUMBER_INPUT).waitForResponse();
		verifyTitle("How to Return or Cancel");
		return this;
	}


	public OrderDetailsPage verifyReadyPickupEmail(){
		getAction().waitFor(1000);
		Logger.log("Verify ready for pickup mail",TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(CUSTOMER_EMAIL_ADDRESS).waitForResponse();
		String Email = getAction().getText(CUSTOMER_EMAIL_ADDRESS).trim();
		if (AjaxCondition.forElementVisible(ORDERDETAILS_ACTION_SELECT_BOX).waitWithoutException(1)) {
			Logger.log("Select Ready for Pickup Email action", TestStepType.DATA_CAPTURE);
			getAction().selectByVisibleText(ORDERDETAILS_ACTION_SELECT_BOX, "Ready for Pickup Email");
			AjaxCondition.forElementVisible(SALECANCEL_ACTION_GO_BUTTON).waitForResponse();
			getAction().click(SALECANCEL_ACTION_GO_BUTTON);

		}

		Logger.log("Verify Ready for Pickup Email Pop-up Window is visible", TestStepType.STEP);
		AjaxCondition.forElementVisible(READY_FOR_PICKUP_EMAIL_POP_UP.format("Send Ready for Pickup Email")).waitForResponse();
		AjaxCondition.forElementVisible(READY_FOR_PICKUP_EMAIL).waitForResponse();
		getAction().waitFor(2000);
		getAction().click(READY_FOR_PICKUP_EMAIL);

		Logger.log("Assert that email in ready for pickup mail is same as customer email ID",TestStepType.VERIFICATION_STEP);
		PageAssert.verifyEqual(getAction().getValue(READY_FOR_PICKUP_EMAIL), Email);
		Logger.log("writing general email");
		getAction().type(READY_FOR_PICKUP_EMAIL, "protest@automation.com");


		Logger.log("Enter E-mail notes in Ready for Pickup Email Pop-up Window", TestStepType.STEP);
		String notes="Test Notes For Ready For Pickup Email";
		AjaxCondition.forElementVisible(READY_FOR_PICKUP_EMAIL_NOTES).waitForResponse();
		getAction().type(READY_FOR_PICKUP_EMAIL_NOTES, notes);
		getContext().put("adjustmentOption", notes);

		Logger.log("Verify Submit Button is Visible in Ready for Pickup Email Pop-up Window", TestStepType.STEP);
		AjaxCondition.forElementVisible(READY_FOR_PICKUP_EMAIL_SUBMIT).waitForResponse(3);
		getAction().click(READY_FOR_PICKUP_EMAIL_SUBMIT);

		AjaxCondition.forElementVisible(SUCCESS_MESSAGE_RFP).waitForResponse(3000);
		Logger.log("verifying success message");
		String expSucessMsg="Send Ready for Pickup Email Action has been successfully processed";
		SoftAssert.checkConditionAndContinueOnFailure("Send Ready for Pickup Email Action has been successfully processed", getAction().getText(SUCCESS_MESSAGE_RFP).equals(expSucessMsg));
		getAction().click(OK_BUTTON);

		Logger.log("verifying contact history");
	//	AjaxCondition.forElementPresent(OK_BUTTON).waitForResponse(3000);
		return this;

	}


	public OrderDetailsPage closeMarketplaceWarningPopUp(){

		Logger.log("Click on \'Close\' button in Marketplace Warning Popup",TestStepType.STEP);
		AjaxCondition.forElementPresent(MARKETPLACE_WARNING_POPUP).waitForResponse();
		AjaxCondition.forElementVisible(MARKETPLACE_WARNING_POPUP_CONTENT).waitForResponse();
		AjaxCondition.forElementVisible(MARKETPLACE_WARNING_POPUP_CLOSE_BUTTON).waitForResponse();
		getAction().click(MARKETPLACE_WARNING_POPUP_CLOSE_BUTTON);

		return this;
	}

	public OrderDetailsPage verifylineitemdetails()  {

		Logger.log("Verify line item Details Page", TestStepType.VERIFICATION_SUBSTEP);

		AjaxCondition.forElementVisible(LINEITEMDETAILSTABLE).waitForResponse();

		Logger.log("Verify if line item summary displayed in Line item Details Page", TestStepType.VERIFICATION_SUBSTEP);

		AjaxCondition.forElementVisible(LINEITEMDETAILSUMMARY).waitForResponse();

		Logger.log("Verify if line item information is displayed in Line item Details Page", TestStepType.VERIFICATION_SUBSTEP);

		AjaxCondition.forElementVisible(LINEITEMDETAILINFORMATION).waitForResponse();

		return this;
	}
	public OrderDetailsPage verifysalescheckdetails()  {
		Logger.log("Verify sales check Details Page", TestStepType.VERIFICATION_SUBSTEP);
		AjaxCondition.forElementVisible(SALESCHECKDETAILS).waitForResponse();
		Logger.log("Verify if sales check summary displayed in sales check Details Page", TestStepType.VERIFICATION_SUBSTEP);
		AjaxCondition.forElementVisible(SALESCHECKSUMMARY).waitForResponse();
		Logger.log("Verify if sales check shipping information displayed in sales check Details Page", TestStepType.VERIFICATION_SUBSTEP);
		AjaxCondition.forElementVisible(SALESCHECKSHIPPINGADDRESS).waitForResponse();
		return this;
	}


	public OrderDetailsPage customerInfoVerify(String OrderID) throws SQLException{
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		String customerName = null;
		String customerAddress = null;
		String phoneNumber = null ;
		String email_Address = null;
		String sql ="select cci.FIRST_NM, cci.LAST_NM, cci.ADDR_LINE_1, cci.ADDR_LINE_2, cci.ADDR_LINE_3, cci.CITY, "
				+ "cci.STATE_CD, cci.ZIP_CD, cci.PHONE_1, cci.EMAIL_ADDR_1 "
				+ "from ord o, customer_contact_info cci "
				+ "where o. BILLING_ADDRESS_ID = cci.ADDRESS_ID and o.site_gen_ord_id =?";
		getAction().waitFor(1000);
		try {
			if(conn!=null){
				st = conn.prepareStatement(sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				getAction().waitFor(2000);
				st.setString (1,OrderID.trim() );	            
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(3000);
				while(rs.next()){
					customerName = rs.getString("FIRST_NM") + " "+rs.getString("LAST_NM");
					if(rs.getString("ADDR_LINE_2")==null){
						customerAddress = rs.getString("ADDR_LINE_1")+","+rs.getString("CITY")+ ", "+rs.getString("STATE_CD")+ ", "+rs.getString("ZIP_CD");
					}else{
						customerAddress = rs.getString("ADDR_LINE_1")+", "+ rs.getString("ADDR_LINE_2")+rs.getString("CITY")+ ", "+rs.getString("STATE_CD")+ ", "+rs.getString("ZIP_CD"); 	
					}
					phoneNumber = rs.getString("PHONE_1");
					email_Address = rs.getString("EMAIL_ADDR_1");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getAction().waitFor(3000);

		Logger.log("Verify Customer Name for Order: " + OrderID, TestStepType.STEP);
		PageAssert.verifyTrue(customerName.equalsIgnoreCase(getAction().getText(CUSTOMER_NAME)), "expected ["+customerName+"] but found ["+getAction().getText(CUSTOMER_NAME)+"]");

		Logger.log("Verify Customer Address for Order: " + OrderID, TestStepType.STEP);
		PageAssert.verifyEqual(customerAddress, getAction().getText(CUSTOMER_ADDRESS).replace("\n", "").replace("\r", ""));

		Logger.log("Verify Customer Phone", TestStepType.STEP);
		PageAssert.textPresent(CUSTOMER_PHONE_TITLE, "Phone");
		PageAssert.verifyEqual(phoneNumber, getAction().getText(CUSTOMER_PHONE_NUMBER).replaceAll("[\\D]", ""));
		validatePhoneNumber(getAction().getText(CUSTOMER_PHONE_NUMBER));

		Logger.log("Verify Customer Email ", TestStepType.STEP);
		PageAssert.textPresent(CUSTOMER_EMAIL_TITLE, "Email");
		PageAssert.verifyEqual(email_Address, getAction().getText(CUSTOMER_EMAIL_ADDRESS));
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;
	}



	public OrderDetailsPage orderSummaryVerify(String OrderID) throws ParseException, SQLException{

		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		String storeName=null;
		String orderNumber=null;
		String store=null;
		String phurchaseTimeStamp=null;
		String orderStatus=null;
		String orderSource=null;
		String assoc_ID=null;
		String loyaltyNumber=null;
		String sql ="select o.site_gen_ord_id, o.site_id, o.PLACEMENT_TS, os.order_sts_ds, ors.ASSOC_ID, ors.SOURCE, cpi.SYWR_NUMBER "
				+ "from ord o left outer join order_sts os on o.order_sts_cd = os.order_sts_cd left outer join order_source ors "
				+ "on o.order_id = ors.order_id left outer join customer_pgm_info cpi on o.BILLING_ADDRESS_ID = cpi.address_id "
				+ "where o.site_gen_ord_id =?";
		try {
			if(conn!=null){
				st = conn.prepareStatement(sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1,OrderID.trim() );	       
				st.execute();
				rs = st.getResultSet();
				while(rs.next()){
					orderNumber = rs.getString("site_gen_ord_id");
					store = rs.getString("site_id");
					phurchaseTimeStamp = rs.getString("PLACEMENT_TS");
					orderStatus = rs.getString("order_sts_ds");
					orderSource = rs.getString("SOURCE");
					assoc_ID = rs.getString("ASSOC_ID");
					loyaltyNumber = rs.getString("SYWR_NUMBER");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getAction().waitFor(3000);
		PageAssert.textPresent(ORDER_SUMMARY_TITLE_TEXT, "Order Summary");
		Logger.log("Verify Order Summary Text is Present", TestStepType.STEP);
		getAction().scrollTo(ORDER_SUMMARY_TITLE_TEXT);
		String url=FrameworkProperties.SELENIUM_BASE_URL;
		if(!url.contains("msp.prod.global")){
			if(orderNumber!=null || store!=null || phurchaseTimeStamp!=null || orderStatus!=null 
					|| orderSource!=null || assoc_ID!=null || loyaltyNumber!=null){
				if(getAction().isVisible(ORDER_SUMMARY_TABLE)){
					Logger.log("Verify Order Summary Table is Visible", TestStepType.STEP);
					Logger.log("Verify Order Number", TestStepType.STEP);
					PageAssert.textPresent(ORDER_SUMMARY_ORDER_NUMBER_TEXT, "Order Number");
					PageAssert.verifyEqual(orderNumber, getAction().getText(ORDER_SUMMARY_ORDER_NUMBER));

					Logger.log("Verify Store Name in Order Summary", TestStepType.STEP);
					PageAssert.textPresent(ORDER_SUMMARY_STORE_TEXT, "Store");
					storeName = getStoreName(store);
					PageAssert.verifyEqual(storeName, getAction().getText(ORDER_SUMMARY_STORE_NAME));

					Logger.log("Verify Purchase Date Text is Present", TestStepType.STEP);
					PageAssert.textPresent(ORDER_SUMMARY_PURCHASE_DATE_TEXT, "Purchase Date");

					Logger.log("Verify Order Status Text is Present", TestStepType.STEP);
					PageAssert.textPresent(ORDER_SUMMARY_ORDER_STATUS_TEXT, "Order Status");

					Logger.log("Verify Order Satus in Order Summary", TestStepType.STEP);
					PageAssert.verifyEqual(orderStatus, getAction().getText(ORDER_SUMMARY_ORDER_STATUS));

					Logger.log("Verify 'Order Placed By' Text is Present", TestStepType.STEP);
					PageAssert.textPresent(ORDER_SUMMARY_PLACED_BY_TEXT, "Order Placed By");

					if(orderSource !=null && orderSource=="CCN"){
						if(isNumberOfAsscoID(assoc_ID)){
							String OrderPlacedBy = assoc_ID;
							PageAssert.verifyEqual(OrderPlacedBy, getAction().getText(ORDER_SUMMARY_PLACED_BY));
							Logger.log("Verify Order Placed By " +OrderPlacedBy +" in Order Summary", TestStepType.STEP);
						}
					}else if(orderSource !=null && orderSource!="CCN"){
						if(!isNumberOfAsscoID(assoc_ID)){
							String OrderPlacedBy = "CUSTOMER";
							PageAssert.verifyEqual(OrderPlacedBy, getAction().getText(ORDER_SUMMARY_PLACED_BY));
							Logger.log("Verify Order Placed By " +OrderPlacedBy +" in Order Summary", TestStepType.STEP);
						}
					}else{
						Logger.log("Order Placed By Orther Source", TestStepType.STEP);
					}

					if(loyaltyNumber==null){
						Logger.log("Verify Loyalty# Not Present in Order Summary", TestStepType.STEP);
						PageAssert.elementNotPresent(ORDER_SUMMARY_LOYALTY_NO_TEXT);
						PageAssert.elementNotPresent(ORDER_SUMMARY_LOYALTY_NO);

					}else{
						Logger.log("Verify Loyalty# Text is Present", TestStepType.STEP);
						PageAssert.textPresent(ORDER_SUMMARY_LOYALTY_NO_TEXT, "Loyalty #");
						Logger.log("Verify Loyalty# in Order Summary", TestStepType.STEP);
						PageAssert.verifyEqual(loyaltyNumber, getAction().getText(ORDER_SUMMARY_LOYALTY_NO));
					}	            
				}else{
					PageAssert.fail("Retrieval Data from Database failed");
				}
			}else{
				Logger.log("There are no order summary information in Database", TestStepType.STEP);
			}
		}
		else{
			AjaxCondition.forElementVisible(ORDER_SUMMARY_TABLE).waitForResponse();
			Logger.log("Verify Order Summary Table is Visible", TestStepType.STEP);
			Logger.log("Verify Order Number", TestStepType.STEP);
			PageAssert.textPresent(ORDER_SUMMARY_ORDER_NUMBER_TEXT, "Order Number");
			SoftAssert.checkConditionAndContinueOnFailure("Verify Order Number should not be empty", 
					!getAction().getText(ORDER_SUMMARY_ORDER_NUMBER).isEmpty());

			Logger.log("Verify Store Name in Order Summary", TestStepType.STEP);
			PageAssert.textPresent(ORDER_SUMMARY_STORE_TEXT, "Store");
			SoftAssert.checkConditionAndContinueOnFailure("Verify Store Name should not be empty", 
					!getAction().getText(ORDER_SUMMARY_STORE_NAME).isEmpty());


			Logger.log("Verify Purchase Date Text is Present", TestStepType.STEP);
			PageAssert.textPresent(ORDER_SUMMARY_PURCHASE_DATE_TEXT, "Purchase Date");

			Logger.log("Verify Order Status Text is Present", TestStepType.STEP);
			PageAssert.textPresent(ORDER_SUMMARY_ORDER_STATUS_TEXT, "Order Status");

			Logger.log("Verify Order Satus in Order Summary", TestStepType.STEP);
			SoftAssert.checkConditionAndContinueOnFailure("Verify Order Satus in Order Summary should not be empty", 
					!getAction().getText(ORDER_SUMMARY_ORDER_STATUS).isEmpty());

			Logger.log("Verify 'Order Placed By' Text is Present", TestStepType.STEP);
			PageAssert.textPresent(ORDER_SUMMARY_PLACED_BY_TEXT, "Order Placed By");

		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;
	}

	public OrderDetailsPage verifyOrderCharges(String OrderID) throws SQLException,SQLException{
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		double mt_current = 0.0;
		double sc_current = 0.0;
		double dc_current = 0.0;
		double ic_current = 0.0;
		double tax_current = 0.0;
		double ef_current = 0.0;
		double ship_tax_orignial = 0.0;
		double all_list_price_sum = 0.0;
		double mt_original = 0.0;
		double sc_original = 0.0;
		double dc_original = 0.0;
		double ic_original = 0.0;
		double tax_original = 0.0;
		double instal_original_sum = 0.0;
		double environment_fee_sum = 0.0;
		double ship_adj_sum = 0.0;
		double sales_adj_sum  = 0.0;
		double sales_tax_adj_sum  = 0.0;
		double total_original = 0.0;
		double total_original_cal = 0.0;
		double total_current_cal = 0.0;
		double adjst_amt = 0.0;
		double discount_amt_sum = 0.0;
		double total_adjustment_discount = 0.0;
		double total_adjustment_discount_percentage = 0.0;
		ArrayList<Double> order_total = new ArrayList<Double>();
		ArrayList<Double> discount_amt_list = new ArrayList<Double>();
		ArrayList<Double> salesadjust = new ArrayList<Double>();
		ArrayList<Double> shipadjust = new ArrayList<Double>();
		ArrayList<Double> salestaxadjust = new ArrayList<Double>();
		ArrayList<Double> ef_original = new ArrayList<Double>();
		ArrayList<String>  order_item_type = new ArrayList<String>();
		ArrayList<Double>  installlation = new ArrayList<Double>();

		String general_charge_sql = "select o.SHIPPING_AMT, o.TAX_AMT, o.SHIPPING_TAX_AMOUNT, o.TOTAL_AMOUNT, "
				+ "o.ADJMNT_AMT from ord o where o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String order_total_sql = "select oi.LIST_PRICE from ord o, ord_item oi where o.order_id = oi.order_id "
				+ "and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String sales_adjustment_sql = "select r.TOTAL_CREDIT_AMT from ord o, ord_item oi, rma r, rma_item ri, sales_check_tran_file sctf "
				+ "where oi.order_id = o.order_id and oi.order_item_id =ri.order_item_id and r.rma_id = ri.rma_id "
				+ "and r.tran_file_id = sctf.tran_file_id and r.rma_type = 'SLADJ' and site_gen_ord_id = ?	and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String ship_adjustment_sql = "select r.TOTAL_CREDIT_AMT from ord o, ord_item oi, rma r, rma_item ri, sales_check_tran_file sctf "
				+ "where oi.order_id = o.order_id and oi.order_item_id =ri.order_item_id and r.rma_id = ri.rma_id "
				+ "and r.tran_file_id = sctf.tran_file_id and r.rma_type = 'SHADJ' and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String sales_tax_adjustment_sql = "select r.TOTAL_CREDIT_AMT from ord o, ord_item oi, rma r, rma_item ri, sales_check_tran_file sctf "
				+ "where oi.order_id = o.order_id and oi.order_item_id =ri.order_item_id and r.rma_id = ri.rma_id "
				+ "and r.tran_file_id = sctf.tran_file_id and r.rma_type = 'TADJ' and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String environment_fee_sql = "select oi.env_fee from ord o, ord_item oi where oi.order_id = o.order_id and oi.env_fee >= 0 "
				+ "and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String discount_sql ="select AMOUNT from ord_item_discount where order_item_id in (select order_item_id "
				+ "from ord_item where order_id in (select order_id from ord where site_gen_ord_id = ?))  ";

		String delivery_charge_sql = "select oi.LIST_PRICE from ord o, ord_item oi where o.order_id = oi.order_id and "
				+ "oi.ITEM_NM like 'HOME DELIVERY CHARGE' and LIST_PRICE > 0 and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String installation_sql = "select oi.ORDER_ITEM_TYP, oi.LIST_PRICE from ord o, ord_item oi where o.order_id = oi.order_id and oi.ITEM_NM like '%installation%' "
				+ "and oi.LIST_PRICE > 0 and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$';";
		try {
			if(conn!=null){
				st = conn.prepareStatement(general_charge_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();

				getAction().waitFor(1000);
				while(rs.next()){
					sc_original = rs.getDouble("SHIPPING_AMT");
					adjst_amt = rs.getDouble("ADJMNT_AMT");
					ship_tax_orignial = rs.getDouble("SHIPPING_TAX_AMOUNT");
					tax_original = rs.getDouble("TAX_AMT")+ship_tax_orignial;
					total_original = rs.getDouble("TOTAL_AMOUNT");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if(conn!=null){
				st = conn.prepareStatement(order_total_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();

				getAction().waitFor(1000);
				while(rs.next()){
					order_total.add(rs.getDouble("LIST_PRICE"));}
				for(double ot:order_total){
					all_list_price_sum+= ot;}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null){
				st = conn.prepareStatement(delivery_charge_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					dc_original = rs.getDouble("LIST_PRICE");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(dc_original==0.0){	
			dc_original = 0.00;
		}
		System.out.println("Original Delivery Charge: "+dc_original);

		try {

			if(conn!=null){
				st = conn.prepareStatement(installation_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					order_item_type.add(rs.getString("ORDER_ITEM_TYP"));
					installlation.add(rs.getDouble("LIST_PRICE"));}
				for(int i = 0; i< order_item_type.size(); i++){
					if(order_item_type.get(i).equals("IM")){
						ic_original = installlation.get(i);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for(double ins:installlation){
			instal_original_sum += ins;
		}
		if(ic_original==0.0){
			ic_original = 0.00;
		}
		mt_original=all_list_price_sum-ic_original;
		//Environment Fee:
		try{
			if(conn!=null){
				st = conn.prepareStatement(environment_fee_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(2000);
				while(rs.next()){
					ef_original.add(rs.getDouble("env_fee"));}
				if(ef_original.size()>0){
					for(Double e: ef_original){
						environment_fee_sum =environment_fee_sum+e;}
				}else{
					environment_fee_sum = 0.00;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		//Shipping Adjustment:
		try{
			if(conn!=null){
				st = conn.prepareStatement(ship_adjustment_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					shipadjust.add(rs.getDouble("TOTAL_CREDIT_AMT"));
				}
				if(shipadjust.size()>0){
					for(Double sa: salesadjust){
						System.out.println(sa);
						ship_adj_sum += sa;}
				}else{
					ship_adj_sum = 0.00;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		//Sales Adjustment
		try{
			if(conn!=null){
				st = conn.prepareStatement(sales_adjustment_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					salesadjust.add(rs.getDouble("TOTAL_CREDIT_AMT"));
				}

				if(salesadjust.size()>0){
					for(Double salesadj: salesadjust){
						System.out.println(salesadj);
						sales_adj_sum += salesadj;}
				}else{
					sales_adj_sum = 0.00;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		//Sales Tax Adjustment
		try{
			if(conn!=null){
				st = conn.prepareStatement(sales_tax_adjustment_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					salestaxadjust.add(rs.getDouble("TOTAL_CREDIT_AMT"));
				}
				if(salestaxadjust.size()>0){
					for(Double st1: salestaxadjust){
						sales_tax_adj_sum += st1;}
				}else{
					sales_tax_adj_sum = 0.00;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//Discount
		try {
			if(conn!=null){
				st = conn.prepareStatement(discount_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					discount_amt_list.add(rs.getDouble("AMOUNT"));
				}
				if(discount_amt_list.size()>0){
					for(Double d: discount_amt_list){
						System.out.println(d);
						discount_amt_sum += d;}
				}	
			}else{
				discount_amt_sum = 0.00;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mt_current = mt_original-sales_adj_sum;
		sc_current = sc_original-Math.abs(adjst_amt);
		dc_current = dc_original;
		ic_current = ic_original;
		tax_current = tax_original-ship_adj_sum-sales_tax_adj_sum;
		ef_current = environment_fee_sum;

		total_original_cal = mt_original+sc_original+dc_original+ic_original+tax_original+environment_fee_sum;
		total_current_cal = total_original_cal-ship_adj_sum-sales_adj_sum-sales_tax_adj_sum-Math.abs(discount_amt_sum);

		total_adjustment_discount= Math.abs(ship_adj_sum)+ Math.abs(discount_amt_sum)+Math.abs(sales_adj_sum)+Math.abs(sales_tax_adj_sum);
		if(total_adjustment_discount!=0.0){
			total_adjustment_discount_percentage= (total_adjustment_discount/total_original)*100;
		}
		if(mt_original!=0.0){	
			getAction().waitFor(2000);
			Logger.log("Verify Order Charges", TestStepType.STEP);
			PageAssert.textPresent(ORDER_CHARGES_TITLE_TEXT, "Order Charges");
			getAction().scrollTo(ORDER_CHARGES_TITLE_TEXT);
			if(getAction().isVisible(ORDER_CHARGES_TABLE)){
				Logger.log("Verify Order Charges Table is Visible", TestStepType.STEP);

				Logger.log("Verify Order Charges Description Column Name is Present", TestStepType.STEP);
				PageAssert.textPresent(ORDER_CHARGES_DESCRIPTION_COL_NAME, "Description");

				Logger.log("Verify Order Charges Original Column Name is Present", TestStepType.STEP);
				PageAssert.textPresent(ORDER_CHARGES_ORIGINAL_COL_NAME, "Original");

				Logger.log("Verify Order Charges Current Column Name is Present", TestStepType.STEP);
				PageAssert.textPresent(ORDER_CHARGES_CURRENT_COL_NAME, "Current");

				Logger.log("Verify Order Charges Merchandise Total Row Name is Present", TestStepType.STEP);
				PageAssert.textPresent(MERCHANDISE_TOTAL_ROW_NAME, "Merchandise Total");

				Logger.log("Verify Order Charges Shipping Charge Row Name is Present", TestStepType.STEP);
				PageAssert.textPresent(SHIPPING_CHARGE_ROW_NAME, "Shipping Charge");

				Logger.log("Verify Order Charges Delivery Charge Row Name is Present", TestStepType.STEP);
				PageAssert.textPresent(DELIVERY_CHARGE_ROW_NAME, "Delivery Charge");

				Logger.log("Verify Order Charges Installation Charge Row Name is Present", TestStepType.STEP);
				PageAssert.textPresent(INSTALLATION_CHARGE_ROW_NAME, "Installation Charge");

				Logger.log("Verify Order Charges Tax Row Name is Present", TestStepType.STEP);
				PageAssert.textPresent(TAX_ROW_NAME, "Tax");

				Logger.log("Verify Order Charges Environmental Fee Row Name is Present", TestStepType.STEP);
				PageAssert.textPresent(ENVIRONMENTAL_FEE_ROW_NAME, "Environmental Fee");

				Logger.log("Verify Order Charges Total Row Name is Present", TestStepType.STEP);
				PageAssert.textPresent(TOTAL_ROW_NAME, "Total");

				Logger.log("Verify Order Merchandise Original Total in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+formatter.format(mt_original), getAction().getText(MERCHANDISE_TOTAL_ORIGINAL_PRICE));

				Logger.log("Verify Order Shipping Charge Original Price in Order Summary", TestStepType.STEP);				
				PageAssert.verifyEqual("$"+df.format(sc_original), getAction().getText(SHIPPING_CHARGE_ORIGINAL_PRICE));

				Logger.log("Verify Order Delivery Charge Original Price in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+df.format(dc_original), getAction().getText(DELIVERY_CHARGE_ORIGINAL_PRICE));

				Logger.log("Verify Order Installation Charge Original Price in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+df.format(ic_original), getAction().getText(INSTALLATION_CHARGE_ORIGINAL_PRICE));

				Logger.log("Verify Order Tax Original Price in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+formatter.format(tax_original), getAction().getText(TAX_ORIGINAL_PRICE));

				Logger.log("Verify Order Environmental Original Fee in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+df.format(environment_fee_sum), getAction().getText(ENVIRONMENTAL_FEE_ORIGINAL_PRICE));

				Logger.log("Verify Order Original Total Price in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+formatter.format(total_original_cal), getAction().getText(TOTAL_ORIGINAL_PRICE));

				Logger.log("Verify Order Merchandise Current Total in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+formatter.format(mt_current), getAction().getText(MERCHANDISE_TOTAL_CURRENT_PRICE));

				if(getAction().getText(SHIPPING_CHARGE_CURRENT_PRICE).contains("(") && getAction().getText(SHIPPING_CHARGE_CURRENT_PRICE).contains(")")){
					Logger.log("Verify Order Shipping Charge Current Price in Order Summary", TestStepType.STEP);
					PageAssert.verifyEqual("($"+df.format(Math.abs(sc_current)+")"), getAction().getText(SHIPPING_CHARGE_CURRENT_PRICE));

				}else{
					Logger.log("Verify Order Shipping Charge Current Price in Order Summary", TestStepType.STEP);
					PageAssert.verifyEqual("$"+df.format(sc_current), getAction().getText(SHIPPING_CHARGE_CURRENT_PRICE));
				}

				Logger.log("Verify Order Delivery Charge Current Price in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+df.format(dc_current), getAction().getText(DELIVERY_CHARGE_CURRENT_PRICE));

				Logger.log("Verify Order Installation Charge Current Price in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+df.format(ic_current), getAction().getText(INSTALLATION_CHARGE_CURRENT_PRICE));

				Logger.log("Verify Order Tax Current Price in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+formatter.format(tax_current), getAction().getText(TAX_CURRENT_PRICE));

				Logger.log("Verify Order Environmental Current Fee in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+df.format(ef_current), getAction().getText(ENVIRONMENTAL_FEE_CURRENT_PRICE));

				Logger.log("Verify Order Current Total Price in Order Summary", TestStepType.STEP);
				PageAssert.verifyEqual("$"+formatter.format(total_current_cal), getAction().getText(TOTAL_CURRENT_PRICE));

				if(total_adjustment_discount>0){
					Logger.log("Verify Total Adjustment / Discount Applied and Percentage in Order Summary", TestStepType.STEP);
					PageAssert.verifyEqual("Total Adjustment / Discount Applied : $"+formatter.format(total_adjustment_discount)+
							" ("+formatter.format(total_adjustment_discount_percentage)+"%)", getAction().getText(TOTAL_ADJUSTMENT_DISCOUNT));
				}
			} else{
				PageAssert.fail("Retrieval Data from Database failed");
			}

		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;

	}

	public OrderDetailsPage verifyDiscounts(String OrderID) throws SQLException{
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		ArrayList<String> description  =  new ArrayList<String>();
		ArrayList<Double> amount  =  new ArrayList<Double>();
		getAction().waitFor(1000);
		String sql ="select PROMOTION_NAME, AMOUNT "
				+ "from ord_item_discount where order_item_id in (select order_item_id "
				+ "from ord_item where order_id in (select order_id from ord where site_gen_ord_id = ?) ) ";

		try {
			if(conn!=null){
				st = conn.prepareStatement(sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();

				while(rs.next()){

					description.add(rs.getString("PROMOTION_NAME"));
					amount.add(rs.getDouble("AMOUNT"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		getAction().waitFor(3000);

		if(description.size()!=0 || amount.size()!=0){
			Logger.log("Verify Discounts Text is Present", TestStepType.STEP);
			PageAssert.textPresent(DISCOUNTS_TITLE_TEXT, "Discounts");

			getAction().scrollTo(DISCOUNTS_TITLE_TEXT);


			if(getAction().isVisible(DISCOUNTS_TABLE_O)){
				Logger.log("Verify Discount Table is Visible", TestStepType.STEP);

				Logger.log("Verify Discount Description Column Name is Present", TestStepType.STEP);
				PageAssert.textPresent(DISCOUNTS_DESCRIPTION_COL_NAME, "Discount Description");

				Logger.log("Verify Discount Amount Column Name is Present", TestStepType.STEP);
				PageAssert.textPresent(DISCOUNTS_AMOUNT_COL_NAME, "Amount");


				WebElement discountsstable = getAction().findElement(DISCOUNTS_TABLE_CONTENT );
				List<WebElement> disct_table_rows = discountsstable.findElements(By.tagName("tr"));
				int disct_table_rows_count = disct_table_rows.size();

				for (int i=0; i<disct_table_rows_count; i++){

					List<WebElement> disct_table_columns = disct_table_rows.get(i).findElements(By.tagName("td"));

					Logger.log("Verify Description in Discount is "+ description.get(i) +" in Database", TestStepType.STEP);
					PageAssert.verifyEqual(description.get(i), disct_table_columns.get(0).getText());

					Logger.log("Verify discount amount in Database ("+amount.get(i)+") matches with amount in application("+"$"+String.format("%.2f", Math.abs(amount.get(i))) +")", TestStepType.STEP);
					PageAssert.verifyEqual("($"+formatter.format(Math.abs(amount.get(i)))+")", disct_table_columns.get(1).getText());
				}	
			}else{

				PageAssert.fail("Retrieval Data from Database failed");
			}
		}else{
			Logger.log("There are no Discounts Information in Database", TestStepType.STEP);
			AjaxCondition.forElementVisible(NO_DISCOUNTS_RESPONSE).waitForResponse(10);
		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;
	}

	public OrderDetailsPage verifyAdjustments(String OrderID) throws ParseException, SQLException{
		getAction().waitFor(1000);
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		ArrayList<String> type = new ArrayList<String>();
		ArrayList<Double> amount = new ArrayList<Double>();
		ArrayList<String> date = new ArrayList<String>();
		ArrayList<String> sc_No = new ArrayList<String>();
		String type_des = null;
		String sql ="select r.rma_type, r.TOTAL_CREDIT_AMT, sctf.eod_dt, r.RETURN_SALES_CHECK_NUMBER "
				+ "from ord o, ord_item oi, rma r, rma_item ri, sales_check_tran_file sctf "
				+ "where oi.order_id = o.order_id and oi.order_item_id =ri.order_item_id and r.rma_id = ri.rma_id  "
				+ "and r.tran_file_id = sctf.tran_file_id and site_gen_ord_id = ?";

		/***
		 * data from mysql database
		 ***/
		try {
			if(conn!=null){
				st = conn.prepareStatement(sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){

					type.add(rs.getString("rma_type"));
					amount.add(rs.getDouble("TOTAL_CREDIT_AMT"));
					date.add(rs.getString("eod_dt"));
					sc_No.add(rs.getString("RETURN_SALES_CHECK_NUMBER"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		if(type.size()!=0 || amount.size()!=0 || date.size()!=0 ||sc_No.size()!=0){

			/***
			 * verify text present
			 ***/

			Logger.log("Verify Adjustment Text is Present", TestStepType.STEP);
			PageAssert.textPresent(ADJUSTMENTS_TITLE_TEXT, "Adjustments");


			if( getAction().isVisible(ADJUSTMENTS_TABLE)){
				Logger.log("Verify Adjustment Table is Visible", TestStepType.STEP);

				Logger.log("Verify Adjustment Type Column Name is Present", TestStepType.STEP);
				PageAssert.textPresent(ADJUSTMENTS_TYPE_COL_NAME, "Type");

				Logger.log("Verify Adjustment Amount Column Name is Present", TestStepType.STEP);
				PageAssert.textPresent( ADJUSTMENTS_AMOUNT_COL_NAME, "Amount");

				Logger.log("Verify Adjustment Date Column Name is Present", TestStepType.STEP);
				PageAssert.textPresent(ADJUSTMENTS_DATE_COL_NAME, "Date");

				Logger.log("Verify Adjustment Sales Check # Column Name is Present", TestStepType.STEP);
				PageAssert.textPresent(ADJUSTMENTS_SALECHECK_NO_COL_NAME, "Sales Check #");


				/***
				 * verify data
				 ***/
				getAction().waitFor(2000);

				WebElement adjustmentstable = getAction().findElement(ADJUSTMENTS_TABLE_CONTENT );
				List<WebElement> adj_table_rows = adjustmentstable.findElements(By.tagName("tr"));
				int adj_table_rows_count = adj_table_rows.size();

				for (int i=0; i<adj_table_rows_count; i+=3){

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

					Logger.log("Verify adjustment type in database ("+ type_des+") matches with application ("+ adj_table_columns.get(0).getText()+")", TestStepType.STEP);
					PageAssert.verifyEqual(type_des, adj_table_columns.get(0).getText());

					Logger.log("Verify adjustment amount in database ("+amount.get(i)+") matches with application("+adj_table_columns.get(1).getText()+")", TestStepType.STEP);
					//PageAssert.verifyEqual("($"+formatter.format(amount.get(i))+")", adj_table_columns.get(1).getText());
					PageAssert.verifyTrue(adj_table_columns.get(1).getText().contains(formatter.format(amount.get(i))), "Verify Amount in Adjustment is "+"($"+df.format(amount.get(i))+")"+" in DataBase");
					System.out.println("Page value:  "+adj_table_columns.get(1).getText()+" DB Value:  "+formatter.format(amount.get(i)));

					Logger.log("Verify adjustment date in database ("+sdf2.format(sdf1.parse(date.get(i)))+") matches with date in application ("+adj_table_columns.get(2).getText()+")", TestStepType.STEP);
					PageAssert.verifyEqual(sdf2.format(sdf1.parse(date.get(i))), adj_table_columns.get(2).getText());

					Logger.log("Verify Sales Check # for adjustment in database ( "+ sc_No.get(i)+") matches with application ("+adj_table_columns.get(3).getText()+")", TestStepType.STEP);
					PageAssert.verifyEqual(sc_No.get(i), adj_table_columns.get(3).getText());

				}	

			}  else{

				PageAssert.fail("Retrieval Data from Database failed");
			}
		}else{
			Logger.log("There are no Adjustments Information in Database", TestStepType.STEP);
			AjaxCondition.forElementVisible(NO_ADJUSTMENTS_RESPONSE).waitForResponse(10);
		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}

		return this;
	}

	public OrderDetailsPage verifyPayments( String OrderID, String storeId) throws SQLException {
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		String Sql ="select pm.PAYMENT_METHOD_DS, pm.brand, pi.TOTAL_AMT "
				+ "from pmt_method pm, payment_instruction pi, ord o "
				+ "where o.order_id = pi.order_id and pi.payment_method_id = pm.payment_method_id "
				+ "and site_gen_ord_id = ?";
		ArrayList<String> payment_type  =  new ArrayList<String>();
		ArrayList<String> card_type =  new ArrayList<String>();
		ArrayList<Double> payment_amount =  new ArrayList<Double>();

		//String payment_type_ws = null;
		String card_number_ws = null;
		//String card_type_ws = null;
		String name_on_card_ws = null;
		String expire_month_ws = null;
		String expire_year_ws = null;
		//String pay_amount_ws = null;
		String adj_credit_pts_ws = null;
		String adj_credit$_ws = null;

		JSONObject orderSummary = null;

		/****
		 * data from mysql db
		 ****/
		Logger.log("SQL for fetching payment "+Sql,TestStepType.DATA_CAPTURE);
		try {
			if(conn!=null){
				st = conn.prepareStatement(Sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID);
				st.execute();
				rs = st.getResultSet();
				getAction().waitFor(1000);
				while(rs.next()){
					payment_type.add(rs.getString("PAYMENT_METHOD_DS"));
					card_type.add(rs.getString("brand"));
					payment_amount.add(rs.getDouble("TOTAL_AMT"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		if(payment_type!=null || card_type!=null || payment_amount!=null){	

			/***
			 * data from web services
			 ****/
			orderSummary = verifysoapdetails(OrderID, storeId);
			/***
			 * verify text present
			 ***/
			getAction().waitFor(3000);

			PageAssert.textPresent(PAYMENTS_TITLE_TEXT, "Payments");
			Logger.log("Verify Payments Text is Present", TestStepType.STEP);
			getAction().scrollTo(PAYMENTS_TITLE_TEXT);
			//highlight(PAYMENTS_TITLE_TEXT);

			if( getAction().isVisible(PAYMENTS_TABLE_O)){
				Logger.log("Verify Payments Table is Visible", TestStepType.STEP);

				Logger.log("Verify Payments Type Column is not empty", TestStepType.STEP);
				String paymentType = getAction().getText(PAYMENT_TYPE_COL_NAME);
				PageAssert.verifyTrue(paymentType.length()>1, "Payment type column doesn't contain data");


				Logger.log("Verify Payments Card Number column in not empty", TestStepType.STEP);
				String cardNumber=getAction().getText(CARD_NUMBER_COL_NAME);
				PageAssert.verifyTrue(cardNumber.length()>1, "Card number column doesn't contain data");


				Logger.log("Verify Payments Card Type Column is not empty", TestStepType.STEP);				
				String cardType=getAction().getText(CARD_TYPE_COL_NAME);
				PageAssert.verifyTrue(cardType.length()>1, "Card type column doesn't contain data");

				Logger.log("Verify Name on Card Column Name is not empty", TestStepType.STEP);
				String nameOnCard = getAction().getText(NAME_ON_CARD_COL_NAME);
				PageAssert.verifyTrue(nameOnCard.length()>1, "Name on card column doesn't contain data");

				Logger.log("Verify Expiration Date Column Name is not empty", TestStepType.STEP);
				String expDate = getAction().getText(EXPIRATION_DATE_COL_NAME);
				PageAssert.verifyTrue(expDate.length()>1, "Expiration Date is empty");

				Logger.log("Verify Payment Amount Column Name is not empty", TestStepType.STEP);
				String paymentAmt = getAction().getText(PAYMENT_AMOUNT_COL_NAME);
				PageAssert.verifyTrue(paymentAmt.length()>0, "Payment Amount is empty");

				Logger.log("Verify Adj/Credit Points Column Name is not empty", TestStepType.STEP);
				String creditPoints = getAction().getText(CREDIT_POINTS_COL_NAME);
				PageAssert.verifyTrue(creditPoints.length()>0, "Adj/Credit pts is empty");

				Logger.log("Verify Adj/Credit $ Column Name is not empty", TestStepType.STEP);
				String creditDollar = getAction().getText(CREDIT_COL_NAME);
				PageAssert.verifyTrue(creditDollar.length()>0, "Adj/Credit $ is empty");

			}

			/***
			 * verify value
			 ***/	

			WebElement paymentstable = getAction().findElement(PAYMENTS_TABLE_CONTENT);
			List<WebElement> payment_table_rows = paymentstable.findElements(By.tagName("tr"));
			int payment_table_rows_count = payment_table_rows.size();
			getAction().waitFor(2000);

			try
			{
				if (orderSummary.get("PaymentDetails") instanceof JSONObject)
				{
					JSONObject paymentDetailsObj = orderSummary.getJSONObject("PaymentDetails");
					if(paymentDetailsObj.getString("MaskedCardNumber")!=null){
						card_number_ws = String.valueOf(paymentDetailsObj.getString("MaskedCardNumber"));
					}else{
						card_number_ws = maskCreditCardNumber(String.valueOf(paymentDetailsObj.getLong("CardNumber")));
					}
					adj_credit_pts_ws = String.valueOf(paymentDetailsObj.getInt("AdjCreditSywrPoints"));
					adj_credit$_ws = String.valueOf(paymentDetailsObj.getDouble("AdjCreditDollarValue"));

					List<WebElement> payment_table_columns = payment_table_rows.get(0).findElements(By.tagName("td"));

					Logger.log("Verify Payments type in database[ "+ payment_type.get(0)+"] matches with application", TestStepType.STEP);
					PageAssert.verifyEqual(payment_type.get(0), payment_table_columns.get(0).getText());

					Logger.log("Verify Card Number type in database[ "+ card_number_ws+"] matches with application", TestStepType.STEP);
					PageAssert.verifyEqual(card_number_ws, payment_table_columns.get(2).getText());


					if(payment_type.get(0).equalsIgnoreCase("CreditCard"))
					{
						name_on_card_ws = paymentDetailsObj.getString("NameonTheCard");
						expire_month_ws = String.valueOf(paymentDetailsObj.getInt("ExpiryMonth"));
						expire_year_ws = String.valueOf(paymentDetailsObj.getInt("ExpiryYear"));

						Logger.log("Verify payment type in database [" +card_type.get(0)+"] matches with application", TestStepType.STEP);
						PageAssert.verifyEqual(card_type.get(0), payment_table_columns.get(3).getText());

						Logger.log("Verify Name on Card in webservice ["+ name_on_card_ws+"] matches database", TestStepType.STEP);
						PageAssert.verifyEqual(name_on_card_ws, payment_table_columns.get(4).getText());

						if(null!=(String) getContext().get("UserType")){
							if(((String) getContext().get("UserType")).equalsIgnoreCase("offlineagent")){
								Logger.log("Click on Get Token link",TestStepType.STEP);
								AjaxCondition.forElementVisible(GET_TOKEN_BUTTON).waitForResponse();
								getAction().click(GET_TOKEN_BUTTON);
								Logger.log("Verify token is displayed", TestStepType.VERIFICATION_STEP);
								AjaxCondition.forElementVisible(TOKEN_POPUP).waitForResponse();
								Logger.log("Close token popup",TestStepType.STEP);
								getAction().click(CLOSE_TOKEN);
								Logger.log("Verify expiry month is displayed",TestStepType.STEP);
								String expiry = payment_table_columns.get(5).getText();
								PageAssert.verifyEqual(Integer.parseInt(expiry.split("/")[0].trim())+"/"+Integer.parseInt(expiry.split("/")[1].trim()),expire_month_ws+"/"+expire_year_ws);
							}
							else{
								Logger.log("Verify expiry date is masked",TestStepType.STEP);
								PageAssert.verifyEqual("** / ****", payment_table_columns.get(5).getText());		
							}
						}
					}

					Logger.log("Verify Payment Amount "+"$"+String.format("%.2f", payment_amount.get(0))+" in dataBase matches with application", TestStepType.STEP);
					PageAssert.verifyEqual("$"+String.format("%.2f", payment_amount.get(0)), payment_table_columns.get(6).getText());

					Logger.log("Verify Adj/Credit Points "+ adj_credit_pts_ws+" in Web Services matches with application", TestStepType.STEP);
					PageAssert.verifyEqual(adj_credit_pts_ws, payment_table_columns.get(7).getText());

					Logger.log("Verify Credit adjustment "+ "$"+df.format(Double.parseDouble(adj_credit$_ws))+" in Web Services matches with application", TestStepType.STEP);
					PageAssert.verifyEqual("$"+df.format(Double.parseDouble(adj_credit$_ws)), payment_table_columns.get(8).getText());
				}    		

				else if (orderSummary.get("PaymentDetails") instanceof JSONArray)
				{
					JSONArray paymentDetailsArray = orderSummary.getJSONArray("PaymentDetails");
					Logger.log("Verify number of payments in application matches with webservice response",TestStepType.STEP);
					PageAssert.verifyEqual(payment_table_rows_count, paymentDetailsArray.length());

					for (int i=0; i<payment_table_rows_count; i++){
						JSONObject tempObject = (JSONObject)paymentDetailsArray.getJSONObject(i);
						getAction().waitFor(2000);
						card_number_ws = maskCreditCardNumber(String.valueOf(tempObject.getLong("MaskedCardNumber")));
						adj_credit_pts_ws = String.valueOf(tempObject.getInt("AdjCreditSywrPoints"));
						adj_credit$_ws = String.valueOf(tempObject.getDouble("AdjCreditDollarValue"));

						List<WebElement> payment_table_columns = payment_table_rows.get(i).findElements(By.tagName("td"));

						if(!payment_type.get(i).equalsIgnoreCase("SywrCard")){
							Logger.log("Verify payments type "+ payment_type.get(i)+" in databse matches with application", TestStepType.STEP);
							PageAssert.verifyEqual(payment_type.get(i), payment_table_columns.get(0).getText());
						}
						if(!payment_type.get(i).equalsIgnoreCase("GiftCard")){
							Logger.log("Verify Card Number "+ card_number_ws+" in Web Services matches with application", TestStepType.STEP);
							PageAssert.verifyEqual(card_number_ws, payment_table_columns.get(2).getText());}


						if(payment_type.get(i).equalsIgnoreCase("CreditCard"))
						{
							name_on_card_ws = tempObject.getString("NameonTheCard");
							expire_month_ws = String.valueOf(tempObject.getInt("ExpiryMonth"));
							expire_year_ws = String.valueOf(tempObject.getInt("ExpiryYear"));

							Logger.log("Verify card type "+ card_type.get(i)+" in databse matches with application", TestStepType.STEP);
							PageAssert.verifyEqual(card_type.get(i), payment_table_columns.get(3).getText());

							Logger.log("Verify Name on Card "+ name_on_card_ws+" in Web Services matches with application", TestStepType.STEP);
							PageAssert.verifyEqual(name_on_card_ws, payment_table_columns.get(4).getText());

							Logger.log("Verify Expiration Date "+expire_month_ws+"/"+expire_year_ws +" in Web Services matches with application", TestStepType.STEP);        	    		
							PageAssert.verifyEqual(expire_month_ws.replaceAll("\\d+.*", "")+"/"+expire_year_ws.replaceAll("\\d+.*", ""), payment_table_columns.get(6).getText());

						}

						Logger.log("Verify Payment Amount "+"$"+String.format("%.2f", payment_amount.get(i))+" in DataBase matches with application", TestStepType.STEP);
						PageAssert.verifyEqual("$"+df.format(payment_amount.get(i)), payment_table_columns.get(7).getText());

						Logger.log("Verify Adj/Credit points "+ adj_credit_pts_ws+" in Web Services matches with application", TestStepType.STEP);
						PageAssert.verifyEqual(adj_credit_pts_ws, payment_table_columns.get(8).getText());

						Logger.log("Verify Credit in Payments "+ "$"+df.format(Double.parseDouble(adj_credit$_ws))+" in Web Services matches with application", TestStepType.STEP);
						PageAssert.verifyEqual("$"+df.format(Double.parseDouble(adj_credit$_ws)), payment_table_columns.get(9).getText());
					}
				}

				else{
					Logger.log("Payments Table is not visible", TestStepType.STEP);
					PageAssert.fail("Payments Table is not visible");
				}

			}

			catch (Exception e) {				
				e.printStackTrace();
			}   		 
		}else{

			Logger.log("There are no Adjustments Information in Database", TestStepType.STEP);

		}	
		getAction().waitFor(1000);
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;   		
	}	

	public OrderDetailsPage verifyOrderDetails(String OrderID, String storeId) throws SQLException, JSONException{
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		getAction().waitFor(1000);
		String item_status = null;
		ArrayList<String> item_Status  =  new ArrayList<String>();
		ArrayList<String> item_Description  =  new ArrayList<String>();
		ArrayList<String> sKU  =  new ArrayList<String>();
		ArrayList<String> qty  =  new ArrayList<String>();
		ArrayList<Double> price  =  new ArrayList<Double>();
		ArrayList<String> fulfilled_By  =  new ArrayList<String>();
		ArrayList<Double> shipping_Charge  =  new ArrayList<Double>();
		ArrayList<String> Tracking  =  new ArrayList<String>();
		ArrayList<String> Shipping_Method  =  new ArrayList<String>();
		//ArrayList<String> Shipping_LName  =  new ArrayList<String>();
		String fullfilby_ws = null;
		String tracking_number = null;
		String carrier = null;
		JSONArray itemSummaryArray=null;
		JSONObject orderSummary = null;
		if(FrameworkProperties.TEST_ENV.equalsIgnoreCase("qa")){
			String Sql ="select oi.ORDER_ITEM_STS_CD, oi.ITEM_NM, oi.ITEM_ID, oi.QUANTITY, oi.SUBTOTAL_AMOUNT, fm.ffm_class_id, oi.SHIPPING_AMT "
					+ "from ord o, ord_item oi, ffm_method fm "
					+ "where o.order_id = oi.order_id and oi.ffm_method_id = fm.ffm_method_id and o.site_gen_ord_id =?";
			String Sql_shipping ="select TRACKING_NUMBER,CARRIER from ship_unit where SHIP_UNIT_ID in (select SHIP_UNIT_ID from ship_invoice_item "
					+ "where order_item_id in (select order_item_id  from ord_item where order_id in (select order_id from ord where site_gen_ord_id =?)))";

			try {
				if(conn!=null){
					st = conn.prepareStatement(Sql);
					OrderID = OrderID.replaceAll("[^\\d.]", "");
					st.setString (1, OrderID.trim());
					st.execute();
					rs = st.getResultSet();
					while(rs.next()){
						item_Status.add(rs.getString("ORDER_ITEM_STS_CD"));
						item_Description.add(rs.getString("ITEM_NM"));
						sKU.add(rs.getString("ITEM_ID"));
						qty.add(rs.getString("QUANTITY"));
						price.add(rs.getDouble("SUBTOTAL_AMOUNT"));
						fulfilled_By.add(rs.getString("ffm_class_id"));
						shipping_Charge.add(rs.getDouble("SHIPPING_AMT"));
						//Shipping_LName.add(rs.getString(""));
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if(conn!=null){
					st = conn.prepareStatement(Sql_shipping);
					OrderID = OrderID.replaceAll("[^\\d.]", "");
					st.setString (1, OrderID.trim());
					st.execute();
					rs = st.getResultSet();
					while(rs.next()){
						Tracking.add(rs.getString("TRACKING_NUMBER"));
						Shipping_Method.add(rs.getString("CARRIER"));
						//Shipping_LName.add(rs.getString(""));
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			getAction().waitFor(3000);
		}

		if(item_Status!=null || item_Description!=null || sKU!=null || qty!=null || price!=null 
				|| fulfilled_By!=null || shipping_Charge!=null){
			try{
				orderSummary = verifysoapdetails(OrderID, storeId); 
				itemSummaryArray=(JSONArray)orderSummary.opt("ItemSummary");

			}

			catch (Exception e) {
				e.printStackTrace();
			}
			/***
			 * verify text present
			 ***/

			PageAssert.textPresent(ORDER_DETAILS_TITLE_TEXT, "Order Details");
			Logger.log("Verify Order Details Text is Present", TestStepType.STEP);
			getAction().scrollTo(ORDER_DETAILS_TITLE_TEXT);
			if(getAction().isVisible(ORDER_DETAILS_TABLE)){
				Logger.log("Verify Order Details Table is Visible", TestStepType.STEP);

				//column name

				SoftAssert.checkConditionAndContinueOnFailure("Status column is present",
						getAction().getText(STATUS_COL_NAME).equalsIgnoreCase("Status"));

				SoftAssert.checkConditionAndContinueOnFailure("Description column is present",
						getAction().getText(DESCRIPTION_COL_NAME).equalsIgnoreCase("Description"));

				SoftAssert.checkConditionAndContinueOnFailure("SKU column is present",
						getAction().getText(SKU_COL_NAME).equalsIgnoreCase("SKU"));

				SoftAssert.checkConditionAndContinueOnFailure("Qty column is present",
						getAction().getText(QTY_COL_NAME).equalsIgnoreCase("Qty"));

				SoftAssert.checkConditionAndContinueOnFailure("Price column is present",
						getAction().getText(PRICE_COL_NAME).equalsIgnoreCase("Price"));

				SoftAssert.checkConditionAndContinueOnFailure("Fulfill By column is present",
						getAction().getText(FULFILL_BY_COL_NAME).equalsIgnoreCase("Fulfill By"));

				SoftAssert.checkConditionAndContinueOnFailure("Shipping Charge column is present",
						getAction().getText(SHIPPING_CHARGE_COL_NAME).equalsIgnoreCase("Shipping Charge"));

				SoftAssert.checkConditionAndContinueOnFailure("Tracking column is present",
						getAction().getText(TRACKING_COL_NAME).equalsIgnoreCase("Tracking"));

				SoftAssert.checkConditionAndContinueOnFailure("Shipping Method is present",
						getAction().getText(SHIPPING_METHOD_COL_NAME).equalsIgnoreCase("Shipping Method"));

				SoftAssert.checkConditionAndContinueOnFailure("Shipping Last Name is present",
						getAction().getText(SHIPPING_LAST_NAME_COL_NAME).equalsIgnoreCase("Shipping Last Name"));
				if(!FrameworkProperties.TEST_ENV.equalsIgnoreCase("PROD")){
					Logger.log("Verify order details data",TestStepType.STEP);
					WebElement orderdetailstable = getAction().findElement(ORDER_DETAILS_TABLE);
					List<WebElement> SC_tables = orderdetailstable.findElements(By.tagName("tbody"));
					int child_table_count = SC_tables.size();
					for(int t=0; t<child_table_count; t++){
						List<WebElement> order_details_table_rows = SC_tables.get(t).findElements(By.tagName("tr"));
						int order_details_table_rows_count = order_details_table_rows.size();
						JSONArray ItemSummaryArray = orderSummary.getJSONArray("ItemSummary");
						Logger.log("Assert the number of rows in item summary matches with database",TestStepType.VERIFICATION_STEP);
						PageAssert.verifyEqual(child_table_count*(order_details_table_rows_count-1) , ItemSummaryArray.length());

						/***
						 * verify value
						 ***/
						for (int i=t; i<order_details_table_rows_count+t-1; i++){
							try {
								JSONObject tempObject = (JSONObject)itemSummaryArray.getJSONObject(i);
								fullfilby_ws = tempObject.getString("FulfillBy");
							}
							catch (Exception e) {
								e.printStackTrace();
							}


							List<WebElement> order_details_table_columns = order_details_table_rows.get(i+1-t).findElements(By.tagName("td"));


							item_status = Utility.getLineItemStatusDescription(item_Status.get(i));
							Logger.log("Verify Item Status "+ item_Status.get(i)+" in Database matches with description in application "+item_status, TestStepType.STEP);
							PageAssert.verifyEqual(item_status, order_details_table_columns.get(0).getText());

							Logger.log("Verify Item Description is"+ item_Description.get(i)+" in Database", TestStepType.STEP);
							PageAssert.verifyEqual(item_Description.get(i), order_details_table_columns.get(1).getText());

							Logger.log("Verify SKU is"+ sKU.get(i)+" in Database", TestStepType.STEP);
							PageAssert.verifyEqual(sKU.get(i), order_details_table_columns.get(2).getText());

							Logger.log("Verify Quantity is"+ qty.get(i)+" in Database", TestStepType.STEP);
							PageAssert.verifyEqual(qty.get(i), order_details_table_columns.get(3).getText());

							Logger.log("Verify Price is"+ price.get(i)+" in Database", TestStepType.STEP);
							PageAssert.verifyEqual("$"+formatter.format(price.get(i)), order_details_table_columns.get(4).getText());

							//need to know what's the numbers after fulfillment method
							Logger.log("Verify Fulfillment is"+ fullfilby_ws+"  from Web Services", TestStepType.STEP);
							PageAssert.verifyEqual(order_details_table_columns.get(5).getText(), fullfilby_ws);

							Logger.log("Verify Shipping Charge is"+ shipping_Charge.get(i)+" in Database", TestStepType.STEP);
							PageAssert.verifyEqual("$"+String.format("%.2f", shipping_Charge.get(i)), order_details_table_columns.get(6).getText());


							if(Tracking.size()==0){
								tracking_number = "";
							}else{
								tracking_number = Tracking.get(i);
							}

							if(Shipping_Method.size()==0){
								carrier = "";
							}else{
								carrier = Shipping_Method.get(i);
							}
							Logger.log("Verify Tracking is"+ tracking_number+" in Database", TestStepType.STEP);
							PageAssert.verifyEqual(tracking_number.trim(), order_details_table_columns.get(7).getText());

							Logger.log("Verify Shipping Method is"+ carrier+" in Database", TestStepType.STEP);
							PageAssert.verifyEqual(carrier, order_details_table_columns.get(8).getText());
						}
					}
				}
			}else{
				PageAssert.fail("Retrieval Data from Database failed");
			}

		}else{

			Logger.log("There are no Adjustments Information in Database", TestStepType.STEP);
		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return this;	

	}


	private OrderCharge executeQuery(String sql){
		OrderCharge ordCharge = new OrderCharge();

		return ordCharge;
	}
	private HashMap<String,String> executeQuery(String sqlToExecute,ArrayList<String>parameters){

		HashMap<String,String>sqlReturnValues=new HashMap<String,String>();
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		try {
			if(conn!=null){
				st = conn.prepareStatement(sqlToExecute);
				st.execute();
				rs = st.getResultSet();
				while(rs.next()){
					for(String param:parameters){
						if(rs.getString(param)!=null){
							sqlReturnValues.put(param, rs.getString(param));
						}else{
							sqlReturnValues.put(param, "");
						}
					}
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		return sqlReturnValues;

	}

	private void verifyCustomerInformation(String OrderID, String storeId) throws SQLException, ParseException{
		String customerName = null;
		String customerAddress = null;
		String phoneNumber = null ;
		String email_Address = null;
		OrderID = OrderID.replaceAll("[^\\d.]", "");
		String sql ="select cci.FIRST_NM, cci.LAST_NM, cci.ADDR_LINE_1, cci.ADDR_LINE_2, cci.ADDR_LINE_3, cci.CITY, "
				+ "cci.STATE_CD, cci.ZIP_CD, cci.PHONE_1, cci.EMAIL_ADDR_1 "
				+ "from ord o, customer_contact_info cci "
				+ "where o. BILLING_ADDRESS_ID = cci.ADDRESS_ID and o.site_gen_ord_id ="+OrderID;
		getAction().waitFor(1000);
		ArrayList<String> parameters = new ArrayList<String>();
		parameters.add("FIRST_NM");
		parameters.add("LAST_NM");
		parameters.add("ADDR_LINE_1");
		parameters.add("ADDR_LINE_2");
		parameters.add("CITY");
		parameters.add("STATE_CD");
		parameters.add("ZIP_CD");
		parameters.add("PHONE_1");
		parameters.add("EMAIL_ADDR_1");
		HashMap<String,String> queryResults = executeQuery(sql,parameters);

		customerName=queryResults.get("FIRST_NM")+" "+ queryResults.get("LAST_NM");
		customerAddress =queryResults.get("ADDR_LINE_1")+", "+ queryResults.get("ADDR_LINE_2")+queryResults.get("CITY")+ ", "+
				queryResults.get("STATE_CD")+ ", "+queryResults.get("ZIP_CD");
		phoneNumber=queryResults.get("PHONE_1");
		email_Address=queryResults.get("EMAIL_ADDR_1");

		SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Name for Order: " + OrderID, customerName.replaceAll("\\s", "").equalsIgnoreCase(getAction().getText(CUSTOMER_NAME).replaceAll("\\s", "")));
		SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Address for Order: " + OrderID, customerAddress.replaceAll("\\s", "").replace(",", "")
				.equals(getAction().getText(CUSTOMER_ADDRESS).replace("\n", " ").replace("\r", " ").replaceAll("\\s", "").replace(",", "")));
		SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Phone Title is Present", getAction().getText(CUSTOMER_PHONE_TITLE).equals("Phone"));
		SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Phone Number is Present", phoneNumber.equals(getAction().getText(CUSTOMER_PHONE_NUMBER).replaceAll("[\\D]", "")));
		SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Email Text is Present", getAction().getText(CUSTOMER_EMAIL_TITLE).equals("Email"));
		SoftAssert.checkConditionAndContinueOnFailure("Verify Customer Email Address is Present", email_Address.equals(getAction().getText(CUSTOMER_EMAIL_ADDRESS)));
	}

	private String getStoreName(String store){
		String storeName="";
		if(store.equals("10153")){
			storeName = "Sears";
		}else if(store.equals("10151")){
			storeName = "KMart";
		}else if(store.equals("30151")){
			storeName = "Kmart";
		}else if(store.equals("30165")){
			storeName = "Kmart (PR)";
		}else if(store.equals("10171")){
			storeName = "Outlet";
		}else if(store.equals("10175")){
			storeName = "My Gofer 3";
		}else if(store.equals("10199")){
			storeName = "B2BGC";
		}else if(store.equals("10165")){
			storeName = "Sears PR";
		}else if(store.equals("10191")){
			storeName = "Sears SCO";
		}else if(store.equals("9305")){
			storeName = "ShopYourWay Mall";
		}else if(store.equals("10195")){
			storeName = "Affinity";
		}else if(store.equals("10156")){
			storeName = "TGI";
		}else if(store.equals("10181")){
			storeName = "Delver";
		}else if(store.equals("10197")){
			storeName = "Skymall";
		}else if(store.equals("10182")){
			storeName = "Delver Mkt Place";
		}else if(store.equals("10188")){
			storeName = "Case Management Git Card";
		}else if(store.equals("10196")){
			storeName = "Full Line Commercial";
		}else if(store.equals("20101")){
			storeName = "Multi Channel Fulfill by Sears";
		}else if(store.equals("20195")){
			storeName = "Affinity Kmart";
		}else if(store.equals("30153")){
			storeName = "Sears (ShopSears)";
		}else if(store.equals("40153")){
			storeName = "Sears.com";
		}else if(store.equals("40154")){
			storeName = "Sears (MarketPlace";
		}else if(store.equals("50153")){
			storeName = "Sears Mobile";
		}else if(store.equals("50151")){
			storeName = "KMart Mobile";
		}
		//kana legacy store type
		else if(store.equals("10152")){
			storeName = "KCON";
		}else if(store.equals("10161")){
			storeName = "My Gofer 2";
		}else if(store.equals("30154")){
			storeName = "Shopsears market place items";
		}
		return storeName;

	}

	private void verifyOrderDetailsSection(String OrderID,String storeId) throws SQLException, ParseException{

		OrderID = OrderID.replaceAll("[^\\d.]", "");
		String sql_ordersummary ="select o.site_gen_ord_id, o.site_id, o.PLACEMENT_TS, os.order_sts_ds, ors.ASSOC_ID, ors.SOURCE, cpi.SYWR_NUMBER "
				+ "from ord o, order_sts os, order_source ors, customer_pgm_info cpi "
				+ "where o.order_sts_cd = os.order_sts_cd and o.order_id = ors.order_id and o.BILLING_ADDRESS_ID = cpi.address_id "
				+ "and o.site_gen_ord_id = "+OrderID;

		ArrayList<String> parameters = new ArrayList<String>();
		parameters.add("site_gen_ord_id");
		parameters.add("site_id");
		parameters.add("PLACEMENT_TS");
		parameters.add("order_sts_ds");
		parameters.add("SOURCE");
		parameters.add("ASSOC_ID");
		parameters.add("SYWR_NUMBER");
		HashMap<String,String>queryResults = executeQuery(sql_ordersummary,parameters);
		String orderNumber=queryResults.get("site_gen_ord_id");
		String store = queryResults.get("site_id");
		String storeName=getStoreName(store);
		String purchaseTimeStamp=queryResults.get("PLACEMENT_TS");
		String orderStatus=queryResults.get("order_sts_ds");
		String orderSource=queryResults.get("SOURCE");
		String assoc_ID=queryResults.get("ASSOC_ID");
		String loyaltyNumber=queryResults.get("SYWR_NUMBER");
		PageAssert.textPresent(ORDER_SUMMARY_TITLE_TEXT, "Order Summary");
		Logger.log("Verify Order Summary Text is Present", TestStepType.STEP);

		getAction().scrollTo(ORDER_SUMMARY_TITLE_TEXT);
		if(orderNumber!=null || store!=null || purchaseTimeStamp!=null || orderStatus!=null 
				|| orderSource!=null || assoc_ID!=null || loyaltyNumber!=null){
			if(getAction().isVisible(ORDER_SUMMARY_TABLE)){
				Logger.log("Verify Order Summary Table is Visible", TestStepType.STEP);
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Number Text is Present", getAction().getText(ORDER_SUMMARY_ORDER_NUMBER_TEXT).equals("Order Number"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Number in Order Summary", orderNumber.equals(getAction().getText(ORDER_SUMMARY_ORDER_NUMBER)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Store Text is Present", getAction().getText(ORDER_SUMMARY_STORE_TEXT).equals("Store"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Store Name in Order Summary",storeName.equals(getAction().getText(ORDER_SUMMARY_STORE_NAME)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Purchase Date Text is Present",getAction().getText(ORDER_SUMMARY_PURCHASE_DATE_TEXT).equals("Purchase Date"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Purchase Date in Order Summary",purchaseTimeStamp.split(" ")[0].equals(sdf1.format(sdf2.parse(getAction().getText(ORDER_SUMMARY_PURCHASE_DATE)))));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Status Text is Present",(getAction().getText(ORDER_SUMMARY_ORDER_STATUS_TEXT).equals("Order Status")));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Satus in Order Summary",orderStatus.equals(getAction().getText(ORDER_SUMMARY_ORDER_STATUS)));

				//getAction().waitFor(1000);
				SoftAssert.checkConditionAndContinueOnFailure("Verify 'Order Placed By' Text is Present",getAction().isVisible(ORDER_SUMMARY_PLACED_BY_TEXT));

				if(orderSource !=null && orderSource=="CCN"){
					if(isNumberOfAsscoID(assoc_ID)){
						String OrderPlacedBy = assoc_ID;
						SoftAssert.checkConditionAndContinueOnFailure("Verify Order Placed By " +OrderPlacedBy +" in Order Summary",OrderPlacedBy.equals(getAction().getText(ORDER_SUMMARY_PLACED_BY)));
					}

				}else if(orderSource !=null && orderSource!="CCN"){
					if(!isNumberOfAsscoID(assoc_ID)){
						String OrderPlacedBy = "CUSTOMER";
						SoftAssert.checkConditionAndContinueOnFailure("Verify Order Placed By " +OrderPlacedBy +" in Order Summary",OrderPlacedBy.equals(getAction().getText(ORDER_SUMMARY_PLACED_BY)));
					}
				}else{
					Logger.log("Order Placed By Orther Source", TestStepType.STEP);
				}

			}else{

				PageAssert.fail("Retrieval Data from Database failed");
				Logger.log("Failed retrieval data for Order Summary ", TestStepType.STEP);

			}
		}else{

			Logger.log("There are no order summary information in Database", TestStepType.STEP);

		}
		//getAction().waitFor(2000);
	}

	private void verifyOrderCharges(String OrderID,String storeId) throws SQLException,ParseException{
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection(); 
		double mt_current = 0.0;
		double sc_current = 0.0;
		double dc_current = 0.0;
		double ic_current = 0.0;
		double tax_current = 0.0;
		double ef_current = 0.0;
		double ship_tax_orignial = 0.0;
		double all_list_price_sum = 0.0;
		double mt_original = 0.0;
		double sc_original = 0.0;
		double dc_original = 0.0;
		double ic_original = 0.0;
		double tax_original = 0.0;
		double instal_original_sum = 0.0;
		double environment_fee_sum = 0.0;
		double ship_adj_sum = 0.0;
		double sales_adj_sum  = 0.0;
		double sales_tax_adj_sum  = 0.0;
		double total_original = 0.0;
		double total_original_cal = 0.0;
		double total_current_cal = 0.0;
		double adjst_amt = 0.0;
		double discount_amt_sum = 0.0;
		double total_adjustment_discount = 0.0;
		double total_adjustment_discount_percentage = 0.0;
		ArrayList<Double> order_total = new ArrayList<Double>();
		ArrayList<Double> discount_amt_list = new ArrayList<Double>();
		ArrayList<Double> salesadjust = new ArrayList<Double>();
		ArrayList<Double> shipadjust = new ArrayList<Double>();
		ArrayList<Double> salestaxadjust = new ArrayList<Double>();
		ArrayList<Double> ef_original = new ArrayList<Double>();
		ArrayList<String>  order_item_type = new ArrayList<String>();
		ArrayList<Double>  installlation = new ArrayList<Double>();

		String general_charge_sql = "select o.SHIPPING_AMT, o.TAX_AMT, o.SHIPPING_TAX_AMOUNT, o.TOTAL_AMOUNT, "
				+ "o.ADJMNT_AMT from ord o where o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String order_total_sql = "select oi.LIST_PRICE from ord o, ord_item oi where o.order_id = oi.order_id "
				+ "and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String sales_adjustment_sql = "select r.TOTAL_CREDIT_AMT from ord o, ord_item oi, rma r, rma_item ri, sales_check_tran_file sctf "
				+ "where oi.order_id = o.order_id and oi.order_item_id =ri.order_item_id and r.rma_id = ri.rma_id "
				+ "and r.tran_file_id = sctf.tran_file_id and r.rma_type = 'SLADJ' and site_gen_ord_id = ?	and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String ship_adjustment_sql = "select r.TOTAL_CREDIT_AMT from ord o, ord_item oi, rma r, rma_item ri, sales_check_tran_file sctf "
				+ "where oi.order_id = o.order_id and oi.order_item_id =ri.order_item_id and r.rma_id = ri.rma_id "
				+ "and r.tran_file_id = sctf.tran_file_id and r.rma_type = 'SHADJ' and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String sales_tax_adjustment_sql = "select r.TOTAL_CREDIT_AMT from ord o, ord_item oi, rma r, rma_item ri, sales_check_tran_file sctf "
				+ "where oi.order_id = o.order_id and oi.order_item_id =ri.order_item_id and r.rma_id = ri.rma_id "
				+ "and r.tran_file_id = sctf.tran_file_id and r.rma_type = 'TADJ' and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String environment_fee_sql = "select oi.env_fee from ord o, ord_item oi where oi.order_id = o.order_id and oi.env_fee >= 0 "
				+ "and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String discount_sql ="select AMOUNT from ord_item_discount where order_item_id in (select order_item_id "
				+ "from ord_item where order_id in (select order_id from ord where site_gen_ord_id = ?))  ";

		String delivery_charge_sql = "select oi.LIST_PRICE from ord o, ord_item oi where o.order_id = oi.order_id and "
				+ "oi.ITEM_NM like 'HOME DELIVERY CHARGE' and LIST_PRICE > 0 and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$'";

		String installation_sql = "select oi.ORDER_ITEM_TYP, oi.LIST_PRICE from ord o, ord_item oi where o.order_id = oi.order_id and oi.ITEM_NM like '%installation%' "
				+ "and oi.LIST_PRICE > 0 and o.site_gen_ord_id = ? and o.site_gen_ord_id REGEXP '^-?[0-9]+$';";
		try {
			if(conn!=null){
				st = conn.prepareStatement(general_charge_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();

				//getAction().waitFor(1000);
				while(rs.next()){
					sc_original = rs.getDouble("SHIPPING_AMT");
					adjst_amt = rs.getDouble("ADJMNT_AMT");
					ship_tax_orignial = rs.getDouble("SHIPPING_TAX_AMOUNT");
					tax_original = rs.getDouble("TAX_AMT")+ship_tax_orignial;
					total_original = rs.getDouble("TOTAL_AMOUNT");
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if(conn!=null){
				st = conn.prepareStatement(order_total_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();

				//getAction().waitFor(1000);
				while(rs.next()){
					order_total.add(rs.getDouble("LIST_PRICE"));}
				for(double ot:order_total){
					all_list_price_sum+= ot;}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null){
				st = conn.prepareStatement(delivery_charge_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				//getAction().waitFor(1000);
				while(rs.next()){
					dc_original = rs.getDouble("LIST_PRICE");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(dc_original==0.0){	
			dc_original = 0.00;
		}
		System.out.println("Original Delivery Charge: "+dc_original);

		try {

			if(conn!=null){
				st = conn.prepareStatement(installation_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				//getAction().waitFor(1000);
				while(rs.next()){
					order_item_type.add(rs.getString("ORDER_ITEM_TYP"));
					installlation.add(rs.getDouble("LIST_PRICE"));}
				for(String oit:order_item_type){
					System.out.println("type: "+oit);}
				for(int i = 0; i< order_item_type.size(); i++){
					if(order_item_type.get(i).equals("IM")){
						ic_original = installlation.get(i);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for(double ins:installlation){
			instal_original_sum += ins;
		}
		if(ic_original==0.0){
			ic_original = 0.00;
		}
		mt_original=all_list_price_sum-ic_original;


		//Environment Fee:
		try{
			if(conn!=null){
				st = conn.prepareStatement(environment_fee_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				//getAction().waitFor(2000);
				while(rs.next()){
					ef_original.add(rs.getDouble("env_fee"));}
				if(ef_original.size()>0){
					for(Double e: ef_original){
						environment_fee_sum =environment_fee_sum+e;}
				}else{
					environment_fee_sum = 0.00;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		//Shipping Adjustment:
		try{
			if(conn!=null){
				st = conn.prepareStatement(ship_adjustment_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				//getAction().waitFor(1000);
				while(rs.next()){
					shipadjust.add(rs.getDouble("TOTAL_CREDIT_AMT"));
				}
				if(shipadjust.size()>0){
					for(Double sa: salesadjust){
						System.out.println(sa);
						ship_adj_sum += sa;}
				}else{
					ship_adj_sum = 0.00;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		//Sales Adjustment
		try{
			if(conn!=null){
				st = conn.prepareStatement(sales_adjustment_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				//getAction().waitFor(1000);
				while(rs.next()){
					salesadjust.add(rs.getDouble("TOTAL_CREDIT_AMT"));
				}

				if(salesadjust.size()>0){
					for(Double salesadj: salesadjust){
						System.out.println(salesadj);
						sales_adj_sum += salesadj;}
				}else{
					sales_adj_sum = 0.00;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		//Sales Tax Adjustment
		try{
			if(conn!=null){
				st = conn.prepareStatement(sales_tax_adjustment_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				//getAction().waitFor(1000);
				while(rs.next()){
					salestaxadjust.add(rs.getDouble("TOTAL_CREDIT_AMT"));
				}
				if(salestaxadjust.size()>0){
					for(Double st1: salestaxadjust){
						System.out.println(st1);
						sales_tax_adj_sum += st1;}
				}else{
					sales_tax_adj_sum = 0.00;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//Discount
		try {
			if(conn!=null){
				st = conn.prepareStatement(discount_sql);
				OrderID = OrderID.replaceAll("[^\\d.]", "");
				st.setString (1, OrderID.trim());
				st.execute();
				rs = st.getResultSet();
				//getAction().waitFor(1000);
				while(rs.next()){
					discount_amt_list.add(rs.getDouble("AMOUNT"));
				}
				if(discount_amt_list.size()>0){
					for(Double d: discount_amt_list){
						System.out.println(d);
						discount_amt_sum += d;}
				}	
			}else{
				discount_amt_sum = 0.00;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		mt_current = mt_original-sales_adj_sum;
		sc_current = sc_original-Math.abs(adjst_amt);
		dc_current = dc_original;
		ic_current = ic_original;
		tax_current = tax_original-ship_adj_sum-sales_tax_adj_sum;
		ef_current = environment_fee_sum;

		total_original_cal = mt_original+sc_original+dc_original+ic_original+tax_original+environment_fee_sum;
		total_current_cal = total_original_cal-ship_adj_sum-sales_adj_sum-sales_tax_adj_sum-Math.abs(discount_amt_sum);

		total_adjustment_discount= Math.abs(ship_adj_sum)+ Math.abs(discount_amt_sum)+Math.abs(sales_adj_sum)+Math.abs(sales_tax_adj_sum);
		if(total_adjustment_discount!=0.0){
			total_adjustment_discount_percentage= (total_adjustment_discount/total_original)*100;}
		if(mt_original!=0.0){
			Logger.log("Verify Order Charges Text is Present", TestStepType.STEP);
			PageAssert.textPresent(ORDER_CHARGES_TITLE_TEXT, "Order Charges");
			getAction().scrollTo(ORDER_CHARGES_TITLE_TEXT);
			if(getAction().isVisible(ORDER_CHARGES_TABLE)){
				Logger.log("Verify Order Charges are displayed", TestStepType.STEP);
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Charges Description Column Name is Present",getAction().getText(ORDER_CHARGES_DESCRIPTION_COL_NAME).equals("Description"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Charges Original Column Name is Present",getAction().getText(ORDER_CHARGES_ORIGINAL_COL_NAME).equals("Original"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Charges Current Column Name is Present",getAction().getText(ORDER_CHARGES_CURRENT_COL_NAME).equals("Current"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Charges Merchandise Total Row Name is Present",getAction().getText(MERCHANDISE_TOTAL_ROW_NAME).equals("Merchandise Total"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Charges Shipping Charge Row Name is Present",getAction().getText(SHIPPING_CHARGE_ROW_NAME).equals("Shipping Charge"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Charges Delivery Charge Row Name is Present",getAction().getText(DELIVERY_CHARGE_ROW_NAME).equals("Delivery Charge"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Charges Installation Charge Row Name is Present",getAction().getText(INSTALLATION_CHARGE_ROW_NAME).equals("Installation Charge"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Charges Tax Row Name is Present",getAction().getText(TAX_ROW_NAME).equals("Tax"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Charges Environmental Fee Row Name is Present",getAction().getText(ENVIRONMENTAL_FEE_ROW_NAME).equals("Environmental Fee"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Charges Total Row Name is Present",getAction().getText(TOTAL_ROW_NAME).equals("Total"));

				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Merchandise Original Total in Order Summary",getAction().getText(MERCHANDISE_TOTAL_ORIGINAL_PRICE).equals("$"+formatter.format(mt_original)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Shipping Charge Original Price in Order Summary",getAction().getText(SHIPPING_CHARGE_ORIGINAL_PRICE).equals("$"+df.format(sc_original)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Delivery Charge Original Price in Order Summary",getAction().getText(DELIVERY_CHARGE_ORIGINAL_PRICE).equals("$"+df.format(dc_original)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Installation Charge Original Price in Order Summary",getAction().getText(INSTALLATION_CHARGE_ORIGINAL_PRICE).equals("$"+df.format(ic_original)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Tax Original Price in Order Summary",getAction().getText(TAX_ORIGINAL_PRICE).equals("$"+formatter.format(tax_original)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Environmental Original Fee in Order Summary",getAction().getText(ENVIRONMENTAL_FEE_ORIGINAL_PRICE).equals("$"+df.format(environment_fee_sum)));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Original Total Price in Order Summary",getAction().getText(TOTAL_ORIGINAL_PRICE).equals("$"+formatter.format(total_original_cal)));

				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Merchandise Current Total in Order Summary",getAction().getText(MERCHANDISE_TOTAL_CURRENT_PRICE).equals("$"+formatter.format(mt_current)));

				if(getAction().getText(SHIPPING_CHARGE_CURRENT_PRICE).contains("(") && getAction().getText(SHIPPING_CHARGE_CURRENT_PRICE).contains(")")){
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Shipping Charge Current Price in Order Summary",getAction().getText(SHIPPING_CHARGE_CURRENT_PRICE).equals("$"+df.format(Math.abs(sc_current)+")")));

				}else{
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Shipping Charge Current Price in Order Summary",getAction().getText(SHIPPING_CHARGE_CURRENT_PRICE).equals("$"+df.format(sc_current)));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Delivery Charge Current Price in Order Summary",getAction().getText(DELIVERY_CHARGE_CURRENT_PRICE).equals("$"+df.format(dc_current)));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Installation Charge Current Price in Order Summary",getAction().getText(INSTALLATION_CHARGE_CURRENT_PRICE).equals("$"+df.format(ic_current)));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Tax Current Price in Order Summary",getAction().getText(TAX_CURRENT_PRICE).equals("$"+formatter.format(tax_current)));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Environmental Current Fee in Order Summary",getAction().getText(ENVIRONMENTAL_FEE_CURRENT_PRICE).equals("$"+df.format(ef_current)));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Current Total Price in Order Summary",getAction().getText(TOTAL_CURRENT_PRICE).equals("$"+formatter.format(total_current_cal)));
					if(total_adjustment_discount>0){
						SoftAssert.checkConditionAndContinueOnFailure("Total Adjustment / Discount Applied and Percentage in Order Summary",getAction().getText(TOTAL_ADJUSTMENT_DISCOUNT).equals("Total Adjustment / Discount Applied : $"+formatter.format(total_adjustment_discount)+
								" ("+formatter.format(total_adjustment_discount_percentage)+"%)"));
						Logger.log("Total Adjustment / Discount Applied and Percentage in Order Summary", TestStepType.STEP);}
				}
			}
		}

		else{
			PageAssert.fail("Retrieval Data from Database failed");
			Logger.log("Failed retrieval data for Order Charge ", TestStepType.STEP);
		}
		//getAction().waitFor(1000);
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}

	}

	private void verifyItemDiscount(String OrderID,String storeId) throws SQLException,ParseException{
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		ArrayList<String> description  =  new ArrayList<String>();
		ArrayList<Double> amount  =  new ArrayList<Double>();
		//getAction().waitFor(1000);
		String sql_discount ="select PROMOTION_NAME, AMOUNT "
				+ "from ord_item_discount where order_item_id in (select order_item_id "
				+ "from ord_item where order_id in (select order_id from ord where site_gen_ord_id = ?) ) ";

		try {
			if(conn!=null){
				st = conn.prepareStatement(sql_discount);
				st.setString (1, OrderID);
				st.execute();
				rs = st.getResultSet();

				while(rs.next()){

					description.add(rs.getString("PROMOTION_NAME"));
					amount.add(rs.getDouble("AMOUNT"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//getAction().waitFor(3000);

		if(description.size()!=0 || amount.size()!=0){
			SoftAssert.checkConditionAndContinueOnFailure("Verify Discounts Text is Present",getAction().getText(DISCOUNTS_TITLE_TEXT).equals("Discounts"));
			getAction().scrollTo(DISCOUNTS_TITLE_TEXT);
			if(getAction().isVisible(DISCOUNTS_TABLE_O)){
				Logger.log("Verify Discount details", TestStepType.STEP);
				SoftAssert.checkConditionAndContinueOnFailure("Verify Discount Description Column Name is Present",getAction().getText(DISCOUNTS_DESCRIPTION_COL_NAME).equals("Discount Description"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Discount Amount Column Name is Present",getAction().getText(DISCOUNTS_AMOUNT_COL_NAME).equals("Amount"));

				WebElement discountsstable = getAction().findElement(DISCOUNTS_TABLE_CONTENT );
				List<WebElement> disct_table_rows = discountsstable.findElements(By.tagName("tr"));
				int disct_table_rows_count = disct_table_rows.size();

				for (int i=0; i<disct_table_rows_count; i++){

					List<WebElement> disct_table_columns = disct_table_rows.get(i).findElements(By.tagName("td"));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Description in Discount",description.get(i).equals(disct_table_columns.get(0).getText()));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Amount in Discount",disct_table_columns.get(1).getText().equals("($"+formatter.format(Math.abs(amount.get(i)))+")"));

				}	
			}else{
				PageAssert.fail("Retrieval Data from Database failed");
			}
		}else{
			SoftAssert.checkElementAndContinueOnFailure(NO_DISCOUNTS_RESPONSE, "No discount records found", CheckLocatorFor.isPresent); 

		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}

	private void verifyPaymentSection(String OrderID,String storeId) throws SQLException,ParseException{
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		String Sql_payment ="select pm.PAYMENT_METHOD_DS, pm.brand, pi.TOTAL_AMT "
				+ "from pmt_method pm, payment_instruction pi, ord o "
				+ "where o.order_id = pi.order_id and pi.payment_method_id = pm.payment_method_id "
				+ "and site_gen_ord_id = ?";
		ArrayList<String> payment_type  =  new ArrayList<String>();
		ArrayList<String> card_type =  new ArrayList<String>();
		ArrayList<Double> payment_amount =  new ArrayList<Double>();

		//String payment_type_ws = null;
		String card_number_ws = null;
		//String card_type_ws = null;
		String name_on_card_ws = null;
		String expire_month_ws = null;
		String expire_year_ws = null;
		//String pay_amount_ws = null;
		String adj_credit_pts_ws = null;
		String adj_credit$_ws = null;

		JSONObject orderSummary = null;

		/****
		 * data from mysql db
		 ****/
		try {
			if(conn!=null){
				st = conn.prepareStatement(Sql_payment);

				st.setString (1, OrderID);
				st.execute();
				rs = st.getResultSet();
				//getAction().waitFor(1000);
				while(rs.next()){

					payment_type.add(rs.getString("PAYMENT_METHOD_DS"));
					card_type.add(rs.getString("brand"));
					payment_amount.add(rs.getDouble("TOTAL_AMT"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//getAction().waitFor(3000);

		/***
		 * data from web services
		 ****/
		System.out.println("Order ID: "+OrderID+" Store ID: "+storeId);
		orderSummary = verifysoapdetails(OrderID, storeId);
		if(payment_type!=null || card_type!=null || payment_amount!=null || card_number_ws!=null || name_on_card_ws!=null
				|| expire_month_ws!=null || expire_year_ws!=null || adj_credit_pts_ws!=null || adj_credit$_ws!=null){	
			/***
			 * verify text present
			 ***/
			SoftAssert.checkConditionAndContinueOnFailure("Verify Payments Text is Present",getAction().getText(PAYMENTS_TITLE_TEXT).equals("Payments"));

			if( getAction().isVisible(PAYMENTS_TABLE_O)){
				Logger.log("Verify Payments Table is Visible", TestStepType.STEP);
				SoftAssert.checkConditionAndContinueOnFailure("Verify Payments Type Column Name is Present",getAction().getText(PAYMENT_TYPE_COL_NAME).equals("Payment Type"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Payments Card Number Column Name is Present",getAction().getText(CARD_NUMBER_COL_NAME).equals("Card Number"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Payments Card Type Column Name is Present",getAction().getText(CARD_TYPE_COL_NAME).equals("Card Type"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Name on Card Column Name is Present",getAction().getText(NAME_ON_CARD_COL_NAME).equals("Name on Card"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Expiration Date Column Name is Present",getAction().getText(EXPIRATION_DATE_COL_NAME).equals("Expiration Date"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Payment Amount Column Name is Present",getAction().getText(PAYMENT_AMOUNT_COL_NAME).equals("Payment Amount"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Adj/Credit Points Column Name is Present",getAction().getText(CREDIT_POINTS_COL_NAME).equals("Adj/Credit pts"));
				SoftAssert.checkConditionAndContinueOnFailure("Verify Adj/Credit $ Column Name is Present",getAction().getText(CREDIT_COL_NAME).equals("Adj/Credit $"));

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
						System.out.println("paymentDetailsObj : "+paymentDetailsObj);
						card_number_ws =(String.valueOf(paymentDetailsObj.get("MaskedCardNumber")));
						System.out.println("card Number original : "+String.valueOf(paymentDetailsObj.get("MaskedCardNumber")));
						adj_credit_pts_ws = String.valueOf(paymentDetailsObj.getInt("AdjCreditSywrPoints"));
						adj_credit$_ws = String.valueOf(paymentDetailsObj.getDouble("AdjCreditDollarValue"));

						List<WebElement> payment_table_columns = payment_table_rows.get(0).findElements(By.tagName("td"));

						SoftAssert.checkConditionAndContinueOnFailure("Verify Payments Type in Payments",payment_type.get(0).equals(payment_table_columns.get(0).getText()));

						SoftAssert.checkConditionAndContinueOnFailure("Verify Card Number in Payments",card_number_ws.equals(payment_table_columns.get(2).getText()));

						if(payment_type.get(0).equalsIgnoreCase("CreditCard"))
						{
							name_on_card_ws = paymentDetailsObj.getString("NameonTheCard");
							expire_month_ws = String.valueOf(paymentDetailsObj.getInt("ExpiryMonth"));
							expire_year_ws = String.valueOf(paymentDetailsObj.getInt("ExpiryYear"));

							SoftAssert.checkConditionAndContinueOnFailure("Verify Card Type in Payments",card_type.get(0).equals(payment_table_columns.get(3).getText()));

							SoftAssert.checkConditionAndContinueOnFailure("Verify Name on Card in Payments",name_on_card_ws.equals(payment_table_columns.get(4).getText()));	    		    	    		
							SoftAssert.checkConditionAndContinueOnFailure("Verify Expiration Date in Payments",payment_table_columns.get(5).getText().equals("** / ****"));	    		    	    		
						}

						SoftAssert.checkConditionAndContinueOnFailure("Verify Payment Amount in Payments",payment_table_columns.get(6).getText().equals("$"+String.format("%.2f", payment_amount.get(0))));	    		    	    		

						SoftAssert.checkConditionAndContinueOnFailure("Verify Adj/Credit Points in Payments",payment_table_columns.get(7).getText().equals(adj_credit_pts_ws));	    		    	    		

						SoftAssert.checkConditionAndContinueOnFailure("Verify Credit in Payments in Payments",payment_table_columns.get(8).getText().equals("$"+df.format(Double.parseDouble(adj_credit$_ws))));	    		    	    		    		

					}    		

					else if (orderSummary.get("PaymentDetails") instanceof JSONArray)
					{
						JSONArray paymentDetailsArray = orderSummary.getJSONArray("PaymentDetails");

						for (int i=0; i<payment_table_rows_count; i++){
							JSONObject tempObject = (JSONObject)paymentDetailsArray.getJSONObject(i);

							card_number_ws = maskCreditCardNumber(String.valueOf(tempObject.getLong("MaskedCardNumber")));
							adj_credit_pts_ws = String.valueOf(tempObject.getInt("AdjCreditSywrPoints"));
							adj_credit$_ws = String.valueOf(tempObject.getString("AdjCreditDollarValue"));

							List<WebElement> payment_table_columns = payment_table_rows.get(i).findElements(By.tagName("td"));

							if(!payment_type.get(i).equalsIgnoreCase("SywrCard")){

								SoftAssert.checkConditionAndContinueOnFailure("Verify Payments Type in Payments",payment_table_columns.get(0).getText().equals(payment_type.get(i)));	    		    	    		    		
							}
							if(!payment_type.get(i).equalsIgnoreCase("GiftCard")){
								SoftAssert.checkConditionAndContinueOnFailure("Verify Card Number in Payments",card_number_ws.equals(payment_table_columns.get(2).getText()));	    		    	    		    		
							}

							if(payment_type.get(i).equalsIgnoreCase("CreditCard"))
							{
								name_on_card_ws = tempObject.getString("NameonTheCard");
								expire_month_ws = String.valueOf(tempObject.getInt("ExpiryMonth"));
								expire_year_ws = String.valueOf(tempObject.getInt("ExpiryYear"));

								SoftAssert.checkConditionAndContinueOnFailure("Verify Card Type in Payments",card_type.get(i).equals(payment_table_columns.get(3).getText()));	    		    	    		    		
								SoftAssert.checkConditionAndContinueOnFailure("Verify Name on Card in Payments",name_on_card_ws.equals(payment_table_columns.get(4).getText()));	    		    	    		    		

								SoftAssert.checkConditionAndContinueOnFailure("Verify Expiration Date in Payments",payment_table_columns.get(5).getText().equals("** / ****"));	    		    	    		    		
							}

							SoftAssert.checkConditionAndContinueOnFailure("Verify Payment Amount in Payments",payment_table_columns.get(6).getText().equals("$"+String.format("%.2f", payment_amount.get(i))));	    		    	    		    		

							SoftAssert.checkConditionAndContinueOnFailure("Verify Adj/Credit Points in Payments",adj_credit_pts_ws.equals(payment_table_columns.get(7).getText()));	    		    	    		    		

							SoftAssert.checkConditionAndContinueOnFailure("Verify Credit in Payments in Payments",payment_table_columns.get(8).getText().equals("$"+df.format(Double.parseDouble(adj_credit$_ws))));	    		    	    		    		  		
						}
					}

					else{
						Logger.log("Payments Table is not visible", TestStepType.STEP);
						PageAssert.fail("Retrieval Data from Database failed");
					}

				}

				catch (Exception e) {				
					e.printStackTrace();
				}   		 
			}else{

				Logger.log("There is no Adjustments Information in Database", TestStepType.STEP);

			}	
			//getAction().waitFor(1000);
			//getAction().waitFor(1000);
			String item_status = null;
			ArrayList<String> item_Status_dc  =  new ArrayList<String>();
			ArrayList<String> item_Description  =  new ArrayList<String>();
			ArrayList<String> sKU  =  new ArrayList<String>();
			ArrayList<String> qty  =  new ArrayList<String>();
			ArrayList<Double> price  =  new ArrayList<Double>();
			ArrayList<String> fulfilled_By  =  new ArrayList<String>();
			ArrayList<Double> shipping_Charge  =  new ArrayList<Double>();
			ArrayList<String> Tracking  =  new ArrayList<String>();
			ArrayList<String> Shipping_Method  =  new ArrayList<String>();
			//ArrayList<String> Shipping_LName  =  new ArrayList<String>();
			String fullfilby_ws = null;
			String tracking_number = null;
			String carrier = null;
			JSONArray itemSummaryArray=null;
			String Sql ="select oi.ORDER_ITEM_STS_CD, oi.ITEM_NM, oi.ITEM_ID, oi.QUANTITY, oi.SUBTOTAL_AMOUNT, fm.ffm_class_id, oi.SHIPPING_AMT "
					+ "from ord o, ord_item oi, ffm_method fm "
					+ "where o.order_id = oi.order_id and oi.ffm_method_id = fm.ffm_method_id and o.site_gen_ord_id =?";
			String Sql_shipping ="select TRACKING_NUMBER,CARRIER from ship_unit where SHIP_UNIT_ID in (select SHIP_UNIT_ID from ship_invoice_item "
					+ "where order_item_id in (select order_item_id  from ord_item where order_id in (select order_id from ord where site_gen_ord_id =?)))";

			try {
				if(conn!=null){
					st = conn.prepareStatement(Sql);
					OrderID = OrderID.replaceAll("[^\\d.]", "");
					st.setString (1, OrderID.trim());
					st.execute();
					rs = st.getResultSet();
					while(rs.next()){
						item_Status_dc.add(rs.getString("ORDER_ITEM_STS_CD"));
						item_Description.add(rs.getString("ITEM_NM"));
						sKU.add(rs.getString("ITEM_ID"));
						qty.add(rs.getString("QUANTITY"));
						price.add(rs.getDouble("SUBTOTAL_AMOUNT"));
						fulfilled_By.add(rs.getString("ffm_class_id"));
						shipping_Charge.add(rs.getDouble("SHIPPING_AMT"));
						//Shipping_LName.add(rs.getString(""));
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if(conn!=null){
					st = conn.prepareStatement(Sql_shipping);
					OrderID = OrderID.replaceAll("[^\\d.]", "");
					st.setString (1, OrderID.trim());
					st.execute();
					rs = st.getResultSet();
					while(rs.next()){
						Tracking.add(rs.getString("TRACKING_NUMBER"));
						Shipping_Method.add(rs.getString("CARRIER"));
						//Shipping_LName.add(rs.getString(""));
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			//getAction().waitFor(2000);
			if(item_Status_dc!=null || item_Description!=null || sKU!=null || qty!=null || price!=null 
					|| fulfilled_By!=null || shipping_Charge!=null){
				/***
				 * verify text present
				 ***/
				SoftAssert.checkConditionAndContinueOnFailure("Verify Order Details Text is Present",getAction().getText(ORDER_DETAILS_TITLE_TEXT).equals("Order Details"));	    		    	    		    		

				if(getAction().isVisible(ORDER_DETAILS_TABLE)){
					Logger.log("Verify Order Details Table is Visible", TestStepType.STEP);

					//column name
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Details Status Column Name is Present",getAction().getText(STATUS_COL_NAME).equals("Status"));	    		    	    		    		
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Details Description Column Name is Present",getAction().getText(DESCRIPTION_COL_NAME).equals("Description"));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Details SKU Column Name is Present",getAction().getText(SKU_COL_NAME).equals("SKU"));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Details Quantity Column Name is Present",getAction().getText(QTY_COL_NAME).equals("Qty"));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Details Price Column Name is Present",getAction().getText(PRICE_COL_NAME).equals("Price"));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Details Fulfill By Column Name is Present",getAction().getText(FULFILL_BY_COL_NAME).equals("Fulfill By"));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Details Shipping Charge Column Name is Present",getAction().getText(SHIPPING_CHARGE_COL_NAME).equals("Shipping Charge"));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Details Tracking Column Name is Present",getAction().getText(TRACKING_COL_NAME).equals("Tracking"));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Details Shipping Method Column Name is Present",getAction().getText(SHIPPING_METHOD_COL_NAME).equals("Shipping Method"));
					SoftAssert.checkConditionAndContinueOnFailure("Verify Order Details Shipping Last Name Column Name is Present",getAction().getText(SHIPPING_LAST_NAME_COL_NAME).equals("Shipping Last Name"));



					WebElement orderdetailstable = getAction().findElement(ORDER_DETAILS_TABLE);
					List<WebElement> SC_tables = orderdetailstable.findElements(By.tagName("tbody"));
					int child_table_count = SC_tables.size();
					System.out.println("Order Details Table Number is: "+child_table_count);
					for(int t=0; t<child_table_count; t++){
						System.out.println("table "+ (t+1)+"'s content is: ");

						List<WebElement> order_details_table_rows = SC_tables.get(t).findElements(By.tagName("tr"));
						int order_details_table_rows_count = order_details_table_rows.size();
						itemSummaryArray=(JSONArray)orderSummary.opt("ItemSummary");
						/***
						 * verify value
						 ***/

						Logger.log("Verify data from Order Detail Table", TestStepType.STEP);
						for (int i=t; i<order_details_table_rows_count+t-1; i++){
							try {
								JSONObject tempObject1 = (JSONObject)itemSummaryArray.getJSONObject(i);
								fullfilby_ws = tempObject1.getString("FulfillBy");
							}
							catch (Exception e) {
								e.printStackTrace();
							}


							List<WebElement> order_details_table_columns = order_details_table_rows.get(i+1-t).findElements(By.tagName("td"));

							item_status=Utility.getLineItemStatusDescription(item_Status_dc.get(i));

							SoftAssert.checkConditionAndContinueOnFailure("Verify Item Status",item_status.equals(order_details_table_columns.get(0).getText()));
							SoftAssert.checkConditionAndContinueOnFailure("Verify Item Description",item_Description.get(i).equals(order_details_table_columns.get(1).getText()));
							SoftAssert.checkConditionAndContinueOnFailure("Verify SKU",sKU.get(i).equals(order_details_table_columns.get(2).getText().trim()));
							SoftAssert.checkConditionAndContinueOnFailure("Verify Quantity",qty.get(i).equals(order_details_table_columns.get(3).getText().trim()));
							SoftAssert.checkConditionAndContinueOnFailure("Verify Price",order_details_table_columns.get(4).getText().trim().equals("$"+String.format("%.2f", price.get(i))));
							//need to know what's the numbers after fulfillment method
							SoftAssert.checkConditionAndContinueOnFailure("Verify Fulfillment",order_details_table_columns.get(5).getText().equals(fullfilby_ws));
							SoftAssert.checkConditionAndContinueOnFailure("Verify Shipping Charge",order_details_table_columns.get(6).getText().equals("$"+String.format("%.2f", shipping_Charge.get(i))));
							if(Tracking.size()==0){
								tracking_number = "";
							}else{
								tracking_number = Tracking.get(i);
							}

							if(Shipping_Method.size()==0){
								carrier = "";
							}else{
								carrier = Shipping_Method.get(i);
							}
							SoftAssert.checkConditionAndContinueOnFailure("Verify Tracking Number",order_details_table_columns.get(7).getText().equals(tracking_number));

						}
					}
				}else{
					PageAssert.fail("Retrieval Data from Database failed");
				}

			}else{

				Logger.log("There is no Order Details in Database", TestStepType.STEP);
			}
			//getAction().waitFor(1000);

			SoftAssert.checkConditionAndContinueOnFailure("Verify Agent Notes Text is Present",getAction().getText(AGENT_NOTES_TEXT).equals("Agent Notes"));
			SoftAssert.checkElementAndContinueOnFailure(AGENT_NOTES_DROPDOWN_BUTTON, "Agent Notes DropDown Button is Present", CheckLocatorFor.isPresent);
			SoftAssert.checkElementAndContinueOnFailure(AGENT_NOTES_TEXT_AREA, "Agent Notes Text Area is Present", CheckLocatorFor.isPresent);
			SoftAssert.checkElementAndContinueOnFailure(AGENT_NOTES_REQUIREDFIELD_SIGN, "Agent Notes * Sign is Present", CheckLocatorFor.isPresent);
			SoftAssert.checkElementAndContinueOnFailure(AGENT_NOTES_SAVE_BUTTON, "Agent Notes Save Button is Present", CheckLocatorFor.isPresent);
			SoftAssert.checkElementAndContinueOnFailure(AGENT_NOTES_DELETE, "Agent Notes DELETE sign is Present", CheckLocatorFor.isPresent);

			getAction().click(AGENT_NOTES_DROPDOWN_BUTTON);
			getAction().click(AGENT_NOTES_ADD_NEW_BUTTON);
			getAction().type(AGENT_NOTES_TEXT_AREA, "Testing Notes");
			//getAction().waitFor(3000);
			SoftAssert.checkElementAndContinueOnFailure(AGENT_NOTES_SAVE_BUTTON, "Agent Notes Dropdown button is Present", CheckLocatorFor.isPresent);
		}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}

	}

	private void wrapUpAndVerify(){
		selectWrapUpTab();
		SoftAssert.checkConditionAndContinueOnFailure("Verify Category Text is Present", getAction().getText(CATEGORY_TEXT).equals("Category"));
		SoftAssert.checkConditionAndContinueOnFailure("Verify Reason Code Text is Present", getAction().getText(REASON_CODE_TEXT).equals("Reason Code"));

		if(getAction().isVisible(CATEGORY_DROPDOWN)&& getAction().isVisible(REASON_CODE_DROPDOWN)){
			Logger.log("Verify Category Dropdown Select Box is Present ", TestStepType.VERIFICATION_PASSED);
			Logger.log("Verify Reason Code Dropdown Select Box is Present ", TestStepType.VERIFICATION_PASSED);

			getAction().click(CATEGORY_DROPDOWN);
			getAction().selectByVisibleText(CATEGORY_DROPDOWN, "General Inquiry");
			Logger.log("Select General Inquiry Option in Category", TestStepType.VERIFICATION_PASSED);

			getAction().click(REASON_CODE_DROPDOWN);
			getAction().waitFor(1000);
			getAction().selectUsingIndex(REASON_CODE_DROPDOWN, 4);
			Logger.log("Select 'NO Action Needed - General Question' in Reason Code", TestStepType.STEP);
			SoftAssert.checkConditionAndContinueOnFailure("Verify Not Applicable Text is Present", getAction().getText(SELECT_QUEUE_NO_RESPONSE).equals("Not Applicable"));

		}
		SoftAssert.checkConditionAndContinueOnFailure("Verify Select Queue to route Text is Present", getAction().getText(SELECT_QUEUE_TO_ROUTE).equals("Select Queue to route"));

		if(getAction().isVisible(SELECT_QUEUE_ONE_RESPONSE)){
			Logger.log("Verify Selected Queue Name "+getAction().getText(SELECT_QUEUE_ONE_RESPONSE)+" Is Visible ", TestStepType.VERIFICATION_PASSED);
		}else if(getAction().isVisible(SELECT_QUEUE_MULTI_RESPONSE)){
			Logger.log("Verify Selected Queue Name "+getAction().getText(SELECT_QUEUE_MULTI_RESPONSE)+" Are Visible ", TestStepType.VERIFICATION_PASSED);
		}else if(getAction().isVisible(SELECT_QUEUE_NO_RESPONSE)){
			Logger.log("Verify Not Applicable "+"'"+getAction().getText(SELECT_QUEUE_NO_RESPONSE)+"'"+" Is Visible ", TestStepType.VERIFICATION_PASSED);
		}
		SoftAssert.checkElementAndContinueOnFailure(ORDER_NOTES_TEXT_AREA,"Verify Order Notes Text Area is Present", CheckLocatorFor.isPresent);
		getAction().type(ORDER_NOTES_TEXT_AREA, "No action was performed on the order other than opening and closing the order through automation script to monitor production environment.");
		SoftAssert.checkElementAndContinueOnFailure(ORDER_NOTES_REQUIREDFIELD_SIGN, "Verify Order Notes Required Field Sign is Present", CheckLocatorFor.isPresent);

		getAction().click(SELECT_QUEUE_TO_ROUTE);
		getAction().waitFor(1000);
		SoftAssert.checkElementAndContinueOnFailure(ORDER_SUMMARY_TAB, "Verify Order Summary Tab is Present", CheckLocatorFor.isPresent);
		SoftAssert.checkElementAndContinueOnFailure(LINE_ITEM_SALES_CHECKS_TAB, "Verify Line Item/Sales Checks Tab is Present", CheckLocatorFor.isPresent);
		SoftAssert.checkElementAndContinueOnFailure(AUDIT_TRAIL_TAB, "Verify Audit Trail Tab is Present", CheckLocatorFor.isPresent);
		SoftAssert.checkElementAndContinueOnFailure(SYW_MAX_TAB, "Verify SYW Max Tab is Present", CheckLocatorFor.isPresent);
	}


	public OrderDetailsPage orderSummaryPageVerify(String OrderID, String storeId) throws SQLException, ParseException{
		verifyCustomerInformation(OrderID,storeId);
		verifyOrderDetailsSection(OrderID,storeId);
		verifyOrderCharges(OrderID,storeId);
		verifyItemDiscount(OrderID,storeId);
		verifyPaymentSection(OrderID,storeId);
		wrapUpAndVerify();
		return this;
	}



	public OrderDetailsPage agentNotesVerifyPresent(){
		Logger.log("Verify Agent Notes Text is Present", TestStepType.STEP);
		PageAssert.textPresent(AGENT_NOTES_TEXT, "Agent Notes");

		Logger.log("Agent Notes DropDown Button is Present", TestStepType.STEP);
		PageAssert.elementPresent(AGENT_NOTES_DROPDOWN_BUTTON);
		PageAssert.getAction().findElement(AGENT_NOTES_DROPDOWN_BUTTON).isEnabled();

		Logger.log("Agent Notes Text Area is Present", TestStepType.STEP);
		PageAssert.elementPresent(AGENT_NOTES_TEXT_AREA);
		PageAssert.getAction().findElement(AGENT_NOTES_TEXT_AREA).isEnabled();

		Logger.log("Agent Notes * Sign is Present", TestStepType.STEP);
		PageAssert.elementPresent(AGENT_NOTES_REQUIREDFIELD_SIGN);

		Logger.log("Agent Notes Save Button is Present", TestStepType.STEP);
		PageAssert.elementPresent(AGENT_NOTES_SAVE_BUTTON);

		Logger.log("Agent Notes DELETE sign is Present", TestStepType.STEP);
		PageAssert.elementPresent(AGENT_NOTES_DELETE);


		Logger.log("Enter agent notes", TestStepType.STEP);
		getAction().click(AGENT_NOTES_DROPDOWN_BUTTON);
		getAction().click(AGENT_NOTES_ADD_NEW_BUTTON);
		getAction().type(AGENT_NOTES_TEXT_AREA, "Testing Notes");
		getAction().waitFor(3000);
		PageAssert.getAction().findElement(AGENT_NOTES_SAVE_BUTTON).isEnabled();

		return this;

	}

	public JSONObject verifysoapdetails(String OrderID, String storeId)  {
		Logger.log("Fetch order details for order "+OrderID+" from webservice",TestStepType.STEP);
		String url = "http://oms.qa.ch3.s.com/KanaRS/services/Sears-Services";
		Logger.log("Webservice end point - "+url,TestStepType.SUBSTEP);
		String pagerequest="sch:RetrieveOrderSummaryRequest", schemaURL="http://www.shc.com/schema", transactionid="39ee24db-cd9e-47fe-a687-a0d56f3d57ce", username="XXXX",password="XXXXX",  orderid = OrderID, stoteid = storeId, source="msp";

		JSONObject orderSummary=null;
		String result=null;
		JSONObject xmlJSONObj, envelop, body = null, retrieveOrderSummaryResponse, dataArea;

		try {
			result=SOAPRequest.getSOAPRespose(url,SOAPRequest.createSOAPRequest( pagerequest, schemaURL, transactionid, username, password, orderid, stoteid, source));
			Logger.log("Soap Response: "+result, TestStepType.DATA_CAPTURE);
			xmlJSONObj = XML.toJSONObject(result);
			envelop=(JSONObject)xmlJSONObj.get("soapenv:Envelope");
			body=(JSONObject)envelop.get("soapenv:Body");
			retrieveOrderSummaryResponse=(JSONObject)body.get("RetrieveOrderSummaryResponse");
			dataArea=(JSONObject)retrieveOrderSummaryResponse.get("DATAAREA");
			orderSummary=(JSONObject)dataArea.get("OrderSummary");

		} catch (Exception e) {
			try {
				retrieveOrderSummaryResponse=(JSONObject)body.get("ns1:RetrieveOrderSummaryResponse");
				dataArea=(JSONObject)retrieveOrderSummaryResponse.get("DATAAREA");
				orderSummary=(JSONObject)dataArea.get("OrderSummary");
			} catch(Exception e1) {
				e1.printStackTrace();
			}   			

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

	public static boolean validatePhoneNumber(String phoneNo) {
		if (phoneNo.matches("D: "+"\\d{10}")) return true;
		else if(phoneNo.matches("D: "+"\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
		else if(phoneNo.matches("D: "+"\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
		else if(phoneNo.matches("D: "+"\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
		else return false;

	}

	public boolean isNumberOfAsscoID(String Assoc_ID) {
		try {
			Integer.parseInt(Assoc_ID);
			return true;
		} catch (NumberFormatException nfe) {}
		return false;
	}
	public OrderDetailsPage verifyDDCfulfillment(){

		Logger.log("Delivery Details text is present", TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(DELIVERYDETAILS_TEXT).waitForResponse(5);

		Logger.log("Delivery Details DOS  is present", TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(DELIVERYDETAILS_DOS_NUMBER).waitForResponse(5);


		Logger.log("Delivery Details  is present", TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(DELIVERYDETAILS).waitForResponse(5);


		Logger.log("Order Details  is present", TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(DELIVERYDETAILS_TABS.format("Order Detail")).waitForResponse(5);
		getAction().waitFor(2000);
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


		return this;

	}


	public OrderDetailsPage clickTabs(){
		getAction().waitFor(3000);
		if(getAction().isVisible(LINE_ITEM_SALES_CHECKS_TAB)){
			clickOnOrderTabInODP(OrderTab.LINE_ITEM_SALES_CHECKS);
			SoftAssert.checkElementAndContinueOnFailure(LINE_ITEM_DETAIL_PAGE, "Verify if Line Item Detail Page is displayed", CheckLocatorFor.isPresent);
		}
		if(getAction().isVisible(AUDIT_TRAIL_TAB)){
			clickOnOrderTabInODP(OrderTab.AUDIT_TRAIL);
			SoftAssert.checkElementAndContinueOnFailure(AUDIT_TRAIL_DETAIL_PAGE, "Verify if AUDIT TRAIL Detail Page is displayed", CheckLocatorFor.isPresent);
		}
		if(getAction().isVisible(SYW_MAX_TAB)){
			clickOnOrderTabInODP(OrderTab.SYW_MAX);
			SoftAssert.checkElementAndContinueOnFailure(SYW_MAX_DETAIL_PAGE, "Verify if SYW MAX Detail Page is displayed", CheckLocatorFor.isPresent);
		}
		clickOnOrderTabInODP(OrderTab.ORDER_SUMMARY);
		return this;
	}

	public void captureInteractionCaseId() {
		Logger.log("Capture Current Interaction Case Id", TestStepType.VERIFICATION_STEP);
		AjaxCondition.forElementVisible(CURRENT_INTERACTION).waitForResponse();
		setData("caseId", getMatchingTextGroup("# ([0-9]+) ", getAction().getText(CURRENT_INTERACTION).trim()));
		Logger.log("Current Interaction Case Id - "+getDataString("caseId")+" stored", TestStepType.VERIFICATION_STEP);

	}
	public void captureOrderIdFromODPage(){
		AjaxCondition.forElementVisible(ORDER_ID).waitForResponse();
		String []order=getAction().getText(ORDER_ID).split("#");
		Logger.log("Current order Id:"+order[1].replace(" ", "").trim(), TestStepType.SUBSTEP);
		setData("orderId", order[1].replace(" ", "").trim());
	}

	public void verifyOrderWrapUp() {
		wrapUpAndVerify();
		Logger.log("Select Item for Order Wrap Up", TestStepType.STEP);
		check(SELECT_ITEM_FOR_WRAPUP);
		clickWrapOrderContact();
	}

	public void verifyRoutingForOfflineAgent() {
		selectWrapUpTab();
		if(getAction().isVisible(CATEGORY_DROPDOWN)&& getAction().isVisible(REASON_CODE_DROPDOWN)){
			getAction().click(CATEGORY_DROPDOWN);
			getAction().selectByVisibleText(CATEGORY_DROPDOWN, "Damaged Order");
			Logger.log("Select Damaged Order Option in Category", TestStepType.VERIFICATION_PASSED);

			getAction().click(REASON_CODE_DROPDOWN);
			getAction().waitFor(1000);
			getAction().selectByVisibleText(REASON_CODE_DROPDOWN, "ACTION NEEDED - APO/FPO Damaged");
			getAction().waitFor(1000);
			Logger.log("Select 'ACTION NEEDED - APO/FPO Damaged' in Reason Code", TestStepType.STEP);
			SoftAssert.checkConditionAndContinueOnFailure("Verify USPS Queue selected to route", getAction().getText(SELECT_QUEUE_ONE_RESPONSE).trim().equals("USPS"));

		}
		SoftAssert.checkConditionAndContinueOnFailure("Verify Select Queue to route Text is Present", getAction().getText(SELECT_QUEUE_TO_ROUTE).equals("Select Queue to route"));

		if(getAction().isVisible(SELECT_QUEUE_ONE_RESPONSE)){
			Logger.log("Verify Selected Queue Name "+getAction().getText(SELECT_QUEUE_ONE_RESPONSE)+" Is Visible ", TestStepType.VERIFICATION_PASSED);
		}else if(getAction().isVisible(SELECT_QUEUE_MULTI_RESPONSE)){
			Logger.log("Verify Selected Queue Name "+getAction().getText(SELECT_QUEUE_MULTI_RESPONSE)+" Are Visible ", TestStepType.VERIFICATION_PASSED);
		}else if(getAction().isVisible(SELECT_QUEUE_NO_RESPONSE)){
			Logger.log("Verify Not Applicable "+"'"+getAction().getText(SELECT_QUEUE_NO_RESPONSE)+"'"+" Is Visible ", TestStepType.VERIFICATION_PASSED);
		}
		getAction().type(ORDER_NOTES_TEXT_AREA, "Routing Case to Queue - USPS");
		SoftAssert.checkElementAndContinueOnFailure(ORDER_NOTES_REQUIREDFIELD_SIGN, "Verify Order Notes Required Field Sign is Present", CheckLocatorFor.isPresent);

		getAction().click(SELECT_QUEUE_TO_ROUTE);
		getAction().waitFor(1000);

		AjaxCondition.forElementVisible(ROUTE_BUTTON).waitForResponse();
		getAction().click(ROUTE_BUTTON);


	}
	public void verifyCreateCaseByRouting() {
		selectWrapUpTab();
		String queue="USPS";
		if(getAction().isVisible(CATEGORY_DROPDOWN)&& getAction().isVisible(REASON_CODE_DROPDOWN)){
			getAction().click(CATEGORY_DROPDOWN);
			getAction().selectByVisibleText(CATEGORY_DROPDOWN, "Damaged Order");
			Logger.log("Select Damaged Order Option in Category", TestStepType.VERIFICATION_PASSED);

			getAction().click(REASON_CODE_DROPDOWN);
			getAction().waitFor(1000);
			getAction().selectByVisibleText(REASON_CODE_DROPDOWN, "ACTION NEEDED - APO/FPO Damaged");
			getAction().waitFor(1000);
			Logger.log("Select 'ACTION NEEDED - APO/FPO Damaged' in Reason Code", TestStepType.STEP);
			if(AjaxCondition.forElementVisible(CASE_CREATED).waitWithoutException(10)){
				getAction().click(OK_BUTTON);
				queue="no queue since a case already exists in USPS queue for that order";
			}
			else{
				SoftAssert.checkConditionAndContinueOnFailure("Verify USPS Queue selected to route", getAction().getText(SELECT_QUEUE_ONE_RESPONSE).trim().equals("USPS"));
			}
		}
		SoftAssert.checkConditionAndContinueOnFailure("Verify Select Queue to route Text is Present", getAction().getText(SELECT_QUEUE_TO_ROUTE).equals("Select Queue to route"));

		if(getAction().isVisible(SELECT_QUEUE_ONE_RESPONSE)){
			Logger.log("Verify Selected Queue Name "+getAction().getText(SELECT_QUEUE_ONE_RESPONSE)+" Is Visible ", TestStepType.VERIFICATION_PASSED);
		}else if(getAction().isVisible(SELECT_QUEUE_MULTI_RESPONSE)){
			Logger.log("Verify Selected Queue Name "+getAction().getText(SELECT_QUEUE_MULTI_RESPONSE)+" Are Visible ", TestStepType.VERIFICATION_PASSED);
		}else if(getAction().isVisible(SELECT_QUEUE_NO_RESPONSE)){
			Logger.log("Verify Not Applicable "+"'"+getAction().getText(SELECT_QUEUE_NO_RESPONSE)+"'"+" Is Visible ", TestStepType.VERIFICATION_PASSED);
		}
		getAction().type(ORDER_NOTES_TEXT_AREA, "Routing Case to "+queue);
		SoftAssert.checkElementAndContinueOnFailure(ORDER_NOTES_REQUIREDFIELD_SIGN, "Verify Order Notes Required Field Sign is Present", CheckLocatorFor.isPresent);

		getAction().click(SELECT_QUEUE_TO_ROUTE);
		getAction().waitFor(1000);

		Logger.log("Select Item for Order Wrap Up", TestStepType.STEP);
		scrollDown();
		getAction().click(SELECT_ITEM_FOR_WRAPUP);
		getAction().waitFor(1000);
		clickWrapOrderContact();
	}
	public void verifyCloseCaseByWrapupOfflineAgent(){

		selectWrapUpTab();
		if(getAction().isVisible(CATEGORY_DROPDOWN)&& getAction().isVisible(REASON_CODE_DROPDOWN)){
			getAction().click(CATEGORY_DROPDOWN);
			getAction().selectByVisibleText(CATEGORY_DROPDOWN, "General Inquiry");
			Logger.log("Select General Inquiry Option in Category", TestStepType.VERIFICATION_PASSED);

			getAction().click(REASON_CODE_DROPDOWN);
			getAction().waitFor(1000);
			getAction().selectByVisibleText(REASON_CODE_DROPDOWN, "NO Action Needed - General Question");
			getAction().waitFor(1000);
			Logger.log("Select 'NO Action Needed - General Question ' in Reason Code", TestStepType.STEP);

		}

		getAction().type(ORDER_NOTES_TEXT_AREA, "Testing closing of case");
		SoftAssert.checkElementAndContinueOnFailure(ORDER_NOTES_REQUIREDFIELD_SIGN, "Verify Order Notes Required Field Sign is Present", CheckLocatorFor.isPresent);
		getAction().waitFor(1000);
		Logger.log("Closing the case", TestStepType.STEP);
		AjaxCondition.forElementVisible(CLOSE_BUTTON).waitForResponse();
		getAction().click(CLOSE_BUTTON);
	}

	public void verifyAllCategoriesReasonCodes(int i) {
		selectWrapUpTab();
		AjaxCondition.forElementVisible(CATEGORY_DROPDOWN).waitForResponse();
		scrollDown();
		getAction().waitFor(1000);
		check(SELECT_ITEM_FOR_WRAPUP);
		int k = 0;
		if (AjaxCondition.forElementVisible(CATEGORY_OPTION.format(i)).waitWithoutException(3)) {
			String category = getAction().getText(CATEGORY_OPTION.format(i));
			getAction().selectByVisibleText(CATEGORY_DROPDOWN, category);
			getAction().waitFor(1000);
			int j = 2;
			while (AjaxCondition.forElementVisible(REASON_CODE_OPTION.format(j)).waitWithoutException(3)) {
				String reasonCode = getAction().getText(REASON_CODE_OPTION.format(j));
				getAction().selectByVisibleText(REASON_CODE_DROPDOWN, reasonCode);
				getAction().waitFor(1000);
				scrollDown();
				getAction().waitFor(1000);
				String queue = getAction().getText(SELECT_QUEUE).trim();
				Logger.log("Testing " + category + ":" + reasonCode + ":" + queue, TestStepType.VERIFICATION_PASSED);
				System.out.println(category.trim() + ":" + reasonCode.trim() + ">" + queue.trim());
				FileUtil.logToFile(category.trim() + ":" + reasonCode.trim() + ">" + queue.trim()+"\r\n");
				getAction().type(ORDER_NOTES_TEXT_AREA, "Testing Category - " + category + ", Reason Code - " + reasonCode + " - " + (i - 1) + "_" + (j - 1));
				getAction().waitFor(1000);
				scrollDown();
				getAction().waitFor(1000);
				getAction().click(BUTTON_WRAP_ORDER_CONTACT);
				getAction().waitFor(2000);
				AjaxCondition.forElementVisible(RFC_BUTTON_CANCEL).waitForResponse();
				getAction().click(RFC_BUTTON_CANCEL);
				getAction().waitFor(1000);
				if (reasonCode.contains("ACTION NEEDED")) {
					SoftAssert.checkConditionAndContinueOnFailure("Queue Not selected for category - " + category + ", reason code - " + reasonCode, !queue.contains("Not Applicable"));
					getAction().pageRefresh();
					this.factory.homePage().searchByCaseId(getDataString("caseId"));
					this.factory.homePage().verifyCaseDetails("CASE_UNASSIGNED", "Case Search Results");
					this.factory.homePage().verifyCaseAssignedToQueue(queue);
					MongoDB.deleteCasesforOrderfromDB(getDataString("orderId"));
					this.factory.homePage().searchByOrderId(getDataString("orderId"));
					this.factory.homePage().closeWarningPopupWindow();
					selectWrapUpTab();
					getAction().selectByVisibleText(CATEGORY_DROPDOWN, category);
					this.factory.orderdetailspage().captureInteractionCaseId();
					getAction().waitFor(1000);
					scrollDown();
					getAction().waitFor(1000);
					check(SELECT_ITEM_FOR_WRAPUP);
				}
				j++;
				k++;
				Logger.log("Verified Categories + Reson codes for combinations count of " + k, TestStepType.STEP);
			}

		}
		String category = getAction().getText(CATEGORY_OPTION.format(2));
		getAction().selectByVisibleText(CATEGORY_DROPDOWN, category);
		getAction().waitFor(1000);
		String reasonCode = getAction().getText(REASON_CODE_OPTION.format(2));
		getAction().selectByVisibleText(REASON_CODE_DROPDOWN, reasonCode);
		getAction().waitFor(1000);
		getAction().type(ORDER_NOTES_TEXT_AREA, "Testing Category - " + category + ", Reason Code - " + reasonCode + " - " + (2 - 1) + "_" + (2 - 1));
		getAction().waitFor(1000);
		Logger.log("Verified Categories + Reson codes for combinations count of " + k, TestStepType.STEP);
		clickWrapOrderContact();
	}

	public void clickWrapOrderContact() {
		Logger.log("Click Wrap Order & Contact", TestStepType.STEP);
		scrollDown();
		clickJ(BUTTON_WRAP_ORDER_CONTACT);
	}


	public void selectWrapUpTab() {
		Logger.log("Click on 'Wrap up' tab",TestStepType.STEP);
		AjaxCondition.forElementVisible(WRAP_UP_TAB).waitForResponse();
		getAction().click(WRAP_UP_TAB);
		Logger.log("Verify category drop down is present in the page",TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(CATEGORY_TEXT).waitForResponse();
	}

	public void fillRFCForm() {
		Logger.log("Fill RFC Form and click Wrap Up", TestStepType.STEP);
		AjaxCondition.forElementVisible(RFC_OPTION.format("1")).waitForResponse();
		int i=1;
		while(AjaxCondition.forElementVisible(RFC_OPTION.format(i)).waitWithoutException(3)){
			Logger.log("Select \""+getAction().getText(RFC_FIRST_OPTION_VALUE.format(i))+"\" for \""+getAction().getText(RFC_OPTION_LABEL.format(i))+"\"", TestStepType.VERIFICATION_PASSED);
			getAction().selectByText(RFC_OPTION.format(i), getAction().getText(RFC_FIRST_OPTION_VALUE.format(i)));
			getAction().waitFor(500);
			i++;
		}
		Logger.log("Click Wrap Up on RFC Form", TestStepType.STEP);
		getAction().click(RFC_BUTTON_WRAPUP);
		getAction().waitFor(3000);
	}

	public void verifyEmailCapturedInInteraction(){

		Logger.log("Verify Email sent to customer are captured in Interaction", TestStepType.STEP);
		AjaxCondition.forElementVisible(STORE_DROPDOWN).waitForResponse();
		getAction().click(STORE_DROPDOWN);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(STORE_DROPDOWN_OPTION.format(0)).waitForResponse();
		getAction().click(STORE_DROPDOWN_OPTION.format(0));
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(EMAILTEMPLATE_DROPDOWN).waitForResponse();
		getAction().click(EMAILTEMPLATE_DROPDOWN);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(EMAILTEMPLATE_DROPDOWN_OPTION.format(11)).waitForResponse();
		getAction().click(EMAILTEMPLATE_DROPDOWN_OPTION.format(11));
		getAction().waitFor(2000);
		String emailTemplateName=getAction().getText(EMAILTEMPLATE_DROPDOWN_OPTION.format(11));
		AjaxCondition.forElementVisible(EMAIL_SUBMIT_BUTTON).waitForResponse();
		getAction().click(EMAIL_SUBMIT_BUTTON);
		getAction().waitFor(8000);
		getContext().put("selectedemailtemplatename", emailTemplateName);
		//	AjaxCondition.forElementVisible(SUCCESS_OK_BUTTON).waitForResponse();
		//getAction().click(SUCCESS_OK_BUTTON);
		//getAction().waitFor(3000);
		closeWarningPopupWindow();

		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(EMAIL_TEMPLATE_NOTES.format("Contact Customer",emailTemplateName)).waitForResponse();
		getAction().click(EMAIL_TEMPLATE_NOTES.format("Contact Customer",emailTemplateName));
		getAction().waitFor(3000);
		//if(!getAction().containsString(getAction().getText(EMAIL_TEMPLATE_NOTES), templatename)){
		PageAssert.textPresent(EMAIL_TEMPLATE_NOTES.format("Contact Customer",emailTemplateName), emailTemplateName);

		AjaxCondition.forElementVisible(newxpath).waitForResponse();

		System.out.println("-------------------------------------------------------"+getAction().getText(newxpath));
		String emailcontent="Subject: Sears.com Order # (XXXXXX)"
				+"Message:"
				+" Dear  ,"
				+" Thank you for shopping at Sears.  "
				+" We have received your online order for the (item). However, your order did not properly process. If you would like us to replace the order for you, please call 1-800-283-6940 and a customer service representative will be happy to assist you.   We apologize for any inconvenience this may cause you. "
				+" If you have any questions or concerns, please e-mail us at order@customerservice.sears.com or call us at 1-800-283-6940. "
				+" Loook for Great Ideas throughout the store and find Sears exclusive innovations from great brands like Sony, Kenmore, NordicTrack, Craftsman and Reebok. "
				+" Shop sears.com now to pick up great products for the season. "
				+" http://www.sears.com" 
				+"  Sincerely, "
				+" Sears Customer Care"
				+" order@customerservice.sears.com"
				+" 1-800-283-6940";

		emailcontent = emailcontent.replaceAll("[^a-zA-Z0-9]", ""); 
		String actualEmailContentDisplayed=getAction().getText(newxpath);
		actualEmailContentDisplayed = actualEmailContentDisplayed.replaceAll("[^a-zA-Z0-9]", ""); 
		System.out.println("-------------------------------------actualemail: "+actualEmailContentDisplayed);
		System.out.println("-------------------------------------mycontent  : "+emailcontent);

		SoftAssert.checkConditionAndContinueOnFailure("Verify the correct performance message is shown", actualEmailContentDisplayed.contains(emailcontent.trim()));
		getAction().waitFor(3000);

		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);

	}
	public void verifyAdjustmentCapturedInInteraction(String adjust){
		closeWarningPopupWindow();
		getAction().waitFor(5000);
		Logger.log("Verify adjustment done on order are captured in Current Interaction", TestStepType.STEP);
		System.out.println("----------------------------verifyAdjustmentCapturedInInteraction");
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		getAction().waitFor(3000);
		String type=(String) getContext().get("adjustmentOption");
		System.out.println("locator under trial "+ORDER_CONTACT_HISTORY_ADJUSTMENT.format(adjust,type).getValue());
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY_ADJUSTMENT.format(adjust,type)).waitForResponse();
		getAction().click(CONTACT_HISTORY_MENU_DOWN);

	}

	public OrderDetailsPage verifyAdjustmentCapturedInOrderSummary(String adjust, String amount){
		getAction().waitFor(2000);
		Logger.log("Verify "+adjust+" done are captured in Order Summary", TestStepType.STEP);
		getAction().scrollTo(LATEST_ADJUSTMENT_IN_ORDER_SUMMARY.format("1"));
		String retrievedAdj = getAction().getText(LATEST_ADJUSTMENT_IN_ORDER_SUMMARY.format("1"));
		String retrievedAmount = getAction().getText(LATEST_ADJUSTMENT_IN_ORDER_SUMMARY.format("2"));
		System.out.println("Adjustment "+retrievedAdj.equalsIgnoreCase(adjust));
		SoftAssert.checkConditionAndContinueOnFailure("Verify "+adjust+" is the latest adjustment in the table", retrievedAdj.equalsIgnoreCase(adjust));
		System.out.println("Amount "+retrievedAmount.contains(amount));
		SoftAssert.checkConditionAndContinueOnFailure("Verify adjustment amount Displayed Amount:- "+retrievedAmount+"  Expected Amount :- "+amount, retrievedAmount.contains(amount));
		System.out.println("Verify adjustment amount Displayed Amount:- "+retrievedAmount+"  Expected Amount :- "+amount);
		return this;		
	}

	public void verifyEmailCapturedInNotes(){
		Logger.log("Verify Email sent to customer are captured in Notes", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(CONTACT_HISTORY_EMAILTEMPLATE.format(getContext().get("selectedemailtemplatename"))).waitForResponse();
		getAction().click(CONTACT_HISTORY_EMAILTEMPLATE.format(getContext().get("selectedemailtemplatename")));
		PageAssert.textPresent(CONTACT_HISTORY_EMAILTEMPLATE.format(getContext().get("selectedemailtemplatename")), (String) getContext().get("selectedemailtemplatename"));
		getAction().waitFor(1000);
	}

	public void verifyAdjustmentCapturedInNotes(String adjust){
		Logger.log("Verify adjustment done on order are captured in Notes", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		getAction().waitFor(3000);

		PageAssert.textPresent(ORDER_CONTACT_HISTORY_NOTES.format(adjust), "Notes: "+(String) getContext().get("adjustmentOption"));
	}

	public void verifyActionCapturedInNotes(String action){
		Logger.log("Verify adjustment done on order are captured in Notes", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		getAction().waitFor(3000);

		PageAssert.elementPresent(ORDER_CONTACT_HISTORY_NOTES_DELIVERY.format(action));
	}

	public void verifyRereservebuttonPresent(){
		Logger.log("Verify Rereserve button is present in action center", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(RERESERVE_BUTTON).waitForResponse();
		if(getAction().isNotPresent(RERESERVE_BUTTON)){
			PageAssert.fail("Rereserve button is not present");
		}
		else
			Logger.log("Rereserve button is present ",TestStepType.VERIFICATION_PASSED);

	}
	public void rereserveItem(String orderType,String order){

		if (orderType.equalsIgnoreCase("Shipped")||orderType.equalsIgnoreCase("Cancelled")){
			PageAssert.elementNotVisible(RERESERVE_BUTTON);
			Logger.log("Rereserve button is not available in Action Center" , TestStepType.VERIFICATION_PASSED);
		}

		else{
			Logger.log("Verify Rereserve button is present in action center", TestStepType.STEP);
			String dosOrderNumber = getAction().getText(DELIVERYDETAILS_DOS_NUMBER);
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(RERESERVE_BUTTON).waitForResponse();
			getAction().scrollTo(RERESERVE_BUTTON);
			getAction().click(RERESERVE_BUTTON);
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(RERESERVE_ITEM_COUNT).waitForResponse();
			int num=getAction().getVisibleElementCount(RERESERVE_ITEM_COUNT);
			if(order.equalsIgnoreCase("whole order")){
				for(int i=1;i<=num;i++){
					getAction().scrollTo(RERESERVE_ITEM.format(i));
					AjaxCondition.forElementVisible(RERESERVE_ITEM.format(i)).waitForResponse();
					getAction().click(RERESERVE_ITEM.format(i));
					AjaxCondition.forElementVisible(LINE_ITEM_ROW_QUANTITY.format(orderType,i)).waitForResponse();
					getAction().type(LINE_ITEM_ROW_QUANTITY.format(orderType,i), getAction().getText(LINE_ITEM_ROW_QUANTITY_ORDERED.format(i)));
					getAction().waitFor(3000);
				}
			}
			else{
				AjaxCondition.forElementVisible(RERESERVE_ITEM.format(1)).waitForResponse();
				getAction().click(RERESERVE_ITEM.format(1));
				AjaxCondition.forElementVisible(LINE_ITEM_ROW_QUANTITY.format(orderType,1)).waitForResponse();
				getAction().type(LINE_ITEM_ROW_QUANTITY.format(orderType,1), getAction().getText(LINE_ITEM_ROW_QUANTITY_ORDERED.format(1)));
				getAction().waitFor(3000);
				num=1;
			}
			AjaxCondition.forElementVisible(ACTION_CETNER_CONTINUE_BUTTON).waitForResponse(5);
			getAction().click(ACTION_CETNER_CONTINUE_BUTTON);
			Logger.log("Click 'No' on the Consession confirmation dialog",TestStepType.STEP);
			AjaxCondition.forElementVisible(OFFER_CONSESSION_NO_BUTTON).waitForResponse(5);
			getAction().click(OFFER_CONSESSION_NO_BUTTON);
			Logger.log("Select the Category Code",TestStepType.STEP);
			for(int i=1;i<=num;i++){
				AjaxCondition.forElementVisible(CATEGORY_CODE_DROPDOWN_RERESERVE.format(i)).waitForResponse();
				getAction().click(CATEGORY_CODE_DROPDOWN_RERESERVE.format(i));
				getAction().waitFor(3000);
				AjaxCondition.forElementVisible(REASON_CODE_DROPDOWN_OPTION_RERESERVE.format(i)).waitForResponse();
				getAction().click(REASON_CODE_DROPDOWN_OPTION_RERESERVE.format(i));
			}
			getAction().waitFor(10000);
			Logger.log("Click Create Order button",TestStepType.STEP);
			AjaxCondition.forElementVisible(RERESERVE_CREATE_ORDER_BUTTON).waitForResponse(5);
			getAction().click(RERESERVE_CREATE_ORDER_BUTTON);
			Logger.log("Verify the Success pop up is displayed",TestStepType.STEP);
			AjaxCondition.forElementVisible(EVEN_EXCHANGE_SUCCESS_MESSAGE).waitForResponse(5);
			PageAssert.elementVisible(EVEN_EXCHANGE_SUCCESS_MESSAGE);
			getAction().click(ORDERCREATED_OK_BUTTON);
			getAction().waitFor(4000);
			String newDosOrderNumber = getAction().getText(DOS_ORDER_SUMMARY_ORDER_NUMBER);
			System.out.println("New order created    "+newDosOrderNumber+" Old order "+dosOrderNumber);
			SoftAssert.checkTrue(!(dosOrderNumber.equals(newDosOrderNumber)), "New order is created for re reserve:-"+newDosOrderNumber);
			Logger.log("Verified that New Order status is Open", TestStepType.VERIFICATION_PASSED);
			AjaxCondition.forElementVisible(ORDER_STATUS_OPEN).waitForResponse();
			//test as part of P2
			if(order.equalsIgnoreCase("Whole")){
			@SuppressWarnings("unchecked")
			List<String> list=(List<String>) getContext().get("adjustmentOption");
			Iterator<String> itr=list.iterator();
			while (itr.hasNext()) {
					String[] details=itr.next().split(":");
					SoftAssert.checkElementAndContinueOnFailure(ITEM_DETAIL.format(details[0],details[1],details[2]), "items details are auto populated correctly", CheckLocatorFor.isVisible);
					getAction().waitFor(3000);}
			}
		}
	}
	public void verifyPickupbuttonPresent(){
		Logger.log("Verify Pickup button is present in action center", TestStepType.STEP);
		getAction().waitFor(3000);
		if(getAction().isNotPresent(PICKUP_BUTTON)){
			PageAssert.fail("Pickup button is not present");
		}
		else
			Logger.log("Pickup button is present ",TestStepType.VERIFICATION_PASSED);

	}
	public void pickupOrder(){
		Logger.log("Agent should be able to do pickup action", TestStepType.STEP);
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(PICKUP_BUTTON).waitForResponse();
		getAction().scrollTo(PICKUP_BUTTON);
		getAction().click(PICKUP_BUTTON);
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(SELECT_ITEM.format(1)).waitForResponse();
		getAction().click(SELECT_ITEM.format(1));
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(REASON_FOR_PICKUP_DROPDOWN).waitForResponse();
		getAction().click(REASON_FOR_PICKUP_DROPDOWN);
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(REASON_FOR_PICKUP_DROPDOWN_OPTION).waitForResponse();
		getAction().click(REASON_FOR_PICKUP_DROPDOWN_OPTION);
		//	getAction().waitFor(3000);
		AjaxCondition.forElementVisible(PICKUP_QUANTITY).waitForResponse();
		getAction().click(PICKUP_QUANTITY);
		getAction().type(PICKUP_QUANTITY, "1");
		//getAction().waitFor(3000);
		if(getAction().isVisible(MATTRESS_TEXT))
			verifyMattressExchange();
		else{
			getAction().scrollTo(CONTINUE_BUTTON);
			AjaxCondition.forElementVisible(CONTINUE_BUTTON).waitForResponse();
			getAction().click(CONTINUE_BUTTON);}
		getAction().waitFor(2000);
		/*if(getAction().isVisible(RETURN_POLICY)){
			Logger.log(getAction().getText(RETURN_POLICY),TestStepType.STEP);
			getAction().click(CONTINUE_BUTTON);
			AjaxCondition.forElementVisible(PICK_UP_EXCEPTION).waitForResponse(2000);
			getAction().type(EXCEPTION_USERNAME, "testdelivery0121");
			getAction().type(EXCEPTION_PASSWORD, "TestPassword");
			getAction().type(REASON_EXCEPTION, "this is an automated test");
			getAction().click(SUBMIT);
		}*/
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(CONCESSION_YES).waitForResponse();
		getAction().click(CONCESSION_YES);
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(CONCESSIONACCEPTED_NO).waitForResponse();
		getAction().click(CONCESSIONACCEPTED_NO);
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(CATEGORY_CODE_DROPDOWN.format(1)).waitForResponse();
		getAction().click(CATEGORY_CODE_DROPDOWN.format(1));
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(CATEGORY_CODE_DROPDOWN_OPTION.format(1)).waitForResponse();
		getAction().click(CATEGORY_CODE_DROPDOWN_OPTION.format(1));
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(PICKUP_REASON_CODE_DROPDOWN.format(1)).waitForResponse();
		getAction().click(PICKUP_REASON_CODE_DROPDOWN.format(1));
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(REASON_CODE_DROPDOWN_OPTION.format(1)).waitForResponse();
		getAction().click(REASON_CODE_DROPDOWN_OPTION.format(1));
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(CREATE_ORDER_BUTTON).waitForResponse();
		getAction().click(CREATE_ORDER_BUTTON);
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(NEW_ORDER_CREATED_MSG).waitForResponse();
		if(!getAction().getText(NEW_ORDER_CREATED_MSG).contains("New order has been created. Close this notification to proceed to new order.")){
			PageAssert.fail("New order created sucess msg not shown");
		}
		else{
			Logger.log("Verified new order created sucess message ",TestStepType.VERIFICATION_PASSED);
			getAction().click(ORDERCREATED_OK_BUTTON);
			getAction().waitFor(4000);
			getAction().scrollTo(DOS_ORDER_STATUS);
			if(!getAction().getText(DOS_ORDER_STATUS).contains("Open")){
				PageAssert.fail("New open order is not visible");
			}
			else
				Logger.log("Verified new pickup order ",TestStepType.VERIFICATION_PASSED);	   
		}



	}
	public void pickupEntireOrder(String orderStatus){
		String orderRouteStatus= (String) getContext().get("orderRouteStatus");
		String dosOrderNumber = getAction().getText(DELIVERYDETAILS_DOS_NUMBER);  
		Logger.log("Agent should be able to do pickup action", TestStepType.STEP);
		getAction().waitFor(3000);
		getAction().scrollTo(PICKUP_BUTTON);
		AjaxCondition.forElementVisible(PICKUP_BUTTON).waitForResponse();
		getAction().click(PICKUP_BUTTON);
		getAction().waitFor(3000);
		int num=getAction().getVisibleElementCount(SELECT_ITEM_NUMBER);
		for(int i=num;i>0;i--){
			AjaxCondition.forElementVisible(SELECT_ITEM.format(i)).waitForResponse();
			getAction().click(SELECT_ITEM.format(i));
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(REASON_FOR_PICKUP_DROPDOWN).waitForResponse();
			getAction().click(REASON_FOR_PICKUP_DROPDOWN);
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(REASON_FOR_PICKUP_DROPDOWN_OPTION).waitForResponse();
			getAction().click(REASON_FOR_PICKUP_DROPDOWN_OPTION);
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(PICKUP_QUANTITY).waitForResponse();
			getAction().click(PICKUP_QUANTITY);
			getAction().type(PICKUP_QUANTITY, "1");
		}
		getAction().waitFor(3000);
		getAction().scrollTo(CONTINUE_BUTTON);
		AjaxCondition.forElementVisible(CONTINUE_BUTTON).waitForResponse();
		getAction().click(CONTINUE_BUTTON);
		getAction().waitFor(3000);
		try {
			if(orderRouteStatus.equalsIgnoreCase("ON TIME")){
				getAction().waitFor(3000);
				verifyDayOfDelivery("Delivery Driver","Select Items");
				if("DELIVERY DRIVER".equalsIgnoreCase("Delivery Driver")){
					AjaxCondition.forElementVisible(CONTINUE_TO_RETURN_EXCHANGE_ITEM).waitForResponse();
					getAction().click(CONTINUE_TO_RETURN_EXCHANGE_ITEM);}}
		} catch (NullPointerException e ) {
			orderRouteStatus="";
		}
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(CONCESSION_YES).waitForResponse();
		getAction().click(CONCESSION_YES);
		AjaxCondition.forElementVisible(CONCESSIONACCEPTED_NO).waitForResponse();
		getAction().click(CONCESSIONACCEPTED_NO);
		for(int i=1;i<=num;i++){
			AjaxCondition.forElementVisible(CATEGORY_CODE_DROPDOWN.format(i)).waitForResponse();
			getAction().click(CATEGORY_CODE_DROPDOWN.format(i));
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(CATEGORY_CODE_DROPDOWN_OPTION.format(i)).waitForResponse();
			getAction().click(CATEGORY_CODE_DROPDOWN_OPTION.format(i));
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(PICKUP_REASON_CODE_DROPDOWN.format(i)).waitForResponse();
			getAction().click(PICKUP_REASON_CODE_DROPDOWN.format(i));
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(REASON_CODE_DROPDOWN_OPTION.format(i)).waitForResponse();
			getAction().click(REASON_CODE_DROPDOWN_OPTION.format(i));
		}
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(CREATE_ORDER_BUTTON).waitForResponse();
		getAction().click(CREATE_ORDER_BUTTON);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(NEW_ORDER_CREATED_MSG).waitForResponse();
		if(!getAction().getText(NEW_ORDER_CREATED_MSG).contains("New order has been created. Close this notification to proceed to new order.")){
			PageAssert.fail("New order created sucess msg not shown");
		}
		else{
			Logger.log("Verified new order created sucess message ",TestStepType.VERIFICATION_PASSED);
			getAction().click(ORDERCREATED_OK_BUTTON);
			getAction().waitFor(4000);
			getAction().scrollTo(DOS_ORDER_STATUS);
			if(!getAction().getText(DOS_ORDER_STATUS).contains("Open")){
				PageAssert.fail("New open order is not visible");
			}
			else
				Logger.log("Verified new pickup order ",TestStepType.VERIFICATION_PASSED);	   
		}

	}

	public void goToActionCenter(){
		Logger.log("Verify whether user is on action center", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(ACTION_CENTER).waitForResponse(30);
		getAction().scrollTo(ACTION_CENTER);
		getAction().click(ACTION_CENTER);
	}
	public void goToOrderDetail(){
		Logger.log("Verify whether user is on order details", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(ORDER_DETAIL).waitForResponse();
		getAction().click(ORDER_DETAIL);
	}
	public void goToDeliveryNotes(){
		Logger.log("Verify whether user is on delivery notes", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementPresent(DELIVERY_NOTES).waitForResponse();
		getAction().scrollTo(DELIVERY_NOTES);
		getAction().click(DELIVERY_NOTES);
	}

	public OrderDetailsPage goToAuditTrail(){
		Logger.log("Go to Audit Trail", TestStepType.STEP);
		AjaxCondition.forElementPresent(AUDIT_TRAIL_TAB).waitWithoutException(5);
		clickOnOrderTabInODP(OrderTab.AUDIT_TRAIL);	
		return this;
	}

	public OrderDetailsPage verifyActionCapturedInAuditTrail(String action){
		Logger.log("Sort the Audits by US Central Time", TestStepType.STEP);
		AjaxCondition.forElementPresent(SORT_BY_US_CENTRAL_TIME).waitWithoutException(5);
		getAction().scrollTo(SORT_BY_US_CENTRAL_TIME);
		getAction().click(SORT_BY_US_CENTRAL_TIME);
		getAction().waitFor(2000);
		SoftAssert.checkElementAndContinueOnFailure(AUDIT_TRAIL_ENTRY.format(action), "Verify the action:- "+action+" is present in the Audit Trail",CheckLocatorFor.isVisible);
		return this;
	}

	public void clickSearchAnotherOrder(){
		Logger.log("Click on Search for Another Order", TestStepType.STEP);
		AjaxCondition.forElementVisible(SEARCH_FOR_ANOTHER_ORDER_LINK).waitForResponse(5);
		getAction().click(SEARCH_FOR_ANOTHER_ORDER_LINK);		
	}

	public void verifyDataInDeliveryNotes(String data){
		Logger.log("verify Data In DeliveryNotes", TestStepType.STEP);
		getAction().waitFor(3000);
		System.out.println("----"+data);
		System.out.println("------"+ getAction().getText(DELIVERY_NOTES_DATA.format(data)));
		//System.out.println("----"+DELIVERY_NOTES_DATA.format(data).getValue());
		getAction().scrollTo(DELIVERY_NOTES_DATA.format(data));
		if(data.equalsIgnoreCase("MSP  ENTIRE ORDER HAS BEEN CANCELLED")){

			String cancelledOrderNotes="MSP ENTIRE ORDER HAS BEEN CANCELLED";
			SoftAssert.checkConditionAndContinueOnFailure("verified DeliveryNotes has data:"+cancelledOrderNotes, getAction().getText(DELIVERY_NOTES_DATA.format(data)).contains(cancelledOrderNotes));
			System.out.println(cancelledOrderNotes);
		}
		else{
			AjaxCondition.forElementVisible(DELIVERY_NOTES_DATA.format(data)).waitForResponse();
			String text=getAction().getText(DELIVERY_NOTES_DATA.format(data)).trim();
			System.out.println("---------------------------------system value"+text);
			System.out.println("---------------------------------my     value"+data.trim());
			SoftAssert.checkConditionAndContinueOnFailure("verified DeliveryNotes has data:"+data,text.contains(data.trim()));
		}
	}

	public void cancelOrderDelivery(String orderType, String orderStatus,String agent ) {


		String orderRouteStatus =(String) getContext().get("orderRouteStatus");
		Logger.log("Verify whether order can be cancelled", TestStepType.STEP);
		//AjaxCondition.forElementVisible(LINE_ITEM_NAME_NOT_CANCELLED).waitForResponse();
		//String itemToCancelName = getAction().getText(LINE_ITEM_NAME_NOT_CANCELLED);
		String itemToCancelName = "";
		goToActionCenter();
		AjaxCondition.forElementVisible(CANCEL_BUTTON).waitForResponse();
		getAction().scrollTo(CANCEL_BUTTON);
		getAction().click(CANCEL_BUTTON);
		
		closeCommercialOrderNotificationPopUp();

		if (orderType.equalsIgnoreCase("Whole order")) {
			AjaxCondition.forElementVisible(ENTIRE_ORDER).waitForResponse();
			getAction().click(ENTIRE_ORDER);
			if(orderRouteStatus.equalsIgnoreCase("ON TIME")){
				getAction().waitFor(3000);
				verifyDayOfDelivery(agent,"Entire Order");
				//AjaxCondition.forElementVisible(CONTINUE_TO_CANCEL_ORDER).waitForResponse(3000);
				if(agent.equalsIgnoreCase("Delivery Driver")){
					AjaxCondition.forElementVisible(CONTINUE_TO_CANCEL_ORDER);
					getAction().click(CONTINUE_TO_CANCEL_ORDER);}}} 
		else {
			//String lineItemIndex = "1";
			AjaxCondition.forElementVisible(LINE_ITEM).waitForResponse();
			getAction().click(LINE_ITEM);
			int multiLineItem=getAction().getElementCount(DOS_ITEM_STATUS_COUNT.format(orderStatus));

			for (int i = 1; i <= multiLineItem; i++) {

				AjaxCondition.forElementVisible(LINE_ITEM_ROW.format(i)).waitForResponse();
				getAction().click(LINE_ITEM_ROW.format(i));
				//getAction().waitFor(2000);
				AjaxCondition.forElementVisible(LINE_ITEM_ROW_QUANTITY.format(orderStatus,i)).waitForResponse();
				//AjaxCondition.forElementVisible(LINE_ITEM_ROW_QUANTITY_AVAILABLE.format(i)).waitForResponse();
				getAction().type(LINE_ITEM_ROW_QUANTITY.format(orderStatus,i), getAction().getText(LINE_ITEM_ROW_QUANTITY_AVAILABLE.format(i)));
				getAction().waitFor(2000);
			}
			AjaxCondition.forElementVisible(LINE_ITEM_CANCEL).waitForResponse();
			itemToCancelName =  getAction().getText(LINE_ITEM_NAME_NOT_CANCELLED);
			getAction().scrollTo(LINE_ITEM_CANCEL);
			getAction().click(LINE_ITEM_CANCEL);
		}
		AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL).waitForResponse();

		int reasonCodeCount = getAction().getElementCount(REASON_DROPDOWN_CANCEL_OPTIONS_COUNT);
		if (reasonCodeCount == 0)
			PageAssert.fail("Reason code drop down has no data to select");
		else {
			int ran = util.randomGenerator(reasonCodeCount);
			if (ran == 0)
				ran++;
			Logger.log("Select one reason code from dropdown in random ", TestStepType.STEP);
			String reasonName = getAction().getText(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran));
			Logger.log("Select reason code " + reasonName, TestStepType.STEP);
			AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran)).waitForResponse();
			getAction().click(REASON_DROPDOWN_CANCEL);
			getAction().waitFor(1000);
			Logger.log("Select cancel reason code ", TestStepType.STEP);
			getAction().click(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran));

		}
		AjaxCondition.forElementVisible(COMPLETE_CANCEL_BUTTON).waitForResponse();
		getAction().click(COMPLETE_CANCEL_BUTTON);
		if (orderType.equalsIgnoreCase("Whole order")) {
			AjaxCondition.forElementVisible(COMPLETE_CANCEL_VERIFY).waitForResponse();
			Logger.log("Verified that Order status is Cancelled", TestStepType.VERIFICATION_PASSED);

			if (orderStatus.equalsIgnoreCase("Released")){
				AjaxCondition.forElementVisible(PEND_CODE).waitForResponse();
				String pendCode=getAction().getText(PEND_CODE);
				System.out.println("pend code -------------------"+pendCode);
				highlight(PEND_CODE);
				PageAssert.verifyEqual(pendCode, "TBC");}
			else
				AjaxCondition.forElementVisible(ORDER_STATUS_CANCELLED).waitForResponse();

			int lineItem = getAction().getElementCount(LINE_ITEM_COUNT);
			for (int i = 1; i <= lineItem; i++) {
				AjaxCondition.forElementVisible(LINE_ITEM_TEXT.format(i)).waitForResponse();
				if(getAction().getText(LINE_ITEM_TEXT.format(i)).contains("Released")){//For released items only after batch job runs, the status is shown as cancelled
					PageAssert.textPresentIn(LINE_ITEM_TEXT.format(i), "Released");
				}
				else
					PageAssert.textPresentIn(LINE_ITEM_TEXT.format(i), "Cancelled");
				//Logger.log("Verified that Line item no:" + i + " is cancelled", TestStepType.VERIFICATION_PASSED);

			}
		} else {
			AjaxCondition.forElementVisible(COMPLETE_CANCEL_VERIFY_FOR_LINE_ITEM).waitForResponse();
			AjaxCondition.forElementVisible(LINE_ITEM_TEXT.format(1)).waitForResponse();
			if(!getAction().getText(LINE_ITEM_TEXT.format(1)).contains("Released")){ //For released items only after batch job runs, the status is shown as cancelled

				AjaxCondition.forElementVisible(LINE_ITEM_NAME_STATUS.format(itemToCancelName,"Cancelled")).waitForResponse();
				PageAssert.textPresentIn(LINE_ITEM_NAME_STATUS.format(itemToCancelName,""), "Cancelled");
			}
			Logger.log("Verified that Line item is cancelled", TestStepType.VERIFICATION_PASSED);
			if(getAction().getElementCount(LINE_ITEM_NAME_CANCELLED)==getAction().getElementCount(LINE_ITEM_COUNT)){
				Logger.log("Verified that Order status is Cancelled", TestStepType.VERIFICATION_PASSED);
				AjaxCondition.forElementVisible(ORDER_STATUS_CANCELLED).waitForResponse();

			}else if (getAction().getText(LINE_ITEM_TEXT.format(1)).contains("Released")){ 
				AjaxCondition.forElementVisible(ORDER_STATUS_RELEASED).waitForResponse();
				Logger.log("Verified that order status is Released", TestStepType.VERIFICATION_PASSED);
			}
			else
				AjaxCondition.forElementVisible(ORDER_STATUS_SHIPPED).waitForResponse();
			Logger.log("Verified that order status is Shipped", TestStepType.VERIFICATION_PASSED);
		}

	}
	public void verifyTabsDelivery(String ordType){
		getAction().waitFor(3000);
		String tabArray[]={"Order Detail","Delivery Detail","Miscellaneous","Delivery Notes","Route Tracking","Action Center"};
		for(int i=0;i<tabArray.length;i++){
			AjaxCondition.forElementVisible(TAB_NAME.format(tabArray[i])).waitForResponse();
			getAction().scrollTo(TAB_NAME.format(tabArray[i]));
			getAction().click(TAB_NAME.format(tabArray[i]));
			if(tabArray[i].equalsIgnoreCase("Order Detail")){
				AjaxCondition.forElementVisible(DOS_ORDER_STATUS).waitForResponse();
				if(!getAction().getText(DOS_ORDER_STATUS).equalsIgnoreCase(ordType)){
					PageAssert.fail("Order status choosen and displayed is different. Order status choosen:"+ordType+" order status displayed:"+getAction().getText(DOS_ORDER_STATUS));
				}
				AjaxCondition.forElementVisible(DOS_ORDER_NUMBER).waitForResponse();
				String dosOrderNumber=getAction().getText(DOS_ORDER_NUMBER);
				SoftAssert.checkConditionAndContinueOnFailure("Verify whether dos order number not empty. Dos order number displayed:"+dosOrderNumber,!dosOrderNumber.isEmpty());
				AjaxCondition.forElementVisible(DOS_UNIT_NUMBER).waitForResponse();
				String dosUnitNumber=getAction().getText(DOS_UNIT_NUMBER);
				SoftAssert.checkConditionAndContinueOnFailure("Verify whether dos unit number not empty. Dos unit number displayed:"+dosUnitNumber,!dosUnitNumber.isEmpty());
				AjaxCondition.forElementVisible(SALESCHECK_LINK).waitForResponse();
				String salescheckLink=getAction().getText(SALESCHECK_LINK);
				SoftAssert.checkConditionAndContinueOnFailure("Verify whether salescheck Link not empty.sales checkLink displayed:"+salescheckLink,!salescheckLink.isEmpty());
				AjaxCondition.forElementVisible(SALE_DATE).waitForResponse();
				String saleDate=getAction().getText(SALE_DATE);
				SoftAssert.checkConditionAndContinueOnFailure("Verify whether sale Date not empty.sale Date displayed:"+saleDate,!saleDate.isEmpty());
				AjaxCondition.forElementVisible(DELIVERY_DATE_ORDER).waitForResponse();
				String delDate=getAction().getText(DELIVERY_DATE_ORDER);
				SoftAssert.checkConditionAndContinueOnFailure("Verify whether Delivery Date not empty.Delivery Date displayed:"+delDate,!delDate.isEmpty());
				AjaxCondition.forElementVisible(SHIP_METHOD).waitForResponse();
				String shipMethod=getAction().getText(SHIP_METHOD);
				SoftAssert.checkConditionAndContinueOnFailure("Verify whether ship method not empty.ship method displayed:"+shipMethod,!shipMethod.isEmpty());
				AjaxCondition.forElementVisible(TIME_WINDOW.format(1)).waitForResponse();
				String timeWindow=getAction().getText(TIME_WINDOW.format(1));
				SoftAssert.checkConditionAndContinueOnFailure("Verify whether time Window not empty.time Window displayed:"+timeWindow,!timeWindow.isEmpty());
				SoftAssert.checkElementAndContinueOnFailure(ORDER_TABLE, "Verify whether order table not empty", CheckLocatorFor.isVisible);

			}
			else if(tabArray[i].equalsIgnoreCase("Delivery Detail")){
				AjaxCondition.forElementVisible(TIME_WINDOW.format(2)).waitForResponse();
				String timeWindow=getAction().getText(TIME_WINDOW.format(2));
				SoftAssert.checkConditionAndContinueOnFailure("Verify whether time Window not empty.Time Window displayed:"+timeWindow,!timeWindow.isEmpty());
				AjaxCondition.forElementVisible(ORIGINAL_DELIVERY_DATE.format(1)).waitForResponse();
				String origDelDate=getAction().getText(ORIGINAL_DELIVERY_DATE.format(1));
				SoftAssert.checkConditionAndContinueOnFailure("Verify whether original delivery date not empty.original delivery Date displayed:"+origDelDate,!origDelDate.isEmpty());

			}
			else if(tabArray[i].equalsIgnoreCase("Miscellaneous")){
				AjaxCondition.forElementVisible(ORIGINAL_DELIVERY_DATE.format(2)).waitForResponse();
				String origDelDate=getAction().getText(ORIGINAL_DELIVERY_DATE.format(2));
				SoftAssert.checkConditionAndContinueOnFailure("Verify whether original delivery date not empty.original delivery Date displayed:"+origDelDate,!origDelDate.isEmpty());
			}
			else if(tabArray[i].equalsIgnoreCase("Delivery Notes")){
				SoftAssert.checkElementAndContinueOnFailure(DELIVERY_NOTE, "Verify whether delivery note text area is displayed", CheckLocatorFor.isVisible);
				SoftAssert.checkElementAndContinueOnFailure(ADD_NOTE, "Verify whether add note button is visible", CheckLocatorFor.isVisible);

			}
			else if(tabArray[i].equalsIgnoreCase("Route Tracking")){
				if(ordType.equalsIgnoreCase("open")){
					SoftAssert.checkElementAndContinueOnFailure(ROUTE_STATUS_OPEN, "Verify whether route status text displayed", CheckLocatorFor.isVisible);

				}
				else{
					SoftAssert.checkElementAndContinueOnFailure(ROUTE_STATUS, "Verify whether route status rows displayed", CheckLocatorFor.isVisible);
				}

			}
			else if(tabArray[i].equalsIgnoreCase("Action Center")){
				if(ordType.equalsIgnoreCase("open")){
					SoftAssert.checkElementAndContinueOnFailure(CANCEL_BUTTON, "Verify whether cancel button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(HOLD_FOR_DELIVERY, "Verify whether hold for future delivery button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(QUEUE_FOR_FOLLOW_UP, "Verify whether queue for followup button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(RERESERVE_BUTTON, "Verify whether rereserve button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(UPDATE_SCIM_CODE, "Verify whether update scim code button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(WRAPUP_BUTTON, "Verify whether wrapup button displayed", CheckLocatorFor.isVisible);

				}
				else if(ordType.equalsIgnoreCase("shipped")){
					SoftAssert.checkElementAndContinueOnFailure(PICKUP_BUTTON, "Verify whether pickup button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(EVEN_EXCHANGE_BUTTON, "Verify whether even exchange button displayed", CheckLocatorFor.isVisible);

				}
				else if(ordType.equalsIgnoreCase("released")){
					SoftAssert.checkElementAndContinueOnFailure(CANCEL_BUTTON, "Verify whether cancel button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(HOLD_FOR_DELIVERY, "Verify whether hold for future delivery button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(QUEUE_FOR_FOLLOW_UP, "Verify whether queue for followup button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(RERESERVE_BUTTON, "Verify whether rereserve button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(UPDATE_SCIM_CODE, "Verify whether update scim code button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(WRAPUP_BUTTON, "Verify whether wrapup button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(PICKUP_BUTTON, "Verify whether pickup button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(EVEN_EXCHANGE_BUTTON, "Verify whether even exchange button displayed", CheckLocatorFor.isVisible);

				}
				else if(ordType.equalsIgnoreCase("partially-shipped")){
					SoftAssert.checkElementAndContinueOnFailure(CANCEL_BUTTON, "Verify whether cancel button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(HOLD_FOR_DELIVERY, "Verify whether hold for future delivery button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(QUEUE_FOR_FOLLOW_UP, "Verify whether queue for followup button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(RERESERVE_BUTTON, "Verify whether rereserve button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(UPDATE_SCIM_CODE, "Verify whether update scim code button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(WRAPUP_BUTTON, "Verify whether wrapup button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(PICKUP_BUTTON, "Verify whether pickup button displayed", CheckLocatorFor.isVisible);
					SoftAssert.checkElementAndContinueOnFailure(EVEN_EXCHANGE_BUTTON, "Verify whether even exchange button displayed", CheckLocatorFor.isVisible);
				}
			}
		}

	}

	public void verifyAction(String action){
		getAction().waitFor(3000);
		Logger.log("Verify whether action can be performed on order :"+action, TestStepType.STEP);
		if(action.equalsIgnoreCase("cancel")){
			AjaxCondition.forElementVisible(CANCEL_BUTTON).waitForResponse();
			getAction().scrollTo(CANCEL_BUTTON);
			getAction().click(CANCEL_BUTTON);
			closeCommercialOrderNotificationPopUp();
			AjaxCondition.forElementVisible(ENTIRE_ORDER).waitForResponse();
			getAction().click(ENTIRE_ORDER);
			AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL).waitForResponse();
			AjaxCondition.forElementVisible(BACK_TO_ORDER).waitForResponse();
			getAction().click(BACK_TO_ORDER);

		}
		else if(action.equalsIgnoreCase("hold for future")){

			AjaxCondition.forElementVisible(HOLD_FOR_DELIVERY).waitForResponse();
			getAction().scrollTo(HOLD_FOR_DELIVERY);
			getAction().click(HOLD_FOR_DELIVERY);
			AjaxCondition.forElementVisible(SCHEDULE_FOLLOW_UP).waitForResponse();
			getAction().scrollTo(SCHEDULE_FOLLOW_UP);
			getAction().click(SCHEDULE_FOLLOW_UP);
			AjaxCondition.forElementVisible(AVAILABLE_DATE_IN_CALANDER).waitForResponse();
			AjaxCondition.forElementVisible(CANCEL_BUTTON_POPUP).waitForResponse();
			getAction().click(CANCEL_BUTTON_POPUP);

		}
		else if(action.equalsIgnoreCase("queue for follow up")){

			AjaxCondition.forElementVisible(QUEUE_FOR_FOLLOW_UP).waitForResponse();
			getAction().scrollTo(QUEUE_FOR_FOLLOW_UP);
			getAction().click(QUEUE_FOR_FOLLOW_UP);
			AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL).waitForResponse();
		}
		else if(action.equalsIgnoreCase("re-reserve")){
			AjaxCondition.forElementVisible(RERESERVE_BUTTON).waitForResponse();
			getAction().scrollTo(RERESERVE_BUTTON);
			getAction().click(RERESERVE_BUTTON);
			AjaxCondition.forElementVisible(LINE_ITEM_ROW.format(1)).waitForResponse();

		}

		else if(action.equalsIgnoreCase("update scim")){
			AjaxCondition.forElementVisible(UPDATE_SCIM_CODE).waitForResponse();
			getAction().scrollTo(UPDATE_SCIM_CODE);
			getAction().click(UPDATE_SCIM_CODE);
			AjaxCondition.forElementVisible(UPDATE_SCIM_CODES).waitForResponse();

		}
		else if(action.equalsIgnoreCase("wrapup")){
			AjaxCondition.forElementVisible(WRAPUP_BUTTON).waitForResponse();
			getAction().scrollTo(WRAPUP_BUTTON);
			getAction().click(WRAPUP_BUTTON);
			AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL).waitForResponse();
			AjaxCondition.forElementVisible(BACK_TO_ORDER).waitForResponse();
			getAction().click(BACK_TO_ORDER);

		}
		else if(action.equalsIgnoreCase("reschedule")){
			AjaxCondition.forElementVisible(RESCHEDULE_BUTTON).waitForResponse();
			getAction().scrollTo(RESCHEDULE_BUTTON);
			getAction().click(RESCHEDULE_BUTTON);	
			AjaxCondition.forElementVisible(ENTIRE_ORDER).waitForResponse();
			getAction().click(ENTIRE_ORDER);
			AjaxCondition.forElementVisible(AVAILABLE_DATE_IN_CALANDER).waitForResponse();

		}
		else if(action.equalsIgnoreCase("pickup")){

			AjaxCondition.forElementVisible(PICKUP_BUTTON).waitForResponse();
			getAction().scrollTo(PICKUP_BUTTON);
			getAction().click(PICKUP_BUTTON);
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(SELECT_ITEM.format(1)).waitForResponse();

		}
		else if(action.equalsIgnoreCase("even exchange")){
			AjaxCondition.forElementVisible(EVEN_EXCHANGE_BUTTON).waitForResponse();
			getAction().scrollTo(EVEN_EXCHANGE_BUTTON);
			getAction().click(EVEN_EXCHANGE_BUTTON);
			AjaxCondition.forElementVisible(EVEN_EXCHANGE_ITEM.format("1")).waitForResponse(5);
		}
		if(!action.equalsIgnoreCase("wrapup")){
			AjaxCondition.forElementVisible(SELECT_ANOTHER_ACTION).waitForResponse();
			getAction().click(SELECT_ANOTHER_ACTION);
		}

	}

	public void rescheduleDeliveryOrder(String type) throws ParseException{

		getAction().waitFor(3000);
		Logger.log("Verify whether order can be rescheduled", TestStepType.STEP);

		goToOrderDetail();
		String fDate=getAction().getText(DELIVERY_DATE);

		String pend1=getAction().getText(PEND_CODE);
		goToActionCenter();
		AjaxCondition.forElementVisible(RESCHEDULE_BUTTON).waitForResponse();
		getAction().scrollTo(RESCHEDULE_BUTTON);
		getAction().click(RESCHEDULE_BUTTON);


		AjaxCondition.forElementVisible(ENTIRE_ORDER).waitForResponse();
		getAction().click(ENTIRE_ORDER);
		AjaxCondition.forElementVisible(AVAILABLE_DATE_IN_CALANDER).waitForResponse();
		Logger.log("Verified that calander is shown with available reschedule dates ",TestStepType.VERIFICATION_PASSED);
		getAction().scrollTo(AVAILABLE_DATE_IN_CALANDER);
		getAction().click(AVAILABLE_DATE_IN_CALANDER);
		AjaxCondition.forElementVisible(AVAILABLE_TIME).waitForResponse();
		getAction().click(AVAILABLE_TIME);

		AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL).waitForResponse();


		int reasonCodeCount =getAction().getElementCount(REASON_DROPDOWN_CANCEL_OPTIONS_COUNT);
		if(reasonCodeCount==0)
			PageAssert.fail("Reason code drop down has no data to select");
		else
		{
			int ran = util.randomGenerator(reasonCodeCount);
			if(ran==0)
				ran++;
			Logger.log("Select one reason code from dropdown in random ",TestStepType.STEP);
			String reasonName=getAction().getText(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran));
			Logger.log("Select reason code "+reasonName,TestStepType.STEP);
			AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran)).waitForResponse();
			getAction().click(REASON_DROPDOWN_CANCEL);
			getAction().waitFor(1000);
			Logger.log("Select cancel reason code ",TestStepType.STEP);
			getAction().click(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran));

		}
		AjaxCondition.forElementVisible(RESCHEDULE_SUBMIT).waitForResponse();
		getAction().click(RESCHEDULE_SUBMIT);
		AjaxCondition.forElementVisible(RESCHEDULE_VERIFY).waitForResponse();

		String lDate=getAction().getText(DELIVERY_DATE);
		String pend2=getAction().getText(PEND_CODE);

		if(type.equalsIgnoreCase("OPEN")){
			PageAssert.verifyEqual(pend2, "");
		}
		else if(type.equalsIgnoreCase("RELEASED")){
			PageAssert.verifyEqual(pend2, "TBR");
		}else{
			PageAssert.verifyEqual(pend2, "");
		}

		Logger.log("Verified that pend code before reshedule was: "+pend1+" and pend code after reshedule is:"+pend2,TestStepType.VERIFICATION_PASSED);

		SimpleDateFormat simpleDate = new SimpleDateFormat("mm/dd/yyyy");
		Date date = simpleDate.parse(fDate);
		Date date2 = simpleDate.parse(lDate);
		int comResult = date2.compareTo(date);
		if (!(comResult>0)) {
			PageAssert.fail("Resheduled date is invalid");	
		}

		Logger.log("Verified that delivery date is rescheduled to a later date. Date before reschedule:"+fDate+" Date after reschedule:"+lDate,TestStepType.VERIFICATION_PASSED);	



	}

	public void rescheduleDeliveryOrder(String type, String ordType) throws ParseException {
		String orderWith;
		Logger.log("Verify whether order can be rescheduled", TestStepType.STEP);

		goToOrderDetail();
		String fDate = getAction().getText(DELIVERY_DATE);

		String pend1 = getAction().getText(PEND_CODE);
		goToActionCenter();
		AjaxCondition.forElementVisible(RESCHEDULE_BUTTON).waitForResponse();
		getAction().scrollTo(RESCHEDULE_BUTTON);
		getAction().click(RESCHEDULE_BUTTON);
		getContext().put("adjustmentOption", "");
		orderWith = (String) getContext().get("orderWith");
		if (orderWith==null){
			orderWith="nothingSpecial";
		}
		System.out.println("value of order with      -"+orderWith+"-");
		if(orderWith.equalsIgnoreCase("InstallationItem")){
			Logger.log("Verify the  Reschedule installation service notification is displayed", TestStepType.STEP);
			AjaxCondition.forElementPresent(ACTION_CENTER_INSTALLATION_NOTES).waitWithoutException(5);
			PageAssert.textPresent(ACTION_CENTER_INSTALLATION_NOTES, "Note: This order includes installation service, please reschedule the installation service.");
		}

		if (ordType.equalsIgnoreCase("ORDER")) {
			AjaxCondition.forElementVisible(ENTIRE_ORDER).waitForResponse();
			getAction().click(ENTIRE_ORDER);
		} else {
			AjaxCondition.forElementVisible(LINE_ITEM).waitForResponse();
			getAction().click(LINE_ITEM);
			AjaxCondition.forElementVisible(LINE_ITEM_ROW).waitForResponse();
			getAction().click(LINE_ITEM_ROW);
			AjaxCondition.forElementVisible(CONTINUE_BUTTON).waitForResponse();
			getAction().scrollTo(CONTINUE_BUTTON);
			getAction().waitFor(2000);
			getAction().click(CONTINUE_BUTTON);
			AjaxCondition.forElementVisible(CREATE_NEW_ORDER).waitForResponse();
			getAction().click(CREATE_NEW_ORDER);
			AjaxCondition.forElementVisible(RESCHEDULE_LINE_ITEM_VERIFY).waitForResponse();
			AjaxCondition.forElementVisible(SUCCESS_OK_BUTTON).waitForResponse();
			getAction().click(SUCCESS_OK_BUTTON);
			ordType = "ORDER";
			rescheduleDeliveryOrder(type, ordType);
			return;
		}
		AjaxCondition.forElementVisible(AVAILABLE_DATE_IN_CALANDER).waitForResponse();
		Logger.log("Verified that calander is shown with available reschedule dates ", TestStepType.VERIFICATION_PASSED);
		getAction().scrollTo(AVAILABLE_DATE_IN_CALANDER);
		getAction().click(AVAILABLE_DATE_IN_CALANDER);
		AjaxCondition.forElementVisible(AVAILABLE_TIME).waitForResponse();
		getAction().click(AVAILABLE_TIME);

		AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL).waitForResponse();

		int reasonCodeCount = getAction().getElementCount(REASON_DROPDOWN_CANCEL_OPTIONS_COUNT);
		if (reasonCodeCount == 0)
			PageAssert.fail("Reason code drop down has no data to select");
		else {
			int ran = util.randomGenerator(reasonCodeCount);
			if (ran == 0)
				ran++;
			Logger.log("Select one reason code from dropdown in random ", TestStepType.STEP);
			String reasonName = getAction().getText(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran));
			Logger.log("Select reason code " + reasonName, TestStepType.STEP);
			AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran)).waitForResponse();
			getAction().click(REASON_DROPDOWN_CANCEL);
			getAction().waitFor(1000);
			Logger.log("Select cancel reason code ", TestStepType.STEP);
			getAction().click(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran));

		}
		AjaxCondition.forElementVisible(RESCHEDULE_SUBMIT).waitForResponse();
		getAction().click(RESCHEDULE_SUBMIT);
		AjaxCondition.forElementVisible(RESCHEDULE_VERIFY).waitForResponse();
		AjaxCondition.forElementVisible(DELIVERY_DATE).waitForResponse();
		AjaxCondition.forElementNotVisible(DELIVERY_DATE_TEXT.format(fDate)).waitForResponse();

		String lDate = getAction().getText(DELIVERY_DATE);
		String pend2 = getAction().getText(PEND_CODE);

		if (type.equalsIgnoreCase("OPEN")) {
			PageAssert.verifyEqual(pend2, "");
		} else if (type.equalsIgnoreCase("RELEASED")) {
			PageAssert.verifyEqual(pend2, "TBR");
		} else {
			PageAssert.verifyEqual(pend2, "");
		}

		Logger.log("Verified that pend code before reshedule was: " + pend1 + " and pend code after reshedule is:" + pend2, TestStepType.VERIFICATION_PASSED);

		SimpleDateFormat simpleDate = new SimpleDateFormat("mm/dd/yyyy");
		Date date = simpleDate.parse(fDate);
		Date date2 = simpleDate.parse(lDate);
		int comResult = date2.compareTo(date);
		if (!(comResult > 0)) {
			PageAssert.fail("Resheduled date is invalid");
		}

		Logger.log("Verified that delivery date is rescheduled to a later date. Date before reschedule:" + fDate + " Date after reschedule:" + lDate, TestStepType.VERIFICATION_PASSED);

	}

	public void scheduleFollowUp(){
		Logger.log("Verify whether schedule for follow up can be done", TestStepType.STEP);
		goToOrderDetail();
		String fDate=getAction().getText(DELIVERY_DATE);

		String pend1=getAction().getText(PEND_CODE);

		String orderStatus = getAction().getText(ORDER_STATUS);
		goToActionCenter();

		AjaxCondition.forElementVisible(HOLD_FOR_DELIVERY).waitForResponse();
		getAction().scrollTo(HOLD_FOR_DELIVERY);
		getAction().click(HOLD_FOR_DELIVERY);
		getContext().put("adjustmentOption", "");
		AjaxCondition.forElementVisible(SCHEDULE_FOLLOW_UP).waitForResponse();
		getAction().scrollTo(SCHEDULE_FOLLOW_UP);
		getAction().click(SCHEDULE_FOLLOW_UP);
		AjaxCondition.forElementVisible(AVAILABLE_DATE_IN_CALANDER).waitForResponse();
		String selectedDate=getAction().getText(AVAILABLE_DATE_IN_CALANDER_TEXT);
		getAction().click(AVAILABLE_DATE_IN_CALANDER);
		AjaxCondition.forElementVisible(RESCHEDULE_SUBMIT).waitForResponse();
		getAction().click(RESCHEDULE_SUBMIT);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(DELIVERY_DATE).waitForResponse();
		String lDate=getAction().getText(DELIVERY_DATE);

		AjaxCondition.forElementVisible(PEND_CODE).waitForResponse();
		String pend2=getAction().getText(PEND_CODE);
		System.out.println("pend 2 -------------------"+pend2);
		if(orderStatus.equalsIgnoreCase("Open")){

			PageAssert.verifyEqual(pend2, "DDH");

		}else if (orderStatus.equalsIgnoreCase("Released")) {
			PageAssert.verifyEqual(pend2, "TBH");

		}

		Logger.log("Verified that pend code before reshedule was: "+pend1+" and pend code after reshedule is:"+pend2,TestStepType.VERIFICATION_PASSED);

		String[] dateArray=lDate.split("/");
		PageAssert.verifyEqual(selectedDate, dateArray[1]);
		Logger.log("Verified that delivery date is rescheduled to a later date. Date before schedule for follow up:"+fDate+" Date after Date before schedule for follow up:"+lDate,TestStepType.VERIFICATION_PASSED);	

	}

	public void queueForFollowUp(){
		Logger.log("Verify whether item can be routed to HD-account validation queue", TestStepType.STEP);

		AjaxCondition.forElementVisible(QUEUE_FOR_FOLLOW_UP).waitForResponse();
		getAction().scrollTo(QUEUE_FOR_FOLLOW_UP);
		getAction().click(QUEUE_FOR_FOLLOW_UP);
		AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL).waitForResponse();
		getAction().click(REASON_DROPDOWN_CANCEL);
		AjaxCondition.forElementVisible(SELECTED_OPTION_QUEUE).waitForResponse();
		getAction().click(SELECTED_OPTION_QUEUE);
		AjaxCondition.forElementVisible(CONTINUE_BUTTON).waitForResponse();
		getAction().click(CONTINUE_BUTTON);
		AjaxCondition.forElementVisible(ADJUSTMENT_NOTES).waitForResponse();
		getAction().type(ADJUSTMENT_NOTES, "Test");
		AjaxCondition.forElementVisible(QUEUE_FOR_FOLLOW_UP_CHECKBOX).waitForResponse();
		getAction().click(QUEUE_FOR_FOLLOW_UP_CHECKBOX);
		AjaxCondition.forElementVisible(BUTTON_WRAP_ORDER).waitForResponse();
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(QUEUE_FOR_FOLLOW_UP_VERIFY).waitForResponse();

		Logger.log("Order successfully routed to HD-Account validation queue",TestStepType.VERIFICATION_PASSED);	

	}
	public void queueForFollowUp(String reasonName){
		Logger.log("Click on the queue for follow up button" , TestStepType.STEP);
		AjaxCondition.forElementVisible(QUEUE_FOR_FOLLOW_UP).waitForResponse();
		getAction().scrollTo(QUEUE_FOR_FOLLOW_UP);
		getAction().click(QUEUE_FOR_FOLLOW_UP);
		Logger.log("Click on the reason dropdown" , TestStepType.STEP);
		AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL).waitForResponse();
		getAction().click(REASON_DROPDOWN_CANCEL);
		AjaxCondition.forElementVisible(SELECTED_OPTION_QUEUE.format(reasonName)).waitForResponse();
		getAction().click(SELECTED_OPTION_QUEUE.format(reasonName));
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(QUEUE_NAME).waitForResponse();
		String queue=getAction().getText(QUEUE_NAME);
		getContext().put("queue", queue);

		if( getAction().isVisible(CASE_EXISTS)){
			SoftAssert.checkConditionAndContinueOnFailure(getAction().getText(CASE_EXISTS), true);
			Logger.log("click on OK button",TestStepType.STEP);
			getAction().click(OK_BUTTON_ON_POPUP);
			SoftAssert.checkConditionAndContinueOnFailure("Continue button is disable as case already exists ", (!getAction().findElement(CONTINUE_BUTTON).isEnabled()));}

		else{
			SoftAssert.checkElementAndContinueOnFailure(SELECTED_OPTION_QUEUE.format(reasonName), reasonName+" is present", CheckLocatorFor.isPresent);
			Logger.log("Click on the continue button" , TestStepType.STEP);
			AjaxCondition.forElementVisible(CONTINUE_BUTTON).waitForResponse();
			getAction().click(CONTINUE_BUTTON);
			AjaxCondition.forElementVisible(ADJUSTMENT_NOTES).waitForResponse();
			getAction().type(ADJUSTMENT_NOTES, "Test");
			AjaxCondition.forElementVisible(QUEUE_FOR_FOLLOW_UP_CHECKBOX).waitForResponse();
			getAction().click(QUEUE_FOR_FOLLOW_UP_CHECKBOX);
			Logger.log("Click on the wrap up button" , TestStepType.STEP);
			AjaxCondition.forElementVisible(BUTTON_WRAP_ORDER).waitForResponse();
			getAction().waitFor(3000);
			getAction().click(BUTTON_WRAP_ORDER);
			AjaxCondition.forElementVisible(QUEUE_FOR_FOLLOW_UP_VERIFY).waitForResponse();		
			Logger.log("Order successfully routed to "+getContext().get("queue")+" "+TestStepType.VERIFICATION_PASSED);
			getAction().click(OK_BUTTON_ON_POPUP);
			getAction().waitFor(3000);
		}
	}
	public void verifyOrderPhoneNumber(String phoneNumber) {
		Logger.log("Verify Call phone number matches with order phone number on Order details page "+phoneNumber ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(CUSTOMER_PHONE_NUMBER).waitForResponse();
		PageAssert.verifyTrue(StringUtils.extractNumber(getAction().getText(CUSTOMER_PHONE_NUMBER)).equals(phoneNumber), "Verify Customer phone number matcher with order - "+phoneNumber);

	}

	public void msatOnlineAgentOrderWrapupRFC() {
		getAction().waitFor(2000);
		Logger.log("Click on line item/sales check tab",TestStepType.STEP);
		AjaxCondition.forElementVisible(WRAP_UP_TAB).waitForResponse();
		getAction().click(WRAP_UP_TAB);

		//Click on Category
		getAction().click(CATEGORY_DROPDOWN);
		getAction().selectByVisibleText(CATEGORY_DROPDOWN, "General Inquiry");
		Logger.log("Select General Inquiry Option in Category", TestStepType.VERIFICATION_PASSED);

		getAction().click(REASON_CODE_DROPDOWN);
		getAction().waitFor(1000);

		getAction().selectUsingIndex(REASON_CODE_DROPDOWN, 4);

		Logger.log("Select 'NO Action Needed - General Question' in Reason Code", TestStepType.STEP);
		SoftAssert.checkConditionAndContinueOnFailure("Verify Not Applicable Text is Present", getAction().getText(SELECT_QUEUE_NO_RESPONSE).equals("Not Applicable"));

		getAction().type(ORDER_NOTES_TEXT_AREA, "No action was performed on the order other than opening and closing the order through automation script to monitor production environment.");

		check(SELECT_ITEM_FOR_WRAPUP);
		clickWrapOrderContact();

		Logger.log("Click on Wrap Up Tab",TestStepType.STEP);
		getAction().waitFor(1000);

		//Select Member in the RFC Form
		getAction().click(RFC_WHO_CONTACTED);
		getAction().selectByVisibleText(RFC_WHO_CONTACTED, "Member");

		//Select "Order Status And Tracking" for "why did they contact us?"
		getAction().click(RFC_WHY_CONTACTED);
		getAction().selectByVisibleText(RFC_WHY_CONTACTED, "Order Status and Tracking");


		//Select "Order Status" for "Tell us more"
		getAction().click(RFC_TELL_US_MORE);
		getAction().selectByVisibleText(RFC_TELL_US_MORE, "Order Status");


		//Select RFC Wrapup Reason 1
		getAction().click(RFC_REASON1);
		getAction().selectByVisibleText(RFC_REASON1, "Order status problem");

		//Edited Email Address
		getAction().click(RFC_EDITED_EMAIL_ADDRESS);
		getAction().type(RFC_EDITED_EMAIL_ADDRESS, "realtimemsat@Sharklasers.com");		

		//Radio button - Email address confirmation
		getAction().click(RFC_RADIO_EDITED_EMAIL);

		Logger.log("Click Wrap Up on RFC Form", TestStepType.STEP);
		getAction().click(RFC_BUTTON_WRAPUP);
		getAction().waitFor(3000);
	}


	public void msatOnlineAgentGeneralInquiryWrapupRFC() {
		getAction().waitFor(2000);
		Logger.log("Guest Icon",TestStepType.STEP);
		AjaxCondition.forElementVisible(RFC_GUEST_ICON).waitForResponse();
		getAction().click(RFC_GUEST_ICON);

		//Click on User Icon
		getAction().click(USER_ICON);
		getAction().click(USER_ICON_RFC);
		Logger.log("Select RCF from user icon", TestStepType.VERIFICATION_PASSED);

		//Select Member in the RFC Form
		getAction().click(RFC_WHO_CONTACTED);
		getAction().selectByVisibleText(RFC_WHO_CONTACTED, "Member");

		//Select "Order Status And Tracking" for "why did they contact us?"
		getAction().click(RFC_WHY_CONTACTED);
		getAction().selectByVisibleText(RFC_WHY_CONTACTED, "Order Status and Tracking");


		//Select "Order Status" for "Tell us more"
		getAction().click(RFC_TELL_US_MORE);
		getAction().selectByVisibleText(RFC_TELL_US_MORE, "Order Status");


		//Select RFC Wrapup Reason 1
		getAction().click(RFC_REASON1);
		getAction().selectByVisibleText(RFC_REASON1, "Order status problem");

		//Edited Email Address
		getAction().click(RFC_EDITED_EMAIL_ADDRESS);
		getAction().type(RFC_EDITED_EMAIL_ADDRESS, "realtimemsat@Sharklasers.com");		

		//Radio button - Email address confirmation
		getAction().click(RFC_RADIO_EDITED_EMAIL);

		Logger.log("Click Wrap Up on RFC Form", TestStepType.STEP);
		getAction().click(RFC_BUTTON_WRAPUP);
		getAction().waitFor(3000);
	}


	/**
	 * Delivery Flow - Verification to assert that pended orders don't have the 'UPDATE' button
	 */
	public OrderDetailsPage verifySuggestedAddressForAddressUpdates(String searchVal, String searchField) {
		Logger.log("Verify if Order Details Page is displayed", TestStepType.VERIFICATION_STEP);
		getAction().waitFor(2000);
		if(AjaxCondition.forElementVisible(NO_RESULTS_FOUND).waitWithoutException(1)) 	
			Logger.log("Test Data is not valid");
		else
		{
			SoftAssert.checkElementAndContinueOnFailure(DOS_ORDER_NO_LONGER_EXIST, "Verify Order Exists", CheckLocatorFor.isNotVisible);

			//Check if the Update button is enabled
			AjaxCondition.forElementVisible(UPDATE_BUTTON).waitForResponse();
			if (getAction().findElement(UPDATE_BUTTON).isEnabled()) {
				Logger.log("Update button is enabled.", TestStepType.VERIFICATION_PASSED);
				getAction().click(UPDATE_BUTTON);
			}
			else {
				PageAssert.fail("Update button is disabled");
			}

			//DOS_ORDER_SUMMARY_ADDRESS1
			AjaxCondition.forElementVisible(DOS_ORDER_SUMMARY_ADDRESS1).waitForResponse();
			getAction().click(DOS_ORDER_SUMMARY_ADDRESS1);
			String address1	= getAction().getText(DOS_ORDER_SUMMARY_ADDRESS1).toString();
			String newaddress1 = "99999" + address1;
			getAction().type(DOS_ORDER_SUMMARY_ADDRESS1, newaddress1);
			//getAction().click(UPDATE_BUTTON);
			AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse();
			getAction().scrollTo(SAVE_BUTTON);
			getAction().click(SAVE_BUTTON);



		}	
		return this;
	}

	public void OpenMsatEmail() {
		getAction().waitFor(2000);
		Logger.log("Guest Icon",TestStepType.STEP);
		AjaxCondition.forElementVisible(RFC_GUEST_ICON).waitForResponse();
		getAction().click(RFC_GUEST_ICON);
		getAction().driver.get("https://mail.cognizant.com");
	}
	public OrderDetailsPage verifySywLinkDetailsPageDisplayed() {
		Logger.log("Verify if syw Details Page is displayed", TestStepType.VERIFICATION_STEP);
		getAction().waitFor(2000);
		if(AjaxCondition.forElementVisible(SYW_NO_SEARCH_RESULTS_LABEL).waitWithoutException(1)){
			Logger.log("Test Data is not valid");
			System.out.println("--------------------------------------------no results");
		}

		else
		{
			System.out.println("--------------------pass");
			SoftAssert.checkElementAndContinueOnFailure(SYW_LINK_LINKED_INFORMATION, "Verify syw Linked section is displayed", CheckLocatorFor.isVisible);
			SoftAssert.checkElementAndContinueOnFailure(SYW_LINK_ADDRESS_INFORMATION, "Verify address Information section is displayed", CheckLocatorFor.isVisible);
			SoftAssert.checkElementAndContinueOnFailure(SYW_LINK_EMAIL_INFORMATION, "Verify email Information is displayed", CheckLocatorFor.isVisible);
			SoftAssert.checkElementAndContinueOnFailure(SYW_LINK_CITY_INFORMATION, "Verify city Information is displayed", CheckLocatorFor.isVisible);
		}
		return this;
	}
	/*public OrderDetailsPage verifyReasonCodePresence(String reasonName, boolean statusPresence){
	Logger.log("Verify for reason code presence in queue for follow up", TestStepType.STEP);
	AjaxCondition.forElementVisible(QUEUE_FOR_FOLLOW_UP).waitForResponse();
	getAction().scrollTo(QUEUE_FOR_FOLLOW_UP);
	getAction().click(QUEUE_FOR_FOLLOW_UP);
	AjaxCondition.forElementVisible(REASON_DROPDOWN).waitForResponse();
	getAction().click(REASON_DROPDOWN);	
	scrollDown();

	if(statusPresence){
		SoftAssert.checkElementAndContinueOnFailure(SELECTED_OPTION_QUEUE.format(reasonName), reasonName+" is present", CheckLocatorFor.isVisible);
	}
	else
		SoftAssert.checkElementAndContinueOnFailure(SELECTED_OPTION_QUEUE.format(reasonName), reasonName+" is not present", CheckLocatorFor.isNotVisible);
	return this;

}*/
	public OrderDetailsPage clickOnReasonDropdown(){
		Logger.log("Click on the queue for folow up button", TestStepType.STEP);
		AjaxCondition.forElementVisible(QUEUE_FOR_FOLLOW_UP).waitForResponse();
		getAction().scrollTo(QUEUE_FOR_FOLLOW_UP);
		getAction().click(QUEUE_FOR_FOLLOW_UP);
		Logger.log("Click on the reasons dropdown", TestStepType.STEP);
		AjaxCondition.forElementVisible(REASON_DROPDOWN).waitForResponse();
		getAction().click(REASON_DROPDOWN);
		getAction().waitFor(3000);
		return this;

	}
	public OrderDetailsPage verifyReasonCodes(String reasonName, boolean statusPresence){
		if(statusPresence){
			SoftAssert.checkElementAndContinueOnFailure(SELECTED_OPTION_QUEUE.format(reasonName), reasonName+" is present", CheckLocatorFor.isVisible);
		}
		else
			SoftAssert.checkElementAndContinueOnFailure(SELECTED_OPTION_QUEUE.format(reasonName), reasonName+" is not present", CheckLocatorFor.isNotVisible);
		return this;
	}

	public int generateRandomNumberSelect(Locator select){
		System.out.println("Count for select"+getAction().getElementCount(select));
		System.out.println("Conunt Locator "+select.getValue());
		int randomCode=util.randomGenerator(getAction().getElementCount(select));
		if(randomCode<=1){
			randomCode++;
		}
		return randomCode;
	}
	public OrderDetailsPage wrapUpOrderWithoutContactDelivery(){
		Logger.log("Click on Wrap Up button in Action Center");
		AjaxCondition.forElementVisible(WRAPUP_BUTTON).waitForResponse();
		getAction().scrollTo(WRAPUP_BUTTON);
		getAction().click(WRAPUP_BUTTON);
		getAction().waitFor(4000);

		//int rndCodeCategory = generateRandomNumberSelect(CATEGORY_DROPDOWN_CODE_COUNT);
		//System.out.println("rndCodeCategory-----"+rndCodeCategory);
		Logger.log("Select option "+"HD-General Inquiry"+" in category dropdown",TestStepType.STEP);
		AjaxCondition.forElementPresent(CATEGORY_DROPDOWN_CODE.format(7));
		getAction().selectUsingIndex(CATEGORY_DROPDOWN_CODE, 7);

		//int rndCodeReason = generateRandomNumberSelect(DELIVERY_REASON_DROPDOWN_COUNT);
		//	Logger.log("Select option #"+rndCodeReason+" in category select");
		Logger.log("Select option "+"Gift Card Inquiry"+" in Reason dropdown",TestStepType.STEP);
		AjaxCondition.forElementPresent(DELIVERY_REASON_DROPDOWN.format(1));
		getAction().selectUsingIndex(DELIVERY_REASON_DROPDOWN, 1);
		/*}catch(Exception e){
			Logger.log("Reason Code Dropdown empty. Proceeding with Wrapup without selecting any Reason Code");
			e.printStackTrace();
		}*/

		Logger.log("Select All in Items for contact");
		getAction().click(SELECT_ITEMS_FOR_CONTACT);
		Logger.log("Click WRAP UP & CONTINUE W/ CONTACT");
		getAction().click(WRAP_UP_WITHOUT_CONTACT);
		getAction().waitFor(4000);
		return this;

	}

	public OrderDetailsPage closeCommercialOrderNotificationPopUp(){
		Logger.log("Close the Commercial Sales Order Pop Up if present",TestStepType.STEP);
		if (AjaxCondition.forElementVisible(COMMERCIAL_SALES_ORDER_POPUP_TEXT).waitWithoutException(3))
		{	
			getAction().click(COMMERCIAL_SALES_ORDER_POPUP_CONTINUE_BUTTON);
		}
		return this;

	}
	public void verifyCapturedInInteractionsforUpdateContact() {
		getAction().waitFor(5000);
		Logger.log("Verify adjustment done on order are captured in Current Interaction", TestStepType.STEP);
		System.out.println("----------------------------verifyAdjustmentCapturedInInteraction");
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		getAction().waitFor(3000);
		String[] type= {"Update Delivery Address","Update Delivery Contact Details"} ;
		for(int i=0; i<type.length;i++)
			SoftAssert.checkElementAndContinueOnFailure(ORDER_CONTACT_HISTORY_ADJUSTMENT_UPDATE.format(type[i]), type[i]+" is present", CheckLocatorFor.isPresent);
		getAction().click(CONTACT_HISTORY_MENU_DOWN);

	}
	public void verifyActionCapturedInNotesForUpdateContact(){
		Logger.log("Verify adjustment done on order are captured in Notes", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		getAction().waitFor(3000);

		String originalName = (String) getContext().get("originalName");
		String updatedName= (String) getContext().get("updatedName");


		String originalHomePhone= (String) getContext().get("originalHomePhone");
		String updatedHomePhone= (String) getContext().get("updatedHomePhone");

		String originalEmail= (String) getContext().get("originalEmail");
		String updatedEmail= (String) getContext().get("updatedEmail");

		String orginalAddress= (String) getContext().get("orginalAddress");
		String updatedAddress= (String) getContext().get("updatedAddress");
		AjaxCondition.forElementPresent(UPDATE_DELIVERY_ADDRESS).waitForResponse(3000);
		getAction().click(UPDATE_DELIVERY_ADDRESS);
		getAction().click(SHOW_NAME_UPDATE);
		//AjaxCondition.forElementPresent((VALUES.format("Order Ship To Cutomer Name",originalName,updatedName))).waitForResponse(3000);
		//getAction().scrollTo(VALUES.format("Order Ship To Cutomer Name",originalName,updatedName));
		//AjaxCondition.forElementPresent(SHOW_NAME_UPDATE).waitForResponse();
		//getAction().click(SHOW_NAME_UPDATE);
		//PageAssert.textPresent(VALUE.format(originalName), originalName);
		//PageAssert.textPresent(VALUE.format(getContext().get("UpdatedName")), (String) getContext().get("UpdatedName"));
		//SoftAssert.checkConditionAndContinueOnFailure("Verify the original name is displayed in ",getAction().getText(VALUE.format(originalName)).equalsIgnoreCase(originalName));
		System.out.println("------------------------------------------------------"+UPDATE_CONTACT_NOTES.format("Order Ship To Cutomer Name",originalName,updatedName).getValue());
		SoftAssert.checkElementAndContinueOnFailure(UPDATE_CONTACT_NOTES.format("Order Ship To Cutomer Name",originalName,updatedName),originalName+" has been updated to "+updatedName , CheckLocatorFor.isPresent);
		SoftAssert.checkElementAndContinueOnFailure(UPDATE_CONTACT_NOTES.format("Order Ship To Home Phone",	 originalHomePhone,updatedHomePhone),originalHomePhone+" has been updated to "+updatedHomePhone , CheckLocatorFor.isPresent);
		SoftAssert.checkElementAndContinueOnFailure(UPDATE_CONTACT_NOTES.format("Order Sold To Email Address",originalEmail,updatedEmail),originalEmail+" has been updated to "+updatedEmail, CheckLocatorFor.isPresent);
		//		SoftAssert.checkElementAndContinueOnFailure(UPDATE_CONTACT_NOTES.format("Order Ship To Cutomer Address",originalName,updatedName),"original value and updated value " , CheckLocatorFor.isPresent);
		/*SoftAssert.checkElementAndContinueOnFailure(UPDATE_CONTACT_NOTES.format("Order Ship To Cutomer Name",originalName,updatedName),"original value and updated value " , CheckLocatorFor.isPresent);
		SoftAssert.checkElementAndContinueOnFailure(UPDATE_CONTACT_NOTES.format("Order Ship To Cutomer Name",originalName,updatedName),"original value and updated value " , CheckLocatorFor.isPresent);*/

	}
	public void verifyLineItemDetail(String orderType){

		scrollDown();
		getAction().waitFor(3000);
		List<String> list = new ArrayList<String>();

		AjaxCondition.forElementPresent(ORDER_ROUTE_STATUS).waitForResponse(3000);
		String orderRouteStatus=getAction().getText(ORDER_ROUTE_STATUS);
		System.out.println("orderRouteStatus==="+ orderRouteStatus);
		int multiLineItem=getAction().getElementCount(DOS_ITEM_STATUS_COUNT.format(orderType));
		System.out.println("-------------------------------------------- "+multiLineItem);
		getAction().waitFor(2000);
		Logger.log("Order have "+multiLineItem+" multiline item");
		for (int i = 1; i <= multiLineItem; i++) {
			list.add(getAction().getText(DIVISION.format(orderType,i))+getAction().getText(ITEM.format(orderType,i)) +"000");
		}
		getContext().put("adjustmentOption", list);
		getContext().put("orderRouteStatus", orderRouteStatus);
		getContext().put("multiLineItem", multiLineItem);
		System.out.println("---------------------------------------------"+getContext().get("adjustmentOption"));
		
	}

	public void verifyAdjustmentCapturedInInteractions(String adjust, String note){
		getAction().waitFor(5000);
		Logger.log("Verify adjustment done on order are captured in Current Interaction", TestStepType.STEP);
		System.out.println("----------------------------verifyAdjustmentCapturedInInteraction");
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		getAction().waitFor(5000);
		@SuppressWarnings("unchecked")
		List <String> list = (List<String>) getContext().get("adjustmentOption");
		Iterator<String> it = list.iterator();
		String notes=null;
		String historyNotes=null;
		StringBuilder sb= new StringBuilder(note);	
		while (it.hasNext()){
			String item = (String) it.next();
			System.out.println(item);
			notes =sb.append(item+", ").toString();
			if(notes.endsWith(", "))
				historyNotes =notes.substring(0, notes.length()-2)+"]";
		}
		System.out.println(historyNotes);
		getContext().put("historyNotes", historyNotes);
		System.out.println("locator under trial "+ORDER_CONTACT_HISTORY_ADJUSTMENT.format(adjust,historyNotes).getValue());
		SoftAssert.checkElementAndContinueOnFailure(ORDER_CONTACT_HISTORY_ADJUSTMENT.format(adjust,historyNotes),historyNotes , CheckLocatorFor.isPresent);
		getAction().click(CONTACT_HISTORY_MENU_DOWN);

	}

	public void verifyPickupbuttonnotPresent(){
		Logger.log("Verify Pickup button is not present in action center", TestStepType.STEP);
		getAction().waitFor(3000);
		if(getAction().isVisible(PICKUP_BUTTON)){
			PageAssert.fail("Pickup button is Present");
		}
		else
			Logger.log("Pickup button is not present ",TestStepType.VERIFICATION_PASSED);
	}

	public void verifyActionCapturedHistoryNotes(){
		Logger.log("Verify adjustment done on order are captured in History Notes", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		getAction().waitFor(3000);
		String historyNotes=(String) getContext().get("historyNotes");
		SoftAssert.checkElementAndContinueOnFailure(CONTACT_HISTORY_NOTES.format(historyNotes),historyNotes , CheckLocatorFor.isPresent);
	}

	/**
	 * Close warning  popup if its present
	 * @return
	 */
	public OrderDetailsPage closeWarningPopupWindow() {

		getAction().waitFor(3000);
		if(AjaxCondition.forElementVisible(ERROR_POPUP_CLOSE).waitWithoutException(1)){
			Logger.log("Close Error Pop-up Window", TestStepType.SUBSTEP);
			int numOfCloseButtons = getAction().getElementCount(ERROR_POPUP_CLOSE);
			Locator closeLocator = new Locator("","(//button[@id='modalclose'])[{0}]","Close button");
			while(numOfCloseButtons>0){
				getAction().click(closeLocator.format(numOfCloseButtons));
				numOfCloseButtons--;
				getAction().waitFor(1000);
			}
		}   
		if(AjaxCondition.forElementVisible(WARNING_POPUP).waitWithoutException(1)){
			Logger.log("Close Warning Pop-up Window", TestStepType.SUBSTEP);
			getAction().click(WARNING_POPUP_CLOSE);
			getAction().waitFor(1000);
		}
		return this;
	}
	public OrderDetailsPage clickSubmitButton() {
		getAction().waitFor(2000);
		getAction().click(SUBMIT_BUTTON);
		getAction().waitFor(2000);
		return this;
	}

	public OrderDetailsPage verifyupdateScimCode(String orderType) {
		getAction().waitFor(4000);
		scrollDown();
		//	if(orderType.equalsIgnoreCase("open")){
		getAction().waitFor(4000);
		int multiLineItem=getAction().getElementCount(DOS_ITEM_STATUS_COUNT.format(orderType));
		System.out.println("----------------------------------------------------------------xpath"+DOS_ITEM_STATUS_COUNT.format(orderType).getValue());
		System.out.println("-----------------------------------------------------------"+multiLineItem);
		Logger.log("Order have "+multiLineItem+""+ orderType+""+" multiline item");
		Logger.log("Item description before SCIM CODE updation", TestStepType.STEP);
		for (int i = 1; i <= multiLineItem; i++) {
			System.out.println("----------------------------------------------------------------xpath"+SCIM_CODE.format(orderType,i).getValue());
			if (getAction().getText(SCIM_CODE.format(orderType,i)).equalsIgnoreCase(""))
				SoftAssert.checkConditionAndContinueOnFailure(getAction().getText(ITEM_DESCRIPTION.format(orderType,i))+" has scim code "+getAction().getText(SCIM_CODE.format(orderType,i)).replaceFirst("", "not available"), true);
			else
				SoftAssert.checkElementAndContinueOnFailure(SCIM_CODE.format(orderType,i), getAction().getText(ITEM_DESCRIPTION.format(orderType,i))+" has scim code "+getAction().getText(SCIM_CODE.format(orderType,i)), CheckLocatorFor.isVisible);
		}
		goToActionCenter();
		AjaxCondition.forElementPresent(UPDATE_SCIM_CODE).waitForResponse();
		getAction().scrollTo(UPDATE_SCIM_CODE);
		getAction().click(UPDATE_SCIM_CODE);

		for(int i=1;i<=multiLineItem;i++){
			AjaxCondition.forElementPresent(CLICK_TO_UPDATE.format(i)).waitForResponse();
			getAction().click(CLICK_TO_UPDATE.format(i));
			AjaxCondition.forElementPresent(SCIM_CODE_COUNT).waitForResponse();
			int rndCodeCategory = generateRandomNumberSelect(SCIM_CODE_COUNT);
			AjaxCondition.forElementPresent(SCIM_CODE_SELECTION.format(i,rndCodeCategory)).waitForResponse();
			//getAction().waitFor(5000);
			System.out.println(getAction().getText(SCIM_CODE_SELECTION.format(i,rndCodeCategory)));

			getAction().click(SCIM_CODE_SELECTION.format(i,rndCodeCategory));
			getAction().waitFor(3000);
		}
		//getAction().waitFor(5000);
		AjaxCondition.forElementPresent(UPDATE_SCIM_CODES).waitForResponse(2000);
		getAction().click(UPDATE_SCIM_CODES);

		getAction().waitFor(3000);
		if(getAction().isVisible(EARLIEST_AVAILABLE_DATE)){
			System.out.println("--------------------------------------------------"+getAction().getText(EARLIEST_AVAILABLE_DATE));
			Logger.log("click on OK button",TestStepType.STEP);
			getAction().click(OK_BUTTON_ON_POPUP);}
		getAction().waitFor(3000);
		verifyOrderDetailsPageDisplayed();
		Logger.log("Item description after SCIM CODE updation", TestStepType.STEP);
		for (int i = 1; i <= multiLineItem; i++) {
			if (getAction().getText(SCIM_CODE.format(orderType,i)).equalsIgnoreCase(""))
				SoftAssert.checkConditionAndContinueOnFailure(getAction().getText(ITEM_DESCRIPTION.format(orderType,i))+" has "+getAction().getText(SCIM_CODE.format(orderType,i)).replaceFirst("", "no scim code"), true);
			else
				SoftAssert.checkElementAndContinueOnFailure(SCIM_CODE.format(orderType,i),getAction().getText(ITEM_DESCRIPTION.format(orderType,i))+" has scim code updated to "+getAction().getText(SCIM_CODE.format(orderType,i)),CheckLocatorFor.isVisible);
		}	

		return this;

	}


	public OrderDetailsPage verifyReasoncodeAndWrapup(){
		Logger.log("Click on Wrap Up button in Action Center");
		AjaxCondition.forElementVisible(WRAPUP_BUTTON).waitForResponse();
		getAction().scrollTo(WRAPUP_BUTTON);
		getAction().click(WRAPUP_BUTTON);
		getAction().waitFor(4000);

		int rndCodeCategory = generateRandomNumberSelect(CATEGORY_DROPDOWN_CODE_COUNT);
		Logger.log("Select option #"+rndCodeCategory+" in category select");
		AjaxCondition.forElementPresent(CATEGORY_DROPDOWN_CODE.format(rndCodeCategory));
		getAction().selectUsingIndex(CATEGORY_DROPDOWN_CODE, rndCodeCategory);

		int rndCodeReason = generateRandomNumberSelect(DELIVERY_REASON_DROPDOWN_COUNT);

		System.out.println("-----------------------------------------------------------------------------"+DELIVERY_REASON_DROPDOWN_COUNT);
		Logger.log("Select option #"+rndCodeReason+" in category select");
		AjaxCondition.forElementPresent(DELIVERY_REASON_DROPDOWN.format(rndCodeReason));

		AjaxCondition.forElementPresent(DELIVERY_REASON_DROPDOWN.format(rndCodeReason));
		//select[@ng-model='selected.reasonCode']/option[contains(text(),'{0}')]
		getAction().selectUsingIndex(DELIVERY_REASON_DROPDOWN, rndCodeReason);

		AjaxCondition.forElementVisible(DELIVERY_REASON_DROPDOWN_OPTIONS.format("Delivery Confirmed As Is")).waitWithoutException(3);
		AjaxCondition.forElementVisible(DELIVERY_REASON_DROPDOWN_OPTIONS.format("Delivery Confirmed As Is - gift, spouse")).waitWithoutException(3);
		AjaxCondition.forElementVisible(DELIVERY_REASON_DROPDOWN_OPTIONS.format("Delivery Order Cancelled")).waitWithoutException(3);
		AjaxCondition.forElementVisible(DELIVERY_REASON_DROPDOWN_OPTIONS.format("Delivery Order converted to Dock Pick-up with Installation")).waitWithoutException(3);
		AjaxCondition.forElementVisible(DELIVERY_REASON_DROPDOWN_OPTIONS.format("elivery Order referred to Loss Prevention")).waitWithoutException(3);

		//verifyReasonCodes(DELIVERY_REASON_DROPDOWN_OPTIONS.format("Delivery Confirmed As Is - gift, spouse"),true);
		//verifyReasonCodes(DELIVERY_REASON_DROPDOWN_OPTIONS.format("Delivery Order Cancelled"),true);
		//verifyReasonCodes(DELIVERY_REASON_DROPDOWN_OPTIONS.format("Delivery Order converted to Dock Pick-up with Installation"),true);
		//verifyReasonCodes(DELIVERY_REASON_DROPDOWN_OPTIONS.format("Delivery Order referred to Loss Prevention"));
		getAction().click(WRAP_UP);
		getAction().waitFor(4000);
		return this;

	}
	public void recoveryServiceWindowVerification(){

		if(getAction().isVisible(RESCHEDULE_BUTTON)){
			Logger.log("Click on Reschedule button", TestStepType.STEP);
			AjaxCondition.forElementVisible(RESCHEDULE_BUTTON).waitForResponse();
			getAction().scrollTo(RESCHEDULE_BUTTON);
			getAction().click(RESCHEDULE_BUTTON);
			Logger.log("Click on the entire order", TestStepType.STEP);
			AjaxCondition.forElementVisible(ENTIRE_ORDER).waitForResponse();
			getAction().click(ENTIRE_ORDER);}

		getAction().waitFor(1000);
		//getAction().scrollTo(SERVICE_RECOVERY_WINDOW.format(1));
		Logger.log("verify all the time windows available", TestStepType.STEP);
		int unrestrictedWindowCount=getAction().getElementCount(UNRESTRICTED_TIME_WINDOWS_COUNT);
		Logger.log("Unrestricted Time Windows available is/are",TestStepType.SUBSTEP);
		for (int i = 1; i <=unrestrictedWindowCount; i++) {
			AjaxCondition.forElementPresent(UNRESTRICTED_TIME_WINDOWS.format(i));
			SoftAssert.checkElementAndContinueOnFailure(UNRESTRICTED_TIME_WINDOWS.format(i),getAction().getText(UNRESTRICTED_TIME_WINDOWS.format(i)), CheckLocatorFor.isPresent);
		}
		getAction().waitFor(1000);
		if(getAction().isElementPresent(PREFERRED_TIME_WINDOWS_NOT_AVAILABLE))
			SoftAssert.checkConditionAndContinueOnFailure(getAction().getText(PREFERRED_TIME_WINDOWS_NOT_AVAILABLE), true);
		else{
			int preferedWindowCount=getAction().getElementCount(PREFERRED_TIME_WINDOWS_COUNT);
			Logger.log("Preferred Time Windows available are",TestStepType.SUBSTEP);
			for (int i = 1; i <=preferedWindowCount; i++) {
				AjaxCondition.forElementPresent(PREFERRED_TIME_WINDOWS.format(i));
				SoftAssert.checkElementAndContinueOnFailure(PREFERRED_TIME_WINDOWS.format(i),getAction().getText(PREFERRED_TIME_WINDOWS.format(i)), CheckLocatorFor.isPresent);}
		}
		getAction().waitFor(1000);
		int serviceRecoveryWindowCount=getAction().getElementCount(SERVICE_RECOVERY_WINDOW_COUNT);
		Logger.log("Service Recovery Windows available are", TestStepType.SUBSTEP);
		for (int i = 1; i <=serviceRecoveryWindowCount; i++) {
			AjaxCondition.forElementPresent(SERVICE_RECOVERY_WINDOW.format(i));
			SoftAssert.checkElementAndContinueOnFailure(SERVICE_RECOVERY_WINDOW.format(i),getAction().getText(SERVICE_RECOVERY_WINDOW.format(i)), CheckLocatorFor.isPresent);
		}

	}

	public void rescheduleServiceWindowOrder(String type, String ordType,String windowType,String agent ) throws ParseException {

		String orderWith;
		Logger.log("Verify whether order can be rescheduled", TestStepType.STEP);

		goToOrderDetail();
		String currentDeliveryDate = getAction().getText(DELIVERY_DATE);
		Logger.log("CurrentDeliveryDate is :"+currentDeliveryDate,TestStepType.SUBSTEP);
		String originalTimeWindow= getAction().getText(ORIGINAL_TIME_WINDOW);
		Logger.log("CurrentTimeWindow is :"+originalTimeWindow,TestStepType.SUBSTEP);
		String timeWindowType=getAction().getText(TIME_WINDOW_TYPE);
		Logger.log("TimeWindowType is :"+timeWindowType,TestStepType.SUBSTEP);
		String currentTimeWindow= originalTimeWindow+" ("+timeWindowType+")";
		getContext().put("currentTimeWindow", currentTimeWindow);
		System.out.println("currentTimeWindow:"+currentTimeWindow);
		String orderRouteStatus=getAction().getText(ORDER_ROUTE_STATUS);
		getContext().put("route status", orderRouteStatus);
		goToActionCenter();
		Logger.log("Click on the Reschedule Delivery button", TestStepType.STEP);
		AjaxCondition.forElementVisible(RESCHEDULE_BUTTON).waitForResponse();
		getAction().scrollTo(RESCHEDULE_BUTTON);
		getAction().click(RESCHEDULE_BUTTON);
		getContext().put("adjustmentOption", "");
		if (ordType.equalsIgnoreCase("ENTIRE ORDER")) {
			Logger.log("Click on the Entire Order button", TestStepType.STEP);
			AjaxCondition.forElementVisible(ENTIRE_ORDER).waitForResponse();
			getAction().click(ENTIRE_ORDER);
		} else {
			AjaxCondition.forElementVisible(LINE_ITEM).waitForResponse();
			Logger.log("Click on the Line Item", TestStepType.STEP);
			getAction().click(LINE_ITEM);
			Logger.log("Select the Item on the row", TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(LINE_ITEM_ROW.format(1)).waitForResponse();
			getAction().click(LINE_ITEM_ROW.format(1));
			AjaxCondition.forElementVisible(CONTINUE_BUTTON).waitForResponse();
			getAction().scrollTo(CONTINUE_BUTTON);
			Logger.log("Click on the Continue button", TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			getAction().click(CONTINUE_BUTTON);
			AjaxCondition.forElementVisible(CREATE_NEW_ORDER).waitForResponse();
			getAction().click(CREATE_NEW_ORDER);
			AjaxCondition.forElementVisible(RESCHEDULE_LINE_ITEM_VERIFY).waitForResponse();
			Logger.log("New order is created", TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(SUCCESS_OK_BUTTON).waitForResponse();
			getAction().click(SUCCESS_OK_BUTTON);
			ordType = "ENTIRE ORDER";
			rescheduleServiceWindowOrder(type,ordType,windowType,agent);
			return;
		}

		AjaxCondition.forElementVisible(AVAILABLE_DATE_IN_CALANDER).waitForResponse();
		Logger.log("Verified that calander is shown with available reschedule dates ", TestStepType.VERIFICATION_PASSED);
		Logger.log("Click on the Available Date",TestStepType.SUBSTEP);
		getAction().scrollTo(AVAILABLE_DATE_IN_CALANDER);
		getAction().click(AVAILABLE_DATE_IN_CALANDER);
		getAction().waitFor(3000);
		//String color =getAction().driver.findElement(getAction().getWebElement(BLUE)).getCssValue("color");
		/*String selectedDate=getAction().getText(SELECTED_DATE);
			//System.out.println("color:"+color);
			System.out.println("selectedDate:"+selectedDate);

			String monthYear=getAction().getText(MONTH_AND_YEAR_PICKER);
			String input= selectedDate+" "+monthYear;
			SimpleDateFormat parser = new SimpleDateFormat("d MMM yyyy");
			Date allDate = parser.parse(input);
			SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
			String formattedDate = formatter.format(allDate);
			System.out.println(formattedDate);

			getContext().put("selectedDate", selectedDate);*/

		System.out.println(CURRENT_TIME_WINDOW.format(currentTimeWindow).getValue());
		SoftAssert.checkElementAndContinueOnFailure(CURRENT_TIME_WINDOW.format(currentTimeWindow), "currentTimeWindow:"+currentTimeWindow, CheckLocatorFor.isPresent);

		recoveryServiceWindowVerification();
		getAction().waitFor(3000);
		selectwindowType(windowType,agent);


		if(orderRouteStatus.equalsIgnoreCase("ON TIME")){
			if(windowType.equalsIgnoreCase("Preferred Time Windows")||windowType.equalsIgnoreCase("Service Recovery Windows")){
				getAction().waitFor(3000);
				verifyDayOfDelivery(agent,"Entire Order");
				if(agent.equalsIgnoreCase("Delivery Driver")){
					AjaxCondition.forElementVisible(CONTINUE_TO_RESCHDEULE);
					getAction().click(CONTINUE_TO_RESCHDEULE);}}}

		AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL).waitForResponse();


		int reasonCodeCount = getAction().getElementCount(REASON_DROPDOWN_CANCEL_OPTIONS_COUNT);
		if (reasonCodeCount == 0)
			PageAssert.fail("Reason code drop down has no data to select");
		else {
			int ran = util.randomGenerator(reasonCodeCount);
			if (ran == 0)
				ran++;
			Logger.log("Select one reason code from dropdown in random ", TestStepType.STEP);
			String reasonName = getAction().getText(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran));
			Logger.log("Select reason code " + reasonName, TestStepType.STEP);
			AjaxCondition.forElementVisible(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran)).waitForResponse();
			getAction().click(REASON_DROPDOWN_CANCEL);
			getAction().waitFor(1000);
			Logger.log("Select cancel reason code ", TestStepType.STEP);
			getAction().click(REASON_DROPDOWN_CANCEL_OPTIONS.format(ran));

		}
		AjaxCondition.forElementVisible(RESCHEDULE_SUBMIT).waitForResponse();
		getAction().click(RESCHEDULE_SUBMIT);
		AjaxCondition.forElementVisible(RESCHEDULE_VERIFY).waitForResponse();
		AjaxCondition.forElementVisible(DELIVERY_DATE).waitForResponse();
		AjaxCondition.forElementNotVisible(DELIVERY_DATE_TEXT.format(currentDeliveryDate)).waitForResponse();
		getAction().waitFor(3000);
		String updatedTimeWindow=(String) getContext().get("updatedTimeWindow");
		String[] updatedTimeWindowAndType=updatedTimeWindow.split("\\(");
		String newTime=updatedTimeWindowAndType[0].trim().replace("-", "to");


		String newTimeWindowType=updatedTimeWindowAndType[1].substring(0, updatedTimeWindowAndType[1].length()-1);

		//SoftAssert.checkConditionAndContinueOnFailure("The updated time window is:"+getAction().getText(ORIGINAL_TIME_WINDOW),getAction().getText(ORIGINAL_TIME_WINDOW).equalsIgnoreCase(newTime));
		SoftAssert.checkConditionAndContinueOnFailure("The updated time window is:"+getAction().getText(TIME_WINDOW_TYPE),getAction().getText(TIME_WINDOW_TYPE).equalsIgnoreCase(newTimeWindowType));


		String lDate = getAction().getText(DELIVERY_DATE);
		String pend2 = getAction().getText(PEND_CODE);
		System.out.println("Current date   :: "+currentDeliveryDate+" New Date  :: "+lDate);
				if (type.equalsIgnoreCase("OPEN")) {
			PageAssert.verifyEqual(pend2, "");
		} else if (type.equalsIgnoreCase("RELEASED")) {
			PageAssert.verifyEqual(pend2, "TBR");
		} else {
			PageAssert.verifyEqual(pend2, "");
		}

		//Logger.log("Verified that pend code before reshedule was: " + pend1 + " and pend code after reshedule is:" + pend2, TestStepType.VERIFICATION_PASSED);

		SimpleDateFormat simpleDate = new SimpleDateFormat("MM/dd/yyyy");
		Date date = simpleDate.parse(currentDeliveryDate);
		System.out.println();
		Date date2 = simpleDate.parse(lDate);
		System.out.println("date----"+date);
		System.out.println("date2-----"+date2);
		int comResult = date2.compareTo(date);
		if (!(comResult > 0)) {
			PageAssert.fail("Resheduled date is invalid");
		}

		Logger.log("Verified that delivery date is rescheduled to a later date. Date before reschedule:" + currentDeliveryDate + " Date after reschedule:" + lDate, TestStepType.VERIFICATION_PASSED);
	}
	public OrderDetailsPage verifyDayOfDelivery(String agent,String orderType) {
		int lineItemCount=0;
		try {
			lineItemCount=(int) getContext().get("multiLineItem");
			System.out.println("lineItemCount:"+lineItemCount);

		} catch (Exception e) {
			lineItemCount=0;
		}

		;
		Logger.log("Verify Day of Delivery Orders");

		AjaxCondition.forElementVisible(DAY_OF_DELIVERY_AGENT.format(agent));
		getAction().click(DAY_OF_DELIVERY_AGENT.format(agent));
		if (orderType.equalsIgnoreCase("Entire order")) {
			int rndCodeCategory = generateRandomNumberSelect(DAY_OF_DELIVERY_RETURN_CODES_COUNT);
			AjaxCondition.forElementVisible(DAY_OF_DELIVERY_RETURN_CODES.format(rndCodeCategory));
			getAction().click(DAY_OF_DELIVERY_RETURN_CODES.format(rndCodeCategory));

			if(rndCodeCategory==3||rndCodeCategory==7||rndCodeCategory==12||rndCodeCategory==13||rndCodeCategory==14){
				AjaxCondition.forElementVisible(STATUS_UPDATE).waitForResponse(3000);
				getAction().click(STATUS_UPDATE);}

			else{
				AjaxCondition.forElementPresent(ARRIVAL_TIME).waitForResponse(3000);
				getAction().click(ARRIVAL_TIME);
				AjaxCondition.forElementPresent(DEPARTURE_TIME).waitForResponse(3000);
				getAction().click(DEPARTURE_TIME);
				AjaxCondition.forElementVisible(STATUS_UPDATE).waitForResponse(3000);
				getAction().click(STATUS_UPDATE);
			}
		}
		else {
			System.out.println("----------------------------------------------lineItemCount"+lineItemCount);
			for (int i = 1; i <= lineItemCount; i++) {
				AjaxCondition.forElementPresent(DOD_RETURN_CODES_LINE_ITEM_COUNT.format(i)).waitForResponse();
				int rndCodeCategory = generateRandomNumberSelect(DOD_RETURN_CODES_LINE_ITEM_COUNT.format(i));
				if(rndCodeCategory==1)
					rndCodeCategory =2;
				System.out.println("rndCodeCategory----"+rndCodeCategory);
				AjaxCondition.forElementVisible(DOD_RETURN_CODES_LINE_ITEM.format(i,rndCodeCategory)).waitForResponse();
				getAction().click(DOD_RETURN_CODES_LINE_ITEM.format(i,rndCodeCategory));

				//if(rndCodeCategory==5||rndCodeCategory==9||rndCodeCategory==14||rndCodeCategory==15||rndCodeCategory==16){

				//}
				if(getAction().isElementPresent(ARRIVAL_TIME)){
					AjaxCondition.forElementPresent(ARRIVAL_TIME).waitForResponse(3000);
					getAction().click(ARRIVAL_TIME);
					AjaxCondition.forElementPresent(DEPARTURE_TIME).waitForResponse(3000);
					getAction().click(DEPARTURE_TIME);
				}
			}	
		}
		AjaxCondition.forElementVisible(UPDATE_DELIVERY_STATUS).waitForResponse(3000);
		getAction().click(UPDATE_DELIVERY_STATUS);
		return this;
	}


	public OrderDetailsPage selectwindowType(String windowType,String agent) {
		Logger.log("Select the:"+windowType,TestStepType.STEP);
		String updatedTimeWindow="";
		if(windowType.equalsIgnoreCase("Unrestricted Time Window")){
			Logger.log("Click on Unrestricted Time Windows", TestStepType.SUBSTEP);
			int rndCodeCategory = generateRandomNumberSelect(UNRESTRICTED_TIME_WINDOWS_COUNT);
			System.out.println("rndCodeCategory:"+rndCodeCategory);
			updatedTimeWindow= getAction().getText(UNRESTRICTED_TIME_WINDOWS.format(rndCodeCategory));
			Logger.log("Selecting the "+updatedTimeWindow+" from the Unrestricted time window",TestStepType.SUBSTEP);
			System.out.println("updatedTimeWindow:"+updatedTimeWindow);
			getContext().put("updatedTimeWindow", updatedTimeWindow);
			AjaxCondition.forElementPresent(UNRESTRICTED_TIME_WINDOWS.format(rndCodeCategory)).waitForResponse(3000);
			try{
				getAction().scrollTo(UNRESTRICTED_TIME_WINDOWS.format(rndCodeCategory));
				getAction().click(UNRESTRICTED_TIME_WINDOWS.format(rndCodeCategory));
			}catch(Exception e){
				clickJ(UNRESTRICTED_TIME_WINDOWS.format(rndCodeCategory));
			}
			String orderRouteStatus =(String) getContext().get("route status");
			if(orderRouteStatus.equalsIgnoreCase("ON TIME")){
				getAction().waitFor(3000);
				verifyDayOfDelivery(agent,"Entire Order");
				if(agent.equalsIgnoreCase("Delivery Driver")){
					AjaxCondition.forElementVisible(CONTINUE_TO_RESCHDEULE).waitForResponse(2000);
					getAction().click(CONTINUE_TO_RESCHDEULE);}}
			else{
				AjaxCondition.forElementVisible(UNRESTRICTED_CONFIRMATION_TIME_WINDOW_POP_UP).waitForResponse();
				SoftAssert.checkElementAndContinueOnFailure(UNRESTRICTED_CONFIRMATION_TIME_WINDOW_POP_UP, getAction().getText(UNRESTRICTED_CONFIRMATION_TIME_WINDOW_POP_UP), CheckLocatorFor.isVisible);
				getAction().click(OK_BUTTON);}}

		if(windowType.equalsIgnoreCase("Preferred Time Windows")) {

			if(getAction().isElementPresent(PREFERRED_TIME_WINDOWS_NOT_AVAILABLE)){
				SoftAssert.checkConditionAndContinueOnFailure(getAction().getText(PREFERRED_TIME_WINDOWS_NOT_AVAILABLE), true);
				Logger.log("Selecting the Recovery Windows");
				windowType="Service Recovery Windows";}

			else{
				Logger.log("Click on Preferred Time Windows", TestStepType.STEP);
				int rndCodeCategory = generateRandomNumberSelect(PREFERRED_TIME_WINDOWS_COUNT);
				System.out.println("rndCodeCategory:"+rndCodeCategory);
				Logger.log("Selecting the "+updatedTimeWindow+" from the Preferred Time window",TestStepType.SUBSTEP);
				updatedTimeWindow= getAction().getText(PREFERRED_TIME_WINDOWS.format(rndCodeCategory));
				System.out.println("updatedTimeWindow:"+updatedTimeWindow);
				getContext().put("updatedTimeWindow", updatedTimeWindow);
				AjaxCondition.forElementPresent(PREFERRED_TIME_WINDOWS.format(rndCodeCategory)).waitForResponse();
				getAction().click(PREFERRED_TIME_WINDOWS.format(rndCodeCategory));
				System.out.println(PREFERRED_TIME_WINDOWS.format(rndCodeCategory));
				AjaxCondition.forElementVisible(PREFERRED_CONFIRMATION_TIME_WINDOW_POP_UP).waitForResponse();
				SoftAssert.checkElementAndContinueOnFailure(PREFERRED_CONFIRMATION_TIME_WINDOW_POP_UP, getAction().getText(PREFERRED_CONFIRMATION_TIME_WINDOW_POP_UP), CheckLocatorFor.isVisible);
				getAction().click(OK_BUTTON);}}


		if(windowType.equalsIgnoreCase("Service Recovery Windows")){


			Logger.log("Click on the Recovery Time Windows", TestStepType.STEP);
			int rndCodeCategory = generateRandomNumberSelect(SERVICE_RECOVERY_WINDOW_COUNT);
			System.out.println("rndCodeCategory:"+rndCodeCategory);
			Logger.log("Selecting the "+updatedTimeWindow+" from theRecovery Time Windows",TestStepType.SUBSTEP);
			updatedTimeWindow= getAction().getText(SERVICE_RECOVERY_WINDOW.format(rndCodeCategory));
			System.out.println("updatedTimeWindow:"+updatedTimeWindow);
			getContext().put("updatedTimeWindow", updatedTimeWindow);
			AjaxCondition.forElementPresent(SERVICE_RECOVERY_WINDOW.format(rndCodeCategory)).waitForResponse(3000);
			getAction().click(SERVICE_RECOVERY_WINDOW.format(rndCodeCategory));
			if(getAction().isVisible(RECOVERY_TIME_WINDOW_EXIST))
				SoftAssert.checkConditionAndContinueOnFailure(getAction().getText(RECOVERY_CONFIRMATION_TIME_WINDOW_POP_UP), true);
			else{
				AjaxCondition.forElementVisible(RECOVERY_CONFIRMATION_TIME_WINDOW_POP_UP).waitForResponse(2000);
				SoftAssert.checkElementAndContinueOnFailure(RECOVERY_CONFIRMATION_TIME_WINDOW_POP_UP, getAction().getText(RECOVERY_CONFIRMATION_TIME_WINDOW_POP_UP), CheckLocatorFor.isVisible);}
			AjaxCondition.forElementPresent(OK_BUTTON);
			getAction().click(OK_BUTTON);}
		return this;


	}

	public OrderDetailsPage verifyReturnSuccess(String sku){
		Logger.log("verify the status message ");
		getAction().waitFor(2000);
		getAction().click(ORDER_SUMMARY_TAB);
		getAction().click(LINE_ITEM_SALES_CHECKS_TAB);

		if((Integer.parseInt(getAction().getText(QUANTITY)))>1){
			AjaxCondition.forElementVisible(SHIPPED_STATUS.format(sku)).waitForResponse(2000);
			highlight(SHIPPED_STATUS.format(sku));
			SoftAssert.checkElementAndContinueOnFailure(SHIPPED_STATUS.format(sku), "status: "+getAction().getText(SHIPPED_STATUS.format(sku)), CheckLocatorFor.isVisible);
			Logger.log("The status is Shipped",TestStepType.VERIFICATION_PASSED);
		}
		else {
			AjaxCondition.forElementVisible(RETURNED_STATUS.format(sku)).waitForResponse(3000);
			highlight(RETURNED_STATUS.format(sku));
			SoftAssert.checkElementAndContinueOnFailure(RETURNED_STATUS.format(sku), "status: "+getAction().getText(RETURNED_STATUS.format(sku)), CheckLocatorFor.isVisible);
			Logger.log("The status has been changed to Returned",TestStepType.VERIFICATION_PASSED);}
		return this;

	}

	public void verifyCancelbuttonnotPresent(String orderStatus){
		if(orderStatus.equalsIgnoreCase("Release")){
			Logger.log("Verify Cancle button is not present in action center", TestStepType.STEP);
			getAction().waitFor(3000);
			if(getAction().isVisible(CANCEL_BUTTON)){
				PageAssert.fail("cancel button is Present");
			}
			else
				Logger.log("cancel button is not present ",TestStepType.VERIFICATION_PASSED);}
		if(orderStatus.equalsIgnoreCase("open")){
			AjaxCondition.forElementVisible(CANCEL_BUTTON).waitForResponse();
			getAction().scrollTo(CANCEL_BUTTON);
			getAction().click(CANCEL_BUTTON);
			if(getAction().isVisible(LINE_ITEM_CANCEL))
				PageAssert.fail("line item cancel button is Present");
			else 
				Logger.log("line item cancel button is not present ",TestStepType.VERIFICATION_PASSED);
		}	
	}

	public void captureSalescheckNumber(){
		Logger.log("Verify Cancle button is not present in action center", TestStepType.STEP);
		getAction().waitFor(4000);
		if(getAction().isVisible(DOS_ORDER_SUMMARY_SALESCHECK_NUMBER)){
			String saleschecknumber=getAction().getText(DOS_ORDER_SUMMARY_SALESCHECK_NUMBER);
			System.out.println("----------------------------------saleschecknumber"+saleschecknumber);
			getContext().put("oldOrdersaelchecknumber", saleschecknumber);
		}
		else
			PageAssert.fail("sales check number is not displayed");
	}

	public void verifyNewOrderhassameSalescheckNumber(){
		Logger.log("verify the salescheck number of the new created order is same as old order salescheck", TestStepType.STEP);
		getAction().waitFor(3000);
		if(getAction().isVisible(DOS_ORDER_SUMMARY_SALESCHECK_NUMBER)){
			String saleschecknumber=getAction().getText(DOS_ORDER_SUMMARY_SALESCHECK_NUMBER);
			System.out.println("----------------------------------------new order saleschecknumber"+saleschecknumber);
			String oldsaleschecknumber=(String) getContext().get("oldOrdersaelchecknumber");
			System.out.println("----------------------------------------old order saleschecknumber"+oldsaleschecknumber);
			if(saleschecknumber.equalsIgnoreCase(oldsaleschecknumber)){
				Logger.log("new order has same salescheck number as old order",TestStepType.VERIFICATION_PASSED);
			}
			else
				PageAssert.fail("sales check number is not displayed");
		}
	}

	public void verifyDateandUserInDeliveryOSHNote(String str1,String str2){
		Logger.log("verify Data In DeliveryNotes", TestStepType.STEP);
		getAction().waitFor(3000);
		System.out.println("------inside verifyDateandUserInDeliveryOSHNote"+ getAction().getText(DELIVERY_NOTES_DATA1.format(str1,str2)));
		getAction().scrollTo(DELIVERY_NOTES_DATA1.format(str1,str2));

		AjaxCondition.forElementVisible(DELIVERY_NOTES_DATA1.format(str1,str2)).waitForResponse();
	}

	public OrderDetailsPage verifyOfflineReasoncodeAndWrapup(){
		Logger.log("Click on Wrap Up button in Action Center");
		AjaxCondition.forElementVisible(WRAPUP_BUTTON).waitForResponse();
		getAction().scrollTo(WRAPUP_BUTTON);
		getAction().click(WRAPUP_BUTTON);
		getAction().waitFor(4000);

		int rndCodeCategory = generateRandomNumberSelect(CATEGORY_DROPDOWN_CODE_COUNT);
		Logger.log("Select option #"+rndCodeCategory+" in category select");
		AjaxCondition.forElementPresent(CATEGORY_DROPDOWN_CODE.format(rndCodeCategory));
		getAction().selectUsingIndex(CATEGORY_DROPDOWN_CODE, rndCodeCategory);

		/*int rndCodeReason = generateRandomNumberSelect(DELIVERY_REASON_DROPDOWN_COUNT);

		System.out.println("-----------------------------------------------------------------------------"+DELIVERY_REASON_DROPDOWN_COUNT);
		Logger.log("Select option #"+rndCodeReason+" in category select");
		AjaxCondition.forElementPresent(DELIVERY_REASON_DROPDOWN.format(rndCodeReason));

		AjaxCondition.forElementPresent(DELIVERY_REASON_DROPDOWN.format(rndCodeReason))*/;
		getAction().click(DELIVERY_REASON_DROPDOWN);
		return this;

	}
	public void pickupEntireOrderTillConcessionPopUp(String reasoncode){

		String orderRouteStatus= (String) getContext().get("orderRouteStatus");
		String dosOrderNumber = getAction().getText(DELIVERYDETAILS_DOS_NUMBER);  

		Logger.log("Agent should be able to do pickup action", TestStepType.STEP);
		getAction().waitFor(3000);
		getAction().scrollTo(PICKUP_BUTTON);
		AjaxCondition.forElementVisible(PICKUP_BUTTON).waitForResponse();
		getAction().click(PICKUP_BUTTON);
		getAction().waitFor(3000);
		int num=getAction().getVisibleElementCount(SELECT_ITEM_NUMBER);
		for(int i=num;i>0;i--){
			AjaxCondition.forElementVisible(SELECT_ITEM.format(i)).waitForResponse();
			getAction().click(SELECT_ITEM.format(i));
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(REASON_FOR_PICKUP_DROPDOWN).waitForResponse();
			getAction().click(REASON_FOR_PICKUP_DROPDOWN);
			getAction().waitFor(3000);
			System.out.println("-------------------------------------------------+reasoncode:"+reasoncode);
			AjaxCondition.forElementVisible(REASON_FOR_PICKUP_DROPDOWN_OPTION).waitForResponse();
			if(reasoncode.equalsIgnoreCase(""))
				getAction().click(REASON_FOR_PICKUP_DROPDOWN_OPTION);
			else
				getAction().click(REASON_FOR_PICKUP_DROPDOWN_OPTIONS.format(reasoncode));
			
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(PICKUP_QUANTITY).waitForResponse();
			getAction().click(PICKUP_QUANTITY);
			getAction().type(PICKUP_QUANTITY, "1");
		}
		getAction().waitFor(3000);
		getAction().scrollTo(CONTINUE_BUTTON);
		AjaxCondition.forElementVisible(CONTINUE_BUTTON).waitForResponse();
		getAction().click(CONTINUE_BUTTON);
		getAction().waitFor(3000);

		try {
			if(orderRouteStatus.equalsIgnoreCase("ON TIME")){
				getAction().waitFor(3000);
				UpdateDeliveryStatusOfDOD("Delivery Driver",0);
				if("DELIVERY DRIVER".equalsIgnoreCase("Delivery Driver")){
					AjaxCondition.forElementVisible(CONTINUE_TO_RETURN_EXCHANGE_ITEM).waitForResponse();
					getAction().click(CONTINUE_TO_RETURN_EXCHANGE_ITEM);}}
		} catch (NullPointerException e ) {
			orderRouteStatus="";
		}

	}

	public void rereserveItemTillOfferConcessionPopUp(String orderType,String order){

		if (orderType.equalsIgnoreCase("Shipped")||orderType.equalsIgnoreCase("Cancelled")){
			PageAssert.elementNotVisible(RERESERVE_BUTTON);
			Logger.log("Rereserve button is not available in Action Center" , TestStepType.VERIFICATION_PASSED);
		}

		else{
			Logger.log("Verify Rereserve button is present in action center", TestStepType.STEP);
			String dosOrderNumber = getAction().getText(DELIVERYDETAILS_DOS_NUMBER);
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(RERESERVE_BUTTON).waitForResponse();
			getAction().scrollTo(RERESERVE_BUTTON);
			getAction().click(RERESERVE_BUTTON);
			getAction().waitFor(3000);
			AjaxCondition.forElementVisible(RERESERVE_ITEM_COUNT).waitForResponse();
			int num=0;
			if(order.equalsIgnoreCase("whole order")){
				num=getAction().getVisibleElementCount(RERESERVE_ITEM_COUNT);
				for(int i=1;i<=num;i++){
					getAction().scrollTo(RERESERVE_ITEM.format(i));
					AjaxCondition.forElementVisible(RERESERVE_ITEM.format(i)).waitForResponse();
					getAction().click(RERESERVE_ITEM.format(i));
					AjaxCondition.forElementVisible(LINE_ITEM_ROW_QUANTITY.format(orderType,i)).waitForResponse();
					getAction().type(LINE_ITEM_ROW_QUANTITY.format(orderType,i), getAction().getText(LINE_ITEM_ROW_QUANTITY_ORDERED.format(i)));
					getAction().waitFor(3000);
				}
			}
			else{
				AjaxCondition.forElementVisible(RERESERVE_ITEM.format(1)).waitForResponse();
				getAction().click(RERESERVE_ITEM.format(1));
				AjaxCondition.forElementVisible(LINE_ITEM_ROW_QUANTITY.format(orderType,1)).waitForResponse();
				getAction().type(LINE_ITEM_ROW_QUANTITY.format(orderType,1), getAction().getText(LINE_ITEM_ROW_QUANTITY_ORDERED.format(1)));
				getAction().waitFor(3000);
				num=1;
			}
			AjaxCondition.forElementVisible(ACTION_CETNER_CONTINUE_BUTTON).waitForResponse(5);
			getAction().click(ACTION_CETNER_CONTINUE_BUTTON);
			Logger.log("Click 'No' on the Consession confirmation dialog",TestStepType.STEP);
			AjaxCondition.forElementVisible(OFFER_CONSESSION_NO_BUTTON).waitForResponse(5);

		}
	}
	//input parameters separated by "," and further separated by : 
	//MEMBER_CONSESSION_YES_BUTTON-this means click on yes whether the  agent offered the concession to member
	//MEMBER_CONSESSION_YES_BUTTON-click on yes button if agent accepted the offer
	//SELECT_CONSESSION_TYPE:Coupon:1--this gives us the value which you want to select ex: coupon, syw points etc 
	//and also give the index of the select box and the text box, if you want to select 1st select box then give 1 or else give 2 or 3 based on your requirement

	public OrderDetailsPage acceptofferconcession(String concessionInputs){
		String[] inputs=concessionInputs.split(",");
		for(int i=0;i<inputs.length;i++){
			String[] casetype=inputs[i].split(":");
			switch(casetype[0]){
			case "OFFER_CONSESSION_YES_BUTTON":
				getAction().click(OFFER_CONSESSION_YES_BUTTON);
				break;
			case "MEMBER_CONSESSION_YES_BUTTON":
				getAction().click(MEMBER_CONSESSION_YES_BUTTON);
				break;
			case "SELECT_CONSESSION_TYPE":
				getAction().waitFor(4000);
				getAction().selectByText(SELECT_CONSESSION_TYPE.format(casetype[2]),casetype[1]);
				break;
			case "CONSESSION_AMOUNT":
				getAction().type(CONSESSION_AMOUNT.format(casetype[2]), casetype[1]);
				break;
			case "ADD_CONCESSION":				
				getAction().click(ADD_CONCESSION.format(casetype[1]));
				break;
			case "DELETE_CONCESSION":
				getAction().click(DELETE_CONCESSION.format(casetype[1]));
				break;
			case "FINISH_BUTTON":
				getAction().click(FINISH_BUTTON);
				break;
			default:
				break;
			}
		}
		return this;	
	}

	public OrderDetailsPage verifyPickUpItemCreated(){
		int num=(int) getContext().get("num");
		Logger.log("Verify pick up items created equal to orignal items in order detail page",TestStepType.STEP);
		AjaxCondition.forElementVisible(PICK_UP_ITEMS_COUNT).waitForResponse();
		SoftAssert.checkConditionAndContinueOnFailure(num+" pick up items are created", getAction().getElementCount(PICK_UP_ITEMS_COUNT)==num);
		Logger.log("Description for the pick up items are",TestStepType.STEP);
		for(int i = 1; i <=num; i++)
			Logger.log(getAction().getText(PICK_UP_ITEMS.format(i)));
		Logger.log("verify original items needs to be delivered",TestStepType.STEP);
		for (int i = 1; i <= num; i++){
			AjaxCondition.forElementVisible(ITEM_DESCRIPTION_WITHOUT_PICKUP.format(i)).waitForResponse();
			SoftAssert.checkConditionAndContinueOnFailure(getAction().getText(ITEM_DESCRIPTION_WITHOUT_PICKUP.format(i)), getAction().getElementCount(ITEM_DESCRIPTION_WITHOUT_PICKUP_COUNT)==num);
		}
		return this;
	}

	public OrderDetailsPage verifylineItemScimCode(){
		@SuppressWarnings("unchecked")
		List<String> list=(List<String>) getContext().get("list");
		Iterator<String> it=list.iterator();
		while (it.hasNext()) {
			String str=it.next();
			String[] split=str.split(":");
			String[] scimCode=split[1].split("-");
			System.out.println(ORDER_DETAIL_SCIM_CODE.format(split[0],scimCode[0].trim()).getValue());
			AjaxCondition.forElementVisible(ORDER_DETAIL_SCIM_CODE.format(split[0],scimCode[0].trim())).waitForResponse(2000);
			SoftAssert.checkElementAndContinueOnFailure(ORDER_DETAIL_SCIM_CODE.format(split[0],scimCode[0].trim()),"Scim code of "+split[0]+" is changed to "+ getAction().getText(ORDER_DETAIL_SCIM_CODE.format(split[0],scimCode[0].trim())), CheckLocatorFor.isVisible);
			getAction().waitFor(2000);
		}
		return this;
	}

	public void verifyActionCapturedHistoryNotes(String notes,String dosNumber,String newOrderNumber){
		Logger.log("Verify adjustment done on order are captured in History Notes", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		getAction().waitFor(3000);
		SoftAssert.checkElementAndContinueOnFailure(CONTACT_HISTORY_NOTESS.format(notes,dosNumber,newOrderNumber),"new order details desplayed in contact history" , CheckLocatorFor.isPresent);
	}
	
	public void verifyActionCapturedHistoryNotes(String notes){
		Logger.log("Verify action performed on order are captured in Contact History Notes", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(ORDER_CONTACT_HISTORY).waitForResponse();
		getAction().click(ORDER_CONTACT_HISTORY);
		getAction().waitFor(3000);
		SoftAssert.checkElementAndContinueOnFailure(ORDER_CONTACT_HISTORY_NOTES.format(notes),"cancel order" , CheckLocatorFor.isPresent);
	}
	public void captureNewOrderNumber(){
		Logger.log("Verify Cancle button is not present in action center", TestStepType.STEP);
		getAction().waitFor(4000);
		if(getAction().isVisible((DELIVERYDETAILS_DOS_NUMBER))){
			String newOrderNum=getAction().getText(DELIVERYDETAILS_DOS_NUMBER);
			System.out.println("----------------------------------new order number"+newOrderNum);
			getContext().put("newOrderNum", newOrderNum);
		}
		else
			PageAssert.fail("order number is not displayed");
	}

	public void verifynewOrderdetailsinContactHistoryOfOldOrder(){
		Logger.log("verify the salescheck number of the new created order is same as old order salescheck", TestStepType.STEP);
		getAction().waitFor(3000);
		if(getAction().isVisible(DOS_ORDER_SUMMARY_SALESCHECK_NUMBER)){
			String saleschecknumber=getAction().getText(DOS_ORDER_SUMMARY_SALESCHECK_NUMBER);
			System.out.println("----------------------------------------new order saleschecknumber"+saleschecknumber);
			String oldsaleschecknumber=(String) getContext().get("oldOrdersaelchecknumber");
			System.out.println("----------------------------------------old order saleschecknumber"+oldsaleschecknumber);
			if(saleschecknumber.equalsIgnoreCase(oldsaleschecknumber)){
				Logger.log("new order has same salescheck number as old order",TestStepType.VERIFICATION_PASSED);
			}
			else
				PageAssert.fail("sales check number is not displayed");
		}
	}
	public void verifyPendCode(String codetype){
		getAction().waitFor(3000);
		goToOrderDetail();
		Logger.log("verify the pend code is displayed as expected for the order", TestStepType.STEP);
		if(getAction().isVisible(DOS_ORDER_SUMMARY_PEND_CODE)){
			String pendcode=getAction().getText(DOS_ORDER_SUMMARY_PEND_CODE);
			System.out.println("----------------------------------------pendcode:"+pendcode);
			if(codetype.equalsIgnoreCase(pendcode)){
				Logger.log("order has the expected pend code",TestStepType.VERIFICATION_PASSED);
			}
			else
				PageAssert.fail("Order pend code is not expected");
		}
	}

	public void verifyMattressExchange(){
		getAction().click(MATTRESS_DROPDOWN);
		AjaxCondition.forElementPresent(MATRESS_DROPDOWN_TEXT).waitForResponse(2000);
		getAction().click(MATRESS_DROPDOWN_TEXT);
		getAction().scrollTo(CONTINUE_BUTTON);
		AjaxCondition.forElementVisible(CONTINUE_BUTTON).waitForResponse();
		getAction().click(CONTINUE_BUTTON);
		getAction().waitFor(2000);
		if(getAction().isVisible(RETURN_POLICY)){
			Logger.log(getAction().getText(RETURN_POLICY),TestStepType.STEP);
			getAction().click(CONTINUE_BUTTON);
			AjaxCondition.forElementVisible(PICK_UP_EXCEPTION).waitForResponse(2000);
			getAction().type(EXCEPTION_USERNAME, "testdelivery0121");
			getAction().type(EXCEPTION_PASSWORD, "TestPassword");
			getAction().type(REASON_EXCEPTION, "this is an automated test");
			getAction().click(SUBMIT);}

	}

	public void verifyInvalidQuantityError(String action){
		Logger.log("Agent should be able to do "+action+" action", TestStepType.STEP);
		if(action.equalsIgnoreCase("Pickup")){
			AjaxCondition.forElementVisible(PICKUP_BUTTON).waitForResponse();
			getAction().scrollTo(PICKUP_BUTTON);
			getAction().click(PICKUP_BUTTON);}
		else {
			AjaxCondition.forElementVisible(EVEN_EXCHANGE_BUTTON).waitForResponse();
			getAction().scrollTo(EVEN_EXCHANGE_BUTTON);
			getAction().click(EVEN_EXCHANGE_BUTTON);}

		AjaxCondition.forElementVisible(SELECT_ITEM.format(1)).waitForResponse();
		getAction().click(SELECT_ITEM.format(1));
		AjaxCondition.forElementVisible(REASON_FOR_PICKUP_DROPDOWN).waitForResponse();
		getAction().click(REASON_FOR_PICKUP_DROPDOWN);
		AjaxCondition.forElementVisible(REASON_FOR_PICKUP_DROPDOWN_OPTION).waitForResponse();
		getAction().click(REASON_FOR_PICKUP_DROPDOWN_OPTION);
		AjaxCondition.forElementVisible(PICKUP_QUANTITY).waitForResponse();
		getAction().click(PICKUP_QUANTITY);
		int availableQty=  Integer.parseInt(getAction().getText(AVAILABLE_QTY));
		availableQty++;
		String i =Integer.toString(availableQty);
		getAction().type(PICKUP_QUANTITY,i);
		PageAssert.elementVisible(INVALID_QTY_ERR);
		Logger.log("Verified Invalid Quantity Error", TestStepType.STEP);


	}
	public OrderDetailsPage UpdateDeliveryStatusOfDOD(String agent,int Reasoncode) {
		int lineItemCount=0;
		try {
			lineItemCount=(int) getContext().get("openMultiLineItem");
			System.out.println("lineItemCount:"+lineItemCount);

		} catch (Exception e) {
			lineItemCount=0;
		}
		Logger.log("Verify Day of Delivery Orders");

		AjaxCondition.forElementVisible(DAY_OF_DELIVERY_AGENT.format(agent));
		getAction().click(DAY_OF_DELIVERY_AGENT.format(agent));
		for (int i = 1; i <= lineItemCount; i++) {
			AjaxCondition.forElementPresent(DOD_RETURN_CODES_LINE_ITEM_COUNT.format(i)).waitForResponse();
			int rndCodeCategory;
			if(i!=1){
				rndCodeCategory = generateRandomNumberSelect(DOD_RETURN_CODES_LINE_ITEM_COUNT.format(i));
			}
			else
				rndCodeCategory=2;

			if(rndCodeCategory==1)
				rndCodeCategory =2;
			System.out.println("rndCodeCategory----"+rndCodeCategory);
			AjaxCondition.forElementVisible(DOD_RETURN_CODES_LINE_ITEM.format(i,rndCodeCategory)).waitForResponse();
			getAction().click(DOD_RETURN_CODES_LINE_ITEM.format(i,rndCodeCategory));

			if(getAction().isElementPresent(ARRIVAL_TIME)){
				AjaxCondition.forElementPresent(ARRIVAL_TIME).waitForResponse(3000);
				getAction().click(ARRIVAL_TIME);
				AjaxCondition.forElementPresent(DEPARTURE_TIME).waitForResponse(3000);
				getAction().click(DEPARTURE_TIME);
			}
		}	
		AjaxCondition.forElementVisible(UPDATE_DELIVERY_STATUS).waitForResponse(3000);
		getAction().click(UPDATE_DELIVERY_STATUS);
		return this;

	}
public OrderDetailsPage updateAddress(){
		
		Logger.log("Updating name, email and home phone number ",TestStepType.STEP);

		AjaxCondition.forElementVisible(UPDATE_BUTTON).waitForResponse();
		getAction().click(UPDATE_BUTTON);
		AjaxCondition.forElementVisible(UPDATE_NAME).waitForResponse();
		getAction().scrollTo(UPDATE_NAME);
		
		String originalValue=getAction().getValue(UPDATE_ADDRESS);
		System.out.println("---------------------------------originalValue"+originalValue);
		
		getAction().type(UPDATE_ADDRESS, "CHESTERFIELD");
		getAction().waitFor(2000);
		
		//String updatedAddress= (String) getContext().get("updatedAddress");
		
		AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse();
		getAction().click(SAVE_BUTTON);
		String selectedAddress = null;
		
		
		if(AjaxCondition.forElementVisible(SUGGESTED_ADDRESS).waitWithoutException(5000)){
			AjaxCondition.forElementVisible(SUGGESTED_ADDRESS_MSG).waitForResponse();
			AjaxCondition.forElementVisible(VERIFY_SUGGESTED_ADDRESS_DISPLAYED.format("100 CHESTERFIELD BUS PKWY FL 2, CHESTERFIELD, MO 63005")).waitForResponse();
			getAction().click(SUGGESTED_ADDRESS);
			
			selectedAddress=getAction().getValue(FIRST_SUGGESTED_ADDRESS);
			System.out.println("-----------------------------------------selectedAddress:"+selectedAddress);
			AjaxCondition.forElementVisible(SUBMIT_BUTTON_ADDRESS).waitForResponse();
			getAction().click(SUBMIT_BUTTON_ADDRESS);
			AjaxCondition.forElementVisible(NOTATION_PAD).waitForResponse(3000);	
			SoftAssert.checkConditionAndContinueOnFailure("updatedAddress: "+selectedAddress, getAction().getValue(UPDATE_ADDRESS).equalsIgnoreCase(selectedAddress));	
		}
		
		//very that the address what we selected is updated properly
		
		AjaxCondition.forElementVisible(UPDATE_BUTTON).waitForResponse();
		getAction().click(UPDATE_BUTTON);
		AjaxCondition.forElementVisible(UPDATE_NAME).waitForResponse();
		getAction().scrollTo(UPDATE_NAME);
		
		SoftAssert.checkConditionAndContinueOnFailure("updatedAddress: "+selectedAddress, getAction().getValue(UPDATE_ADDRESS).equalsIgnoreCase(selectedAddress));
		
		
		
		getContext().put("adjustmentOption", "");
		return this;
	}
	
	public void itemDetail(String orderType){

		scrollDown();
		getAction().waitFor(3000);
		List<String> list = new ArrayList<String>();
		int multiLineItem=getAction().getElementCount(DOS_ITEM_STATUS_COUNT.format(orderType));
		System.out.println("-------------------------------------------- "+multiLineItem);
		getAction().waitFor(2000);
		Logger.log("Order have "+multiLineItem+" multiline item");
		for (int i = 1; i <= multiLineItem; i++) {
			list.add(getAction().getText(ITEM_DESCRIPTION.format(orderType,i))+":"+getAction().getText(DIVISION.format(orderType,i))+":"+getAction().getText(ITEM.format(orderType,i)));
		}
		getContext().put("adjustmentOption", list);
		System.out.println(list);
	}
	
	public void goToMiscellaneous(){
		Logger.log("Verify user is in Miscellaneous", TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(DELIVERYDETAILS_TABS.format("Miscellaneous")).waitForResponse(5);
		getAction().click(DELIVERYDETAILS_TABS.format("Miscellaneous"));
	}
	
	public OrderDetailsPage enterClaimNumber(String claimNumber){
		Logger.log("Verify the claim pop up is displayed",TestStepType.STEP);
		AjaxCondition.forElementVisible(CLAIM_NUMBER_ENTRY).waitWithoutException(30);
		
		getAction().click(CLAIM_NUMBER_ENTRY);
		getAction().type(CLAIM_NUMBER, claimNumber);
		getAction().waitFor(1000);
		getAction().click(SUBMIT_BUTTON);		
		return this;
	}
	public OrderDetailsPage verifyReturnPolicyWarningMessage(){
		Logger.log("Verify the claim pop up is displayed",TestStepType.STEP);
		AjaxCondition.forElementVisible(RETURN_DAMAGED_ITEM_WARNING_MSG).waitForResponse(500);
		return this;
	}
	public OrderDetailsPage verifyEvenExchangeEntireOrderTillConcessionPopUp(String orderStatus,String reasoncode){
		List<String> list= new ArrayList<String>();
		String orderRouteStatus= (String) getContext().get("orderRouteStatus");
		String dosOrderNumber = getAction().getText(DELIVERYDETAILS_DOS_NUMBER);  
		Logger.log("Click on Even Exchange Button",TestStepType.STEP);
		try{
			getAction().scrollTo(EVEN_EXCHANGE_BUTTON);
			getAction().click(EVEN_EXCHANGE_BUTTON);
		}catch(Exception e){
			clickJ(EVEN_EXCHANGE_BUTTON);
		}
		getAction().waitFor(3000);
		Logger.log("Click on Line Item",TestStepType.STEP);
		int num=getAction().getVisibleElementCount(EVEN_EXCHANGE_ITEM_TABLE);
		getContext().put("num", num);
		for(int i=1;i<=num;i++){
			AjaxCondition.forElementVisible(EVEN_EXCHANGE_ITEM.format(i)).waitForResponse(5);
			try {
				getAction().scrollTo(EVEN_EXCHANGE_ITEM.format(i));
				getAction().click(EVEN_EXCHANGE_ITEM.format(i));
			} catch (Exception e) {
				clickJ(EVEN_EXCHANGE_ITEM.format(i));
			}

			Logger.log("Select the Reason for even exchange",TestStepType.STEP);
			AjaxCondition.forElementVisible(EVEN_EXCHANGE_ITEM_DROPDOWN.format(i)).waitForResponse(5);
			
			//getAction().selectUsingIndex(EVEN_EXCHANGE_ITEM_DROPDOWN.format(i), 3);

			
			if(reasoncode.equalsIgnoreCase(""))
				getAction().selectUsingIndex(EVEN_EXCHANGE_ITEM_DROPDOWN.format(i), 3);
			else
				getAction().selectUsingIndex(EVEN_EXCHANGE_ITEM_DROPDOWN.format(i), 1);
			
			
			Logger.log("Click on Continue",TestStepType.STEP);
			AjaxCondition.forElementVisible(LINE_ITEM_ROW_QUANTITY_COUNT.format(i)).waitForResponse();
			getAction().type(LINE_ITEM_ROW_QUANTITY_COUNT.format(i), getAction().getText(LINE_ITEM_ROW_QUANTITY_AVAILABLE_EVEN_EXCHANGE));
			getAction().waitFor(1000);
		}
		AjaxCondition.forElementVisible(ACTION_CETNER_CONTINUE_BUTTON).waitForResponse(5);
		getAction().click(ACTION_CETNER_CONTINUE_BUTTON);

		try {
			if(orderRouteStatus.equalsIgnoreCase("ON TIME")){
				getAction().waitFor(3000);
				verifyDayOfDelivery("Delivery Driver","Select Items");
				if("DELIVERY DRIVER".equalsIgnoreCase("Delivery Driver")){
					AjaxCondition.forElementVisible(CONTINUE_TO_RETURN_EXCHANGE_ITEM).waitForResponse();
					getAction().click(CONTINUE_TO_RETURN_EXCHANGE_ITEM);}}
		} catch (NullPointerException e ) {
			orderRouteStatus="";
		}

		/*if(orderStatus.equalsIgnoreCase("Shipped")||orderStatus.equalsIgnoreCase("Partially Shipped")||orderStatus.equalsIgnoreCase("Released")){
			Logger.log("Click 'No' on the Consession confirmation dialog",TestStepType.STEP);
			AjaxCondition.forElementVisible(OFFER_CONSESSION_NO_BUTTON).waitForResponse(5);
			getAction().click(OFFER_CONSESSION_NO_BUTTON);
		}*/
		return this;
	}
	
	public OrderDetailsPage verifyPendCodeAlertMsg(){
		String alertMsg="This order is pended.  Please take appropriate action to remove the pend code"
				+ " to make sure the order is delivered when expected. "
				+ "Reschedule action will remove most pend codes.";
		Logger.log("Click on pend code alert",TestStepType.STEP);
		AjaxCondition.forElementVisible(PEND_CODE_ALERT.format(alertMsg)).waitForResponse(5);
		SoftAssert.checkElementAndContinueOnFailure(PEND_CODE_ALERT.format(alertMsg), "Pended order msg", CheckLocatorFor.isVisible);
		getAction().click(OK_BUTTON);
		return this;
		
		
	}
}







