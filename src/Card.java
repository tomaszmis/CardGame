
public class Card {
	private int rank;
	private int suit;
	
	public static final String[] RANKS = {null, "2", "3", "4", "5", "6", "7", "8", "9", "10", "walet", "dama", "król", "as"};
	public static final String[] SUITS = {"trefl", "karo", "kier", "pik"};
	
	public Card(int rank, int suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public String toString() {
		return RANKS[this.rank] + SUITS[this.suit];
	}
	
	public boolean equals(Card that) {
		return this.rank == that.rank && this.suit == that.suit;
	}
	
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
	
	public int getRank() {
		return this.rank;
	}
	
	public int getSuit() {
		return this.suit;
	}
	
	public static int search(Card[] cards, Card target) {
		for(int i = 0; i < cards.length; i++) {
			if(cards[i].equals(target)) {
				return i;
			}
		}
		return -1;
	}
	
	public Card[] makeDeck() {
		Card[] cards = new Card[52];
		int index = 0;
		for(int suit = 0; suit <= 3; ++suit) {
			for(int rank = 1; rank <= 13; ++rank) {
				cards[index] = new Card(rank,suit);
			}
		}
		return cards;
	}
	public int[] suitHist(Card[] deck) {
		int[] suitHist = new int [4];
		for(Card c : deck) {
			suitHist[c.suit]++;
			}
		return suitHist;
		}
	public boolean hasFlush(Card[] deck) {
		int[] suitHist = suitHist(deck);
		for(int i: suitHist) {
			if(i >= 5) {
				return true;
			}
		}
		return false;
	}
}
