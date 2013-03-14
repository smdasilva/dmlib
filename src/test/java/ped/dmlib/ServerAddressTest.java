package ped.dmlib;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ServerAddressTest {
	private ServerAddress s;
	private String name1 = "toto";
	private String address1 = "127.0.0.1:/documents/toto/image";
	
	private String name2 = "titi";
	private String address2 = "127.0.0.1:/documents/titi/music";

	@Before
	public void setUp() throws Exception {
		s = new ServerAddress();
    	s.addAddress(name1, address1);
    	s.addAddress(name2, address2);
	}

	@Test
	public void testGetAddress() {
		assertEquals(address1, s.getAddress(name1));
		assertEquals(address2, s.getAddress(name2));
	}
	
	@Test
	public void testToString() {
		String expected = name2 + " -- " + address2 + "\n" 
						+ name1 + " -- " + address1 + "\n";
		assertEquals(expected, s.toString());
	}
	
	

}
