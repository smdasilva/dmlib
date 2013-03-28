package ped.dmlib.local.controller;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import ped.dmlib.local.model.Dico;
import ped.dmlib.local.model.JPGMeta;
import ped.dmlib.local.model.Mp3Meta;
import ped.dmlib.local.model.MyFileWatcher;
import ped.dmlib.local.presentation_data.MetaJPGDTO;
import ped.dmlib.local.presentation_data.MetaMP3DTO;
import ped.dmlib.local.presentation_data.MyFileDTO;
import ped.dmlib.local.presentation_data.MyRepDTO;

import com.drew.imaging.ImageProcessingException;

public class LocalController
{
	private Dico myDico;
	private ArrayList<Thread> watcherList;
	private ArrayList<MyFileDTO> fileList;
	
	public LocalController()
	{
		myDico = new Dico();
		watcherList = new ArrayList<Thread>();
		String path = System.getProperty("user.home")+"/SharedMediaCenter/";
		File startFile = new File(path);
		if (! startFile.exists()) {
			startFile.mkdir();
		}
		Path dir = Paths.get(startFile.getAbsolutePath());
		try {
			Thread myWatcher = new MyFileWatcher(dir, true);
			myWatcher.start();
			addFileWatcher(System.getProperty("user.home")+"/Bureau/PED/dmlib/hg/Meta");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addFileWatcher(String repPath)
	{
		File f = new File(repPath);
		if (! f.exists()) {
			f.mkdir();
		}
		Path dir = Paths.get(f.getAbsolutePath());
		try {
			Thread myWatcher = new MyFileWatcher(dir, true);
			watcherList.add(myWatcher);
			myWatcher.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public MyRepDTO constructCurrentTree(File rep)
	{
		int currentID = myDico.add(rep.getAbsolutePath(), "d", null);
		MyRepDTO myRoot = new MyRepDTO(rep.getName(), currentID, rep.getAbsolutePath());
	    constructTree(rep, myRoot);
		return myRoot;
	}
	
	public void constructTree(File file, MyRepDTO node)
	{
		MyRepDTO current;
		if(file.listFiles().length > 0)
		{
		    for (File f : file.listFiles())
		    {
		    	Path myPath = Paths.get(f.getAbsolutePath());
		        if (f.isDirectory() && Files.isReadable(myPath))
		        {
		        	int currentID = myDico.add(f.getAbsolutePath(), "d", Integer.toString(node.getId()));
		        	current = new MyRepDTO(f.getName(), currentID, f.getAbsolutePath());
		        	current.setAllowsChildren(true);
		        	node.add(current);
		        	constructTree(f, current);
		        }
		    }
		}
	}
		
	//renvoie une liste de fichiers a partir du dossier en parametre
	public ArrayList<MyFileDTO> getFileFromRep(String repPath, int idRep)
	{
		File rep = new File(repPath);
		
		if(rep.listFiles().length > 0)
		{
		    for (File f : rep.listFiles())
		    {
		    	Path myPath = Paths.get(f.getAbsolutePath());
		        if (f.isFile() && Files.isReadable(myPath))
		        {
		        	if(getExtension(f) != null) {
		        		int currentID = myDico.add(f.getAbsolutePath(), "File", Integer.toString(idRep));
		        		MyFileDTO currentFile = new MyFileDTO(currentID, f.getName(), getExtension(f), getSize(f), true);
		        		fileList.add(currentFile);
		        	}
		        }
		        else if(f.isDirectory() && Files.isReadable(myPath))
		        {
		        	int currentID = myDico.add(f.getAbsolutePath(), "Dir", Integer.toString(idRep));
		        	getFileFromRep(f.getAbsolutePath(), currentID);
		        }
		    }
		}
		return fileList;
	}
	
	//vide la liste de MyFileDTO
	public void clearList() {
		if(fileList != null) {
			fileList.clear();
		}
		fileList = new ArrayList<MyFileDTO>();
	}
	
	//renovie l'extension du fichier
	public String getExtension(File currentFile) {
		String extension = currentFile.getAbsolutePath().substring(currentFile.getAbsolutePath().indexOf("."));
		if (extension.matches(".mp3") || extension.matches(".jpg")) {
			return extension;
		}
		else {
			return null;
		}
	}
	
	//renovie la taille du fichier
	public String getSize(File currentFile) {
		String size;
		double fileSize = (double)currentFile.length();
		if(fileSize > 1000000) {
			size = String.valueOf(fileSize/(1024*1024));
			size = size.substring(0, size.indexOf(".")+2)+" Mo";
		}
		else if(fileSize > 1000) {
			size = String.valueOf(fileSize/1024);
			size = size.substring(0, size.indexOf(".")+2)+" Ko";
		}
		else {
			size = String.valueOf(fileSize);
			size = size.substring(0, size.indexOf(".")+2)+" Octets";
		}
		return size;
	}
	
	public void pushAction(String filePath)
	{
	}
	
	public MetaMP3DTO createMP3DTO(int fileID)
	{
		File temp = null;
		Mp3Meta meta = null;
		MetaMP3DTO metaDTO = null;
		try {
			temp = new File(myDico.getAbsPath(fileID));
			meta = new Mp3Meta(temp);
			metaDTO = new MetaMP3DTO(fileID, meta.getTitle(), meta.getArtist(), meta.getAlbum(), meta.getYear(), meta.getTrackLenght(), 
					meta.getComment(), meta.getCodec(), meta.getChannel(), meta.getSampleRate(), meta.getBiteRate());
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
		return metaDTO;
	}
	
	public MetaJPGDTO createJPGDTO(int fileID)
	{
		File temp = null;
		JPGMeta meta = null;
		MetaJPGDTO metaDTO = null;
		try {
			temp = new File(myDico.getAbsPath(fileID));
			meta = new JPGMeta(temp);
			metaDTO = new MetaJPGDTO(fileID, meta.getTitle(), meta.getComments(), meta.getAuthor(), meta.getPictureWidth(), meta.getPictureHeight(), 
					meta.getCompression(), meta.getDataPrecision(), meta.getPicturesDate());
		} catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return metaDTO;
	}
	
	public void deleteFile(int fileID)
	{
		File temp = new File(myDico.getAbsPath(fileID));
        File toDel = new File(System.getProperty("user.home")+"/SharedMediaCenter/"+temp.getParentFile().getName()+"/"+temp.getName());
        File toDel2 = new File(System.getProperty("user.home")+"/SharedMediaCenter2/"+temp.getParentFile().getName()+"/"+temp.getName());
        	
    	if(toDel.exists()) {
    		toDel.delete();
    	} else if (toDel2.exists()) {
    		toDel2.delete();
    	}
		temp.delete();
	}
	
	public void renameFile(int fileID, String fileName)
	{
		File temp = null;
		temp = new File(myDico.getAbsPath(fileID));
		File newFile = new File(temp.getAbsolutePath().substring(0, temp.getAbsolutePath().indexOf(temp.getName()))+fileName);
		myDico.replacePath(fileID, temp.getAbsolutePath().substring(0, temp.getAbsolutePath().indexOf(temp.getName()))+fileName);
		temp.renameTo(newFile);
	}
	
	public void updateMP3File(int fileID, String title, String artist, String album, String year, String comments)
	{
		File temp = null;
		Mp3Meta meta = null;
		
		try {
			temp = new File(myDico.getAbsPath(fileID));
			
			meta = new Mp3Meta(temp);
			meta.setTitle(title);
			meta.setArtist(artist);
			meta.setAlbum(album);
			meta.setYear(year);
			meta.setComment(comments);
			meta.save();
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
		} catch (CannotWriteException e) {
			e.printStackTrace();
		}
	}
}
