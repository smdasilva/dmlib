package ped.dmlib.temp;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ped.dmlib.Util;


@XmlRootElement
public class Repo {
	private String name;
	private String address;
	private int port;
	
	private Map<String, String> librariesPaths;
	
	public Repo() {
		this.init(Util.getComputerFullName(), Util.myIP(), 8000);
	}

	public Repo(String name, String address, int port) {
		this.init(name, address, port);
	}
	
	private void init (String name, String address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
		this.librariesPaths  = new TreeMap<String, String>();
	}
	
	public String getURI(String libraryName) {
		StringBuilder uri = null;
		
		if(librariesPaths.containsKey(libraryName)) {
			uri = new StringBuilder();
			uri.append(address);
			uri.append(":");
			uri.append(librariesPaths.get(libraryName));
		}
		
		return uri.toString();
	}
	
	public String getURL() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getAddress());
		
		if(this.getPort() > 1024)
			sb.append(":" + this.getPort());
		
		return sb.toString();
	}
	
	public String getLibraryPath(String libraryName) {
		String path = "";
		if(this.librariesPaths.containsKey(libraryName))
		{
			path = this.librariesPaths.get(libraryName);
		}
		else
		{
			path = System.getProperty("user.home")+"/SharedMediaCenter/" + libraryName;
			this.librariesPaths.put(libraryName, path);
		}
		return path;
	}
	
	public Set<String> getLibraries() {
		return this.librariesPaths.keySet();
	}
	
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@XmlElement
	public Map<String, String> getLibrariesPaths() {
		return librariesPaths;
	}

	public void setLibrariesPaths(Map<String, String> librariesPaths) {
		this.librariesPaths = librariesPaths;
	}

	public void addLibrary(String libraryName, String path) {
		librariesPaths.put(libraryName, path);
	}

	public boolean equals(Repo other) {		
		return this.getURL().equals(other.getURL());
	}

	@Override
	public String toString() {
		return "Repo [name=" + name + ", address=" + address + ", port=" + port
				+ ", librariesPaths=" + librariesPaths + "]";
	}
	
}
