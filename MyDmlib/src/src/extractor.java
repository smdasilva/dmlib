import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.jaudiotagger.audio.mp3.MP3File;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;


public class extractor {

	ExifSubIFDDirectory directory;
	
	public extractor(File file) throws ImageProcessingException, IOException {
		File jpegFile = file;
		Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
		this.directory = metadata.getDirectory(ExifSubIFDDirectory.class);
		for (Directory directory : metadata.getDirectories()) {
		    for (Tag tag : directory.getTags()) {
		        System.out.println(tag);
		    }
		}
		
	
		
		// obtain the Exif directory
		ExifSubIFDDirectory directory = metadata.getDirectory(ExifSubIFDDirectory.class);

		// query the tag's value
		String s = directory.getDescription(directory.TAG_EXIF_IMAGE_HEIGHT);
		//directory.getDescription(directory.);
		System.out.println(s);
		
	}
}
