package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentagematcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerVmCountMatcher.hasAVmCountOf;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	/*
	 * @Test public void itCompiles() { assertThat(true, equalTo(true)); }
	 */

	@Test
	public void balancingAServer_noVms_serverStaysEmpty() {
		Server theServer = a(server().withCapacity(1));

		balance(aListOfServersWith(theServer), anEmptyListOfVms());

		assertThat(theServer, hasCurrentLoadPercentageOf(0.0d));
	}

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm() {
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));

		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat(theServer, hasCurrentLoadPercentageOf(100.0d));
		assertThat("the server should contain vm", theServer.contains(theVm));
	}

	@Test
	public void balancingOneServerWithTenSlotCapacity_andOneSlotVm_fillsTheServerWithTenPercent() {
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));

		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat(theServer, hasCurrentLoadPercentageOf(10.0d));
		assertThat("the server should contain vm", theServer.contains(theVm));
	}

	private Matcher<? super Server> hasCurrentLoadPercentageOf(
			double expectedLoadPercentage) {
		return new CurrentLoadPercentagematcher(expectedLoadPercentage);
	}

	@Test
	public void balancingAServerWithEnoughRoom_getsFilledWithAllVms() {
		Server theServer = a(server().withCapacity(100));
		Vm theFirstVm = a(vm().ofSize(1));
		Vm theSecondVm = a(vm().ofSize(1));

		balance(aListOfServersWith(theServer),
				aListOfVmsWith(theFirstVm, theSecondVm));

		assertThat(theServer, hasAVmCountOf(2));
		assertThat("the server should contain vm",
				theServer.contains(theFirstVm));
		assertThat("the server should contain vm",
				theServer.contains(theSecondVm));
	}

	@Test
	public void aVm_shouldBeBalanced_onLessLoadedServerFirst() {
		Server lessLoadedServer = a(server().withCapacity(100)
				.withCurrentLoadOf(45.0d));
		Server moreLoadedServer = a(server().withCapacity(100)
				.withCurrentLoadOf(50.0d));
		balance(aListOfServersWith(moreLoadedServer, lessLoadedServer),
				aListOfVmsWith(theVm));

		assertThat("the less loaded server should contain vm",
				lessLoadedServer.contains(theVm));
	}

	private Vm[] aListOfVmsWith(Vm... theVm) {
		return theVm;
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Server[] aListOfServersWith(Server theServer) {
		// TODO Auto-generated method stub
		return new Server[] { theServer };
	}

	private Vm[] anEmptyListOfVms() {
		// TODO Auto-generated method stub
		return new Vm[0];
	}

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}

}