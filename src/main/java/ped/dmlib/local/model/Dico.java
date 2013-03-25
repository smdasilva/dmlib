package ped.dmlib.local.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dico {
	
	Map<Integer, String[]> dictionary;
	int idIncrement = 0;
		
	public Dico() {
		this.dictionary = new HashMap<Integer, String[]>();
	}
		
	public int add(String path, String type, String parent) 
	{
		String[] datas  = new String[3];
		datas[0] = path;
		datas[1] = type;
		datas[2] = parent;
		
		int id = fileExist(path);
		if(id > 0) {
			return id;
		} 
		else {
			this.dictionary.put(idIncrement, datas); 
			return idIncrement++; 
		}
	}
	
	public int fileExist(String path) {
		for (Integer key : this.dictionary.keySet()) {
			String[] datas = this.dictionary.get(key);
			if (datas[0] == "path") {
				return key;
			}
		} 
		return -1;
	}
		
	public String[] getDatas(int id) {
		return this.dictionary.get(id);
	}
	
	public String getAbsPath(int id) {
		return this.dictionary.get(id)[0];
	}
	
	public void remove(int id) {
		this.dictionary.remove(id);
	}
	
	public void replacePath(int id, String path) {
		for (Integer key : this.dictionary.keySet()) {
			if(key == id) {
				this.dictionary.get(key)[0] = path;
			}
		}
	}
	
	public ArrayList<String> getChild(String id) {
		ArrayList<String> result = new ArrayList<String>(); 
		for (String[] datas : this.dictionary.values()) {
			String value = datas[2];
			if (value == id) {
				result.add(value);
			}
		}
		return result;
	}
}