/*
 * Copyright 2014 AG Softwaretechnik, University of Bremen, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package de.unibremen.swp.stundenplan.logic;

import java.util.Collection;

import org.apache.log4j.Logger;

import de.unibremen.swp.stundenplan.config.Weekday;
import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.data.Timeslot;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.persistence.Data;

/**
 * Diese Klasse verwaltet die Lehrer. Es können beispielsweise Lehrer hinzugefügt werden oder Lehrer ausgegeben werden.
 * 
 * @author D. Lüdemann, K. Hölscher
 * @version 0.1
 * 
 */
public final class TeacherManager {

    /**
     * Logger dieser Klasse zum Protokollieren von Ereignissen und Informationen.
     */
    private static final Logger LOGGER = Logger.getLogger(TeacherManager.class.getName());

    /**
     * Privater Konstruktor, der eine Instanziierung dieser Utility-Klasse verhindert.
     */
    private TeacherManager() {
    }

    /**
     * Prüft, ob schon LehrerInnen im Datenbestand vorhanden sind. Falls nicht, werden einige Default-Lehrer angelegt.
     * 
     * @throws DatasetException
     *             falls bei der Erzeugung oder der Verwendung des Persistenzobjektes ein Fehler auftritt
     */
    public static void init() throws DatasetException {
        LOGGER.debug("Checking database for teachers");
        final Collection<Teacher> teachers = Data.getAllTeachers();
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
        addTeacher("DrL", "Dr. Hannibal Lecter", "35");
        addTeacher("KKK", "Karsten Klaus Kinski", "2");
        addTeacher("HdF", "Halto de Fressi", "40");
        final Timeslot timeslot = TimetableManager.getTimeslotAt(Weekday.TUESDAY, 1);
        timeslot.addTeacher(getTeacherByAcronym("HdF"));
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
    public static void addTeacher(final String acronym, final String name, final String hoursPerWeek)
            throws DatasetException {
        LOGGER.debug("adding teacher");
        final Teacher teacher = new Teacher();
        // setter checks for illegal argument
        teacher.setAcronym(acronym);
        // setter checks for illegal argument
        teacher.setName(name);
        // setter checks for illegal argument
        teacher.setHoursPerWeek(hoursPerWeek);
        Data.addTeacher(teacher);
        TimetableManager.init(teacher);
        LOGGER.debug("teacher added " + teacher);
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

}
