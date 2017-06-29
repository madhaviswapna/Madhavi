package com.shc.msp.ft.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.FrameworkProperties;
import com.shc.automation.dao.ProductData;
import com.shc.automation.data.TestData;
import com.shc.automation.dataprovider.TestDataProvider;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.Constant;
import com.shc.msp.ft.util.ExcelUtil;
import com.shc.msp.ft.util.Retrieval_Test_Data_By_Query;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class OrderSearch extends BaseTests{
	String url=FrameworkProperties.SELENIUM_BASE_URL;
	@Test(dataProvider = "DP_SearchAll", groups = {TestGroup.MSPP1OnlineTests, "MSP_Search_Tests"}

    , description = "Verify search by name, orderid, phone, email, salescheck and store", enabled = true)
	public void MSP_Search_Tests(String orderId, String firstName, String lastName, String phonenumber, String email, String salesCheckNO,String agentID, String store) throws Exception {
		TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
		addCloneIDHostname(data);
	
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);
		
	    User user = new User();
	    
	    user.userName = agentID;
	    user.password = Constant.OnlinePassword;
	    
	     
	    As.guestUser.goToHomePage()
	    		.addlogType(TestStepType.WHEN)
	            .login(user)
	            .addlogType(TestStepType.THEN)
	            .verifyonlineagent()
	            .addlogType(TestStepType.GIVEN)
	            .searchCustomer()
	            .addlogType(TestStepType.WHEN)
	            .searchByOrderId(orderId)
	            .closeWarningPopupWindow()
	            ._OrderDetailsAction()
	            .addlogType(TestStepType.THEN)
	            .verifyOrderDetailsPageDisplayed()
	            ._NavigationAction()
	            .addlogType(TestStepType.GIVEN)
	            .searchCustomer()
	            .addlogType(TestStepType.WHEN)
	            .searchByName(firstName, lastName)
	            .selectOrderInMyRecentInteractions(1)
	             .closeWarningPopupWindow()
	            ._OrderDetailsAction()
	            .addlogType(TestStepType.THEN)
	            .verifyOrderDetailsPageDisplayed()
	            ._NavigationAction()
	            .addlogType(TestStepType.GIVEN)
	            .searchCustomer()
	            .addlogType(TestStepType.WHEN)
	            .searchByPhone(phonenumber)
	             .selectOrderInMyRecentInteractions(1)
	             .closeWarningPopupWindow()
	            ._OrderDetailsAction()
	            .addlogType(TestStepType.THEN)
	            .verifyOrderDetailsPageDisplayed()
	            ._NavigationAction()
	            .addlogType(TestStepType.GIVEN)
	            .searchCustomer()
	            .addlogType(TestStepType.WHEN)
	            .searchByEmail(email)
	             .selectOrderInMyRecentInteractions(1)
	             .closeWarningPopupWindow()
	            ._OrderDetailsAction()
	            .addlogType(TestStepType.THEN)
	            .verifyOrderDetailsPageDisplayed()
	            ._NavigationAction()
	            .addlogType(TestStepType.GIVEN)
	            .searchCustomer()
	            .addlogType(TestStepType.WHEN)
	            .searchBySalesCheck(salesCheckNO,store)
	            .closeWarningPopupWindow()
	            ._OrderDetailsAction()
	            .addlogType(TestStepType.THEN)
	            .verifyOrderDetailsPageDisplayed();
		
	}
	
	
	/*
	 * Online Agent   
	 */

		@Test(dataProvider = "DP_SearchByOrderID", groups = {TestGroup.MSPP0Tests, "MSP_Online_Agent_Search_By_OrderNumber"}

        , description = "Verify search by order id", enabled = true, priority=1)
		public void MSP_Online_Agent_Search_By_OrderNumber(String orderId,String agentID) throws Exception {
			
			
			User user = new User();
			user.userName = UserPool.getUser();
			
			Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().searchOrder_By_OrderID();
			String OrderID=Retrieval_Test_Data_By_Query.orderID;
		
			
			
			/*TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
			addCloneIDHostname(data);*/
		
			//ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);
			
		    LogFormatterAction.beginSetup();
		     
		    As.guestUser.goToHomePage()
		    		.addlogType(TestStepType.WHEN)
		            .login(user)
		            .closeWarningPopupWindow()
		            .addlogType(TestStepType.WHEN)
		            .searchByOrderId(OrderID)
		            ._OrderDetailsAction()
		            .addlogType(TestStepType.THEN)
		            .verifyOrderDetailsPageDisplayed();
		    
		}
		
		@Test(dataProvider = "DP_SearchByOrderID", groups = {TestGroup.MSPP1OnlineTests, "MSP_Itemcondition"}
        , description = "Verify search by order id", enabled = true, priority=1)
		public void MSP_Itemcondition(String orderId,String agentID) throws Exception {
			
			TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
			addCloneIDHostname(data);
			
			HashMap<String, String> hmap = new HashMap<String, String>();
			 
			/*String[] test_data = getProductToTest("MSP_ItemConditionOrders").split("\\|");
			String OrderID=test_data[0];
			String condition = test_data[1];
			orderId=OrderID;
		    String itemcondition=condition;
		    
			*/
			
		    LogFormatterAction.beginSetup();
		
		    User user = new User();
		    user.userName=UserPool.getUser();
		    
		    orderId="941481206";
		    String itemcondition="Used - Like New";
		   
		     
		    As.guestUser.goToHomePage()
	        .addlogType(TestStepType.WHEN)
	              .login(user)
	              .closeWarningPopupWindow()
	              .addlogType(TestStepType.WHEN)
	              .searchByOrderId(orderId)
	              .closeWarningPopupWindow()
	              ._OrderDetailsAction()
	              .addlogType(TestStepType.THEN)
	              .verifyOrderDetailsPageDisplayed()
	              .verifyOrderDetailsDescription(itemcondition,"1")
	              .clickOnSkuNumberUnderLineItemTab(1)
	              ._LineItemDetailsAction()
	              .addlogType(TestStepType.WHEN)
	              .veryItemConditionInlineItemSummary(1,itemcondition); 
		}
		
		@Test(dataProvider = "DP_SearchBySubOrder",groups = {TestGroup.MSPP0Tests,"MSP_Online_Order_Search_By_SubOrder"}
		, description = "Verify Order search using Suborder Number")
		public void MSP_Online_Order_Search_By_SubOrder(String data) throws Exception {
			User user = new User();
			user.userName = UserPool.getUser();
			
			// Below line has to be substituted with the getproducttotest() statements			
			//String subOrderID = "1410092472"; 
			String subOrderID=data;
			As.guestUser.goToHomePage()
    		.addlogType(TestStepType.WHEN)
            .login(user)
            .closeWarningPopupWindow()
            .addlogType(TestStepType.WHEN)
            .searchBySubOrderId(subOrderID)
            ._OrderDetailsAction()
            .addlogType(TestStepType.THEN)
            .verifyOrderDetailsPageDisplayed();
		}
		
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests,TestGroup.MSPP0SanityProdTests,"MSP_Online_Search_Layaway_By_Contract_Id"}
		, description = "MSP_Online_Search_Layaway_By_Contract_Id")
		public void MSP_Online_Search_Layaway_By_Contract_Id(TestData data) throws Exception {
			String contractId = null;
			User user = new User();
			contractId = getProductToTest("MSP_Layaway_Contract_ID");
		
			if(url.contains("msp.prod.global")){
				user.userName = "testonline0509";
				user.password = Constant.OnlinePasswordProd;
		    }else{
		    	user.userName = UserPool.getUser();
		    }

			As.guestUser.goToHomePage()
    		.addlogType(TestStepType.WHEN)
            .login(user)
            .closeWarningPopupWindow()
            .addlogType(TestStepType.WHEN)
            .searchByLayawayContractID(contractId)
            ._OrderDetailsAction()
            .addlogType(TestStepType.WHEN)
            .verifyLayawayDetailsPageDisplayed();
		}
		
		@Test(dataProvider = "TestData", dataProviderClass = TestDataProvider.class,groups = {TestGroup.MSPP0Tests,"MSP_Online_Search_Layaway_By_Phone_Number"}
		, description = "MSP_Online_Search_Layaway_By_Phone_Number")
		public void MSP_Online_Search_Layaway_By_PhoneNumber(TestData data) throws Exception {
			User user = new User();
			String phNumber= getProductToTest("MSP_Layaway_Phone_Number");
			//String phNumber= "9876543210";
		
			System.out.println("-----------------------------------------"+phNumber);
			
			if(url.contains("msp.prod.global")){
				user.userName = "testonline0509";
				user.password = Constant.OnlinePasswordProd;
				//phNumber = "9103311499";
		    }else{
		    	user.userName = UserPool.getUser();
		    }
			
			As.guestUser.goToHomePage()
    		.addlogType(TestStepType.WHEN)
            .login(user)
            .closeWarningPopupWindow()
            .addlogType(TestStepType.WHEN)
            .searchByLayawayphoneNumber(phNumber)
            .addlogType(TestStepType.WHEN)
            .selectLayawayOrderFromSearchResults(1)
            ._OrderDetailsAction()
            .addlogType(TestStepType.WHEN)
            .verifyLayawayDetailsPageDisplayed();
		}
		
		@Test(dataProvider = "DP_SearchByFName_LName", groups = {TestGroup.MSPP0Tests, "MSP_Online_Agent_Search_By_Name"}
	            , description = "Verify search by name", enabled = true, priority=2)
		      
		public void MSP_Online_Agent_Search_By_Name(String firstName, String lastName,String agentID) throws Exception {
			TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
			addCloneIDHostname(data);
			 
    		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);
    	
	        LogFormatterAction.beginSetup();
	        User user = new User();
	        user.userName = agentID;
	        user.password = Constant.OnlinePassword;
	        
	        String name=getSearchTermToTest("MSP_MemberSearch_By_FirstAndLastName");
	        String[] arr=name.split(" ");
	        firstName=arr[0];
	        lastName=arr[1];
	        
	        As.guestUser.goToHomePage()
	        	.addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .closeWarningPopupWindow()
	                .addlogType(TestStepType.WHEN)
	                .searchByName(firstName, lastName)
	                .selectOrderInMyRecentInteractions(1)
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed();
	    	
	    }

	
		@Test(dataProvider = "DP_SearchByOrderID",	groups = {TestGroup.MSPP0Tests, "MSP_Online_Agent_Search_SalesCheck"}
	            , description = "Verify search by salescheck", enabled = true, priority=3)
	
	    public void MSP_Online_Agent_Search_SalesCheck(String salesCheck,String store) throws Exception {
			TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
			addCloneIDHostname(data);
	
    		//ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);
    		
    		Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().searchOrder_By_OrderID();
			String salescheckID=Retrieval_Test_Data_By_Query.salescheckID;
    		
    		
	        LogFormatterAction.beginSetup();
	        
	        User user = new User();
			user.userName = UserPool.getUser();
  
	        As.guestUser.goToHomePage()
	        		.addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .closeWarningPopupWindow()
	                .addlogType(TestStepType.WHEN)
	                .searchBySalesCheck(salescheckID,"40153")
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
		            .verifyOrderDetailsPageDisplayed();
	    }
	    
		@Test(dataProvider = "DP_SearchByPhone", groups = {TestGroup.MSPP0Tests, "MSP_Online_Agent_Search_Phone"}
	            , description = "Verify agent search by phone number", enabled = true, priority=4)
	    
		public void MSP_Online_Agent_Search_Phone(String phoneNumber,String agentID) throws Exception {
			TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
			addCloneIDHostname(data);
		    	
    		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);
    		
    		
    		phoneNumber=getProductToTest("MSP_OnlineAgent_Phone_Number");
	        LogFormatterAction.beginSetup();
	        
	        User user = new User();
	        user.userName = agentID;
	        user.password = Constant.OnlinePassword;
        
	        As.guestUser.goToHomePage()
	        	.addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .closeWarningPopupWindow()
	                .addlogType(TestStepType.WHEN)
	                .searchByPhone(phoneNumber)
	                .selectOrderInMyRecentInteractions(1)
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed() ;
		    	
	    }
	    
	    
	    @Test(dataProvider = "DP_SearchByEmail", groups = {TestGroup.MSPP0Tests, "MSP_Online_Agent_Search_Email"}
	            , description = "Verify order search by email", enabled = true, priority=5)
	    
	    public void MSP_Online_Agent_Search_Email(String email,String agentID) throws Exception {
			TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
			addCloneIDHostname(data);
		    	
	    	ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);
	    	
	    	//String phoneNumber=getProductToTest("MSP_OnlineAgent_EmailID");
	    	String email1=getSearchTermToTest("MSP_OnlineAgent_EmailID");
	 
	        LogFormatterAction.beginSetup();

	        User user = new User();
	        user.userName = agentID;
	        user.password = Constant.OnlinePassword;
	        
	        As.guestUser.goToHomePage()
	        		.addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .closeWarningPopupWindow()
	                .searchByEmail(email1)
	                .addlogType(TestStepType.WHEN)
	                .selectOrderInMyRecentInteractions_SearchByEmail(1)
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed() ;
	    	
	    }
	    private void getSeachTearm() {
			// TODO Auto-generated method stub
			
		}


		@Test(dataProvider = "TestData",dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPP1Tests,"Verify_MSP_Online_SYWMax_ActiveUser_WithMaxSavings"}
	    , description = "Verify_MSP_Online_SYWMax_ActiveUser_WithMaxSavings", enabled = true)
	  
		public void Verify_MSP_Online_SYWMax_ActiveUser_WithMaxSavings(TestData data) throws Exception {
			addCloneIDHostname(data);
			
			Retrieval_Test_Data_By_Query.SYW_Max_Member_OrderWithMaxSavings();
			String orderId = Retrieval_Test_Data_By_Query.SYW_Max_orderIdWithMaxSaving;
			String amount = Retrieval_Test_Data_By_Query.SYW_Max_orderIdMaxSavingAmount;
			
			LogFormatterAction.beginSetup();
			User user = new User();
			user.userName = UserPool.getUser();
		    
		    As.guestUser.goToHomePage()
			.addlogType(TestStepType.WHEN)
	        .login(user)
	        .closeWarningPopupWindow()
	        .addlogType(TestStepType.WHEN)
	        .searchByOrderId(orderId)
	        ._OrderDetailsAction()
	        .addlogType(TestStepType.THEN)
	        .clickSYWMaxTabandVerify("active")
	        .addlogType(TestStepType.THEN)
	        .verifySYWMaxTabSavingsAmount(amount);
		}
	    
		@Test(dataProvider = "TestData",dataProviderClass = TestDataProvider.class, groups = {TestGroup.MSPP1Tests,"Verify_MSP_Online_SYWMax_InActiveUser"}
	    , description = "Verify search by order id", enabled = true, priority=1)
		public void Verify_MSP_Online_SYWMax_InActiveUser(TestData data) throws Exception {
			
			addCloneIDHostname(data);
			
			Retrieval_Test_Data_By_Query.NonSYW_Max_Member_Order();
			String orderId = Retrieval_Test_Data_By_Query.NonSYW_Max_Member_OrderId;
			
			LogFormatterAction.beginSetup();
			
			User user = new User();
			user.userName = UserPool.getUser();
		    
		    
		    As.guestUser.goToHomePage()
			.addlogType(TestStepType.WHEN)
	        .login(user)
	        .closeWarningPopupWindow()
	        .addlogType(TestStepType.WHEN)
	        .searchByOrderId(orderId)
	        ._OrderDetailsAction()
	        .addlogType(TestStepType.THEN)
	        .clickSYWMaxTabandVerify("inactive");
		}
		
		@Test(dataProvider = "DP_SearchByOrderID2", groups = {TestGroup.MSPP0Tests, "SearchmemberBySYWlinkPhonenumber"}
	    , description = "SearchmemberBySYWlinkPhonenumber", enabled = true, priority=1)
		
		public void SearchmemberBySYWlinkPhonenumber(String orderId,String agentID) throws Exception {
			TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
			addCloneIDHostname(data);
		
			String phno="8888888888";
			LogFormatterAction.beginSetup();
			
		    User user = new User();
			user.userName = UserPool.getUser();
			
		    As.guestUser.goToHomePage()
			.addlogType(TestStepType.WHEN)
	        .login(user)
	        .closeWarningPopupWindow()
	        .addlogType(TestStepType.GIVEN)
	        .searchBySYWlinkPhoneNumber(phno)
	        ._OrderDetailsAction()
	        .addlogType(TestStepType.THEN)
	        .verifySywLinkDetailsPageDisplayed();
	        
		}
		//searchBySYWlinkEmail
		
		@Test(dataProvider = "DP_SearchByOrderID2", groups = {TestGroup.MSPP0Tests, "SearchmemberBySYWlinkEmail"}
	    , description = "SearchmemberBySYWlinkEmail", enabled = true, priority=1)
		
		public void SearchmemberBySYWlinkEmail(String orderId,String agentID) throws Exception {
			TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
			addCloneIDHostname(data);
	 
			//ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSearch);
			String email="sarika.patil@searshc.com";
			LogFormatterAction.beginSetup();
			
			User user = new User();
			user.userName = agentID;
		    user.password = Constant.OnlinePassword;

		    As.guestUser.goToHomePage()
			.addlogType(TestStepType.WHEN)
	        .login(user)
	        .closeWarningPopupWindow()
	        .addlogType(TestStepType.GIVEN)
	        .searchBySYWlinkEmail(email)
	        ._OrderDetailsAction()
	        .addlogType(TestStepType.THEN)
	        .verifySywLinkDetailsPageDisplayed();
	       
		}
		@DataProvider (name="DP_SearchByOrderID2",parallel=true)
	    public Object[][] DP_SearchByOrderID2() throws Exception{
			Object[][] testData =null;
			if(url.contains("msp.prod.global")){
				testData = ExcelUtil.getExcelUtil().getDataArray("OrderSearchProd",Constant.StartRow_SearchOrder, 
	        		 Constant.StartColumn_SearchByOrderIDProd, Constant.TotalCols_SearchByOrderIDProd);
			}else{
				ArrayList<String> orderID = ExcelUtil.getExcelUtil().searchKeyWord("OrderID3", Constant.OrderSearch, 
						Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
				ArrayList<String> agentID = ExcelUtil.getExcelUtil().searchKeyWord("OrderID3", Constant.OrderSearch, 
						Constant.StartColumn_AgentID, Constant.TotalCols_SearchByOrderID);
				testData=new String[orderID.size()][2];
				 for (int i=0;i<orderID.size();i++) { 
					   testData[i][0]=orderID.get(i).trim();
					   testData[i][1]=agentID.get(i).trim();
					   System.out.println("testing data "+(i+1)+": "+testData[i][0]+","+testData[i][1]);
					}
			}
	         return testData;
			}
	    
	 	@DataProvider (name="DP_SearchByOrderID",parallel=true)
	    public Object[][] DP_SearchByOrderID() throws Exception{
			Object[][] testData =null;
			
			if(url.contains("msp.prod.global")){
				testData = ExcelUtil.getExcelUtil().getDataArray("OrderSearchProd",Constant.StartRow_SearchOrder, 
	        		 Constant.StartColumn_SearchByOrderIDProd, Constant.TotalCols_SearchByOrderIDProd);
			}else{
				ArrayList<String> orderID = ExcelUtil.getExcelUtil().searchKeyWord("OrderID2", Constant.OrderSearch, 
						Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
				ArrayList<String> agentID = ExcelUtil.getExcelUtil().searchKeyWord("OrderID2", Constant.OrderSearch, 
						Constant.StartColumn_AgentID, Constant.TotalCols_SearchByOrderID);
				testData=new String[orderID.size()][2];
				 for (int i=0;i<orderID.size();i++) { 

					 testData[i][0]=orderID.get(i).trim();
					 testData[i][1]=agentID.get(i).trim();
					   System.out.println("testing data "+(i+1)+": "+testData[i][0]+","+testData[i][1]);

					  }
			
			}
	         return testData;
	 
			}
		
		@DataProvider (name="DP_SearchBySalesCheckNo",parallel=true)
	    public Object[][] DP_SearchBySalesCheckNo() throws Exception{
			Object[][] testData =null;
			ArrayList<String> salesCheckNo = ExcelUtil.getExcelUtil().searchKeyWord("SalesCheckNo", Constant.OrderSearch,
					Constant.StartColumn_SearcByhSC,Constant.TotalCols_SearchBySC);
			ArrayList<String> agentID = ExcelUtil.getExcelUtil().searchKeyWord("SalesCheckNo", Constant.OrderSearch, 
					Constant.StartColumn_AgentID, Constant.TotalCols_SearchByOrderID);
			ArrayList<String> storeID = ExcelUtil.getExcelUtil().searchKeyWord("OrderSummary", Constant.OrderSearch, 
					Constant.StartColumn_StoreID, Constant.TotalCols_SearchByOrderID);
			testData=new String[salesCheckNo.size()][3];
			 for (int i=0;i<salesCheckNo.size();i++) { 

				 testData[i][0]=salesCheckNo.get(i).trim();
				 testData[i][1]=agentID.get(i).trim();
				 testData[i][2]=storeID.get(i).trim();
				   System.out.println("testing data "+(i+1)+": "+testData[i][0]+","+testData[i][1]+","+testData[i][2]);

				  }
			
	         return (testData);
	 
			}
		
		@DataProvider(name="DP_SearchByFName_LName",parallel=true) 
	    public Object[][] DP_SearchByFName_LName() throws Exception{
			ArrayList<String> firstname = ExcelUtil.getExcelUtil().searchKeyWord("CustomerName", Constant.OrderSearch,
					Constant.StartColumn_SearchByFName,Constant.TotalCols_SearchByName);
			ArrayList<String> lastname = ExcelUtil.getExcelUtil().searchKeyWord("CustomerName", Constant.OrderSearch,
						Constant.StartColumn_SearchByLName,Constant.TotalCols_SearchByName);
			ArrayList<String> agentID = ExcelUtil.getExcelUtil().searchKeyWord("CustomerName", Constant.OrderSearch, 
					Constant.StartColumn_AgentID, Constant.TotalCols_SearchByOrderID);
			String[][] dataArray = null;
			dataArray=new String[firstname.size()][3];
			 
			 for (int i=0;i<firstname.size();i++) { 

				   dataArray[i][0]=firstname.get(i).trim();
				   dataArray[i][1]=lastname.get(i).trim();
				   dataArray[i][2]=agentID.get(i).trim();
				   System.out.println("testing data "+(i+1)+": "+dataArray[i][0]+","+dataArray[i][1]+","+dataArray[i][2]);

				  }
			
				return dataArray;
		   
		}
		
		@DataProvider(name="DP_SearchByFName_LNameSpecT",parallel=true) 
	    public Object[][] DP_SearchByFName_LNameSpecT() throws Exception{
			ArrayList<String> firstname = ExcelUtil.getExcelUtil().searchKeyWord("CustomerNameSpec", Constant.OrderSearch,
					Constant.StartColumn_SearchByFName,Constant.TotalCols_SearchByName);
			ArrayList<String> lastname = ExcelUtil.getExcelUtil().searchKeyWord("CustomerNameSpec", Constant.OrderSearch,
						Constant.StartColumn_SearchByLName,Constant.TotalCols_SearchByName);
			String[][] dataArray = null;
			dataArray=new String[firstname.size()][2];
			 
			 for (int i=0;i<firstname.size();i++) { 

				   dataArray[i][0]=firstname.get(i).trim();
				   dataArray[i][1]=lastname.get(i).trim();
				   System.out.println("testing data "+(i+1)+": "+dataArray[i][0]+","+dataArray[i][1]);

				  }
			
				return dataArray;
		   
		}
		

		@DataProvider (name="DP_SearchByEmail",parallel=true) 
	    public Object[][] DP_SearchByEmail() throws Exception{
			Object[][] testData =null;
			ArrayList<String> email = ExcelUtil.getExcelUtil().searchKeyWord("Email", Constant.OrderSearch,
					Constant.StartColumn_SearchByEmail,Constant.TotalCols_SearchByEmail);
			ArrayList<String> agentID = ExcelUtil.getExcelUtil().searchKeyWord("Email", Constant.OrderSearch, 
					Constant.StartColumn_AgentID, Constant.TotalCols_SearchByOrderID);
			testData=new String[email.size()][2];
			 for (int i=0;i<email.size();i++) { 

				 testData[i][0]=email.get(i).trim();
				 testData[i][1]=agentID.get(i).trim();
				   System.out.println("testing data "+(i+1)+": "+testData[i][0]+","+testData[i][1]);

				  }
	         return (testData);
	 
			}
		
		@DataProvider (name="DP_SearchByEmailSpecT",parallel=true) 
	    public Object[][] DP_SearchByEmailSpecT() throws Exception{
			ArrayList<String> searchrows = ExcelUtil.getExcelUtil().searchKeyWord("EmailSpec", Constant.OrderSearch,
					Constant.StartColumn_SearchByEmail,Constant.TotalCols_SearchByEmail);
	         Object[][] testData = ExcelUtil.getExcelUtil().getFilterDataArray(searchrows,Constant.TotalCols_SearchByEmail);
	 
	         return (testData);
	 
			}

		@DataProvider (name="DP_SearchByPhone",parallel=true) 
	    public Object[][] DP_SearchByPhone() throws Exception{
			Object[][] testData =null;
			ArrayList<String> phoneNo = ExcelUtil.getExcelUtil().searchKeyWord("PhoneNumber", Constant.OrderSearch,
					Constant.StartColumn_SearchByPhone,Constant.TotalCols_SearchByPhone);
			ArrayList<String> agentID = ExcelUtil.getExcelUtil().searchKeyWord("PhoneNumber", Constant.OrderSearch, 
					Constant.StartColumn_AgentID, Constant.TotalCols_SearchByOrderID);
			testData=new String[phoneNo.size()][2];
			 for (int i=0;i<phoneNo.size();i++) { 

				 testData[i][0]=phoneNo.get(i).trim();
				 testData[i][1]=agentID.get(i).trim();
				   System.out.println("testing data "+(i+1)+": "+testData[i][0]+","+testData[i][1]);

				  }
	         return (testData);
	 
			}

		@DataProvider (name="DP_SearchByPhoneSpecT",parallel=true) 
	    public Object[][] DP_SearchByPhoneSpecT() throws Exception{
			ArrayList<String> searchrows = ExcelUtil.getExcelUtil().searchKeyWord("PhoneNumberSpec", Constant.OrderSearch,
					Constant.StartColumn_SearchByPhone,Constant.TotalCols_SearchByPhone);
	         Object[][] testData = ExcelUtil.getExcelUtil().getFilterDataArray(searchrows, Constant.TotalCols_SearchByPhone);
	 
	         return (testData);
	 
			}
		@DataProvider (name="DP_SearchAll, parallel=true")
		public Object[][] DP_SearchAll() throws Exception{
			ArrayList<String> orderID = ExcelUtil.getExcelUtil().searchKeyWord("OrderID2", Constant.OrderSearch, 
					Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
			ArrayList<String> firstName = ExcelUtil.getExcelUtil().searchKeyWord("CustomerName", Constant.OrderSearch,
					Constant.StartColumn_SearchByFName,Constant.TotalCols_SearchByName);
			ArrayList<String> lastName = ExcelUtil.getExcelUtil().searchKeyWord("CustomerName", Constant.OrderSearch,
						Constant.StartColumn_SearchByLName,Constant.TotalCols_SearchByName);
			 ArrayList<String> phoneNumber = ExcelUtil.getExcelUtil().searchKeyWord("PhoneNumber", Constant.OrderSearch,
						Constant.StartColumn_SearchByPhone,Constant.TotalCols_SearchByPhone);
			 ArrayList<String> email = ExcelUtil.getExcelUtil().searchKeyWord("Email", Constant.OrderSearch,
						Constant.StartColumn_SearchByEmail,Constant.TotalCols_SearchByEmail);
			 ArrayList<String> salesCheckNo = ExcelUtil.getExcelUtil().searchKeyWord("SalesCheckNo", Constant.OrderSearch,
						Constant.StartColumn_SearcByhSC,Constant.TotalCols_SearchBySC);
			 ArrayList<String> agentID = ExcelUtil.getExcelUtil().searchKeyWord("OrderID2", Constant.OrderSearch, 
						Constant.StartColumn_AgentID, Constant.TotalCols_SearchByOrderID);
			 ArrayList<String> storeID = ExcelUtil.getExcelUtil().searchKeyWord("SalesCheckNo", Constant.OrderSearch, 
						Constant.StartColumn_StoreID, Constant.TotalCols_SearchByOrderID);
			 ArrayList<Integer> dpsize = new ArrayList<Integer>();
			 String[][] testData = null;
			 dpsize.add(orderID.size());
			 dpsize.add(firstName.size());
			 dpsize.add(phoneNumber.size());
			 dpsize.add(email.size());
			 dpsize.add(salesCheckNo.size());
			 dpsize.add(agentID.size());
			 int rowNum = dpsize.get(0);

			 for (int i = 1; i < dpsize.size(); i++) {
			     if (dpsize.get(i) > rowNum) {
			    	 rowNum = dpsize.get(i);
			     }
			 }
			 testData = new String[rowNum][8];
			 for (int i=0;i<orderID.size();i++) { 

				 testData[i][0]=orderID.get(i).trim();
				 System.out.println("Order ID "+(i+1)+": "+testData[i][0]);
				  }
			 for (int i=0;i<firstName.size();i++) { 

				 testData[i][1]=firstName.get(i).trim();
				 testData[i][2]=lastName.get(i).trim();
				 System.out.println("Customer Name "+(i+1)+": "+testData[i][1]+","+testData[i][2]);
				  }
			 for (int i=0;i<phoneNumber.size();i++) { 

				 testData[i][3]=phoneNumber.get(i).trim();
				 System.out.println("Phone Number "+(i+1)+": "+testData[i][3]);
				  }
			 for (int i=0;i<email.size();i++) { 

				 testData[i][4]=email.get(i).trim();
				 System.out.println("Email "+(i+1)+": "+testData[i][4]);
				  }
			 for (int i=0;i<salesCheckNo.size();i++) { 

				 testData[i][5]=salesCheckNo.get(i).trim();
				 System.out.println("SalesCheckNo "+(i+1)+": "+testData[i][5]);
				  }
			 for (int i=0;i<agentID.size();i++) { 

				 testData[i][6]=agentID.get(i).trim();
				 System.out.println("Agent ID "+(i+1)+": "+testData[i][6]);
				  }
			 for (int i=0;i<storeID.size();i++) { 

				 testData[i][7]=storeID.get(i).trim();
				 System.out.println("Store ID "+(i+1)+": "+testData[i][7]);
				  }
			 
		         return (testData);
		 
		}
		
		@DataProvider (name="DP_SearchBySubOrder",parallel=true)
		public Object[][] DP_SearchBySubOrder() throws Exception{
			Retrieval_Test_Data_By_Query.getRetrievalTestDataByQuery().searchOrder_By_SubOrder();
			String OrderID[]={Retrieval_Test_Data_By_Query.subOrderID};
			System.out.println("----------------------------------------suborder[]:"+OrderID[0]);
			Object testData[][] = {OrderID};
			System.out.println("----------------------------------------test data:OrderID[]:"+testData[0][0]);
			return (testData);		
		}
		
}
