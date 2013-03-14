package ped.dmlib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.Writer;
import java.util.Map;

public interface ServerAddressSerialization {
	void save(Map<String, String> address, Writer out);
	String save(Map<String, String> address);
	
	Map<String, String> load(String in);
	Map<String, String> load(File in) throws FileNotFoundException;
	Map<String, String> load(Reader in);
}
