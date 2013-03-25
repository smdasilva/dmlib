package ped.dmlib.local.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.ID3v23Tag;

public class Mp3Meta implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	AudioFile file;
	AudioHeader header;
	Tag tag;
	
	public Mp3Meta(File f) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		file = AudioFileIO.read(f);
		this.header = file.getAudioHeader();
		
		if(file.getTag() instanceof ID3v23Tag) {
			this.tag = file.getTag();
		}
		else {
			file.setTag(new ID3v23Tag());
			this.tag = file.getTag();
		}
	}
		
	/* Getter to access Metadata file */
	public String getTitle() {
		return tag.getFirst(FieldKey.TITLE);
	}
	public String getArtist() {
		return tag.getFirst(FieldKey.ALBUM_ARTIST);
	}
	public String getAlbum() {
		return tag.getFirst(FieldKey.ALBUM);
	}
	public String getYear() {
		return tag.getFirst(FieldKey.YEAR);
	}
	public String getTrackLenght() {
		return String.valueOf(header.getTrackLength()/60+" min "+header.getTrackLength()%60+" sec");
	}
	public String getComment() {
		return tag.getFirst(FieldKey.COMMENT);
	}
	public String getCodec() {
		return header.getFormat()+" ("+header.getEncodingType()+")";
	}
	public String getChannel() {
		return header.getChannels();
	}
	public String getSampleRate() {
		return header.getSampleRate()+" Hz";
	}
	public String getBiteRate() {
		return header.getBitRate()+" Kb/s";
	}

	/* Setter to modify the Metadata file */
	public void setTitle(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.TITLE,value);
	}
	public void setArtist(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.ALBUM_ARTIST,value);
	}
	public void setAlbum(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.ALBUM,value);	
	}
	public void setYear(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.YEAR,value);
	}
	public void setComment(String value) throws KeyNotFoundException, FieldDataInvalidException {
		tag.setField(FieldKey.COMMENT,value);
		System.out.println(value+" test "+tag.getFirst(FieldKey.COMMENT));
	}
	
	public void save() throws CannotWriteException{
		this.file.commit();
	}

	/*public void saveTagToFile(String path) throws IOException {	
		Util.clearFile(path);
		
		for(FieldKey fk : FieldKey.values()) {
			if (tag.getFirst(fk) != "") {
			Util.addLineIntoFile(path, fk +"|"+ tag.getFirst(fk));
			}
		}	
	}
	public void saveTagFromFile(String path) throws IOException, KeyNotFoundException, FieldDataInvalidException, CannotWriteException {
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
	}*/
}
