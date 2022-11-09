package fossid.report.main;

import fossid.report.values.CompareLicenseAttributeValues;
import fossid.report.values.LoginValues;
import fossid.report.values.ProjectValues;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PrintInfo {
	private static final Logger logger = LogManager.getLogger(PrintInfo.class);
	public static void startFOSSID() {
		logger.info("Start Report Generator");
		logger.info("");
		logger.info("******                                 *****    ****");
		logger.info("******                                 *****    ****");
		logger.info("*                                        *      *    *");
		logger.info("*                                        *      *     *");
		logger.info("******    ****     *****     *****       *      *     *");
		logger.info("*        *    *   *         *            *      *     *");
		logger.info("*        *    *    *****     *****       *      *    *");
		logger.info("*        *    *         *         *      *      *   *");
		logger.info("*         ****     *****     *****     *****    ****");
		logger.info("");
	}
	
	public static void endFOSSID() {
		CompareLicenseAttributeValues compareLicenseAttributes = CompareLicenseAttributeValues.getInstance();

		logger.info("");
		logger.info("Finish Report Generator");
		if(compareLicenseAttributes.getOutOfLicense().size() == 1) {
			logger.info("");
			logger.info("The license compatibility is reviewed by \"license_Attribute.json\" file");
			logger.info("Below license is not in the \"license_Attribute.json\" file and reviewed as \"Unspecified\" license");
			logger.info("Please, review below license or add license attributes in the \"license_Attribute.json\" file and run exporting report again");
			for(int i = 0; i < compareLicenseAttributes.getOutOfLicense().size(); i++) {
				logger.info("- " + compareLicenseAttributes.getOutOfLicense().get(i));
			}
		}
		if(compareLicenseAttributes.getOutOfLicense().size() > 2) {
			logger.info("");
			logger.info("The license compatibility is reviewed by \"license_Attribute.json\" file");
			logger.info("Below licenses are not in the \"license_Attribute.json\" file and reviewed as \"Unspecified\" license");
			logger.info("Please, review below licenses in the report or add license attributes in the \"license_Attribute.json\" file and run exporting report again");
			for(int i = 0; i < compareLicenseAttributes.getOutOfLicense().size(); i++) {
				logger.info("- " + compareLicenseAttributes.getOutOfLicense().get(i));
			}
		}
	}

	public static void printInfo() {
		LoginValues lvalues = new LoginValues();
		ProjectValues pValues = new ProjectValues();

		logger.info("");
		logger.info("Server URL: " + lvalues.getServerApiUri());
		logger.info("UserName: " + lvalues.getUsername());
		logger.info("ApiKey: " + "*******");
		logger.info("Project Name/Code: " + pValues.getProjectName() + " / " + pValues.getProjectId());
		logger.info("Scan Name/Code: " + pValues.getVersionName() + " / " + pValues.getVersionId());
		logger.info("Project License: " + pValues.getProjectLicense());
		logger.info("");
	}

}
