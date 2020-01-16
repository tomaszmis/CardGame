package model;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Tomasz Mi�
 * @version 1.0
 * Class represent card collection.
 */
public class CardCollection{
	/**
	 * Label of collection.
	 */
	private String label;
	/**
	 * List include cards of collection.
	 */
	protected ArrayList<Card> cards;
	
	
	/**
	 * Constructor which create empty collection.
	 * @param label - label of new collection.
	 */
	public CardCollection(String label) {
		this.label = label;
		this.cards = new ArrayList<Card>();
	}
	/**
	 * Method add new card to collection.
	 * @param card - which is being added to collection
	 */
	public void addCard(Card card) {
		cards.add(card);
	}
	/**
	 * Method removed card from collection.
	 * @param i - Index of removed card.
	 * @return - removed card.
	 */
	public Card popCard(int i) {
		return cards.remove(i);
	}
	/**
	 * Method removed last card in collection.
	 * @return - removed card
	 */
	public Card popCard() {
		int i = size() - 1;
		return popCard(i);
	}
	/**
	 * Method which return count of cards in collection.
	 * @return count of cards in collection
	 */
	public int size() {
		return cards.size();
	}
	/**
	 * Supported method.
	 * @return <code>true</code> if collection is empty in other case <code>false</code>
	 */
	public boolean empty() {
		return cards.size() == 0;
	}
	/**
	 * This method removed first <code>n</code> from <code>this</code> collection and add these cards to <code>that</code> collection.  
	 * @param that collection to which cards being added.
	 * @param n count of first n card which are removed from this collection and added to that collection.
	 */
	public void deal(CardCollection that, int n) {
		for(int i = 0; i < n; ++i) {
			Card card = popCard();
			that.addCard(card);
		}
	}
	/**
	 * Getter which return card which index is <code>i</code>.
	 * @param i - index of returned card.
	 * @return - Card object.
	 */
	public Card getCard(int i) {
		return cards.get(i);
	}
	/**
	 * Getter last's card from collection.
	 * @return Card object.
	 */
	public Card last() {
		return cards.get(size() - 1);
	}

	/**
	 * Method which return String object which is label of collection.
	 * @return Label of this collection.
	 */
	public String getLable() {
		return label;
	}
	/**
	 * This method move all card from <code>this</code> to <code>that</code> collection.
	 * @param that collection to which cards are moved.
	 */
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