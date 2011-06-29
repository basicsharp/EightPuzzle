package com.softcocoa.eightpuzzle;

import java.net.URL;
import java.util.LinkedList;

import org.eclipse.e4.xwt.IConstants;
import org.eclipse.e4.xwt.XWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

import com.softcocoa.eightpuzzle.heuristic.EuclideanDistanceHeuristicCalculator;
import com.softcocoa.eightpuzzle.heuristic.HeuristicCalculator;
import com.softcocoa.eightpuzzle.heuristic.ManhattanDistanceHeuristicCalculator;
import com.softcocoa.eightpuzzle.heuristic.MisplacedTilesHeuristicCalculator;
import com.softcocoa.eightpuzzle.solvers.AStarSearching;
import com.softcocoa.eightpuzzle.solvers.PuzzleSolveAlgorithm;


public class Main {
	
	private static CLabel[] uiTiles;
	private static Combo heuristicCombo;
	private static Button solveButton;
	
	private static PuzzleState initialPuzzleState; 

	public static void main(String args[]) throws Exception {
		// XWT init
		URL url = Main.class.getResource(Main.class.getSimpleName()
				+ IConstants.XWT_EXTENSION_SUFFIX);
		Control control = XWT.load(url);
		Shell shell = control.getShell();
		shell.layout();
		centerInDisplay(shell);
		
		// UI tiles inflating
		uiTiles = new CLabel[C.NUMBER_OF_TILES];
		for (int i=0; i<C.NUMBER_OF_TILES; i++) {
			uiTiles[i] = (CLabel) XWT.findElementByName(shell, "tile"+i);
		}
		heuristicCombo = (Combo) XWT.findElementByName(shell, "heuristicCombo");
		solveButton = (Button) XWT.findElementByName(shell, "solveButton");
		
		
		// data stuctures and puzzle tiles init
		initPuzzle();
		
		// run events loop
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch())
				shell.getDisplay().sleep();
		}
	}
	
	private static void initPuzzle() {
		int[] tiles = Helper.randomInitialPuzzleStateTiles();
		initialPuzzleState = new PuzzleState(tiles);
		drawPuzzle(initialPuzzleState);
	}
	
	private static void drawPuzzle(PuzzleState state) {
		int[] tiles = state.getTiles();
		for (int i=0; i<tiles.length; i++) {
			if (tiles[i]!=0) {
				uiTiles[i].setText(""+tiles[i]);
				uiTiles[i].setVisible(true);
			}
			else {
				uiTiles[i].setText("");
				uiTiles[i].setVisible(false);
			} 
		}
	}
	
	// XWT stubs 
	private static void centerInDisplay(Shell shell) {
		Rectangle displayArea = shell.getDisplay().getClientArea();
		shell.setBounds((displayArea.width-C.WINDOW_WIDTH)/2, (displayArea.height-C.WINDOW_HEIGHT)/2,
				(displayArea.width-C.WINDOW_WIDTH)/2+C.WINDOW_WIDTH, (displayArea.height-C.WINDOW_HEIGHT)/2+C.WINDOW_HEIGHT);
		shell.setSize(C.WINDOW_WIDTH, C.WINDOW_HEIGHT);
	}
	
	public void onRandomButtonSelection(Event event) {
		initPuzzle();
		System.out.println(heuristicCombo.getSelectionIndex());
	}
	public void onSolveButtonSelection(Event event) {
		HeuristicCalculator hCalculator = null;
		switch (heuristicCombo.getSelectionIndex()) {
			case 1:
				hCalculator = new ManhattanDistanceHeuristicCalculator();
				break;
	
			case 2:
				hCalculator = new EuclideanDistanceHeuristicCalculator();
				break;
				
			case 3:
				hCalculator = new MisplacedTilesHeuristicCalculator();
				break;
		}
//		PuzzleSolveAlgorithm algorithm = new AStarSearching(initialPuzzleState, hCalculator);
//		LinkedList<PuzzleState> steps = algorithm.solvePuzzle();
//		drawPuzzle(steps.get(steps.size()-1));
	}
	public void onExitButtonSelection(Event event) {
		System.exit(1);
	}
	public void onHeuristicComboModify(Event event) {
		if (solveButton != null) {
			if ( ((Combo) event.widget).getSelectionIndex() != 0)
				solveButton.setEnabled(true);
			else
				solveButton.setEnabled(false);
		}
	}
}
