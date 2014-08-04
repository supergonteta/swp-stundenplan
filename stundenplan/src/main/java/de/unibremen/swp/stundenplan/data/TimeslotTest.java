package de.unibremen.swp.stundenplan.data;

import static org.powermock.api.mockito.PowerMockito.when;
import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import de.unibremen.swp.stundenplan.config.Config;

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(Config.class)
public class TimeslotTest {
	
	/**
	 * Ein Objekt vom Typ Timeslot, dessen Methoden wir nun testen.
	 */
	private Timeslot timeslot = new Timeslot();
	
	/**
	 * Vor den Tests wird die Config gestartet, da es vorkommen kann,
	 * dass man mehrmals starten muss, damit die Config erfolgreich ausgeführt
	 * wird.
	 */
	@Before
	public void setUp() {
		PowerMockito.mockStatic(Config.class);
		when(Config.getString("persistence_name", "stundenplan")).thenReturn(
				"stundenplan");
	}

	/**
	 * Testet addTeacher. Es werden 4 Teacher erstellt und in timeslot
	 * geaddet. Anschließend wird überprüft, ob die Lehrer sich auch tatsächlich
	 * in der dafür vorgesehenen Liste befinden. Ein Lehrer wird nicht hinzugefügt
	 * und für diesen soll die contains() Methode daher auch false zurück geben.
	 */
	@Test
	public void testAddTeacher() {
		Teacher t1 = new Teacher();
		Teacher t2 = new Teacher();
		Teacher t3 = new Teacher();
		Teacher t4 = new Teacher();
		
		timeslot.addTeacher(t1);
		timeslot.addTeacher(t2);
		timeslot.addTeacher(t3);
		timeslot.addTeacher(null);
		
		assertTrue(timeslot.getTeachers().contains(t1));
		assertTrue(timeslot.getTeachers().contains(t2));
		assertTrue(timeslot.getTeachers().contains(t3));
		assertFalse(timeslot.getTeachers().contains(t4));
	}
	
	/**
	 * Testet addSchoolclass. Es werden 4 Schoolclasses erstellt und in timeslot
	 * geaddet. Anschließend wird überprüft, ob die Klassen sich auch tatsächlich
	 * in der dafür vorgesehenen Liste befinden. Eine Klasse wird nicht hinzugefügt
	 * und für diesen soll die contains() Methode daher auch false zurück geben.
	 */
	@Test
	public void testAddSchoolclass(){
		Schoolclass s1 = new Schoolclass();
		Schoolclass s2 = new Schoolclass();
		Schoolclass s3 = new Schoolclass();
		Schoolclass s4 = new Schoolclass();
		
		timeslot.addSchoolclass(s1);
		timeslot.addSchoolclass(s2);
		timeslot.addSchoolclass(s3);
		
		assertTrue(timeslot.getTeachers().contains(s1));
		assertTrue(timeslot.getTeachers().contains(s2));
		assertTrue(timeslot.getTeachers().contains(s3));
		assertFalse(timeslot.getTeachers().contains(s4));	
	}
	
	/**
	 * Testet setStartTime und getTimeDisplay. Erzeugt einen Calendar und fügt diesem
	 * für HOUR 13 und für Minute 45 hinzu. Dieser wird im Timeslot gespeichert.
	 * Anschließend wird überprüft, ob getTimeDisplay auch den richtigen String ausgibt.
	 */
	@Test
	public void testTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 13);
		calendar.set(Calendar.MINUTE, 45);
		
		timeslot.setStartTime(calendar);
		String zeit = "13:45";	
		assertEquals(timeslot.getTimeDisplay(), zeit);
	}

}
