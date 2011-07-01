package com.softcocoa.eightpuzzle;

import java.util.TreeSet;

public class PuzzleState {
	protected int[] tiles;
	protected double heuristic;
	protected PuzzleState parent;
//	protected PuzzleState heuristicBaseState;
	
//	protected HeuristicCalculator hCalculator;
	
	public PuzzleState(PuzzleState parent, int[] tiles) {
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
	 
	public TreeSet<PuzzleState> expand() {
		TreeSet<PuzzleState> expandedStates = new TreeSet<PuzzleState>();
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
			
			PuzzleState newState = new PuzzleState(this, newTiles);
			expandedStates.add(newState);
		}
		
		if (blankTileDownMovable) {
			int[] newTiles = tiles.clone();
			int swapPosition = blankTilePosition+C.PUZZLE_SIDE_LENGTH;
			
			int temp = newTiles[blankTilePosition];
			newTiles[blankTilePosition] = newTiles[swapPosition];
			newTiles[swapPosition] = temp;
			
			PuzzleState newState = new PuzzleState(this, newTiles);
			expandedStates.add(newState);
		}
		
		if (blankTileLeftMovable) {
			int[] newTiles = tiles.clone();
			int swapPosition = blankTilePosition-1;
			
			int temp = newTiles[blankTilePosition];
			newTiles[blankTilePosition] = newTiles[swapPosition];
			newTiles[swapPosition] = temp;
			
			PuzzleState newState = new PuzzleState(this, newTiles);
			expandedStates.add(newState);
		}
		
		if (blankTileRightMovable) {
			int[] newTiles = tiles.clone();
			int swapPosition = blankTilePosition+1;
			
			int temp = newTiles[blankTilePosition];
			newTiles[blankTilePosition] = newTiles[swapPosition];
			newTiles[swapPosition] = temp;
			
			PuzzleState newState = new PuzzleState(this, newTiles);
			expandedStates.add(newState);
		}
		
		return expandedStates;
	}
	
	public boolean equals(PuzzleState state) {
		return tiles.equals(state.getTiles());
	}
	
	public boolean isFinishState() {
		return tiles.equals(C.FINISH_PUZZLE_TILES());
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
	
	public PuzzleState getParent() {
		return parent;
	}
	
	public void setParent(PuzzleState state) {
		this.parent = state;
	}
	
}
