package ped.dmlib.local.model;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.SortedMap;

import ped.dmlib.Util;

import net.sourceforge.jheader.App1Header;
import net.sourceforge.jheader.App1Header.Tag;
import net.sourceforge.jheader.ExifFormatException;
import net.sourceforge.jheader.JpegFormatException;
import net.sourceforge.jheader.JpegHeaders;
import net.sourceforge.jheader.TagFormatException;
import net.sourceforge.jheader.TagValue;

public class JPGMeta2 {

        JpegHeaders headers;
        SortedMap<Tag, TagValue> tags;
        
	public JPGMeta2(String file) throws IOException, FileNotFoundException, JpegFormatException {

             new File(file);             
             JpegHeaders.preheat();   
             
             headers = new JpegHeaders(file);
             headers.convertToExif();
             System.out.println(headers.getFileType());
             if(headers.getApp1Header() == null)
             {
            	 System.out.println(headers.getApp1Header().getTags());
             }
             //System.out.println(headers.getApp1Header().getValue(Tag.ARTIST).toString());
             //app1Header.setValue(new TagValue(Tag.ARTIST, "Merde"));
             //headers.save(false);
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
        
        public void saveTagFromFile(String path, String name) throws IOException, FileNotFoundException, JpegFormatException {   
            
            File f = new File(path+name);
		if(f.exists()) {
		Util.clearFile(f.getAbsolutePath()); }

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
                
                    //Util.addLineIntoFile(name, tag.location+"|"+ tag.toString() +"|"+ tag.format +"|"+ value); 
                    Util.writeIntoFile(path, name, tag.location+"|"+ tag.toString() +"|"+ tag.format +"|"+ value);
                }
            }            
        }                                 
        
      
        /*public void saveTagToFile(String path) throws IOException, KeyNotFoundException, FieldDataInvalidException, CannotWriteException, TagFormatException, ExifFormatException, FileNotFoundException, JpegFormatException, JAXBException {
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
    }  */
        
        
    public static void saveHashToFile(String source, String destPath, String name) throws FileNotFoundException, IOException {
		Image image1 = Toolkit.getDefaultToolkit().getImage(source);
		try {
			PixelGrabber grab1 = new PixelGrabber(image1, 0, 0, -1, -1,false);
			int[] data = null;
			if (grab1.grabPixels()) {
				int width = grab1.getWidth();
				int height = grab1.getHeight();
				data = new int[width * height];
				data = (int[]) grab1.getPixels();
			}
                        int total = 0;
			for(int d : data) {
                            total += d;
                        }
                        Util.writeIntoFile(destPath, name, String.valueOf(total));

		} catch (InterruptedException e1) {
			System.out.println("Fail compareImageJPG()");
		}
                
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
