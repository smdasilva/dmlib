/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mercurial;

import java.io.IOException;

/**
 *
 * @author abndoye
 */
public class Testmain {
    
    public static void main(String[] args) throws IOException{
        
        Client cl = new Client("/net/cremi/abndoye/espaces/travail/MYDEARTEST/client","http://cody:8000");
        //cl.RemoveFile("toto.txt");
        //cl.Diff();
       //cl.FileAdded("/net/cremi/abndoye/espaces/travail/MYDEARTEST/client/tototototo"); 
        
       // cl.OutGoingFonc();
    }
    
}
