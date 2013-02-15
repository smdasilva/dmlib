import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.SocketServer

public class ClientSocket{
	
	static final int port = 6671;
	
	public static void main(String[] args)
	{
		Socket socket = new Socket(args[0], port);
		System.out.println("SOCKET = " + socket + " avec le port" + port);
		
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject("test yo" );
		
        
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        System.out.println("Message: " + message);
        
        ois.close();
        oos.close();
	}
	
	
}