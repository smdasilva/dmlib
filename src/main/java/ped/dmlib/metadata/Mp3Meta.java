package ped.dmlib.metadata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import ped.dmlib.Util;


public class Mp3Meta implements Serializable {

	private static final long serialVersionUID = 1L;

	AudioFile file;
	Tag tag;

	public Mp3Meta(File f) throws CannotReadException, IOException, TagException, 
								ReadOnlyFileException, InvalidAudioFrameException {
		file = AudioFileIO.read(f);
		this.tag = file.getTag();
	}

	
	public Tag getTag() {
		return this.tag;
	}

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

	public void deleteMeta() throws CannotReadException, CannotWriteException,
								KeyNotFoundException, FieldDataInvalidException {
		//AudioFileIO.delete(this.file);
		for(FieldKey fk : FieldKey.values()) {

			if(fk != FieldKey.COVER_ART && fk != FieldKey.ENGINEER && 
					fk != FieldKey.PRODUCER && fk != FieldKey.ARRANGER && 
					fk != FieldKey.DJMIXER && fk != FieldKey.MIXER) {
				try { 
					tag.setField(fk, "1"); 
				} catch(Exception e) {
					System.out.println(e); 
				}
			}
		}
		save();
	}

	public void saveTagToFile(String path, String name) throws IOException {

		for(FieldKey fk : FieldKey.values()) {
			if (tag.getFirst(fk) != "") {
				Util.writeIntoFile(path,name ,fk +"|"+ tag.getFirst(fk));
			}
		}	
	}

	public void saveTagFromFile(String path) throws IOException, KeyNotFoundException,
												FieldDataInvalidException, CannotWriteException {
		InputStream ips=new FileInputStream(path); 
		InputStreamReader ipsr=new InputStreamReader(ips);
		BufferedReader br=new BufferedReader(ipsr);
		String line;

		while ((line=br.readLine())!=null){
			String[] str = line.split("\\|");

			if(!this.tag.getFirst(FieldKey.valueOf(str[0])).equals(str[1])) {
				this.tag.setField(FieldKey.valueOf(str[0]), str[1]);
			}    
		}
		br.close(); 
		save();
	}

}
