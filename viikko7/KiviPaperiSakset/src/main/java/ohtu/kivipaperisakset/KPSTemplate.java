package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KPSTemplate implements KPS {

	protected static final Scanner scanner = new Scanner(System.in);

	public static KPSTemplate luoKPSHelppoTekoaly() {
		return new KPSTekoaly( new Tekoaly() );
	}
	
	public static KPSTemplate luoKPSVaikeaTekoaly(int muisti) {
		return new KPSTekoaly( new TekoalyParannettu(muisti) );
	}
	
	public static KPSTemplate luoKPSPelaajaVsPelaaja() {
		return new KPSPelaajaVsPelaaja();
	}
	
	@Override
	public void pelaa() {
		Tuomari tuomari = new Tuomari();
        String ekanSiirto = "";
        String tokanSiirto = "";
        
        System.out.print("Ensimmäisen pelaajan siirto: ");
        ekanSiirto = scanner.nextLine();
        
        tokanSiirto = siirra(ekanSiirto);

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();
            
            tokanSiirto = siirra(ekanSiirto);
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
		
	}
	
	protected abstract String siirra(String eka);
	
	private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }

}
