package ped.dmlib.local.presentation;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import ped.dmlib.local.controller.LocalController;
import ped.dmlib.local.controller.ServerController;

public class MainPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	SpringLayout myLayout;
	ButtonPanel buttonPanel;
	TreePanel treePanel;
	FileExplorerPanel filexpPanel;
	FileInfoPanel fileinfoPanel;
	
	LocalController myLocalController;
	ServerController myServController;
	
	public MainPanel(LocalController controller, ServerController servController, int currentWidth, int currentHeight)
	{
		myLocalController = controller;
		myServController = servController;
		myLayout = new SpringLayout();
		buttonPanel = new ButtonPanel(controller);
		treePanel = new TreePanel(controller);
		filexpPanel = new FileExplorerPanel(controller);
		fileinfoPanel = new FileInfoPanel(controller);
		
		this.setLayout(myLayout);
		this.add(buttonPanel);
		this.add(treePanel);
		this.add(filexpPanel);
		this.add(fileinfoPanel);
		
		//Button Panel
		myLayout.putConstraint(SpringLayout.WEST, buttonPanel, 10, SpringLayout.WEST, this);
		myLayout.putConstraint(SpringLayout.EAST, buttonPanel, -10, SpringLayout.EAST, this);
		myLayout.putConstraint(SpringLayout.NORTH, buttonPanel, 10, SpringLayout.NORTH, this);
		myLayout.putConstraint(SpringLayout.SOUTH, buttonPanel, 50, SpringLayout.NORTH, this);
		
		//Tree Panel
		myLayout.putConstraint(SpringLayout.WEST, treePanel, 10, SpringLayout.WEST, this);
		myLayout.putConstraint(SpringLayout.EAST, treePanel, currentWidth/4, SpringLayout.WEST, this);
		myLayout.putConstraint(SpringLayout.NORTH, treePanel, 10, SpringLayout.SOUTH, buttonPanel);
		myLayout.putConstraint(SpringLayout.SOUTH, treePanel, -10, SpringLayout.SOUTH, this);
		
		//File Explorer Panel
		myLayout.putConstraint(SpringLayout.WEST, filexpPanel, 10, SpringLayout.EAST, treePanel);
		myLayout.putConstraint(SpringLayout.EAST, filexpPanel, -10, SpringLayout.EAST, this);
		myLayout.putConstraint(SpringLayout.NORTH, filexpPanel, 10, SpringLayout.SOUTH, buttonPanel);
		myLayout.putConstraint(SpringLayout.SOUTH, filexpPanel, -currentHeight/4, SpringLayout.SOUTH, this);
		
		//File Info Panel
		myLayout.putConstraint(SpringLayout.WEST, fileinfoPanel, 10, SpringLayout.EAST, treePanel);
		myLayout.putConstraint(SpringLayout.EAST, fileinfoPanel, -10, SpringLayout.EAST, this);
		myLayout.putConstraint(SpringLayout.NORTH, fileinfoPanel, 10, SpringLayout.SOUTH, filexpPanel);
		myLayout.putConstraint(SpringLayout.SOUTH, fileinfoPanel, -10, SpringLayout.SOUTH, this);
	}
	
	public LocalController getLocalController() {
		return this.myLocalController;
	}
	public ServerController getServerController() {
		return this.myServController;
	}
	public FileExplorerPanel getFileExpPanel() {
		return this.filexpPanel;
	}
	public TreePanel getTreePanel() {
		return this.treePanel;
	}
}
