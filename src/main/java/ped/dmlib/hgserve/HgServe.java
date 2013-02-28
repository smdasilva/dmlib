package ped.dmlib.hgserve;

import java.io.IOException;

import com.aragost.javahg.Repository;
import com.aragost.javahg.log.Logger;
import com.aragost.javahg.log.LoggerFactory;

/**
 * HgServe serve = new HgServe(repository, 8000);
 * try {
 * 	Process p = serve.execute();
 * 	System.out.println(p.waitFor());
 * } catch (IOException e) {}
 *   catch (InterruptedException e) {}
 *   
 *   if p.waitFor() returns 255 then address already in use, try another port
 */

public class HgServe {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	
	private Repository repository;
	private int port;

	public HgServe(Repository repository, int port) {
		init(repository, port);
	}
	
	public HgServe(Repository repository) {
		init(repository, 8000);
	}
	
	private void init(Repository repository, int port) {
		this.repository = repository;
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Process execute() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        
        String[] cmd = new String[]{"hg", "serve", "-p " + port};
        
        LOG.info("Start hg serve in " + repository.getDirectory() + " on port " + this.port);
        
        Process p = runtime.exec(cmd, null, repository.getDirectory());

        return p;
    }

}