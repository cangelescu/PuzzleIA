package test;


import model.Node;
import model.Stare;

public class mainTest1 {


	public static void main(String argv[]) {


		// corect
		int[] puzzle7 = { 7, 2, 4, 5, 8, 6, 0, 3, 1 };
		int[] puzzle1 = { 0, 7, 4, 5, 2, 6, 8, 3, 1 };
		int[] puzzle2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 11, 13, 14, 15, 12 };
		int[] puzzle6 = { 1, 8, 2, 0, 4, 3, 7, 6, 5 };
 		// gresit
		int[] puzzle3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 14, 0 };
		int[] puzzle4 = { 5, 6, 7, 4, 0, 8, 3, 2, 1 };
		int[] puzzle5 = { 8, 1, 2, 0, 4, 3, 7, 6, 5 };
		int[] puzzle8 = { 1, 0, 2, 3 };

		System.out.println("Este rezolvabil? " + util.Util.isSolvable(puzzle1));
		System.out.println("Este rezolvabil? " + util.Util.isSolvable(puzzle2));
		System.out.println("Este rezolvabil? " + util.Util.isSolvable(puzzle3));
		System.out.println("Este rezolvabil? " + util.Util.isSolvable(puzzle4));
		System.out.println("Este rezolvabil? " + util.Util.isSolvable(puzzle5));
		System.out.println("Este rezolvabil? " + util.Util.isSolvable(puzzle6));
		System.out.println("2x2 " + util.Util.isSolvable(puzzle8));
		System.out.println("test " + util.Util.isSolvable(new Stare(puzzle1).getStateArray()));
		
		
		Node firstNod = new Node(new Stare( (int) Math.sqrt(puzzle7.length),puzzle7));
		System.out.println("firstNod: " + firstNod.toString());
		
		Node nextNod = new Node(firstNod, model.Node.Actiune.RIGHT);		
		System.out.println("nextNod: " + nextNod.toString());
		
		Node nextNode2 = new Node(nextNod, model.Node.Actiune.UP);
		System.out.println("nextNode2: " + nextNode2.toString());


	}
}
