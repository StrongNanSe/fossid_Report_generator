package fossid.report.attribute;

import fossid.report.values.BillOfMaterialsValues;
import fossid.report.values.CompareLicenseAttributeValues;
import fossid.report.values.ProjectLicenseAttributeValues;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GetProjectLicenseConflict {
	private final ProjectLicenseAttributeValues projectLicenseAttributes = ProjectLicenseAttributeValues.getInstance();
	private final CompareLicenseAttributeValues compareLicenseAttributes = CompareLicenseAttributeValues.getInstance();
	private final BillOfMaterialsValues bomValues = BillOfMaterialsValues.getInstance();
	private final Logger logger = LogManager.getLogger(GetProjectLicenseConflict.class);

	public void projectLicenseConflict(String licenseName) {
		logger.debug("licenseName: " + licenseName);

		// if project license is proprietary		
		if(projectLicenseAttributes.getPjAttribute2().equals("X")) {
			if(compareLicenseAttributes.getCoAttribute1().equals("O")
					/*
					&& !compareLicenseAttributes.getCoAttribute13().equals("X")
					&& !projectLicenseAttributes.getPjAttribute13().equals("X")
					*/
				)
			{
				if(!(projectLicenseAttributes.getPjAttribute2().equals(compareLicenseAttributes.getCoAttribute2()) ||
						projectLicenseAttributes.getPjAttribute2().equals("M") || compareLicenseAttributes.getCoAttribute2().equals("M"))) {
					logger.debug("proprietary_project conflict2:" + projectLicenseAttributes.getPjAttribute2() + "  " + compareLicenseAttributes.getCoAttribute2());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getPjAttribute3().equals(compareLicenseAttributes.getCoAttribute3()) ||
						projectLicenseAttributes.getPjAttribute3().equals("M") || compareLicenseAttributes.getCoAttribute3().equals("M"))) {
					logger.debug("proprietary_project conflict3:" + projectLicenseAttributes.getPjAttribute3() + "  " + compareLicenseAttributes.getCoAttribute3());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getPjAttribute4().equals(compareLicenseAttributes.getCoAttribute4()) ||
						projectLicenseAttributes.getPjAttribute4().equals("M") || compareLicenseAttributes.getCoAttribute4().equals("M"))) {
					logger.debug("proprietary_project conflict4:" + projectLicenseAttributes.getPjAttribute4() + "  " + compareLicenseAttributes.getCoAttribute4());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getpjAttribute5().equals(compareLicenseAttributes.getCoAttribute5()) ||
						projectLicenseAttributes.getpjAttribute5().equals("M") || compareLicenseAttributes.getCoAttribute5().equals("M"))) {
					logger.debug("proprietary_project conflict5:" + projectLicenseAttributes.getpjAttribute5() + "  " + compareLicenseAttributes.getCoAttribute5());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getPjAttribute6().equals(compareLicenseAttributes.getCoAttribute6()) ||
						projectLicenseAttributes.getPjAttribute6().equals("X") || compareLicenseAttributes.getCoAttribute6().equals("X"))) {
					logger.debug("proprietary_project conflict6:" + projectLicenseAttributes.getPjAttribute6() + "  " + compareLicenseAttributes.getCoAttribute6());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
								
				if(!(projectLicenseAttributes.getPjAttribute7().equals(compareLicenseAttributes.getCoAttribute7()) ||
						projectLicenseAttributes.getPjAttribute7().equals("X") || compareLicenseAttributes.getCoAttribute7().equals("X"))) {
					logger.debug("proprietary_project conflict7:" + projectLicenseAttributes.getPjAttribute7() + "  " + compareLicenseAttributes.getCoAttribute7());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
			
				if(!(projectLicenseAttributes.getPjAttribute8().equals(compareLicenseAttributes.getCoAttribute8()) ||
						projectLicenseAttributes.getPjAttribute8().equals("X") || compareLicenseAttributes.getCoAttribute8().equals("X"))) {
					logger.debug("proprietary_project conflict8:" + projectLicenseAttributes.getPjAttribute8() + "  " + compareLicenseAttributes.getCoAttribute8());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}

				if(!(projectLicenseAttributes.getPjAttribute9().equals(compareLicenseAttributes.getCoAttribute9()) ||
						projectLicenseAttributes.getPjAttribute9().equals("X") || compareLicenseAttributes.getCoAttribute9().equals("X"))) {
					logger.debug("proprietary_project conflict9:" + projectLicenseAttributes.getPjAttribute9() + "  " + compareLicenseAttributes.getCoAttribute9());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}

				if(!(projectLicenseAttributes.getPjAttribute10().equals(compareLicenseAttributes.getCoAttribute10()) ||
						projectLicenseAttributes.getPjAttribute10().equals("X") || compareLicenseAttributes.getCoAttribute10().equals("X"))) {
					logger.debug("proprietary_project conflict10:" + projectLicenseAttributes.getPjAttribute10() + "  " + compareLicenseAttributes.getCoAttribute10());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}

				if(!(projectLicenseAttributes.getPjAttribute11().equals(compareLicenseAttributes.getCoAttribute11()) ||
						projectLicenseAttributes.getPjAttribute11().equals("X") || compareLicenseAttributes.getCoAttribute11().equals("X"))) {
					logger.debug("proprietary_project conflict11:" + projectLicenseAttributes.getPjAttribute11() + "  " + compareLicenseAttributes.getCoAttribute11());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}

				if(!(projectLicenseAttributes.getPjAttribute12().equals(compareLicenseAttributes.getCoAttribute12()) ||
						projectLicenseAttributes.getPjAttribute12().equals("X") || compareLicenseAttributes.getCoAttribute12().equals("X"))) {
					logger.debug("proprietary_project conflict12:" + projectLicenseAttributes.getPjAttribute12() + "  " + compareLicenseAttributes.getCoAttribute12());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
			}			
		}
		
		// if project license is open source
		if(!projectLicenseAttributes.getPjAttribute2().equals("X")) {
			
				if(!(projectLicenseAttributes.getPjAttribute1().equals(compareLicenseAttributes.getCoAttribute1()) ||
						projectLicenseAttributes.getPjAttribute1().equals("X") || compareLicenseAttributes.getCoAttribute1().equals("X"))) {
					logger.debug("open source_project conflict1:" + projectLicenseAttributes.getPjAttribute1() + "  " + compareLicenseAttributes.getCoAttribute1());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getPjAttribute2().equals(compareLicenseAttributes.getCoAttribute2()) ||
						projectLicenseAttributes.getPjAttribute2().equals("M") || compareLicenseAttributes.getCoAttribute2().equals("M"))) {
					logger.debug("open source_project conflict2:" + projectLicenseAttributes.getPjAttribute2() + "  " + compareLicenseAttributes.getCoAttribute2());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getPjAttribute3().equals(compareLicenseAttributes.getCoAttribute3()) ||
						projectLicenseAttributes.getPjAttribute3().equals("M") || compareLicenseAttributes.getCoAttribute3().equals("M"))) {
					logger.debug("open source_project conflict3:" + projectLicenseAttributes.getPjAttribute3() + "  " + compareLicenseAttributes.getCoAttribute3());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getPjAttribute4().equals(compareLicenseAttributes.getCoAttribute4()) ||
						projectLicenseAttributes.getPjAttribute4().equals("M") || compareLicenseAttributes.getCoAttribute4().equals("M"))) {
					logger.debug("open source_project conflict4:" + projectLicenseAttributes.getPjAttribute4() + "  " + compareLicenseAttributes.getCoAttribute4());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getpjAttribute5().equals(compareLicenseAttributes.getCoAttribute5()) ||
						projectLicenseAttributes.getpjAttribute5().equals("M") || compareLicenseAttributes.getCoAttribute5().equals("M"))) {
					logger.debug("open source_project conflict5:" + projectLicenseAttributes.getpjAttribute5() + "  " + compareLicenseAttributes.getCoAttribute5());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getPjAttribute6().equals(compareLicenseAttributes.getCoAttribute6()) ||
						projectLicenseAttributes.getPjAttribute6().equals("X") || compareLicenseAttributes.getCoAttribute6().equals("X"))) {
					logger.debug("open source_project conflict6:" + projectLicenseAttributes.getPjAttribute6() + "  " + compareLicenseAttributes.getCoAttribute6());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getPjAttribute7().equals(compareLicenseAttributes.getCoAttribute7()) ||
						projectLicenseAttributes.getPjAttribute7().equals("X") || compareLicenseAttributes.getCoAttribute7().equals("X"))) {
					logger.debug("open source_project conflict7:" + projectLicenseAttributes.getPjAttribute7() + "  " + compareLicenseAttributes.getCoAttribute7());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
							
				if(!(projectLicenseAttributes.getPjAttribute8().equals(compareLicenseAttributes.getCoAttribute8()) ||
						projectLicenseAttributes.getPjAttribute8().equals("X") || compareLicenseAttributes.getCoAttribute8().equals("X"))) {
					logger.debug("open source_project conflict8:" + projectLicenseAttributes.getPjAttribute8() + "  " + compareLicenseAttributes.getCoAttribute8());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getPjAttribute9().equals(compareLicenseAttributes.getCoAttribute9()) ||
						projectLicenseAttributes.getPjAttribute9().equals("X") || compareLicenseAttributes.getCoAttribute9().equals("X"))) {
					logger.debug("open source_project conflict9:" + projectLicenseAttributes.getPjAttribute9() + "  " + compareLicenseAttributes.getCoAttribute9());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
						
				if(!(projectLicenseAttributes.getPjAttribute10().equals(compareLicenseAttributes.getCoAttribute10()) ||
						projectLicenseAttributes.getPjAttribute10().equals("X") || compareLicenseAttributes.getCoAttribute10().equals("X"))) {
					logger.debug("open source_project conflict10:" + projectLicenseAttributes.getPjAttribute10() + "  " + compareLicenseAttributes.getCoAttribute10());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
			
				if(!(projectLicenseAttributes.getPjAttribute11().equals(compareLicenseAttributes.getCoAttribute11()) ||
						projectLicenseAttributes.getPjAttribute11().equals("X") || compareLicenseAttributes.getCoAttribute11().equals("X"))) {
					logger.debug("open source_project conflict11:" + projectLicenseAttributes.getPjAttribute11() + "  " + compareLicenseAttributes.getCoAttribute11());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(!(projectLicenseAttributes.getPjAttribute12().equals(compareLicenseAttributes.getCoAttribute12()) ||
						projectLicenseAttributes.getPjAttribute12().equals("X") || compareLicenseAttributes.getCoAttribute12().equals("X"))) {
					logger.debug("open source_project conflict12:" + projectLicenseAttributes.getPjAttribute12() + "  " + compareLicenseAttributes.getCoAttribute12());

					bomValues.setProjectLicenseConflict(licenseName, 1);
				}
				
				if(projectLicenseAttributes.getPjAttribute2().equals("O") &&
						!(projectLicenseAttributes.getPjAttribute13().equals("X")) &&
						compareLicenseAttributes.getCoAttribute2().equals("O") &&
						!(compareLicenseAttributes.getCoAttribute13().equals("X"))) {
							if(!(projectLicenseAttributes.getPjAttribute13().equals(compareLicenseAttributes.getCoAttribute13()))) {
								logger.debug("open source_Project license conflict13:" + projectLicenseAttributes.getPjAttribute13() + "  " + compareLicenseAttributes.getCoAttribute13());

								bomValues.setComponentLicenseConflict(licenseName, 1);
							}
				} 
				
		}
	}
}
