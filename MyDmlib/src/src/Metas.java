import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import com.drew.imaging.ImageProcessingException;


public abstract class Metas {

	Metas meta;
	
	public void Metadata(File f) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ImageProcessingException {
		    String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
		    if(ext == ".mp3") {
		    	this.m = new Mp3Meta(f);	
		    	} 
		    else if (ext == ".jpg") {
			this.m = new JPGMeta(f);
		}	
	}
	

	
