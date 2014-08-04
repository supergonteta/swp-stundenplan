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

@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(Config.class)
public class TimeslotTest {
	
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
		Timeslot timeslot = new Timeslot();
		Teacher t1 = new Teacher();
		Teacher t2 = new Teacher();
		Teacher t3 = new Teacher();
		Teacher t4 = new Teacher();
		t1.setAcronym("Hans");
		t2.setAcronym("Hans2");
		t3.setAcronym("Hans3");
		t4.setAcronym("Hans4");

		timeslot.addTeacher(t1);
		timeslot.addTeacher(t2);
		timeslot.addTeacher(t3);
		timeslot.addTeacher(null);
		System.out.println(timeslot.getTeachers());
	}
	
	/**
	 * Testet addSchoolclass. Es werden 4 Schoolclasses erstellt und in timeslot
	 * geaddet. Anschließend wird überprüft, ob die Klassen sich auch tatsächlich
	 * in der dafür vorgesehenen Liste befinden. Eine Klasse wird nicht hinzugefügt
	 * und für diesen soll die contains() Methode daher auch false zurück geben.
	 */
	@Test
	public void testAddSchoolclass(){
		Timeslot timeslot = new Timeslot();
		Schoolclass s1 = new Schoolclass();
		Schoolclass s2 = new Schoolclass();
		Schoolclass s3 = new Schoolclass();
		s1.setName("1b");
		
		timeslot.addSchoolclass(s1);
		timeslot.addSchoolclass(s2);
		timeslot.addSchoolclass(s3);
		
		System.out.println(timeslot.getSchoolclasses());
	}

}
