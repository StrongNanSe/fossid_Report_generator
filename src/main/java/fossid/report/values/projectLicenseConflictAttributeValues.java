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
	
	private ArrayList<String> prlicenseName = new ArrayList<String>();
	private ArrayList<String> prconflictReason = new ArrayList<String>();
	
	public ArrayList<String> getprLicenseConflictName() {
		return prlicenseName;
	}
	public void setprLicenseConflictName(String licenseName) {
		this.prlicenseName.add(licenseName);
	}
	
	public ArrayList<String> getprLicenseConflictReason() {
		return prconflictReason;
	}
	public void setprLicenseConflictReason(String conflictReason) {
		this.prconflictReason.add(conflictReason);
	}	

}