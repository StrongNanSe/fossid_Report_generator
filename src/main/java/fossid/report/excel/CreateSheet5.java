package fossid.report.excel;

import fossid.report.values.VulnerableComponents;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class CreateSheet5 extends CreateSheet {
	private final Logger logger = LogManager.getLogger(CreateSheet5.class);
	public CreateSheet5(){
		super();
	}
	
	VulnerableComponents vulnerableComponent = VulnerableComponents.getInstance();
	ExcelValues excelVal = ExcelValues.getInstance();
	WritableSheet sheet5 = excelVal.getSheet5();

	public void writeSheet() throws WriteException {
		logger.info("Creating sheet 6..");

		sheet5 = excelVal.getWB().createSheet("6. 취약점 정보", 5);
		
		for(int i=0; i < 7; i++) {
			sheet5.setColumnView(i, 28);				
		}
		sheet5.setColumnView(7, 33);
	
		sheet5.setRowView(0, 800);
		addLabel(sheet5, 0, 0, "6. 취약점 정보", style.sh1TitleFormat);
		
		sheet5.setRowView(1, 700);
		addLabel(sheet5, 0, 1, "컴포넌트", style.sh1tableFormat2);		
		addLabel(sheet5, 1, 1, "버전", style.sh1tableFormat2);
		addLabel(sheet5, 2, 1, "CVE", style.sh1tableFormat2);
		addLabel(sheet5, 3, 1, "심각도", style.sh1tableFormat2);
		addLabel(sheet5, 4, 1, "공격 벡터", style.sh1tableFormat2);
		addLabel(sheet5, 5, 1, "공격 복잡성", style.sh1tableFormat2);
		addLabel(sheet5, 6, 1, "공격 영향도", style.sh1tableFormat2);
		addLabel(sheet5, 7, 1, "링크", style.sh1tableFormat2);
		
		if(vulnerableComponent.getVulCVE().size() > 0) {
			
			int loop = 2, rowCount = 2;
			WritableHyperlink link = null;
			String componentVersion = vulnerableComponent.getVulComponentName().get(0) + vulnerableComponent.getVulComponentVersion().get(0);
			
			for(int i = 0; i < vulnerableComponent.getVulCVE().size(); i++) {
				sheet5.setRowView(loop, 500);
				
				addLabel(sheet5, 0, loop, vulnerableComponent.getVulComponentName().get(i), style.sh1tableFormat1);
				addLabel(sheet5, 1, loop, vulnerableComponent.getVulComponentVersion().get(i), style.sh1tableFormat1);
				addLabel(sheet5, 2, loop, vulnerableComponent.getVulCVE().get(i), style.sh1tableFormat1);
				addLabel(sheet5, 3, loop, vulnerableComponent.getVulSeverity().get(i), style.sh1tableFormat1);
				addLabel(sheet5, 4, loop, vulnerableComponent.getVulAttackVector().get(i), style.sh1tableFormat1);
				addLabel(sheet5, 5, loop, vulnerableComponent.getVulAttackComplexity().get(i), style.sh1tableFormat1);
				addLabel(sheet5, 6, loop, vulnerableComponent.getVulAttackImpact().get(i), style.sh1tableFormat1);
				addLabel(sheet5, 7, loop, "", style.sh1tableFormat1);
				
				try {				
					link = new WritableHyperlink(7, loop, new URL("https://nvd.nist.gov/vuln/detail/" + vulnerableComponent.getVulCVE().get(i)));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}			
				
			    Objects.requireNonNull(link).setDescription("NVD 링크");
			    sheet5.addHyperlink(link);		    
				
			   	// merge component and version cells if the component contains more than two CVEs	   
				if(!(componentVersion.equals(vulnerableComponent.getVulComponentName().get(i) + vulnerableComponent.getVulComponentVersion().get(i)))) {
					componentVersion = vulnerableComponent.getVulComponentName().get(i) + vulnerableComponent.getVulComponentVersion().get(i);
					sheet5.mergeCells(0, rowCount, 0, loop);
					sheet5.mergeCells(1, rowCount, 1, loop);
					rowCount = loop + 1;
				}			
				loop++;
			}
			
		   	// merge component and version cells if the component contains more than two CVEs
			if(loop - 2 == vulnerableComponent.getVulCVE().size()) {
				logger.debug(rowCount + " " +loop);
				//sheet5.mergeCells(0, rowCount, 0, loop - 1);
				//sheet5.mergeCells(1, rowCount, 1, loop - 1);			
			}
		}
	}
}
