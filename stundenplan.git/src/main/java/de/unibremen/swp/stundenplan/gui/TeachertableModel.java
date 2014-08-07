package de.unibremen.swp.stundenplan.gui;

import de.unibremen.swp.stundenplan.config.Weekday;
import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.TimetableManager;

public class TeachertableModel extends TimetableModel {
	
	private Teacher teacher;
	
	public TeachertableModel(final Teacher pTeacher){
		super();
		teacher = pTeacher;
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
                return TimetableManager.getTimeslotAt(Weekday.values()[col - 1], row, teacher);
            } catch (DatasetException e) {
                /*
                 * Exception wurde schon protokolliert.
                 */
                return null;
            }
        }
    }

}
