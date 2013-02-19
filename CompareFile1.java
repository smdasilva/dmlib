package vue;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CompareFile1 {

	public boolean compare(File f1, File f2) {

		
		byte[] hashMD5_file1 = null;
		byte[] hashMD5_file2 = null;

		
		byte[] dataToHash1 = f1.getAbsolutePath().getBytes();

		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(dataToHash1);
			hashMD5_file1 = digest.digest(dataToHash1);
			
		} 
		catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		byte[] dataToHash2 = f2.getAbsolutePath().getBytes();

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
