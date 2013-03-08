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
		BufferedWriter w = new BufferedWriter(new FileWriter(path, true));
		w.write(value + "\n");
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
		/*clearFile("/net/cremi/sdasilva/Documents/dmlib/Client/testWrite");
		addLineIntoFile("/net/cremi/sdasilva/Documents/dmlib/Client/testWrite", "dfmgjdfgjkldfhglj | sesdfsdkljsdfklj | toto");
		addLineIntoFile("/net/cremi/sdasilva/Documents/dmlib/Client/testWrite", "dfmgjdfgjkldfhglj | sesdfsdkljsdfklj | dsfsdklfjsdfl");
		addLineIntoFile("/net/cremi/sdasilva/Documents/dmlib/Client/testWrite", "dfmgjdfgjkldfhglj | sesdfsdkljsdfklj | dsfsdklfjsdfl");
		addLineIntoFile("/net/cremi/sdasilva/Documents/dmlib/Client/testWrite", "dfmgjdfgjkldfhglj | sesdfsdkljsdfklj | dsfsdklfjsdfl");*/
		readLineFromFile("/net/cremi/sdasilva/Documents/dmlib/Client/testWrite");

	}
}
