package de.unibremen.swp.stundenplan.gui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import de.unibremen.swp.stundenplan.data.*;

public class TeacherListModelTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Testet die Grundfunktionen der Klasse TeacherListModel
	 */
	@Test
	public void functiontest() {
		Teacher t1 = new Teacher();
		Teacher t2 = new Teacher();
		Teacher t3 = new Teacher();
		Teacher t4 = new Teacher();
		TeacherListModel tlm = new TeacherListModel();
		t1.setName("Leonardo Da Vinci");
		t1.setAcronym("DVI");
		t1.setHoursPerWeek("10");
		t2.setName("Michelangelo");
		t2.setAcronym("MLO");
		t2.setHoursPerWeek("15");
		t3.setName("Donatello");
		t3.setAcronym("DLO");
		t3.setHoursPerWeek("15");
		t4.setName("Raphael");
		t4.setAcronym("RAP");
		t4.setHoursPerWeek("18");
		tlm.addTeacher(t1);
		tlm.addTeacher(t2);
		tlm.addTeacher(t3);
		tlm.addTeacher(t4);
		assertEquals(tlm.getTeacherAt(0), t1);
		assertEquals(tlm.getTeacherAt(1), t2);
		assertEquals(tlm.getTeacherAt(2), t3);
		assertEquals(tlm.getTeacherAt(3), t4);
		tlm.clear();
		assertEquals(tlm.size(), 0);
	}

}
