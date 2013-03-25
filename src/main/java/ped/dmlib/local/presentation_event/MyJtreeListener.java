package ped.dmlib.local.presentation_event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JTree;

import ped.dmlib.local.presentation.MainPanel;
import ped.dmlib.local.presentation_data.MyFileDTO;
import ped.dmlib.local.presentation_data.MyRepDTO;

public class MyJtreeListener implements MouseListener
{
	MainPanel mainPanel;
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getButton() == 1 && e.getClickCount() == 1)
		{
			JTree currentTree = (JTree) e.getComponent();
			MyRepDTO currentNode = (MyRepDTO) currentTree.getLastSelectedPathComponent();
			if(currentNode != null && currentNode.getId() != -1)
			{
				System.out.println(currentNode.getAbsolutePath());
				mainPanel = (MainPanel) currentTree.getParent().getParent().getParent().getParent();
				mainPanel.getLocalController().clearList();
				ArrayList<MyFileDTO> fileList = mainPanel.getLocalController().getFileFromRep(currentNode.getAbsolutePath(), currentNode.getId());
				mainPanel.getFileExpPanel().loadFiles(fileList);
			}
		}
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
}
