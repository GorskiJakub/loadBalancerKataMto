package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;

public class ServerBuilder implements Builder<Server> {

	private int capacity;
	private double expectedLoad;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);
		addInitialLoad(server);
		return server;
	}

	private void addInitialLoad(Server server) {
		if(expectedLoad > 0){
			int vmsSize = (int) (expectedLoad/ (double) server.getCapacity() * 100.0d);
			server.addVm(vm().ofSize(vmsSize).build());
		}
	}
	
	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public Builder<Server> withCurrentLoadOf(double expectedLoad) {
		this.expectedLoad = expectedLoad;
		return this;
	}

}
