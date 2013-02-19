package ped.dmlib.serveur;


public abstract class Handler {
	protected Handler successor = null;
		
	public void setSuccessor(Handler next){
		this.successor = next;
	}
	
	public boolean handleRequest(Request request) {
		if(successor == null)
			return false;
		
		return successor.handleRequest(request);
	}
}
