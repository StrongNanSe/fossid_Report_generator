package fossid.report.excel;

import jxl.write.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CreateSheet {
	private final Logger logger = LogManager.getLogger(CreateSheet.class);
	JxlStyle style = new JxlStyle();
	
	Blank blank = null;
	Label label = null;


	Properties props = new Properties();
	String propsPath = System.getProperty("user.dir") + "\\config.properties";
	FileReader resources = null;

	public CreateSheet() {
		style.setStyle();
		try {
			resources = new FileReader(propsPath);
			props.load(resources);
		} catch (IOException e) {
			logger.error("Exception Message", e);
		} finally {
			try {
				if (resources != null) {
					resources.close();
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
