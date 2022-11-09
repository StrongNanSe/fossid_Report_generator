package fossid.report.getVuln;

import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fossid.report.values.VulnerableComponents;

public class SetVulnData {
	static Logger logger = LogManager.getLogger(SetVulnData.class);
	public static void setData(String cliResult, String componentCPE, String componentName, String componentVersion) {
		VulnerableComponents vulnerableComponent = VulnerableComponents.getInstance();
		
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj1, jsonObj2, jsonObj3, jsonObj4, jsonObj5;
        Iterator iter = null;
        
        
		try {
			jsonObj1 = (JSONObject) jsonParser.parse(cliResult.toString());			
			jsonObj2 = (JSONObject) jsonObj1.get(componentCPE);
			
			iter = jsonObj2.keySet().iterator();
			
			while(iter.hasNext()) {	  
				// set key value to key
            	String key = (String) iter.next();
            	// get values from key
            	JSONObject tempObj = (JSONObject) jsonObj2.get(key);
            	
            	//System.out.println(tempObj.toString());
            	
            	jsonObj3 = (JSONObject) tempObj.get("impact");
           	
            	String severity = "";
            	
            	System.out.println(jsonObj3.toString());
            	if(jsonObj3 != null) {
            		
            		jsonObj4 = (JSONObject) jsonObj3.get("baseMetricV2");
                	jsonObj5 = (JSONObject) jsonObj4.get("cvssV2");   
                	
                	String baseScore = jsonObj5.get("baseScore").toString();
                		
                	if(jsonObj4.get("severity").toString().equals("HIGH") || jsonObj4.get("severity").toString().equals("MEDIUM")) {
                	//if(jsonObj4.get("severity").toString().equals("HIGH")) {
                		
    		            severity = jsonObj4.get("severity").toString() + "(" + jsonObj5.get("baseScore").toString() +")";
    		    				
    		            vulnerableComponent.setvulcomponentName(componentName);
    		            vulnerableComponent.setvulcomponentVersion(componentVersion);
    		            vulnerableComponent.setvulCVE(key);
    		            vulnerableComponent.setvulSeverity(severity);
    		            vulnerableComponent.setvulAttackVector(jsonObj5.get("accessVector").toString());
    		            vulnerableComponent.setvulAttackComplexity(jsonObj5.get("accessComplexity").toString());
    		            vulnerableComponent.setvulAttackImpact(jsonObj5.get("availabilityImpact").toString());
    	                /*
                    	System.out.println(componentName+ "  " + componentVersion + "  " + key + "  " +	severity + "  " +
    	                		jsonObj5.get("accessVector").toString() + "  " + jsonObj5.get("accessComplexity").toString() + "  " +
    	                		jsonObj5.get("availabilityImpact").toString() + "  ");
    	                */
                	}
            		
            	}
            	
			}			
		} catch (ParseException e) {
			logger.error("Exception Message", e);
		}
		
	}
	
}
