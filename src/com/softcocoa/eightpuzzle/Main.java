package com.softcocoa.eightpuzzle;

import java.net.URL;
import java.util.ArrayList;

import org.eclipse.e4.xwt.IConstants;
import org.eclipse.e4.xwt.XWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import com.softcocoa.eightpuzzle.heuristic.EuclideanDistanceHeuristicCalculator;
import com.softcocoa.eightpuzzle.heuristic.HeuristicCalculator;
import com.softcocoa.eightpuzzle.heuristic.ManhattanDistanceHeuristicCalculator;
import com.softcocoa.eightpuzzle.heuristic.MisplacedTilesHeuristicCalculator;
import com.softcocoa.eightpuzzle.solvers.AStarSearching;
import com.softcocoa.eightpuzzle.solvers.PuzzleSolveAlgorithm;


public class Main {
	
	private static Shell shell;
	private static CLabel[] uiTiles;
	private static Combo heuristicCombo;
	private static Button solveButton;
	private static CLabel stepLabel;
	private static Button prevButton;
	private static Button nextButton;
	
	private static PuzzleState initialPuzzleState; 
	
	private static int numberOfSteps;
	private static int stepIndex;
	private static ArrayList<PuzzleState> steps;

	public static void main(String args[]) throws Exception {
		// XWT init
		URL url = Main.class.getResource(Main.class.getSimpleName()
				+ IConstants.XWT_EXTENSION_SUFFIX);
		Control control = XWT.load(url);
		shell = control.getShell();
		shell.layout();
		centerInDisplay(shell);
		
		// UI tiles inflating
		uiTiles = new CLabel[C.NUMBER_OF_TILES];
		for (int i=0; i<C.NUMBER_OF_TILES; i++) {
			uiTiles[i] = (CLabel) XWT.findElementByName(shell, "tile"+i);
		}
		heuristicCombo = (Combo) XWT.findElementByName(shell, "heuristicCombo");
		solveButton = (Button) XWT.findElementByName(shell, "solveButton");
		stepLabel = (CLabel) XWT.findElementByName(shell, "stepLabel");
		prevButton = (Button) XWT.findElementByName(shell, "prevButton");
		nextButton = (Button) XWT.findElementByName(shell, "nextButton");
		
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
		initialPuzzleState = new PuzzleState(null, tiles);
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
		stepLabel.setText("");
		initPuzzle();
		prevButton.setEnabled(false);
		nextButton.setEnabled(false);
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
	public void onSolveButtonSelection(Event event) {
		stepIndex = 0;
		numberOfSteps = 0;
		steps = null;
		stepLabel.setText("");
		
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
		PuzzleSolveAlgorithm algorithm = new AStarSearching(initialPuzzleState, hCalculator);
		steps = algorithm.solvePuzzle();
		
		if (steps != null) {
			numberOfSteps = steps.size();
			stepIndex = steps.size()-1;
			drawPuzzle(steps.get(stepIndex));
			printStepLabel(stepIndex+1);
			
			prevButton.setEnabled(true);
			nextButton.setEnabled(false);
		}
		else {
			MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR);
			dialog.setText(C.APP_NAME);
			dialog.setMessage("Finish state not found!");
			dialog.open();
		}
	}
	public void onPrevButtonSelection(Event event) {
		if(stepIndex>0) {
			drawPuzzle(steps.get(--stepIndex));
			printStepLabel(stepIndex+1);
			nextButton.setEnabled(true);
		}
		if(stepIndex<=0) {
			prevButton.setEnabled(false);
		}
	}
	public void onNextButtonSelection(Event event) {
		if(stepIndex<numberOfSteps-1) {
			drawPuzzle(steps.get(++stepIndex));
			printStepLabel(stepIndex+1);
			prevButton.setEnabled(true);
		}
		if(stepIndex>=numberOfSteps-1) {
			nextButton.setEnabled(false);
		}
	}
	
	public void printStepLabel(int stepIndex) {
		stepLabel.setText("Step: "+stepIndex+"/"+numberOfSteps);
	}
}
