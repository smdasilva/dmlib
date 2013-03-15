import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;


public class Dico {
	
	Map<Integer, String[]> dictionary;
	//Metadata metas;
	int idIncrement = 0;
		
	public Dico() {
		this.dictionary = new HashMap<Integer, String[]>();
		}
		
	public int add(String path, String type, String parent) {
		String[] datas  = new String[3];
		datas[0] = path;
		datas[1] = type;
		datas[2] = parent;
		
		int id = fileExist(path);
		if(id > 0) {
			return id;
		} else {
			this.dictionary.put(idIncrement, datas); 
			return idIncrement++; }
		}
	
	public int fileExist(String path) {
		for ( Integer key : this.dictionary.keySet()) {
			String[] datas = this.dictionary.get(key);
			if (datas[0] == "path") {
				return key;
			}
		} 
		return -1;
	}

		
	public String[] getDatas(int id) {
		return this.dictionary.get(id);
	}
	
	public void remove(int id) {
		this.dictionary.remove(id);
	}
	
	public ArrayList<String> getChild(String id) {
		ArrayList<String> result = new ArrayList<String>(); 
		for (String[] datas : this.dictionary.values()) {
			String value = datas[2];
			if (value == id) {
				result.add(value);
			}
		}
	return result;
	}
	
	public ArrayList<String> getMP3File() {
		ArrayList<String> result = new ArrayList<String>(); 
		for (String[] datas : this.dictionary.values()) {
			String value = datas[2];
			if (value == "musique") {
				result.add(value);
			}
		}
		return result;
	}
	
	public ArrayList<String> getJPGFile() {
		ArrayList<String> result = new ArrayList<String>(); 
		for (String[] datas : this.dictionary.values()) {
			String value = datas[2];
			if (value == "jpg") {
				result.add(value);
			}
		}
		return result;
	}
	
	public String getPath(int id) {
		String[] datas = this.getDatas(id);
		String path = datas[2];
		return path;
	}
	
	public String getType(int id) {
		String[] datas = this.getDatas(id);
		String type = datas[2];
		return type;
	}
	
	public String getType(File f) {
		if (f.getAbsolutePath().lastIndexOf(".") > 0) {
		    // On récupère l'extension du fichier
		    String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
		    return ext;
		}
		return null;
	}
		
	public Mp3Meta getMP3Metas(int id) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException {
		File file = new File(this.getPath(id));
		Mp3Meta metas = new Mp3Meta(file);	
		return metas;
	}
	
	/*public JPGMeta getJPGMetas(int id) throws ImageProcessingException, IOException {
		File file = new File(this.getPath(id));
		JPGMeta metas = new JPGMeta(file);	
		return metas;
	}*/
	
	
}
