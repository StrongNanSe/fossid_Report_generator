package fossid.report.attribute;

import fossid.report.values.CompareComponentLicenseAttributeValues;
import fossid.report.values.CompareLicenseAttributeValues;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class SetCompareComponentLicenseAttribute {
	private static final Logger logger = LogManager.getLogger(SetCompareComponentLicenseAttribute.class);

	// this method is to set license to compare a license with project license
	public static void setCompareAttribute(String licenseName) {
		CompareComponentLicenseAttributeValues compareComponentLicenseAttributes = CompareComponentLicenseAttributeValues.getInstance();
		CompareLicenseAttributeValues compareLicenseAttributes = CompareLicenseAttributeValues.getInstance();
		String jsonPath = System.getProperty("user.dir") + "\\license_Attribute.json";
		FileReader resources = null;

		try {
			resources = new FileReader(jsonPath);

			JSONParser parser = new JSONParser();
			Object object1 = parser.parse(resources);
			JSONObject object2 = (JSONObject) object1;
			JSONArray array1 = (JSONArray) object2.get("licenseattribute");

			int tfCount = 0;

			for (Object o : array1) {
				JSONObject temp1 = (JSONObject) o;
				if (temp1.get("LICENSE_NAME").toString().equals(licenseName)) {
					tfCount++;
				}
			}

			if(tfCount < 1) {
				if(!compareLicenseAttributes.getOutOfLicense().contains(licenseName)){
					compareLicenseAttributes.setOutOfLicense(licenseName);

					logger.debug("Unspecified license : " + licenseName);
				}
				licenseName = "Unspecified";
			}

			for (Object o : array1) {
				JSONObject temp1 = (JSONObject) o;
				if (temp1.get("LICENSE_NAME").toString().equals(licenseName)) {
					JSONArray temp2 = (JSONArray) temp1.get("DATA");
					JSONObject temp3 = (JSONObject) temp2.get(0);

					compareComponentLicenseAttributes.setCoAttribute1(temp3.get("1").toString());
					compareComponentLicenseAttributes.setCoAttribute2(temp3.get("2").toString());
					compareComponentLicenseAttributes.setCoAttribute3(temp3.get("3").toString());
					compareComponentLicenseAttributes.setCoAttribute4(temp3.get("4").toString());
					compareComponentLicenseAttributes.setCoAttribute5(temp3.get("5").toString());
					compareComponentLicenseAttributes.setCoAttribute6(temp3.get("6").toString());
					compareComponentLicenseAttributes.setCoAttribute7(temp3.get("7").toString());
					compareComponentLicenseAttributes.setCoAttribute8(temp3.get("8").toString());
					compareComponentLicenseAttributes.setCoAttribute9(temp3.get("9").toString());
					compareComponentLicenseAttributes.setCoAttribute10(temp3.get("10").toString());
					compareComponentLicenseAttributes.setCoAttribute11(temp3.get("11").toString());
					compareComponentLicenseAttributes.setCoAttribute12(temp3.get("12").toString());
					compareComponentLicenseAttributes.setCoAttribute13(temp3.get("13").toString());
				}
			}
		} catch (IOException | ParseException e) {
			logger.error("Exception Message", e);
		} finally {
			try {
				if (resources != null) {
					resources.close();
				}
			} catch (Exception e) {
				logger.error("Exception Message", e);
			}
		}
	}
}
