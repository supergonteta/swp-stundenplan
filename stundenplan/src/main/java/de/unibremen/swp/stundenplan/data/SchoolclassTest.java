package de.unibremen.swp.stundenplan.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SchoolclassTest {

	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * teste die Grundfunktion der Klasse Schoolclass
	 */
	@Test
	public void test() {
		Schoolclass s1 = new Schoolclass();
		s1.setName("3a");
		assertEquals(s1.getName(), "3a");
		s1.setName("3b");
		assertNotEquals(s1.getName(), "3a");
		
	}

}
