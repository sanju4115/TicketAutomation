package automationFramework;

import java.util.ArrayList;
import java.util.HashMap;

public class Application {
	
	private static ExcelUtil excelUtil = new ExcelUtil();
	private static Scripts script = new Scripts();
	private static ArrayList<String> incList;
	public static void main(String[] args) throws InterruptedException {
		
		
		ArrayList<Incidents> list = excelUtil.readingExcel();
		script.launch();
		
		Thread.sleep(5000);
		
		HashMap<String, String> hMap = excelUtil.loginRecords();
		
		script.signIn(hMap);

		Thread.sleep(2000);
		
		incList = script.createBasisIncident(list);
		
		excelUtil.incidentRecords(incList);
		Thread.sleep(5000);

		script.quite();
		
	}

}
