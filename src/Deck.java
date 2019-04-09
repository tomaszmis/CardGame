import java.util.Random;
public class Deck {
	private Card[] cards;
	
	private Random generator = new Random();
	
	public static void main(String[] args) {
		Deck test = new Deck();
		
		test.print();
		test.shuffle();
		
		test.print();
		test = test.mergeSort();
		test.print();
		
	}
	public Deck(int n) {
		this.cards = new Card[n];
	}
	
	public Deck() {
		this.cards = new Card[52];
		int index = 0;
		for(int suit = 0; suit <= 3; suit++) {
			for(int rank = 1; rank <= 13; rank++) {
				this.cards[index] = new Card(rank, suit);
				index++;
			}
		}
	}
	
	public void print() {
		for(int i = 0; i < cards.length; ++i) {
			System.out.print(this.cards[i] + " ");
			
		}
		System.out.println();
	}
	/*
	 * Support method which return integer form range of low to high.
	 */
	int randomInt(int low, int high) {
		return generator.nextInt(high - low + 1) + low; 
	}
	/*
	 * Void method which swap two cards with indexes:
	 * @param firstIndex
	 * @param secondIndex
	 */
	void swapCards(int firstIndex, int secondIndex) {
		Card temp = cards[firstIndex];
		cards[firstIndex] = cards[secondIndex];
		cards[secondIndex] = temp;
	}
	/*
	 * It shuffle the deck.
	 */
	public void shuffle() {
		for(int i = 0; i < cards.length; ++i) {
			swapCards(i, randomInt(i,cards.length - 1));
		}
	}
	/*
	 * Support Method
	 * @param lowIndex low bound
	 * @param highIndex high bound
	 * @return the index of lowest card form range of lowIndex to highIndex
	 */
	int indexLowest(int lowIndex, int highIndex) {
		int lowest = 0;
		for(int i = lowIndex; i < highIndex; ++i ) {
			if(cards[lowest].compareTo(cards[i]) == 1) {
				lowest = i;
			}else {
				continue;
			}
		}
		return lowest;
	}
	
	void selectionSort() {
		int min = 0;
		for(int i = 0; i < cards.length; ++i) {
			min = i;
			for(int j = i; j < cards.length; ++j) {
				if(cards[j].compareTo(cards[min]) == -1) {
					min = j;
				}
			}
			swapCards(i,min);
		}
		}

	public Deck subDeck(int low, int high) {
		Deck sub = new Deck(high - low + 1);
		for(int i = 0; i < sub.cards.length; ++i) {
			sub.cards[i] = this.cards[low + i];
		}
		return sub;
		
	}
	
	public static Deck merge(Deck d1, Deck d2) {
		Deck result = new Deck(d1.cards.length + d2.cards.length);
		
		int i = 0;
		int j = 0;
		for(int k = 0 ; k < result.cards.length; k++) {
			if(j == d2.cards.length) {
				result.cards[k] = d1.cards[i];
				i++;
			}else if(i == d2.cards.length) {
				result.cards[k] = d2.cards[j];
				j++;
			}else {
				 if(d1.cards[i].compareTo(d2.cards[j]) == -1) {
					 result.cards[k] = d1.cards[i];
					 i++;
				 }else {
					 result.cards[k] = d2.cards[j];
					 j++;
				 }
			}
		}
		return result;
	}
	public static Deck almostMergeSort(Deck d) {
		
	Deck d1 = d.subDeck(0, d.cards.length / 2);
	Deck d2 = d.subDeck(d.cards.length / 2 + 1, d.cards.length-1);
	
	d1.selectionSort();
	d2.selectionSort();
	
	return merge(d1,d2);
	}	
	
	public Deck mergeSort() {
		if(this.cards.length == 1 || this.cards.length == 0) {
			return this;
		}else {
			int mid = this.cards.length / 2;
			
			Deck d1 = this.subDeck(0, mid);
			Deck d2 = this.subDeck(mid + 1, this.cards.length);
			
			d1 = d1.mergeSort();
			d1 = d2.mergeSort();
			
			return merge(d1,d2);
		}
	}
}
