package ped.dmlib.temp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class FactoryRepo {
	private Repo localRepository = null;
	private List<Repo> remoteRepositories = new ArrayList<Repo>();
	
	private String installationPath;
	private final static String configPah = ".config/";
	private final static String remoteRepoPah = "hg/.config/";
	
	public FactoryRepo(String installationPath) {
		this.installationPath = installationPath;
	}
	
	public Repo newRepo() {
		return new Repo();
	}
	
	public Repo getLocalRepo() {
		return localRepository;
	}
	
	public void setLocalRepo(Repo repo) {
		this.localRepository = repo;
	}
	
	public List<Repo> getRemoteRepositories() {
		return this.remoteRepositories;
	}
	
	public void addRemoteRepo(Repo repo) {
		this.remoteRepositories.add(repo);
	}

	public void loadRepositories() {
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

	private Repo loadRepository(File fileLocal) {
		Repo repoLoaded = null;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Repo.class);
			Unmarshaller um = context.createUnmarshaller();
			
			repoLoaded = (Repo) um.unmarshal(fileLocal);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return repoLoaded;
	}
	
	public void saveRepositories() {
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

}
