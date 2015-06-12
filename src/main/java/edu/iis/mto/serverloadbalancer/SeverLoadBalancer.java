package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class SeverLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			List<Server> vmCapableServers = new ArrayList<Server>();
			for(Server server: servers){
				if(server.canFit(vm)){
					vmCapableServers.add(server);
				}
			}
			Server lessLoaded = findLessLoadedServer(vmCapableServers);
			if(lessLoaded != null){
				lessLoaded.addVm(vm);
			}
		}

	}

	private Server findLessLoadedServer(List<Server> vmCapableServers) {
		Server lessLoaded = null;
		for (Server server : vmCapableServers) {
			if (lessLoaded == null
					|| lessLoaded.currentLoadPercentage > server.currentLoadPercentage) {
				lessLoaded = server;
			}
		}
		return lessLoaded;

		
	}

}
