package fossid.report.getdata;

import fossid.report.attribute.SetCompareLicenseAttribute;
import fossid.report.values.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;

public class GetBillOfMaterials {
	private final Logger logger = LogManager.getLogger(GetBillOfMaterials.class);
	public void getInfo() {
		getBom();
	}

	private void getBom() {
		BillOfMaterialsValues bomValues = BillOfMaterialsValues.getInstance();
		CompareLicenseAttributeValues componentLicenseAttributeValues = CompareLicenseAttributeValues.getInstance();
		IdentifiedFilesValues idValues = IdentifiedFilesValues.getInstance();
		BufferedReader br = null;
		
		LoginValues lValues = LoginValues.getInstance();
		ProjectValues pValues = ProjectValues.getInstance();
		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lValues.getUsername());
        dataObject.put("key", lValues.getApikey());
        dataObject.put("scan_code", pValues.getVersionId());
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "get_scan_identified_components");
		rootObject.put("data", dataObject);	
				
		HttpPost httpPost = new HttpPost(lValues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		try {
			StringEntity entity = new StringEntity(rootObject.toString(), "UTF-8");
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);			
			
			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				throw new Exception("Failed : HTTP Error code : " + httpClientResponse.getStatusLine().getStatusCode());
			}
			
			br = new BufferedReader(new InputStreamReader(httpClientResponse.getEntity().getContent(), "UTF-8"));
			String result = br.readLine();

			logger.debug("bom"+ result);

	        JSONParser jsonParser = new JSONParser();
	        //JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());	        
	        //JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");
	        
	        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result);

			logger.debug("object1: " + jsonObj1.toString());

	        if(jsonObj1.toString().contains("You are not the creator of the scan nor part of the scan project. Please assign yourself as a project member.")) {
				throw new Exception("You are not the creator of the scan nor part of the scan project. Please assign yourself as a project member.");
	        }
	        
	        if(jsonObj1.get("data") == null) {
				throw new Exception("Please, check \"" + pValues.getVersionName() + "\" is mapped to \"" + pValues.getProjectName() + "\"");
	        }
	        
	        String temp = jsonObj1.get("data").toString();
	        JSONObject jsonObj2 = null;
	        
	        if(temp.equals("false")) {
				logger.warn("This scan does not include identification information. You may check the [File license only] or [Empty identification] in 'marked as identified' tab");
	        } else {
	        	jsonObj2 = (JSONObject) jsonObj1.get("data");
	        }
	        
	        Iterator iter = null;
	        
	        // to count patentIssueFile
            int patentIssueFileCount = 0;
            
            String url = "";
	        
	        if(jsonObj2 != null) {
				iter = jsonObj2.keySet().iterator();

				while(iter.hasNext()) {
					// set key value to key
					String key = (String) iter.next();
					// get values from key
					JSONObject tempObj = (JSONObject) jsonObj2.get(key);

					logger.debug(tempObj.toString());

					if(tempObj.get("version") == null || tempObj.get("name").toString().isEmpty()) {
						bomValues.setUComponentName("Unspecified");
					} else {
						bomValues.setUComponentName(tempObj.get("name").toString());
					}

					if(tempObj.get("version") == null || tempObj.get("version").toString().isEmpty()) {
						bomValues.setUComponentVersion("Unspecified");
						bomValues.setUComponentFileCount(tempObj.get("name").toString() + "Unspecified", 0);
					} else {
						bomValues.setUComponentVersion(tempObj.get("version").toString());
						bomValues.setUComponentFileCount(tempObj.get("name").toString() + tempObj.get("version").toString(), 0);
					}


					url = ("Unspecified");

					if(!(tempObj.get("url") == null || tempObj.get("supplier_url") == null || tempObj.get("community_url") == null || tempObj.get("download_url") == null)) {
						if(!tempObj.get("community_url").toString().isEmpty()) {
							url = (tempObj.get("community_url").toString());
						} else if(!tempObj.get("supplier_url").toString().isEmpty()) {
							url = (tempObj.get("supplier_url").toString());
						} else if(!tempObj.get("url").toString().isEmpty()) {
							url = (tempObj.get("url").toString());
						} else if(!tempObj.get("download_url").toString().isEmpty()) {
							url = tempObj.get("download_url").toString();
						}
					}

					bomValues.setUComponentHomepage(url);

					if(tempObj.get("license_name") == null || tempObj.get("license_name").toString().isEmpty()) {
						bomValues.setUComponentLicenseName("Unspecified");
						if(!bomValues.getULicenseFileCount().containsKey("Unspecified")){
							// To set licenseFileCount key/value
							bomValues.setULicenseFileCount("Unspecified", 0);
							// To set licenseConflict key/value
							bomValues.setProjectLicenseConflict("Unspecified", 0);
						}

						// set license name
						SetCompareLicenseAttribute.setCompareAttribute("Unspecified");
						// get attribute value regarding patent
						if(componentLicenseAttributeValues.getCoAttribute6().equals("O")){
							patentIssueFileCount++;
						}
					} else {
						// set license name as 'Unspecified' if Not Applicable
						String licenseName = tempObj.get("license_name").toString();
						if(licenseName.equals("Not Applicable")) {
							licenseName = "Unspecified";
						}

						bomValues.setUComponentLicenseName(licenseName);
						if(!bomValues.getULicenseFileCount().containsKey(licenseName)){
							// To set licenseFileCount key/value
							bomValues.setULicenseFileCount(licenseName, 0);
							// To set licenseConflict key/value
							bomValues.setProjectLicenseConflict(licenseName, 0);
						}

						// set license name
						SetCompareLicenseAttribute.setCompareAttribute(licenseName);
						// get attribute value regarding patent
						if(componentLicenseAttributeValues.getCoAttribute6().equals("O")){
							patentIssueFileCount++;
						}
					}
				}
			}
        	        	
        	idValues.setPatentIssueFileCount(patentIssueFileCount);
			
		} catch (Exception e) {
			logger.info("Exception Message", e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				logger.info("Exception Message", e);
			}
		}

	}
	
}
