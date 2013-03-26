package ped.dmlib.connexion;

import java.io.IOException;

import com.aragost.javahg.BaseRepository;
import com.aragost.javahg.log.Logger;
import com.aragost.javahg.log.LoggerFactory;

public class MercurialServer extends Thread {
	private static final Logger LOG = LoggerFactory.getLogger(HgServer.class);

	private HgServer server;

	public MercurialServer(BaseRepository br, int port) {
		server = new HgServer(br, port);
	}

	@Override
	public void run() {
		this.execute();
	}

	private void execute() {
		int returnCode = 0;
		try {
			Process p = server.execute();
			returnCode = p.waitFor();
			
			LOG.info("hg serve return code " + returnCode);
		} catch (IOException e) {
			LOG.error("hg serve IOException");
		}
		catch (InterruptedException e) {
			LOG.error("hg serve InterruptedException");
		}
	}
}


