package ped.dmlib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YamlServerAddressSerialization implements ServerAddressSerialization {
	Yaml yaml = new Yaml();

	public void save(Map<String, String> address, Writer out) {
		yaml.dump(address, out);
	}

	public String save(Map<String, String> address) {
		return yaml.dump(address);
	}

	public Map<String, String> load(String in) {
		return (Map<String, String>) yaml.load(in);
	}

	public Map<String, String> load(File in) throws FileNotFoundException {
		return (Map<String, String>) yaml.load(new FileInputStream(in));
	}

	public Map<String, String> load(Reader in) {
		return (Map<String, String>) yaml.load(in);
	}

}
