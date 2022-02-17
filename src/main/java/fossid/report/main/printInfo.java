package fossid.report.main;

import fossid.report.values.compareLicenseAttributeValues;
import fossid.report.values.loginValues;
import fossid.report.values.projectValues;

public class printInfo {
	public static void startFOSSID() {
		System.out.println("Start Report Generator");
		System.out.println();
		System.out.println("******                                 *****    ****");
		System.out.println("*                                        *      *   *");
		System.out.println("*                                        *      *    *");
		System.out.println("*                                        *      *     *");
		System.out.println("******    ****     *****     *****       *      *     *");
		System.out.println("*        *    *   *         *            *      *     *");
		System.out.println("*        *    *    *****     *****       *      *    *");
		System.out.println("*        *    *         *         *      *      *   *");		
		System.out.println("*         ****     *****     *****     *****    ****");
		System.out.println();
	}
	
	public static void endFOSSID() {
		compareLicenseAttributeValues compareLicenseAttributes = compareLicenseAttributeValues.getInstance();
		
		System.out.println();
		System.out.println("Finish Report Generator");
		if(compareLicenseAttributes.getoutofLicense().size() == 1) {
			System.out.println();
			System.out.println("The license compatibility is reviewed by \"license_Attribute.json\" file");
			System.out.println("Below license is not in the \"license_Attirbute.json\" file and reviewed as \"Unspecified\" license");
			System.out.println("Please, review below license or add license attirbutes in the \'license_Attribute.json\" file and run exporting report again");
			for(int i = 0; i < compareLicenseAttributes.getoutofLicense().size(); i++) {
				System.out.println("- " + compareLicenseAttributes.getoutofLicense().get(i));
			}				
		}
		if(compareLicenseAttributes.getoutofLicense().size() > 2) {
			System.out.println();
			System.out.println("The license compatibility is reviewed by \"license_Attribute.json\" file");
			System.out.println("Below licenses are not in the \"license_Attirbute.json\" file and reviewed as \"Unspecified\" license");
			System.out.println("Please, review below licenses in the report or add license attirbutes in the \'license_Attribute.json\" file and run exporting report again");
			for(int i = 0; i < compareLicenseAttributes.getoutofLicense().size(); i++) {
				System.out.println("- " + compareLicenseAttributes.getoutofLicense().get(i));
			}				
		}
	}

	public static void printinfo() {
		loginValues lvalues = new loginValues();
		projectValues pvalues = new projectValues();
		
		System.out.println();
		System.out.println("Server URL: " + lvalues.getServerApiUri());
		System.out.println("UserName: " + lvalues.getUsername());
		System.out.println("ApiKey: " + "*******");
		System.out.println("Project Name/Code: " + pvalues.getProjectName() + " / " + pvalues.getProjectId());
		System.out.println("Scan Name/Code: " + pvalues.getVersionName() + " / " + pvalues.getVersionId());
		System.out.println("Project License: " + pvalues.getProjectLicense());
		System.out.println();
	}

}
