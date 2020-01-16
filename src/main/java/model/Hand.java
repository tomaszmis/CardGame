package model;
import java.io.PrintWriter;

public class Hand extends CardCollection {

	private PrintWriter output;
	public Hand(String label, PrintWriter output) {
		super(label);
		this.output = output;

	}

//	public Hand(String label) { super(label); }

	public void display() {
		if(getLable().equalsIgnoreCase("Wyrzucone")){
			output.println("Ostatnia wyrzcona karta: |" + getCard(size() - 1) +"|" );
		}else {
			String out = "";
			out = (getLable() + ": ");
			for (int i = 0; i < size(); ++i) {
				out = out + (i + " - |" + getCard(i) + "| ");
			}
			output.println(out);
		}
	}



}