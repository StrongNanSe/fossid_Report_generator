package fossid.report.values;

import java.io.Serializable;

public class ProjectValues implements Serializable {
	
		private static final ProjectValues values = new ProjectValues();

		public ProjectValues() {
		}

		public static ProjectValues getInstance() {
			return values;
		}

		private static String projectId;
		private static String projectName;
		private static String versionId;
		private static String versionName;
		private static String projectLicense;	
		
		public String getProjectId() {
			return projectId;
		}

		public void setProjectId(String projectId) {
			ProjectValues.projectId = projectId;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			ProjectValues.projectName = projectName;
		}
		
		public String getVersionId() {
			return versionId;
		}

		public void setVersionId(String versionId) {
			ProjectValues.versionId = versionId;
		}

		public void setVersionName(String versionName) {
			ProjectValues.versionName = versionName;
		}
		
		public String getVersionName() {
			return versionName;
		}
		
		public String getProjectLicense() {
			return projectLicense;
		}

		public void setProjectLicense(String license) {
			projectLicense = license;
		}
		


}
