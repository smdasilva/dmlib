import java.io.File;
import java.io.IOException;

import com.aragost.javahg.BaseRepository;
import com.aragost.javahg.Repository;

/**
 *
 * @author abndoye
 */
public class Testmain {
    
    public static void main(String[] args) throws IOException{
    	String repoPath = "/net/cremi/sdasilva/Documents/Mercurial/serveur";
<<<<<<< HEAD
=======
    	
    	// Create the .hg
>>>>>>> 2f488ef... New
    	BaseRepository br;      
		try {
			br = Repository.open(new File(repoPath));
		} catch (IllegalArgumentException iae) {
			br = Repository.create(new File(repoPath));
		}    
		br.close();
        
<<<<<<< HEAD
    	
        Thread t = new MercurialServer(br, 8000);
        t.start();
=======
    	// Run the server
        Thread t = new MercurialServer(br, 8000);
        t.start();
        
        // Create the client
       
>>>>>>> 2f488ef... New
        //Client cl = new Client(repoPath,"http://cody:8000");
        //cl.RemoveFile("toto.txt");
        //cl.Diff();
       //cl.FileAdded("/net/cremi/abndoye/espaces/travail/MYDEARTEST/client/tototototo"); 
        
        //cl.OutGoingFonc();
        
    }
    
}
