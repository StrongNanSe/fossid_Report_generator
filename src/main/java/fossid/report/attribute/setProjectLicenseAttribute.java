package fossid.report.attribute;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fossid.report.values.projectLicenseAttributeValues;
import fossid.report.values.projectValues;;

public class setProjectLicenseAttribute {
	
	public void getInfo() {
		
		projectAttribute();		
		
	}
	
	// this method is to set project license attribute
	private void projectAttribute() {
		projectLicenseAttributeValues licenseAttributes = projectLicenseAttributeValues.getInstance();
		projectValues pvalues = projectValues.getInstance();        
        
		try {		
		InputStream inputStream = getClass().getResourceAsStream("/license_Attribute.json");
		String attirbute = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		
		JSONParser parser = new JSONParser();
	    Object object1 = parser.parse(attirbute);
	    JSONObject object2 = (JSONObject) object1;
		JSONArray array1 = (JSONArray) object2.get("licenseattribute");
		
		
		
		for(int i=0; i < array1.size(); i++) {
			JSONObject temp1 = (JSONObject) array1.get(i);
 			if(temp1.get("LICENSE_NAME").toString().equals(pvalues.getProjectLicense())) {
 				JSONArray temp2 = (JSONArray) temp1.get("DATA");
 				JSONObject temp3 = (JSONObject) temp2.get(0);
 				
 				licenseAttributes.setpjAttribute1(temp3.get("1").toString());
 				licenseAttributes.setpjAttribute2(temp3.get("2").toString());
 				licenseAttributes.setpjAttribute3(temp3.get("3").toString());
 				licenseAttributes.setpjAttribute4(temp3.get("4").toString());
 				licenseAttributes.setpjAttribute5(temp3.get("5").toString());
 				licenseAttributes.setpjAttribute6(temp3.get("6").toString());
 				licenseAttributes.setpjAttribute7(temp3.get("7").toString());
 				licenseAttributes.setpjAttribute8(temp3.get("8").toString());
 				licenseAttributes.setpjAttribute9(temp3.get("9").toString());
 				licenseAttributes.setpjAttribute10(temp3.get("10").toString());
 				licenseAttributes.setpjAttribute11(temp3.get("11").toString());
 				licenseAttributes.setpjAttribute12(temp3.get("12").toString());
 				licenseAttributes.setpjAttribute13(temp3.get("13").toString());			
 			}
		}
		
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}		
	}
	
}
