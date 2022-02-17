package fossid.report.values;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class identifiedFilesValues {
	
	private static identifiedFilesValues values = new identifiedFilesValues();

	private identifiedFilesValues() {
	}

	public static identifiedFilesValues getInstance() {
		return values;
	}
	
	private int fileTotalCount;
	private int pendingFileCount;
	private int ignoredFileCount;
	private int fileTotalSize;
	private int identifiedFileCount;
	private int projectConflictFileCount;
	private int patentIssueFileCount;
	private int analyzedFileCount;
	
	private ArrayList<String> filepath = new ArrayList<String>();
	private ArrayList<String> identificationCopyright = new ArrayList<String>();
	
	private ArrayList<String> componenetName = new ArrayList<String>();
	private ArrayList<String> componentVersion = new ArrayList<String>();
	private ArrayList<String> componentCopyright = new ArrayList<String>();
	private ArrayList<String> componentCPE = new ArrayList<String>();
	private ArrayList<String> componentLicenseName = new ArrayList<String>();
	private ArrayList<String> componentCopyleft = new ArrayList<String>();
	
	private ArrayList<String> fileLicenseName = new ArrayList<String>();
	private ArrayList<String> fileLicenseCopyleft = new ArrayList<String>();
	private ArrayList<String> fileLicenseMatchType = new ArrayList<String>();
	
	private ArrayList<String> comment = new ArrayList<String>();
	
	private ArrayList<String> matchType = new ArrayList<String>();
	private Map<String, String> matchTypeHashmap = new HashMap<>();

	public int getAnalzyedFileCount() {
		return analyzedFileCount;
	}
	public void setAnalyzedFileCount(int analyzedFileCount) {
		this.analyzedFileCount = analyzedFileCount;
	}
	
	public int getIgnoredCount() {
		return ignoredFileCount;
	}
	public void setIgnoredFileCount(int ignoredFileCount) {
		this.ignoredFileCount = ignoredFileCount;
	}
	
	public int getpendingFileCount() {
		return pendingFileCount;
	}
	public void setpendingFileCount(int pendingFileCount) {
		this.pendingFileCount = pendingFileCount;
	}
	
	public int getpatentIssueFileCount() {
		return patentIssueFileCount;
	}
	public void setpatentIssueFileCount(int patentIssueFileCount) {
		this.patentIssueFileCount = patentIssueFileCount;
	}
	
	public int getprojectConflictFileCount() {
		return projectConflictFileCount;
	}
	public void setprojectConflictFileCount(int projectConflictFileCount) {
		this.projectConflictFileCount = projectConflictFileCount;
	}
	
	public int getfileTotalCount() {
		return fileTotalCount;
	}
	public void setgetfileTotalCount(int fileTotalCount) {
		this.fileTotalCount = fileTotalCount;
	}
	
	public int getfileTotalSize() {
		return fileTotalSize;
	}
	public void setfileTotalSize(int filesize) {
		this.fileTotalSize = fileTotalSize + filesize;
	}
	
	public int getIdentifiedFileCount() {
		return identifiedFileCount;
	}
	public void setIdentifiedFileCount(int count) {
		this.identifiedFileCount = identifiedFileCount + count;
	}
	
	public ArrayList<String> getfilepath() {
		return filepath;
	}
	public void setfilepath(String filepath) {
		this.filepath.add(filepath);
	}
	
	public ArrayList<String> getidentificationCopyright() {
		return identificationCopyright;
	}
	public void setidentificationCopyright(String identificationCopyright) {
		this.identificationCopyright.add(identificationCopyright);
	}
	
	public ArrayList<String> getcomponenetName() {
		return componenetName;
	}
	public void setcomponenetName(String componenetName) {
		this.componenetName.add(componenetName);
	}

	public ArrayList<String> getcomponentVersion() {
		return componentVersion;
	}
	public void setcomponentVersion(String componentVersion) {
		this.componentVersion.add(componentVersion);
	}
	
	public ArrayList<String> getcomponentCopyright() {
		return componentCopyright;
	}
	public void setcomponentCopyright(String componentCopyright) {
		this.componentCopyright.add(componentCopyright);
	}
	
	public ArrayList<String> getcomponentCPE() {
		return componentCPE;
	}
	public void setcomponentCPE(String componentCPE) {
		this.componentCPE.add(componentCPE);
	}
	
	public ArrayList<String> getcomponentLicenseName() {
		return componentLicenseName;
	}
	public void setcomponentLicenseName(String componentLicenseName) {
		this.componentLicenseName.add(componentLicenseName);
	}
	
	public ArrayList<String> getcomponentCopyleft() {
		return componentCopyleft;
	}
	public void setcomponentCopyleft(String componentCopyleft) {
		this.componentCopyleft.add(componentCopyleft);
	}
	
	public ArrayList<String> getfileLicenseName() {
		return fileLicenseName;
	}
	public void setfileLicenseName(String fileLicenseName) {
		this.fileLicenseName.add(fileLicenseName);
	}
	
	public ArrayList<String> getfileLicenseMatchType() {
		return fileLicenseMatchType;
	}
	public void setfileLicenseMatchType(String fileLicenseMatchType) {
		this.fileLicenseMatchType.add(fileLicenseMatchType);
	}
	
	
	public ArrayList<String> getfileLicenseCopyleft() {
		return fileLicenseCopyleft;
	}
	public void setfileLicenseCopyleft(String fileLicenseCopyleft) {
		this.fileLicenseCopyleft.add(fileLicenseCopyleft);
	}	
	
	public ArrayList<String> getcommnet() {
		return comment;
	}
	public void setcommnet(String comment) {
		this.comment.add(comment);
	}
	
	public ArrayList<String> getmatchType() {
		return matchType;
	}
	public void setmatchType(String matchType) {
		this.matchType.add(matchType);
	}
	
	public Map<String, String> getmatchTypeHashmap() {
		return matchTypeHashmap;
	}
	public void setmatchTypeHashmap(String key, String value) {
		this.matchTypeHashmap.put(key, value);
	}
}

