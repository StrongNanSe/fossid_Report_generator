package fossid.report.values;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IdentifiedFilesValues implements Serializable {
	
	private static IdentifiedFilesValues values = new IdentifiedFilesValues();

	private IdentifiedFilesValues() {
	}

	public static IdentifiedFilesValues getInstance() {
		return values;
	}
	
	private int fileTotalCount;
	private int pendingFileCount;
	private int ignoredFileCount;
	private long fileTotalSize;
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

	public int getAnalyzedFileCount() {
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
	
	public int getPendingFileCount() {
		return pendingFileCount;
	}
	public void setPendingFileCount(int pendingFileCount) {
		this.pendingFileCount = pendingFileCount;
	}
	
	public int getPatentIssueFileCount() {
		return patentIssueFileCount;
	}
	public void setPatentIssueFileCount(int patentIssueFileCount) {
		this.patentIssueFileCount = patentIssueFileCount;
	}
	
	public int getProjectConflictFileCount() {
		return projectConflictFileCount;
	}
	public void setProjectConflictFileCount(int projectConflictFileCount) {
		this.projectConflictFileCount = projectConflictFileCount;
	}
	
	public int getFileTotalCount() {
		return fileTotalCount;
	}
	public void setGetFileTotalCount(int fileTotalCount) {
		this.fileTotalCount = fileTotalCount;
	}
	
	public long getFileTotalSize() {
		return fileTotalSize;
	}
	public void setFileTotalSize(long filesize) {
		this.fileTotalSize = fileTotalSize + filesize;
	}
	
	public int getIdentifiedFileCount() {
		return identifiedFileCount;
	}
	public void setIdentifiedFileCount(int count) {
		this.identifiedFileCount = identifiedFileCount + count;
	}
	
	public ArrayList<String> getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath.add(filepath);
	}
	
	public ArrayList<String> getIdentificationCopyright() {
		return identificationCopyright;
	}
	public void setIdentificationCopyright(String identificationCopyright) {
		this.identificationCopyright.add(identificationCopyright);
	}
	
	public ArrayList<String> getComponentName() {
		return componenetName;
	}
	public void setComponentName(String componentName) {
		this.componenetName.add(componentName);
	}

	public ArrayList<String> getComponentVersion() {
		return componentVersion;
	}
	public void setComponentVersion(String componentVersion) {
		this.componentVersion.add(componentVersion);
	}
	
	public ArrayList<String> getComponentCopyright() {
		return componentCopyright;
	}
	public void setComponentCopyright(String componentCopyright) {
		this.componentCopyright.add(componentCopyright);
	}
	
	public ArrayList<String> getComponentCPE() {
		return componentCPE;
	}
	public void setComponentCPE(String componentCPE) {
		this.componentCPE.add(componentCPE);
	}
	
	public ArrayList<String> getComponentLicenseName() {
		return componentLicenseName;
	}
	public void setComponentLicenseName(String componentLicenseName) {
		this.componentLicenseName.add(componentLicenseName);
	}
	
	public ArrayList<String> getComponentCopyleft() {
		return componentCopyleft;
	}
	public void setComponentCopyleft(String componentCopyleft) {
		this.componentCopyleft.add(componentCopyleft);
	}
	
	public ArrayList<String> getFileLicenseName() {
		return fileLicenseName;
	}
	public void setFileLicenseName(String fileLicenseName) {
		this.fileLicenseName.add(fileLicenseName);
	}
	
	public ArrayList<String> getFileLicenseMatchType() {
		return fileLicenseMatchType;
	}
	public void setFileLicenseMatchType(String fileLicenseMatchType) {
		this.fileLicenseMatchType.add(fileLicenseMatchType);
	}
	
	
	public ArrayList<String> getFileLicenseCopyleft() {
		return fileLicenseCopyleft;
	}
	public void setFileLicenseCopyleft(String fileLicenseCopyleft) {
		this.fileLicenseCopyleft.add(fileLicenseCopyleft);
	}	
	
	public ArrayList<String> getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment.add(comment);
	}
	
	public ArrayList<String> getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType.add(matchType);
	}
	
	public Map<String, String> getMatchTypeHashmap() {
		return matchTypeHashmap;
	}
	public void setMatchTypeHashmap(String key, String value) {
		this.matchTypeHashmap.put(key, value);
	}
}

