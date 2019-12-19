import java.io.PrintWriter;

public class Hand extends CardCollection {

	private PrintWriter output;
	public Hand(String label, PrintWriter output) {
		super(label);
		this.output = output;

	}

//	public Hand(String label) { super(label); }
	
	public void display() {
		output.println(getLable() + ": ");
		for(int i = 0; i < size(); ++i) {
			output.print(i + " - " + getCard(i) + " ");
		}
		output.println();
	}
	
	

}
