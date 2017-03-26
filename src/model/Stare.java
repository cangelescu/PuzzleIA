/**
 * 
 */
package model;

import java.util.Arrays;

/**
 * @author Cristian Angelescu
 *
 */
public class Stare {

	private int n;
	/**
	 * Starea puzzle-ului retinuta intr-un sir
	 */
	private int stateArray[];

	public Stare() {
	}

	/**
	 * Genereaza starea finala a puzzle-ului
	 * 
	 * @param sizePuzzle
	 *            - marimea puzzle-ului
	 */
	public Stare(int sizePuzzle) {
		stateArray = new int[sizePuzzle * sizePuzzle];
		n = sizePuzzle;
		for (int i = 0; i < sizePuzzle * sizePuzzle; i++)
			stateArray[i] = i + 1;
		stateArray[sizePuzzle * sizePuzzle - 1] = 0;
	}

	public Stare(int stateArray[]) {
		this.stateArray = stateArray;
	}

	public Stare(int n, int stateArray[]) {
		this.n = n;
		this.stateArray = stateArray;
	}

	public int[] getStateArray() {
		return stateArray;
	}

	public void setStateArray(int[] stateArray) {
		this.stateArray = stateArray;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	@Override
	public String toString() {
		return "Stare [n=" + n + ", stateArray=" + Arrays.toString(stateArray) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + n;
		result = prime * result + Arrays.hashCode(stateArray);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stare other = (Stare) obj;
		if (n != other.n)
			return false;
		if (!Arrays.equals(stateArray, other.stateArray))
			return false;
		return true;
	}
}
