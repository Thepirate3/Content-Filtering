//package tema2;

/**
 * Clasa ce implementeaza Visitor pentru rezolvarea arborelui de decizie.
 * 
 * @author Mocanu Alexandru
 *
 */
public class TreeVisistor implements Visitor {

	public boolean visit(OperatorNode node, String name, float value) {

		if (node.data.equals("||")) {
			return (node.left.accept(this, name, value) || node.right.accept(this, name, value));
		} else {

			return (node.left.accept(this, name, value) && node.right.accept(this, name, value));
		}
	}

	public boolean visit(OperandNode node, String name, float value) {

		switch (node.data) {

		case "le":

			if (value <= ((OperandNode) node).value) {

				return true;
			}
			break;

		case "ge":
			if (value >= ((OperandNode) node).value) {
				return true;
			}
			break;
		case "lt":
			if (value < ((OperandNode) node).value) {
				return true;
			}
			break;
		case "gt":
			if (value > ((OperandNode) node).value) {
				return true;
			}
			break;
		case "eq":

			if (((OperandNode) node).nameOrNumber.equals("name")) {
				if (name.equals(((OperandNode) node).valueName)) {
					return true;
				}
			} else if (value == ((OperandNode) node).value) {
				return true;
			}

			break;
		case "ne":
			if (((OperandNode) node).nameOrNumber.equals("name")) {
				if (!name.equals(((OperandNode) node).valueName))
					return true;
			} else {
				if (value != ((OperandNode) node).value)
					return true;
			}

			break;
		default:
			break;
		}
		return false;
	}

}
