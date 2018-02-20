//package tema2;

/**
 * Clasa ce defineste structura unui nod,din arbore, aceasta fiind extinsa de
 * alte doua clase. De asemenea implemnteaza metoda din interfata Visitable
 * pentru utilizarea Visitor pattern.
 * 
 * @author Mocanu Alexandru
 *
 */
public class DecisionNode implements Visitable {

	public DecisionNode left;
	public DecisionNode right;
	public String data;

	public void addNodeL(DecisionNode node) {
		this.left = node;
	}

	public void addNodeR(DecisionNode node) {
		this.right = node;
	}
	
	@Override
	public boolean accept(Visitor visitor, String name, float value) {
		System.out.println("Neimplementat");
		return false;
	}
}
