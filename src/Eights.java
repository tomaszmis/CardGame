import java.util.Scanner;

public class Eights {
	private Player one;
	private Player two;
	private Hand drawPile;
	private Hand discardPile;
	private Scanner in;
	
	
	public Eights() {
		Deck deck = new Deck("Talia");
		deck.shuffle();
		
		int handSize = 5;
		one = new Player("Adam");
		deck.deal(one.getHand(), handSize);
		two = new Player("Cezary");
		deck.deal(two.getHand(), handSize);
		
		discardPile = new Hand("Wyrzucone");
		deck.deal(discardPile, 1);
		
		drawPile = new Hand("Stos ci¹gniêcia");
		deck.dealAll(drawPile);
		
		in = new Scanner(System.in);
	}
	
	public boolean isDone() {
		return one.getHand().empty() || two.getHand().empty();
	}
	
	public void reshuffle() {
		Card prev = discardPile.popCard();
		discardPile.dealAll(drawPile);
		discardPile.addCard(prev);
		drawPile.shuffle();
	}
	
	public Card draw() {
		if(drawPile.empty()) {
			reshuffle();
		}
		return drawPile.popCard();
	}
	
	public Player nextPlayer(Player current) {
		
		return (current == one) ? two : one;
	}
	
	public void displayState() {
		one.display();
		two.display();
		
		discardPile.display();
		
		System.out.println("Stos ci¹gniêcia:");
		System.out.println(drawPile.size() + " kart");
	}
	
	public void waitForUser() {
		in.nextLine();
	}
	
	public void takeTurn(Player player) {
		Card prev = discardPile.last();
		Card next = player.play(this, prev);
		discardPile.addCard(next);
		
		System.out.println(player.getName() + " gra " + next);
		System.out.println();
	}
	
	public void playGame() {
		Player player = one;
		
		while(!isDone()){
			displayState();
			waitForUser();
			takeTurn(player);
			nextPlayer(player);
		}
		
		one.displayScore();
		two.displayScore();
		
	}
	
	
}
