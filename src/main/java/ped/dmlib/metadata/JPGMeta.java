package ped.dmlib.metadata;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

public class JPGMeta {

	ExifSubIFDDirectory directory;
	Metadata metadata;

	public JPGMeta(File file) throws ImageProcessingException, IOException {
		this.metadata = ImageMetadataReader.readMetadata(file);
		this.directory = metadata.getDirectory(ExifSubIFDDirectory.class);
	}
}
