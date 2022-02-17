package fossid.report.excel;

import java.io.File;
import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Blank;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import fossid.report.values.projectValues;

public class ExcelValues {
	private static ExcelValues values = new ExcelValues();
	projectValues pvalues = projectValues.getInstance();

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
		//System.out.println("ExcelVlues.java = " + date);

		fileName = date + "_"+ pvalues.getProjectName() + "_" + pvalues.getVersionName() + "_Report.xls";

		WorkbookSettings wbSetting = new WorkbookSettings();
		wbSetting.setAutoFilterDisabled(false);

		try {
			wb = Workbook.createWorkbook(new File(fileName), wbSetting);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public WritableWorkbook getWB(){
		return wb;
	}
	
	

	public void closeExcel(){
		try {
			wb.write();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
