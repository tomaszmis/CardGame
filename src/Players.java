import java.util.ArrayList;

public class Players extends Player {
	
	protected ArrayList<Player> players;

	public Players(String name) {
		super(name);
		this.players = new ArrayList<Player>();
	}

	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public Player getPlayer(int i) {
		return players.get(i);
	}
	
	public int size() {
		return players.size();
	}
	
	public boolean equals(Player player) {
		if(this.getName() == player.getName()) {
			return true;
		} else return false;
	}
	
	public int getIndex(Player player) {
		for(int i = 0; i < size(); ++i) {
			if(player.equals(players.get(i))) {
				return i;
			}
		}
		return -1;
	}
}