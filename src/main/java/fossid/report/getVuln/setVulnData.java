package fossid.report.getVuln;

import fossid.report.values.VulnerableComponents;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

public class SetVulnData {
	static Logger logger = LogManager.getLogger(SetVulnData.class);
	public static void setData(String cliResult, String componentCPE, String componentName, String componentVersion) {
		VulnerableComponents vulnerableComponent = VulnerableComponents.getInstance();
		
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj1, jsonObj2, jsonObj3, jsonObj4, jsonObj5;
        Iterator iter;
        
        
		try {
			jsonObj1 = (JSONObject) jsonParser.parse(cliResult.toString());			
			jsonObj2 = (JSONObject) jsonObj1.get(componentCPE);
			
			iter = jsonObj2.keySet().iterator();
			
			while(iter.hasNext()) {	  
				// set key value to key
            	String key = (String) iter.next();
            	// get values from key
            	JSONObject tempObj = (JSONObject) jsonObj2.get(key);

				logger.debug("tempObj : " + tempObj.toString());

            	jsonObj3 = (JSONObject) tempObj.get("impact");
           	
            	String severity = "";

				logger.debug("jsonObj3 : " + jsonObj3.toString());

            	if(jsonObj3 != null) {

            		jsonObj4 = (JSONObject) jsonObj3.get("baseMetricV2");

					logger.debug("jsonObj4 : " + jsonObj4.toString());

                	jsonObj5 = (JSONObject) jsonObj4.get("cvssV2");

                	String baseScore = jsonObj5.get("baseScore").toString();

                	if(jsonObj4.get("severity").toString().equals("HIGH") || jsonObj4.get("severity").toString().equals("MEDIUM")) {
                	//if(jsonObj4.get("severity").toString().equals("HIGH")) {

    		            severity = jsonObj4.get("severity").toString() + "(" + jsonObj5.get("baseScore").toString() +")";

    		            vulnerableComponent.setVulComponentName(componentName);
    		            vulnerableComponent.setVulComponentVersion(componentVersion);
    		            vulnerableComponent.setVulCVE(key);
    		            vulnerableComponent.setVulSeverity(severity);
    		            vulnerableComponent.setVulAttackVector(jsonObj5.get("accessVector").toString());
    		            vulnerableComponent.setVulAttackComplexity(jsonObj5.get("accessComplexity").toString());
    		            vulnerableComponent.setVulAttackImpact(jsonObj5.get("availabilityImpact").toString());

						logger.debug(componentName+ "  " + componentVersion + "  " + key + "  " +	severity + "  " +
								jsonObj5.get("accessVector").toString() + "  " + jsonObj5.get("accessComplexity").toString() + "  " +
								jsonObj5.get("availabilityImpact").toString() + "  ");
                	}

            	}

			}
		} catch (ParseException e) {
			logger.error("Exception Message", e);
		}
		
	}
	
}
