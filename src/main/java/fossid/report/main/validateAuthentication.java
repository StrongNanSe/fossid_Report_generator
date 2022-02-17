package fossid.report.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import fossid.report.values.loginValues;

public class validateAuthentication {
	
	public static void validateauthentication() {
		
		loginValues lvalues = loginValues.getInstance();
		
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lvalues.getUsername());
        dataObject.put("key", lvalues.getApikey());
        dataObject.put("searched_username", lvalues.getUsername());
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "users");
        rootObject.put("action", "get_information");
		rootObject.put("data", dataObject);		
		
		HttpPost httpPost = new HttpPost(lvalues.getServerApiUri());
		HttpClient httpClient = HttpClientBuilder.create().build();		
		
		try {

			StringEntity entity = new StringEntity(rootObject.toString());
			httpPost.addHeader("content-type", "application/json");
			httpPost.setEntity(entity);
			
			HttpResponse httpClientResponse = httpClient.execute(httpPost);		
			
			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				System.out.println("Please, check the fossid.domain/fossid.schema in the config.properties file or --protocol/--address values");
				System.out.println("Failed : HTTP Error code : " + httpClientResponse.getStatusLine().getStatusCode());
				System.exit(1);				
			} 
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();
			
			JSONParser jsonParser = new JSONParser();
	        JSONObject jsonObj = (JSONObject) jsonParser.parse(result.toString());	        
	        
	        // set false if valiation is failed 
	        if(jsonObj.get("status").equals("0")){
	        	System.out.println("Please, check the fossid.username/fossid.apikey in the config.properties file or --username/--apikey values");
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	

}
