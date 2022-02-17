package fossid.report.values;

public class projectValues {	
	
		private static projectValues values = new projectValues();

		public projectValues() {
		}

		public static projectValues getInstance() {
			return values;
		}

		private static String projectid;	
		private static String projectname;
		private static String versionid;
		private static String versionname;
		private static String projectLicense;	
		
		public String getProjectId() {
			return projectid;
		}

		public void setProjectId(String projectid) {
			this.projectid = projectid;
		}

		public String getProjectName() {
			return projectname;
		}

		public void setProjectName(String projectname) {
			this.projectname = projectname;
		}
		
		public String getVersionId() {
			return versionid;
		}

		public void setVersionId(String versionid) {
			this.versionid = versionid;
		}

		public void setVersionName(String versionname) {
			this.versionname = versionname;
		}
		
		public String getVersionName() {
			return versionname;
		}
		
		public String getProjectLicense() {
			return projectLicense;
		}

		public void setProjectLicense(String license) {
			this.projectLicense = license;
		}
		


}
