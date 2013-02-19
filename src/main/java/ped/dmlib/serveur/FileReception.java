package ped.dmlib.serveur;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

public class FileReception extends Thread implements Reception {
	Socket socket = null;
	File f = null;
	int fileSize = 0;
	
    private static Logger logger = Logger.getLogger(FileReception.class);
	
	public FileReception(InetAddress ia, int port, String fileName, int fileSize) {
		try {
			this.socket = new Socket(ia, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.f = new File(fileName);
		this.fileSize = fileSize;
	}
	
	@Override
	public void run() {
		this.receive();
	}

	protected void receive(){
		try {
			logger.info("File reception from " + socket.getInetAddress() + ", size = " + fileSize);
			DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));

			byte[] b = new byte[1024];
			
			int tmp = fileSize;
			
			for(int i = 0;i < fileSize - 1024; i+=1024){
				in.readFully(b);
				dos.write(b);
				tmp -=1024;
			}
			
			byte[] bb = new byte[tmp];
			in.readFully(bb);
			dos.write(bb);
			
			logger.info("Close file and socket");
			
			dos.close();
			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
