//package tema2;

import java.util.LinkedList;
import java.util.List;

/**
 * Clasa utilizata pentru generare de instante de tip Observer.
 * 
 * @author Mocanu Alexandru
 *
 */

public class ObserverFactory {

	private static ObserverFactory obsfactory = null;
	private OperatorsFactory opfactory = OperatorsFactory.getOperatorsFactory();
	public Feed subiect;

	public static ObserverFactory getObsFactory() {
		if (obsfactory == null) {
			obsfactory = new ObserverFactory();
		}
		return obsfactory;
	}

	public Observer getObserver(String filter, int id) {

		List<String> rpn = new LinkedList<String>();
		rpn = StringtoRPN.convertRPN(filter);
		List<DecisionNode> queue = new LinkedList<DecisionNode>();

		for (String str : rpn) {
			queue.add(opfactory.getOperators(str));
		}
		return new Observer(subiect, queue, id);
	}
}
