package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

	private double expectedLoadPercentage;

	public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
		this.expectedLoadPercentage = expectedLoadPercentage;
	}

	public void describeTo(Description description) {
		description.appendText("a server load percentage of ").appendValue(
				expectedLoadPercentage);
	}

	@Override
	protected void describeMismatchSafely(Server item, Description description) {
		description.appendText("a server load percentage of ").appendValue(
				item.currentLoadPercentage);
	}

	@Override
	protected boolean matchesSafely(Server item) {
		return Math.abs(expectedLoadPercentage - item.currentLoadPercentage) < 0.01d;
	}

}
