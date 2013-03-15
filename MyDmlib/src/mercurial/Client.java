package mercurial;

import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import com.aragost.javahg.RepositoryConfiguration;
import com.aragost.javahg.RepositoryConfiguration.CachePolicy;
import com.aragost.javahg.commands.AddCommand;
import com.aragost.javahg.commands.CloneCommand;
import com.aragost.javahg.commands.CommitCommand;
import com.aragost.javahg.commands.DiffCommand;
import com.aragost.javahg.commands.IncomingCommand;
import com.aragost.javahg.commands.OutgoingCommand;
import com.aragost.javahg.commands.PullCommand;
import com.aragost.javahg.commands.PushCommand;
import com.aragost.javahg.commands.RemoveCommand;
import com.aragost.javahg.commands.StatusCommand;
import com.aragost.javahg.commands.StatusResult;
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
import net.sourceforge.jheader.JpegFormatException;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class Client {
    
     String hgrcPath;
     Repository repository;
     HgServer server;
     String MetaPath = "/net/cremi/sdasilva/Documents/dmlib/Client/Meta";
     String HashPath = "/net/cremi/sdasilva/Documents/Mercurial/toSynchronize/Hash";
     String installationPath;
     

    public Client(String installationPath) throws IOException {
        this.installationPath = installationPath;
        //RepositoryConfiguration REPO_CONF = makeRepoConf();
        //this.repository = Repository.open(REPO_CONF, new File(this.installationPath));
        
        // 
        /*BaseRepository br;
        File f = new File("/tmp");        
        try {
         br = Repository.open(f);
        } catch (IllegalArgumentException iae) {
         br = Repository.create(f);
        }    
        br.close();*/
        
        
        
        this.server = new HgServer(this.repository, 8000);
    }
    
    public void addServer(String server) {
        File hg = new File(this.installationPath+".hg");
        if(!hg.exists()) {
            cloneServer(this.repository.getDirectory(), server);
        } else {
           System.out.println("Modifier le .hg et rajouter un second serveur !");
           
        }
    }

     /* RepositoryConfiguration REPO_CONF = makeRepoConf();
       for(String r : repo) {
     File f = new File(r);
     this.re Repository.open(REPO_CONF, f);
     this.repository.add(e);
       }
       
       if(!this.server.isEmpty()) {
     String[] s = installationPath.split("\\/");
     String last = s[s.length-1];
     String rep_parent = installationPath.substring(0,installationPath.length()-last.length());
    
     for(String serv : this.server) {
     String repository_path = rep_parent+Math.random()+"/";
     File file = new File(repository_path);
     file.mkdirs();
     /*CloneCommand cl = new CloneCommand(this.repository.get(0));
     cl.execute(installationPath);  */     
     //cloneServer(file,serv);
     //Util.addLineIntoFile(installationPath+"repository", repository_path);
     //}
   //}
       
       
 
              
       // Detect if modification have occured when the application was close
       //modificationTreatment();
    //}
    
    public void initialisation(String installationPath) throws IOException {
        File hg = new File(installationPath+".hg");
        if(!hg.exists()) {
            String serveur = "localhsoyfgmlk";
            File f = new File(installationPath);
            cloneServer(f,serveur);
        }
        
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

public void addFile(String filePath) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ImageProcessingException, FileNotFoundException, JpegFormatException {
            storeFileMeta(filePath);
            AddCommand ac = new AddCommand(this.repository);
            ac.execute(filePath);
            CommitCommand ci = new CommitCommand(this.repository);
            ci.message("Ajout du fichier" + filePath).user("admin");
            ci.execute();
        }

public void add() {
AddCommand ac = new AddCommand(this.repository);
         ac.execute();
}


public void storeFileMeta(String filePath) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ImageProcessingException, FileNotFoundException, JpegFormatException {
File f = new File(filePath);
        String nameFile = (f.getName() != null) ? f.getName().substring(0,f.getName().indexOf('.')) : "";
        String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));

        // Create the object to serialize
        if (ext.equals(".mp3")) {
        Mp3Meta fileMetas = new Mp3Meta(f);
        String repPath = f.getCanonicalPath().replace(this.repository.getBaseRepository().getDirectory().getAbsolutePath(), "").replace(nameFile+ext, "");
        //fileMetas.serializeMetas(MetaPath+repPath, nameFile);
        } else if (ext == ".jpg") {
         JPGMeta fileMetas = new JPGMeta(f.getName());
         fileMetas.saveTagFromFile(nameFile, ext);
        }
}

public void storeFileHash(String filePath) throws IOException {
     String hash = Util.sha1sum(filePath);
     File f = new File(filePath);
     String nameFile = (f.getName() != null) ? f.getName().substring(0,f.getName().lastIndexOf('.')) : "";
     String repPath = f.getCanonicalPath().replace(this.repository.getBaseRepository().getDirectory().getAbsolutePath(), "").replace(f.getName(), "");
     Util.writeIntoFile(HashPath+repPath, nameFile,hash);
}
     


    
        
        public void removeFile(String fileName) throws IOException{
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
        
        public void changeFile(String filePath, String nameFile) throws IOException{
            CommitCommand ci = new CommitCommand(this.repository);
            ci.message("Ajout du fichier" + filePath).user("userrrr");
            ci.execute();
            PushCommand push = new PushCommand(this.repository);
             //for(String server : getServeur()){
            // push.on(this.repository).execute(server);
            //}
            push.on(this.repository).execute("http://cody:8000");
        }
        
        public boolean isFileModified(File f, String sha)
        {
         FileHashSum hash = new FileHashSum();
         return hash.compareSha1sum(f, sha);
        }
     
public boolean IsTagModifier(File f) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ImageProcessingException, ClassNotFoundException {
String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
File fichierMeta = new File("repertoirMeta") ;
ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichierMeta)) ;
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
       
 /*       
        public String diff()throws IOException{
            DiffCommand diff = new DiffCommand(this.repository);
            return diff.execute();
        }
        
        public StatusResult status() {
         StatusCommand status = new StatusCommand(this.repository);
         return status.execute();
        }
        
        public void outGoingFonc ()throws IOException{
            
            OutgoingCommand out = new OutgoingCommand(this.repository);
            //System.out.println(out.execute(this.repository));
             List<Changeset> l = new ArrayList();
             l = out.execute("http://cody:8000");
             System.out.println(l);
             
            Changeset ch = l.get(0);
            System.out.println(ch);
            System.out.println(ch.getModifiedFiles());
        }
        
         public void incomingFonc ()throws IOException{
            
            IncomingCommand in = new IncomingCommand(this.repository);
            //System.out.println(out.execute(this.repository));
             List<Changeset> l = new ArrayList();
             //l = in.execute("http://cody:8000");
             System.out.println(l);
             
            Changeset ch = l.get(0);
            System.out.println(ch);
            System.out.println(ch.getModifiedFiles());
        }
        
        // Regeneration of the Metadata file and the Hash file if the file has been modified
        public void modificationTreatment() throws ImageProcessingException, CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
         StatusResult result = status();

         if(!result.getUnknown().isEmpty()) {
         List<String> list = new ArrayList<String>();
         list = result.getUnknown();
         for(String file : list) {
         addFile(file);
         }
         }
        
         if(!result.getModified().isEmpty()) {
         List<String> list = new ArrayList<String>();
         list = result.getModified();
         for(String file : list) {
         storeFileMeta(file);
         storeFileHash(file);
         }
         }
        
        
         if(!result.getMissing().isEmpty()) {
         List<String> list = new ArrayList<String>();
         list = result.getMissing();
         for(String file : list) {
         System.out.println("Vous avez supprimé le fichier :"+file);
         }
         }
        }
       */
}