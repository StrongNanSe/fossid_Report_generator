package fossid.report.attribute;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fossid.report.values.compareLicenseAttributeValues;

public class setCompareLicenseAttribute {
	
	   // this method is to set license to compare a license with project license
		public static void setCompareAttribute(String licenseName) {
			compareLicenseAttributeValues compareLicenseAttributes = compareLicenseAttributeValues.getInstance();			
	        
			try {		
			InputStream inputStream = setCompareLicenseAttribute.class.getResourceAsStream("/license_Attribute.json");
			String attirbute = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
				
			JSONParser parser = new JSONParser();
		    Object object1 = parser.parse(attirbute);
		    JSONObject object2 = (JSONObject) object1;
			JSONArray array1 = (JSONArray) object2.get("licenseattribute");
			
			int tfCount = 0;
			
			for(int i=0; i < array1.size(); i++) {
				JSONObject temp1 = (JSONObject) array1.get(i);
	 			if(temp1.get("LICENSE_NAME").toString().equals(licenseName)) {	 				
	 				tfCount++;
	 			}
			}
			
			if(tfCount < 1) {				
				if(!compareLicenseAttributes.getoutofLicense().contains(licenseName)){
					compareLicenseAttributes.setoutofLicense(licenseName);
				}				
				licenseName = "Unspecified";				
			}
			
			for(int i=0; i < array1.size(); i++) {
				JSONObject temp1 = (JSONObject) array1.get(i);
	 			if(temp1.get("LICENSE_NAME").toString().equals(licenseName)) {
	 				JSONArray temp2 = (JSONArray) temp1.get("DATA");
	 				JSONObject temp3 = (JSONObject) temp2.get(0);
	 				
	 				compareLicenseAttributes.setcoAttribute1(temp3.get("1").toString());
	 				compareLicenseAttributes.setcoAttribute2(temp3.get("2").toString());
	 				compareLicenseAttributes.setcoAttribute3(temp3.get("3").toString());
	 				compareLicenseAttributes.setcoAttribute4(temp3.get("4").toString());
	 				compareLicenseAttributes.setcoAttribute5(temp3.get("5").toString());
	 				compareLicenseAttributes.setcoAttribute6(temp3.get("6").toString());
	 				compareLicenseAttributes.setcoAttribute7(temp3.get("7").toString());
	 				compareLicenseAttributes.setcoAttribute8(temp3.get("8").toString()); 				
	 				compareLicenseAttributes.setcoAttribute9(temp3.get("9").toString());
	 				compareLicenseAttributes.setcoAttribute10(temp3.get("10").toString());
	 				compareLicenseAttributes.setcoAttribute11(temp3.get("11").toString());
	 				compareLicenseAttributes.setcoAttribute12(temp3.get("12").toString());	 				
	 				compareLicenseAttributes.setcoAttribute13(temp3.get("13").toString());
	 			}
			}
			
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
			
		}

}
