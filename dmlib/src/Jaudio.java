import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;


public class Jaudio {

	public static void main(String[] args) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
		MP3File f = (MP3File) AudioFileIO.read(new File("C:/Users/samuel/Desktop/test.MP3"));
		//MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
		Tag tag = f.getTag();
		
		
		String artiste = tag.getFirst(FieldKey.ARTIST);
		String album = tag.getFirst(FieldKey.ALBUM);
		String titre = tag.getFirst(FieldKey.TITLE);
		String comment = tag.getFirst(FieldKey.COMMENT);
		String annee = tag.getFirst(FieldKey.YEAR);
		String track = tag.getFirst(FieldKey.TRACK);
		String disc = tag.getFirst(FieldKey.DISC_NO);
		String composer = tag.getFirst(FieldKey.COMPOSER);
		
		System.out.println("artiste : "+artiste);
		System.out.println("album : "+album);
		System.out.println("titre : "+titre);
		System.out.println("comment : "+comment);
		System.out.println("année : "+annee);
		System.out.println("track : "+track);
		System.out.println("disc : "+disc);
		System.out.println("composer : "+composer);
		System.out.println("-------------");
		//tag.setField(FieldKey.ARTIST,"Sam");
		//f.commit();

		


	}
};
