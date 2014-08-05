package de.unibremen.swp.stundenplan.logic;

import java.util.Collection;

import org.apache.log4j.Logger;

import de.unibremen.swp.stundenplan.*;
import de.unibremen.swp.stundenplan.config.Weekday;
import de.unibremen.swp.stundenplan.data.Activity;
import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.data.Timeslot;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.persistence.Data;

public class ActivityManager {

    /**
     * Logger dieser Klasse zum Protokollieren von Ereignissen und Informationen.
     */
    private static final Logger LOGGER = Logger.getLogger(ActivityManager.class.getName());

    /**
     * Privater Konstruktor, der eine Instanziierung dieser Utility-Klasse verhindert.
     */
    private ActivityManager() {
    }

    /**
     * Prüft, ob schon LehrerInnen im Datenbestand vorhanden sind. Falls nicht, werden einige Default-Lehrer angelegt.
     * 
     * @throws DatasetException
     *             falls bei der Erzeugung oder der Verwendung des Persistenzobjektes ein Fehler auftritt
     */
    public static void init() throws DatasetException {
        LOGGER.debug("Checking database for activities");
        final Collection<Activity> teachers = Data.getAllActivities();
        if (teachers.isEmpty()) {
            LOGGER.debug("Creating default teachers.");
            fillDefaultData();
        }
    }

    /**
     * Füllt den Datenbestand mit drei Lehrern und weist dem Zeitslot 1,1 einen dieser Lehrer zu.
     * 
     * @throws DatasetException
     *             falls ein Fehler beim Aktualisieren des Datenbestandes auftritt
     */
    private static void fillDefaultData() throws DatasetException {
        LOGGER.info("Creating test data in database");
        addActivity("SAU", "Saufen", 8, 15, 12, 45);
        addActivity("RUG", "Rumgangstern", 12, 45, 13, 15);
        addActivity("OV�", "Omas verm�geln", 15, 0, 20, 0);
        final Timeslot timeslot = TimetableManager.getTimeslotAt(Weekday.TUESDAY, 1);
        timeslot.addActivity(getActivityByAcronym("OV�"));
        Data.updateTimeslot(timeslot);
    }

    /**
     * Legt einen neue LehrerIn mit den gegebenen Werten an und persistiert ihn. Löst eine
     * {@link IllegalArgumentException} aus, falls ein oder mehrere Parameterwerte nicht erlaubt sind.
     * 
     * @param acronym
     *            die Abkürzung für die neue LehrerIn
     * @param name
     *            der Name der neuen LehrerIn
     * @param hoursPerWeek
     *            die Anzahl an Stunden, die diese LehrerIn gibt
     * @throws DatasetException
     *             falls ein Fehler beim Aktualisieren des Datenbestandes auftritt
     */
    public static void addActivity(final String acronym, final String name, final int startStunde,
    		final int startMinute, final int endStunde, final int endMinute)
            throws DatasetException {
        LOGGER.debug("adding activity");
        final Activity activity = new Activity();
        // setter checks for illegal argument
        activity.setAcronym(acronym);
        // setter checks for illegal argument
        activity.setName(name);
        // setter checks for illegal argument
        activity.setStartTime(startStunde, startMinute);
        activity.setEndTime(endStunde, endMinute);
        Data.addActivity(activity);
        LOGGER.debug("teacher added " + activity);
    }

    /**
     * Gibt eine Sammlung aller LehrerInnen zurück, die von diesem Manager aktuell verwaltet werden.
     * 
     * @return die Sammlung aller LehrerInnen, die aktuell von diesem Manager verwaltet werden
     * @throws DatasetException
     *             falls ein Fehler beim Abfragen des Datenbestandes auftritt
     */
    public static Collection<Teacher> getAllTeachers() throws DatasetException {
        LOGGER.debug("List of all teachers");
        return Data.getAllTeachers();
    }

    /**
     * Gibt die LehrerIn mit dem gegebenen Kürzel zurück.
     * 
     * @param acronym
     *            das Kürzel der gesuchten LehrerIn
     * @return die LehrerIn mit dem gesuchten Kürzel oder {@code null} falls keine LehrerIn mit dem gegebenen Kürzel
     *         existiert
     * @throws DatasetException
     *             falls ein Fehler beim Zugriff auf den Datenbestand auftritt
     */
    public static Teacher getTeacherByAcronym(final String acronym) throws DatasetException {
        LOGGER.debug("Teachers for acronym " + acronym);
        return Data.getTeacherByAcronym(acronym);
    }
    
    public static Activity getActivityByAcronym(final String acronym) throws DatasetException {
        LOGGER.debug("Activity for acronym " + acronym);
        return Data.getActivityByAcronym(acronym);
    }
}
