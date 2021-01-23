package sweyp.constants;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.io.IOException;

public class GC {

	/****** General ******/

	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public static final int WINDOW_BAR_HEIGHT = 22;

	/****** Home Screen ******/

	public static final Color COLOR_HOME_BACKGROUND = new Color(170, 170, 190);
	
	public static final Color COLOR_HOME_TITLE_TEXT = new Color(40, 40, 50);

	public static final Color COLOR_HOME_TITLE_UNDERLINE_1 = new Color(40, 40, 50);
	public static final Color COLOR_HOME_TITLE_UNDERLINE_2 = new Color(170, 170, 190);
	public static final GradientPaint GRADIENT_HOME_TITLE_UNDERLINE = new GradientPaint(
			(int)(SCREEN_WIDTH * .20), 0, COLOR_HOME_TITLE_UNDERLINE_1,
			(int)(SCREEN_WIDTH * .80), 0, COLOR_HOME_TITLE_UNDERLINE_2);
	
	/****** Game Screen ******/

	public static final Color COLOR_GAME_BACKGROUND = new Color(40, 40, 50);

	public static final Color COLOR_GAME_SIDE_BAR_1 = new Color(190, 190, 210);
	public static final Color COLOR_GAME_SIDE_BAR_2 = new Color(80, 80, 90);
	public static final GradientPaint GRADIENT_GAME_SIDE_BAR = new GradientPaint(
			0, 0, COLOR_GAME_SIDE_BAR_1, 0, SCREEN_HEIGHT, COLOR_GAME_SIDE_BAR_2);

	public static final Color COLOR_GAME_TITLE_TEXT = new Color(40, 40, 50);
	public static final Color COLOR_GAME_TIME_TEXT = new Color(40, 40, 50);
	public static final Color COLOR_GAME_SCORE_TEXT = new Color(40, 40, 50);
	public static final Color COLOR_GAME_RESTART_TEXT = new Color(50, 50, 60);
	public static final Color COLOR_GAME_RESTART_TEXT_P = new Color(20, 20, 30);
	
	public static final Color COLOR_GAME_LIST_TEXT = new Color(200, 200, 220);
	public static final Color COLOR_GAME_LIST_BACKGROUND = new Color(0.05f, 0.05f, 0.1f, 0.2f);
	public static final int LIST_CURVATURE = 20;
	
	public static final Color COLOR_GAME_TITLE_UNDERLINE_1 = new Color(40, 40, 50);
	public static final Color COLOR_GAME_TITLE_UNDERLINE_2 = new Color(190, 190, 210);
	public static final GradientPaint GRADIENT_GAME_TITLE_UNDERLINE = new GradientPaint(
			0, 0, COLOR_GAME_TITLE_UNDERLINE_1, SCREEN_WIDTH - SCREEN_HEIGHT, 0, COLOR_GAME_TITLE_UNDERLINE_2);
	
	public static final Color COLOR_GAME_TILE_BORDER_1 = new Color(140, 140, 150);
	public static final Color COLOR_GAME_TILE_BORDER_2 = new Color(80, 80, 100);
	public static final Color COLOR_GAME_TILE_BORDER_H1 = new Color(255, 200, 50);
	public static final Color COLOR_GAME_TILE_BORDER_H2 = new Color(170, 140, 30);
	public static final Color COLOR_GAME_TILE_FACE_1 = new Color(100, 100, 120);
	public static final Color COLOR_GAME_TILE_FACE_2 = new Color(170, 170, 180);
	
	public static final Color COLOR_GAME_TILE_LETTER = new Color(40, 40, 50);
	
	public static final int TILE_CURVATURE = 20;
	public static final int TILE_SPACING = 30;
	public static final int TILE_BORDER_WIDTH = 8;
	public static final int TILE_GRADIENT_SHIFT = 10;
	
	public static final Color COLOR_GAME_PLUS_TEXT = new Color(220, 220, 220);
	public static final Color COLOR_GAME_PLUS_BORDER = new Color(50, 50, 60);
	public static final double PLUS_FLOAT_SPEED = 1.5;
	public static final int PLUS_MAX_DISTANCE_X = 30;
	public static final int PLUS_MAX_DISTANCE_Y = 40;

	/****** Results Screen ******/
	
	
	
	/****** Fonts ******/
	
	public static final String FONT_FILE_BOLD = "/CamptonBold.otf";
	public static Font FONT_BOLD;
	public static final String FONT_FILE_THIN = "/CamptonLight.otf";
	public static Font FONT_THIN;

	static {
		try {
			FONT_BOLD = Font.createFont(Font.TRUETYPE_FONT, 
					GC.class.getResourceAsStream(FONT_FILE_BOLD));
			FONT_THIN = Font.createFont(Font.TRUETYPE_FONT, 
					GC.class.getResourceAsStream(FONT_FILE_THIN));
			
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
