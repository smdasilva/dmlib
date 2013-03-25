package ped.dmlib.local.laucher;

import java.io.IOException;

import javax.swing.JFrame;

import ped.dmlib.local.controller.LocalController;
import ped.dmlib.local.controller.ServerController;
import ped.dmlib.local.presentation.MainPanel;

public class MainAppLauncher 
{
	public static void main(String[] args) 
	{	
		/*try {
			JPGMeta2 jpg = new JPGMeta2("/net/cremi/echicoua/espaces/travail/PED_BibMultiDis/testfiles/pictures/others/1325286294.jpg");
		} catch (ImageProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JpegFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		int frameHeight = 600;
		int frameWidth = 1000;
		LocalController localController = null;
		ServerController servController = null;
		try {
			localController = new LocalController();
			servController = new ServerController();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JFrame myFrame = new JFrame();
		MainPanel myMainPanel = new MainPanel(localController, servController, frameWidth, frameHeight);
		
		myFrame.setSize(frameWidth, frameHeight);
		myFrame.setLocation(400, 200);
		myFrame.setTitle("Shared Media Center");
		myFrame.setContentPane(myMainPanel);
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setResizable(false);
		myFrame.setVisible(true);
	}
}
