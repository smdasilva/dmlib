package ped.dmlib.filemanagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class ComputerRepositoriesList {
	
	private List<ComputerRepository> computersRepositories = new ArrayList<ComputerRepository>();
	
	public boolean addRepository(ComputerRepository cr) {
		return computersRepositories.add(cr);
	}
	
	public boolean removeRepository(ComputerRepository cr) {
		return computersRepositories.remove(cr);
	}
	
	public Map<String, ComputerRepository> getRepositories() {
		return null;
	}

	public String save() {
		Yaml yaml = new Yaml();
		
		return yaml.dump(computersRepositories);
	}
	
	public void save(File out) throws IOException {
		Yaml yaml = new Yaml();
		
		yaml.dump(computersRepositories, new FileWriter(out));
	}
	
	public void load(String in) {
		Yaml yaml = new Yaml();
		
		computersRepositories = (ArrayList<ComputerRepository>) yaml.load(in);
	}
}