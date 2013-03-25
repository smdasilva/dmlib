package ped.dmlib.local.presentation;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import ped.dmlib.local.controller.LocalController;

public class FileInfoPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public FileInfoPanel(LocalController controller) 
	{
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		
	}

}
