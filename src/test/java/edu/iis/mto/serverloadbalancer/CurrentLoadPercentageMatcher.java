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
		description.appendText("a server with load percentage of ")
				.appendValue(expectedLoadPercentage);

	}

	protected void describeMismatchSafely(Server item, Description description) {
		description.appendText("a server with load percentage of ")
				.appendValue(item.getCurrentLoadPercentage());
	}

	@Override
	protected boolean matchesSafely(Server item) {
		return equalsDouble(expectedLoadPercentage, item.getCurrentLoadPercentage());
	}

	private boolean equalsDouble(double d1, double d2) {
		return Math.abs(d1 - d2) < 0.01;
	}

	public static Matcher<? super Server> hasCurrentLoadPercentageOf(
			double expectedLoadPercentage) {
		return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
	}

	public static Matcher<? super Server> hasAVmCountOf(int expectedCount) {
		return new ServerVmCountMatcher(expectedCount);
	}
	public static Matcher<? super Server> hasLoadPercentageOf(double expectedLoadPercentage) {
		return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
	}

}
