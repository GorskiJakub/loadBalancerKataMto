package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmCountMatcher extends TypeSafeMatcher<Server> {

	private int expectedCount;

	public ServerVmCountMatcher(int expectedCount) {
		this.expectedCount = expectedCount;
	}

	public void describeTo(Description description) {
		description.appendText("a server with vms count of ").appendValue(expectedCount);
	}
	@Override
	protected void describeMismatchSafely(Server item,
			Description description) {
		description.appendText("a server with vms count of ").appendValue(item.vmsCount());
	}
	
	@Override
	protected boolean matchesSafely(Server item) {
		return item.vmsCount() == expectedCount;
	}
	public static Matcher<? super Server> hasAVmCountOf(int expectedCount) {
		return new ServerVmCountMatcher(expectedCount);
	}

}
