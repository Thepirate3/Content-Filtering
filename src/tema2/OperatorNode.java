//package tema2;
/**
 * Clasa ce extinde clasa DecisionNode si care memoreaza tipul de operator.
 * @author Mocanu Alexandru
 *
 */
public class OperatorNode extends DecisionNode implements Visitable {

	public OperatorNode(String str) {
		this.data = str;
	}

	public boolean accept(Visitor visitor, String name, float value) {
		return visitor.visit(this, name, value);
	}
}
