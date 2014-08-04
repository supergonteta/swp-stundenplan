package de.unibremen.swp.stundenplan.data;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Id;

import de.unibremen.swp.stundenplan.persistence.Data;

/**
 * Repräsentiert ein Fach 
 * 
 * @author Belavic, Oliver
 *
 */

public class Subject extends Activity {
    
	/**
	 * Die dem Fach zugeordnete Klasse.
	 */
	private Schoolclass klasse;

	/**
	 * Überschreibt die Methode addClass aus Activity. Da jedem Fach nur eine Klasse
	 * hinzugefügt werden soll, fügt addClass die Klasse aus dem Parameter nun nicht mehr der Liste der beteiligten
	 * Klassen, sondern verwendet das interne Attribut 'klasse'.
	 * 
	 * @param s
	 * 	 	Die Schulklasse, die hinzugefügt werden soll.
	 */
	@Override
	public void addClass(final Schoolclass s){
		if(s != null){
			klasse = s;
		}
	}
}
