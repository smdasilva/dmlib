package ped.dmlib;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import java.util.TreeMap;


public class ServerAddress {
	private Map<String, String> servers = new TreeMap<String, String>();
	
	public void addAddress(String name, String URI){
		servers.put(name, URI);
	}
	
	public String getAddress(String name){
		return servers.get(name);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> tmp : servers.entrySet()) {
			sb.append(tmp.getKey() + " -- " + tmp.getValue());
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	public void save(ServerAddressSerialization as, Writer out){
		as.save(servers, out);
	}
	
	public String save(ServerAddressSerialization as){
		return as.save(servers);
	}
	
	public ServerAddress load(ServerAddressSerialization as, String in){
		servers = as.load(in);
		return this;
	}
	
	public ServerAddress load(ServerAddressSerialization as, File f) throws IOException{
		servers = as.load(f);
		return this;
	}
}
