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
				.appendValue(item.currnetLoadPercentage);
	}

	@Override
	protected boolean matchesSafely(Server item) {

		return expectedLoadPercentage == item.currnetLoadPercentage
				|| Math.abs(expectedLoadPercentage)
						- item.currnetLoadPercentage < 0.01;
	}

}
