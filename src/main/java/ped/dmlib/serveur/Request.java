package ped.dmlib.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

public class Request {
	
    private static Logger logger = Logger.getLogger(Request.class);


	protected Socket socket = null;
	protected String args[] = null;
	
	
	public Request(Socket socket) {
		
		/*try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = in.readLine();
			args = msg.split(" ");
			
			logger.info("Request 1 from: " + socket.getInetAddress() + " => " + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		*/	
		
		
		this.socket = socket;
		
		this.init();
	}
	
	private void init(){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = in.readLine();			
			args = msg.split(" ");
			
			logger.info("Request from: " + socket.getInetAddress() + " => " + msg);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getAction(){
		if(args.length > 0)
			return args[0];
		
		return null;
	}

	public String[] getArgs() {
		return args;
	}
	
	public OutputStream getOutputStream () throws IOException{
		return socket.getOutputStream();
	}

	public InetAddress getInetAddress() {
		return socket.getInetAddress();
	}

}
