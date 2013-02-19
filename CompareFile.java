package vue;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CompareFile {

	public boolean compare(File f1, File f2) {

		
		byte[] hashMD5_file1 = null;
		byte[] hashMD5_file2 = null;

		File file1 = new File("C:\\Documents\f1.mp3");
		byte[] dataToHash1 = file1.getAbsolutePath().getBytes();

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(dataToHash1);
			hashMD5_file1 = digest.digest(dataToHash1);
			
		} 
		catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		File file2 = new File("C:\\Documents\f2.mp3");
		byte[] dataToHash2 = file2.getAbsolutePath().getBytes();

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(dataToHash2);
			hashMD5_file2 = digest.digest(dataToHash2);
			
		} 
		catch (NoSuchAlgorithmException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		return(Arrays.equals(hashMD5_file1, hashMD5_file2)); 

	}
}
