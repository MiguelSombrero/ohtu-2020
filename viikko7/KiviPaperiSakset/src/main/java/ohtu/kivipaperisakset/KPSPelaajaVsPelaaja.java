package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KPSTemplate {

	@Override
	protected String siirra(String eka) {
        System.out.print("Toisen pelaajan siirto: ");
        return scanner.nextLine();
	}

    
}