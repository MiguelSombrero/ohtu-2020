package ohtuesimerkki;

import ohtuesimerkki.Statistics;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class StatisticsTest {

	Reader readerStub = new Reader() {
		 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void searchPlayerExists() {
    	Player p = stats.search("Kurri");
    	
    	assertEquals("Kurri", p.getName());
    	assertEquals("EDM", p.getTeam());
    	assertEquals(37, p.getGoals());
    	assertEquals(53, p.getAssists());
    }
    
    @Test
    public void searchPlayerNotExists() {
    	Player p = stats.search("Somero");
    	assertEquals(null, p);
    }
    
    @Test
    public void teamReturnsAllPlayers() {
    	List<Player> p = stats.team("EDM");
    	assertEquals(3, p.size());
    }
    
    @Test
    public void teamReturnsZeroUnexistingTeam() {
    	List<Player> p = stats.team("JEE");
    	assertEquals(0, p.size());
    }
    
    @Test
    public void ReturnRightAmountOfTopScorers() {
    	List<Player> p = stats.topScorers(2);
    	assertEquals(2, p.size());
    }
    
    @Test
    public void ReturnTopScorersInRightOrder() {
    	List<Player> p = stats.topScorers(3);
    	
    	assertEquals("Gretzky", p.get(0).getName());
    	assertEquals("Lemieux", p.get(1).getName());
    	assertEquals("Yzerman", p.get(2).getName());
    }
    
}
