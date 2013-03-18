import java.util.Map;
import java.util.TreeMap;

public class ComputerRepository {
	private String name;
	private String address;
	private Map<String, String> libraries = new TreeMap<String, String>();
	
	public ComputerRepository() {}

	public ComputerRepository(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public void addLibrary(String libraryName, String libraryPath) {
		this.libraries.put(libraryName, libraryPath);
	}
	
	public void removeLibrary(String libraryName) {
		this.libraries.remove(libraryName);
	}
	
	public String getURI(String libraryName) {
		StringBuilder uri = null;
		
		if(libraries.containsKey(libraryName)) {
			uri = new StringBuilder();
			uri.append(address);
			uri.append(":");
			uri.append(libraries.get(libraryName));
		}
		
		return uri.toString();
	}
	
	public String getLibraryPath(String libraryName) {
		return this.libraries.get(libraryName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Map<String, String> getLibraries() {
		return libraries;
	}

	public void setLibraries(Map<String, String> libraries) {
		this.libraries = libraries;
	}	
}