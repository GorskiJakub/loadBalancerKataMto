package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			Server lessLoaded = null;
			for (Server server : servers) {
				if (lessLoaded == null
						|| lessLoaded.currnetLoadPercentage > server.currnetLoadPercentage) {
					lessLoaded = server;
				}

			}
			lessLoaded.addVm(vm);
		}

	}

}
