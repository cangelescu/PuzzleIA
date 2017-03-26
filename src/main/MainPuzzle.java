/**
 * 
 */
package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

import model.Stare;

/**
 * @author Cristian Angelescu
 *
 */
public class MainPuzzle {
	@SuppressWarnings({ "resource" })
	public static void main(String args[]) throws IOException {
		long startTime = System.currentTimeMillis();
		Scanner sc = new Scanner(new File("input1"));
		int marimePuzzle = sc.nextInt();
		System.out.println("marime puzzle: " + marimePuzzle + " X " + marimePuzzle);
		int[] puzzle = new int[marimePuzzle * marimePuzzle];
		for (int i = 0; i < marimePuzzle * marimePuzzle; i++)
			puzzle[i] = sc.nextInt();

		Stare stareInitiala = new Stare(marimePuzzle, puzzle);
		Stare stareFinala = new Stare(marimePuzzle);
		System.out.println("StareInitiala: " + stareInitiala.toString());
		System.out.println("StareFinala: " + stareFinala.toString());

		String solutie = null;
		if (util.Util.isSolvable(stareInitiala.getStateArray())) {
			solutie = util.Util.cautareInArbore(stareInitiala, stareFinala);
			System.out.println(solutie);
		} else
			System.out.println("nu se poate rezolva");
		
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output1.txt"), "utf-8"))) {
			writer.write(solutie);
		}
		long endTime = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
	}
}
