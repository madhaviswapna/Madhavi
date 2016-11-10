package com.shc.msp.ft.util;

public class StringUtils {

	public static String extractNumber(String text) {
		StringBuilder number = new StringBuilder();
		char[] chars = text.toCharArray();
		for(char c:chars){
			if(Character.isDigit(c)){
				number.append(c);
			}
		}
		return number.toString();
	}

}
