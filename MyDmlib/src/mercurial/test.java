package mercurial;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import com.drew.imaging.ImageProcessingException;
import java.io.FileNotFoundException;
import net.sourceforge.jheader.JpegFormatException;


public class test {

	/**
	 * @param args
	 * @throws InvalidAudioFrameException 
	 * @throws ReadOnlyFileException 
	 * @throws TagException 
	 * @throws IOException 
	 * @throws CannotReadException 
	 * @throws ImageProcessingException 
	 * @throws ClassNotFoundException 
	 * @throws CannotWriteException 
	 */
	public static void main(String[] args) throws ImageProcessingException, CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ClassNotFoundException, CannotWriteException, FileNotFoundException, JpegFormatException{
		
                
                
                //Client c = new Client("/home/abndoye/Bureau/SUPASERV/", "http://lechapelain:8000");
                //Client c = new Client("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/", "http://lechapelain:8000");
		//c.serializeFileMeta("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/Nikon.jpg");
                
            
                JPGMeta jpg = new JPGMeta("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/a.jpg");
                //JPGMeta jpg = new JPGMeta("/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/Nikon.jpg");
            
            
                jpg.saveTagToFile("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/toto", "/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/toto");
                //System.out.println(0x65);
                //jpg.test();
                //jpg.setColor("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/Nikon.jpg");
                jpg.test("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/a.jpg");
                
                //jpg.test("/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/Nikon.jpg");
                //jpg.saveTagToFile("/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/Nikon.jpg", "/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/toto.txt" );
                //c.serializeFileMeta("/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/Blue_Dock.jpg");
                //c.serializeFileMeta("/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/LinkingPark.mp3");
                //c.serializeFileMeta("/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/12.jpg");
                //c.serializeFileMeta("/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/Nikon.jpg");
                //c.hashedFile("/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/Blue_Dock.jpg");
                //c.hashedFile("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/04.jpg");
		//c.deserializeFileMeta("/net/cremi/sdasilva/Documents/dmlib/Boobs-Caramel.mp3");
		//c.deserializeFileMeta("/net/cremi/sdasilva/Documents/dmlib/musique.mp3");
		//m.getArtist();
		
		// dans une méthode main
		 // on simplifie le code en retirant la gestion des exceptions


		 // ouverture d'un flux sur un fichier
		//ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream("/net/cremi/sdasilva/Documents/dmlib/caca"));
		//Mp3Meta m = new Mp3Meta(new File("/net/cremi/sdasilva/Documents/dmlib/musique.mp3"));
		//m.copyTagToFile(new File("/net/cremi/sdasilva/Documents/dmlib/tag"));
		
		//Mp3Meta m = new Mp3Meta(new File("/net/cremi/sdasilva/Documents/dmlib/musique.mp3"));
		//m.serializeMetas("/net/cremi/sdasilva/Documents/dmlib/Meta/", "ESSAI");

	}

}
