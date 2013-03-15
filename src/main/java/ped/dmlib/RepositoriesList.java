package ped.dmlib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.Yaml;

public class RepositoriesList {
	private List<Repository> repositories = new ArrayList<Repository>();
	
	public void addRepository(Repository r) {
		repositories.add(r);
	}
	
	public void removeRepository(Repository r) {
		repositories.remove(r);
	}
	
	public List<Repository> getRepositories() {
		return repositories;
	}

	public String save() {
		Yaml yaml = new Yaml();
		
		return yaml.dump(repositories);
	}
	
	public void save(File out) throws IOException {
		Yaml yaml = new Yaml();
		
		yaml.dump(repositories, new FileWriter(out));
	}
	
	public void load(String in) {
		Yaml yaml = new Yaml();
		
		repositories = (List<Repository>) yaml.load(in);
	}
}
