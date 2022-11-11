package fossid.report.getdata;

import fossid.report.values.LoginValues;
import fossid.report.values.ProjectValues;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

;

public class GetLoginInfo {
	private final Logger logger = LogManager.getLogger(GetLoginInfo.class);

	public void getInfo(String protocol, String address, String userName, String apikey) {
		getLoginInfo(protocol, address, userName, apikey);
	}
	
	private void getLoginInfo(String protocol, String address, String userName, String apiKey) {
		LoginValues lvalues = LoginValues.getInstance();
		ProjectValues pValues = ProjectValues.getInstance();
		
		try {
			Properties props = new Properties();
			InputStream is = getClass().getResourceAsStream("/config.properties");
			props.load(is);
			
			String schema = "";
			String url = "";

			if(protocol.equals("")) {
				schema = props.getProperty("fossid.schema");
			} else {
				schema = protocol;
			}
			
			if(address.equals("")) {
				url = props.getProperty("fossid.domain");
			} else {
				url = address;
			}
			
			if(schema.equals("http")) {
				
				lvalues.setServerUri("http://" + url);
				
				//check "fossid.domain" to add / in front of api.php
				String temp = lvalues.getServerUri();
				temp = temp.substring(temp .length() - 1, temp.length());
				
				if(temp.endsWith("/")) {
					lvalues.setServerApiUri("http://" + url + "api.php");
				} else {
					lvalues.setServerApiUri("http://" + url + "/api.php");
				}
				
			} else if(schema.equals("https")) {
				lvalues.setServerUri("https://" + url);
				
				//check "fossid.domain" to add / in front of api.php
				String temp = lvalues.getServerUri();				
				temp = temp.substring(temp .length() - 1, temp.length());
				
				if(temp.endsWith("/")) {
					lvalues.setServerApiUri("https://" + url + "api.php");
				} else {
					lvalues.setServerApiUri("https://" + url + "/api.php");
				}				
			}
			
			String username = "";
			String apikey = "";
			
			if(userName.equals("")) {
				username = props.getProperty("fossid.username");
			} else {
				username = userName;
			}
			
			if(apiKey.equals("")) {
				apikey = props.getProperty("fossid.apikey");
			} else {
				apikey = apiKey;
			}
			
			lvalues.setUsername(username);			
			lvalues.setApikey(apikey);
		} catch (IOException e) {
			logger.error("Exception Message", e);
		}
	}
}
