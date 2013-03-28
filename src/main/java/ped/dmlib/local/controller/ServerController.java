package ped.dmlib.local.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.sourceforge.jheader.JpegFormatException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import ped.dmlib.connexion.Client;
import ped.dmlib.connexion.MercurialServer;

import com.aragost.javahg.BaseRepository;
import com.aragost.javahg.Repository;
import com.drew.imaging.ImageProcessingException;
import java.util.Map;

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
		} catch (JpegFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, String> addServer(String ip, String port, String repPath)
	{
                Map<String, String> repList = null;
		try {
			repList = c.addServer(ip, port, repPath);
		} catch (IOException e) {
			e.printStackTrace();
		}	
            return repList;
	}
	
	public void updateFile(File f)
	{
		String filePath = f.getAbsolutePath();
		String repName = f.getParentFile().getName();
		try {
			if(c.isHashModified(filePath, repName)) {
				System.out.println("push binaire");
				c.commit(filePath);
				c.binaryPushLibrary(repName);
			}
			else if(c.isMetaModified(filePath, repName)) {
				System.out.println("push meta");
				c.commit(filePath);
				c.push();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CannotReadException e) {
			e.printStackTrace();
		} catch (TagException e) {
			e.printStackTrace();
		} catch (ReadOnlyFileException e) {
			e.printStackTrace();
		} catch (InvalidAudioFrameException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addFile(File f)
	{
		String filePath = f.getAbsolutePath();
		String repName = f.getParentFile().getName();
		
		try {
			c.generateFileMeta(filePath, repName);
			c.add();
			c.commit(filePath);
			c.push();
			c.binaryPushLibrary(repName);
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
		} catch (JpegFormatException e) {
			e.printStackTrace();
		}
	}
	
	public void delFile(File f)
	{
		String filePath = f.getAbsolutePath();
		String repName = f.getParentFile().getName();
		
		c.commit(filePath);
		c.push();
		c.binaryPushLibrary(repName);
	}

	public String getFileName(String repName) {
		return c.localRepository.getLibraryPath(repName);
	}
}
