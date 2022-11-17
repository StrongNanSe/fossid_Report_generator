package fossid.report.excel;

import jxl.write.WritableSheet;
import jxl.write.WriteException;

import fossid.report.attribute.GetProjectLicenseConflict;
import fossid.report.attribute.SetCompareLicenseAttribute;
import fossid.report.values.BillOfMaterialsValues;
import fossid.report.values.IdentifiedFilesValues;
import fossid.report.values.ProjectValues;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CreateSheet4 extends CreateSheet{
	private final Logger logger = LogManager.getLogger(CreateSheet4.class);

	public CreateSheet4(){
		super();
	}	
	
	BillOfMaterialsValues bomValues = BillOfMaterialsValues.getInstance();
	ExcelValues excelVal = ExcelValues.getInstance();
	
	GetProjectLicenseConflict projectLicenseConflict = new GetProjectLicenseConflict();

	WritableSheet sheet4 = excelVal.getSheet4();	
	
	public void writeSheet() throws WriteException {
		logger.info("Creating sheet 5..");

		IdentifiedFilesValues idValues = IdentifiedFilesValues.getInstance();
		ProjectValues pValues = ProjectValues.getInstance();
		
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
		String key;
		int value;
		int loopCount = 0;
		
		for(int i = 0; i < idValues.getFilepath().size(); i++) {
			if(loopCount%200 == 0) {
				logger.info(".");
        	}
        	loopCount++;
			
			if(!idValues.getComponentName().get(i).equals(pValues.getProjectName())) {
				
				// this set affect column 0(license conflict) and 6(Patent retaliation clause) 
				SetCompareLicenseAttribute.setCompareAttribute(idValues.getComponentLicenseName().get(i));
				
				// value: 0 - no conflict / 1 - project license conflict / 2 - component conflict
				projectLicenseConflict.projectLicenseConflict(idValues.getComponentLicenseName().get(i));
				
				key = idValues.getComponentLicenseName().get(i);
				value =	bomValues.getProjectLicenseConflict().get(key);
				if(value == 0) {
					addLabel(sheet4, 0, 2+row, "충돌없음", style.noConflict);
				} else if (value == 1) {
					addLabel(sheet4, 0, 2+row, "프로젝트에 선언된 라이선스와 충돌", style.projectConflict);
				}
				
				if(bomValues.getComponentConflictLicense().contains(idValues.getComponentLicenseName().get(i))) {
					addLabel(sheet4, 0, 2+row, "다른 컴포넌트 라이선스와 충돌", style.componentConflict);
				}
				
				addLabel(sheet4, 1, 2+row, idValues.getFilepath().get(i), style.sh1tableFormat5);
				addLabel(sheet4, 2, 2+row, idValues.getComponentName().get(i), style.sh1tableFormat1);
				addLabel(sheet4, 3, 2+row, idValues.getComponentVersion().get(i), style.sh1tableFormat1);
				addLabel(sheet4, 4, 2+row, idValues.getComponentLicenseName().get(i), style.sh1tableFormat1);
				addLabel(sheet4, 5, 2+row, idValues.getMatchType().get(i), style.sh1tableFormat1);
				addLabel(sheet4, 6, 2+row, idValues.getComment().get(i), style.sh1tableFormat1);
				row++;
			}	
		}
		logger.info("");
	}
}