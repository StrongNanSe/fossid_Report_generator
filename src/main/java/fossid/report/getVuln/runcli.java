package fossid.report.getVuln;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import fossid.report.getVuln.osValidator;
import fossid.report.getVuln.setVulnData;
import fossid.report.values.vulnerableComponents;

public class runcli {
	
    public void runCli(String cliPath) {		
    	getProp(cliPath);		
    	cliexe();
	}
    
	static osValidator osValidation = new osValidator();
	static String fossidCli = "";
	static String cliResult = "";
	
	public void getProp(String cliPath) {
		Properties props = new Properties();
		InputStream is = getClass().getResourceAsStream("/config.properties");
		
		try {
			props.load(is);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			if(osValidation.isWindows()) {
				fossidCli = fossidCli + "\\";
			} else if (osValidation.isMac() || osValidation.isSolaris() || osValidation.isUnix()){					
				fossidCli = fossidCli + "/";
			}
		}		
	}

	public void cliexe() {
		vulnerableComponents vulnerableComponent = vulnerableComponents.getInstance();
		setVulnData setData = new setVulnData();
		
		String command = "";
		String vulnResult = "";
		
		for(int i = 0 ; i < vulnerableComponent.getcomponentCPE().size(); i++) {
			if(osValidation.isWindows()){
				
				try {					
					command = fossidCli + "fossid-cli.exe --cpe " + vulnerableComponent.getcomponentCPE().get(i);					
					shellCmd(command);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
				//System.out.print(cliResult);				
				setData.setData(cliResult, vulnerableComponent.getcomponentCPE().get(i).toString(),
						vulnerableComponent.getcomponentName().get(i).toString(), vulnerableComponent.getcomponentVersion().get(i).toString());
			} else if(osValidation.isUnix() || osValidation.isSolaris()){
				
				try {					
					command = fossidCli + "fossid-cli --cpe " + vulnerableComponent.getcomponentCPE().get(i);					
					shellCmd(command);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
				setData.setData(cliResult, vulnerableComponent.getcomponentCPE().get(i).toString(),
						vulnerableComponent.getcomponentName().get(i).toString(), vulnerableComponent.getcomponentVersion().get(i).toString());
			}
		}
		
		//System.out.println();
			
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