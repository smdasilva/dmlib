package ped.dmlib.serveur;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;


public class Serveur {

	public static void main( String[] args )
	{
		System.out.println("Main Server");
		Serveur serveur = new Serveur(6671);
	}
	
    private static Logger logger = Logger.getLogger(Serveur.class);

	private ServerSocket socketserver;
	private int port;
	private BufferedReader in;

	public Serveur(int port) {
		super();
		this.port = port;
		receiveMsg();
	}

	private boolean startServer(){
		try {
			socketserver = new ServerSocket(port);
			logger.info("Server start: "+ port);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private void stopServer(){
		try {
			socketserver.close();
			logger.info("Server stop");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void receiveMsg(){
		if(startServer()){
			try {			
				while(true){
					final Socket socket = socketserver.accept();

					logger.info("Connection from " + socket.getInetAddress());
					
					Handler fh = new FileHandler();
					
					Request request = new Request(socket);
					fh.handleRequest(request);			
				}
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
}
