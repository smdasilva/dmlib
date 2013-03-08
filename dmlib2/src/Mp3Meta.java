import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

import org.jaudiotagger.audio.AudioFile;
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
import org.jaudiotagger.tag.TagField;


public class Mp3Meta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	AudioFile file;
	Tag tag;
	
	public Mp3Meta(File f) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		file = AudioFileIO.read(f);
		this.tag = file.getTag();
	}
	
	
	public Tag getTag() {
		return this.tag;
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
	
	public String getGenre() {
		return tag.getFirst(FieldKey.GENRE);
	}
	
	public String getTotalTrack() {
		return tag.getFirst(FieldKey.TRACK_TOTAL);
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
	
	public void setComposer(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.COMPOSER,value);
	}
	
	public void setGenre(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.GENRE,value);
	}
	
	public void setTotalTrack(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.TRACK_TOTAL,value);
	}
	
	
	public void save() throws CannotWriteException{
		this.file.commit();
	}
	
	public void deleteMeta() throws CannotReadException, CannotWriteException {
		AudioFileIO.delete(this.file);
	}
	
	public void copyTagToFile(File destination) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
		AudioFile dest = AudioFileIO.read(destination);
		dest.setTag(this.tag);
		dest.commit();
	}
	
	public boolean compareTag(Mp3Meta mp3Meta) {
		return this.tag.equals(mp3Meta.getTag());
	}
	
	public boolean compareFile(File fileToCompare) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		AudioFile toCompare = AudioFileIO.read(fileToCompare);
		Tag t = toCompare.getTag();
		return t.equals(this.tag);
	}
	
	public void serializeMetas(String MetaPath, String nameFile) throws FileNotFoundException, IOException {
		File path = new File(MetaPath);  
		if (!path.exists())
		  { path.mkdirs(); }
		
		ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(MetaPath+"/"+ nameFile));
        Mp3MetaSerializable mp3 = new Mp3MetaSerializable(getArtist(), getAlbum(), getTitle(), getYear(), getComment(), getTrack(), getDisc(), getComposer(), getGenre(), getTotalTrack());
        out.writeObject(mp3);
   		out.flush();
   		out.close();		
	}
	
	public void setAllMetas(Mp3MetaSerializable metas) throws KeyNotFoundException, FieldDataInvalidException {
		setAlbum(metas.getAlbum());
		setArtist(metas.getArtist());
		setComment(metas.getComment());
		setComposer(metas.getComposer());
		setDisc(metas.getDisc());
		setGenre(metas.getGenre());
		setTitle(metas.getTitle());
		setTotalTrack(metas.getTotalTrack());
		setTrack(metas.getTrack());
		setYear(metas.getYear());
	}
	
	
	
}
