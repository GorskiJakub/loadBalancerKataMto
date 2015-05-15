package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAXIMUM_LOAD = 100.0d;
	private int capacity;
	private List<Vm> vms = new ArrayList<Vm>();
	public double currnetLoadPercentage;

	public Server(int capacity) {
		this.capacity = capacity;
		
	}

	public boolean contains(Vm theVm) {
		return vms.contains(theVm);
	}

	public int getCapacity() {
		return capacity;
	}

	public void addVm(Vm vm) {
		vms.add(vm);
		this.currnetLoadPercentage += loadOfVm(vm);
		
	}

	public int vmsCount() {
		return vms.size();
	}

	public boolean canFit(Vm vm) {
		return this.currnetLoadPercentage + loadOfVm(vm) <= MAXIMUM_LOAD;
	}

	private double loadOfVm(Vm vm) {
		return (double)vm.getSize()
				/ (double)this.getCapacity() *MAXIMUM_LOAD;
	}

}
