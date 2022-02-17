package fossid.report.excel;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import fossid.report.attribute.getComponetLicenseConflict;
import fossid.report.attribute.getProjectLicenseConflict;
import fossid.report.attribute.setCompareComponentLicenseAttribute;
import fossid.report.attribute.setCompareLicenseAttribute;
import fossid.report.getdata.getNumberofIgnored;
import fossid.report.values.billofmaterialsValues;
import fossid.report.values.compareLicenseAttributeValues;
import fossid.report.values.identifiedFilesValues;
import fossid.report.values.loginValues;
import fossid.report.values.projectValues;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class CreateSheet0 extends CreateSheet{
	
	public CreateSheet0(){
		super();
	}

	loginValues lvalues = loginValues.getInstance();
	projectValues pvalues = projectValues.getInstance();
	identifiedFilesValues idvalues = identifiedFilesValues.getInstance();
	ExcelValues excelVal = ExcelValues.getInstance();
	WritableSheet sheet0 = excelVal.getSheet0();
	
	getNumberofIgnored ignoredNumber = new getNumberofIgnored();	
	
	int componentConflictCount = 0;
	
	public void writeSheet() throws WriteException {
		System.out.print("Creating sheet 1..");
		
		sheet0 = excelVal.getWB().createSheet("1. 라이선스 검증결과서", 0);
		String date = new DateTime().toString(DateTimeFormat.forPattern("yyyy. MM. dd"));
		
		// to set Project and Componet Conflict
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
			addLabel(sheet0, 2, 5, pvalues.getProjectName() + "  /  " + pvalues.getVersionName(), style.sh0tableFormat);
			
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
			addLabel(sheet0, 2, 7, pvalues.getProjectLicense(), style.sh0tableFormat);
			
				
			sheet0.setRowView(8, 800);
			sheet0.mergeCells(0, 8, 8, 8);
			addLabel(sheet0, 0, 8, "2. 분석 및 검증 정보", style.sh0TitleFormat2);
			
			sheet0.setRowView(9, 500);
			sheet0.mergeCells(0, 9, 1, 9);
			addLabel(sheet0, 0, 9, "분석된 파일 수", style.sh0tableFormat);

			sheet0.mergeCells(2, 9, 8, 9);
			addLabel(sheet0, 2, 9, idvalues.getfileTotalCount() + "개", style.sh0tableFormat);
			
			sheet0.setRowView(10, 500);
			sheet0.mergeCells(0, 10, 1, 10);
			addLabel(sheet0, 0, 10, "분석된 바이트 수", style.sh0tableFormat);
			
			sheet0.mergeCells(2, 10, 8, 10);						
			if(idvalues.getfileTotalSize() / 1024 > 1048576) {
				addLabel(sheet0, 2, 10, String.format("%1$d GiB (%2$,d MiB)", 
						idvalues.getfileTotalSize() / 1024 / 1024 / 1024, idvalues.getfileTotalSize() / 1024 / 1024), style.sh0tableFormat);
			} else if(idvalues.getfileTotalSize() / 1024 < 1048576){
				addLabel(sheet0, 2, 10, String.format("%1$d MiB (%2$,d KiB)", 
						idvalues.getfileTotalSize() / 1024 / 1024, idvalues.getfileTotalSize() / 1024), style.sh0tableFormat);
			}			
			
			sheet0.setRowView(11, 500);
			sheet0.mergeCells(0, 11, 1, 11);
			addLabel(sheet0, 0, 11, "검증대상 파일 수", style.sh0tableFormat);
			
			int ignoredNum = idvalues.getfileTotalCount() - idvalues.getIgnoredCount();
			sheet0.mergeCells(2, 11, 8, 11);			
			addLabel(sheet0, 2, 11, Integer.toString(ignoredNum) + "개", style.sh0tableFormat);			
			
			sheet0.setRowView(12, 500);
			sheet0.mergeCells(0, 12, 1, 12);
			addLabel(sheet0, 0, 12, "오픈소스SW 사용 파일 수", style.sh0tableFormat);
			
			double percent = ((double)idvalues.getpendingFileCount() / (double)ignoredNum )* 100;			
			sheet0.mergeCells(2, 12, 8, 12);
			addLabel(sheet0, 2, 12, Integer.toString(idvalues.getpendingFileCount()) + "개"
					+ " ( " + String.format("%.2f", percent) + "% )", style.sh0tableFormat);			
			
			sheet0.setRowView(13, 500);
			sheet0.mergeCells(0, 13, 1, 13);
			addLabel(sheet0, 0, 13, "준법성 위반 파일 수", style.sh0tableFormat);
			
			//percent = ((double)idvalues.getprojectConflictFileCount() / (double)idvalues.getfileTotalCount() )* 100;		
			percent = ((double)idvalues.getprojectConflictFileCount() / (double)ignoredNum )* 100;
			sheet0.mergeCells(2, 13, 8, 13);
			addLabel(sheet0, 2, 13, Integer.toString(idvalues.getprojectConflictFileCount()) + "개"
					+ " ( " + String.format("%.2f", percent) + "% )", style.sh0tableFormat);
			
			sheet0.setRowView(14, 500);
			sheet0.mergeCells(0, 14, 1, 14);
			addLabel(sheet0, 0, 14, "준법성 주의 파일 수", style.sh0tableFormat);
						
			//percent = ((double)componentConflictCount / (double)idvalues.getfileTotalCount() )* 100;		
			percent = ((double)componentConflictCount / (double)ignoredNum )* 100;
			sheet0.mergeCells(2, 14, 8, 14);
			addLabel(sheet0, 2, 14, Integer.toString(componentConflictCount) + "개"
					+ " ( " + String.format("%.2f", percent) + "% )", style.sh0tableFormat);
			
			idvalues.setAnalyzedFileCount(ignoredNum);
					
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}				
	}
	
	private void getComponentConflictCount() {
		
		billofmaterialsValues bomValues = billofmaterialsValues.getInstance();
		getProjectLicenseConflict projectLicenseConflict = new getProjectLicenseConflict();
		getComponetLicenseConflict componentLicenseConflict = new getComponetLicenseConflict();	
		setCompareLicenseAttribute setCompareLicenseAttribute = new setCompareLicenseAttribute();
		setCompareComponentLicenseAttribute setcompareComponentLicenseAttribute = new setCompareComponentLicenseAttribute();		
		compareLicenseAttributeValues compareLicenseAttributes = compareLicenseAttributeValues.getInstance();
				
		String key = null;
		int value = 0;
		int loopCount = 0;
		
		for(int i = 0; i < bomValues.getUcomponentName().size(); i++) {

			if(loopCount%5 == 0) {
        		System.out.print(".");
        	}            	
        	loopCount++;
			
			// this set affect column 0(license conflict) and 6(Patent retaliation clause) 
			setCompareLicenseAttribute.setCompareAttribute(bomValues.getUcomponentLicenseName().get(i));			
			// value: 0 - no conflict / 1 - project license conflict
			projectLicenseConflict.projectLicenseConflict(bomValues.getUcomponentLicenseName().get(i));
			
			key = bomValues.getUcomponentLicenseName().get(i);
			value =	bomValues.getProjectLicenseConflict().get(key);
			
			if(value == 0) {
				String componentKey = bomValues.getUcomponentName().get(i)+ bomValues.getUcomponentVersion().get(i);
				
				//set component list without project license conflict
				bomValues.setLicensetwithoutProjectConflict(bomValues.getUcomponentLicenseName().get(i));
				
				//set component list without project license conflict with value 0
				bomValues.setComponentLicenseConflict(bomValues.getUcomponentLicenseName().get(i), 0);
				
				setCompareLicenseAttribute.setCompareAttribute(bomValues.getUcomponentLicenseName().get(i));
				for(int j = 0; j < bomValues.getUcomponentLicenseName().size(); j++) {				
					// set licenses to compare all component license
					setcompareComponentLicenseAttribute.setCompareAttribute(bomValues.getUcomponentLicenseName().get(j));
								
					componentLicenseConflict.componentLicenseConflict(bomValues.getUcomponentLicenseName().get(i));				
				}
				
				// value: 0 - no conflict / 1 - component license conflict
				key = bomValues.getUcomponentLicenseName().get(i);
				value =	bomValues.getComponentLicenseConflict().get(key);
				if (value == 1) {
					componentConflictCount = componentConflictCount + bomValues.getUcomponentFileCount().get(componentKey);
				}
				
			} else if (value == 1) {	
				// set licenses with project license conflict
				bomValues.setLicensetwithProjectConflict(bomValues.getUcomponentLicenseName().get(i));
			}
		}
		System.out.println();
	}
}

