package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

	private double expectedLoadBalance;

	public CurrentLoadPercentageMatcher(double expectedLoadBalance) {
		this.expectedLoadBalance = expectedLoadBalance;
	}

	public void describeTo(Description description) {
		description.appendText("a server with load percentage of ").appendValue(expectedLoadBalance);
	}
	@Override
	protected void describeMismatchSafely(Server item,
			Description description) {
		description.appendText("a server with load percentage of ").appendValue(item.currentLoadPercentage);
		}
	@Override
	protected boolean matchesSafely(Server item) {
		return Math.abs(expectedLoadBalance - item.currentLoadPercentage)< 0.01d;
	}

}
