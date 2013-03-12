import java.io.Serializable;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.tag.Tag;

public class Personne implements Serializable {
	static private final long serialVersionUID = 6L;
	private String nom;
	private String prenom; 
	private Integer age;


	public Personne(String nom, String prenom, Integer age) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}

	public String toString() {
		return nom + " " + nom + " " + age + " ans";
	} 
}