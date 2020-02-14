package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPSTemplate implements KPS {

	protected static final Scanner scanner = new Scanner(System.in);

	@Override
	public void pelaa() {
		Tuomari tuomari = new Tuomari();
        String ekanSiirto = "";
        String tokanSiirto = "";
        
        siirra(ekanSiirto, tokanSiirto);

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            siirra(ekanSiirto, tokanSiirto);
            
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
		
	}
	
	protected abstract void siirra(String eka, String toka);
	
	private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

}
