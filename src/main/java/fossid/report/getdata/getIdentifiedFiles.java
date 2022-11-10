package fossid.report.getdata;

import fossid.report.attribute.GetProjectLicenseConflict;
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

public class GetIdentifiedFiles {
	private final Logger logger = LogManager.getLogger(GetIdentifiedFiles.class);
	
	public void getData() {	
		//Enable below method to get specific values of identified_files for BOM
		getIdFilesBom();
		
	}
	
	private void getIdFilesBom() {

		LoginValues lvalues = LoginValues.getInstance();
		ProjectValues pValues = ProjectValues.getInstance();
		IdentifiedFilesValues idValues = IdentifiedFilesValues.getInstance();
	    BillOfMaterialsValues bomValues = BillOfMaterialsValues.getInstance();
		VulnerableComponents vulnerableComponent = VulnerableComponents.getInstance();
	    
	    GetProjectLicenseConflict projectLicenseConflict = new GetProjectLicenseConflict();
		GetMatchTypes matchType = new GetMatchTypes();
	    
	    String matchTypeValue = "";
    	String matchTypeKey = "";
    	String tempValue = "";
				
		// create json to call FOSSID project/list_projects api 		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
        dataObject.put("scan_code", pValues.getVersionId());
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "get_identified_files");
		rootObject.put("data", dataObject);		
		
		HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();				
		
		try {

			StringEntity entity = new StringEntity(rootObject.toString(), "UTF-8");
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);			
			
			
			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				throw new Exception("Failed : HTTP Error code : " + httpClientResponse.getStatusLine().getStatusCode());
			}
								
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();		
					
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());
            JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");
            Iterator iter = jsonObj2.keySet().iterator();
                        
            int identifiedFileCount = 0;
            
            // this value is to count number of files belong to a component
            int bomFileCount = 0;
            
            // this value is to count number of files belong to a license
            int licenseFileCount = 0;
            
            // to count projectConflictFile
            int projectConflictFileCount = 0;
            
            // to count patentIssueFile
            int patentIssueFileCount = 0;
            
            // to change license name of 'Not Applicable'
            String licenseName = "";                        
            
            int loopCount = 0;
			logger.info("In progress..");

            // set key value of jsonObj2 and run loop while(until) iter has value
            while(iter.hasNext()) {

            	if(loopCount%100 == 0) {
					logger.info(loopCount + "/"+ idValues.getfileTotalCount());
            	}
            	
            	loopCount++;
            	
            	// set key value to key
            	String key = (String) iter.next();
            	// get values from key
            	JSONObject tempObj = (JSONObject) jsonObj2.get(key);            	       	
            	
            	matchTypeValue = matchType.getMatchType(tempObj.get( "file_path").toString());
            	
            	// to avoid duplicate of file_path because multiple file_license can be founded in a file
            	if(!(idValues.getfilepath().contains(tempObj.get("file_path"))) || matchTypeValue.equals("Partial")) {
            		
            		//set values if component_name is exist
            		if(tempObj.get("component_name") != null) {
                		//set file_path
                    	if(tempObj.get("file_path") == null) {
                    		idValues.setfilepath("");
                    	} else {
                    		idValues.setfilepath(tempObj.get("file_path").toString());            		
                    	}  
                    	
                    	
                    	//set file_size
                    	if(tempObj.get("file_size") == null) {
                    		idValues.setfileTotalSize(0);
                    	} else {
                    		// from 21.2.3
                    		//String st = Long.toString((long) tempObj.get("file_size"));
                    		//int temp = Integer.parseInt(st);
                    		
                    		// by 21.2.3 
                    		int temp = Integer.parseInt(tempObj.get("file_size").toString());     
                    		
                    		idValues.setfileTotalSize(temp);           		
                    	}  
                    	
                    	//set component_name
                    	if(tempObj.get("component_name") == null) {
                    		idValues.setcomponenetName("Unspecified");
                    		matchTypeKey = "Unspecified";
                    	} else {
                    		idValues.setcomponenetName(tempObj.get("component_name").toString());
                    		matchTypeKey = tempObj.get("component_name").toString();
                    	}
                    	
                    	//set component_version
                    	if(tempObj.get("component_version") == null) {
                    		idValues.setcomponentVersion("Unspecified");
                    		matchTypeKey = matchTypeKey + "Unspecified";
                    	} else {
                    		idValues.setcomponentVersion(tempObj.get("component_version").toString());         
                    		matchTypeKey = matchTypeKey + tempObj.get("component_version").toString();
                    	}
                    	
                    	
                    	// set match_types for individual files
                    	//matchTypeValue = matchType.getmatchtype(tempObj.get("file_path").toString());
                    	idValues.setmatchType(matchTypeValue);
                    	
                    	// set Match Types for each component
                    	tempValue = matchTypeValue;
                    	
                    	if(idValues.getmatchTypeHashmap().get(matchTypeKey) != null){
                    		tempValue = idValues.getmatchTypeHashmap().get(matchTypeKey);
                    	}
                    	
                    	if(!(matchTypeValue.equals(tempValue))) {                    	
                    		matchTypeValue = matchTypeValue + ", " + tempValue;
                    	}
                    	
                    	if(matchTypeValue.equals("Partial, Full") || matchTypeValue.equals("Full, Partial")) {
                    		idValues.getmatchTypeHashmap().put(matchTypeKey, matchTypeValue);
                    	}
                    	
                    	if(!(idValues.getmatchTypeHashmap().containsKey(matchTypeKey))) {
                    		idValues.getmatchTypeHashmap().put(matchTypeKey, matchTypeValue);
                    	}
                    	
                    	
                    	// To set project license conflict and patent issue file count
                    	if(tempObj.get("component_license_name") == null) {
                    		idValues.setcomponentVersion("Unspecified");
                    	} else if(tempObj.get("component_license_name") != null) {                   		
                    		String cKey = null;
                    		int value = 0;      
                    		// set license name as 'Unspecified' if Not Applicable
                    		licenseName = tempObj.get("component_license_name").toString();
                    		if(licenseName.equals("Not Applicable")) {
                    			licenseName = "Unspecified";
                    		}
                    		
                   			// this set affect column 0(license conflict) and 6(Patent retaliation clause) 
                   			SetCompareLicenseAttribute.setCompareAttribute(licenseName);
                   			// value: 0 - no conflict / 1 - project license conflict
                   			projectLicenseConflict.projectLicenseConflict(licenseName);
                   			cKey = licenseName;
                   			value =	bomValues.getProjectLicenseConflict().get(cKey);
                   			if (value == 1) {
                   				projectConflictFileCount++;
                   			}		
                    	}                		
                    	
                    	//set component_license_name
                    	if(tempObj.get("component_license_name") == null) {
                    		idValues.setcomponentLicenseName("Unspecified");
                    	} else {
                    		licenseName = tempObj.get("component_license_name").toString();
                    		if(licenseName.equals("Not Applicable")) {
                    			licenseName = "Unspecified";
                    		}
                    		idValues.setcomponentLicenseName(licenseName);            		
                    	}
                    	
                    	// set comment
                    	if(tempObj.get("comment") == null) {
                    		idValues.setcommnet("");
                    	} else {
                    		idValues.setcommnet(tempObj.get("comment").toString());            		
                    	} 
                    	
                    	// set value for a component
                    	String componentValue = tempObj.get("component_name").toString()+tempObj.get("component_version").toString();                    	
                    	                    	
                    	// get a component value
                    	bomFileCount = bomValues.getUComponentFileCount().get(componentValue);
                    	// plus value
                    	bomFileCount++;
                    	// then put value in the component
                    	bomValues.setUComponentFileCount(componentValue, bomFileCount);
                    	
                    	// set value for a license
                    	String licenseValue = null;
                    	if(tempObj.get("component_license_name") == null) {
                    		licenseValue = "Unspecified";
                    	} else {
                    		licenseValue = licenseName;          		
                    	}
                    	// get a component value
                    	licenseFileCount = bomValues.getULicenseFileCount().get(licenseValue);
                    	// plus value
                    	licenseFileCount++;                    	
                    	// then put value in the license
                    	bomValues.setULicenseFileCount(licenseValue, licenseFileCount);
                    	
                    	//set vulnerable components
                    	if(!(tempObj.get("component_cpe") == null || tempObj.get("component_cpe").equals(""))) {
							if(!vulnerableComponent.getcpeHashmap().containsKey(componentValue)) {
								vulnerableComponent.setcomponentName(tempObj.get("component_name").toString());
								vulnerableComponent.setcomponentVersion(tempObj.get("component_version").toString());
								vulnerableComponent.setcomponentCPE(tempObj.get("component_cpe").toString());
							}
							vulnerableComponent.setcpeHashmap(componentValue, tempObj.get("component_cpe").toString());
                    	}
                    
                    identifiedFileCount++;                    
                    
                    //set values if component_name is not exist
                	} else {
                		//set file_path
                    	if(tempObj.get("file_path") == null) {
                    		idValues.setfilepath("");
                    	} else {
                    		idValues.setfilepath(tempObj.get("file_path").toString());            		
                    	}          
                    	
                    	idValues.setmatchType(matchType.getMatchType(tempObj.get("file_path").toString()));
                    	
                    	//set file_size
                    	if(tempObj.get("file_size") == null) {
                    		idValues.setfileTotalSize(0);
                    	} else {
                    		// from 21.2.3
                    		//String st = Long.toString((long) tempObj.get("file_size"));
                    		//int temp = Integer.parseInt(st);
                    		
                    		// by 21.2.3  
                    		//long temp = Integer.parseInt(tempObj.get("file_size").toString());
                    		long temp = Long.parseLong(tempObj.get("file_size").toString());
                    	
                    		idValues.setfileTotalSize(temp);
                    	} 
                		
                    	//set Project Name for original files (not identified)
                   		idValues.setcomponenetName(pValues.getProjectName());
                    	
                    	//set Project Version for original files (not identified)
                   		idValues.setcomponentVersion("Unspecified");            		
                   		
                        //set Project License for original files (not identified)
                    	idValues.setcomponentLicenseName(pValues.getProjectLicense());
                   		
                   	    //set comment
                    	if(tempObj.get("comment") == null) {
                    		idValues.setcommnet("");
                    	} else {
                    		idValues.setcommnet(tempObj.get("comment").toString());            		
                    	}
                    	
                	}       		
            	} 
            }                        

			logger.info("");
			logger.info("");

            idValues.setIdentifiedFileCount(identifiedFileCount);
            idValues.setprojectConflictFileCount(projectConflictFileCount);
            idValues.setpendingFileCount(identifiedFileCount);
                   
		} catch (Exception e) {
			logger.error("Exception Message", e);
			System.exit(1);
		}
	}
	
}