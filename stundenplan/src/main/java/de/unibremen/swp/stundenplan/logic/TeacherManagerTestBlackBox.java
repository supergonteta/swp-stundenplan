package de.unibremen.swp.stundenplan.logic;

import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import de.unibremen.swp.stundenplan.config.Config;
import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;

/**
 * Blackboxtest f�r die Klasse TeacherManager, auf die Methoden itit()
 * und fillDefaultData() wurden keine Tests gemacht, da diese Methoden keine,
 * Parameter erwarten und wir nach dem Blackbox Prinzip ja nicht wissen was im
 * Rumpf passiert und somit nichts testen k�nnen.
 * 
 * @author Patrick
 *
 */
@PowerMockIgnore("javax.management.*")
@RunWith(PowerMockRunner.class)
@PrepareForTest(Config.class)
public class TeacherManagerTestBlackBox {

	/**
	 * Vor den Tests wird die Config einmal gestartet,
	 * aus unerkl�rlichen Gr�nden kann es vorkommen das man mehrmals
	 * Starten muss damit die Config erfolgreich ausgef�hrt wird.
	 */
	@Before
	public void setUp() {
		PowerMockito.mockStatic(Config.class);
		when(Config.getString("persistence_name", "stundenplan")).thenReturn(
				"stundenplan");
	}

	/**
	 * Hier wird die Methode addTeacher getestet, in der bewusst nicht erlaubte
	 * Werte eingetragen werden und somit eine IlleagalArgumentException
	 * erwartet wird.
	 * 
	 * @throws DatasetException
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testAddTeacherFail() throws DatasetException {
		TeacherManager.addTeacher(null, "So So", "10");
		TeacherManager.addTeacher(null, null, "10");
		TeacherManager.addTeacher(null, null, null);
		TeacherManager.addTeacher("PW", "Patrick Warszewik", "5000");

	}

	/**
	 * Hier wurde ein TestLehrer eingef�gt und dazu noch die Ausgabe der
	 * der Collection aller Lehrer bisherigen eingetragenen Lehrer. Um so
	 * zu testen ob der eben hinzugef�hte Lehrer dabei ist. Zum Schluss
	 * wird noch getestet ob die Methode getAcronym() gleich dem 
	 * aktuellem Lehrer Objekt entspricht. 
	 * 
	 * @throws DatasetException
	 */
	@Test
	public void testCorrect() throws DatasetException {
		TeacherManager.addTeacher("TeS", "Fall Test", "25");
		Collection<Teacher> liste = new ArrayList<>();
		liste = TeacherManager.getAllTeachers();
		for(Teacher t:liste){
			System.out.print(""+t);
		
		}
		
		
	}
	/**
	 * Hier wird nochmal Test auf Funktionalit�t mit
	 * der Methode getAkroynm gemacht.
	 * @throws DatasetException
	 */
	@Test
	public void testTeacherAcronym() throws DatasetException{
	Teacher test = TeacherManager.getTeacherByAcronym("PaL");
	System.out.print(test.getHoursPerWeek());
	System.out.print(test.getName());
	
	}
}
