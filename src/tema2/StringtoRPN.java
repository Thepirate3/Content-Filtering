//package tema2;

import java.util.LinkedList;
import java.util.List;

/**
 * Clasa auxiliara pentru definirea metodelor auxiliare de conversie a expreisie
 * parantezate in reverse polish notation(rpn).
 * 
 * @author Mocanu Alexandru
 *
 */
public class StringtoRPN {

	private static int nextChar(String str, int pos) {
		while (str.charAt(pos) != '(') {
			if (str.charAt(pos) > 96) {
				return 1;
			}
			pos++;
		}
		return 0;
	}

	public static List<String> convertRPN(String str) {

		List<String> coada = new LinkedList<String>();
		List<String> aux = new LinkedList<String>();

		if (str.equals("nil")) {
			coada.add("nil");
			return coada;
		} else {
			int capat = 0;
			int end = 0;
			for (int i = 0; i < str.length(); i++) {

				if (str.charAt(i) == '(' && nextChar(str, i + 1) == 0) {
					aux.add(str.substring(i, i + 1));
					continue;
				}
				if (str.charAt(i) == '(' && nextChar(str, i + 1) != 0) {
					capat = i;

					for (; i < str.length(); i++) {
						if (str.charAt(i) == ')') {
							end = i;
							i++;
							break;
						}
					}
					coada.add(str.substring(capat + 1, end));
				}
				if (i == str.length() - 1 && str.charAt(i) == ' ') {
					while (aux.size() != 0) {
						String scoate = aux.get(aux.size() - 1);
						coada.add(scoate);
						aux.remove(aux.size() - 1);
					}
					break;
				}
				if (i == str.length()) {
					while (aux.size() != 0) {
						String scoate = aux.get(aux.size() - 1);
						coada.add(scoate);
						aux.remove(aux.size() - 1);
					}
					break;
				}

				if (str.charAt(i) == '|' || str.charAt(i) == '&') {
					aux.add(str.substring(i, i + 2));
					i++;
					continue;
				}

				if (str.charAt(i) == ')') {
					String scoate = aux.get(aux.size() - 1);
					while (!scoate.equals("(")) {

						coada.add(scoate);
						aux.remove(aux.size() - 1);
						scoate = aux.get(aux.size() - 1);
					}
					aux.remove(aux.size() - 1);
				}
			}

			return coada;
		}
	}
}
