package laskin;

public class Sovelluslogiikka {
 
    private int tulos;
 
    public void plus(int luku) {
        tulos += luku;
    }
     
    public void miinus(int luku) {
        tulos -= luku;
    }
 
    public void nollaa() {
        tulos = 0;
    }
 
    public void aseta(int arvo) {
    	this.tulos = arvo;
    }
    
    public int tulos() {
        return tulos;
    }
}