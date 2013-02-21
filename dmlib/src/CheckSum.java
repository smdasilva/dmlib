import java.io.*;
import java.util.zip.CheckedInputStream;
import java.util.zip.Adler32;

public class CheckSum {

	public static long calculChecksum(String adresse_fichier) {
	    try {
	        FileInputStream fis = new FileInputStream(adresse_fichier);
	        CheckedInputStream cis = new CheckedInputStream(fis, new Adler32());
	        byte[] tempBuf = new byte[128];
	        while (cis.read(tempBuf) >= 0) {}
	        long checksum = cis.getChecksum().getValue();
	 	return checksum;	       
	    } catch (IOException e) {
	     System.out.println("Error occuring during the Checksum calcul" );
	     return 0;
	    }
	}
	
	public static void main(String[] args) {
		System.out.println(calculChecksum("/net/cremi/sdasilva/Documents/dmlib/dmlib/musique.mp3"));
		System.out.println(calculChecksum("/net/cremi/sdasilva/Documents/dmlib/dmlib/musique2.mp3"));
	}
	
}