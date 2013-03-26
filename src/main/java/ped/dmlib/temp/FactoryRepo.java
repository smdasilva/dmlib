package ped.dmlib.temp;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.common.net.InetAddresses;

public class FactoryRepo {
	private Repo localRepository = null;
	private List<Repo> remoteRepositories = new ArrayList<Repo>();
	
	private String installationPath;
	private final static String configPah = "/.config/";
	private final static String remoteRepoPah = "/hg/.config/";
	
	public FactoryRepo(String installationPath) {
		this.installationPath = installationPath;
	}
	
	public Repo newRepo() {
		return new Repo();
	}
	
	public Repo getLocalRepo() {
		if(this.localRepository == null)
			this.localRepository = this.loadRepository(new File(this.installationPath + this.configPah + "local.repo"));
			
		if(this.localRepository == null)
			this.localRepository = new Repo();
		
		return localRepository;
	}
	
	public void setLocalRepo(Repo repo) {
		this.localRepository = repo;
	}
	
	public List<Repo> getRemoteRepositories() {
            this.loadRemoteRepositories();
		return this.remoteRepositories;
	}
	
	public Repo getRemoteRepository(String address) {
		InetAddress ia = null;
		
		try {
			ia = InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		this.loadRemoteRepositories();
		
		for (Repo tmpRepo : this.remoteRepositories) {
			if(tmpRepo.getAddress().equals(ia.getHostAddress()))
				return tmpRepo;
		}
		
		System.out.println(this.remoteRepositories);
		
		return null;
	}
	
	public void addRemoteRepo(Repo repo) {
		this.remoteRepositories.add(repo);
	}
	
	private void createArbo(String path) {
		File dir = new File(path);
		dir.mkdirs();
	}

	public void loadRepositories() {
		createArbo(installationPath + configPah);
		createArbo(installationPath + remoteRepoPah);
		
		String pathLocal = installationPath + configPah + "local.repo";
		File fileLocal = new File(pathLocal);
		
		if(fileLocal.exists())
			this.localRepository = loadRepository(fileLocal);
		
		String reposPath = installationPath + remoteRepoPah;
		File reposDir = new File(reposPath);
		
		for (File fileRepo : reposDir.listFiles()) {
			Repo repoRemote = loadRepository(fileRepo);
			if(!repoRemote.equals(localRepository))
				remoteRepositories.add(repoRemote);
		}
	}
	
	private void loadRemoteRepositories() {
		String reposPath = installationPath + remoteRepoPah;
		File reposDir = new File(reposPath);
		
		for (File fileRepo : reposDir.listFiles()) {
			Repo repoRemote = loadRepository(fileRepo);
			
			if(!repoRemote.equals(localRepository))
				remoteRepositories.add(repoRemote);
			
//                        if(!repoRemote.getAddress().equals(localRepository.getAddress()))
//                        {
//                            remoteRepositories.add(repoRemote);
//                            /*if(repoRemote.getPort() != (localRepository.getPort()))
//                            {
//                                remoteRepositories.add(repoRemote);      
//                            }*/
//                        }
		}
	}

	private Repo loadRepository(File fileLocal) {
		Repo repoLoaded = null;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Repo.class);
			Unmarshaller um = context.createUnmarshaller();
			
			repoLoaded = (Repo) um.unmarshal(fileLocal);
		} catch (JAXBException e) {
			return null;
		}
		
		return repoLoaded;
	}
	
	public void saveRepositories() {
		createArbo(installationPath + configPah);
		createArbo(installationPath + remoteRepoPah);
		
		String pathLocal = installationPath + configPah + "local.repo"; 
		File fileLocal = new File(pathLocal);
		
		if(this.localRepository != null)
			saveRepository(localRepository, fileLocal);
		
		String reposPath = installationPath + remoteRepoPah;
		//File reposDir = new File(reposPath);
		
		for (Repo repoToSave : this.remoteRepositories) {
			File fileRepoToSave = new File(reposPath + repoToSave.getName() + ".repo");
			saveRepository(repoToSave, fileRepoToSave);
		}
		
		File fileRepoToSave = new File(reposPath + this.localRepository.getName() + ".repo");
		saveRepository(this.localRepository, fileRepoToSave);
	}

	
	private void saveRepository(Repo repoToSave, File fileRepo) {
		JAXBContext context;
		
		try {
			context = JAXBContext.newInstance(Repo.class);
			Marshaller m = context.createMarshaller();
			
			m.marshal(repoToSave, fileRepo);

		} catch (JAXBException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public String toString() {
		return "FactoryRepo [localRepository=" + localRepository
				+ ",\n remoteRepositories=" + remoteRepositories + "]";
	}

	
}
