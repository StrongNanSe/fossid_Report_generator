package fossid.report.getdata;

import fossid.report.values.LoginValues;
import fossid.report.values.ProjectValues;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Base64.Encoder;

public class GetMatchTypes {
	private final Logger logger = LogManager.getLogger(GetMatchTypes.class);

	public String getMatchType(String filePath) {
		LoginValues lValues = LoginValues.getInstance();
		ProjectValues pValues = ProjectValues.getInstance();
		
		String matchType = "";
		
		Encoder encoder = Base64.getEncoder();		
		byte[] pathByte = filePath.getBytes();
		byte[] pathEncode = encoder.encode(pathByte);
				
		JSONObject dataObject = new JSONObject();
        dataObject.put("username", lValues.getUsername());
        dataObject.put("key", lValues.getApikey());
        dataObject.put("scan_code", pValues.getVersionId());
		dataObject.put("path", new String(pathEncode));
		
		JSONObject rootObject = new JSONObject();
        rootObject.put("group", "files_and_folders");
        rootObject.put("action", "get_fossid_results");
		rootObject.put("data", dataObject);
	
		HttpPost httpPost = new HttpPost(lValues.getServerApiUri());
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
	        JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result);
	        char tmp1 = jsonObj1.get("data").toString().charAt(0);
	        String tmp2 = String.valueOf(tmp1);

			logger.debug("tmp2 : " + tmp2);

	        if(tmp2.equals("[")) {
	        	JSONArray jsonObj2 = (JSONArray) jsonObj1.get("data");
	        	// loop all matches in case of match_type is 'intake'
				for (Object o : jsonObj2) {
					JSONObject jsonObj3 = (JSONObject) o;
					if (jsonObj3.get("match_type").equals("full")) {
						matchType = "Full";
						break;
					} else if (jsonObj3.get("match_type").equals("partial")) {
						matchType = "Partial";
						break;
					}
				}
	        } else {
	        	JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");
				// loop all matches in case of match_type is 'intake'

				logger.debug("jsonObj2 : " + jsonObj2);

				for (Object o : jsonObj2.keySet()) {
					String key = (String) o;
					JSONObject jsonObj3 = (JSONObject) jsonObj2.get(key);

					logger.debug("jsonObj3 : " + jsonObj3);
					logger.debug("match_type : " + jsonObj3.get("match_type"));

					if (jsonObj3.get("match_type").equals("full")) {
						matchType = "Full";
						break;
					} else if (jsonObj3.get("match_type").equals("partial")) {
						matchType = "Partial";
						break;
					}
				}
	        }
		} catch (Exception e) {
			logger.error("Exception Message", e);
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e) {
				logger.error("Exception Message", e);
			}
		}
		return matchType;
	}
}
