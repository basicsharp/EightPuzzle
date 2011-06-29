If you want to implement with GUI, please download the latest Eclipse for Java Developer release (Indigo) from http://www.eclipse.org/. See [this wiki page](https://github.com/basicsharp/EightPuzzle/wiki/Installing-XWT-GUI-Framework-on-Eclipse-3.7-Indigo) for more information about setup Eclipse for using XWT GUI Framework.

Anyway, you can implement without install new Eclipse for just GUI (use System.out.print to test or other ways, up to you) and send code back to me for merging, since I  separated works into definite methods.

There are some unimplemented methods left as listed below:

__AStarSearching.solvePuzzle()__

I prepared 2 ~~TreeMap~~ TreeSet named "nodes" and "expandedNodes" for node pruning following the A* search algorithm. hCalculator will calculate heuristic score (the lower is the better).

and 3 more methods…

__ManhattanDistanceHeuristicCalculator.calculate(PuzzleState targetState, PuzzleState baseState)__

__EuclideanDistanceHeuristicCalculator.calculate(PuzzleState targetState, PuzzleState baseState)__

__MisplacedTilesHeuristicCalculator.calculate(PuzzleState targetState, PuzzleState baseState)__

## PuzzleState Class
PuzzleState instance is basically a node in our puzzle tree. It stores an array of puzzle tiles and its heuristic score. It can expand itself (generates more states by tile movings) and check whether it is a final state or not.

## Constants
Constants for this project are defined in C.java

## Questions?
If you have questions or found problems, post it on our Facebook group's wall.

http://www.facebook.com/home.php?sk=group_157364804332499&ap=1