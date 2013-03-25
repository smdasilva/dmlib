package ped.dmlib.local.presentation;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.tree.DefaultTreeModel;

import ped.dmlib.local.controller.LocalController;
import ped.dmlib.local.presentation_data.MyRepDTO;
import ped.dmlib.local.presentation_event.MyJtreeListener;

public class TreePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	LocalController myController;
	MyJtreeListener myTreeListener;
	BorderLayout myBorderLayout;
	MyRepDTO myRoot;
	DefaultTreeModel myTreeModel;
	JTree myTree;
		
	public TreePanel(LocalController controller)
	{
		myController = controller;
		myTreeListener = new MyJtreeListener();
		myBorderLayout = new BorderLayout();
		
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.setLayout(myBorderLayout);
		
		myRoot = new MyRepDTO("Shared Media Center", 0, System.getProperty("user.home")+"/Shared Media Center");
		
		myTreeModel = new DefaultTreeModel(myRoot);
	    myTreeModel.setAsksAllowsChildren(true);
	    myTree = new JTree(myTreeModel);
	    myTree.addMouseListener(myTreeListener);
	    
	    int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
	    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
	    JScrollPane jsp = new JScrollPane(myTree, v, h);
	    this.add(jsp, BorderLayout.CENTER);	    
	}
	
	public void refreshTreePanel(File rep)
	{
		MyRepDTO newRep = myController.constructCurrentTree(rep);
		myRoot.add(newRep);
		myTreeModel.reload();
	}
}
