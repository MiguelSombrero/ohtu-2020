package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Operaatio extends Komento {
	protected int syote;
	
	public Operaatio(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		super(tuloskentta, syotekentta, nollaa, undo, sovellus);
	}
	
	@Override
	public void suorita() {
		syote = 0;
		try {
			syote = Integer.parseInt(syotekentta.getText());
		} catch (Exception e) {
			
		}
		
		laske();
		int tulos = sovellus.tulos();
		syotekentta.setText("");
	    tuloskentta.setText("" + tulos);
		
	}

	@Override
	public void peru() {
		// TODO Auto-generated method stub
		
	}
	
	protected abstract void laske();

}
