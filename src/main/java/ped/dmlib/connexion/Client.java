package ped.dmlib.connexion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import ped.dmlib.Util;
import ped.dmlib.filemanagement.ComputerRepositoriesList;
import ped.dmlib.filemanagement.ComputerRepository;
import ped.dmlib.local.model.Mp3Meta;
import ped.dmlib.temp.FactoryRepo;
import ped.dmlib.temp.Repo;
import ped.dmlib.transfer.BinaryFileTransfer;
import ped.dmlib.transfer.RsyncTransfer;

import com.aragost.javahg.BaseRepository;
import com.aragost.javahg.Changeset;
import com.aragost.javahg.Repository;
import com.aragost.javahg.RepositoryConfiguration;
import com.aragost.javahg.RepositoryConfiguration.CachePolicy;
import com.aragost.javahg.commands.AddCommand;
import com.aragost.javahg.commands.CommitCommand;
import com.aragost.javahg.commands.PullCommand;
import com.aragost.javahg.commands.PushCommand;
import com.aragost.javahg.commands.UpdateCommand;
import com.aragost.javahg.internals.Server;
import com.drew.imaging.ImageProcessingException;
import com.sun.org.apache.bcel.internal.generic.PUSH;


public class Client 
{
	private ArrayList<File> myFileList = new ArrayList<File>();
	private FactoryRepo factoryRepo;
	private Repo localRepository;
	
	/*public Client(String installationPath) throws IOException {
        this.installationPath = installationPath;      
        HgServer server = new HgServer(this.repository, 8000);
         try {
         Process p = server.execute();
         System.out.println(p.waitFor());
         } catch (IOException e) {}
         catch (InterruptedException e) {}    
    }*/

	HgServer server;
	Repository repository;
	String installationPath;
	String repositoryMetaName = "Meta";
	String repositoryHashName = "Hash";
	String repoPath = "/hg/";
	
	private void init(String installationPath) {
		this.factoryRepo = new FactoryRepo(".");
		this.localRepository = factoryRepo.getLocalRepo();
		
		allowPushHgrc();
	}
	
	private void allowPushHgrc() {
		File hgrc = new File(this.installationPath + this.repoPath + ".hg/hgrc");
		FileWriter fw;
		try {
			fw = new FileWriter(hgrc);
			String allowPush = "[web]\n" +
					"ssl_required = false\n"+
					"allow_push = *";

			fw.write(allowPush);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	


	public Client(String installationPath) throws IOException {
		this.installationPath = installationPath;

		// Step 0
		//RepositoryUpdate(repository);

		// Step 1
		// List<Changeset> changesetList = pull(serveur);

		// Step 2
		//List<String> addedFiles = new ArrayList<String>();
		//List<String> modifiedFiles = new ArrayList<String>();
		//List<String> deletedFiles = new ArrayList<String>();

		//for(Changeset c : changesetList) {
		//	addedFiles.add(c.getAddedFiles());
		//	modifiedFiles.add(c.getModifiedFiles());
		//	deletedFiles.add(c.getDeletedFiles());
		// }

		//	for(String fileModified : modifiedFiles) {
		//	String ext = Util.getExt(fileModified);
		//if(ext == ".hash") {
		// Rpull(fichier correspondant)	
		//} else if(ext == ".meta") {
		// Maj des metas
		//}

		// modificationTreatment


		BaseRepository br;
		File f = new File(installationPath);
		this.repository = Repository.open(f);
		
        try {
         br = Repository.open(f);
        } catch (IllegalArgumentException iae) {
         br = Repository.create(f);
        }    
		br.close();


		init(installationPath);

		//this.server = new HgServer(this.repository, 8000);
	}

	public void addServer(String server, String port, String repPath) throws IOException {
		
		pull("http://" + server + ":" + port);
		System.out.println("pull");
		update();
		
		Repo remoteRepo = this.factoryRepo.getRemoteRepository(server);
		BinaryFileTransfer bft = new RsyncTransfer(this.localRepository, remoteRepo);
		
		for (String libraryName : remoteRepo.getLibraries()) {
			this.localRepository.addLibrary(libraryName, repPath + "/" + libraryName);
			bft.pull(libraryName, "*");
		}
		
		this.factoryRepo.saveRepositories();
		
		add();
		commit(this.localRepository.getName() + ".repo");
		
	}
	
	
	//renvoie une liste de fichiers a partir du dossier en parametre
	public ArrayList<File> getFileFromRep(String repPath)
	{
		File rep = new File(repPath);
		if(rep.listFiles().length > 0)
		{
		    for (File f : rep.listFiles())
		    {
		    	Path myPath = Paths.get(f.getAbsolutePath());
		        if (f.isFile() && Files.isReadable(myPath))
		        {
		        	if(getExtension(f) != null) {
		        		myFileList.add(f);
		        	}
		        }
		        else if(f.isDirectory() && Files.isReadable(myPath))
		        {
		        	getFileFromRep(f.getAbsolutePath());
		        }
		    }
		}
		return myFileList;
	}
	
	//renovie l'extension du fichier
	public String getExtension(File currentFile) {
		String extension = currentFile.getAbsolutePath().substring(currentFile.getAbsolutePath().indexOf("."));
		if (extension.matches(".mp3") || extension.matches(".jpg")) {
			return extension;
		}
		else {
			return null;
		}
	}
	
	public void addRepository(File rep) throws ImageProcessingException, FileNotFoundException, CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException 
	{
		this.localRepository.addLibrary(rep.getName(), rep.getAbsolutePath());
		this.factoryRepo.setLocalRepo(localRepository);
		this.factoryRepo.saveRepositories();
		myFileList = getFileFromRep(rep.getAbsolutePath());
		for (File file : myFileList) {
			generateFileMeta(file.getAbsolutePath(),rep.getName());
			add();
			commit(file.getAbsolutePath());
		}
		myFileList.clear();
	}
	

	public List<Changeset> pull(String source) throws IOException {
		PullCommand pull = new PullCommand(this.repository);
		return pull.execute(source);
	}

	public void RepositoryUpdate(String repository) throws IOException, ImageProcessingException, CannotReadException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		List<String> listFile = new ArrayList<String>();                                                                    
		for(String file : listFile) {
			generateFileHash(file);
			//generateFileMeta(file);
		}
	}

	/* RepositoryConfiguration REPO_CONF = makeRepoConf();
       for(String r : repo) {
     File f = new File(r);
     this.re Repository.open(REPO_CONF, f);
     this.repository.add(e);
       }

       if(!this.server.isEmpty()) {
     String[] s = installationPath.split("\\/");
     String last = s[s.length-1];
     String rep_parent = installationPath.substring(0,installationPath.length()-last.length());

     for(String serv : this.server) {
     String repository_path = rep_parent+Math.random()+"/";
     File file = new File(repository_path);
     file.mkdirs();
     /*CloneCommand cl = new CloneCommand(this.repository.get(0));
     cl.execute(installationPath);  */     
	//cloneServer(file,serv);
	//Util.addLineIntoFile(installationPath+"repository", repository_path);
	//}
	//}




	// Detect if modification have occured when the application was close
	//modificationTreatment();
	//}



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


	/*
public void addFile(String filePath) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ImageProcessingException, FileNotFoundException, JpegFormatException {
            storeFileMeta(filePath);
            AddCommand ac = new AddCommand(this.repository);
            ac.execute(filePath);
            CommitCommand ci = new CommitCommand(this.repository);
            ci.message("Ajout du fichier" + filePath).user("admin");
            ci.execute();
        }
	 */
	
	public void push() {
		PushCommand push = new PushCommand(this.repository);
		for(Repo server : this.factoryRepo.getRemoteRepositories()){
			try {
				push.on(this.repository).execute(server.getURL());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void add() {
		AddCommand ac = new AddCommand(this.repository);
		ac.execute();
	}
	
	public void commit(String filePath) {
		 CommitCommand ci = new CommitCommand(this.repository);
         ci.message("Ajout du fichier" + filePath).user("admin");
         ci.execute();
     }

	public void update() throws IOException {
		UpdateCommand up = new UpdateCommand(this.repository);
		up.execute();
	}

	public String getDirectoryFile(String repositoryName, String fileAbsolutePath)
	{
		String path[] = fileAbsolutePath.split(repositoryName);
		return repositoryName+path[1];
	}


	public void generateFileMeta(String srcFile, String repositoryName ) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ImageProcessingException, FileNotFoundException {
		File f = new File(srcFile);
		String name = f.getName().replace(".mp3", ".meta"); 
		String repPath = this.installationPath+this.repositoryMetaName+"/"+getDirectoryFile(repositoryName, srcFile);
		
		String destDirectory = repPath.replace(f.getName(), "");

		if (Util.getExt(srcFile).equals(".mp3")) {
			Mp3Meta fileMetas = new Mp3Meta(f);
			fileMetas.saveTagToFile(destDirectory,name);
		}
	}

	public void generateFileHash(String filePath) throws IOException {
		int[] hash = Util.extractPixel(filePath);
		String result = "";
		int t = 0;
		for(int value : hash) {

			//result = result.concat(String.valueOf(value));
			//System.out.println(result);

			//Util.writeIntoFile("/net/cremi/sdasilva/Documents/Mercurial/toSynchronize/", "TOTO", String.valueOf(value));
			Util.addLineIntoFile("/net/cremi/sdasilva/Documents/Mercurial/toSynchronize/TOTO", value);

		}

		System.out.println(t);
		//System.out.println(result);
		// System.out.println(hash[100]);
		/*File f = new File(filePath);
     String nameFile = (f.getName() != null) ? f.getName().substring(0,f.getName().lastIndexOf('.')) : "";
     String repPath = f.getCanonicalPath().replace(this.repository.getBaseRepository().getDirectory().getAbsolutePath(), "").replace(f.getName(), "");
     Util.writeIntoFile(""+this.repositoryHashName+repPath, nameFile+".hash",hash);*/
	}




	public void initialisation(String installationPath) throws IOException {
		File hg = new File(installationPath+".hg");
		if(!hg.exists()) {
			String serveur = "localhsoyfgmlk";
			File f = new File(installationPath);
			cloneServer(f,serveur);
		}

	}






	public void changeFile(String filePath, String nameFile) throws IOException{
		CommitCommand ci = new CommitCommand(this.repository);
		ci.message("Ajout du fichier" + filePath).user("userrrr");
		ci.execute();
		PushCommand push = new PushCommand(this.repository);
		//for(String server : getServeur()){
		// push.on(this.repository).execute(server);
		//}
		push.on(this.repository).execute("http://cody:8000");
	}

	/*   public boolean isFileModified(File f, String sha)
        {
         FileHashSum hash = new FileHashSum();
         return hash.compareSha1sum(f, sha);
        }

    public boolean IsTagModifier(File f) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, ImageProcessingException, ClassNotFoundException {
    String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
    File fichierMeta = new File("repertoirMeta") ;
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichierMeta)) ;
    if (ext == ".mp3") {
    Mp3Meta metaStorage = (Mp3Meta)ois.readObject() ;
    Mp3Meta metasFichier = new Mp3Meta(f);
    return metasFichier.compareTag(metaStorage);
    } else if (ext == ".jpg") {
    JPGMeta metaStorage = (JPGMeta)ois.readObject() ;
    JPGMeta metasFichier = new JPGMeta(f);
    return true;
    } else {
    return false;
    }
    }*/

	/*       
        public String diff()throws IOException{
            DiffCommand diff = new DiffCommand(this.repository);
            return diff.execute();
        }

        public StatusResult status() {
         StatusCommand status = new StatusCommand(this.repository);
         return status.execute();
        }

        public void outGoingFonc ()throws IOException{

            OutgoingCommand out = new OutgoingCommand(this.repository);
            //System.out.println(out.execute(this.repository));
             List<Changeset> l = new ArrayList();
             l = out.execute("http://cody:8000");
             System.out.println(l);

            Changeset ch = l.get(0);
            System.out.println(ch);
            System.out.println(ch.getModifiedFiles());
        }

         public void incomingFonc ()throws IOException{

            IncomingCommand in = new IncomingCommand(this.repository);
            //System.out.println(out.execute(this.repository));
             List<Changeset> l = new ArrayList();
             //l = in.execute("http://cody:8000");
             System.out.println(l);

            Changeset ch = l.get(0);
            System.out.println(ch);
            System.out.println(ch.getModifiedFiles());
        }

        // Regeneration of the Metadata file and the Hash file if the file has been modified
        public void modificationTreatment() throws ImageProcessingException, CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
         StatusResult result = status();

         if(!result.getUnknown().isEmpty()) {
         List<String> list = new ArrayList<String>();
         list = result.getUnknown();
         for(String file : list) {
         addFile(file);
         }
         }

         if(!result.getModified().isEmpty()) {
         List<String> list = new ArrayList<String>();
         list = result.getModified();
         for(String file : list) {
         storeFileMeta(file);
         storeFileHash(file);
         }
         }


         if(!result.getMissing().isEmpty()) {
         List<String> list = new ArrayList<String>();
         list = result.getMissing();
         for(String file : list) {
         System.out.println("Vous avez supprimé le fichier :"+file);
         }
         }
        }
	 */
}