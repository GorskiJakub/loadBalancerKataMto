package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAX_LOAD = 100.0d;
	private int capacity;
	public double currentLoadPercentage;

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm theVm) {
		return true;
	}

	public double getCapacity() {
		return capacity;
	}

	public void addVm(Vm vm) {
		this.currentLoadPercentage += (double) vm.getSize()
				/ (double) this.getCapacity() * MAX_LOAD;
	}


}
