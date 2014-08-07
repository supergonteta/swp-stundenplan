package de.unibremen.swp.stundenplan.gui;

	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import de.unibremen.swp.stundenplan.config.Messages;
import de.unibremen.swp.stundenplan.config.Weekday;
import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.data.Timeslot;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.TimetableManager;

public class SchoolclassFrame extends MainFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5648861390882189564L;
	
	/**
     * Der Dialog, der aufpopt, um ein Fach hinzuzufügen.
     */
    private final AddTeacherDialog addTeacherDialog;

    /**
     * Der Konstruktor des Frames. Hier werden die wesentlichen Eigenschaften der Darstellung des Frames definiert. Die
     * JTable wird angelegt und dem Frame hinzugefügt. Es werden die Darstellungseigenschaften der JTable festgelegt.
     * 
     */
    public SchoolclassFrame() {
        super();
        addTeacherDialog = new AddTeacherDialog(this);
        setTitle(super.getTitle() + " von der Klasse");
    }

    public SchoolclassFrame(Schoolclass schoolclass) {
        super();
        addTeacherDialog = new AddTeacherDialog(this);
        setTitle("Stundenplan der Klasse : "+schoolclass.getName());
    }
    
    /**
     * Erzeugt ein Popup zum Hinzufügen eines Lehrers.
     * 
     * @param row
     *            Die Zeile.
     * @param col
     *            Die Spalte.
     * @return das neue Popup-Menu
     */
    protected JPopupMenu createPopup(final int row, final int col) {
        final JPopupMenu popmen = super.createPopup(row, col);
        final JMenuItem menu1 = new JMenuItem(Messages.getString("MainFrame.AddTeacher"));
        final JMenuItem menu2 = new JMenuItem(Messages.getString("MainFrame.RemoveTeacher"));
        menu1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent event) {
                addTeacherDialog.setTimeslot(Weekday.values()[col], row);
                addTeacherDialog.setVisible(true);
            }
        });
        menu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
            	try {
					Timeslot timeslot = TimetableManager.getTimeslotAt(Weekday.values()[col], row);
					if(!timeslot.getTeacherAcronymList().equals("")) timeslot.getTeachers().clear();					
				} catch (DatasetException e) {
					e.printStackTrace();
				}
            }
        });
        popmen.add(menu1);
        popmen.add(menu2);
        popmen.setVisible(true);
        return popmen;
    }
}
