import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CompareFile {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		//boolean resultat = compare("/net/cremi/sdasilva/Documents/Shaun.jpg","/net/cremi/sdasilva/Desktop/photo-dr.jpg");
		//System.out.println(resultat);
		
		byte[]  sortie = checkmd5("/net/cremi/sdasilva/Documents/Shaun.jpg");
		
		//convert the byte to hex format
	    StringBuffer sb = new StringBuffer("");
	    for (int i = 0; i < sortie.length; i++) {
	    	sb.append(Integer.toString((sortie[i] & 0xff) + 0x100, 16).substring(1));
	    }
	 
	    System.out.println("Digest(in hex format):: " + sb.toString());
	    
	    byte[]  sortie2 = checkmd5("/net/cremi/sdasilva/Desktop/photo-dr.jpg");
		
		//convert the byte to hex format
	    StringBuffer sb2 = new StringBuffer("");
	    for (int i = 0; i < sortie2.length; i++) {
	    	sb2.append(Integer.toString((sortie2[i] & 0xff) + 0x100, 16).substring(1));
	    }
	 
	    System.out.println("Digest(in hex format):: " + sb2.toString());
	
	}
	
	public static boolean compare(String f1, String f2) throws NoSuchAlgorithmException, FileNotFoundException {

		
		MessageDigest digest = MessageDigest.getInstance("MD5");
		File file1 = new File(f1);
		InputStream is1 = new FileInputStream(file1);
		is1 = new DigestInputStream(is1, digest);
		byte[] dataToHash1 = digest.digest();
		
		

		MessageDigest digest2 = MessageDigest.getInstance("MD5");
		File file2 = new File(f2);
		InputStream is2 = new FileInputStream(file2);
		is2 = new DigestInputStream(is2, digest2);
		byte[] dataToHash2 = digest2.digest();

		 StringBuffer sb = new StringBuffer("");
		    for (int i = 0; i < dataToHash1.length; i++) {
		    	sb.append(Integer.toString((dataToHash1[i] & 0xff) + 0x100, 16).substring(1));
		    }
		 
		  System.out.println("Digest(in hex format):: " + sb.toString());
		  
		  
		  StringBuffer sb2 = new StringBuffer("");
		    for (int i = 0; i < dataToHash2.length; i++) {
		    	sb2.append(Integer.toString((dataToHash2[i] & 0xff) + 0x100, 16).substring(1));
		    }
		 
		  System.out.println("Digest(in hex format):: " + sb.toString());
		
		return(Arrays.equals(dataToHash1, dataToHash2)); 

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