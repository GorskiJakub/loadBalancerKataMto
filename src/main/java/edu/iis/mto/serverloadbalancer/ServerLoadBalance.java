package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalance {

	public void balance(Server[] servers, Vm[] vms) {
		if(vms.length >0){
			servers[0].currentLoadPrcentage = 100.0d;
		}
		
	}

}
