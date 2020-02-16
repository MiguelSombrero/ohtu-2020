package ohtu.kivipaperisakset;

public class KPSTekoaly extends KPSTemplate {

    private AI tekoaly;
    
    public KPSTekoaly(AI tekoaly) {
    	this.tekoaly = tekoaly;
    }
    
	@Override
	protected String siirra(String eka) {
        String toka = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + toka);
        tekoaly.asetaSiirto(eka);
        return toka;
	}

    
}