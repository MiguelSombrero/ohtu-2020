
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkiot;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 
    
    public IntJoukko() {
        alustaMuuttujat(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        alustaMuuttujat(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei voi olla negatiivinen");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei voi olla negatiivinen");
        }
        alustaMuuttujat(kapasiteetti, kasvatuskoko);
    }

    private void alustaMuuttujat(int kapasiteetti, int kasvatus) {
    	alkiot = new int[kapasiteetti];
        for (int i = 0; i < alkiot.length; i++) {
            alkiot[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatus;
    }
    
    private void kasvataTaulukkoa() {
    	int[] uusiTaulukko = new int[ alkiot.length + OLETUSKASVATUS ];
		kopioiTaulukko(alkiot, uusiTaulukko);
		this.alkiot = uusiTaulukko;
    }
    
    public boolean lisaa(int luku) {
    	if (!kuuluuJoukkoon(luku)) {
    		
            alkiot[alkioidenLkm] = luku;
            alkioidenLkm++;
            
    		if (alkioidenLkm == alkiot.length) {
    			kasvataTaulukkoa();
    		}
    		
            return true;
        }
        return false;
    }

    public boolean kuuluuJoukkoon(int luku) {
    	for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkiot[i]) {
                return true;
            }
        }
    	
        return false;
    }

    private void siirraAlkioitaVasemmalle(int lahtien) {
    	for (int i = lahtien; i < alkioidenLkm; i++) {
            alkiot[i] = alkiot[i + 1];
        }
    }
    
    public boolean poistaAlkio(int luku) {
    	for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkiot[i]) {
                siirraAlkioitaVasemmalle(i);
                alkioidenLkm--;
                return true;
            }
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
    	if (alkioidenLkm == 0) {
          return "{}";
    	}
    	String tuotos = "{";
    	for (int i = 0; i < alkioidenLkm - 1; i++) {
          tuotos += alkiot[i] + ", ";
    	}
    	tuotos += alkiot[alkioidenLkm - 1];
    	tuotos += "}";
    	return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = alkiot[i];
        }
        return taulu;
    }
   

    public int[] getLjono() {
    	return this.alkiot;
    }
    
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aTaulu = a.getLjono();
        int[] bTaulu = b.getLjono();
        for (int i = 0; i < a.mahtavuus(); i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < b.mahtavuus(); i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aTaulu = a.getLjono();
        int[] bTaulu = b.getLjono();
        for (int i = 0; i < a.mahtavuus(); i++) {
            for (int j = 0; j < b.mahtavuus(); j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aTaulu = a.getLjono();
        int[] bTaulu = b.getLjono();
        for (int i = 0; i < a.mahtavuus(); i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < b.mahtavuus(); i++) {
            z.poistaAlkio(bTaulu[i]);
        }
 
        return z;
    }
        
}
