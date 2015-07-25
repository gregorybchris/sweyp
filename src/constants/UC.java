package constants;

import java.util.Random;

import data.Location;

public class UC {
	
	/******* Game Settings ******/
	
	public static final int BOARD_SIZE = 4;
	public static final int DEFAULT_GAME_TIME = 60;
	public static final int MINIMUM_WORD_LENGTH = 3;
	public static final int LIST_WORDS_TO_SHOW = 8;
	
	/******* Game Constants ******/
	
	public static final String GAME_NAME = "Sweyp";
	public static final String GAME_TITLE = GAME_NAME + " - Copyright 2015 © Chris Gregory";
	
	public static final int SCREEN_HOME = 0;
	public static final int SCREEN_GAME = 1;
	public static final int SCREEN_RESULTS = 2;
	public static final int SCREEN_DEFAULT = SCREEN_GAME;
	
	public static final double TILE_SPRING_RIGIDITY = 2.0;
	public static final double TILE_SPRING_DAMPER = 0.5;
	public static final double TILE_SPRING_MIN = 1.0;
	
	public static final Location DEFAULT_TILE_LOCATION = new Location(-1, -1);
			
	/******* Timing ******/
	
	public static final int GAME_DELAY = 60;
	
	/******* Other ******/
	
	public static final Random RANDOM = new Random();
	public static final String DICTIONARY_FILE_NAME = "/english.txt";
}
