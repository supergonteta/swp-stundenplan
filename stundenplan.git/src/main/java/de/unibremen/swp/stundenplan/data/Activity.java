package de.unibremen.swp.stundenplan.data;

import java.util.Calendar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import de.unibremen.swp.stundenplan.persistence.Data;

/**
 * Realisiert Aktivitäten im Stundenplan.
 * 
 * @author Roman
 */
public class Activity extends Subject {

	/**
	 * Liste der Lehrer, die dieser Aktivität angehören.
	 */
	private ArrayList<Teacher> teachers;

	/**
	 * Liste der Klassen, die dieser Aktivität angehören.
	 */
	private ArrayList<Schoolclass> classes;

	/**
	 * Startzeit der Aktivität. Die Einträge für {@linkplain Calendar#HOUR} und {@linkplain Calendar#MINUTE}
     * müssen entsprechend gesetzt sein.
	 */
	@Temporal(TemporalType.TIME)
	private Calendar activityStart;

	/**
	 * Endzeit der Aktivität. Die Einträge für {@linkplain Calendar#HOUR} und {@linkplain Calendar#MINUTE}
     * müssen entsprechend gesetzt sein.
	 */
	private Calendar activityEnd;

	/**
	 * Setzt die Startzeit für diese Aktivität. Die Eingabe von @param
	 * startStunde muss zwischen 0 und 23 liegen. Die Eingabe von @param
	 * startMinute muss zwischen 0 und 59 liegen.
	 * 
	 * @param startStunde
	 *            Die Stunde der Uhrzeit, an der die Aktivität beginnen soll.
	 * @param startMinute
	 *            Die Minute der Uhrzeit, an der die Aktivität beginnen soll.
	 * @exception Falls @param startStunde kleiner als 0 oder größer als 23 ist.
	 *            Falls @param startMinute kleiner als 0 oder größer als 59
	 *                ist.
	 *            Falls die Startzeit hinter der bereits vorhandenen Endzeit liegt.
	 * 
	 * @author Roman
	 */
	public void setStartTime(final int startStunde, final int startMinute) {
		if (startStunde < 0 || startStunde > 23 || startMinute < 0
				|| startMinute > 59) {
			throw new IllegalArgumentException(
					"Die Startzeit entspricht nicht dem geforderten Format.");
		}
		if (activityEnd != null) {
			if (activityEnd.get(Calendar.HOUR_OF_DAY) > startStunde) {
				throw new IllegalArgumentException(
						"Die Startzeit muss vor der Endzeit liegen.");
			}
			if (activityEnd.get(Calendar.HOUR_OF_DAY) == startStunde) {
				if (activityEnd.get(Calendar.MINUTE) > startMinute) {
					throw new IllegalArgumentException(
							"Die Endzeit muss nach der Startzeit liegen.");
				}
			}
		}
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, startStunde);
		c.set(Calendar.MINUTE, startMinute);

		activityStart = c;
	}

	/**
	 * Setzt die Endzeit für diese Aktivität. Die Eingabe von @param endStunde
	 * muss zwischen 0 und 23 liegen. Die Eingabe von @param endMinute muss
	 * zwischen 0 und 59 liegen.
	 * 
	 * @param endStunde
	 *            Die Stunde der Uhrzeit, an der die Aktivität beginnen soll.
	 * @param endMinute
	 *            Die Minute der Uhrzeit, an der die Aktivität beginnen soll.
	 * @exception Falls @param endStunde kleiner als 0 oder größer als 23 ist.
	 *            Falls @param endMinute kleiner als 0 oder größer als 59
	 *                ist.
	 *            Falls die Endzeit vor der bereits vorhandenen Startzeit liegt.
	 * 
	 * @author Roman
	 */
	public void setEndTime(final int endStunde, final int endMinute) {
		if (endStunde < 0 || endStunde > 23 || endMinute < 0 || endMinute > 59) {
			throw new IllegalArgumentException(
					"Die Endzeit entspricht nicht dem geforderten Format.");
		}
		if (activityStart != null) {
			if (activityStart.get(Calendar.HOUR_OF_DAY) < endStunde) {
				throw new IllegalArgumentException(
						"Die Endzeit muss nach der Startzeit liegen.");
			}
			if (activityStart.get(Calendar.HOUR_OF_DAY) == endStunde) {
				if (activityStart.get(Calendar.MINUTE) < endMinute) {
					throw new IllegalArgumentException(
							"Die Endzeit muss nach der Startzeit liegen.");
				}
			}
		}
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, endStunde);
		c.set(Calendar.MINUTE, endMinute);

		activityEnd = c;
	}
}
