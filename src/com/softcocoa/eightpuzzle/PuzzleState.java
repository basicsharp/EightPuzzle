package com.softcocoa.eightpuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import com.softcocoa.eightpuzzle.heuristic.HeuristicCalculator;

public class PuzzleState {
	protected int[] tiles;
	protected double heuristic;
	protected PuzzleState parent;
//	protected PuzzleState heuristicBaseState;
	
//	protected HeuristicCalculator hCalculator;
	
	public PuzzleState(PuzzleState parent, int[] tiles) {
		this.parent = parent;
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
	 
	public ArrayList<PuzzleState> expand() {
		ArrayList<PuzzleState> expandedStates = new ArrayList<PuzzleState>(4);
		int blankTilePosition = Helper.searchTileInTiles(C.BLANK_TILE, tiles);
		
		boolean blankTileUpMovable = ((int) blankTilePosition/C.PUZZLE_SIDE_LENGTH)-1 >= 0;
		boolean blankTileDownMovable = ((int) blankTilePosition/C.PUZZLE_SIDE_LENGTH)+1 < C.PUZZLE_SIDE_LENGTH;
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
		
		expandedStates.trimToSize();
		return expandedStates;
	}
	
	public boolean equals(Object obj) {
		return Arrays.equals(tiles, ((PuzzleState) obj).getTiles());
//		return tiles.equals(((PuzzleState) obj).getTiles());
	}
	
	public boolean isFinishState() {
		return Arrays.equals(tiles, C.FINISH_PUZZLE_TILES());
	}
	
	public String toString() {
		String output = "";
		for (int i = 0; i < tiles.length; i++) {
			output += ", " + tiles[i]; 
		}
		output = 
				output.substring(2);
		return output;
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

	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}
	
	public PuzzleState getParent() {
		return parent;
	}
	
	public void setParent(PuzzleState state) {
		this.parent = state;
	}
	
}
