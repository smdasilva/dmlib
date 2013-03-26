package ped.dmlib.local.presentation;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class AddServerPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	MainPanel mainPanel;
	SpringLayout layout;
	JLabel title;
	JLabel serverAdd;
	JTextField serverAdd2;
	JTextField serverAdd3;
	JLabel repChooser;
	JTextField repChooser2;
	JButton repChooser3;
	JButton valid;
	JButton cancel;
	
	public AddServerPanel(final MainPanel main)
	{
		mainPanel = main;
		layout = new SpringLayout();
		title = new JLabel("Specify the repository to clone : ");
		serverAdd = new JLabel("Server : ");
		serverAdd2 = new JTextField();
		serverAdd3 = new JTextField();
		repChooser = new JLabel("Target Directory : ");
		repChooser2 = new JTextField();
		repChooser3 = new JButton("...");
		valid = new JButton("Finish");
		cancel = new JButton("Cancel");
		
		repChooser3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddServerPanel panel = (AddServerPanel) ((Component) e.getSource()).getParent();
				JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home")+"/Shared Media Center/");
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				int returnValue = fileChooser.showOpenDialog(panel);
				if(returnValue == JFileChooser.APPROVE_OPTION)
				{
					repChooser2.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddServerPanel panel = (AddServerPanel) ((Component) e.getSource()).getParent();
				JFrame frame = (JFrame) (panel.getParent().getParent().getParent());
				frame.dispose();
			}
		});
		
		valid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddServerPanel panel = (AddServerPanel) ((Component) e.getSource()).getParent();
				String ip = panel.serverAdd2.getText();
				String port = panel.serverAdd3.getText();
				String repPath = panel.repChooser2.getText();
				
				Map<String, String> repList = main.getServerController().addServer(ip, port, repPath);
                                if(repList != null)
                                {
                                    for(String rep : repList.keySet())
                                    {
                                        File f = new File(repList.get(rep));
                                        main.getTreePanel().refreshTreePanel(f);
                                    }
                                }
				JFrame frame = (JFrame) (panel.getParent().getParent().getParent());
				frame.dispose();
			}
		});
		
		this.setLayout(layout);
		
		this.add(title);
		this.add(serverAdd);
		this.add(serverAdd2);
		this.add(serverAdd3);
		this.add(repChooser);
		this.add(repChooser2);
		this.add(repChooser3);
		this.add(valid);
		this.add(cancel);
		
		//Position by Top
		layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.NORTH, serverAdd, 30, SpringLayout.NORTH, title);
		layout.putConstraint(SpringLayout.NORTH, serverAdd2, 30, SpringLayout.NORTH, title);
		layout.putConstraint(SpringLayout.NORTH, serverAdd3, 30, SpringLayout.NORTH, title);
		layout.putConstraint(SpringLayout.NORTH, repChooser, 20, SpringLayout.SOUTH, serverAdd);
		layout.putConstraint(SpringLayout.NORTH, repChooser2, 20, SpringLayout.SOUTH, serverAdd);
		layout.putConstraint(SpringLayout.NORTH, repChooser3, 20, SpringLayout.SOUTH, serverAdd);
		
		//Position by Left
		layout.putConstraint(SpringLayout.WEST, title, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, serverAdd, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, repChooser, 30, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.WEST, serverAdd2, 10, SpringLayout.EAST, repChooser);
		layout.putConstraint(SpringLayout.WEST, serverAdd3, 5, SpringLayout.EAST, serverAdd2);
		layout.putConstraint(SpringLayout.WEST, repChooser2, 10, SpringLayout.EAST, repChooser);
		layout.putConstraint(SpringLayout.WEST, repChooser3, 5, SpringLayout.EAST, repChooser2);
		
		//Position by Right
		layout.putConstraint(SpringLayout.EAST, serverAdd2, -50, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, serverAdd3, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, repChooser2, -40, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, repChooser3, -10, SpringLayout.EAST, this);
		
		layout.putConstraint(SpringLayout.SOUTH, repChooser3, 0, SpringLayout.SOUTH, repChooser2);

		//Position for Button
		layout.putConstraint(SpringLayout.SOUTH, valid, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, valid, 60, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, cancel, -60, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.EAST, valid, -30, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.WEST, cancel, 30, SpringLayout.HORIZONTAL_CENTER, this);
	}
}
