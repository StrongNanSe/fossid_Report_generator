package fossid.report.values;

import java.io.Serializable;

public class LoginValues implements Serializable {

	private static LoginValues values = new LoginValues();

	public LoginValues() {
	}

	public static LoginValues getInstance() {
		return values;
	}

	private String serveruri;
	private static String serverApiuri;
	private static String username;
	private static String apikey;	
	
	public String getServerUri() {
		return serveruri;
	}

	public void setServerUri(String serveruri) {
		this.serveruri = serveruri;
	}

	public String getServerApiUri() {
		return serverApiuri;
	}

	public void setServerApiUri(String serverApiuri) {
		this.serverApiuri = serverApiuri;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	
}
