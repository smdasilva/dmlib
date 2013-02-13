import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;


public class Mp3Meta {
	MP3File file;
	Tag tag;
	
	public Mp3Meta(File f) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		file = (MP3File) AudioFileIO.read(f);
		this.tag = file.getTag();
	}
	
	
	// Getter pour acceder aux metas du fichier
	public String getArtist() {
		return tag.getFirst(FieldKey.ARTIST);
	}
	
	public String getAlbum() {
		return tag.getFirst(FieldKey.ALBUM);
	}
	
	public String getTitle() {
		return tag.getFirst(FieldKey.TITLE);
	}
	
	public String getComment() {
		return tag.getFirst(FieldKey.TITLE);
	}
	
	public String getYear() {
		return tag.getFirst(FieldKey.YEAR);
	}
	
	public String getTrack() {
		return tag.getFirst(FieldKey.TRACK);
	}
	
	public String getDisc() {
		return tag.getFirst(FieldKey.DISC_NO);
	}
	
	public String getComposer() {
		return tag.getFirst(FieldKey.COMPOSER);
	}
	
	// Setter pour modifier les metas
	
	public void setArtist(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.ARTIST,value);
	}
	
	public void setAlbum(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.ALBUM,value);
	}
	
	public void setTitle(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.TITLE,value);
	}
	
	public void setComment(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.COMMENT,value);
	}
	
	public void setYear(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.YEAR,value);
	}
	
	public void setTrack(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.TRACK,value);
	}
	
	public void setDisc(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.DISC_NO,value);
	}
	
	public void composer(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.COMPOSER,value);
	}
	
	public void save() throws CannotWriteException{
		this.file.commit();
	}

}
