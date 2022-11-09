package fossid.report.values;


import java.io.Serializable;

// This class is to set project license attributes
public class ProjectLicenseAttributeValues implements Serializable {
	
	private static final ProjectLicenseAttributeValues values = new ProjectLicenseAttributeValues();

	private ProjectLicenseAttributeValues() {
	}

	public static ProjectLicenseAttributeValues getInstance() {
		return values;
	}
	
	private String pjAttribute1;
	private String pjAttribute2;
	private String pjAttribute3;
	private String pjAttribute4;
	private String pjAttribute5;
	private String pjAttribute6;
	private String pjAttribute7;
	private String pjAttribute8;
	private String pjAttribute9;
	private String pjAttribute10;
	private String pjAttribute11;
	private String pjAttribute12;
	private String pjAttribute13;
	
	public String getPjAttribute1() {
		return pjAttribute1;
	}
	public void setPjAttribute1(String attribute) {
		this.pjAttribute1 = attribute;
	}
	
	public String getPjAttribute2() {
		return pjAttribute2;
	}
	public void setPjAttribute2(String attribute) {
		this.pjAttribute2 = attribute;
	}
	
	public String getPjAttribute3() {
		return pjAttribute3;
	}
	public void setPjAttribute3(String attribute) {
		this.pjAttribute3 = attribute;
	}
	
	public String getPjAttribute4() {
		return pjAttribute4;
	}
	public void setPjAttribute4(String attribute) {
		this.pjAttribute4 = attribute;
	}
	
	public String getpjAttribute5() {
		return pjAttribute5;
	}
	public void setPjAttribute5(String attribute) {
		this.pjAttribute5 = attribute;
	}
	
	public String getPjAttribute6() {
		return pjAttribute6;
	}
	public void setPjAttribute6(String attribute) {
		this.pjAttribute6 = attribute;
	}
	
	public String getPjAttribute7() {
		return pjAttribute7;
	}
	public void setPjAttribute7(String attribute) {
		this.pjAttribute7 = attribute;
	}
	
	public String getPjAttribute8() {
		return pjAttribute8;
	}
	public void setPjAttribute8(String attribute) {
		this.pjAttribute8 = attribute;
	}
	
	public String getPjAttribute9() {
		return pjAttribute9;
	}
	public void setPjAttribute9(String attribute) {
		this.pjAttribute9 = attribute;
	}
	
	public String getPjAttribute10() {
		return pjAttribute10;
	}
	public void setPjAttribute10(String attribute) {
		this.pjAttribute10 = attribute;
	}
	
	public String getPjAttribute11() {
		return pjAttribute11;
	}
	public void setPjAttribute11(String attribute) {
		this.pjAttribute11 = attribute;
	}
	
	public String getPjAttribute12() {
		return pjAttribute12;
	}
	public void setPjAttribute12(String attribute) {
		this.pjAttribute12 = attribute;
	}
	
	public String getPjAttribute13() {
		return pjAttribute13;
	}
	public void setPjAttribute13(String attribute) {
		this.pjAttribute13 = attribute;
	}
}
