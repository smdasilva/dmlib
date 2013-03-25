package ped.dmlib.local.presentation_event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import ped.dmlib.local.presentation.AddServerPanel;
import ped.dmlib.local.presentation.MainPanel;

public class MyButtonListener implements ActionListener
{
	JButton current;
	
	public void actionPerformed(ActionEvent e) 
	{
		current = (JButton) e.getSource();
		MainPanel main = (MainPanel) current.getParent().getParent();
		
		if(current.getText() == "Add Server")
		{
			JFrame addServerFrame = new JFrame();
			addServerFrame.setTitle("File Update");
			addServerFrame.setSize(400, 200);
			addServerFrame.setLocation(800, 350);
			AddServerPanel myAddServerPanel = new AddServerPanel(main);
			addServerFrame.setContentPane(myAddServerPanel);
			addServerFrame.setResizable(false);
			addServerFrame.setVisible(true);
		}
		else if(current.getText() == "Add Repository")
		{
			JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int returnValue = fileChooser.showOpenDialog(main);
			if(returnValue == JFileChooser.APPROVE_OPTION)
			{
				//try {
					//FileUtils.copy(new File(fileChooser.getSelectedFile().getAbsolutePath()), new File(System.getProperty("user.home")+"/Shared Media Center/"+fileChooser.getSelectedFile().getName()));
					//File rep = new File(System.getProperty("user.home")+"/Shared Media Center/"+fileChooser.getSelectedFile().getName());
					File rep = new File(fileChooser.getSelectedFile().getAbsolutePath());
					main.getTreePanel().refreshTreePanel(rep);
				/*} catch (IOException e1) {
					e1.printStackTrace();
				}*/
			}
		}
		else if(current.getText() == "Add File")
		{
			
		}
	}
}
