package fossid.report.excel;

import fossid.report.getdata.GetNumberOfIgnored;
import fossid.report.values.BillOfMaterialsValues;
import fossid.report.values.IdentifiedFilesValues;
import fossid.report.values.ProjectValues;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Set;

public class CreateSheet3 extends CreateSheet{
	private final Logger logger = LogManager.getLogger(CreateSheet3.class);
	public CreateSheet3(){
		super();
	}
	
	IdentifiedFilesValues idValues = IdentifiedFilesValues.getInstance();
	BillOfMaterialsValues bomValues = BillOfMaterialsValues.getInstance();
	ProjectValues pValues = ProjectValues.getInstance();
	ExcelValues excelVal = ExcelValues.getInstance();
	WritableSheet sheet3 = excelVal.getSheet3();
		
	GetNumberOfIgnored ignoredNumber = new GetNumberOfIgnored();

	public void writeSheet() throws WriteException {
		logger.info("Creating sheet 4..");

		sheet3 = excelVal.getWB().createSheet("4. 라이선스 비율", 3);
		
		sheet3.setColumnView(0, 60);
		sheet3.setColumnView(1, 28);
		sheet3.setColumnView(2, 28);
		sheet3.setColumnView(3, 28);
		sheet3.setColumnView(4, 28);
		
		sheet3.setRowView(0, 800);
		addLabel(sheet3, 0, 0, "4. 라이선스 비율", style.sh1TitleFormat);
		
		sheet3.setRowView(1, 700);
		addLabel(sheet3, 0, 1, "라이선스", style.sh1tableFormat2);
		addLabel(sheet3, 1, 1, "결합 형태", style.sh1tableFormat2);
		addLabel(sheet3, 2, 1, "해당파일수", style.sh1tableFormat2);
		addLabel(sheet3, 3, 1, "%", style.sh1tableFormat2);
		
		int projectRowCount = 0;
		
		Set<String> set = bomValues.getULicenseFileCount().keySet();
		for (String key : set) {

			sheet3.setRowView(projectRowCount + 2, 500);

			addLabel(sheet3, 0, projectRowCount + 2, key, style.sh1tableFormat1);
			addLabel(sheet3, 1, projectRowCount + 2, bomValues.getLicenseMatchType().get(key), style.sh1tableFormat1);
			addLabel(sheet3, 2, projectRowCount + 2, Integer.toString(bomValues.getULicenseFileCount().get(key)), style.sh1tableFormat1);

			int licenseCount = Integer.parseInt(bomValues.getULicenseFileCount().get(key).toString());
			double percent = ((double) licenseCount / idValues.getAnalyzedFileCount()) * 100;
			addLabel(sheet3, 3, projectRowCount + 2, String.format("%.2f", percent) + "%", style.sh1tableFormat1);

			projectRowCount++;
		}					
				 
		sheet3.setRowView(projectRowCount + 2, 500);
		addLabel(sheet3, 0, projectRowCount + 2, pValues.getProjectLicense(), style.sh1tableFormat1);
		int totalCount = idValues.getFileTotalCount() - idValues.getIgnoredCount() - idValues.getIdentifiedFileCount();
		double percent = ((double) totalCount / (double)(idValues.getFileTotalCount() - idValues.getIgnoredCount()))* 100;
		addLabel(sheet3, 1, projectRowCount + 2, "N/A", style.sh1tableFormat1);
		addLabel(sheet3, 2, projectRowCount + 2, Integer.toString(totalCount), style.sh1tableFormat1);
		addLabel(sheet3, 3, projectRowCount + 2, String.format("%.2f", percent) + "%", style.sh1tableFormat1);
		
		sheet3.setRowView(projectRowCount + 3, 500);
		addLabel(sheet3, 0, projectRowCount + 3, "합계", style.sh1tableFormat1);		
		addLabel(sheet3, 1, projectRowCount + 3, "", style.sh1tableFormat1);
		sheet3.mergeCells(0, projectRowCount + 3, 1, projectRowCount + 3);
		addLabel(sheet3, 2, projectRowCount + 3, Integer.toString(idValues.getFileTotalCount() - idValues.getIgnoredCount()), style.sh1tableFormat1);
		addLabel(sheet3, 3, projectRowCount + 3, "100%", style.sh1tableFormat1);
	}
}