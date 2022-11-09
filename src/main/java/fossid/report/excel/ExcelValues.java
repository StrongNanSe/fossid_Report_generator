package fossid.report.excel;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import fossid.report.values.ProjectValues;

public class ExcelValues {
	private final Logger logger = LogManager.getLogger(ExcelValues.class);
	private static final ExcelValues values = new ExcelValues();
	ProjectValues pValues = ProjectValues.getInstance();

	private ExcelValues() {
	}

	public static ExcelValues getInstance() {
		return values;
	}

	private WritableWorkbook wb;
	private WritableSheet sheet0;
	private WritableSheet sheet1;
	private WritableSheet sheet2;
	private WritableSheet sheet3;
	private WritableSheet sheet4;
	private WritableSheet sheet5;

	
	String fileName;

	public void setWB() {
		
		String date = new DateTime().toString(DateTimeFormat.forPattern("yyyyMMdd_HHmmss"));
		//System.out.println("ExcelValues.java = " + date);

		fileName = date + "_"+ pValues.getProjectName() + "_" + pValues.getVersionName() + "_Report.xls";

		WorkbookSettings wbSetting = new WorkbookSettings();
		wbSetting.setAutoFilterDisabled(false);

		try {
			wb = Workbook.createWorkbook(new File(fileName), wbSetting);
		} catch (IOException e) {
			logger.error("Exception Message", e);
		}

	}
	public WritableWorkbook getWB(){
		return wb;
	}
	
	

	public void closeExcel(){
		try {
			wb.write();
			wb.close();
		} catch (IOException | WriteException e) {
			logger.error("Exception Message", e);
		}

	}
	

	public String getFileName(){
		return fileName;
	}
	
	public WritableSheet getSheet0(){
		return sheet0;
	}
	
	public WritableSheet getSheet1(){
		return sheet1;
	}
	
	public WritableSheet getSheet2(){
		return sheet2;
	}
	
	public WritableSheet getSheet3(){
		return sheet3;
	}
	
	public WritableSheet getSheet4(){
		return sheet4;
	}
	
	public WritableSheet getSheet5(){
		return sheet5;
	}
	
}
