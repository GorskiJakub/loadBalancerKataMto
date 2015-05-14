package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAXIMUM_LOAD = 100.0d;
	public double currnetLoadPercentage;
	private int capacity;

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm theVm) {
		return true;
	}

	public int getCapacity() {
		return capacity;
	}

	public void addVm(Vm vm) {
		this.currnetLoadPercentage += (double) vm.getSize()
				/ (double) this.getCapacity() * MAXIMUM_LOAD;

	}

}
