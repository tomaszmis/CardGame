
import java.util.Scanner;
/**
 * 
 * @author Tomasz Miœ
 * @version 1.0
 *
 */
public class Eights {
	private Players players;
	private Hand drawPile;
	private Hand discardPile;
	private Scanner in;
	
	
	public Eights() {
		Deck deck = new Deck("Talia");
		deck.shuffle();
		players = new Players("players");
		int handSize = 5;
		int numberOfPlayers = setNumberOfPlayers();
		for(int i = 0; i < numberOfPlayers; ++i) {
			Player player = new Player("player_" + (i + 1));
			players.addPlayer(player);
			deck.deal(players.getPlayer(i).getHand(), handSize);
		}
		
		discardPile = new Hand("Wyrzucone");
		deck.deal(discardPile, 1);
		
		drawPile = new Hand("Stos ci¹gniêcia");
		deck.dealAll(drawPile);
		
		in = new Scanner(System.in);
	}
	/*
	 * Get from user count of players, if number will be less then two, return 2, else return count;
	 */
	public int setNumberOfPlayers() {
		in = new Scanner(System.in);
		System.out.println("Podaj liczbê graczy");
		int Number = in.nextInt();
		return (Number < 2) ? 2 : Number;
	}
	
	public boolean isDone() {
		for(int i = 0; i < players.size(); ++i) {
			if(players.getPlayer(i).getHand().empty()) {
				return true;
			}
		}
		return false;
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
		
		int currentIndex = players.getIndex(current);
		if(currentIndex == players.size() - 1) {
			return players.getPlayer(0);
		}else {
			return players.getPlayer(currentIndex + 1);
		}
	}
	
	public void displayState() {
		for(int i = 0; i < players.size(); ++i) {
			players.getPlayer(i).display();
		}
		
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
		Player player = players.getPlayer(0);
		
		while(!isDone()){
			displayState();
			waitForUser();
			takeTurn(player);
			player = nextPlayer(player);
		}
		
		for(int i = 0; i < players.size(); ++i) {
			players.getPlayer(i).displayScore();
		}
		
	}
	
	
}
