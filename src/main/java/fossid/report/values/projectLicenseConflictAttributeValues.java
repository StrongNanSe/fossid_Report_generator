package fossid.report.values;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectLicenseConflictAttributeValues implements Serializable {
	
	private static ProjectLicenseConflictAttributeValues values = new ProjectLicenseConflictAttributeValues();

	private ProjectLicenseConflictAttributeValues() {
	}

	public static ProjectLicenseConflictAttributeValues getInstance() {
		return values;
	}
	
	private final ArrayList<String> prLicenseName = new ArrayList<String>();
	private final ArrayList<String> prConflictReason = new ArrayList<String>();
	
	public ArrayList<String> getPrLicenseConflictName() {
		return prLicenseName;
	}
	public void setPrLicenseConflictName(String licenseName) {
		this.prLicenseName.add(licenseName);
	}
	
	public ArrayList<String> getPrLicenseConflictReason() {
		return prConflictReason;
	}
	public void setPrLicenseConflictReason(String conflictReason) {
		this.prConflictReason.add(conflictReason);
	}	

}