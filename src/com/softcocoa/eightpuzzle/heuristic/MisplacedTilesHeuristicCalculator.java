package com.softcocoa.eightpuzzle.heuristic;

import com.softcocoa.eightpuzzle.C;
import com.softcocoa.eightpuzzle.PuzzleState;

public class MisplacedTilesHeuristicCalculator implements HeuristicCalculator {

	@Override
	public double calculate(PuzzleState targetState, PuzzleState baseState) {
		// TODO
		double misplaceHeuristic = 0;		
		for(int i=0;i<C.NUMBER_OF_TILES;i++) {			
			if(targetState.getTile(i) != baseState.getTile(i)){
				misplaceHeuristic++;
			}
		}		
		return misplaceHeuristic;
	}

}
