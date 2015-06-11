package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentagematcher extends TypeSafeMatcher<Server> {

	private double expectedLoadPercentage;

	public CurrentLoadPercentagematcher(double expectedLoadPercentage) {
		this.expectedLoadPercentage = expectedLoadPercentage;
	}

	public void describeTo(Description description) {
		description.appendText("a server with load Percentage of ")
				.appendValue(expectedLoadPercentage);
	}

	@Override
	protected void describeMismatchSafely(Server item, Description description) {
		description.appendText("a server with load Percentage of ")
				.appendValue(item.currentLoadPercentage);
	}

	@Override
	protected boolean matchesSafely(Server item) {
		return equalsDouble(expectedLoadPercentage, item.currentLoadPercentage);
	}

	private boolean equalsDouble(double d1, double d2) {
		return Math.abs(d1 - d2) < 0.01d;
	}
	
	public static Matcher<? super Server> hasLoadPercentageOf(double expectedLoadPercentage) {
		return new CurrentLoadPercentagematcher(expectedLoadPercentage);
	}

}
