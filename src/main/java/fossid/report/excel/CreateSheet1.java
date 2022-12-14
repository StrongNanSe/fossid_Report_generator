package fossid.report.excel;

import fossid.report.attribute.SetCompareLicenseAttribute;
import fossid.report.values.BillOfMaterialsValues;
import fossid.report.values.CompareLicenseAttributeValues;
import fossid.report.values.ProjectLicenseAttributeValues;
import fossid.report.values.ProjectValues;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class CreateSheet1 extends CreateSheet{
	private final Logger logger = LogManager.getLogger(CreateSheet1.class);
	public CreateSheet1(){
		super();
	}
	
	
	ExcelValues excelVal = ExcelValues.getInstance();
	ProjectValues pValues = ProjectValues.getInstance();
	BillOfMaterialsValues bomValues = BillOfMaterialsValues.getInstance();
	ProjectLicenseAttributeValues atValues = ProjectLicenseAttributeValues.getInstance();
	CompareLicenseAttributeValues getCompareLicenseAttribute = CompareLicenseAttributeValues.getInstance();
	WritableSheet sheet1 = excelVal.getSheet1();
		

	public void writeSheet() throws WriteException {
		logger.info("Creating sheet 2..");

		sheet1 = excelVal.getWB().createSheet("2. 검증의견", 1);
		
		sheet1.setColumnView(8, 15);
		sheet1.setColumnView(9, 33);
		
		sheet1.setRowView(0, 800);
		addLabel(sheet1, 0, 0, "2. 오픈소스SW 라이선스 검증 의견", style.sh1TitleFormat);
		
		sheet1.setRowView(1, 500);
		sheet1.mergeCells(0, 1, 2, 1);
		addLabel(sheet1, 0, 1, "프로젝트 라이선스", style.sh1tableFormat2);
		
		sheet1.setRowView(1, 500);
		sheet1.mergeCells(3, 1, 9, 1);
		addLabel(sheet1, 3, 1, pValues.getProjectLicense(), style.sh1tableFormat1);
		
		sheet1.setRowView(3, 500);
		sheet1.mergeCells(0, 3, 1, 3);
		addBlank(sheet1, 0, 3, style.sh1tableFormat3);
		addLabel(sheet1, 2, 3, "충돌의무사항", style.sh1tableFormat4);
		
		sheet1.setRowView(5, 700);
		sheet1.mergeCells(0, 5, 8, 5);
		addLabel(sheet1, 0, 5, "라이선스 의무사항", style.sh1tableFormat2);		
		addLabel(sheet1, 9, 5, "프로젝트 라이선스", style.sh1tableFormat2);
		
		sheet1.setRowView(6, 700);
		addLabel(sheet1, 0, 6, "No.", style.sh1tableFormat2);
		sheet1.mergeCells(1, 6, 8, 6);
		addLabel(sheet1, 1, 6, "의무사항", style.sh1tableFormat2);		
		addLabel(sheet1, 9, 6, pValues.getProjectLicense(), style.sh1tableFormat2);
		
		sheet1.setRowView(7, 500);
		addLabel(sheet1, 0, 7, "1", style.sh1tableFormat1);
		sheet1.mergeCells(1, 7, 8, 7);
		addLabel(sheet1, 1, 7, "배포 (바이너리 파일 배포에 의해서만 부여되는 의무사항)", style.sh1tableFormat5);
		if(atValues.getPjAttribute1().toString().equals("X")) {
			addLabel(sheet1, 9, 7, "X", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute1().toString().equals("O")){
			addLabel(sheet1, 9, 7, "O", style.sh1tableFormat1);
		}		
		
		sheet1.setRowView(8, 500);
		addLabel(sheet1, 0, 8, "2", style.sh1tableFormat1);
		sheet1.mergeCells(1, 8, 8, 8);		
		addLabel(sheet1, 1, 8, "소스코드 공개/강제적 공유 의무사항", style.sh1tableFormat5);
		if(atValues.getPjAttribute2().toString().equals("O")) {
			addLabel(sheet1, 9, 8, "O", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute2().toString().equals("M")){
			addLabel(sheet1, 9, 8, "△", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute2().toString().equals("X")){
			addLabel(sheet1, 9, 8, "X", style.sh1tableFormat1);
		}
		
		sheet1.setRowView(9, 500);
		addLabel(sheet1, 0, 9, "3", style.sh1tableFormat1);
		sheet1.mergeCells(1, 9, 8, 9);
		addLabel(sheet1, 1, 9, "복제 및 수정 권한 허용", style.sh1tableFormat5);
		if(atValues.getPjAttribute3().toString().equals("O")) {
			addLabel(sheet1, 9, 9, "O", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute3().toString().equals("M")){
			addLabel(sheet1, 9, 9, "△", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute3().toString().equals("X")){
			addLabel(sheet1, 9, 9, "X", style.sh1tableFormat1);
		}		

		sheet1.setRowView(10, 500);
		addLabel(sheet1, 0, 10, "4", style.sh1tableFormat1);
		sheet1.mergeCells(1, 10, 8, 10);
		addLabel(sheet1, 1, 10, "역설계 권한 허용", style.sh1tableFormat5);
		if(atValues.getPjAttribute4().toString().equals("O")) {
			addLabel(sheet1, 9, 10, "O", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute4().toString().equals("M")){
			addLabel(sheet1, 9, 10, "△", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute4().toString().equals("X")){
			addLabel(sheet1, 9, 10, "X", style.sh1tableFormat1);
		}
		
		sheet1.setRowView(11, 500);
		addLabel(sheet1, 0, 11, "5", style.sh1tableFormat1);
		sheet1.mergeCells(1, 11, 8, 11);
		addLabel(sheet1, 1, 11, "차별적 제한조건 제한", style.sh1tableFormat5);
		if(atValues.getpjAttribute5().toString().equals("O")) {
			addLabel(sheet1, 9, 11, "O", style.sh1tableFormat1);
		} else if(atValues.getpjAttribute5().toString().equals("M")){
			addLabel(sheet1, 9, 11, "△", style.sh1tableFormat1);
		} else if(atValues.getpjAttribute5().toString().equals("X")){
			addLabel(sheet1, 9, 11, "X", style.sh1tableFormat1);
		}
		
		sheet1.setRowView(12, 500);
		addLabel(sheet1, 0, 12, "6", style.sh1tableFormat1);
		sheet1.mergeCells(1, 12, 8, 12);
		addLabel(sheet1, 1, 12, "특허 무상실시권 부여 (특허소송을 제기하지 않음, 제기시 라이선스 종료)", style.sh1tableFormat5);
		if(atValues.getPjAttribute6().toString().equals("X")) {
			addLabel(sheet1, 9, 12, "X", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute6().toString().equals("O")){
			addLabel(sheet1, 9, 12, "O", style.sh1tableFormat1);
		}	
		
		sheet1.setRowView(13, 500);
		addLabel(sheet1, 0, 13, "7", style.sh1tableFormat1);
		sheet1.mergeCells(1, 13, 8, 13);		
		addLabel(sheet1, 1, 13, "기술적 보호조치의 보호금지, 설치정보 제공", style.sh1tableFormat5);
		if(atValues.getPjAttribute7().toString().equals("X")) {
			addLabel(sheet1, 9, 13, "X", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute7().toString().equals("O")){
			addLabel(sheet1, 9, 13, "O", style.sh1tableFormat1);
		}
		
		sheet1.setRowView(14, 500);
		addLabel(sheet1, 0, 14, "8", style.sh1tableFormat1);
		sheet1.mergeCells(1, 14, 8, 14);
		addLabel(sheet1, 1, 14, "고지 (라이선스 사본 포함)", style.sh1tableFormat5);
		if(atValues.getPjAttribute8().toString().equals("X")) {
			addLabel(sheet1, 9, 14, "X", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute8().toString().equals("O")){
			addLabel(sheet1, 9, 14, "O", style.sh1tableFormat1);
		}
		
		sheet1.setRowView(15, 500);
		addLabel(sheet1, 0, 15, "9", style.sh1tableFormat1);
		sheet1.mergeCells(1, 15, 8, 15);		
		addLabel(sheet1, 1, 15, "변경사항 고지 (라이선스 사본 포함)", style.sh1tableFormat5);		
		if(atValues.getPjAttribute9().toString().equals("X")) {
			addLabel(sheet1, 9, 15, "X", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute9().toString().equals("O")){
			addLabel(sheet1, 9, 15, "O", style.sh1tableFormat1);
		}
				
		sheet1.setRowView(16, 500);
		addLabel(sheet1, 0, 16, "10", style.sh1tableFormat1);
		sheet1.mergeCells(1, 16, 8, 16);
		addLabel(sheet1, 1, 16, "보증의 부인 및 책임의 제한", style.sh1tableFormat5);
		if(atValues.getPjAttribute10().toString().equals("X")) {
			addLabel(sheet1, 9, 16, "X", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute10().toString().equals("O")){
			addLabel(sheet1, 9, 16, "O", style.sh1tableFormat1);
		}	
				
		sheet1.setRowView(17, 500);
		addLabel(sheet1, 0, 17, "11", style.sh1tableFormat1);
		sheet1.mergeCells(1, 17, 8, 17);
		addLabel(sheet1, 1, 17, "광고/홍보시 배포자,저작자, 특정상표사용 금지", style.sh1tableFormat5);		
		if(atValues.getPjAttribute11().toString().equals("X")) {
			addLabel(sheet1, 9, 17, "X", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute11().toString().equals("O")){
			addLabel(sheet1, 9, 17, "O", style.sh1tableFormat1);
		}
		
		sheet1.setRowView(18, 500);
		addLabel(sheet1, 0, 18, "12", style.sh1tableFormat1);
		sheet1.mergeCells(1, 18, 8, 18);
		addLabel(sheet1, 1, 18, "원코드와 동일조건 허가", style.sh1tableFormat5);
		if(atValues.getPjAttribute12().toString().equals("X")) {
			addLabel(sheet1, 9, 18, "X", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute12().toString().equals("O")){
			addLabel(sheet1, 9, 18, "O", style.sh1tableFormat1);
		}
				
		sheet1.setRowView(19, 1000);
		addLabel(sheet1, 0, 19, "13", style.sh1tableFormat1);
		sheet1.mergeCells(1, 19, 8, 19);
		addLabel(sheet1, 1, 19, "라이선스 적용 범위", style.sh1tableFormat5);
		if(atValues.getPjAttribute13().toString().equals("X")) {
			addLabel(sheet1, 9, 19, "X", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute13().toString().equals("MPL")){
			addLabel(sheet1, 9, 19, "파일\n(per MPL)", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute13().toString().equals("EPL_CPL")){
			addLabel(sheet1, 9, 19, "모듈\n(per CPL)", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute13().toString().equals("LGPL")){
			addLabel(sheet1, 9, 19, "동적 라이브러리\n(per LGPL)", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute13().toString().equals("GPL")){
			addLabel(sheet1, 9, 19, "코드 기반의 산출물\n(per GPL)", style.sh1tableFormat1);
		} else if(atValues.getPjAttribute13().toString().equals("SLEEPYCAT")){
			addLabel(sheet1, 9, 19, "수반 소프트웨어\n(per SleepyCat)", style.sh1tableFormat1);
		}

		int mergeCount = 9;	int column = 0;	
		ArrayList<String> checkLicense = new ArrayList<String>();
		for(int i = 0; i < bomValues.getLicenseWithProjectConflict().size(); i++) {
			if(!checkLicense.contains(bomValues.getLicenseWithProjectConflict().get(i))) {
				checkLicense.add(bomValues.getLicenseWithProjectConflict().get(i));
				sheet1.setColumnView(10 + column, 33);
				addLabel(sheet1, 10 + column, 6, bomValues.getLicenseWithProjectConflict().get(i), style.sh1tableFormat2);
				
				// set component Attribute
				SetCompareLicenseAttribute.setCompareAttribute(bomValues.getLicenseWithProjectConflict().get(i));

				if(getCompareLicenseAttribute.getCoAttribute1().toString().equals(atValues.getPjAttribute1().toString())) {
					addLabel(sheet1, 10 + column, 7, getCompareLicenseAttribute.getCoAttribute1(), style.sh1tableFormat1);
				} else{
					addLabel(sheet1, 10 + column, 7, getCompareLicenseAttribute.getCoAttribute1(), style.sh1tableFormat3);
					
					addLabel(sheet1, 0, 7, "1", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 7, "배포 (바이너리 파일 배포에 의해서만 부여되는 의무사항)", style.sh1tableFormat6);
                    if (atValues.getPjAttribute1().toString().equals("X")) {
                        addLabel(sheet1, 9, 7, "X", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute1().toString().equals("O")) {
                        addLabel(sheet1, 9, 7, "O", style.sh1tableFormat3);
                    }
					
				}
				
				if(getCompareLicenseAttribute.getCoAttribute2().toString().equals(atValues.getPjAttribute2().toString())) {
					if(getCompareLicenseAttribute.getCoAttribute2().toString().equals("O")) {
						addLabel(sheet1, 10 + column, 8, "O", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute2().toString().equals("M")){
						addLabel(sheet1, 10 + column, 8, "△", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute2().toString().equals("X")){
						addLabel(sheet1, 10 + column, 8, "X", style.sh1tableFormat1);
					}				
				} else{				
					if(getCompareLicenseAttribute.getCoAttribute2().toString().equals("O")) {
						addLabel(sheet1, 10 + column, 8, "O", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute2().toString().equals("M")){
						addLabel(sheet1, 10 + column, 8, "△", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute2().toString().equals("X")){
						addLabel(sheet1, 10 + column, 8, "X", style.sh1tableFormat3);
					}
					
					addLabel(sheet1, 0, 8, "2", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 8, "소스코드 공개/강제적 공유 의무사항", style.sh1tableFormat6);
                    if (atValues.getPjAttribute2().toString().equals("O")) {
                        addLabel(sheet1, 9, 8, "O", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute2().toString().equals("M")) {
                        addLabel(sheet1, 9, 8, "△", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute2().toString().equals("X")) {
                        addLabel(sheet1, 9, 8, "X", style.sh1tableFormat3);      
                    }
				}
				
				if(getCompareLicenseAttribute.getCoAttribute3().toString().equals(atValues.getPjAttribute3().toString())) {
					if(getCompareLicenseAttribute.getCoAttribute3().toString().equals("O")) {
						addLabel(sheet1, 10 + column, 9, "O", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute3().toString().equals("M")){
						addLabel(sheet1, 10 + column, 9, "△", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute3().toString().equals("X")){
						addLabel(sheet1, 10 + column, 9, "X", style.sh1tableFormat1);
					}
				} else{
					if(getCompareLicenseAttribute.getCoAttribute3().toString().equals("O")) {
						addLabel(sheet1, 10 + column, 9, "O", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute3().toString().equals("M")){
						addLabel(sheet1, 10 + column, 9, "△", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute3().toString().equals("X")){
						addLabel(sheet1, 10 + column, 9, "X", style.sh1tableFormat3);
					}						
					
					addLabel(sheet1, 0, 9, "3", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 9, "복제 및 수정 권한 허용", style.sh1tableFormat6);
                    if (atValues.getPjAttribute3().toString().equals("O")) {
                        addLabel(sheet1, 9, 9, "O", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute3().toString().equals("M")) {
                        addLabel(sheet1, 9, 9, "△", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute3().toString().equals("X")) {
                        addLabel(sheet1, 9, 9, "X", style.sh1tableFormat3);
                    }
				}
				
				if(getCompareLicenseAttribute.getCoAttribute4().toString().equals(atValues.getPjAttribute4().toString())) {
					if(getCompareLicenseAttribute.getCoAttribute4().toString().equals("O")) {
						addLabel(sheet1, 10 + column, 10, "O", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute4().toString().equals("M")){
						addLabel(sheet1, 10 + column, 10, "△", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute4().toString().equals("X")){
						addLabel(sheet1, 10 + column, 10, "X", style.sh1tableFormat1);
					}
				} else{
					if(getCompareLicenseAttribute.getCoAttribute4().toString().equals("O")) {
						addLabel(sheet1, 10 + column, 10, "O", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute4().toString().equals("M")){
						addLabel(sheet1, 10 + column, 10, "△", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute4().toString().equals("X")){
						addLabel(sheet1, 10 + column, 10, "X", style.sh1tableFormat3);
					}
					
					addLabel(sheet1, 0, 10, "4", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 10, "역설계 권한 허용", style.sh1tableFormat6);
                    if (atValues.getPjAttribute4().toString().equals("O")) {
                        addLabel(sheet1, 9, 10, "O", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute4().toString().equals("M")) {
                        addLabel(sheet1, 9, 10, "△", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute4().toString().equals("X")) {
                        addLabel(sheet1, 9, 10, "X", style.sh1tableFormat3);
                    }
				}
				
				if(getCompareLicenseAttribute.getCoAttribute5().toString().equals(atValues.getpjAttribute5().toString())) {
					if(getCompareLicenseAttribute.getCoAttribute5().toString().equals("O")) {
						addLabel(sheet1, 10 + column, 11, "O", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute5().toString().equals("M")){
						addLabel(sheet1, 10 + column, 11, "△", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute5().toString().equals("X")){
						addLabel(sheet1, 10 + column, 11, "X", style.sh1tableFormat1);
					}
				} else{
					if(getCompareLicenseAttribute.getCoAttribute5().toString().equals("O")) {
						addLabel(sheet1, 10 + column, 11, "O", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute5().toString().equals("M")){
						addLabel(sheet1, 10 + column, 11, "△", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute5().toString().equals("X")){
						addLabel(sheet1, 10 + column, 11, "X", style.sh1tableFormat3);
					}						
					
					addLabel(sheet1, 0, 11, "5", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 11, "차별적 제한조건 제한", style.sh1tableFormat6);
                    if (atValues.getpjAttribute5().toString().equals("O")) {
                        addLabel(sheet1, 9, 11, "O", style.sh1tableFormat3);
                    }
                    else if (atValues.getpjAttribute5().toString().equals("M")) {
                        addLabel(sheet1, 9, 11, "△", style.sh1tableFormat3);
                    }
                    else if (atValues.getpjAttribute5().toString().equals("X")) {
                        addLabel(sheet1, 9, 11, "X", style.sh1tableFormat3);
                    }					
				}
				
				if(getCompareLicenseAttribute.getCoAttribute6().toString().equals(atValues.getPjAttribute6().toString())) {
					addLabel(sheet1, 10 + column, 12, getCompareLicenseAttribute.getCoAttribute6(), style.sh1tableFormat1);
				} else{
					addLabel(sheet1, 10 + column, 12, getCompareLicenseAttribute.getCoAttribute6(), style.sh1tableFormat3);
					
					addLabel(sheet1, 0, 12, "6", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 12, "특허 무상실시권 부여 (특허소송을 제기하지 않음, 제기시 라이선스 종료)", style.sh1tableFormat6);
                    if (atValues.getPjAttribute6().toString().equals("X")) {
                        addLabel(sheet1, 9, 12, "X", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute6().toString().equals("O")) {
                        addLabel(sheet1, 9, 12, "O", style.sh1tableFormat3);
                    }
				}
				
				if(getCompareLicenseAttribute.getCoAttribute7().toString().equals(atValues.getPjAttribute7().toString())) {
					addLabel(sheet1, 10 + column, 13, getCompareLicenseAttribute.getCoAttribute7(), style.sh1tableFormat1);
				} else{
					addLabel(sheet1, 10 + column, 13, getCompareLicenseAttribute.getCoAttribute7(), style.sh1tableFormat3);
					
					addLabel(sheet1, 0, 13, "7", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 13, "기술적 보호조치의 보호금지, 설치정보 제공", style.sh1tableFormat6);
                    if (atValues.getPjAttribute7().toString().equals("X")) {
                        addLabel(sheet1, 9, 13, "X", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute7().toString().equals("O")) {
                        addLabel(sheet1, 9, 13, "O", style.sh1tableFormat3);
                    }
				}
				
				if(getCompareLicenseAttribute.getCoAttribute8().toString().equals(atValues.getPjAttribute8().toString())) {
					addLabel(sheet1, 10 + column, 14, getCompareLicenseAttribute.getCoAttribute8(), style.sh1tableFormat1);
				} else{
					addLabel(sheet1, 10 + column, 14, getCompareLicenseAttribute.getCoAttribute8(), style.sh1tableFormat3);
					
					addLabel(sheet1, 0, 14, "8", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 14, "고지 (라이선스 사본 포함)", style.sh1tableFormat6);
                    if (atValues.getPjAttribute8().toString().equals("X")) {
                        addLabel(sheet1, 9, 14, "X", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute8().toString().equals("O")) {
                        addLabel(sheet1, 9, 14, "O", style.sh1tableFormat3);
                    }
				}
				
				if(getCompareLicenseAttribute.getCoAttribute9().toString().equals(atValues.getPjAttribute9().toString())) {
					addLabel(sheet1, 10 + column, 15, getCompareLicenseAttribute.getCoAttribute9(), style.sh1tableFormat1);
				} else{
					addLabel(sheet1, 10 + column, 15, getCompareLicenseAttribute.getCoAttribute9(), style.sh1tableFormat3);
					
					addLabel(sheet1, 0, 15, "9", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 15, "변경사항 고지 (라이선스 사본 포함)", style.sh1tableFormat6);
                    if (atValues.getPjAttribute9().toString().equals("X")) {
                        addLabel(sheet1, 9, 15, "X", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute9().toString().equals("O")) {
                        addLabel(sheet1, 9, 15, "O", style.sh1tableFormat3);
                    }
				}
				
				if(getCompareLicenseAttribute.getCoAttribute10().toString().equals(atValues.getPjAttribute10().toString())) {
					addLabel(sheet1, 10 + column, 16, getCompareLicenseAttribute.getCoAttribute10(), style.sh1tableFormat1);
				} else{
					addLabel(sheet1, 10 + column, 16, getCompareLicenseAttribute.getCoAttribute10(), style.sh1tableFormat3);
					
					addLabel(sheet1, 0, 16, "10", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 16, "보증의 부인 및 책임의 제한", style.sh1tableFormat6);
                    if (atValues.getPjAttribute10().toString().equals("X")) {
                        addLabel(sheet1, 9, 16, "X", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute10().toString().equals("O")) {
                        addLabel(sheet1, 9, 16, "O", style.sh1tableFormat3);
                    }
				}
				
				if(getCompareLicenseAttribute.getCoAttribute11().toString().equals(atValues.getPjAttribute11().toString())) {
					addLabel(sheet1, 10 + column, 17, getCompareLicenseAttribute.getCoAttribute11(), style.sh1tableFormat1);
				} else{
					addLabel(sheet1, 10 + column, 17, getCompareLicenseAttribute.getCoAttribute11(), style.sh1tableFormat3);
					
					addLabel(sheet1, 0, 17, "11", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 17, "광고/홍보시 배포자,저작자, 특정상표사용 금지", style.sh1tableFormat6);
                    if (atValues.getPjAttribute11().toString().equals("X")) {
                        addLabel(sheet1, 9, 17, "X", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute11().toString().equals("O")) {
                        addLabel(sheet1, 9, 17, "O", style.sh1tableFormat3);
                    }
				}
				
				if(getCompareLicenseAttribute.getCoAttribute12().toString().equals(atValues.getPjAttribute11().toString())) {
					addLabel(sheet1, 10 + column, 18, getCompareLicenseAttribute.getCoAttribute12(), style.sh1tableFormat1);
				} else{
					addLabel(sheet1, 10 + column, 18, getCompareLicenseAttribute.getCoAttribute12(), style.sh1tableFormat3);
					
					addLabel(sheet1, 0, 18, "12", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 18, "원코드와 동일조건 허가", style.sh1tableFormat6);
                    if (atValues.getPjAttribute12().toString().equals("X")) {
                        addLabel(sheet1, 9, 18, "X", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute12().toString().equals("O")) {
                        addLabel(sheet1, 9, 18, "O", style.sh1tableFormat3);
                    }
				}
				
				if(getCompareLicenseAttribute.getCoAttribute13().toString().equals(atValues.getPjAttribute13().toString())) {
					if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("X")){
						addLabel(sheet1, 10 + column, 19, "X", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("MPL")){
						addLabel(sheet1, 10 + column, 19, "파일\n(per MPL)", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("EPL_CPL")){
						addLabel(sheet1, 10 + column, 19, "모듈\n(per CPL)", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("LGPL")){
						addLabel(sheet1, 10 + column, 19, "동적 라이브러리\n(per LGPL)", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("GPL")){
						addLabel(sheet1, 10 + column, 19, "코드 기반의 산출물\n(per GPL)", style.sh1tableFormat1);
					} else if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("SLEEPYCAT")){
						addLabel(sheet1, 10 + column, 19, "수반 소프트웨어\n(per SleepyCat)", style.sh1tableFormat1);
					}				
				} else{
					if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("X")){
						addLabel(sheet1, 10 + column, 19, "X", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("MPL")){
						addLabel(sheet1, 10 + column, 19, "파일\n(per MPL)", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("EPL_CPL")){
						addLabel(sheet1, 10 + column, 19, "모듈\n(per CPL)", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("LGPL")){
						addLabel(sheet1, 10 + column, 19, "동적 라이브러리\n(per LGPL)", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("GPL")){
						addLabel(sheet1, 10 + column, 19, "코드 기반의 산출물\n(per GPL)", style.sh1tableFormat3);
					} else if(getCompareLicenseAttribute.getCoAttribute13().toString().equals("SLEEPYCAT")){
						addLabel(sheet1, 10 + column, 19, "수반 소프트웨어\n(per SleepyCat)", style.sh1tableFormat3);
					}
					
					addLabel(sheet1, 0, 19, "13", style.sh1tableFormat3);
                    addLabel(sheet1, 1, 19, "라이선스 적용 범위", style.sh1tableFormat6);
                    if (atValues.getPjAttribute13().toString().equals("X")) {
                        addLabel(sheet1, 9, 19, "X", style.sh1tableFormat1);
                    }
                    else if (atValues.getPjAttribute13().toString().equals("MPL")) {
                        addLabel(sheet1, 9, 19, "파일\n(per MPL)", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute13().toString().equals("EPL_CPL")) {
                        addLabel(sheet1, 9, 19, "모듈\n(per CPL)", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute13().toString().equals("LGPL")) {
                        addLabel(sheet1, 9, 19, "동적 라이브러리\n(per LGPL)", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute13().toString().equals("GPL")) {
                        addLabel(sheet1, 9, 19, "코드 기반의 산출물\n(per GPL)", style.sh1tableFormat3);
                    }
                    else if (atValues.getPjAttribute13().toString().equals("SLEEPYCAT")) {
                        addLabel(sheet1, 9, 19, "수반 소프트웨어\n(per SleepyCat)", style.sh1tableFormat3);
                    }
				}				
				mergeCount++;	
				column++;				
				}		
			}		
				
		// if there is more than one project license conflict 
		if(mergeCount >= 10) {
			addLabel(sheet1, 10, 5, "충돌 라이선스", style.sh1tableFormat2);
			sheet1.mergeCells(10, 5, mergeCount, 5);
		}				
		
		sheet1.setRowView(21, 5000);
		sheet1.mergeCells(0, 21, 1, 21);
		addLabel(sheet1, 0, 21, "프로젝트\n배포에 따른\n라이선스\n검증 의견", style.sh1tableFormat2);		
		sheet1.mergeCells(2, 21, 9, 21);
		addLabel(sheet1, 2, 21, "", style.sh1tableFormat5);
		
		sheet1.setRowView(22, 3000);
		sheet1.mergeCells(0, 22, 1, 22);
		addLabel(sheet1, 0, 22, "사업책임자\n종합 의견", style.sh1tableFormat2);
		sheet1.mergeCells(2, 22, 9, 22);
		addBlank(sheet1, 2, 22, style.sh1tableFormat1);
		
		sheet1.setRowView(23, 2000);
		sheet1.mergeCells(0, 23, 1, 23);
		addLabel(sheet1, 0, 23, "오픈소스센터\n의견", style.sh1tableFormat2);
		sheet1.mergeCells(2, 23, 9, 23);
		addBlank(sheet1, 2, 23, style.sh1tableFormat1);
	}
}
