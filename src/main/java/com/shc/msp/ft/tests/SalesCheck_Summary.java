package com.shc.msp.ft.tests;

import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
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

public class SalesCheck_Summary extends BaseTests{
	
	TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
	
	
	@Test(dataProvider = "DP_SearchByOrderID1",groups = {TestGroup.MSPP0Tests,TestGroup.MSPSalesCheckSummary, "MSPSalesCheckSummaryTests"}
    , description = "Verify sales check summary section", enabled = true, priority =15)
	public void salesCheckSummaryVerify(String OrderID) throws Exception {
			addCloneIDHostname(data);
	        User user = new User(); user.userName=UserPool.getUser();         
	        As.guestUser.goToHomePage()
	        		.addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.WHEN)
	                .clickOnSalesCheckNumberUnderSalesCheckTab(1)
	                ._SalesCheckDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.verifySalesCheckSummary(OrderID);
	}
	
	@Test(dataProvider = "DP_SearchByOrderID2",groups = {TestGroup.MSPP0Tests,TestGroup.MSPSalesCheckSummary, "MSPSalesCheckDeatailTests"}
    , description = "Verify sales check detail section", enabled = true, priority =16)
	public void salesCheckSummaryDetailVerify(String OrderID) throws Exception{
			addCloneIDHostname(data);
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	        		.addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.WHEN)
	                .clickOnSalesCheckNumberUnderSalesCheckTab(1)
	                ._SalesCheckDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.salesCheckDetailVerify(OrderID);
	}

	@Test(dataProvider = "DP_StorePOStoWEB_Information",groups = {TestGroup.MSPSalesCheckSummary, "MSPSalesCheckstorePOStoWEBTests"}
    , description = "Verify Store POS to Web flow", enabled = true, priority =17)
	public void salesCheckSummarystorePOStoWEBVerify(String OrderID,String scNO) throws Exception{
			addCloneIDHostname(data);
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	        		.addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.WHEN)
	                .clickOnSalesCheckNumberUnderSalesCheckTab(scNO)
	                ._SalesCheckDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.storePOStoWEBVerify();
	}
	

	@Test(dataProvider = "DP_SearchByOrderID4",groups = {TestGroup.MSPP0Tests,TestGroup.MSPSalesCheckSummary, "MSPSalesCheckContactInfoTests"}
    , description = "Verify contact information for salescheck", enabled = true, priority =19)
	public void salesCheckSummaryContactInfoVerify(String OrderID) throws Exception{
			addCloneIDHostname(data);
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	        		.addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.WHEN)
	                .clickOnSalesCheckNumberUnderSalesCheckTab(1)
	                ._SalesCheckDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.salesCheckContactInfoVerify(OrderID);
	}
	
	@Test(dataProvider = "DP_Return_Gift_Card_Information",groups = {TestGroup.MSPP0Tests, "MSPSalesCheckReturnGiftCardInfoTests"}
	, description = "Verify return gift card information", enabled = true, priority =20)
	public void salesCheckSummaryverifyReturnGiftCardInformation(String OrderID, String scNO) throws Exception{
			addCloneIDHostname(data);
		    User user = new User(); user.userName=UserPool.getUser();
		    As.guestUser.goToHomePage()
		    		.addlogType(TestStepType.WHEN)
		            .login(user)
		            .addlogType(TestStepType.THEN)
		            .verifyonlineagent()
		            .addlogType(TestStepType.WHEN)
		            .searchByOrderId(OrderID)
		            ._OrderDetailsAction()
		            .addlogType(TestStepType.THEN)
		            .verifyOrderDetailsPageDisplayed()
		            .addlogType(TestStepType.WHEN)
		            .clickOnSalesCheckNumberUnderSalesCheckTab(scNO)
		            ._SalesCheckDetailsAction()
		            .addlogType(TestStepType.THEN)
		    		.verifyReturnGiftCardInformation(OrderID);

}
	
	@Test(dataProvider = "DP_Discount_Eligible_orderID",groups = {TestGroup.MSPSalesCheckSummary, "MSPSalesCheckDiscountsTests"}
    , description = "Verify discounts in sales check", enabled = true, priority =21)
	public void salesCheckSummaryverifyDiscounts(String OrderID, String scNO) throws Exception{
			addCloneIDHostname(data);
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	        		.addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.WHEN)
	                .clickOnSalesCheckNumberUnderSalesCheckTab(scNO)
	                ._SalesCheckDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.verifyDiscounts();

	}
	
	@Test(dataProvider = "DP_Delivery_Details",groups = {TestGroup.MSPP0Tests, "MSPSalesCheckDeliveryDetailsTests"}
	, description = "Verify delivery details for salescheck", enabled = true, priority =22)
	public void salesCheckSummaryverifyDeliveryDetails(String OrderID,String scNO) throws Exception{
			addCloneIDHostname(data);
		    User user = new User(); user.userName=UserPool.getUser();
		    As.guestUser.goToHomePage()
		    	.addlogType(TestStepType.WHEN)
		            .login(user)
		            .addlogType(TestStepType.THEN)
		            .verifyonlineagent()
		            .addlogType(TestStepType.WHEN)
		            .searchByOrderId(OrderID)
		            ._OrderDetailsAction()
		            .addlogType(TestStepType.THEN)
		            .verifyOrderDetailsPageDisplayed()
		            .addlogType(TestStepType.WHEN)
		            .clickOnSalesCheckNumberUnderSalesCheckTab(scNO)
		            ._SalesCheckDetailsAction()
		            .addlogType(TestStepType.THEN)
		    		.verifyDeliveryDetails(OrderID);
		
		}

	@Test(dataProvider = "TestData",dataProviderClass = TestDataProvider.class,
	groups = {TestGroup.QA_Environment, "MSPSalesCheckPaymentsTests"}
	, description = "Verify SOAP request response for salescheck", enabled = true)
	public void MSP_Verify_SOAPrequest_SalesCheck(TestData data) throws Exception{
			addCloneIDHostname(data);
			ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_TestData + Constant.File_TestData,Constant.OrderSummary);
			String OrderID = ExcelUtil.getExcelUtil().getCellData(1, 0);
			String storeId = ExcelUtil.getExcelUtil().getCellData(1, 1);
			
		    User user = new User(); user.userName=UserPool.getUser();
		     
		    As.guestUser.goToHomePage()
		    		.addlogType(TestStepType.WHEN)
		            .login(user)
		            .addlogType(TestStepType.THEN)
		            .verifyonlineagent()
		            .addlogType(TestStepType.WHEN)
		            .searchByOrderId(OrderID)
		            ._OrderDetailsAction()
		            .addlogType(TestStepType.THEN)
		            .verifyOrderDetailsPageDisplayed()
		            .addlogType(TestStepType.WHEN)
		            .clickOnSalesCheckNumberUnderSalesCheckTab(1)
		            ._SalesCheckDetailsAction()
		            .addlogType(TestStepType.THEN)
		    		.verifySoapDetailsSalesCheck(OrderID,storeId)
		    	; 
			}
			
	
	@Test(dataProvider = "DP_Payment",groups = {TestGroup.MSPSalesCheckSummary, "MSPSalesCheckPaymentsTests"}
	, description = "Verify payments in salescheck summary", enabled = true, priority =23)
	public void salesCheckSummaryverifyPayments(String OrderID, String storeId) throws Exception{
			addCloneIDHostname(data);
		
		    User user = new User(); user.userName=UserPool.getUser();
		     
		    As.guestUser.goToHomePage()
		    		.addlogType(TestStepType.WHEN)
		            .login(user)
		            .addlogType(TestStepType.THEN)
		            .verifyonlineagent()
		            .addlogType(TestStepType.WHEN)
		            .searchByOrderId(OrderID)
		            ._OrderDetailsAction()
		            .addlogType(TestStepType.THEN)
		            .verifyOrderDetailsPageDisplayed()
		            .addlogType(TestStepType.WHEN)
		            .clickOnSalesCheckNumberUnderSalesCheckTab(1)
		            ._SalesCheckDetailsAction()
		            .addlogType(TestStepType.THEN)
		    		.verifyPayments(OrderID,storeId);
		
		}
	
	@Test(dataProvider = "DP_SearchByOrderID2",groups = {TestGroup.MSPSalesCheckSummary, "MSPSalesCheckPaymentsTests"}
			, description = "Verify SARA link in salescheck", enabled = true, priority =24)
			public void salesCheckSummaryverifySaralink(String OrderID) throws Exception{
					addCloneIDHostname(data);
				    User user = new User(); user.userName=UserPool.getUser();
				    As.guestUser.goToHomePage()
				    		.addlogType(TestStepType.WHEN)
				            .login(user)
				            .addlogType(TestStepType.THEN)
				            .verifyonlineagent()
				            .addlogType(TestStepType.WHEN)
				            .searchByOrderId(OrderID)
				            ._OrderDetailsAction()
				            .addlogType(TestStepType.THEN)
				            .verifyOrderDetailsPageDisplayed()
				            .addlogType(TestStepType.WHEN)
				            .clickOnSalesCheckNumberUnderSalesCheckTab(1)
				            ._SalesCheckDetailsAction()
				            .addlogType(TestStepType.THEN)
				    		.saraLinkVerify();
				
				}
	
	@Test(dataProvider = "DP_Delivery_Details",groups = {TestGroup.QA_Environment, "MSPSalesCheckDeliveryDetailsTests"}
	, description = "Verify DDC fulfillment in salescheck summary page", enabled = true)
	public void MSP_Verify_SalesCheck_DDC_Fulfillment (String OrderID,String scNO) throws Exception{
			addCloneIDHostname(data);		
		    User user = new User(); user.userName=UserPool.getUser();
		    As.guestUser.goToHomePage()
		    		.addlogType(TestStepType.WHEN)
		            .login(user)
		            .addlogType(TestStepType.THEN)
		            .verifyonlineagent()
		            .addlogType(TestStepType.WHEN)
		            .searchByOrderId(OrderID)
		            ._OrderDetailsAction()
		            .addlogType(TestStepType.THEN)
		            .verifyOrderDetailsPageDisplayed()
		            .addlogType(TestStepType.WHEN)
		            .clickOnSalesCheckNumberUnderSalesCheckTab(scNO)
		            .addlogType(TestStepType.THEN)
		            .verifyDDCfulfillment(); 
			}
		
	
	 @DataProvider (name="DP_Payment",parallel=true)
	    public Object[][] DP_Payment() throws Exception{
		 ArrayList<String> storeID = ExcelUtil.getExcelUtil().searchKeyWord("SalesCheckSummary5", Constant.OrderSearch, 
					Constant.StartColumn_StoreID, Constant.TotalCols_SearchByOrderID);
		 ArrayList<String> orderID = ExcelUtil.getExcelUtil().searchKeyWord("SalesCheckSummary5", Constant.OrderSearch, 
					Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
		 Object testData[][] = ExcelUtil.getExcelUtil().getStoreid_Orderid_DataArray(orderID, storeID, Constant.OrderSearch);
	         return (testData);
			}
	 
	 @DataProvider (name="DP_SearchByOrderID1",parallel=true)
	    public Object[][] DP_SearchByOrderID1() throws Exception{
			Object[][] testData =null;
			ArrayList<String> searchrows = ExcelUtil.getExcelUtil().searchKeyWord("SalesCheckSummary1", Constant.OrderSearch, 
					Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
			testData = ExcelUtil.getExcelUtil().getFilterDataArray(searchrows, Constant.TotalCols_SearchByOrderID);
	         return (testData);
			}
	 @DataProvider (name="DP_SearchByOrderID2",parallel=true)
	    public Object[][] DP_SearchByOrderID2() throws Exception{
			Object[][] testData =null;
			ArrayList<String> searchrows = ExcelUtil.getExcelUtil().searchKeyWord("SalesCheckSummary2", Constant.OrderSearch, 
					Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
			testData = ExcelUtil.getExcelUtil().getFilterDataArray(searchrows, Constant.TotalCols_SearchByOrderID);
	         return (testData);
			}
	 @DataProvider (name="DP_SearchByOrderID3",parallel=true)
	    public Object[][] DP_SearchByOrderID3() throws Exception{
			Object[][] testData =null;
			ArrayList<String> searchrows = ExcelUtil.getExcelUtil().searchKeyWord("SalesCheckSummary3", Constant.OrderSearch, 
					Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
			testData = ExcelUtil.getExcelUtil().getFilterDataArray(searchrows, Constant.TotalCols_SearchByOrderID);
	         return (testData);
			}
	 @DataProvider (name="DP_SearchByOrderID4",parallel=true)
	    public Object[][] DP_SearchByOrderID4() throws Exception{
			Object[][] testData =null;
			ArrayList<String> searchrows = ExcelUtil.getExcelUtil().searchKeyWord("SalesCheckSummary4", Constant.OrderSearch, 
					Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
			testData = ExcelUtil.getExcelUtil().getFilterDataArray(searchrows, Constant.TotalCols_SearchByOrderID);
	         return (testData);
			}
	 @DataProvider (name="DP_Discount_Eligible_orderID")
	    public Object[][] DP_Discount_Eligible_orderID() throws Exception{
		 Retrieval_Test_Data_By_Query.Discount_OrderID();
	  String orderID[]={Retrieval_Test_Data_By_Query.hasDiscount_OrderID};
	  String scNO[]={Retrieval_Test_Data_By_Query.hasDiscount_SCNO};
	  Object[][] testData = new Object[1][2];
		 testData[0][0]= orderID[0];
		 testData[0][1]= scNO[0];
				
	         return (testData);
	 		}
	 @DataProvider (name="DP_Return_Gift_Card_Information",parallel=true)
	    public Object[][] DP_Return_Gift_Card_Information() throws Exception{
		 Retrieval_Test_Data_By_Query.returnGC_OrderID();
		 String [] orderID = {Retrieval_Test_Data_By_Query.hasReturnGiftCardInfo_OrderID};
		 String [] scNO = {Retrieval_Test_Data_By_Query.hasReturnGiftCardInfo_SCNO};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= orderID[0];
		 testData[0][1]= scNO[0];
	         return (testData);
			}
	 @DataProvider (name="DP_StorePOStoWEB_Information",parallel=true)
	    public Object[][] DP_StorePOStoWEB_Information() throws Exception{
		 Retrieval_Test_Data_By_Query.store_POS_to_Web_OrderID();
		 String [] orderID = {Retrieval_Test_Data_By_Query.hasstore_POS_to_Web_OrderID};
		 String [] scNO = {Retrieval_Test_Data_By_Query.hasstore_POS_to_Web_SCNO};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= orderID[0];
		 testData[0][1]= scNO[0];
	         return (testData);
			}
	 @DataProvider (name="DP_Delivery_Details",parallel=true)
	    public Object[][] DP_Delivery_Details() throws Exception{
		 Retrieval_Test_Data_By_Query.delivery_Details_OrderID();
		 String [] orderID = {Retrieval_Test_Data_By_Query.hasDelivery_Details_OrderID};
		 String [] scNO = {Retrieval_Test_Data_By_Query.hasDelivery_Details_SCNO};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= orderID[0];
		 testData[0][1]= scNO[0];
	         return (testData);
			}
}
