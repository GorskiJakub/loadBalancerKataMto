package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


public class SeverVmCountMatcher extends TypeSafeMatcher<Server> {

	private int expectedCount;

	public SeverVmCountMatcher(int expectedCount) {
		this.expectedCount = expectedCount;
	}

	public void describeTo(Description description) {
		description.appendText("a server with vms count of").appendValue(expectedCount);		
	}
	
	protected void describeMismatchSafely(Server item, Description description) {
		description.appendText("a server with load percentage of ")
				.appendValue(item.vmsCount());
	}

	@Override
	public boolean matchesSafely(Server item) {

		return item.vmsCount() == expectedCount;
	}

}
