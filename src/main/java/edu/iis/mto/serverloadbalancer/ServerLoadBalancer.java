package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for(Vm vm: vms) {
			Server lessLoad = findLessLoadedServer(servers);
			lessLoad.addVm(vm);
			
		}
	}

	private Server findLessLoadedServer(Server[] servers) {
		Server lessLoad = null;
		for(Server server : servers){
			if(lessLoad ==null || lessLoad.currentLoadPErcentage > server.currentLoadPErcentage){
				lessLoad = server;
			}
		}
		return lessLoad;
	}

}
