package com.softcocoa.eightpuzzle.solvers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import com.softcocoa.eightpuzzle.C;
import com.softcocoa.eightpuzzle.Helper;
import com.softcocoa.eightpuzzle.PuzzleState;
import com.softcocoa.eightpuzzle.heuristic.HeuristicCalculator;

public class AStarSearching extends PuzzleSolveAlgorithm {
	
	private PriorityQueue<PuzzleState> nodes;
	private ArrayList<PuzzleState> closedNodes;

	public AStarSearching(PuzzleState initialState, HeuristicCalculator hCalculator) {
		super(initialState, hCalculator);
		nodes = new PriorityQueue<PuzzleState>(C.INITIAL_CLOSED_LIST_SIZE, puzzleStateHeuristicComparator);
		closedNodes = new ArrayList<PuzzleState>(C.INITIAL_CLOSED_LIST_SIZE);
		nodes.add(initialState);
		numberOfGeneratedNodes++;
	}

	@Override
	public ArrayList<PuzzleState> solvePuzzle() {
		PuzzleState finishState = null;
		PuzzleState currentState = null;
		do {
			currentState = nodes.poll();
			System.out.println("current: " + currentState + "("+currentState.getHeuristic()+")");
			
			if (currentState.isFinishState()) {
				finishState = currentState;
				break;
			}
			
			closedNodes.add(currentState);
			
			ArrayList<PuzzleState> expandedStates = currentState.expand();
			for (PuzzleState expandedState : expandedStates) {
				double distance = hCalculator.calculate(expandedState, C.FINISH_STATE());
				double cost = hCalculator.calculate(expandedState, currentState);
				double heuristic = distance + cost;
				expandedState.setHeuristic(heuristic);
				if (!closedNodes.contains(expandedState)) {
					Helper.removeHigherHeuristicPuzzleState(expandedState, nodes);
					System.out.println(expandedState + (", "+nodes.add(expandedState)) + " " + expandedState.getHeuristic());
				}
				else {
					System.out.println("duplicated!");
				}
			}
			numberOfGeneratedNodes += expandedStates.size();
			System.out.println(numberOfGeneratedNodes + "("+ nodes.size() +")");
			if(nodes.size()<3) {
				System.out.println("nodes==========================");
				for (Iterator<PuzzleState> iterator = nodes.iterator(); iterator
						.hasNext();) {
					PuzzleState puzzleState = (PuzzleState) iterator.next();
					System.out.println(puzzleState);
				}
				System.out.println("===============================");
			}
		} while(nodes.size()>0 && numberOfGeneratedNodes<C.NODE_LIMIT);
		
		/*if (finishState==null)
			finishState = closedNodes.get(closedNodes.size()-1);*/
		
		if (finishState != null) {
			ArrayList<PuzzleState> solvingSteps = new ArrayList<PuzzleState>(C.INITIAL_STEP_LIST_SIZE);
			PuzzleState successor = finishState;
			solvingSteps.add(0, successor);
			while (successor.getParent() != null) {
				successor = successor.getParent();
				solvingSteps.add(0, successor);
			}
			solvingSteps.trimToSize();
			return solvingSteps;
		}
		else {
			System.out.println("Finish state not found!");
			return null;
		}
		
		// TODO expand PuzzleState in nodes TreeSet and keep expanded nodes in closedNodes
		// until we reach the goal and extract the solution path from closedNodes into solvingSteps.
		
		// testing dummy
		/*solvingSteps.add(initialState);
		solvingSteps.add(new PuzzleState(null, new int[]{1,0,3,4,2,5,7,8,6}));
		solvingSteps.add(new PuzzleState(null, new int[]{1,3,4,0,5,2,7,8,6}));
		solvingSteps.add(new PuzzleState(null, C.FINISH_PUZZLE_TILES()));
		solvingSteps.trimToSize();*/
		
//		solvingSteps.trimToSize();
//		return solvingSteps;
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
