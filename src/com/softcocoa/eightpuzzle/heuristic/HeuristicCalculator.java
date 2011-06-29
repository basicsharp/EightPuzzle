package com.softcocoa.eightpuzzle.heuristic;

import com.softcocoa.eightpuzzle.PuzzleState;


public interface HeuristicCalculator {
	public double calculate(PuzzleState targetState, PuzzleState baseState);
}