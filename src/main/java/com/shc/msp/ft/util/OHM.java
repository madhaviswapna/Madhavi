package com.shc.msp.ft.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.testng.Reporter;

public class OHM {

	public static synchronized void deactivateProduct(String keyword, String value) {
		Connection conn = null; PreparedStatement st = null; conn = MysqlDBConnection.getmysqlConnectionOHM();
		try {
			String Sql_DeleteProduct="UPDATE product p SET p.is_active='0' WHERE p.part_num='"+value+"' AND p.created_by like 'MSPDataFeed'"+
			" AND p.keyword_id=(SELECT id FROM keyword WHERE keyword_name='"+keyword+"') AND p.site_id=(87) AND p.env_id='4';";
			st = conn.prepareStatement(Sql_DeleteProduct);
			Reporter.log("SQL Query: " + Sql_DeleteProduct);
			st.execute();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
