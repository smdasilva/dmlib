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

import ped.dmlib.local.presentation_data.MetaJPGDTO;

import com.toedter.calendar.JDateChooser;

public class JPGUpdatePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	MainPanel mainPanel;
	MetaJPGDTO metaDTO;
	public SpringLayout layout;
	
	public JLabel globalSection;
	public JLabel pictureSection;
	
	public JLabel fileName;
	public JLabel title;
	public JLabel comments;
	public JLabel author;
	public JLabel pictureDate;
	public JLabel pictureWidth;
	public JLabel pictureHeight;
	public JLabel compressionType;
	public JLabel dataPrecision;
	
	public JTextField fileName2;
	public JTextField fileName3;
	public JTextField title2;
	public JTextArea comments2;
	public JTextField author2;
	public JDateChooser pictureDate2;
	public JTextField pictureWidth2;
	public JTextField pictureHeight2;
	public JTextField compressionType2;
	public JTextField dataPrecision2;
	
	public JButton save;
	public JButton cancel;
	
	public JPGUpdatePanel(String name, MetaJPGDTO metaDTO, MainPanel mainPanel)
	{
		this.mainPanel = mainPanel;
		this.metaDTO = metaDTO;
		layout = new SpringLayout();
		
		globalSection = new JLabel("General");
		pictureSection = new JLabel("Picture");
		
		fileName = new JLabel("File Name : ");
		title = new JLabel("Title : ");
		comments = new JLabel("Comments : ");
		author = new JLabel("Author : ");
		pictureDate = new JLabel("Picture Date : ");
		pictureWidth = new JLabel("Picture Width : ");
		pictureHeight = new JLabel("Picture Height : ");
		compressionType = new JLabel("Compression Type : ");
		dataPrecision = new JLabel("Data Precision : ");
		
		fileName2 = new JTextField(name.substring(0, name.indexOf(".")));
		fileName3 = new JTextField(".jpg");
		title2 = new JTextField(metaDTO.tilte);
		comments2 = new JTextArea(metaDTO.comments);
		author2 = new JTextField(metaDTO.author);
		
		pictureWidth2 = new JTextField(metaDTO.pictureWidth);
		pictureHeight2 = new JTextField(metaDTO.pictureHeight);
		compressionType2 = new JTextField(metaDTO.compression);
		dataPrecision2 = new JTextField(metaDTO.dataPrecision);
		pictureDate2 = new JDateChooser();
		pictureDate2.setDate(metaDTO.pictureDate);
		
		save = new JButton("Save");
		cancel = new JButton("Cancel");
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPGUpdatePanel panel = (JPGUpdatePanel) ((Component) e.getSource()).getParent();
				JFrame frame = (JFrame) (panel.getParent().getParent().getParent());
				panel.mainPanel.getParent().getParent().getParent().setEnabled(true);
				frame.dispose();
			}
		});
		
		JScrollPane scrollComment = new JScrollPane(comments2);
		scrollComment.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		fileName3.setEditable(false);
		compressionType2.setEditable(false);
		dataPrecision2.setEditable(false);
		pictureWidth2.setEditable(false);
		pictureHeight2.setEditable(false);
		
		fileName2.setBorder(BorderFactory.createLoweredBevelBorder());
		fileName3.setBorder(BorderFactory.createLoweredBevelBorder());
		title2.setBorder(BorderFactory.createLoweredBevelBorder());
		author2.setBorder(BorderFactory.createLoweredBevelBorder());
		pictureWidth2.setBorder(BorderFactory.createLoweredBevelBorder());
		pictureHeight2.setBorder(BorderFactory.createLoweredBevelBorder());
		compressionType2.setBorder(BorderFactory.createLoweredBevelBorder());
		dataPrecision2.setBorder(BorderFactory.createLoweredBevelBorder());
		scrollComment.setBorder(BorderFactory.createLoweredBevelBorder());
		
		this.setLayout(layout);
		
		this.add(globalSection);
		this.add(fileName);
		this.add(fileName2);
		this.add(fileName3);
		this.add(title);
		this.add(title2);
		this.add(comments);
		this.add(scrollComment);
		this.add(pictureSection);
		this.add(author);
		this.add(author2);
		this.add(pictureDate);
		this.add(pictureDate2);
		this.add(pictureWidth);
		this.add(pictureWidth2);
		this.add(pictureHeight);
		this.add(pictureHeight2);
		this.add(compressionType);
		this.add(compressionType2);
		this.add(dataPrecision);
		this.add(dataPrecision2);
		
		this.add(save);
		this.add(cancel);
		
		//Position by Top
		layout.putConstraint(SpringLayout.NORTH, globalSection, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, fileName, 20, SpringLayout.SOUTH, globalSection);
		layout.putConstraint(SpringLayout.NORTH, fileName2, 20, SpringLayout.SOUTH, globalSection);
		layout.putConstraint(SpringLayout.NORTH, fileName3, 20, SpringLayout.SOUTH, globalSection);
		layout.putConstraint(SpringLayout.NORTH, title, 10, SpringLayout.SOUTH, fileName);
		layout.putConstraint(SpringLayout.NORTH, title2, 10, SpringLayout.SOUTH, fileName);
		layout.putConstraint(SpringLayout.NORTH, comments, 10, SpringLayout.SOUTH, title);
		layout.putConstraint(SpringLayout.NORTH, scrollComment, 10, SpringLayout.SOUTH, title);
		layout.putConstraint(SpringLayout.NORTH, pictureSection, 10, SpringLayout.SOUTH, scrollComment);
		layout.putConstraint(SpringLayout.NORTH, author, 10, SpringLayout.SOUTH, pictureSection);
		layout.putConstraint(SpringLayout.NORTH, author2, 10, SpringLayout.SOUTH, pictureSection);
		layout.putConstraint(SpringLayout.NORTH, pictureDate, 10, SpringLayout.SOUTH, author);
		layout.putConstraint(SpringLayout.NORTH, pictureDate2, 10, SpringLayout.SOUTH, author);
		layout.putConstraint(SpringLayout.NORTH, pictureWidth, 10, SpringLayout.SOUTH, pictureDate);
		layout.putConstraint(SpringLayout.NORTH, pictureWidth2, 10, SpringLayout.SOUTH, pictureDate);
		layout.putConstraint(SpringLayout.NORTH, pictureHeight, 10, SpringLayout.SOUTH, pictureWidth);
		layout.putConstraint(SpringLayout.NORTH, pictureHeight2, 10, SpringLayout.SOUTH, pictureWidth);
		layout.putConstraint(SpringLayout.NORTH, compressionType, 10, SpringLayout.SOUTH, pictureHeight);
		layout.putConstraint(SpringLayout.NORTH, compressionType2, 10, SpringLayout.SOUTH, pictureHeight);
		layout.putConstraint(SpringLayout.NORTH, dataPrecision, 10, SpringLayout.SOUTH, compressionType);
		layout.putConstraint(SpringLayout.NORTH, dataPrecision2, 10, SpringLayout.SOUTH, compressionType);
		
		//Position by Left
		layout.putConstraint(SpringLayout.WEST, globalSection, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, fileName, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, title, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, comments, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, pictureSection, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, author, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, pictureDate, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, pictureWidth, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, pictureHeight, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, compressionType, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, dataPrecision, 30, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.WEST, fileName2, 100, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, fileName3, 5, SpringLayout.EAST, fileName2);
		layout.putConstraint(SpringLayout.WEST, title2, 100, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, scrollComment, 100, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, author2, 100, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, pictureDate2, 100, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, pictureWidth2, 100, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, pictureHeight2, 100, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, compressionType2, 100, SpringLayout.EAST, globalSection);
		layout.putConstraint(SpringLayout.WEST, dataPrecision2, 100, SpringLayout.EAST, globalSection);
		
		//Position by Right
		layout.putConstraint(SpringLayout.EAST, fileName2, -50, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, fileName3, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, title2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, scrollComment, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, author2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, pictureDate2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, pictureWidth2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, pictureHeight2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, compressionType2, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, dataPrecision2, -10, SpringLayout.EAST, this);
		
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
