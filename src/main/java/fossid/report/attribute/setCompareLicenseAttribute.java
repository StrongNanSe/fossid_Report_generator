package fossid.report.attribute;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fossid.report.values.CompareLicenseAttributeValues;

public class SetCompareLicenseAttribute {
	private static final Logger logger = LogManager.getLogger(SetCompareLicenseAttribute.class);
	   // this method is to set license to compare a license with project license
	public static void setCompareAttribute(String licenseName) {
		CompareLicenseAttributeValues compareLicenseAttributes = CompareLicenseAttributeValues.getInstance();
		InputStream inputStream = null;

		try {
			inputStream = SetCompareLicenseAttribute.class.getResourceAsStream("/license_Attribute.json");
			String attirbute = IOUtils.toString(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);

			JSONParser parser = new JSONParser();
			Object object1 = parser.parse(attirbute);
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
				}
				licenseName = "Unspecified";
			}

			for (Object o : array1) {
				JSONObject temp1 = (JSONObject) o;
				if (temp1.get("LICENSE_NAME").toString().equals(licenseName)) {
					JSONArray temp2 = (JSONArray) temp1.get("DATA");
					JSONObject temp3 = (JSONObject) temp2.get(0);

					compareLicenseAttributes.setCoAttribute1(temp3.get("1").toString());
					compareLicenseAttributes.setCoAttribute2(temp3.get("2").toString());
					compareLicenseAttributes.setCoAttribute3(temp3.get("3").toString());
					compareLicenseAttributes.setCoAttribute4(temp3.get("4").toString());
					compareLicenseAttributes.setCoAttribute5(temp3.get("5").toString());
					compareLicenseAttributes.setCoAttribute6(temp3.get("6").toString());
					compareLicenseAttributes.setCoAttribute7(temp3.get("7").toString());
					compareLicenseAttributes.setCoAttribute8(temp3.get("8").toString());
					compareLicenseAttributes.setCoAttribute9(temp3.get("9").toString());
					compareLicenseAttributes.setCoAttribute10(temp3.get("10").toString());
					compareLicenseAttributes.setCoAttribute11(temp3.get("11").toString());
					compareLicenseAttributes.setCoAttribute12(temp3.get("12").toString());
					compareLicenseAttributes.setCoAttribute13(temp3.get("13").toString());
				}
			}
		} catch (IOException | ParseException e) {
			logger.error("Exception Message", e);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				logger.error("Exception Message", e);
			}
		}
	}
}
