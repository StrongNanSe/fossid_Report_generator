package fossid.report.values;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VulnerableComponents implements Serializable {
	
	private static VulnerableComponents values = new VulnerableComponents();

	public VulnerableComponents() {
	}

	public static VulnerableComponents getInstance() {
		return values;
	}

	private static ArrayList<String> componentName = new ArrayList<String>();	
	private static ArrayList<String> componentVersion = new ArrayList<String>();
	private static ArrayList<String> componentCPE = new ArrayList<String>();
	
	private static ArrayList<String> vulcomponentName = new ArrayList<String>();
	private static ArrayList<String> vulcomponentVersion = new ArrayList<String>();
	private static ArrayList<String> vulCVE = new ArrayList<String>();
	private static ArrayList<String> vulCVSS = new ArrayList<String>();
	private static ArrayList<String> vulSeverity = new ArrayList<String>();
	private static ArrayList<String> vulAttackVector = new ArrayList<String>();
	private static ArrayList<String> vulAttackComplexity = new ArrayList<String>();
	private static ArrayList<String> vulAttackImpact = new ArrayList<String>();
	
	private Map<String, String> cpeHashmap = new HashMap<>();
	
	public ArrayList<String> getcomponentName() {
		return componentName;
	}
	public void setcomponentName(String componentName) {
		this.componentName.add(componentName);
	}
	
	public ArrayList<String> getcomponentVersion() {
		return componentVersion;
	}
	public void setcomponentVersion(String componentVersion) {
		this.componentVersion.add(componentVersion);
	}
	
	public ArrayList<String> getcomponentCPE() {
		return componentCPE;
	}
	public void setcomponentCPE(String componentCPE) {
		this.componentCPE.add(componentCPE);
	}
	
	public Map<String, String> getcpeHashmap() {
		return cpeHashmap;
	}
	public void setcpeHashmap(String key, String value) {
		this.cpeHashmap.put(key, value);
	}
	
	public ArrayList<String> getvulcomponentName() {
		return vulcomponentName;
	}
	public void setvulcomponentName(String vulcomponentName) {
		this.vulcomponentName.add(vulcomponentName);
	}
	
	public ArrayList<String> getvulcomponentVersion() {
		return vulcomponentVersion;
	}	
	public void setvulcomponentVersion(String vulcomponentVersion) {
		this.vulcomponentVersion.add(vulcomponentVersion);
	}
	
	public ArrayList<String> getvulCVE() {
		return vulCVE;
	}
	public void setvulCVE(String vulCVE) {
		this.vulCVE.add(vulCVE);
	}
	
	public ArrayList<String> getvulCVSS() {
		return vulCVSS;
	}
	public void setvulCVSS(String vulCVSS) {
		this.vulCVSS.add(vulCVSS);
	}
	
	public ArrayList<String> getvulSeverity() {
		return vulSeverity;
	}
	public void setvulSeverity(String vulSeverity) {
		this.vulSeverity.add(vulSeverity);
	}
	
	public ArrayList<String> getvulAttackVector() {
		return vulAttackVector;
	}
	public void setvulAttackVector(String vulAttackVector) {
		this.vulAttackVector.add(vulAttackVector);
	}
	
	public ArrayList<String> getvulAttackComplexity() {
		return vulAttackComplexity;
	}
	public void setvulAttackComplexity(String vulAttackComplexity) {
		this.vulAttackComplexity.add(vulAttackComplexity);
	}
	
	public ArrayList<String> getvulAttackImpact() {
		return vulAttackImpact;
	}
	public void setvulAttackImpact(String vulAttackImpact) {
		this.vulAttackImpact.add(vulAttackImpact);
	}
	
}
