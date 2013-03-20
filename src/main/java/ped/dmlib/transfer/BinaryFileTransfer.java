package ped.dmlib.transfer;

public interface BinaryFileTransfer {
	/**
	 * Receive file(s) from remote repository into local repository
	 * @param libraryName: the library name like 'image' or 'music'
	 * @param filePath: the relative path file from the library
	 * @return true => no error, false => error
	 */
	boolean pull(String libraryName, String filePath);
	
	/**
	 * Send file(s) from local repository to remote repository
	 * @param libraryName: the library name like 'image' or 'music'
	 * @param filePath: the relative path file from the library
	 * @return true => no error, false => error
	 */
	boolean push(String libraryName, String filePath);
}
