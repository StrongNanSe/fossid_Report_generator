package fossid.report.main;

import fossid.report.attribute.SetProjectLicenseAttribute;
import fossid.report.excel.CreateReport;
import fossid.report.getVuln.RunCli;
import fossid.report.getdata.GetBillOfMaterials;
import fossid.report.getdata.GetFolderMatrices;
import fossid.report.getdata.GetIdentifiedFiles;
import fossid.report.getdata.GetLoginInfo;
import fossid.report.getdata.GetProjectVersionInfo;
import jxl.write.WriteException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);
	private static final long start = System.currentTimeMillis();

	public static void main(String[] args) {
		try {
			
			String protocol = "";
			String address = "";
			String userName = "";
			
			String apikey = "";
			String projectName = "";
			String scanName = "";
			String license = "";
			String cliPath = "";
			
			for(int i = 0; i < args.length; i++) {
				
				if(args[i].equals("--protocol")) {
					protocol = args[i+1]; 
				}
				
				if(args[i].equals("--address")) {
					address = args[i+1];
				}
				
				if(args[i].equals("--username")) {
					userName = args[i+1]; 
				}
				
				if(args[i].equals("--apikey")) {
					apikey = args[i+1];
				}
				
				if(args[i].equals("--projectname")) {
					projectName = args[i+1]; 
				}
				
				if(args[i].equals("--scanname")) {
					scanName = args[i+1];
				}
				
				if(args[i].equals("--license")) {
					license = args[i+1]; 
				}
				
				if(args[i].equals("--clipath")) {
					cliPath = args[i+1]; 
				}
				
				i++;
			}
			
			PrintInfo.startFOSSID();
			
			GetLoginInfo common = new GetLoginInfo();
			common.getInfo(protocol, address, userName, apikey);
			
			ValidateAuthentication.validateAuthentication();
			
			GetProjectVersionInfo pvInfo = new GetProjectVersionInfo();
			pvInfo.getInfo(projectName, scanName, license);
			
			PrintInfo.printInfo();
			
			SetProjectLicenseAttribute attributeInfo = new SetProjectLicenseAttribute();
			attributeInfo.getInfo();
			
			GetBillOfMaterials getBom = new GetBillOfMaterials();
			getBom.getInfo();
			
			// to set total and pending file count 
			GetFolderMatrices folderMatrices = new GetFolderMatrices();
			folderMatrices.getMatrices();
			
			GetIdentifiedFiles idFiles = new GetIdentifiedFiles();
			idFiles.getData();
			
			RunCli runCli = new RunCli();
			runCli.runCli(cliPath);
			
			CreateReport writeExcel = new CreateReport();
			writeExcel.writeReport();

			PrintInfo.endFOSSID();

			long end = System.currentTimeMillis();
			logger.info("실행시간 : " + (end - start)/60000 + "m" + ((end - start)%60000)/1000 + "s");
		} catch (WriteException e) {
			logger.error("Exception Message", e);
			System.exit(1);
		}
	}
}
