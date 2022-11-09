package fossid.report.values;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BillOfMaterialsValues implements Serializable {

	private static final BillOfMaterialsValues values = new BillOfMaterialsValues();

	private BillOfMaterialsValues() {
	}

	public static BillOfMaterialsValues getInstance() {
		return values;
	}

	private final ArrayList<String> uComponentDownload = new ArrayList<String>();
	private final ArrayList<String> uComponentName = new ArrayList<String>();
	private final ArrayList<String> uComponentVersion = new ArrayList<String>();
	private final ArrayList<String> uComponentHomepage = new ArrayList<String>();
	private final ArrayList<String> uComponentLicenseName = new ArrayList<String>();
	private final ArrayList<String> licenseWithProjectConflict = new ArrayList<String>();
	private final ArrayList<String> licenseWithoutProjectConflict = new ArrayList<String>();	
	private final ArrayList<String> componentConflictLicense = new ArrayList<String>();
	private final HashMap<String, Integer> uComponentFileCount = new HashMap<>();
	private final Map<String, Integer> uLicenseFileCount = new HashMap<>();
	private final Map<String, Integer> projectLicenseConflict = new HashMap<>();
	private final Map<String, Integer> componentLicenseConflict = new HashMap<>();
	private final Map<String, String> licenseMatchType = new HashMap<>();
	
	public ArrayList<String> getUComponentDownload() {
		return uComponentDownload;
	}
	public void setUComponentDownload(String downLoadUrl) {
		this.uComponentDownload.add(downLoadUrl);
	}	
	
	public ArrayList<String> getUComponentName() {
		return uComponentName;
	}
	public void setUComponentName(String uComponentName) {
		this.uComponentName.add(uComponentName);
	}
	
	public ArrayList<String> getUComponentVersion() {
		return uComponentVersion;
	}
	public void setUComponentVersion(String uComponentVersion) {
		this.uComponentVersion.add(uComponentVersion);
	}
	
	public ArrayList<String> getUComponentHomepage() {
		return uComponentHomepage;
	}
	public void setUComponentHomepage(String uComponentHomepage) {
		this.uComponentHomepage.add(uComponentHomepage);
	}
	
	public ArrayList<String> getUComponentLicenseName() {
		return uComponentLicenseName;
	}
	public void setUComponentLicenseName(String uComponentLicenseName) {
		this.uComponentLicenseName.add(uComponentLicenseName);
	}
	
	public  HashMap<String, Integer> getUComponentFileCount() {
		return uComponentFileCount;
	}
	public void setUComponentFileCount(String key, Integer value) {
		this.uComponentFileCount.put(key, value);
	}
	
	public  Map<String, Integer> getULicenseFileCount() {
		return uLicenseFileCount;
	}
	public void setULicenseFileCount(String key, Integer value) {
		this.uLicenseFileCount.put(key, value);
	}
	// to compare project license
	public  Map<String, Integer> getProjectLicenseConflict() {
		return projectLicenseConflict;
	}
	public void setProjectLicenseConflict(String key, Integer value) {
		this.projectLicenseConflict.put(key, value);
	}	
	public ArrayList<String> getLicenseWithProjectConflict() {
			return licenseWithProjectConflict;
	}
	public void setLicenseWithProjectConflict(String licenseWithProjectConflict) {
		this.licenseWithProjectConflict.add(licenseWithProjectConflict);
	}

	public ArrayList<String> getLicenseWithoutProjectConflict() {
		return licenseWithoutProjectConflict;
	}
	public void setLicenseWithoutProjectConflict(String licenseWithoutProjectConflict) {
		this.licenseWithoutProjectConflict.add(licenseWithoutProjectConflict);
	}
	
	public  Map<String, Integer> getComponentLicenseConflict() {
		return componentLicenseConflict;
	}
	public void setComponentLicenseConflict(String key, Integer value) {
		this.componentLicenseConflict.put(key, value);
	}

	public ArrayList<String> getComponentConflictLicense() {
		return componentConflictLicense;
	}
	public void setComponentConflictLicense(String componentConflictLicense) {
		this.componentConflictLicense.add(componentConflictLicense);
	}
	
	public  Map<String, String> getLicenseMatchType() {
		return licenseMatchType;
	}
	public void setLicenseMatchType(String key, String value) {
		this.licenseMatchType.put(key, value);
	}

}
