package ped.dmlib.local.controller;

import java.io.File;
import java.io.FileNotFoundException;
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

public class ServerController 
{
	Client c;
	
	public ServerController() throws IOException
	{    	
    	BaseRepository br1;
    	String serveurPath = "./hg/";
    	
    	File f1 = new File(serveurPath);
    			
        try {
         br1 = Repository.open(f1);
        } catch (IllegalArgumentException iae) {
         br1 = Repository.create(f1);
        }   
		br1.close();
		
        Thread t1 = new MercurialServer(br1, 8000);
        t1.start();
        
		//Lancement client
		c = new Client(serveurPath);
	}
	
	public void addRepository(File rep)
	{
		try {
			c.addRepository(rep);
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CannotReadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			e.printStackTrace();
		}
	}
	
	public void addServer(String ip, String port, String repPath)
	{
		try {
			c.addServer(ip, port, repPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
