package fossid.report.getdata;

import fossid.report.values.IdentifiedFilesValues;
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
import java.io.InputStreamReader;

public class GetNumberOfIgnored {
	private final Logger logger = LogManager.getLogger(GetNumberOfIgnored.class);
	
	public void setNumber() {
		int number = 0;
		
		IdentifiedFilesValues idValues = IdentifiedFilesValues.getInstance();
		
		LoginValues lValues = LoginValues.getInstance();
		ProjectValues pValues = ProjectValues.getInstance();
		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lValues.getUsername());
        dataObject.put("key", lValues.getApikey());
        dataObject.put("scan_code", pValues.getVersionId());
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "get_ignored_files");
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
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();

			logger.debug(result);

	        JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result);
	        JSONArray jsonArr1 = (JSONArray) jsonObj1.get("data");
	        
	        number = jsonArr1.size();
		} catch (Exception e) {
			logger.error("Exception Message", e);
		}

		idValues.setIgnoredFileCount(number);
	}
}
