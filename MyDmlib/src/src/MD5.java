import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;


public class MD5 {
	
	public static void main(String[] args) throws Exception {
		//long sum = calculChecksum("/net/cremi/sdasilva/Documents/dmlib/dmlib/Damian.mp3");
		//System.out.println(sum);
		//byte[]  sortie = test("/net/cremi/sdasilva/Documents/dmlib/dmlib/Damian.mp3");
		//System.out.println(sortie);
		//checkSHA("/net/cremi/sdasilva/Documents/dmlib/dmlib/Damian.mp3");
		byte[]  sortie = checkmd5("/net/cremi/sdasilva/Documents/dmlib/dmlib/Damian.mp3");
		
		//convert the byte to hex format
	    StringBuffer sb = new StringBuffer("");
	    for (int i = 0; i < sortie.length; i++) {
	    	sb.append(Integer.toString((sortie[i] & 0xff) + 0x100, 16).substring(1));
	    }
	 
	    System.out.println("Digest(in hex format):: " + sb.toString());
	}
	
	public static byte[] test(String adresse_fichier) throws IOException, NoSuchAlgorithmException, NoSuchProviderException {
		File file = new File(adresse_fichier);
		byte[] buffer = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(buffer);
		fis.close();
		MessageDigest md = MessageDigest.getInstance("MD5", "FlexiCore");
		md.update(buffer);
		byte[] digest = md.digest();
		return digest;
	}
	
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
	
	 public static void checkSHA(String fichier)throws Exception
	    {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        FileInputStream fis = new FileInputStream(fichier);
	 
	        byte[] dataBytes = new byte[1024];
	 
	        int nread = 0; 
	        while ((nread = fis.read(dataBytes)) != -1) {
	          md.update(dataBytes, 0, nread);
	        };
	        byte[] mdbytes = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < mdbytes.length; i++) {
	          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	 
	        System.out.println("Hex format : " + sb.toString());
	 
	       //convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<mdbytes.length;i++) {
	    	  hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
	    	}
	 
	    	System.out.println("Hex format : " + hexString.toString());
	    }
	 
	 public static byte[] checkmd5(String fichier) throws NoSuchAlgorithmException, IOException {
		 MessageDigest md = MessageDigest.getInstance("MD5");
		 InputStream is = new FileInputStream(fichier);
		 try {
		   is = new DigestInputStream(is, md);
		   // read stream to EOF as normal...
		 }
		 finally {
		   is.close();
		 }
		 byte[] digest = md.digest();
		 return digest;
	 }
	 

}
