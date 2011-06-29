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

import com.softcocoa.eightpuzzle.heuristic.HeuristicCalculator;
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
		initialPuzzleState = new PuzzleState(tiles);
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
		// TODO
		PuzzleSolveAlgorithm algorithm = new AStarSearching(initialPuzzleState, new HeuristicCalculator() {
			
			@Override
			public double calculate(PuzzleState targetState, PuzzleState baseState) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
	}
	public void onExitButtonSelection(Event event) {
		System.exit(1);
	}
	public void onHeuristicComboModify(Event event) {
		if (solveButton !=null) {
			if ( ((Combo) event.widget).getSelectionIndex() != 0)
				solveButton.setEnabled(true);
			else
				solveButton.setEnabled(false);
		}
	}
}
