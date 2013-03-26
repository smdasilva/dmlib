package ped.dmlib.local.presentation;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import ped.dmlib.local.controller.LocalController;
import ped.dmlib.local.presentation_event.MyButtonListener;

public class ButtonPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	SpringLayout mySpringLayout;
	MyButtonListener buttonListener;
	JButton addFileButton;
	JButton addRepButton;
	JButton addServButton;
	JSeparator separatorVertical2;
	JLabel searchLabel;
	JTextField searchTextField;
	
	public ButtonPanel(LocalController controller)
	{
		mySpringLayout = new SpringLayout();
		buttonListener = new MyButtonListener();
		addFileButton = new JButton("Add File");
		addRepButton = new JButton("Add Repository");
		addServButton = new JButton("Add Server");
		separatorVertical2 = new JSeparator(Separator.VERTICAL);
		searchLabel = new JLabel("File Search : ");
		searchTextField = new JTextField();
		
		addFileButton.addActionListener(buttonListener);
		addRepButton.addActionListener(buttonListener);
		addServButton.addActionListener(buttonListener);
		
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.setLayout(mySpringLayout);
		this.add(addFileButton);
		this.add(separatorVertical2);
		this.add(addRepButton);
		this.add(addServButton);
		this.add(searchLabel);
		this.add(searchTextField);
		
		//Position by left
		mySpringLayout.putConstraint(SpringLayout.WEST, addFileButton, 10, SpringLayout.WEST, this);
		mySpringLayout.putConstraint(SpringLayout.WEST, separatorVertical2, 10, SpringLayout.EAST, addFileButton);
		mySpringLayout.putConstraint(SpringLayout.WEST, addRepButton, 10, SpringLayout.EAST, separatorVertical2);
		mySpringLayout.putConstraint(SpringLayout.WEST, addServButton, 10, SpringLayout.EAST, addRepButton);
		mySpringLayout.putConstraint(SpringLayout.WEST, searchTextField, -200, SpringLayout.EAST, this);
		
		//Position by right
		mySpringLayout.putConstraint(SpringLayout.EAST, searchTextField, -10, SpringLayout.EAST, this);
		mySpringLayout.putConstraint(SpringLayout.EAST, searchLabel, -10, SpringLayout.WEST, searchTextField);
		
		//Position by north
		mySpringLayout.putConstraint(SpringLayout.NORTH, addFileButton, 5, SpringLayout.NORTH, this);
		mySpringLayout.putConstraint(SpringLayout.NORTH, separatorVertical2, 5, SpringLayout.NORTH, this);
		mySpringLayout.putConstraint(SpringLayout.NORTH, addRepButton, 5, SpringLayout.NORTH, this);
		mySpringLayout.putConstraint(SpringLayout.NORTH, addServButton, 5, SpringLayout.NORTH, this);
		mySpringLayout.putConstraint(SpringLayout.NORTH, searchLabel, 5, SpringLayout.NORTH, this);
		mySpringLayout.putConstraint(SpringLayout.NORTH, searchTextField, 5, SpringLayout.NORTH, this);
		
		//Position by south
		mySpringLayout.putConstraint(SpringLayout.SOUTH, addFileButton, -5, SpringLayout.SOUTH, this);
		mySpringLayout.putConstraint(SpringLayout.SOUTH, separatorVertical2, -5, SpringLayout.SOUTH, this);
		mySpringLayout.putConstraint(SpringLayout.SOUTH, addRepButton, -5, SpringLayout.SOUTH, this);
		mySpringLayout.putConstraint(SpringLayout.SOUTH, addServButton, -5, SpringLayout.SOUTH, this);
		mySpringLayout.putConstraint(SpringLayout.SOUTH, searchLabel, -5, SpringLayout.SOUTH, this);
		mySpringLayout.putConstraint(SpringLayout.SOUTH, searchTextField, -5, SpringLayout.SOUTH, this);
	}
}
