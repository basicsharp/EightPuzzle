package com.softcocoa.eightpuzzle.solvers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

import com.softcocoa.eightpuzzle.PuzzleState;
import com.softcocoa.eightpuzzle.heuristic.HeuristicCalculator;

public class AStarSearching extends PuzzleSolveAlgorithm {
	
	private TreeSet<PuzzleState> nodes, expandedNodes;

	public AStarSearching(PuzzleState initialState, HeuristicCalculator hCalculator) {
		super(initialState, hCalculator);
		nodes = new TreeSet<PuzzleState>(puzzleStateHeuristicComparator);
		expandedNodes = new TreeSet<PuzzleState>(puzzleStateHeuristicComparator);
		nodes.add(initialState);
	}

	@Override
	public LinkedList<PuzzleState> solvePuzzle() {
		// TODO
		return null;
	}
	
	private Comparator<PuzzleState> puzzleStateHeuristicComparator = new Comparator<PuzzleState>() {
		@Override
		public int compare(PuzzleState o1, PuzzleState o2) {
			int result = 0;
			
			if (o1.getHeuristic()<o2.getHeuristic())
				result = -1;
			else if (o1.getHeuristic()>o2.getHeuristic())
				result = 1;
			
			return result;
		}
	};

}
