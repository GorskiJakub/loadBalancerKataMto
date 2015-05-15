package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAXIMUM_LOAD = 100.0d;
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

	public void addVm(Vm vm) {
		this.currnetLoadPercentage += (double)vm.getSize()
				/ (double)this.getCapacity() *MAXIMUM_LOAD;
		
	}

}
