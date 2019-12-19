
public class Hand extends CardCollection {
	
	public Hand(String label) {
		super(label);
	}
	
	public void display() {
		System.out.println(getLable() + ": ");
		for(int i = 0; i < size(); ++i) {
			System.out.print(i + " - " + getCard(i) + " ");
		}
		System.out.println();
	}
	
	

}
