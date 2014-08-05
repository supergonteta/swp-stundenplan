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
import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.data.Timeslot;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.persistence.Data;

/**
 * Diese Klasse verwaltet die F�cher. Es können beispielsweise F�cher hinzugefügt werden oder F�cher ausgegeben werden.
 * 
 * @author Belavic, Oliver
 * 
 */
public final class SchoolclassManager {

    /**
     * Logger dieser Klasse zum Protokollieren von Ereignissen und Informationen.
     */
    private static final Logger LOGGER = Logger.getLogger(SchoolclassManager.class.getName());

    /**
     * Privater Konstruktor, der eine Instanziierung dieser Utility-Klasse verhindert.
     */
    private SchoolclassManager() {
    }

    /**
     * Prüft, ob schon Klassen im Datenbestand vorhanden sind. Falls nicht, werden einige Default-F�cher angelegt.
     * 
     * @throws DatasetException
     *             falls bei der Erzeugung oder der Verwendung des Persistenzobjektes ein Fehler auftritt
     */
    public static void init() throws DatasetException {
        LOGGER.debug("Checking database for schoolclasses");
        final Collection<Schoolclass> schoolclasses = Data.getAllSchoolclasses();
        if (schoolclasses.isEmpty()) {
            LOGGER.debug("Creating default schoolclasses.");
            fillDefaultData();
        }
    }

    /**
     * Füllt den Datenbestand mit drei F�chern und weist dem Zeitslot 1,1 eins dieser Fach zu.
     * 
     * @throws DatasetException
     *             falls ein Fehler beim Aktualisieren des Datenbestandes auftritt
     */
    private static void fillDefaultData() throws DatasetException {
        LOGGER.info("Creating test data in database");
        addSchoolclass("1b");
        addSchoolclass("2c");
        addSchoolclass("4a");
        final Timeslot timeslot = TimetableManager.getTimeslotAt(Weekday.TUESDAY, 1);
        timeslot.addSchoolclass(getSchoolclassByAcronym("4b"));
        Data.updateTimeslot(timeslot);
    }

    /**
     * Legt ein neues Fach mit den gegebenen Werten an und persistiert ihn. Löst eine
     * {@link IllegalArgumentException} aus, falls ein oder mehrere Parameterwerte nicht erlaubt sind.
     * 
     * @param acronym
     *            die Abkürzung für das neue fach
     * @param name
     *            der Name des neuen faches
     * @throws DatasetException
     *             falls ein Fehler beim Aktualisieren des Datenbestandes auftritt
     */
    public static void addSchoolclass(final String name)
            throws DatasetException {
        LOGGER.debug("adding schoolclass");
        final Schoolclass schoolclass = new Schoolclass();
        // setter checks for illegal argument
        schoolclass.setName(name);
        Data.addSchoolclass(schoolclass);
        LOGGER.debug("schoolclass added " + schoolclass);
    }

    /**
     * Gibt eine Sammlung aller LehrerInnen zurück, die von diesem Manager aktuell verwaltet werden.
     * 
     * @return die Sammlung aller LehrerInnen, die aktuell von diesem Manager verwaltet werden
     * @throws DatasetException
     *             falls ein Fehler beim Abfragen des Datenbestandes auftritt
     */
    public static Collection<Schoolclass> getAllSchoolclasses() throws DatasetException {
        LOGGER.debug("List of all schoolclasses");
        return Data.getAllSchoolclasses();
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
    public static Schoolclass getSchoolclassByAcronym(final String acronym) throws DatasetException {
        LOGGER.debug("Schoolclasss for acronym " + acronym);
        return Data.getSchoolclassByAcronym(acronym);
    }

}

