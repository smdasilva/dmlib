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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class Client {
    
     String hgrcPath;
     Repository repository;
     Dico dico;
     String MetaPath = "/net/cremi/sdasilva/Documents/dmlib/Client/Meta";
	
    public Client(String repository, String Urlserver) {
       File repositoryPath = new File(repository);
       if (!repositoryPath.exists()) {
        cloneServer(new File(repository),Urlserver);
       }
       RepositoryConfiguration  REPO_CONF = makeRepoConf();
       this.repository = Repository.open(REPO_CONF, repositoryPath);
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
	
	public void storeFileMeta(String filePath) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ImageProcessingException {
		File f = new File(filePath);
        String nameFile = (f.getName() != null) ? f.getName().substring(0,f.getName().indexOf('.')) : "";
        String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));

        // Create the object to serialize
        if (ext.equals(".mp3")) {
       		Mp3Meta fileMetas = new Mp3Meta(f);
       		String repPath = f.getCanonicalPath().replace(this.repository.getBaseRepository().getDirectory().getAbsolutePath(), "").replace(nameFile+ext, "");
       		fileMetas.serializeMetas(MetaPath+repPath, nameFile);		
        } else if (ext == ".jpg") {
        	JPGMeta fileMetas = new JPGMeta(f);	
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
	
	/*
	public JPGMetaSerializable getJPGStoredMeta(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(filePath);
		String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
        String nameFile = (f.getName() != null) ? f.getName().substring(0,f.getName().indexOf('.')) : "";
		ObjectInputStream ois = new ObjectInputStream( new FileInputStream(MetaPath+"/"+ nameFile));

		JPGMetaSerializable e = (JPGMetaSerializable) ois.readObject();
		return e;
	}
	*/
	
	public boolean isMp3MetaModifier(String filePath) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ClassNotFoundException {
		File f = new File(filePath);
		Mp3Meta currentMp3 = new Mp3Meta(f);
		// We get the old Meta version
		Mp3MetaSerializable oldMeta = getMp3StoredMeta(filePath);
		// Reconstruct the old Mp3 File
		Mp3Meta oldMp3 = new Mp3Meta(f);
		oldMp3.setAllMetas(oldMeta);
		// Compare old and new tag
		return currentMp3.compareTag(oldMp3);
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
        
        public boolean IsTagModifier(File f) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ImageProcessingException, ClassNotFoundException {
        	String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
        	
        	File fichierMeta =  new File("repertoirMeta") ;
        	ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichierMeta)) ;
        	
        	if (ext == ".mp3") {
        		Mp3Meta metaStorage = (Mp3Meta)ois.readObject() ;
        		Mp3Meta metasFichier = new Mp3Meta(f);
        		return metasFichier.compareTag(metaStorage);
        	} else if (ext == ".jpg") {
        		JPGMeta metaStorage = (JPGMeta)ois.readObject() ;
        		JPGMeta metasFichier = new JPGMeta(f);
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
