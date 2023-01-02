package fossid.report.getVuln;

import fossid.report.values.VulnerableComponents;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Iterator;

public class SetVulnData {
	static Logger logger = LogManager.getLogger(SetVulnData.class);
	public static void setData(String cliResult, String componentCPE, String componentName, String componentVersion) {
		VulnerableComponents vulnerableComponent = VulnerableComponents.getInstance();
		
		JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj1, jsonObj2, impact, baseMetric, cvss = null;
        Iterator iter;

		try {
			jsonObj1 = (JSONObject) jsonParser.parse(cliResult);
			jsonObj2 = (JSONObject) jsonObj1.get(componentCPE);

			logger.debug("jsonObj1 : " + jsonObj1);
			logger.debug("jsonObj2 : " + jsonObj2);

			iter = jsonObj2.keySet().iterator();
			
			while(iter.hasNext()) {	  
				// set key value to key
            	String key = (String) iter.next();
            	// get values from key
            	JSONObject tempObj = (JSONObject) jsonObj2.get(key);

				logger.debug("tempObj : " + tempObj);

            	impact = (JSONObject) tempObj.get("impact");
           	
            	String severity;

				logger.debug("impact : " + impact);

            	if(impact != null) {
					if (impact.containsKey("baseMetricV2")) {
						baseMetric = (JSONObject) impact.get("baseMetricV2");

						if (baseMetric.containsKey("cvssV2")) {
							cvss = (JSONObject) baseMetric.get("cvssV2");
						} else {
							logger.debug("No Exit cvssV2");
						}
					} else {
						logger.debug("No Exit baseMetricV2");

						baseMetric = (JSONObject) impact.get("baseMetricV3");

						if (baseMetric.containsKey("cvssV3")) {
							cvss = (JSONObject) baseMetric.get("cvssV3");
						} else {
							logger.debug("No Exit cvssV3");
						}
					}

					if (cvss != null) {
						String baseScore = cvss.get("baseScore").toString();

						if (baseMetric.containsKey("severity")) {
							severity = baseMetric.get("severity").toString() + "(" + baseScore +")";
						} else {
							severity = cvss.get("baseSeverity").toString() + "(" + baseScore +")";
						}

						vulnerableComponent.setVulComponentName(componentName);
						vulnerableComponent.setVulComponentVersion(componentVersion);
						vulnerableComponent.setVulCVE(key);
						vulnerableComponent.setVulSeverity(severity);

						if (cvss.containsKey("accessVector")) {
							vulnerableComponent.setVulAttackVector(cvss.get("accessVector").toString());
						} else {
							vulnerableComponent.setVulAttackVector(cvss.get("attackVector").toString());
						}

						if (cvss.containsKey("accessComplexity")) {
							vulnerableComponent.setVulAttackComplexity(cvss.get("accessComplexity").toString());
						} else {
							vulnerableComponent.setVulAttackComplexity(cvss.get("attackComplexity").toString());
						}

						vulnerableComponent.setVulAttackImpact(cvss.get("availabilityImpact").toString());

						logger.debug(vulnerableComponent.getVulComponentName()+ "  "
								+ vulnerableComponent.getVulComponentVersion() + "  "
								+ vulnerableComponent.getVulCVE() + "  "
								+	vulnerableComponent.getVulSeverity() + "  "
								+ vulnerableComponent.getVulAttackVector() + "  "
								+ vulnerableComponent.getVulAttackComplexity() + "  "
								+ vulnerableComponent.getVulAttackImpact() + "  ");
					} else {
						logger.debug("No Exit cvss");
					}
            	} else {
					logger.debug("No Exit impact");
				}
			}
		} catch (Exception e) {
			logger.error("Exception Message", e);
		}

	}
	
}
