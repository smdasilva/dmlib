import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

public class main {
	
	public static void main(String[] args) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
		//ID3Reader id3reader = new ID3Reader("C:/Users/samuel/Desktop/test.MP3");
		//id3reader.displayinformations();
		//Mp3Meta mp3 = new Mp3Meta(new File("/net/cremi/sdasilva/Documents/dmlib/dmlib/test.mp3"));
		Mp3Meta mp32 = new Mp3Meta(new File("/net/cremi/sdasilva/Documents/dmlib/dmlib/test1.mp3"));
		//mp3.deleteMeta();
		mp32.deleteMeta();
		//System.out.println(mp3.getTitle());
		
		//System.out.println(mp3.getTitle());

	}
}
