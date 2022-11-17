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
import fossid.report.values.BillOfMaterialsValues;
import fossid.report.values.IdentifiedFilesValues;
import fossid.report.values.ProjectLicenseAttributeValues;
import fossid.report.values.CompareLicenseAttributeValues;
import fossid.report.values.ProjectValues;
import fossid.report.attribute.SetCompareLicenseAttribute;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class CreateSheet2 extends CreateSheet{
	private final Logger logger = LogManager.getLogger(CreateSheet2.class);
	public CreateSheet2(){
		super();
	}	
	
	ExcelValues excelVal = ExcelValues.getInstance();
	BillOfMaterialsValues bomValues = BillOfMaterialsValues.getInstance();
	ProjectValues pValues = ProjectValues.getInstance();
	IdentifiedFilesValues idValues = IdentifiedFilesValues.getInstance();
	ProjectLicenseAttributeValues projectLicenseAttribute = ProjectLicenseAttributeValues.getInstance();
	CompareLicenseAttributeValues compareLicenseAttribute = CompareLicenseAttributeValues.getInstance();

	WritableSheet sheet2 = excelVal.getSheet2();		
	
	public void writeSheet() throws WriteException {
		logger.info("Creating sheet 3..");

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
		int value;
		String key;
		
		for(int i = 0; i < bomValues.getUComponentName().size(); i++) {
			// this set affect column 0(license conflict) and 6(Patent retaliation clause) 
			//setCompareLicenseAttribute.setCompareAttribute(bomValues.getUcomponentLicenseName().get(i));
			
			// value: 0 - no conflict / 1 - project license conflict
			//ProjectLicenseConflict.projectLicenseConflict(bomValues.getUcomponentLicenseName().get(i));
			
			key = bomValues.getUComponentLicenseName().get(i);
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
			
			for(int j = 0; j < bomValues.getLicenseWithoutProjectConflict().size(); j++) {
				key = bomValues.getLicenseWithoutProjectConflict().get(j);
				value =	bomValues.getComponentLicenseConflict().get(key);
				if (value == 1 && key.equals(bomValues.getUComponentLicenseName().get(i))) {
					addLabel(sheet2, 0, i + 2, "다른 컴포넌트 라이선스와 충돌", style.componentConflict);
					bomValues.setComponentConflictLicense(bomValues.getLicenseWithoutProjectConflict().get(j));
				}
			}
			
			addLabel(sheet2, 1, i + 2, bomValues.getUComponentName().get(i), style.sh1tableFormat1);
			addLabel(sheet2, 2, i + 2, bomValues.getUComponentVersion().get(i), style.sh1tableFormat1);
			addLabel(sheet2, 3, i + 2, bomValues.getUComponentHomepage().get(i), style.sh1tableFormat1);
			addLabel(sheet2, 4, i + 2, bomValues.getUComponentLicenseName().get(i), style.sh1tableFormat1);
			
			String tempValue = bomValues.getUComponentName().get(i) + bomValues.getUComponentVersion().get(i);
			addLabel(sheet2, 5, i + 2, idValues.getMatchTypeHashmap().get(tempValue), style.sh1tableFormat1);
			
			// to use CreateSheet3.java
			if(!(bomValues.getLicenseMatchType().containsKey(bomValues.getUComponentLicenseName().get(i)))){
				bomValues.setLicenseMatchType(bomValues.getUComponentLicenseName().get(i), idValues.getMatchTypeHashmap().get(tempValue));
			}
			
			if(idValues.getMatchTypeHashmap().get(tempValue) != null) {
				if(idValues.getMatchTypeHashmap().get(tempValue).equals("Partial, Full")){
					bomValues.getLicenseMatchType().remove(bomValues.getUComponentLicenseName().get(i));
					bomValues.setLicenseMatchType(bomValues.getUComponentLicenseName().get(i), idValues.getMatchTypeHashmap().get(tempValue));
				}				
			}
						
			key = bomValues.getUComponentName().get(i)+bomValues.getUComponentVersion().get(i);
			addLabel(sheet2, 6, i + 2, Integer.toString(bomValues.getUComponentFileCount().get(key)), style.sh1tableFormat1);
			
			SetCompareLicenseAttribute.setCompareAttribute(bomValues.getUComponentLicenseName().get(i));
			if (compareLicenseAttribute.getCoAttribute2().equals("X") || compareLicenseAttribute.getCoAttribute2().equals("M")) {
				addLabel(sheet2, 7, i + 2, "X", style.sh1tableFormat1);
			} else if(compareLicenseAttribute.getCoAttribute2().equals("O")){
				addLabel(sheet2, 7, i + 2, "O", style.sh1tableFormat1);
			}
			
			projectRowCount++;
		}
		
		addLabel(sheet2, 0, projectRowCount + 2, "N/A", style.sh1tableFormat1);
		addLabel(sheet2, 1, projectRowCount + 2, pValues.getProjectName(), style.sh1tableFormat1);
		addLabel(sheet2, 2, projectRowCount + 2, pValues.getVersionName(), style.sh1tableFormat1);
		addLabel(sheet2, 3, projectRowCount + 2, "", style.sh1tableFormat1);
		addLabel(sheet2, 4, projectRowCount + 2, pValues.getProjectLicense(), style.sh1tableFormat1);
		addLabel(sheet2, 5, projectRowCount + 2, "", style.sh1tableFormat1);		
		
		int totalCount = idValues.getFileTotalCount() - idValues.getIgnoredCount() - idValues.getIdentifiedFileCount();
		addLabel(sheet2, 6, projectRowCount + 2, Integer.toString(totalCount), style.sh1tableFormat1);
		
		if(projectLicenseAttribute.getPjAttribute2().equals("X")) {
			addLabel(sheet2, 7, projectRowCount + 2, "X", style.sh1tableFormat1);
		} else if(projectLicenseAttribute.getPjAttribute2().equals("O")){
			addLabel(sheet2, 7, projectRowCount + 2, "O", style.sh1tableFormat1);
		}	
		
	}	
}