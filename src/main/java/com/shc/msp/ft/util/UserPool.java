package com.shc.msp.ft.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Reporter;

import com.shc.automation.utils.DataReader;
import com.shc.msp.ft.entities.User;

public class UserPool {
	static HashMap<String, Boolean> users;
	static HashMap<String, Boolean> deliveryUsers;
	static Connection conn = MysqlDBConnection.getmysqlConnectionMSPOHM();

	public static void loadUsers() {
		users = new HashMap<String, Boolean>();
		Map<String, User> userMap = new DataReader<User>().readMap("userDetails");
		for (String user : userMap.keySet()) {
			users.put(userMap.get(user).userName, false);
			//addUser(userMap.get(user).userName);
			// MongoDB.createUser(userMap.get(user).userName,"testonline0001");
		}
	}


	public static synchronized String getUser() {
		try {
			if (users == null) {
				loadUsers();
			}
			String user = "";
			PreparedStatement st = null;
			ResultSet rs = null;
			String query = "SELECT username FROM users WHERE username like 'testonline%' AND used='false' LIMIT 1";
			st = conn.prepareStatement(query);
			st.execute();
			rs = st.getResultSet();
			if (rs.next()) {
				user = rs.getString("username");
				setUser(user);
				return user;
			} else {
				resetUsers("testonline");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getUser();
	}

	public static void loadDeliveryUsers() {
		deliveryUsers = new HashMap<String, Boolean>();
		Map<String, User> userMap = new DataReader<User>().readMap("userDetailsDelivery");
		for (String user : userMap.keySet()) {
			deliveryUsers.put(userMap.get(user).userName, false);
			//addUser(userMap.get(user).userName);
			// MongoDB.createUser(userMap.get(user).userName,"testdelivery0001");
		}
	}

	
	public static synchronized String getDeliveryUser() {
		try {
			if (deliveryUsers == null) {
				loadDeliveryUsers();
			}
			String user = "";
			PreparedStatement st = null;
			ResultSet rs = null;
			String query = "SELECT username FROM users WHERE username like 'testdelivery%' AND used='false' LIMIT 1";
			st = conn.prepareStatement(query);
			st.execute();
			rs = st.getResultSet();
			if (rs.next()) {
				user = rs.getString("username");
				setUser(user);
				return user;
			} else {
				resetUsers("testdelivery");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getDeliveryUser();
	}
	
	private static void addUser(String userName) {
		try {
			PreparedStatement st = null;
			String query = "INSERT INTO users (username) VALUES ('"+userName+"')";
			st = conn.prepareStatement(query);
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void setUser(String userName) {
		try {
			PreparedStatement st = null;
			String query = "UPDATE users SET used='true' WHERE username='" + userName+"'";
			st = conn.prepareStatement(query);
			st.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void resetUsers(String userType) {
		try {
			PreparedStatement st = null;
			String query = "UPDATE users SET used='false' WHERE username like '"+userType+"%'";
			st = conn.prepareStatement(query);
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
