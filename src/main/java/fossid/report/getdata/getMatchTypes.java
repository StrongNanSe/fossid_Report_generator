package fossid.report.getdata;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Iterator;
import java.util.Base64.Encoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import fossid.report.values.LoginValues;
import fossid.report.values.ProjectValues;

public class GetMatchTypes {
	
	public String getmatchtype(String filePath) {	
		
	
		LoginValues lvalues = LoginValues.getInstance();
		ProjectValues pvalues = ProjectValues.getInstance();
		
		String matchType = "";
		
		Encoder encoder = Base64.getEncoder();		
		byte[] pathByte = filePath.getBytes();
		byte[] pathEncode = encoder.encode(pathByte);
				
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
        dataObject.put("scan_code", pvalues.getVersionId());
		dataObject.put("path", new String(pathEncode));
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "files_and_folders");
        rootObject.put("action", "get_fossid_results");
		rootObject.put("data", dataObject);
	
		HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		try {

			StringEntity entity = new StringEntity(rootObject.toString(), "UTF-8");
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);			
			
			
			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException(
						"Failed : HTTP error code : " + httpClientResponse.getStatusLine().getStatusCode());
			}	
								
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();
					
			JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());
	        char tmp1 = jsonObj1.get("data").toString().charAt(0);
	        String tmp2 = String.valueOf(tmp1);
	        
	        if(tmp2.equals("[")) {
	        	JSONArray jsonObj2 = (JSONArray) jsonObj1.get("data");
	        	// loop all matches in case of match_type is 'intake'
	        	for(int i=0; i < jsonObj2.size(); i++) {
	        		JSONObject jsonObj3 = (JSONObject) jsonObj2.get(i);	        	
	        		if(jsonObj3.get("match_type").equals("full")) {
	        			matchType = "Full";
	        			break;
	        		} else if(jsonObj3.get("match_type").equals("partial")) {
	        			matchType = "Partial";
	        			break;
	        		} 
	        	}
	        } else {
	        	JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");
		        Iterator iter = jsonObj2.keySet().iterator();
	        	// loop all matches in case of match_type is 'intake'
		        while(iter.hasNext()) {
	            	String key = (String) iter.next();
	            	JSONObject jsonObj3 = (JSONObject) jsonObj2.get(key);	            		            	
	            	if(jsonObj3.get("match_type").equals("full")) {
	        			matchType = "Full";
	        			break;
	        		} else if(jsonObj3.get("match_type").equals("partial")) {
	        			matchType = "Partial";
	        			break;
	        		} 
		        }	    	
	        }	   
	
	    httpClient.close();
	    
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return matchType;		
	}
}
