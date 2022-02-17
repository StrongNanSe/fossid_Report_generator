package fossid.report.excel;

import jxl.write.WritableSheet;
import jxl.write.WriteException;

import fossid.report.attribute.getProjectLicenseConflict;
import fossid.report.attribute.setCompareLicenseAttribute;
import fossid.report.excel.ExcelValues;
import fossid.report.values.billofmaterialsValues;
import fossid.report.values.identifiedFilesValues;
import fossid.report.values.projectValues;

public class CreateSheet4 extends CreateSheet{
	
	public CreateSheet4(){
		super();
	}	
	
	billofmaterialsValues bomValues = billofmaterialsValues.getInstance();
	ExcelValues excelVal = ExcelValues.getInstance();
	
	getProjectLicenseConflict projectLicenseConflict = new getProjectLicenseConflict();	
	setCompareLicenseAttribute setCompareLicenseAttribute = new setCompareLicenseAttribute();	
	
	WritableSheet sheet4 = excelVal.getSheet4();	
	
	public void writeSheet() throws WriteException {
		System.out.print("Creating sheet 5..");
		
		identifiedFilesValues idValues = identifiedFilesValues.getInstance();
		projectValues pvalues = projectValues.getInstance();
		
		sheet4 = excelVal.getWB().createSheet("5. 상세파일 정보", 4);
		
		for(int i=0; i < 9; i++) {	
			if(i==1) {
				sheet4.setColumnView(i, 60);
			}else {
				sheet4.setColumnView(i, 28);	
			}			
		}
		
		sheet4.setRowView(0, 800);
		addLabel(sheet4, 0, 0, "5. 상세파일 정보", style.sh1TitleFormat);
		
		sheet4.setRowView(1, 700);
		addLabel(sheet4, 0, 1, "라이선스 충돌 현황", style.sh1tableFormat2);		
		addLabel(sheet4, 1, 1, "파일/폴더", style.sh1tableFormat2);
		addLabel(sheet4, 2, 1, "컴포넌트명", style.sh1tableFormat2);
		addLabel(sheet4, 3, 1, "버전", style.sh1tableFormat2);
		addLabel(sheet4, 4, 1, "컴포넌트 라이선스", style.sh1tableFormat2);
		addLabel(sheet4, 5, 1, "결합 형태", style.sh1tableFormat2);
		addLabel(sheet4, 6, 1, "코멘트", style.sh1tableFormat2);
		
		int row = 0;
		String key = null;
		int value = 0;
		int loopCount = 0;
		
		for(int i=0; i < idValues.getfilepath().size(); i++) {
			if(loopCount%200 == 0) {
        		System.out.print(".");
        	}            	
        	loopCount++;
			
			if(!idValues.getcomponenetName().get(i).toString().equals(pvalues.getProjectName())) {
				
				// this set affect column 0(license conflict) and 6(Patent retaliation clause) 
				setCompareLicenseAttribute.setCompareAttribute(idValues.getcomponentLicenseName().get(i));			
				
				// value: 0 - no conflict / 1 - project license conflict / 2 - component conflict
				projectLicenseConflict.projectLicenseConflict(idValues.getcomponentLicenseName().get(i));
				
				key = idValues.getcomponentLicenseName().get(i);
				value =	bomValues.getProjectLicenseConflict().get(key);
				if(value == 0) {
					addLabel(sheet4, 0, 2+row, "충돌없음", style.noConflict);
				} else if (value == 1) {
					addLabel(sheet4, 0, 2+row, "프로젝트에 선언된 라이선스와 충돌", style.projectConflict);
				}
				
				if(bomValues.getcomponentConflictLicense().contains(idValues.getcomponentLicenseName().get(i))) {
					addLabel(sheet4, 0, 2+row, "다른 컴포넌트 라이선스와 충돌", style.componentConflict);
				}
				
				addLabel(sheet4, 1, 2+row, idValues.getfilepath().get(i), style.sh1tableFormat5);
				addLabel(sheet4, 2, 2+row, idValues.getcomponenetName().get(i), style.sh1tableFormat1);
				addLabel(sheet4, 3, 2+row, idValues.getcomponentVersion().get(i), style.sh1tableFormat1);
				addLabel(sheet4, 4, 2+row, idValues.getcomponentLicenseName().get(i), style.sh1tableFormat1);
				addLabel(sheet4, 5, 2+row, idValues.getmatchType().get(i), style.sh1tableFormat1);
				addLabel(sheet4, 6, 2+row, idValues.getcommnet().get(i), style.sh1tableFormat1);				
				row++;
			}	
		}		
		
		System.out.println();
	}
}