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
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingAServer_noVms_serverStaysEmpty() {
		Server theServer = a(server().withCapacity(1));

		balance(aListOfServersWith(theServer), anEmptyListOfVms());

		assertThat(theServer, hasLoadPercentageOf(0.0d));
	}

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWiththeVm() {
		Server theServer = a(server().withCapacity(1));
		Vm theVm = a(vm().ofSize(1));
		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat(theServer, hasLoadPercentageOf(100.0d));
		assertThat("server should contains", theServer.contains(theVm));
	}

	@Test
	public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillTheServerWithTenPercent() {
		Server theServer = a(server().withCapacity(10));
		Vm theVm = a(vm().ofSize(1));
		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat(theServer, hasLoadPercentageOf(10.0d));
		assertThat("server should contains", theServer.contains(theVm));
	}

	@Test
	public void balancingAServerWithEnoughRoom_getsFilledWithAllVms() {
		Server theServer = a(server().withCapacity(100));
		Vm theFirstVm = a(vm().ofSize(1));
		Vm theSecondVm = a(vm().ofSize(1));
		balance(aListOfServersWith(theServer),
				aListOfVmsWith(theFirstVm, theSecondVm));

		assertThat(theServer, hasAVmCountOf(2));
		assertThat("server should contains", theServer.contains(theFirstVm));
		assertThat("server should contains", theServer.contains(theSecondVm));
	}

	@Test
	public void aVm_shouldBeBalanced_onLessLoadedServerFirst() {
		Server lessLoadedServer = a(server().withCapacity(100)
				.withCurrentLoadOf(45.0d));
		Server moreLoadedServer = a(server().withCapacity(100)
				.withCurrentLoadOf(50.0d));
		Vm theVm = a(vm().ofSize(10));
		balance(aListOfServersWith(moreLoadedServer, lessLoadedServer),
				aListOfVmsWith(theVm));

		assertThat("the less loaded server should contain vm",
				lessLoadedServer.contains(theVm));
	}

	@Test
	public void balanceAServerWithNotEnoughRoom_shouldNotBeFilledSithAVm() {
		Server theServer = a(server().withCapacity(10).withCurrentLoadOf(90.0d));
		Vm theVm = a(vm().ofSize(2));
		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat("the less loaded server should not contain vm",
				!theServer.contains(theVm));
	}

	private Server[] aListOfServersWith(Server... theServer) {
		return theServer;
	}

	private Vm[] aListOfVmsWith(Vm... theVm) {
		return theVm;
	}

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}

	private Vm[] aListOfVmsWith(Vm theVm) {
		return new Vm[] { theVm };
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server[] aListOfServersWith(Server theServer) {
		return new Server[] { theServer };
	}

}
