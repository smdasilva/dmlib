import java.util.Scanner;

public class EmpreinteMP3 {

	public String empreinteMP3(String file) throws Exception {

		Runtime runtime = Runtime.getRuntime();
		String cmd[] = new String[] { "./fpcalc", file };
		final Process process = runtime.exec(cmd);
		process.waitFor();

		Scanner out = new Scanner(process.getInputStream());
		out.next();
		out.next();

		String res = null;
		while (out.hasNextLine())
			res += out.nextLine();
		out.close();
		return res;

	}

	public static void main(String[] args) throws Exception {

		EmpreinteMP3 mp = new EmpreinteMP3();
		System.out.println(mp
				.empreinteMP3("/net/cremi/rbenojem/espaces/travail/t.mp3"));

	}

}
