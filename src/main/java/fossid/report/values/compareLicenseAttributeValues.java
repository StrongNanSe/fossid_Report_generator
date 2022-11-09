package fossid.report.values;

import java.io.Serializable;
import java.util.ArrayList;

// This class is set to compare license attributes
public class CompareLicenseAttributeValues implements Serializable {
	
	private static final CompareLicenseAttributeValues values = new CompareLicenseAttributeValues();

	private CompareLicenseAttributeValues() {
	}

	public static CompareLicenseAttributeValues getInstance() {
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
	
	private final ArrayList<String> outOfLicense = new ArrayList<String>();
	
	public String getCoAttribute1() {
		return coAttribute1;
	}
	public void setCoAttribute1(String attribute) {
		this.coAttribute1 = attribute;
	}
	
	public String getCoAttribute2() {
		return coAttribute2;
	}
	public void setCoAttribute2(String attribute) {
		this.coAttribute2 = attribute;
	}
	
	public String getCoAttribute3() {
		return coAttribute3;
	}
	public void setCoAttribute3(String attribute) {
		this.coAttribute3 = attribute;
	}
	
	public String getCoAttribute4() {
		return coAttribute4;
	}
	public void setCoAttribute4(String attribute) {
		this.coAttribute4 = attribute;
	}
	
	public String getCoAttribute5() {
		return coAttribute5;
	}
	public void setCoAttribute5(String attribute) {
		this.coAttribute5 = attribute;
	}
	
	public String getCoAttribute6() {
		return coAttribute6;
	}
	public void setCoAttribute6(String attribute) {
		this.coAttribute6 = attribute;
	}
	
	public String getCoAttribute7() {
		return coAttribute7;
	}
	public void setCoAttribute7(String attribute) {
		this.coAttribute7 = attribute;
	}
	
	public String getCoAttribute8() {
		return coAttribute8;
	}
	public void setCoAttribute8(String attribute) {
		this.coAttribute8 = attribute;
	}
	
	public String getCoAttribute9() {
		return coAttribute9;
	}
	public void setCoAttribute9(String attribute) {
		this.coAttribute9 = attribute;
	}
	
	public String getCoAttribute10() {
		return coAttribute10;
	}
	public void setCoAttribute10(String attribute) {
		this.coAttribute10 = attribute;
	}
	
	public String getCoAttribute11() {
		return coAttribute11;
	}
	public void setCoAttribute11(String attribute) {
		this.coAttribute11 = attribute;
	}
	
	public String getCoAttribute12() {
		return coAttribute12;
	}
	public void setCoAttribute12(String attribute) {
		this.coAttribute12 = attribute;
	}
	
	public String getCoAttribute13() {
		return coAttribute13;
	}
	public void setCoAttribute13(String attribute) {
		this.coAttribute13 = attribute;
	}
	
	public ArrayList<String> getOutOfLicense() {
		return outOfLicense;
	}
	public void setOutOfLicense(String outOfLicense) {
		this.outOfLicense.add(outOfLicense);
	}	

}
