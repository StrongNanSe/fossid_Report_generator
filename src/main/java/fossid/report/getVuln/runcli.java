package fossid.report.getVuln;

import fossid.report.values.VulnerableComponents;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class RunCli {
	private static final Logger logger = LogManager.getLogger(RunCli.class);
    public void runCli(String cliPath) {		
    	getProp(cliPath);		
    	cliExe();
	}

	static String fossidCli = "";
	static String cliResult = "";
	
	public void getProp(String cliPath) {
		String propsPath = System.getProperty("user.dir") + "\\config.properties";
		FileReader resources = null;
		Properties props = new Properties();

		try {
			resources = new FileReader(propsPath);
			props.load(resources);

			// To get path of cli
			if(cliPath.equals("")) {
				fossidCli = props.getProperty("fossid.cli");
			} else {
				fossidCli = cliPath;
			}

			String end = fossidCli.substring(fossidCli.length() - 1);

			// To set path based on platform
			if(!(end.equals("/") || end.equals("\\"))) {
				if(OsValidator.isWindows()) {
					fossidCli = fossidCli + "\\";
				} else if (OsValidator.isMac() || OsValidator.isSolaris() || OsValidator.isUnix()){
					fossidCli = fossidCli + "/";
				}
			}
		} catch (IOException e1) {
			logger.error("Exception Message", e1);
		} finally {
			try {
				if (resources != null) {
					resources.close();
				}
			} catch (Exception e) {
				logger.error("Exception Message", e);
			}
		}

	}

	public void cliExe() {
		VulnerableComponents vulnerableComponent = VulnerableComponents.getInstance();

		String command;
//		String vulnResult = "";

		for(int i = 0; i < vulnerableComponent.getComponentCPE().size(); i++) {
			String componentCPE = vulnerableComponent.getComponentCPE().get(i);
			String componentVersion = vulnerableComponent.getComponentVersion().get(i);
			String componentName = vulnerableComponent.getComponentName().get(i);

			if(OsValidator.isWindows()){
				
				try {					
					command = fossidCli + "fossid-cli.exe --cpe " + componentCPE;
					shellCmd(command);
				} catch (Exception e) {
					logger.error("Exception Message", e);
				}

				logger.debug("cliResult : " + cliResult);

				SetVulnData.setData(cliResult, componentCPE,
						componentName, componentVersion);
			} else if(OsValidator.isUnix() || OsValidator.isSolaris()){
				
				try {					
					command = fossidCli + "fossid-cli --cpe " + componentCPE;
					shellCmd(command);
				} catch (Exception e) {
					logger.error("Exception Message", e);
				}		
				
				SetVulnData.setData(cliResult, componentCPE,
						componentName, componentVersion);
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
			  logger.debug("cliResult = " + result);
        }
	}
	
}