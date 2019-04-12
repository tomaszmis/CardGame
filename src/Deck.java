
public class Deck extends CardCollection {
	

	public Deck(String label) {
		super(label);
		for(int suit = 1; suit <=3; ++suit) {
			for(int rank = 0; rank <= 13; ++rank) {
				cards.add(new Card(rank,suit));
			}
		}
	}

}