package ped.dmlib.local.presentation_event;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import ped.dmlib.local.presentation.JPGUpdatePanel;
import ped.dmlib.local.presentation.MP3UpdatePanel;
import ped.dmlib.local.presentation.MainPanel;
import ped.dmlib.local.presentation_data.MetaJPGDTO;
import ped.dmlib.local.presentation_data.MetaMP3DTO;
import ped.dmlib.local.presentation_data.MyFileDTO;

public class MyFilePanelListener implements MouseListener
{
	MainPanel mainPanel;
	static int firstSelectedFileIndex;
	MyFileDTO currentFile;
	JPanel container;
	
	public void mouseClicked(MouseEvent e) 
	{
		mainPanel = (MainPanel) e.getComponent().getParent().getParent().getParent().getParent().getParent();
		currentFile = (MyFileDTO) e.getComponent();
		container = (JPanel) currentFile.getParent();
		
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			if(!e.isControlDown() && !e.isShiftDown()) {
				cleanContainer(container);
				selectFile(currentFile);
				firstSelectedFileIndex = container.getComponentZOrder(currentFile);
			}
			else if(e.isShiftDown()) {
				selectFile(currentFile);
				if(countSelectedComponents(container) > 1) {
					cleanContainer(container);
					int currentSelectedFileIndex = container.getComponentZOrder(currentFile);
					for (Component comp : getSelectedComponents(container, firstSelectedFileIndex, currentSelectedFileIndex)) {
						MyFileDTO file = (MyFileDTO) comp;
						selectFile(file);
					}
				}
			}
			else {
				if(currentFile.isSelected()) {
					unSelectFile(currentFile);
				}
				else {
					selectFile(currentFile);
				}
			}
		}
		
		if(e.getButton() == MouseEvent.BUTTON3)
		{				
			JPopupMenu myMenu = new JPopupMenu();
			JMenuItem change = new JMenuItem(" Modify");
			JMenuItem delete = new JMenuItem(" Delete");
			
			if(countSelectedComponents(container) > 1) {
				change.setEnabled(false);
			}
			
			//To update a File
			change.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					JFrame updateFrame = new JFrame();
					updateFrame.setTitle("File Update");
					updateFrame.setSize(400, 500);
					updateFrame.setLocation(800, 280);
					if(currentFile.getTypeLabel().equals(".jpg")) {
						MetaJPGDTO jpgDTO = mainPanel.getLocalController().createJPGDTO(currentFile.getFileID());
						JPGUpdatePanel jpgPanel = new JPGUpdatePanel(currentFile.getNameLabel(), jpgDTO, mainPanel);
						updateFrame.setContentPane(jpgPanel);
					}
					else if(currentFile.getTypeLabel().equals(".mp3")) {
						MetaMP3DTO mp3DTO = mainPanel.getLocalController().createMP3DTO(currentFile.getFileID());
						MP3UpdatePanel mp3Panel = new MP3UpdatePanel(currentFile.getNameLabel(), mp3DTO, mainPanel);
						updateFrame.setContentPane(mp3Panel);
					}
					updateFrame.setResizable(false);
					updateFrame.setVisible(true);
				}
			});
			
			//To delete a File
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					delSelectedComponents(container);
				}
			});
			
			myMenu.add(change);
			myMenu.add(delete);
			myMenu.show(currentFile, e.getX(), e.getY());
		}
	}

	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	
	public void selectFile(MyFileDTO file)
	{
		file.setBackground(new Color(196, 215, 233));
		file.setSelected(true);
	}
	
	public void unSelectFile(MyFileDTO file)
	{
		file.setBackground(Color.WHITE);
		file.setSelected(false);
	}
	
	public void cleanContainer(JPanel cont)
	{
		for (Component comp : cont.getComponents()) {
			MyFileDTO file = (MyFileDTO) comp;
			file.setBackground(Color.WHITE);
			file.setSelected(false);
		}
	}
	
	public int countSelectedComponents(JPanel cont)
	{
		int nbElem = 0;
		for (Component comp : cont.getComponents()) {
			MyFileDTO file = (MyFileDTO) comp;
			if(file.isSelected()) {
				nbElem = nbElem + 1;
			}
		}
		return nbElem;
	}
	
	public ArrayList<Component> getSelectedComponents(JPanel cont, int firstSelectedFileIndex, int currentSelectedFileIndex)
	{
		ArrayList<Component> compList = new ArrayList<Component>();
		if(firstSelectedFileIndex < currentSelectedFileIndex) {
			for(int i = firstSelectedFileIndex; i <= currentSelectedFileIndex; i++) {
				compList.add(cont.getComponent(i));
			}
		}
		else {
			for(int i = firstSelectedFileIndex; i >= currentSelectedFileIndex; i--) {
				compList.add(cont.getComponent(i));
			}
		}
		return compList;
	}
	
	public void delSelectedComponents(JPanel cont)
	{
		for (Component comp : cont.getComponents()) {
			MyFileDTO file = (MyFileDTO) comp;
			if(file.isSelected()) {
				cont.remove(file);
				mainPanel.getLocalController().deleteFile(file.getFileID());
			}
		}
		cont.updateUI();
	}
}
