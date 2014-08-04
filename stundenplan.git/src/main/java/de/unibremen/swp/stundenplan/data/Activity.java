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

import org.apache.derby.iapi.services.cache.SizedCacheable;

import de.unibremen.swp.stundenplan.persistence.Data;

/**
 * Realisiert Aktivitäten im Stundenplan.
 * 
 * @author Oliver, Roman
 */
public class Activity implements Serializable {

	/**
	 * Die eineindeutige ID fÃ¼r Serialisierung.
	 */
	private static final long serialVersionUID = 2597139574206115533L;

	/**
	 * Der Name diese Faches.
	 */
	@Column(nullable = false, length = Data.MAX_NORMAL_STRING_LEN)
	private String name;

	/**
	 * Das Kürzel dieses Faches. Ein KÃ¼rzel muss systemweit eindeutig sein.
	 */
	@Id
	@Column(length = Data.MAX_ACRONYM_LEN)
	private String acronym;

	/**
	 * Liste der Lehrer, die dieser Aktivität angehören.
	 */
	private ArrayList<Teacher> teachers;

	/**
	 * Liste der Klassen, die dieser Aktivität angehören.
	 */
	private ArrayList<Schoolclass> classes;

	/**
	 * Startzeit der Aktivität. Die Einträge für {@linkplain Calendar#HOUR} und
	 * {@linkplain Calendar#MINUTE} müssen entsprechend gesetzt sein.
	 */
	@Temporal(TemporalType.TIME)
	private Calendar activityStart;

	/**
	 * Endzeit der Aktivität. Die Einträge für {@linkplain Calendar#HOUR} und
	 * {@linkplain Calendar#MINUTE} müssen entsprechend gesetzt sein.
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
	 * @exception Falls
	 *                @param startStunde kleiner als 0 oder größer als 23 ist.
	 *                Falls @param startMinute kleiner als 0 oder größer als 59
	 *                ist. Falls die Startzeit hinter der bereits vorhandenen
	 *                Endzeit liegt.
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
	 * @exception Falls
	 *                @param endStunde kleiner als 0 oder größer als 23 ist.
	 *                Falls @param endMinute kleiner als 0 oder größer als 59
	 *                ist. Falls die Endzeit vor der bereits vorhandenen
	 *                Startzeit liegt.
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

	/**
	 * Gibt die Liste mit den beteiligten Lehrern zurück.
	 * 
	 * @return Eine Liste mit den beteiligten Lehrern.
	 */
	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	/**
	 * Gibt die Liste mit den beteiligten Klassen zurück.
	 * 
	 * @return Eine Liste mit den beteiligten Klassen.
	 */
	public ArrayList<Schoolclass> getClasses() {
		return classes;
	}

	/**
	 * Fügt einen Lehrer zu der Liste der beteiligten Lehrer hinzu, falls dieser
	 * nicht {@code null} ist.
	 * 
	 * @param t
	 *            Der Lehrer, der hinzugefügt werden soll.
	 */
	public void addTeacher(final Teacher t) {
		if (t != null) {
			this.teachers.add(t);
		}
	}

	/**
	 * Fügt eine Klasse zu der Liste der beteiligten Klassen hinzu, falls dieser
	 * nicht {@code null} ist.
	 * 
	 * @param s
	 *            Die Klasse, die hinzugefügt werden soll.
	 */
	public void addClass(final Schoolclass s) {
		if (s != null) {
			this.classes.add(s);
		}
	}

	/**
	 * Gibt den Namen dieses Faches zurÃ¼ck.
	 * 
	 * @return den Namen dieses Faches
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen dieses faches auf den gegebenen Wert. Ein Parameterwert
	 * von {@code null} wird ignoriert.
	 * 
	 * @param pName
	 *            der neue Name dieses Faches (falls nicht {@code null}
	 */
	public void setName(final String pName) {
		name = pName;
	}

	/**
	 * Gibt das Kürzel dieses Faches zurÃ¼ck.
	 * 
	 * @return das Kürzel dieses LehrerIn
	 */
	public String getAcronym() {
		return acronym;
	}

	/**
	 * Setzt das KÃ¼rzel dieses Faches auf das Ã¼bergebene KÃ¼rzel. Falls das
	 * KÃ¼rzel lÃ¤nger als {@linkplain Data#MAX_ACRONYM_LEN} Zeichen ist, wird
	 * es entsprechend gekÃ¼rzt. FÃ¼hrende und folgende Leerzeichen werden
	 * entfernt. LÃ¶st eine {@link IllegalArgumentException} aus, falls das
	 * KÃ¼rzel leer ist.
	 * 
	 * Die systemweite Eindeutigkeit des KÃ¼rzels wird hier NICHT geprÃ¼ft!
	 * 
	 * @param pAcronym
	 *            das neue KÃ¼rzel dieser LehrerIn
	 */
	public void setAcronym(final String pAcronym) {
		if (pAcronym == null || pAcronym.trim().isEmpty()) {
			throw new IllegalArgumentException("KÃ¼rzel der LehrerIn ist leer");
		}
		acronym = pAcronym.trim().substring(0,
				Math.min(Data.MAX_ACRONYM_LEN, pAcronym.length()));
	}
}
