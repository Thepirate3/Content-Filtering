//package tema2;
/**
 * Clasa ce extinde conceptul de nod de decizie,ca fiind operand, ce contine doar o expresie.
 * @author Mocanu Alexandru
 *
 */
public class OperandNode extends DecisionNode implements Visitable {

	float value = 0;
	String valueName;
	String nameOrNumber;

	public OperandNode(String s) {
		String[] operand = s.trim().split("\\s+");
		this.data = operand[0];
		this.nameOrNumber = operand[1];
		if (operand[1].equals("name")) {
			this.valueName = operand[2];
		} else {

			this.value = Float.parseFloat(operand[2]);
		}
	}

	public boolean accept(Visitor visitor, String name, float value) {
		return visitor.visit(this, name, value);
	}

}
