package src;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


import com.aragost.javahg.*;
import com.aragost.javahg.commands.CloneCommand;
import com.aragost.javahg.internals.Server;
import com.aragost.javahg.internals.Utils;
import com.google.common.io.Files;

public class MercurialTools {

	public void cloneServer(File rep, String hgrcPath) {
            Server server = new Server(
                 RepositoryConfiguration.DEFAULT.getHgBin(),
                 RepositoryConfiguration.DEFAULT.getEncoding());    
            server.cloneMercurialRepository(rep,hgrcPath,"http://reinhardt:8000");
	}
	
	 public File createRepository(String path){
		 File dir = new File(path);
	    	if (!dir.exists()) {
	    	new File(path).mkdir(); 
	        Server server = new Server(RepositoryConfiguration.DEFAULT.getHgBin(),
	                                   RepositoryConfiguration.DEFAULT.getEncoding());
	        
	        server.initMecurialRepository(dir);
	    	}
                return dir;
	    }
	
	 
}
