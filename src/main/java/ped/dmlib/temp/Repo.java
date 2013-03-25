package ped.dmlib.temp;

import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Repo {
	private String name;
	private String address;
	private int port;
	
	private Map<String, String> librariesPaths;
	
	public Repo() {}

	public Repo(String name, String address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
		this.librariesPaths  = new TreeMap<String, String>();
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
		return ((this.address == other.getAddress()) &&
				(this.port == other.getPort()));
	}

	@Override
	public String toString() {
		return "Repo [name=" + name + ", address=" + address + ", port=" + port
				+ ", librariesPaths=" + librariesPaths + "]";
	}
	
}
