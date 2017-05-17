package com.shc.msp.ft.tests;

import java.util.ArrayList;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shc.automation.BaseTests;
import com.shc.automation.data.TestData;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.actions.LogFormatterAction;
import com.shc.msp.ft.entities.As;
import com.shc.msp.ft.entities.User;
import com.shc.msp.ft.util.Constant;
import com.shc.msp.ft.util.ExcelUtil;
import com.shc.msp.ft.util.Retrieval_Test_Data_By_Query;
import com.shc.msp.ft.util.TestGroup;
import com.shc.msp.ft.util.UserPool;

public class LineItem_Summary extends BaseTests {
	
	TestData<String, String, Integer> data = new TestData<String, String, Integer>("Test", "Test", 1);
	
	@Test(dataProvider = "DP_SearchByOrderID1",groups = {TestGroup.MSPP0Tests,TestGroup.MSPLineItemSummary, "MSPLineItemSummaryTests"}
    , description = "Verify line item summary for an order", enabled = true, priority =25)
	public void lineItemSummaryVerify(String OrderID) throws Exception{
			addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	                ._NavigationAction()
	                .addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                .closeWarningPopupWindow()
	                ._OrderDetailsAction().addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.GIVEN)
	                .clickOnSkuNumberUnderLineItemTab(2)
	                ._LineItemDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.verifylineItemSummary(2);
	}
	
	@Test(dataProvider = "DP_SearchByOrderID2",groups = {TestGroup.MSPP0Tests,TestGroup.MSPLineItemSummary, "MSPLineItemShippingInfoTests"}
    , description = "Verify shipping information in line item summary", enabled = true, priority =26)
	public void lineItemShippingInfoVerify(String OrderID) throws Exception{
			addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	                ._NavigationAction()
	                .addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                .closeWarningPopupWindow()
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.GIVEN)
	                .clickOnSkuNumberUnderLineItemTab(1)
	                ._LineItemDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.verifyShippingInfo(1);

	}
	
	@Test(dataProvider = "DP_Tracking_Detail",groups = {TestGroup.MSPP0Tests,TestGroup.MSPLineItemSummary, "MSPLineItemTrackingDeatilsTests"}
    , description = "Verify tracking details in line item summary", enabled = true, priority =27)
	public void lineItemTrackingDetailsVerify(String OrderID, String sku) throws Exception{
			addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	                .demarcate(LogFormatterAction.LogType.BEGINTESTFLOW, "Search for Order Id : " + OrderID)
	                ._NavigationAction()
	                .addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                .closeWarningPopupWindow()
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.GIVEN)
	                .clickOnSkuNumberUnderLineItemTab(sku)
	                ._LineItemDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.verifyTrackingDetails();
	}
	
	@Test(dataProvider = "DP_Return_Information",groups = {TestGroup.MSPP0Tests,TestGroup.MSPP0Tests,TestGroup.MSPLineItemSummary, "MSPLineItemReturnInformationTests"}
    , description = "Verify return information in line item tab", enabled = true, priority =28)
	public void lineItemReturnInfoVerify(String OrderID,String sku) throws Exception{
			addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	                ._NavigationAction()
	                .addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                .closeWarningPopupWindow()
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.GIVEN)
	                .clickOnSkuNumberUnderLineItemTab(sku)
	                ._LineItemDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.verifyReturnInformation(1);

	}
	
	@Test(dataProvider = "DP_Return_Tracking_Information", groups = {TestGroup.MSPLineItemSummary, "MSPLineItemReturnTrackingInfoTests"}
    , description = "Verify return tracking information in line item tab", enabled = true, priority =29)
	public void lineItemReturnTrackingInfo(String OrderID,String sku) throws Exception{
			addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	                ._NavigationAction()
	                .addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                .closeWarningPopupWindow()
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.WHEN)
	                .clickOnSkuNumberUnderLineItemTab(sku)
	                ._LineItemDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.verifyReturnTrackingInfo(1);
	}
	
	@Test(dataProvider = "DP_Installation_Information",groups = {TestGroup.MSPP0Tests,TestGroup.MSPLineItemSummary, "MSPLineItemInstallationInfoTests"}
    , description = "Verify installation details in line item details tab", enabled = true, priority =30)
	public void lineItemInstallationInfoVerify(String OrderID, String sku) throws Exception{
			addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	                ._NavigationAction()
	                .addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                .closeWarningPopupWindow()
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.GIVEN)
	                .clickOnSkuNumberUnderLineItemTab(1)
	                ._LineItemDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.verifyInstallationInformation(OrderID,1);

	}

	@Test(dataProvider = "DP_Gift_Card_Information",groups = {TestGroup.MSPLineItemSummary, "MSPLineItemGiftCardInfoTests","lineItemGiftCardInfoVerify"}
    , description = "Verify all relevant details are displayed for a Gift Card order", enabled = true, priority =31)
	public void lineItemGiftCardInfoVerify(String orderID, String sku) throws Exception{
			addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        System.out.println(orderID+"--////--"+sku);
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	                ._NavigationAction()
	                .addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(orderID)
	                .closeWarningPopupWindow()
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.GIVEN)
	                .clickOnSkuNumberUnderLineItemTab(sku)
	                ._LineItemDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.verifyGiftCardInformation();

	}

	@Test(dataProvider = "DP_Discount_Eligible_orderID",groups = {TestGroup.MSPP0Tests,TestGroup.MSPLineItemSummary, "MSPLineItemDiscountsInfoTests"}
    , description = "Verify discount information in line item tab", enabled = true, priority =32)
	public void lineItemDiscountsVerify(String OrderID, String sku) throws Exception{
			addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	                ._NavigationAction()
	                .addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                .closeWarningPopupWindow()
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.GIVEN)
	                .clickOnSkuNumberUnderLineItemTab(sku)
	                ._LineItemDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.verifyDiscountsOnLineItem(OrderID);
	}
	
	@Test(dataProvider = "DP_Adjustment",groups = {TestGroup.MSPP0Tests,TestGroup.MSPLineItemSummary, "MSPLineItemAdjustmentsTests"}
    , description = "Verify adjustment details in line item tab", enabled = true, priority =33)
	public void lineItemAdjustmentsVerify(String OrderID, String sku) throws Exception{
			addCloneIDHostname(data);
	        LogFormatterAction.beginSetup();
	        User user = new User(); user.userName=UserPool.getUser();
	        As.guestUser.goToHomePage()
	                ._NavigationAction()
	                .addlogType(TestStepType.WHEN)
	                .login(user)
	                .addlogType(TestStepType.THEN)
	                .verifyonlineagent()
	                .addlogType(TestStepType.WHEN)
	                .searchByOrderId(OrderID)
	                .closeWarningPopupWindow()
	                ._OrderDetailsAction()
	                .addlogType(TestStepType.THEN)
	                .verifyOrderDetailsPageDisplayed()
	                .addlogType(TestStepType.GIVEN)
	                .clickOnSkuNumberUnderLineItemTab(sku)
	                ._LineItemDetailsAction()
	                .addlogType(TestStepType.THEN)
	        		.verifyAdjustmentsOnLineItem(OrderID);
	}
	
	 @DataProvider (name="DP_Adjustment",parallel=true)
	    public Object[][] DP_Adjustment() throws Exception{
		 Retrieval_Test_Data_By_Query.Adjustment_OrderID();
		 String [] orderID = {Retrieval_Test_Data_By_Query.hasAdjustment_OrderID};
		 String [] sku = {Retrieval_Test_Data_By_Query.hasAdjustment_SKU};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= orderID[0];
		 testData[0][1]= sku[0];
	         return (testData);
			}
	 @DataProvider (name="DP_SearchByOrderID1",parallel=true)
	    public Object[][] DP_SearchByOrderID1() throws Exception{
			Object[][] testData =null;
			ArrayList<String> searchrows = ExcelUtil.getExcelUtil().searchKeyWord("LineItemSummary1", Constant.OrderSearch, 
					Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
			testData = ExcelUtil.getExcelUtil().getFilterDataArray(searchrows, Constant.TotalCols_SearchByOrderID);
	         return (testData);
			}
	 @DataProvider (name="DP_SearchByOrderID2",parallel=true)
	    public Object[][] DP_SearchByOrderID2() throws Exception{
			Object[][] testData =null;
			ArrayList<String> searchrows = ExcelUtil.getExcelUtil().searchKeyWord("LineItemSummary2", Constant.OrderSearch, 
					Constant.StartColumn_SearchBySiteOrderID, Constant.TotalCols_SearchByOrderID);
			testData = ExcelUtil.getExcelUtil().getFilterDataArray(searchrows, Constant.TotalCols_SearchByOrderID);
	         return (testData);
			}
	 @DataProvider (name="DP_Discount_Eligible_orderID")
	    public Object[][] DP_Discount_Eligible_orderID() throws Exception{
		 
		 Retrieval_Test_Data_By_Query.Discount_OrderID();
		 String OrderID[]={Retrieval_Test_Data_By_Query.hasDiscount_OrderID};
		 String sku[]={Retrieval_Test_Data_By_Query.hasDiscount_SKU};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= OrderID[0];
		 testData[0][1]= sku[0];
	         return (testData);
	 		}
	 @DataProvider (name="DP_Tracking_Detail",parallel=true)
	    public Object[][] DP_Tracking_Detail() throws Exception{
		 Retrieval_Test_Data_By_Query.tracking_Details_OrderID();
		 String [] orderID = {Retrieval_Test_Data_By_Query.hasTracking_Detail_OrderID};
		 String [] sku = {Retrieval_Test_Data_By_Query.hasTracking_Detail_SKU};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= orderID[0];
		 testData[0][1]= sku[0];
	         return (testData);
			}
	 @DataProvider (name="DP_Return_Information",parallel=true)
	    public Object[][] DP_Return_Information() throws Exception{
		 Retrieval_Test_Data_By_Query.return_Information_OrderID();
		 String [] orderID = {Retrieval_Test_Data_By_Query.hasReturnInfo_OrderID};
		 String [] sku = {Retrieval_Test_Data_By_Query.hasReturnInfo_SKU};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= orderID[0];
		 testData[0][1]= sku[0];
	         return (testData);
			}
	 @DataProvider (name="DP_Return_Tracking_Information",parallel=true)
	    public Object[][] DP_Return_Tracking_Information() throws Exception{
		 Retrieval_Test_Data_By_Query.return_Tracking_Information_OrderID();
		 String [] orderID = {Retrieval_Test_Data_By_Query.hasReturnTrackingInfo_OrderID};
		 String [] sku = {Retrieval_Test_Data_By_Query.hasReturnTrackingInfo_SKU};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= orderID[0];
		 testData[0][1]= sku[0];
	         return (testData);
			}
	 @DataProvider (name="DP_Installation_Information",parallel=true)
	    public Object[][] DP_Installation_Information() throws Exception{
		 Retrieval_Test_Data_By_Query.installation_Information_OrderID();
		 String [] orderID = {Retrieval_Test_Data_By_Query.hasInstallationInfo_OrderID};
		 String [] sku = {Retrieval_Test_Data_By_Query.hasInstallationInfo_SKU};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= orderID[0];
		 testData[0][1]= sku[0];
		// Object testData[][] = {orderID};
		 return (testData);
			}
	 @DataProvider (name="DP_Gift_Card_Information",parallel=true)
	    public Object[][] DP_Gift_Card_Information() throws Exception{
		 Retrieval_Test_Data_By_Query.giftCard_Information_OrderID();
		 String [] orderID = {Retrieval_Test_Data_By_Query.hasGiftCardInfo_OrderID};
		 String [] sku = {Retrieval_Test_Data_By_Query.hasGiftCardInfo_SKU};
		 Object[][] testData = new Object[1][2];
		 testData[0][0]= orderID[0];
		 testData[0][1]= sku[0];
	         return (testData);
			}
}
