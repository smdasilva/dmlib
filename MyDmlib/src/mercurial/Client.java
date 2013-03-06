/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.sun.org.apache.xpath.internal.operations.Bool;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class Client {
    
     String hgrcPath;
     Repository repository;
	
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
            
            return true;
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
