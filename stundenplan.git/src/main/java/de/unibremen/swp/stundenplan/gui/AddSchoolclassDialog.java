package de.unibremen.swp.stundenplan.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;

import de.unibremen.swp.stundenplan.config.Messages;
import de.unibremen.swp.stundenplan.config.Weekday;
import de.unibremen.swp.stundenplan.data.Schoolclass;
import de.unibremen.swp.stundenplan.data.Teacher;
import de.unibremen.swp.stundenplan.data.Timeslot;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.SchoolclassManager;
import de.unibremen.swp.stundenplan.logic.TimetableManager;

/**
 * Der Dialog zum Hinzufügen eines Lehrers.
 * 
 * @author K. Hölscher, D. Lüdemann
 * @version 0.1
 * 
 */
public final class AddSchoolclassDialog extends JDialog implements PropertyChangeListener, ListSelectionListener {

    /**
     * Logger dieser Klasse zum Protokollieren von Ereignissen und Informationen.
     */
    private static final Logger LOGGER = Logger.getLogger(TimetableRenderer.class.getName());

    /**
     * Die eineindeutige Kennzeichnung für Serialisierung.
     */
    private static final long serialVersionUID = 8990701411087003806L;

    /**
     * String-Konstante für die OK-Nachricht. Verhindert unnötige Mehrfachinstanziierung, da die Nachricht mehrfach
     * verwendet wird.
     */
    private static final String MSG_OK = Messages.getString("AddSchoolclassDialog.OK");

    /**
     * Der aktuelle Timeslot.
     */
    private Timeslot timeslot;

    /**
     * Eine JList für die Fächer.
     */
    private final JList<String> schoolclassList;

    /**
     * Das aktuelle SchoolclassListModel.
     */
    private final SchoolclassListModel schoolclassListModel;

    /**
     * Eine JOptionPane.
     */
    private final JOptionPane contentPane;

    /**
     * Der OK-Button als JButton.
     */
    private final JButton okButton;

    /**
     * Der Konstruktor. Setzt die Darstellungseigenschaften des Dialog.
     * 
     * @param owner
     *            Der Frame, von dem der Dialog aus aufgerufen wird.
     */
    public AddSchoolclassDialog(final JFrame owner) {
        super(owner, Messages.getString("AddSchoolclassDialog.AddSchoolclass"), true);
        final JLabel label = new JLabel(Messages.getString("AddSchoolclassDialog.RemoveSchoolclass"));
        schoolclassListModel = new SchoolclassListModel();
        schoolclassList = new JList<>(schoolclassListModel);
        schoolclassList.addListSelectionListener(this);

        final Object[] elements = { label, schoolclassList };
        final String[] buttonLabels = { MSG_OK, Messages.getString("AddSchoolclassDialog.Cancel") };

        contentPane = new JOptionPane(elements, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null,
                buttonLabels, buttonLabels[1]);
        setContentPane(contentPane);
        final JPanel buttonPanel = (JPanel) contentPane.getComponent(1);
        okButton = (JButton) buttonPanel.getComponent(0);
        okButton.setEnabled(false);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                contentPane.setValue(Integer.valueOf(JOptionPane.CLOSED_OPTION));
            }
        });

        contentPane.addPropertyChangeListener(this);
    }

    /**
     * Setzt den Timeslot auf die Zeiteinheit die sich aus dem gegebenen Wochentag und der gegebenen Position ergibt.
     * Fügt dazu alle Lehrer aus dem übergebenen Timeslot in das zugehörige schoolclassListModel ein.
     * 
     * @param weekday
     *            Der Wochentag der Zeiteinheit
     * @param position
     *            die Position der Zeiteinheit
     */
    public void setTimeslot(final Weekday weekday, final int position) {
        try {
            Collection<Schoolclass> schoolclasses;
            timeslot = TimetableManager.getTimeslotAt(weekday, position);
            final Collection<Schoolclass> schoolclasssInSlot = timeslot.getSchoolclasses();
            schoolclasses = SchoolclassManager.getAllSchoolclasses();
            schoolclassListModel.clear();
            for (final Schoolclass schoolclass : schoolclasses) {
                if (!schoolclasssInSlot.contains(schoolclass)) {
                    schoolclassListModel.addSchoolclass(schoolclass);
                }
            }
            pack();

        } catch (DatasetException e) {
            LOGGER.error("Exception while setting timeslot " + timeslot, e);
            ErrorHandler.criticalDatasetError();
        }

    }
    
    /**
     * Setzt den Timeslot auf die Zeiteinheit die sich aus dem gegebenen Wochentag und der gegebenen Position ergibt.
     * Fügt dazu alle Lehrer aus dem übergebenen Timeslot in das zugehörige schoolclassListModel ein.
     * 
     * @param weekday
     *            Der Wochentag der Zeiteinheit
     * @param position
     *            die Position der Zeiteinheit
     */
    public void setTimeslot(final Weekday weekday, final int position, final Object clazz) {
        try {
            Collection<Schoolclass> schoolclasses;
            timeslot = TimetableManager.getTimeslotAt(weekday, position, clazz);
            final Collection<Schoolclass> schoolclasssInSlot = timeslot.getSchoolclasses();
            schoolclasses = SchoolclassManager.getAllSchoolclasses();
            schoolclassListModel.clear();
            for (final Schoolclass schoolclass : schoolclasses) {
                if (!schoolclasssInSlot.contains(schoolclass)) {
                    schoolclassListModel.addSchoolclass(schoolclass);
                }
            }
            pack();

        } catch (DatasetException e) {
            LOGGER.error("Exception while setting timeslot " + timeslot, e);
            ErrorHandler.criticalDatasetError();
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
     */
    @Override
    public void propertyChange(final PropertyChangeEvent event) {
        final String prop = event.getPropertyName();

        if (isVisible() && event.getSource() == contentPane
                && (JOptionPane.VALUE_PROPERTY.equals(prop) || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            final Object value = contentPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }

            contentPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

            if (value.equals(MSG_OK)) {
                updateTimeslot();
            }
            clearAndHide();
        }
    }

    /**
     * Aktualisiert den Timeslot gemäß der Auswahl in der Popup-Liste.
     */
    private void updateTimeslot() {
        final int[] selectedIndices = schoolclassList.getSelectedIndices();
        for (final int index : selectedIndices) {
            final Schoolclass schoolclass = schoolclassListModel.getSchoolclassAt(index);
            timeslot.addSchoolclass(schoolclass);
            try {
                TimetableManager.updateTimeslot(timeslot);
            } catch (DatasetException ex) {
                LOGGER.error("Exception while updating timeslot " + timeslot, ex);
                ErrorHandler.criticalDatasetError();
            }
        }
    }

    /**
     * Löscht die Elemente des SchoolclassListModel und setzt den Dialog auf unsichtbar.
     */
    public void clearAndHide() {
        schoolclassListModel.clear();
        setVisible(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    @Override
    public void valueChanged(final ListSelectionEvent event) {
        if (isVisible()) {
            if (schoolclassList.getSelectedIndices().length > 0) {
                okButton.setEnabled(true);
            } else {
                okButton.setEnabled(false);
            }
        }
    }

}
