package mercurial;

import com.drew.imaging.ImageMetadataReader;
import java.io.File;
import java.io.IOException;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.Tag;

public class JPGMeta {

	ExifSubIFDDirectory subFDDirectory;
        ExifIFD0Directory ifD0directory;
        Directory directory;
	//ImageMetadataReader img;
        
       
        
	public JPGMeta(File file) throws ImageProcessingException, IOException {
                //Metadata metadata = null;
		Metadata metadata = JpegMetadataReader.readMetadata(file);
		//this.directory = metadata.getDirectory(ExifSubIFDDirectory.class);
                this.subFDDirectory = metadata.getDirectory(ExifSubIFDDirectory.class);
                this.ifD0directory = metadata.getDirectory(ExifIFD0Directory.class);
        }
	
	public String getAperture() {
            if((subFDDirectory.getDescription(subFDDirectory.TAG_APERTURE)) == null)  
                    return "";
       
            return subFDDirectory.getTagName( subFDDirectory.TAG_APERTURE);
           
	}
        
        /*public String getTitle(){
            return directory.getDescription(directory.TAG_TILE_OFFSETS);
        }*/     
        
        
        public String getLensModel()
        {   if(ifD0directory.getDescription(ifD0directory.TAG_MODEL)== null)      return "";  
                        
            return ifD0directory.getDescription(ifD0directory.TAG_MODEL);
        }  
        
        /*public String getLensModel()
        {   if()      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_LENS_MODEL);
        }*/
               
        public String getColorSpace()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_COLOR_SPACE) == null)      return "";  
             System.out.println(subFDDirectory.getDescription(subFDDirectory.TAG_COLOR_SPACE));
            return subFDDirectory.getDescription(subFDDirectory.TAG_COLOR_SPACE);
        }
        
        public String getCustomRendered()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_CUSTOM_RENDERED) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_CUSTOM_RENDERED);
        }
        
        public String getDateTimeDigitized()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_DATETIME_DIGITIZED) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_DATETIME_DIGITIZED);
        }
        
        

        public String getDateTimeOriginal()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_DATETIME_ORIGINAL) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_DATETIME_ORIGINAL);
        }
        
        
        public String getExifVersion()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_EXIF_VERSION) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_EXIF_VERSION);
        }
        
        
        public String getExposureBiasValue()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_EXPOSURE_BIAS) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_EXPOSURE_BIAS);
        }
        
        
        public String getExposureMode()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_EXPOSURE_MODE) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_EXPOSURE_MODE);
        }
        
        public String getExposureTime()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_EXPOSURE_TIME) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_EXPOSURE_TIME);
        }
        
        public String getExposureProgram()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_EXPOSURE_PROGRAM) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_EXPOSURE_PROGRAM);
        }
        
        
        public String getFlash()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_FLASH) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_FLASH);
        }
        
        public String getFlashPixVersion()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_FLASHPIX_VERSION) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_FLASHPIX_VERSION);
        }
        
        /*public String getFNumber()
        {   if()      return "";  
            return directory.getDescription(directory.TAG_FNUMBER);
        }*/
        
        public String getFocalLength()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_FOCAL_LENGTH) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_FOCAL_LENGTH);
        }
        
        public String getFocalPlanResolutionUnit()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_FOCAL_PLANE_UNIT) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_FOCAL_PLANE_UNIT);
        }
        
        public String getFocalPlanXResolution()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_FOCAL_PLANE_X_RES) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_FOCAL_PLANE_X_RES);
        }
        
        
        public String getFocalPlanYResolution()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_FOCAL_PLANE_Y_RES) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_FOCAL_PLANE_Y_RES);
        }
        
        public String getIsoSpeddRatings()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_ISO_EQUIVALENT) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_ISO_EQUIVALENT);
        }
        
        public String getMeteringMode()
        {   
            
            if( subFDDirectory.getDescription(subFDDirectory.TAG_METERING_MODE) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_METERING_MODE);
        }
        
        public String getSceneCaptureType()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_SCENE_CAPTURE_TYPE) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_SCENE_CAPTURE_TYPE);
        }
       
        public String getShutterSpeedValue()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_SHUTTER_SPEED) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_SHUTTER_SPEED);
        }
        
        /*public String getSubsecondTime()
        {   if()      return "";  
            return directory.getDescription(directory.TAG_SUBSECOND_TIME);
        }
        
        public String getSubsecondTimeDig()
        {   if()      return "";  
            return directory.getDescription(directory.TAG_SUBSECOND_TIME_DIGITIZED);
        }
        
        public String getSubsecondTimeOrigin()
        {   if()      return "";  
            return directory.getDescription(directory.TAG_SUBSECOND_TIME_ORIGINAL);
        }*/
        
        
        public String getWhiteBalance()
        {   if(subFDDirectory.getDescription(subFDDirectory.TAG_WHITE_BALANCE) == null)      return "";  
            return subFDDirectory.getDescription(subFDDirectory.TAG_WHITE_BALANCE);
        }
        
        /*public String getFlashComp()
        {   if()      return "";  
            return directory.getDescription(directory.TAG_FLASH_ENERGY);
        }
        
        public String getLendId()
        {   if()      return "";  
            return directory.getDescription(directory.TAG_LENS_SPECIFICATION);
        }
        
        public String getLensSerialNumb()
        {   if()      return "";  
            return directory.getDescription(directory.TAG_LENS_SERIAL_NUMBER);
        }*/
        
        
        
        
        public void serializeMetas(String MetaPath, String nameFile) throws FileNotFoundException, IOException
        {   
            File path = new File(MetaPath);  
            if (!path.exists())
             {     path.mkdirs(); }
		
            ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(MetaPath+"/"+ nameFile));
            JPGMetaSerializable jpg = new JPGMetaSerializable(
                                                            getAperture(), 
                                                            getLensModel(),  
                                                            getColorSpace(), 
                                                            getCustomRendered(), 
                                                            getDateTimeDigitized(), 
                                                            getDateTimeOriginal(),  
                                                            getExifVersion(), 
                                                            getExposureBiasValue(), 
                                                            getExposureMode(), 
                                                            getExposureTime(), 
                                                            getExposureProgram(),  
                                                            getFlash(), 
                                                            getFlashPixVersion(), 
                                                            getFocalLength(), 
                                                            getFocalPlanResolutionUnit(),
                                                            getFocalPlanXResolution(),  
                                                            getFocalPlanYResolution(), 
                                                            getIsoSpeddRatings(), 
                                                            getMeteringMode(),   
                                                            getSceneCaptureType(), 
                                                            getShutterSpeedValue(), 
                                                            getWhiteBalance() );
            out.writeObject(jpg);
            out.flush();
            out.close();
            
            //System.out.println(this.getMeteringMode());
            
	}
        
        
        
}
