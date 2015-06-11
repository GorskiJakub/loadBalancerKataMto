package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalance {

	public void balance(Server[] servers, Vm[] vms) {
		if (vms.length > 0) {
			servers[0].currentLoadPrcentage += (double) vms[0].getSize()
					/ (double) servers[0].getCapacity() * 100.0d;
		}

	}

}
