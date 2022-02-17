package fossid.report.main;

import fossid.report.attribute.setProjectLicenseAttribute;
import fossid.report.excel.CreateReport;
import fossid.report.getVuln.runcli;
import fossid.report.getdata.getBillofMaterials;
import fossid.report.getdata.getFolderMatrics;
import fossid.report.getdata.getIdentifiedFiles;
import fossid.report.getdata.getLoginInfo;
import fossid.report.getdata.getprojectVersionInfo;
import jxl.write.WriteException;

public class main {

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
			
			printInfo print = new printInfo();
			print.startFOSSID();
			
			getLoginInfo common = new getLoginInfo();
			common.getInfo(protocol, address, userName, apikey);
			
			validateAuthentication validation = new validateAuthentication();
			validation.validateauthentication();
			
			getprojectVersionInfo pvInfo = new getprojectVersionInfo();
			pvInfo.getInfo(projectName, scanName, license);
			
			print.printinfo();
			
			setProjectLicenseAttribute attributeInfo = new setProjectLicenseAttribute();
			attributeInfo.getInfo();
			
			getBillofMaterials getBom = new getBillofMaterials();
			getBom.getInfo();
			
			// to set total and pending file count 
			getFolderMatrics folderMatrics = new getFolderMatrics();
			folderMatrics.getMatrics();
			
			getIdentifiedFiles idFiles = new getIdentifiedFiles();
			idFiles.getData();
			
			runcli runCli = new runcli();			
			runCli.runCli(cliPath);
			
			CreateReport writeExcel = new CreateReport();
			writeExcel.writeReport();

			print.endFOSSID();
			
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
	}
}
