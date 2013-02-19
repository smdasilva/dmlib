import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CompareFile {
/*
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		//boolean resultat = compare("/net/cremi/sdasilva/Documents/Shaun.jpg","/net/cremi/sdasilva/Desktop/photo-dr.jpg");
		//System.out.println(resultat);
		
		String  sortie = sha1sum(new File("/net/cremi/sdasilva/Documents/dmlib/dmlib/Caramel.mp3"));
	    String  sortie2 = sha1sum(new File("/net/cremi/sdasilva/Documents/dmlib/dmlib/Caramel2.mp3"));
		
	    System.out.println(sortie);
	    System.out.println(sortie2);
	}
*/	
	  /** 
     * compare le <code>sha1sum</code> donné avec l'empreinte du fichier local <code>file</code>. 
     * @param file le fichier dont on doit calculer l'empreinte sha1 
     * @param sha1sum l'empreinte à comparer 
     * @return true si l'empreinte du fichier correspond à <code>sha1sum</code> 
     */  
    public static boolean compareSha1sum(File file, String sha1sum) {  
            boolean res = false;  
    if (sha1sum.equals(sha1sum(file))) {  
                    res = true;  
            }  
            return res;  
    }  

    /** 
     * Retourne l'empreinte sha1 de fichier <code>file</code> ou null si le 
     * fichier n'a pas pu être lu. 
     * @param file le fichier dont on doit calculer l'empreinte sha1 
     * @return l'empreinte sha1 
     */  
    public static String sha1sum(File file){  
            String localSha1Sum = null;  
            if (file.exists() && file.isFile() && file.canRead()){  
        try {  
            MessageDigest md = MessageDigest.getInstance("SHA-1");  
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
    
    
    
    public static boolean compareMd5sum(File file, String md5sum) {  
        boolean res = false;  
if (md5sum.equals(md5sum(file))){  
                res = true;  
        }  
        return res;  
}  

/** 
 * Retourne l'empreinte md5 de fichier <code>file</code> ou null si le 
 * fichier n'a pas pu être lu. 
 * @param file le fichier dont on doit calculer l'empreinte md5 
 * @return l'empreinte md5 
 */  
public static String md5sum(File file) {  
        String localMd5Sum = null;  
        if (file.exists() && file.isFile() && file.canRead()){  
    try {  
        MessageDigest md = MessageDigest.getInstance("MD5");  
        DigestInputStream dis = new DigestInputStream(new FileInputStream(file), md);  
        dis.on(true);  

        while (dis.read() != -1){  
            ;  
        }  
        byte[] b = md.digest();  
        localMd5Sum = getHexString(b);  
    } catch (Exception ex) {  
        ex.printStackTrace();  
    }   
} else {  
    System.out.println("impossible de trouver le fichier : "+file.getAbsolutePath());  
}  
        return localMd5Sum;  
}  
    
}
    