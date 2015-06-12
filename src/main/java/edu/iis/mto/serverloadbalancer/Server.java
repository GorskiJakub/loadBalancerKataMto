package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAX_LOAD = 100.0d;
	private int capacity;
	public double currentLoadPercentage;
	private List<Vm> vms = new ArrayList();

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public boolean contains(Vm theVm) {
		return vms.contains(theVm);
	}

	public double getCapacity() {
		return capacity;
	}

	public void addVm(Vm vm) {
		vms.add(vm);
		this.currentLoadPercentage += lessLoadedVm(vm);
	}

	private double lessLoadedVm(Vm vm) {
		return (double) vm.getSize()
				/ (double) this.getCapacity() * MAX_LOAD;
	}

	public int vmsCount() {
		return vms.size();
	}

	public boolean canFit(Vm vm) {
		return this.currentLoadPercentage + lessLoadedVm(vm) <= MAX_LOAD;
	}


}
