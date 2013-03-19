package ped.dmlib.transfer;

import java.io.IOException;

import ped.dmlib.filemanagement.ComputerRepository;

public class RsyncTransfer implements BinaryFileTransfer {
	private ComputerRepository local;
	private ComputerRepository remote;
	
	private String srcPath;
	private String destPath;

	public RsyncTransfer(ComputerRepository local, ComputerRepository remote) {
		this.local = local;
		this.remote = remote;
	}

	public void pull(String libraryName, String filePath) {
		this.constructPaths(remote.getLibraryPath(libraryName), 
				local.getLibraryPath(libraryName), 
				filePath);
		
		//execute();
	}

	public void push(String libraryName, String filePath) {
		this.constructPaths(local.getLibraryPath(libraryName), 
				remote.getLibraryPath(libraryName), 
				filePath);
		
		//execute();
	}
	
	private void constructPaths(String srcPathLibrary, String destPathLibrary, 
			String filePath) {
		StringBuilder srcPathTmp = new StringBuilder(srcPathLibrary);
		StringBuilder destPathTmp = new StringBuilder(destPathLibrary);
		
		if(!srcPathLibrary.endsWith("/"))
			srcPathTmp.append("/");
		
		if(filePath.equals("") || 
				filePath.equals("*") || 
				filePath == null)
			srcPathTmp.append("*");
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
		String cmd[] = new String[]{"rsync", "-az", this.srcPath, this.destPath};
		ProcessBuilder pb = new ProcessBuilder(cmd);
		Process p = pb.start();
		int val = p.waitFor();

		if (val != 0)
			throw new Exception("Exception during RSync; return code = " + val);
	}

}
