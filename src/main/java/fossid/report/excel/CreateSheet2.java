/**
 * FOSSID Report Generator
 *
 * Copyright (C) 2020 FOSSID
 * http://www.fossid.co.kr/
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package fossid.report.excel;

import jxl.write.WritableSheet;
import jxl.write.WriteException;
import fossid.report.excel.ExcelValues;
import fossid.report.values.billofmaterialsValues;
import fossid.report.values.identifiedFilesValues;
import fossid.report.values.projectLicenseAttributeValues;
import fossid.report.values.compareLicenseAttributeValues;
import fossid.report.values.projectValues;
import fossid.report.attribute.getProjectLicenseConflict;
import fossid.report.attribute.getComponetLicenseConflict;
import fossid.report.attribute.setCompareLicenseAttribute;
import fossid.report.attribute.setCompareComponentLicenseAttribute;
import fossid.report.getdata.getNumberofIgnored;


public class CreateSheet2 extends CreateSheet{
	
	public CreateSheet2(){
		super();
	}	
	
	ExcelValues excelVal = ExcelValues.getInstance();
	billofmaterialsValues bomValues = billofmaterialsValues.getInstance();
	projectValues pvalues = projectValues.getInstance();	
	identifiedFilesValues idValues = identifiedFilesValues.getInstance();
	projectLicenseAttributeValues proejctLicenseAttirbute = projectLicenseAttributeValues.getInstance();
	compareLicenseAttributeValues compareLicenseAttribute = compareLicenseAttributeValues.getInstance();
	
	getProjectLicenseConflict projectLicenseConflict = new getProjectLicenseConflict();
	getComponetLicenseConflict componentLicenseConflict = new getComponetLicenseConflict();	
	setCompareLicenseAttribute setCompareLicenseAttribute = new setCompareLicenseAttribute();
	setCompareComponentLicenseAttribute setcompareComponentLicenseAttribute = new setCompareComponentLicenseAttribute();
	getNumberofIgnored ignoredNumber = new getNumberofIgnored();
	
	WritableSheet sheet2 = excelVal.getSheet2();		
	
	public void writeSheet() throws WriteException {
		System.out.println("Creating sheet 3..");		
		
		sheet2 = excelVal.getWB().createSheet("3. 준법성 현황", 2);
		
		for(int i=0; i < 9; i++) {
			if(i==3 || i==4) {
				sheet2.setColumnView(3, 45);				
				sheet2.setColumnView(4, 45);
			}else {
				sheet2.setColumnView(i, 28);	
			}			
		}
		
		sheet2.setRowView(0, 800);
		addLabel(sheet2, 0, 0, "3. 준법성 현황", style.sh1TitleFormat);
		
		sheet2.setRowView(1, 700);
		addLabel(sheet2, 0, 1, "준법성 현황", style.sh1tableFormat2);
		addLabel(sheet2, 1, 1, "컴포넌트", style.sh1tableFormat2);
		addLabel(sheet2, 2, 1, "버전", style.sh1tableFormat2);
		addLabel(sheet2, 3, 1, "홈페이지", style.sh1tableFormat2);		
		addLabel(sheet2, 4, 1, "라이선스", style.sh1tableFormat2);
		addLabel(sheet2, 5, 1, "결합 형태", style.sh1tableFormat2);		
		addLabel(sheet2, 6, 1, "해당파일 수", style.sh1tableFormat2);
		addLabel(sheet2, 7, 1, "소스코드 공개필요", style.sh1tableFormat2);
		
		int projectRowCount = 0;		
		int value = 0;
		String key = null;
		
		for(int i = 0; i < bomValues.getUcomponentName().size(); i++) {
			// this set affect column 0(license conflict) and 6(Patent retaliation clause) 
			//setCompareLicenseAttribute.setCompareAttribute(bomValues.getUcomponentLicenseName().get(i));
			
			// value: 0 - no conflict / 1 - project license conflict
			//projectLicenseConflict.projectLicenseConflict(bomValues.getUcomponentLicenseName().get(i));
			
			key = bomValues.getUcomponentLicenseName().get(i);
			value =	bomValues.getProjectLicenseConflict().get(key);
			if(value == 0) {
				addLabel(sheet2, 0, i + 2, "충돌없음", style.noConflict);
				//set component list without project license conflict
				//bomValues.setLicensetwithoutProjectConflict(bomValues.getUcomponentLicenseName().get(i));
				//set component list without project license conflict with value 0
				//bomValues.setComponentLicenseConflict(bomValues.getUcomponentLicenseName().get(i), 0);
			} else if (value == 1) {
				addLabel(sheet2, 0, i + 2, "프로젝트에 선언된 라이선스와 충돌", style.projectConflict);	
				// set licenses with project license conflict
				//bomValues.setLicensetwithProjectConflict(bomValues.getUcomponentLicenseName().get(i));
			} 	
			
			for(int j =0; j < bomValues.getLicensewithoutProjectConflict().size(); j++) {
				key = bomValues.getLicensewithoutProjectConflict().get(j);
				value =	bomValues.getComponentLicenseConflict().get(key);
				if (value == 1 && key.equals(bomValues.getUcomponentLicenseName().get(i))) {				
					addLabel(sheet2, 0, i + 2, "다른 컴포넌트 라이선스와 충돌", style.componentConflict);
					bomValues.setcomponentConflictLicense(bomValues.getLicensewithoutProjectConflict().get(j));				
				}
			}
			
			addLabel(sheet2, 1, i + 2, bomValues.getUcomponentName().get(i), style.sh1tableFormat1);
			addLabel(sheet2, 2, i + 2, bomValues.getUcomponentVersion().get(i), style.sh1tableFormat1);
			addLabel(sheet2, 3, i + 2, bomValues.getUcomponentHomepage().get(i), style.sh1tableFormat1);
			addLabel(sheet2, 4, i + 2, bomValues.getUcomponentLicenseName().get(i), style.sh1tableFormat1);
			
			String tempValue = bomValues.getUcomponentName().get(i) + bomValues.getUcomponentVersion().get(i);
			addLabel(sheet2, 5, i + 2, idValues.getmatchTypeHashmap().get(tempValue), style.sh1tableFormat1);
			
			// to use CreateSheet3.java
			if(!(bomValues.getlicenseMatchType().containsKey(bomValues.getUcomponentLicenseName().get(i)))){
				bomValues.setlicenseMatchType(bomValues.getUcomponentLicenseName().get(i), idValues.getmatchTypeHashmap().get(tempValue));
			}
			
			if(idValues.getmatchTypeHashmap().get(tempValue) != null) {
				if(idValues.getmatchTypeHashmap().get(tempValue).equals("Partial, Full")){	
					bomValues.getlicenseMatchType().remove(bomValues.getUcomponentLicenseName().get(i));
					bomValues.setlicenseMatchType(bomValues.getUcomponentLicenseName().get(i), idValues.getmatchTypeHashmap().get(tempValue));
				}				
			}
						
			key = bomValues.getUcomponentName().get(i)+bomValues.getUcomponentVersion().get(i);
			addLabel(sheet2, 6, i + 2, Integer.toString(bomValues.getUcomponentFileCount().get(key)), style.sh1tableFormat1);			
			
			setCompareLicenseAttribute.setCompareAttribute(bomValues.getUcomponentLicenseName().get(i));
			if (compareLicenseAttribute.getcoAttribute2().toString().equals("X") || compareLicenseAttribute.getcoAttribute2().toString().equals("M")) {
				addLabel(sheet2, 7, i + 2, "X", style.sh1tableFormat1);
			} else if(compareLicenseAttribute.getcoAttribute2().toString().equals("O")){
				addLabel(sheet2, 7, i + 2, "O", style.sh1tableFormat1);
			}
			
			projectRowCount++;
		}
		
		addLabel(sheet2, 0, projectRowCount + 2, "N/A", style.sh1tableFormat1);
		addLabel(sheet2, 1, projectRowCount + 2, pvalues.getProjectName(), style.sh1tableFormat1);
		addLabel(sheet2, 2, projectRowCount + 2, pvalues.getVersionName(), style.sh1tableFormat1);
		addLabel(sheet2, 3, projectRowCount + 2, "", style.sh1tableFormat1);
		addLabel(sheet2, 4, projectRowCount + 2, pvalues.getProjectLicense(), style.sh1tableFormat1);
		addLabel(sheet2, 5, projectRowCount + 2, "", style.sh1tableFormat1);		
		
		int totalCount = idValues.getfileTotalCount() - idValues.getIgnoredCount() - idValues.getIdentifiedFileCount();
		addLabel(sheet2, 6, projectRowCount + 2, Integer.toString(totalCount), style.sh1tableFormat1);
		
		if(proejctLicenseAttirbute.getpjAttribute2().toString().equals("X")) {
			addLabel(sheet2, 7, projectRowCount + 2, "X", style.sh1tableFormat1);
		} else if(proejctLicenseAttirbute.getpjAttribute2().toString().equals("O")){
			addLabel(sheet2, 7, projectRowCount + 2, "O", style.sh1tableFormat1);
		}	
		
	}	
}