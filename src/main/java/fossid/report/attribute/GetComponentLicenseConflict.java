package fossid.report.attribute;

import fossid.report.values.BillOfMaterialsValues;
import fossid.report.values.CompareComponentLicenseAttributeValues;
import fossid.report.values.CompareLicenseAttributeValues;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GetComponentLicenseConflict {
	private final CompareComponentLicenseAttributeValues comparecomponentLicenseAttributeValues = CompareComponentLicenseAttributeValues.getInstance();
	private final CompareLicenseAttributeValues compareLicenseAttributes = CompareLicenseAttributeValues.getInstance();
	private final BillOfMaterialsValues bomValues = BillOfMaterialsValues.getInstance();
	private final Logger logger = LogManager.getLogger(GetComponentLicenseConflict.class);

	public void componentLicenseConflict(String licenseName) {
		if(!(comparecomponentLicenseAttributeValues.getCoAttribute1().equals(compareLicenseAttributes.getCoAttribute1()) ||
			comparecomponentLicenseAttributeValues.getCoAttribute1().equals("X") || compareLicenseAttributes.getCoAttribute1().equals("X"))) {
			logger.debug("component license conflict1:" + comparecomponentLicenseAttributeValues.getCoAttribute1() + "  " + compareLicenseAttributes.getCoAttribute1());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
			
		if(!(comparecomponentLicenseAttributeValues.getCoAttribute2().equals(compareLicenseAttributes.getCoAttribute2()) ||
				comparecomponentLicenseAttributeValues.getCoAttribute2().equals("M") || compareLicenseAttributes.getCoAttribute2().equals("M"))) {
			logger.debug("component license conflict2:" + comparecomponentLicenseAttributeValues.getCoAttribute2() + "  " + compareLicenseAttributes.getCoAttribute2());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}

		if(!(comparecomponentLicenseAttributeValues.getCoAttribute3().equals(compareLicenseAttributes.getCoAttribute3()) ||
				comparecomponentLicenseAttributeValues.getCoAttribute3().equals("M") || compareLicenseAttributes.getCoAttribute3().equals("M"))) {
			logger.debug("component license conflict3:" + comparecomponentLicenseAttributeValues.getCoAttribute3() + "  " + compareLicenseAttributes.getCoAttribute3());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
				
		if(!(comparecomponentLicenseAttributeValues.getCoAttribute4().equals(compareLicenseAttributes.getCoAttribute4()) ||
				comparecomponentLicenseAttributeValues.getCoAttribute4().equals("M") || compareLicenseAttributes.getCoAttribute4().equals("M"))) {
			logger.debug("component license conflict4:" + comparecomponentLicenseAttributeValues.getCoAttribute4() + "  " + compareLicenseAttributes.getCoAttribute4());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
				
		if(!(comparecomponentLicenseAttributeValues.getCoAttribute5().equals(compareLicenseAttributes.getCoAttribute5()) ||
				comparecomponentLicenseAttributeValues.getCoAttribute5().equals("M") || compareLicenseAttributes.getCoAttribute5().equals("M"))) {
			logger.debug("component license conflict5:" + comparecomponentLicenseAttributeValues.getCoAttribute5() + "  " + compareLicenseAttributes.getCoAttribute5());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
			
		if(!(comparecomponentLicenseAttributeValues.getCoAttribute6().equals(compareLicenseAttributes.getCoAttribute6()) ||
				comparecomponentLicenseAttributeValues.getCoAttribute6().equals("X") || compareLicenseAttributes.getCoAttribute6().equals("X"))) {
			logger.debug("component license conflict6:" + comparecomponentLicenseAttributeValues.getCoAttribute6() + "  " + compareLicenseAttributes.getCoAttribute6());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
				
		if(!(comparecomponentLicenseAttributeValues.getCoAttribute7().equals(compareLicenseAttributes.getCoAttribute7()) ||
				comparecomponentLicenseAttributeValues.getCoAttribute7().equals("X") || compareLicenseAttributes.getCoAttribute7().equals("X"))) {
			logger.debug("component license conflict7:" + comparecomponentLicenseAttributeValues.getCoAttribute7() + "  " + compareLicenseAttributes.getCoAttribute7());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
							
		if(!(comparecomponentLicenseAttributeValues.getCoAttribute8().equals(compareLicenseAttributes.getCoAttribute8()) ||
				comparecomponentLicenseAttributeValues.getCoAttribute8().equals("X") || compareLicenseAttributes.getCoAttribute8().equals("X"))) {
			logger.debug("component license conflict8:" + comparecomponentLicenseAttributeValues.getCoAttribute8() + "  " + compareLicenseAttributes.getCoAttribute8());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
				
		if(!(comparecomponentLicenseAttributeValues.getCoAttribute9().equals(compareLicenseAttributes.getCoAttribute9()) ||
				comparecomponentLicenseAttributeValues.getCoAttribute9().equals("X") || compareLicenseAttributes.getCoAttribute9().equals("X"))) {
			logger.debug("component license conflict9:" + comparecomponentLicenseAttributeValues.getCoAttribute9() + "  " + compareLicenseAttributes.getCoAttribute9());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
						
		if(!(comparecomponentLicenseAttributeValues.getCoAttribute10().equals(compareLicenseAttributes.getCoAttribute10()) ||
				comparecomponentLicenseAttributeValues.getCoAttribute10().equals("X") || compareLicenseAttributes.getCoAttribute10().equals("X"))) {
			logger.debug("component license conflict10:" + comparecomponentLicenseAttributeValues.getCoAttribute10() + "  " + compareLicenseAttributes.getCoAttribute10());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
			
		if(!(comparecomponentLicenseAttributeValues.getCoAttribute11().equals(compareLicenseAttributes.getCoAttribute11()) ||
				comparecomponentLicenseAttributeValues.getCoAttribute11().equals("X") || compareLicenseAttributes.getCoAttribute11().equals("X"))) {
			logger.debug("component license conflict11:" + comparecomponentLicenseAttributeValues.getCoAttribute11() + "  " + compareLicenseAttributes.getCoAttribute11());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
				
		if(!(comparecomponentLicenseAttributeValues.getCoAttribute12().equals(compareLicenseAttributes.getCoAttribute12()) ||
				comparecomponentLicenseAttributeValues.getCoAttribute12().equals("X") || compareLicenseAttributes.getCoAttribute12().equals("X"))) {
			logger.debug("component license conflict12:" + comparecomponentLicenseAttributeValues.getCoAttribute12() + "  " + compareLicenseAttributes.getCoAttribute12());

			bomValues.setComponentLicenseConflict(licenseName, 1);
		}
		
		if(comparecomponentLicenseAttributeValues.getCoAttribute2().equals("O") &&
				!(comparecomponentLicenseAttributeValues.getCoAttribute13().equals("X")) &&
				compareLicenseAttributes.getCoAttribute2().equals("O") &&
				!(compareLicenseAttributes.getCoAttribute13().equals("X"))) {
					if(!(compareLicenseAttributes.getCoAttribute13().equals(comparecomponentLicenseAttributeValues.getCoAttribute13()))) {
						logger.debug("component license conflict13:" + comparecomponentLicenseAttributeValues.getCoAttribute13() + "  " + compareLicenseAttributes.getCoAttribute13());

						bomValues.setComponentLicenseConflict(licenseName, 1);
					}
		} 
		
	}
}

