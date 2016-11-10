package com.shc.msp.ft.util;
import java.io.StringWriter;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;

import com.shc.automation.Logger;
import com.shc.automation.utils.TestUtils.TestStepType;
//import

	public class SOAPRequest {

	    /**
	     * Starting point for the SAAJ - SOAP Client Testing
	     * @throws Exception 
	     */
	    @SuppressWarnings("restriction")
		public static void main(String args[]) throws Exception {
	    	
	    }	    
	    public static String getSOAPRespose(String endPoint, SOAPMessage saopMessage){
	    	String responseMessage=null;
	    	try {
	            // Create SOAP Connection
	            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

	            // Send SOAP Message to SOAP Server
	            SOAPMessage soapResponse = soapConnection.call(saopMessage, endPoint);
	           //  Process the SOAP Response
	            responseMessage = printSOAPResponse(soapResponse);
	            soapConnection.close();
	        } catch (Exception e) {
	            System.err.println("Error occurred while sending SOAP Request to Server");
	            e.printStackTrace();
	        }
	    	return responseMessage;
	    }
	    
	    @SuppressWarnings("restriction")
	    @Test
	    public static  SOAPMessage createSOAPRequest(String pagerequest,String schemaURL,String transactionid,String username,String password,String orderid,String stoteid,String source) throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String serverURI = "http://www.shc.com/schema";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("shc", serverURI);
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement soapBodyElem = soapBody.addChildElement("sch:RetrieveOrderSummaryRequest");
	        soapBodyElem.addNamespaceDeclaration("sch", schemaURL);
	        SOAPElement controlArea = soapBodyElem.addChildElement("CONTROLAREA", "", "");
	        SOAPElement TransactionID = controlArea.addChildElement("TransactionID","","");
	        TransactionID.addTextNode(transactionid);
	        SOAPElement UserName = controlArea.addChildElement("UserID","","");
	        UserName.addTextNode(username);
	        SOAPElement Password = controlArea.addChildElement("Password","","");
	        Password.addTextNode(password);
	        SOAPElement DataArea = soapBodyElem.addChildElement("DATAAREA", "", "");
	        SOAPElement OrderID = DataArea.addChildElement("OrderID","","");
	        OrderID.addTextNode(orderid);
	        SOAPElement StoreId = DataArea.addChildElement("StoreId","","");
	        StoreId.addTextNode(stoteid);
	        SOAPElement Source = DataArea.addChildElement("Source","","");
	        Source.addTextNode(source);

	        MimeHeaders headers = soapMessage.getMimeHeaders();
	        headers.addHeader("SOAPAction", serverURI  + "VerifyEmail");

	        soapMessage.saveChanges();
	        
	        /* Print the request message */
	        System.out.print("Requested SOAP Message is ");
	        soapMessage.writeTo(System.out);
	        Logger.log("SOAP Request can be found in console by searching for key 'Requested SOAP Message is' ",TestStepType.DATA_CAPTURE);
	        System.out.println();
			return soapMessage;

	    }

	    /**
	     * Method used to print the SOAP Response
	     */
	    public static  String  printSOAPResponse(SOAPMessage soapResponse) throws Exception {
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        Source sourceContent = soapResponse.getSOAPPart().getContent();
	       // System.out.print("\nResponse SOAP Message= ");
	       // StreamResult responce = new StreamResult(System.out);
	        
	        StringWriter writer =new StringWriter();
	        StreamResult result = new StreamResult(writer);
	        
	 
	        transformer.transform(sourceContent, result);
	        String strRespones = writer.toString();
	        
	        return strRespones;
	   
	        /*   System.out.println(strRespones);	      
	    	try{
	   			org.json.JSONObject xmlJSONObj = XML.toJSONObject(strRespones);
	   			String jsonResp = xmlJSONObj.toString();
	   			System.out.println("RESPONCE COVERTED TO JSON:"+jsonResp);
	   			org.json.JSONObject envelop=(org.json.JSONObject)xmlJSONObj.get("soapenv:Envelope");
	   			org.json.JSONObject body=(org.json.JSONObject)envelop.get("soapenv:Body");
	   			org.json.JSONObject retrieveOrderSummaryResponse=(org.json.JSONObject)body.get("RetrieveOrderSummaryResponse");
	   			org.json.JSONObject dataArea=(org.json.JSONObject)retrieveOrderSummaryResponse.get("DATAAREA");
	   			org.json.JSONObject orderSummary=(org.json.JSONObject)dataArea.get("OrderSummary");
	   			org.json.JSONObject paymentDetails=(org.json.JSONObject)orderSummary.get("PaymentDetails");
	   			System.out.println(paymentDetails);
	   			String CardNumber=paymentDetails.getString("CardNumber");
	   		
	   			System.out.println(CardNumber);
	   			
	   		}catch(Exception e){
	   			System.out.println(e.getMessage());
	   		}
	        return  strRespones;
	       // data = parseSoapResponse(result);*/

	    
	    }
	


	}
	    



