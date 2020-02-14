
package statistics.matcher;

import statistics.Player;

public class PlaysIn implements Matcher {
	private Matcher matcher;
    private String team;

    public PlaysIn(Matcher matcher, String team) {
    	this.matcher = matcher;
        this.team = team;
    }        
    
    @Override
    public boolean matches(Player p) {
        if (!p.getTeam().contains(team)) {
        	return false;
        }
        
        return matcher.matches(p);
    }
    
}
