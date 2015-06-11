package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for(Vm vm : vms){
			findLessLoadedServer(servers, vm);
		}
	}

	private void findLessLoadedServer(Server[] servers, Vm vm) {
		Server lessLoaded = null;
		for(Server server : servers){
			if(lessLoaded == null || lessLoaded.currentLoadPercentage>server.currentLoadPercentage){
				lessLoaded = server;
			}
		}
		lessLoaded.addVm(vm);
	}

}
