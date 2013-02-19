package ped.dmlib.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Library {
	protected String name;
	protected List<File> files = new ArrayList<File>();

	public Library(String name) {
		super();
		this.name = name;
	}
	
	public void addFile(File f) {
		files.add(f);
	}
}
