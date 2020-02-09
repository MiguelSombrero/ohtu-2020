package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Operaatio extends Komento {
	protected int syote;
	protected int edellinen;
	
	public Operaatio(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
		super(tuloskentta, syotekentta, nollaa, undo, sovellus);
	}
	
	private void tulosta() {
		syotekentta.setText("");
	    tuloskentta.setText("" + sovellus.tulos());
	}
	
	@Override
	public void suorita() {
		syote = 0;
		try {
			syote = Integer.parseInt(syotekentta.getText());
		} catch (Exception e) {
			
		}
		
		edellinen = sovellus.tulos();
		laske();
		tulosta();
	}

	@Override
	public void peru() {
		sovellus.aseta(edellinen);
		tulosta();
	}
	
	protected abstract void laske();

}
