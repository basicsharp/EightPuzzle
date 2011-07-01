package com.softcocoa.eightpuzzle;

public class C {
	public static int WINDOW_WIDTH = 500;
	public static int WINDOW_HEIGHT = 560;
	
	public static int NUMBER_OF_TILES = 9;
	public static int PUZZLE_SIDE_LENGTH = (int) Math.sqrt(NUMBER_OF_TILES);
	public static int[] FINISH_PUZZLE_TILES() {
		return new int[]{1, 2, 3, 8, 0, 4, 7, 6, 5};
	}
	public static int BLANK_TILE = 0;
	
	public static int INITIAL_CLOSED_LIST_SIZE = 25000;
	public static int INITIAL_STEP_LIST_SIZE = 10000;
}
