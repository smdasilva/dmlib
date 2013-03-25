package ped.dmlib.local.presentation_data;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import ped.dmlib.local.presentation_event.MyFilePanelListener;

public class MyFileDTO extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private int fileID;
	private boolean isSelected = false;
	MigLayout myMigLayout;
	private JLabel nameLabel;
	private JLabel typeLabel;
	private JLabel sizeLabel;
	private JLabel stateLabel;
	MyFilePanelListener fileListener;
	
	public MyFileDTO()
	{
		myMigLayout = new MigLayout();
		nameLabel = new JLabel("Name");
		nameLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		typeLabel = new JLabel("Type");
		typeLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		sizeLabel = new JLabel("Size");
		sizeLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		stateLabel = new JLabel("Is Update");
		
		this.setLayout(myMigLayout);
		this.add(nameLabel, "width 400");
		this.add(typeLabel, "width 80");
		this.add(sizeLabel, "width 100");
		this.add(stateLabel, "width 80");
	}
	
	public MyFileDTO(int fileID, String name, String type, String size, boolean isupdate)
	{
		this.setFileID(fileID);
		this.setBackground(Color.WHITE);
		
		fileListener = new MyFilePanelListener();
		myMigLayout = new MigLayout();
		nameLabel = new JLabel(name);
		nameLabel.setMaximumSize(new Dimension(400, 20));
		typeLabel = new JLabel(type);
		sizeLabel = new JLabel(size);
		
		//To Do
		if(isupdate) {
			stateLabel = new JLabel(new ImageIcon("pictures/true.jpg"));
		}else{
			stateLabel = new JLabel(new ImageIcon("pictures/false.jpg"));
		}
		
		this.setLayout(myMigLayout);
		this.add(nameLabel, "width 400");
		this.add(typeLabel, "width 80");
		this.add(sizeLabel, "width 100");
		this.add(stateLabel, "width 100");
		
		this.addMouseListener(fileListener);
	}

	//Getters and Setters
	public void setFileID(int fileID) {
		this.fileID = fileID;
	}
	public int getFileID() {
		return fileID;
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getNameLabel() {
		return nameLabel.getText();
	}
	public void setNameLabel(String name) {
		nameLabel.setText(name);
	}
	public String getTypeLabel() {
		return typeLabel.getText();
	}
}
