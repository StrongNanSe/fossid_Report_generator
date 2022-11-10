package fossid.report.excel;

import fossid.report.attribute.GetComponentLicenseConflict;
import fossid.report.attribute.GetProjectLicenseConflict;
import fossid.report.attribute.SetCompareComponentLicenseAttribute;
import fossid.report.attribute.SetCompareLicenseAttribute;
import fossid.report.getdata.GetNumberOfIgnored;
import fossid.report.values.BillOfMaterialsValues;
import fossid.report.values.IdentifiedFilesValues;
import fossid.report.values.ProjectValues;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


public class CreateSheet0 extends CreateSheet{
	private final Logger logger = LogManager.getLogger(CreateSheet0.class);
	public CreateSheet0(){
		super();
	}

	ProjectValues pValues = ProjectValues.getInstance();
	IdentifiedFilesValues idValues = IdentifiedFilesValues.getInstance();
	ExcelValues excelVal = ExcelValues.getInstance();
	WritableSheet sheet0 = excelVal.getSheet0();
	
	GetNumberOfIgnored ignoredNumber = new GetNumberOfIgnored();
	
	int componentConflictCount = 0;
	
	public void writeSheet() throws WriteException {
		logger.info("Creating sheet 1..");

		sheet0 = excelVal.getWB().createSheet("1. 라이선스 검증결과서", 0);
		String date = new DateTime().toString(DateTimeFormat.forPattern("yyyy. MM. dd"));
		
		// to set Project and Component Conflict
		getComponentConflictCount();
		
		// to set ignored file count
		ignoredNumber.setNumber();
		
		try {
			sheet0.setColumnView(0, 17);
			sheet0.setColumnView(1, 17);
			sheet0.setColumnView(2, 11);
			sheet0.setColumnView(3, 11);
			sheet0.setColumnView(4, 11);
			sheet0.setColumnView(5, 11);
			sheet0.setColumnView(6, 11);
			sheet0.setColumnView(7, 11);			
			sheet0.setColumnView(8, 11);
						
			sheet0.mergeCells(0, 0, 8, 0);
			
			sheet0.setRowView(1, 1000);
			sheet0.mergeCells(0, 1, 8, 1);
			addLabel(sheet0, 0, 1, "오픈소스SW 라이선스 검증보고서", style.sh0TitleFormat1);
			
			sheet0.mergeCells(0, 2, 8, 2);
			
			sheet0.mergeCells(0, 3, 8, 3);
			addLabel(sheet0, 0, 3, "본 SW결과물(프로젝트)에 대한 오픈소스SW 라이선스 검증 결과입니다.", style.sh0RegularFormat);
		
			sheet0.setRowView(4, 800);
			sheet0.mergeCells(0, 4, 8, 4);
			addLabel(sheet0, 0, 4, "1. FOSSID 프로젝트 정보", style.sh0TitleFormat2);
			
			sheet0.setRowView(5, 500);
			sheet0.mergeCells(0, 5, 1, 5);
			addLabel(sheet0, 0, 5, "프로젝트 및 스캔 명", style.sh0tableFormat);

			sheet0.mergeCells(2, 5, 8, 5);
			addLabel(sheet0, 2, 5, pValues.getProjectName() + "  /  " + pValues.getVersionName(), style.sh0tableFormat);
			
			sheet0.setRowView(6, 500);
			sheet0.mergeCells(0, 6, 1, 6);
			addLabel(sheet0, 0, 6, "프로젝트 공개 여부", style.sh0tableFormat);
						
			sheet0.mergeCells(2, 6, 4, 6);
			addLabel(sheet0, 2, 6, "공개(    )     비공개(    )", style.sh0tableFormat);			
						
			sheet0.mergeCells(5, 6, 6, 6);
			addLabel(sheet0, 5, 6, "보고서 생성일", style.sh0tableFormat);
			
			sheet0.mergeCells(7, 6, 8, 6);
			addLabel(sheet0, 7, 6, date, style.sh0tableFormat);
			
			sheet0.setRowView(7, 500);
			sheet0.mergeCells(0, 7, 1, 7);
			addLabel(sheet0, 0, 7, "프로젝트 라이선스", style.sh0tableFormat);
			
			sheet0.mergeCells(2, 7, 8, 7);
			addLabel(sheet0, 2, 7, pValues.getProjectLicense(), style.sh0tableFormat);
			
				
			sheet0.setRowView(8, 800);
			sheet0.mergeCells(0, 8, 8, 8);
			addLabel(sheet0, 0, 8, "2. 분석 및 검증 정보", style.sh0TitleFormat2);
			
			sheet0.setRowView(9, 500);
			sheet0.mergeCells(0, 9, 1, 9);
			addLabel(sheet0, 0, 9, "분석된 파일 수", style.sh0tableFormat);

			sheet0.mergeCells(2, 9, 8, 9);
			addLabel(sheet0, 2, 9, idValues.getfileTotalCount() + "개", style.sh0tableFormat);
			
			sheet0.setRowView(10, 500);
			sheet0.mergeCells(0, 10, 1, 10);
			addLabel(sheet0, 0, 10, "분석된 바이트 수", style.sh0tableFormat);
			
			sheet0.mergeCells(2, 10, 8, 10);						
			if(idValues.getfileTotalSize() / 1024 > 1048576) {
				addLabel(sheet0, 2, 10, String.format("%1$d GiB (%2$,d MiB)", 
						idValues.getfileTotalSize() / 1024 / 1024 / 1024, idValues.getfileTotalSize() / 1024 / 1024), style.sh0tableFormat);
			} else if(idValues.getfileTotalSize() / 1024 < 1048576){
				addLabel(sheet0, 2, 10, String.format("%1$d MiB (%2$,d KiB)", 
						idValues.getfileTotalSize() / 1024 / 1024, idValues.getfileTotalSize() / 1024), style.sh0tableFormat);
			}			
			
			sheet0.setRowView(11, 500);
			sheet0.mergeCells(0, 11, 1, 11);
			addLabel(sheet0, 0, 11, "검증대상 파일 수", style.sh0tableFormat);
			
			int ignoredNum = idValues.getfileTotalCount() - idValues.getIgnoredCount();
			sheet0.mergeCells(2, 11, 8, 11);			
			addLabel(sheet0, 2, 11, Integer.toString(ignoredNum) + "개", style.sh0tableFormat);			
			
			sheet0.setRowView(12, 500);
			sheet0.mergeCells(0, 12, 1, 12);
			addLabel(sheet0, 0, 12, "오픈소스SW 사용 파일 수", style.sh0tableFormat);
			
			double percent = ((double) idValues.getpendingFileCount() / (double)ignoredNum )* 100;
			sheet0.mergeCells(2, 12, 8, 12);
			addLabel(sheet0, 2, 12, Integer.toString(idValues.getpendingFileCount()) + "개"
					+ " ( " + String.format("%.2f", percent) + "% )", style.sh0tableFormat);			
			
			sheet0.setRowView(13, 500);
			sheet0.mergeCells(0, 13, 1, 13);
			addLabel(sheet0, 0, 13, "준법성 위반 파일 수", style.sh0tableFormat);
			
			//percent = ((double)idValues.getProjectConflictFileCount() / (double)idValues.getFileTotalCount() )* 100;
			percent = ((double) idValues.getprojectConflictFileCount() / (double)ignoredNum )* 100;
			sheet0.mergeCells(2, 13, 8, 13);
			addLabel(sheet0, 2, 13, Integer.toString(idValues.getprojectConflictFileCount()) + "개"
					+ " ( " + String.format("%.2f", percent) + "% )", style.sh0tableFormat);
			
			sheet0.setRowView(14, 500);
			sheet0.mergeCells(0, 14, 1, 14);
			addLabel(sheet0, 0, 14, "준법성 주의 파일 수", style.sh0tableFormat);
						
			//percent = ((double)componentConflictCount / (double)idValues.getFileTotalCount() )* 100;
			percent = ((double)componentConflictCount / (double)ignoredNum )* 100;
			sheet0.mergeCells(2, 14, 8, 14);
			addLabel(sheet0, 2, 14, Integer.toString(componentConflictCount) + "개"
					+ " ( " + String.format("%.2f", percent) + "% )", style.sh0tableFormat);
			
			idValues.setAnalyzedFileCount(ignoredNum);
					
		} catch (RowsExceededException e) {
			logger.error("Exception Message", e);
		}
	}
	
	private void getComponentConflictCount() {
		
		BillOfMaterialsValues bomValues = BillOfMaterialsValues.getInstance();
		GetProjectLicenseConflict projectLicenseConflict = new GetProjectLicenseConflict();
		GetComponentLicenseConflict componentLicenseConflict = new GetComponentLicenseConflict();

		String key;
		int value;

		for(int i = 0; i < bomValues.getUComponentName().size(); i++) {

			if(i%5 == 0) {
				logger.info(".");
        	}

			// this set affect column 0(license conflict) and 6(Patent retaliation clause) 
			SetCompareLicenseAttribute.setCompareAttribute(bomValues.getUComponentLicenseName().get(i));
			// value: 0 - no conflict / 1 - project license conflict
			projectLicenseConflict.projectLicenseConflict(bomValues.getUComponentLicenseName().get(i));
			
			key = bomValues.getUComponentLicenseName().get(i);
			value =	bomValues.getProjectLicenseConflict().get(key);
			
			if(value == 0) {
				String componentKey = bomValues.getUComponentName().get(i)+ bomValues.getUComponentVersion().get(i);
				
				//set component list without project license conflict
				bomValues.setLicenseWithoutProjectConflict(bomValues.getUComponentLicenseName().get(i));
				
				//set component list without project license conflict with value 0
				bomValues.setComponentLicenseConflict(bomValues.getUComponentLicenseName().get(i), 0);
				
				SetCompareLicenseAttribute.setCompareAttribute(bomValues.getUComponentLicenseName().get(i));
				for(int j = 0; j < bomValues.getUComponentLicenseName().size(); j++) {
					// set licenses to compare all component license
					SetCompareComponentLicenseAttribute.setCompareAttribute(bomValues.getUComponentLicenseName().get(j));
								
					componentLicenseConflict.componentLicenseConflict(bomValues.getUComponentLicenseName().get(i));
				}
				
				// value: 0 - no conflict / 1 - component license conflict
				key = bomValues.getUComponentLicenseName().get(i);
				value =	bomValues.getComponentLicenseConflict().get(key);
				if (value == 1) {
					componentConflictCount = componentConflictCount + bomValues.getUComponentFileCount().get(componentKey);
				}
				
			} else if (value == 1) {	
				// set licenses with project license conflict
				bomValues.setLicenseWithProjectConflict(bomValues.getUComponentLicenseName().get(i));
			}
		}
	}
}

