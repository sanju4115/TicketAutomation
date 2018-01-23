package automationFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
	 //private static final String FILE_NAME = "C:\\Users\\sku261\\Desktop\\BasisAutomation\\incident_format.xlsx";
	private static final String FILE_NAME = "./incident_format.xlsx";
	private static ArrayList<Incidents> list = new ArrayList<>();
	
	public ArrayList<Incidents> readingExcel(){
		try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            iterator.next();  //skipping the header            
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                String caller = " ";
            	String description = " "; 
            	String serviceCategory = " ";
            	String assignmentGroup = " ";
            	String assignedTo = " ";
            	String customerUpdate = " ";

            	int i=1;
                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();                   
                    if(i==1){
                    	caller = currentCell.getStringCellValue();
                    }else if(i==2){
                    	description = currentCell.getStringCellValue();
                    }else if(i==3){
                    	serviceCategory = currentCell.getStringCellValue();
                    }else if(i==4){
                    	assignmentGroup = currentCell.getStringCellValue();
                    }else if(i==5){
                    	assignedTo = currentCell.getStringCellValue();
                    }else if(i==6){
                    	customerUpdate = currentCell.getStringCellValue();
                    }
                    
                    i++;
               }
                
                Incidents incident = new Incidents(caller, description, serviceCategory, assignmentGroup, assignedTo, customerUpdate);
                list.add(incident);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return list;
	}
	
	
	public void incidentRecords(ArrayList<String> incList){
		
		try {
            FileInputStream inputStream = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = WorkbookFactory.create(inputStream);
 
            Sheet sheet = workbook.getSheetAt(1);
 
            int rowCount = sheet.getLastRowNum();
 
            for (String s : incList) {
                Row row = sheet.createRow(++rowCount);
 
                int columnCount = 0;
                 
                Cell cell = row.createCell(columnCount);
                cell.setCellValue((String) s);
                
                CellStyle cellStyle = workbook.createCellStyle();
                CreationHelper createHelper = workbook.getCreationHelper();
                cellStyle.setDataFormat(
                    createHelper.createDataFormat().getFormat("d/m/yy"));
                cell = row.createCell(++columnCount);
                cell.setCellValue(new Date());
                cell.setCellStyle(cellStyle); 
            }
 
            inputStream.close();
 
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
             
        } catch (IOException | EncryptedDocumentException
                | InvalidFormatException ex) {
            ex.printStackTrace();
        }
		
	}

	
	public HashMap<String, String> loginRecords(){
		
		HashMap<String, String> hMap = new HashMap<>();
		String loginID = " ";
    	String password = " "; 
		try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(2);
            Iterator<Row> iterator = datatypeSheet.iterator();
            iterator.next();  //skipping the header            
            if(iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
            	Cell currentCell = cellIterator.next();                                  
            	loginID = currentCell.getStringCellValue();          	
            	currentCell = cellIterator.next();                                  
            	password = currentCell.getStringCellValue();
            	
          }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		System.out.println(loginID);
		System.out.println(password);
		hMap.put("loginID", loginID);
		hMap.put("password", password);
		return hMap;
		
		
	}

}
