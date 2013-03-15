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
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
	public static void main(String[] args) throws ImageProcessingException, CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ClassNotFoundException, CannotWriteException, FileNotFoundException, JpegFormatException, JAXBException{
		
                
                //Client c = new Client("/home/abndoye/Bureau/SUPASERV/", "http://lechapelain:8000");
                //Client c = new Client("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/", "http://lechapelain:8000");
		//c.serializeFileMeta("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/Nikon.jpg");
                
            
                //JPGMeta jpg = new JPGMeta("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/01.jpg");
               


                
                Client c = new Client("/net/cremi/abndoye/Desktop/espaces/travail/MYSUPATEST/Test1");
                
                
                
                
                //jpg.setTittle();
                //jpg1.setTittle();
                //JPGMeta jpg = new JPGMeta("/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/b.jpg");
                
               
                

                //jpg.saveTagFromFile("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/a.jpg", "/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/toto");
                
                
                //jpg.saveTagFromFile("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/01.jpg", "/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/toto");

                //jpg.saveTagToFile("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/toto");
                
                //System.out.println(Util.sha1sum("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/a.jpg"));
                //System.out.println(Util.sha1sum("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/b.jpg"));
                
                //System.out.println(Util.calculChecksum("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/a.jpg"));
                //System.out.println(Util.calculChecksum("/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Link/ING/Park/b.jpg"));
                
                
                
                
                
                
                //jpg.saveTagFromFile("/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/b.jpg,", "/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/toto");
                //jpg.saveTagToFile("/home/abndoye/Bureau/SUPASERV/MyC/toto/Darkness/toto");
                
                //jpg.setTittle();
                //jpg1.setTittle();

                //jpg.removeRadicalTag();
                //jpg1.removeRadicalTag();

	}

}
