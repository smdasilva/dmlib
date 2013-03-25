package ped.dmlib.local.presentation_data;

public class MetaMP3DTO 
{
	public int fileID;
	public String tilte, artist, album, year, trackLenght, comment, codec, channel, sample, bite;
	
	public MetaMP3DTO(int fileID, String tilte, String artist, String album, String year, String trackLenght, String comment, String codec, String channel, String sample, String bite)
	{
		this.fileID = fileID;
		this.tilte = tilte;
		this.artist = artist;
		this.album = album;
		this.year = year;
		this.trackLenght = trackLenght;
		this.comment = comment;
		this.codec = codec;
		this.channel = channel;
		this.sample = sample;
		this.bite = bite;
	}
}