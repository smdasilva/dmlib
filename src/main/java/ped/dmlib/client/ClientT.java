import java.util.*;
import java.net.*;
import java.io.*;
public class ClientT{
    public static void main(String[] argv){
	try{


	    InetAddress adresse;
	    //File f = new File("/net/cremi/abndoye/Desktop/TheTest/04.jpg");
	     File f = new File("/net/cremi/abndoye/Desktop/TheTest/Wild.flv");
	    RandomAccessFile outFile = new RandomAccessFile(f,"r" ); 
	    
	    
	    int nbPort = 6666;
	    long size =  outFile.length();
	    String name = f.getName();
	    System.out.println(name + (double) size + " B");

	    //FileInputStream outFile = new FileInputStream(new File("/home/abndoye/Bureau/TheTest/04.jpg"));
	   
	    
	    //nouvelle socket pour le transfert du fichier
	    //Socket socketClient = new Socket(InetAddress.getLocalHost(),6671); 
	    Socket socketClient = new Socket(argv[0],6671); 
	    
	    // DataOutputStream dataOut = new DataOutputStream(socketClient.getOutputStream());
	    //DataOutputStream dataOut1 = new DataOutputStream(socketClient.getOutputStream());
	    // DataOutputStream dataOut2 = new DataOutputStream(socketClient.getOutputStream());
	    
	   
	    
	    

	    OutputStream out2 = socketClient.getOutputStream(); 
	       /** PrintWriter nameFile = new PrintWriter(out2);
	    PrintWriter sizeFile = new PrintWriter(out2);
	    PrintWriter nbPort = new PrintWriter(out2);	    	    
	    nameFile.write(name +"  ");
	    sizeFile.write(Long.toString(size)+ "   ");//String.ValueOf(Long size));
	    nbPort.write(Integer.toString(6671));
	    nameFile.flush();
	    sizeFile.flush();
	    nbPort.flush();*/

	    // dataOut.writeUTF(name);
	    //dataOut1.writeUTF(Long.toString(size));
	    //dataOut2.writeUTF(Integer.toString(6666));
	    
	    
	    //nameFile.write(name);
	    PrintWriter sizeFile = new PrintWriter(out2);
	    sizeFile.write("addFile " + "toto " + name + " " + Long.toString(size) + " " + nbPort);
	    sizeFile.flush();

	    out2.close();
	    //out.close();
	    //outFile.close();
	    //socketClient.close();


	    /*byte bb[] = new byte[1024];
	    int amount;
	    while((amount = outFile.read(bb)) != -1){
               out.write(bb, 0,amount);
	    }
	    out.flush();*/

	    ServerSocket socketServeur = new ServerSocket(nbPort); 
	    Socket c = socketServeur.accept();

	    ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());


	    byte bb[] = new byte[1024];
	    //byte bb[] = new byte[65000];
	    int amount;
	    while((amount = outFile.read(bb)) != -1){
               out.write(bb, 0,amount);
	    }
	    out.flush();

	    
	    //socketClient.close();
	    //c.close();
	}catch(IOException e){System.out.println(e);} 
	
	
    }
     
}
