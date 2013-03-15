package mercurial;

import java.io.Serializable;

import org.jaudiotagger.tag.FieldKey;


public class Mp3MetaSerializable implements Serializable {

	private String artist;
	private String album;
	private String title;
	private String year;
	private String comment;
	private String track;
	private String disc_no;
	private String composer;
	private String genre;
	private String totalTrack;
	
	
	public Mp3MetaSerializable(String artist, String album, String title, String year, String comment, String track, String disc_no, String composer, String genre, String totalTrack) {
		this.artist = artist;
		this.album = album;
		this.title = title;
		this.year = year;
		this.comment = comment;
		this.track = track;
		this.disc_no = disc_no;
		this.composer = composer;
		this.genre = genre;
		this.totalTrack = totalTrack;
	}

		public String getArtist() {
			return this.artist;
		}
		
		public String getAlbum() {
			return this.album;
		}
		
		public String getTitle() {
			return this.title;
		}
		
		public String getComment() {
			return this.comment;
		}
		
		public String getYear() {
			return this.year;
		}
		
		public String getTrack() {
			return this.track;
		}
		
		public String getDisc() {
			return this.disc_no;
		}
		
		public String getComposer() {
			return this.composer;
		}
		
		public String getGenre() {
			return this.genre;
		}
		
		public String getTotalTrack() {
			return this.totalTrack;
		}
}
