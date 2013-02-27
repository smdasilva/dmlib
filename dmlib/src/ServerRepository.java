import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerRepository {

	/**
	 * @param args
	 * @throws IOException
	 */
	
	static File annuaire;
	static File dir;
	static File repo;
	static File repository;

	public void addServer(String server) throws IOException {
		File dir = new File(
				"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/ServerRepository");
		System.out.println(dir.exists());
		annuaire = new File(dir, "");

		if (!annuaire.exists()) {
			// ajout d'un serveur dans l'annuaire
			new File(dir, "").createNewFile();
			PrintWriter write;
			try {
				write = new PrintWriter(new FileWriter(annuaire));
				write.print(server + "\n");
				write.flush();
				write.close();
			} catch (NullPointerException a) {
				System.out.println("Erreur : pointeur null");
			} catch (IOException a) {
				System.out.println("Problème d'IO");
			}
		}

	}

	public void addRepository(String path) throws IOException {
		File repo = new File(
				"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/ServerRepository");
		System.out.println(repo.exists());
		repository = new File(repo, "");

		if (!repository.exists()) {
			// ecrire le repertoire a synchroniser dans le le fichier
			// .repository
			new File(repo, "").createNewFile();
			PrintWriter write;
			try {
				write = new PrintWriter(new FileWriter(repository));
				write.print(path + "\n");
				write.flush();
				write.close();
			} catch (NullPointerException a) {
				System.out.println("Erreur : pointeur null");
			} catch (IOException a) {
				System.out.println("Problème d'IO");
			}
		}

	}

	public List<String> getServer() throws FileNotFoundException {
		File repos = new File("/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/ServerRepository");
		
		File repository = new File(repos, "");
		if (!repository.exists()) {
			System.out.println("Annuaire not existe");
		} else {
			if (repository.hashCode() == 0) {
				System.out.println("empty annuaire");
			} else {
				List<String> liste = new ArrayList<String>();

				Scanner scanner = new Scanner(new File("/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/ServerRepository"));

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					liste.add(line);
				}
				scanner.close();
			}

		}
		return null;

	}

	
	public List<String> getReporitory() throws FileNotFoundException {
		
		return null;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(dir.exists());
	}

}
