package fossid.report.getdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fossid.report.attribute.setCompareLicenseAttribute;
import fossid.report.values.billofmaterialsValues;
import fossid.report.values.compareLicenseAttributeValues;
import fossid.report.values.identifiedFilesValues;
import fossid.report.values.loginValues;
import fossid.report.values.projectValues;

public class getBillofMaterials {
	
	public void getInfo() {
		getBom();
	}

	private void getBom() {
		
		billofmaterialsValues bomValues = billofmaterialsValues.getInstance();
		setCompareLicenseAttribute componetLicenseAttribute = new setCompareLicenseAttribute();
		compareLicenseAttributeValues componentlicenseAttribute = compareLicenseAttributeValues.getInstance();		
		identifiedFilesValues idValues = identifiedFilesValues.getInstance();
		
		loginValues lvalues = loginValues.getInstance();
		projectValues pvalues = projectValues.getInstance();
		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
        dataObject.put("scan_code", pvalues.getVersionId()); 
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "get_scan_identified_components");
		rootObject.put("data", dataObject);	
				
		HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();	
		
		try {
			StringEntity entity = new StringEntity(rootObject.toString(), "UTF-8");
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
			
			//System.out.println("bom"+result.toString());
						
	        JSONParser jsonParser = new JSONParser();
	        //JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());	        
	        //JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");
	        
	        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());
	        //System.out.println("object1: " + jsonObj1.toString());	        
	        if(jsonObj1.toString().contains("You are not the creator of the scan nor part of the scan project. Please assign yourself as a project member.")) {
	        	System.out.println("You are not the creator of the scan nor part of the scan project. Please assign yourself as a project member.");
	        	System.exit(1);
	        }
	        
	        if(jsonObj1.get("data") == null) {
	        	System.out.println("Please, check \"" + pvalues.getVersionName() + "\" is mapped to \"" + pvalues.getProjectName() + "\"");
	        	System.exit(1);
	        } 
	        
	        String temp = jsonObj1.get("data").toString();
	        JSONObject jsonObj2 = null;
	        
	        if(temp.equals("false")) {	        	
	        	System.out.println("This scan does not include identification information. You may check the [File license only] or [Empty identificatin] in 'Makrded as identified' tab");	        		        	
	        	System.out.println();
	        } else {
	        	jsonObj2 = (JSONObject) jsonObj1.get("data");
	        }
	        
	        Iterator iter = null;
	        
	        // to count patentIssueFile
            int patentIssueFileCount = 0;
            
            String url = "";
	        
	        if(jsonObj2 == null) {	        		        	
	        } else {
	        	iter = jsonObj2.keySet().iterator();
	        	
	        	while(iter.hasNext()) {	        	
	            	// set key value to key
	            	String key = (String) iter.next();         
	            	// get values from key
	            	JSONObject tempObj = (JSONObject) jsonObj2.get(key);
	            	
	            	//System.out.println(tempObj.toString());
	            	
	            	if(tempObj.get("version") == null || tempObj.get("name").toString().isEmpty()) {
	            		bomValues.setUcomponentName("Unspecified");
	            	} else {
	            		bomValues.setUcomponentName(tempObj.get("name").toString());
	            	}
	            	
	            	if(tempObj.get("version") == null || tempObj.get("version").toString().isEmpty()) {
	            		bomValues.setUcomponentVersion("Unspecified");
	            		bomValues.setUcomponentFileCount(tempObj.get("name").toString() + "Unspecified", 0);
	            	} else {
	            		bomValues.setUcomponentVersion(tempObj.get("version").toString());
	            		bomValues.setUcomponentFileCount(tempObj.get("name").toString() + tempObj.get("version").toString(), 0);
	            	} 
	            	
	            	
	            	url = ("Unspecified");
	            	
	            	if(tempObj.get("url") == null || tempObj.get("supplier_url") == null || tempObj.get("community_url") == null || tempObj.get("download_url") == null) {
	            		
	            	} else {
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
	            	
	            	
	            	bomValues.setUcomponentHompage(url);
	            		
	            	if(tempObj.get("license_name") == null || tempObj.get("license_name").toString().isEmpty()) {            		
	               		bomValues.setUcomponentLicenseName("Unspecified");
	               		if(!bomValues.getUlicenseFileCount().containsKey("Unspecified")){
	               			// To set licenseFileCount key/value
	               			bomValues.setUlicenseFileCount("Unspecified", 0);
	               			// To set licenseConflict key/value
	               			bomValues.setProjectLicenseConflict("Unspecified", 0);
	               		}
	               		
	            		// set lisense name
	           			componetLicenseAttribute.setCompareAttribute("Unspecified");
	           			// get attirbute value regarding patent
	           			if(componentlicenseAttribute.getcoAttribute6().equals("O")){
	           				patentIssueFileCount++;                   			
	           			}         
	                } else {
	            		// set license name as 'Unspecified' if Not Applicable
	                	String licenseName = tempObj.get("license_name").toString();
	            		if(licenseName.equals("Not Applicable")) {
	            			licenseName = "Unspecified";
	            		}

	            		bomValues.setUcomponentLicenseName(licenseName);
	            		if(!bomValues.getUlicenseFileCount().containsKey(licenseName)){
	               			// To set licenseFileCount key/value
	            			bomValues.setUlicenseFileCount(licenseName, 0);
	               			// To set licenseConflict key/value
	            			bomValues.setProjectLicenseConflict(licenseName, 0);            			
	               		}
	            		
	            		// set lisense name
	           			componetLicenseAttribute.setCompareAttribute(licenseName);
	           			// get attirbute value regarding patent
	           			if(componentlicenseAttribute.getcoAttribute6().equals("O")){
	           				patentIssueFileCount++;                   			
	           			}         
	            	}
		        }
	        }	        
		    
        	        	
        	idValues.setpatentIssueFileCount(patentIssueFileCount);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
			System.exit(1);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (ParseException e) {						
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}	
		
	}
	
}
