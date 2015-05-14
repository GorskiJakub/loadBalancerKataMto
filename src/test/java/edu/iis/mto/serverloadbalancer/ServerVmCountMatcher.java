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
		description.appendText("a server with vms count of").appendValue(
				expectedCount);
	}
	
	protected void descriptionmatchSafely(Server item, Description description){
		description.appendText("a server with vms count of").appendValue(
				item.vmsCount());
	}
	
	@Override
	protected boolean matchesSafely(Server item) {
		// TODO Auto-generated method stub
		return item.vmsCount() == expectedCount;
	}

}
