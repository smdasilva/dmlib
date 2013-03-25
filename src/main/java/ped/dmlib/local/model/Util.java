package ped.dmlib.local.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.DigestInputStream;
import java.security.MessageDigest;


public class Util {
	
	public static void addLineIntoFile(String path, String value) throws IOException {
		BufferedWriter w = new BufferedWriter(new FileWriter(path, true));
		w.write(value + "\r\n");
		w.flush();
		w.close();
	}
	
	public static void clearFile(String path) throws IOException {
		new FileWriter(new File(path)).close();
	}
	
	
	public static void readLineFromFile(String path) throws IOException {
		
			InputStream ips=new FileInputStream(path); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			
			String ligne;
			while ((ligne=br.readLine())!=null){
				System.out.println(ligne);
			}
			br.close(); 
	}
	
	public static String getExt(String path) {
		File f = new File(path);
		return f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
	}
	
	private static String getHexString(byte[] bytes) {  
        StringBuilder sb = new StringBuilder(bytes.length*2);  
        for (byte b : bytes) {  
                        if (b <= 0x0F && b >= 0x00) { // On rajoute le 0 de poid fort ignoré à la conversion.  
                                sb.append('0');  
                        }  
            sb.append( String.format("%x", b) );  
        }  
        return sb.toString();  
    }  
	
	public static String sha1sum(String path){
		File file = new File(path);
        String localSha1Sum = null;  
        if (file.exists() && file.isFile() && file.canRead()){  
    try {  
        MessageDigest md = MessageDigest.getInstance("SHA-1");  
        @SuppressWarnings("resource")
		DigestInputStream dis = new DigestInputStream(new FileInputStream(file), md);  
        dis.on(true);  

        while (dis.read() != -1){  
            ;  
        }  
        byte[] b = md.digest();
        localSha1Sum = getHexString(b); 

    } catch (Exception ex) {  
        ex.printStackTrace();  
    }  
    } else {  
    System.out.println("impossible de trouver le fichier : "+file.getAbsolutePath());  
    }  
        return localSha1Sum;  
	}  
	
	
	public static void writeIntoFile(String repPath, String name, String content) throws FileNotFoundException, IOException {
 
    File rep = new File(repPath);  
    
    if (!rep.exists())
    	{ 
    	rep.mkdirs(); 
    	}
    
    clearFile(repPath+name);
    addLineIntoFile(repPath+name, content);
}
				
	
}
