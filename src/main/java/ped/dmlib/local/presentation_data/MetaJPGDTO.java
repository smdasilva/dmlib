package ped.dmlib.local.presentation_data;

import java.util.Date;

public class MetaJPGDTO 
{
	public int fileID;
	public String tilte, comments, author, pictureWidth, pictureHeight, compression, dataPrecision;
	public Date pictureDate;
	
	public MetaJPGDTO(int fileID, String tilte, String comments, String author, String pictureWidth, String pictureHeight, String compression, String dataPrecision, Date pictureDate)
	{
		this.fileID = fileID;
		this.tilte = tilte;
		this.comments = comments;
		this.author = author;
		this.pictureWidth = pictureWidth;
		this.pictureHeight = pictureHeight;
		this.compression = compression;
		this.dataPrecision = dataPrecision;
		this.pictureDate = pictureDate;
	}
}
