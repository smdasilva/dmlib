package ped.dmlib.factory;

import ped.dmlibtest.model.Library;

public class LibraryFactory {
	private Library lib = new Library("test");
	
	public Library getLibrary(String name) {
		return lib;
	}
}
