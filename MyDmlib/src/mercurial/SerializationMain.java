package mercurial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

public class SerializationMain {

	static public void main(String ...args) throws CannotReadException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		try {
			// création d'une personne
			Mp3Meta p = new Mp3Meta(new File("/net/cremi/sdasilva/Documents/dmlib/musique.mp3"));
			
			System.out.println("creation de : " + p);

			// ouverture d'un flux de sortie vers le fichier "personne.serial"
			FileOutputStream fos = new FileOutputStream("/net/cremi/sdasilva/Documents/dmlib/test.serial");

			// création d'un "flux objet" avec le flux fichier
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			try {
				// sérialisation : écriture de l'objet dans le flux de sortie
				oos.writeObject(p); 
				// on vide le tampon
				oos.flush();
				System.out.println(p + " a ete serialise");
			} finally {
				//fermeture des flux
				try {
					oos.close();
				} finally {
					fos.close();
				}
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}