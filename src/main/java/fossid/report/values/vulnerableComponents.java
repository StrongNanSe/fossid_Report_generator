package fossid.report.values;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VulnerableComponents implements Serializable {
	
	private static final VulnerableComponents values = new VulnerableComponents();

	public VulnerableComponents() {
	}

	public static VulnerableComponents getInstance() {
		return values;
	}

	private static final ArrayList<String> componentName = new ArrayList<String>();
	private static final ArrayList<String> componentVersion = new ArrayList<String>();
	private static final ArrayList<String> componentCPE = new ArrayList<String>();
	
	private static final ArrayList<String> vulComponentName = new ArrayList<String>();
	private static final ArrayList<String> vulComponentVersion = new ArrayList<String>();
	private static final ArrayList<String> vulCVE = new ArrayList<String>();
	private static final ArrayList<String> vulCVSS = new ArrayList<String>();
	private static final ArrayList<String> vulSeverity = new ArrayList<String>();
	private static final ArrayList<String> vulAttackVector = new ArrayList<String>();
	private static final ArrayList<String> vulAttackComplexity = new ArrayList<String>();
	private static final ArrayList<String> vulAttackImpact = new ArrayList<String>();
	
	private final Map<String, String> cpeHashmap = new HashMap<>();
	
	public ArrayList<String> getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		VulnerableComponents.componentName.add(componentName);
	}
	
	public ArrayList<String> getComponentVersion() {
		return componentVersion;
	}
	public void setComponentVersion(String componentVersion) {
		VulnerableComponents.componentVersion.add(componentVersion);
	}
	
	public ArrayList<String> getComponentCPE() {
		return componentCPE;
	}
	public void setComponentCPE(String componentCPE) {
		VulnerableComponents.componentCPE.add(componentCPE);
	}
	
	public Map<String, String> getCpeHashmap() {
		return cpeHashmap;
	}
	public void setCpeHashmap(String key, String value) {
		this.cpeHashmap.put(key, value);
	}
	
	public ArrayList<String> getVulComponentName() {
		return vulComponentName;
	}
	public void setVulComponentName(String vulComponentName) {
		VulnerableComponents.vulComponentName.add(vulComponentName);
	}
	
	public ArrayList<String> getVulComponentVersion() {
		return vulComponentVersion;
	}	
	public void setVulComponentVersion(String vulComponentVersion) {
		VulnerableComponents.vulComponentVersion.add(vulComponentVersion);
	}
	
	public ArrayList<String> getVulCVE() {
		return vulCVE;
	}
	public void setVulCVE(String vulCVE) {
		VulnerableComponents.vulCVE.add(vulCVE);
	}
	
	public ArrayList<String> getVulCVSS() {
		return vulCVSS;
	}
	public void setVulCVSS(String vulCVSS) {
		VulnerableComponents.vulCVSS.add(vulCVSS);
	}
	
	public ArrayList<String> getVulSeverity() {
		return vulSeverity;
	}
	public void setVulSeverity(String vulSeverity) {
		VulnerableComponents.vulSeverity.add(vulSeverity);
	}
	
	public ArrayList<String> getVulAttackVector() {
		return vulAttackVector;
	}
	public void setVulAttackVector(String vulAttackVector) {
		VulnerableComponents.vulAttackVector.add(vulAttackVector);
	}
	
	public ArrayList<String> getVulAttackComplexity() {
		return vulAttackComplexity;
	}
	public void setVulAttackComplexity(String vulAttackComplexity) {
		VulnerableComponents.vulAttackComplexity.add(vulAttackComplexity);
	}
	
	public ArrayList<String> getVulAttackImpact() {
		return vulAttackImpact;
	}
	public void setVulAttackImpact(String vulAttackImpact) {
		VulnerableComponents.vulAttackImpact.add(vulAttackImpact);
	}
	
}
