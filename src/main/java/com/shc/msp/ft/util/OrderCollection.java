package com.shc.msp.ft.util;


import java.io.PrintWriter;
import java.sql.*;

public class OrderCollection {
   // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://dbomsqa2.qa.ch3.s.com:3306/shc_oms";

   //  Database credentials
   static final String USER = "gpayyap";
   static final String PASS = "shc0ms##";

   public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");
	
	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	
	      //PrintWriter 
	      PrintWriter writer = new PrintWriter("C:/Temp/OrderFetchOutput.txt", "UTF-8");
	      	      
	      //Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      //String[] siteid = {"10153","30151", "40153", "10171"};
	     // String[] siteid = {"10153","10171","10191","10195","10199","20181","20195","30151","40153","40165"};
	      //String[] statuscd = {"SHP","SUB","WFP","HFM"};
	     // String[] statuscd = {"BAD","CAN","CMP","CNF","CSI","EDT","FDC","FRC","HLD","PAC","PRO","PSH","RET","SHP","SUB","TEST","TRN","WAP","WFP"};
	      //String[] ffmmethod = {"SPU","TW"};
	      String[] ffmmethod = {"AUTB2B","EXCHNG","FBHD","FBT","GC","IRES","KHD","LWSRES","MCFBS","MDDC","MFBK","MFBY","MIRES","MSPU","MSRES","NDCSLS","PD","RGC","SYWM","ISPU","B2BGC","CRES","DDC","DRES","FBM","FBS","HFM","KHD","LWY","NDC","SFS","SPU","SRES","TW","VD","VGC","VRES","XCRES","XDRES","XSRES","XVRES"};	      
	      																					

	      String sql;
	      ResultSet rs = null;

	      //Print the header for output
	      System.out.print("site_id\tstatus\tffm\tsite_gen_ord_id\torder_id\tsales_check\t       first_nm\t\tlast_nm\t\t\t        email_id\tphone_nm\tlast_updated_ts\n");
	      writer.printf("site_id\tstatus\tffm\tsite_gen_ord_id\torder_id\tsales_check\t       first_nm\t\tlast_nm\t\t\t        email_id\tphone_nm\tlast_updated_ts\r\n");
	      
		 // for (int storeIterator=0; storeIterator<siteid.length; storeIterator++)
	     // {
	    //	  for (int statusIterator=0; statusIterator<statuscd.length; statusIterator++)
	    //	  {
	    		  for(int ffmIterator=0; ffmIterator<ffmmethod.length; ffmIterator++)
	    		  {
				      sql = "select o.site_gen_ord_id, o.order_id, o.site_id, fm.ffm_class_id, oi.order_item_sts_cd, sc.SALES_CHECK_NUMBER, cci.FIRST_NM, cci.last_nm, cci.EMAIL_ADDR_1, cci.phone_1, o.last_updated_ts " + 
				    		  "from ord o, ord_item oi, ffm_method fm, sales_check sc, customer_contact_info cci " +
					            "where o.order_id = oi.order_id and o.order_id = sc.order_id and oi.ffm_method_id = fm.ffm_method_id and o.BILLING_ADDRESS_ID = cci.ADDRESS_ID and sc.sales_check_number is not null" + 
					            " and fm.ffm_class_id = '" + ffmmethod[ffmIterator] + "'" +
					            " and o.ORDER_STS_CD NOT in ('ABC','CSI','m','FRC','HLD','NCON','FDC','BAD','TEST','WFP','SHP','RET') and oi.order_item_sts_cd NOT in ('PCON','TEST') "
					            + "and o.site_gen_ord_id like '9%' and o.site_gen_ord_id REGEXP '^-?[0-9]+$' and o.last_updated_ts > '2015-06-26 01:01:01' and o.last_updated_ts < '2015-07-06 01:01:01'order by o.last_updated_ts LIMIT 1";
				      rs = stmt.executeQuery(sql);
			 
				      //Extract data from result set
				      while(rs.next()){
				         //Retrieve by column name
				    	 String site_gen_ord_id = rs.getString("SITE_GEN_ORD_ID"); 
				         int site_id = rs.getInt("SITE_ID");
				         String ffm_method = rs.getString("FFM_CLASS_ID");
				         String item_sts_cd = rs.getString("ORDER_ITEM_STS_CD");
				         String last_updated_ts = rs.getString("LAST_UPDATED_TS");
				         String order_id = rs.getString("ORDER_ID");
				         String first_nm = rs.getString("FIRST_NM");
				         String last_nm = rs.getString("LAST_NM");
				         String sales_check_number = rs.getString("SALES_CHECK_NUMBER");
				         String email_id = rs.getString("EMAIL_ADDR_1");
				         String phone_nm = rs.getString("PHONE_1");
				         
				         //Use Order_id for outlet store (site_id = 10171)
				         if (site_id == 10171||site_id == 10195) {site_gen_ord_id=order_id;}
 			             
				         System.out.format("%d\t%s\t%s\t%10s\t%s\t%s\t%15s\t%15s\t%32s\t%s\t%s\n", site_id, item_sts_cd, 
				        		 ffm_method, site_gen_ord_id, order_id, sales_check_number, first_nm, last_nm, email_id, phone_nm, last_updated_ts);
				         
				         //Ouptup to text file
				         writer.printf("%d\t%s\t%s\t%10s\t%s\t%s\t%15s\t%15s\t%32s\t%s\t%s\r\n", site_id, item_sts_cd, 
				        		 ffm_method, site_gen_ord_id, order_id, sales_check_number, first_nm, last_nm, email_id, phone_nm, last_updated_ts);
				         				      }	    			 
	    		  }	
	  //  	  }
	 //     }
	      //Clean-up environment
	      rs.close();
	      stmt.close();
	      conn.close();
	      writer.close();
	      
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("***End of report***");
	}//end main
}//end OrderCollection

