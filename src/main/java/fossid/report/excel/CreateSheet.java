package fossid.report.excel;

import jxl.write.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CreateSheet {
	private final Logger logger = LogManager.getLogger(CreateSheet.class);
	JxlStyle style = new JxlStyle();
	
	Blank blank = null;
	Label label = null;


	BufferedReader br;
	Properties props = new Properties();
	InputStream is;

	public CreateSheet() {
		style.setStyle();
		is = getClass().getResourceAsStream("/config.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			logger.error("Exception Message", e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				logger.error("Exception Message", e);
			}
		}
	}

	public void addLabel(WritableSheet sheet, int col, int row, String text, WritableCellFormat format)
			throws WriteException {

		label = new Label(col, row, text, format);
		sheet.addCell(label);

	}

	public void addBlank(WritableSheet sheet, int col, int row, WritableCellFormat format)
			throws WriteException {

		blank = new Blank(col, row, format);
		sheet.addCell(blank);

	}
}
