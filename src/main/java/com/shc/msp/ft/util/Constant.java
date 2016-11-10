package com.shc.msp.ft.util;


public class Constant {
	
	/***
	 * Application Login
	 ***/
	 
/*    public static final String OnlineUserName = "testonline0001";
    
    public static final String OnlineUserName2 = "testonline0002";
    
    public static final String OnlineUserName3 = "testonline0003";
    
    public static final String OnlineUserName4 = "testonline0004";
    
    public static final String OnlineUserName5 = "testonline0005";
    
    public static final String OnlineUserName6 = "testonline0006";
    
    public static final String OnlineUserName7 = "testonline0007";
    
    public static final String OnlineUserName8 = "testonline0008";
    
    public static final String OnlineUserName9 = "testonline0009";
    
    public static final String OnlineUserName10 = "testonline0010";
    
    public static final String OnlineUserName11 = "testonline0011";
    
    public static final String OnlineUserName12 = "testonline0012";
    
    public static final String OnlineUserName13 = "testonline0013";
    
    public static final String OnlineUserName14 = "testonline0014";
    
    public static final String OnlineUserName15 = "testonline0015";
    
    public static final String OnlineUserName16 = "testonline0016";
    
    public static final String OnlineUserName17 = "testonline0017";
    
    public static final String OnlineUserName18 = "testonline0018";
    
    public static final String OnlineUserName19 = "testonline0019";
    
    public static final String OnlineUserName20 = "testonline0020";
    
    public static final String OnlineUserName21 = "testonline0021";
    
    public static final String OnlineUserName22 = "testonline0022";
    
    public static final String OnlineUserName23 = "testonline0023";
    
    public static final String OnlineUserName24 = "testonline0024";
    
    public static final String OnlineUserName25 = "testonline0025";
    
    public static final String OnlineUserName26 = "testonline0026";
    
    public static final String OnlineUserName27 = "testonline0027";
    
    public static final String OnlineUserName28 = "testonline0028";
    
    public static final String OnlineUserName29 = "testonline0029";
    
    public static final String OnlineUserName30 = "testonline0030";
    
    public static final String OnlineUserName31 = "testonline0031";
    
    public static final String OnlineUserName32 = "testonline0032";
    
    public static final String OnlineUserName33 = "testonline0033";
    
    public static final String OnlineUserName34 = "testonline0034";
    
    public static final String OnlineUserName35 = "testonline0035";*/
    
    public static final String OnlinePassword = "TestPassword";
    
    public static final String OnlinePasswordProd = "Password";
    
    public static final String OfflineUserName = "testoffline0001";

    public static final String OfflinePassword = "TestPassword";
    
    public static final String DeliveryUserName = "testdelivery0001";

    public static final String DeliveryPassword = "TestPassword";
        
    /*****
     * Excel Path And File Name And Sheet Name
     ******/

    public static final String Path_Rules = "./src/main/resources/";

    public static final String File_Name = "Rules.xls";
    
    public static final String Path_TestData = "./src/main/resources/";
    
    public static final String File_TestData = "TestData.xls";
   
    public static final String Path_RuleSheet = "C:/Caroline/MSP/RuleSheet/";
    
    public static final String Path_OrderLevelRuleFileName = "OrderStatus.xls";
    
    public static final String Path_SalesCheckLevelRuleFileName = "SalesCheckStatus.xls";
    
    public static final String Path_LineItemLevelRuleFileName = "ItemStatus.xls";
    
    public static final String OriginalRulesSheetName = "TablesOriginal";
    
    public static final String NewRulesSheetName = "TablesNew";
    
    public static final String OrderRulesSheetName = "OrderStatus";
    
    public static final String LineItemRulesSheetName = "ItemStatus";
    
    public static final String SalesCheckRulesSheetName = "SalesCheckStatus";
    
    public static final String OrderLevelData = "OrderLevel";
    
    public static final String LineItemLevelData = "LineItemLevel";
    
    public static final String SalesCheckLevelData = "SalesCheckLevel";
    
    public static final String Data_Retrieval_Querys = "Data_Retrieval_Query";
  
    public static final String OrderSearch = "OrderSearch";
    
    public static final String DeliveryOrderSearch = "DeliveryOrderSearch";
    
    public static final String OrderSearchProd = "PROD_SearchOrder";

    public static final String OrderSummary = "OrderSummary";
	

	/****
	 * Test Data Set
	 ****/
	public static final int StartRow_SearchOrder = 1;
	
	public static final int StartColumn_SearchBySiteOrderID = 3;
	
	public static final int StartColumn_StoreID = 0;
	
	public static final int TotalCols_SearchByOrderID = 1;
	
	public static final int StartColumn_SearchByOrderID = 4;
	
	public static final int StartColumn_SearcByhSC = 5;
	
	public static final int StartColumn_AgentID = 12;
	
	public static final int TotalCols_SearchBySC = 1;
	
	public static final int StartColumn_SearchByFName = 6;
	
	public static final int StartColumn_SearchByLName = 7;
	
	public static final int TotalCols_SearchByName = 1;
	
	public static final int StartColumn_SearchByEmail = 8;
	
	public static final int TotalCols_SearchByEmail = 1;
	
	public static final int StartColumn_SearchByPhone = 9;
	
	public static final int TotalCols_SearchByPhone = 1;
	
	public static final int StartRow_rules_Action = 1;
	
	public static final int StartColumn_Order_Data = 0;
	
	public static final int StartRow_Order_Data = 1;

	public static final int OrderLevelDataTotalCols = 19;

	public static final int StartColumn_SalesCheck_Data = 0;
	
	public static final int StartRow_SalesCheck_Data = 1;

	public static final int SalesCheckLevelDataTotalCols = 15;
	
	public static final int StartColumn_STAdj_Eligible = 0;

	public static final int StartRow_STAdj_Eligible = 0;

	public static final int TotalCols = 19;
	
	public static final int StartColumn_SearchByOrderIDProd = 0;
	
	public static final int TotalCols_SearchByOrderIDProd = 2;
	
	/****
	 * Test Data Set - For Delivery Orders
	 ****/
	public static final int TotalCols_SearchByDOSOrderID = 1;
	
	public static final int StartColumn_SearchByDOSPhoneID = 0;
	
	public static final int StartColumn_SearchByDOSOrderID = 1;
	
	public static final int StartColumn_SearchByDOSUnitID = 2;
	
	public static final int StartColumn_SearchByDOSSalesCheck = 3;
	
	public static final int StartColumn_DeliveryAgentID = 6;
	
	

    /****
     * Rules RowName And ColumnName
     ****/
    
    //Order Status Sheet Rows and Columns
    public static final int Row_SalesTaxAdjustment = 11;	

	public static final int Row_ShippingAdjustment =12 ;

	public static final int Row_ReleaseOrder = 13;
	
	public static final int Row_ContactCustomer_Rule = 14;
	
	public static final int Row_CancellationOrder = 15;
	
	public static final int Row_ReSendOrderConfirmation = 16;
	
    
	public static final int Col_OrderEligibleStatus = 2;	

	public static final int Col_RI5exception =3 ;

	public static final int Col_Locationexception = 4;
	
	public static final int Col_StoreException = 5;	

	public static final int Col_Fulfillbyexception = 7;

	public static final int Col_ShsFlagexception = 8;
	
	public static final int Col_SKUexception_Order = 10;

	
	//Line Item Status Sheet Rows and Columns
	public static final int Row_SalesAdjustment = 11;	

	public static final int Row_ContactMarketplaceSeller  =12 ;

	public static final int Row_ContactVendor = 13;
	
	public static final int Row_UpdateExpectedShip_ArrivalDate = 14;
	
	public static final int Row_ReturnTrackingInformation  = 15;
	
	public static final int Row_ContactCustomer_Item = 16;
	 
	public static final int Row_CancellationLineItem = 17;
	
	public static final int Row_CancellationLineItem_2forSSC = 18;
	
	public static final int Row_CancellationLineItem_3forSSC = 19;
	
	public static final int Row_CancellationLineItem_4forSSC = 20;
	
	public static final int Row_RescheduleDelivery = 21;
	 
	public static final int Row_ScheduleReturn = 22;
	
	public static final int Row_ReturnItem = 23;
	 
	public static final int Row_RefundWithoutReturn = 25;
	
	
	public static final int Col_ItemEligibleStatus = 2;	

	public static final int Col_SHSflagsexception_Item = 5 ;

	public static final int Col_Reserveflagsexception_Item = 6;
	
	public static final int Col_Vendoridsalescheckexception1 = 7;	

	public static final int Col_Vendoridsalescheckexception2 = 8;

	public static final int Col_Salescheckno_exception = 9;
	
	public static final int Col_Storefullfilledbyexception_store = 10;
	
	public static final int Col_Storefullfilledbyexception_ffm = 11;
	
	public static final int Col_Storeexception_Item = 12;	

	public static final int Col_SellerPortalexception_Status = 13 ;
	
	public static final int Col_SellerPortalexception_descp = 14 ;
	
	public static final int Col_StatusStoreCheck_status = 15;
	
	public static final int Col_StatusStoreCheck_store = 16;
	
	public static final int Col_FulfilStatusStoreallowed_ffm = 18;	
	
	public static final int Col_FulfilStatusStoreallowed_status = 19;
	
	public static final int Col_FulfilStatusStoreallowed_store = 20;
	
	public static final int Col_FulfilStatusStoreallowed_marktAtt = 21;

	public static final int Col_Fulfilbyexception = 22;

	public static final int Col_FBMandfException = 23;
	
	public static final int Col_FulfilbyStatusCheck_ffm = 24;
	
	public static final int Col_FulfilbyStatusCheck_status = 25;
	
	public static final int Col_Itemdescriptionexception = 27;
	
	public static final int Col_SKUexception_Item = 28;
	
	public static final int Col_Fulfillinexception = 29;
	
	public static final int Col_Currentandship_deliverydateexception = 30;
	
	public static final int Col_Returnquantityexception = 31;
	
	public static final int Col_KPOSstatusexception = 32;
	
	public static final int Col_MarketPlaceIndException = 33;
	
	public static final int Col_RefundWithoutReturnException_price = 34;
	
	public static final int Col_RefundWithoutReturnException_store = 35;
	
	public static final int Col_RefundWithoutReturnException_fullfilment = 36;
	
	
	//Sales Check Status Sheet Rows and Columns
    public static final int Row_ReleaseSalesCheck = 11;	

	public static final int Row_UpdateSalesCheck =12 ;

	public static final int Row_ContactCustomer_SalesCheck = 13;
	
	public static final int Row_CancellationSalesCheck  = 14;
    
	public static final int Row_CancellationSalesCheck_2forSSC = 15;
	
	public static final int Row_CancellationSalesCheck_3forSSC = 16;
	
	public static final int Row_ReadyforPickupEmail = 17;

	
	
	public static final int Col_SCEligibleStatus = 2;	

	public static final int Col_SHSflagsexception_SC = 4;

	public static final int Col_Reserveflagsexception_SC = 5;
	
	public static final int Col_Storeexception_SC = 6;	

	public static final int Col_StatusStoreCheck_Status = 7;
	
	public static final int Col_StatusStoreCheck_Stroe = 8;

	public static final int Col_FulfilStatusStoreallowed_Ffm = 10;
	
	public static final int Col_FulfilStatusStoreallowed_Status = 11;
	
	public static final int Col_FulfilStatusStoreallowed_Store = 12;
	
	public static final int Col_FulfillByStatusCheck_Ffm = 13;
	
	public static final int Col_FulfillByStatusCheck_Status = 14;
	
	public static final int Col_FulfillbyexceptionexceptallowedFFMsduring30minhold_Ffm = 16;
	
	public static final int Col_FulfillbyexceptionexceptallowedFFMsduring30minhold_statusSFS = 17;
	
	public static final int Col_FulfillbyexceptionexceptallowedFFMsduring30minhold_status = 18;
	
	public static final int Col_FulfillbyexceptionexceptallowedFFMsduring30minhold_Ffm2 = 19;



	

	
	
}
