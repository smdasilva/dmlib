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
        
        public String getTitle(){
            return directory.getDescription(directory.TAG_TILE_OFFSETS);
        }
        
        public String getDateTaken()
        {
            return directory.getDescription(directory.TAG_DATETIME_ORIGINAL);
        }

        
        public String getLensModel()
        {
            return directory.getDescription(directory.TAG_LENS_MODEL);
        }     
               
        public String getColorSpace()
        {
            return directory.getDescription(directory.TAG_COLOR_SPACE);
        }
        
        public String getCustomRendered()
        {
            return directory.getDescription(directory.TAG_CUSTOM_RENDERED);
        }
        
        public String getDateTimeDigitized()
        {
            return directory.getDescription(directory.TAG_DATETIME_DIGITIZED);
        }
        
        

        public String getDateTimeOriginal()
        {
            return directory.getDescription(directory.TAG_DATETIME_ORIGINAL);
        }
        
        
        public String getExifVersion()
        {
            return directory.getDescription(directory.TAG_EXIF_VERSION);
        }
        
        
        public String getExposureBiasValue()
        {
            return directory.getDescription(directory.TAG_EXPOSURE_BIAS);
        }
        
        
        public String getExposureMode()
        {
            return directory.getDescription(directory.TAG_EXPOSURE_MODE);
        }
        
        public String getExposureTime()
        {
            return directory.getDescription(directory.TAG_EXPOSURE_TIME);
        }
        
        public String getExposureProgram()
        {
            return directory.getDescription(directory.TAG_EXPOSURE_PROGRAM);
        }
        
        
        public String getFlash()
        {
            return directory.getDescription(directory.TAG_FLASH);
        }
        
        public String getFlashPixVersion()
        {
            return directory.getDescription(directory.TAG_FLASHPIX_VERSION);
        }
        
        public String getFNumber()
        {
            return directory.getDescription(directory.TAG_FNUMBER);
        }
        
        public String getFocalLength()
        {
            return directory.getDescription(directory.TAG_FOCAL_LENGTH);
        }
        
        public String getFocalPlanResolutionUnit()
        {
            return directory.getDescription(directory.TAG_FOCAL_PLANE_UNIT);
        }
        
        public String getFocalPlanXResolution()
        {
            return directory.getDescription(directory.TAG_FOCAL_PLANE_X_RES);
        }
        
        
        public String getgetFocalPlanYResolution()
        {
            return directory.getDescription(directory.TAG_FOCAL_PLANE_Y_RES);
        }
        
        public String getIsoSpeddRatings()
        {
            return directory.getDescription(directory.TAG_ISO_EQUIVALENT);
        }
        
        public String getMeteringMode()
        {
            return directory.getDescription(directory.TAG_METERING_MODE);
        }
        
        public String getSceneCaptureType()
        {
            return directory.getDescription(directory.TAG_SCENE_CAPTURE_TYPE);
        }
       
        public String getShutterSpeedValue()
        {
            return directory.getDescription(directory.TAG_SHUTTER_SPEED);
        }
        
        public String getSubsecondTime()
        {
            return directory.getDescription(directory.TAG_SUBSECOND_TIME);
        }
        
        public String getSubsecondTimeDig()
        {
            return directory.getDescription(directory.TAG_SUBSECOND_TIME_DIGITIZED);
        }
        
        public String getSubsecondTimeOrigin()
        {
            return directory.getDescription(directory.TAG_SUBSECOND_TIME_ORIGINAL);
        }
        
        
        public String getWhiteBalance()
        {
            return directory.getDescription(directory.TAG_WHITE_BALANCE);
        }
        
        public String getFlashComp()
        {
            return directory.getDescription(directory.TAG_FLASH_ENERGY);
        }
        
        public String getLendId()
        {
            return directory.getDescription(directory.TAG_LENS_SPECIFICATION);
        }
        
        public String getLensSerialNumb()
        {
            return directory.getDescription(directory.TAG_LENS_SERIAL_NUMBER);
        }
        
        
}
