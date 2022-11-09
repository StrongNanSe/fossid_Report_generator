package fossid.report.getdata;

import fossid.report.values.IdentifiedFilesValues;
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
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetNumberOfIgnored {
	
	public void setNumber() {
		int number = 0;
		
		IdentifiedFilesValues idValues = IdentifiedFilesValues.getInstance();
		
		LoginValues lvalues = LoginValues.getInstance();
		ProjectValues pvalues = ProjectValues.getInstance();
		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
        dataObject.put("scan_code", pvalues.getVersionId()); 
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "get_ignored_files");
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
			
			//System.out.println(result.toString());
						
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());	        
	        JSONArray jsonArr1 = (JSONArray) jsonObj1.get("data");
	        
	        number = jsonArr1.size();        
	        
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

		idValues.setIgnoredFileCount(number);
		
	}

}
