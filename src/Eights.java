
import java.util.Scanner;
/**
 * 
 * @author Tomasz Mi?
 * @version 1.0
 * Main class which provides essential methods to play a Eights.
 */
public class Eights {
	/**
	 * Variable which store each player in game.
	 */
	private Players players;
	/**
	 * Store pile of cards from which players get card in case that don't have proper card in his pile.
	 */
	private Hand drawPile;
	/**
	 * Store pile of cards which was throw by players, last card on pile determine next move.
	 */
	private Hand discardPile;
	/**
	 * Thanks <code>in</code> program read number of players and in simulation read next move (Enter).
	 */
	private Scanner in;
	
	/**
	 * Constructor <code>Eights</code> create deck for each player and draw pile and discard pile.
	 */
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
		
		drawPile = new Hand("Stos ci?gni?cia");
		deck.dealAll(drawPile);
		
		in = new Scanner(System.in);
	}
	public Eights(Player player, int numberOfPlayers){
		Deck deck = new Deck("Talia");
		deck.shuffle();
		players = new Players("players");
		int handSize = 5;
		players.addPlayer(player);
		deck.deal(player.getHand(), handSize);
		for(int i = 1; i < numberOfPlayers; i++){
			Player computer = new Player("player_" + (i+1));
			players.addPlayer(computer);
			deck.deal(players.getPlayer(i).getHand(), handSize);
		}

		discardPile = new Hand("Wyrzucone");
		deck.deal(discardPile, 1);

		drawPile = new Hand("Stos ci?gni?cia");
		deck.dealAll(drawPile);

		in = new Scanner(System.in);
	}

	/*
	 * Get from user count of players, if number from user will be less then two, return 2, else return count;
	 */
	public int setNumberOfPlayers() {
		in = new Scanner(System.in);
		System.out.println("Podaj liczbê graczy");
		int Number = in.nextInt();
		return (Number < 2) ? 2 : Number;
	}
	/**
	 * Decide if the game was ended.
	 */
	public boolean isDone() {
		for(int i = 0; i < players.size(); ++i) {
			if(players.getPlayer(i).getHand().empty()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * If draw pile is empty, cards from discard pile will be moved to draw pile.
	 */
	public void reshuffle() {
		Card prev = discardPile.popCard();
		discardPile.dealAll(drawPile);
		discardPile.addCard(prev);
		drawPile.shuffle();
	}
	/**
	 * Remove card draw pile.
	 * @return Card from draw pile.
	 */
	public Card draw() {
		if(drawPile.empty()) {
			reshuffle();
		}
		return drawPile.popCard();
	}
	
	
	/**
	 * Switch player(threads) in game.
	 * @param current - player who last push card on discard pile.
	 * @return - next player
	 */
	synchronized public Player nextPlayer(Player current) {
		
		int currentIndex = players.getIndex(current);
		if(currentIndex == players.size() - 1) {
			return players.getPlayer(0);
		}else {
			return players.getPlayer(currentIndex + 1);
		}
	}
	/**
	 * Display state each player in console.
	 */
	public void displayState() {
		for(int i = 0; i < players.size(); ++i) {
			players.getPlayer(i).display();
		}
		
		discardPile.display();
		
		System.out.println("Stos ciagniecia:");
		System.out.println(drawPile.size() + " kart");
	}
	/**
	 * Wait for <code>Enter</code> in console.
	 */
	public void waitForUser() {
		in.nextLine();
	}
	/**
	 * Turn current player, bring {@link #players.play}
	 * @param player = current player
	 */
	synchronized public void takeTurn(Player player) {
		if(players.getIndex(player) == 0){
			try{
				Card prev = discardPile.last();

				Card next = player.playOwn(this, prev);

				discardPile.addCard(next);

				System.out.println(player.getName() + " gra " + next);
				System.out.println();
			} catch (Exception ie) {
				System.out.println("Cos poszlo nie tak");
			}
		}
		else {
			Card prev = discardPile.last();
			Card next = player.play(this, prev);
			discardPile.addCard(next);

			System.out.println(player.getName() + " gra " + next);
			System.out.println();
		}
	}
	/**
	 * Main method in which play a Game.
	 */
	public void playGame() {
		Player player = players.getPlayer(0);
		
		while(!isDone()){
			displayState();
			//waitForUser();
			takeTurn(player);
			player = nextPlayer(player);
		}
		
		for(int i = 0; i < players.size(); ++i) {
			players.getPlayer(i).displayScore();
		}
		
	}
	
	
}
