package ped.dmlib;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import ped.dmlib.connexion.Client;
import ped.dmlib.connexion.MercurialServer;

import com.aragost.javahg.BaseRepository;
import com.aragost.javahg.Repository;
import com.drew.imaging.ImageProcessingException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, ImageProcessingException, CannotReadException, TagException, ReadOnlyFileException, InvalidAudioFrameException, InterruptedException
    {
    	String test = ".";
    	
    	BaseRepository br1;
    	String serveur1Path = test + "serveur1/hg/";
    	
    	File f1 = new File(serveur1Path);
    			
        try {
         br1 = Repository.open(f1);
        } catch (IllegalArgumentException iae) {
         br1 = Repository.create(f1);
        }   
		br1.close();
		
		
        Thread t1 = new MercurialServer(br1, 8013);
        t1.start();
        
        
        
        BaseRepository br2;
    	String serveur2Path = test + "serveur2/hg/";
    	
    	File f2 = new File(serveur2Path);
    			
        try {
         br2 = Repository.open(f2);
        } catch (IllegalArgumentException iae) {
         br2 = Repository.create(f2);
        }   
		br2.close();
		
		
        Thread t2 = new MercurialServer(br2, 8023);
        t2.start();
        
        //Lancement client
        Client c = new Client(serveur1Path);
        Client c2 = new Client(serveur2Path);
        //Thread.sleep(5000);
        c2.addRepository("/autofs/netapp/travail/bnoleau/project/serveur2/bin/music/");
        c.addServer("http://localhost:8023");
        
        
    }
}
