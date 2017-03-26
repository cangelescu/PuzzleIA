/**
 * 
 */
package model;

import util.Util;

/**
 * @author Cristian Angelescu
 *
 */

public class Node {

	public enum Actiune {
		LEFT, RIGHT, UP, DOWN
	}

	private Stare stare;
	private Node parinte;
	private Actiune actiune;
	private int cost;
	public int depth;
	public int heuristic;

	public Node() {
	}

	public Node(Stare stare) {
		this.stare = stare;
	}

	/**
	 * Copiaza nodul succesor
	 * 
	 * @param nod
	 * @param act
	 */
	public Node(Node nod, Actiune act) {
		int sizePuzzle = (int) Math.sqrt(nod.getStare().getStateArray().length);
		Stare s = new Stare();
		switch (act) {
		case UP:
			//if (zeroPosition >= sizePuzzle){
			s.setStateArray(Util.moveUp(nod.getStare().getStateArray(), sizePuzzle));
			s.setN(sizePuzzle);
			this.stare = s;
			this.actiune = Actiune.UP;
			this.cost = nod.getCost() + 1;
			this.parinte = nod;
			//}
			break;
		case RIGHT:
			//if ((zeroPosition + 1) % sizePuzzle != 0){
			s.setStateArray(Util.moveRight(nod.getStare().getStateArray(), sizePuzzle));
			s.setN(sizePuzzle);
			this.stare = s;
			this.actiune = Actiune.RIGHT;
			this.cost = nod.getCost() + 1;
			this.parinte = nod;
			//}
			break;
		case LEFT:
			//if ((zeroPosition % sizePuzzle) != 0){
			s.setStateArray(Util.moveLeft(nod.getStare().getStateArray(), sizePuzzle));
			s.setN(sizePuzzle);
			this.stare = s;
			this.actiune = Actiune.LEFT;
			this.cost = nod.getCost() + 1;
			this.parinte = nod;
			//}
			break;
		case DOWN:
			//if (zeroPosition % sizePuzzle < sizePuzzle - 1 && zeroPosition + sizePuzzle < sizePuzzle * sizePuzzle){
			s.setStateArray(Util.moveDown(nod.getStare().getStateArray(), sizePuzzle));
			s.setN(sizePuzzle);
			this.stare = s;
			this.actiune =Actiune.DOWN;
			this.cost = nod.getCost() + 1;
			this.parinte =nod; 
			//}
			break;
		}
	}

	public Stare getStare() {
		return stare;
	}

	public void setStare(Stare stare) {
		this.stare = stare;
	}

	public Node getParinte() {
			return parinte;
	}

	public void setParinte(Node parinte) {
		this.parinte = parinte;
	}

	public Actiune getActiune() {
		return actiune;
	}

	public void setActiune(Actiune actiune) {
		this.actiune = actiune;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Node [stare=" + stare + ", parinte=" + parinte + ", actiune=" + actiune + ", cost=" + cost + "]";
	}

}
