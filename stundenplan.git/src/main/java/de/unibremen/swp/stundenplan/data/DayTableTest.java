package de.unibremen.swp.stundenplan.data;

import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import de.unibremen.swp.stundenplan.config.Config;


/**
 * Blackboxtest für die Klasse DayTable.
 * 
 * @author Oliver Belavic
 */
@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(Config.class)
public class DayTableTest {

	/**
	 * Vor den Tests wird die Config gestartet.
	 */
	@Before
	public void setUp() {
		PowerMockito.mockStatic(Config.class);
		when(Config.getString("persistence_name", "stundenplan")).thenReturn(
				"stundenplan");
	}

	/**
	 * Fügt null in die addTimeslot Methode ein, was eine Illegal Argument Exception auslösen soll.
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAddTimeslotNull() {
		DayTable dt = new DayTable();
		dt.addTimeslot(null);
	}
	
	/**
	 * Ein normaler timeslot wird in die addTimeslot Methode eingefügt.
	 */
	@Test
	public void testAddTimeslotNormal() {
		DayTable dt = new DayTable();
		Timeslot ts = new Timeslot();
		Teacher t = new Teacher();
		t.setName("Oliver");
		Schoolclass sc = new Schoolclass();
		sc.setName("Kulturmeister");
		ts.addTeacher(t);
		ts.addSchoolclass(sc);
		dt.addTimeslot(ts);
		System.out.println(dt.getTimeslot(0));
	}
}
