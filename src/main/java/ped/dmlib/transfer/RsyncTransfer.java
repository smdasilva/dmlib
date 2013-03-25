
package ped.dmlib.transfer;

import ped.dmlib.filemanagement.ComputerRepository;
import ped.dmlib.temp.Repo;

/**
 * Using rsync to transfer binary files
 *
 */
public class RsyncTransfer implements BinaryFileTransfer {
	private Repo local;
	private Repo remote;
	
	private String srcPath;
	private String destPath;

	public RsyncTransfer(Repo local, Repo remote) {
		this.local = local;
		this.remote = remote;
	}

	public boolean pull(String libraryName, String filePath) {
		this.constructPaths(remote.getLibraryPath(libraryName), 
							local.getLibraryPath(libraryName), 
							filePath);
		
		try {
			execute();
			return true;
		} catch (Exception e) {
			System.out.println("Exception during rsync");
			return false;
		}
	}

	public boolean push(String libraryName, String filePath) {
		this.constructPaths(local.getLibraryPath(libraryName), 
							remote.getLibraryPath(libraryName), 
							filePath);
		
		try {
			execute();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private void constructPaths(String srcPathLibrary, String destPathLibrary, 
			String filePath) {
		
		StringBuilder srcPathTmp = new StringBuilder(srcPathLibrary);
		StringBuilder destPathTmp = new StringBuilder(destPathLibrary);
		
		if(!srcPathLibrary.endsWith("/"))
			srcPathTmp.append("/");
		
		if(filePath.equals("") || 
				filePath.equals("*") || 
				filePath == null) {}
		else {
			srcPathTmp.append(filePath);
			destPathTmp.append(filePath);
		}
		
		if(!destPathLibrary.endsWith("/"))
			destPathTmp.append("/");

		this.srcPath = srcPathTmp.toString();
		this.destPath = destPathTmp.toString();
	}
	
	private void execute() throws Exception {
		String cmd[] = new String[]{"rsync", "-arv", this.srcPath, this.destPath};

		ProcessBuilder pb = new ProcessBuilder(cmd);
		Process p = pb.start();
		int val = p.waitFor();

		if (val != 0)
			throw new Exception("Exception during RSync; return code = " + val);
	}

}
