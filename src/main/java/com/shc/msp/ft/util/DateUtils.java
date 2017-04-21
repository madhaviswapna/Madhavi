package com.shc.msp.ft.util;

import java.util.Calendar;

public class DateUtils {
	 public static int fetchCurrentDayOfMonth(){
	    	Calendar cal = Calendar.getInstance();
			int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
			return dayOfMonth;
	    }
}
