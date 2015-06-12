package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	private int capacity;
	public double currentLoadPErcentage;

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm theVm) {
		return true;
	}

	public double getCapacity() {
		return capacity;
	}

}
