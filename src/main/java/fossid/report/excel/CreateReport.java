package fossid.report.excel;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CreateReport {
	
	ExcelValues excelVal = ExcelValues.getInstance();
	JxlStyle style = new JxlStyle();
	
	CreateSheet0 sheet0 = new CreateSheet0();
	CreateSheet1 sheet1 = new CreateSheet1();
	CreateSheet2 sheet2 = new CreateSheet2();
	CreateSheet3 sheet3 = new CreateSheet3();
	CreateSheet4 sheet4 = new CreateSheet4();
	CreateSheet5 sheet5 = new CreateSheet5();

	
	public void writeReport() throws RowsExceededException, WriteException {
		excelVal.setWB();
		
		sheet0.writeSheet();
		sheet1.writeSheet();		
		sheet2.writeSheet();
		sheet3.writeSheet();
		sheet4.writeSheet();
		sheet5.writeSheet();

		excelVal.closeExcel();
	}

}