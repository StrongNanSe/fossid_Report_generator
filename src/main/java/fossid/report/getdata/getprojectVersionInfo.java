package fossid.report.getdata;

import fossid.report.values.LoginValues;
import fossid.report.values.ProjectValues;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

;

public class GetProjectVersionInfo {
	private final Logger logger = LogManager.getLogger(GetProjectVersionInfo.class);
	
	public void getInfo(String projectName, String scanName, String license) {
		InputStream is = null;

		try {
			ProjectValues pValues = ProjectValues.getInstance();
			Properties props = new Properties();
			is = getClass().getResourceAsStream("/config.properties");
			props.load(is);
			
			// To change encoding to UTF-8
			String encoding = "";
			
			if(projectName.equals("")) {
				encoding = new String(props.getProperty("fossid.project").getBytes("iso-8859-1"), "UTF-8");			
				pValues.setProjectName(encoding);
			} else {
				pValues.setProjectName(projectName);
			}
			
			if(scanName.equals("")) {
				encoding = new String(props.getProperty("fossid.scan").getBytes("iso-8859-1"), "UTF-8");			
				pValues.setVersionName(encoding);
			} else {
				pValues.setVersionName(scanName);
			}
			
			if(license.equals("")) {
				pValues.setProjectLicense(props.getProperty("fossid.license"));
			} else {
				pValues.setProjectLicense(license);
			}
		} catch (IOException e) {
			logger.error("Exception Message", e);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				logger.error("Exception Message", e);
			}
		}
		
		getProjectId();
		//getVersionId();
		checkProjectVersion();
	}
	
	private void getProjectId() {
		LoginValues lvalues = LoginValues.getInstance();
		ProjectValues pValues = ProjectValues.getInstance();
				
		// create json to call FOSSID project/list_projects api 		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "projects");
        rootObject.put("action", "list_projects");
		rootObject.put("data", dataObject);		
		
		HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();
		BufferedReader br = null;
		
		try {

			StringEntity entity = new StringEntity(rootObject.toString());
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);			

			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				throw new Exception("Failed : HTTP Error code : " + httpClientResponse.getStatusLine().getStatusCode());
			}
			
			br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();

			logger.debug("result: " + result.toString());

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
            JSONArray dataArray = (JSONArray) jsonObj.get("data");
            
            ArrayList<String> projectList = new ArrayList<String>();
            //set projectid
			for (Object o : dataArray) {
				JSONObject tempObj = (JSONObject) o;
				if (tempObj.get("project_name").equals(pValues.getProjectName())) {
					pValues.setProjectId(tempObj.get("project_code").toString());
				}
				projectList.add(tempObj.get("project_name").toString());
			}
            
            if(!projectList.contains(pValues.getProjectName())){
				logger.warn("Please, check the fossid.project in the config.properties file");
            }
            
		} catch (Exception e) {
			logger.error("Exception Message", e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				logger.error("Exception Message", e);
			}
		}
	}
	
	private void getVersionId() {

		LoginValues lValues = LoginValues.getInstance();
		ProjectValues pValues = ProjectValues.getInstance();
				
		// create json to call FOSSID projects/get_all_scans api 		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lValues.getUsername());
        dataObject.put("key", lValues.getApikey());
        dataObject.put("project_code", pValues.getProjectId());
        
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "list_scans");        
		rootObject.put("data", dataObject);		
		
		HttpPost httpPost = new HttpPost(lValues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();
		BufferedReader br = null;
		
		try {
			StringEntity entity = new StringEntity(rootObject.toString());
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);		

			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				throw new Exception("Failed : HTTP Error code : " + httpClientResponse.getStatusLine().getStatusCode());
			}

			br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();

			logger.debug("result: " + result);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());
            JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");

			// set key value of jsonObj2 and run loop while(until) iter has value
			for (Object o : jsonObj2.keySet()) {
				// set key value to key
				String key = (String) o;
				// get values from key
				JSONObject tempObj = (JSONObject) jsonObj2.get(key);
				if (tempObj.get("name").equals(pValues.getVersionName())) {
					pValues.setVersionId(tempObj.get("code").toString());
				}
			}
		} catch (Exception e) {
			logger.error("Exception Message", e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				logger.error("Exception Message", e);
			}
		}
	}
	
	private void checkProjectVersion() {

		LoginValues lvalues = LoginValues.getInstance();
		ProjectValues pValues = ProjectValues.getInstance();
		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
        dataObject.put("project_code", pValues.getProjectId());
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "projects");
        rootObject.put("action", "get_all_scans");
		rootObject.put("data", dataObject);
		
		HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();
		BufferedReader br = null;
		
		try {			
			// TO set UTF-8 Entity
			StringEntity entity = new StringEntity(rootObject.toString(), "UTF-8");
			
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);	
			
			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				throw new Exception("Please, check " + pValues.getVersionName() + " is mapped to "
						+ pValues.getProjectName() + "or \ncheck " + lvalues.getUsername() + " is assigned to "
						+ pValues.getProjectName() + "\nFailed : HTTP Error code : "
						+ httpClientResponse.getStatusLine().getStatusCode());
			}
			
			br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();

			logger.debug("result2: " + result.toString());
			logger.debug("rootObject" + rootObject.toString());

			JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());
            
            /**
             * Check Project contains fossid.scanname dedicated in config.properties.
             * Implement if project include one or more scan names 
             * one scanname return JSONArray, more scanmes return JSONObject 
             */
            // to recognize JSONArray or JSONObject
            String arrayOrObject = jsonObj1.toString().substring(8,9);
            if(arrayOrObject.equals("{")) {
            	JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");
                Iterator iter = jsonObj2.keySet().iterator();
                
                ArrayList<String> codeList = new ArrayList<String>();
                // set key value of jsonObj2 and run loop while(until) iter has value	        
                while(iter.hasNext()) {
                	// set key value to key
                	String key = (String) iter.next();
                	// get values from key
                	JSONObject tempObj = (JSONObject) jsonObj2.get(key);
                	if(pValues.getVersionName().equals(tempObj.get("name"))) {
                		pValues.setVersionId(tempObj.get("code").toString());
                	}   
                	codeList.add(tempObj.get("code").toString());
                }	        
                
                if(!codeList.contains(pValues.getVersionId())) {
					logger.warn("Please, check the fossid.scanname in the config.properties file or assign a scan to a Project on FOSSID");
                }
            } else if (arrayOrObject.equals("[")){
            	JSONArray dataArray = (JSONArray) jsonObj1.get("data");
				JSONObject tempObj = (JSONObject) dataArray.get(0);
      	        
      	        if(pValues.getVersionName().equals(tempObj.get("name"))) {
      	        	pValues.setVersionId(tempObj.get("code").toString());
      	        } else {
				    logger.warn("Please, check the fossid.scanname in the config.properties file or assign a scan to a Project on FOSSID");
      	        }
            }
		} catch (Exception e) {
			logger.error("Exception Message",e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				logger.error("Exception Message", e);
			}
		}
	}
}
