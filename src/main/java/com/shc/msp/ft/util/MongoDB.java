package com.shc.msp.ft.util;

import java.util.Collection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDB {
	static DB db;

	public static synchronized DB getDB() {
		if (db == null) {
			try {
				MongoClient mg = new MongoClient(new MongoClientURI("mongodb://msp_qa_user:mspqau3er@mspmdv301p.dev.ch3.s.com:20000/member_service_portal_qa"));
				db = mg.getDB("member_service_portal_qa");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return db;
	}
	
	public static synchronized boolean deleteCasesforOrderfromDB(String orderId) {
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("order.orderId", orderId);
			MongoDB.getDB().getCollection("cssCases").remove(searchQuery);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static synchronized boolean deleteAssignedCasesforUserfromDB(String userID) {
		try {
			BasicDBObject searchQuery = new BasicDBObject("owner.username",userID).
					 append("status", "CASE_WORKING");
			System.out.println("-----------------------------------------------query:"+searchQuery.toString());
			MongoDB.getDB().getCollection("cssCases").remove(searchQuery);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static synchronized boolean deleteCasesforOrderfromDB(String jsonPath,String orderId) {
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(jsonPath, orderId);
			MongoDB.getDB().getCollection("cssCases").remove(searchQuery);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public static synchronized boolean createUser(String userName) {
		return createUser(userName,"testonline0001");
	}
	public static synchronized boolean createUser(String userName,String userNameToCopy) {
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("username", userName);
			if(MongoDB.getDB().getCollection("cssUserDetails").find(searchQuery).count()>0){
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("username", userNameToCopy);
			DBObject doc = MongoDB.getDB().getCollection("cssUserDetails").find(searchQuery).next();
			doc.removeField("_id");
			doc.put("ocaSalesId", userName);
			doc.put("username", userName);
			doc.put("firstName", userName.replace("testdelivery", "TestDelivery"));
			doc.put("firstName", userName.replace("testonline", "TestOnline"));
			MongoDB.getDB().getCollection("cssUserDetails").insert(doc);	
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		/*try {
			JsonObject jsonObject = new JsonObject();
			try {
	            JsonParser parser = new JsonParser();
	            JsonElement jsonElement = parser.parse(new FileReader("./src/main/resources/userJsonTemplateDelivery.json"));
	            jsonObject = jsonElement.getAsJsonObject();
	        } catch (Exception e) {
	           e.printStackTrace();
	        } 
			DBObject user = (DBObject)JSON.parse(jsonObject.toString());
			user.put("ocaSalesId", userName);
			user.put("username", userName);
			user.put("firstName", userName.replace("testdelivery", "TestDelivery"));
			MongoDB.getDB().getCollection("cssUserDetails").insert(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}*/
		return true;
	}
}
