
public class CompareMP3 {

	/**
	 * @param args
	 */
	
	public void compareFichiersMP3{
		try {

		       String filepath = "/net/cremi/rbenojem/espaces/travail/t.mp3;

		       Vector<String> mp3_files = new Vector<String>();
		       mp3_files.add(filepath + "test_with_id3.mp3");
		       mp3_files.add(filepath + "test_without_id3");

		       Iterator<String> i_mp3fp = mp3_files.iterator();

		       while (i_mp3fp.hasNext()){

		          String mp3_fp = i_mp3fp.next();

		          AudioInputStream din = null;
		          File file = new File(mp3_fp);
		          AudioInputStream in = AudioSystem.getAudioInputStream(file);
		          AudioFormat baseFormat = in.getFormat();

		          AudioFormat decodedFormat = new AudioFormat(
		             AudioFormat.Encoding.PCM_SIGNED,
		             baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
		             baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
		             false);
		          din = AudioSystem.getAudioInputStream(decodedFormat, in);

		          String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex( din );
		          System.out.println("Name: "+mp3_fp+" | Hash: "+md5);
		          din.close();

		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
