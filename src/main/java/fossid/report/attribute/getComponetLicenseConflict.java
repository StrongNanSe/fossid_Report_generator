package fossid.report.attribute;

import fossid.report.values.billofmaterialsValues;
import fossid.report.values.compareComponentLicenseAttributeValues;
import fossid.report.values.compareLicenseAttributeValues;
import fossid.report.values.projectLicenseConflictAttributeValues;

public class getComponetLicenseConflict {
	
	public void componentLicenseConflict(String licenseName) {
		
		compareComponentLicenseAttributeValues comparecomponentLicenseAttributeValues = compareComponentLicenseAttributeValues.getInstance();
		compareLicenseAttributeValues compareLicenseAttributes = compareLicenseAttributeValues.getInstance();
		projectLicenseConflictAttributeValues projectlicenseconflictValues = projectLicenseConflictAttributeValues.getInstance();
		billofmaterialsValues bomValues = billofmaterialsValues.getInstance();	
	
		if(comparecomponentLicenseAttributeValues.getcoAttribute1().toString().equals(compareLicenseAttributes.getcoAttribute1().toString()) ||
			comparecomponentLicenseAttributeValues.getcoAttribute1().toString().equals("X") || compareLicenseAttributes.getcoAttribute1().toString().equals("X")) {
		   
		} else {
			//System.out.println("component license conflict1:" + comparecomponentLicenseAttributeValues.getcoAttribute1() + "  " + compareLicenseAttributes.getcoAttribute1());
			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
			
		if(comparecomponentLicenseAttributeValues.getcoAttribute2().toString().equals(compareLicenseAttributes.getcoAttribute2().toString()) ||
				comparecomponentLicenseAttributeValues.getcoAttribute2().toString().equals("M") || compareLicenseAttributes.getcoAttribute2().toString().equals("M")) {
		} else {
			//System.out.println("component license conflict2:" + comparecomponentLicenseAttributeValues.getcoAttribute2() + "  " + compareLicenseAttributes.getcoAttribute2());
			bomValues.setComponentLicenseConflict(licenseName, 1);			
		}										
				
		if(comparecomponentLicenseAttributeValues.getcoAttribute3().toString().equals(compareLicenseAttributes.getcoAttribute3().toString()) ||
				comparecomponentLicenseAttributeValues.getcoAttribute3().toString().equals("M") || compareLicenseAttributes.getcoAttribute3().toString().equals("M")) {
		} else {
			//System.out.println("component license conflict3:" + comparecomponentLicenseAttributeValues.getcoAttribute3() + "  " + compareLicenseAttributes.getcoAttribute3());
			bomValues.setComponentLicenseConflict(licenseName, 1);			
		}				
				
		if(comparecomponentLicenseAttributeValues.getcoAttribute4().toString().equals(compareLicenseAttributes.getcoAttribute4().toString()) ||
				comparecomponentLicenseAttributeValues.getcoAttribute4().toString().equals("M") || compareLicenseAttributes.getcoAttribute4().toString().equals("M")) {
		} else {
			//System.out.println("component license conflict4:" + comparecomponentLicenseAttributeValues.getcoAttribute4() + "  " + compareLicenseAttributes.getcoAttribute4());
			bomValues.setComponentLicenseConflict(licenseName, 1);			
		}
				
		if(comparecomponentLicenseAttributeValues.getcoAttribute5().toString().equals(compareLicenseAttributes.getcoAttribute5().toString()) ||
				comparecomponentLicenseAttributeValues.getcoAttribute5().toString().equals("M") || compareLicenseAttributes.getcoAttribute5().toString().equals("M")) {					
		} else {
			//System.out.println("component license conflict5:" + comparecomponentLicenseAttributeValues.getcoAttribute5() + "  " + compareLicenseAttributes.getcoAttribute5());
			bomValues.setComponentLicenseConflict(licenseName, 1);
		}								
			
		if(comparecomponentLicenseAttributeValues.getcoAttribute6().toString().equals(compareLicenseAttributes.getcoAttribute6().toString()) ||
				comparecomponentLicenseAttributeValues.getcoAttribute6().toString().equals("X") || compareLicenseAttributes.getcoAttribute6().toString().equals("X")) {					
		} else {
			//System.out.println("component license conflict6:" + comparecomponentLicenseAttributeValues.getcoAttribute6() + "  " + compareLicenseAttributes.getcoAttribute6());
			bomValues.setComponentLicenseConflict(licenseName, 1);
		}				
				
		if(comparecomponentLicenseAttributeValues.getcoAttribute7().toString().equals(compareLicenseAttributes.getcoAttribute7().toString()) ||
				comparecomponentLicenseAttributeValues.getcoAttribute7().toString().equals("X") || compareLicenseAttributes.getcoAttribute7().toString().equals("X")) {					
		} else {
			//System.out.println("component license conflict7:" + comparecomponentLicenseAttributeValues.getcoAttribute7() + "  " + compareLicenseAttributes.getcoAttribute6());
			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
							
		if(comparecomponentLicenseAttributeValues.getcoAttribute8().toString().equals(compareLicenseAttributes.getcoAttribute8().toString()) ||
				comparecomponentLicenseAttributeValues.getcoAttribute8().toString().equals("X") || compareLicenseAttributes.getcoAttribute8().toString().equals("X")) {
			
		} else {
			//System.out.println("component license conflict8:" + comparecomponentLicenseAttributeValues.getcoAttribute8() + "  " + compareLicenseAttributes.getcoAttribute8());
			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
				
		if(comparecomponentLicenseAttributeValues.getcoAttribute9().toString().equals(compareLicenseAttributes.getcoAttribute9().toString()) ||
				comparecomponentLicenseAttributeValues.getcoAttribute9().toString().equals("X") || compareLicenseAttributes.getcoAttribute9().toString().equals("X")) {
		} else {
			//System.out.println("component license conflict9:" + comparecomponentLicenseAttributeValues.getcoAttribute9() + "  " + compareLicenseAttributes.getcoAttribute9());
			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
						
		if(comparecomponentLicenseAttributeValues.getcoAttribute10().toString().equals(compareLicenseAttributes.getcoAttribute10().toString()) ||
				comparecomponentLicenseAttributeValues.getcoAttribute10().toString().equals("X") || compareLicenseAttributes.getcoAttribute10().toString().equals("X")) {
		} else {
			//System.out.println("component license conflict10:" + comparecomponentLicenseAttributeValues.getcoAttribute10() + "  " + compareLicenseAttributes.getcoAttribute10());
			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
			
		if(comparecomponentLicenseAttributeValues.getcoAttribute11().toString().equals(compareLicenseAttributes.getcoAttribute11().toString()) ||
				comparecomponentLicenseAttributeValues.getcoAttribute11().toString().equals("X") || compareLicenseAttributes.getcoAttribute11().toString().equals("X")) {
		} else {
			//System.out.println("component license conflict11:" + comparecomponentLicenseAttributeValues.getcoAttribute11() + "  " + compareLicenseAttributes.getcoAttribute11());
			bomValues.setComponentLicenseConflict(licenseName, 1);
		}				
				
		if(comparecomponentLicenseAttributeValues.getcoAttribute12().toString().equals(compareLicenseAttributes.getcoAttribute12().toString()) ||
				comparecomponentLicenseAttributeValues.getcoAttribute12().toString().equals("X") || compareLicenseAttributes.getcoAttribute12().toString().equals("X")) {
		} else {
			//System.out.println("component license conflict12:" + comparecomponentLicenseAttributeValues.getcoAttribute12() + "  " + compareLicenseAttributes.getcoAttribute12());
			bomValues.setComponentLicenseConflict(licenseName, 1);
		}	
		
		
		if(comparecomponentLicenseAttributeValues.getcoAttribute2().toString().equals("O") && 
				!(comparecomponentLicenseAttributeValues.getcoAttribute13().toString().equals("X")) &&
				compareLicenseAttributes.getcoAttribute2().toString().equals("O") && 
				!(compareLicenseAttributes.getcoAttribute13().toString().equals("X"))) {
			
					if(!(compareLicenseAttributes.getcoAttribute13().toString().equals(comparecomponentLicenseAttributeValues.getcoAttribute13().toString()))) {
						//System.out.println("component license conflict13:" + comparecomponentLicenseAttributeValues.getcoAttribute13() + "  " + compareLicenseAttributes.getcoAttribute13());
						bomValues.setComponentLicenseConflict(licenseName, 1);				
					}
		} 
		
	}
}

