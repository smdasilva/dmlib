package ped.dmlib;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RepositoriesListTest {
	RepositoriesList rl;

	@Before
	public void setUp() throws Exception {
		rl = new RepositoriesList();
		
	}

	@Test
	public void testSave() {
		String expected ="- !!ped.dmlib.Repository" +
		  "address: 127.0.0.1" +
		  "libraries: {image: /home/image, music: /home/music}" +
		  "name: toto" +
		"- !!ped.dmlib.Repository" +
		  "address: 127.127.127.1" +
		  "libraries: {image: /home/titi/image, music: /home/titi/music}" +
		  "name: titi";
		
		assertEquals(expected, "actual");
	}

}
