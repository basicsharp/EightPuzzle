package com.softcocoa.eightpuzzle.solvers;

import java.util.LinkedList;
import java.util.TreeMap;

import com.softcocoa.eightpuzzle.PuzzleState;
import com.softcocoa.eightpuzzle.heuristic.HeuristicCalculator;

public class AStarSearching extends PuzzleSolveAlgorithm {
	
	private TreeMap<Integer, PuzzleState> nodes, expandedNodes;

	public AStarSearching(PuzzleState initialState, HeuristicCalculator hCalculator) {
		super(initialState, hCalculator);
		nodes = new TreeMap<Integer, PuzzleState>();
		expandedNodes = new TreeMap<Integer, PuzzleState>();
		nodes.put(initialState.hashCode(), initialState);
	}

	@Override
	public LinkedList<PuzzleState> solvePuzzle() {
		// TODO Auto-generated method stub
		return null;
	}

}
