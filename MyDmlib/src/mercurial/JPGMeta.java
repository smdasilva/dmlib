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
//import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifThumbnailDirectory;
import com.drew.metadata.exif.NikonType1MakernoteDirectory;
import com.drew.metadata.exif.NikonType2MakernoteDirectory;
import com.drew.metadata.exif.PanasonicMakernoteDirectory;
import com.drew.metadata.jpeg.JpegCommentDirectory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedMap;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.KeyNotFoundException;

import net.sourceforge.jheader.*;
import net.sourceforge.jheader.JpegHeaders.*;
import net.sourceforge.jheader.App1Header.*;


public class JPGMeta {


        Metadata metadata;
        JpegHeaders headers;
        SortedMap<Tag, TagValue> tags;
        
	public JPGMeta(String file) throws ImageProcessingException, IOException, FileNotFoundException, JpegFormatException {

             new File(file);             
             JpegHeaders.preheat();   
             headers = new JpegHeaders(file);
             App1Header  app1Header = headers.getApp1Header();
             this.tags = app1Header.getTags();
             
            //System.out.println(app1Header.getValue(Tag.RELATEDIMAGELENGTH));
            //app1Header.getValue(Tag.RESOLUTIONUNIT);
        }
	
        
        public void saveTagFromFile(String pathFile, String pathDest) throws IOException, FileNotFoundException, JpegFormatException {   
            
            
            new File(pathDest);  
            Util.clearFile(pathDest); 
            if (headers.getApp1Header() == null)
		headers.convertToExif();
    
            App1Header app1Header = headers.getApp1Header();          
            SortedMap<Tag, TagValue> tags = app1Header.getTags();
	    for (Tag tag : tags.keySet())
	    {
		TagValue value = tags.get(tag);
                if(!value.toString().trim().isEmpty()) {
                    Util.addLineIntoFile(pathDest, tag.location+"|"+ tag.toString() +"|"+ value); 
                }
            }            
            
        }                                 
        
        
         /*public void test(String path) throws IOException, FileNotFoundException, JpegFormatException {  
             
             if (headers.getApp1Header() == null)
		headers.convertToExif();

	    App1Header app1Header = headers.getApp1Header();
	    // set a field
	    app1Header.setValue(new TagValue(Tag.IMAGEDESCRIPTION, "rgRADICAL")); 
            app1Header.setValue(new TagValue(Tag.MODEL, "YEAHHAHAHAHAHAHAHkkkkkkkkkkkkkkkkkkkkkkkjjjjjjjjjjjjjjAHAHAHAHAHA"));
            // save the file, making a backup first
	    headers.save(false);
         
	    
	}*/

        public void saveTagToFile(String path) throws IOException, KeyNotFoundException, FieldDataInvalidException, CannotWriteException, TagFormatException, ExifFormatException, FileNotFoundException, JpegFormatException {
        InputStream ips=new FileInputStream(path);
        InputStreamReader ipsr=new InputStreamReader(ips);
        BufferedReader br=new BufferedReader(ipsr);
        String line;
        
        while ((line=br.readLine())!=null){     // On utilise ce bout de code pour parcourir le fichier ligne par ligne
            String[] str = line.split("\\|"); // Je splite les "|" pour récupérer les colonnes, moi j'en est que deux, toi tu en auras trois
            if(!this.tags.get(Tag.valueOf(str[1])).toString().equals(str[2])) {
                App1Header app1Header = headers.getApp1Header();
                app1Header.setValue(new TagValue(this.tags.get(Tag.valueOf(str[1])).getTag(), str[2])); 
            }
                
        }
        
        headers.save(false);
        br.close();
    }
        

        
  /*public void setColor(String path) throws IOException
  {
      //Util.clearFile(path); 
            Iterator ite = metadata.getDirectories().iterator();
            while(ite.hasNext()){
                Directory dire = (Directory) ite.next(); 
                Collection<Tag> yo =   dire.getTags();                    
                Iterator it = yo.iterator();
                while(it.hasNext()){
                    Tag t = (Tag) it.next();
                    System.out.println(t.getTagType());
                    //dire.setString(t.getTagType(), "SUPARGB");
                    t.getDescription();
                    
                }
            }

      
                    
                   //directory.getDescription(directory.TAG_EXPOSURE_BIAS);
  }*/
        
        
   /*     public void serializeMetas(String MetaPath, String nameFile) throws FileNotFoundException, IOException
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
            
	}*/
        
        
        
}
