package com.shc.msp.ft.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.Reporter;

public class ExcelUtil {
		
	public static HSSFSheet ExcelWSheet;
	 
	public static HSSFWorkbook ExcelWBook;
	
	public static HSSFRow Row;

	public static HSSFCell Cell;
	
	public static ThreadLocal<ExcelUtil> thread = new ThreadLocal<ExcelUtil>() {
		protected ExcelUtil initialValue() {
			return new ExcelUtil();
		}
	};
	
	public static ExcelUtil getExcelUtil(){
		return thread.get();
	}
	
	public void setupExcelFile(String Path,String SheetName) throws IOException {
	
			try {

			FileInputStream RulesFile = new FileInputStream(Path);
	
			ExcelWBook = new HSSFWorkbook(RulesFile);
			
			Reporter.log("Open the Excel File...");
	
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
	
			} catch (IOException e){
	
				e.printStackTrace();
				
			}
	}
	
	public String getCellData(int RowNum, int ColNum) throws Exception{
		
		try{

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();
			return CellData;

			}catch (Exception e){
				Reporter.log("error from getCellData(int, int) :"+e);

				return"";	
			}
		}
	
	
	public  String[][] getCellData(int RowNum, int ColNum, int TotalCols) throws Exception{
		String[][] arrayExcelData = null;
			try{

				arrayExcelData = new String[ExcelWSheet.getPhysicalNumberOfRows()-1][TotalCols];
	
				for (int i= 1 ; i < ExcelWSheet.getPhysicalNumberOfRows(); i++) {

					for (int j=0; j < TotalCols; j++) {
						arrayExcelData[i-1][j] = ExcelWSheet.getRow(i).getCell(j).toString();
					}
			
				}
				}catch (Exception e){
					Reporter.log("error from getCellData(int, int) :"+e);
	
						
				}
			return arrayExcelData;
			}
	

	public  void setCellData(String sheetname, Map<Integer, Object[]> result,  int RowNum, int ColNum) throws IOException	{
		
		FileInputStream DataFile = new FileInputStream(Constant.Path_TestData + Constant.File_TestData);
		HSSFWorkbook workbook = new HSSFWorkbook(DataFile);
		
		HSSFSheet sheet = workbook.getSheet(sheetname);
			
		try{

				Set<Integer> keyset = result.keySet();
			    
			      for (int i = 0; i < keyset.size(); i++, RowNum++)
			      {
			          Row row = sheet.getRow(RowNum);
			          
			          Object [] objArr = result.get(i);
			          
			          for (int j = 0; j < objArr.length; j++, ColNum++)
			          {
			     
			        	  Cell cell = row.createCell(ColNum);
			
			            	 cell.setCellValue((String)objArr[j]);
			
			          }
			      }
			      
			//FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
			
			workbook.write(fileOut);
				
				Reporter.log("Save the Excel File...");

				fileOut.flush();

				fileOut.close();
			workbook.close();	

			}catch(IOException e){

				throw (e);
		}
	}
	
	public  Object[][] getDataArray(String SheetName, int StartRow, int StartColumn, int TotalCols) throws Exception {   
		 
		   String[][] tabArray = null;

		   try {

			   FileInputStream ExcelFile = new FileInputStream(Constant.Path_TestData + Constant.File_TestData);

			   ExcelWBook = new HSSFWorkbook(ExcelFile);

			   ExcelWSheet = ExcelWBook.getSheet(SheetName);
			   
			   int ci, cj, r, c;
			   
			   tabArray=new String[ExcelWSheet.getPhysicalNumberOfRows()-1][TotalCols];

			   ci=0;
			   r = StartRow;
			   for (int i=0;i<ExcelWSheet.getPhysicalNumberOfRows()-1;i++, r++, ci++) {           	   

				cj=0; 
				c = StartColumn;
				   for (int j=0;j<TotalCols;j++, c++, cj++){

					   tabArray[ci][cj]=getCellData(r,c).trim();

					   System.out.println("testing data "+(i+1)+": "+tabArray[ci][cj]);  
					

						}}

				}catch (FileNotFoundException e){

					Reporter.log("Could not read the Excel sheet");

				e.printStackTrace();

				}catch (IOException e){

					Reporter.log("Could not read the Excel sheet");

				e.printStackTrace();

				}

			return(tabArray);

			}

	public  Object[][] getFilterDataArray(ArrayList<String> searchrows, int TotalCols) throws Exception {   
		 
		   String[][] tabArray = null;

			   tabArray=new String[searchrows.size()][TotalCols];
			   for (int i=0;i<searchrows.size();i++ ) {           	   

				   for (int j=0;j<TotalCols;j++){

					   tabArray[i][j]=searchrows.get(i).trim();
					   
						}
				   if(TotalCols==1){
					   System.out.println("testing data "+(i+1)+": "+tabArray[i][0]);
				   }else{
					   
					   System.out.println("testing data "+(i+1)+": "+tabArray[i][0]+","+tabArray[i][1]);   }
			   }
			   
			return(tabArray);

			}
	
	public  Object[][] getTestDataArray(String SheetName, int StartRow, int StartColumn, int TotalCols,String inputData) throws Exception {   
		 
		   String[][] tabArray = null;
		   String outPut = null;
		   String newArray[][]=new String[1][1];
		   FileInputStream ExcelFile = new FileInputStream(Constant.Path_TestData + Constant.File_TestData);

		   ExcelWBook = new HSSFWorkbook(ExcelFile);

		   ExcelWSheet = ExcelWBook.getSheet(SheetName);

		   try {

			   int ci, cj, r, c;

			   tabArray=new String[ExcelWSheet.getPhysicalNumberOfRows()][TotalCols];

			   ci=0;
			   r = StartRow;
			   for (int i=0;i<ExcelWSheet.getPhysicalNumberOfRows();i++, r++, ci++) {           	   

				cj=0; 
				c = StartColumn;
				   for (int j=0;j<TotalCols;j++, c++, cj++){

					   tabArray[ci][cj]=getCellData(r,c).trim();
					 //  System.out.println("testing data: "+i);
					  // System.out.println("testing data: "+tabArray[ci][cj]);  

						}}
			   

				}catch (FileNotFoundException e){

					Reporter.log("Could not read the Excel sheet");

				e.printStackTrace();

				}catch (IOException e){

					Reporter.log("Could not read the Excel sheet");

				e.printStackTrace();

				}
		   for(int i=0;i<tabArray.length;i++)
		   {
			   for(int j=0;j<tabArray[i].length;j++)
			   {
				   if(tabArray[i][j].equals(inputData))
				   {System.out.println("tabArray[i][j]"+tabArray[i][j]);
					    outPut= tabArray[i+1][j].trim();
					  //  newArray[0][0]=inputData;
					    newArray[0][0]=outPut;
						   System.out.println("testing data: "+newArray[0][0]);  
						  // System.out.println("testing data: "+newArray[0][1]);  

				   }
				   else outPut=null;
					   
			   }
		   }
		return newArray;
			

			}
	public  Object[][] getStoreid_Orderid_DataArray(ArrayList<String> orderID, ArrayList<String> storeID,  String SheetName) throws Exception {   
		 
		   String[][] dataArray = null;

			   FileInputStream ExcelFile = new FileInputStream(Constant.Path_TestData + Constant.File_TestData);

			   ExcelWBook = new HSSFWorkbook(ExcelFile);

			   ExcelWSheet = ExcelWBook.getSheet(SheetName);
			   
			   dataArray=new String[orderID.size()][2];

			   for (int i=0;i<orderID.size();i++) { 

				   dataArray[i][0]=orderID.get(i).trim();
				   dataArray[i][1]=storeID.get(i).trim();
				   System.out.println("testing data "+(i+1)+": "+dataArray[i][0]+","+dataArray[i][1]);

				  }
	
				return dataArray;
	}
	
	public   ArrayList<String> searchKeyWord(String searchText, String SheetName, int StartColumn, int TotalCols) throws Exception {

		 FileInputStream ExcelFile = new FileInputStream(Constant.Path_TestData + Constant.File_TestData);

		   ExcelWBook = new HSSFWorkbook(ExcelFile);

		   ExcelWSheet = ExcelWBook.getSheet(SheetName);
		   
        ArrayList<String> filteredRows = new ArrayList<String>();

        for (int j = Constant.StartRow_SearchOrder; j < ExcelWSheet.getPhysicalNumberOfRows(); j++) {

            Row row = ExcelWSheet.getRow(j);
            
            int c = StartColumn;

            for (int k =0; k < TotalCols; k++, c++) {
                
            	Cell cell = row.getCell(11);
            	
            	if (cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
            	{
            		if(cell.getStringCellValue().contains(searchText))  
                    	
                        filteredRows.add(getCellData(j, c));
            	}
            }
        }
        
        return filteredRows;
    }
}
