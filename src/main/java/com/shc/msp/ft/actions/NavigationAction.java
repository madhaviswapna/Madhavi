package com.shc.msp.ft.actions;

import java.text.ParseException;





import com.shc.automation.Logger;
import com.shc.automation.utils.TestUtils;
import com.shc.automation.utils.TestUtils.Feature;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.factory.SiteFactory;
import com.shc.msp.ft.util.MongoDB;
import com.shc.msp.ft.util.ScreenPopAPIRequest;


public class NavigationAction extends BaseAction {

	public NavigationAction(SiteFactory factory) {
        super(factory);
    }

    //@Override
    protected Feature feature() {
        return Feature.BROWSE;
    }
    
    
    @Override
	public NavigationAction addlogType(TestUtils.TestStepType testStepType)
	{
		super.addlogType(testStepType);
		return this;
	}

    public NavigationAction login(User user) {
    	Logger.log("Agent logs in to MSP application", TestUtils.TestStepType.WHEN);
    	Logger.log("Agent is Logged in to MSP", TestUtils.TestStepType.GIVEN);
        this.factory.homePage().login(user);
        return this;
    }
    public NavigationAction verifyQueueName() {
    	Logger.log("Agent verifies queue name on home page", TestUtils.TestStepType.THEN);
    	this.factory.homePage().verifyQueueName();
    	return this;
    }

    public NavigationAction logout() {
    	Logger.log("Agent logs out of MSP application", TestUtils.TestStepType.WHEN);
    	Logger.log("Agent should be able to logout of application", TestUtils.TestStepType.THEN);
        this.factory.homePage().logout();
        return this;
    }

    public NavigationAction searchByOrderId(String orderId) {
    	Logger.log("Agent searches for an order id", TestUtils.TestStepType.WHEN);
        this.factory.homePage().searchByOrderId(orderId);
        return this;
    }
    public NavigationAction searchBySubOrderId(String subOrderID){
    	Logger.log("Agent searches for a Sub order id", TestUtils.TestStepType.WHEN);
        this.factory.homePage().searchBySubOrderId(subOrderID);
    	return this;
    }
    public NavigationAction searchByLayawayContractID(String contractId){
    	Logger.log("Agent searches for a Layaway Contract ID ", TestUtils.TestStepType.WHEN);
        this.factory.homePage().searchByLayawayContractID(contractId);
    	return this;
    }
    
    public NavigationAction  searchByLayawayphoneNumber(String phNumber){
    	Logger.log("Agent searches for a Layaway using Phone Number ", TestUtils.TestStepType.WHEN);
        this.factory.homePage().searchByLayawayphoneNumber(phNumber);
    	return this;
    }
    public NavigationAction selectLayawayOrderFromSearchResults(int index){
    	Logger.log("Agent selects item # "+index+" from search results", TestUtils.TestStepType.WHEN);
    	this.factory.homePage().selectLayawayOrderFromSearchResults(index);
    	return this;
    }
    public NavigationAction searchByDeliveryOrderId(String dosorderID, String dosunitID) {
    	Logger.log("Agent searches for an order id", TestUtils.TestStepType.WHEN);
    	Logger.log("Order search results are available for agent", TestUtils.TestStepType.GIVEN);
        this.factory.homePage().searchByDeliveryOrderId(dosorderID, dosunitID);
        return this;
    }
    
    
    public NavigationAction verifyonlineagent() {
    	Logger.log("Agent should be authenticated and shown the home page", TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyonlineagent();
        return this;

    }
    public NavigationAction verifyAdmin() {
    	Logger.log("Admin should be authenticated and shown the home page", TestUtils.TestStepType.THEN);
    	this.factory.homePage().verifyAdmin();
    	return this;
    	
    }
    public NavigationAction verifyDisplayAdmin(String roleName) {
    	Logger.log("Verify Display settings of admin", TestUtils.TestStepType.THEN);
    	this.factory.homePage().verifyDisplayAdmin(roleName);
    	return this;
    	
    }
    public NavigationAction verifyAgentRole(String role) {
    	Logger.log("Agent should be authenticated and shown the home page", TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyAgentRole(role);
        return this;

    }
    public NavigationAction VerifyDeliveryAgent() {
    	Logger.log("Agent should be authenticated and shown the home page", TestUtils.TestStepType.THEN);
        this.factory.homePage().VerifyDeliveryAgent();
        return this;

    }
    
    public NavigationAction verifydeliveryagent() {
    	Logger.log("Agent should be authenticated and shown the home page", TestUtils.TestStepType.THEN);
        this.factory.homePage().VerifyDeliveryAgent();
        return this;

    }
    
    public NavigationAction verifyofflineagent() {
    	Logger.log("OFFLINE AGENT should be authenticated and shown the home page", TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyofflineagent();
        return this;

    }
    public NavigationAction verifyDeliveryOfflineagent() {
    	Logger.log("DELIVERY OFFLINE AGENT should be authenticated and shown the home page", TestUtils.TestStepType.THEN);
        this.factory.homePage().VerifyDeliveryOfflineAgent();
        return this;

    }
    
    public NavigationAction verifySuperAdmin()  {
    	Logger.log("Super admin should be authenticated and shown the home page", TestUtils.TestStepType.THEN);
        this.factory.homePage().verifySuperAdmin() ;
        return this;

    }
    
     public NavigationAction verifyDatePeriod()  {
        this.factory.homePage().verifyDatePeriod() ;
        return this;

    }

    public NavigationAction searchByName(String firstName, String lastName) {
        this.factory.homePage().searchByName(firstName, lastName);
        return this;
    }
    
    public NavigationAction searchByNameForSpecPeriod(String firstName, String lastName) {
        this.factory.homePage().searchByNameForSpecPeriod(firstName, lastName);
        return this;
    }
    
    
    //For Deliver Flow
    public NavigationAction searchDOSOrdersByDate() {
        this.factory.homePage().searchDOSOrdersByDate();
        return this;
    }
    
    public NavigationAction searchBySalesCheck(String salesCheck, String store) {
        this.factory.homePage().searchBySalesCheck(salesCheck,store);
        return this;
    }

    public NavigationAction selectOrderInMyRecentInteractions(int order) throws Exception {
    	Logger.log("Agent selects an order from recent interactions",TestStepType.WHEN);
        this.factory.homePage().selectOrderInMyRecentInteractions(order);
        return this;
    }
    
    //For Delivery Flow
    public NavigationAction selectOrderInMyRecentDeliveryInteractions(int order) throws Exception {
    	Logger.log("Agent selects an order from recent delivery interactions",TestStepType.WHEN);
        this.factory.homePage().selectOrderInMyRecentDeliveryInteractions(order);
        return this;
    }
    
      
    public NavigationAction selectOrderInMyRecentInteractions_SearchByEmail(int order){
    	try {
			this.factory.homePage().selectOrderInMyRecentInteractions(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return this;
    }

	public NavigationAction searchByPhone(String phoneNumber) {
		Logger.log("Agent searches for an order by phone",TestStepType.WHEN);
		this.factory.homePage().searchByPhone(phoneNumber);
        return this;
	}
	
	
	//For Delivery Flow
	public NavigationAction searchByDOSPhone(String dosphoneNumber) {
		Logger.log("Agent searches for an order by phone",TestStepType.WHEN);
		this.factory.homePage().searchByDOSPhone(dosphoneNumber);
        return this;
	}
	
	//For Delivery Flow
	public NavigationAction searchByDOSSalesCheck(String dosphoneNumber) {
		Logger.log("Agent searches for an order by phone",TestStepType.WHEN);
		this.factory.homePage().searchByDOSSalesCheck(dosphoneNumber);
        return this;
	}
	
	
	
	public NavigationAction manageQueues() {
		Logger.log("Super admin tries to change the queue priority",TestStepType.THEN);
		this.factory.vendorDetailsPage().ManageQueues();
        return this;
	}
	
	public NavigationAction manageRoles() {
		Logger.log("Super admin tries to manage roles",TestStepType.THEN);
		this.factory.vendorDetailsPage().manageRoles();
        return this;
	}


	public NavigationAction searchByEmail(String email) {
		Logger.log("Agent searches for an order by email",TestStepType.WHEN);
		this.factory.homePage().searchByEmail(email);
        return this;
	}
	public NavigationAction navigateToTab(String tabName) {
		Logger.log("Agent navigates to tab:"+tabName,TestStepType.WHEN);
		this.factory.homePage().navigateToTab(tabName);
		return this;
	}
	
	public NavigationAction searchandVerifyCustomer(String customer) {
		Logger.log("Agent searches for a customer using customer search",TestStepType.WHEN);
		this.factory.homePage().searchandVerifyCustomer(customer);
		return this;
	}
	public NavigationAction searchandVerifyProductInShop(String customer) {
		Logger.log("Agent searches for a product using product tab search search",TestStepType.WHEN);
		this.factory.homePage().searchandVerifyProductInShop(customer);
		return this;
	}
	public NavigationAction searchAndVerifyProduct(String store, String product){
		Logger.log("Agent searches for a product in "+store+" using product tab search search",TestStepType.THEN);
		this.factory.homePage().searchAndVerifyProduct(store, product);
		return this;
	}

    public NavigationAction searchCustomer(){
    	Logger.log("Agent selects Search Customer option from menu",TestStepType.GIVEN);
		this.factory.homePage().searchCustomer();
        return this;
	}

	public NavigationAction searchByPhoneForSpecPeriod(String phoneNumber) {
		this.factory.homePage().searchByPhoneForSpecPeriod(phoneNumber);
        return this;
	}
	
	public NavigationAction searchByEmailForSpecPeriod(String email){
		this.factory.homePage().searchByEmailForSpecPeriod(email);
        return this;
	}

	public NavigationAction verifyLogin(User user) {
		this.factory.homePage().verifyLogin(user);
        return this;
	}

	public NavigationAction compareTwoExcelFiles(String fileName, String sheetNameOriginal,
			String sheetNameNew) {
		compareTwoExcelFiles(fileName, sheetNameOriginal,sheetNameNew);
		
		 return this;
	}

	public NavigationAction closeWarningPopupWindow() {
		Logger.log("Close any Warnings", TestUtils.TestStepType.WHEN);
         this.factory.homePage().closeWarningPopupWindow();
	        return this;
	}

	/*public  NavigationAction compareTwoExcelFiles(String fileName, String sheetNameOriginal,
			String sheetNameNew) throws Exception {
		CompareExcelRuleSheet.compareTwoExcelFiles(fileName,sheetNameOriginal,sheetNameNew);
		 return this;
	}*/


//Delivery Action navigation

	public NavigationAction searchByPhoneForDeliveryUser(String phoneNumber) {
		Logger.log("Search by phone number", TestUtils.TestStepType.WHEN);
		this.factory.homePage().searchByPhoneForDeliveryUser(phoneNumber);
		return this;
	}
	public NavigationAction verifySequenceOfDisplay() throws ParseException {
		Logger.log("Verifying the sequence of orders in order search results", TestUtils.TestStepType.WHEN);
		this.factory.homePage().verifySequenceOfDisplay();
		return this;
	}
	public NavigationAction searchByALTPhoneForDeliveryUser(String phoneNumber) {
		Logger.log("Search by phone number", TestUtils.TestStepType.WHEN);
		this.factory.homePage().searchByALTPhoneForDeliveryUser(phoneNumber);
		return this;
	}
	
	public NavigationAction searchBySOLDTOPhoneForDeliveryUser(String phoneNumber) {
		Logger.log("Search by phone number", TestUtils.TestStepType.WHEN);
		this.factory.homePage().searchBySOLDTOPhoneForDeliveryUser(phoneNumber);
		return this;
	}
	
	public NavigationAction searchByCELLPhoneForDeliveryUser(String phoneNumber) {
		Logger.log("Search by phone number", TestUtils.TestStepType.WHEN);
		this.factory.homePage().searchByCELLPhoneForDeliveryUser(phoneNumber);
		return this;
	}
	public NavigationAction chooseOpenHDOrders() {
		Logger.log("Choose open Home delivery order", TestUtils.TestStepType.GIVEN);
		Logger.log("Choose open Home delivery order", TestUtils.TestStepType.WHEN);
		this.factory.homePage().chooseOpenHDOrders();
		return this;
	}
	public NavigationAction chooseReleasedHDOrders() {
		Logger.log("Choose Released Home delivery order", TestUtils.TestStepType.GIVEN);
		this.factory.homePage().chooseReleasedHDOrders();
		return this;
	}
	public NavigationAction chooseShippedHDOrders() {
		Logger.log("Choose Shipped Home delivery order", TestUtils.TestStepType.GIVEN);
		this.factory.homePage().chooseShippedHDOrders();
		return this;
	}
	public NavigationAction choosePartiallyshippedHDOrders() {
		Logger.log("Choose Partiallyshipped Home delivery order", TestUtils.TestStepType.GIVEN);
		this.factory.homePage().choosePartiallyshippedHDOrders();
		return this;
	}
	
	
	public NavigationAction chooseHDOrders(String orderType) {
		Logger.log("Choose "+orderType+" Home Delivery order", TestUtils.TestStepType.WHEN);
		this.factory.homePage().chooseHDOrder(orderType);
		return this;
	}

	/** Method to perform member search from Customer tab
	 * @param memberInfo SYW number, Email and Phone Number
	 * @return
	 */
	public NavigationAction searchByCustomer(String memberInfo){
		Logger.log("Agent searches for a member",TestStepType.WHEN);
		this.factory.homePage().searchForMember(memberInfo)
		.selectFirstCustomerFromSearchResults();
		return this;
	}
	public NavigationAction searchForMemberWithName(String name,String ZipCode){
		Logger.log("Agent searches for a member",TestStepType.WHEN);
		this.factory.homePage().searchForMemberWithName(name,ZipCode)
		.selectFirstCustomerFromSearchResults();
		return this;
	}
	
	
	/**
	 * Verify all elements are displayed in profile page. Click on view items button and verify order item details are displayed
	 * @return
	 */
	public NavigationAction verify360DegreePage(){
		Logger.log("User profile page should be displayed with details of orders and purchase history",TestStepType.THEN);
		this.factory.profilePage().verifyProfilePage()
		.clickOnViewItemsButtonForFirstOrder()
		.verifyItemsAreDisplayed()
		.verifyFilterForPurchaseHistory();
		return this;
		
	}
	/**
	 * Verify all elements are displayed in profile page.
	 * @return
	 */
	public NavigationAction verifyProfilePage(){
		Logger.log("User profile page should be displayed",TestStepType.THEN);
		this.factory.profilePage().verifyProfilePage();
		return this;
		
	}
	
	public NavigationAction searchByCaseId() {
		Logger.log("Agent searches for an case id", TestUtils.TestStepType.WHEN);
        this.factory.homePage().searchByCaseId();
        return this;
	}
	public NavigationAction searchSYWLinkStatus(String member) {
		Logger.log("Agent proceeds to see whether membership status is linked or not", TestUtils.TestStepType.WHEN);
		this.factory.homePage().searchSYWLinkStatus(member);
		return this;
	}
	public NavigationAction openCaseByOrderID() {
		Logger.log("Agent opens the case by clicking order id", TestUtils.TestStepType.THEN);
        this.factory.homePage().openCaseByOrderID();
        return this;
	}
	public NavigationAction selectCaseFromAssignedQueue() {
		Logger.log("Agent selects a case from the list of unassigned cases", TestUtils.TestStepType.WHEN);
        this.factory.homePage().selectCaseFromAssignedQueue();
        return this;
	}
	
	
    public NavigationAction searchByCaseId(String caseId) {
    	Logger.log("Agent searches for an case id", TestUtils.TestStepType.WHEN);
        this.factory.homePage().searchByCaseId(caseId);
        return this;
    }
    public NavigationAction verifyCaseAssignedToQueue(String queue) {
    	Logger.log("Verify case routed to queue "+queue, TestUtils.TestStepType.THEN);
    	this.factory.homePage().verifyCaseAssignedToQueue(queue);
        return this;
    }
    public NavigationAction verifyCaseDetails(String status, String location) {
    	Logger.log("Verify Case Details in "+location, TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyCaseDetails(status,location);
        return this;
    }
    public NavigationAction navigateToHomePageFromMenu() {
    	Logger.log("Navigate to Home Page From Menu", TestUtils.TestStepType.WHEN);
        this.factory.homePage().navigateToHomePageFromMenu();
        return this;
    }
    public NavigationAction refreshPage() {
    	Logger.log("Refresh Page", TestUtils.TestStepType.THEN);
        this.factory.homePage().refreshPage();
        return this;
    }
    public NavigationAction verifyRoleChange(String user,String role) {
    	Logger.log("Verify role change", TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyRoleChange(user,role);
        return this;
    }
    
    public NavigationAction VerifyLayawayContractDetails(String contractID){
    	Logger.log("verfiy layway contract details", TestUtils.TestStepType.THEN);
        this.factory.vendorDetailsPage().VerifyLayawayContractDetails(contractID);
        return this;
    }
    public NavigationAction verifyAdminBreadCrumb(){
    	Logger.log("verfiy BREADCRUMB OF ADMIN", TestUtils.TestStepType.THEN);
    	this.factory.homePage().verifyAdminBreadCrumb();
    	return this;
    }
    public NavigationAction queueVolumeReport(){
    	Logger.log("verfiy queue volume report by logging in as admin", TestUtils.TestStepType.THEN);
    	this.factory.homePage().queueVolumeReport();
    	return this;
    }
    public NavigationAction VerifyVendorDetails(String VendorID){
    	Logger.log("verfiy Vendor details", TestUtils.TestStepType.THEN);
        this.factory.vendorDetailsPage().VerifyVendorDetails(VendorID);
        return this;
    }
    public NavigationAction deleteCasesforOrderfromDB(String orderId) {
    	Logger.log("Delete existing cases from Database for an order id - "+orderId, TestUtils.TestStepType.WHEN);
    	MongoDB.deleteCasesforOrderfromDB(orderId);
    	return this;
    }
    public NavigationAction deleteAssignedCasesforUserfromDB(String userID) {
    	Logger.log("Delete all the case which are in case working status for the user", TestUtils.TestStepType.WHEN);
    	MongoDB.deleteAssignedCasesforUserfromDB(userID);
    	return this;
    }
    
    

	public NavigationAction callAPIForScreenPop(String pv7, String pv8, String agentLDAPID) {
		Logger.log("Call API for Screen Pop", TestUtils.TestStepType.WHEN);
    	ScreenPopAPIRequest.call(pv7,pv8,agentLDAPID);
    	this.factory.homePage().verifyScreenPop(pv7);
    	return this;
	}
	
	public NavigationAction callAPIForScreenPopPV7Null(String pv1, String pv8, String agentLDAPID){
		Logger.log("Call API for Screen Pop", TestUtils.TestStepType.WHEN);
		ScreenPopAPIRequest.callWithPV7Null(pv1, pv8, agentLDAPID);
		this.factory.homePage().verifyScreenPopPV7Null(pv1);
		return this;
	}

	public NavigationAction enterPhoneId(String phoneId) {
		Logger.log("Enter Phone Id and Submit ", TestUtils.TestStepType.WHEN);
        this.factory.homePage().enterPhoneId(phoneId);
        return this;
	}

	public NavigationAction verifyPhoneIdDialog() {
		Logger.log("Verify Phone Id Dialog ", TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyPhoneIdDialog();
		return this;
	}
	public NavigationAction verifyNoPhoneIdDialog() {
		Logger.log("Verify Phone Id Dialog Not Displayed", TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyNoPhoneIdDialog();
		return this;
	}

	public NavigationAction verifyInvalidPhoneId() {
		Logger.log("Verify Invalid Phone Id Dialog Errors", TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyInvalidPhoneId();
		return this;
	}

	public NavigationAction verifyPhoneIdCancel() {
		Logger.log("Verify Phone Id Cancel button", TestUtils.TestStepType.THEN);
		Logger.log("Phone Id Cancel button is clicked", TestUtils.TestStepType.WHEN);
        this.factory.homePage().verifyPhoneIdCancel();
		return this;
	}

	public NavigationAction verifyManualSearchEnabled() {
		Logger.log("Verify Manual Search Enabled", TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyManualSearchEnabled();
		return this;
	}

	public NavigationAction verifyPhoneIdReconnect() {
		Logger.log("Verify Phone Id Reconnect", TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyPhoneIdReconnect();
		return this;
	}

	public NavigationAction verifyManualSearchDisabled() {
		Logger.log("Verify Manual Search is Disabled", TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyManualSearchDisabled();
		return this;
	}
	
	public NavigationAction verifyOrderPhoneNumberInOrderSearchResults(String phoneNumber) {
		Logger.log("Verify Order Phone Number matches in order results",TestStepType.THEN);
		this.factory.homePage().verifyOrderPhoneNumberInOrderSearchResults(phoneNumber);
		return this;
	}

	public NavigationAction verifyPhoneInProfileSearchResults(String phoneNumber) {
		Logger.log("Verify Order Phone Number matches in Profile Search results",TestStepType.THEN);
		this.factory.homePage().verifyPhoneInProfileSearchResults(phoneNumber);
		return this;
	}

	public NavigationAction selectProfileFromSearchResults(String index) {
		Logger.log("Verify Agent can select Profile from Profile Search results",TestStepType.THEN);
		Logger.log("Select Profile from Profile Search results",TestStepType.WHEN);
		this.factory.homePage().selectProfileFromSearchResults(index);
		return this;
	}

	public NavigationAction verifyPhoneOnProfilePage(String phoneNumber) {
		Logger.log("Verify Phone Number in Profile Page",TestStepType.THEN);
		this.factory.profilePage().verifyPhoneOnProfilePage(phoneNumber);
		return this;
	}

	public NavigationAction verifyOrderSearchPhonePrePopulated(String phoneNumber) {
		Logger.log("Verify Order Search form Pre-Populated with Phone Number",TestStepType.THEN);
		this.factory.homePage().verifyOrderSearchPhonePrePopulated(phoneNumber);
		return this;
	}
	
	public NavigationAction verifySearchButtonEnabled(){
		Logger.log("Verify Search button is Enabled",TestStepType.THEN);
		this.factory.homePage().verifySearchButtonEnabled();
		return this;
	}
	
	public NavigationAction verifySearchButtonEnabledDelivery(){
		Logger.log("Verify Search button is Enabled",TestStepType.THEN);
		this.factory.homePage().verifySearchButtonEnabledDelivery();
		return this;
	}
	public NavigationAction verifyDeliveryOrderSearchPhonePrePopulated(String phoneNumber) {
		Logger.log("Verify Delivery Order Search form Pre-Populated with Phone Number",TestStepType.THEN);
		this.factory.homePage().verifyDeliveryOrderSearchPhonePrePopulated(phoneNumber);
		return this;
	}
	public NavigationAction verifyEmptyDeliverySearchResult(){
		Logger.log("Verify No Result Found Displayed",TestStepType.THEN);
		this.factory.homePage().verifyEmptyDeliverySearchResult();
		return this;
	}
	public NavigationAction verifyManualSearchEnabledDelivery(){
		Logger.log("Verify Delivery Manual Search is Disabled", TestUtils.TestStepType.THEN);
		this.factory.homePage().verifyManualSearchEnabledDelivery();
		return this;
	}
	public NavigationAction clickChangeCustomer(){
		Logger.log("Click on Change Customer", TestUtils.TestStepType.WHEN);
		this.factory.profilePage().clickChangeCustomer();
		return this;
	}
	public NavigationAction clickLogoutOfMember(){
		Logger.log("Click on Log Out Of Member", TestUtils.TestStepType.WHEN);
		this.factory.profilePage().clickLogoutOfMember();
		return this;
	}
	public NavigationAction verifyPrePopulatedPhoneNumberChangeCustomer(String phonenumber){
		Logger.log("Verify Change Customer popup is Pre Populated with Customer Phone", TestUtils.TestStepType.THEN);
		this.factory.profilePage().verifyPrePopulatedPhoneNumberChangeCustomer(phonenumber);
		return this;
	}
	public NavigationAction searchAnotherCustomerFromChangeCustomer(String phoneNumber){
		Logger.log("Search for another phone number from Change Customer Popup", TestUtils.TestStepType.WHEN);
		this.factory.profilePage().searchAnotherCustomerFromChangeCustomer(phoneNumber);
		return this;
	}
	public NavigationAction selectCustomerFromChangeCustomerList(int index){
		Logger.log("Select Customer from Change Customer List",TestUtils.TestStepType.WHEN);
		this.factory.profilePage().selectCustomerFromChangeCustomerList(index);
		return this;
	}

	public NavigationAction verifyPhoneIdText(String phoneId) {
		Logger.log("Verify Phone Id text displyed next to Connected Text",TestUtils.TestStepType.THEN);
		this.factory.homePage().verifyPhoneIdText(phoneId);
		return this;
	}
	
	/**
	 * Method for changing the Agent role.
	 * @param role Desired Role.
	 * @param warningPopup Specify 'true' if Phone ID dialog needs is expected to appear.
	 * @return
	 */
	public NavigationAction changeAgentRole(String role,Boolean warningPopup){
		Logger.log("Agent role is changed to "+role,TestUtils.TestStepType.WHEN);
		this.factory.homePage().changeAgentRole(role,warningPopup);
		return this;
	}
	public NavigationAction validateAgentRole(String role){
		Logger.log("Validate the agent role is "+role,TestUtils.TestStepType.THEN);
		this.factory.homePage().validateAgentRole(role);
		return this;
	}
	
	public NavigationAction verifyPhoneIdNotConnected(){
		Logger.log("Verify Phone Id is disconnected", TestUtils.TestStepType.THEN);
		this.factory.homePage().verifyPhoneIdNotConnected();
		return this;
	}
	
	public NavigationAction changeAgentChannel(String channel,Boolean warningPopup){
		Logger.log("Agent channel is changed to "+channel,TestUtils.TestStepType.WHEN);
		this.factory.homePage().changeAgentChannel(channel, warningPopup);
		return this;	
	}

	public NavigationAction verifyCTILogout(String confirm) {
		Logger.log("Verify Agent can Logout when Connected to CTI - "+confirm, TestUtils.TestStepType.THEN);
        this.factory.homePage().verifyCTILogout(confirm);
		return this;
        
	}
	
	public NavigationAction validateAgentChannel(String channel){
		Logger.log("Verify Agent Channel", TestUtils.TestStepType.THEN);
		this.factory.homePage().validateAgentChannel(channel);
		return this;
	}
	public NavigationAction searchBySYWlinkPhoneNumber(String phno) {
		Logger.log("searchBySYWlinkPhoneNumber", TestUtils.TestStepType.THEN);
		this.factory.homePage().searchBySYWlinkPhoneNumber(phno);
		return this;
	}
	public NavigationAction searchBySYWlinkEmail(String email) {
		Logger.log("searchBySYWlinkPhoneNumber", TestUtils.TestStepType.THEN);
		this.factory.homePage().searchBySYWlinkEmail(email);
		return this;
	}
	public NavigationAction deleteCasesforOrderfromDB(String jsonPath,String orderId) {
		Logger.log("Delete existing cases from Database for a "+orderId, TestUtils.TestStepType.WHEN);
		MongoDB.deleteCasesforOrderfromDB(jsonPath,orderId);
		return this;
	}
	 public NavigationAction ClickOnOrderTab() {
	    	Logger.log("Click on the performance support(?) symbol", TestUtils.TestStepType.THEN);
	        this.factory.homePage().ClickOnOrderTab();
	        return this;
	 }
	 public NavigationAction ClickOnPerformanceSupport(String name) {
	    	Logger.log("Click on the performance support(?) symbol", TestUtils.TestStepType.THEN);
	        this.factory.homePage().ClickOnPerformanceSupport(name);
	        return this;
	 }
	 public NavigationAction VerifyPerformanceSupportMessage(String msg) {
	    	Logger.log("Verify the correct message is displayed after clicking on performance support", TestUtils.TestStepType.THEN);
	        this.factory.homePage().VerifyPerformanceSupportMessage(msg);
	        return this;
	 }
	 public NavigationAction ClickOnPerformanceSupportOnOrderSearchResults(String name) {
	    	Logger.log("Click on the performance support(?) symbol", TestUtils.TestStepType.THEN);
	        this.factory.homePage().ClickOnPerformanceSupportOnOrderSearchResults(name);
	        return this;
	 }
		
}

