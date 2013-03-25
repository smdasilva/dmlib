package ped.dmlib.local.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.jpeg.JpegDirectory;

public class JPGMeta 
{	
	Metadata metadata;
	JpegDirectory jpegDir;
	ExifIFD0Directory exifIFD0;
	ExifSubIFDDirectory exifSubIFD;
	
	public JPGMeta(File file) throws ImageProcessingException, IOException, FileNotFoundException
	{
		metadata = ImageMetadataReader.readMetadata(file);
		jpegDir = metadata.getOrCreateDirectory(JpegDirectory.class);
		exifIFD0 = metadata.getOrCreateDirectory(ExifIFD0Directory.class);
		exifSubIFD = metadata.getOrCreateDirectory(ExifSubIFDDirectory.class);
	}
	
	//Getters for ExifIFD0Directory
	public String getTitle() {
		return exifIFD0.getDescription(ExifIFD0Directory.TAG_WIN_TITLE);
	}
	public String getComments() {
		return exifIFD0.getDescription(ExifIFD0Directory.TAG_WIN_COMMENT);
	}
	public String getAuthor() {
		return exifIFD0.getDescription(ExifIFD0Directory.TAG_WIN_AUTHOR);
	}
	
	//Getters for ExifSubIFDDirectory
	public Date getPicturesDate() {
		return exifSubIFD.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
	}
	
	//Getters for JpegDirectory
	public String getPictureWidth() {
		return jpegDir.getDescription(JpegDirectory.TAG_JPEG_IMAGE_WIDTH);
	}
	public String getPictureHeight() {
		return jpegDir.getDescription(JpegDirectory.TAG_JPEG_IMAGE_HEIGHT);
	}
	public String getCompression() {
		return jpegDir.getDescription(JpegDirectory.TAG_JPEG_COMPRESSION_TYPE);
	}
	public String getDataPrecision() {
		return jpegDir.getDescription(JpegDirectory.TAG_JPEG_DATA_PRECISION);
	}
 }
