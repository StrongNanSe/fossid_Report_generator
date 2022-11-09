package fossid.report.getdata;

import fossid.report.values.LoginValues;
import fossid.report.values.ProjectValues;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
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
	
	public void getInfo(String projectName, String scanName, String license) {
		
		try {
			ProjectValues pvalues = ProjectValues.getInstance();
			Properties props = new Properties();
			InputStream is = getClass().getResourceAsStream("/config.properties");
			props.load(is);
			
			// To change encoding to UTF-8
			String encoding = "";
			
			if(projectName.equals("")) {
				encoding = new String(props.getProperty("fossid.project").getBytes("iso-8859-1"), "UTF-8");			
				pvalues.setProjectName(encoding);
			} else {
				pvalues.setProjectName(projectName);
			}
			
			if(scanName.equals("")) {
				encoding = new String(props.getProperty("fossid.scan").getBytes("iso-8859-1"), "UTF-8");			
				pvalues.setVersionName(encoding);
			} else {
				pvalues.setVersionName(scanName);
			}
			
			if(license.equals("")) {
				pvalues.setProjectLicense(props.getProperty("fossid.license"));
			} else {
				pvalues.setProjectLicense(license);
			}
			
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getProjectId();
		//getVersionId();
		checkprojectVersion();
	}
	
	private void getProjectId() {

		LoginValues lvalues = LoginValues.getInstance();
		ProjectValues pvalues = ProjectValues.getInstance();
				
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
		
		try {

			StringEntity entity = new StringEntity(rootObject.toString());
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);			
			
			
			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				System.out.println("Failed : HTTP Error code : " + httpClientResponse.getStatusLine().getStatusCode());
				System.exit(1);
			}	
								
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();
			
			//System.out.println("result: " + result.toString());
			
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
            JSONArray dataArray = (JSONArray) jsonObj.get("data");
            
            ArrayList<String> projectList = new ArrayList<String>();
            //set projectid
            for(int i=0; i < dataArray.size(); i++) {
                JSONObject tempObj = (JSONObject) dataArray.get(i);                                
                if(tempObj.get("project_name").equals(pvalues.getProjectName())) {
                	pvalues.setProjectId(tempObj.get("project_code").toString());
                }            
                projectList.add(tempObj.get("project_name").toString());
            }
            
            if(!projectList.contains(pvalues.getProjectName())){
            	System.out.println("Please, check the fossid.project in the config.properties file");
            }
            
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}		
	}
	
	private void getVersionId() {

		LoginValues lvalues = LoginValues.getInstance();
		ProjectValues pvalues = ProjectValues.getInstance();
				
		// create json to call FOSSID projects/get_all_scans api 		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
        dataObject.put("project_code", pvalues.getProjectId());
        
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "list_scans");        
		rootObject.put("data", dataObject);		
		
		HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();				
		
		try {
			
			StringEntity entity = new StringEntity(rootObject.toString());
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);		
			
			
			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				System.out.println("Failed : HTTP Error code : " + httpClientResponse.getStatusLine().getStatusCode());
				System.exit(1);
			}	
								
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();
			
			//System.out.println("result: " + result.toString());
			
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());
            JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");
            Iterator iter = jsonObj2.keySet().iterator();
            
            // set key value of jsonObj2 and run loop while(until) iter has value            
            while(iter.hasNext()) {
            	// set key value to key
            	String key = (String) iter.next();
            	// get values from key
            	JSONObject tempObj = (JSONObject) jsonObj2.get(key);            	
                if(tempObj.get("name").equals(pvalues.getVersionName())) {
                	pvalues.setVersionId(tempObj.get("code").toString());
                }
            }
            
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}		
	}
	
	private void checkprojectVersion() {

		LoginValues lvalues = LoginValues.getInstance();
		ProjectValues pvalues = ProjectValues.getInstance();
		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
        dataObject.put("project_code", pvalues.getProjectId());
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "projects");
        rootObject.put("action", "get_all_scans");
		rootObject.put("data", dataObject);
		
		HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();
		
		try {			
			// TO set UTF-8 Entity
			StringEntity entity = new StringEntity(rootObject.toString(), "UTF-8");
			
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);	
			
			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				
				
				System.out.println("Please, check " + pvalues.getVersionName() + " is mapped to " + pvalues.getProjectName() + " or ");
				System.out.println("check " + lvalues.getUsername() + " is assigned to " + pvalues.getProjectName());				
				
				System.out.println("Failed : HTTP Error code : " + httpClientResponse.getStatusLine().getStatusCode());
				System.exit(1);
			}					
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();
			
			//System.out.println("result2: " + result.toString());
			//System.out.println("rootObject" + rootObject.toString());
			
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
                	if(pvalues.getVersionName().equals(tempObj.get("name"))) {
                		pvalues.setVersionId(tempObj.get("code").toString());
                	}   
                	codeList.add(tempObj.get("code").toString());
                }	        
                
                if(!codeList.contains(pvalues.getVersionId())) {
                	System.out.println("Please, check the fossid.scanname in the config.properties file or assign a scan to a Project on FOSSID");
                } 
            } else if (arrayOrObject.equals("[")){
            	
            	JSONArray dataArray = (JSONArray) jsonObj1.get("data");      	        
      	        JSONObject data = new JSONObject();      	                   			
      	        JSONObject tempObj = (JSONObject) dataArray.get(0);
      	        
      	        if(pvalues.getVersionName().equals(tempObj.get("name"))) {
      	        	pvalues.setVersionId(tempObj.get("code").toString());
      	        } else {
                   	System.out.println("Please, check the fossid.scanname in the config.properties file or assign a scan to a Project on FOSSID");
      	        }
            }
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}		
	}
}
