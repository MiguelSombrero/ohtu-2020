package statistics.matcher;

import statistics.Player;

public class HasFewerThan implements Matcher {
	private Matcher matcher;
	private HasAtLeast atLeastMatcher;
	
	public HasFewerThan(Matcher matcher, int value, String category) {
		this.matcher = matcher;
		this.atLeastMatcher = new HasAtLeast(matcher, value, category);
	}
	
	@Override
	public boolean matches(Player p) {
		if (atLeastMatcher.matches(p)) {
			return false;
		}
		
		return matcher.matches(p);
	}

}
