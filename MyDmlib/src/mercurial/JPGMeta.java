package mercurial;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.SortedMap;
import javax.xml.bind.JAXBException;
import net.sourceforge.jheader.*;
import net.sourceforge.jheader.App1Header.Tag;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.KeyNotFoundException;


public class JPGMeta {


        Metadata metadata;
        JpegHeaders headers;
        SortedMap<Tag, TagValue> tags;
        
	public JPGMeta(String file) throws ImageProcessingException, IOException, FileNotFoundException, JpegFormatException {

             new File(file);             
             JpegHeaders.preheat();   
             
             headers = new JpegHeaders(file, false);
             App1Header  app1Header = headers.getApp1Header();
             System.out.println(headers.getFileType());
             /*if(app1Header.getTags() == null){
                 System.out.println("Bad file");
                 
             }*/
             //this.tags = app1Header.getTags();
             
        }
	
        public String getMODEL()
        {
            App1Header  app1Header = headers.getApp1Header();
            if(app1Header.getValue(Tag.MODEL) != null) {
                return app1Header.getValue(Tag.MODEL).toString();
            }
            return "_";
        }
        
        public void setTittle() throws TagFormatException, ExifFormatException, IOException, FileNotFoundException, JpegFormatException
        {      
                App1Header app1Header = headers.getApp1Header();
                
                app1Header.setValue(new TagValue(Tag.ARTIST, "a")); 
                headers.save(false);
        }
        
        public String getRESOLUTIONUNIT()
        {
            App1Header  app1Header = headers.getApp1Header();

            if(app1Header.getValue(Tag.RESOLUTIONUNIT) !=null) {
                return app1Header.getValue(Tag.RESOLUTIONUNIT).toString();
            }
            return "_";
        }
        
        public void saveTagFromFile(String pathFile, String pathDest) throws IOException, FileNotFoundException, JpegFormatException {   
            
            
            new File(pathDest);  
            Util.clearFile(pathDest); 
            if (headers.getApp1Header() == null) {
                headers.convertToExif();
            }
    
            App1Header app1Header = headers.getApp1Header();          
            this.tags = app1Header.getTags(false);
	    for (Tag tag : tags.keySet())
	    {
		TagValue value = tags.get(tag);
                               
                if(!value.toString().trim().isEmpty() && ! (tag.toString().equals("DATETIME") || tag.toString().equals("EXIFOFFSET") 
                        || tag.toString().equals("EXIFINTEROPERABILITYOFFSET") || tag.toString().equals("IFD1_JPEGIFOFFSET")) ) {
                
                    Util.addLineIntoFile(pathDest, tag.location+"|"+ tag.toString() +"|"+ tag.format +"|"+ value); 
                    
                }
            }            
        }                                 
        
      
        public void saveTagToFile(String path) throws IOException, KeyNotFoundException, FieldDataInvalidException, CannotWriteException, TagFormatException, ExifFormatException, FileNotFoundException, JpegFormatException, JAXBException {
        InputStream ips=new FileInputStream(path);
        InputStreamReader ipsr=new InputStreamReader(ips);
        BufferedReader br=new BufferedReader(ipsr);
        String line;
        
        App1Header app1Header = headers.getApp1Header();
        
        while ((line=br.readLine())!=null){     // On utilise ce bout de code pour parcourir le fichier ligne par ligne
            String[] str = line.split("\\|"); // Je splite les "|" pour récupérer les colonnes, moi j'en est que deux, toi tu en auras trois
            if(!this.tags.get(Tag.valueOf(str[1])).toString().equals(str[3])) {
                // System.out.println(this.tags.get(Tag.valueOf(str[1])).toString());        
                switch (str[2])
                 {                    
                    case "USHORT":break;
                    case "SBYTE":break;
                    case "UNDEFINED":break;
                    case "SSHORT":break;
                    case "SLONG":break;
                    case "SRATIONAL":break;
                    case "SINGLE":break;
                    case "DOUBLE":break;
                         
                    case "STRING":                                            
                        app1Header.setValue(new TagValue(this.tags.get(Tag.valueOf(str[1])).getTag(), str[3])); 
                        break;

                    case "ULONG": 
                        Long value = new Long(str[3]);
                        app1Header.setValue(new TagValue(this.tags.get(Tag.valueOf(str[1])).getTag(), value)); 
                        break; 
                    case "URATIONAL": 
                        String[] r = str[3].split("\\/");
                        long numer = Long.parseLong(r[0]);
                        long denom = Long.parseLong(r[1]);
                        Rational rational = new Rational(numer, denom);
                        app1Header.setValue(new TagValue(this.tags.get(Tag.valueOf(str[1])).getTag(), rational));
                        break;   
                    
                    default: 
                        app1Header.setValue(new TagValue(this.tags.get(Tag.valueOf(str[1])).getTag(), str[3]));                            
                }
            }                
        }        
        headers.save(false);
        br.close();          
    }  
        
        
    public void removeRadicalTag() throws IOException, FileNotFoundException, JpegFormatException
    {
         App1Header app1Header = headers.getApp1Header();          
            this.tags = app1Header.getTags(false);
	    for (Tag tag : tags.keySet())
	    {
                
		//TagValue value = tags.get(tag);
                app1Header.removeTag(tag);
            }
             headers.save(false);
    }
 }
