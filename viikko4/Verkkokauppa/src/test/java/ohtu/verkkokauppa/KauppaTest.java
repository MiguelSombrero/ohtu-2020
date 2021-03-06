package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
	
	Kauppa k;
	Pankki pankki;
	Viitegeneraattori viite;
	Varasto varasto;
	
	@Before
	public void setUp() {
		pankki = mock(Pankki.class);
		viite = mock(Viitegeneraattori.class);
		varasto = mock(Varasto.class);
		k = new Kauppa(varasto, pankki, viite);              

	}

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
            
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));  
    }
    
    @Test
    public void kahdenTuotteenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
    	when(viite.uusi()).thenReturn(1);
    	when(varasto.saldo(1)).thenReturn(10);
    	when(varasto.saldo(2)).thenReturn(5);
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 3));
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(1);
    	k.lisaaKoriin(2);
    	k.tilimaksu("pekka", "12345");
    	
    	verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(8));
    }
    
    @Test
    public void kahdenSamanTuotteenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
    	when(viite.uusi()).thenReturn(1);
    	when(varasto.saldo(1)).thenReturn(10);
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(1);
    	k.lisaaKoriin(1);
    	k.tilimaksu("pekka", "12345");
    	
    	verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(10));
    }
    
    @Test
    public void loppuneenTuotteenOstoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaArvoilla() {
    	when(viite.uusi()).thenReturn(1);
    	when(varasto.saldo(1)).thenReturn(10);
    	when(varasto.saldo(2)).thenReturn(0);
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 3));
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(1);
    	k.lisaaKoriin(2);
    	k.tilimaksu("pekka", "12345");
    	
    	verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(5));
    }
    
    @Test
    public void edellisenOstoksenHintaEiNayUudenOstoksenHinnassa() {
    	when(viite.uusi()).thenReturn(1);
    	when(varasto.saldo(1)).thenReturn(10);
    	when(varasto.saldo(2)).thenReturn(10);
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 3));
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(1);
    	k.tilimaksu("pekka", "12345");
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(2);
    	k.tilimaksu("pekka", "12345");
    	
    	verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(3));
    }
    
    @Test
    public void kauppaPyytääUudenViitenumeronJokaiselleMaksutapahtumalle() {
    	when(viite.uusi()).thenReturn(1).thenReturn(2).thenReturn(3);
    	when(varasto.saldo(1)).thenReturn(10);
    	when(varasto.saldo(2)).thenReturn(10);
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 3));
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(1);
    	k.tilimaksu("pekka", "12345");
    	
    	verify(pankki).tilisiirto(anyString(), eq(1), anyString(), anyString(), anyInt());
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(2);
    	k.tilimaksu("pekka", "12345");
    	
    	verify(pankki).tilisiirto(anyString(), eq(2), anyString(), anyString(), anyInt());
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(2);
    	k.tilimaksu("jukka", "12345");
    	
    	verify(pankki).tilisiirto(anyString(), eq(3), anyString(), anyString(), anyInt());
    }
    
    @Test
    public void tuotteenPoistaminenKoristaPoistaaTuotteenOstoksesta() {
    	when(viite.uusi()).thenReturn(1).thenReturn(2).thenReturn(3);
    	when(varasto.saldo(1)).thenReturn(10);
    	when(varasto.saldo(2)).thenReturn(10);
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 3));
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(1);
    	k.lisaaKoriin(2);
    	k.poistaKorista(1);
    	k.tilimaksu("pekka", "12345");
    	
    	verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(3));
    }
    
    @Test
    public void olemattomanTuotteenPoistaminenKoristaEiPoistaTuotteitaOstoksesta() {
    	when(viite.uusi()).thenReturn(1).thenReturn(2).thenReturn(3);
    	when(varasto.saldo(1)).thenReturn(10);
    	when(varasto.saldo(2)).thenReturn(10);
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    	when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "kahvi", 3));
    	when(varasto.haeTuote(3)).thenReturn(null);
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(1);
    	k.lisaaKoriin(2);
    	k.poistaKorista(3);
    	k.tilimaksu("pekka", "12345");
    	
    	verify(pankki).tilisiirto(eq("pekka"), eq(1), eq("12345"), eq("33333-44455"), eq(8));
    }
    
    @Test
    public void tuoteenPoistaminenKoristaKutsuuVarastonPalautaTuoteMetodia() {
    	when(viite.uusi()).thenReturn(1);
    	when(varasto.saldo(1)).thenReturn(10);
    	when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    	
    	k.aloitaAsiointi();
    	k.lisaaKoriin(1);
    	k.poistaKorista(1);
    	k.tilimaksu("pekka", "12345");
    	
    	verify(varasto, times(1)).palautaVarastoon(any());
    }
    
    
}