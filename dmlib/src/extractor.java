import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;


public class extractor {

	public static void main(String[] args) throws ImageProcessingException, IOException {
		File jpegFile = new File("C:/Users/samuel/Desktop/P1060555.JPG");
		Metadata metadata = ImageMetadataReader.readMetadata(jpegFile);
		
		for (Directory directory : metadata.getDirectories()) {
		    for (Tag tag : directory.getTags()) {
		        System.out.println(tag);
		    }
		}
		
	
		
		// obtain the Exif directory
		ExifSubIFDDirectory directory = metadata.getDirectory(ExifSubIFDDirectory.class);

		// query the tag's value
		String s = directory.getDescription(directory.TAG_EXIF_IMAGE_HEIGHT)  ;
		System.out.println(s);
		
	}
}
