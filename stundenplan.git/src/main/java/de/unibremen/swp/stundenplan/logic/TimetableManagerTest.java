package de.unibremen.swp.stundenplan.logic;

import org.junit.Test;

import de.unibremen.swp.stundenplan.config.Weekday;
import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.data.Timeslot;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;

/**
 * Hier wird mittels Blackbox die Klasse TimetableManager getestet.
 * 
 * *UPDATE* : Test kann nicht erfolgreich gestartet werden, da ich das
 * initialisieren nicht richtig hinkriege.
 * 
 * Daher schlagen alle Tests fehl. Somit ist diese Testklasse voerst
 * unbrauchbar.
 * 
 * @author Fabian
 * 
 */
public class TimetableManagerTest {

	/**
	 * 
	 */
	@Test
	public void testGetTimeSlotAt() {
		try {
			Timeslot t1 = TimetableManager.getTimeslotAt(Weekday.TUESDAY, 1);

			if (t1 == null) {
				System.out.println("Dieses Feld ist leer");
			}
		} catch (DatasetException e) {
			System.out.println("getTimeslotAt fehlgeschlagen");
		}

	}

	/**
	 * 
	 */
	@Test
	public void testGetTimeframeDisplay() {
		System.out.println(TimetableManager.getTimeframeDisplay(0));
		System.out.println(TimetableManager.getTimeframeDisplay(1));
		System.out.println(TimetableManager.getTimeframeDisplay(2));
		System.out.println(TimetableManager.getTimeframeDisplay(3));
		System.out.println(TimetableManager.getTimeframeDisplay(4));

	}

	/**
	 * @throws DatasetException
	 * 
	 */
	@Test
	public void testUpdateTimeSlot() throws DatasetException {
		Timeslot ts3 = new Timeslot();
		Schoolclass sc1 = new Schoolclass();
		sc1.setName("1a");
		ts3.addSchoolclass(sc1);
		Teacher teacher1 = new Teacher();
		teacher1.setName("Frank Bob");
		teacher1.setAcronym("FrB");
		teacher1.setHoursPerWeek("30");
		ts3.addTeacher(teacher1);

		System.out.println(ts3.getTeacherAcronymList());

		teacher1.setAcronym("FBr");
		TimetableManager.updateTimeslot(ts3);

		System.out.println(ts3.getTeacherAcronymList());

	}
}
