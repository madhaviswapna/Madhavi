package com.shc.msp.ft.util;

import java.io.FileWriter;

public class FileUtil {

	public static void logToFile(String msg) {
		logToFile(msg,"fileLog");
	}

	public static void logToFile(String msg, String fileName) {
		try {
			FileWriter fw = new FileWriter(fileName+".txt",true);
			fw.write(msg);
			fw.close();
		} catch (Exception e) {}
	}

}
