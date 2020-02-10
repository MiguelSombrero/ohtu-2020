package statistics.matcher;

import statistics.Player;

public class HasFewerThan implements Matcher {

	private HasAtLeast atLeastMatcher;
	
	public HasFewerThan(int value, String category) {
		this.atLeastMatcher = new HasAtLeast(value, category);
	}
	
	@Override
	public boolean matches(Player p) {
		return !atLeastMatcher.matches(p);
	}

}
