package com.softcocoa.eightpuzzle.solvers;

import java.util.LinkedList;

import com.softcocoa.eightpuzzle.PuzzleState;
import com.softcocoa.eightpuzzle.heuristic.HeuristicCalculator;


public abstract class PuzzleSolveAlgorithm {
	
	private PuzzleState initialState;
	private HeuristicCalculator hCalculator;
	
	public PuzzleSolveAlgorithm(PuzzleState initialState, HeuristicCalculator hCalculator) {
		super();
		this.initialState = initialState;
		this.hCalculator = hCalculator;
	}

	public abstract LinkedList<PuzzleState> solvePuzzle();
}
