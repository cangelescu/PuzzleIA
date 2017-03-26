/**
 * 
 */
package util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

import model.Node;
import model.Stare;
import model.Node.Actiune;

/**
 * @author Cristian Angelescu
 *
 */

public class Util {

	static long startTime = System.currentTimeMillis();

	public static String cautareInArbore(Stare stareInitiala, Stare stareFinala) {
		Comparator<Node> comparator = new HeuristicComparatorMan();
		PriorityQueue<Node> fringe = new PriorityQueue<Node>(100, comparator);
		Node nextNod = new Node();
		Node startNod = new Node(stareInitiala);
		startNod.setParinte(null);
		startNod.depth = 1;
		startNod.heuristic = startNod.depth + nrOfMisplaced(stareInitiala.getStateArray(), stareFinala.getStateArray());
		String solutie = null;
		HashSet<Stare> closed = new HashSet<Stare>();
		fringe.add(startNod);
		while (!fringe.isEmpty()) {
			if (fringe.isEmpty()) {
				solutie = "esuare";
				return solutie;
			}
			nextNod = fringe.poll();
			if (stareFinala.equals(nextNod.getStare())) {
				long endTime = System.currentTimeMillis();
				NumberFormat formatter = new DecimalFormat("#0.00000");
				System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
				solutie = solutie(nextNod);
				System.out.println("fringe:  " + fringe.size());
				System.out.println("closed:  " + closed.size());
				break;
			}
			if (!closed.contains(nextNod.getStare())) {
				closed.add(nextNod.getStare());
				fringe.addAll(expandeaza(nextNod, stareFinala));
			}
		}
		return solutie;
	}

	private static HashSet<Node> expandeaza(Node nod, Stare stareFinala) {
		HashSet<Node> succesori = new HashSet<Node>();
		int sizePuzzle = (int) Math.sqrt(nod.getStare().getStateArray().length);
		int zeroPosition = findZeroPosition(nod.getStare().getStateArray());
		for (model.Node.Actiune act : model.Node.Actiune.values()) {
			Node nodNou = new Node();
			Stare s = new Stare();
			if (act.toString() == "UP" && zeroPosition >= sizePuzzle) {
				s.setStateArray(Util.moveUp(nod.getStare().getStateArray(), sizePuzzle));
				s.setN(sizePuzzle);
				nodNou.setStare(s);
				nodNou.setActiune(Actiune.UP);
				nodNou.setCost(nod.getCost() + 1);
				nodNou.setParinte(nod);
				nodNou.depth = nod.depth + 1;
				nodNou.heuristic = nod.depth
						+ manHattanDistance(nodNou.getStare().getStateArray(), stareFinala.getStateArray());
				succesori.add(nodNou);
			}
		
			else if ((act.toString() == "DOWN")
					&& (zeroPosition < nod.getStare().getStateArray().length - sizePuzzle)) {
				s.setStateArray(Util.moveDown(nod.getStare().getStateArray(), sizePuzzle));
				s.setN(sizePuzzle);
				nodNou.setStare(s);
				nodNou.setActiune(Actiune.DOWN);
				nodNou.setCost(nod.getCost() + 1);
				nodNou.setParinte(nod);
				nodNou.depth = nod.depth + 1;
				nodNou.heuristic = nod.depth
						+ manHattanDistance(nodNou.getStare().getStateArray(), stareFinala.getStateArray());
				succesori.add(nodNou);

			} else if (act.toString() == "RIGHT" && ((zeroPosition + 1) % sizePuzzle != 0)) {
				s.setStateArray(Util.moveRight(nod.getStare().getStateArray(), sizePuzzle));
				s.setN(sizePuzzle);
				nodNou.setStare(s);
				nodNou.setActiune(Actiune.RIGHT);
				nodNou.setCost(nod.getCost() + 1);
				nodNou.setParinte(nod);
				nodNou.depth = nod.depth + 1;
				nodNou.heuristic = nod.depth
						+ manHattanDistance(nodNou.getStare().getStateArray(), stareFinala.getStateArray());
				succesori.add(nodNou);

			} else if (act.toString() == "LEFT" && (zeroPosition % sizePuzzle) != 0) {
				s.setStateArray(Util.moveLeft(nod.getStare().getStateArray(), sizePuzzle));
				s.setN(sizePuzzle);
				nodNou.setStare(s);
				nodNou.setActiune(Actiune.LEFT);
				nodNou.setCost(nod.getCost() + 1);
				nodNou.setParinte(nod);
				nodNou.depth = nod.depth + 1;
				nodNou.heuristic = nod.depth
						+ manHattanDistance(nodNou.getStare().getStateArray(), stareFinala.getStateArray());
				succesori.add(nodNou);
			}
		}
		return succesori;
	}

	private static String solutie(Node nod) {
		String solutie = "";
		solutie = solutie + nod.getActiune().toString();
		Node parinte = nod.getParinte();
		while (parinte.getParinte() != null) {
			solutie = solutie + " " + parinte.getActiune().toString();
			parinte = parinte.getParinte();
		}
		return solutie;
	}

	public static int[] moveUp(int[] state, int sizePuzzle) {
		int[] nextstate = new int[sizePuzzle * sizePuzzle];
		int zeroPosition = findZeroPosition(state);
		nextstate = Arrays.copyOf(state, state.length);
		nextstate[zeroPosition] = state[zeroPosition - sizePuzzle];
		nextstate[zeroPosition - sizePuzzle] = 0;
		return nextstate;
	}

	public static int[] moveDown(int[] state, int sizePuzzle) {
		int[] nextstate = new int[sizePuzzle * sizePuzzle];
		int zeroPosition = findZeroPosition(state);
		nextstate = Arrays.copyOf(state, state.length);
		nextstate[zeroPosition] = state[zeroPosition + sizePuzzle];
		nextstate[zeroPosition + sizePuzzle] = 0;
		return nextstate;
	}

	public static int[] moveRight(int[] state, int sizePuzzle) {
		int[] nextstate = new int[sizePuzzle * sizePuzzle];
		int zeroPosition = findZeroPosition(state);
		nextstate = Arrays.copyOf(state, state.length);
		nextstate[zeroPosition] = state[zeroPosition + 1];
		nextstate[zeroPosition + 1] = 0;
		return nextstate;
	}

	public static int[] moveLeft(int[] state, int sizePuzzle) {
		int[] nextstate = new int[sizePuzzle * sizePuzzle];
		int zeroPozition = findZeroPosition(state);
		nextstate = Arrays.copyOf(state, state.length);
		nextstate[zeroPozition] = state[zeroPozition - 1];
		nextstate[zeroPozition - 1] = 0;
		return nextstate;
	}

	public static int findZeroPosition(int[] stateArray) {
		for (int i = 0; i <= stateArray.length; i++) {
			if (stateArray[i] == 0)
				return i;
		}
		return 0;
	}

	public static int manHattanDistance(int[] stare, int[] stareFinala) {
		int manDistance = 0;
		int[][] goalarray, statearray = new int[(int) Math.sqrt(stare.length)][(int) Math.sqrt(stare.length)];
		goalarray = convertArray(stareFinala);
		statearray = convertArray(stare);
		int curentElement;
		for (int goalrow = 0; goalrow < (int) Math.sqrt(stare.length); goalrow++) {
			for (int goalcol = 0; goalcol < (int) Math.sqrt(stare.length); goalcol++) {
				curentElement = goalarray[goalrow][goalcol];
				for (int staterow = 0; staterow < (int) Math.sqrt(stare.length); staterow++) {
					for (int statecol = 0; statecol < (int) Math.sqrt(stare.length); statecol++) {
						if (curentElement == statearray[staterow][statecol]) {
							manDistance += Math.abs(goalrow - staterow) + Math.abs(goalcol - statecol);
						}
					}
				}
			}

		}
		return manDistance;
	}

	public static int[][] convertArray(int[] stare) {
		int[][] convertedarray = new int[(int) Math.sqrt(stare.length)][(int) Math.sqrt(stare.length)];
		int index = 0;
		for (int i = 0; i < (int) Math.sqrt(stare.length); i++) {
			for (int j = 0; j < (int) Math.sqrt(stare.length); j++) {
				convertedarray[i][j] = stare[index];
				index += 1;
			}
		}
		return convertedarray;
	}

	public static int nrOfMisplaced(int[] stateArray, int[] stareFinala) {
		int noOfMisplaced = 0;
		for (int index = 0; index < stateArray.length; index++) {
			if (stareFinala[index] != stateArray[index])
				noOfMisplaced += 1;
		}
		return noOfMisplaced - 1;
	}

	/**
	 * <h1>isSolvable</h1>
	 * <p>
	 * Verifica daca o stare initiala a unui puzzle n X n este rezolvabila;
	 * 
	 * @param puzzle
	 *            - sirul de valori ale puzzle-ului
	 * @return <i>true</i> - daca puzzle-ul se poate rezolva;
	 */

	public static boolean isSolvable(int[] puzzle) {
		int parity = 0;
		int gridWidth = (int) Math.sqrt(puzzle.length);
		int row = 0;
		int blankRow = 0;
		for (int i = 0; i < puzzle.length; i++) {
			if (i % gridWidth == 0)
				row++;

			if (puzzle[i] == 0) {
				blankRow = row;
				continue;
			}
			for (int j = i + 1; j < puzzle.length; j++) {
				if (puzzle[i] > puzzle[j] && puzzle[j] != 0)
					parity++;
			}
		}
		if (gridWidth % 2 == 0) {
			if (blankRow % 2 == 0)
				return parity % 2 == 0;
			else
				return parity % 2 != 0;
		} else
			return parity % 2 == 0;
	}
}
