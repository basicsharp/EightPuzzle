If you want to implement with GUI, please download the latest Eclipse for Java Developer release (Indigo) from http://www.eclipse.org/. See this wiki page for more information about setup Eclipse for using XWT GUI framework.

Anyway, you can implement without install new Eclipse for just GUI (use System.out.print to test or other ways, up to you) and send code back to me for merging, since I  separated works into definite methods.

There are some unimplemented methods left as listed below:

###AStarSearching.solvePuzzle()
I prepare 2 TreeMap named "nodes" and "expandedNodes" for node pruning following the A* search algorithm. hCalculator will calculate heuristic score (the lower is the better).

and 3 more methods…
###ManhattanDistanceHeuristicCalculator.calculate(PuzzleState targetState, PuzzleState baseState)
###EuclideanDistanceHeuristicCalculator.calculate(PuzzleState targetState, PuzzleState baseState)
###MisplacedTilesHeuristicCalculator.calculate(PuzzleState targetState, PuzzleState baseState)

##PuzzleState Class
PuzzleState instance is basically a node in tree. It stores an array of puzzle tiles and heuristic score of itself. It can expand itself (generates more states by tile movings) and check whether it is a final state or not.

##Constants
Constants for this project are defined in C.java

##Questions?
If you have questions or found problems, post it on our Facebook group's wall.
http://www.facebook.com/home.php?sk=group_157364804332499&ap=1