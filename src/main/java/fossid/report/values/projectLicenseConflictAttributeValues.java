package fossid.report.values;

import java.util.ArrayList;

public class projectLicenseConflictAttributeValues {
	
	private static projectLicenseConflictAttributeValues values = new projectLicenseConflictAttributeValues();

	private projectLicenseConflictAttributeValues() {
	}

	public static projectLicenseConflictAttributeValues getInstance() {
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