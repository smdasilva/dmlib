import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;


public class dico {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

			      // create a new hashtable
			      Dictionary d = new Hashtable();
			      String[] t = new String[4];
			      t[0] = "sam";
			      t[1] = "toto";
			      d.put(1, t);
			      System.out.println(t[0]);
			      String[] f = (String[]) d.get(1);
			      System.out.println(f[0]);
			      System.out.println("----------");
			      //System.out.println(f[0]);
			      
			      // add 2 elements
			      
			      d.put(4, "Chocolate" + "Bar");
			      System.out.println("\"1\" is " + d.get(1));
			      System.out.println("\"4\" is " + d.get(4));

			      // generates a series of elements, one at a time
			      for (Enumeration e = d.elements(); e.hasMoreElements();) {
			         System.out.println(e.nextElement());
			      }
		

	}

}
