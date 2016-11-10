package com.shc.msp.ft.util;

import java.util.HashMap;
import java.util.Properties;

import org.apache.http.client.methods.CloseableHttpResponse;

import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.shc.automation.FrameworkProperties;
import com.shc.automation.utils.TestContext;
import com.shc.msp.ft.pages.Page;

public class ScreenPopAPIRequest {

	//private static final String API_ENDPOINT = "http://msptraining.prod.global.s.com";
	//private static final String API_ENDPOINT = "http://mspapp301p.qa.ch3.s.com:9780";
	private static final String API_ENDPOINT = FrameworkProperties.getProperty("screenpopapi", "http://mspapp301p.qa.ch3.s.com:9780");
	
	public static void call(String pv7, String pv8, String agentLDAPID) {
		
		DBObject payload = new BasicDBObject();
		payload.put("agentLDAPID", agentLDAPID);
		payload.put("mspSessionID", TestContext.get().get("JSESSIONID").toString());
		payload.put("phoneCallID", "s");
		payload.put("pv7", pv7);
		payload.put("pv8", pv8);
		payload.put("pv9", "N");
		String path = "/rubix-cube/cti/interface/messageAgent";
		APIRequest.post(API_ENDPOINT, path, null, null, payload.toString(), null);
	}

	public static void callWithPV7Null(String pv1, String pv8, String agentLDAPID) {
		DBObject payload = new BasicDBObject();
		payload.put("agentLDAPID", agentLDAPID);
		payload.put("mspSessionID", TestContext.get().get("JSESSIONID").toString());
		payload.put("phoneCallID", "s");
		payload.put("pv1", "8006973277^"+pv1+"^E");
		System.out.println("PV1 value"+ pv1);
		payload.put("pv7", "");
		payload.put("pv8", pv8);
		payload.put("pv9", "N");
		String path = "/rubix-cube/cti/interface/messageAgent";
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		APIRequest.post(API_ENDPOINT, path, null, null, payload.toString(), null);
	}
	
	public static String getAuthToken() {
		String access_token = "752b8f09-1130-4955-b62e-fd8504ec057f";
		String url = "http://mspvip.qa.ch3.s.com/rubix-cube/oauth/token";
		Properties urlParams = new Properties();
		urlParams.setProperty("grant_type", "client_credentials");
		HashMap<String, Object> response = APIRequest.post(url, null, urlParams, null, null, null);
		JsonObject res = APIRequest.parseJsonObject(response.get("responseContent").toString());
		access_token = Page.getMatchingTextGroup("\"(.*)\"", res.get("access_token").toString());
		System.out.println("Auth Token:" + access_token);
		return access_token;
	}

}
 