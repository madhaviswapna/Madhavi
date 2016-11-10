package com.shc.msp.ft.entities;

import java.util.ArrayList;

public class OrderCharge {

	 double mt_current = 0.0;
	 double sc_current = 0.0;
	 double dc_current = 0.0;
	 double ic_current = 0.0;
	 double tax_current = 0.0;
	 double ef_current = 0.0;
	 double ship_tax_orignial = 0.0;
	 double all_list_price_sum = 0.0;
	 double mt_original = 0.0;
	 double sc_original = 0.0;
	 double dc_original = 0.0;
	 double ic_original = 0.0;
	 double tax_original = 0.0;
	 double instal_original_sum = 0.0;
	 double environment_fee_sum = 0.0;
	 double ship_adj_sum = 0.0;
	 double sales_adj_sum  = 0.0;
	 double sales_tax_adj_sum  = 0.0;
	 double total_original = 0.0;
	 double total_original_cal = 0.0;
	 double total_current_cal = 0.0;
	 double adjst_amt = 0.0;
	 double discount_amt_sum = 0.0;
	 double total_adjustment_discount = 0.0;
	 double total_adjustment_discount_percentage = 0.0;
	 ArrayList<Double> order_total = new ArrayList<Double>();
	 ArrayList<Double> discount_amt_list = new ArrayList<Double>();
	 ArrayList<Double> salesadjust = new ArrayList<Double>();
	 ArrayList<Double> shipadjust = new ArrayList<Double>();
	 ArrayList<Double> salestaxadjust = new ArrayList<Double>();
	 ArrayList<Double> ef_original = new ArrayList<Double>();
	 ArrayList<String>  order_item_type = new ArrayList<String>();
	 ArrayList<Double>  installlation = new ArrayList<Double>();
	 
	 ArrayList<String> parametersForQuery=new ArrayList<String>();
	 public OrderCharge(){
		 parametersForQuery.add("SHIPPING_AMT");
		 parametersForQuery.add("ADJMNT_AMT");
		 parametersForQuery.add("SHIPPING_TAX_AMOUNT");
		 parametersForQuery.add("TAX_AMT");
		 parametersForQuery.add("TOTAL_AMOUNT");
		 parametersForQuery.add("LIST_PRICE");
		 parametersForQuery.add("mt_current");
		 parametersForQuery.add("mt_current");
		 parametersForQuery.add("mt_current");
		 parametersForQuery.add("mt_current");
		 parametersForQuery.add("mt_current");
		 parametersForQuery.add("mt_current");
		 parametersForQuery.add("mt_current");
		 parametersForQuery.add("mt_current");
		 parametersForQuery.add("mt_current");
		 parametersForQuery.add("mt_current");
		 parametersForQuery.add("mt_current");
		 parametersForQuery.add("mt_current");
	 }
	 

}
