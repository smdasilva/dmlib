import java.io.File;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;


public class Mp3File {
	
	MP3File file;
	
	public Mp3File(File f) {
		this.file = (MP3File) AudioFileIO.read(f);
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

}
