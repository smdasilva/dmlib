package ped.dmlib.local.presentation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;
import ped.dmlib.local.controller.LocalController;
import ped.dmlib.local.presentation_data.MyFileDTO;

public class FileExplorerPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
		
	BorderLayout myBorderLayout;
	MigLayout myMigLayout;
	JPanel principalPanel;
	MyFileDTO titlePanel;
	
	public FileExplorerPanel(LocalController controller)
	{
		myBorderLayout = new BorderLayout();
		principalPanel = new JPanel();
		myMigLayout = new MigLayout();
		
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.setLayout(myBorderLayout);
		principalPanel.setLayout(myMigLayout);
		principalPanel.setBackground(Color.WHITE);
		this.add(principalPanel, BorderLayout.CENTER);
		
		titlePanel = new MyFileDTO(); 
		this.add(titlePanel, BorderLayout.NORTH);
				
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
	    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
	    JScrollPane jsp = new JScrollPane(principalPanel, v, h);
	    this.add(jsp, BorderLayout.CENTER);
	}
	
	public void clearPanel()
	{
		principalPanel.removeAll();
	}
	
	public void loadFiles(ArrayList<MyFileDTO> fileList)
	{
		clearPanel();
		for (MyFileDTO myFileDTO : fileList) {
			principalPanel.add(myFileDTO, "wrap 0");
		}
		principalPanel.updateUI();
	}
	
	public void renameFileDTO(int fileID, String fileName)
	{
		for (Component comp : principalPanel.getComponents()) {
			MyFileDTO file = (MyFileDTO) comp;
			if(file.getFileID() == fileID) {
				file.setNameLabel(fileName);
			}
		}
		principalPanel.updateUI();
	}
}
