import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrazyMatching {

	public static void main(String[] args) {
		//Spot It size list: 2, 3, 4, 6, 8, 12, ......
		int cardSize = 8;

		CrazyMatching cm = new CrazyMatching();
		List<Card> cards = cm.createCards(cardSize);
		if (cm.checkSpotit(cards)) {
			cm.printCards(cards);
		    System.out.println("Cards : " + cards.size());
			cm.statCards(cards);
			System.out.println("Spot It !");
		} else {
			System.out.println("AHoooo !");
			List<Card> clean = cm.cleanDup(cards);
			cm.printCards(clean);
			cm.statCards(clean);
			System.out.println("Cards : " + clean.size() + ", Size : " + cardSize);
		}

	}

	public void statCards(List<Card> cards) {
		if (cards==null||cards.isEmpty()) return;
		Map<Integer, Integer> items = new HashMap<Integer, Integer>();
		int size = cards.get(0).getSize();
		for (int i=0;i<cards.size();i++) {
			for (int j=0;j<size;j++) {
				int n = cards.get(i).getNumber(j);
				Integer t = items.get(n);
				if (t==null) {
					items.put(n, 1);
				} else {
					items.put(n, t+1);
				}
			}
		}
		
		for (Map.Entry<Integer, Integer> entry : items.entrySet()) {
			System.out.println("Number : " + entry.getKey() + ", Count: " + entry.getValue());
		}
		System.out.println("Items count: " + items.size());
	}

	public boolean checkMatch(Card card0, Card card1) {
		int match = 0;
		for (int i=0;i<card0.getLength();i++) {
			int n = card0.getNumber(i);
			if (card1.findNumber(n)) {
				match++;
				continue;
			}
		}
		return (match==1)?true:false;
	}

	public List<Card> cleanDup(List<Card> cardslist) {
		if (cardslist==null || cardslist.isEmpty()) return null;
		List<Card> cards = new ArrayList<Card>(cardslist);
		for (int i=0;i<cards.size();i++) {
			Card card0 = cards.get(i);
			for(int j=0;j<cards.size();j++) {
				if (i==j) continue;
				Card card1 = cards.get(j);
				if (!checkMatch(card0,card1)) {
					cards.remove(j);
					return cleanDup(cards);
				}
			}
		}
		return cards;
	}

	public boolean checkSpotit(List<Card> cards) {
		if (cards==null || cards.isEmpty()) return false;
		for (int i=0;i<cards.size();i++) {
			Card card0 = cards.get(i);
			for(int j=0;j<cards.size();j++) {
				if (i==j) continue;
				Card card1 = cards.get(j);
				if (!checkMatch(card0,card1)) {
					card0.print();
					card1.print();
					return false;
				}
			}
		}
		return true;
	}

	public List<Card> createCards(int cardsize) {
		if (cardsize < 2)
			return null;
		int startNum = 1;
		int incNumber = startNum;
		List<Card> cards = new ArrayList<Card>();
		Card card0 = new Card(cardsize);
		incNumber = card0.fillIncNumer(incNumber);
		cards.add(card0);
		int q = 0;
		for (int i = 0; i < cardsize; i++) {
			for (int n = 1; n < cardsize; n++) {
				Card card = new Card(cardsize);
				card.putNumber(card0.getNumber(i));
				if (cards.size() >= cardsize)
					for (int j = 1; j < cards.size(); j++) {
						q = (i + q - 1) % (cardsize - 1);
						int m = cards.get(j).getNumber(q+1);
						q++;
						card.putNumber(m);
						// card.print();
						if (card.isFull()) {
							cards.add(card);
							q++;
							break;
						}
					}
				if (!card.isFull()) {
					incNumber = card.fillIncNumer(incNumber);
					cards.add(card);
				}
			}
		}
		
		return cards;
	}

	public void printCards(List<Card> cards) {
		System.out.println("--------------------------------");
		if (cards == null || cards.isEmpty()) {
			return;
		}

		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).print();
		}
	}

}
