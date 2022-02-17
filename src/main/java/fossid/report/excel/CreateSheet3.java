package fossid.report.excel;

import jxl.write.WritableSheet;
import jxl.write.WriteException;

import java.util.Iterator;
import java.util.Set;

import fossid.report.excel.ExcelValues;
import fossid.report.values.billofmaterialsValues;
import fossid.report.values.identifiedFilesValues;
import fossid.report.getdata.getNumberofIgnored;
import fossid.report.values.projectValues;

public class CreateSheet3 extends CreateSheet{
	
	public CreateSheet3(){
		super();
	}
	
	identifiedFilesValues idvalues = identifiedFilesValues.getInstance();
	billofmaterialsValues bomValues = billofmaterialsValues.getInstance();
	projectValues pvalues = projectValues.getInstance();
	ExcelValues excelVal = ExcelValues.getInstance();
	WritableSheet sheet3 = excelVal.getSheet3();
		
	getNumberofIgnored ignoredNumber = new getNumberofIgnored();

	public void writeSheet() throws WriteException {
		System.out.println("Creating sheet 4..");		
		
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
		
		Set set = bomValues.getUlicenseFileCount().keySet();
		Iterator entries = set.iterator();
		while(entries.hasNext()){			
			
			sheet3.setRowView(projectRowCount + 2, 500);
			
			String key = (String)entries.next();			
			addLabel(sheet3, 0, projectRowCount + 2, key, style.sh1tableFormat1);			
			addLabel(sheet3, 1, projectRowCount + 2, bomValues.getlicenseMatchType().get(key), style.sh1tableFormat1);
			addLabel(sheet3, 2, projectRowCount + 2, Integer.toString(bomValues.getUlicenseFileCount().get(key)), style.sh1tableFormat1);
			
			int alicenseCount = Integer.parseInt(bomValues.getUlicenseFileCount().get(key).toString());
			double percent = ((double) alicenseCount / idvalues.getAnalzyedFileCount())* 100;
			addLabel(sheet3, 3, projectRowCount + 2, String.format("%.2f", percent) + "%", style.sh1tableFormat1);
			
			projectRowCount++;			
		}					
				 
		sheet3.setRowView(projectRowCount + 2, 500);
		addLabel(sheet3, 0, projectRowCount + 2, pvalues.getProjectLicense(), style.sh1tableFormat1);
		int totalCount = idvalues.getfileTotalCount() - idvalues.getIgnoredCount() - idvalues.getIdentifiedFileCount();		
		double percent = ((double) totalCount / (double)(idvalues.getfileTotalCount() - idvalues.getIgnoredCount()))* 100;
		addLabel(sheet3, 1, projectRowCount + 2, "N/A", style.sh1tableFormat1);
		addLabel(sheet3, 2, projectRowCount + 2, Integer.toString(totalCount), style.sh1tableFormat1);
		addLabel(sheet3, 3, projectRowCount + 2, String.format("%.2f", percent) + "%", style.sh1tableFormat1);
		
		sheet3.setRowView(projectRowCount + 3, 500);
		addLabel(sheet3, 0, projectRowCount + 3, "합계", style.sh1tableFormat1);		
		addLabel(sheet3, 1, projectRowCount + 3, "", style.sh1tableFormat1);
		sheet3.mergeCells(0, projectRowCount + 3, 1, projectRowCount + 3);
		addLabel(sheet3, 2, projectRowCount + 3, Integer.toString(idvalues.getfileTotalCount() - idvalues.getIgnoredCount()), style.sh1tableFormat1);
		addLabel(sheet3, 3, projectRowCount + 3, "100%", style.sh1tableFormat1);
	}
}