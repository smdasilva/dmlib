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
import com.drew.metadata.exif.ExifThumbnailDirectory;
import com.drew.metadata.exif.NikonType1MakernoteDirectory;
import com.drew.metadata.exif.NikonType2MakernoteDirectory;
import com.drew.metadata.jpeg.JpegCommentDirectory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.KeyNotFoundException;
//import com.drew.metadata.tag.TagException;
//import com.drew.metadata.tag.TagField;

public class JPGMeta {

	ExifSubIFDDirectory subFDDirectory;
        ExifIFD0Directory ifD0directory;
        Metadata metadata;
        //Directory dir;
	//ImageMetadataReader img;
        //Tag tag;

        
	public JPGMeta(File file) throws ImageProcessingException, IOException {
                //Metadata metadata = null;
		metadata = JpegMetadataReader.readMetadata(file);
		//this.directory = metadata.getDirectory(ExifSubIFDDirectory.class);
        }
	
        
        public void saveTagToFile(String path) throws IOException {   
            
            Util.clearFile(path); 
            Iterator ite = metadata.getDirectories().iterator();
            while(ite.hasNext()){
                Directory dire = (Directory) ite.next();                    
                Collection<Tag> yo =   dire.getTags();                    
                Iterator it = yo.iterator();
                while(it.hasNext()){
                    Tag t = (Tag) it.next();
                    Util.addLineIntoFile(path, dire.getName()+" | "+t.getTagName()+" | "+t.getDescription());}
                }                          
}

      
	
        public void saveTagFromFile(String path) throws IOException, KeyNotFoundException, FieldDataInvalidException, CannotWriteException {
        InputStream ips=new FileInputStream(path);
        InputStreamReader ipsr=new InputStreamReader(ips);
        BufferedReader br=new BufferedReader(ipsr);
        String line;

        while ((line=br.readLine())!=null){     // On utilise ce bout de code pour parcourir le fichier ligne par ligne
            String[] str = line.split("\\|"); // Je splite les "|" pour récupérer les colonnes, moi j'en est que deux, toi tu en auras trois
            System.out.println(str[0]);
            System.out.println(str[1]);
            System.out.println(str[2]);
            //this.metadata.
            /*if(!this.tag.getFirst(FieldKey.valueOf(str[0])).equals(str[1])) {  // Je modifie le TAG du fichier UNIQUEMENT si la donnée a été changée
                this.tag.setField(FieldKey.valueOf(str[0]), str[1]); */// Si la donnée a été changé, alors je fait le setField
            }   
        //}
        //br.close();
       // save();
    }

        
  public void setColor(String path) throws IOException
  {
      //Util.clearFile(path); 
            Iterator ite = metadata.getDirectories().iterator();
            while(ite.hasNext()){
                Directory dire = (Directory) ite.next(); 
                Collection<Tag> yo =   dire.getTags();                    
                Iterator it = yo.iterator();
                while(it.hasNext()){
                    Tag t = (Tag) it.next();
                    dire.setString(t.getTagType(), "SUPARGB");
                }
            }

      
                    
                   //directory.getDescription(directory.TAG_EXPOSURE_BIAS);
  }
        
        
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
