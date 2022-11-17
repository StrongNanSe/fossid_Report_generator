package fossid.report.getdata;

import fossid.report.values.IdentifiedFilesValues;
import fossid.report.values.LoginValues;
import fossid.report.values.ProjectValues;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetFolderMatrices {
	private IdentifiedFilesValues idValues = IdentifiedFilesValues.getInstance();
	public void getMatrices() {
		LoginValues lvalues = LoginValues.getInstance();
		ProjectValues pvalues = ProjectValues.getInstance();
					
		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
        dataObject.put("scan_code", pvalues.getVersionId());		
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "scans");
        rootObject.put("action", "get_folder_metrics");
		rootObject.put("data", dataObject);
		
		
		HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();
		
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
	        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result);
	        JSONObject jsonObj2 = (JSONObject) jsonParser.parse(jsonObj1.get("data").toString());
	        
	        int fileTotalCount = Integer.parseInt(jsonObj2.get("total").toString());
	        
            idValues.setGetFileTotalCount(fileTotalCount);
	        
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
