package ped.dmlib.local.controller;

import java.io.File;
import java.io.IOException;

import ped.dmlib.connexion.Client;
import ped.dmlib.connexion.MercurialServer;

import com.aragost.javahg.BaseRepository;
import com.aragost.javahg.Repository;

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
		
        Thread t1 = new MercurialServer(br1, 8013);
        t1.start();
        
		//Lancement client
		c = new Client(serveurPath);
	}
}
