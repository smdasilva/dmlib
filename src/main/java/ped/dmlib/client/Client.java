package ped.dmlib.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main( String[] args )
	{
		System.out.println("Main Client");
		Client client = new Client("bowmore", 6671);

		client.sendMsg("addFile Image toto.jpg 65129 7524");
	}

	private Socket socket;
	private String host;
	private int port;


	public Client(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	private boolean connexion(){
		try {
			socket = new Socket(host, port);
			if (socket == null)
				return false;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}


	public void sendMsg(String msg){
		if(connexion()){
			try {
				PrintWriter out = new PrintWriter(socket.getOutputStream());

				out.println(msg);
				out.flush();
				out.close();

				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
