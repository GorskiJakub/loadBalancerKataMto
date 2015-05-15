package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	private int capacity;
	public double currnetLoadPercentage;

	public Server(int capacity) {
		this.capacity = capacity;
		
	}

	public boolean contains(Vm theVm) {
		return true;
	}

	public int getCapacity() {
		// TODO Auto-generated method stub
		return capacity;
	}

}
