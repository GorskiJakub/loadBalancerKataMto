package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalance {

	public void balance(Server[] servers, Vm[] vms) {
			for(Vm vm: vms){
				Server lessLoaded= null;
				for(Server server: servers){
					if(lessLoaded ==null || lessLoaded.currentLoadPrcentage>server.currentLoadPrcentage){
						lessLoaded = server;
					}
				}
				lessLoaded.addVm(vm);
			}
			
			

	}

}
