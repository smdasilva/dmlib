package mercurial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Util {
	
	public static void addLineIntoFile(String path, String value) throws IOException {                               
                File repository = new File(path);
                if (!repository.exists())
                {     repository.mkdirs(); }
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
	
				
	public static void main(String[] argv) throws IOException {
		clearFile("C:/Users/samuel/Desktop/client/t");
		addLineIntoFile("C:/Users/samuel/Desktop/client/t", "dfmgjdfgjkldfhglj | sesdfsdkljsdfklj | toto");
		addLineIntoFile("C:/Users/samuel/Desktop/client/t", "dfmgjdfgjkldfhglj | sesdfsdkljsdfklj | dsfsdklfjsdfl");

		/*
		 * * sous Mac OS (Apple Macintosh), la fin de ligne est indiqu�e par un retour de chariot (CR) ;
		 * sous Unix ou Linux, la fin de ligne est indiqu�e par un saut de ligne (LF) ;
		 * sous Microsoft Windows, la fin de ligne est indiqu�e par un retour chariot suivi d'un saut de ligne (CRLF).
		 */
	}
}
