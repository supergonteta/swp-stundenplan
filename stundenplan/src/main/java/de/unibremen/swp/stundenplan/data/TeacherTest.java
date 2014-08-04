package de.unibremen.swp.stundenplan.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TeacherTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Testet die Klasse Teacher auf die Grundfunktionen, auf Gleichheit
	 */
	@Test
	public void starttest() {
		Teacher t1 = new Teacher();
		Teacher t2 = new Teacher();
		String newName = "Musterfrau";
		String newAcro = "MFU";
		t1.setName(newName);
		t1.setAcronym(newAcro);
		t1.setHoursPerWeek("2");
		t2.setName(newName);
		t2.setAcronym(newAcro);
		t2.setHoursPerWeek("2");
		assertEquals(t1.getName(), newName);
		assertEquals(t1.getAcronym(), newAcro);
		assertTrue(t1.equals(t2));
		t2.setName("Mustermann");
		t2.setAcronym("MMN");
		t2.setHoursPerWeek("3");
		assertFalse(t1.equals(t2));
	}
	
	/**
	 * Testet alle Exceptions die die Klasse Teacher verursachen kann
	 */
	@Test(expected=IllegalArgumentException.class)
	public void exceptiontest() {
		Teacher t1 = new Teacher();
		t1.setName("");
		t1.setName(null);
		t1.setAcronym("");
		t1.setAcronym(null);
		t1.setHoursPerWeek("");
		t1.setHoursPerWeek(null);
	}
}
