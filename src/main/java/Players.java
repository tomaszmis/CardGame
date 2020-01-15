
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 *
 * @author Tomasz Miœ
 * @version 1.0
 * Store information about few players
 */
public class Players extends Player {
	/**
	 * List of players.
	 */
	protected ArrayList<Player> players;
	/**
	 * Create list of players
	 * @param name new player
	 */
	public Players(String name, PrintWriter output) {
		super(name,output);
		this.players = new ArrayList<Player>();
	}
	/**
	 * Add new player  to list
	 * @param player who will be add to list
	 */
	public void addPlayer(Player player) {
		players.add(player);
	}
	/**
	 * Getter returned player which index is <code>i</code>
	 * @param i - index of player in list
	 * @return player from list
	 */
	public Player getPlayer(int i) {
		return players.get(i);
	}
	/**
	 *
	 * @return number of players
	 */
	public int size() {
		return players.size();
	}
	/**
	 * Override method 
	 * @param player
	 * @return true if it is this same player in other case false
	 */
	public boolean equals(Player player) {
		if(this.getName() == player.getName()) {
			return true;
		} else return false;
	}
	/**
	 * Return index of player.
	 * @param player
	 * @return index of param's player
	 */
	public int getIndex(Player player) {
		for(int i = 0; i < size(); ++i) {
			if(player.equals(players.get(i))) {
				return i;
			}
		}
		return -1;
	}
}