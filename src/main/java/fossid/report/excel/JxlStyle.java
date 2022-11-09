package fossid.report.excel;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.BorderLineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class JxlStyle {
	Logger logger = LogManager.getLogger(JxlStyle.class);
	WritableFont sh0TitleFont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD);
	WritableFont sh0RegularFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);
	WritableFont sh0RegularBoldFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);	
	
	//sheet0
	WritableCellFormat sh0TitleFormat1 = new WritableCellFormat(sh0TitleFont);	
	WritableCellFormat sh0TitleFormat2 = new WritableCellFormat(sh0RegularBoldFont);
	WritableCellFormat sh0RegularFormat = new WritableCellFormat(sh0RegularFont);	
	WritableCellFormat sh0tableFormat = new WritableCellFormat(sh0RegularFont);
	
	//sheet1
	WritableCellFormat sh1TitleFormat = new WritableCellFormat(sh0TitleFont);
	WritableCellFormat sh1tableFormat1 = new WritableCellFormat(sh0RegularFont);
	WritableCellFormat sh1tableFormat2 = new WritableCellFormat(sh0RegularBoldFont);	
	WritableCellFormat sh1tableFormat3 = new WritableCellFormat(sh0RegularFont);
	WritableCellFormat sh1tableFormat4 = new WritableCellFormat(sh0RegularFont);
	WritableCellFormat sh1tableFormat5 = new WritableCellFormat(sh0RegularFont);
	WritableCellFormat sh1tableFormat6 = new WritableCellFormat(sh0RegularFont);
	
	//sheet2
	WritableCellFormat noConflict = new WritableCellFormat(sh0RegularFont);
	WritableCellFormat projectConflict = new WritableCellFormat(sh0RegularFont);
	WritableCellFormat componentConflict = new WritableCellFormat(sh0RegularFont);
	
	public void setStyle(){
		try {
			//sheet0
			sh0TitleFormat1.setAlignment(Alignment.CENTRE);
			sh0TitleFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
			sh0TitleFormat1.setBorder(Border.LEFT, BorderLineStyle.THIN);
			sh0TitleFormat1.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			sh0TitleFormat1.setBorder(Border.TOP, BorderLineStyle.THIN);
			sh0TitleFormat1.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			sh0TitleFormat1.setBackground(Colour.GRAY_25);	
	
			sh0RegularFormat.setAlignment(Alignment.LEFT);
			
			sh0TitleFormat2.setAlignment(Alignment.LEFT);
			sh0TitleFormat2.setVerticalAlignment(VerticalAlignment.BOTTOM);
			
			sh0tableFormat.setAlignment(Alignment.CENTRE);
			sh0tableFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			sh0tableFormat.setBorder(Border.LEFT, BorderLineStyle.THIN);
			sh0tableFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			sh0tableFormat.setBorder(Border.TOP, BorderLineStyle.THIN);
			sh0tableFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			sh0tableFormat.setWrap(true);
			
			//sheet1
			sh1TitleFormat.setAlignment(Alignment.LEFT);
			sh1TitleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			sh1tableFormat1.setAlignment(Alignment.CENTRE);
			sh1tableFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
			sh1tableFormat1.setBorder(Border.LEFT, BorderLineStyle.THIN);
			sh1tableFormat1.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			sh1tableFormat1.setBorder(Border.TOP, BorderLineStyle.THIN);
			sh1tableFormat1.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			sh1tableFormat1.setWrap(true);
			
			sh1tableFormat2.setAlignment(Alignment.CENTRE);
			sh1tableFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
			sh1tableFormat2.setBorder(Border.LEFT, BorderLineStyle.THIN);
			sh1tableFormat2.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			sh1tableFormat2.setBorder(Border.TOP, BorderLineStyle.THIN);
			sh1tableFormat2.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			sh1tableFormat2.setBackground(Colour.GRAY_25);
			sh1tableFormat2.setWrap(true);
			
			sh1tableFormat3.setAlignment(Alignment.CENTRE);
			sh1tableFormat3.setVerticalAlignment(VerticalAlignment.CENTRE);
			sh1tableFormat3.setBorder(Border.LEFT, BorderLineStyle.THIN);
			sh1tableFormat3.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			sh1tableFormat3.setBorder(Border.TOP, BorderLineStyle.THIN);
			sh1tableFormat3.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			sh1tableFormat3.setBackground(Colour.YELLOW);
			sh1tableFormat3.setWrap(true);			
			
			sh1tableFormat4.setAlignment(Alignment.LEFT);
			sh1tableFormat4.setVerticalAlignment(VerticalAlignment.CENTRE);			
			
			sh1tableFormat5.setAlignment(Alignment.LEFT);
			sh1tableFormat5.setVerticalAlignment(VerticalAlignment.CENTRE);
			sh1tableFormat5.setBorder(Border.LEFT, BorderLineStyle.THIN);
			sh1tableFormat5.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			sh1tableFormat5.setBorder(Border.TOP, BorderLineStyle.THIN);
			sh1tableFormat5.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			sh1tableFormat5.setWrap(true);
			
			sh1tableFormat6.setAlignment(Alignment.LEFT);
			sh1tableFormat6.setVerticalAlignment(VerticalAlignment.CENTRE);
			sh1tableFormat6.setBorder(Border.LEFT, BorderLineStyle.THIN);
			sh1tableFormat6.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			sh1tableFormat6.setBorder(Border.TOP, BorderLineStyle.THIN);
			sh1tableFormat6.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			sh1tableFormat6.setBackground(Colour.YELLOW);
			sh1tableFormat6.setWrap(true);		
			
			//sheet2
			noConflict.setAlignment(Alignment.CENTRE);
			noConflict.setVerticalAlignment(VerticalAlignment.CENTRE);
			noConflict.setBorder(Border.LEFT, BorderLineStyle.THIN);
			noConflict.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			noConflict.setBorder(Border.TOP, BorderLineStyle.THIN);
			noConflict.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			noConflict.setBackground(Colour.GREEN);
			noConflict.setWrap(true);
			
			projectConflict.setAlignment(Alignment.CENTRE);
			projectConflict.setVerticalAlignment(VerticalAlignment.CENTRE);
			projectConflict.setBorder(Border.LEFT, BorderLineStyle.THIN);
			projectConflict.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			projectConflict.setBorder(Border.TOP, BorderLineStyle.THIN);
			projectConflict.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			projectConflict.setBackground(Colour.RED);
			projectConflict.setWrap(true);
			
			componentConflict.setAlignment(Alignment.CENTRE);
			componentConflict.setVerticalAlignment(VerticalAlignment.CENTRE);
			componentConflict.setBorder(Border.LEFT, BorderLineStyle.THIN);
			componentConflict.setBorder(Border.RIGHT, BorderLineStyle.THIN);
			componentConflict.setBorder(Border.TOP, BorderLineStyle.THIN);
			componentConflict.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			componentConflict.setBackground(Colour.YELLOW);
			componentConflict.setWrap(true);
			
			
		} catch (WriteException e) {
			logger.error("Exception Message", e);
		}
	}
}

