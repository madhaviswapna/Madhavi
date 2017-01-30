package com.shc.msp.ft.util;

import java.sql.Connection;
import java.sql.DriverManager;

import org.testng.Reporter;

public class MysqlDBConnection {

	public synchronized static Connection getmysqlConnection() {

		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		// final String DB_URL =
		// "jdbc:mysql://dbomsqa2.qa.ch3.s.com:3306/shc_oms";
		final String DB_URL = "jdbc:mysql://dvomsq.qa.ch3.s.com:3306/shc_oms";

		Connection conn = null;
		try {

			Class.forName(JDBC_DRIVER).newInstance();

			Reporter.log("Connecting to database - dvomsq.qa.ch3.s.com:3306/shc_oms");

			Reporter.log("DB Username - omsdev");
			Reporter.log("DB Pwd - 0msd3v");
			conn = DriverManager.getConnection(DB_URL, "omsdev", "0msd3v");

			Reporter.log("MySQL JDBC Connection successful!");
		} catch (Exception e) {

			Reporter.log("Connection Failed!");
			e.printStackTrace();
		}
		return conn;
	}
	public synchronized static Connection getmysqlConnectionMSPOHM() {
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://atubohm3006p.prod.ch3.s.com:3306/msp";
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			Reporter.log("Connecting to database - "+DB_URL);
			conn = DriverManager.getConnection(DB_URL, "mspuser", "mspuser");
			Reporter.log("MSP OHM Connection successful!");
		} catch (Exception e) {
			Reporter.log("MSP OHM Connection Failed!");
			e.printStackTrace();
		}
		return conn;
	}
	public synchronized static Connection getmysqlConnectionOHM() {
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://atubohm3004p.prod.ch3.s.com:3306/aeng_prod_productdb";
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			Reporter.log("Connecting to database - "+DB_URL);
			conn = DriverManager.getConnection(DB_URL, "reportuser", "reportuser");
			Reporter.log("MySQL OHM JDBC Connection successful!");
		} catch (Exception e) {
			Reporter.log("OHM Connection Failed!");
			e.printStackTrace();
		}
		return conn;
	}
	public synchronized static Connection getmysqlConnectionOHMConfig() {
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://atubohm3004p.prod.ch3.s.com:3306/automation_config";
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER).newInstance();
			Reporter.log("Connecting to database - "+DB_URL);
			conn = DriverManager.getConnection(DB_URL, "reportuser", "reportuser");
			Reporter.log("MySQL OHM Config JDBC Connection successful!");
		} catch (Exception e) {
			Reporter.log("OHM OHM Config Connection Failed!");
			e.printStackTrace();
		}
		return conn;
	}
}
