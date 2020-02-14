package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPSTemplate {

    private AI tekoaly;
    
    public KPSTekoaly(AI tekoaly) {
    	this.tekoaly = tekoaly;
    }
    
	@Override
	protected void siirra(String eka, String toka) {
		System.out.print("Ensimmäisen pelaajan siirto: ");
        eka = scanner.nextLine();
        
        toka = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + toka);
        
        tekoaly.asetaSiirto(eka);
	}

    
}