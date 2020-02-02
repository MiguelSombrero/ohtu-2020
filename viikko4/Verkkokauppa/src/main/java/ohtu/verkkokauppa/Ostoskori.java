package ohtu.verkkokauppa;

import java.util.ArrayList;

public class Ostoskori {

    ArrayList<Tuote> tuotteet;

    public Ostoskori() {
        tuotteet = new ArrayList<Tuote>();
    }

    public void lisaa(Tuote t) {
        tuotteet.add(t);
    }

    public void poista(Tuote t) {
        tuotteet.remove(t);
    }
    
    public boolean sisaltaa(Tuote t) {
    	return tuotteet.contains(t);
    }

    public int hinta() {
        int hinta = 0;

        for (Tuote tuote : tuotteet) {
            hinta += tuote.getHinta();
        }

        return hinta;
    }
}
