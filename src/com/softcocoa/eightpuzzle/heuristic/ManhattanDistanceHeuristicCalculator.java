package com.softcocoa.eightpuzzle.heuristic;

import com.softcocoa.eightpuzzle.C;
import com.softcocoa.eightpuzzle.Helper;
import com.softcocoa.eightpuzzle.PuzzleState;

public class ManhattanDistanceHeuristicCalculator implements
		HeuristicCalculator {

	@Override
	public double calculate(PuzzleState targetState, PuzzleState baseState) {
		// TODO Auto-generated method stub
		double manhattanDist = 0;
		int targetTileIndex;
		int baseTileIndex;
		int xTarget,xBase,yTarget,yBase;
		double abs;
		for(int i=0;i<C.NUMBER_OF_TILES;i++){
			targetTileIndex = Helper.searchTileInTiles(i, targetState.getTiles());
			baseTileIndex = Helper.searchTileInTiles(i, baseState.getTiles());
			
			xTarget = (int)Math.floor(targetTileIndex/3);
			xBase = (int)Math.floor(baseTileIndex/3);
			
			yTarget = targetTileIndex%3;
			yBase = baseTileIndex%3;
			
			abs = Math.abs(xTarget-xBase) + Math.abs(yTarget-yBase);
			manhattanDist += abs;
		}
		return manhattanDist;
	}

}
