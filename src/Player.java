/**
 * 
 * @author Tomasz Miœ
 * @version 1.0
 * Represent one player and provide methods to play in Eights.
 */
public class Player {
	/**
	 * Name of player
	 */
	private String name;
	/**
	 * Store player's card
	 */
	protected Hand hand;
	/**
	 * Create new player
	 * @param name - name of player
	 */
	public Player(String name) {
		this.name = name;
		this.hand = new Hand(name);
	}
	/**
	 * Method which finally return card to discard pile.
	 * @param eights - current game
	 * @param prev - last card on discard pile
	 * @return card from hand deck to discard pile
	 */
	public Card play(Eights eights, Card prev) {
		
		Card card = searchForEight();
		if(card == null) {
			card = searchForMatch(prev);
			if(card == null) {
				card  = drawForMatch(eights, prev);	
			}
		}
		return card;
	}
	/**
	 * Looking for card which rank is 8, if is return it else return null.
	 * @return
	 */
	public Card searchForEight() {
		for(int i = 0; i < hand.size(); ++i) {
			Card card = hand.getCard(i);
			if(card.getRank() == 8) {
				return hand.popCard(i);
			}
		}
		return null;
	}
	/**
	 * Looking for card which match to last card in discard pile id est this same rank or suit.
	 * @param prev
	 * @return
	 */
	public Card searchForMatch(Card prev) {
		for(int i = 0; i < hand.size(); ++i) {
			Card card = hand.getCard(i);
			if(cardMatches(card,prev)) {
				return hand.popCard(i);
			}
		}
		return null;
	}
	/**
	 * If player don't have match card in hand he must draw from draw pile while he don't get match card, each drawed card was add to hand cards.
	 * @param eights - current game
	 * @param prev - last card on discard pile
	 * @return - matches card
	 */
	public Card drawForMatch(Eights eights, Card prev) {
		while(true) {
			Card card = eights.draw();
			System.out.println(name + " wyci¹gn¹³ " + card);
			if(cardMatches(card, prev)) {
				return card;
			}
			hand.addCard(card);
		}
	}
	/**
	 * Decided if card matches to last card in discard pile id est have this same rank or suit or in hand is card which rank is 8.
	 * @param card1
	 * @param card2
	 * @return
	 */
	public static boolean cardMatches(Card card1, Card card2) {
		if(card1.getSuit() == card2.getSuit()) {
			return true;
		} 
		if(card1.getRank() == card2.getRank()) {
			return true;
		}
		if(card1.getRank() == 8) {
			return true;
		}
		return false;
	}
	/**
	 * Calculate point each player. 
	 * @return sum of points
	 */
	public int score() {
		int sum = 0;
		for(int i = 0; i < hand.size(); ++i) {
			Card card = hand.getCard(i);
			int rank = card.getRank();
			if(rank == 8) {
				sum =- 20;
			} else if(rank > 10) {
				sum -=10;
			}else {
				sum -= rank;
			}
		}
		return sum;
	}
	/**
	 * Write in console score.
	 */
	public void displayScore() {
		System.out.println("Wynik: " + name + " to " + score());
	}
	/**
	 * Display all information about player name, count of cards in hand and score.
	 */
	public void display() {
		System.out.println("Gracz " + name + " Liczba kart w rêce: " + hand.size() + " Wynik to " + score());
	}
	/**
	 * Getter return name of player.
	 * @return - name of player.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Getter return players cards.
	 * @return - players cards.
	 */
	public Hand getHand() {
		return hand;
	}
	
}