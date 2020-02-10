package statistics.matcher;

import statistics.Player;

public class Not implements Matcher {

	private And andMatcher;
	
	public Not(Matcher...matchers) {
		this.andMatcher = new And(matchers);
	}
	
	@Override
	public boolean matches(Player p) {
		return !andMatcher.matches(p);
	}

}
