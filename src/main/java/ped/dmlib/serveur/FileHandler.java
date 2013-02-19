package ped.dmlib.serveur;

import java.io.File;

public class FileHandler extends Handler {

	@Override
	public boolean handleRequest(Request request) {
		
		if(request.getAction().equals("addFile")){
			request.close();
			String args[] = request.getArgs();
			
			String lib = args[1];
			String fileName = args[2];
			int fileSize = Integer.parseInt(args[3]);
			int port = Integer.parseInt(args[4]);
			
			FileReception fr = new FileReception(request.getInetAddress(), port, fileName, fileSize);
			fr.start();
		}
		
		return super.handleRequest(request);
	}
}
