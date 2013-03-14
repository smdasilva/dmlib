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
	public static void main(String[] args) throws ImageProcessingException, CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ClassNotFoundException, CannotWriteException {
		//Client c = new Client("/net/cremi/sdasilva/Documents/Mercurial/toSynchronize/", "http://picard:8000");
		//c.modificationTreatment();
		//c.storeFileHash("/net/cremi/sdasilva/Documents/Mercurial/toSynchronize/a/b/c/d/e/ok.txt");
		//c.storeFileMeta("/net/cremi/sdasilva/Documents/dmlib/Client/musique.mp3");
		//c.isMp3MetaModifier("/net/cremi/sdasilva/Documents/dmlib/Client/musique.mp3");
		//c.deserializeFileMeta("/net/cremi/sdasilva/Documents/dmlib/Boobs-Caramel.mp3");
		//c.deserializeFileMeta("/net/cremi/sdasilva/Documents/dmlib/musique.mp3");
		//m.getArtist();
		
		// dans une méthode main
		 // on simplifie le code en retirant la gestion des exceptions


		 // ouverture d'un flux sur un fichier
		//ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream("/net/cremi/sdasilva/Documents/dmlib/caca"));
		//Mp3Meta m = new Mp3Meta(new File("/net/cremi/sdasilva/Documents/dmlib/musique.mp3"));
		//m.copyTagToFile(new File("/net/cremi/sdasilva/Documents/dmlib/tag"));
		
		//Mp3Meta m = new Mp3Meta(new File("/net/cremi/sdasilva/Documents/dmlib/t.mp3"));
		//Mp3Meta m2 = new Mp3Meta(new File("/net/cremi/sdasilva/Documents/dmlib/t2.mp3"));
		//m2.setTitle("lkjhkjhkjh");
		//m2.save();
		//m.deleteMeta();
		//m2.deleteMeta();
		//System.out.println(Util.sha1sum("/net/cremi/sdasilva/Documents/dmlib/t.mp3"));
		//System.out.println(Util.sha1sum("/net/cremi/sdasilva/Documents/dmlib/t2.mp3"));

		//m.saveTagToFile("C:/Users/samuel/Desktop/client/t");
		//m.saveTagFromFile("C:/Users/samuel/Desktop/client/t");
		//m.serializeMetas("/net/cremi/sdasilva/Documents/dmlib/Meta/", "ESSAI");
		
		//JPGMeta j = new JPGMeta(new File("/net/cremi/sdasilva/Documents/dmlib/Nikon.jpg"));
		//j.p();
		String installationPath = "/net/cremi/sdasilva/Documents/dmlib";
 	    String[] s = installationPath.split("\\/");
 	    String last = s[s.length-1];
		String klk = installationPath.substring(installationPath.length()-last.length());
		//System.out.println(installationPath.length()-last.length());
		System.out.println(installationPath.substring(0,installationPath.length()-last.length()));
		

	}

}
