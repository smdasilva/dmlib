public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ID3Reader id3reader = new ID3Reader("C:/Users/samuel/Desktop/test.MP3");
		id3reader.displayinformations();

	}
}
