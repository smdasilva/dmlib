package ped.dmlib;

import java.util.HashMap;
import java.util.Map;

public class Servers {

	String repositoryPath;
	Map<String, String> servers = new HashMap<String, String>();

	public Servers(String repositoryPath) {
		this.repositoryPath = repositoryPath;
	}
	
	public void addServer(String name, String URI){
		servers.put(name, URI);
	}
	
	public void removeServer(String name){
		servers.remove(name);
	}
	
	public void existServer(String name){
	
		
	}
	
	public void save(){
		
	}
	
	public String[] getServers(){
		
		return null;
	}
}
