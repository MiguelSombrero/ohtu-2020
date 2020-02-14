package ohtu.kivipaperisakset;

public class KPSPelaajaVsPelaaja extends KPSTemplate {

	@Override
	protected void siirra(String eka, String toka) {
		System.out.print("Ensimmäisen pelaajan siirto: ");
        eka = scanner.nextLine();
        
        System.out.print("Toisen pelaajan siirto: ");
        toka = scanner.nextLine();
	}

    
}