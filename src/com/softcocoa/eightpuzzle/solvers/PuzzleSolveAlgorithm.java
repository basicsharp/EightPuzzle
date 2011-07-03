package com.softcocoa.eightpuzzle.solvers;

import java.util.ArrayList;

import com.softcocoa.eightpuzzle.PuzzleState;
import com.softcocoa.eightpuzzle.heuristic.HeuristicCalculator;


public abstract class PuzzleSolveAlgorithm {
	
	protected PuzzleState initialState;
	protected HeuristicCalculator hCalculator;
	protected int numberOfGeneratedNodes;
	
	public PuzzleSolveAlgorithm(PuzzleState initialState, HeuristicCalculator hCalculator) {
		super();
		this.initialState = initialState;
		this.hCalculator = hCalculator;
		numberOfGeneratedNodes = 0;
	}

	public abstract ArrayList<PuzzleState> solvePuzzle();
}
