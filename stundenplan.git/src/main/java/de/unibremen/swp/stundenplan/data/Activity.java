package de.unibremen.swp.stundenplan.data;

import java.util.Calendar;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Id;

import de.unibremen.swp.stundenplan.persistence.Data;

/**
 * Realisiert Aktivitäten im Stundenplan.
 * 
 * @author Roman
 */
public class Activity extends Subject {

	private ArrayList<Teacher> teachers;
	
	private ArrayList<Schoolclass> classes;
	
	private Calendar activityStart;
	
	private Calendar activityEnd;
	
	 /**
     *
     */
    public void setStartTime(final int startStunde, final int startMinute) {
        if (startStunde < 0 || startStunde > 23 || startMinute < 0 || startMinute > 59){
        	throw new IllegalArgumentException("Die Startzeit entspricht nicht dem geforderten Format.");
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, startStunde);
        c.set(Calendar.MINUTE, startMinute);
       
        activityStart = c;
        }
    }
