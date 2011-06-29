package com.softcocoa.eightpuzzle;

import java.util.TreeMap;

public class PuzzleState {
	private int[] tiles;
	private double heuristic;
//	private PuzzleState heuristicBaseState;
	
//	private HeuristicCalculator hCalculator;
	
	public PuzzleState(int[] tiles) {
		this.tiles = tiles;
	}
	
//	public PuzzleState(int[] tiles, PuzzleState heuristicBaseState) {
//		this.tiles = tiles;
//		this.heuristicBaseState = heuristicBaseState;
//		this.calculateHeuristic();
//	}

//	private void calculateHeuristic() {
//		if (heuristicBaseState != null)
//			this.heuristic = hCalculator.calculate(this, heuristicBaseState);
//		else
//			this.heuristic = 0;
//	}
	 
	public TreeMap<Integer, PuzzleState> expand() {
		TreeMap<Integer, PuzzleState> expandedStates = new TreeMap<Integer, PuzzleState>();
		int blankTilePosition = Helper.searchTileInTiles(C.BLANK_TILE, tiles);
		
		boolean blankTileUpMovable = (blankTilePosition/C.PUZZLE_SIDE_LENGTH)-1 >= 0;
		boolean blankTileDownMovable = (blankTilePosition/C.PUZZLE_SIDE_LENGTH)+1 < C.PUZZLE_SIDE_LENGTH;
		boolean blankTileLeftMovable = (blankTilePosition%C.PUZZLE_SIDE_LENGTH)-1 >= 0;
		boolean blankTileRightMovable = (blankTilePosition%C.PUZZLE_SIDE_LENGTH)+1 < C.PUZZLE_SIDE_LENGTH;
		
		if (blankTileUpMovable) {
			int[] newTiles = tiles.clone();
			int swapPosition = blankTilePosition-C.PUZZLE_SIDE_LENGTH;
			
			int temp = newTiles[blankTilePosition];
			newTiles[blankTilePosition] = newTiles[swapPosition];
			newTiles[swapPosition] = temp;
			
			PuzzleState newState = new PuzzleState(newTiles);
			expandedStates.put(newState.hashCode(), newState);
		}
		
		if (blankTileDownMovable) {
			int[] newTiles = tiles.clone();
			int swapPosition = blankTilePosition+C.PUZZLE_SIDE_LENGTH;
			
			int temp = newTiles[blankTilePosition];
			newTiles[blankTilePosition] = newTiles[swapPosition];
			newTiles[swapPosition] = temp;
			
			PuzzleState newState = new PuzzleState(newTiles);
			expandedStates.put(newState.hashCode(), newState);
		}
		
		if (blankTileLeftMovable) {
			int[] newTiles = tiles.clone();
			int swapPosition = blankTilePosition-1;
			
			int temp = newTiles[blankTilePosition];
			newTiles[blankTilePosition] = newTiles[swapPosition];
			newTiles[swapPosition] = temp;
			
			PuzzleState newState = new PuzzleState(newTiles);
			expandedStates.put(newState.hashCode(), newState);
		}
		
		if (blankTileRightMovable) {
			int[] newTiles = tiles.clone();
			int swapPosition = blankTilePosition+1;
			
			int temp = newTiles[blankTilePosition];
			newTiles[blankTilePosition] = newTiles[swapPosition];
			newTiles[swapPosition] = temp;
			
			PuzzleState newState = new PuzzleState(newTiles);
			expandedStates.put(newState.hashCode(), newState);
		}
		
		return expandedStates;
	}
	
	public boolean isFinishState() {
		return tiles.equals(C.FINISH_PUZZLE_TILES);
	}
	
	// Getters and setters
	
	public int getTile(int index) {
		return tiles[index];
	}
	
	public void setTile(int index, int value) {
		tiles[index] = value;
	}
	
	public int[] getTiles() {
		return tiles;
	}

	public void setTiles(int[] tiles) {
		this.tiles = tiles;
	}

	public double getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	
	
}
