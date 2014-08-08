package de.unibremen.swp.stundenplan.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.List;

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
import de.unibremen.swp.stundenplan.data.Subject;
import de.unibremen.swp.stundenplan.data.Timeslot;
import de.unibremen.swp.stundenplan.exceptions.DatasetException;
import de.unibremen.swp.stundenplan.logic.SubjectManager;
import de.unibremen.swp.stundenplan.logic.TimetableManager;

/**
 * Der Dialog zum Hinzufügen eines Lehrers.
 * 
 * @author K. Hölscher, D. Lüdemann
 * @version 0.1
 * 
 */
public final class EditSubjectDialog extends JDialog implements
		PropertyChangeListener, ListSelectionListener {

	/**
	 * Logger dieser Klasse zum Protokollieren von Ereignissen und
	 * Informationen.
	 */
	private static final Logger LOGGER = Logger
			.getLogger(TimetableRenderer.class.getName());

	/**
	 * mlm Die eineindeutige Kennzeichnung für Serialisierung.
	 */
	private static final long serialVersionUID = 8990701411087003816L;

	/**
	 * String-Konstante für die OK-Nachricht. Verhindert unnötige
	 * Mehrfachinstanziierung, da die Nachricht mehrfach verwendet wird.
	 */
	private static final String MSG_OK = Messages
			.getString("EditSubjectDialog.OK");

	/**
	 * Der aktuelle Timeslot.
	 */
	private Timeslot timeslot;

	/**
	 * Eine JList für die Fächer.
	 */
	private final JList<String> subjectList;

	/**
	 * Das aktuelle SubjectListModel.
	 */
	private final SubjectListModel subjectListModel;

	/**
	 * Eine JOptionPane.
	 */
	private final JOptionPane contentPane;

	public static JFrame e;
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
	public EditSubjectDialog(final JFrame owner) {
		super(owner, Messages.getString("EditSubjectDialog.EditSubject"), true);
		final JLabel label = new JLabel(
				Messages.getString("EditSubjectDialog.EditSubject"));
		subjectListModel = new SubjectListModel();
		subjectList = new JList<>(subjectListModel);
		subjectList.addListSelectionListener(this);

		final Object[] elements = { label, subjectList };
		final String[] buttonLabels = { MSG_OK,
				Messages.getString("EditSubjectDialog.Cancel") };

		contentPane = new JOptionPane(elements, JOptionPane.PLAIN_MESSAGE,
				JOptionPane.OK_CANCEL_OPTION, null, buttonLabels,
				buttonLabels[1]);
		setContentPane(contentPane);
		final JPanel buttonPanel = (JPanel) contentPane.getComponent(1);
		okButton = (JButton) buttonPanel.getComponent(0);
		okButton.setEnabled(false);

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(final WindowEvent windowEvent) {
				contentPane
						.setValue(Integer.valueOf(JOptionPane.CLOSED_OPTION));
			}
		});

		contentPane.addPropertyChangeListener(this);
		buttonOkay(okButton);
	}

	/**
	 * Setzt den Timeslot auf die Zeiteinheit die sich aus dem gegebenen
	 * Wochentag und der gegebenen Position ergibt. Fügt dazu alle Lehrer aus
	 * dem übergebenen Timeslot in das zugehörige subjectListModel ein.
	 * 
	 * @param weekday
	 *            Der Wochentag der Zeiteinheit
	 * @param position
	 *            die Position der Zeiteinheit
	 */
	public void setTimeslot(final Weekday weekday, final int position, final Object clazz) {
		try {
			Collection<Subject> subjects;
			timeslot = TimetableManager.getTimeslotAt(weekday, position, clazz);
			final Collection<Subject> subjectsInSlot = timeslot.getSubjects();
			subjects = SubjectManager.getAllSubjects();
			subjectListModel.clear();
			for (final Subject subject : subjects) {
				if (subjectsInSlot.contains(subject)) {
					subjectListModel.addSubject(subject);
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
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		final String prop = event.getPropertyName();

		if (isVisible()
				&& event.getSource() == contentPane
				&& (JOptionPane.VALUE_PROPERTY.equals(prop) || JOptionPane.INPUT_VALUE_PROPERTY
						.equals(prop))) {
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
		final int[] selectedIndices = subjectList.getSelectedIndices();
		for (final int index : selectedIndices) {
			final Subject subject = subjectListModel.getSubjectAt(index);
			try {
				TimetableManager.updateTimeslot(timeslot);
			} catch (DatasetException ex) {
				LOGGER.error("Exception while updating timeslot " + timeslot,
						ex);
				ErrorHandler.criticalDatasetError();
			}
		}
	}

	/**
	 * Löscht die Elemente des SubjectListModel und setzt den Dialog auf
	 * unsichtbar.
	 */
	public void clearAndHide() {
		subjectListModel.clear();
		setVisible(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event
	 * .ListSelectionEvent)
	 */
	@Override
	public void valueChanged(final ListSelectionEvent event) {
		if (isVisible()) {
			if (subjectList.getSelectedIndices().length > 0) {
				okButton.setEnabled(true);
				buttonOkay(okButton);
			} else {
				okButton.setEnabled(false);
			}
		}
	}

	private void buttonOkay(JButton b) {
		
		b.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {	
				JFrame fr = new JFrame();
				List<Subject> subjects = subjectListModel.getSubjects();
				EditSubject editsubject= new EditSubject(subjects.get(subjectList.getSelectedIndex()));
				System.out.println("EditSubject erstellt");
				fr.setLocation(500, 150);
				fr.add(editsubject, BorderLayout.CENTER);
				fr.pack();
				fr.setVisible(true);
				e = fr;
			}
		});
	}
}

