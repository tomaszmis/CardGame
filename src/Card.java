/**
 * 
 * @author Tomasz Miœ
 * @version 1.0.1
 * 
 * Class represent all single card.
 */
public class Card {
	/**
	 * Card's rank.
	 */
	private final int rank;
	/**
	 * Card's suit
	 */
	private final int suit;
	
	/**
	 * This array represent all exist ranks, to easy use first rank that is <code>2</code> have index <code>1</code>.
	 */
	public static final String[] RANKS = {null, "2", "3", "4", "5", "6", "7", "8", "9", "10", "walet", "dama", "król", "as"};
	/**
	 * This array represent all exist suits.
	 */
	public static final String[] SUITS = {"trefl", "karo", "kier", "pik"};
	
	/**
	 * Constructor which take rank and suit.
	 * @param rank - rank of card
	 * @param suit - suit of card
	 */
	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}
	/**
	 * Override method which return String
	 */
	public String toString() {
		return RANKS[this.rank] + SUITS[this.suit];
	}
	
	/**
	 * Override method which compare two cards, and decide if are equal.
	 * @param that - card to which is compared.
	 * @return - true if card is equal, false if cards are different.
	 */
	public boolean equals(Card that) {
		return this.rank == that.rank && this.suit == that.suit;
	}
	/**
	 * Method to compare cards and decide which is stronger. 
	 * @param that
	 * @return if <code>This</code> is "stronger" return <code>1</code> other case <code>-1</code>.
	 */
	public int compareTo(Card that) {
		if(this.suit < that.suit) {
			return -1;
		}
		if(this.suit > that.suit) {
			return 1;
		}
		if(this.rank < that.rank) {
			return -1;
		}
		if(this.rank > that.rank) {
			return 1;
		}
		return 0;
	}
	/**
	 * Getter which
	 * @return rank of card
	 */
	public int getRank() {
		return this.rank;
	}
	/**
	 * Getter which
	 * @return suit of card
	 */
	public int getSuit() {
		return this.suit;
	}
}