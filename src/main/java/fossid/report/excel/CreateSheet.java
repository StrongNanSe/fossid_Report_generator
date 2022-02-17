package fossid.report.excel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jxl.write.Blank;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class CreateSheet {

	ExcelValues excelVal = ExcelValues.getInstance();
	JxlStyle style = new JxlStyle();
	
	Blank blank = null;
	Label label = null;


	BufferedReader br;
	Properties props = new Properties();
	InputStream is = getClass().getResourceAsStream("/config.properties");
	InputStream ins;

	String json;
	List<Integer> vulCount = new ArrayList<Integer>();

	public CreateSheet() {
		style.setStyle();
		try {
			props.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addLabel(WritableSheet sheet, int col, int row, String text, WritableCellFormat format)
			throws RowsExceededException, WriteException {

		label = new Label(col, row, text, format);
		sheet.addCell(label);

	}

	public void addBlank(WritableSheet sheet, int col, int row, WritableCellFormat format)
			throws RowsExceededException, WriteException {

		blank = new Blank(col, row, format);
		sheet.addCell(blank);

	}


}
