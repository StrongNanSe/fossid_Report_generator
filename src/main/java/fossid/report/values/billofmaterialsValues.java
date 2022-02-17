package fossid.report.values;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class billofmaterialsValues {

	private static billofmaterialsValues values = new billofmaterialsValues();

	private billofmaterialsValues() {
	}

	public static billofmaterialsValues getInstance() {
		return values;
	}

	private ArrayList<String> ucomponentDownload = new ArrayList<String>();
	private ArrayList<String> ucomponenetName = new ArrayList<String>();
	private ArrayList<String> ucomponenetVersion = new ArrayList<String>();
	private ArrayList<String> ucomponenetHomepage = new ArrayList<String>();
	private ArrayList<String> ucomponentLicenseName = new ArrayList<String>();
	private ArrayList<String> licenseWithProjectConflict = new ArrayList<String>();
	private ArrayList<String> licenseWithoutProjectConflict = new ArrayList<String>();	
	private ArrayList<String> componentConflictLicense = new ArrayList<String>();
	private HashMap<String, Integer> ucomponentFileCount = new HashMap<>();	 
	private Map<String, Integer> ulicenseFileCount = new HashMap<>();
	private Map<String, Integer> projectLicenseConflict = new HashMap<>();
	private Map<String, Integer> componentLicenseConflict = new HashMap<>();
	private Map<String, String> licenseMatchType = new HashMap<>();
	
	public ArrayList<String> getUcomponentDownload() {
		return ucomponentDownload;
	}
	public void setUcomponenDownload(String downloadurl) {
		this.ucomponentDownload.add(downloadurl);
	}	
	
	public ArrayList<String> getUcomponentName() {
		return ucomponenetName;
	}
	public void setUcomponentName(String ucomponenetName) {
		this.ucomponenetName.add(ucomponenetName);
	}
	
	public ArrayList<String> getUcomponentVersion() {
		return ucomponenetVersion;
	}
	public void setUcomponentVersion(String ucomponentVersion) {
		this.ucomponenetVersion.add(ucomponentVersion);
	}
	
	public ArrayList<String> getUcomponentHomepage() {
		return ucomponenetHomepage;
	}
	public void setUcomponentHompage(String ucomponenetHomepage) {
		this.ucomponenetHomepage.add(ucomponenetHomepage);
	}
	
	public ArrayList<String> getUcomponentLicenseName() {
		return ucomponentLicenseName;
	}
	public void setUcomponentLicenseName(String ucomponentLicenseName) {
		this.ucomponentLicenseName.add(ucomponentLicenseName);
	}
	
	public  HashMap<String, Integer> getUcomponentFileCount() {
		return ucomponentFileCount;
	}
	public void setUcomponentFileCount(String key, Integer value) {
		this.ucomponentFileCount.put(key, value);
	}
	
	public  Map<String, Integer> getUlicenseFileCount() {
		return ulicenseFileCount;
	}
	public void setUlicenseFileCount(String key, Integer value) {
		this.ulicenseFileCount.put(key, value);
	}
	// to compare project license
	public  Map<String, Integer> getProjectLicenseConflict() {
		return projectLicenseConflict;
	}
	public void setProjectLicenseConflict(String key, Integer value) {
		this.projectLicenseConflict.put(key, value);
	}	
	public ArrayList<String> getLicensewithProjectConflict() {
			return licenseWithProjectConflict;
	}
	public void setLicensetwithProjectConflict(String licenseWithProjectConflict) {
		this.licenseWithProjectConflict.add(licenseWithProjectConflict);
	}

	public ArrayList<String> getLicensewithoutProjectConflict() {
		return licenseWithoutProjectConflict;
	}
	public void setLicensetwithoutProjectConflict(String licenseWithoutProjectConflict) {
		this.licenseWithoutProjectConflict.add(licenseWithoutProjectConflict);
	}
	
	public  Map<String, Integer> getComponentLicenseConflict() {
		return componentLicenseConflict;
	}
	public void setComponentLicenseConflict(String key, Integer value) {
		this.componentLicenseConflict.put(key, value);
	}

	public ArrayList<String> getcomponentConflictLicense() {
		return componentConflictLicense;
	}
	public void setcomponentConflictLicense(String componentConflictLicense) {
		this.componentConflictLicense.add(componentConflictLicense);
	}
	
	public  Map<String, String> getlicenseMatchType() {
		return licenseMatchType;
	}
	public void setlicenseMatchType(String key, String value) {
		this.licenseMatchType.put(key, value);
	}

}
