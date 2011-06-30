package com.softcocoa.eightpuzzle;

import java.util.Random;

public class Helper {
	public static int[] randomInitialPuzzleStateTiles() {
		int[] tiles = C.FINISH_PUZZLE_TILES();
		Random randomizer = new Random(System.currentTimeMillis());
		for (int i=0; i<C.NUMBER_OF_TILES; i++) {
			int swapPosition = randomizer.nextInt(C.NUMBER_OF_TILES);
			int temp = tiles[i];
			tiles[i] = tiles[swapPosition];
			tiles[swapPosition] = temp;
		}
		return tiles;
	}
	
	public static int searchTileInTiles(int tileValue, int tiles[]) {
		for (int i=0; i<tiles.length; i++) {
			if(tiles[i] == tileValue)
				return i;
		}
		return -1; // not found!
	}
}
