package fossid.report.attribute;

import fossid.report.values.billofmaterialsValues;
import fossid.report.values.compareLicenseAttributeValues;
import fossid.report.values.projectLicenseAttributeValues;
import fossid.report.values.projectLicenseConflictAttributeValues;

public class getProjectLicenseConflict {
	
	public void projectLicenseConflict(String licenseName) {
		
		//System.out.println("licenseNmae: " + licenseName);
		
		projectLicenseAttributeValues projectLicenseAttributes = projectLicenseAttributeValues.getInstance();
		compareLicenseAttributeValues compareLicenseAttributes = compareLicenseAttributeValues.getInstance();
		projectLicenseConflictAttributeValues projectlicenseconflictValues = projectLicenseConflictAttributeValues.getInstance();
		billofmaterialsValues bomValues = billofmaterialsValues.getInstance();		
		
		// if project license is proprietary		
		if(projectLicenseAttributes.getpjAttribute2().toString().equals("X")) {			
			if(compareLicenseAttributes.getcoAttribute1().toString().equals("O")
					/**
					&& !compareLicenseAttributes.getcoAttribute13().toString().equals("X")
					&& !projectLicenseAttributes.getpjAttribute13().equals("X")
					**/
				)
			{
				if(projectLicenseAttributes.getpjAttribute2().toString().equals(compareLicenseAttributes.getcoAttribute2().toString()) ||
						projectLicenseAttributes.getpjAttribute2().toString().equals("M") || compareLicenseAttributes.getcoAttribute2().toString().equals("M")) {
				} else {
					//System.out.println("1_project conflict2:" + projectLicenseAttributes.getpjAttribute2() + "  " + compareLicenseAttributes.getcoAttribute3());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}										
				
				if(projectLicenseAttributes.getpjAttribute3().toString().equals(compareLicenseAttributes.getcoAttribute3().toString()) ||
						projectLicenseAttributes.getpjAttribute3().toString().equals("M") || compareLicenseAttributes.getcoAttribute3().toString().equals("M")) {
				} else {
					//System.out.println("1_project conflict3:" + projectLicenseAttributes.getpjAttribute3() + "  " + compareLicenseAttributes.getcoAttribute3());
					bomValues.setProjectLicenseConflict(licenseName, 1);														
				}				
				
				if(projectLicenseAttributes.getpjAttribute4().toString().equals(compareLicenseAttributes.getcoAttribute4().toString()) ||
						projectLicenseAttributes.getpjAttribute4().toString().equals("M") || compareLicenseAttributes.getcoAttribute4().toString().equals("M")) {
				} else {
					//System.out.println("1_project conflict4:" + projectLicenseAttributes.getpjAttribute4() + "  " + compareLicenseAttributes.getcoAttribute4());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(projectLicenseAttributes.getpjAttribute5().toString().equals(compareLicenseAttributes.getcoAttribute5().toString()) ||
						projectLicenseAttributes.getpjAttribute5().toString().equals("M") || compareLicenseAttributes.getcoAttribute5().toString().equals("M")) {					
				} else {
					//System.out.println("1_project conflict5:" + projectLicenseAttributes.getpjAttribute5() + "  " + compareLicenseAttributes.getcoAttribute5());
					bomValues.setProjectLicenseConflict(licenseName, 1);					
				}								
				
				if(projectLicenseAttributes.getpjAttribute6().toString().equals(compareLicenseAttributes.getcoAttribute6().toString()) ||
						projectLicenseAttributes.getpjAttribute6().toString().equals("X") || compareLicenseAttributes.getcoAttribute6().toString().equals("X")) {					
				} else {
					//System.out.println("1_project conflict6:" + projectLicenseAttributes.getpjAttribute6() + "  " + compareLicenseAttributes.getcoAttribute6());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
								
				if(projectLicenseAttributes.getpjAttribute7().toString().equals(compareLicenseAttributes.getcoAttribute7().toString()) ||
						projectLicenseAttributes.getpjAttribute7().toString().equals("X") || compareLicenseAttributes.getcoAttribute7().toString().equals("X")) {					
				} else {
					//System.out.println("1_project conflict7:" + projectLicenseAttributes.getpjAttribute7() + "  " + compareLicenseAttributes.getcoAttribute6());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
			
				if(projectLicenseAttributes.getpjAttribute8().toString().equals(compareLicenseAttributes.getcoAttribute8().toString()) ||
						projectLicenseAttributes.getpjAttribute8().toString().equals("X") || compareLicenseAttributes.getcoAttribute8().toString().equals("X")) {
					
				} else {
					//System.out.println("1_project conflict8:" + projectLicenseAttributes.getpjAttribute8() + "  " + compareLicenseAttributes.getcoAttribute8());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(projectLicenseAttributes.getpjAttribute9().toString().equals(compareLicenseAttributes.getcoAttribute9().toString()) ||
						projectLicenseAttributes.getpjAttribute9().toString().equals("X") || compareLicenseAttributes.getcoAttribute9().toString().equals("X")) {
				} else {
					//System.out.println("1_project conflict9:" + projectLicenseAttributes.getpjAttribute9() + "  " + compareLicenseAttributes.getcoAttribute9());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
		
				if(projectLicenseAttributes.getpjAttribute10().toString().equals(compareLicenseAttributes.getcoAttribute10().toString()) ||
						projectLicenseAttributes.getpjAttribute10().toString().equals("X") || compareLicenseAttributes.getcoAttribute10().toString().equals("X")) {
				} else {
					//System.out.println("1_project conflict10:" + projectLicenseAttributes.getpjAttribute10() + "  " + compareLicenseAttributes.getcoAttribute10());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
			
				if(projectLicenseAttributes.getpjAttribute11().toString().equals(compareLicenseAttributes.getcoAttribute11().toString()) ||
						projectLicenseAttributes.getpjAttribute11().toString().equals("X") || compareLicenseAttributes.getcoAttribute11().toString().equals("X")) {
				} else {
					//System.out.println("1_project conflict11:" + projectLicenseAttributes.getpjAttribute11() + "  " + compareLicenseAttributes.getcoAttribute11());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}			
				
				if(projectLicenseAttributes.getpjAttribute12().toString().equals(compareLicenseAttributes.getcoAttribute12().toString()) ||
						projectLicenseAttributes.getpjAttribute12().toString().equals("X") || compareLicenseAttributes.getcoAttribute12().toString().equals("X")) {
				} else {
					//System.out.println("1_project conflict12:" + projectLicenseAttributes.getpjAttribute12() + "  " + compareLicenseAttributes.getcoAttribute12());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}	
			}			
		}
		
		// if project license is open source
		if(!projectLicenseAttributes.getpjAttribute2().toString().equals("X")) {
			
				if(projectLicenseAttributes.getpjAttribute1().toString().equals(compareLicenseAttributes.getcoAttribute1().toString()) ||
						projectLicenseAttributes.getpjAttribute1().toString().equals("X") || compareLicenseAttributes.getcoAttribute1().toString().equals("X")) {
				} else {
					//System.out.println("project conflict1:" + projectLicenseAttributes.getpjAttribute1() + "  " + compareLicenseAttributes.getcoAttribute1());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(projectLicenseAttributes.getpjAttribute2().toString().equals(compareLicenseAttributes.getcoAttribute2().toString()) ||
						projectLicenseAttributes.getpjAttribute2().toString().equals("M") || compareLicenseAttributes.getcoAttribute2().toString().equals("M")) {
				} else {
					//System.out.println("project conflict2:" + projectLicenseAttributes.getpjAttribute2() + "  " + compareLicenseAttributes.getcoAttribute2());
					bomValues.setProjectLicenseConflict(licenseName, 1);						
				}										
				
				if(projectLicenseAttributes.getpjAttribute3().toString().equals(compareLicenseAttributes.getcoAttribute3().toString()) ||
						projectLicenseAttributes.getpjAttribute3().toString().equals("M") || compareLicenseAttributes.getcoAttribute3().toString().equals("M")) {
				} else {
					//System.out.println("project conflict3:" + projectLicenseAttributes.getpjAttribute3() + "  " + compareLicenseAttributes.getcoAttribute3());
					bomValues.setProjectLicenseConflict(licenseName, 1);									
				}				
				
				if(projectLicenseAttributes.getpjAttribute4().toString().equals(compareLicenseAttributes.getcoAttribute4().toString()) ||
						projectLicenseAttributes.getpjAttribute4().toString().equals("M") || compareLicenseAttributes.getcoAttribute4().toString().equals("M")) {
				} else {
					//System.out.println("project conflict4:" + projectLicenseAttributes.getpjAttribute4() + "  " + compareLicenseAttributes.getcoAttribute4());
					bomValues.setProjectLicenseConflict(licenseName, 1);		
				}
				
				if(projectLicenseAttributes.getpjAttribute5().toString().equals(compareLicenseAttributes.getcoAttribute5().toString()) ||
						projectLicenseAttributes.getpjAttribute5().toString().equals("M") || compareLicenseAttributes.getcoAttribute5().toString().equals("M")) {					
				} else {
					//System.out.println("project conflict5:" + projectLicenseAttributes.getpjAttribute5() + "  " + compareLicenseAttributes.getcoAttribute5());
					bomValues.setProjectLicenseConflict(licenseName, 1);				
				}								
				
				if(projectLicenseAttributes.getpjAttribute6().toString().equals(compareLicenseAttributes.getcoAttribute6().toString()) ||
						projectLicenseAttributes.getpjAttribute6().toString().equals("X") || compareLicenseAttributes.getcoAttribute6().toString().equals("X")) {					
				} else {
					//System.out.println("project conflict6:" + projectLicenseAttributes.getpjAttribute6() + "  " + compareLicenseAttributes.getcoAttribute6());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}				
				
				if(projectLicenseAttributes.getpjAttribute7().toString().equals(compareLicenseAttributes.getcoAttribute7().toString()) ||
						projectLicenseAttributes.getpjAttribute7().toString().equals("X") || compareLicenseAttributes.getcoAttribute7().toString().equals("X")) {					
				} else {
					//System.out.println("project conflict7:" + projectLicenseAttributes.getpjAttribute7() + "  " + compareLicenseAttributes.getcoAttribute6());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
							
				if(projectLicenseAttributes.getpjAttribute8().toString().equals(compareLicenseAttributes.getcoAttribute8().toString()) ||
						projectLicenseAttributes.getpjAttribute8().toString().equals("X") || compareLicenseAttributes.getcoAttribute8().toString().equals("X")) {
					
				} else {
					//System.out.println("project conflict8:" + projectLicenseAttributes.getpjAttribute8() + "  " + compareLicenseAttributes.getcoAttribute8());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(projectLicenseAttributes.getpjAttribute9().toString().equals(compareLicenseAttributes.getcoAttribute9().toString()) ||
						projectLicenseAttributes.getpjAttribute9().toString().equals("X") || compareLicenseAttributes.getcoAttribute9().toString().equals("X")) {
				} else {
					//System.out.println("project conflict9:" + projectLicenseAttributes.getpjAttribute9() + "  " + compareLicenseAttributes.getcoAttribute9());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
						
				if(projectLicenseAttributes.getpjAttribute10().toString().equals(compareLicenseAttributes.getcoAttribute10().toString()) ||
						projectLicenseAttributes.getpjAttribute10().toString().equals("X") || compareLicenseAttributes.getcoAttribute10().toString().equals("X")) {
				} else {
					//System.out.println("project conflict10:" + projectLicenseAttributes.getpjAttribute10() + "  " + compareLicenseAttributes.getcoAttribute10());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
			
				if(projectLicenseAttributes.getpjAttribute11().toString().equals(compareLicenseAttributes.getcoAttribute11().toString()) ||
						projectLicenseAttributes.getpjAttribute11().toString().equals("X") || compareLicenseAttributes.getcoAttribute11().toString().equals("X")) {
				} else {
					//System.out.println("project conflict11:" + projectLicenseAttributes.getpjAttribute11() + "  " + compareLicenseAttributes.getcoAttribute11());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}				
				
				if(projectLicenseAttributes.getpjAttribute12().toString().equals(compareLicenseAttributes.getcoAttribute12().toString()) ||
						projectLicenseAttributes.getpjAttribute12().toString().equals("X") || compareLicenseAttributes.getcoAttribute12().toString().equals("X")) {
				} else {
					//System.out.println("project conflict12:" + projectLicenseAttributes.getpjAttribute12() + "  " + compareLicenseAttributes.getcoAttribute12());
					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(projectLicenseAttributes.getpjAttribute2().toString().equals("O") && 
						!(projectLicenseAttributes.getpjAttribute13().toString().equals("X")) &&
						compareLicenseAttributes.getcoAttribute2().toString().equals("O") && 
						!(compareLicenseAttributes.getcoAttribute13().toString().equals("X"))) {
					
							if(!(projectLicenseAttributes.getpjAttribute13().toString().equals(compareLicenseAttributes.getcoAttribute13().toString()))) {
								//System.out.println("Project license conflict13:" + projectLicenseAttributes.getpjAttribute13() + "  " + compareLicenseAttributes.getcoAttribute13());
								bomValues.setComponentLicenseConflict(licenseName, 1);				
							}
				} 
				
		}
	}
}
