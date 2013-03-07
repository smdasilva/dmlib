import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;


public class JPGMeta {

	ExifSubIFDDirectory directory;
	
	public JPGMeta(File file) throws ImageProcessingException, IOException {
		Metadata metadata = ImageMetadataReader.readMetadata(file);
		this.directory = metadata.getDirectory(ExifSubIFDDirectory.class);
	}
	
	public String getAperture() {
		return directory.getDescription(directory.TAG_APERTURE);
	}

}
