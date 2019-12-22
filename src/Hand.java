import java.io.ObjectOutputStream;

public class Hand extends CardCollection {

	private ObjectOutputStream output;
	public Hand(String label, ObjectOutputStream output) {
		super(label);
		this.output = output;

	}

//	public Hand(String label) { super(label); }
	
	public void display() throws Exception {
		output.writeObject(getLable() + ": ");
		for(int i = 0; i < size(); ++i) {
			output.writeObject(i + " - " + getCard(i) + " ");
		}

	}
	
	

}
