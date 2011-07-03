package com.softcocoa.eightpuzzle;

public class C {
	public static int WINDOW_WIDTH = 500;
	public static int WINDOW_HEIGHT = 560;
	
	public static int NUMBER_OF_TILES = 9;
	public static int PUZZLE_SIDE_LENGTH = (int) Math.sqrt(NUMBER_OF_TILES);
	
	public static PuzzleState FINISH_STATE() {
		return new PuzzleState(null, FINISH_PUZZLE_TILES());
	}
	public static int[] FINISH_PUZZLE_TILES() {
		//return new int[]{1, 2, 3, 8, 0, 4, 7, 6, 5};
		return new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0};
	}
	public static int BLANK_TILE = 0;
	
	public static int NODE_LIMIT = 500000;
	
	public static int INITIAL_CLOSED_LIST_SIZE = 250000;
	public static int INITIAL_STEP_LIST_SIZE = 100000;
}
