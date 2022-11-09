package fossid.report.getVuln;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import fossid.report.values.VulnerableComponents;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RunCli {
	private final Logger logger = LogManager.getLogger(RunCli.class);
    public void runCli(String cliPath) {		
    	getProp(cliPath);		
    	cliexe();
	}

	static String fossidCli = "";
	static String cliResult = "";
	
	public void getProp(String cliPath) {
		Properties props = new Properties();
		InputStream is = getClass().getResourceAsStream("/config.properties");
		
		try {
			props.load(is);
		} catch (IOException e1) {
			logger.error("Exception Message", e1);
		}
		
		// To get path of cli
		if(cliPath.equals("")) {
			fossidCli = props.getProperty("fossid.cli");
		} else {
			fossidCli = cliPath;
		}
				
		String end = fossidCli.substring(fossidCli.length() - 1, fossidCli.length());		
		  
		// To set path based on platform
		if(end.equals("/") || end.equals("\\")) {		  		
		 } else {
			if(OsValidator.isWindows()) {
				fossidCli = fossidCli + "\\";
			} else if (OsValidator.isMac() || OsValidator.isSolaris() || OsValidator.isUnix()){
				fossidCli = fossidCli + "/";
			}
		}		
	}

	public void cliexe() {
		VulnerableComponents vulnerableComponent = VulnerableComponents.getInstance();

		String command = "";
		String vulnResult = "";
		
		for(int i = 0 ; i < vulnerableComponent.getcomponentCPE().size(); i++) {
			if(OsValidator.isWindows()){
				
				try {					
					command = fossidCli + "fossid-cli.exe --cpe " + vulnerableComponent.getcomponentCPE().get(i);					
					shellCmd(command);
				} catch (Exception e) {
					logger.error("Exception Message", e);
				}

				logger.debug(cliResult);

				SetVulnData.setData(cliResult, vulnerableComponent.getcomponentCPE().get(i).toString(),
						vulnerableComponent.getcomponentName().get(i).toString(), vulnerableComponent.getcomponentVersion().get(i).toString());
			} else if(OsValidator.isUnix() || OsValidator.isSolaris()){
				
				try {					
					command = fossidCli + "fossid-cli --cpe " + vulnerableComponent.getcomponentCPE().get(i);					
					shellCmd(command);
				} catch (Exception e) {
					logger.error("Exception Message", e);
				}		
				
				SetVulnData.setData(cliResult, vulnerableComponent.getcomponentCPE().get(i).toString(),
						vulnerableComponent.getcomponentName().get(i).toString(), vulnerableComponent.getcomponentVersion().get(i).toString());
			}
		}
	}
	
	public static void shellCmd(String command) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String result;
        
        while((result = br.readLine()) != null) {
              cliResult = result;
              //System.out.println("cliResult = " + result);
        }
	}
	
}