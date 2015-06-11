package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmCountMatcher extends TypeSafeMatcher<Server> {

	private int exptectedCount;

	public ServerVmCountMatcher(int exptectedCount) {
		this.exptectedCount = exptectedCount;
	}

	public void describeTo(Description description) {
		description.appendText("a server with vms count of ").appendValue(
				exptectedCount);
	}

	@Override
	protected void describeMismatchSafely(Server item, Description description) {
		description.appendText("a server with vms count of ").appendValue(
				item.vmsCount());

	}

	@Override
	protected boolean matchesSafely(Server item) {
		return item.vmsCount() == exptectedCount;
	}
	
	public static Matcher<? super Server> hasAVmCountOf(int exptectedCount) {
		return new ServerVmCountMatcher(exptectedCount);
	}

}
