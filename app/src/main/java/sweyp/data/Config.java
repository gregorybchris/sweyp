package sweyp.data;

import java.util.ArrayList;

import sweyp.constants.GC;
import sweyp.constants.UC;

public class Config {
	public Board board;
	public ArrayList<String> correctWords;
	
	public ArrayList<Location> tileList;
	public Vector[][] tileVelocities;
	public Point[][] tilePoints;
	public Point[][] tileDefaultPoints;
	public Location[][] tileLocations;
	
	public Point plusPoint;
	public Point plusDefaultPoint;
	public Point plusFinalPoint;
	public Vector plusVector;
	public int plusValue;
	
	public Location activeTileLocation;
	
	public int time;
	public int score;
	public double clock;
	
	public boolean playing;
	public boolean gameOver;
	
	public int screenMode = UC.SCREEN_DEFAULT;
	
	public Config() {
		setupConfig();
	}
	
	private void setupConfig() {
		board = new Board();
		correctWords = new ArrayList<String>();
		
		tileList = new ArrayList<Location>();
		activeTileLocation = UC.DEFAULT_TILE_LOCATION;
		tileVelocities = new Vector[board.getWidth()][board.getHeight()];
		tilePoints = new Point[board.getWidth()][board.getHeight()];
		tileDefaultPoints = new Point[board.getWidth()][board.getHeight()];
		tileLocations = new Location[board.getWidth()][board.getHeight()];
		
		plusDefaultPoint = new Point((GC.SCREEN_WIDTH - GC.SCREEN_HEIGHT) / 2, GC.SCREEN_HEIGHT / 2);
		plusPoint = new Point(plusDefaultPoint);
		plusFinalPoint = new Point(plusDefaultPoint);
		plusVector = new Vector(plusDefaultPoint, plusFinalPoint);
		plusValue = 0;
		
		time = UC.DEFAULT_GAME_TIME;
		score = 0;
		clock = 0;
		//TODO: Switch this to false
		playing = true;
		gameOver = false;
		
		initializeTiles();
	}
	
	public void reset() {
		setupConfig();
	}
	
	private void initializeTiles() {
		double sideBarWidth = GC.SCREEN_WIDTH - GC.SCREEN_HEIGHT;
		double gameWidth = GC.SCREEN_WIDTH - sideBarWidth;
		int boardWidth = board.getWidth();
		int boardHeight = board.getHeight();
		double tileSpaceWidth = (gameWidth - GC.TILE_SPACING) / boardWidth;
		double tileSpaceHeight = (gameWidth - GC.TILE_SPACING) / boardHeight;
		for(int y = 0; y < board.getHeight(); y++) {
			for(int x = 0; x < board.getWidth(); x++) {
				double tileX = sideBarWidth + x * tileSpaceWidth + tileSpaceWidth / 2;
				double tileY = y * tileSpaceHeight + tileSpaceHeight / 2;
				tilePoints[x][y] = new Point(tileX, tileY);
				tileDefaultPoints[x][y] = new Point(tileX, tileY);
				tileVelocities[x][y] = new Vector();
				tileLocations[x][y] = new Location(x, y);
			}
		}
	}
}
