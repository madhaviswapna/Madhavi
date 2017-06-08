package com.shc.msp.ft.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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
	
	public static synchronized String fetchRandomRoleFrom_cssRole(){
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("enabled", true);
			
			Integer Count = (int) MongoDB.getDB().getCollection("cssRole").find(searchQuery).count();
			System.out.println("Count of records in cssRole:- "+Count);
			Random rand = new Random();
			DBCursor cursor =MongoDB.getDB().getCollection("cssRole").find(searchQuery).skip(rand.nextInt(Count)-1).limit(1);
			BasicDBObject result = (BasicDBObject) cursor.next();
			String role = result.get("name").toString();
			System.out.println(role);
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			return "Execution Failed "+e.getMessage();
		}		
	}
	
	public static synchronized String fetchRandomQueueFrom_cssQueue(){
		try {
			BasicDBObject searchQuery = new BasicDBObject();
			List<BasicDBObject> obj1 = new ArrayList<BasicDBObject>();
			obj1.add(new BasicDBObject("isActive", true));
			obj1.add(new BasicDBObject("management.piority",new BasicDBObject("$exists", true)));
			//obj1.add(new BasicDBObject("svl.hour",new BasicDBObject("$gt", 0)));
			searchQuery.put("$and", obj1);
		
			
			Integer Count = (int) MongoDB.getDB().getCollection("cssQueue").find(searchQuery).count();
			System.out.println("Count of records in cssQueue:- "+Count);
			Random rand = new Random();
			DBCursor cursor =MongoDB.getDB().getCollection("cssQueue").find(searchQuery).skip(rand.nextInt(Count)-1).limit(1);
			BasicDBObject result = (BasicDBObject) cursor.next();
			String role = result.get("description").toString();
			System.out.println(role);
			return role;
		} catch (Exception e) {
			e.printStackTrace();
			return "Execution Failed "+e.getMessage();
		}		
	}
	//Main method for testing purposes
	public static void main(String[] args) {
		
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
