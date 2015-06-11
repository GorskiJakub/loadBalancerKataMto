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
		description.appendText("a server load percentage of ").appendValue(expectedLoadPercentage);
	}
	@Override
	protected void describeMismatchSafely(Server item,
			Description description) {
		description.appendText("a server load percentage of ").appendValue(item.currentLoadPrcentage);
	}
	@Override
	protected boolean matchesSafely(Server item) {
		return equealsDouble(expectedLoadPercentage, item.currentLoadPrcentage);
	}

	private boolean equealsDouble(double d1, double d2) {
		return Math.abs(d1 - d2)< 0.01d;
	}
	
	public static Matcher<? super Server> hasLoadPercentageOf(double expectedLoadPercentage) {
		return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
	}

}
