# Usage
1. Download FOSSID CLI, please ask the way downloading FOSSID CLI to your FOSSID administrator
2. Edit the config file, 'config.properties'
```
- fossid.domain: FOSSID domain address 
  + e.g.) fossid.osbc.co.kr/webapp
- fossid.schema: FOSSID webserver protocol
  + e.g.) http or https
- fossid.username: FOSSID username
- fossid.apikey: FOSSID apikey
  + You need to generate apikey before running this tool on FOSSID user menu.
- fossid.project: FOSSID Project Name
- fossid.scan: FOSSID Scan Name
  + You need to map a scan to a project on FOSSID 
- fossid.license: 
  + Please, appply one of licenses listed in the "license_Attribute.json" file. This license will be a policy of project/scan.
 - fossid.cli: 
  + Please, set a path of FOSSIC CLI  
```
3. Reveiw the license policy file, 'licensese_Attribute.json'.
4. Run the jar file
```
$ java -jar fossid_report.jar --protocol https --address fossid.osbc.co.kr/webapp --username byunghoon --apikey 12345 --projectname project --scanname scan --license "LICENSE NAME" --clipath "c:\\path\\to\\fossid\\cli\\path"

Parameters:
--protocol
--address
--username
--apikey
--projectname
--scanname
--license
--clipath

Note: Parameters will overwrite the configurations of the 'config.properties' file.
```


# Sheet Description
- Sheet1: Summary of project/scan
- Sheet2: 
  + Describe the reason why discovered licenses are conflicted with other licenses based on the license attributes
  + Describe manually the opinion, how to resolve the license conflict issues
- Sheet3: Bill of materials
  + Red Components: Conflict with the project license
  + Yellow Components: Conflict with licenses. But, do not conflict with the project license
  + Green Components: No conflict with licenses
  + If discovered licenses are not defined in the 'license_Attribute.json' file, the license attribute is set as 'Unspecified' license. This means that user need to review or add the licenses in the 'license_Attribute.json' file and you can find out the license list at the end of log which displayed on the screen as below
	```
	The license compatibility is reviewed by "license_Attribute.json" file
	Below license is not in the "license_Attirbute.json" file and reviewed as "Unspecified" license
	Please, review below license or add license attirbutes in the 'license_Attribute.json" file and run exporting report again
	- Free Software Foundation - Unlimited License
	```
- Sheet4: Summary of license list
- Sheet5: Identified Files List
- Sheet6: Discovered "Severity: HIGH" and "Severity: MEDIUM" vulnerabilities based on CVSS v2.0


# Backlog
- If the files are analyzed over 65,000, the shee5 need to be separated into multiple sheets.