import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class JPGMeta {

	ExifSubIFDDirectory directory;
	Metadata metadata;

	public JPGMeta(File file) throws ImageProcessingException, IOException {
		this.metadata = ImageMetadataReader.readMetadata(file);
		this.directory = metadata.getDirectory(ExifSubIFDDirectory.class);
	}
	
	public void p() {
		for (Directory directory : metadata.getDirectories()) {
		    for (Tag tag : this.directory.getTags()) {
		    }
		}
	}
	
	public String getAperture() {
		return directory.getDescription(directory.TAG_APERTURE);
	}
	
	
	

}
