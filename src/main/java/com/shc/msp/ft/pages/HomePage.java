package com.shc.msp.ft.pages;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.shc.automation.AjaxCondition;
import com.shc.automation.FrameworkProperties;
import com.shc.automation.Locator;
import com.shc.automation.Logger;
import com.shc.automation.PageAssert;
import com.shc.automation.SoftAssert;
import com.shc.automation.utils.TagAttributes;
import com.shc.automation.utils.TestContext;
import com.shc.automation.utils.TestUtils.CheckLocatorFor;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.util.Constant;
import com.shc.msp.ft.util.StringUtils;


public class HomePage extends Page {

	public HomePage(SiteFactory factory) {
		super(factory);
	}

	protected String getPageName() {
		return "Home Page";
	}

	protected String pageName() {
		return "Home Page";
	}
	 public static final Locator SEARCH_LAYAWAY= new Locator("SEARCH_LAYAWAY", "//a[@ui-sref='search-layaway' ]", "Search Layaway");
	public static final Locator MANAGE_QUEUES= new Locator("Manage queues", "//span[contains(text(),'Manage Queues')]", "Manage queues");
	public static final Locator SEARCH_CASE= new Locator("SEARCH_CASE", "//span[contains(text(),'Search Case')]", "SEARCH_CASE");
	public static final Locator SEARCH_ORDER= new Locator("SEARCH_ORDER", "//span[contains(text(),'Search Order')]", "SEARCH_ORDER");
	public static final Locator SEARCH_MEMBER= new Locator("SEARCH_MEMBER", "//span[contains(text(),'Search/Link Member')]", "SEARCH_MEMBER");
	public static final Locator MANAGE_BULK_NOTES= new Locator("MANAGE_BULK_NOTES", "//span[contains(text(),'Manage Bulk Notes')]", "MANAGE_BULK_NOTES");
	public static final Locator REPORTS= new Locator("REPORTS", "//a[contains(text(),'Reports')]", "REPORTS");
	public static final Locator REPORTS_DROPDOWN= new Locator("REPORTS DROPDOWN", "//select[@id='reportName']", "REPORTS DROPDOWN");
	public static final Locator REPORTS_DROPDOWN_OPTION= new Locator("REPORTS DROPDOWN OPTION", "//select[@id='reportName']//option[@value='{0}']", "REPORTS DROPDOWN OPTION");
	public static final Locator GENERATE= new Locator("GENERATE", "//button[contains(text(),' Generate')]", "GENERATE button");
	public static final Locator QUEUE_VOLUME_TITLE= new Locator("QUEUE_VOLUME_TITLE", "//h4[contains(text(),'Cases for ')]", "QUEUE_VOLUME_TITLE");
	public static final Locator QUEUE_VOLUME_QUEUE_NAME= new Locator("QUEUE_VOLUME_QUEUE_NAME", "//div[contains(text(),'Queue Name')]", "QUEUE_VOLUME_QUEUE_NAME");
	public static final Locator QUEUE_VOLUME_UNASSIGNED= new Locator("QUEUE_VOLUME_UNASSIGNED", "(//td[@data-title-text='Unassigned']/a)[3]", "QUEUE_VOLUME_UNASSIGNED LINK");
	public static final Locator QUEUE_VOLUME_CASES_UNASSIGNED= new Locator("QUEUE_VOLUME_CASES_UNASSIGNED", "//h4[contains(text(),'Cases for ')]", "QUEUE_VOLUME_CASES_UNASSIGNED");
	public static final Locator MANAGE_EMAIL_TEMPLATE= new Locator("MANAGE_EMAIL_TEMPLATE", "//span[contains(text(),'Manage Email Template')]", "MANAGE_EMAIL_TEMPLATE");
	public static final Locator MANAGE_USERS= new Locator("MANAGE_USERS", "//span[contains(text(),'Manage Users')]", "MANAGE_USERS");
	public static final Locator MANAGE_VENDOR= new Locator("Manage Vendor", "//span[contains(text(),'Manage Vendor')]", "Manage Vendor");
	public final Locator MENU_BUTTON = new Locator("", "//div[@class='fa fa-bars header-icon']","Menu Button");
	public final Locator USER_SEARCH = new Locator("", "//h3[contains(text(),'User Search')]","User search");
	public final Locator USER_ID = new Locator("", "//input[@id='userId']","User id");
	public final Locator USER_ID_LINK = new Locator("", "//td[contains(@data-title-text,'User Id')]//a","User ID LINK");
	public final Locator AGENT_CHECKBOX = new Locator("", "//input[@id='{0}']","AGENT_CHECKBOX");
	public final Locator UPDATE_BUTTON = new Locator("", "//button[contains(text(),'Update')]","UPDATE_BUTTON");
	public final Locator UPDATE_MESSAGE = new Locator("", "//div[contains(text(),'Update user Action has been successfully processed')]","UPDATE_MESSAGE");
	public final Locator UPDATE_MESSAGE_OK = new Locator("", "//button[contains(text(),'OK')]","UPDATE_MESSAGE_OK");
	public final Locator SEARCH_CUSTOMER = new Locator("","//a[@ui-sref='search-customer']","Search Customer");
	public final Locator HOME = new Locator("","//a[text()='Home']","Home link in Menu");
	public final Locator AGENTID_TEXTBOX = new Locator("", "//input[@id='j_username']", "Agent Id text box");
	public final Locator PASSWORD_TEXTBOX = new Locator("", "//input[@name='j_password']", "password input text box");
	public final Locator LOGIN_BUTTON = new Locator("", "//button[contains(text(),'Login')]", "Login button");
	public final Locator ERROR_POP_WINDOW = new Locator("ERROR POP UP WINDOW","//div[@class='modal-content']","Error Pop-up Window");
	public final Locator CLOSE_ERROR_POP_WINDOW = new Locator("CLOSE ERROR POP UP WINDOW","//button[@id='modalclose']","Close Error Pop-up Window Button");
	public final Locator ORDER_NUMBER_FIELD = new Locator("", "//input[@name='orderNumber']", "Order number ");
	public final Locator ORDER_TAB = new Locator("", "//div[contains(@ng-click,'Order')]", "Order Tab ");
	public final Locator MOVE_RIGHT_RESULTS = new Locator("", "(//a[contains(text(),'›')])[2]", "MOVE_RIGHT_RESULTS");
	public final Locator MOVE_RIGHT_RESULTS_END = new Locator("", "(//a[contains(text(),'»')])[2]", "MOVE_RIGHT_RESULTS_END");
	public final Locator ACTIVE_PAGE = new Locator("", "//ul[@class='pagination-sm pagination ng-isolate-scope']//li[@class='ng-scope active']", "ACTIVE_PAGE");
	public final Locator MOVE_LEFT_RESULTS_END = new Locator("", "(//a[contains(text(),'«')])[2]", "MOVE_RIGHT_RESULTS_END");
	public final Locator OPEN_HD_ORDERS = new Locator("", "//span[contains(text(),'Open') and //span[contains(text(),'Home Delivery')]]//ancestor::div[contains(@class,'mspselector ')]", "Open HD orders ");
	public final Locator PARAMATERIZED_HD_ORDER = new Locator("", "//span[contains(text(),'{0}') and //span[contains(text(),'Home Delivery')]]//ancestor::div[contains(@class,'mspselector ')]", "HD order");
	public final Locator CASE_NUMBER_FIELD = new Locator("", "//input[@name='caseNumber']", "Case number ");
	public final Locator CASE_TAB = new Locator("", "//div[contains(@ng-click,'Case')]", "Case Tab ");
	public final Locator SEARCH_BUTTON = new Locator("", "//button[@ng-click='searchCustomer()']", "Search button");
	public final Locator SEARCH_CASE_BUTTON = new Locator("", "(//button[contains(@ng-click,'searchCase')])[2]", "Search Case button");
	public final Locator LOGOUT_BUTTON = new Locator("", "//button[contains(@ng-click,'submitLogout')]", "Logout button");
	public final Locator NO_RESULT = new Locator("0 RESULT", "//div[contains(@ng-if,'data.customers.length == 0')]", "No Result");
	public static Locator TAB_NAME = new Locator("TAB_NAME", "//div[@class='layout horizontal start-justified center']//div[contains(text(),'{0}')]//parent::span/div[1]", "TAB_NAME"); 
	public final Locator MEMBER_PRODUCT_ENTRY = new Locator("TAB_NAME", "//input[@id='memberInfo']", "TAB_NAME");
	public final Locator MEMBER_RESULTS = new Locator("MEMBER_RESULTS", "//div[@ng-repeat='member in model.searchResults']", "MEMBER_RESULTS");
	public final Locator MEMBER_NUMBER = new Locator("MEMBER_NUMBER", "//div[contains(text(),'Member #: ')]", "MEMBER_NUMBER");

	public final Locator MEMBER_STATUS = new Locator("MEMBER_STATUS", "//span[contains(@ng-if,'member.status')]", "MEMBER_STATUS");
	public final Locator MEMBER_STATUS_RESULT = new Locator("MEMBER_STATUS_RESULT", "//div[@ng-repeat='member in memberData.member']", "MEMBER_STATUS_RESULT");
	public final Locator SHOP_CART = new Locator("SHOP_CART", "//div[contains(text(),'Cart')]", "SHOP_CART");
	public final Locator SHOP_MENU_BAR = new Locator("SHOP_MENU_BAR", "//ul[@id='department-list']", "SHOP_MENU_BAR");
	public final Locator SHOP_CATEGORY_SECTION = new Locator("SHOP_CATEGORY", "//div[@id='mspoeContent']", "SHOP_CATEGORY_SECTION");
	public final Locator SEARCH_BOX = new Locator("SEARCH_BOX", "//input[@ng-model='model.search.partNum']", "SEARCH_BOX");
	public final Locator UPDATE_PROFILE = new Locator("UPDATE_PROFILE", "//a[contains(text(),'Update Profile')]", "UPDATE_PROFILE");
	public final Locator SEARCH = new Locator("SEARCH", "//button[contains(text(),'Search')]", "SEARCH");
	//public final Locator SEARS_RADIO = new Locator("SEARS_RADIO", "//input[@value='10153']", "SEARS_RADIO");
	public final Locator SEARS_RADIO = new Locator("SEARS_RADIO", "(//input[@type='radio'])[1]", "SEARS_RADIO");
	public final Locator KMART_RADIO = new Locator("KMART_RADIO", "(//input[@type='radio'])[2]", "KMART_RADIO");
	public final Locator CUSTOMER_TAB_ZIPCODE = new Locator("Zipcode", "//input[@placeholder='ZipCode']", "CUSTOMER_TAB_ZIPCODE_TEXTBOX");
	public final Locator SUB_ORDER_NUMBER_FIELD = new Locator ("","//input[@id='subOrderNumber']","SUBORDER_NUMBER_TEXTBOX"); 
	public final Locator SEARCH_LAYAWAY_RADIO_BUTTON = new Locator("","//input[@value='Layaway']","SEARCH_LAYAWAY_RADIO_BUTTON"); 
	public final Locator LAYAWAY_CONTRACT_ID_TEXTBOX = new Locator("","//input[@id='contractId']","LAYAWAY_CONTRACT_ID_TEXTBOX");
	public final Locator LAYAWAY_PHONE_NUMBER_TEXTBOX = new Locator("LAYAWAY_PHONE_NUMBER_TEXTBOX","//form[@name='searchLayawayForm']//input[@id='phoneNumber']","LAYAWAY_PHONE_NUMBER_TEXTBOX");
	public final Locator LAYAWAY_STORE_NO_TEXTBOX = new Locator("","//input[@id='storeNo']","LAYAWAY_STORE_NO_TEXTBOX");
	public final Locator LAYAWAY_SEARCH_BUTTON = new Locator("","//form[@name='searchLayawayForm']//button[contains(text(),'Search')]","LAYAWAY_SEARCH_BUTTON");
	public final Locator LAYAWAY_SEARCH_RESULT = new Locator("","(//tr[contains(@ng-repeat,'layaway')])[{0}]/td[1]/a","LAYAWAY_SEARCH_RESULT");
	
	//For Delivery Flow
	public final Locator DOS_ORDER_NUMBER_FIELD = new Locator("", "//input[@id='orderNo']", "DOS Order number ");
	public final Locator DOS_UNIT_NUMBER_FIELD = new Locator("", "//input[@id='dosUnit']", "DOS Unit number ");
	public final Locator SEARCH_DELIVERY_BUTTON = new Locator("", "//button[@ng-click='search()']", "Search Delivery");
	public final Locator ORDERS_IN_MY_RECENT_DELIVERY_INTERACTIONS = new Locator("ORDER_ID_IN_MY_RECENT_DELIVERY_INTERACTIONS", "//div[@ng-repeat='order in deliveryOrders'][{0}]/div", "Order Id in My Recent Delivery Interactions");
	public final Locator DOS_PHONE_FIELD = new Locator("DOS_PHONE_FIELD","//input[@name='P']", "DOS Phone Number Field");
	public final Locator DOS_SALESCHECK_FIELD = new Locator("DOS_SALESCHECK_FIELD","//input[@name='S']", "Sales Check Number Field");
	public final Locator DOS_DATEFROM_FIELD = new Locator("DOS_DATEFROM_FIELD", "//input[@id='deliveryDate']", "Delivery Date Field");
	public final Locator SELECTED_DOS_DATE = new Locator("","(//table)[3]//tr[2]//td[contains(@id,'datepicker')][1]","Selected DOS Data");
	//public final Locator SELECTED_DOS_DATE = new Locator("","(//table)[2]//tr[5]//td[contains(@id,'datepicker')][1]","Selected From Data");


	public final Locator FIRSTNAME_FIELD = new Locator("FIRSTNAME_FIELD", "//input[@name='firstName']", "First Name Field");
	public final Locator LASTNAME_FIELD = new Locator("LASTNAME_FIELD", "//input[@name='lastName']", "Last Name Field");
	public final Locator SALESCHECK_FIELD = new Locator("SALESCHECK_FIELD", "//input[@name='salesCheck']", "Sales Check Field");
	public final Locator PHONE_FIELD = new Locator("PHONE_FIELD","//input[@name='phoneNumber']", "Phone Number Field");
	public final Locator EMAIL_FIELD = new Locator("EMAIL_FIELD", "//input[@name='email']", "Email Field");
	public final Locator STORE_FIELD = new Locator("STORE_FIELD","//select[@id='storeId']","Store Select");
	public final Locator DATEFROM_FIELD = new Locator("DATEFROM_FIELD", "//div[@class='form-group'][1]//input[@datepicker-options='fromOrderDate.dateOptions']", "Date From Field");
	public final Locator DATETO_FIELD = new Locator("DATETO_FIELD", "//div[@class='form-group'][2]//input[@datepicker-options='toOrderDate.dateOptions']", "Date To Field");
	public final Locator MONFROM_RIGHTCLICK = new Locator("", "(//button[@class='btn btn-default btn-sm pull-right'])[1]","From Right Click");
	public final Locator MONTO_LEFTCLICK = new Locator("", "(//button[@class='btn btn-default btn-sm pull-left'])[2]","To Left Click");
	public final Locator SELECTED_FROM_DATE = new Locator("","(//table)[1]//tr[2]//td[contains(@id,'datepicker')][1]","Selected From Data");
	public final Locator SELECTED_TO_DATE = new Locator("","((//table)[2]//tr[5]//td[contains(@id,'datepicker')])[1]","Selected To Data");


	public final Locator MY_RECENT_INTERACTIONS = new Locator("MY_RECENT_INTERACTIONS", "//h3[text()='My Recent Interactions']/ancestor::div[@class='panel panel-info']", "My Recent Interactions");
	public final Locator SEARCH_RESULTS = new Locator("Search result", "//div[@id='searchResult']", "Search result");
	public final Locator RESULT_NUMBER  = new Locator ("RESULT NUMBER","//div[@ng-if='data.customers != null']//h4","Result Number");
	public final Locator ORDER_ID_IN_MY_RECENT_INTERACTIONS = new Locator("ORDER_ID_IN_MY_RECENT_INTERACTIONS", "(//div[@ng-repeat='order in onlineOrders'])[{0}]", "Order Id in My Recent Interactions");

	public final Locator AGENT_ACTIVE_ROLE = new Locator("", "//select[@name='role']/option", "Agent Active Role");
	public final Locator MY_SETTINGS = new Locator("MY_SETTINGS", "//h3[contains(text(),' My Settings ')]", "MY_SETTINGS");
	public final Locator MY_INFORMATION = new Locator("MY_INFORMATION", "//h3[contains(text(),' My Information ')]", "MY_INFORMATION");
	public final Locator MY_QUEUELIST = new Locator("MY_QUEUELIST", "//h3[contains(text(),' My Queue List: ')]", "MY_QUEUELIST");
	public final Locator AGENT_ACTIVE_ROLE_OPTION = new Locator("AGENT_ACTIVE_ROLE_OPTION", "//select[@name='role']/option[contains(text(),'{0}')]", "AGENT_ACTIVE_ROLE_OPTION");
	public final Locator DELIVERY_AGENT_ACTIVE = new Locator("", "//span[text()='DELIVERY AGENT']//preceding-sibling::span//parent::button", "Delivery agent active");
	public final Locator OFFLINE_AGENT_ACTIVE = new Locator("", "//span[text()='OFFLINE AGENT']//preceding-sibling::span//parent::button", "Offline agent active");
	public final Locator ADMIN_ACTIVE = new Locator("", "//span[text()='ADMIN']//preceding-sibling::span//parent::button", "Admin active");
	public final Locator SUPERADMIN_ACTIVE = new Locator("", "//span[text()='SUPER ADMIN']//preceding-sibling::span//parent::button", "Super Admin active");
	public final Locator ONLINE_AGENT_INACTIVE = new Locator("", "//span[text()=' ONLINE AGENT']//following-sibling::span", "Online agent inactive");
	public final Locator ONLINEAGENT_INACTIVE = new Locator("", "//select[@name='role']/option[contains(text(),'ONLINE AGENT')]", "Online agent inactive");
	public final Locator AGENT_INACTIVE = new Locator("", "//select[@name='role']/option[text()='{0}']", "Agent inactive");
	public final Locator OFFLINE_AGENT_INACTIVE = new Locator("", "//span[text()=' OFFLINE AGENT']//following-sibling::span", "Offline agent inactive");
	public final Locator  OFFLINEAGENT_INACTIVE = new Locator("", "//select[@name='role']/option[contains(text(),'OFFLINE AGENT')]", "OfflineAgent inactive");
	public final Locator SUPERADMIN_INACTIVE = new Locator("", "//select[@name='role']/option[contains(text(),'SUPER ADMIN')]", "Super Admin inactive");
	public final Locator ADMIN_INACTIVE = new Locator("", "//select[@name='role']/option[contains(text(),'ADMIN')]", "Super Admin inactive");
	public final Locator DELIVERYAGENT_INACTIVE = new Locator("", "//select[@name='role']/option[contains(text(),'DELIVERY AGENT')]", "Delivery agent inactive");
	public final Locator DELIVERY_OFFLINE_AGENT_INACTIVE = new Locator("", "//select[@name='role']/option[contains(text(),'DELIVERY OFFLINE AGENT')]", "Delivery offline agent inactive");
	public final Locator USER_ROLE = new Locator("", "//select[@name='role']", "Current active mode");
	public final Locator AGENT_CHANNEL = new Locator("", "//select[@name='channel']", "Current active channel");
	public final Locator SWITCH_ROLE_POPUP_PAGE= new Locator("SWITCH ROLE POPUP PAGE", "(//div[@class='modal-content'])", "Switch Role Popup Success Page");
	public final Locator SWITCH_ROLE_POPUP = new Locator("", "//button[contains(@class,'close btn ')]", "close button");
	public final Locator SAVE_BUTTON = new Locator("", "//button[contains(text(),'Save Changes')]", "save button");
	public final Locator OK_BUTTON = new Locator("", "(//button[contains(@id,'modalclose')])[last()]", "Ok button pop-up");
	public static final Locator VIEW_PROFILE_LINK = new Locator("", "//ul[@class='nav navbar-nav navbar-right user-profile-pull-down']//li[@class='dropdown']//a", "View use profile link");
	public final Locator QUEUE_NAME = new Locator("QUEUE_NAME", "//td[contains(@data-title,'Queue Name')]", "QUEUE_NAME");

	public final Locator ORDER_RESULT_DATE_R1 = new Locator("","//div[@class='table-responsive ng-scope']//fieldset//table[@ng-table='orderListTableParams']//tbody/tr[1]/td[1]","Order Result Date Row 1");
	public final Locator ORDER_RESULT_DATE_R2 = new Locator("","//div[@class='table-responsive ng-scope']//fieldset//table[@ng-table='orderListTableParams']//tbody/tr[2]/td[1]","Order Result Date Row 2");
	public final Locator ORDER_RESULT_DATE_R3 = new Locator("","//div[@class='table-responsive ng-scope']//fieldset//table[@ng-table='orderListTableParams']//tbody/tr[3]/td[1]","Order Result Date Row 3");
	public final Locator ORDER_RESULT_DATE_R4 = new Locator("","//div[@class='table-responsive ng-scope']//fieldset//table[@ng-table='orderListTableParams']//tbody/tr[4]/td[1]","Order Result Date Row 4");
	public final Locator ORDER_RESULT_DATE_R5 = new Locator("","//div[@class='table-responsive ng-scope']//fieldset//table[@ng-table='orderListTableParams']//tbody/tr[5]/td[1]","Order Result Date Row 5");
	public final Locator ORDER_RESULT_FOOT_NEXT = new Locator("","//a[@ng-switch-when='next'][@ng-click='params.page(page.number)']","Order Research Page Next");
	public final Locator ORDER_RESULT_FIRST_PAGE = new Locator("","//a[@ng-switch-when='first'][@ng-click='params.page(page.number)']","Order Research First Page");
	public final Locator ORDER_RESULT_TABLE = new Locator("","//div[@class='table-responsive ng-scope']//fieldset/table","Order Result Table");
	public final Locator CASE_ORDERID = new Locator("","//tr//td[@data-title-text='Order Id']/a/span[@ng-if='case.order.orderId != null']","Case order id");
	public final Locator CASE_UNASSIGNED = new Locator("","//a[@ng-click='seletCaseMine(unassignedCase)']","Case order id OF UNASSIGNED CASE");
	public final Locator CASE_ASSIGN_SUCCESS = new Locator("","//div[contains(text(),'Case successfully Assigned.')]","Case successfully Assigned popup");
	public final Locator CASE_ASSIGN_OK = new Locator("","//button[@id='modalclose']","Case successfully Assigned popup OK button");


	//Error PopUp window
	public final Locator ERROR_POPUP = new Locator("Error popup","//div[@class='modal-header dialog-header-error ng-scope ng-isolate-scope']","Error popup");
	public final Locator ERROR_POPUP_CLOSE = new Locator("Error popup close","(//button[@id='modalclose'])","Error popup close");   
	public final Locator WARNING_POPUP = new Locator("Warning popup","//h4[@class='modal-title text-info ng-binding']","Warning popup");
	public final Locator WARNING_POPUP_CLOSE = new Locator("WARNING_POPUP close","//button[@id='modalclose']","WARNING_POPUP close");

	//  Delivery Locators 
	public final Locator DEL_PHONENUMBER_SEARCH = new Locator("DEL_PHONENUMBER_SEARCH","//input[@class='form-control input-sm ng-pristine ng-valid ng-valid-phone']","DEL_PHONENUMBER_SEARCH");
	public final Locator DELPHONENUMBER_SEARCH = new Locator("DEL_PHONENUMBER_SEARCH","	//div[@class='input-group']//input[@id='P']","Delivery phone number search");
	public final Locator DELSEARCH_BUTTON = new Locator("", "//button[@ng-click='search()']", "Search button");
	public final Locator DELIVERY_DATE = new Locator("", "//strong[contains(text(),'Delivery Date: ')]//ancestor::div[@class='form-group']//span[2]", "Delivery date");
	public final Locator RELEASED_HD_ORDERS = new Locator("", "//span[contains(text(),'Released')]/parent::div/parent::div/parent::div/following-sibling::div[@class='row']//span[contains(text(),'Home Delivery')]", "released HD orders ");
	public final Locator PARTIALLY_SHIPPED_HD_ORDERS = new Locator("", "//span[contains(text(),'Partially Shipped')]/parent::div/parent::div/parent::div/following-sibling::div[@class='row']//span[contains(text(),'Home Delivery')]", "Partially shipped HD orders ");
	public final Locator SHIPPED_HD_ORDERS = new Locator("", "//span[contains(text(),'Shipped')]/parent::div/parent::div/parent::div/following-sibling::div[@class='row']//span[contains(text(),'Home Delivery')]", "Shipped HD orders ");


	public final Locator MEMBER_INFO_TXT_BOX = new Locator("","memberInfo","Member Information textbox",TagAttributes.ID);
	public final Locator SEARCH_MEMBER_BUTTON = new Locator("","//form[@name='searchForm']//button[text()='Search']","Search button");
	public final Locator MEMBER_SEARCH_RESULT_IDENTIFIER = new Locator("","(//div[@ng-repeat='member in model.searchResults'])[1]","Search results for member");

	public final Locator CLOSE_PROFILE_MODAL = new Locator("","//div[@class='modal-dialog']//button[@ng-click='close()']","Close button in profile");
	public final Locator SAVE_CHANGES_BUTTON = new Locator("","//button[text()='Save Changes']","Save Changes button in profile");
	public final Locator PHONE_ID = new Locator("","//input[@name='phoneId']","Phone Id Input");
	public final Locator PHONE_ID_SUBMIT_BUTTON = new Locator("","//button[@id='phoneIdModelSubmit']","Phone Id Submit button");
	public final Locator PHONE_ID_CANCEL_BUTTON = new Locator("","//button[text()='Cancel']","Phone Id Cancel button");
	public final Locator PHONE_ID_OK_BUTTON = new Locator("","//button[text()='OK']","Phone Id OK button");
	public final Locator PHONE_ID_ERROR = new Locator("","//div[@class='error']","Phone Id Error Text");
	public final Locator PHONE_ID_CONNECTED = new Locator("","//span[text()='Connected']","Phone Id Connected");
	public final Locator PHONE_ID_TEXT = new Locator("","//span[text()='({0})']","Phone Id Text");
	public final Locator PHONE_ID_NOT_CONNECTED = new Locator("","//span[text()='Not Connected']","Phone Id Text");
	public final Locator PHONE_ICON = new Locator("","//span[@class='glyphicon glyphicon-earphone']","Phone Icon");
	public final Locator PHONE_ORDER_RESULTS = new Locator("","//*[contains(text(),'Phone Number')]//ancestor::div[@class='form-group']//span[2]","Phone Number in Order Results");
	public final Locator PHONE_PROFILE_SEARCH_RESULTS = new Locator("","//span[@ng-repeat='phone in member.phoneNumbers']//parent::div[@class='form-group']","Phone Number in Profile Search Results");
	public final Locator SELECT_PROFILE_FRON_SEARCH_RESULTS = new Locator("","//div[@ng-repeat='member in model.searchResults'][{0}]","Select Profile in Profile Search Results");
	public final Locator NO_SEARCH_RESULTS_LABEL = new Locator("No Results Found Label", "//h4[contains(text(),'No Result found')]", "No search result label");
	public static final Locator CTI_LOGOUT_CONFIRMATION_TEXT = new Locator("", "//div[contains(text(),'Your MSP session will be disconnected from your Phone session.')]", "CTI Logout Confirmation Text");
	public static final Locator CTI_LOGOUT_CONFIRMATION_YES = new Locator("", "//button[contains(text(),'Yes')]", "CTI Logout Confirmation Yes");
	public static final Locator CTI_LOGOUT_CONFIRMATION_NO = new Locator("", "//button[contains(text(),'No')]", "CTI Logout Confirmation No");
	
	public final Locator SYW_LINK_TAB = new Locator("", "//div[contains(@ng-click,'Link')]", "SYW link");
	public final Locator SYW_LINK_PHONE_NUMBER = new Locator("", "//*[@id='phoneNumber']", "SYW link phone number");
	public final Locator SYW_LINK_EMAIL = new Locator("", "//*[@id='email']", "SYW link phone number");
	public final Locator SYW_LINK_SEARCH_RESULT = new Locator("","//*[contains(@ng-repeat,'member in memberData.member')][1]","SYW_LINK_SEARCH_RESULT");
	public final Locator IGNORE_CERTIFICATE_ERROR = new Locator("", "//a[@id='overridelink']", "Certificate error IE 11");
	private int invalidLoginCount = 1;
	
	public final Locator PERFORMANCE_SUPPORT_DISPLAY_MSG = new Locator("","(//performance-support)[2]","PERFORMANCE_SUPPORT_DISPLAY_MSG");
	public final Locator PERFORMANCE_SUPPORT_NAME = new Locator("", "//label[contains(text(),'{0}')]/preceding-sibling::img","PERFORMANCE_SUPPORT_NAME");
	
	
	public void maximizeWindow() {
		getAction().driver.manage().window().maximize();
	}

	public HomePage login(User user) {
		
		/*// proceed on Certificate error
		if (getAction().isElementPresent(IGNORE_CERTIFICATE_ERROR)){
			System.out.println("------------------------------- Certificate Error, clicking on proceed -------------------------------");
			Logger.log("Certificate Error, clicking on proceed",TestStepType.STEP);
			getAction().click(IGNORE_CERTIFICATE_ERROR);
		}*/
		
		Logger.log("Login using the Agent credentials" , TestStepType.STEP);
		AjaxCondition.forElementVisible(LOGIN_BUTTON).waitForResponse(5);
		getAction().waitFor(2000);
		Logger.log("Agentid - "+user.userName,TestStepType.SUBSTEP);
		getAction().click(AGENTID_TEXTBOX);
		getAction().type(AGENTID_TEXTBOX, user.userName);
		if(FrameworkProperties.SELENIUM_BASE_URL.contains("msp.prod.global.s.com")){
			user.password=Constant.OnlinePasswordProd;
		}
		Logger.log("Password - "+user.password,TestStepType.SUBSTEP);
		getAction().click(PASSWORD_TEXTBOX);
		getAction().type(PASSWORD_TEXTBOX, user.password);
		getAction().waitFor(1000);
		Logger.log("Click on login button",TestStepType.STEP);
		getAction().click(LOGIN_BUTTON); 
		getAction().waitFor(3000);
		
		/*// Closing certificate alert if present
		if(getAction().isAlertPresent()){
			System.out.println("-----------------------  Certificate Alert Present, closing alert -----------------------------");
			Logger.log("Certificate Alert Present, closing alert",TestStepType.STEP);
			getAction().closeAlertIfPresent();
		}*/
				
		// Temporary code for Login issue in prod 
		if(getAction().isElementPresent(LOGIN_BUTTON) && (invalidLoginCount<5)){
			System.out.println("------------------------------------------------Login failure---------------------------------------Attempt # "+invalidLoginCount);
			invalidLoginCount++;
			login(user);
		}
		invalidLoginCount =1;
		
		// Close popup when not testing CTI
		if (!FrameworkProperties.getProperty("cti", "false").equalsIgnoreCase("true")) {
			AjaxCondition.forElementVisible(PHONE_ID).waitWithoutException(5);
			if (getAction().isElementPresent(PHONE_ID)) {
				Logger.log("Click cancel on the PHONE ID popup", TestStepType.STEP);
				verifyPhoneIdCancel();
				TAB_NAME = new Locator("TAB_NAME", "//div[@class='layout horizontal start-justified center']//div[contains(text(),'{0}')]//parent::span/div[2]", "TAB_NAME");
			}
			
		}
				
		return this;
	}

	public HomePage verifyLogin(User user){
		if(getAction().isVisible(VIEW_PROFILE_LINK)){
			searchCustomer();
		}else{
			login(user);
		}
		return this;
	}

	/**
	 * Logout from the application
	 * @return
	 */
	public HomePage logout() {
		getAction().waitFor(3000);
		Logger.log("Logout from application", TestStepType.STEP);
		Logger.log("Click on view profile link", TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse(10);
		getAction().click(VIEW_PROFILE_LINK);
		Logger.log("Click on logout button", TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(LOGOUT_BUTTON).waitForResponse(50);
		getAction().click(LOGOUT_BUTTON);
		return this;
	}
	public HomePage verifyQueueName() {
		Logger.log("Verifying queue name displayed on home page", TestStepType.STEP);
		AjaxCondition.forElementVisible(QUEUE_NAME).waitForResponse(10);
		String queue=(String) getContext().get("queue");
		PageAssert.textPresentIn(QUEUE_NAME, queue);
		return this;
	}

	/**
	 * Close warning  popup if its present
	 * @return
	 */
	public HomePage closeWarningPopupWindow() {
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


	public HomePage verifyonlineagent() {
		closeWarningPopupWindow();
		Logger.log("Verify user role is 'ONLINE AGENT'", TestStepType.STEP);
		Logger.log("Click on view profile link", TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		if(getAction().getText(VIEW_PROFILE_LINK).split("\\|")[1].trim().equals("ONLINE AGENT")){
			return this;
		}
		getAction().click(VIEW_PROFILE_LINK);
		getAction().waitFor(3000);
		Logger.log("Verify agent role is displayed", TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(AGENT_ACTIVE_ROLE).waitWithoutException(10);
		if (getSelectedOption(USER_ROLE).equals("ONLINE AGENT")) {
			Logger.log("User  is in 'ONLINE AGENT' mode", TestStepType.VERIFICATION_STEP);
			Logger.log("Click on close button in profile",TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(CLOSE_PROFILE_MODAL).waitForResponse();
			getAction().click(CLOSE_PROFILE_MODAL);
		} else {
			Logger.log("Currently user is in " + getSelectedOption(USER_ROLE), TestStepType.SUBSTEP);
			getAction().selectByText(USER_ROLE, "ONLINE AGENT");
			getAction().waitFor(1000);
			Logger.log("Switch role to online agent",TestStepType.SUBSTEP);
			getAction().click(SAVE_CHANGES_BUTTON);
			Logger.log("Accept role change confirmation in popup",TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(SWITCH_ROLE_POPUP_PAGE).waitForResponse(10);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(5);
			//getAction().waitFor(2000);
			getAction().click(OK_BUTTON);
			getAction().waitFor(1000);
			getAction().click(SWITCH_ROLE_POPUP);
			getAction().waitFor(1000);
			if(AjaxCondition.forElementVisible(PHONE_ID_CANCEL_BUTTON).waitWithoutException(10)){
				Logger.log("Click Cancel on Phone ID dialog",TestStepType.SUBSTEP);
				getAction().click(PHONE_ID_CANCEL_BUTTON);
			}
			if(AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse(30)){
				getAction().waitFor(3000);
				getAction().click(VIEW_PROFILE_LINK);
				getAction().waitFor(3000);
				if (getAction().isSelected(ONLINEAGENT_INACTIVE)) {
					Logger.log("User  is changed to 'ONLINE AGENT' mode", TestStepType.STEP);
				} else {
					PageAssert.fail("User role was not changed to 'ONLINE AGENT' mode");
				}
				getAction().click(SWITCH_ROLE_POPUP);
				getAction().waitFor(1000);
			}else{
				PageAssert.fail("View Profile Link is not Visible");
			}
		}

		return this;
	}

	public HomePage changeAgentRole(String role, Boolean warningPopup){
		Logger.log("Click on view profile link", TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		try{
			getAction().click(VIEW_PROFILE_LINK);
		}catch(Throwable t){
			clickJ(VIEW_PROFILE_LINK);
		}		
		//getAction().waitFor(3000);
		AjaxCondition.forElementVisible(USER_ROLE).waitWithoutException(5);
		Logger.log("Change te role to - "+role, TestStepType.SUBSTEP);
		getAction().selectByText(USER_ROLE, role);
		Logger.log("Click on Save Changes Button",TestStepType.SUBSTEP);
		getAction().click(SAVE_CHANGES_BUTTON);
		getAction().waitFor(1000);
		if(warningPopup){
			getAction().waitFor(2000);
			closePhoneDisconnectionWarningPopup();
		}
		Logger.log("Accept role change confirmation in popup",TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(SWITCH_ROLE_POPUP_PAGE).waitWithoutException(10);
		AjaxCondition.forElementVisible(OK_BUTTON).waitWithoutException(10);
		getAction().click(OK_BUTTON);
		getAction().waitFor(500);
		getAction().click(SWITCH_ROLE_POPUP);
		return this;		
	}

	public HomePage changeAgentChannel(String channel,Boolean warningPopup){
		Logger.log("Click on view profile link", TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		try{
			getAction().click(VIEW_PROFILE_LINK);
		}catch(Throwable t){
			clickJ(VIEW_PROFILE_LINK);
		}		
		AjaxCondition.forElementVisible(USER_ROLE).waitWithoutException(5);
		Logger.log("Change te channel to - "+channel, TestStepType.SUBSTEP);
		getAction().selectByText(AGENT_CHANNEL, channel);
		Logger.log("Click on Save Changes Button",TestStepType.SUBSTEP);
		getAction().click(SAVE_CHANGES_BUTTON);
		getAction().waitFor(1000);
		if(warningPopup){
			getAction().waitFor(2000);
			closePhoneDisconnectionWarningPopup();
		}
		Logger.log("Accept role change confirmation in popup",TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(SWITCH_ROLE_POPUP_PAGE).waitWithoutException(10);
		AjaxCondition.forElementVisible(OK_BUTTON).waitWithoutException(10);
		getAction().click(OK_BUTTON);
		getAction().waitFor(500);
		getAction().click(SWITCH_ROLE_POPUP);
		return this;
	}
	
	public HomePage validateAgentChannel(String channel){
		Logger.log("Validate the Agent Channel is :-"+ channel,TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		try{
			getAction().click(VIEW_PROFILE_LINK);
		}catch(Throwable t){
			clickJ(VIEW_PROFILE_LINK);
		}
		String currentChannel = getSelectedOption(AGENT_CHANNEL);
		System.out.println("Current Channel :- "+currentChannel);
		getAction().waitFor(500);
		getAction().click(SWITCH_ROLE_POPUP);
		return this;
	}


	public HomePage closePhoneDisconnectionWarningPopup(){
		Logger.log("Close Phone Disconnection Warning", TestStepType.STEP);
		AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(5);
		getAction().click(OK_BUTTON);
		getAction().waitFor(2000);
		return this;
	}

	public HomePage validateAgentRole(String role){

		String activeRole = getAction().getText(VIEW_PROFILE_LINK).split("\\|")[1].trim();
		System.out.println("Active role---------------"+activeRole);
		Logger.log("Verify the active role is - "+role, TestStepType.VERIFICATION_SUBSTEP);
		PageAssert.verifyTrue(activeRole.equals(role), "Validate selected role is" + role);
		return this;
	}

	public HomePage verifyAgentRole(String role) {
		closeWarningPopupWindow();
		Logger.log("Verify user role is - "+role, TestStepType.STEP);
		Logger.log("Click on view profile link", TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		if(getAction().getText(VIEW_PROFILE_LINK).split("\\|")[1].trim().equals(role)){
			return this;
		}
		getAction().click(VIEW_PROFILE_LINK);
		getAction().waitFor(3000);
		Logger.log("Verify agent role is displayed", TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(AGENT_ACTIVE_ROLE).waitWithoutException(10);
		if (getSelectedOption(USER_ROLE).equals(role)) {
			Logger.log("User  is in mode - "+role, TestStepType.VERIFICATION_STEP);
			Logger.log("Click on close button in profile",TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(CLOSE_PROFILE_MODAL).waitForResponse();
			getAction().click(CLOSE_PROFILE_MODAL);
		} else {
			Logger.log("Currently user is in " + getSelectedOption(USER_ROLE), TestStepType.SUBSTEP);
			getAction().selectByText(USER_ROLE, role);
			getAction().waitFor(1000);
			Logger.log("Switch role to - "+role,TestStepType.SUBSTEP);
			getAction().click(SAVE_CHANGES_BUTTON);
			Logger.log("Accept role change confirmation in popup",TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(SWITCH_ROLE_POPUP_PAGE).waitForResponse(10);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(5);
			//getAction().waitFor(2000);
			getAction().click(OK_BUTTON);
			getAction().waitFor(1000);
			getAction().click(SWITCH_ROLE_POPUP);
			getAction().waitFor(1000);

			if(AjaxCondition.forElementVisible(PHONE_ID_CANCEL_BUTTON).waitWithoutException(10)){
				Logger.log("Click Cancel on Phone ID dialog",TestStepType.SUBSTEP);
				getAction().click(PHONE_ID_CANCEL_BUTTON);
			}

			if(AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse(30)){
				getAction().waitFor(3000);
				getAction().click(VIEW_PROFILE_LINK);
				getAction().waitFor(3000);
				if (getAction().isSelected(AGENT_INACTIVE.format(role))) {
					Logger.log("User  is changed to mode - "+role, TestStepType.STEP);
				} else {
					PageAssert.fail("User role was not changed to mode - "+role);
				}
				getAction().click(SWITCH_ROLE_POPUP);
				getAction().waitFor(1000);
			}else{
				PageAssert.fail("View Profile Link is not Visible");
			}
		}

		return this;
	}

	public HomePage verifydeliveryagent() {
		closeWarningPopupWindow();
		Logger.log("Verify user role is 'DELIVERY AGENT'", TestStepType.STEP);
		Logger.log("Click on view profile link", TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		if(getAction().getText(VIEW_PROFILE_LINK).split("\\|")[1].trim().equals("DELIVERY AGENT")){
			return this;
		}
		getAction().click(VIEW_PROFILE_LINK);
		getAction().waitFor(3000);
		Logger.log("Verify agent role is displayed", TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(DELIVERY_AGENT_ACTIVE).waitWithoutException(10);
		if (getSelectedOption(USER_ROLE).equals("DELIVERY AGENT")) {
			Logger.log("User  is in 'DELIVERY AGENT' mode", TestStepType.VERIFICATION_STEP);
			Logger.log("Click on close button in profile",TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(CLOSE_PROFILE_MODAL).waitForResponse();
			getAction().click(CLOSE_PROFILE_MODAL);
		} else {
			Logger.log("Currently user is in " + getSelectedOption(USER_ROLE), TestStepType.SUBSTEP);
			getAction().selectByText(USER_ROLE, "ONLINE AGENT");
			getAction().waitFor(1000);
			Logger.log("Switch role to online agent",TestStepType.SUBSTEP);
			getAction().click(SAVE_CHANGES_BUTTON);
			Logger.log("Accept role change confirmation in popup",TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(SWITCH_ROLE_POPUP_PAGE).waitForResponse(10);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(5);
			//getAction().waitFor(2000);
			getAction().click(OK_BUTTON);
			getAction().waitFor(1000);
			getAction().click(SWITCH_ROLE_POPUP);
			getAction().waitFor(1000);
			if(AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse(30)){
				getAction().click(VIEW_PROFILE_LINK);
				if (getAction().isVisible(DELIVERY_AGENT_ACTIVE)) {
					Logger.log("User  is changed to 'DELIVERY AGENT' mode", TestStepType.STEP);
				} else {
					PageAssert.fail("User role was not changed to 'DELIVERY AGENT' mode");
				}
				getAction().click(SWITCH_ROLE_POPUP);
				getAction().waitFor(1000);
			}else{
				PageAssert.fail("View Profile Link is not Visible");
			}
		}

		return this;
	}

	public String getSelectedOption(Locator locator) {
		AjaxCondition.forElementVisible(USER_ROLE).waitForResponse();
		Select select = new Select(getAction().findElement(locator));
		String selectedOption=select.getFirstSelectedOption().getText();
		return selectedOption;
	}

	public HomePage verifyofflineagent() {
		getAction().waitFor(1000);
		closeWarningPopupWindow();
		Logger.log("Verify user role is 'OFFLINE AGENT", TestStepType.STEP);
		Logger.log("Click on view profile link", TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		getAction().click(VIEW_PROFILE_LINK);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(AGENT_ACTIVE_ROLE).waitWithoutException(10);
		if (getAction().isVisible(AGENT_ACTIVE_ROLE) && getAction().isSelected(OFFLINEAGENT_INACTIVE)) {
			getAction().waitFor(1000);
			Logger.log("User  is in 'OFFLINEAGENT' mode", TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(OFFLINEAGENT_INACTIVE).waitForResponse(10);
			getAction().click(OFFLINEAGENT_INACTIVE);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse(10);
			Logger.log("Accepting role change confirmation", TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			getAction().click(SAVE_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(10);
			getAction().click(OK_BUTTON);
			Logger.log("Click on close button in profile",TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(CLOSE_PROFILE_MODAL).waitForResponse(10);
			getAction().click(CLOSE_PROFILE_MODAL);

		} else {
			AjaxCondition.forElementVisible(USER_ROLE).waitForResponse();
			Logger.log("Currently user is in " + getSelectedOption(USER_ROLE), TestStepType.SUBSTEP);
			Logger.log("Changing to  'OFFLINE AGENT' mode", TestStepType.SUBSTEP);
			getAction().waitFor(1000);
			getAction().click(OFFLINEAGENT_INACTIVE);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse(10);
			Logger.log("Accepting role change confirmation", TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			getAction().click(SAVE_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(10);
			getAction().click(OK_BUTTON);
			AjaxCondition.forElementVisible(SWITCH_ROLE_POPUP).waitForResponse(10);
			getAction().waitFor(1000);
			getAction().click(SWITCH_ROLE_POPUP);
			getAction().waitFor(1000);
			if(AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse(30)){
				getAction().click(VIEW_PROFILE_LINK);
				getAction().waitFor(5000);
				if (getAction().isSelected(OFFLINEAGENT_INACTIVE)) {
					getAction().waitFor(1000);
					Logger.log("User  is changed to 'OFFLINE AGENT' mode", TestStepType.STEP);
				} else {
					PageAssert.fail("User role was not changed to 'OFFLINE AGENT' mode");
				}
			}else{
				PageAssert.fail("View Profile Link is not Visible");
			}
			getAction().click(SWITCH_ROLE_POPUP);
		}

		return this;
	}

	public HomePage verifySuperAdmin() {
		getAction().waitFor(1000);
		closeWarningPopupWindow();
		Logger.log("Verify User is in 'SUPER ADMIN' mode", TestStepType.STEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		getAction().click(VIEW_PROFILE_LINK);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(AGENT_ACTIVE_ROLE).waitWithoutException(10);
		if (getAction().isVisible(AGENT_ACTIVE_ROLE) && getAction().isSelected(SUPERADMIN_INACTIVE)) {
			getAction().waitFor(1000);
			Logger.log("User  is in 'SUPER ADMIN' mode", TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(SUPERADMIN_INACTIVE).waitForResponse(10);
			getAction().click(SUPERADMIN_INACTIVE);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse(10);
			Logger.log("Accepting role change confirmation", TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			getAction().click(SAVE_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(10);
			getAction().click(OK_BUTTON);
			Logger.log("Click on close button in profile",TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(CLOSE_PROFILE_MODAL).waitForResponse(10);
			getAction().click(CLOSE_PROFILE_MODAL);

		} else {
			AjaxCondition.forElementVisible(USER_ROLE).waitForResponse();
			Logger.log("Currently user is in " + getSelectedOption(USER_ROLE), TestStepType.SUBSTEP);
			Logger.log("Changing to  'SUPER ADMIN' mode", TestStepType.SUBSTEP);
			getAction().waitFor(1000);
			getAction().click(SUPERADMIN_INACTIVE);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse(10);
			Logger.log("Accepting role change confirmation", TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			getAction().click(SAVE_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(10);
			getAction().click(OK_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(SWITCH_ROLE_POPUP).waitForResponse(10);
			getAction().click(SWITCH_ROLE_POPUP);
			getAction().waitFor(1000);
			if(AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse(30)){
				getAction().click(VIEW_PROFILE_LINK);
				getAction().waitFor(5000);
				if (getAction().isSelected(SUPERADMIN_INACTIVE)) {
					getAction().waitFor(1000);
					Logger.log("User  is changed to 'SUPER ADMIN' mode", TestStepType.STEP);
				} else {
					PageAssert.fail("User is not in 'SUPER ADMIN' mode");
				}
			}else{
				PageAssert.fail("View Profile Link is not Visible");
			}
			getAction().click(SWITCH_ROLE_POPUP);
		}

		return this;
	}
	
	public HomePage verifyAdmin() {
		getAction().waitFor(1000);
		closeWarningPopupWindow();
		Logger.log("Verify User is in 'ADMIN' mode", TestStepType.STEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		getAction().click(VIEW_PROFILE_LINK);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(AGENT_ACTIVE_ROLE).waitWithoutException(10);
		if (getAction().isVisible(AGENT_ACTIVE_ROLE) && getAction().isSelected(ADMIN_INACTIVE)) {
			getAction().waitFor(1000);
			Logger.log("User  is in 'SUPER ADMIN' mode", TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(ADMIN_INACTIVE).waitForResponse(10);
			getAction().click(ADMIN_INACTIVE);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse(10);
			Logger.log("Accepting role change confirmation", TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			getAction().click(SAVE_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(10);
			getAction().click(OK_BUTTON);
			Logger.log("Click on close button in profile",TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(CLOSE_PROFILE_MODAL).waitForResponse(10);
			getAction().click(CLOSE_PROFILE_MODAL);

		} else {
			AjaxCondition.forElementVisible(USER_ROLE).waitForResponse();
			Logger.log("Currently user is in " + getSelectedOption(USER_ROLE), TestStepType.SUBSTEP);
			Logger.log("Changing to  'ADMIN' mode", TestStepType.SUBSTEP);
			getAction().waitFor(1000);
			getAction().click(ADMIN_INACTIVE);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse(10);
			Logger.log("Accepting role change confirmation", TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			getAction().click(SAVE_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(10);
			getAction().click(OK_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(SWITCH_ROLE_POPUP).waitForResponse(10);
			getAction().click(SWITCH_ROLE_POPUP);
			getAction().waitFor(1000);
			if(AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse(30)){
				getAction().click(VIEW_PROFILE_LINK);
				getAction().waitFor(5000);
				if (getAction().isSelected(ADMIN_INACTIVE)) {
					getAction().waitFor(1000);
					Logger.log("User  is changed to 'ADMIN' mode", TestStepType.STEP);
				} else {
					PageAssert.fail("User is not in 'ADMIN' mode");
				}
			}else{
				PageAssert.fail("View Profile Link is not Visible");
			}
			getAction().click(SWITCH_ROLE_POPUP);
		}

		return this;
	}
	public HomePage verifyDisplayAdmin(String roleName){
		Logger.log("Verifying the display for admin", TestStepType.STEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		getAction().click(VIEW_PROFILE_LINK);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(AGENT_ACTIVE_ROLE).waitWithoutException(10);
		SoftAssert.checkElementAndContinueOnFailure(MY_SETTINGS, "Check whether My Settings is displayed in profile", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(MY_QUEUELIST, "Check whether My queuelist is displayed in profile", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(MY_INFORMATION, "Check whether My information is displayed in profile", CheckLocatorFor.isVisible);
		boolean status=(Boolean) getContext().get("Check");
		if(status==false){
		AjaxCondition.forElementVisible(AGENT_ACTIVE_ROLE_OPTION.format(roleName)).waitForResponse();
		SoftAssert.checkElementAndContinueOnFailure(AGENT_ACTIVE_ROLE_OPTION.format(roleName), "check whether the role:"+roleName+" is displayed in subscribed roles", CheckLocatorFor.isVisible);
		}
		else if(status==true){
			SoftAssert.checkElementAndContinueOnFailure(AGENT_ACTIVE_ROLE_OPTION.format(roleName), "check whether the role:"+roleName+" is not displayed in subscribed roles", CheckLocatorFor.isNotVisible);
		}
		
		return this;
		
	}
	public HomePage VerifyDeliveryAgent() {
		getAction().waitFor(1000);
		closeWarningPopupWindow();
		Logger.log("Verify user is in 'Delivery agent' mode", TestStepType.STEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		if(getAction().getText(VIEW_PROFILE_LINK).split("\\|")[1].trim().equals("DELIVERY AGENT")){
			return this;
		}
		getAction().click(VIEW_PROFILE_LINK);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(AGENT_ACTIVE_ROLE).waitWithoutException(10);
		if (getAction().isVisible(AGENT_ACTIVE_ROLE) && getAction().isSelected(DELIVERYAGENT_INACTIVE)) {
			getAction().waitFor(1000);
			Logger.log("User  is in 'Delivery agent' mode", TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(DELIVERYAGENT_INACTIVE).waitForResponse(10);
			getAction().click(DELIVERYAGENT_INACTIVE);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse(10);
			Logger.log("Accepting role change confirmation", TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			getAction().click(SAVE_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(10);
			getAction().click(OK_BUTTON);
			Logger.log("Click on close button in profile",TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(CLOSE_PROFILE_MODAL).waitForResponse(10);
			getAction().click(CLOSE_PROFILE_MODAL);

		} else {
			AjaxCondition.forElementVisible(USER_ROLE).waitForResponse();
			Logger.log("Currently user is in " + getSelectedOption(USER_ROLE), TestStepType.SUBSTEP);
			Logger.log("Changing to  'delivery agent' mode", TestStepType.SUBSTEP);
			getAction().waitFor(1000);
			getAction().click(DELIVERYAGENT_INACTIVE);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse(10);
			Logger.log("Accepting role change confirmation", TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			getAction().click(SAVE_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(10);
			getAction().click(OK_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(SWITCH_ROLE_POPUP).waitForResponse(10);
			getAction().click(SWITCH_ROLE_POPUP);
			getAction().waitFor(1000);
			if(AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse(30)){
				getAction().click(VIEW_PROFILE_LINK);
				getAction().waitFor(5000);
				if (getAction().isSelected(DELIVERYAGENT_INACTIVE)) {
					getAction().waitFor(1000);
					Logger.log("User  is changed to 'Delivery agent' mode", TestStepType.STEP);
				} else {
					PageAssert.fail("User is not in 'Delivery agent' mode");
				}
			}else{
				PageAssert.fail("View Profile Link is not Visible");
			}
			getAction().click(SWITCH_ROLE_POPUP);
			getAction().waitFor(1000);
		}

		return this;
	}

	public HomePage VerifyDeliveryOfflineAgent() {
		getAction().waitFor(1000);
		closeWarningPopupWindow();
		Logger.log("Verify user is in 'Delivery offline agent' mode", TestStepType.STEP);
		AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse();
		if(getAction().getText(VIEW_PROFILE_LINK).split("\\|")[1].trim().equals("DELIVERY OFFLINE AGENT")){
			return this;
		}
		getAction().click(VIEW_PROFILE_LINK);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(AGENT_ACTIVE_ROLE).waitWithoutException(10);
		if (getAction().isVisible(AGENT_ACTIVE_ROLE) && getAction().isSelected(DELIVERY_OFFLINE_AGENT_INACTIVE)) {
			getAction().waitFor(1000);
			Logger.log("User  is in 'Delivery offline agent' mode", TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(DELIVERY_OFFLINE_AGENT_INACTIVE).waitForResponse(10);
			getAction().click(DELIVERY_OFFLINE_AGENT_INACTIVE);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse(10);
			Logger.log("Accepting role change confirmation", TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			getAction().click(SAVE_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(10);
			getAction().click(OK_BUTTON);
			Logger.log("Click on close button in profile",TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(CLOSE_PROFILE_MODAL).waitForResponse(10);
			getAction().click(CLOSE_PROFILE_MODAL);

		} else {
			AjaxCondition.forElementVisible(USER_ROLE).waitForResponse();
			Logger.log("Currently user is in " + getSelectedOption(USER_ROLE), TestStepType.SUBSTEP);
			Logger.log("Changing to  'delivery offline agent' mode", TestStepType.SUBSTEP);
			getAction().waitFor(1000);
			getAction().click(DELIVERY_OFFLINE_AGENT_INACTIVE);
			getAction().waitFor(2000);
			AjaxCondition.forElementVisible(SAVE_BUTTON).waitForResponse(10);
			Logger.log("Accepting role change confirmation", TestStepType.SUBSTEP);
			getAction().waitFor(2000);
			getAction().click(SAVE_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(OK_BUTTON).waitForResponse(10);
			getAction().click(OK_BUTTON);
			getAction().waitFor(1000);
			AjaxCondition.forElementVisible(SWITCH_ROLE_POPUP).waitForResponse(10);
			getAction().click(SWITCH_ROLE_POPUP);
			getAction().waitFor(1000);
			if(AjaxCondition.forElementVisible(VIEW_PROFILE_LINK).waitForResponse(30)){
				getAction().click(VIEW_PROFILE_LINK);
				getAction().waitFor(5000);
				if (getAction().isSelected(DELIVERY_OFFLINE_AGENT_INACTIVE)) {
					getAction().waitFor(1000);
					Logger.log("User  is changed to 'Delivery offline agent' mode", TestStepType.STEP);
				} else {
					PageAssert.fail("User is not in 'Delivery offline agent' mode");
				}
			}else{
				PageAssert.fail("View Profile Link is not Visible");
			}
			getAction().click(SWITCH_ROLE_POPUP);
			getAction().waitFor(1000);
		}

		return this;
	}
	public HomePage searchCustomer(){
		Logger.log("Click on Menu button" , TestStepType.STEP);
		AjaxCondition.forElementVisible(MENU_BUTTON).waitForResponse();
		getAction().click(MENU_BUTTON);
		Logger.log("Select Search Customer from Search Dropdown Box" , TestStepType.STEP);
		AjaxCondition.forElementVisible(SEARCH_CUSTOMER).waitForResponse();
		getAction().click(SEARCH_CUSTOMER);
		getAction().waitFor(1000);
		getAction().findElement(ORDER_NUMBER_FIELD).clear();
		getAction().findElement(SALESCHECK_FIELD).clear();
		getAction().findElement(EMAIL_FIELD).clear();
		getAction().findElement(FIRSTNAME_FIELD).clear();
		getAction().findElement(LASTNAME_FIELD).clear();
		getAction().findElement(PHONE_FIELD).clear();
		return this;
	}

	public HomePage superAdminSearch(){
		AjaxCondition.forElementVisible(MENU_BUTTON).waitForResponse();
		Logger.log("Search Dropdown Button is Visible for Super Admin" , TestStepType.STEP);
		getAction().click(MENU_BUTTON);
		Logger.log("Click Dropdown Button" , TestStepType.STEP);
		getAction().waitFor(1000);
		getAction().focus(SEARCH_CUSTOMER);
		getAction().click(SEARCH_CUSTOMER);
		Logger.log("Super Admin Select Search Customer from Search Dropdown Box" , TestStepType.STEP);
		getAction().waitFor(1000);
		return this;

	}

	public HomePage  selectDateFrom(){
		getAction().waitFor(1000);
		Logger.log("Click on from date button " , TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(DATEFROM_FIELD).waitForResponse();
		getAction().click(DATEFROM_FIELD);
		getAction().waitFor(1000);
		AjaxCondition.forElementVisible(MONFROM_RIGHTCLICK).waitForResponse();
		getAction().click(MONFROM_RIGHTCLICK);
		getAction().waitFor(1000);
		AjaxCondition.forElementVisible(SELECTED_FROM_DATE).waitForResponse();
		getAction().click(SELECTED_FROM_DATE);
		getAction().waitFor(1000);
		Logger.log("From Date Selected is "+ getAction().getValue(DATEFROM_FIELD), TestStepType.DATA_CAPTURE);
		return this;

	}

	//For Delivery Flow
	public HomePage  searchDOSOrdersByDate(){
		selectOrderTab();
		getAction().waitFor(1000);
		Logger.log("Click on from date button " , TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(DOS_DATEFROM_FIELD).waitForResponse();
		getAction().click(DOS_DATEFROM_FIELD);
		getAction().waitFor(1000);
		AjaxCondition.forElementVisible(SELECTED_DOS_DATE).waitForResponse();
		getAction().click(SELECTED_DOS_DATE);
		getAction().waitFor(1000);
		Logger.log("Delivery Date Selected is "+ getAction().getValue(DOS_DATEFROM_FIELD), TestStepType.DATA_CAPTURE);
		Logger.log("Click on Search Button", TestStepType.STEP);
		AjaxCondition.forElementVisible(SEARCH_DELIVERY_BUTTON).waitForResponse(3000);
		getAction().focus(SEARCH_DELIVERY_BUTTON);
		getAction().click(SEARCH_DELIVERY_BUTTON);
		return this;
	}

	public HomePage selectDateTo(){
		getAction().waitFor(1000);
		Logger.log("Select A To Date " , TestStepType.DATA_CAPTURE);
		AjaxCondition.forElementVisible(DATETO_FIELD).waitForResponse();
		getAction().click(DATETO_FIELD);
		getAction().waitFor(1000);
		AjaxCondition.forElementVisible(MONTO_LEFTCLICK).waitForResponse();
		getAction().click(MONTO_LEFTCLICK);
		Logger.log("Select Last Month " , TestStepType.DATA_CAPTURE);
		getAction().waitFor(1000);
		AjaxCondition.forElementVisible(SELECTED_TO_DATE).waitForResponse();
		getAction().click(SELECTED_TO_DATE);
		Logger.log("To Date Selected is "+ getAction().getValue(DATETO_FIELD), TestStepType.DATA_CAPTURE);
		return this;
	}

	public boolean verifyDatePeriod(){
		ArrayList<String> OrderDate = new ArrayList<String>();
		Boolean isBetweenSelectedDates = null;
		try{
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
			Date dateFrom = format.parse(getAction().getValue(DATEFROM_FIELD));
			Date dateTo = format.parse(getAction().getValue(DATETO_FIELD));
			AjaxCondition.forElementVisible(ORDER_RESULT_TABLE).waitForResponse();
			//while(getAction().findElement(ORDER_RESULT_FOOT_NEXT).isEnabled()){
			if(getAction().isVisible(ORDER_RESULT_DATE_R1)){
				OrderDate.add(getAction().getText(ORDER_RESULT_DATE_R1));
				if(getAction().isVisible(ORDER_RESULT_DATE_R2)){
					OrderDate.add(getAction().getText(ORDER_RESULT_DATE_R2));	
					if(getAction().isVisible(ORDER_RESULT_DATE_R3)){
						OrderDate.add(getAction().getText(ORDER_RESULT_DATE_R3));
						if(getAction().isVisible(ORDER_RESULT_DATE_R4)){
							OrderDate.add(getAction().getText(ORDER_RESULT_DATE_R4));
							if(getAction().isVisible(ORDER_RESULT_DATE_R5)){
								OrderDate.add(getAction().getText(ORDER_RESULT_DATE_R5));
								Logger.log("Order Result is Done For This Page", TestStepType.STEP);
							}
						}
					}
				}
			}else{
				Logger.log("Order Result is 0", TestStepType.STEP);
			}
			//	getAction().click(ORDER_RESULT_FOOT_NEXT);
			//	highlight(ORDER_RESULT_FOOT_NEXT);
			//}
			if(OrderDate.size() != 0){
				for(int i=0; i < OrderDate.size();i++){
					Date ResultDate = format.parse(OrderDate.get(i));
					isBetweenSelectedDates = ResultDate.compareTo(dateFrom) >= 0 && ResultDate.compareTo(dateTo) <= 0;
					//getAction().click(ORDER_RESULT_FIRST_PAGE);
				}
				Logger.log("Verify the Search Result " + OrderDate.size()+" Orders On this Page Between "+getAction().getValue(DATEFROM_FIELD)
						+" and "+getAction().getValue(DATETO_FIELD) + " is "+isBetweenSelectedDates, TestStepType.STEP);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return isBetweenSelectedDates;

	}

	public HomePage searchByOrderId(String orderId) {
		setData("orderId", orderId);
		getAction().waitFor(2000);
		Logger.log("Search using order id ", TestStepType.STEP);
		if( !AjaxCondition.forElementVisible(ORDER_NUMBER_FIELD).waitWithoutException(1)){
			Logger.log("Click on order tab", TestStepType.STEP);
			AjaxCondition.forElementVisible(ORDER_TAB).waitForResponse();
			getAction().click(ORDER_TAB);
			getAction().waitFor(1000);
		}
		Logger.log("Enter Order Id :" + orderId, TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(ORDER_NUMBER_FIELD).waitForResponse();
		getAction().click(ORDER_NUMBER_FIELD);
		getAction().type(ORDER_NUMBER_FIELD, orderId);
		getAction().waitFor(1000);
		if(getAction().getText(ORDER_NUMBER_FIELD)==""){
			Logger.log("OrderID was not entered " , TestStepType.STEP);
		}else{
			Logger.log("Click on Search Button", TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse(3);
			getAction().focus(SEARCH_BUTTON);
			getAction().click(SEARCH_BUTTON);
			getAction().waitFor(2000);
			if(NO_RESULT.equals("Total Found: 0")){
				Logger.log("Invalid Order ID - 0 ", TestStepType.STEP);
			}
		}

		if(!AjaxCondition.forElementVisible(OrderDetailsPage.ORDERDETAILS_ACTION_GO_BUTTON).waitWithoutException(5)){
			if(AjaxCondition.forElementVisible(SEARCH_BUTTON).waitWithoutException(2)){
				scrollDown();
				getAction().waitFor(1000);
				Logger.log("Click on Search Button1", TestStepType.STEP);
				clickJ(SEARCH_BUTTON);
			}
		}
		if(!AjaxCondition.forElementVisible(OrderDetailsPage.ORDERDETAILS_ACTION_GO_BUTTON).waitWithoutException(5)){
			if(AjaxCondition.forElementVisible(SEARCH_BUTTON).waitWithoutException(2)){
				scrollDown();
				getAction().waitFor(1000);
				Logger.log("Click on Search Button2", TestStepType.STEP);
				clickJ(SEARCH_BUTTON);
			}
		}

		return this;
	}
	
	public HomePage searchBySubOrderId(String subOrderId) {
		getAction().waitFor(2000);
		Logger.log("Search using order id ", TestStepType.STEP);
		if( !AjaxCondition.forElementVisible(SUB_ORDER_NUMBER_FIELD).waitWithoutException(1)){
			Logger.log("Click on order tab", TestStepType.STEP);
			AjaxCondition.forElementVisible(ORDER_TAB).waitForResponse();
			getAction().click(ORDER_TAB);
			getAction().waitFor(1000);
		}
		Logger.log("Enter Sub Order Id :" + subOrderId, TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(SUB_ORDER_NUMBER_FIELD).waitForResponse();
		getAction().click(SUB_ORDER_NUMBER_FIELD);
		getAction().type(SUB_ORDER_NUMBER_FIELD, subOrderId);
		Logger.log("Click on Search Button", TestStepType.STEP);
		AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse(3);
		getAction().focus(SEARCH_BUTTON);
		getAction().click(SEARCH_BUTTON);
		return this;
	}
	
	public HomePage searchByLayawayContractID(String contractId){
		Logger.log("Switch to Order Tab",TestStepType.STEP);
		selectOrderTab();
		Logger.log("Select Layaway radio button",TestStepType.STEP);
		AjaxCondition.forElementVisible(SEARCH_LAYAWAY_RADIO_BUTTON).waitWithoutException(5);
		getAction().click(SEARCH_LAYAWAY_RADIO_BUTTON);
		Logger.log("Enter Layaway Contract ID",TestStepType.STEP);
		getAction().type(LAYAWAY_CONTRACT_ID_TEXTBOX, contractId);
		Logger.log("Click Search button",TestStepType.STEP);
		getAction().waitFor(1000);
		getAction().click(LAYAWAY_SEARCH_BUTTON);
		return this;
	}
	
	public HomePage searchByLayawayphoneNumber(String phNumber){
		Logger.log("Switch to Order Tab",TestStepType.STEP);
		selectOrderTab();
		Logger.log("Select Layaway radio button",TestStepType.STEP);
		AjaxCondition.forElementVisible(SEARCH_LAYAWAY_RADIO_BUTTON).waitWithoutException(205);
		getAction().click(SEARCH_LAYAWAY_RADIO_BUTTON);
		Logger.log("Enter Phone Number",TestStepType.STEP);
		getAction().waitFor(1000);
		System.out.println("-------------------------------------------------------------");
		getAction().type(LAYAWAY_PHONE_NUMBER_TEXTBOX, phNumber);
		Logger.log("Click Search button",TestStepType.STEP);
		getAction().waitFor(1000);
		getAction().click(LAYAWAY_SEARCH_BUTTON);
		return this;
	}
	public HomePage selectLayawayOrderFromSearchResults(int index){
		Logger.log("Click on search result number " + index);
		AjaxCondition.forElementVisible(LAYAWAY_SEARCH_RESULT.format(index)).waitWithoutException(5);
		getAction().waitFor(2000);
		getAction().click(LAYAWAY_SEARCH_RESULT.format(index));
		return this;
	}
	
	public HomePage searchByDeliveryOrderId(String dosorderID, String dosunitID) {
		setData("dosorderID", dosorderID);
		selectOrderTab();
		Logger.log("Search using delivery order : "+dosorderID+" "+dosunitID, TestStepType.STEP);
		AjaxCondition.forElementVisible(DOS_ORDER_NUMBER_FIELD).waitForResponse();
		Logger.log("DOS Order Number : " +dosorderID, TestStepType.DATA_CAPTURE);
		getAction().click(DOS_ORDER_NUMBER_FIELD);
		getAction().type(DOS_ORDER_NUMBER_FIELD, dosorderID);
		Logger.log("DOS Unit Number : " +dosunitID, TestStepType.DATA_CAPTURE);
		AjaxCondition.forElementVisible(DOS_UNIT_NUMBER_FIELD ).waitForResponse();
		getAction().type(DOS_UNIT_NUMBER_FIELD , dosunitID);

		if(getAction().getText(DOS_ORDER_NUMBER_FIELD)==""||getAction().getText(DOS_UNIT_NUMBER_FIELD )==""){
			Logger.log("DOS Order search is empty " , TestStepType.STEP);
		}else{
			Logger.log("Click on search button",TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_DELIVERY_BUTTON ).waitForResponse(3000);
			getAction().focus(SEARCH_DELIVERY_BUTTON );
			getAction().click(SEARCH_DELIVERY_BUTTON );
		}
		return this;		

	}	


	public HomePage searchByCaseId() {
		return searchByCaseId(getDataString("caseId"));
	}
	public HomePage searchSYWLinkStatus(String member) {
		Logger.log("Searching a member to see whether the membership status is linked", TestStepType.STEP);
		AjaxCondition.forElementVisible(EMAIL_FIELD).waitForResponse();
		getAction().type(EMAIL_FIELD, member);
		AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse();
		getAction().click(SEARCH_BUTTON);
		AjaxCondition.forElementVisible(MEMBER_STATUS_RESULT).waitForResponse();
		AjaxCondition.forElementVisible(MEMBER_STATUS).waitForResponse();
		Logger.log("Membership status is: "+getAction().getText(MEMBER_STATUS), TestStepType.VERIFICATION_PASSED);

		return this;
	}
	public HomePage searchByCaseId(String caseId) {
		getAction().waitFor(2000);
		Logger.log("Search using case id ", TestStepType.STEP);
		if( !AjaxCondition.forElementVisible(CASE_NUMBER_FIELD).waitWithoutException(1)){
			Logger.log("Click on Case tab", TestStepType.STEP);
			AjaxCondition.forElementVisible(CASE_TAB).waitForResponse();
			getAction().click(CASE_TAB);
		}
		Logger.log("Enter Case Id :" + caseId, TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(CASE_NUMBER_FIELD).waitForResponse();
		getAction().click(CASE_NUMBER_FIELD);
		getAction().type(CASE_NUMBER_FIELD, caseId);
		getAction().waitFor(1000);
		if(getAction().getText(CASE_NUMBER_FIELD)==""){
			Logger.log("caseId was not entered " , TestStepType.STEP);
		}else{
			Logger.log("Click on Search Button", TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_CASE_BUTTON).waitForResponse(3000);
			getAction().focus(SEARCH_CASE_BUTTON);
			getAction().click(SEARCH_CASE_BUTTON);
			getAction().waitFor(2000);
			if(NO_RESULT.equals("Total Found: 0")){
				Logger.log("Invalid Case ID - 0 ", TestStepType.STEP);
			}
		}

		return this;
	}



	public HomePage searchByName(String firstName, String lastName) {
		selectOrderTab();
		Logger.log("Search using Name : "+firstName+" "+lastName, TestStepType.STEP);
		AjaxCondition.forElementVisible(FIRSTNAME_FIELD).waitForResponse();
		Logger.log("First Name : " +firstName, TestStepType.DATA_CAPTURE);
		getAction().click(FIRSTNAME_FIELD);
		getAction().type(FIRSTNAME_FIELD, firstName);
		Logger.log("Last Name : " +lastName, TestStepType.DATA_CAPTURE);
		AjaxCondition.forElementVisible(LASTNAME_FIELD).waitForResponse();
		getAction().type(LASTNAME_FIELD, lastName);

		if(getAction().getText(FIRSTNAME_FIELD)==""||getAction().getText(LASTNAME_FIELD)==""){
			Logger.log("Customer Name is empty " , TestStepType.STEP);
		}else{
			Logger.log("Click on search button",TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse(3000);
			getAction().focus(SEARCH_BUTTON);
			getAction().click(SEARCH_BUTTON);
		}
		return this;
	}

	public HomePage searchByNameForSpecPeriod(String firstName, String lastName) {
		getAction().waitFor(2000);
		Logger.log("Search using Name : "+firstName+" "+lastName, TestStepType.STEP);
		Logger.log("First Name : " +firstName, TestStepType.DATA_CAPTURE);
		AjaxCondition.forElementVisible(FIRSTNAME_FIELD).waitForResponse();
		getAction().focus(FIRSTNAME_FIELD);
		getAction().click(FIRSTNAME_FIELD);
		getAction().type(FIRSTNAME_FIELD, firstName);
		Logger.log("Last Name : " +lastName, TestStepType.DATA_CAPTURE);
		AjaxCondition.forElementVisible(LASTNAME_FIELD).waitForResponse();
		getAction().type(LASTNAME_FIELD, lastName);
		selectDateFrom();
		selectDateTo();
		if(getAction().getText(FIRSTNAME_FIELD)==""||getAction().getText(LASTNAME_FIELD)==""){
			Logger.log("Customer Name has Empty " , TestStepType.STEP);
		}else{
			Logger.log("Click on Search Button", TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse(3000);
			getAction().focus(SEARCH_BUTTON);
			getAction().click(SEARCH_BUTTON);
		} 
		return this;
	}

	public HomePage searchBySalesCheck(String salesCheck, String store) {
		selectOrderTab();
		getAction().waitFor(2000);
		Logger.log("Search using Sales Check # : " + salesCheck, TestStepType.STEP);
		AjaxCondition.forElementVisible(SALESCHECK_FIELD).waitForResponse();
		getAction().click(SALESCHECK_FIELD);
		getAction().type(SALESCHECK_FIELD, salesCheck);
		String storeName = null;
		if(store.equals("10153")||store.equals("40153")){storeName = "Sears";}
		else if(store.equals("30151")||store.equals("30153")){storeName = "KMart";}
		else if(store.equals("10171")){storeName = "Outlet";}
		else if(store.equals("10175")){storeName = "My Gofer 3";}
		else if(store.equals("10199")){storeName = "B2BGC";}
		else if(store.equals("10165")){storeName = "Sears PR";}
		else if(store.equals("10191")){storeName = "Sears SCO";}
		else if(store.equals("10195")){storeName = "Affinity";}
		else if(store.equals("10156")){storeName = "TGI";}
		else if(store.equals("10181")){storeName = "Delver";}
		else if(store.equals("10197")){storeName = "Skymall";}
		else if(store.equals("9305")){storeName = "ShopYourWay Mall";}
		Logger.log("Select store: "+storeName, TestStepType.DATA_CAPTURE);
		AjaxCondition.forElementVisible(STORE_FIELD).waitForResponse();	
		getAction().selectByVisibleText(STORE_FIELD, storeName);
		getAction().waitFor(1000);
		if(getAction().getText(SALESCHECK_FIELD)==""){
			Logger.log("Sales Check # is Empty " , TestStepType.STEP);
		}
		/**
		 * TODO - The below else part is repeating in multiple places. Move to a function
		 */
		else{
			Logger.log("Click on Search Button", TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse(3000);
			getAction().focus(SEARCH_BUTTON);
			getAction().click(SEARCH_BUTTON);
		}
		return this;
	}

	public HomePage searchByPhone(String phoneNumber){
		selectOrderTab();
		getAction().waitFor(2000);
		Logger.log("Search using Phone Number # : " + phoneNumber, TestStepType.STEP);
		AjaxCondition.forElementVisible(PHONE_FIELD).waitForResponse();
		Logger.log("Enter Phone Number # : " +phoneNumber, TestStepType.DATA_CAPTURE);
		getAction().click(PHONE_FIELD);
		getAction().type(PHONE_FIELD, phoneNumber);
		if(getAction().getText(PHONE_FIELD)==""){
			Logger.log("Phone Number is Empty " , TestStepType.STEP);
		}else{
			Logger.log("Click on Search Button", TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse(3000);
			getAction().focus(SEARCH_BUTTON);
			getAction().click(SEARCH_BUTTON);
		}
		return this;
	}

	//For Delivery FLow
	public HomePage searchByDOSPhone(String dosphoneNumber){
		selectOrderTab();
		getAction().waitFor(2000);
		Logger.log("Search using Phone Number # : " + dosphoneNumber, TestStepType.STEP);
		AjaxCondition.forElementVisible(DOS_PHONE_FIELD).waitForResponse();
		Logger.log("Enter Phone Number # : " +dosphoneNumber, TestStepType.DATA_CAPTURE);
		getAction().click(DOS_PHONE_FIELD);
		getAction().type(DOS_PHONE_FIELD, dosphoneNumber);
		if(getAction().getText(DOS_PHONE_FIELD)==""){
			Logger.log("Phone Number is Empty " , TestStepType.STEP);
		}else{
			Logger.log("Click on Search Button", TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_DELIVERY_BUTTON).waitForResponse(3000);
			getAction().focus(SEARCH_DELIVERY_BUTTON);
			getAction().click(SEARCH_DELIVERY_BUTTON);
		}
		return this;
	}

	//For Delivery FLow
	public HomePage searchByDOSSalesCheck(String dosSalesCheckNumber){
		selectOrderTab();
		getAction().waitFor(2000);
		Logger.log("Search using Sales Check Number # : " + dosSalesCheckNumber, TestStepType.STEP);
		AjaxCondition.forElementVisible(DOS_PHONE_FIELD).waitForResponse();
		Logger.log("Enter Sales Check Number # : " +dosSalesCheckNumber, TestStepType.DATA_CAPTURE);
		getAction().click(DOS_SALESCHECK_FIELD);
		getAction().type(DOS_SALESCHECK_FIELD, dosSalesCheckNumber);
		if(getAction().getText(DOS_SALESCHECK_FIELD)==""){
			Logger.log("Sales check number is empty " , TestStepType.STEP);
		}else{
			Logger.log("Click on Search Button", TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_DELIVERY_BUTTON).waitForResponse(3000);
			getAction().focus(SEARCH_DELIVERY_BUTTON);
			getAction().click(SEARCH_DELIVERY_BUTTON);
		}
		return this;
	}	


	public HomePage searchByPhoneForSpecPeriod(String phoneNumber) {
		getAction().waitFor(2000);
		Logger.log("Search using Phone Number # : " + phoneNumber, TestStepType.STEP);
		AjaxCondition.forElementVisible(PHONE_FIELD).waitForResponse();
		Logger.log("Enter Phone Number # : " +phoneNumber, TestStepType.SUBSTEP);
		getAction().click(PHONE_FIELD);
		getAction().type(PHONE_FIELD, phoneNumber);

		selectDateFrom();
		selectDateTo();

		if(getAction().getText(PHONE_FIELD)==""){
			Logger.log("Phone Number is Empty " , TestStepType.STEP);
		}else{
			Logger.log("Click on Search Button", TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse(3000);
			getAction().focus(SEARCH_BUTTON);
			getAction().click(SEARCH_BUTTON);
			getAction().waitFor(2000);
		}
		return this;
	}

	public HomePage searchByEmail(String email){
		selectOrderTab();
		getAction().waitFor(2000);
		Logger.log("Search using E-mail ", TestStepType.STEP);
		AjaxCondition.forElementVisible(EMAIL_FIELD).waitForResponse();
		Logger.log("Enter E-mail Address : " +email, TestStepType.DATA_CAPTURE);
		getAction().click(EMAIL_FIELD);
		getAction().type(EMAIL_FIELD, email);
		getAction().waitFor(1000);
		if(getAction().getText(EMAIL_FIELD)==""){
			Logger.log("Email is Empty " , TestStepType.STEP);
		}else{
			Logger.log("Click on Search Button", TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse(3000);
			getAction().focus(SEARCH_BUTTON);
			getAction().click(SEARCH_BUTTON);
			getAction().waitFor(2000);
		}
		return this;
	}
	public HomePage navigateToTab(String tabName) {

		Logger.log("Navigating to tab:"+tabName , TestStepType.STEP);

		AjaxCondition.forElementVisible(TAB_NAME.format(tabName)).waitForResponse();
		getAction().click(TAB_NAME.format(tabName));
		getAction().waitFor(2000);

		return this;
	}

	public HomePage searchandVerifyCustomer(String customer) {

		Logger.log("Searching for customer:"+customer , TestStepType.STEP);
		AjaxCondition.forElementVisible(MEMBER_PRODUCT_ENTRY).waitForResponse();
		getAction().type(MEMBER_PRODUCT_ENTRY, customer);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(SEARCH).waitForResponse();
		getAction().click(SEARCH);
		getAction().waitFor(4000);
		AjaxCondition.forElementVisible(MEMBER_RESULTS).waitForResponse();
		Logger.log("Verified that customer results are shown after customer search" , TestStepType.VERIFICATION_PASSED);
		getAction().click(MEMBER_RESULTS);		
		SoftAssert.checkElementAndContinueOnFailure(MEMBER_NUMBER, "Verify Member number is displayed in member details page", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(UPDATE_PROFILE, "Verify update profile is displayed in member details page", CheckLocatorFor.isVisible);



		return this;
	}

	public HomePage searchandVerifyProductInShop(String product) {

		Logger.log("Searching for product:"+product , TestStepType.STEP);

		AjaxCondition.forElementVisible(SEARS_RADIO).waitForResponse();
		getAction().click(SEARS_RADIO);
		AjaxCondition.forElementVisible(MEMBER_PRODUCT_ENTRY).waitForResponse();
		getAction().type(MEMBER_PRODUCT_ENTRY, product);
		AjaxCondition.forElementVisible(SEARCH).waitForResponse();
		getAction().waitFor(2000);
		getAction().click(SEARCH);	
		getAction().waitFor(8000);
		SoftAssert.checkElementAndContinueOnFailure(SHOP_CART, "Verify cart is displayeD on shop page", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(SEARCH_BOX, "Verify  that search box is visible in shop page", CheckLocatorFor.isVisible);



		return this;
	}
	
	public HomePage searchAndVerifyProduct(String store, String product){
		if(store.equalsIgnoreCase("Sears")){
			AjaxCondition.forElementVisible(SEARS_RADIO).waitForResponse();
			getAction().click(SEARS_RADIO);
		}else if (store.equalsIgnoreCase("Kmart")) {
			AjaxCondition.forElementVisible(KMART_RADIO).waitForResponse();
			getAction().click(KMART_RADIO);
		}
		AjaxCondition.forElementVisible(MEMBER_PRODUCT_ENTRY).waitForResponse();
		getAction().type(MEMBER_PRODUCT_ENTRY, product);
		AjaxCondition.forElementVisible(SEARCH).waitForResponse();
		getAction().waitFor(2000);
		getAction().click(SEARCH);	
		getAction().waitFor(8000);
		SoftAssert.checkElementAndContinueOnFailure(SHOP_CART, "Verify cart is displayed on shop page", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(SEARCH_BOX, "Verify  that search box is visible in shop page", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(SHOP_MENU_BAR,"Verify  that Category Bar is displayed",CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(SHOP_CATEGORY_SECTION,"Verify category page is displayed",CheckLocatorFor.isVisible);
		return this;
		
	}
	public HomePage searchByEmailForSpecPeriod(String email) {
		getAction().waitFor(2000);
		Logger.log("Search using E-mail for a specific period" , TestStepType.STEP);
		AjaxCondition.forElementVisible(EMAIL_FIELD).waitForResponse();
		Logger.log("Enter email id: " +email, TestStepType.DATA_CAPTURE);
		getAction().click(EMAIL_FIELD);
		getAction().type(EMAIL_FIELD, email);
		getAction().waitFor(1000);
		selectDateFrom();
		selectDateTo();
		if(getAction().getText(EMAIL_FIELD)==""){
			Logger.log("Email is Empty " , TestStepType.STEP);
		}else{
			Logger.log("Click on Search Button", TestStepType.STEP);
			AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse(3000);
			getAction().focus(SEARCH_BUTTON);
			getAction().click(SEARCH_BUTTON);
			getAction().waitFor(2000);
		}
		return this;
	}


	public HomePage selectOrderInMyRecentInteractions(int order) throws Exception {
		getAction().waitFor(2000);
		Logger.log("Verify if 'results' are visible", TestStepType.VERIFICATION_SUBSTEP);
		getAction().waitFor(5000);
		if(getAction().isVisible(NO_RESULT)){
			if(getAction().getText(NO_RESULT).contains("Total Found: 0")){
				PageAssert.fail("No results were displayed for order - "+order);
			}
		}else{
			AjaxCondition.forElementVisible(SEARCH_RESULTS).waitForResponse(10);
			Logger.log("Select order "+order+" in 'My Recent Interactions'", TestStepType.STEP);
			AjaxCondition.forElementVisible(ORDER_ID_IN_MY_RECENT_INTERACTIONS.format(order)).waitForResponse(10);
			getAction().click(ORDER_ID_IN_MY_RECENT_INTERACTIONS.format(order));
		}
		getAction().waitFor(3000);
		return this;
	}

	//For Delivery Flow
	public HomePage selectOrderInMyRecentDeliveryInteractions(int order) throws Exception {
		getAction().waitFor(2000);
		Logger.log("Verify if 'results' are visible", TestStepType.VERIFICATION_SUBSTEP);
		getAction().waitFor(5000);
		if(getAction().isVisible(NO_RESULT)){
			if(getAction().getText(NO_RESULT).contains("Total Found: 0")){
				PageAssert.fail("No results were displayed for order - "+order);
			}
		}else{
			//AjaxCondition.forElementVisible(SEARCH_RESULTS).waitForResponse(2000);
			Logger.log("Select order "+order+" in 'My Recent Interactions'", TestStepType.STEP);
			AjaxCondition.forElementVisible(ORDERS_IN_MY_RECENT_DELIVERY_INTERACTIONS.format(order)).waitForResponse(10);
			getAction().click(ORDERS_IN_MY_RECENT_DELIVERY_INTERACTIONS.format(order));
		}
		getAction().waitFor(3000);
		return this;
	}	
	//Delivery Script

	public HomePage searchByPhoneForDeliveryUser(String phoneNumber) {
		Logger.log("Search using Phone Number # : " + phoneNumber, TestStepType.STEP);
		selectOrderTab();
		// this.factory.homePage().closeWarningPopupWindow();
		Logger.log("Search using Phone Number ", TestStepType.STEP);
		Logger.log("Enter Phone Number # : " +phoneNumber, TestStepType.DATA_CAPTURE);
		AjaxCondition.forElementVisible(DELPHONENUMBER_SEARCH).waitForResponse();
		getAction().click(DELPHONENUMBER_SEARCH);
		getAction().type(DELPHONENUMBER_SEARCH, phoneNumber);
		Logger.log("Click on Search Button", TestStepType.STEP);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(DELSEARCH_BUTTON).waitForResponse(3000);
		getAction().focus(DELSEARCH_BUTTON);
		getAction().click(DELSEARCH_BUTTON);
		getAction().waitFor(2000);
		return this;
	}

	/**
	 * Click on the order tab in home page to search for orders
	 * @return
	 */
	private HomePage selectOrderTab(){
		if(!AjaxCondition.forElementVisible(DELPHONENUMBER_SEARCH).waitWithoutException(2)){
			Logger.log("Click on order tab",TestStepType.STEP);
			getAction().click(ORDER_TAB);
			getAction().waitFor(1000);
		}
		return this;

	}

	public HomePage chooseOpenHDOrders(){

		Logger.log("Click on any open HD orders",TestStepType.STEP);	
		if(AjaxCondition.forElementVisible(OPEN_HD_ORDERS).waitWithoutException(8)){
		getAction().click(OPEN_HD_ORDERS);
		getAction().waitFor(1000);	
		}
		else{
			AjaxCondition.forElementVisible(MOVE_RIGHT_RESULTS_END).waitForResponse();
			getAction().click(MOVE_RIGHT_RESULTS_END);
			AjaxCondition.forElementVisible(ACTIVE_PAGE).waitForResponse();
			String i=getAction().getText(ACTIVE_PAGE);
			int count=Integer.parseInt(i);
			AjaxCondition.forElementVisible(MOVE_LEFT_RESULTS_END).waitForResponse();
			getAction().click(MOVE_LEFT_RESULTS_END);
			for(int c=0; c<count;c++){
				getAction().click(MOVE_RIGHT_RESULTS);
				if(AjaxCondition.forElementVisible(OPEN_HD_ORDERS).waitWithoutException(8)){
					getAction().click(OPEN_HD_ORDERS);
					break;
					}			
			}			
			
		}
		return this;

	}


	public HomePage chooseReleasedHDOrders(){

		Logger.log("Click on any RELEASED HD orders",TestStepType.STEP);
		int num=0;
		if(AjaxCondition.forElementVisible(RELEASED_HD_ORDERS).waitWithoutException(8)){
		getAction().click(RELEASED_HD_ORDERS);
		getAction().waitFor(1000);	
		}
		else{
			AjaxCondition.forElementVisible(MOVE_RIGHT_RESULTS_END).waitForResponse();
			getAction().click(MOVE_RIGHT_RESULTS_END);
			AjaxCondition.forElementVisible(ACTIVE_PAGE).waitForResponse();
			String i=getAction().getText(ACTIVE_PAGE);
			int count=Integer.parseInt(i);
			AjaxCondition.forElementVisible(MOVE_LEFT_RESULTS_END).waitForResponse();
			getAction().click(MOVE_LEFT_RESULTS_END);
			for(int c=0; c<count;c++){
				getAction().click(MOVE_RIGHT_RESULTS);
				if(AjaxCondition.forElementVisible(RELEASED_HD_ORDERS).waitWithoutException(8)){
					getAction().click(RELEASED_HD_ORDERS);
					num++;
					break;
					}
				
			}
			if(num==0){
				PageAssert.fail("Released orders are not available for the particular dos order ID and dos unit id");
			}
			
			
			
		}
		return this;

	}
	public HomePage chooseShippedHDOrders(){

		Logger.log("Click on any shipped HD orders",TestStepType.STEP);
		if(AjaxCondition.forElementVisible(SHIPPED_HD_ORDERS).waitWithoutException(8)){
			getAction().click(SHIPPED_HD_ORDERS);
			getAction().waitFor(1000);	
			}
			else{
				AjaxCondition.forElementVisible(MOVE_RIGHT_RESULTS_END).waitForResponse();
				getAction().click(MOVE_RIGHT_RESULTS_END);
				AjaxCondition.forElementVisible(ACTIVE_PAGE).waitForResponse();
				String i=getAction().getText(ACTIVE_PAGE);
				int count=Integer.parseInt(i);
				AjaxCondition.forElementVisible(MOVE_LEFT_RESULTS_END).waitForResponse();
				getAction().click(MOVE_LEFT_RESULTS_END);
				for(int c=0; c<count;c++){
					getAction().click(MOVE_RIGHT_RESULTS);
					if(AjaxCondition.forElementVisible(SHIPPED_HD_ORDERS).waitWithoutException(8)){
						getAction().click(SHIPPED_HD_ORDERS);
						break;
						}
					
				}
				
				
				
			}
		return this;

	}
	public HomePage choosePartiallyshippedHDOrders(){


		Logger.log("Click on any Partiallyshipped HD orders",TestStepType.STEP);
		if(AjaxCondition.forElementVisible(PARTIALLY_SHIPPED_HD_ORDERS).waitWithoutException(8)){
			getAction().click(PARTIALLY_SHIPPED_HD_ORDERS);
			getAction().waitFor(1000);	
			}
			else{
				AjaxCondition.forElementVisible(MOVE_RIGHT_RESULTS_END).waitForResponse();
				getAction().click(MOVE_RIGHT_RESULTS_END);
				AjaxCondition.forElementVisible(ACTIVE_PAGE).waitForResponse();
				String i=getAction().getText(ACTIVE_PAGE);
				int count=Integer.parseInt(i);
				AjaxCondition.forElementVisible(MOVE_LEFT_RESULTS_END).waitForResponse();
				getAction().click(MOVE_LEFT_RESULTS_END);
				for(int c=0; c<count;c++){
					getAction().click(MOVE_RIGHT_RESULTS);
					if(AjaxCondition.forElementVisible(PARTIALLY_SHIPPED_HD_ORDERS).waitWithoutException(8)){
						getAction().click(PARTIALLY_SHIPPED_HD_ORDERS);
						break;
						}
					
				}
				
				
				
			}	
		return this;
	}
	/**
	 * Click on Home Delivery Order 
	 * @param orderType Type of Order
	 * @return 
	 */
	public HomePage chooseHDOrder(String orderType){ 
		Logger.log("Click on any "+ orderType +" HD orders",TestStepType.STEP);
		AjaxCondition.forElementVisible(PARAMATERIZED_HD_ORDER.format(orderType)).waitWithoutException(5);
		getAction().click(PARAMATERIZED_HD_ORDER.format(orderType));
		return this;

	}

	/**
	 * Search for a customer with given information in customer tab.
	 * @param memberInfo
	 * @return
	 */
	public HomePage searchForMember(String memberInfo){
		Logger.log("Search for a customer with the information "+memberInfo,TestStepType.STEP);
		Logger.log("Enter member information "+memberInfo,TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(MEMBER_INFO_TXT_BOX).waitForResponse();
		getAction().type(MEMBER_INFO_TXT_BOX, memberInfo);
		getAction().waitFor(3000);
		Logger.log("Click on search button",TestStepType.STEP);
		AjaxCondition.forElementVisible(SEARCH).waitForResponse();
		getAction().click(SEARCH);
		Logger.log("Verify search results are displayed",TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(MEMBER_RESULTS).waitWithoutException(10);
		SoftAssert.checkElementAndContinueOnFailure(MEMBER_RESULTS, "Verify Member Search results are displayed",CheckLocatorFor.isPresent);
		
		return this;
	}
	
	public HomePage searchForMemberWithName(String name,String ZipCode){
		Logger.log("Enter member First Name Last Name "+name,TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(MEMBER_INFO_TXT_BOX).waitForResponse();
		SoftAssert.checkElementAndContinueOnFailure(CUSTOMER_TAB_ZIPCODE, "Verify the ZipCode textbox is not visible", CheckLocatorFor.isNotVisible);
		getAction().type(MEMBER_INFO_TXT_BOX, name);
		getAction().waitFor(2000);
		SoftAssert.checkElementAndContinueOnFailure(CUSTOMER_TAB_ZIPCODE, "Verify the ZipCode textbox is visible after entering the FirstName and Last Name", CheckLocatorFor.isVisible);
		getAction().type(CUSTOMER_TAB_ZIPCODE, ZipCode);
		getAction().waitFor(3000);
		Logger.log("Click on search button",TestStepType.STEP);
		AjaxCondition.forElementVisible(SEARCH).waitForResponse();
		getAction().click(SEARCH);
		Logger.log("Verify search results are displayed",TestStepType.STEP);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(MEMBER_RESULTS).waitWithoutException(10);
		SoftAssert.checkElementAndContinueOnFailure(MEMBER_RESULTS, "Verify Member Search results are displayed",CheckLocatorFor.isPresent);
		
		return this;
	}
	public HomePage verifySequenceOfDisplay() throws ParseException{
		Logger.log("Verifying that the newest order is displayed first",TestStepType.STEP);

		AjaxCondition.forElementVisible(DELIVERY_DATE).waitForResponse();
		List<WebElement> dates = getAction().driver
				.findElements(By.xpath("//strong[contains(text(),'Delivery Date: ')]//ancestor::div[@class='form-group']//span[2]"));


		for (int i = 0; i < dates.size(); i++) {
			if (i + 1 != dates.size()) {
				String fDate = dates.get(i).getText();
				String lDate = dates.get(i + 1).getText();

				SimpleDateFormat simpleDate = new SimpleDateFormat("mm/dd/yyyy");
				Date date = simpleDate.parse(fDate);
				Date date2 = simpleDate.parse(lDate);
				int comResult = date.compareTo(date2);
				if (comResult == 0 || comResult > 0) {
					continue;
				} else {
					PageAssert.fail("Orders are not sorted in decreasing order");
				}
			}
		}

		Logger.log("Verified that Orders are sorted in decreasing order ",TestStepType.VERIFICATION_PASSED);

		return this;
	}

	/**
	 * Click on the first result after member search
	 * @return
	 */
	public HomePage selectFirstCustomerFromSearchResults(){
		Logger.log("Select the first customer from customer search result",TestStepType.STEP);
		AjaxCondition.forElementVisible(MEMBER_SEARCH_RESULT_IDENTIFIER).waitForResponse();
		getAction().click(MEMBER_SEARCH_RESULT_IDENTIFIER);
		return this;
	}

	public void verifyCaseDetails(String status, String location) {
		String caseId=getDataString("caseId");
		Logger.log("Verify Case Details for View Order in "+location,TestStepType.STEP);
		Logger.log("Verify Case Record displayed",TestStepType.VERIFICATION_PASSED);
		verifyTableData(caseId,caseId);
		Logger.log("Verify Case Status is "+status,TestStepType.VERIFICATION_PASSED);
		verifyTableData(caseId,status);
		Logger.log("Verify Case Order Id -"+getDataString("orderId"),TestStepType.VERIFICATION_PASSED);
		verifyTableData(caseId,getDataString("orderId"));
	}


	public void verifyCaseAssignedToQueue(String queue) {
		Logger.log("Verify Case assigned to queue "+queue,TestStepType.VERIFICATION_PASSED);
		verifyTableData(getDataString("caseId"),queue);
	}

	public void verifyTableData(String id, String data, boolean present) {
		Locator locator = new Locator("", "//td[contains(text(),'"+id+"')]//ancestor::tr//*[contains(text(),'"+data+"')]", "Table Data");
		if (present) {
			if (!AjaxCondition.forElementVisible(locator).waitWithoutException(1)) {
				SoftAssert.checkElementAndContinueOnFailure(locator, "Table Data - " + data + " with id - " + id + " displayed.", CheckLocatorFor.isPresent);
			}
		}else{
			if (AjaxCondition.forElementVisible(locator).waitWithoutException(1)) {
				SoftAssert.checkElementAndContinueOnFailure(locator, "Table Data - " + data + " with id - " + id + " NOT displayed.", CheckLocatorFor.isNotPresent);
			}
		}
	}
	public void verifyTableData(String id, String data) {
		verifyTableData(id, data, true);
	}

	public void navigateToHomePageFromMenu() {
		Logger.log("Click on Menu button" , TestStepType.STEP);
		AjaxCondition.forElementVisible(MENU_BUTTON).waitForResponse();
		getAction().click(MENU_BUTTON);
		Logger.log("Select Home Link" , TestStepType.STEP);
		AjaxCondition.forElementVisible(HOME).waitForResponse();
		getAction().click(HOME);
		getAction().waitFor(1000);

	}

	public void refreshPage() {
		Logger.log("Refresh Page" , TestStepType.STEP);
		getAction().pageRefresh();
		if(AjaxCondition.forElementVisible(PHONE_ID_CANCEL_BUTTON).waitWithoutException(15)){
			Logger.log("Click Cancel on Phone ID dialog",TestStepType.SUBSTEP);
			getAction().click(PHONE_ID_CANCEL_BUTTON);
		}
	}
	
	public void verifyRoleChange(String user,String role){
		Logger.log("Verify admin homepage and role change" , TestStepType.STEP);
		getAction().waitFor(4000);
		AjaxCondition.forElementVisible(USER_SEARCH).waitForResponse();
		
		AjaxCondition.forElementVisible(USER_ID).waitForResponse();
		getAction().type(USER_ID, user);
		AjaxCondition.forElementVisible(SEARCH).waitForResponse();
		getAction().click(SEARCH);
		
		AjaxCondition.forElementVisible(USER_ID_LINK).waitForResponse();
		getAction().click(USER_ID_LINK);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(AGENT_CHECKBOX.format(role)).waitForResponse();
		if(getAction().isChecked(AGENT_CHECKBOX.format(role))){
			Logger.log("The checkbox for role:"+role+" is selected. Deselecting the checkbox" , TestStepType.SUBSTEP);
			getContext().put("Check", true);
		}
		else{
			Logger.log("The checkbox for role:"+role+" is not selected. Selecting the checkbox" , TestStepType.SUBSTEP);
			getContext().put("Check", false);
		}
		getAction().scrollTo(AGENT_CHECKBOX.format(role));
		getAction().click(AGENT_CHECKBOX.format(role));
		AjaxCondition.forElementVisible(UPDATE_BUTTON).waitForResponse();
		getAction().scrollTo(UPDATE_BUTTON);
		getAction().click(UPDATE_BUTTON);
		
		AjaxCondition.forElementVisible(UPDATE_MESSAGE).waitForResponse();
		AjaxCondition.forElementVisible(UPDATE_MESSAGE_OK).waitForResponse();
		getAction().click(UPDATE_MESSAGE_OK);
		getAction().waitFor(2000);

		
	}
	public void verifyAdminBreadCrumb(){
		Logger.log("Verify admin options are available in hamburger menu" , TestStepType.STEP);
		AjaxCondition.forElementVisible(MENU_BUTTON).waitForResponse();
		getAction().scrollTo(MENU_BUTTON);
		getAction().waitFor(2000);
		getAction().click(MENU_BUTTON);
		
		SoftAssert.checkElementAndContinueOnFailure(MANAGE_VENDOR, "Verify manage vendor is visible", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(MANAGE_QUEUES, "Verify manage queues is visible", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(MANAGE_USERS, "Verify manage users is visible", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(MANAGE_BULK_NOTES, "Verify manage bulk notes is visible", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(MANAGE_EMAIL_TEMPLATE, "Verify manage email template is visible", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(SEARCH_CASE, "Verify search case is visible in hamburger menu", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(SEARCH_LAYAWAY, "Verify search layaway is visible in hamburger menu", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(SEARCH_MEMBER, "Verify search member is visible in hamburger menu", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(SEARCH_ORDER, "Verify search order is visible in hamburger menu", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(REPORTS, "Verify REPORTS is visible in hamburger menu", CheckLocatorFor.isVisible);
		
	}
	
	public void queueVolumeReport(){
		Logger.log("View queue volume report" , TestStepType.STEP);
		AjaxCondition.forElementVisible(MENU_BUTTON).waitForResponse();
		getAction().scrollTo(MENU_BUTTON);
		getAction().waitFor(2000);
		getAction().click(MENU_BUTTON);
		AjaxCondition.forElementVisible(REPORTS).waitForResponse();
		getAction().click(REPORTS);
		getAction().waitFor(3000);
		AjaxCondition.forElementVisible(REPORTS_DROPDOWN).waitForResponse();
		getAction().click(REPORTS_DROPDOWN);
		getAction().waitFor(4000);
		Locator REPORTS_DROPDOWN_OPTION_REPORT = new Locator("REPORTS_DROPDOWN_OPTION_REPORT","//select[@id='reportName']//option[contains(text(),'Queue Volume')]","REPORTS_DROPDOWN_OPTION_REPORT");
		AjaxCondition.forElementVisible(REPORTS_DROPDOWN_OPTION_REPORT).waitForResponse();	
		getAction().click(REPORTS_DROPDOWN_OPTION_REPORT);
		AjaxCondition.forElementVisible(GENERATE).waitForResponse();
		getAction().click(GENERATE);
		getAction().waitFor(2000);		
		
		SoftAssert.checkElementAndContinueOnFailure(QUEUE_VOLUME_QUEUE_NAME, "Verify whether queue name in queue volume report is displayed", CheckLocatorFor.isVisible);
		AjaxCondition.forElementVisible(QUEUE_VOLUME_UNASSIGNED).waitForResponse();
		SoftAssert.checkElementAndContinueOnFailure(QUEUE_VOLUME_QUEUE_NAME, "Verify whether unassigned cases in queue volume report is displayed", CheckLocatorFor.isVisible);
		getAction().click(QUEUE_VOLUME_UNASSIGNED);
		SoftAssert.checkElementAndContinueOnFailure(QUEUE_VOLUME_TITLE, "Verify whether title for queue volume report is displayed", CheckLocatorFor.isVisible);
		SoftAssert.checkElementAndContinueOnFailure(QUEUE_VOLUME_CASES_UNASSIGNED, "Verify whether unassigned cases link in queue volume report is displayed and it redirects to queue specific unassigned cases", CheckLocatorFor.isVisible);
	}




	public HomePage openCaseByOrderID() {
		Logger.log("opening the searched case" ,TestStepType.STEP);
		getAction().waitFor(2000);
		Logger.log("Click on orderID" , TestStepType.SUBSTEP);
		AjaxCondition.forElementVisible(CASE_ORDERID).waitForResponse();
		getAction().click(CASE_ORDERID);

		return this;

	}


	public HomePage selectCaseFromAssignedQueue() {
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(CASE_UNASSIGNED).waitForResponse();
		getAction().click(CASE_UNASSIGNED);
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(CASE_ASSIGN_SUCCESS).waitForResponse();
		getAction().waitFor(2000);
		AjaxCondition.forElementVisible(CASE_ASSIGN_OK).waitForResponse();
		getAction().click(CASE_ASSIGN_OK);
		return this;

	}

	public void enterPhoneId(String phoneId) {
		Logger.log("Enter Phone Id - "+phoneId ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(PHONE_ID).waitForResponse();
		getAction().type(PHONE_ID, phoneId);
		Logger.log("Click Submit",TestStepType.VERIFICATION_PASSED);
		getAction().click(PHONE_ID_SUBMIT_BUTTON);
		Logger.log("Verify Connected" ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(PHONE_ID_OK_BUTTON).waitForResponse();
		getAction().click(PHONE_ID_OK_BUTTON);
		AjaxCondition.forElementVisible(PHONE_ID_CONNECTED).waitForResponse();
		AjaxCondition.forElementVisible(PHONE_ID_TEXT.format(phoneId)).waitForResponse();
		
		String JsessionID= getAction().getCookieValueByName("JSESSIONID").split("\\.")[0];
		if(FrameworkProperties.SELENIUM_BASE_URL.contains("training")){
			String[] sessionIDarr = JsessionID.split("-");
			System.out.println("Trimmed session ID           "+ sessionIDarr[0]);
			JsessionID= sessionIDarr[0];
		}
		setData("JSESSIONID",JsessionID);
	}

	public HomePage verifyInvalidPhoneId() {
		String error = "Please enter valid Phone Id 4 to 7 digits long.";

		Logger.log("Verify Error displayed for Invalid Phone Id" ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(PHONE_ID).waitForResponse();
		getAction().click(PHONE_ID_SUBMIT_BUTTON);
		AjaxCondition.forElementVisible(PHONE_ID_ERROR).waitForResponse();
		PageAssert.textPresent(PHONE_ID_ERROR, error);
		Logger.log("Verify Error displayed for blank Phone Id - "+getAction().getText(PHONE_ID_ERROR) ,TestStepType.VERIFICATION_PASSED);
		getAction().waitFor(1000);

		getAction().type(PHONE_ID, "1");
		getAction().click(PHONE_ID_SUBMIT_BUTTON);
		AjaxCondition.forElementVisible(PHONE_ID_ERROR).waitForResponse();
		PageAssert.textPresent(PHONE_ID_ERROR, error);
		Logger.log("Verify Error displayed for single digit Phone Id - "+getAction().getText(PHONE_ID_ERROR) ,TestStepType.VERIFICATION_PASSED);
		getAction().waitFor(1000);

		getAction().type(PHONE_ID, "12");
		getAction().click(PHONE_ID_SUBMIT_BUTTON);
		AjaxCondition.forElementVisible(PHONE_ID_ERROR).waitForResponse();
		PageAssert.textPresent(PHONE_ID_ERROR, error);
		Logger.log("Verify Error displayed for two digit Phone Id - "+getAction().getText(PHONE_ID_ERROR) ,TestStepType.VERIFICATION_PASSED);
		getAction().waitFor(1000);

		getAction().type(PHONE_ID, "123");
		getAction().click(PHONE_ID_SUBMIT_BUTTON);
		AjaxCondition.forElementVisible(PHONE_ID_ERROR).waitForResponse();
		PageAssert.textPresent(PHONE_ID_ERROR, error);
		Logger.log("Verify Error displayed for three digit Phone Id - "+getAction().getText(PHONE_ID_ERROR) ,TestStepType.VERIFICATION_PASSED);
		getAction().waitFor(1000);

		getAction().type(PHONE_ID, "123a");
		getAction().click(PHONE_ID_SUBMIT_BUTTON);
		AjaxCondition.forElementVisible(PHONE_ID_ERROR).waitForResponse();
		PageAssert.textPresent(PHONE_ID_ERROR, error);
		Logger.log("Verify Error displayed for alpha-numeric Phone Id - "+getAction().getText(PHONE_ID_ERROR) ,TestStepType.VERIFICATION_PASSED);
		getAction().waitFor(1000);

		getAction().type(PHONE_ID, "abcd");
		getAction().click(PHONE_ID_SUBMIT_BUTTON);
		AjaxCondition.forElementVisible(PHONE_ID_ERROR).waitForResponse();
		PageAssert.textPresent(PHONE_ID_ERROR, error);
		Logger.log("Verify Error displayed for alphabetic Phone Id - "+getAction().getText(PHONE_ID_ERROR) ,TestStepType.VERIFICATION_PASSED);
		getAction().waitFor(1000);

		getAction().type(PHONE_ID, "1234!@");
		getAction().click(PHONE_ID_SUBMIT_BUTTON);
		AjaxCondition.forElementVisible(PHONE_ID_ERROR).waitForResponse();
		PageAssert.textPresent(PHONE_ID_ERROR, error);
		Logger.log("Verify Error displayed for special character Phone Id - "+getAction().getText(PHONE_ID_ERROR) ,TestStepType.VERIFICATION_PASSED);
		getAction().waitFor(1000);

		getAction().type(PHONE_ID, "12345678");
		PageAssert.verifyEqual(getAction().getValue(PHONE_ID), "1234567");
		Logger.log("Verify User cannot enter phone Id greater then 7 digits long",TestStepType.VERIFICATION_PASSED);
		getAction().waitFor(1000);

		getAction().type(PHONE_ID, "12345678");
		getAction().click(PHONE_ID_SUBMIT_BUTTON);
		AjaxCondition.forElementVisible(PHONE_ID_OK_BUTTON).waitForResponse();
		getAction().click(PHONE_ID_OK_BUTTON);
		AjaxCondition.forElementNotVisible(PHONE_ID_ERROR).waitForResponse();
		PageAssert.textPresent(PHONE_ID_CONNECTED, "Connected");
		Logger.log("Verify Valid Phone Id Accepted and agent gets connected" ,TestStepType.VERIFICATION_PASSED);
		getAction().waitFor(1000);
		return this;
	}

	public HomePage verifyPhoneIdDialog() {
		Logger.log("Verify Phone Id Dialog" ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(PHONE_ID).waitForResponse();
		AjaxCondition.forElementVisible(PHONE_ID_CANCEL_BUTTON).waitForResponse();
		AjaxCondition.forElementVisible(PHONE_ID_SUBMIT_BUTTON).waitForResponse();
		return this;
	}
	public HomePage verifyNoPhoneIdDialog() {
		Logger.log("Verify Phone Id Dialog Not Displayed" ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementNotVisible(PHONE_ID).waitForResponse();
		AjaxCondition.forElementNotVisible(PHONE_ID_CANCEL_BUTTON).waitForResponse();
		AjaxCondition.forElementNotVisible(PHONE_ID_SUBMIT_BUTTON).waitForResponse();
		return this;
	}

	public HomePage verifyPhoneIdCancel() {
		Logger.log("Verify Phone Id Cancel button" ,TestStepType.STEP);
		AjaxCondition.forElementVisible(PHONE_ID).waitForResponse();
		AjaxCondition.forElementVisible(PHONE_ID_NOT_CONNECTED).waitForResponse();

		Logger.log("Click Cancel button" ,TestStepType.VERIFICATION_PASSED);
		getAction().click(PHONE_ID_CANCEL_BUTTON);

		Logger.log("Verify Phone Status is Not Connected" ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementNotVisible(PHONE_ID).waitForResponse();
		AjaxCondition.forElementVisible(PHONE_ID_NOT_CONNECTED).waitForResponse();
		PageAssert.textPresent(PHONE_ID_NOT_CONNECTED, "Not Connected");
		return this;

	}

	public void verifyManualSearchEnabled() {
		Logger.log("Verify Manual Search is Enabled" ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(MEMBER_INFO_TXT_BOX).waitForResponse();
		getAction().type(MEMBER_INFO_TXT_BOX, "test");
		PageAssert.verifyEqual(getAction().getValue(MEMBER_INFO_TXT_BOX), "test");
	}

	public void verifyManualSearchEnabledDelivery() {
		Logger.log("Verify Manual Search is Enabled" ,TestStepType.STEP);
		AjaxCondition.forElementVisible(DOS_ORDER_NUMBER_FIELD).waitWithoutException(5);
		getAction().type(DOS_ORDER_NUMBER_FIELD, "1234");
		PageAssert.verifyEqual(getAction().getValue(DOS_ORDER_NUMBER_FIELD), "1234");
	}

	public void verifyPhoneIdReconnect() {
		Logger.log("Verify Phone Id Reconnect" ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(PHONE_ID_NOT_CONNECTED).waitForResponse();
		Logger.log("Click Phone Icon" ,TestStepType.STEP);
		getAction().click(PHONE_ICON);
		AjaxCondition.forElementVisible(PHONE_ID).waitForResponse();
		enterPhoneId("1234567");

	}

	public void verifyManualSearchDisabled() {
		Logger.log("Verify Manual Search is disabled" ,TestStepType.VERIFICATION_PASSED);
		getAction().waitFor(1000);
		PageAssert.verifyFalse(getAction().findElement(MEMBER_INFO_TXT_BOX).isEnabled(), "Manual Search is NOT disabled");
	}

	public HomePage verifyEmptyDeliverySearchResult(){
		Logger.log("Verify No Search Result returned" ,TestStepType.STEP);
		AjaxCondition.forElementVisible(NO_SEARCH_RESULTS_LABEL).waitWithoutException(5);
		PageAssert.elementVisible(NO_SEARCH_RESULTS_LABEL);
		return this;
	}

	public void verifyOrderPhoneNumberInOrderSearchResults(String phoneNumber) {
		Logger.log("Verify Call phone number matches with phone number in order results - "+phoneNumber ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(PHONE_ORDER_RESULTS).waitForResponse();
		getAction().waitFor(5000);
		List<WebElement> phoneTags = getAction().driver.findElements(getAction().getWebElement(PHONE_ORDER_RESULTS));
		for(WebElement phoneTag:phoneTags){
			PageAssert.verifyTrue(StringUtils.extractNumber(phoneTag.getText()).equals(phoneNumber), "Verify phone number in order search results - "+phoneNumber);
		}

	}

	public void verifyPhoneInProfileSearchResults(String phoneNumber) {
		Logger.log("Verify Call phone number matches with phone number in Profile Search results - "+phoneNumber ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(PHONE_PROFILE_SEARCH_RESULTS).waitForResponse();
		getAction().waitFor(5000);
		List<WebElement> phoneTags = getAction().driver.findElements(getAction().getWebElement(PHONE_PROFILE_SEARCH_RESULTS));
		for(WebElement phoneTag:phoneTags){
			PageAssert.verifyTrue(StringUtils.extractNumber(phoneTag.getText()).contains(phoneNumber), "Verify phone number in Profile search results - "+phoneNumber);
		}
	}

	public void selectProfileFromSearchResults(String index) {
		Logger.log("Select Profile with index - "+index ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(SELECT_PROFILE_FRON_SEARCH_RESULTS.format(index)).waitForResponse();
		getAction().click(SELECT_PROFILE_FRON_SEARCH_RESULTS.format(index));
	}

	public void verifyScreenPop(String pv7) {
		Logger.log("Verify Screen Pop displayed with pv7 - "+pv7 ,TestStepType.VERIFICATION_PASSED);
		verifyTableData("Order Phone", pv7);
		verifyTableData("Order Phone", pv7,false);
	}
	
	public void verifyScreenPopPV7Null(String pv1){
		Logger.log("Verify Screen Pop displayed with pv1 - "+pv1 ,TestStepType.VERIFICATION_PASSED);
		verifyTableData("Number called from", pv1);
		verifyTableData("Number called from", pv1,false);
	}

	public void verifyOrderSearchPhonePrePopulated(String phoneNumber) {
		Logger.log("Verify Order Search form Pre-Populated with Phone Number - "+phoneNumber ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(PHONE_FIELD).waitForResponse();
		PageAssert.verifyEqual(getAction().getValue(PHONE_FIELD), phoneNumber);
	}

	public void verifySearchButtonEnabled(){
		Logger.log("Verify Search Button is Enabled", TestStepType.VERIFICATION_STEP);
		SoftAssert.checkConditionAndContinueOnFailure("Verify Search Button is Enabled", getAction().findElement(SEARCH_BUTTON).isEnabled());
	}
	
	public void verifySearchButtonEnabledDelivery(){
		Logger.log("Verify Search Button is Enabled", TestStepType.VERIFICATION_STEP);
		SoftAssert.checkConditionAndContinueOnFailure("Verify Search Button is Enabled", getAction().findElement(DELSEARCH_BUTTON).isEnabled());
	}

	public void verifyDeliveryOrderSearchPhonePrePopulated(String phoneNumber) {
		Logger.log("Verify Delivery Order Search form Pre-Populated with Phone Number - "+phoneNumber ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(DOS_PHONE_FIELD).waitWithoutException(5);
		PageAssert.verifyEqual(getAction().getValue(DOS_PHONE_FIELD), phoneNumber);
	}

	public void verifyPhoneIdText(String phoneId) {
		Logger.log("Verify Phone Id text displyed next to Connected Text - "+phoneId ,TestStepType.VERIFICATION_PASSED);
		AjaxCondition.forElementVisible(PHONE_ID_TEXT.format(phoneId)).waitForResponse();
	}
	public void verifyPhoneIdNotConnected(){
		Logger.log("Verify Phone Id is disconnected",TestStepType.STEP);
		AjaxCondition.forElementVisible(PHONE_ID_NOT_CONNECTED).waitWithoutException(5);
		PageAssert.elementVisible(PHONE_ID_NOT_CONNECTED);
	}

	public void verifyCTILogout(String confirm) {
		if(confirm.equalsIgnoreCase("No")){
			Logger.log("Verify Logout Confirmation dialog -"+confirm,TestStepType.VERIFICATION_PASSED);
			AjaxCondition.forElementVisible(CTI_LOGOUT_CONFIRMATION_TEXT).waitForResponse();
			
			Logger.log("Select No on Logout Confirmation Dialog",TestStepType.VERIFICATION_PASSED);
			getAction().click(CTI_LOGOUT_CONFIRMATION_NO);
			getAction().waitFor(1000);
			Logger.log("Verify Logout Cancelled",TestStepType.VERIFICATION_PASSED);
			getAction().click(CLOSE_PROFILE_MODAL);
			getAction().waitFor(1000);
			
		}else{
			Logger.log("Verify Logout Confirmation dialog -"+confirm,TestStepType.VERIFICATION_PASSED);
			AjaxCondition.forElementVisible(CTI_LOGOUT_CONFIRMATION_TEXT).waitForResponse();
			
			Logger.log("Select Yes on Logout Confirmation Dialog",TestStepType.VERIFICATION_PASSED);
			getAction().click(CTI_LOGOUT_CONFIRMATION_YES);
			Logger.log("Verify Logout Successfull",TestStepType.VERIFICATION_PASSED);
			AjaxCondition.forElementVisible(AGENTID_TEXTBOX).waitForResponse();
			
		}
		
		
	}
	public HomePage searchBySYWlinkPhoneNumber(String phoneNumber) {
		getAction().waitFor(2000);
		Logger.log("Search using syw link phone number ", TestStepType.STEP);
		
		if( AjaxCondition.forElementVisible(SYW_LINK_TAB).waitWithoutException(1)){
			Logger.log("Click on syw link tab", TestStepType.STEP);
			AjaxCondition.forElementVisible(SYW_LINK_TAB).waitForResponse();
			getAction().click(SYW_LINK_TAB);
			Logger.log("Enter phone number :" + phoneNumber, TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(SYW_LINK_PHONE_NUMBER).waitForResponse();
			getAction().click(SYW_LINK_PHONE_NUMBER);
			getAction().type(SYW_LINK_PHONE_NUMBER, phoneNumber);
			getAction().waitFor(1000);
			if(getAction().getText(SYW_LINK_PHONE_NUMBER)==""){
				Logger.log("phone number was not entered " , TestStepType.STEP);
			}else{
				Logger.log("Click on Search Button", TestStepType.STEP);
				AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse(3000);
				getAction().focus(SEARCH_BUTTON);
				getAction().click(SEARCH_BUTTON);
				getAction().waitFor(2000);
				if(NO_RESULT.equals("Total Found: 0")){
					Logger.log("Invalid phone number ", TestStepType.STEP);
				}
			}
		}
		else
			Logger.log("syw link tab not visible", TestStepType.STEP);
		return this;
	}
	public HomePage searchBySYWlinkEmail(String email) {
		getAction().waitFor(2000);
		Logger.log("Search using syw link email id ", TestStepType.STEP);
		
		if(AjaxCondition.forElementVisible(SYW_LINK_TAB).waitWithoutException(1)){
			Logger.log("Click on syw link tab", TestStepType.STEP);
			AjaxCondition.forElementVisible(SYW_LINK_TAB).waitForResponse();
			getAction().click(SYW_LINK_TAB);
			
			Logger.log("Enter email :" + email, TestStepType.SUBSTEP);
			AjaxCondition.forElementVisible(SYW_LINK_EMAIL).waitForResponse();
			getAction().click(SYW_LINK_EMAIL);
			getAction().type(SYW_LINK_EMAIL, email);
			getAction().waitFor(1000);
			if(getAction().getText(SYW_LINK_EMAIL)==""){
				Logger.log("phone number was not entered " , TestStepType.STEP);
			}
			else{
				Logger.log("Click on Search Button", TestStepType.STEP);
				AjaxCondition.forElementVisible(SEARCH_BUTTON).waitForResponse(3000);
				getAction().focus(SEARCH_BUTTON);
				getAction().click(SEARCH_BUTTON);
				getAction().waitFor(2000);
				if(NO_RESULT.equals("Total Found: 0")){
					Logger.log("Invalid phone number ", TestStepType.STEP);
				}
			}
		}
		else
			Logger.log("syw link not visible", TestStepType.STEP);
		
		
		return this;
	}
	
	
	public HomePage ClickOnOrderTab() {
		getAction().click(ORDER_TAB);
		return this;
	}
	public HomePage ClickOnPerformanceSupport(String name) {
		AjaxCondition.forElementVisible(PERFORMANCE_SUPPORT_NAME.format(name)).waitForResponse();
		getAction().click(PERFORMANCE_SUPPORT_NAME.format(name));
		Logger.log("click on performance support", TestStepType.STEP);
		getAction().waitFor(2000);
		return this;
	}
	public HomePage VerifyPerformanceSupportMessage(String msg) {
		String msg1=getAction().getAttribute(PERFORMANCE_SUPPORT_DISPLAY_MSG, "innerText");
		 msg = msg.replaceAll("[^a-zA-Z0-9]", ""); 
		 msg1 = msg1.replaceAll("[^a-zA-Z0-9]", ""); 
		System.out.println("-------------------------------------Performance support Message displayed on application is : "+msg1);
		System.out.println("----------------------------------------my message which i need to verify is :"+msg);
		//PageAssert.verifyTrue(msg1.contains(msg), "TEst passedjkwefhwkjfhlasddh");
		SoftAssert.checkConditionAndContinueOnFailure("Verify the correct performance message is shown", msg1.contains(msg.trim()));
		return this;
	}
	
}