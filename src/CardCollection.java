import java.util.ArrayList;
import java.util.Random;


public class CardCollection{
	private String label;
	protected ArrayList<Card> cards;
	
	
	
	public CardCollection(String label) {
		this.label = label;
		this.cards = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public Card popCard(int i) {
		return cards.remove(i);
	}
	
	public Card popCard() {
		int i = size() - 1;
		return popCard(i);
	}
	
	public int size() {
		return cards.size();
	}
	
	public boolean empty() {
		return cards.size() == 0;
	}
	
	public void deal(CardCollection that, int n) {
		for(int i = 0; i < n; ++i) {
			Card card = popCard();
			that.addCard(card);
		}
	}
	
	public Card getCard(int i) {
		return cards.get(i);
	}
	
	public Card last() {
		return cards.get(size() - 1);
	}


	public String getLable() {
		return label;
	}

	public void dealAll(CardCollection that) {
		int n = size();
		deal(that, n);
	}
	

	/*
	 * Void method which swap two cards with indexes:
	 * @param firstIndex
	 * @param secondIndex
	 */
	void swapCards(int firstIndex, int secondIndex) {
		Card temp = cards.get(firstIndex);
		cards.set(firstIndex, cards.get(secondIndex)); 
		cards.set(secondIndex,temp);
	}
	

	
	/*
	 * It shuffle the deck.
	 */
	public void shuffle() {
		for(int i = 0; i < cards.size(); ++i) {
			Random generator = new Random();
			int j = generator.nextInt(size() - 1); 
			swapCards(i, j);
		}
	}
}