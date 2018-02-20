//package tema2;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MainClass {

	public static void main(String[] args) {

		ObserverFactory obsFactory = ObserverFactory.getObsFactory();
		Map<Integer, Observer> observers = new TreeMap<Integer, Observer>();
		Feed subject = Feed.getFeed();
		obsFactory.subiect = subject;
		Scanner scan = new Scanner(System.in);
		String buffer = scan.nextLine();
		char firstChar = 0;

		while (!buffer.equals("begin")) {
			buffer = scan.nextLine();
		}

		buffer = scan.nextLine();

		while (!buffer.equals("end")) {
			firstChar = buffer.charAt(0);

			switch (firstChar) {

			case 'p':

				String[] vPrint = buffer.split(" ");
				int aux = Integer.parseInt(vPrint[1]);
				observers.get(aux).print();
				break;

			case 'f':
				String[] vFeed = buffer.split(" ");
				subject.updateFeed(vFeed[1], Float.parseFloat(vFeed[2]));
				break;

			case 'd':
				String[] vDelete = buffer.split(" ");
				int key = Integer.parseInt(vDelete[1]);
				observers.get(key).deleteObserver();
				observers.remove(key);
				break;

			case 'c':
				if (buffer.contains("nil")) {
					String[] vCreate = buffer.split(" ");
					int aux1 = Integer.parseInt(vCreate[1]);
					observers.put(aux1, obsFactory.getObserver("nil", aux1));
				} else {

					int pos1 = buffer.indexOf(' ') + 1;
					int pos2 = buffer.indexOf('(') - 1;
					int lastpos = buffer.length();
					int aux2 = Integer.parseInt(buffer.substring(pos1, pos2));
					observers.put(aux2, obsFactory.getObserver(buffer.substring(pos2 + 1, lastpos), aux2));
				}
				break;

			default:
				break;
			}
			buffer = scan.nextLine();
		}
		scan.close();
	}
}
