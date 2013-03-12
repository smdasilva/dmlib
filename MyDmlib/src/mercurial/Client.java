package mercurial;
 

import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import com.aragost.javahg.RepositoryConfiguration;
import com.aragost.javahg.RepositoryConfiguration.CachePolicy;
import com.aragost.javahg.commands.AddCommand;
import com.aragost.javahg.commands.CommitCommand;
import com.aragost.javahg.commands.DiffCommand;
import com.aragost.javahg.commands.IncomingCommand;
import com.aragost.javahg.commands.OutgoingCommand;
import com.aragost.javahg.commands.PullCommand;
import com.aragost.javahg.commands.PushCommand;
import com.aragost.javahg.commands.RemoveCommand;
import com.aragost.javahg.commands.UpdateCommand;
import com.aragost.javahg.internals.Server;
import com.drew.imaging.ImageProcessingException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.jheader.JpegFormatException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagException;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class Client {
    
     String hgrcPath;
     Repository repository;
     Dico dico;
     //String MetaPath = "/net/cremi/sdasilva/Documents/dmlib/Client/Meta";
     String MetaPath = "/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Meta";
     String HashPath = "/net/cremi/abndoye/Desktop/espaces/travail/MYDEARTEST/Hash";
     
     //String MetaPath = "/home/abndoye/Bureau/SUPASERV/Meta";
     //String HashPath = "/home/abndoye/Bureau/SUPASERV/Hash";
	
    public Client(String repository, String Urlserver) {
       File repositoryPath = new File(repository);
       if (!repositoryPath.exists()) {
        cloneServer(new File(repository),Urlserver);
       }
       RepositoryConfiguration  REPO_CONF = makeRepoConf();
       this.repository = Repository.open(REPO_CONF, repositoryPath);
       this.dico = new Dico();
    }
   

    protected static RepositoryConfiguration makeRepoConf() {
	        RepositoryConfiguration conf = new RepositoryConfiguration();
	        conf.setCachePolicy(CachePolicy.WEAK);
	        return conf;
	    }
    
    public void cloneServer(File rep, String Urlserver) {
            Server server = new Server(
                 RepositoryConfiguration.DEFAULT.getHgBin(),
                 RepositoryConfiguration.DEFAULT.getEncoding());    
            server.cloneMercurialRepository(rep,null,Urlserver);
	}
    
        public List<String> getServeur(){
            List<String> list = new ArrayList();
            list.add("http://cody:8000");
            //list.add("http://curtis:8000");
            return list;

        }
	
	public void FileAdded(String filePath) throws IOException{
            
            /***************************a voir ***********************/
            PullCommand pull = new PullCommand(this.repository);
            pull.execute();
            UpdateCommand up = new UpdateCommand(this.repository);
            up.execute();
            /************************/

            AddCommand ac = new AddCommand(this.repository);
            ac.execute();
            CommitCommand ci = new CommitCommand(this.repository);
            ci.message("Ajout du fichier" + filePath).user("userrrr");
            ci.execute();
            PushCommand push = new PushCommand(this.repository);
            //for(String server : getServeur()){
            //    push.on(this.repository).execute(server);
            //}
            push.on(this.repository).execute("http://cody:8000");
            //push.on(this.repository).execute("http://curtis:8000");
        }
	
	public void serializeFileMeta(String filePath) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ImageProcessingException, KeyNotFoundException, FieldDataInvalidException, CannotWriteException, FileNotFoundException, JpegFormatException {
        File f = new File(filePath);
        String nameFile = (f.getName() != null) ? f.getName().substring(0,f.getName().indexOf('.')) : "";
        String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));

        // Create the object to serialize
        if (ext.equals(".mp3")) {
       		Mp3Meta fileMetas = new Mp3Meta(f);
       		String repPath = f.getCanonicalPath().replace(this.repository.getBaseRepository().getDirectory().getAbsolutePath(), "").replace(nameFile+ext, "");
                fileMetas.serializeMetas(MetaPath+repPath, nameFile);		
        } else if (ext.equals(".jpg")) {
        	JPGMeta fileMetas = new JPGMeta(filePath);	
                 //System.out.println(MetaPath);
               String repPath = f.getCanonicalPath().replace(this.repository.getBaseRepository().getDirectory().getAbsolutePath(), "").replace(nameFile+ext, "");
                //fileMetas.saveTagToFile(MetaPath+repPath+nameFile);
                //fileMetas.saveTagFromFile(MetaPath+repPath+nameFile+".txt");
                //fileMetas.setColor(f.getAbsolutePath());
                //fileMetas.serializeMetas(MetaPath+repPath, nameFile);
        }
	}

        
        private static String getHexString(byte[] bytes) {  
        StringBuilder sb = new StringBuilder(bytes.length*2);  
        for (byte b : bytes) {  
                        if (b <= 0x0F && b >= 0x00) { // On rajoute le 0 de poid fort ignoré à la conversion.  
                                sb.append('0');  
                        }  
            sb.append( String.format("%x", b) );  
        }  
        return sb.toString();  
    }  
        
        
	public void hashedFile(String filePath) throws IOException{
        File f = new File(filePath);
        String localSha1Sum = null;
        String nameFile = (f.getName() != null) ? f.getName().substring(0,f.getName().indexOf('.')) : "";
        String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
        String repPath = f.getAbsolutePath().replace(this.repository.getBaseRepository().getDirectory().getAbsolutePath(), "").replace(nameFile+ext, "");
            
        
        if (f.exists() && f.isFile() && f.canRead()){  
            try {  
                MessageDigest md = MessageDigest.getInstance("SHA-1");  
                DigestInputStream dis = new DigestInputStream(new FileInputStream(f), md);  
                dis.on(true);  
  
                while (dis.read() != -1){  
                    ;  
                }  
                byte[] b = md.digest();  
                localSha1Sum = getHexString(b);  
            } catch (Exception ex) {  
                ex.printStackTrace(); }      
                
              File path = new File(HashPath+repPath);  
                if (!path.exists())
		  { path.mkdirs(); }  
             ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream(HashPath+repPath+"/"+ nameFile));
             out.writeChars(localSha1Sum);
             out.flush();
             out.close();
        
             
        }
        
        }
        
        
	public Mp3MetaSerializable getMp3StoredMeta(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(filePath);
		String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
        String nameFile = (f.getName() != null) ? f.getName().substring(0,f.getName().indexOf('.')) : "";
		ObjectInputStream ois = new ObjectInputStream( new FileInputStream(MetaPath+"/"+ nameFile));

		Mp3MetaSerializable e = (Mp3MetaSerializable) ois.readObject();
		return e;
	}
	
	public JPGMetaSerializable getJPGStoredMeta(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(filePath);
		String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
        String nameFile = (f.getName() != null) ? f.getName().substring(0,f.getName().indexOf('.')) : "";
		ObjectInputStream ois = new ObjectInputStream( new FileInputStream(MetaPath+"/"+ nameFile));

		JPGMetaSerializable e = (JPGMetaSerializable) ois.readObject();
		return e;
	}
    
        
        public void RemoveFile(String fileName) throws IOException{
            RemoveCommand rm = new RemoveCommand(this.repository);
            rm.execute(fileName);
            CommitCommand ci = new CommitCommand(this.repository);
            ci.message("Suppression du fichier" + fileName).user("userrrr");
            ci.execute();
            PushCommand push = new PushCommand(this.repository);
             for(String server : getServeur()){
                push.on(this.repository).execute(server);
            }
           //push.on(this.repository).execute(getServeur());
            
        }
        
        public void ChangeFile(String filePath, String nameFile) throws IOException{                 
            CommitCommand ci = new CommitCommand(this.repository);
            ci.message("Ajout du fichier" + filePath).user("userrrr");
            ci.execute();
            PushCommand push = new PushCommand(this.repository);
             //for(String server : getServeur()){
            //    push.on(this.repository).execute(server);
            //}
            push.on(this.repository).execute("http://cody:8000");
        }
        
        public boolean IsFileModified(File f, String sha)
        {
        	FileHashSum hash = new FileHashSum();
        	return hash.compareSha1sum(f, sha);
        }
        
        public boolean IsTagModifier(File f) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ImageProcessingException, ClassNotFoundException, FileNotFoundException, JpegFormatException {
        	String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
        	
        	File fichierMeta =  new File("repertoirMeta") ;
        	ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichierMeta)) ;
        	
        	if (ext == ".mp3") {
        		Mp3Meta metaStorage = (Mp3Meta)ois.readObject() ;
        		Mp3Meta metasFichier = new Mp3Meta(f);
        		return metasFichier.compareTag(metaStorage);
        	} else if (ext == ".jpg") {
        		JPGMeta metaStorage = (JPGMeta)ois.readObject() ;
        		JPGMeta metasFichier = new JPGMeta(f.getAbsolutePath());
        		return true;
        	} else {
        		return false;
        	}
        }
        
        public void Diff()throws IOException{   
            
            DiffCommand diff = new DiffCommand(this.repository);
            System.out.println("ici"+diff.execute());
            //System.out.println(diff.getReturnCode());
            
        }
        
        public void OutGoingFonc ()throws IOException{ 
            
            OutgoingCommand out = new OutgoingCommand(this.repository);
            //System.out.println(out.execute(this.repository));
             List<Changeset> l = new ArrayList();
             l = out.execute("http://cody:8000");
             System.out.println(l);
             
            Changeset ch = l.get(0);
            System.out.println(ch);
            System.out.println(ch.getModifiedFiles());
        }
        
         public void IncomingFonc ()throws IOException{ 
            
            IncomingCommand in = new IncomingCommand(this.repository);
            //System.out.println(out.execute(this.repository));
             List<Changeset> l = new ArrayList();
             //l = in.execute("http://cody:8000");
             System.out.println(l);
             
            Changeset ch = l.get(0);
            System.out.println(ch);
            System.out.println(ch.getModifiedFiles());
        }
}
