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
import java.util.Objects;

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
			JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj1 = (JSONObject) jsonParser.parse(br.readLine());
            JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");
            Iterator iter = jsonObj2.keySet().iterator();

			String project_Name = String.valueOf(pValues.getProjectName());

            int identifiedFileCount = 0;
            int projectFileCount = 0;

            // this value is to count number of files belong to a component
            int bomFileCount = 0;
            
            // this value is to count number of files belong to a license
            int licenseFileCount = 0;
            
            // to count projectConflictFile
            int projectConflictFileCount = 0;
            
            // to count patentIssueFile
            int patentIssueFileCount = 0;

            // to change license name of 'Not Applicable'
            int loopCount = 0;
			logger.info("In progress..");

            // set key value of jsonObj2 and run loop while(until) iter has value
            while(iter.hasNext()) {

            	if(loopCount%100 == 0) {
					logger.info(loopCount + "/"+ idValues.getFileTotalCount());
            	}
            	
            	loopCount++;
            	
            	// set key value to key
            	String key = (String) iter.next();

				logger.debug("key : " + key);

            	// get values from key
            	JSONObject tempObj = (JSONObject) jsonObj2.get(key);

				logger.debug("tempObj-" + loopCount + " : " + tempObj);

				String matchTypeValue;
				String matchTypeKey;
				String tempValue;

				String file_Path = String.valueOf(tempObj.get("file_path"));
				String file_Size = String.valueOf(tempObj.get("file_size"));
				String component_Name = String.valueOf(tempObj.get("component_name"));
				String component_Version = String.valueOf(tempObj.get("component_version"));
				String component_License_Name = String.valueOf(tempObj.get("component_license_name"));
				String comment = String.valueOf(tempObj.get("comment"));
				String component_Cpe = String.valueOf(tempObj.get("component_cpe"));

				String licenseValue;

				logger.debug("file_Path : " + file_Path);

            	matchTypeValue = matchType.getMatchType(file_Path);

				logger.debug("matchTypeValue : " + matchTypeValue);
            	
            	// to avoid duplicate of file_path because multiple file_license can be founded in a file
            	if(!(idValues.getFilepath().contains(file_Path)) || matchTypeValue.equals("Partial")) {
					//set file_path
					if(file_Path.equals("null")) {
						idValues.setFilepath("");
					} else {
						idValues.setFilepath(file_Path);
					}

					//set file_size
					if(Objects.equals(file_Size, "null")) {
						idValues.setFileTotalSize(0);
					} else {
						// from 21.2.3
						//String st = Long.toString((long) tempObj.get("file_size"));
						//int temp = Integer.parseInt(st);

						// by 21.2.3
						idValues.setFileTotalSize(Integer.parseInt(file_Size));
					}

					// set match_types for individual files
					idValues.setMatchType(matchTypeValue);

					if(!Objects.equals(component_Name, "null")) {
						idValues.setComponentName(component_Name);
						matchTypeKey = component_Name;
                    	
                    	//set component_version
                    	if(Objects.equals(component_Version, "null")) {
                    		idValues.setComponentVersion("Unspecified");
                    		matchTypeKey = matchTypeKey + "Unspecified";
                    	} else {
                    		idValues.setComponentVersion(component_Version);
                    		matchTypeKey = matchTypeKey + component_Version;
                    	}
                    	
                    	// set Match Types for each component
                    	tempValue = matchTypeValue;
                    	
                    	if(idValues.getMatchTypeHashmap().get(matchTypeKey) != null){
                    		tempValue = idValues.getMatchTypeHashmap().get(matchTypeKey);
                    	}
                    	
                    	if(!(matchTypeValue.equals(tempValue))) {                    	
                    		matchTypeValue = matchTypeValue + ", " + tempValue;
                    	}
                    	
                    	if(matchTypeValue.equals("Partial, Full") || matchTypeValue.equals("Full, Partial")) {
                    		idValues.getMatchTypeHashmap().put(matchTypeKey, matchTypeValue);
                    	}
                    	
                    	if(!(idValues.getMatchTypeHashmap().containsKey(matchTypeKey))) {
                    		idValues.getMatchTypeHashmap().put(matchTypeKey, matchTypeValue);
                    	}

                    	// To set project license conflict and patent issue file count
                    	if(Objects.equals(component_License_Name, "null")) {
							licenseValue = "Unspecified";
							idValues.setComponentVersion(licenseValue);
                    	} else {
                    		String cKey = null;
                    		int value = 0;
                    		
                   			// this set affect column 0(license conflict) and 6(Patent retaliation clause) 
                   			SetCompareLicenseAttribute.setCompareAttribute(component_License_Name);
                   			// value: 0 - no conflict / 1 - project license conflict
                   			projectLicenseConflict.projectLicenseConflict(component_License_Name);
                   			cKey = component_License_Name;
                   			value =	bomValues.getProjectLicenseConflict().get(cKey);
                   			if (value == 1) {
                   				projectConflictFileCount++;
                   			}

							licenseValue = component_License_Name;
							idValues.setComponentLicenseName(licenseValue);
                    	}
                    	
                    	// set comment
                    	if(Objects.equals(comment, "null")) {
                    		idValues.setComment("");
                    	} else {
                    		idValues.setComment(comment);
                    	}

                    	// set value for a component
                    	String componentValue = component_Name + component_Version;
                    	                    	
                    	// get a component value
                    	bomFileCount = bomValues.getUComponentFileCount().get(componentValue);
                    	// plus value
                    	bomFileCount++;
                    	// then put value in the component
                    	bomValues.setUComponentFileCount(componentValue, bomFileCount);

                    	// get a component value
                    	licenseFileCount = bomValues.getULicenseFileCount().get(licenseValue);
                    	// plus value
                    	licenseFileCount++;                    	
                    	// then put value in the license
                    	bomValues.setULicenseFileCount(licenseValue, licenseFileCount);
                    	
                    	//set vulnerable components
                    	if(!(Objects.equals(component_Cpe, "null") || Objects.equals(component_Cpe, ""))) {
							if(!vulnerableComponent.getCpeHashmap().containsKey(componentValue)) {
								vulnerableComponent.setComponentName(component_Name);
								vulnerableComponent.setComponentVersion(component_Version);
								vulnerableComponent.setComponentCPE(component_Cpe);
							}
							vulnerableComponent.setCpeHashmap(componentValue, component_Cpe);
                    	}
                    
                    	identifiedFileCount++;

						logger.debug("identifiedFileCount : " + identifiedFileCount + " / " + component_Name);
                    //set values if component_name is not exist
                	} else {
                    	//set Project Name for original files (not identified)
                   		idValues.setComponentName(project_Name);
                    	
                    	//set Project Version for original files (not identified)
                   		idValues.setComponentVersion("Unspecified");
                   		
                        //set Project License for original files (not identified)
                    	idValues.setComponentLicenseName(pValues.getProjectLicense());

						projectFileCount++;

						logger.debug("projectFileCount : " + projectFileCount + " / " + component_Name);
                	}

					// set comment
					if(Objects.equals(comment, "null")) {
						idValues.setComment("");
					} else {
						idValues.setComment(comment);
					}
            	} 
            }

			logger.debug("MatchType Size : " + idValues.getMatchType().size());
			logger.debug("File Path detail : " + idValues.getFilepath().size() + " : " + idValues.getFilepath());

            idValues.setIdentifiedFileCount(identifiedFileCount);
            idValues.setProjectConflictFileCount(projectConflictFileCount);
            idValues.setPendingFileCount(identifiedFileCount);
                   
		} catch (Exception e) {
			logger.error("Exception Message", e);
		}
	}
	
}