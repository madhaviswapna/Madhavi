package com.shc.msp.ft.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;


	public class Utility {
		
		public int randomGenerator(int number)
		{
			Random randomGenerator = new Random();
			 int randomInt = randomGenerator.nextInt(number);
			 return randomInt;
		}
		
		public static String getCurrentDate(){
			Calendar now = Calendar.getInstance();
			String currentDate="'"+now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DATE)+" 01:01:01'";
			now.add(Calendar.MONTH, -1);
			return currentDate;
		}
		
		public static String getOldDate(int numOfMonthsToReduceFromCurrent){
			Calendar now = Calendar.getInstance();
			now.add(Calendar.MONTH, numOfMonthsToReduceFromCurrent*-1);
			String oldDate="'"+now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DATE)+" 01:01:01'";
			return oldDate;
		}
		
		
		/**
	     * Get description of order line item status
	     * @param statusInDB
	     * @return
	     */
	    public static String getLineItemStatusDescription(String statusInDB){
	    		HashMap<String,String> itemStatusMap  = new HashMap<String,String>();
		    	itemStatusMap.put("AFD", "Awaiting Future Date");
		    	itemStatusMap.put("ALM", "Arrived at Last Mile");
		    	itemStatusMap.put("APD", "Awaiting Presell Date");
		    	itemStatusMap.put("ARC", "Assign Release Complete");
		    	itemStatusMap.put("BAD", "Bad");
		    	itemStatusMap.put("CAN", "Canceled");
		    	itemStatusMap.put("CNF", "Confirmed");
		    	itemStatusMap.put("CSI", "Held CSI");
		    	itemStatusMap.put("DPSH", "DART Partial Shipped");
		    	itemStatusMap.put("DRES", "780");
		    	itemStatusMap.put("DSHP", "DART Shipped");
		    	itemStatusMap.put("DZSP", "DART Zero Shipped");
		    	itemStatusMap.put("EHC", "EHDS ORDER COMPLETED");
		    	itemStatusMap.put("FCAN", "Fusion Cancellation");
		    	itemStatusMap.put("FDC", "Fusion Download Complete");
		    	itemStatusMap.put("FRC", "Fraud Check Complete");
		    	itemStatusMap.put("GADC", "Gift Card Act Download Complete");
		    	itemStatusMap.put("GDC", "Gift Card Download Complete");
		    	itemStatusMap.put("HLC", "Held in payment auth");
		    	itemStatusMap.put("HLD", "Held");
		    	itemStatusMap.put("INPR", "In Progress");
		    	itemStatusMap.put("INQC", "NPOS Order Inquiry Completed");
		    	itemStatusMap.put("INTR", "IN Transit");
		    	itemStatusMap.put("IPS", "IPS Download Complete");
		    	itemStatusMap.put("KNCN", "Cancellation from KANA");
		    	itemStatusMap.put("NCON","Non Confirmed Cancel");
		    	itemStatusMap.put("NDDC", "NDDC");
		    	itemStatusMap.put("NNCN", "Order Not Found In NPOS");
		    	itemStatusMap.put("PAC","Payment Auth Complete");
		    	itemStatusMap.put("PCAN","Pending Cancellation");
		    	itemStatusMap.put("PCON","Pending Confirmation");
		    	itemStatusMap.put("PFS","Picked up From Store");
		    	itemStatusMap.put("PRET","Pending Return");
		    	itemStatusMap.put("PRFD","Partially Refused");
		    	itemStatusMap.put("PRO","Processing");
		    	itemStatusMap.put("PSCN","Pending Store Cancellation");
		    	itemStatusMap.put("PSH","Partial Shipped");
		    	itemStatusMap.put("PSTC","Pending Store Cancellation");
		    	itemStatusMap.put("RET", "Returned");
		    	itemStatusMap.put("RFP", "Ready For Pickup");
		    	itemStatusMap.put("SCC","Scim Confirmation Complete");
		    	itemStatusMap.put("SCCA","Scim Confirmation Complete ALP");
		    	itemStatusMap.put("SCCN","Scim Canceled");
		    	itemStatusMap.put("SDC","Salescheck Download to POS Complete");
		    	itemStatusMap.put("SHP","Shipped");
		    	itemStatusMap.put("SRC","Scim Release Complete");
		    	itemStatusMap.put("STC","Store Canceled");
		    	itemStatusMap.put("SUB","Submitted");
		    	itemStatusMap.put("TEST","Key note order");
		    	itemStatusMap.put("TRN","Transmitting");
		    	itemStatusMap.put("TRNS","Transmitting to second ext sys");
		    	itemStatusMap.put("VDC","Vendor Download Complete");
			    itemStatusMap.put("VGCA","VGC Activated");
			    itemStatusMap.put("VPSH","Vendor Partial Shipped");
			   itemStatusMap.put("VSHP","Vendor Shipped");
			   itemStatusMap.put("VUC","Vendor Upload Complete");
			   itemStatusMap.put("VZSP","Vendor Zero Shipped");
			   itemStatusMap.put("WFP","Waiting For Payment");
			   itemStatusMap.put("ZSP","Zero Shipped / Shipment Canceled");
			   itemStatusMap.put("CNRQ","Cancellation From Profile or MSP");
			   itemStatusMap.put("CPSH","CDFC Partial Shipped");
			   itemStatusMap.put("CSHP","CDFC Shipped");
			   itemStatusMap.put("CZSP","CDFC Zero Shipped");
			   itemStatusMap.put("RCAN","RCAN");
			   itemStatusMap.put("REP","Item replaced by new EO item");
			   itemStatusMap.put("CMIX","Mixed CDFC shipment");
			   itemStatusMap.put("MIX","Mixed");
			   return itemStatusMap.get(statusInDB);
	    }
}
