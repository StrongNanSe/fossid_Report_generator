package fossid.report.values;

import java.util.ArrayList;

// This class is set to compare license attirbutes
public class compareLicenseAttributeValues {
	
	private static compareLicenseAttributeValues values = new compareLicenseAttributeValues();

	private compareLicenseAttributeValues() {
	}

	public static compareLicenseAttributeValues getInstance() {
		return values;
	}
	
	private String coAttribute1;
	private String coAttribute2;
	private String coAttribute3;
	private String coAttribute4;
	private String coAttribute5;
	private String coAttribute6;
	private String coAttribute7;
	private String coAttribute8;
	private String coAttribute9;
	private String coAttribute10;
	private String coAttribute11;
	private String coAttribute12;
	private String coAttribute13;
	
	private ArrayList<String> outofLicense = new ArrayList<String>();
	
	public String getcoAttribute1() {
		return coAttribute1;
	}
	public void setcoAttribute1(String attribute) {
		this.coAttribute1 = attribute;
	}
	
	public String getcoAttribute2() {
		return coAttribute2;
	}
	public void setcoAttribute2(String attribute) {
		this.coAttribute2 = attribute;
	}
	
	public String getcoAttribute3() {
		return coAttribute3;
	}
	public void setcoAttribute3(String attribute) {
		this.coAttribute3 = attribute;
	}
	
	public String getcoAttribute4() {
		return coAttribute4;
	}
	public void setcoAttribute4(String attribute) {
		this.coAttribute4 = attribute;
	}
	
	public String getcoAttribute5() {
		return coAttribute5;
	}
	public void setcoAttribute5(String attribute) {
		this.coAttribute5 = attribute;
	}
	
	public String getcoAttribute6() {
		return coAttribute6;
	}
	public void setcoAttribute6(String attribute) {
		this.coAttribute6 = attribute;
	}
	
	public String getcoAttribute7() {
		return coAttribute7;
	}
	public void setcoAttribute7(String attribute) {
		this.coAttribute7 = attribute;
	}
	
	public String getcoAttribute8() {
		return coAttribute8;
	}
	public void setcoAttribute8(String attribute) {
		this.coAttribute8 = attribute;
	}
	
	public String getcoAttribute9() {
		return coAttribute9;
	}
	public void setcoAttribute9(String attribute) {
		this.coAttribute9 = attribute;
	}
	
	public String getcoAttribute10() {
		return coAttribute10;
	}
	public void setcoAttribute10(String attribute) {
		this.coAttribute10 = attribute;
	}
	
	public String getcoAttribute11() {
		return coAttribute11;
	}
	public void setcoAttribute11(String attribute) {
		this.coAttribute11 = attribute;
	}
	
	public String getcoAttribute12() {
		return coAttribute12;
	}
	public void setcoAttribute12(String attribute) {
		this.coAttribute12 = attribute;
	}
	
	public String getcoAttribute13() {
		return coAttribute13;
	}
	public void setcoAttribute13(String attribute) {
		this.coAttribute13 = attribute;
	}
	
	public ArrayList<String> getoutofLicense() {
		return outofLicense;
	}
	public void setoutofLicense(String outofLicense) {
		this.outofLicense.add(outofLicense);
	}	

}
