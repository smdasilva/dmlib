import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerRepository {

	/**
	 * @param args
	 * @throws IOException
	 */

	public static void addServer(String server) throws IOException {

		File annuaire = new File(
				"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Server");
		// annuaire.getPath();
		// System.out.println(annuaire.getPath());

		// System.out.println(annuaire.exists());
		if (!annuaire.exists()) {
			// ajout d'un serveur dans l'annuaire
			new File("/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Server")
					.createNewFile();
			try {
				BufferedWriter w = new BufferedWriter(
						new FileWriter(
								"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Server",
								true));
				w.write(server + "\n");
				w.flush();
				w.close();
			} catch (NullPointerException a) {
				System.out.println("Erreur : pointeur null");
			} catch (IOException a) {
				System.out.println("Problème d'IO");
			}
		} else {

			try {
				BufferedWriter w = new BufferedWriter(
						new FileWriter(
								"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Server",
								true));
				w.write(server + "\n");
				w.flush();
				w.close();
			} catch (NullPointerException a) {
				System.out.println("Erreur : pointeur null");
			} catch (IOException a) {
				System.out.println("Problème d'IO");
			}

		}

	}

	public static void addRepository(String path) throws IOException {

		File repository = new File(
				"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Repository");

		if (!repository.exists()) {

			new File(
					"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Repository")
					.createNewFile();
			try {
				BufferedWriter w = new BufferedWriter(
						new FileWriter(
								"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Repository",
								true));
				w.write(path + "\n");
				w.flush();
				w.close();
			} catch (NullPointerException a) {
				System.out.println("Erreur : pointeur null");
			} catch (IOException a) {
				System.out.println("Problème d'IO");
			}

		} else {

			try {
				BufferedWriter w = new BufferedWriter(
						new FileWriter(
								"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Repository",
								true));
				w.write(path + "\n");
				w.flush();
				w.close();
			} catch (NullPointerException a) {
				System.out.println("Erreur : pointeur null");
			} catch (IOException a) {
				System.out.println("Problème d'IO");
			}

		}

	}

	public static List<String> getServer() throws FileNotFoundException {
		File dir = new File(
				"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Server");
		List<String> liste = new ArrayList<String>();

		File annuaire = new File(dir, "");
		if (!annuaire.exists()) {
			System.out.println("Annuaire not existe");
			liste = null;
		} else {
			if (annuaire.hashCode() == 0) {
				System.out.println("empty annuaire");
				liste = null;
			} else {

				Scanner scanner = new Scanner(
						new File(
								"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Server"));

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					liste.add(line);
				}
				scanner.close();
			}

		}
		return liste;

	}

	public static List<String> getRepository() throws FileNotFoundException {

		File repository = new File(
				"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Repository");
		List<String> liste = new ArrayList<String>();

		// File repository = new File(dir, "");
		if (!repository.exists()) {
			System.out.println("Repository not existe");
			liste = null;
		} else {
			if (repository.hashCode() == 0) {
				System.out.println("empty repository");
				liste = null;
			} else {

				Scanner scanner = new Scanner(
						new File(
								"/net/cremi/rbenojem/espaces/travail/dmlib/dmlib/Repository"));

				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					liste.add(line);
				}
				scanner.close();
			}

		}
		return liste;

	}


}
