package com.shc.msp.ft.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.shc.automation.Logger;
import com.shc.automation.utils.TestUtils.TestStepType;
import com.shc.msp.ft.pages.Page;

public class APIRequest {

	public static HashMap<String, Object> get(String apiEndpoint) {
		return send("GET", apiEndpoint, null, null, null, null, null);
	}

	public static HashMap<String, Object> get(String apiEndpoint, String path) {
		return send("GET", apiEndpoint, path, null, null, null, null);
	}

	public static HashMap<String, Object> get(String apiEndpoint, String path, Properties urlParams) {
		return send("GET", apiEndpoint, path, urlParams, null, null, null);
	}

	public static HashMap<String, Object> get(String apiEndpoint, Properties urlParams) {
		return send("GET", apiEndpoint, null, urlParams, null, null, null);
	}

	public static HashMap<String, Object> get(String apiEndpoint, String path, Properties urlParams, Properties headers) {
		return send("GET", apiEndpoint, path, urlParams, null, null, headers);
	}

	public static HashMap<String, Object> post(String apiEndpoint, String path, Properties urlParams, String payloadContentType, String payload, Properties headers) {
		return send("POST", apiEndpoint, path, urlParams, payloadContentType, payload, headers);
	}

	public static HashMap<String, Object> send(String requestType, String apiEndpoint, String path, Properties urlParams, String payloadContentType, String payload, Properties headers) {
		System.out.println(apiEndpoint+path+":"+payload.toString());
		CloseableHttpResponse response = null;
		HashMap<String, Object> responseMap = new HashMap<String, Object>();
		String url = null;
		try {
			if (requestType == null || requestType.length() < 2) {
				requestType = "GET";
			}

			if (path == null) {
				path = "";
			}
			url = apiEndpoint + path;
			/*
			 * if (urlParams != null) { for (Object key : urlParams.keySet()) {
			 * url += "&" + key.toString() + "=" +
			 * urlParams.getProperty(key.toString()); } }
			 */
			
			HttpRequestBase method = null;
			if(requestType.equalsIgnoreCase("GET")){
				method = new HttpGet(url);
			}else if(requestType.equalsIgnoreCase("POST")){
				method = new HttpPost(url);
			}else if(requestType.equalsIgnoreCase("PUT")){
				method = new HttpPut(url);
			}else if(requestType.equalsIgnoreCase("DELETE")){
				method = new HttpDelete(url);
			}else {
				method = new HttpGet(url);
			}

			if (urlParams != null) {
				List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
				if (!urlParams.containsKey("grant_type")) {
					urlParameters.add(new BasicNameValuePair("grant_type", "client_credentials"));
				}
				for (Object key : urlParams.keySet()) {
					urlParameters.add(new BasicNameValuePair(key.toString(), urlParams.getProperty(key.toString())));
				}
				((HttpPost) method).setEntity(new UrlEncodedFormEntity(urlParameters));
			}

			CloseableHttpClient http = HttpClientBuilder.create().build();

			if (payload != null) {
				if (payloadContentType == null) {
					payloadContentType = "application/json";
				}
				StringEntity entity = new StringEntity(payload, "UTF-8");
				entity.setContentType(payloadContentType);
				((HttpPost) method).setEntity(entity);
			}
			RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT).setSocketTimeout(10000).setConnectTimeout(5000).setConnectionRequestTimeout(10000).build();
			method.setConfig(requestConfig);
			if (headers == null) {
				headers = new Properties();
			}
			if (!headers.containsKey("User-Agent")) {
				method.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.94 Safari/537.36/SHC Automation/1.0 KTXN");
			}
			if (!headers.containsKey("Accept")) {
				method.addHeader("Accept", "application/json");
			}
			if (!headers.containsKey("Authorization")) {
				method.addHeader("Authorization", "Basic SW50ZXJhY3Rpb25zOlNtOXphR2xUYUdGcGJHVnphQT09");
			}
			for (Object key : headers.keySet()) {
				method.addHeader(key.toString(), headers.getProperty(key.toString()));
			}
			response = http.execute(method);
			System.out.println(response.toString());
			responseMap.put("response", response);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			responseMap.put("responseContent", result.toString());
			http.close();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.log("Exception while calling API :" + url + ", Error message - " + e.getMessage(), TestStepType.ERRORMESSAGE);
		}

		return responseMap;
	}

	public static JsonObject parseJsonObject(String jsonString) {
		JsonObject res = null;
		try {
			res = (JsonObject) new JsonParser().parse(Page.getMatchingTextGroup("(\\{.*\\})", jsonString));
		} catch (Exception e) {
			e.printStackTrace();
			Logger.log("Exception while parsing response :" + e.getMessage(), TestStepType.ERRORMESSAGE);
		}
		return res;

	}

}
