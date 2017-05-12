package com.shc.msp.ft.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.testng.Reporter;

public class Retrieval_Test_Data_By_Query {
	
/*	static Connection conn1 = null;
	static PreparedStatement st1 = null;
	static ResultSet rs1 = null;*/
	
	/***********************
	 * * * Order Level * * *
	 ***********************/
	static String Sql_ST_Adj_Eligible = null;
	static String Sql_ST_Adj_Store = null;
	static String Sql_Store_ID = null;

	
	static String Sql_ST_Adj_Status = null;
	static String Sql_ST_Adj_FFM = null;
	static String Sql_Ship_Adj_Eligible = null;
	static String Sql_Ship_Adj_Store = null;
	static String Sql_Ship_Adj_Status = null;
	static String Sql_Release_Order_Eligible = null;
	static String Sql_Release_Order_Store = null;
	static String Sql_Release_Order_Status = null;
	static String Sql_Contact_Customer_Eligible = null;
	static String Sql_Contact_Customer_Status = null;
	static String Sql_Cancellation_Eligible = null;
	static String Sql_Cancellation_Store = null;
	static String Sql_Cancellation_Status = null;
	static String Sql_Cancellation_FFM = null;
	static String Sql_Resend_Order_Confirmation_Eligible = null;
	static String Sql_Resend_Order_Confirmation_Store = null;
	static String Sql_Resend_Order_Confirmation_Status = null;
	static Map<Integer, Object[]> result = new TreeMap<Integer, Object[]>();
	public static String adj_eligible_orderID = null;
    public static String adj_store_exception = null;
    public static String adj_status_exception = null;
    public static String adj_FFM_exception = null;

	public static String resend_order_confirm_eligible_orderID = null;
	public static String resend_order_confirm_store_exception = null;
	public static String resend_order_confirm_status_exception = null;
	public static String cc_sales_check_eligible_orderID = null;
	public static String cc_sales_check_eligible_SCNO = null;
	public static String cc_sales_check_status_exception_ID = null;
	public static String cc_sales_check_status_exception_SCNO = null;
	public static String cancellation_orderID = null;
	public static String cancellation_store_exception = null;
	public static String cancellation_status_exception = null;
	public static String cancellation_FFM_exception = null;

	public static String release_order_eligible_orderID = null;
	public static String release_order_store_exception = null;
	public static String release_order_status_exception = null;

	public static String cc_eligible_orderID = null;
	public static String cc_status_exception = null;
	public static String ship_adj_eligible_orderID = null;
    public static String ship_adj_store_exception = null;
	public static String adj_eligible_storeID = null;
	public static String ship_adj_status_exception = null;

	
	public static String cancel_sc_eligible_orderID = null;
	public static String cancel_sc_eligible_SCNO = null;
	public static String cancel_sc_store_exception_ID = null;
	public static String cancel_sc_store_exception_SCNO = null;
	public static String cancel_sc_statusstore_exception_ID = null;
	public static String cancel_sc_statusstore_exception_SCNO = null;
	public static String cancel_sc_30mins_eligible_ID = null;	
	public static String cancel_sc_30mins_eligible_SCNO = null;	
	public static String release_sales_check_eligible_orderID = null;
	public static String release_sales_check_eligible_SCNO = null;
	//TODO Not used by DP
	public static String release_sales_check_store_exception_ID = null;
	//TODO Not used by DP
	public static String release_sales_check_store_exception_SCNO = null;
	public static String release_sales_check_status_exception_ID = null;
	public static String release_sales_check_status_exception_SCNO = null;
	public static String update_sales_check_eligible_orderID = null;
	public static String update_sales_check_eligible_SCNO = null;
	public static String update_sales_check_store_exception_ID = null;
	public static String update_sales_check_store_exception_SCNO = null;
	public static String update_sales_check_status_exception_ID = null;
	public static String update_sales_check_status_exception_SCNO = null;
	public static String ready_for_Pickup_Email_eligible_orderID = null;
	public static String ready_for_Pickup_Email_eligible_SCNO = null;
	public static String ready_for_Pickup_Email_store_exception_ID = null;
	public static String ready_for_Pickup_Email_store_exception_SCNO = null;
	public static String ready_for_Pickup_Email_status_exception_ID = null;
	public static String ready_for_Pickup_Email_status_exception_SCNO = null;
	public static String contactcustomer_eligible_commercial_orderID = null;
	public static String subOrderID = null;
	public static String orderID = null;
	public static String salescheckID = null;

    
    public synchronized void sales_Tax_Adjustment_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.OrderRulesSheetName);
		String stadj_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_SalesTaxAdjustment, Constant.Col_OrderEligibleStatus).replace("\"", "'"); 
		String store_exp = ExcelUtil.getExcelUtil().getCellData(Constant.Row_SalesTaxAdjustment, Constant.Col_StoreException).replace("\"", "'");
		String ffm_exp = ExcelUtil.getExcelUtil().getCellData(Constant.Row_SalesTaxAdjustment, Constant.Col_Fulfillbyexception).replace("\"", "'").replace(",", "','");
		
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		
			/*Sql_ST_Adj_Eligible = "select distinct o.site_gen_ord_id AS Adj_Eligible_OrderID from ord o, ord_item oi, ffm_method fm where "+ 
					"o.order_id = oi.order_id and oi.ffm_method_id = fm.ffm_method_id and o.order_sts_cd in ( 'CMP','CNF','D','C','EDT','E','H','I','G','PRO','SUB','B','WAP','z' ) "+ 
			"and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST')  "+
			"and o.SITE_ID not in ('30153') and fm.ffm_class_id not in ( 'TRYBUY','PARTSDIRECT','TRYBUY','PARTSDIRECT','PARTSDIRECT','TRYBUY','PARTSDIRECT' ) "+ 
			"and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-16 01:01:01' and o.last_updated_ts < '2016-05-15 01:01:01' limit 1";*/
					
			Sql_ST_Adj_Eligible ="select distinct o.site_gen_ord_id AS order_id from ord o, ord_item oi, ffm_method fm where o.order_id = oi.order_id "
					+ "and oi.ffm_method_id = fm.ffm_method_id and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','WAP','SHP','RET','SUB') "
					+ "and oi.order_item_sts_cd NOT in ('PCON','TEST','BAD') and o.order_sts_cd in ('CNF','D','C','EDT','E','H','I','G','PRO','B','WAP','z') "
					+ "and o.SITE_ID not in (30153) and fm.ffm_class_id  in ('TW') and o.ORDER_ID=o.SITE_GEN_ORD_ID and oi.so_line_number=1 "
					+ "and o.placement_ts > DATE_SUB(CURDATE(),INTERVAL 30 DAY) ORDER BY RAND() limit 1";
			
			Sql_ST_Adj_Store = "select distinct o.site_gen_ord_id AS Adj_Store_Expt_OrderID from ord o, ord_item oi, ffm_method fm "
					+ "where o.order_id = oi.order_id and oi.ffm_method_id = fm.ffm_method_id and o.order_sts_cd "
					+ "in ("+stadj_eligible_status+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.SITE_ID in ("+store_exp+") and fm.ffm_class_id not in ( "+ffm_exp+" ) and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
					+ "and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-07-15 01:01:01' order by o.last_updated_ts desc limit 1";
			
			Sql_ST_Adj_Status = "select distinct o.site_gen_ord_id AS Adj_Status_Expt_OrderID from ord o, ord_item oi, ffm_method fm "
					+ "where o.order_id = oi.order_id and oi.ffm_method_id = fm.ffm_method_id and o.order_sts_cd "
					+ "not in ("+stadj_eligible_status+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in "
					+ "('PCON','TEST') and o.SITE_ID not in ("+store_exp+") and fm.ffm_class_id "
					+ "not in ( "+ffm_exp+" ) and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
			
			Sql_ST_Adj_FFM = "select distinct o.site_gen_ord_id AS Adj_FFM_Expt_OrderID from ord o, ord_item oi, ffm_method fm "
					+ "where o.order_id = oi.order_id and oi.ffm_method_id = fm.ffm_method_id and o.order_sts_cd "
					+ "in ("+stadj_eligible_status+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in "
					+ "('PCON','TEST') and o.SITE_ID not in ("+store_exp+") and fm.ffm_class_id "
					+ "in ( "+ffm_exp+" ) and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
	
			Sql_Store_ID = "select distinct o.SITE_ID AS Adj_Eligible_StoreID from ord o, ord_item oi, ffm_method fm "
					+ "where o.order_id = oi.order_id and oi.ffm_method_id = fm.ffm_method_id and o.order_sts_cd in ( "+stadj_eligible_status
					+ " ) and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') and o.SITE_ID not in ("+store_exp+") "
					+ "and fm.ffm_class_id not in ( "+ffm_exp+" ) and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
	try {
		
		st = conn.prepareStatement(Sql_ST_Adj_Eligible);
		Reporter.log("SQL Query: "+Sql_ST_Adj_Eligible);
		st.execute();
		rs = st.getResultSet();
		while(rs.next()){
		int k = 0;
	adj_eligible_orderID = rs.getString("order_id");
				System.out.println("eligible status: "+adj_eligible_orderID);
		result.put(k++, new Object[] {adj_eligible_orderID});
		//ExcelUtil.setCellData("OrderLevel", result, k+1, 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	try {
		
			st = conn.prepareStatement(Sql_ST_Adj_Store);
			Reporter.log("SQL Query: "+Sql_ST_Adj_Store);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			adj_store_exception = rs.getString("Adj_Store_Expt_OrderID");
			System.out.println("store exception: "+adj_store_exception);
			result.put(k++, new Object[] {adj_store_exception});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_ST_Adj_Status);
			Reporter.log("SQL Query: "+Sql_ST_Adj_Status);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			adj_status_exception = rs.getString("Adj_Status_Expt_OrderID");
			System.out.println("status exception: "+adj_status_exception);
			result.put(k++, new Object[] {adj_status_exception});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1 , 2);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_ST_Adj_FFM);
			Reporter.log("SQL Query: "+Sql_ST_Adj_FFM);
			st.execute();
			rs = st.getResultSet();
			
			while(rs.next()){
			int k = 0;
			adj_FFM_exception = rs.getString("Adj_FFM_Expt_OrderID");
			System.out.println("ffm exception: "+adj_FFM_exception);
			result.put(k++, new Object[] {adj_FFM_exception});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 3);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try {
		st = conn.prepareStatement(Sql_Store_ID);
		Reporter.log("SQL Query: "+Sql_Store_ID);
		st.execute();
		rs = st.getResultSet();
		while(rs.next()){
		int k = 0;
		adj_eligible_storeID = rs.getString("Adj_Eligible_StoreID");
		System.out.println("eligible storeID: "+adj_eligible_storeID);
		result.put(k++, new Object[] {adj_eligible_storeID});
	//	ExcelUtil.setCellData("OrderLevel", result, k+1, 0);
		adj_eligible_storeID=adj_eligible_storeID+" ";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
	}
	
	public static void shipping_Adjustment_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.OrderRulesSheetName);
		String ship_adj_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ShippingAdjustment, Constant.Col_OrderEligibleStatus).replaceAll("\"", "'");
		String ship_store_exp = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ShippingAdjustment, Constant.Col_StoreException).replaceAll("\"", "'");
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
			Sql_Ship_Adj_Eligible = "select distinct o.site_gen_ord_id AS Ship_Adj_Eligible_OrderID from ord o, ord_item oi "
					+ "where o.order_id = oi.order_id and o.order_sts_cd in ("+ship_adj_eligible_status+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
					+ "and oi.order_item_sts_cd NOT in ('PCON','TEST')and o.SITE_ID not in ("+ship_store_exp+") "
					+ "and o.shipping_amt>0 and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2016-02-15 01:01:01' and o.last_updated_ts < '2016-05-15 01:01:01' limit 1";
	
			Sql_Ship_Adj_Store = "select distinct o.site_gen_ord_id AS Ship_Adj_Store_Expt from ord o, ord_item oi "
					+ "where o.order_id = oi.order_id and o.order_sts_cd in ("+ship_adj_eligible_status+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
					+ "and oi.order_item_sts_cd NOT in ('PCON','TEST') and o.SITE_ID in ("+ship_store_exp+") and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
					+ " and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01'  limit 1";
			
			Sql_Ship_Adj_Status = "select distinct o.site_gen_ord_id AS Ship_Adj_Status_Expt from ord o, ord_item oi "
					+ "where o.order_id = oi.order_id and o.order_sts_cd NOT in ("+ship_adj_eligible_status+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
					+ "and oi.order_item_sts_cd NOT in ('PCON','TEST') and o.SITE_ID not in ("+ship_store_exp+") and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
					+ " and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
			
			
	try {
			
			st = conn.prepareStatement(Sql_Ship_Adj_Eligible);
			Reporter.log("SQL Query: "+Sql_Ship_Adj_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			ship_adj_eligible_orderID = rs.getString("Ship_Adj_Eligible_OrderID");
			System.out.println("shipping adjustment eligible status: "+ship_adj_eligible_orderID);
			result.put(k++, new Object[] {ship_adj_eligible_orderID});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 4);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Ship_Adj_Store);
			Reporter.log("SQL Query: "+Sql_Ship_Adj_Store);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			ship_adj_store_exception = rs.getString("Ship_Adj_Store_Expt");
			System.out.println("shipping adjustment store exception: "+ship_adj_store_exception);
			result.put(k++, new Object[] {ship_adj_store_exception});
		//	ExcelUtil.setCellData("OrderLevel", result,k+1, 5);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Ship_Adj_Status);
			Reporter.log("SQL Query: "+Sql_Ship_Adj_Status);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			ship_adj_status_exception = rs.getString("Ship_Adj_Status_Expt");
			System.out.println("shipping adjustment status exception: "+ship_adj_status_exception);
			result.put(k++, new Object[] {ship_adj_status_exception});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 6);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
	}
	
	public static void release_Order_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.OrderRulesSheetName);
		String release_order_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReleaseOrder, Constant.Col_OrderEligibleStatus).replaceAll("\"", "'");
		String release_store_exp = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReleaseOrder, Constant.Col_StoreException).replaceAll("\"", "'");
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
			Sql_Release_Order_Eligible = "select distinct o.site_gen_ord_id from ord o, ord_item oi "
					+ "where o.order_id = oi.order_id and o.order_sts_cd in ("+release_order_eligible_status+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.SITE_ID not in ("+release_store_exp+") and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.placement_ts > DATE_SUB(CURDATE(),INTERVAL 30 DAY) "
					+ " limit 1";
			System.out.println("SQL query is "+Sql_Release_Order_Eligible);
			Sql_Release_Order_Store = "select distinct o.site_gen_ord_id AS Release_Order_Store_Expt from ord o, ord_item oi "
					+ "where o.order_id = oi.order_id and o.order_sts_cd in ("+release_order_eligible_status+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.SITE_ID in ("+release_store_exp+") " +
							"and o.placement_ts > DATE_SUB(CURDATE(),INTERVAL 30 DAY)"
					+ " limit 1";
			System.out.println("Store excpetion SQL query is "+Sql_Release_Order_Store);
			Sql_Release_Order_Status = "select distinct o.site_gen_ord_id AS Release_Order_Status_Expt from ord o, ord_item oi "
					+ "where o.order_id = oi.order_id and o.order_sts_cd NOT in ("+release_order_eligible_status+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.SITE_ID not in ("+release_store_exp+") and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' "
					+ " limit 1";
			
			
	try {
			
			st = conn.prepareStatement(Sql_Release_Order_Eligible);
			Reporter.log("SQL Query: "+Sql_Release_Order_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			release_order_eligible_orderID = rs.getString("site_gen_ord_id");
			System.out.println("release order eligible status: "+release_order_eligible_orderID);
			result.put(k++, new Object[] {release_order_eligible_orderID});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 7);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Release_Order_Store);
			Reporter.log("SQL Query: "+Sql_Release_Order_Store);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			release_order_store_exception = rs.getString("Release_Order_Store_Expt");
			System.out.println("release order store exception: "+release_order_store_exception);
			result.put(k++, new Object[] {release_order_store_exception});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 8);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Release_Order_Status);
			Reporter.log("SQL Query: "+Sql_Release_Order_Status);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			release_order_status_exception = rs.getString("Release_Order_Status_Expt");
			System.out.println("release order status exception: "+release_order_status_exception);
			result.put(k++, new Object[] {release_order_status_exception});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 9);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
	}
	
	public static void contactCustomer_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.OrderRulesSheetName);
		String cc_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ContactCustomer_Rule, Constant.Col_OrderEligibleStatus).replaceAll("\"", "'");

		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
			Sql_Contact_Customer_Eligible = "select distinct o.site_gen_ord_id AS Contact_Customer_Eligible_OrderID from ord o, ord_item oi, sales_check sc, "
					+ "ffm_method fm where o.order_id = oi.order_id and o.order_id = sc.order_id "
					+ "and oi.ffm_method_id = fm.ffm_method_id and o.order_sts_cd in ("+cc_eligible_status+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
	
			Sql_Contact_Customer_Status = "select distinct o.site_gen_ord_id AS Contact_Customer_Status_Expt from ord o, ord_item oi, sales_check sc, "
					+ "ffm_method fm where o.order_id = oi.order_id and o.order_id = sc.order_id "
					+ "and oi.ffm_method_id = fm.ffm_method_id and o.order_sts_cd NOT in ("+cc_eligible_status+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
			
			
	try {
			
			st = conn.prepareStatement(Sql_Contact_Customer_Eligible);
			Reporter.log("SQL Query: "+Sql_Contact_Customer_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			cc_eligible_orderID = rs.getString("Contact_Customer_Eligible_OrderID");
			System.out.println("contact customer eligible status: "+cc_eligible_orderID);
			result.put(k++, new Object[] {cc_eligible_orderID});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 10);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

	
	try {
		
			st = conn.prepareStatement(Sql_Contact_Customer_Status);
			Reporter.log("SQL Query: "+Sql_Contact_Customer_Status);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			cc_status_exception = rs.getString("Contact_Customer_Status_Expt");
			System.out.println("contact customer status exception: "+cc_status_exception);
			result.put(k++, new Object[] {cc_status_exception});
	//		ExcelUtil.setCellData("OrderLevel", result, k+1, 11);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
	}
	
	public static void cancellation_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.OrderRulesSheetName);
		String cancellation_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationOrder, Constant.Col_OrderEligibleStatus).replaceAll("\"", "'");
		String cancellation_store_exp = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationOrder, Constant.Col_StoreException).replaceAll("\"", "'");
		String cancellation_ffm_exp = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationOrder, Constant.Col_Fulfillbyexception).replaceAll("\"", "'").replaceAll(",", "','");
		String cancellation_sku = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationOrder, Constant.Col_SKUexception_Order).replaceAll("\"", "'");
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
			Sql_Cancellation_Eligible = "select distinct o.site_gen_ord_id AS Cancellation_Eligible_OrderID from ord o, ord_item oi,  ffm_method fm "
					+ "where o.order_id = oi.order_id and oi.ffm_method_id = fm.ffm_method_id and o.order_sts_cd "
					+ "in ("+cancellation_eligible_status+") and o.SITE_ID not in ("+cancellation_store_exp+") and fm.ffm_class_id not "
					+ "in ("+cancellation_ffm_exp+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET','SUB') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
					
			/*"SELECT o.SITE_GEN_ORD_ID AS Cancellation_Eligible_OrderID FROM ORD O,ORD_ITEM OI,SALES_CHECK SC,FFM_METHOD FFM, payment p,payment_instruction pi,pmt_method pm "
					+ "where O.ORDER_SERVICE_ORGINATING_SERVER in ('qa.ecom.sears.com') and O.RECORD_INSERT_TS >'2015-05-25 19:08:58' AND O.ORDER_ID=OI.ORDER_ID " + "AND SC.ORDER_ID=O.ORDER_ID  AND SC.SALES_CHECK_ID=OI.SALES_CHECK_ID and FFM.FFM_METHOD_id=OI.FFM_METHOD_id and pi.PAYMENT_METHOD_ID=pm.payment_method_id and p.PAY_INSTRUCTION_ID=pi.PAY_INSTRUCTION_ID and sc.sales_check_id=p.sales_check_id and o.ORDER_ID=pi.order_ID  AND OI.ORDER_ITEM_STS_CD IN ('CSI') "
					+" order by O.RECORD_INSERT_TS desc LIMIT 1";*/

			Sql_Cancellation_Store = "select distinct o.site_gen_ord_id AS Cancellation_Store_Expt_OrderID from ord o, ord_item oi,  ffm_method fm "
					+ "where o.order_id = oi.order_id and oi.ffm_method_id = fm.ffm_method_id and o.order_sts_cd "
					+ "in ("+cancellation_eligible_status+") and o.SITE_ID in ("+cancellation_store_exp+") and fm.ffm_class_id not in "
					+ "("+cancellation_ffm_exp+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET','SUB') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
			
			Sql_Cancellation_Status = "select distinct o.site_gen_ord_id AS Cancellation_Status_Expt_OrderID from ord o, ord_item oi,  ffm_method fm "
					+ "where o.order_id = oi.order_id and oi.ffm_method_id = fm.ffm_method_id and "
					+ "o.order_sts_cd NOT in ("+cancellation_eligible_status+") and o.SITE_ID not in ("+cancellation_store_exp+") "
					+ "and fm.ffm_class_id not in ("+cancellation_ffm_exp+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET','SUB') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
			
			Sql_Cancellation_FFM = "select distinct o.site_gen_ord_id AS Cancellation_FFM_Expt_OrderID from ord o, ord_item oi,  ffm_method fm "
					+ "where o.order_id = oi.order_id and oi.ffm_method_id = fm.ffm_method_id and o.order_sts_cd "
					+ "in ("+cancellation_eligible_status+") and o.SITE_ID not in ("+cancellation_store_exp+") and fm.ffm_class_id in "
					+ "("+cancellation_ffm_exp+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET','SUB') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
			
	try {
			
			st = conn.prepareStatement(Sql_Cancellation_Eligible);
			Reporter.log("SQL Query: "+Sql_Cancellation_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			cancellation_orderID = rs.getString("Cancellation_Eligible_OrderID");
			System.out.println("Cancellation Eligible Status: "+cancellation_orderID);
			result.put(k++, new Object[] {cancellation_orderID});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 12);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Cancellation_Store);
			Reporter.log("SQL Query: "+Sql_Cancellation_Store);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			cancellation_store_exception = rs.getString("Cancellation_Store_Expt_OrderID");
			System.out.println("Cancellation Store Exception: "+cancellation_store_exception);
			result.put(k++, new Object[] {cancellation_store_exception});
	//		ExcelUtil.setCellData("OrderLevel", result, k+1, 13);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Cancellation_Status);
			Reporter.log("SQL Query: "+Sql_Cancellation_Status);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			cancellation_status_exception = rs.getString("Cancellation_Status_Expt_OrderID");
			System.out.println("Cancellation Status Exception: "+cancellation_status_exception);
			result.put(k++, new Object[] {cancellation_status_exception});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 14);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Cancellation_FFM);
			Reporter.log("SQL Query: "+Sql_Cancellation_FFM);
			st.execute();
			rs = st.getResultSet();
			
			while(rs.next()){
			int k = 0;
			cancellation_FFM_exception = rs.getString("Cancellation_FFM_Expt_OrderID");
			System.out.println("Cancellation FFM Exception: "+cancellation_FFM_exception);
			result.put(k++, new Object[] {cancellation_FFM_exception});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 15);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
	
	}
	
	public static void resend_Order_Confirmation_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.OrderRulesSheetName);
		String resend_order_confirmation_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReSendOrderConfirmation, Constant.Col_OrderEligibleStatus).replaceAll("\"", "'");
		String resend_order_confirmation_store_exp = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReSendOrderConfirmation, Constant.Col_StoreException).replaceAll("\"", "'");
		ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReSendOrderConfirmation, Constant.Col_OrderEligibleStatus).replaceAll("\"", "'");
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
			Sql_Resend_Order_Confirmation_Eligible = "select distinct o.site_gen_ord_id AS Resend_Order_Confirmation_Eligible_OrderID from ord o, ord_item oi "
					+ "where o.order_id = oi.order_id and o.order_sts_cd in ("+resend_order_confirmation_eligible_status+") "
					+ "and o.SITE_ID not in ("+resend_order_confirmation_store_exp+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
	
			Sql_Resend_Order_Confirmation_Store = "select distinct o.site_gen_ord_id AS Resend_Order_Confirmation_Store_Expt from ord o, ord_item oi "
					+ "where o.order_id = oi.order_id and o.order_sts_cd in ("+resend_order_confirmation_eligible_status+") "
					+ "and o.SITE_ID in ("+resend_order_confirmation_store_exp+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
			
			Sql_Resend_Order_Confirmation_Status = "select distinct o.site_gen_ord_id AS Resend_Order_Confirmation_Status_Expt from ord o, ord_item oi "
					+ "where o.order_id = oi.order_id and o.order_sts_cd NOT in ("+resend_order_confirmation_eligible_status+")"
					+ "and o.SITE_ID not in ("+resend_order_confirmation_store_exp+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					+ "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
			
			
	try {
			
			st = conn.prepareStatement(Sql_Resend_Order_Confirmation_Eligible);
			Reporter.log("SQL Query: "+Sql_Resend_Order_Confirmation_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			resend_order_confirm_eligible_orderID = rs.getString("Resend_Order_Confirmation_Eligible_OrderID");
			System.out.println("Resend Order Confirmation Eligible Status: "+resend_order_confirm_eligible_orderID);
			result.put(k++, new Object[] {resend_order_confirm_eligible_orderID});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 16);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Resend_Order_Confirmation_Store);
			Reporter.log("SQL Query: "+Sql_Resend_Order_Confirmation_Store);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			resend_order_confirm_store_exception = rs.getString("Resend_Order_Confirmation_Store_Expt");
			System.out.println("Resend Order Confirmation Store Exception: "+resend_order_confirm_store_exception);
			result.put(k++, new Object[] {resend_order_confirm_store_exception});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 17);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Resend_Order_Confirmation_Status);
			Reporter.log("SQL Query: "+Sql_Resend_Order_Confirmation_Status);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			int k = 0;
			resend_order_confirm_status_exception = rs.getString("Resend_Order_Confirmation_Status_Expt");
			System.out.println("Resend Order Confirmation Status Exception: "+resend_order_confirm_status_exception);
			result.put(k++, new Object[] {resend_order_confirm_status_exception});
		//	ExcelUtil.setCellData("OrderLevel", result, k+1, 18);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
	}

	
	/*****************************
	 * * * Sales Check Level * * *
	 *****************************/
	static String  Sql_Release_Sales_Check_Eligible = null;
	//TODO:Not used
	static String  Sql_Release_Sales_Check_Store = null;
	//TODO:Not used
	static String  Sql_Release_Sales_Check_Status = null;
	//TODO:Not used
	static String  Sql_Update_Sales_Check_Eligible = null;
	static String  Sql_Update_Sales_Check_Store = null;
	static String  Sql_Update_Sales_Check_Status = null;
	
	static String  Sql_Contact_Customer_Sales_Check_Eligible = null;
	static String  Sql_Contact_Customer_Sales_Check_Status_Exp = null;
	static String  Sql_SC_Cancellation_Eligible = null;
	static String  Sql_SC_Cancellation_Store_Exp = null;
	static String  Sql_SC_Cancellation_StatusStore_Exp = null;
	static String  Sql_SC_Cancellation_ffmexp30 = null;
	static String  Sql_Ready_for_Pickup_Email_Eligible = null;
	static String  Sql_Ready_for_Pickup_Email_Store = null;
	static String  Sql_Ready_for_Pickup_Email_Status = null;

	public static void release_Sales_Check_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.SalesCheckRulesSheetName);
		String release_sales_check_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReleaseSalesCheck, Constant.Col_SCEligibleStatus).replaceAll("\"", "'");
		String release_sales_check_store_exp = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReleaseSalesCheck, Constant.Col_Storeexception_SC).replaceAll("\"", "'");
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		
		Sql_Release_Sales_Check_Eligible = "select distinct o.site_gen_ord_id,sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "sc.SALES_CHECK_STS_CD in ("+release_sales_check_eligible_status+") and o.SITE_ID not in ("+release_sales_check_store_exp+") "
				+ "and sc.SALES_CHECK_NUMBER > 1 and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
				+ "and oi.order_item_sts_cd NOT in ('PCON','TEST')and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
				+ "and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";

		Sql_Release_Sales_Check_Store = "select distinct o.site_gen_ord_id,sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "sc.SALES_CHECK_STS_CD in ("+release_sales_check_eligible_status+") and o.SITE_ID in ("+release_sales_check_store_exp+") "
				+ "and sc.SALES_CHECK_NUMBER > 1 and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
				+ "and oi.order_item_sts_cd NOT in ('PCON','TEST')and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
		
		Sql_Release_Sales_Check_Status = "select distinct o.site_gen_ord_id,sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "sc.SALES_CHECK_STS_CD NOT in ("+release_sales_check_eligible_status+") and o.SITE_ID in ("+release_sales_check_store_exp+") "
				+ "and sc.SALES_CHECK_NUMBER > 1 and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
				+ "and oi.order_item_sts_cd NOT in ('PCON','TEST')and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
		
			
	try {
			
			st = conn.prepareStatement(Sql_Release_Sales_Check_Eligible);
			Reporter.log("SQL Query: "+Sql_Release_Sales_Check_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			//int k = 0;
			release_sales_check_eligible_orderID = rs.getString("site_gen_ord_id");
			release_sales_check_eligible_SCNO = rs.getString("sales_check_number");
			System.out.println("release sales check eligible status OrderID: "+release_sales_check_eligible_orderID);
			System.out.println("release sales check eligible status SCNO: "+release_sales_check_eligible_SCNO);
			/*result.put(k++, new Object[] {release_sales_check_eligible_orderID});
			System.out.println(k+1);
			ExcelUtil.setCellData("SalesCheckStatus", result, k+1, 0);*/
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Release_Sales_Check_Store);
			Reporter.log("SQL Query: "+Sql_Release_Sales_Check_Store);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			release_sales_check_store_exception_ID = rs.getString("site_gen_ord_id");
			release_sales_check_store_exception_SCNO = rs.getString("sales_check_number");
			System.out.println("release order store exception: "+release_sales_check_store_exception_ID);
			System.out.println("release order store exception SCNO: "+release_sales_check_store_exception_SCNO);
			
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Release_Sales_Check_Status);
			Reporter.log("SQL Query: "+Sql_Release_Sales_Check_Status);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			release_sales_check_status_exception_ID = rs.getString("site_gen_ord_id");
			System.out.println("release order status exception: "+release_sales_check_status_exception_ID);
			release_sales_check_status_exception_SCNO = rs.getString("sales_check_number");
			System.out.println("release order status exception SCNO: "+release_sales_check_status_exception_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
	}
	

	public static void update_Sales_Check_Data() throws Exception{

		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.SalesCheckRulesSheetName);
		String update_sales_check_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_UpdateSalesCheck, Constant.Col_SCEligibleStatus).replaceAll("\"", "'");
		String update_sales_check_store_exp = ExcelUtil.getExcelUtil().getCellData(Constant.Row_UpdateSalesCheck, Constant.Col_Storeexception_SC).replaceAll("\"", "'");
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		
			Sql_Update_Sales_Check_Eligible = "select distinct o.site_gen_ord_id,sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
					+ "sc.SALES_CHECK_STS_CD in ("+update_sales_check_eligible_status+") and o.site_id not in ("+update_sales_check_store_exp+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') " +
					"and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
	
			Sql_Update_Sales_Check_Store = "select distinct o.site_gen_ord_id,sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
					+ "sc.SALES_CHECK_STS_CD in ("+update_sales_check_eligible_status+") and o.site_id in ("+update_sales_check_store_exp+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') " +
					"and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
			
			Sql_Update_Sales_Check_Status = "select distinct o.site_gen_ord_id,sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
					+ "sc.SALES_CHECK_STS_CD NOT in ("+update_sales_check_eligible_status+") and o.site_id not in ("+update_sales_check_store_exp+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') " +
					"and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
			
			
	try {
			
			st = conn.prepareStatement(Sql_Update_Sales_Check_Eligible);
			Reporter.log("SQL Query: "+Sql_Update_Sales_Check_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			update_sales_check_eligible_orderID = rs.getString("site_gen_ord_id");
			System.out.println("update sales check eligible status: "+update_sales_check_eligible_orderID);
			update_sales_check_eligible_SCNO = rs.getString("sales_check_number");
			System.out.println("update sales check eligible status SCNO: "+update_sales_check_eligible_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Update_Sales_Check_Store);
			Reporter.log("SQL Query: "+Sql_Update_Sales_Check_Store);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			update_sales_check_store_exception_ID = rs.getString("site_gen_ord_id");
			System.out.println("update order store exception: "+update_sales_check_store_exception_ID);
			update_sales_check_store_exception_SCNO = rs.getString("sales_check_number");
			System.out.println("update order store exception SCNO: "+update_sales_check_store_exception_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	/**
	 * Not used
	 */
	try {
		
			st = conn.prepareStatement(Sql_Update_Sales_Check_Status);
			Reporter.log("SQL Query: "+Sql_Update_Sales_Check_Status);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			update_sales_check_status_exception_ID = rs.getString("site_gen_ord_id");
			System.out.println("update order status exception: "+update_sales_check_status_exception_ID);
			update_sales_check_status_exception_SCNO = rs.getString("sales_check_number");
			System.out.println("update order status exception SCNO: "+update_sales_check_status_exception_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
	}
	
	
	public static void contact_Customer_Sales_Check_Data() throws Exception{

		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.SalesCheckRulesSheetName);
		String cc_sales_check_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ContactCustomer_SalesCheck, Constant.Col_SCEligibleStatus).replaceAll("\"", "'");
		
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		
			Sql_Contact_Customer_Sales_Check_Eligible = "select distinct o.site_gen_ord_id,sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
					+ "sc.SALES_CHECK_STS_CD in ("+cc_sales_check_eligible_status+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
					+ "and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
	
			Sql_Contact_Customer_Sales_Check_Status_Exp = "select distinct o.site_gen_ord_id,sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
					+ "sc.SALES_CHECK_STS_CD NOT in ("+cc_sales_check_eligible_status+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";

	try {
			
			st = conn.prepareStatement(Sql_Contact_Customer_Sales_Check_Eligible);
			Reporter.log("SQL Query: "+Sql_Contact_Customer_Sales_Check_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			cc_sales_check_eligible_orderID = rs.getString("site_gen_ord_id");
			System.out.println("contact customer status exception: "+cc_sales_check_eligible_orderID);
			cc_sales_check_eligible_SCNO = rs.getString("sales_check_number");
			System.out.println("contact customer status exceptionvSCNO: "+cc_sales_check_eligible_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Contact_Customer_Sales_Check_Status_Exp);
			Reporter.log("SQL Query: "+Sql_Contact_Customer_Sales_Check_Status_Exp);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
		
			cc_sales_check_status_exception_ID = rs.getString("o.site_gen_ord_id");
			System.out.println("contact customer status exception: "+cc_sales_check_status_exception_ID);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
	}
	
//	TODO Pending
	public static void cancle_Sales_Check_Data() throws Exception{
		
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.SalesCheckRulesSheetName);
		String cancel_sc_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationSalesCheck, Constant.Col_SCEligibleStatus).replaceAll("\"", "'");
		String cancel_sc_store_exp = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationSalesCheck, Constant.Col_Storeexception_SC).replaceAll("\"", "'");
		String cancel_sc_status_store_check_status_r2 = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationSalesCheck_2forSSC, Constant.Col_StatusStoreCheck_Status).replaceAll("\"", "'");
		String cancel_sc_status_store_check_store_r2 = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationSalesCheck_2forSSC, Constant.Col_StatusStoreCheck_Stroe).replaceAll("\"", "'");
		String cancel_sc_status_store_check_status_r3 = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationSalesCheck_3forSSC, Constant.Col_StatusStoreCheck_Status).replaceAll("\"", "'");
		String cancel_sc_status_store_check_store_r3 = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationSalesCheck_3forSSC, Constant.Col_StatusStoreCheck_Stroe).replaceAll("\"", "'");
		String cancel_sc_ffmstatus_ffm = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationSalesCheck, Constant.Col_FulfillByStatusCheck_Ffm).replaceAll("\"", "'");
		String cancel_sc_ffmexp30 = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationSalesCheck, Constant.Col_FulfillbyexceptionexceptallowedFFMsduring30minhold_Ffm).replaceAll("\"", "'");
		String cancelation_Fulfill_30min_hold_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationSalesCheck, Constant.Col_FulfillbyexceptionexceptallowedFFMsduring30minhold_status).replaceAll("\"", "'");
		String cancelationFulfill30minhold_SFS = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationSalesCheck, Constant.Col_FulfillbyexceptionexceptallowedFFMsduring30minhold_statusSFS).replaceAll("\"", "'");
		String cancelationFulfill30minhold_ffm2 = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationSalesCheck, Constant.Col_FulfillbyexceptionexceptallowedFFMsduring30minhold_Ffm2).replaceAll("\"", "'");
		
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		
			
			Sql_SC_Cancellation_Eligible = "select DISTINCT o.site_gen_ord_id, sc.sales_check_number  from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi. sales_check_id = sc.sales_check_id and oi.ffm_method_id = fm.ffm_method_id "
					+ "and sc.SALES_CHECK_STS_CD in ('TRN') and NOT (sc.SALES_CHECK_STS_CD in ('FDC','RFP') AND o.site_id in ('10171')) "
					+ "and NOT (sc.SALES_CHECK_STS_CD in ('NN','F','PRO') AND o.site_id in ('10151','10153','30151','30153','40153')) and NOT fm.ffm_class_id in ('KHD') "
					+ "and NOT fm.ffm_class_id in ('TRYBUY','LAYAWAY','SFS','STS','LWY','XVRES','XDRES','XCRES','FBM') "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
					+ "and oi.order_item_sts_cd NOT in ('PCON','TEST','SPM','BAD','VDC') and o.site_gen_ord_id like '8%' "
					+ "and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and sc.sales_check_number REGEXP '^-?[0-9]+$' and o.last_updated_ts > DATE_SUB(CURDATE(),INTERVAL 30 DAY) ORDER BY RAND() limit 1";
					
		
			Sql_SC_Cancellation_Store_Exp = "select o.site_gen_ord_id, sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id "
					+ "and sc.SALES_CHECK_STS_CD in ("+cancel_sc_eligible_status+") and o.site_id in ("+cancel_sc_store_exp+") "
					+ "and NOT (sc.SALES_CHECK_STS_CD in ("+cancel_sc_status_store_check_status_r2+") AND o.site_id in ("+cancel_sc_status_store_check_store_r2+")) "
					+ "and NOT (sc.SALES_CHECK_STS_CD in ("+cancel_sc_status_store_check_status_r3+") AND o.site_id in ("+cancel_sc_status_store_check_store_r3+")) "
					+ "and NOT fm.ffm_class_id in ("+cancel_sc_ffmstatus_ffm+") "
					+ "and NOT fm.ffm_class_id in ("+cancel_sc_ffmexp30+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') and o.site_gen_ord_id like '8%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' LIMIT 1";
			
			Sql_SC_Cancellation_StatusStore_Exp = "select o.site_gen_ord_id, sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id "
					+ "and sc.SALES_CHECK_STS_CD in ("+cancel_sc_eligible_status+") and o.site_id not in ("+cancel_sc_store_exp+") "
					+ "and (sc.SALES_CHECK_STS_CD in ("+cancel_sc_status_store_check_status_r2+") AND o.site_id in ("+cancel_sc_status_store_check_store_r2+")) "
					+ "and (sc.SALES_CHECK_STS_CD in ("+cancel_sc_status_store_check_status_r3+") AND o.site_id in ("+cancel_sc_status_store_check_store_r3+")) "
					+ "and NOT fm.ffm_class_id in ("+cancel_sc_ffmstatus_ffm+") "
					+ "and NOT fm.ffm_class_id in ("+cancel_sc_ffmexp30+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') and o.site_gen_ord_id like '8%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' LIMIT 1";

			Sql_SC_Cancellation_ffmexp30 = "select o.site_gen_ord_id, sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm, ord_item_extn oie "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and oi.ORDER_ITEM_ID = oie.ORDER_ITEM_ID and "
					+ "o.site_id not in ("+cancel_sc_store_exp+") and oie.ITEM_HELD_TO_ENABLE_CANCEL = 'YES' and sc.SALES_CHECK_STS_CD in ("+cancelation_Fulfill_30min_hold_status+") and fm.ffm_class_id "
					+ "in ("+cancelationFulfill30minhold_SFS+","+cancelationFulfill30minhold_ffm2+") and NOT fm.ffm_class_id in ("+cancel_sc_ffmstatus_ffm+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' LIMIT 1";
			
			
	try {
			
			st = conn.prepareStatement(Sql_SC_Cancellation_Eligible);
			Reporter.log("SQL Query: "+Sql_SC_Cancellation_Eligible);
			System.out.println("cancel query - "+Sql_SC_Cancellation_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			cancel_sc_eligible_orderID = rs.getString("site_gen_ord_id");
			System.out.println("cancel sales check eligible status: "+ cancel_sc_eligible_orderID);
			cancel_sc_eligible_SCNO = rs.getString("sales_check_number");
			System.out.println("cancel sales check eligible statusvSCNO: "+ cancel_sc_eligible_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_SC_Cancellation_Store_Exp);
			Reporter.log("SQL Query: "+Sql_SC_Cancellation_Store_Exp);
			System.out.println("cancel exception query - "+Sql_SC_Cancellation_Store_Exp);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			cancel_sc_store_exception_ID = rs.getString("site_gen_ord_id");
			System.out.println("cancel sales check store exception: "+ cancel_sc_store_exception_ID);
			cancel_sc_store_exception_SCNO = rs.getString("sales_check_number");
			System.out.println("cancel sales check store exception SCNO: "+ cancel_sc_store_exception_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_SC_Cancellation_StatusStore_Exp);
			Reporter.log("SQL Query: "+Sql_SC_Cancellation_StatusStore_Exp);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			cancel_sc_statusstore_exception_ID = rs.getString("site_gen_ord_id");
			System.out.println("cancel sales check status store exception: "+ cancel_sc_statusstore_exception_ID);
			cancel_sc_statusstore_exception_SCNO = rs.getString("sales_check_number");
			System.out.println("cancel sales check status store exception SCNO: "+ cancel_sc_statusstore_exception_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

	
	try {
		
			st = conn.prepareStatement(Sql_SC_Cancellation_ffmexp30);
			Reporter.log("SQL Query: "+Sql_SC_Cancellation_ffmexp30);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			cancel_sc_30mins_eligible_ID = rs.getString("site_gen_ord_id");
			System.out.println("cancel sales check Fulfillby allowed FFMs during 30 min hold: "+ cancel_sc_30mins_eligible_ID);
			cancel_sc_30mins_eligible_SCNO = rs.getString("sales_check_number");
			System.out.println("cancel sales check Fulfillby allowed FFMs during 30 min hold SCNO: "+ cancel_sc_30mins_eligible_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
	}
	

	public static void ready_for_Pickup_Email_Data() throws Exception{

		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.SalesCheckRulesSheetName);
		String ready_for_Pickup_Email_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReadyforPickupEmail, Constant.Col_SCEligibleStatus).replaceAll("\"", "'");
		String ready_for_Pickup_Email_store_exp = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReadyforPickupEmail, Constant.Col_Storeexception_SC).replaceAll("\"", "'");
		
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		
			Sql_Ready_for_Pickup_Email_Eligible = "select distinct o.site_gen_ord_id, sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id "
					+ "and oi.order_item_sts_cd in ("+ready_for_Pickup_Email_eligible_status+") "
					+ "and o.SITE_ID not in ("+ready_for_Pickup_Email_store_exp+") and sc.SALES_CHECK_STS_CD NOT in ('NCON') "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') limit 1";
	
			Sql_Ready_for_Pickup_Email_Store = "select distinct o.site_gen_ord_id, sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
					+ "oi.order_item_sts_cd in ("+ready_for_Pickup_Email_eligible_status+") and o.SITE_ID in ("+ready_for_Pickup_Email_store_exp+") "
					+ "and sc.SALES_CHECK_STS_CD NOT in ('NCON') and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') " +
					"and oi.order_item_sts_cd NOT in ('PCON','TEST')  and o.site_gen_ord_id REGEXP '^-?[0-9]+$' " +
					"limit 1";
			
			
			Sql_Ready_for_Pickup_Email_Status = "select distinct o.site_gen_ord_id, sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and oi.order_item_sts_cd "
					+ "not in ("+ready_for_Pickup_Email_eligible_status+") and o.SITE_ID in ("+ready_for_Pickup_Email_store_exp+") "
					+ "and sc.SALES_CHECK_STS_CD NOT in ('NCON') and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') " +
					"and oi.order_item_sts_cd NOT in ('PCON','TEST') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' limit 1";
			
			
	try {
			
			st = conn.prepareStatement(Sql_Ready_for_Pickup_Email_Eligible);
			Reporter.log("SQL Query: "+Sql_Ready_for_Pickup_Email_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			ready_for_Pickup_Email_eligible_orderID = rs.getString("site_gen_ord_id");
			System.out.println("ready for pick up email eligible status: "+ready_for_Pickup_Email_eligible_orderID);
			ready_for_Pickup_Email_eligible_SCNO = rs.getString("sales_check_number");
			System.out.println("ready for pick up email eligible status SCNO: "+ready_for_Pickup_Email_eligible_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Ready_for_Pickup_Email_Store);
			Reporter.log("SQL Query: "+Sql_Ready_for_Pickup_Email_Store);
			System.out.println(Sql_Ready_for_Pickup_Email_Store);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			ready_for_Pickup_Email_store_exception_ID = rs.getString("site_gen_ord_id");
			System.out.println("ready for pick up email store exception: "+ready_for_Pickup_Email_store_exception_ID);
			ready_for_Pickup_Email_store_exception_SCNO = rs.getString("sales_check_number");
			System.out.println("ready for pick up email store exception SCNO: "+ready_for_Pickup_Email_store_exception_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_Ready_for_Pickup_Email_Status);
			Reporter.log("SQL Query: "+Sql_Ready_for_Pickup_Email_Status);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			ready_for_Pickup_Email_status_exception_ID = rs.getString("site_gen_ord_id");
			System.out.println("ready for pick up email status exception: "+ready_for_Pickup_Email_status_exception_ID);
			ready_for_Pickup_Email_status_exception_SCNO = rs.getString("sales_check_number");
			System.out.println("ready for pick up email status exception SCNO: "+ready_for_Pickup_Email_status_exception_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
	}
	
	
	
	
	/*****************************
	 * * * Line Item Level * * *
	 *****************************/
	static String Sql_sales_Adjustment_Eligible = null;
	static String Sql_sales_Adjustment_scNo_Expt = null;
	static String Sql_sales_Adjustment_FFMExpt = null;
	static String Sql_sales_Adjustment_StatusExpt = null;
	static String Sql_contact_Marketplace_Seller_Eligible = null;
	static String Sql_contact_Marketplace_Seller_StoreExpt = null;
	static String Sql_contact_Marketplace_Seller_Vendor_id_sc_Expt1 = null;
	static String Sql_contact_Vendor_Eligible = null;
	static String Sql_contact_Vendor_StoreExpt = null;
	static String Sql_contact_Vendor_Vendor_id_sc_Expt2 = null;
	static String Sql_update_Expected_Ship_Arrival_Date_Eligible = null;
	static String Sql_update_Expected_Ship_Arrival_Date_StoreExpt = null;
	static String Sql_update_Expected_Ship_Arrival_Date_FFMExpt = null;
	static String Sql_return_Tracking_Information_Eligible = null;
	static String Sql_return_Tracking_Information_StoreExpt = null;
	static String Sql_contact_Customer_LineItem_Eligible = null;
	static String Sql_LineItem_Cancellation_Eligible = null;
	static String Sql_LineItem_Cancellation_Status_Expt = null;
	static String Sql_reschedule_Delivery_Eligible = null;
	static String Sql_reschedule_Delivery_StoreExpt = null;
	static String Sql_reschedule_Delivery_FFMinExpt = null;
	static String Sql_schedule_Return_Eligible = null;
	static String Sql_schedule_Return_StoreExpt = null;
	static String Sql_schedule_Return_FFMinExpt = null;
	static String Sql_LineItem_Return_Item_Eligible = null;
	static String Sql_LineItem_Return_Item_Status_Expt = null;
	static String Sql_LineItem_Start_Automated_Return_Eligible = null;
	static String Sql_LineItem_Start_Automated_Return_Status_Expt = null;
	public static String lineitem_sale_adjustment_eligible_orderID = null;
	public static String lineitem_sale_adjustment_eligible_SKU = null;
	public static String lineitem_sale_adjustment_sc_Expt_orderID = null;
	public static String lineitem_sale_adjustment_sc_Expt_SKU = null;
	public static String lineitem_sale_adjustment_store_Expt_orderID = null;
	public static String lineitem_sale_adjustment_store_Expt_SKU = null;
	public static String lineitem_sale_adjustment_status_Expt_orderID = null;
	public static String lineitem_sale_adjustment_status_Expt_SKU = null;
	public static String lineitem_market_place_seller_eligible_orderID = null;
	public static String market_place_seller_eligible_sku = null;
	public static String market_place_seller_eligible_item_number = null;
	public static String lineitem_market_place_seller_Vendor_id_sc_Expt1_orderID = null;
	public static String lineitem_market_place_seller_Vendor_id_sc_Expt1_SKU = null;
	public static String lineitem_market_place_seller_store_Expt_orderID = null;
	public static String lineitem_market_place_seller_store_Expt_SKU = null;
	public static String lineitem_Vendor_eligible_orderID = null;
	public static String lineitem_Vendor_eligible_SKU = null;
	public static String lineitem_Vendor_Vendor_id_sc_Expt2_orderID = null;
	public static String lineitem_Vendor_Vendor_id_sc_Expt2_SKU = null;
	public static String lineitem_Vendor_store_Expt_orderID = null;
	public static String lineitem_Vendor_store_Expt_SKU = null;
	public static String lineitem_update_Expected_Ship_Arrival_D_Eligible_orderID = null;
	public static String lineitem_update_Expected_Ship_Arrival_D_Eligible_SKU = null;
	public static String lineitem_update_Expected_Ship_Arrival_D_StoreExpt_orderID = null;
	public static String lineitem_update_Expected_Ship_Arrival_D_StoreExpt_SKU = null;
	public static String lineitem_update_Expected_Ship_Arrival_D_FFMExpt_orderID = null;
	public static String lineitem_update_Expected_Ship_Arrival_D_FFMExpt_SKU = null;
	public static String lineitem_Return_Tracking_Information_Eligible_orderID = null;
	public static String lineitem_Return_Tracking_Information_Eligible_SKU = null;
	public static String lineitem_Return_Tracking_Information_StoreExpt_orderID = null;
	public static String lineitem_Return_Tracking_Information_StoreExpt_SKU = null;
	public static String lineitem_Contact_Customer_Eligible_orderID = null;
	public static String lineitem_Contact_Customer_Eligible_SKU = null;
	public static String lineitem_Cancellation_Eligible_orderID = null;
	public static String lineitem_Cancellation_Eligible_SKU = null;
	public static String lineitem_Cancellation_StatusExpt_orderID = null;
	public static String lineitem_Cancellation_StatusExpt_SKU = null;
	public static String lineitem_reschedule_Delivery_Eligible_orderID = null;
	public static String lineitem_reschedule_Delivery_Eligible_SKU = null;
	public static String lineitem_reschedule_Delivery_StoreExpt_orderID = null;
	public static String lineitem_reschedule_Delivery_StoreExpt_SKU = null;
	public static String lineitem_reschedule_Delivery_FFMinExpt_orderID = null;
	public static String lineitem_reschedule_Delivery_FFMinExpt_SKU = null;
	public static String lineitem_schedule_Return_Eligible_orderID = null;
	public static String lineitem_schedule_Return_Eligible_SKU = null;
	public static String lineitem_schedule_Return_StoreExpt_orderID = null;
	public static String lineitem_schedule_Return_StoreExpt_SKU = null;
	public static String lineitem_schedule_Return_FFMinExpt_orderID = null;
	public static String lineitem_schedule_Return_FFMinExpt_SKU = null;
	public static String lineitem_Return_Item_Eligible_orderID = null;
	public static String lineitem_Return_Item_Eligible_SKU = null;
	public static String lineitem_Return_Item_StatusExpt_orderID = null;
	public static String lineitem_Return_Item_StatusExpt_SKU = null;
	public static String lineitem_Start_Automated_Return_Eligible_orderID = null;
	public static String lineitem_Start_Automated_Return_Eligible_SKU = null;
	public static String lineitem_Start_Automated_Return_StatusExpt_orderID = null;
	public static String lineitem_Start_Automated_Return_StatusExpt_SKU = null;

	public static ThreadLocal<Retrieval_Test_Data_By_Query> thread = new ThreadLocal<Retrieval_Test_Data_By_Query>() {
		protected Retrieval_Test_Data_By_Query initialValue() {
			return new Retrieval_Test_Data_By_Query();
		}
	};
	
	public static Retrieval_Test_Data_By_Query getRetrievalTestDataByQuery(){
		return thread.get();
	}
 public synchronized void  sale_Adjustment_Line_Item_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.LineItemRulesSheetName);
		String lineItem_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_SalesAdjustment, Constant.Col_ItemEligibleStatus).replaceAll("\"", "'");
		String LineItem_SalesCheckNO_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_SalesAdjustment, Constant.Col_Salescheckno_exception).replaceAll("\"", "");
		String lineItem_store_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_SalesAdjustment, Constant.Col_Storeexception_Item).replaceAll("\"", "'");
		String lineItem_ffm_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_SalesAdjustment, Constant.Col_Fulfilbyexception).replaceAll("\"", "'");
		String lineItem_hdd_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_SalesAdjustment, Constant.Col_Itemdescriptionexception).replaceAll("\"", "'");
		
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		
			Sql_sales_Adjustment_Eligible = "SELECT o.SITE_GEN_ORD_ID,oi.item_id FROM ORD O,ORD_ITEM OI,SALES_CHECK SC,FFM_METHOD FFM, payment p,payment_instruction pi,pmt_method pm "
					+ "WHERE O.ORDER_SERVICE_ORGINATING_SERVER in ('qa.ecom.sears.com') AND O.ORDER_ID=OI.ORDER_ID AND SC.ORDER_ID=O.ORDER_ID "
					+ "AND SC.SALES_CHECK_ID=OI.SALES_CHECK_ID and FFM.FFM_METHOD_id=OI.FFM_METHOD_id and pi.PAYMENT_METHOD_ID=pm.payment_method_id "
					+ "and p.PAY_INSTRUCTION_ID=pi.PAY_INSTRUCTION_ID and sc.sales_check_id=p.sales_check_id and o.ORDER_ID=pi.order_ID and FFM.FFM_CLASS_ID "
					+ " not in("+lineItem_ffm_Exception+",'LWY') AND OI.ORDER_ITEM_STS_CD IN ("+lineItem_eligible_status+") and NOT sc.SALES_CHECK_NUMBER "
					+ "like '"+LineItem_SalesCheckNO_Exception+"%' and o.SITE_ID not in ("+lineItem_store_Exception+") and oi.ITEM_NM NOT in ("+lineItem_hdd_Exception+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
					+ "and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-06-15 01:01:01' limit 1";
					
			//TODO Remove. Not used in DP
			Sql_sales_Adjustment_scNo_Expt = "SELECT o.SITE_GEN_ORD_ID,oi.item_id FROM ORD O,ORD_ITEM OI,SALES_CHECK SC,FFM_METHOD FFM, payment p,payment_instruction pi,pmt_method pm "
					+ "WHERE O.ORDER_SERVICE_ORGINATING_SERVER in ('qa.ecom.sears.com') AND O.ORDER_ID=OI.ORDER_ID AND SC.ORDER_ID=O.ORDER_ID "
					+ "AND SC.SALES_CHECK_ID=OI.SALES_CHECK_ID and FFM.FFM_METHOD_id=OI.FFM_METHOD_id and pi.PAYMENT_METHOD_ID=pm.payment_method_id "
					+ "and p.PAY_INSTRUCTION_ID=pi.PAY_INSTRUCTION_ID and sc.sales_check_id=p.sales_check_id and o.ORDER_ID=pi.order_ID and FFM.FFM_CLASS_ID "
					+ " not in("+lineItem_ffm_Exception+",'LWY') AND OI.ORDER_ITEM_STS_CD IN ("+lineItem_eligible_status+") and sc.SALES_CHECK_NUMBER "
					+ "like '"+LineItem_SalesCheckNO_Exception+"%' and o.SITE_ID not in ("+lineItem_store_Exception+") and oi.ITEM_NM NOT in ("+lineItem_hdd_Exception+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
					+ "and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-06-15 01:01:01' limit 1";
			//TODO Remove. Not used in Dp
			Sql_sales_Adjustment_FFMExpt = "SELECT o.SITE_GEN_ORD_ID,oi.item_id FROM ORD O,ORD_ITEM OI,SALES_CHECK SC,FFM_METHOD FFM, payment p,payment_instruction pi,pmt_method pm "
					+ "WHERE O.ORDER_SERVICE_ORGINATING_SERVER in ('qa.ecom.sears.com') AND O.ORDER_ID=OI.ORDER_ID AND SC.ORDER_ID=O.ORDER_ID "
					+ "AND SC.SALES_CHECK_ID=OI.SALES_CHECK_ID and FFM.FFM_METHOD_id=OI.FFM_METHOD_id and pi.PAYMENT_METHOD_ID=pm.payment_method_id "
					+ "and p.PAY_INSTRUCTION_ID=pi.PAY_INSTRUCTION_ID and sc.sales_check_id=p.sales_check_id and o.ORDER_ID=pi.order_ID and FFM.FFM_CLASS_ID "
					+ "not in("+lineItem_ffm_Exception+",'LWY') AND OI.ORDER_ITEM_STS_CD IN ("+lineItem_eligible_status+") and NOT sc.SALES_CHECK_NUMBER "
					+ "like '"+LineItem_SalesCheckNO_Exception+"%' and o.SITE_ID in ("+lineItem_store_Exception+") and oi.ITEM_NM NOT in ("+lineItem_hdd_Exception+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
					+ "and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-06-15 01:01:01' limit 1";
			Sql_sales_Adjustment_StatusExpt = "SELECT o.SITE_GEN_ORD_ID,oi.item_id FROM ORD O,ORD_ITEM OI,SALES_CHECK SC,FFM_METHOD FFM, payment p,payment_instruction pi,pmt_method pm "
					+ "WHERE O.ORDER_SERVICE_ORGINATING_SERVER in ('qa.ecom.sears.com') AND O.ORDER_ID=OI.ORDER_ID AND SC.ORDER_ID=O.ORDER_ID "
					+ "AND SC.SALES_CHECK_ID=OI.SALES_CHECK_ID and FFM.FFM_METHOD_id=OI.FFM_METHOD_id and pi.PAYMENT_METHOD_ID=pm.payment_method_id "
					+ "and p.PAY_INSTRUCTION_ID=pi.PAY_INSTRUCTION_ID and sc.sales_check_id=p.sales_check_id and o.ORDER_ID=pi.order_ID and FFM.FFM_CLASS_ID "
					+ " not in("+lineItem_ffm_Exception+",'LWY') AND OI.ORDER_ITEM_STS_CD NOT IN ("+lineItem_eligible_status+") and NOT sc.SALES_CHECK_NUMBER "
					+ "like '"+LineItem_SalesCheckNO_Exception+"%' and o.SITE_ID not in ("+lineItem_store_Exception+") and oi.ITEM_NM NOT in ("+lineItem_hdd_Exception+") "
					+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
					+ "and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-06-15 01:01:01' limit 1";
					
	try {
			
			st = conn.prepareStatement(Sql_sales_Adjustment_Eligible);
			Reporter.log("SQL Query: "+Sql_sales_Adjustment_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_sale_adjustment_eligible_orderID = rs.getString("SITE_GEN_ORD_ID");
				System.out.println("Sales Adjustment Eligible OrderID: "+lineitem_sale_adjustment_eligible_orderID);
				lineitem_sale_adjustment_eligible_SKU = rs.getString("item_id");
				System.out.println("Sales Adjustment Eligible SKU: "+lineitem_sale_adjustment_eligible_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

	
	try {
		
			st = conn.prepareStatement(Sql_sales_Adjustment_scNo_Expt);
			Reporter.log("SQL Query: "+Sql_sales_Adjustment_scNo_Expt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			lineitem_sale_adjustment_sc_Expt_orderID = rs.getString("SITE_GEN_ORD_ID");
			System.out.println("Sales Adjustment SCNo. Exception OrderID: "+lineitem_sale_adjustment_sc_Expt_orderID);
			lineitem_sale_adjustment_sc_Expt_SKU = rs.getString("item_id");
			System.out.println("Sales Adjustment SCNo. Exception SKU: "+lineitem_sale_adjustment_sc_Expt_SKU);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		//TODO Remvoe. Not used
			st = conn.prepareStatement(Sql_sales_Adjustment_FFMExpt);
			Reporter.log("SQL Query: "+Sql_sales_Adjustment_FFMExpt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_sale_adjustment_store_Expt_orderID = rs.getString("SITE_GEN_ORD_ID");
				System.out.println("Sales Adjustment Status Exception OrderID: "+lineitem_sale_adjustment_store_Expt_orderID);
				lineitem_sale_adjustment_store_Expt_SKU = rs.getString("item_id");
				System.out.println("Sales Adjustment Status Exception SKU: "+lineitem_sale_adjustment_store_Expt_SKU);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	
	 try {
			
			st = conn.prepareStatement(Sql_sales_Adjustment_StatusExpt);
			Reporter.log("SQL Query: "+Sql_sales_Adjustment_StatusExpt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_sale_adjustment_status_Expt_orderID = rs.getString("SITE_GEN_ORD_ID");
				System.out.println("Sales Adjustment Store Exception OrderID: "+lineitem_sale_adjustment_status_Expt_orderID);
				lineitem_sale_adjustment_status_Expt_SKU = rs.getString("item_id");
				System.out.println("Sales Adjustment Store Exception SKU: "+lineitem_sale_adjustment_status_Expt_SKU);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		
}
	public void contact_Marketplace_Seller_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.LineItemRulesSheetName);
		String lineItem_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ContactMarketplaceSeller, Constant.Col_ItemEligibleStatus).replaceAll("\"", "'");
		String LineItem_SalesCheckNO_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ContactMarketplaceSeller, Constant.Col_Vendoridsalescheckexception1).replaceAll("\"", "");
		String lineItem_store_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ContactMarketplaceSeller, Constant.Col_Storeexception_Item).replaceAll("\"", "'");
		System.out.println(lineItem_eligible_status);
		System.out.println(LineItem_SalesCheckNO_Exception);
		System.out.println(lineItem_store_Exception);
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		
			Sql_contact_Marketplace_Seller_Eligible = "SELECT o.SITE_GEN_ORD_ID, oi.order_item_id, oi.item_id FROM ORD O,ORD_ITEM OI,SALES_CHECK SC,FFM_METHOD FFM, payment p,payment_instruction pi,pmt_method pm "
					+ " WHERE O.ORDER_SERVICE_ORGINATING_SERVER in ('qa.ecom.sears.com') and O.RECORD_INSERT_TS >'2015-05-25 19:08:58' AND O.ORDER_ID=OI.ORDER_ID  "
					+ "AND SC.ORDER_ID=O.ORDER_ID  AND SC.SALES_CHECK_ID=OI.SALES_CHECK_ID and FFM.FFM_METHOD_id=OI.FFM_METHOD_id and pi.PAYMENT_METHOD_ID=pm.payment_method_id "
					+ "and p.PAY_INSTRUCTION_ID=pi.PAY_INSTRUCTION_ID and sc.sales_check_id=p.sales_check_id and o.ORDER_ID=pi.order_ID and FFM.FFM_CLASS_ID in('FBM') "
					+ "AND OI.ORDER_ITEM_STS_CD IN ('SHP','PRO') " 
					+  " order by O.RECORD_INSERT_TS desc LIMIT 1 ";
										
/*					"select o.site_gen_ord_id, oi.ORDER_ITEM_ID from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id "
					+ "and oi.order_item_sts_cd in ("+lineItem_eligible_status+") and sc.SALES_CHECK_NUMBER like '"+LineItem_SalesCheckNO_Exception+"%' and "
					+ "o.site_id not in ("+lineItem_store_Exception+") and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-04-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
*/		
					Sql_contact_Marketplace_Seller_Vendor_id_sc_Expt1 = "select distinct o.site_gen_ord_id, oi.item_id, oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
					+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and oi.order_item_sts_cd NOT in ('PCON','TEST','') and sc.SALES_CHECK_NUMBER NOT like '"+LineItem_SalesCheckNO_Exception+ "'"
					+ "and o.site_id not in ("+lineItem_store_Exception+") and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-04-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
					
					Sql_contact_Marketplace_Seller_StoreExpt = "select distinct o.site_gen_ord_id, oi.item_id, oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
					+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
					+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and sc.SALES_CHECK_NUMBER like '"+LineItem_SalesCheckNO_Exception+ "'"
					+ "and o.site_id in ("+lineItem_store_Exception+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-04-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
					
					String sql_sku ="select item_id from ord_item where order_item_id=?";
	
	try {
			
			st = conn.prepareStatement(Sql_contact_Marketplace_Seller_Eligible);
			Reporter.log("SQL Query: "+Sql_contact_Marketplace_Seller_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_market_place_seller_eligible_orderID = rs.getString("site_gen_ord_id");
				market_place_seller_eligible_item_number = rs.getString("order_item_id");
				System.out.println("Marketplace Seller Eligible: "+lineitem_market_place_seller_eligible_orderID);
				System.out.println("Marketplace Seller Eligible item number: "+market_place_seller_eligible_item_number);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{
		st = conn.prepareStatement(sql_sku);
		st.setString (1, market_place_seller_eligible_item_number);
		Reporter.log("SQL Query: "+market_place_seller_eligible_item_number);
		st.execute();
		rs = st.getResultSet();
		
		while(rs.next()){
		market_place_seller_eligible_sku = rs.getString("item_id");
		System.out.println("Marketplace Seller Eligible sku: "+market_place_seller_eligible_sku);
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}
	
	try {
		
			st = conn.prepareStatement(Sql_contact_Marketplace_Seller_Vendor_id_sc_Expt1);
			Reporter.log("SQL Query: "+Sql_contact_Marketplace_Seller_Vendor_id_sc_Expt1);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
			lineitem_market_place_seller_Vendor_id_sc_Expt1_orderID = rs.getString("site_gen_ord_id");
			System.out.println("Marketplace Seller Vendor ID Exception: "+lineitem_market_place_seller_Vendor_id_sc_Expt1_orderID);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_contact_Marketplace_Seller_StoreExpt);
			Reporter.log("SQL Query: "+Sql_contact_Marketplace_Seller_StoreExpt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_market_place_seller_store_Expt_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Marketplace Seller Store Exception OrderID: "+lineitem_market_place_seller_store_Expt_orderID);
				lineitem_market_place_seller_store_Expt_SKU = rs.getString("item_id");
				System.out.println("Marketplace Seller Store Exception SKU: "+lineitem_market_place_seller_store_Expt_SKU);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}
	
	
	public void contact_Vendor_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.LineItemRulesSheetName);
		String lineItem_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ContactVendor, Constant.Col_ItemEligibleStatus).replaceAll("\"", "'");
		String LineItem_SalesCheckNO_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ContactVendor, Constant.Col_Vendoridsalescheckexception2).replaceAll("\"", "'");
		String lineItem_store_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ContactVendor, Constant.Col_Storeexception_Item).replaceAll("\"", "'");
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_contact_Vendor_Eligible = "select distinct o.site_gen_ord_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and sc.SALES_CHECK_NUMBER  NOT like "+LineItem_SalesCheckNO_Exception+ " and "
				+ "o.site_id not in ("+lineItem_store_Exception+") and fm.ffm_class_id ='VD' and o.site_gen_ord_id like '9%' and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
		Sql_contact_Vendor_Vendor_id_sc_Expt2 = "select distinct o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and sc.SALES_CHECK_NUMBER like "+LineItem_SalesCheckNO_Exception+ " and "
				+ "o.site_id not in ("+lineItem_store_Exception+") and o.site_gen_ord_id like '9%' and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
		Sql_contact_Vendor_StoreExpt = "select distinct o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and NOT sc.SALES_CHECK_NUMBER like "+LineItem_SalesCheckNO_Exception+ " and "
				+ "o.site_id in ("+lineItem_store_Exception+") and o.site_gen_ord_id like '9%' and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";

	try {
			
			st = conn.prepareStatement(Sql_contact_Vendor_Eligible);
			Reporter.log("SQL Query: "+Sql_contact_Vendor_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Vendor_eligible_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Contact Vendor Eligible OrderID: "+lineitem_Vendor_eligible_orderID);
				lineitem_Vendor_eligible_SKU = rs.getString("item_id");
				System.out.println("Contact Vendor Eligible SKU: "+lineitem_Vendor_eligible_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

	
	try {
		
			st = conn.prepareStatement(Sql_contact_Vendor_Vendor_id_sc_Expt2);
			Reporter.log("SQL Query: "+Sql_contact_Vendor_Vendor_id_sc_Expt2);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Vendor_Vendor_id_sc_Expt2_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Contact Vendor Vendor ID Exception OrderID: "+lineitem_Vendor_Vendor_id_sc_Expt2_orderID);
				lineitem_Vendor_Vendor_id_sc_Expt2_SKU = rs.getString("item_id");
				System.out.println("Contact Vendor Vendor ID Exception SKU: "+lineitem_Vendor_Vendor_id_sc_Expt2_SKU);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_contact_Vendor_StoreExpt);
			Reporter.log("SQL Query: "+Sql_contact_Vendor_StoreExpt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Vendor_store_Expt_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Contact Vendor Store Exception OrderID: "+lineitem_Vendor_store_Expt_orderID);
				lineitem_Vendor_store_Expt_SKU = rs.getString("item_id");
				System.out.println("Contact Vendor Store Exception SKU: "+lineitem_Vendor_store_Expt_SKU);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}
	 
	
	public void update_Expected_Ship_Arrival_Date_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.LineItemRulesSheetName);
		String lineItem_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_UpdateExpectedShip_ArrivalDate, Constant.Col_ItemEligibleStatus).replaceAll("\"", "'");
		String lineItem_store_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_UpdateExpectedShip_ArrivalDate, Constant.Col_Storeexception_Item).replaceAll("\"", "'");
		String lineItem_FFM_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_UpdateExpectedShip_ArrivalDate, Constant.Col_Fulfilbyexception).replaceAll("\"", "'");
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_update_Expected_Ship_Arrival_Date_Eligible = "select distinct o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and oi.order_item_sts_cd "
				+ "in ("+lineItem_eligible_status+") and o.site_id not in ("+lineItem_store_Exception+")"
				+ "and fm.ffm_class_id not in ("+lineItem_FFM_Exception+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') " +
						"and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
		Sql_update_Expected_Ship_Arrival_Date_StoreExpt = "select distinct o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and oi.order_item_sts_cd "
				+ "in ("+lineItem_eligible_status+") and o.site_id in ("+lineItem_store_Exception+") "
				+ "and fm.ffm_class_id not in ("+lineItem_FFM_Exception+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
		Sql_update_Expected_Ship_Arrival_Date_FFMExpt = "select distinct o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and oi.order_item_sts_cd "
				+ "not in ("+lineItem_eligible_status+") and o.site_id not in ("+lineItem_store_Exception+") "
				+ "and fm.ffm_class_id in ("+lineItem_FFM_Exception+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";

		try {
			
			st = conn.prepareStatement(Sql_update_Expected_Ship_Arrival_Date_Eligible);
			Reporter.log("SQL Query: "+Sql_update_Expected_Ship_Arrival_Date_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_update_Expected_Ship_Arrival_D_Eligible_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Update Expected Ship Arrival Date Eligible OrderID: "+lineitem_update_Expected_Ship_Arrival_D_Eligible_orderID);
				lineitem_update_Expected_Ship_Arrival_D_Eligible_SKU = rs.getString("item_id");
				System.out.println("Update Expected Ship Arrival Date Eligible SKU: "+lineitem_update_Expected_Ship_Arrival_D_Eligible_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

	
		try {
			
				st = conn.prepareStatement(Sql_update_Expected_Ship_Arrival_Date_StoreExpt);
				System.out.println("UpdateShipDate non eligible SQL Query: "+Sql_update_Expected_Ship_Arrival_Date_StoreExpt);
				st.execute();
				rs = st.getResultSet();
				while(rs.next()){
					lineitem_update_Expected_Ship_Arrival_D_StoreExpt_orderID = rs.getString("site_gen_ord_id");
					System.out.println("Update Expected Ship Arrival Date Store Exception OrderID: "+lineitem_update_Expected_Ship_Arrival_D_StoreExpt_orderID);
					lineitem_update_Expected_Ship_Arrival_D_StoreExpt_SKU = rs.getString("item_id");
					System.out.println("Update Expected Ship Arrival Date Store Exception SKU: "+lineitem_update_Expected_Ship_Arrival_D_StoreExpt_SKU);
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		try {
			
				st = conn.prepareStatement(Sql_update_Expected_Ship_Arrival_Date_FFMExpt);
				Reporter.log("SQL Query: "+Sql_update_Expected_Ship_Arrival_Date_FFMExpt);
				st.execute();
				rs = st.getResultSet();
				while(rs.next()){
					lineitem_update_Expected_Ship_Arrival_D_FFMExpt_orderID = rs.getString("site_gen_ord_id");
					System.out.println("Update Expected Ship Arrival Date Fulfilment Exception Order ID: "+lineitem_update_Expected_Ship_Arrival_D_FFMExpt_orderID);
					lineitem_update_Expected_Ship_Arrival_D_FFMExpt_SKU = rs.getString("item_id");
					System.out.println("Update Expected Ship Arrival Date Fulfilment Exception SKU: "+lineitem_update_Expected_Ship_Arrival_D_FFMExpt_SKU);
				}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		}
	

	public void return_Tracking_Information_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.LineItemRulesSheetName);
		String lineItem_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReturnTrackingInformation, Constant.Col_ItemEligibleStatus).replaceAll("\"", "'");
		String lineItem_store_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReturnTrackingInformation, Constant.Col_Storeexception_Item).replaceAll("\"", "'");
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		

		Sql_return_Tracking_Information_Eligible = "select o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and o.site_id not in ("+lineItem_store_Exception+") "
				+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
		Sql_return_Tracking_Information_StoreExpt = "select o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and o.site_id in ("+lineItem_store_Exception+") "
				+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";
		try {
			
			st = conn.prepareStatement(Sql_return_Tracking_Information_Eligible);
			Reporter.log("SQL Query: "+Sql_return_Tracking_Information_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Return_Tracking_Information_Eligible_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Return Tracking Information Eligible OrderID: "+lineitem_Return_Tracking_Information_Eligible_orderID);
				lineitem_Return_Tracking_Information_Eligible_SKU = rs.getString("item_id");
				System.out.println("Return Tracking Information Eligible SKU: "+lineitem_Return_Tracking_Information_Eligible_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

	
	try {
		
			st = conn.prepareStatement(Sql_return_Tracking_Information_StoreExpt);
			System.out.println("Return tracking non eligible SQL Query: "+Sql_return_Tracking_Information_StoreExpt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Return_Tracking_Information_StoreExpt_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Return Tracking Information Store Exception OrderID: "+lineitem_Return_Tracking_Information_StoreExpt_orderID);
				lineitem_Return_Tracking_Information_StoreExpt_SKU = rs.getString("item_id");
				System.out.println("Return Tracking Information Store Exception SKU: "+lineitem_Return_Tracking_Information_StoreExpt_SKU);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}

	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}
	
	public void contact_Customer_LineItem_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.LineItemRulesSheetName);
		String lineItem_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ContactCustomer_Item, Constant.Col_ItemEligibleStatus).replaceAll("\"", "'");
		System.out.println(lineItem_eligible_status);
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_contact_Customer_LineItem_Eligible = "select o.site_gen_ord_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and oi.order_item_sts_cd in "
				+ "("+lineItem_eligible_status+") and o.site_gen_ord_id like '9%' and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
				+ "and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' Limit 1";
				
		try {
			
			st = conn.prepareStatement(Sql_contact_Customer_LineItem_Eligible);
			Reporter.log("SQL Query: "+Sql_contact_Customer_LineItem_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Contact_Customer_Eligible_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Contact Customer Eligible OrderID: "+lineitem_Contact_Customer_Eligible_orderID);
				lineitem_Contact_Customer_Eligible_SKU = rs.getString("item_id");
				System.out.println("Contact Customer Eligible SKU: "+lineitem_Contact_Customer_Eligible_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}
	
	public void Line_Item_Cancellation_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.LineItemRulesSheetName);
		String lineItem_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_CancellationLineItem, Constant.Col_ItemEligibleStatus).replaceAll("\"", "'");
		System.out.println(lineItem_eligible_status);
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_LineItem_Cancellation_Eligible = "SELECT o.SITE_GEN_ORD_ID, oi.item_id FROM ORD O,ORD_ITEM OI,SALES_CHECK SC,FFM_METHOD FFM, payment p,payment_instruction pi,pmt_method pm "
				+ "WHERE O.ORDER_SERVICE_ORGINATING_SERVER in ('qa.ecom.sears.com') AND O.ORDER_ID=OI.ORDER_ID  AND SC.ORDER_ID=O.ORDER_ID "
				+ "AND SC.SALES_CHECK_ID=OI.SALES_CHECK_ID and FFM.FFM_METHOD_id=OI.FFM_METHOD_id and pi.PAYMENT_METHOD_ID=pm.payment_method_id "
				+ "and p.PAY_INSTRUCTION_ID=pi.PAY_INSTRUCTION_ID and sc.sales_check_id=p.sales_check_id and o.ORDER_ID=pi.order_ID "
				+ "and FFM.FFM_CLASS_ID in('VD') AND OI.ORDER_ITEM_STS_CD IN ('TRN') and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') "
				+ "and o.last_updated_ts < '2015-06-15 01:01:01' limit 1";
		
		Sql_LineItem_Cancellation_Status_Expt = "SELECT o.SITE_GEN_ORD_ID,oi.item_id FROM ORD O,ORD_ITEM OI,SALES_CHECK SC,FFM_METHOD FFM, payment p,"
				+ "payment_instruction pi,pmt_method pm WHERE O.ORDER_SERVICE_ORGINATING_SERVER in ('qa.ecom.sears.com') AND O.ORDER_ID=OI.ORDER_ID "
				+ "AND SC.ORDER_ID=O.ORDER_ID  AND SC.SALES_CHECK_ID=OI.SALES_CHECK_ID and FFM.FFM_METHOD_id=OI.FFM_METHOD_id and "
				+ "pi.PAYMENT_METHOD_ID=pm.payment_method_id and p.PAY_INSTRUCTION_ID=pi.PAY_INSTRUCTION_ID and sc.sales_check_id=p.sales_check_id "
				+ "and o.ORDER_ID=pi.order_ID and FFM.FFM_CLASS_ID in('VD') AND OI.ORDER_ITEM_STS_CD  Not IN ("+lineItem_eligible_status+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and "
				+ "oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%'  and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
				+ "and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-06-15 01:01:01' limit 1";
				
		try {
			
			st = conn.prepareStatement(Sql_LineItem_Cancellation_Eligible);
			Reporter.log("SQL Query: "+Sql_LineItem_Cancellation_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Cancellation_Eligible_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Line Item Cancellation Eligible Order ID: "+lineitem_Cancellation_Eligible_orderID);
				lineitem_Cancellation_Eligible_SKU = rs.getString("item_id");
				System.out.println("Line Item Cancellation Eligible SKU: "+lineitem_Cancellation_Eligible_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		try {
			
			st = conn.prepareStatement(Sql_LineItem_Cancellation_Status_Expt);
			Reporter.log("SQL Query: "+Sql_LineItem_Cancellation_Status_Expt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Cancellation_StatusExpt_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Line Item Cancellation Status Exception Order ID: "+lineitem_Cancellation_StatusExpt_orderID);
				lineitem_Cancellation_StatusExpt_SKU = rs.getString("item_id");
				System.out.println("Line Item Cancellation Status Exception SKU: "+lineitem_Cancellation_StatusExpt_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		
	}
		
	public void reschedule_Delivery_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.LineItemRulesSheetName);
		String lineItem_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_RescheduleDelivery, Constant.Col_ItemEligibleStatus).replaceAll("\"", "'");
		String lineItem_store_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_RescheduleDelivery, Constant.Col_Storeexception_Item).replaceAll("\"", "'");
		String lineItem_FFMin_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_RescheduleDelivery, Constant.Col_Fulfillinexception).replaceAll("\"", "'");
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_reschedule_Delivery_Eligible = "select distinct o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and o.site_id not in ("+lineItem_store_Exception+") "
				+ "and fm.ffm_class_id in ("+lineItem_FFMin_Exception+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') " +
					"and oi.order_item_sts_cd NOT in ('PCON','TEST','') "
				+ " and o.last_updated_ts > '2016-02-16 01:01:01' and o.last_updated_ts < '2016-05-15 01:01:01' Limit 1";
		System.out.println("reschedule delivery query "+Sql_reschedule_Delivery_Eligible);
		Sql_reschedule_Delivery_StoreExpt = "select distinct o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and o.site_id in ("+lineItem_store_Exception+") "
				+ "and fm.ffm_class_id in ("+lineItem_FFMin_Exception+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') " +
						"and oi.order_item_sts_cd NOT in ('PCON','TEST','')"
				+ " and o.last_updated_ts > '2016-02-16 01:01:01' and o.last_updated_ts < '2016-05-15 01:01:01' Limit 1";
		Sql_reschedule_Delivery_FFMinExpt = "select distinct o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and o.site_id not in ("+lineItem_store_Exception+") "
				+ "and fm.ffm_class_id NOT in ("+lineItem_FFMin_Exception+") and " +
						"o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') " 
				+ "and o.last_updated_ts > '2016-02-16 01:01:01' and o.last_updated_ts < '2016-05-15 01:01:01' Limit 1";

		try {
			
			st = conn.prepareStatement(Sql_reschedule_Delivery_Eligible);
			Reporter.log("SQL Query: "+Sql_reschedule_Delivery_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_reschedule_Delivery_Eligible_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Reschedule Delivery Eligible OrderID: "+lineitem_reschedule_Delivery_Eligible_orderID);
				lineitem_reschedule_Delivery_Eligible_SKU = rs.getString("item_id");
				System.out.println("Reschedule Delivery Eligible SKU: "+lineitem_reschedule_Delivery_Eligible_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

	
	try {
		
			st = conn.prepareStatement(Sql_reschedule_Delivery_StoreExpt);
			Reporter.log("SQL Query: "+Sql_reschedule_Delivery_StoreExpt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_reschedule_Delivery_StoreExpt_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Reschedule Delivery Store Exception OrderID: "+lineitem_reschedule_Delivery_StoreExpt_orderID);
				lineitem_reschedule_Delivery_StoreExpt_SKU = rs.getString("item_id");
				System.out.println("Reschedule Delivery Store Exception SKU: "+lineitem_reschedule_Delivery_StoreExpt_SKU);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	
	try {
		
			st = conn.prepareStatement(Sql_reschedule_Delivery_FFMinExpt);
			Reporter.log("SQL Query: "+Sql_reschedule_Delivery_FFMinExpt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_reschedule_Delivery_FFMinExpt_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Reschedule Delivery Fulfil in Exception OrderID: "+lineitem_reschedule_Delivery_FFMinExpt_orderID);
				lineitem_reschedule_Delivery_FFMinExpt_SKU = rs.getString("item_id");
				System.out.println("Reschedule Delivery Fulfil in Exception SKU: "+lineitem_reschedule_Delivery_FFMinExpt_SKU);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}
	
	public void schedule_Return_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.LineItemRulesSheetName);
		String lineItem_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ScheduleReturn, Constant.Col_ItemEligibleStatus).replaceAll("\"", "'");
		String lineItem_store_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ScheduleReturn, Constant.Col_Storeexception_Item).replaceAll("\"", "'");
		String lineItem_FFMin_Exception = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ScheduleReturn, Constant.Col_Fulfillinexception).replaceAll("\"", "'");
		System.out.println("lineItem_eligible_status: "+lineItem_eligible_status);
		System.out.println("lineItem_store_Exception: "+lineItem_store_Exception);
		System.out.println("lineItem_FFMin_Exception: "+lineItem_FFMin_Exception);
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_schedule_Return_Eligible = "select distinct o.site_gen_ord_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and o.site_id not in ("+lineItem_store_Exception+") "
				+ "and fm.ffm_class_id in ("+lineItem_FFMin_Exception+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') " +
						"and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
				+ "limit 1";
		Sql_schedule_Return_StoreExpt = "select distinct o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and o.site_id in ("+lineItem_store_Exception+") "
				+ "and fm.ffm_class_id in ("+lineItem_FFMin_Exception+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and " +
						"oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
				+ "limit 1";
		Sql_schedule_Return_FFMinExpt = "select distinct o.site_gen_ord_id, o.order_id,oi.item_id from ord o, ord_item oi, sales_check sc, ffm_method fm "
				+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.order_item_sts_cd in ("+lineItem_eligible_status+") and o.site_id not in ("+lineItem_store_Exception+") "
				+ "and fm.ffm_class_id NOT in ("+lineItem_FFMin_Exception+") and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') " +
						"and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' limit 1";

		try {
			
			st = conn.prepareStatement(Sql_schedule_Return_Eligible);
			System.out.println("Schedule Return SQL Query: "+Sql_schedule_Return_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_schedule_Return_Eligible_orderID = rs.getString("o.site_gen_ord_id");
				System.out.println("Reschedule Return Eligible OrderID: "+lineitem_schedule_Return_Eligible_orderID);
				lineitem_schedule_Return_Eligible_SKU = rs.getString("item_id");
				System.out.println("Reschedule Return Eligible SKU: "+lineitem_schedule_Return_Eligible_SKU);
				}
			} catch (SQLException e) {
				System.out.println("Query is "+Sql_schedule_Return_Eligible);
				e.printStackTrace();
			}

	
	    try {
		
			st = conn.prepareStatement(Sql_schedule_Return_StoreExpt);
			Reporter.log("SQL Query: "+Sql_schedule_Return_StoreExpt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_schedule_Return_StoreExpt_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Reschedule Return Store Exception OrderID: "+lineitem_schedule_Return_StoreExpt_orderID);
				lineitem_schedule_Return_StoreExpt_SKU = rs.getString("item_id");
				System.out.println("Reschedule Return Store Exception SKU: "+lineitem_schedule_Return_StoreExpt_SKU);
			}
			} catch (SQLException e) {
				System.out.println("Query is "+Sql_schedule_Return_StoreExpt);
				e.printStackTrace();
			}
	
	    try {
		
			st = conn.prepareStatement(Sql_schedule_Return_FFMinExpt);
			Reporter.log("SQL Query: "+Sql_schedule_Return_FFMinExpt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_schedule_Return_FFMinExpt_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Reschedule Return Fulfil in Exception OrderID: "+lineitem_schedule_Return_FFMinExpt_orderID);
				lineitem_schedule_Return_FFMinExpt_SKU = rs.getString("item_id");
				System.out.println("Reschedule Return Fulfil in Exception SKU: "+lineitem_schedule_Return_FFMinExpt_SKU);
			}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Query is "+Sql_schedule_Return_FFMinExpt);
			}
	    try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		}
	
	public void Line_Item_Return_Item_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.LineItemRulesSheetName);
		String lineItem_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReturnItem, Constant.Col_ItemEligibleStatus).replaceAll("\"", "'");
		String lineItem_ffmExpt = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReturnItem, Constant.Col_Fulfilbyexception).replaceAll("\"", "'").replaceAll(",", "','");
		String lineItem_storeExpt = ExcelUtil.getExcelUtil().getCellData(Constant.Row_ReturnItem, Constant.Col_Storeexception_Item).replaceAll("\"", "'");
		System.out.println(lineItem_eligible_status);
		System.out.println(lineItem_ffmExpt);
		System.out.println(lineItem_storeExpt);
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_LineItem_Return_Item_Eligible = "SELECT o.SITE_GEN_ORD_ID,oi.item_id FROM ORD O,ORD_ITEM OI,SALES_CHECK SC,FFM_METHOD FFM, payment p,payment_instruction pi,pmt_method pm "
				+ "WHERE O.ORDER_ID=OI.ORDER_ID  AND SC.ORDER_ID=O.ORDER_ID "
				+ "AND SC.SALES_CHECK_ID=OI.SALES_CHECK_ID and FFM.FFM_METHOD_id=OI.FFM_METHOD_id and pi.PAYMENT_METHOD_ID=pm.payment_method_id "
				+ "and p.PAY_INSTRUCTION_ID=pi.PAY_INSTRUCTION_ID and sc.sales_check_id=p.sales_check_id and o.ORDER_ID=pi.order_ID "
				+ "AND OI.ORDER_ITEM_STS_CD IN ('SHP') and pm.payment_method_ds not in ('GiftCard') and FFM.FFM_CLASS_ID in('VD','TW','SFS','HFM') AND o.SITE_ID not in ("+lineItem_storeExpt+") "
				+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','')and so_line_number=1 and o.site_gen_ord_id like '8%'  and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
				+ "and o.last_updated_ts > DATE_SUB(CURDATE(),INTERVAL 180 DAY) limit 1";
		
		Sql_LineItem_Return_Item_Status_Expt = "SELECT o.SITE_GEN_ORD_ID,oi.item_id FROM ORD O,ORD_ITEM OI,SALES_CHECK SC,FFM_METHOD FFM, payment p,payment_instruction pi,pmt_method pm "
				+ "WHERE O.ORDER_SERVICE_ORGINATING_SERVER in ('qa.ecom.sears.com') AND O.ORDER_ID=OI.ORDER_ID  AND SC.ORDER_ID=O.ORDER_ID "
				+ "AND SC.SALES_CHECK_ID=OI.SALES_CHECK_ID and FFM.FFM_METHOD_id=OI.FFM_METHOD_id and pi.PAYMENT_METHOD_ID=pm.payment_method_id "
				+ "and p.PAY_INSTRUCTION_ID=pi.PAY_INSTRUCTION_ID and sc.sales_check_id=p.sales_check_id and o.ORDER_ID=pi.order_ID "
				+ "AND OI.ORDER_ITEM_STS_CD NOT IN ("+lineItem_eligible_status+") and FFM.FFM_CLASS_ID NOT in("+lineItem_ffmExpt+") AND o.SITE_ID not in ("+lineItem_storeExpt+") "
				+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%'  and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
				+ "and o.last_updated_ts > DATE_SUB(CURDATE(),INTERVAL 180 DAY) limit 1";
				
		try {
			
			st = conn.prepareStatement(Sql_LineItem_Return_Item_Eligible);
			Reporter.log("SQL Query: "+Sql_LineItem_Return_Item_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Return_Item_Eligible_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Line Item Return Item Eligible Order ID: "+lineitem_Return_Item_Eligible_orderID);
				lineitem_Return_Item_Eligible_SKU = rs.getString("item_id");
				System.out.println("Line Item Return Item Eligible SKU: "+lineitem_Return_Item_Eligible_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		try {
			
			st = conn.prepareStatement(Sql_LineItem_Return_Item_Status_Expt);
			Reporter.log("SQL Query: "+Sql_LineItem_Return_Item_Status_Expt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Return_Item_StatusExpt_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Line Item Return Item Status Exception Order ID: "+lineitem_Return_Item_StatusExpt_orderID);
				lineitem_Return_Item_StatusExpt_SKU = rs.getString("item_id");
				System.out.println("Line Item Return Item Status Exception SKU: "+lineitem_Return_Item_StatusExpt_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		}
	
	public void Line_Item_Start_Automated_Return_Data() throws Exception{
		ExcelUtil.getExcelUtil().setupExcelFile(Constant.Path_Rules + Constant.File_Name,Constant.LineItemRulesSheetName);
		String lineItem_eligible_status = ExcelUtil.getExcelUtil().getCellData(Constant.Row_RefundWithoutReturn, Constant.Col_ItemEligibleStatus).replaceAll("\"", "'");
		String lineItem_ffmExpt = ExcelUtil.getExcelUtil().getCellData(Constant.Row_RefundWithoutReturn, Constant.Col_Fulfilbyexception).replaceAll("\"", "'").replaceAll(",", "','");
		String lineItem_storeExpt = ExcelUtil.getExcelUtil().getCellData(Constant.Row_RefundWithoutReturn, Constant.Col_Storeexception_Item).replaceAll("\"", "'");
		System.out.println(lineItem_eligible_status);
		System.out.println(lineItem_ffmExpt);
		System.out.println(lineItem_storeExpt);
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		/*Sql_LineItem_Start_Automated_Return_Eligible="select o.site_gen_ord_id, oi.order_item_id, oi.ITEM_ID, oi.ITEM_NM, sc.sales_check_number, oi.REQUESTED_SHIP_DATE, oi.PROMISED_AVAILABLE_TIME, "
				+ "sc.sales_check_number, cci.phone_1, o.order_id, oi.QUANTITY, fm.ffm_class_id, o.order_sts_cd, oi.order_item_sts_cd, sc.SALES_CHECK_STS_CD,  o.site_id, "
				+ "o.PLACEMENT_TS from ord o, ord_item oi, sales_check sc, ffm_method fm, customer_contact_info cci "
				+ "where o.order_id = oi.order_id and o.billing_address_id = cci.address_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.SALES_CHECK_ID = sc.SALES_CHECK_ID and oi.order_item_sts_cd in ('SHP') and fm.ffm_class_id in ('TW','VD','HFM','SFS') and o.placement_ts > DATE_SUB(CURDATE(),INTERVAL 15 DAY) "
				+ "and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and oi.ITEM_ID REGEXP '^-?[0-9]+W[0-9]+$' and o.SITE_GEN_ORD_ID in (select o.site_gen_ord_id "
				+ "from ord o, ord_item oi, sales_check sc, ffm_method fm, customer_contact_info cci where o.order_id = oi.order_id and o.billing_address_id = cci.address_id "
				+ "and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and oi.SALES_CHECK_ID = sc.SALES_CHECK_ID and oi.order_item_sts_cd in ('SHP') "
				+ "and fm.ffm_class_id in ('TW','VD','HFM','SFS') and o.placement_ts > DATE_SUB(CURDATE(),INTERVAL 15 DAY) "
				+ "and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and oi.ITEM_NM REGEXP '.*WARRANTY.*') order by o.placement_ts asc limit 1"; */
		
		Sql_LineItem_Start_Automated_Return_Eligible=	"select o.site_gen_ord_id, oi.order_item_id, oi.ITEM_ID, oi.ITEM_NM, sc.sales_check_number, oi.REQUESTED_SHIP_DATE, oi.PROMISED_AVAILABLE_TIME, "
						+ "sc.sales_check_number, cci.phone_1, o.order_id, oi.QUANTITY, fm.ffm_class_id, o.order_sts_cd, oi.order_item_sts_cd, sc.SALES_CHECK_STS_CD,  o.site_id, o.PLACEMENT_TS  "
						+ "from ord o, ord_item oi, sales_check sc, ffm_method fm, customer_contact_info cci  "
						+ "where o.order_id = oi.order_id and o.billing_address_id = cci.address_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id "
						+ "and oi.SALES_CHECK_ID = sc.SALES_CHECK_ID   and oi.order_item_sts_cd in ('SHP') "
						+ "and fm.ffm_class_id in ('TW') and o.placement_ts > DATE_SUB(CURDATE(),INTERVAL 15 DAY)   "
						+ "and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and oi.ITEM_ID REGEXP '^-?[0-9]+W[0-9]+$'  order by o.placement_ts asc limit 1;";
		
		Sql_LineItem_Start_Automated_Return_Status_Expt = "select o.site_gen_ord_id, oi.order_item_id, oi.ITEM_ID, oi.ITEM_NM, sc.sales_check_number, oi.REQUESTED_SHIP_DATE, oi.PROMISED_AVAILABLE_TIME, "
				+ "sc.sales_check_number, cci.phone_1, o.order_id, oi.QUANTITY, fm.ffm_class_id, o.order_sts_cd, oi.order_item_sts_cd, sc.SALES_CHECK_STS_CD,  o.site_id, "
				+ "o.PLACEMENT_TS from ord o, ord_item oi, sales_check sc, ffm_method fm, customer_contact_info cci "
				+ "where o.order_id = oi.order_id and o.billing_address_id = cci.address_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and "
				+ "oi.SALES_CHECK_ID = sc.SALES_CHECK_ID and oi.order_item_sts_cd in ('SHP') and fm.ffm_class_id in ('TW','VD','HFM','SFS') and o.placement_ts > DATE_SUB(CURDATE(),INTERVAL 180 DAY) "
				+ "and o.site_gen_ord_id REGEXP '^-?[0-9]+$' order by o.placement_ts asc limit 1"; ;
				
		try {
			
			st = conn.prepareStatement(Sql_LineItem_Start_Automated_Return_Eligible);
			Reporter.log("SQL Query: "+Sql_LineItem_Start_Automated_Return_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Start_Automated_Return_Eligible_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Line Item Start Automated Return Eligible Order ID: "+lineitem_Start_Automated_Return_Eligible_orderID);
				lineitem_Start_Automated_Return_Eligible_SKU = rs.getString("item_id");
				System.out.println("Line Item Start Automated Return Eligible SKU: "+lineitem_Start_Automated_Return_Eligible_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		try {
			
			st = conn.prepareStatement(Sql_LineItem_Start_Automated_Return_Status_Expt);
			Reporter.log("SQL Query: "+Sql_LineItem_Start_Automated_Return_Status_Expt);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				lineitem_Start_Automated_Return_StatusExpt_orderID = rs.getString("site_gen_ord_id");
				System.out.println("Line Item Start Automated Return Status Exception Order ID: "+lineitem_Start_Automated_Return_StatusExpt_orderID);
				lineitem_Start_Automated_Return_StatusExpt_SKU = rs.getString("item_id");
				System.out.println("Line Item Start Automated Return Status Exception SKU: "+lineitem_Start_Automated_Return_StatusExpt_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		}
	
	
	
	static String Sql_Discount_Eligible = null;
	static String Sql_Adjustment_Eligible = null;
	static String Sql_store_POS_to_Web_Eligible = null;
	static String Sql_Retrun_Gift_Card_Eligible = null;
	static String Sql_Delivery_Details_Eligible = null;
	static String Sql_Tracking_Details_Eligible = null;
	static String Sql_Return_Info_Eligible = null;
	static String Sql_Return_Tracking_Info_Eligible = null;
	static String Sql_Installation_Info_Eligible = null;
	static String Sql_Gift_Card_Info_Eligible = null;
	static String Sql_General_Order = null;
	static String Sql_Commercail_Order = null;
	public static String layaway_contract_details_fetch= null;
	public static String Vendor_details_fetch= null;
	public static String hasDiscount_OrderID = null;
	public static String hasDiscount_SKU = null;
	public static String hasDiscount_SCNO = null;
	public static String hasAdjustment_OrderID = null;
	public static String hasstore_POS_to_Web_OrderID = null;
	public static String hasstore_POS_to_Web_SCNO = null;
	public static String hasReturnGiftCardInfo_OrderID = null;
	public static String hasReturnGiftCardInfo_SCNO = null;
	public static String hasDelivery_Details_OrderID = null;
	public static String hasDelivery_Details_SCNO = null;
	public static String hasAdjustment_SKU = null;
	public static String hasTracking_Detail_OrderID = null;
	public static String hasTracking_Detail_SKU = null;
	public static String hasReturnInfo_OrderID = null;
	public static String hasReturnInfo_SKU = null;
	public static String hasReturnTrackingInfo_OrderID = null;
	public static String hasReturnTrackingInfo_SKU = null;
	public static String hasInstallationInfo_OrderID = null;
	public static String hasInstallationInfo_SKU = null;
	public static String hasGiftCardInfo_OrderID = null;
	public static String hasGiftCardInfo_SKU = null;
	public static boolean Discount_OrderID=false;
	public static boolean Adjustment_OrderID=false;
	private static HashMap<String, Boolean> orders;
	
	public static void Discount_OrderID() throws Exception{
		if(Discount_OrderID){
			return;
		}
		else{
			Discount_OrderID=true;
		}
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_Discount_Eligible = "select o.site_gen_ord_id, oi.item_id, sc.sales_check_number from ord o, ord_item oi, ord_item_discount oid, sales_check sc "
				+ "where o.order_id = oi.order_id and oi.order_item_id = oid.order_item_id and oi.SALES_CHECK_ID = sc.SALES_CHECK_ID "
				+ "and oid.amount < 0 and oi.order_item_sts_cd NOT in ('PCON','TEST','') "
				+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
				+ "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and '2015-06-15 01:01:01' > o.last_updated_ts "
				+ "and o.last_updated_ts > '2015-01-01 01:01:01' limit 1";

		try {
			
			st = conn.prepareStatement(Sql_Discount_Eligible);
			Reporter.log("SQL Query: "+Sql_Discount_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				hasDiscount_OrderID = rs.getString("site_gen_ord_id");
				hasDiscount_SKU = rs.getString("item_id");
				hasDiscount_SCNO = rs.getString("sales_check_number");
				System.out.println("OrderID has Discount: "+hasDiscount_OrderID);
				System.out.println("SKU has Discount: "+hasDiscount_SKU);
				System.out.println("Sales Check Number has Discount: "+hasDiscount_SCNO);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		}
	
public static void Adjustment_OrderID() throws Exception{
		if(Adjustment_OrderID){
			return;
		}
		else{
			Adjustment_OrderID=true;
		}
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_Adjustment_Eligible = "select o.site_gen_ord_id, oi.item_id from ord o, ord_item oi, rma r, rma_item ri where oi.order_id = o.order_id "
				+ "and oi.order_item_id =ri.order_item_id and r.rma_id = ri.rma_id and r.RMA_TYPE in('TADJ','SLADJ','SHADJ') "
				+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd "
				+ "NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%' and site_gen_ord_id REGEXP '^-?[0-9]+$' and '2015-06-15 01:01:01' > o.last_updated_ts "
				+ "and o.last_updated_ts > '2015-01-01 01:01:01' order by r.RECORD_INSERT_TS desc limit 1 ";

		try {
			
			st = conn.prepareStatement(Sql_Adjustment_Eligible);
			Reporter.log("SQL Query: "+Sql_Adjustment_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				hasAdjustment_OrderID = rs.getString("site_gen_ord_id");
				hasAdjustment_SKU = rs.getString("item_id");
				System.out.println("OrderID has Adjustment: "+hasAdjustment_OrderID);
				System.out.println("SKU: "+hasAdjustment_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		}

public static void store_POS_to_Web_OrderID() throws Exception{
	
	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
	Sql_store_POS_to_Web_Eligible = "select o.site_gen_ord_id, sc.sales_check_number from ord o, ord_item oi,sales_check sc,payment_sales_check psc "
			+ "where o.order_id= oi.order_id and oi.order_id =sc.order_id and sc.order_id = psc.order_id and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
			+ "and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
			+ "and o.last_updated_ts > '2015-02-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1";

	try {
		
		st = conn.prepareStatement(Sql_store_POS_to_Web_Eligible);
		Reporter.log("SQL Query: "+Sql_store_POS_to_Web_Eligible);
		st.execute();
		rs = st.getResultSet();
		while(rs.next()){
			hasstore_POS_to_Web_OrderID = rs.getString("site_gen_ord_id");
			System.out.println("OrderID has Store POS to Web: "+hasstore_POS_to_Web_OrderID);
			hasstore_POS_to_Web_SCNO = rs.getString("sales_check_number");
			System.out.println("Sales Check Number has Store POS to Web: "+hasstore_POS_to_Web_SCNO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}


public static void returnGC_OrderID() throws Exception{
	
	Calendar now = Calendar.getInstance();
	String currentDate=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DATE);
	now.add(Calendar.MONTH, -1);
	String lastMonthDate=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DATE);
	
	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
	Sql_Retrun_Gift_Card_Eligible = "select o.site_gen_ord_id,sc.sales_check_number from ord o, ord_item oi, giftcard_order_item goi, sales_check sc "
			+ "where o.order_id = oi.order_id and oi.order_item_id = goi.order_item_id and oi.item_nm like '%Gift Card%' and oi.order_id = sc.order_id "
			+ "and oi.order_item_sts_cd in ('RET') "
			+ "and o.site_gen_ord_id REGEXP '^-?[0-9]+$' order by o.last_updated_ts desc limit 1;";

	try {
		
		st = conn.prepareStatement(Sql_Retrun_Gift_Card_Eligible);
		Reporter.log("SQL Query: "+Sql_Retrun_Gift_Card_Eligible);
		st.execute();
		rs = st.getResultSet();
		while(rs.next()){
			hasReturnGiftCardInfo_OrderID = rs.getString("site_gen_ord_id");
			System.out.println("OrderID has Gift Card Information: "+hasReturnGiftCardInfo_OrderID);
			hasReturnGiftCardInfo_SCNO = rs.getString("sales_check_number");
			System.out.println("Sales Check Number has Gift Card Information: "+hasReturnGiftCardInfo_SCNO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}

public static void delivery_Details_OrderID() throws Exception{
	Calendar now = Calendar.getInstance();
	String currentDate=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DATE);
	now.add(Calendar.MONTH, -1);
	String lastMonthDate=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DATE);
	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
	
	/*Sql_Delivery_Details_Eligible = "select o.site_gen_ord_id, sc.sales_check_number from ord o, ord_item oi, sales_check sc, ffm_method fm "
			+ "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and fm.ffm_class_id = 'DDC' "
			+ "and o.site_gen_ord_id like '9%' and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
			+ "and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '"+lastMonthDate+" 01:01:01' "
			+ "and o.last_updated_ts < '"+currentDate+" 01:01:01' order by o.last_updated_ts desc limit 1;";*/
	
	Sql_Delivery_Details_Eligible = "select o.site_gen_ord_id, sc.sales_check_number"
			+" from ord o, ord_item oi, sales_check sc, ffm_method fm"
			+" where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and"
			+" oi.sales_check_id = sc.sales_check_id and o.site_gen_ord_id REGEXP '^-?[0-9]+$'  and fm.ffm_class_id='DDC' and   o.site_id = 40153 and o.ORDER_SERVICE_ORGINATING_SERVER  in ('QA')"
			+" order by o.last_updated_ts desc limit 1";
	try {
		
		st = conn.prepareStatement(Sql_Delivery_Details_Eligible);
		Reporter.log("SQL Query: "+Sql_Delivery_Details_Eligible);
		st.execute();
		rs = st.getResultSet();
		while(rs.next()){
			hasDelivery_Details_OrderID = rs.getString("site_gen_ord_id");
			System.out.println("OrderID has Delivery_Details: "+hasDelivery_Details_OrderID);
			hasDelivery_Details_SCNO = rs.getString("sales_check_number");
			System.out.println("Sales Check Number has Delivery_Details: "+hasDelivery_Details_SCNO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}

public static void tracking_Details_OrderID() throws Exception{
	
	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
	Sql_Tracking_Details_Eligible = "select o.SITE_GEN_ORD_ID,oi.item_id from ord o, ord_item oi, ship_unit su,ship_invoice_item sii "
			+ "where o.order_id=oi.order_id and su.SHIP_UNIT_ID=sii.SHIP_UNIT_ID and oi.order_item_id=sii.order_item_id "
			+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
			+ "and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%' "
			+ "and o.site_gen_ord_id REGEXP '^-?[0-9]+$' limit 1; ";

	try {
		
		st = conn.prepareStatement(Sql_Tracking_Details_Eligible);
		Reporter.log("SQL Query: "+Sql_Tracking_Details_Eligible);
		st.execute();
		rs = st.getResultSet();
		while(rs.next()){
			hasTracking_Detail_OrderID = rs.getString("site_gen_ord_id");
			System.out.println("OrderID has Tracking Details: "+hasTracking_Detail_OrderID);
			hasTracking_Detail_SKU = rs.getString("item_id");
			System.out.println("SKU has Tracking Details: "+hasTracking_Detail_SKU);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}

public static void return_Information_OrderID() throws Exception{
	
	Calendar now = Calendar.getInstance();
	String currentDate=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DATE);
	now.add(Calendar.MONTH, -1);
	String lastMonthDate=now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DATE);
	
	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
	Sql_Return_Info_Eligible = "select o.SITE_GEN_ORD_ID,oi.item_id from ord o, ord_item oi where o.order_id = oi.order_id and oi.ORDER_ITEM_STS_CD = 'RET' "
			+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST','') "
			+ "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' limit 1;";

	try {
		
		st = conn.prepareStatement(Sql_Return_Info_Eligible);
		Reporter.log("SQL Query: "+Sql_Return_Info_Eligible);
		st.execute();
		rs = st.getResultSet();
		while(rs.next()){
			hasReturnInfo_OrderID = rs.getString("site_gen_ord_id");
			System.out.println("OrderID has Return Information: "+hasReturnInfo_OrderID);
			hasReturnInfo_SKU = rs.getString("item_id");
			System.out.println("SKU has Return Information: "+hasReturnInfo_SKU);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}

public static void return_Tracking_Information_OrderID() throws Exception{
	
	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
	Sql_Return_Tracking_Info_Eligible = "select o.SITE_GEN_ORD_ID, oi.item_id from return_tracking rt,ord_item oi,ord o where o.order_id = oi.order_id "
			+ "and oi.ORDER_ITEM_ID=rt.ORDER_ITEM_ID and o.ORDER_ID=oi.ORDER_ID and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
			+ "and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' limit 1;";

	try {
		
		st = conn.prepareStatement(Sql_Return_Tracking_Info_Eligible);
		Reporter.log("SQL Query: "+Sql_Return_Tracking_Info_Eligible);
		st.execute();
		rs = st.getResultSet();
		while(rs.next()){
			hasReturnTrackingInfo_OrderID = rs.getString("site_gen_ord_id");
			System.out.println("OrderID has Return Tracking Information: "+hasReturnTrackingInfo_OrderID);
			hasReturnTrackingInfo_SKU = rs.getString("item_id");
			System.out.println("SKU has Return Tracking Information: "+hasReturnTrackingInfo_SKU);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	}



public static void giftCard_Information_OrderID() throws Exception{
	
	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
	Sql_Gift_Card_Info_Eligible = "select o.site_gen_ord_id,oi.item_id from ord o, ord_item oi, giftcard_order_item goi "
			+ "where o.order_id = oi.order_id and oi.order_item_id = goi.order_item_id and oi.item_nm like '%Gift Card%' "
			+ "and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
			+ "and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
			+ "and o.last_updated_ts > DATE_SUB(CURDATE(),INTERVAL 30 DAY) order by o.last_updated_ts desc limit 1;";
	
	System.out.println();System.out.println("Query for giftCard_Information_OrderID "+Sql_Gift_Card_Info_Eligible);System.out.println();
	try {
		
		st = conn.prepareStatement(Sql_Gift_Card_Info_Eligible);
		Reporter.log("SQL Query: "+Sql_Gift_Card_Info_Eligible);
		st.execute();
		rs = st.getResultSet();
		while(rs.next()){
			hasGiftCardInfo_OrderID = rs.getString("site_gen_ord_id");
			System.out.println("OrderID has Gift Card Information: "+hasGiftCardInfo_OrderID);
			hasGiftCardInfo_SKU = rs.getString("item_id");
			System.out.println("SKU has Gift Card Information: "+hasGiftCardInfo_SKU);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
}
public static void layaway_contract_details_fetch() throws Exception{
	
	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
	layaway_contract_details_fetch = "select distinct CONTRACT_ID from layaway_contract_detail where CONTRACT_ID is not null and CONTRACT_ID <>0 and END_DATE > DATE_SUB(CURDATE(),INTERVAL 60 DAY) ORDER BY RAND() LIMIT 10;";

	try {
		
		st = conn.prepareStatement(layaway_contract_details_fetch);
		Reporter.log("SQL Query: "+layaway_contract_details_fetch);
		st.execute();
		rs = st.getResultSet();
		while(rs.next()){
			layaway_contract_details_fetch = rs.getString("CONTRACT_ID");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
}
public static void vendor_details_fetch() throws Exception{
	
	Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
	Vendor_details_fetch = "select distinct Vendor_id from ffm_vendor where EDI_ROUTE_CODE IS NOT Null  AND  VENDOR_ID NOT LIKE '%0' LIMIT 10;";

	try {
		
		st = conn.prepareStatement(Vendor_details_fetch);
		Reporter.log("SQL Query: "+Vendor_details_fetch);
		st.execute();
		rs = st.getResultSet();
		while(rs.next()){
			Vendor_details_fetch = rs.getString("VENDOR_ID");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
	
}

	public static synchronized String getOrder() {
		if(orders==null){
			loadOrders();
		}
		for (String order : orders.keySet()) {
			if (!orders.get(order)) {
				orders.put(order, true);
				return order;
			}
		}
		for(String order:orders.keySet()){
			orders.put(order, false);
		}
		return getOrder();
	}

	public static void loadOrders() {
		Sql_General_Order = "select distinct(o.SITE_GEN_ORD_ID) from ord o, ord_item oi " + "where o.order_id = oi.order_id and oi.ITEM_ID not like '%SPM%' and o.ORDER_STS_CD not in ('TEST') " + 
							"and o.ORDER_ID = o.SITE_GEN_ORD_ID and o.last_updated_ts > DATE_SUB(CURDATE(),INTERVAL 85 DAY) order by o.last_updated_ts asc limit 100;";
			orders = new HashMap<String, Boolean>();
			try {

				Connection conn = null;PreparedStatement st = null;ResultSet rs = null;conn = MysqlDBConnection.getmysqlConnection();
				st = conn.prepareStatement(Sql_General_Order);
				Reporter.log("SQL Query: " + Sql_General_Order);
				System.out.println("SQL Query: " + Sql_General_Order);
				st.execute();
				rs = st.getResultSet();
				while (rs.next()) {
					orders.put(rs.getString("SITE_GEN_ORD_ID"), false);
				}
				try {conn.close();st.close();} catch (Exception e) {e.printStackTrace();}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	public static void installation_Information_OrderID() throws Exception{
		
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_Installation_Info_Eligible = "select o.SITE_GEN_ORD_ID, oi.item_id from ord o, ord_item oi where o.order_id = oi.order_id "
				+ "and oi.ITEM_NM like '%installation%' and oi.LIST_PRICE > 0 and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') "
				+ "and oi.order_item_sts_cd NOT in ('PCON','TEST','') and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' "
				+ "and o.last_updated_ts > '2015-04-15 01:01:01' and o.last_updated_ts < '2015-05-15 01:01:01' limit 1;";

		try {
			
			st = conn.prepareStatement(Sql_Installation_Info_Eligible);
			Reporter.log("SQL Query: "+Sql_Installation_Info_Eligible);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				hasInstallationInfo_OrderID = rs.getString("site_gen_ord_id");
				System.out.println("OrderID has Installation Information: "+hasInstallationInfo_OrderID);
				hasInstallationInfo_SKU = rs.getString("item_id");
				System.out.println("OrderID has Installation Information: "+hasInstallationInfo_SKU);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		}
	
	public void contactCustomer_commercialOrder() throws Exception{
		
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_Commercail_Order = "select ORDER_ID from ord o where  o.site_id = 10198  and o.ORDER_STS_CD='PRO' order by last_updated_ts desc limit 1";

		try {
			
			st = conn.prepareStatement(Sql_Commercail_Order);
			Reporter.log("SQL Query: "+Sql_Commercail_Order);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				contactcustomer_eligible_commercial_orderID = rs.getString("ORDER_ID").toString();
				System.out.println("OrderID has Installation Information: "+contactcustomer_eligible_commercial_orderID);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		}

	public void searchOrder_By_SubOrder() throws Exception{
		
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_Commercail_Order = "select suborder_id from kmart_suborder where order_id IN (select o.order_id from ord o, ord_item oi, sales_check sc, ffm_method fm"
								+" where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and"
								+" oi.sales_check_id = sc.sales_check_id and o.site_gen_ord_id REGEXP '^-?[0-9]+$'  and fm.ffm_class_id='SPU' and   o.site_id = 30151 ) order by LAST_UPDATED_TS desc limit 1";

		
		System.out.println("------------------------------------+sql:"+Sql_Commercail_Order);
		try {
			
			st = conn.prepareStatement(Sql_Commercail_Order);
			Reporter.log("SQL Query: "+Sql_Commercail_Order);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				subOrderID = rs.getString("suborder_id").toString();
				System.out.println("----------------------------------------------------suborder id "+subOrderID);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		}
public void searchOrder_By_OrderID() throws Exception{
		
		Connection conn = null; PreparedStatement st = null; ResultSet rs = null; conn = MysqlDBConnection.getmysqlConnection();
		Sql_Commercail_Order = "select * from ord o,ord_item oi, sales_check sc where oi. site_id='40153' and o.order_id=oi.order_id and o.order_id = sc.order_id and oi.ORDER_ITEM_STS_CD in ('CNF') order by o. LAST_UPDATED_TS desc limit 1";

		
		System.out.println("------------------------------------+sql:"+Sql_Commercail_Order);
		try {
			
			st = conn.prepareStatement(Sql_Commercail_Order);
			Reporter.log("SQL Query: "+Sql_Commercail_Order);
			st.execute();
			rs = st.getResultSet();
			while(rs.next()){
				orderID = rs.getString("o.order_id").toString();
				salescheckID=rs.getString("sc.SALES_CHECK_NUMBER").toString();
				System.out.println("----------------------------------------------------suborder id "+orderID);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		try{conn.close();st.close();} catch(Exception e) {e.printStackTrace();}
		}

}