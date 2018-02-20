//package tema2;

/**
 * Clasa ce creeaza noduri de decizie in functie de inputul primit.
 * @author Mocanu Alexandru
 *
 */
public class OperatorsFactory {

	private static OperatorsFactory opfactory = null;

	public DecisionNode getOperators(String str) {

		if (str.equals("||") || str.equals("&&") || str.equals("nil")) {
			return new OperatorNode(str);
		} else {
			return new OperandNode(str);
		}
	}

	public static OperatorsFactory getOperatorsFactory() {
		if (opfactory == null) {
			opfactory = new OperatorsFactory();
		}
		return opfactory;
	}
}
