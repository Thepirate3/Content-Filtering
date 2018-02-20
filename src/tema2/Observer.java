//package tema2;

/**
 * Clasa Observer descrie tipul de observator al feed-lui. Acesta are un id,
 *  pastreaza un stock in care sunt puse datele filtrate de filtrul asociat observatorului
 *  si are arbore de decizie, ce contine filtrul.
 * 
 * @author Mocanu Alexandru
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Observer {

	int id;
	private Feed subiect;
	private DecisionNode root;
	private Map<String, Content> stock = new TreeMap<String, Content>();

	public Observer(Feed subiect, List<DecisionNode> rpnNodes, int id) {
		this.subiect = subiect;
		this.root = getFilter(rpnNodes);
		this.id = id;
		this.subiect.attach(this);

	}

	private DecisionNode getFilter(List<DecisionNode> rpnNodes) {

		List<DecisionNode> stack = new ArrayList<DecisionNode>();

		if (rpnNodes.get(0).data.equals("nil")) {
			return rpnNodes.get(0);
		}

		while (rpnNodes.size() != 0) {

			if (rpnNodes.get(0).data.equals("||") 
					|| rpnNodes.get(0).data.equals("&&")) {

				rpnNodes.get(0).left = stack.get(stack.size() - 1);
				stack.remove(stack.size() - 1);
				rpnNodes.get(0).right = stack.get(stack.size() - 1);
				stack.remove(stack.size() - 1);
				stack.add(rpnNodes.get(0));
				rpnNodes.remove(0);
			} else {
				stack.add(rpnNodes.get(0));
				rpnNodes.remove(0);
			}
		}
		return stack.get(stack.size() - 1);
	}

	public boolean checkTree(String name, float value) {

		return root.accept(new TreeVisistor(), name, value);
	}

	public void updateAll() {

		if (root.data.equals("nil")) {
			for (String str : subiect.dataTable.keySet()) {
				if (!stock.containsKey(str)) {
					Content aux = new Content();
					aux.value = subiect.dataTable.get(str);
					aux.numberOfChanges = 0;
					aux.forPrint = true;
					stock.put(str, aux);
				} else {
					((Content) stock.get(str)).forPrint = true;
					((Content) stock.get(str)).value = subiect.dataTable.get(str);

				}
			}
			return;
		}
		for (String str : subiect.dataTable.keySet()) {
			if (checkTree(str, subiect.dataTable.get(str))) {
				if (!stock.containsKey(str)) {
					Content aux = new Content();
					aux.value = subiect.dataTable.get(str);
					aux.numberOfChanges = 0;
					aux.forPrint = true;
					stock.put(str, aux);
				} else {
					float aux = ((Content) stock.get(str)).value;
					if (aux != subiect.dataTable.get(str)) {
						((Content) stock.get(str)).forPrint = true;
						((Content) stock.get(str)).value = subiect.dataTable.get(str);
					}
				}
			} else {

				if (stock.containsKey(str)) {
					((Content) stock.get(str)).forPrint = false;
					((Content) stock.get(str)).value = 0.000000f;

				} else {
					Content aux = new Content();
					aux.value = 0.00000f;
					aux.numberOfChanges = 0;
					aux.forPrint = false;
					stock.put(str, aux);
				}
			}
		}
	}

	public void update(String name, float value) {
		if (root.data.equals("nil")) {

			if (!stock.containsKey(name)) {
				Content aux = new Content();
				aux.value = subiect.dataTable.get(name);
				aux.numberOfChanges++;
				aux.forPrint = true;
				stock.put(name, aux);
			} else {
				if (((Content) stock.get(name)).value != value) {
					((Content) stock.get(name)).numberOfChanges++;
					((Content) stock.get(name)).forPrint = true;
					((Content) stock.get(name)).value = value;
				}
			}

			return;
		}
		if (checkTree(name, value)) {
			if (!stock.containsKey(name)) {
				Content aux = new Content();
				aux.value = value;
				aux.numberOfChanges++;
				aux.forPrint = true;
				stock.put(name, aux);
			} else {
				float aux = ((Content) stock.get(name)).value;
				if (aux != value) {
					((Content) stock.get(name)).numberOfChanges++;
					((Content) stock.get(name)).forPrint = true;
					((Content) stock.get(name)).value = value;
				}
			}
		} else {

			if (stock.containsKey(name)) {
				((Content) stock.get(name)).numberOfChanges++;
				((Content) stock.get(name)).forPrint = false;
				((Content) stock.get(name)).value = 0.000000f;

			} else {
				Content aux = new Content();
				aux.value = 0.00000f;
				aux.numberOfChanges++;
				aux.forPrint = false;
				stock.put(name, aux);
			}
		}

	}

	public void print() {
		float lastValue = 0f;
		float currentValue = 0f;
		float increase = 0.00f;
		
		updateAll();
		
		for (String str : stock.keySet()) {
			if (stock.get(str).forPrint) {
				
				lastValue = stock.get(str).lastPrintedValue;
				currentValue = stock.get(str).value;
				increase = 0.00f;
				if (lastValue != 0) {
					increase = currentValue - lastValue;
					increase *= 100;
					increase /= lastValue;
				}
				System.out.print( "obs " + this.id + ": " ); 
				System.out.print(str + " " + currentValue + " "); 
				System.out.print(String.format("%.2f", increase) + "% ");
				System.out.print(stock.get(str).numberOfChanges);
				System.out.println();
				stock.get(str).lastPrintedValue = currentValue;
				stock.get(str).numberOfChanges = 0;

			}
		}
	}

	public void deleteObserver() {
		subiect.deleteObs(this);
	}

}
