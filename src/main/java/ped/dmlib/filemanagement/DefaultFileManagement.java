package ped.dmlib.filemanagement;


public class DefaultFileManagement implements FileManagement {
	private ComputerRepository local;

	public DefaultFileManagement(ComputerRepository local) {
		this.local = local;
	}

	public void addFile(String libraryName, String filePath) {
		// TODO Extract file informations and save them

	}

	public void removeFile(String libraryName, String filePath) {
		// TODO Remove file informations and file itself

	}

}
