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
package de.unibremen.swp.stundenplan.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import de.unibremen.swp.stundenplan.config.Config;

/**
 * Entspricht einer Zeiteinheit. Eine Zeiteinheit ist einem Tagesplan zugeordnet und hat eine Startzeit. Die Dauer einer
 * solchen Zeiteinheit ist konfigurierbar und per Default auf {@linkplain Config#TIMESLOT_LENGTH_DEFAULT} festgelegt.
 * 
 * @author D. Lüdemann, K. Hölscher
 * @version 0.1
 * 
 */
@Entity
public final class Timeslot implements Serializable {

    /**
     * Die generierte serialVersionUID.
     */
    private static final long serialVersionUID = 4249954963688259056L;

    /**
     * Die Dauer aller Zeiteinheiten in Minuten.
     */
    @Transient
    public static final int LENGTH = Config.getInt(Config.TIMESLOT_LENGTH_STRING,
            Config.TIMESLOT_LENGTH_DEFAULT);

    /**
     * Die eindeutige, von der unterliegenden Persistenzschicht automatisch erzeugte ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Die LehrerInnen, die dieser Zeiteinheit aktuell zugeordnet sind. Diese Sammlung wird erst aus dem Datenbestand
     * geladen, wenn darauf zugegriffen wird.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Teacher> teachers;
    
    /**
     * Die Fächer, die dieser Zeiteinheit aktuell zugeordnet sind. Diese Sammlung wird erst aus dem Datenbestand
     * geladen, wenn darauf zugegriffen wird.
     */
    private Collection<Subject> subjects;

    private Collection<Activity> activities;
    /**
     * Die Startzeit dieses Timeslots. Die Einträge für {@linkplain Calendar#HOUR} und {@linkplain Calendar#MINUTE}
     * müssen entsprechend gesetzt sein.
     */
    @Temporal(TemporalType.TIME)
    private Calendar startTime;

    /**
     * Die Schulklassen, die dieser Zeiteinheit aktuell zugeordnet sind. Diese Sammlung wird erst aus dem Datenbestand
     * geladen, wenn darauf zugegriffen wird.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Schoolclass> schoolclasses;

    /**
     * Erzeugt eine neue Zeiteinheit mit leeren Sammlungen für die zugeordneten LehrerInnen und Schulklassen.
     */
    public Timeslot() {
        teachers = new ArrayList<Teacher>();
        subjects = new ArrayList<Subject>();
        schoolclasses = new ArrayList<Schoolclass>();
    }

    /**
     * Gibt die Sammlung der LehrerInnen zurück, die dieser Zeiteinheit aktuell zugeordnet sind.
     * 
     * @return die Sammlung der LehrerInnen, die dieser Zeiteinheit aktuell zugeordnet sind
     */
    public Collection<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * Gibt die Sammlung der Schulkassen zurück, die dieser Zeiteinheit aktuell zugeordnet sind.
     * 
     * @return die Sammlung der Schulklassen, die dieser Zeiteinheit aktuell zugeordnet sind
     */
    public Collection<Schoolclass> getSchoolclasses() {
        return schoolclasses;
    }
    
    /**
     * Gibt die Sammlung der Fächer zurück, die dieser Zeiteinheit aktuell zugeordnet sind.
     * 
     * @return die Sammlung der Fächer, die dieser Zeiteinheit aktuell zugeordnet sind
     */
    public Collection<Subject> getSubjects() {
        return subjects;
    }

    /**
     * Fügt die gegebene LehrerIn dieser Zeiteinheit hinzu. Es wird hier nicht überprüft, ob eine gleiche LehrerIn
     * bereits zugeordnet wurde. Ein Parameterwert von {@code null} wird ignoriert.
     * 
     * @param teacher
     *            die hinzuzufügende LehrerIn
     */
    public void addTeacher(final Teacher teacher) {
        if (teacher != null) {
            teachers.add(teacher);
        }
    }
    
    public void addActivity(final Activity activity){
    	if(activity != null){
    		activities.add(activity);
    	}
    }

    /**
     * Fügt die gegebene Schulklasse dieser Zeiteinheit hinzu. Es wird hier nicht überprüft, ob eine gleiche Schulklasse
     * bereits zugeordnet wurde. Ein Parameterwert von {@code null} wird ignoriert.
     * 
     * @param schoolclass
     *            die hinzuzufügende Schulklassse
     */
    public void addSchoolclass(final Schoolclass schoolclass) {
        if (schoolclass != null) {
            schoolclasses.add(schoolclass);
        }
    }
    
    /**
     * Fügt das gegebene Fach dieser Zeiteinheit hinzu. 
     * Ein Parameterwert von {@code null} wird ignoriert.
     * 
     * @param teacher
     *            die hinzuzufügende LehrerIn
     */
    public void addSubject(final Subject subject) {
    	boolean exist = false;
        if (subject != null) {
        	for(final Subject subjectList : subjects) {
        		if(subjectList.equals(subject)) exist = true;
        	}
        	if(!exist) subjects.add(subject);
        }
    }

    /**
     * Gibt die dieser Zeiteinheit zugeordneten LehrerInnen als Liste ihrer Kürzel, getrennt durch Komma, zurück.
     * 
     * @return die dieser Zeiteinheit zugeordneten LehrerInnen als kommaseparierte Liste
     */
    public String getTeacherAcronymList() {
        final StringBuilder teacherString = new StringBuilder();

        String separator = "";
        for (final Teacher teacher : teachers) {
            teacherString.append(separator);
            teacherString.append(teacher.getAcronym());
            separator = ", ";
        }
        return teacherString.toString();
    }
    
    /**
     * Gibt die dieser Zeiteinheit zugeordneten Fächer als Liste ihrer Kürzel, getrennt durch Komma, zurück.
     * 
     * @return die dieser Zeiteinheit zugeordneten Fächer als kommaseparierte Liste
     */
    public String getSubjectAcronymList() {
        final StringBuilder subjectString = new StringBuilder();

        String separator = "";
        for (final Subject subject : subjects) {
            subjectString.append(separator);
            subjectString.append(subject.getAcronym());
            separator = ", ";
        }
        return subjectString.toString();
    }

    @Override
    public String toString() {
        return String.format("Timeslot [teachers=%s,schoolclasses=%s", getTeacherAcronymList(), schoolclasses);
    }

    /**
     * Setzt die Startzeit dieser Zeiteineit auf den übergebenen Wert. Die Einträge für {@linkplain Calendar#HOUR} und
     * {@linkplain Calendar#MINUTE} müssen entsprechend gesetzt sein. Der Parameterwert wird nicht auf Plausibilität
     * geprüft - {@code null} wird allerdings ignoriert.
     * 
     * @param pStartTime
     *            Startzeit dieser Zeiteinheit (es sind nur die Einträge für {@linkplain Calendar#HOUR} und
     *            {@linkplain Calendar#MINUTE} relevant
     */
    public void setStartTime(final Calendar pStartTime) {
        if (pStartTime != null) {
            startTime = pStartTime;
        }
    }

    /**
     * Gibt die Startzeit dieser Zeiteinheit im Format <stunde>:<minute> mit evtl. führenden Nullen zurück oder einen
     * leeren String, falls die Startzeit noch nicht initialisiert wurde.
     * 
     * @return die Startzeit dieser Zeiteinheit im Format <stunde>:<minute> mit evtl. führenden Nullen
     */
    public String getTimeDisplay() {
        if (startTime == null) {
            return "";
        }
        final int hour = startTime.get(Calendar.HOUR);
        final int minute = startTime.get(Calendar.MINUTE);
        return String.format("%02d:%02d", hour, minute);
    }
}
