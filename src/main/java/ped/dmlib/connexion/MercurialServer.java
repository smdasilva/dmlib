package ped.dmlib.connexion;

import java.io.IOException;

import com.aragost.javahg.BaseRepository;

public class MercurialServer extends Thread {
	HgServer server;

	public MercurialServer(BaseRepository br, int port) {
		server = new HgServer(br, port);
	}

	@Override
	public void run() {
		this.execute();
	}

	private void execute() {
		try {
			Process p = server.execute();
			p.waitFor();
		} catch (IOException e) {}

		catch (InterruptedException e) {}
	}
}


