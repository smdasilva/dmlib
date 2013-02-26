package mercurial;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.aragost.javahg.Repository;
import com.aragost.javahg.RepositoryConfiguration;
import com.aragost.javahg.RepositoryConfiguration.CachePolicy;
import com.aragost.javahg.commands.AddCommand;
import com.aragost.javahg.commands.CloneCommand;
import com.aragost.javahg.commands.VersionCommand;
import com.aragost.javahg.commands.flags.CloneCommandFlags;
import com.aragost.javahg.internals.AbstractCommand;
import com.aragost.javahg.internals.Server;
import com.aragost.javahg.internals.Utils;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

public class main {
	
	 public static void main(String[] args) throws IOException {
		 //SimpleTestServer serveur = new SimpleTestServer(new File("/net/cremi/sdasilva/Documents/dmlib/dmlib.git/serveur/"));
		 //serveur.start();
		 /*
		 Server serveur = new Server("hg", Charset.defaultCharset());
		 File serveurRep = new File("/net/cremi/sdasilva/Documents/dmlib/dmlib.git/serveur/");
		 Runnable run = new Thread();
		 List<String> extraArguments = new ArrayList<String>();
		 serveur.start(serveurRep, "/net/cremi/sdasilva/Documents/dmlib/dmlib.git/serveur/.hg/hgrc", extraArguments, run);
		 
		 
		 List<String> extraArguments2 = new ArrayList<String>();
		 extraArguments2.add("add");
		 RepositoryConfiguration conf = new RepositoryConfiguration();
		 conf.setHgrcPath(null);
		 Repository repo = Repository.open(conf, new File("/net/cremi/sdasilva/Documents/dmlib/dmlib.git/serveur/"));
		 AbstractCommand ac = new AddCommand(repo);
		 
		 Client c = new Client();
		 c.createRepository("/net/cremi/sdasilva/Documents/dmlib/dmlib.git/client/");
		 List<String> extraArguments3 = new ArrayList<String>();
		 extraArguments3.add("clone");
		 RepositoryConfiguration conf2 = new RepositoryConfiguration();
		 conf2.setHgrcPath(null);
		 Repository repo2 = Repository.open(conf2, new File("/net/cremi/sdasilva/Documents/dmlib/dmlib.git/client/"));
		 AbstractCommand ac2 = new CloneCommand(repo2);
		 */
		 
		 
		 
		 /*
		 RepositoryConfiguration  REPO_CONF = makeRepoConf();
		 File dir = Files.createTempDir();
         Repository testRepository = Repository.create(REPO_CONF, dir);
         File cloneDir = new File("/net/cremi/sdasilva/Documents/dmlib/dmlib.git/client/");
         
         Server server = new Server(
                 RepositoryConfiguration.DEFAULT.getHgBin(),
                 RepositoryConfiguration.DEFAULT.getEncoding());
         server.cloneMercurialRepository(cloneDir,"/net/cremi/sdasilva/Documents/dmlib/dmlib.git/client/.hg/","http://voisin:8000");
         */
         
		 RepositoryConfiguration  REPO_CONF = makeRepoConf();
		 Server server = new Server(
                 RepositoryConfiguration.DEFAULT.getHgBin(),
                 RepositoryConfiguration.DEFAULT.getEncoding());
		 
         List<String> commande = new ArrayList<String>();
         commande.add("add");
 
         //commande.add("commit");
         //commande.add("push");
         
         Repository repo = Repository.open(REPO_CONF, new File("/net/cremi/sdasilva/Documents/dmlib/dmlib.git/client/"));
		 AbstractCommand ac = new AddCommand(repo);
		
         server.runCommand(Lists.newArrayList("version"), VersionCommand.on(repo));
         // String xContents = Files.readFirstLine(new File(cloneDir, "x"),utf8());                
             
		 
	 }
	 
	 protected static RepositoryConfiguration makeRepoConf() {
	        RepositoryConfiguration conf = new RepositoryConfiguration();
	        conf.setCachePolicy(CachePolicy.WEAK);
	        return conf;
	    }

}
