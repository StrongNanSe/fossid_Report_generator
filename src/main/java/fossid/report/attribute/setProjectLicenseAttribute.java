package fossid.report.attribute;

import fossid.report.values.ProjectLicenseAttributeValues;
import fossid.report.values.ProjectValues;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

;

public class SetProjectLicenseAttribute {
	private final Logger logger = LogManager.getLogger(SetProjectLicenseAttribute.class);
	public void getInfo() {
		
		projectAttribute();		
		
	}

	// this method is to set project license attribute
	private void projectAttribute() {
		ProjectLicenseAttributeValues licenseAttributes = ProjectLicenseAttributeValues.getInstance();
		ProjectValues pValues = ProjectValues.getInstance();
		String jsonPath = System.getProperty("user.dir") + "\\license_Attribute.json";
		FileReader resources = null;

		try {
			resources = new FileReader(jsonPath);

			JSONParser parser = new JSONParser();
			Object object1 = parser.parse(resources);
			JSONObject object2 = (JSONObject) object1;
			JSONArray array1 = (JSONArray) object2.get("licenseattribute");

			for (Object o : array1) {
				JSONObject temp1 = (JSONObject) o;
				if (temp1.get("LICENSE_NAME").toString().equals(pValues.getProjectLicense())) {
					JSONArray temp2 = (JSONArray) temp1.get("DATA");
					JSONObject temp3 = (JSONObject) temp2.get(0);

					licenseAttributes.setPjAttribute1(temp3.get("1").toString());
					licenseAttributes.setPjAttribute2(temp3.get("2").toString());
					licenseAttributes.setPjAttribute3(temp3.get("3").toString());
					licenseAttributes.setPjAttribute4(temp3.get("4").toString());
					licenseAttributes.setPjAttribute5(temp3.get("5").toString());
					licenseAttributes.setPjAttribute6(temp3.get("6").toString());
					licenseAttributes.setPjAttribute7(temp3.get("7").toString());
					licenseAttributes.setPjAttribute8(temp3.get("8").toString());
					licenseAttributes.setPjAttribute9(temp3.get("9").toString());
					licenseAttributes.setPjAttribute10(temp3.get("10").toString());
					licenseAttributes.setPjAttribute11(temp3.get("11").toString());
					licenseAttributes.setPjAttribute12(temp3.get("12").toString());
					licenseAttributes.setPjAttribute13(temp3.get("13").toString());
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
