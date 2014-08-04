package de.unibremen.swp.stundenplan.persistence;

import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import de.unibremen.swp.stundenplan.config.Config;
import de.unibremen.swp.stundenplan.data.DayTable;
import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.data.Timeslot;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(Config.class)
public class DataTest {

		
	/**
	 * Vor den Tests wird die Config gestartet.
	 */
	@Before
	public void setUp() {
		PowerMockito.mockStatic(Config.class);
		when(Config.getString("persistence_name", "stundenplan")).thenReturn(
				"stundenplan");
	}
		
	@Test(expected = IllegalArgumentException.class)
	public void addTeacherNull() {
		try {
			Data.addTeacher(null);
		} catch (DatasetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addTeacherCorrect() {
		try {
			Data.addTeacher(new Teacher());
		} catch (DatasetException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addDayTableNull() {
		try {
			Data.addDayTable(null);
		} catch (DatasetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void addDayTableCorrect() {
		try {
			Data.addDayTable(new DayTable());
		} catch (DatasetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateTimeslotCorrect() {
		try {
			Data.updateTimeslot(new Timeslot());
		} catch (DatasetException e) {
			e.printStackTrace();
		}
	}
}
