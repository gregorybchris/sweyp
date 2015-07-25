package graphics;

import java.awt.Font;
import java.awt.Graphics2D;

public class FontMetrics {
	public static Font transformFontByWidth(Font font, Graphics2D g2, String text, double width) {
		return font.deriveFont((float)(font.getSize2D() * 
				width / g2.getFontMetrics(font).getStringBounds(text, g2).getWidth()));
	}
	
	public static Font transformFontByHeight(Font font, Graphics2D g2, String text, double height) {
		return font.deriveFont((float)(font.getSize2D() * 
				height / g2.getFontMetrics(font).getStringBounds(text, g2).getHeight()));
	}
	
	public static double getTextWidth(Font font, Graphics2D g2, String text) {
		return g2.getFontMetrics(font).getStringBounds(text, g2).getWidth();
	}
	
	public static double getTextHeight(Font font, Graphics2D g2, String text) {
		return g2.getFontMetrics(font).getStringBounds(text, g2).getHeight();
	}
}
