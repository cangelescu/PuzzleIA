package util;

import java.util.Comparator;

import model.Node;

public class HeuristicComparatorMan implements Comparator<Node> {
	@Override
	public int compare(Node x, Node y) {

		if (x.heuristic < y.heuristic) {
			return -1;
		}
		if (x.heuristic > y.heuristic) {
			return 1;
		}
		return 0;
	}
}