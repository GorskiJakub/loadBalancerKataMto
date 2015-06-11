package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			Server lessLoaded = findLessloadedServer(servers);
			lessLoaded.addVm(vm);
		}

	}

	private Server findLessloadedServer(Server[] servers) {
		Server lessLoaded = null;
		for(Server server : servers){
			if(lessLoaded == null || lessLoaded.currentLoadPercentage > server.currentLoadPercentage){
				lessLoaded = server;
			}
		}
		return lessLoaded;
	}
}
