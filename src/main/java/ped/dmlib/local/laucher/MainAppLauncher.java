package ped.dmlib.local.laucher;

import java.io.IOException;

import javax.swing.JFrame;

import ped.dmlib.connexion.Config;
import ped.dmlib.local.controller.LocalController;
import ped.dmlib.local.controller.ServerController;
import ped.dmlib.local.presentation.MainPanel;

public class MainAppLauncher 
{
	static LocalController localController = null;
	static ServerController servController = null;
	
	static MainPanel myMainPanel = null;
	
	public static void main(String[] args) 
	{		
		int frameHeight = 600;
		int frameWidth = 1000;
		
		Config config = new Config(".");
		config.initAll();
		
		try {
			localController = new LocalController();
			servController = new ServerController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JFrame myFrame = new JFrame();
		myMainPanel = new MainPanel(localController, servController, frameWidth, frameHeight);
		
		myFrame.setSize(frameWidth, frameHeight);
		myFrame.setLocation(160, 80);
		myFrame.setTitle("Shared Media Center");
		myFrame.setContentPane(myMainPanel);
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setResizable(false);
		myFrame.setVisible(true);
	}
	
	public static MainPanel getMainPanel()
	{
		return myMainPanel;
	}
}
