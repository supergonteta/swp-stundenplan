package de.unibremen.swp.stundenplan.gui;

import de.unibremen.swp.stundenplan.config.Weekday;
import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.TimetableManager;

public class SchoolclasstableModel extends TimetableModel {
    private Schoolclass schoolclass;
    
    public SchoolclasstableModel(final Schoolclass pSchoolclassModel){
    	schoolclass = pSchoolclassModel;
    	
    }
	
	/*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getValueAt(int, int) Hier darauf geachtet, dass wir ganz links die Uhrzeit der
     * Tabelle anzeigen. Die weiteren Spalten greifen auf die Timeslots des TimetableManagers zurück.
     */
    @Override
    public Object getValueAt(final int row, final int col) {
        if (col == 0) {
            return TimetableManager.getTimeframeDisplay(row);
        } else {
            try {
                return TimetableManager.getTimeslotAt(Weekday.values()[col - 1], row, schoolclass);
            } catch (DatasetException e) {
                /*
                 * Exception wurde schon protokolliert.
                 */
                return null;
            }
        }
    }

}


