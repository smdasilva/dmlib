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

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class Client {
    
     String hgrcPath;
     Repository repository;
     String MetaPath = "/net/cremi/sdasilva/Documents/dmlib/Client/Meta";
     String HashPath = "/net/cremi/sdasilva/Documents/Mercurial/toSynchronize/Hash";
     String installationPath;
     

    public Client(String installationPath) throws IOException {
        this.installationPath = installationPath;      
        HgServer server = new HgServer(this.repository, 8000);
         try {
         Process p = server.execute();
         System.out.println(p.waitFor());
         } catch (IOException e) {}
         catch (InterruptedException e) {}    
    }
    
    
    public void addServer(String server) {
        File hg = new File(this.installationPath+".hg");
        if(!hg.exists()) {
            cloneServer(this.repository.getDirectory(), server);
        } else {
           System.out.println("Modifier le .hg et rajouter un second serveur !");
           
        }
    }

     
    
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
    
      
      
}