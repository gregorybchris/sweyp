package graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

import constants.GC;
import constants.UC;
import data.Config;
import data.IOData;
import data.Point;

public class GraphicsPanel extends JPanel 
implements KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1191344637L;

	private Config config;
	private IOData iodata;

	public GraphicsPanel(Config config, IOData iodata) {
		super();
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setFocusable(true);

		this.config = config;
		this.iodata = iodata;
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		if(config.screenMode == UC.SCREEN_HOME)
			paintHomeScreen(g2);
		else if(config.screenMode == UC.SCREEN_GAME)
			paintGameScreen(g2);
		else if(config.screenMode == UC.SCREEN_RESULTS)
			paintResultsScreen(g2);
	}

	private void paintHomeScreen(Graphics2D g2) {
		g2.setColor(GC.COLOR_HOME_BACKGROUND);
		g2.fillRect(0, 0, GC.SCREEN_WIDTH, GC.SCREEN_HEIGHT);

		String title = UC.GAME_NAME;
		g2.setColor(GC.COLOR_HOME_TITLE_TEXT);
		double titleWidth = GC.SCREEN_WIDTH * 0.60;
		g2.setFont(FontMetrics.transformFontByWidth(GC.FONT_BOLD, g2, title, titleWidth));
		double titleHeight = FontMetrics.getTextHeight(g2.getFont(), g2, title);
		double titleSpacing = (GC.SCREEN_WIDTH - titleWidth) / 2;
		g2.drawString(title, (int)titleSpacing, (int)titleHeight);

		g2.setPaint(GC.GRADIENT_HOME_TITLE_UNDERLINE);
		g2.setStroke(new BasicStroke(12));
		g2.drawLine((int)titleSpacing, (int)titleHeight, (int)(GC.SCREEN_WIDTH - titleSpacing), (int)titleHeight);

		//TODO: Add graphics to home screen
	}

	private void paintGameScreen(Graphics2D g2) {
		g2.setColor(GC.COLOR_GAME_BACKGROUND);
		g2.fillRect(0, 0, GC.SCREEN_WIDTH, GC.SCREEN_HEIGHT);

		paintGameSidebar(g2);
		paintGameTiles(g2);
	}

	private void paintGameTiles(Graphics2D g2) {
		double sideBarWidth = GC.SCREEN_WIDTH - GC.SCREEN_HEIGHT;
		double gameWidth = GC.SCREEN_WIDTH - sideBarWidth;
		int boardWidth = config.board.getWidth();
		int boardHeight = config.board.getHeight();
		double tileSpaceWidth = (gameWidth - GC.TILE_SPACING) / boardWidth;
		double tileSpaceHeight = (gameWidth - GC.TILE_SPACING) / boardHeight;
		for(int y = 0; y < config.board.getHeight(); y++) {
			for(int x = 0; x < config.board.getWidth(); x++) {
				Point tilePoint = config.tilePoints[x][y];
				double tileX = tilePoint.x - tileSpaceWidth / 2 + GC.TILE_SPACING;
				double tileY = tilePoint.y - tileSpaceHeight / 2 + GC.TILE_SPACING;
				double tileWidth = tileSpaceWidth - GC.TILE_SPACING;
				double tileHeight = tileSpaceHeight - GC.TILE_SPACING;

				RoundRectangle2D tileBorder = new RoundRectangle2D.Double(
						tileX, tileY, tileWidth, tileHeight,
						GC.TILE_CURVATURE, GC.TILE_CURVATURE);
				GradientPaint GRADIENT_GAME_TILE_BORDER = new GradientPaint(
						(int)(tileX + GC.TILE_GRADIENT_SHIFT), 
						(int)(tileY - tileSpaceWidth / 2), GC.COLOR_GAME_TILE_BORDER_1,
						(int)(tileX - GC.TILE_GRADIENT_SHIFT), 
						(int)(tileY + tileSpaceWidth / 2), GC.COLOR_GAME_TILE_BORDER_2);
				GradientPaint GRADIENT_GAME_TILE_BORDER_H = new GradientPaint(
						(int)(tileX + GC.TILE_GRADIENT_SHIFT), 
						(int)(tileY - tileSpaceWidth / 2), GC.COLOR_GAME_TILE_BORDER_H1,
						(int)(tileX - GC.TILE_GRADIENT_SHIFT), 
						(int)(tileY + tileSpaceWidth / 2), GC.COLOR_GAME_TILE_BORDER_H2);

				double tileFaceX = tileX + GC.TILE_BORDER_WIDTH;
				double tileFaceY = tileY + GC.TILE_BORDER_WIDTH;
				double tileFaceWidth = tileWidth - GC.TILE_BORDER_WIDTH * 2;
				double tileFaceHeight = tileHeight - GC.TILE_BORDER_WIDTH * 2;
				RoundRectangle2D tileFace = new RoundRectangle2D.Double(
						tileFaceX, tileFaceY, tileFaceWidth, tileFaceHeight, 
						GC.TILE_CURVATURE, GC.TILE_CURVATURE);
				GradientPaint GRADIENT_GAME_TILE_FACE = new GradientPaint(
						(int)(tileX + GC.TILE_GRADIENT_SHIFT), 
						(int)(tileY - tileSpaceWidth / 2), GC.COLOR_GAME_TILE_FACE_1,
						(int)(tileX - GC.TILE_GRADIENT_SHIFT), 
						(int)(tileY + tileSpaceWidth / 2), GC.COLOR_GAME_TILE_FACE_2);

				g2.setPaint(GRADIENT_GAME_TILE_BORDER);
				if(config.tileList.contains(config.tileLocations[x][y]))
					g2.setPaint(GRADIENT_GAME_TILE_BORDER_H);
				g2.fill(tileBorder);

				g2.setPaint(GRADIENT_GAME_TILE_FACE);
				g2.fill(tileFace);

				String tileLetter = config.board.get(x, y) + "";
				double letterWidth = tileFaceWidth * 0.85;
				g2.setFont(FontMetrics.transformFontByHeight(GC.FONT_BOLD, g2, tileLetter, (int)letterWidth));
				double letterHeight = FontMetrics.getTextHeight(g2.getFont(), g2, tileLetter);
				//TODO: Fix where the letters are on the tiles
				g2.setColor(Color.WHITE);
				g2.setStroke(new BasicStroke(1));
				/*
				g2.drawRect(
						(int)(tileX + tileWidth / 2 - letterWidth / 2),
						(int)(tileY + tileHeight / 2 + letterHeight / 2),
						(int)letterWidth, (int)letterHeight);
				 */
				g2.setColor(GC.COLOR_GAME_TILE_LETTER);
				g2.drawString(tileLetter,
						(int)(tileX + tileWidth / 2 - letterWidth / 2 + letterHeight / 6),
						(int)(tileY + tileHeight / 2 + letterHeight / 2 - letterHeight / 6));
			}
		}
	}

	private void paintGameSidebar(Graphics2D g2) {
		double sidebarWidth = GC.SCREEN_WIDTH - GC.SCREEN_HEIGHT;

		g2.setPaint(GC.GRADIENT_GAME_SIDE_BAR);
		g2.fillRect(0, 0, (int)sidebarWidth, GC.SCREEN_HEIGHT);

		String title = UC.GAME_NAME;
		g2.setColor(GC.COLOR_GAME_TITLE_TEXT);
		double titleWidth = sidebarWidth * 0.85;
		g2.setFont(FontMetrics.transformFontByWidth(GC.FONT_BOLD, g2, title, titleWidth));
		double titleHeight = FontMetrics.getTextHeight(g2.getFont(), g2, title);
		g2.drawString(title, (int)((sidebarWidth - titleWidth) / 2), (int)titleHeight);

		g2.setPaint(GC.GRADIENT_GAME_TITLE_UNDERLINE);
		g2.setStroke(new BasicStroke(6));
		g2.drawLine(15, (int)titleHeight, (int)(sidebarWidth - 15), (int)titleHeight);

		String time = "Time: " + config.time;
		String timePlaceholder = "Time: 00";
		g2.setColor(GC.COLOR_GAME_TIME_TEXT);
		double timeWidth = sidebarWidth * 0.70;
		g2.setFont(FontMetrics.transformFontByWidth(GC.FONT_THIN, g2, timePlaceholder, timeWidth));
		double timeHeight = FontMetrics.getTextHeight(g2.getFont(), g2, timePlaceholder);
		g2.drawString(time, (int)((sidebarWidth - timeWidth) / 2), 
				(int)(titleHeight + timeHeight * 2));

		String score = "Score: " + config.score;
		String scorePlaceholder = "Score: ";
		for(int i = 0; i < (config.score + "").length(); i++)
			scorePlaceholder += "0";
		g2.setColor(GC.COLOR_GAME_SCORE_TEXT);
		double scoreWidth = sidebarWidth * 0.70;
		g2.setFont(FontMetrics.transformFontByWidth(GC.FONT_THIN, g2, scorePlaceholder, scoreWidth));
		double scoreHeight = FontMetrics.getTextHeight(g2.getFont(), g2, scorePlaceholder);
		g2.drawString(score, (int)((sidebarWidth - scoreWidth) / 2), 
				(int)(titleHeight + timeHeight + scoreHeight * 2.5));

		if(!config.plusFinalPoint.equals(config.plusDefaultPoint)) {
			float plusTravelRatio = (float)(config.plusPoint.distance(config.plusDefaultPoint) / 
					config.plusFinalPoint.distance(config.plusDefaultPoint));
			if(plusTravelRatio > 1)
				plusTravelRatio = 1;
			plusTravelRatio = 1 - plusTravelRatio;
			g2.setComposite(AlphaComposite.SrcOver.derive(plusTravelRatio));
			g2.setColor(GC.COLOR_GAME_PLUS_BORDER);
			int plusX = (int)config.plusPoint.x;
			int plusY = (int)config.plusPoint.y;
			for(int y = plusY - 1; y <= plusY + 1; y++)
				for(int x = plusX - 1; x <= plusX + 1; x++)
					g2.drawString("" + config.plusValue, x, y);
			g2.setColor(GC.COLOR_GAME_PLUS_TEXT);
			g2.drawString("" + config.plusValue, plusX, plusY);
			g2.setComposite(AlphaComposite.SrcOver.derive(1.0f));
		}

		String restart = "R to Restart";
		double restartWidth = sidebarWidth * 0.50;
		g2.setFont(FontMetrics.transformFontByWidth(GC.FONT_THIN, g2, restart, restartWidth));
		double restartHeight = FontMetrics.getTextHeight(g2.getFont(), g2, restart);
		int restartX = (int)((sidebarWidth - restartWidth) / 2);
		int restartY = (int)(GC.SCREEN_HEIGHT - restartHeight / 2);
		g2.setColor(GC.COLOR_GAME_RESTART_TEXT);
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		if(iodata.getMouseX() > restartX && iodata.getMouseX() < restartX + restartWidth && 
				iodata.getMouseY() > restartY - restartHeight && iodata.getMouseY() < restartY) {
			//TODO: Add this back in when restart works
			//g2.setColor(GC.COLOR_GAME_RESTART_TEXT_P);
			//setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		g2.drawString(restart, restartX, restartY);

		double listWidth = sidebarWidth * 0.80;
		double listHeight = GC.SCREEN_HEIGHT * 0.35;
		double listX = (sidebarWidth - listWidth) / 2;
		double listY = GC.SCREEN_HEIGHT - restartX - listHeight;
		g2.setColor(GC.COLOR_GAME_LIST_BACKGROUND);
		g2.fillRoundRect((int)listX, (int)listY, (int)listWidth, (int)listHeight,
				GC.LIST_CURVATURE, GC.LIST_CURVATURE);

		g2.setColor(GC.COLOR_GAME_LIST_TEXT);
		double listLineHeight = listHeight / (UC.LIST_WORDS_TO_SHOW + 1);
		g2.setFont(FontMetrics.transformFontByHeight(GC.FONT_BOLD, g2, restart, listLineHeight));
		int totalListWords = config.correctWords.size();
		for(int i = 0; i < UC.LIST_WORDS_TO_SHOW; i++) {
			if(totalListWords - i - 1 >= 0) {
				String word = config.correctWords.get(totalListWords - i - 1);
				double listWordWidth = FontMetrics.getTextWidth(g2.getFont(), g2, word);
				double listWordX = (sidebarWidth - listWordWidth) / 2;
				double listWordY = listY + listLineHeight * (i + 1) + listLineHeight / 3;
				g2.drawString(word, (int)listWordX, (int)listWordY);
			}
		}
	}

	private void paintResultsScreen(Graphics2D g2) {
		//TODO: Make results screen
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		iodata.keyDown(ke.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		iodata.keyUp(ke.getKeyCode());
	}

	@Override
	public void mousePressed(MouseEvent me) {
		iodata.setMousePressed(true);
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		iodata.setMousePressed(false);
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		iodata.setMouseLocation(me.getX(), me.getY());
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		iodata.setMouseLocation(me.getX(), me.getY());
	}

	@Override
	public void keyTyped(KeyEvent ke) {}
	@Override
	public void mouseClicked(MouseEvent me) {}
	@Override
	public void mouseEntered(MouseEvent me) {}
	@Override
	public void mouseExited(MouseEvent me) {}

}