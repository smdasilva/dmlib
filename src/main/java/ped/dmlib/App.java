package ped.dmlib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ServerAddressSerialization as = new YamlServerAddressSerialization();

    	ServerAddress s = new ServerAddress();
    	s.addAddress("toto", "127.0.0.1:/documents/toto/image");
    	s.addAddress("titi", "127.0.0.1:/documents/titi/music");
    	
    	StringWriter tmp = new StringWriter();
    	
    	try {
			FileWriter fw = new FileWriter(new File("toto.yaml"));
			s.save(as, fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        s.save(as, tmp);
        System.out.println(tmp);
        
        System.out.println("----------");

        System.out.println(s);
        System.out.println("----------");
        
        ServerAddress s2 = new ServerAddress();
        s2.load(as, tmp.toString());
        
        System.out.println(s2);
        System.out.println("----------");
    }
}
