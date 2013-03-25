package ped.dmlib.local.presentation;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ped.dmlib.local.presentation_data.MetaMP3DTO;

public class MP3UpdatePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	MainPanel mainPanel;
	MetaMP3DTO metaDTO;
	public SpringLayout layout;
	
	public JLabel globalSection;
	public JLabel audioSection;
	
	public JLabel fileName;
	public JLabel tilte;
	public JLabel artist;
	public JLabel album;
	public JLabel year;
	public JLabel trackLenght;
	public JLabel comment;
	public JLabel codec;
	public JLabel channel;
	public JLabel sample;
	public JLabel bite;
	
	public JTextField fileName2;
	public JTextField fileName3;
	public JTextField tilte2;
	public JTextField artist2;
	public JTextField album2;
	public JTextField year2;
	public JTextField trackLenght2;
	public JTextArea comment2;
	public JTextField codec2;
	public JTextField channel2;
	public JTextField sample2;
	public JTextField bite2;
	
	public JButton save;
	public JButton cancel;
	
	public MP3UpdatePanel(String name, MetaMP3DTO metaDTO, MainPanel mainPanel)
	{
		this.mainPanel = mainPanel;
		this.metaDTO = metaDTO;
		layout = new SpringLayout();
		
		globalSection = new JLabel("General");
		audioSection = new JLabel("Audio");
		
		fileName = new JLabel("File Name : ");
		tilte = new JLabel("Title : ");
		artist = new JLabel("Artist : ");
		album = new JLabel("Album : ");
		year = new JLabel("Year : ");
		trackLenght = new JLabel("Track Lenght : ");
		comment = new JLabel("Comments : ");
		codec = new JLabel("Codec : ");
		channel = new JLabel("Channels : ");
		sample = new JLabel("Sample Rate : ");
		bite = new JLabel("Bite Rate : ");
		
		fileName2 = new JTextField(name.substring(0, name.indexOf(".")));
		fileName3 = new JTextField(".mp3");
		tilte2 = new JTextField(metaDTO.tilte);
		artist2 = new JTextField(metaDTO.artist);
		album2 = new JTextField(metaDTO.album);
		year2 = new JTextField(metaDTO.year);
		trackLenght2 = new JTextField(metaDTO.trackLenght);
		comment2 = new JTextArea(metaDTO.comment);
		codec2 = new JTextField(metaDTO.codec);
		channel2 = new JTextField(metaDTO.channel);
		sample2 = new JTextField(metaDTO.sample);
		bite2 = new JTextField(metaDTO.bite);
		
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MP3UpdatePanel panel = (MP3UpdatePanel) ((Component) e.getSource()).getParent();
				JFrame frame = (JFrame) (panel.getParent().getParent().getParent());
				panel.mainPanel.getParent().getParent().getParent().setEnabled(true);
				frame.dispose();
			}
		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MP3UpdatePanel panel = (MP3UpdatePanel) ((Component) e.getSource()).getParent();
				JFrame frame = (JFrame) (panel.getParent().getParent().getParent());
				
				panel.mainPanel.getLocalController().updateMP3File(panel.metaDTO.fileID, tilte2.getText(), artist2.getText(),
						album2.getText(), year2.getText(), comment2.getText());
				panel.mainPanel.getLocalController().renameFile(panel.metaDTO.fileID, fileName2.getText()+fileName3.getText());
				
				panel.mainPanel.getFileExpPanel().renameFileDTO(panel.metaDTO.fileID, fileName2.getText()+fileName3.getText());
				
				frame.dispose();
			}
		});
		
		JScrollPane scrollComment = new JScrollPane(comment2);
		scrollComment.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		fileName3.setEditable(false);
		trackLenght2.setEditable(false);
		codec2.setEditable(false);
		channel2.setEditable(false);
		sample2.setEditable(false);
		bite2.setEditable(false);
		
		fileName2.setBorder(BorderFactory.createLoweredBevelBorder());
		fileName3.setBorder(BorderFactory.createLoweredBevelBorder());
		tilte2.setBorder(BorderFactory.createLoweredBevelBorder());
		artist2.setBorder(BorderFactory.createLoweredBevelBorder());
		album2.setBorder(BorderFactory.createLoweredBevelBorder());
		year2.setBorder(BorderFactory.createLoweredBevelBorder());
		trackLenght2.setBorder(BorderFactory.createLoweredBevelBorder());
		codec2.setBorder(BorderFactory.createLoweredBevelBorder());
		channel2.setBorder(BorderFactory.createLoweredBevelBorder());
		sample2.setBorder(BorderFactory.createLoweredBevelBorder());
		bite2.setBorder(BorderFactory.createLoweredBevelBorder());
		scrollComment.setBorder(BorderFactory.createLoweredBevelBorder());

		this.setLayout(layout);
		
		this.add(globalSection);
		this.add(fileName);
		this.add(fileName2);
		this.add(fileName3);
		this.add(tilte);
		this.add(tilte2);
		this.add(artist);
		this.add(artist2);
		this.add(album);
		this.add(album2);
		this.add(year);
		this.add(year2);
		this.add(trackLenght);
		this.add(trackLenght2);
		this.add(comment);
		this.add(scrollComment);
		this.add(audioSection);
		this.add(codec);
		this.add(codec2);
		this.add(channel);
		this.add(channel2);
		this.add(sample);
		this.add(sample2);
		this.add(bite);
		this.add(bite2);
		
		this.add(save);
		this.add(cancel);
		
			
		//Position by Top
		layout.putConstraint(SpringLayout.NORTH, globalSection, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, fileName, 10, SpringLayout.SOUTH, globalSection);
		layout.putConstraint(SpringLayout.NORTH, fileName2, 10, SpringLayout.SOUTH, globalSection);
		layout.putConstraint(SpringLayout.NORTH, fileName3, 10, SpringLayout.SOUTH, globalSection);
		layout.putConstraint(SpringLayout.NORTH, tilte, 10, SpringLayout.SOUTH, fileName);
		layout.putConstraint(SpringLayout.NORTH, tilte2, 10, SpringLayout.SOUTH, fileName);
		layout.putConstraint(SpringLayout.NORTH, artist, 10, SpringLayout.SOUTH, tilte);
		layout.putConstraint(SpringLayout.NORTH, artist2, 10, SpringLayout.SOUTH, tilte);
		layout.putConstraint(SpringLayout.NORTH, album, 10, SpringLayout.SOUTH, artist);
		layout.putConstraint(SpringLayout.NORTH, album2, 10, SpringLayout.SOUTH, artist);
		layout.putConstraint(SpringLayout.NORTH, year, 10, SpringLayout.SOUTH, album);
		layout.putConstraint(SpringLayout.NORTH, year2, 10, SpringLayout.SOUTH, album);
		layout.putConstraint(SpringLayout.NORTH, trackLenght, 10, SpringLayout.SOUTH, year);
		layout.putConstraint(SpringLayout.NORTH, trackLenght2, 10, SpringLayout.SOUTH, year);
		layout.putConstraint(SpringLayout.NORTH, comment, 10, SpringLayout.SOUTH, trackLenght);
		layout.putConstraint(SpringLayout.NORTH, scrollComment, 10, SpringLayout.SOUTH, trackLenght);
		layout.putConstraint(SpringLayout.NORTH, audioSection, 10, SpringLayout.SOUTH, scrollComment);
		layout.putConstraint(SpringLayout.NORTH, codec, 10, SpringLayout.SOUTH, audioSection);
		layout.putConstraint(SpringLayout.NORTH, codec2, 10, SpringLayout.SOUTH, audioSection);
		layout.putConstraint(SpringLayout.NORTH, channel, 10, SpringLayout.SOUTH, codec);
		layout.putConstraint(SpringLayout.NORTH, channel2, 10, SpringLayout.SOUTH, codec);
		layout.putConstraint(SpringLayout.NORTH, sample, 10, SpringLayout.SOUTH, channel);
		layout.putConstraint(SpringLayout.NORTH, sample2, 10, SpringLayout.SOUTH, channel);
		layout.putConstraint(SpringLayout.NORTH, bite, 10, SpringLayout.SOUTH, sample);
		layout.putConstraint(SpringLayout.NORTH, bite2, 10, SpringLayout.SOUTH, sample);
		
		//Position by Left
		layout.putConstraint(SpringLayout.WEST, globalSection, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, fileName, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, tilte, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, artist, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, album, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, year, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, trackLenght, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, comment, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, audioSection, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, codec, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, channel, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, sample, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, bite, 30, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.WEST, fileName2, 80, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, fileName3, 5, SpringLayout.EAST, fileName2);
		layout.putConstraint(SpringLayout.WEST, tilte2, 80, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, artist2, 80, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, album2, 80, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, year2, 80, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, trackLenght2, 80, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, scrollComment, 80, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, codec2, 80, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, channel2, 80, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, sample2, 80, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, bite2, 80, SpringLayout.EAST, globalSection);
		
		//Position by Right
		layout.putConstraint(SpringLayout.EAST, fileName2, -50, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, fileName3, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, tilte2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, artist2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, album2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, year2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, trackLenght2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, scrollComment, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, codec2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, channel2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, sample2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, bite2, -10, SpringLayout.EAST, this);
		
		//Position for Button
		layout.putConstraint(SpringLayout.SOUTH, save, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, save, 60, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, cancel, -60, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, save, -30, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, cancel, 30, SpringLayout.HORIZONTAL_CENTER, this);
		
		//Components Size
		layout.putConstraint(SpringLayout.SOUTH, scrollComment, 80, SpringLayout.NORTH, scrollComment);
	}
}
