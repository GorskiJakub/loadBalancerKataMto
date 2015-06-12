package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			List<Server> vmCapableServers = new ArrayList<Server>();
			for (Server server : servers) {
				if (server.canFit(vm)) {
					vmCapableServers.add(server);
				}
			}
			Server lessLoad = findLessLoadedServer(vmCapableServers);
			if (lessLoad != null) {
				lessLoad.addVm(vm);
			}
		}
	}

	private Server findLessLoadedServer(List<Server> vmCapableServers) {
		Server lessLoad = null;
		for (Server server : vmCapableServers) {
			if (lessLoad == null
					|| lessLoad.currentLoadPErcentage > server.currentLoadPErcentage) {
				lessLoad = server;
			}
		}
		return lessLoad;
	}

}
