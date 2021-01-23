package sweyp.structure;

import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

import sweyp.constants.GC;
import sweyp.constants.UC;
import sweyp.data.Config;
import sweyp.data.Dictionary;
import sweyp.data.IOData;
import sweyp.data.Location;

public class Engine {
	private IOData iodata;
	private Config config;

	private Dictionary dictionary;

	public Engine(Config config, IOData iodata) {
		this.iodata = iodata;
		this.config = config;
		this.dictionary = new Dictionary();

		getAllValidWords();
	}

	public void update() {
		if(config.screenMode == UC.SCREEN_HOME)
			updateHomeScreen();
		else if(config.screenMode == UC.SCREEN_GAME)
			updateGameScreen();
	}

	private void updateHomeScreen() {
		//TODO: Make home screen interactive with mouse
	}

	private void updateGameScreen() {
		if(!config.gameOver) {
			updateClock();
			updateTileVelocities();
			updateTilePoints();
			updateTileData();
			updatePlus();
		}
		else
			config.correctWords.remove(config.correctWords);

		if(iodata.keyAccess(KeyEvent.VK_R))
			restartGame();
	}

	private void restartGame() {
		config.reset();
		config.playing = true;
	}

	private void updateClock() {
		if(config.playing && !config.gameOver) {
			config.clock += UC.GAME_DELAY / 1000.0;
			config.time = UC.DEFAULT_GAME_TIME - (int)config.clock;
			if(config.time <= 0) {
				config.gameOver = true;
				config.tileList.removeAll(config.tileList);
				config.plusFinalPoint.set(config.plusDefaultPoint);
			}
		}
	}

	private void displaceTiles() {
		for(int y = 0; y < config.board.getHeight(); y++) {
			for(int x = 0; x < config.board.getWidth(); x++) {
				int dx = UC.RANDOM.nextInt(GC.TILE_SPACING) - GC.TILE_SPACING / 2;
				int dy = UC.RANDOM.nextInt(GC.TILE_SPACING) - GC.TILE_SPACING / 2;
				config.tilePoints[x][y].x += dx;
				config.tilePoints[x][y].y += dy;
			}
		}
	}

	private void emitScore(int score) {
		config.score += score;
		config.plusValue = score;
		config.plusPoint.set(config.plusDefaultPoint);
		double plusFinalX = config.plusDefaultPoint.x + 
				UC.RANDOM.nextInt(GC.PLUS_MAX_DISTANCE_X * 2) - GC.PLUS_MAX_DISTANCE_X;
		double plusFinalY = config.plusDefaultPoint.y - 
				GC.PLUS_MAX_DISTANCE_Y - UC.RANDOM.nextInt(GC.PLUS_MAX_DISTANCE_Y / 2);
		config.plusFinalPoint.set(plusFinalX, plusFinalY);
		config.plusVector.set(config.plusDefaultPoint, config.plusFinalPoint);
		config.plusVector.setMagnitude(GC.PLUS_FLOAT_SPEED);
	}

	private void updatePlus() {
		if(!config.plusDefaultPoint.equals(config.plusFinalPoint)) {
			if(config.plusPoint.distance(config.plusDefaultPoint) < 
					config.plusFinalPoint.distance(config.plusDefaultPoint)) {
				config.plusPoint.translate(config.plusVector.getX(), config.plusVector.getY());
			}
			else
				config.plusPoint.set(config.plusFinalPoint);
		}
	}

	private int getWordScore(String word) {
		/*
		 	Letters - 
			+1 - A, E, I, O, R, S, T
			+2 - D, L, N, U
			+3 - G, H, Y
			+4 - B, C, F, M, P, W
			+5 - K, V
			+8 - X
			+10 - J, Q, Z

			Lengths - 
			+3 - 5
			+6 - 6
			+10 - 7
			+15 - 8
			+21 - 9
			+28 - 10
			+36 - 11
		 */

		int score = 0;

		for(char c : word.toCharArray()) {
			if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'R' || c == 'S' || c == 'T')
				score += 1;
			else if(c == 'D' || c == 'L' || c == 'N' || c == 'U')
				score += 2;
			else if(c == 'G' || c == 'H' || c == 'Y')
				score += 3;
			else if(c == 'B' || c == 'C' || c == 'F' || c == 'M' || c == 'P' || c == 'W')
				score += 4;
			else if(c == 'K' || c == 'V')
				score += 5;
			else if(c == 'X')
				score += 8;
			else if(c == 'J' || c == 'Q' || c == 'Z')
				score += 10;
		}

		int length = word.length();
		if(length < 5)
			score += 0;
		else if(length == 5)
			score += 3;
		else if(length == 6)
			score += 6;
		else if(length == 7)
			score += 10;
		else if(length == 8)
			score += 15;
		else if(length == 9)
			score += 21;
		else if(length == 10)
			score += 28;
		else if(length == 11)
			score += 36;
		else
			score += 45;

		return score;
	}

	private void updateTileVelocities() {
		for(int y = 0; y < config.board.getHeight(); y++) {
			for(int x = 0; x < config.board.getWidth(); x++) {
				double dx = config.tileDefaultPoints[x][y].x - config.tilePoints[x][y].x;
				double dy = config.tileDefaultPoints[x][y].y - config.tilePoints[x][y].y;
				config.tileVelocities[x][y].add(
						dx * UC.TILE_SPRING_RIGIDITY,
						dy * UC.TILE_SPRING_RIGIDITY);
				config.tileVelocities[x][y].multiply(UC.TILE_SPRING_DAMPER);
				if(config.tileVelocities[x][y].getMagnitude() < UC.TILE_SPRING_MIN)
					config.tileVelocities[x][y].setMagnitude(0);
			}
		}
	}

	private void updateTilePoints() {
		for(int y = 0; y < config.board.getHeight(); y++) {
			for(int x = 0; x < config.board.getWidth(); x++) {
				config.tilePoints[x][y].x += config.tileVelocities[x][y].getX();
				config.tilePoints[x][y].y += config.tileVelocities[x][y].getY();
			}
		}
	}

	private void updateTileData() {
		Location mouseTileLocation = getHoverTileLocation(iodata.getMouseX(), iodata.getMouseY());

		if(iodata.getMousePressed()) {
			if(!mouseTileLocation.equals(UC.DEFAULT_TILE_LOCATION)) {
				if(!config.tileList.contains(mouseTileLocation)) {
					if(config.activeTileLocation.neighbors(mouseTileLocation) ||
							config.tileList.isEmpty()) {
						config.activeTileLocation = mouseTileLocation;
						config.tileList.add(config.activeTileLocation);
					}
				}
			}
		}
		else {
			if(!config.tileList.isEmpty()) {
				config.activeTileLocation = UC.DEFAULT_TILE_LOCATION;
				StringBuffer wordBuffer = new StringBuffer();
				int listSize = config.tileList.size();
				if(listSize >= UC.MINIMUM_WORD_LENGTH) {
					for(int i = 0; i < listSize; i++) {
						Location tile = config.tileList.get(0);
						wordBuffer.append(config.board.get(tile.x, tile.y));
						config.tileList.remove(0);
					}

					String word = wordBuffer.toString();
					if(dictionary.isWord(word) && !config.correctWords.contains(word)) {
						config.correctWords.add(word);
						int wordScore = getWordScore(word);
						emitScore(wordScore);
					}
					else {
						displaceTiles();
					}
				}
				config.tileList.removeAll(config.tileList);
			}
		}
	}

	private Location getHoverTileLocation(double mx, double my) {
		double sideBarWidth = GC.SCREEN_WIDTH - GC.SCREEN_HEIGHT;
		double gameWidth = GC.SCREEN_WIDTH - sideBarWidth;
		int boardWidth = config.board.getWidth();
		double tileSpaceWidth = (gameWidth - GC.TILE_SPACING) / boardWidth;
		for(int y = 0; y < config.board.getHeight(); y++) {
			for(int x = 0; x < config.board.getWidth(); x++) {
				if(config.tilePoints[x][y].distance(mx, my) <= tileSpaceWidth / 2)
					return config.tileLocations[x][y];
			}
		}
		return UC.DEFAULT_TILE_LOCATION;
	}

	private ArrayList<String> getAllValidWords() {
		ArrayList<String> validWords = new ArrayList<String>();
		ArrayDeque<Location> locations = new ArrayDeque<Location>();
		String word = "";
		boolean[][] visited = new boolean[config.board.getWidth()][config.board.getHeight()];

		for(int y = 0; y < config.board.getHeight(); y++) {
			for(int x = 0; x < config.board.getWidth(); x++) {
				for(int t = 0; t < config.board.getHeight(); t++)
					for(int s = 0; s < config.board.getWidth(); s++)
						visited[s][t] = false;

				locations.push(new Location(x, y));
				while(!locations.isEmpty()) {
					Location loc = locations.pop();

					if(visited[loc.x][loc.y]) {
						visited[loc.x][loc.y] = false;
						word = word.substring(0, word.length() - 1);
					}
					else {
						visited[loc.x][loc.y] = true;
						word = word + config.board.get(loc.x, loc.y);

						String currentWord = word.toString();
						if(currentWord.length() > 2 && dictionary.isWord(currentWord))
							validWords.add(currentWord);

						if(dictionary.isPrefix(word.toString())) {
							locations.push(loc);
							int numNeighbors = 0;
							for(int j = loc.y - 1; j <= loc.y + 1; j++) {
								for(int i = loc.x - 1; i <= loc.x + 1; i++) {
									if(!(i == loc.x && j == loc.y)) {
										if(config.board.isValid(i, j)) {
											if(!visited[i][j]) {
												locations.push(new Location(i, j));
												numNeighbors++;
											}
										}
									}
								}
							}
							if(numNeighbors == 0) {
								locations.pop();
								visited[loc.x][loc.y] = false;
								word = word.substring(0, word.length() - 1);
							}
						}
						else {
							visited[loc.x][loc.y] = false;
							word = word.substring(0, word.length() - 1);
						}
					}
				}
			}
		}
		
		Collections.sort(validWords);
		return validWords;
	}
}
