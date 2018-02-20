//package tema2;

public interface Visitor {
	boolean visit(OperandNode a, String name, float value);

	boolean visit(OperatorNode a, String name, float value);
}
